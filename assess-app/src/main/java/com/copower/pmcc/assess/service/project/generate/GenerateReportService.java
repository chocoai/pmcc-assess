package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
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
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysSymbolListDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
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
import java.math.BigDecimal;
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
    public void createReportWord(String ids, GenerateReportInfo generateReportInfo) throws Exception {
        if (StringUtils.isEmpty(ids) || generateReportInfo.getProjectPlanId() == null) {
            return;
        }
        String[] strings = ids.split(",");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportInfo.getProjectPlanId());
        if (projectPlan == null) {
            return;
        }
        generateReportInfoService.saveGenerateReportInfo(generateReportInfo);
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(generateReportInfo.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportInfo.class));
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
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
        for (String string : strings) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(string));
            if (baseDataDic == null) {
                continue;
            }
            BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
            if (baseReportTemplate == null) {
                continue;
            }
            String path = this.fullReportHandlePath(baseReportTemplate, generateReportInfo, baseDataDic);
            if (StringUtils.isNotBlank(path)) {
                this.createSysAttachment(path, generateReportInfo, baseDataDic, sysAttachmentDtoList);
            }
        }
    }

    /**
     * 上传到erp形成附件id
     *
     * @param path
     * @return
     */
    private void createSysAttachment(String path, GenerateReportInfo generateReportInfo, BaseDataDic reportType, List<SysAttachmentDto> sysAttachmentDtoList) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(generateReportInfo.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportInfo.class));
        File file = new File(path);
        sysAttachmentDto.setFileExtension(FilenameUtils.getExtension(file.getName()));//获取文件后缀名,FilenameUtils.getName()获取文件夹或文件的名称,FilenameUtils.isExtension()判断后缀是否是指定后缀(区分大小写)
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        sysAttachmentDto.setFileSize(org.apache.commons.io.FileUtils.sizeOfAsBigInteger(file).toString());
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setFieldsName(generateCommonMethod.getReportFieldsName(reportType.getFieldName(), generateReportInfo.getAreaGroupId()));
        String timeName = String.join("-", DateUtils.format(DateUtils.now(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(DateUtils.now(), DateUtils.HOUR_MINUTE_CHINESE_PATTERN));
        String fileName = String.join("", reportType.getName(), timeName, ".", FilenameUtils.getExtension(file.getName()));
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
            sysAttachmentDtoList.stream().forEach(attachmentDto -> {
                if (Objects.equal(attachmentDto.getFieldsName(), sysAttachmentDto.getFieldsName())) {
                    baseAttachmentService.deleteAttachmentByDto(attachmentDto);
                }
            });
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
    private String fullReportHandlePath(BaseReportTemplate baseReportTemplate, GenerateReportInfo generateReportInfo, BaseDataDic reportType) throws Exception {
        List<String> names = getReportEnums();
        String dir = null;
        SysAttachmentDto query = new SysAttachmentDto();
        //这里很重要
        //使用曾经使用过的word进行替换
        String[] keys = new String[]{ReportSymbolOperationEnum.GET.getKey(), ReportSymbolOperationEnum.RESET.getKey()};
        for (int i = 0; i < keys.length; i++) {
            if (Objects.equal(generateReportInfo.getSymbolOperation(), keys[i])) {
                query.setTableId(generateReportInfo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportInfo.class));
                query.setFieldsName(generateCommonMethod.getReportFieldsName(reportType.getFieldName(), generateReportInfo.getAreaGroupId()));
            }
        }
        //使用报告模板
        if (Objects.equal(generateReportInfo.getSymbolOperation(), ReportSymbolOperationEnum.NONE.getKey())) {
            query.setTableId(baseReportTemplate.getId());
            query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        }
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            dir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDtoList.stream().findFirst().get().getId());
        }
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            if (Objects.equal(query.getTableName(), FormatUtils.entityNameConvertToTableName(GenerateReportInfo.class))) {
                throw new Exception("先生成报告,再拿号或者重新拿号!");
            }
        }
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportInfo.getProjectPlanId());
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(generateReportInfo.getProjectId()));
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, generateReportInfo.getAreaGroupId(), baseReportTemplate, projectPlan);
        //重新拿号
        if (Objects.equal(generateReportInfo.getSymbolOperation(), ReportSymbolOperationEnum.RESET.getKey())) {
            AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfoVo.getProjectCategoryId());
            ProjectNumberRecord numberRecord = projectNumberRecordService.getProjectNumberRecord(generateReportInfo.getProjectId(), generateReportInfo.getAreaGroupId(), assessProjectType, reportType.getId());
            if (numberRecord != null) {
                numberRecord.setBisDelete(true);
                projectNumberRecordService.updateProjectNumberRecord(numberRecord);
                //将已经老旧的文号替换为最新的文号
                SysSymbolListDto symbolListDto = projectNumberRecordService.getReportNumber(projectInfoVo, generateReportInfo.getAreaGroupId(), assessProjectType, reportType.getId(), false);
                String value = symbolListDto.getSymbol();
                AsposeUtils.replaceText(dir, numberRecord.getNumberValue(), value);
                //替换编号
                AsposeUtils.replaceText(dir, projectNumberRecordService.getWordNumber2(numberRecord.getNumberValue()), projectNumberRecordService.getWordNumber2(value));
            }
            //重新设回拿号标志
            generateReportInfo.setSymbolOperation(ReportSymbolOperationEnum.GET.getKey());
        }
        //评估类型(添加一个封面)
        if (generateReportInfo.getAssessCategory() != null) {
            generateBaseDataService.handleReportCover(generateReportInfo, dir, baseAttachmentService, baseReportFieldService);
        }
        //count 计数器,防止  枚举虽然定义了，但是没有写对应的方法，因此递归设置最多的次数
        int count = 0;
        //最大递归次数 , 最好是不要过大 (ps max-count 就是递归次数)
        final int max = 2;
        Map<String, String> textMap = Maps.newHashMap();
        Map<String, String> bookmarkMap = Maps.newHashMap();
        Map<String, String> fileMap = Maps.newHashMap();
        generateReplaceWord(names, textMap, bookmarkMap, fileMap, dir, generateBaseDataService, generateReportInfo, reportType.getFieldName(), count, max);
        return dir;
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
    private String generateReplaceWord(List<String> names, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, String tempDir, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, String reportType, int count, final int max) throws Exception {
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
                String next = iterator.next();
                if (StringUtils.isBlank(next)) {
                    continue;
                }
                try {
                    handleReport(next, textMap, bookmarkMap, fileMap, generateBaseDataService, generateReportInfo, reportType);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
            replaceWord(tempDir, textMap, bookmarkMap, fileMap);
            if (count >= max) {
                return tempDir;
            }
            //递归回去 判断是否可以跳出循环
            return generateReplaceWord(names, textMap, bookmarkMap, fileMap, tempDir, generateBaseDataService, generateReportInfo, reportType, count, max);
        } else {
            return tempDir;
        }
    }


    private void handleReport(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, String reportType) throws Exception {
        //文号
        if (Objects.equal(ReportFieldCommonEnum.CommonReportNumber.getName(), name)) {
            if (Objects.equal(generateReportInfo.getSymbolOperation(), ReportSymbolOperationEnum.GET.getKey())) {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWordNumber());
            }
        }
        //查询码
        if (Objects.equal(ReportFieldCommonEnum.CommonQueryCode.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateReportInfo.getQueryCode());
        }
        //备案号
        if (Objects.equal(ReportFieldCommonEnum.CommonRecordNo.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateReportInfo.getRecordNo());
        }
        //备案日期
        if (Objects.equal(ReportFieldCommonEnum.CommonRecordDate.getName(), name)) {
            String reportIssuanceStr = "";
            if (generateReportInfo.getRecordDate() != null) {
                reportIssuanceStr = DateUtils.format(generateReportInfo.getRecordDate(), DateUtils.DATE_CHINESE_PATTERN);
            }
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportIssuanceStr);
        }
        //报告编号
        if (Objects.equal(ReportFieldCommonEnum.CommonReportNumbering.getName(), name)) {
            if (Objects.equal(generateReportInfo.getSymbolOperation(), ReportSymbolOperationEnum.GET.getKey())) {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWordNumber2());
            }
        }
        //报告二维码
        if (Objects.equal(ReportFieldCommonEnum.CommonReportQrcode.getName(), name)) {
            if (Objects.equal(generateReportInfo.getSymbolOperation(), ReportSymbolOperationEnum.GET.getKey())) {
                generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportQrcode(generateReportInfo, reportType));
            }
        }
        //报告类别
        if (Objects.equal(ReportFieldCommonEnum.CommonReportingCategories.getName(), name)) {
            if (StringUtils.isNotBlank(reportType)) {
                BaseDataDic reportTypeDic = baseDataDicService.getCacheDataDicByFieldName(reportType);
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportTypeDic.getName());
            }
        }
        //报告出具日期
        if (Objects.equal(ReportFieldCommonEnum.CommonReportIssuanceDate.getName(), name)) {
            String reportIssuanceStr = null;
            if (generateReportInfo.getReportIssuanceDate() != null) {
                reportIssuanceStr = DateUtils.format(generateReportInfo.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
            } else {
                reportIssuanceStr = DateUtils.format(new Date(), DateUtils.DATE_CHINESE_PATTERN);
            }
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportIssuanceStr);
        }
        //协助工作人员
        if (Objects.equal(ReportFieldCommonEnum.CommonAssistanceStaff.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssistanceStaff(generateReportInfo));
        }
        //评估假设
        if (Objects.equal(ReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), name)) {
            String hypothesis = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.HYPOTHESIS);
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, hypothesis);
        }
        //评估依据
        if (Objects.equal(ReportFieldEnum.EVALUATION_BASIS.getName(), name)) {
            String basic = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.BASIS);
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, basic);
        }
        //评估原则
        if (Objects.equal(ReportFieldEnum.EVALUATION_PRINCIPLE.getName(), name)) {
            String principle = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.PRINCIPLE);
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, principle);
        }
        //变现能力分析
        if (Objects.equal(ReportFieldEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRisk(SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_LIQUIDITY));
        }
        //银行变现能力分析
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRiskLittle());
        }
        //风险提示
        if (Objects.equal(ReportFieldEnum.ANALYSIS_CATEGORY_RISK.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRisk(SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_RISK));
        }
        //社会经济发展概况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_DEVELOPMENT.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_DEVELOPMENT));
        }
        //房地产市场总体概况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_GENERAL.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_GENERAL));
        }
        //同类房地产市场状况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_MARKET.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_MARKET));
        }
        //同类房地产市场版块状况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_BLOCK.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_BLOCK));
        }
        //背景估价对象区域物业总体状况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_PROPERTY.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_PROPERTY));
        }
        //特别提示
        if (Objects.equal(ReportFieldCommonEnum.CommonHotTip.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip2(0));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheHotTipBank.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip2(2));
        }
        //作业结束时间
        if (Objects.equal(ReportFieldCommonEnum.CommonHomeWorkEndTime.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkEndTime(generateReportInfo.getHomeWorkEndTime()));
        }
        //作业开始时间
        if (Objects.equal(ReportFieldCommonEnum.CommonHomeWorkStartTime.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkStartTime());
        }
        //现场查勘期
        if (Objects.equal(ReportFieldEnum.surveyExamineDate.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineDate(generateReportInfo.getInvestigationsStartDate(), generateReportInfo.getInvestigationsEndDate()));
        }
        //现场查勘人员
        if (Objects.equal(ReportFieldEnum.surveyExamineCreate.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineCreate());
        }
        //房地产估价机构营业执照复印件
        if (Objects.equal(ReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency());
        }
        //房地产估价机构资质证书复印件
        if (Objects.equal(ReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution());
        }
        //机构住所
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationAddress.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getOrganizationAddress();
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构名称
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationName.getName(), name)
                || Objects.equal(ReportFieldCommonEnum.CommonXIEHE_RealEstateValuationAgencyHouse.getName(), name)
                || Objects.equal(ReportFieldCommonEnum.CommonXIEHE_RealEstateValuationAgency.getName(), name)) {
            if (StringUtils.isNotEmpty(textMap.get(name))) return;
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getOrganizationName();
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构名称法定代表人
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_legalRepresentative.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getLegalRepresentative();
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构工商注册号
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_registeredNo.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getRegisteredNo();
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构资质等级
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationRank.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getOrganizationRank();
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构证书号
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_certificateNo.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getCertificateNo();
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //证书有效期
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_certificateEffectiveDate.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getCertificateEffectiveDate();
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //房地产估价机构信息
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationHouseInfo.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getXIEHE_organizationInfo());
        }
        //估价机构信息
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationInfo.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getXIEHE_organizationInfo());
        }
        //经营范围
        if (Objects.equal(ReportFieldEnum.BusinessScope.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBusinessScope());
        }
        //注册房产估价师及注册号
        if (Objects.equal(ReportFieldCommonEnum.CommonRegisteredHouseRealEstateValuerAndNumber.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerAndNumber(generateReportInfo));
        }
        //注册估价师及注册号
        if (Objects.equal(ReportFieldCommonEnum.CommonRegisteredRealEstateValuerAndNumber.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerAndNumber(generateReportInfo));
        }
        //注册估价师
        if (Objects.equal(ReportFieldCommonEnum.CommonRegisteredRealEstateValuer.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuer(generateReportInfo));
        }
        //注册房地产估价师注册证书复印件
        if (Objects.equal(ReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(generateReportInfo));
        }
        //房地产总价
        if (Objects.equal(ReportFieldEnum.TotalRealEstatePrice.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalRealEstatePrice());
        }
        //房地产总价大写金额
        if (Objects.equal(ReportFieldEnum.CapitalizationAmount.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCapitalizationAmount());
        }
        //外聘专家工作概况
        if (Objects.equal(ReportFieldEnum.ExpertWorkOverview.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getExpertWorkOverview());
        }
        //共有权情况
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralCo_ownership.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCo_ownership());
        }
        //估价对象详细测算过程( 收益法 , 市场比较法)
        if (Objects.equal(ReportFieldEnum.DetailedCalculationProcessValuationObject.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDetailedCalculationProcessValuationObject());
        }
        //收益法租赁限制说明
        if (Objects.equal(ReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTenancyrestrictionRemark());
        }
        //出具报告城市
        if (Objects.equal(ReportFieldEnum.ReportArea.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAreaName());
        }
        //价值类型
        if (Objects.equal(ReportFieldCommonEnum.CommonValueType.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueType());
        }
        //物业类型
        if (Objects.equal(ReportFieldEnum.PROPERTY_TYPE.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPROPERTY_TYPE());
        }
        //价值类型描述
        if (Objects.equal(ReportFieldCommonEnum.CommonValueTypeDesc.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTypeDesc());
        }
        //设定用途
        if (Objects.equal(ReportFieldCommonEnum.CommonSetUse.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(true));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheSetUse.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(false));
        }
        //坐落
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralSeat.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeatText());
        }
        //银行通用权证号
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralCERT_NAME.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSchemeJudgeObjectCertText());
        }
        //证载用途
        if (Objects.equal(ReportFieldCommonEnum.CommonCertificationPurpose.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses(true));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheCertificationPurpose.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses(false));
        }
        //房产类型
        if (Objects.equal(ReportFieldEnum.HouseType.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(true));
        }
        //土地 实际用途
        if (Objects.equal(ReportFieldCommonEnum.CommonPracticalUse.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPracticalUse());
        }
        //评估面积
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAssessArea.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessArea());
        }
        //评估单价
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAssessPrice.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessPrice());
        }
        //评估总价分述
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheAssessPriceClassification.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessPriceClassification());
        }
        //评估总价
        if (Objects.equal(ReportFieldCommonEnum.CommonAssessTotal.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessAssessTotal());
        }
        //评估总价大写
        if (Objects.equal(ReportFieldCommonEnum.CommonAssessTotalRMB.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessAssessTotalAssessTotalRMB());
        }
        //登记时间
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralRegistrationDate.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegistrationDate());
        }
        //房屋结构 建筑结构
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralHousingStructure.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildingStructureCategory());
        }
        //总层数
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFloorCount.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFloorCount());
        }
        //楼盘名称
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralEstateName.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEstateName());
        }
        //房屋性质
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralhouseNature.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHouseNature());
        }
        //房屋所有权人
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralOwnership.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOwnership());
        }
        //户型
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheunitType.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordUnitType());
        }
        //装修状况
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheDecorationStatus.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDecorationStatus());
        }
        //填发单位
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFillingUnit.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFillingUnit());
        }
        //附记
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAttachmentReark.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAttachmentReark());
        }
        //建成年代
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralBeCompletedTimeGetInteger.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBeCompletedTimeGetInteger());
        }
        //楼层
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFloor.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordFloor());
        }

        //当前年份
        if (Objects.equal(ReportFieldCommonEnum.CommonThisYear.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getThisYear());
        }
        //建筑面积
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralBuildArea.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildArea());
        }
        //套内建筑面积
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralCoverArea.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCoverArea());
        }
        //委托单位
        if (Objects.equal(ReportFieldCommonEnum.CommonEntrustedUnit.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEntrustmentUnit());
        }
        //档案保管号
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheArchivesDepositNumber.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentGroundNum());
        }
        //层户数
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLayerNumber.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLayerNumber());
        }
        //朝向
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralOrientation.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOrientation());
        }
        //建行个贷层高
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStoreyHeight.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStoreyHeight());
        }
        //建行个贷其它
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralNetAssessmentOther.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentOther());
        }
        //建行个贷丘地号
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralNetAssessmentGroundNum.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentGroundNum());
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralexteriorWallDecorate.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOutfitDecorate1());
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLobbyDecorate.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOutfitDecorate2());
        }
        //地基及墙面
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFoundationAndWall.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFoundationAndWall());
        }
        //使用状况
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralUsageStatus.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUsageStatus());
        }
        //委托目的
        if (Objects.equal(ReportFieldCommonEnum.CommonDelegatePurpose.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDelegatePurpose());
        }
        //委托目的表述
        if (Objects.equal(ReportFieldCommonEnum.CommonStatementPurposeEntrustment.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatementPurposeEntrustment());
        }
        //评估方法 ,估价对象评估方法
        if (Objects.equal(ReportFieldCommonEnum.CommonEvaluationMethod.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethod());
        }
        //出租或占用情况
        if (Objects.equal(ReportFieldEnum.rentalPossessionDesc.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRentalPossessionDesc());
        }
        //价值时点
        if (Objects.equal(ReportFieldCommonEnum.CommonValueTimePoint.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePoint());
        }
        //价值时点说明
        if (Objects.equal(ReportFieldCommonEnum.CommonValueTimePointRemark.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePointRemark());
        }
        //申报启用表单类型
        if (Objects.equal(ReportFieldEnum.DecalreFormTypeAll.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTypesFormEnabledDeclarationOffice());
        }
        //估价技术思路
        if (Objects.equal(ReportFieldCommonEnum.CommonEvaluationThink.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationThink());
        }
        //估价对象适用的估价方法
        if (Objects.equal(ReportFieldEnum.SelectionValuationMethod.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionValuationMethod());
        }
        //估价对象不适用的估价方法
        if (Objects.equal(ReportFieldEnum.NotSelectionValuationMethod.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNotSelectionValuationMethod());
        }
        //分类评估单价计算试
        if (Objects.equal(ReportFieldEnum.EvaluationExpression.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationExpression());
        }
        //变现分析税费
        if (Objects.equal(ReportFieldEnum.LIQUIDATION_ANALYSIS.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidationAnalysis());
        }
        //法定优先受偿款
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursement.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(0));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursementMortgage.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(1));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursementEngineering.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(2));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursementOther.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(3));
        }
        //抵押价值总金额
        if (Objects.equal(ReportFieldEnum.totalAmountMortgageValue.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValue());
        }
        //抵押价值总金额大写
        if (Objects.equal(ReportFieldEnum.totalAmountMortgageValueCapitalization.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValueCapitalization());
        }
        //他权类别
        if (Objects.equal(ReportFieldEnum.HisRightType.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightType(false));
        }
        //他权类别及明细
        if (Objects.equal(ReportFieldEnum.HisRightTypeAndDetail.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightType(true));
        }
        //最佳利用方式
        if (Objects.equal(ReportFieldEnum.BestUseDesc.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOptimumUtilizationMode());
        }
        //法定优先受偿款总金额
        if (Objects.equal(ReportFieldCommonEnum.CommonStatutoryPriorityAmountTotal.getName(), name)) {
            BigDecimal bigDecimal = generateBaseDataService.getSchemeReimbursementKnowTotalPrice();
            if (bigDecimal.doubleValue() > 0) {
                bigDecimal = bigDecimal.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, bigDecimal.toString());
        }
        //估价对象区位状况表
        if (Objects.equal(ReportFieldEnum.JudgeObjectAreaStatusSheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectAreaStatusSheet());
        }
        //估价土地实体状况表
        if (Objects.equal(ReportFieldEnum.JudgeObjectLandStateSheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLandStateSheet());
        }
        //估价对象建筑实体状况表
        if (Objects.equal(ReportFieldEnum.JudgeBuildLandStateSheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeBuildLandStateSheet());
        }
        //估价对象权益状况表
        if (Objects.equal(ReportFieldEnum.JudgeObjectEquitySheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectEquitySheet());
        }
        //汇总表
        if (Objects.equal(ReportFieldEnum.judgeSummarySheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeSummarySheet());
        }
        //建行预评数据表格
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheCCB_Pre_Evaluation_Data_Form.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCCB_Pre_Evaluation_Data_FormSheet());
        }
        //估价结果一览表
        if (Objects.equal(ReportFieldEnum.JudgeBuildResultSurveySheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeBuildResultSurveySheet(true));
        }
        //估价结果一览表不含坐落
        if (Objects.equal(ReportFieldEnum.JudgeBuildResultSurveySheetNotBeLocated.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeBuildResultSurveySheet(false));
        }
        if (Objects.equal(ReportFieldSifaEnum.SifaJudgeBuildResultSurveySheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudicialSchemeJudgeObjectSheet());
        }
        //相关参数选取与应用
        if (Objects.equal(ReportFieldEnum.SelectionApplicationParameters.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionApplicationParameters());
        }
        //主要计算过程
        if (Objects.equal(ReportFieldEnum.ComputationProcess.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getComputationProcess());
        }
        //估价对象市场价值的确定
        if (Objects.equal(ReportFieldEnum.DeterminationMarketValueValuationObject.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeterminationMarketValueValuationObject());
        }
        //估价对象描述
        if (Objects.equal(ReportFieldCommonEnum.CommonPrincipalDescribe.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalDescribe());
        }
        //估价信息描述
        if (Objects.equal(ReportFieldCommonEnum.CommonPrincipalDataDescribe.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalDataDescribe());
        }
        //估价对象权属
        if (Objects.equal(ReportFieldEnum.EquityStatusObjectSheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, null, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusObjectSheet());
        }
        //估价对象权属明细清单
        if (Objects.equal(ReportFieldEnum.EquityStatusObjectCheckListSheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, null, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusObjectSheetCheckList());
        }
        //权属证号
        if (Objects.equal(ReportFieldEnum.EquityStatusObjectNumber.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusObjectNumber());
        }
        //分类评估方法结果
        if (Objects.equal(ReportFieldEnum.EvaluationMethodResult.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethodResult());
        }
        //估价委托书复印件
        if (Objects.equal(ReportFieldEnum.JudgeObjectPrincipalCopySheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET());
        }
        //估计对象位置示意图
        if (Objects.equal(ReportFieldEnum.EstimatedObjectLocationDiagram.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEstimatedObjectLocationDiagram());
        }
        //估价对象实况照片
        if (Objects.equal(ReportFieldEnum.Valuation_Target_Live_Photos.getName(), name)) {
            try {
                generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuation_Target_Live_Photos());
            } catch (Exception e) {
                baseService.writeExceptionInfo(e, "估价对象实况照片");
            }
        }
        //估价对象权属证明复印件
        if (Objects.equal(ReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object());
        }
        //估价中引用的专用文件资料
        if (Objects.equal(ReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSpecial_documentation_referenced_in_valuation());
        }
        //委托人 估价委托人
        if (Objects.equal(ReportFieldCommonEnum.CommonPRINCIPAL.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipal());
        }
        // 估价委托人信息
        if (Objects.equal(ReportFieldCommonEnum.CommonPrincipalInfo.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalInfo());
        }
        //财产范围说明
        if (Objects.equal(ReportFieldEnum.ScopePropertyExplain.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getScopePropertyExplain());
        }
        //估价项目名称
        if (Objects.equal(ReportFieldCommonEnum.CommonValuationProjectName.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuationProjectName());
        }
        //司法估价项目名称
        if (Objects.equal(ReportFieldSifaEnum.SifaValuationProjectName.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuationProjectName2());
        }
        //户型及布局
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralHuxingLayout.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHuxingLayout());
        }
        //获取某些土地证字段信息
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLandArea.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLandNumber.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandownership.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandcert_use.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLand_right_nature.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandapportionment_area.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandendTime.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandregistration_authority.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandregistration_date.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        //获取某些区位字段信息
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLocation.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralShoppingConditions.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.Bus_Convenience.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralTraffic_accessibility.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralsubway.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralinfrastructure.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralExternal_facilities.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneraleducational_facility.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralMedical_Facilities.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralNatural_environment.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralcultural_environment.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        //获取房屋的装修情况
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_door.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_window.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_land.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_wall.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_Canopy.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_bathroom.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldB(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_kitchen.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldB(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_maintenance.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldValue());
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandscape.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFrontage.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFloorSpacing.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralGreenlandRate.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralBuildingCoverage.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralParkingLot.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralPropertyManagement.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralSuccessRate.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheNetAssessmentOne.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentNumber(name));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheNetAssessmentTwo.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentNumber(name));
        }
        //厌恶设施
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAversionFacility.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAversionFacility());
        }
        //报告使用单位
        if (Objects.equal(ReportFieldCommonEnum.CommonReportUnitString.getName(), name)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportUnitString());
        }
        //工行估价案例情况表
        if (Objects.equal(ReportFieldGongshangBankEnum.GongshangICBCValuationCaseInformationSheet.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getICBCValuationCaseInformationSheet());
        }
        //建行个贷区位分析
        if (Objects.equal(ReportFieldGongshangBankEnum.GongshangDistrict_Analysis.getName(), name)) {
            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getICBCDistrictAnalysisSheet());
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
        List<String> regexS = generateCommonMethod.specialTreatment(stringList);
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
            GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, schemeAreaGroup.getId(), new BaseReportTemplate(), new ProjectPlan());
            String path = generateBaseDataService.getjudgeBuildResultSurveySheet(schemeAreaGroup.getId(), projectInfoVo);
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
                sysAttachmentDtoList.stream().forEach(attachmentDto -> {
                    if (Objects.equal(attachmentDto.getFieldsName(), sysAttachmentDto.getFieldsName())) {
                        baseAttachmentService.deleteAttachmentByDto(attachmentDto);
                    }
                });
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
