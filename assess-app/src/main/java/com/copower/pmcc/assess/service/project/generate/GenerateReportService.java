package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.report.*;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysSymbolListDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by kings on 2018-5-23.
 */
@Service
public class GenerateReportService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BaseReportService baseReportService;
    @Autowired
    private GenerateReportInfoService generateReportInfoService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseReportFieldService baseReportFieldService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private GenerateReportGroupService generateReportGroupService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    public List<SchemeAreaGroup> getAreaGroupList(Integer projectId) {
        return schemeAreaGroupService.getAreaGroupEnableByProjectId(projectId);
    }

    /**
     * 创建报告模板
     *
     * @param ids
     * @param generateReportInfo
     * @return
     * @throws Exception
     */
    public void createReportWord(String ids, GenerateReportInfo generateReportInfo, GenerateReportGroup reportGroup) throws Exception {
        if (StringUtils.isEmpty(ids) || generateReportInfo.getProjectPlanId() == null) {
            return;
        }
        String[] strings = ids.split(",");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportInfo.getProjectPlanId());
        if (projectPlan == null) {
            return;
        }
        generateReportInfoService.saveGenerateReportInfo(generateReportInfo);
        generateReportGroupService.saveAndUpdateGenerateReportGroup(reportGroup, false);
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(reportGroup.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class));
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        removeLocalTemFile();
        for (String string : strings) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(string));
            if (baseDataDic == null) {
                continue;
            }
            BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
            if (baseReportTemplate == null) {
                continue;
            }
            List<String> handlePath = fullReportHandlePath(baseReportTemplate, generateReportInfo, baseDataDic, reportGroup);
            if (CollectionUtils.isNotEmpty(handlePath)) {
                for (String path : handlePath) {
                    this.createBodySysAttachment(path, baseDataDic, sysAttachmentDtoList, reportGroup);
                }
            }
        }
    }

    private void removeLocalTemFile() {
        //必要的(否则垃圾会越来越多)
        File file = new File(baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()));
        if (file.isDirectory()) {
            //往上走三层在递归删除
            File dirFile = null;
            if (file.getParentFile().isDirectory()) {
                dirFile = file.getParentFile();
                if (file.getParentFile().getParentFile().isDirectory()) {
                    dirFile = file.getParentFile().getParentFile();
                    if (file.getParentFile().getParentFile().getParentFile().isDirectory()) {
                        dirFile = file.getParentFile().getParentFile().getParentFile();
                    }
                }
            }
            if (dirFile != null) {
                FileUtils.deleteDir(dirFile);
            }
        }
    }

    /**
     * report footer 报告的附件单独生成一个文件
     *
     * @param ids
     * @param generateReportInfo
     * @param reportGroup
     * @throws Exception
     */
    public void generateReportAttachment(String ids, GenerateReportInfo generateReportInfo, GenerateReportGroup reportGroup) throws Exception {
        if (StringUtils.isEmpty(ids) || generateReportInfo.getProjectPlanId() == null) {
            return;
        }
        String[] strings = ids.split(",");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportInfo.getProjectPlanId());
        if (projectPlan == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(reportGroup.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class));
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        removeLocalTemFile();
        for (String string : strings) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(string));
            if (baseDataDic == null) {
                continue;
            }
            BaseReportField baseReportField = null;
            String fieldName = baseDataDic.getFieldName();
            String lastStr = StringUtils.substringAfterLast(fieldName, ".");
            if (StringUtils.isBlank(lastStr)) {
                lastStr = StringUtils.substringAfterLast(fieldName, "\\.");
            }
            List<String> stringList = Arrays.asList(AssessDataDicKeyConstant.REPORT_ATTACHMENT_PREAUDIT, AssessDataDicKeyConstant.REPORT_ATTACHMENT_CONSULTATION, AssessDataDicKeyConstant.REPORT_ATTACHMENT_TECHNOLOGY, AssessDataDicKeyConstant.REPORT_ATTACHMENT_RESULT);
            for (String key : stringList) {
                if (StringUtils.contains(key, lastStr)) {
                    baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(key);
                }
            }
            if (baseReportField == null) {
                continue;
            }
            List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
            if (CollectionUtils.isEmpty(dtoList)) {
                throw new BusinessException("没有配置模板");
            }
            String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
            List<String> names = getReportEnums();
            ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(generateReportInfo.getProjectId()));
            GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, generateReportInfo.getAreaGroupId(), baseDataDic, reportGroup);
            //count 计数器,防止  枚举虽然定义了，但是没有写对应的方法，因此递归设置最多的次数
            int count = 0;
            //最大递归次数 , 最好是不要过大 (ps max-count 就是递归次数)
            final int max = 2;
            Map<String, String> textMap = Maps.newHashMap();
            Map<String, String> bookmarkMap = Maps.newHashMap();
            Map<String, String> fileMap = Maps.newHashMap();
            generateReplaceWord(names, textMap, bookmarkMap, fileMap, localPath, generateBaseDataService, generateReportInfo, baseDataDic, count, max, reportGroup);
            if (StringUtils.isNotBlank(localPath)) {
                this.createFooterSysAttachment(localPath, baseDataDic, sysAttachmentDtoList, reportGroup);
            }
        }
    }

    /**
     * 上传到erp形成附件id 主体
     *
     * @param path
     * @return
     */
    private void createBodySysAttachment(String path, BaseDataDic reportType, List<SysAttachmentDto> sysAttachmentDtoList, GenerateReportGroup reportGroup) throws Exception {
        String timeName = String.join("-", DateUtils.format(DateUtils.now(), DateUtils.DATE_CHINESE_PATTERN));
        String fileName = String.join("", reportType.getName(), timeName, ".", FilenameUtils.getExtension(path));
        String fieldsName = generateCommonMethod.getReportFieldsName(reportType.getFieldName(), reportGroup);
        createCommonSysAttachment(path, fieldsName, fileName, sysAttachmentDtoList, reportGroup);
    }

    /**
     * 上传到erp形成附件id 尾端 (只有附件)
     *
     * @param path
     * @return
     */
    private void createFooterSysAttachment(String path, BaseDataDic reportType, List<SysAttachmentDto> sysAttachmentDtoList, GenerateReportGroup reportGroup) throws Exception {
        String timeName = String.join("-", DateUtils.format(DateUtils.now(), DateUtils.DATE_CHINESE_PATTERN));
        String fileName = String.join("", reportType.getName(), "附件", timeName, ".", FilenameUtils.getExtension(path));
        String fieldsName = generateCommonMethod.getReportFooterFieldsName(reportType.getFieldName(), reportGroup);
        createCommonSysAttachment(path, fieldsName, fileName, sysAttachmentDtoList, reportGroup);
    }

    /**
     * 提出来的公共方法
     *
     * @param path
     * @param fieldsName
     * @param fileName
     * @param sysAttachmentDtoList
     * @param reportGroup
     * @throws Exception
     */
    private void createCommonSysAttachment(String path, String fieldsName, String fileName, List<SysAttachmentDto> sysAttachmentDtoList, GenerateReportGroup reportGroup) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(reportGroup.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class));
        File file = new File(path);
        sysAttachmentDto.setFileExtension(FilenameUtils.getExtension(file.getName()));//获取文件后缀名,FilenameUtils.getName()获取文件夹或文件的名称,FilenameUtils.isExtension()判断后缀是否是指定后缀(区分大小写)
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        sysAttachmentDto.setFileSize(org.apache.commons.io.FileUtils.sizeOfAsBigInteger(file).toString());
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setFieldsName(fieldsName);
        sysAttachmentDto.setFileName(fileName);
        //注意这里因为是linux 路径所以采用/ 或者使用Java自带的判断符号 windows下 WinNTFileSystem linux 下UnixFileSystem
        String ftpBasePath = baseAttachmentService.createFTPBasePath(DateUtils.formatDate(new Date(), "yyyy-MM"), DateUtils.formatNowToYMD(), commonService.thisUserAccount());
        sysAttachmentDto.setFilePath(ftpBasePath);
        sysAttachmentDto.setFtpFileName(baseAttachmentService.createNoRepeatFileName(sysAttachmentDto.getFileExtension()));
        try {
            ftpUtilsExtense.uploadFilesToFTP(ftpBasePath, new FileInputStream(file.getPath()), sysAttachmentDto.getFtpFileName());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "erp上传文件出错");
        }
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            if (reportGroup.getSymbolOperation().equals(ReportSymbolOperationEnum.GET.getKey()) || reportGroup.getSymbolOperation().equals(ReportSymbolOperationEnum.RESET.getKey())) {
                List<SysAttachmentDto> attachmentDtoList = LangUtils.filter(sysAttachmentDtoList, obj -> obj.getFieldsName().equals(sysAttachmentDto.getFieldsName()));
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    List<Integer> integerList = LangUtils.transform(attachmentDtoList, obj -> obj.getId());
                    baseAttachmentService.deleteAttachment(integerList);
                }
            }
        }
        baseAttachmentService.addAttachment(sysAttachmentDto);
    }

    /**
     * 报告字段 all
     *
     * @return
     */
    private List<String> getReportEnums() {
        List<String> names = Lists.newArrayList();
        //公共报告 字段
        Arrays.asList(ReportFieldCommonEnum.values()).forEach(oo -> names.add(oo.getName()));
        //基础报告 字段
        Arrays.asList(ReportFieldEnum.values()).forEach(oo -> names.add(oo.getName()));
        //收益法 字段
        Arrays.asList(ReportFieldMdIncomeEnum.values()).forEach(oo -> names.add(oo.getName()));
        //基准地 字段
        Arrays.asList(ReportFieldMdBaseLandPriceEnum.values()).forEach(oo -> names.add(oo.getName()));
        //比较法 字段
        Arrays.asList(ReportFieldCompareEnum.values()).forEach(oo -> names.add(oo.getName()));
        //假设法 字段
        Arrays.asList(ReportFieldDevelopmentEnum.values()).forEach(oo -> names.add(oo.getName()));
        //成本法 字段
        Arrays.asList(ReportFieldCostMethodEnum.values()).forEach(oo -> names.add(oo.getName()));
        //银行 字段
        Arrays.asList(ReportFieldUniversalBankEnum.values()).forEach(oo -> names.add(oo.getName()));
        //工商银行 字段
        Arrays.asList(ReportFieldGongshangBankEnum.values()).forEach(oo -> names.add(oo.getName()));
        //建设银行 字段
        Arrays.asList(ReportFieldJiansheBankEnum.values()).forEach(oo -> names.add(oo.getName()));
        //司法字段
        Arrays.asList(ReportFieldSifaEnum.values()).forEach(oo -> names.add(oo.getName()));
        //土地比较法 字段
        Arrays.asList(ReportFieldLandCompareEnum.values()).forEach(oo -> names.add(oo.getName()));
        return names;
    }


    /**
     * 创建报告模板(具体)
     *
     * @param baseReportTemplate
     * @param generateReportInfo
     * @param reportType
     * @return
     * @throws Exception
     */
    private List<String> fullReportHandlePath(BaseReportTemplate baseReportTemplate, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {
        List<String> paths = new ArrayList<>();
        List<String> names = getReportEnums();
        SysAttachmentDto query = new SysAttachmentDto();
        //这里很重要
        //使用曾经使用过的word进行替换
        String[] keys = new String[]{ReportSymbolOperationEnum.GET.getKey(), ReportSymbolOperationEnum.RESET.getKey()};
        for (int i = 0; i < keys.length; i++) {
            if (Objects.equal(reportGroup.getSymbolOperation(), keys[i])) {
                query.setTableId(reportGroup.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class));
                query.setFieldsName(generateCommonMethod.getReportFieldsName(reportType.getFieldName(), reportGroup));
            }
        }
        //使用报告模板
        if (Objects.equal(reportGroup.getSymbolOperation(), ReportSymbolOperationEnum.NONE.getKey())) {
            query.setTableId(baseReportTemplate.getId());
            query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        }
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                String local = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                paths.add(local);
            }
        }
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            if (Objects.equal(query.getTableName(), FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class))) {
                throw new Exception("先生成报告,再拿号或者重新拿号!");
            }
        }
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportInfo.getProjectPlanId());
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(generateReportInfo.getProjectId()));
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, generateReportInfo.getAreaGroupId(), reportType, reportGroup);
        //重新拿号
        if (Objects.equal(reportGroup.getSymbolOperation(), ReportSymbolOperationEnum.RESET.getKey())) {
            AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfoVo.getProjectCategoryId());
            ProjectNumberRecord numberRecord = projectNumberRecordService.getProjectNumberRecord(generateReportInfo.getProjectId(), generateReportInfo.getAreaGroupId(), reportGroup.getId(), assessProjectType, reportType.getId());
            if (numberRecord != null) {
                numberRecord.setBisDelete(true);
                projectNumberRecordService.updateProjectNumberRecord(numberRecord);
                //将已经老旧的文号替换为最新的文号
                SysSymbolListDto symbolListDto = projectNumberRecordService.getReportNumber(projectInfoVo, generateReportInfo.getAreaGroupId(), reportGroup.getId(), assessProjectType, reportType.getId(), false);
                String value = symbolListDto.getSymbol();
                for (String dir : paths) {
                    AsposeUtils.replaceText(dir, numberRecord.getNumberValue(), value);
                    //替换编号
                    AsposeUtils.replaceText(dir, projectNumberRecordService.getWordNumber2(numberRecord.getNumberValue()), projectNumberRecordService.getWordNumber2(value));
                }
            }
            //重新设回拿号标志
//            reportGroup.setSymbolOperation(ReportSymbolOperationEnum.GET.getKey());
        }
        //评估类型(添加一个封面)
        if (generateReportInfo.getAssessCategory() != null) {
            for (String dir : paths) {
                generateBaseDataService.handleReportCover(generateReportInfo, dir, baseAttachmentService, baseReportFieldService);
            }
        }
        //count 计数器,防止  枚举虽然定义了，但是没有写对应的方法，因此递归设置最多的次数
        int count = 0;
        //最大递归次数 , 最好是不要过大 (ps max-count 就是递归次数)
        final int max = 2;
        Map<String, String> textMap = Maps.newHashMap();
        Map<String, String> bookmarkMap = Maps.newHashMap();
        Map<String, String> fileMap = Maps.newHashMap();
        for (String dir : paths) {
            generateReplaceWord(names, textMap, bookmarkMap, fileMap, dir, generateBaseDataService, generateReportInfo, reportType, count, max, reportGroup);
        }
        return paths;
    }

    /**
     * 循环替换操作
     *
     * @param tempDir
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @param count
     * @param max
     * @return
     * @throws Exception
     */
    private String generateReplaceWord(List<String> names, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, String tempDir, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, int count, final int max, GenerateReportGroup reportGroup) throws Exception {
        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = getBookmarkAndRegexDtoHashSet(tempDir);
        Set<String> compareHashSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(bookmarkAndRegexDtoHashSet)) {
            bookmarkAndRegexDtoHashSet.forEach(bookmarkAndRegex -> {
                String name = StringUtils.isNotBlank(bookmarkAndRegex.getChineseName()) ? bookmarkAndRegex.getChineseName() : bookmarkAndRegex.getName();
                //必须在枚举中存在的我们才收集
                if (names.stream().anyMatch(s -> Objects.equal(s, name))) {
                    compareHashSet.add(name);
                }
            });
        }
        if (CollectionUtils.isNotEmpty(compareHashSet)) {
            count++;
            Iterator<String> iterator = compareHashSet.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                if (StringUtils.isBlank(name)) {
                    continue;
                }
                try {
                    //分组来获取值,约定一个名称只属于一个组 如公共文号属于公共组,那么在公共组装方法中找到了要组装的数据,那么就没有必要继续循环下去了

                    //像 公共和 基础字段的组尽量放前面 , 因为大部分数据都是这两个组中,因此在这两个组中找到了那么就没必要在下面继续去循环查找了

                    //以后有时间可以把组里面的if 判断全部改为switch
                    if (GenerateReportAssembleHelp.assembleCommonMap(name, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType, reportGroup)) {
                        continue;
                    }

                    if (GenerateReportAssembleHelp.assembleBaseMap(name, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType, reportGroup)) {
                        continue;
                    }

                    if (GenerateReportAssembleHelp.assembleSifaMap(name, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType, reportGroup)) {
                        continue;
                    }

                    if (GenerateReportAssembleHelp.assembleGongshangMap(name, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType, reportGroup)) {
                        continue;
                    }

                    if (GenerateReportAssembleHelp.assembleJiansheMap(name, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType, reportGroup)) {
                        continue;
                    }

                    if (GenerateReportAssembleHelp.assembleUniversalBankMap(name, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType, reportGroup)) {
                        continue;
                    }

                    if (GenerateReportAssembleHelp.assembleOtherMap(name, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType, reportGroup)) {
                        continue;
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
            replaceWord(tempDir, textMap, bookmarkMap, fileMap);
            if (count >= max) {
                return tempDir;
            }
            //递归回去 判断是否可以跳出循环
            return generateReplaceWord(names, textMap, bookmarkMap, fileMap, tempDir, generateBaseDataService, generateReportInfo, reportType, count, max, reportGroup);
        } else {
            return tempDir;
        }
    }


    private void replaceWord(String localPath, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap) throws Exception {
        Map<String, String> errorMap2 = Maps.newHashMap();
        List<String> typeList = Arrays.asList("\r", "\n");
        if (!textMap.isEmpty()) {
            //特殊处理一些数据
            textMap.forEach((key, value) -> {
                typeList.forEach(s -> {
                    if (StringUtils.contains(value, s)) {
                        errorMap2.put(key, value);
                    }
                });
            });
        }
        if (!errorMap2.isEmpty()) {
            try {
                replaceHandleError(errorMap2, localPath);
            } catch (Exception e) {
                baseService.writeExceptionInfo(e, "报告生成");
            }
        }
        if (!fileMap.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, fileMap);
        }
        if (!textMap.isEmpty()) {
            Map<String, String> errorMap = AsposeUtils.replaceText(localPath, textMap);
            errorMap2.putAll(errorMap);
        }
        if (!bookmarkMap.isEmpty()) {
            AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
        }
    }


    private void replaceHandleError(Map<String, String> errorMap, String localPath) throws Exception {
        if (!errorMap.isEmpty()) {
            Map<String, String> changeMap = Maps.newHashMap();
            Map<String, String> transMap = Maps.newHashMap();
            errorMap.forEach((key, value) -> {
                String text = RandomStringUtils.randomAlphabetic(6);
                changeMap.put(key, text);
                transMap.put(text, value);
            });
            //使用 apache poi 处理
            AsposeUtils.replaceText(localPath, changeMap);
            //文档无法识别
            PoiUtils.replaceText(transMap, localPath);
        }
    }

    private Set<BookmarkAndRegexDto> getBookmarkAndRegexDtoHashSet(String tempDir) throws Exception {
        Document document = new Document(tempDir);
        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = Sets.newHashSet();
        List<String> stringList = Lists.newArrayList();
        String text = null;
        try {
            text = PoiUtils.getWordContent(tempDir);
        } catch (Exception e) {
            try {
                text = AsposeUtils.getWordTableText(document);
            } catch (Exception e1) {
                baseService.writeExceptionInfo(e1, "java.lang.IndexOutOfBoundsException,或者是aspose数据越界 ");
            }
        }
        if (StringUtils.isNotEmpty(text)) {
            //取出word中表格数据
            Matcher m = Pattern.compile(AsposeUtils.reportReplaceString).matcher(text);
            while (m.find()) {
                stringList.add(m.group());
            }
        }
        //获取普通段落
        List<String> regexList = new ArrayList<>();
        try {
            List<String> regexList2 = AsposeUtils.getRegexList(document, null);
            regexList.addAll(regexList2);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "java.lang.IndexOutOfBoundsException,或者是aspose数据越界 ");
        }
        if (CollectionUtils.isNotEmpty(regexList)) {
            stringList.addAll(regexList);
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            //去除重复
            List<String> strings = stringList.stream().distinct().collect(Collectors.toList());
            stringList.clear();
            stringList.addAll(strings);
        }
        //获取待替换文本的集合
        List<String> regexS = GenerateCommonMethod.specialTreatment(stringList);
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                String name = AsposeUtils.getChinese(bookmarkCollection.get(i).getName());
                if (StringUtils.isEmpty(name)) {
                    name = bookmarkCollection.get(i).getName();
                }
                regexDto.setName(name).setChineseName(name);
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        if (CollectionUtils.isNotEmpty(regexS)) {
            for (String name : regexS) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setName(name).setChineseName(name);
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        return bookmarkAndRegexDtoHashSet;
    }

    /**
     * 仅仅生成结果集一个sheet
     *
     * @param fieldsName
     * @param tableName
     * @param projectId
     * @throws Exception
     */
    public void resultSheetReportNew(String fieldsName, String tableName, Integer projectId) throws Exception {
        List<SchemeAreaGroup> schemeAreaGroups = schemeAreaGroupService.getAreaGroupAllByProjectId(projectId);
        if (CollectionUtils.isEmpty(schemeAreaGroups)) {
            throw new Exception("评估方案编制没有设置");
        }
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        for (SchemeAreaGroup schemeAreaGroup : schemeAreaGroups) {
            GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, schemeAreaGroup.getId(), new BaseDataDic(), new GenerateReportGroup());
            List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(schemeAreaGroup.getId());
            schemeJudgeObjectList = schemeJudgeObjectService.transformFullJudgeList(schemeJudgeObjectList);
            String path = generateBaseDataService.getjudgeBuildResultSurveySheet(schemeJudgeObjectList, projectInfoVo);
            resultSheetReportCreateSysAttachmentNew(schemeAreaGroup.getAreaName(), path, fieldsName, tableName, projectId, false);
        }
    }

    private void resultSheetReportCreateSysAttachmentNew(String prefix, String path, String fieldsName, String tableName, Integer tableId, boolean delete) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(tableName);
        sysAttachmentDto.setFieldsName(fieldsName);
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        if (delete) {
            List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
            if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                List<SysAttachmentDto> attachmentDtoList = LangUtils.filter(sysAttachmentDtoList, obj -> obj.getFieldsName().equals(sysAttachmentDto.getFieldsName()));
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    List<Integer> integerList = LangUtils.transform(attachmentDtoList, obj -> obj.getId());
                    baseAttachmentService.deleteAttachment(integerList);
                }
            }
        }
        File file = new File(path);
        String fileName = null;
        if (StringUtils.isNotBlank(prefix)) {
            fileName = String.join("", prefix, DateUtils.format(DateUtils.now(), DateUtils.DATETIME_SHORT_PATTERN), "结果集.", "doc");
        } else {
            fileName = String.join("", DateUtils.format(DateUtils.now(), DateUtils.DATETIME_SHORT_PATTERN), "结果集.", "doc");
        }
        sysAttachmentDto.setFileName(fileName);
        sysAttachmentDto.setFileExtension(FilenameUtils.getExtension(file.getName()));// sysAttachmentDto.setFileExtension(file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()))
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        sysAttachmentDto.setFileSize(org.apache.commons.io.FileUtils.sizeOfAsBigInteger(file).toString());
        //注意这里因为是linux 路径所以采用/ 或者使用Java自带的判断符号 windows下 WinNTFileSystem linux 下UnixFileSystem
        String ftpBasePath = baseAttachmentService.createFTPBasePath(DateUtils.formatDate(new Date(), "yyyy-MM"), DateUtils.formatNowToYMD(), commonService.thisUserAccount());
        sysAttachmentDto.setFilePath(ftpBasePath);
        sysAttachmentDto.setFtpFileName(baseAttachmentService.createNoRepeatFileName(sysAttachmentDto.getFileExtension()));
        try {
            ftpUtilsExtense.uploadFilesToFTP(ftpBasePath, new FileInputStream(file.getPath()), sysAttachmentDto.getFtpFileName());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "erp上传文件出错");
        }
        baseAttachmentService.addAttachment(sysAttachmentDto);
    }


}
