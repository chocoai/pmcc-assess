package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldMdIncomeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by kings on 2018-5-23.
 */
@Service
public class GenerateReportService {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private GenerateReportDao generateReportDao;
    @Autowired
    private DeclareRecordService declareRecordService;
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
    private BaseReportFieldService baseReportFieldService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BaseReportService baseReportService;
    @Autowired
    private GenerateReportGenerationService generateReportGenerationService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<SchemeAreaGroup> getAreaGroupList(Integer projectId) {
        return schemeAreaGroupService.getAreaGroupList(projectId);
    }

    /**
     * 初始化用于生成报告记录信息
     *
     * @param projectId
     * @param planId
     */
    @Transactional(rollbackFor = {Exception.class})
    public void initGenerateReportRecord(Integer projectId, Integer planId) {
        int count = generateReportDao.getGenerateReportRecordCount(projectId, planId);
        if (count > 0) {
            return;
        }
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            GenerateReportRecord generateReportRecord = null;
            for (DeclareRecord declareRecord : declareRecords) {
                generateReportRecord = new GenerateReportRecord();
                BeanUtils.copyProperties(declareRecord, generateReportRecord);
                generateReportRecord.setProjectId(projectId);
                generateReportRecord.setPlanId(planId);
                generateReportDao.addGenerateReportRecord(generateReportRecord);
            }
        }
    }


    /**
     * 创建报告模板
     *
     * @param ids
     * @param generateReportGeneration
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public void createReportWord(String ids, GenerateReportGeneration generateReportGeneration) throws Exception {
        if (StringUtils.isEmpty(ids) || generateReportGeneration.getProjectPlanId() == null) {
            return;
        }
        if (generateReportGeneration.getId() == null || generateReportGeneration.getId().intValue() == 0) {
            generateReportGenerationService.addGenerateReportGeneration(generateReportGeneration);
        } else {
            generateReportGenerationService.updateGenerateReportGeneration(generateReportGeneration);
        }
        String[] strings = ids.split(",");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportGeneration.getProjectPlanId());
        if (projectPlan == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(generateReportGeneration.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGeneration.class));
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
            if (baseDataDic != null) {
                //预评报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        //获取替换后得报告文件路径 ==>
                        String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT, sysAttachmentDtoList);
                        }
                    }
                }
                //技术报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY, sysAttachmentDtoList);
                        }
                    }
                }
                //结果报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_RESULT)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_RESULT, sysAttachmentDtoList);
                        }
                    }
                }
            }
        }
    }

    /**
     * 上传到erp形成附件id
     *
     * @param path
     * @return
     */
    private void createSysAttachment(String path, GenerateReportGeneration generateReportGeneration, String reportType, List<SysAttachmentDto> sysAttachmentDtoList) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(generateReportGeneration.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGeneration.class));
        File file = new File(path);
        sysAttachmentDto.setFileExtension(file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()));
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        sysAttachmentDto.setFileSize(new Long(file.length()).toString());
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        String[] strs = reportType.split("\\.");
        StringBuilder builder = new StringBuilder(strs.length * 12);
        for (String s : strs) {
            builder.append(s);
        }
        sysAttachmentDto.setFieldsName(String.format("%s%d", FormatUtils.underlineToCamel(builder.toString(), false), generateReportGeneration.getAreaGroupId()));
        sysAttachmentDto.setFileName(baseDataDicService.getCacheDataDicByFieldName(reportType).getName());
        String ftpBasePath = String.format("%s/%s/%s/%s", baseAttachmentService.createFTPBasePath(), DateUtils.format(new Date(), "yyyy-MM-dd"), processControllerComponent.getThisUser(), UUID.randomUUID().toString());
        String ftpFileName = baseAttachmentService.createNoRepeatFileName(sysAttachmentDto.getFileExtension());
        sysAttachmentDto.setFilePath(ftpBasePath);
        sysAttachmentDto.setFtpFileName(ftpFileName);
        ftpUtilsExtense.uploadFilesToFTP(ftpBasePath, new FileInputStream(file.getPath()), ftpFileName);
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
     * 创建报告模板(具体)
     *
     * @param baseReportTemplate
     * @param generateReportGeneration
     * @return
     * @throws Exception
     */
    private String fullReportPath(BaseReportTemplate baseReportTemplate, GenerateReportGeneration generateReportGeneration, String reportType) throws Exception {
        String tempDir = "";
        if (baseReportTemplate == null) {
            return "";
        }
        SysAttachmentDto query = new SysAttachmentDto();
        query.setTableId(baseReportTemplate.getId());
        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDtoList.get(0).getId());
            //文本map
            Map<String, String> textMap = Maps.newHashMap();
            Map<String, String> textFileMap = Maps.newHashMap();
            //书签map
            Map<String, String> bookmarkMap = Maps.newHashMap();
            Map<String, String> bookmarkFileMap = Maps.newHashMap();
            List<Set<Map<String, Map<BaseReportFieldReplaceEnum, String>>>> setList = getReportMap(baseReportTemplate, new Document(tempDir), generateReportGeneration, reportType);
            for (Set<Map<String, Map<BaseReportFieldReplaceEnum, String>>> mapSet : setList) {
                if (CollectionUtils.isNotEmpty(mapSet)) {
                    Iterator<Map<String, Map<BaseReportFieldReplaceEnum, String>>> iterator = mapSet.iterator();
                    while (iterator.hasNext()) {
                        //这一层实际只有一个值
                        Map<String, Map<BaseReportFieldReplaceEnum, String>> mapMap = iterator.next();
                        Iterator<Map.Entry<String, Map<BaseReportFieldReplaceEnum, String>>> entryIterator = mapMap.entrySet().iterator();
                        while (entryIterator.hasNext()) {
                            Map.Entry<String, Map<BaseReportFieldReplaceEnum, String>> mapEntry = entryIterator.next();
                            Map<BaseReportFieldReplaceEnum, String> baseReportFieldReplaceEnumObjectMap = mapEntry.getValue();
                            String wordKey = mapEntry.getKey();
                            Iterator<Map.Entry<BaseReportFieldReplaceEnum, String>> it = baseReportFieldReplaceEnumObjectMap.entrySet().iterator();
                            //最终要得这一层
                            while (it.hasNext()) {
                                Map.Entry<BaseReportFieldReplaceEnum, String> enumObjectEntry = it.next();
                                BaseReportFieldReplaceEnum replaceEnum = enumObjectEntry.getKey();
                                String value = enumObjectEntry.getValue();
                                //文本(字符串)
                                if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.TEXT.getKey())) {
                                    textMap.put(wordKey, value);
                                }
                                //文本替换附件
                                if (Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.TEXT_FILE.getKey())) {
                                    if (StringUtils.isNotBlank(value)) {
                                        textFileMap.put(wordKey, value);
                                    } else {
                                        logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                    }
                                }
                                //(书签)
                                if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.BOOKMARK.getKey())) {
                                    if (StringUtils.isNotBlank(value)) {
                                        bookmarkMap.put(wordKey, value);
                                    }
                                }
                                //(书签替换附件)
                                if (Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.BOOKMARK_FILE.getKey())) {
                                    if (StringUtils.isNotBlank(value)) {
                                        bookmarkFileMap.put(wordKey, value);
                                    } else {
                                        logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                    }
                                }
                            }
                        }
                    }
                }
                if (!textMap.isEmpty()) {
                    AsposeUtils.replaceText(tempDir, textMap);
                }
                if (!bookmarkMap.isEmpty()) {
                    AsposeUtils.replaceBookmark(tempDir, bookmarkMap, false);
                }
                if (!textFileMap.isEmpty()) {
                    AsposeUtils.replaceTextToFile(tempDir, textFileMap);
                }
                if (!bookmarkFileMap.isEmpty()) {
                    AsposeUtils.replaceBookmarkToFile(tempDir, bookmarkFileMap);
                }
            }
        }
        return tempDir;
    }


    /**
     * 获取报告模板数据数据
     *
     * @param baseReportTemplate
     * @param generateReportGeneration
     * @param document
     * @return 如:文号,<文号,四川协和预评（2019）0001号>
     * @throws Exception
     */
    private List<Set<Map<String, Map<BaseReportFieldReplaceEnum, String>>>> getReportMap(BaseReportTemplate baseReportTemplate, Document document, GenerateReportGeneration generateReportGeneration, String reportType) throws Exception {
        List<Set<Map<String, Map<BaseReportFieldReplaceEnum, String>>>> list = Lists.newArrayList();
        Set<Map<String, Map<BaseReportFieldReplaceEnum, String>>> preMapSet = Sets.newHashSet();
        Set<Map<String, Map<BaseReportFieldReplaceEnum, String>>> mapSet = Sets.newHashSet();
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportGeneration.getProjectPlanId());
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(generateReportGeneration.getProjectId()));
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, generateReportGeneration.getAreaGroupId(), baseReportTemplate, projectPlan);
        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = Sets.newHashSet();
        //获取待替换文本的集合
        List<String> regexS = specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setChineseName(AsposeUtils.getChinese(bookmarkCollection.get(i).getName())).setName(bookmarkCollection.get(i).getName()).setType(BaseReportFieldReplaceEnum.BOOKMARK.getKey());
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        if (CollectionUtils.isNotEmpty(regexS)) {
            for (String name : regexS) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setChineseName(null).setName(name).setType(BaseReportFieldReplaceEnum.TEXT.getKey());
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        if (CollectionUtils.isNotEmpty(bookmarkAndRegexDtoHashSet)) {
            for (BookmarkAndRegexDto bookmarkAndRegex : bookmarkAndRegexDtoHashSet) {
                try {
                    String name = StringUtils.isNotBlank(bookmarkAndRegex.getChineseName()) ? bookmarkAndRegex.getChineseName() : bookmarkAndRegex.getName();
                    //文号
                    if (Objects.equal(BaseReportFieldEnum.ReportNumber.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getWordNumber(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //报告类别
                    if (Objects.equal(BaseReportFieldEnum.ReportingCategories.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            if (StringUtils.isNotBlank(reportType)) {
                                BaseDataDic reportTypeDic = baseDataDicService.getCacheDataDicByFieldName(reportType);
                                replaceReportPutValue(name, reportTypeDic.getName(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                    }
                    //报告出具日期
                    if (Objects.equal(BaseReportFieldEnum.ReportIssuanceDate.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            String reportIssuanceStr = null;
                            if (generateReportGeneration.getReportIssuanceDate() != null) {
                                reportIssuanceStr = DateUtils.format(generateReportGeneration.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                            } else {
                                reportIssuanceStr = DateUtils.format(generateBaseDataService.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                            }
                            replaceReportPutValue(name, reportIssuanceStr, bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //协助工作人员
                    if (Objects.equal(BaseReportFieldEnum.AssistanceStaff.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getAssistanceStaff(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //评估假设
                    if (Objects.equal(BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.HYPOTHESIS), bookmarkAndRegex.getType(), true, preMapSet);
                        }
                    }
                    //评估依据
                    if (Objects.equal(BaseReportFieldEnum.EVALUATION_BASIS.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.BASIS), bookmarkAndRegex.getType(), true, preMapSet);
                        }
                    }
                    //评估原则
                    if (Objects.equal(BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.PRINCIPLE), bookmarkAndRegex.getType(), true, preMapSet);
                        }
                    }
                    //变现能力分析
                    if (Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY), bookmarkAndRegex.getType(), true, preMapSet);
                        }
                    }
                    //风险提示
                    if (Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_RISK.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_RISK), bookmarkAndRegex.getType(), true, preMapSet);
                        }
                    }
                    //作业结束时间
                    if (Objects.equal(BaseReportFieldEnum.HomeWorkEndTime.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getHomeWorkEndTime(generateReportGeneration.getHomeWorkEndTime()), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //作业开始时间
                    if (Objects.equal(BaseReportFieldEnum.HomeWorkStartTime.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getHomeWorkStartTime(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //现场查勘期
                    if (Objects.equal(BaseReportFieldEnum.surveyExamineDate.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getSurveyExamineDate(generateReportGeneration.getInvestigationsStartDate(), generateReportGeneration.getInvestigationsEndDate()), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //现场查勘人员
                    if (Objects.equal(BaseReportFieldEnum.surveyExamineCreate.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getSurveyExamineCreate(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    AdCompanyQualificationDto qualificationDto = generateBaseDataService.getCompanyQualificationForPractising();
                    if (qualificationDto != null) {
                        //房地产估价机构营业执照复印件
                        if (Objects.equal(BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency(), bookmarkAndRegex.getType(), true, mapSet);
                            }
                        }
                        //房地产估价机构资质证书复印件
                        if (Objects.equal(BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution(), bookmarkAndRegex.getType(), true, mapSet);
                            }
                        }
                        //机构住所
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationAddress.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, qualificationDto.getOrganizationAddress(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //机构名称
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationName.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, qualificationDto.getOrganizationName(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //房地产估价机构
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, qualificationDto.getOrganizationName(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //机构名称法定代表人
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_legalRepresentative.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            replaceReportPutValue(name, qualificationDto.getLegalRepresentative(), bookmarkAndRegex.getType(), false, mapSet);
                            if (baseReportField != null) {
                            }
                        }
                        //机构工商注册号
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_registeredNo.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, qualificationDto.getRegisteredNo(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //机构资质等级
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationRank.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, qualificationDto.getOrganizationRank(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //机构证书号
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_certificateNo.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, qualificationDto.getCertificateNo(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //证书有效期
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, qualificationDto.getCertificateEffectiveDate(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //房地产估价机构信息
                        if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationInfo.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            replaceReportPutValue(name, generateBaseDataService.getXIEHE_organizationInfo(qualificationDto), bookmarkAndRegex.getType(), false, mapSet);
                            if (baseReportField != null) {
                            }
                        }
                        //经营范围
                        if (Objects.equal(BaseReportFieldEnum.BusinessScope.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            replaceReportPutValue(name, generateBaseDataService.getBusinessScope(qualificationDto), bookmarkAndRegex.getType(), false, mapSet);
                            if (baseReportField != null) {
                            }
                        }
                    }
                    //注册房产估价师
                    if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuer.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getRegisteredRealEstateValuer(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //注册房产估价师及注册号
                    if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerAndNumber.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, String.format("%s 注册号 %s", generateBaseDataService.getRegisteredRealEstateValuer(generateReportGeneration.getRealEstateAppraiser()), generateBaseDataService.getRegistrationNumber(generateReportGeneration.getRealEstateAppraiser())), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //注册房产估价师 注册号
                    if (Objects.equal(BaseReportFieldEnum.registrationNumber.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getRegistrationNumber(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //注册房产估价师 注册房地产估价师注册证书复印件
                    if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                    //房地产总价
                    if (Objects.equal(BaseReportFieldEnum.TotalRealEstatePrice.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getTotalRealEstatePrice(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //房地产总价大写金额
                    if (Objects.equal(BaseReportFieldEnum.CapitalizationAmount.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCapitalizationAmount(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //外聘专家工作概况
                    if (Objects.equal(BaseReportFieldEnum.ExpertWorkOverview.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getExpertWorkOverview(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //共有权情况
                    if (Objects.equal(BaseReportFieldEnum.Co_ownership.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCo_ownership(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //估价对象详细测算过程( 收益法 , 市场比较法)
                    if (Objects.equal(BaseReportFieldEnum.DetailedCalculationProcessValuationObject.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getDetailedCalculationProcessValuationObject(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //收益法租赁限制说明
                    if (Objects.equal(BaseReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getTenancyrestrictionRemark(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }


                    //替代原则
                    if (Objects.equal(BaseReportFieldEnum.SubstitutionPrinciple.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getSubstitutionPrinciple(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //未定事项假设
                    if (Objects.equal(BaseReportFieldEnum.UncertaintyAssumption.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getUncertaintyAssumption(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //背离事实假设
                    if (Objects.equal(BaseReportFieldEnum.DeviationFromFactualAssumptions.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getDeviationFromFactualAssumptions(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //不相一致假设
                    if (Objects.equal(BaseReportFieldEnum.InconsistentHypothesis.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getInconsistentHypothesis(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }


                    //变现分析通用性
                    if (Objects.equal(BaseReportFieldEnum.UniversalityLiquidityAnalysis.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getUniversalityLiquidityAnalysis(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现分析独立使用性
                    if (Objects.equal(BaseReportFieldEnum.IndependentUsabilityCashFlowAnalysis.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getIndependentUsabilityCashFlowAnalysis(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现分析可分割转让性
                    if (Objects.equal(BaseReportFieldEnum.LiquidityAnalysisSeparableTransferability.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getLiquidityAnalysisSeparableTransferability(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现分析房地产区位
                    if (Objects.equal(BaseReportFieldEnum.CashAnalysisRealEstateLocation.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCashAnalysisRealEstateLocation(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现分析房地产开发程度
                    if (Objects.equal(BaseReportFieldEnum.CashAnalysisDegreeRealEstateDevelopment.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCashAnalysisDegreeRealEstateDevelopment(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现分析房地产价值大小
                    if (Objects.equal(BaseReportFieldEnum.CashAnalysisValueRealEstate.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCashAnalysisValueRealEstate(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现分析房地产市场状况
                    if (Objects.equal(BaseReportFieldEnum.CashAnalysisRealEstateMarket.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCashAnalysisRealEstateMarket(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现分析其他
                    if (Objects.equal(BaseReportFieldEnum.CashAnalysisOthers.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCashAnalysisOthers(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现拍卖底价规定
                    if (Objects.equal(BaseReportFieldEnum.BasePriceRegulationCashAuction.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getBasePriceRegulationCashAuction(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现时间长短
                    if (Objects.equal(BaseReportFieldEnum.HowLongLiquidationTime.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getHowLongLiquidationTime(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现综合分析
                    if (Objects.equal(BaseReportFieldEnum.Cash_inComprehensiveAnalysis.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCash_inComprehensiveAnalysis(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险区域规划、城市发展战略、城市规划、土地利用对房地产的影响
                    if (Objects.equal(BaseReportFieldEnum.ThreeMajorRiskPlanning.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getThreeMajorRiskPlanning(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险相邻物业抵押影响
                    if (Objects.equal(BaseReportFieldEnum.TheImpactMortgageRiskyNeighbouringProperty.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getTheImpactMortgageRiskyNeighbouringProperty(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险租赁影响
                    if (Objects.equal(BaseReportFieldEnum.ImpactRiskLeasing.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getImpactRiskLeasingy(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险整体变现影响
                    if (Objects.equal(BaseReportFieldEnum.OverallLiquidityImpactRisk.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getOverallLiquidityImpactRisk(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险不可抗力影响
                    if (Objects.equal(BaseReportFieldEnum.RiskForceMajeureEffect.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getRiskForceMajeureEffect(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险政策变化影响
                    if (Objects.equal(BaseReportFieldEnum.ImpactRiskPolicyChange.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getImpactRiskPolicyChange(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险抵押物特殊事件影响
                    if (Objects.equal(BaseReportFieldEnum.ImpactSpecialEventsRiskMortgage.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getImpactSpecialEventsRiskMortgage(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险抵押物使用不当
                    if (Objects.equal(BaseReportFieldEnum.ImproperUseRiskCollateral.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getImproperUseRiskCollaterale(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险抵押物功能及替代物
                    if (Objects.equal(BaseReportFieldEnum.FunctionsSubstitutesRiskMortgage.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getFunctionsSubstitutesRiskMortgage(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险抵押物环境影响
                    if (Objects.equal(BaseReportFieldEnum.EnvironmentalImpactRiskMortgage.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getEnvironmentalImpactRiskMortgage(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //风险抵押期间因抵押物原因而引起的房地产信贷风险关注点
                    if (Objects.equal(BaseReportFieldEnum.ConcernRealEstateCreditRiskCausedMortgageReasonsDuringRiskMortgagePeriod.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getConcernRealEstateCreditRiskCausedMortgageReasonsDuringRiskMortgagePeriod(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //变现比率
                    if (Objects.equal(BaseReportFieldEnum.LiquidRatios.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getLiquidRatios(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //出具报告城市
                    if (Objects.equal(BaseReportFieldEnum.ReportCity.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getReportCity(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }


                    //依据不足假设
                    if (Objects.equal(BaseReportFieldEnum.InsufficientHypothesis.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getInsufficientHypothesis(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //假设开发法适用原因
                    if (Objects.equal(BaseReportFieldEnum.DevelopmentAssistApplyReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //假设开发法不适用原因
                    if (Objects.equal(BaseReportFieldEnum.DevelopmentAssistNotApplicableReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //假设开发法评估思路
                    if (Objects.equal(BaseReportFieldEnum.DevelopmentAssistThink.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //收益法适用原因
                    if (Objects.equal(BaseReportFieldEnum.IncomeAssistApplyReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getIncomeAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //收益法不适用原因
                    if (Objects.equal(BaseReportFieldEnum.IncomeAssistNotApplicableReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getIncomeAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //收益法评估思路
                    if (Objects.equal(BaseReportFieldEnum.IncomeAssistThink.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getIncomeAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //市场比较法适用原因
                    if (Objects.equal(BaseReportFieldEnum.CompareAssistApplyReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCompareAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //市场比较法不适用原因
                    if (Objects.equal(BaseReportFieldEnum.CompareAssistNotApplicableReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCompareAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //市场比较法评估思路
                    if (Objects.equal(BaseReportFieldEnum.CompareAssistThink.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCompareAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //成本法适用原因
                    if (Objects.equal(BaseReportFieldEnum.CostAssistApplyReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCostAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //成本法不适用原因
                    if (Objects.equal(BaseReportFieldEnum.CostAssistNotApplicableReason.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCostAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //成本法评估思路
                    if (Objects.equal(BaseReportFieldEnum.CostAssistThink.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCostAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //区位
                    if (Objects.equal(BaseReportFieldEnum.Location.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getLocation_(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //权利人
                    if (Objects.equal(BaseReportFieldEnum.PowerPerson.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getPowerPerson(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //价值类型
                    if (Objects.equal(BaseReportFieldEnum.ValueType.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValueType(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //价值类型描述
                    if (Objects.equal(BaseReportFieldEnum.ValueTypeDesc.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValueTypeDesc(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //价值内涵
                    if (Objects.equal(BaseReportFieldEnum.ValueConnotation.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValueImplication(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //价值内涵描述
                    if (Objects.equal(BaseReportFieldEnum.ValueImplicationDesc.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValueImplicationDesc(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //设定用途
                    if (Objects.equal(BaseReportFieldEnum.SetUse.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getSetUse(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //证载用途 / 证载用途分述
                    if (Objects.equal(BaseReportFieldEnum.CertificationPurpose.getName(), name) || Objects.equal(BaseReportFieldEnum.SeparationCertificateUses.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getSeparationCertificateUses(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //证载用途总括
                    if (Objects.equal(BaseReportFieldEnum.SummaryCertificateUses.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getSummaryCertificateUses(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //房产类型
                    if (Objects.equal(BaseReportFieldEnum.HouseType.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getSetUse(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //土地 实际用途
                    if (Objects.equal(BaseReportFieldEnum.PracticalUse.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getPracticalUse(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //建筑结构类别
                    if (Objects.equal(BaseReportFieldEnum.BuildingStructureCategory.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                        }
                        replaceReportPutValue(name, generateBaseDataService.getBuildingStructureCategory(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                    //土地使用权类型
                    if (Objects.equal(BaseReportFieldEnum.LandUseRightType.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getUseRightType(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //评估面积
                    if (Objects.equal(BaseReportFieldEnum.AssessArea.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getAssessArea(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                    }
                    //委托目的表述
                    if (Objects.equal(BaseReportFieldEnum.StatementPurposeEntrustment.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getStatementPurposeEntrustment(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //委托目的
                    if (Objects.equal(BaseReportFieldEnum.DelegatePurpose.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getDelegatePurpose(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //评估方法 ,估价对象评估方法
                    if (Objects.equal(BaseReportFieldEnum.EvaluationMethod.getName(), name) || Objects.equal(BaseReportFieldEnum.EvaluationMethodValuationObject.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getEvaluationMethodValuationObject(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //评估方法总括
                    if (Objects.equal(BaseReportFieldEnum.SummaryEvaluationMethod.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getSummaryEvaluationMethod(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //评估方法分述
                    if (Objects.equal(BaseReportFieldEnum.AssessmentMethods.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getAssessmentMethods(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //技术思路通用模板
                    if (Objects.equal(BaseReportFieldEnum.GeneralTemplateTechnicalIdea.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getGeneralTemplateTechnicalIdea(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //土地他项权利情况
                    if (Objects.equal(BaseReportFieldEnum.inventoryRight.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getInventoryRight(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //他权有无租赁权
                    if (Objects.equal(BaseReportFieldEnum.HisRightHasLease.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getHisRightHasLease(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //土地使用管制
                    if (Objects.equal(BaseReportFieldEnum.LandUseControl.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getLandUseControl(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //出租或占用情况
                    if (Objects.equal(BaseReportFieldEnum.rentalPossessionDesc.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getRentalPossessionDesc(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //价值时点
                    if (Objects.equal(BaseReportFieldEnum.ValueTimePoint.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValueTimePoint(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //估价时点
                    if (Objects.equal(BaseReportFieldEnum.dateValue.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getValueTimePoint(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //价值时点说明
                    if (Objects.equal(BaseReportFieldEnum.ValueTimePointRemark.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValueTimePointRemark(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //申报所启用表单类型
                    if (Objects.equal(BaseReportFieldEnum.TypesFormEnabledDeclarationOffice.getName(), name) || Objects.equal(BaseReportFieldEnum.DecalreFormTypeAll.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getTypesFormEnabledDeclarationOffice(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //税费负担
                    if (Objects.equal(BaseReportFieldEnum.TaxBurden.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getTaxBurden(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //付款方式
                    if (Objects.equal(BaseReportFieldEnum.PaymentMethod.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getPaymentMethod(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //估价技术思路
                    if (Objects.equal(BaseReportFieldEnum.EvaluationThink.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getEvaluationThink(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //分类评估单价
                    if (Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGory.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getEvaluationPriceCateGoryOne(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //分类评估面积
                    if (Objects.equal(BaseReportFieldEnum.EvaluationAreaCateGory.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getEvaluationAreaCateGoryOne(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //分类评估总价
                    if (Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getEvaluationPriceCateGoryTotalOne(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //估价对象的总价
                    if (Objects.equal(BaseReportFieldEnum.TotalValueValuationObject.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getTotalValueValuationObject(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //估价对象的单价
                    if (Objects.equal(BaseReportFieldEnum.UnitPriceValuator.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getUnitPriceValuator(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //估价对象选择估价方法
                    if (Objects.equal(BaseReportFieldEnum.SelectionValuationMethod.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getSelectionValuationMethod(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //分类评估单价计算试
                    if (Objects.equal(BaseReportFieldEnum.EvaluationExpression.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getEvaluationExpression(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //权重说明
                    if (Objects.equal(BaseReportFieldEnum.WeightSpecification.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getWeightSpecification(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //价值表达结果
                    if (Objects.equal(BaseReportFieldEnum.ValueExpressionResult.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValueExpressionResult(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //变现分析税费
                    if (Objects.equal(BaseReportFieldEnum.LIQUIDATION_ANALYSIS.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getLiquidationAnalysis(name), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                    //法定优先受偿款
                    if (Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getStatutoryOptimumReimbursement(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //抵押价值总金额
                    if (Objects.equal(BaseReportFieldEnum.totalAmountMortgageValue.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getTotalAmountMortgageValue(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //抵押价值总金额大写
                    if (Objects.equal(BaseReportFieldEnum.totalAmountMortgageValueCapitalization.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getTotalAmountMortgageValueCapitalization(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //他权信息公示
                    if (Objects.equal(BaseReportFieldEnum.HisRightInfoPublicity.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getHisRightInfoPublicity(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //他权类别
                    if (Objects.equal(BaseReportFieldEnum.HisRightType.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getHisRightType(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //有无他项权
                    if (Objects.equal(BaseReportFieldEnum.ThereAnyOtherRight.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getThereAnyOtherRight(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //他权其它
                    if (Objects.equal(BaseReportFieldEnum.RightOther.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getRightOther(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //担保物权设立情况
                    if (Objects.equal(BaseReportFieldEnum.collateralFound.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCollateralFound(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //最佳利用描述
                    if (Objects.equal(BaseReportFieldEnum.OptimumUtilizationDescription.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getOptimumUtilizationDescription(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //资产清查实际地址
                    if (Objects.equal(BaseReportFieldEnum.ActualAddressAssetInventory.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getActualAddressAssetInventory(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //资产清查证明人
                    if (Objects.equal(BaseReportFieldEnum.CertificateAssetInventory.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getCertificateAssetInventory(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //资产清查确认一致
                    if (Objects.equal(BaseReportFieldEnum.AssetInventoryConfirmConsistency.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getAssetInventoryConfirmConsistency(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //资产清查一致说明
                    if (Objects.equal(BaseReportFieldEnum.AssetInventoryAgreement.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getAssetInventoryAgreement(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //法定优先受偿款总金额
                    if (Objects.equal(BaseReportFieldEnum.StatutoryPriorityAmountTotal.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getStatutoryPriorityAmountTotal(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //法定优先受偿款金额
                    if (Objects.equal(BaseReportFieldEnum.StatutoryPriorityAmount.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getStatutoryPriorityAmount(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }
                    //房屋所有权登记状况表
                    if (Objects.equal(BaseReportFieldEnum.HousingOwnershipRegistrationStatementSheet.getName(), name)) {
                        replaceReportPutValue(name, generateBaseDataService.getHousingOwnershipRegistrationStatementSheet(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                    //估价对象区位状况表
                    if (Objects.equal(BaseReportFieldEnum.JudgeObjectAreaStatusSheet.getName(), name)) {
                        replaceReportPutValue(name, generateBaseDataService.getJudgeObjectAreaStatusSheet(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                    //估价对象权属状况表
                    if (Objects.equal(BaseReportFieldEnum.EquityStatusValuatedObject.getName(), name)) {
                        replaceReportPutValue(name, generateBaseDataService.getEquityStatusValuatedObjects(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                    //估价土地实体状况表
                    if (Objects.equal(BaseReportFieldEnum.JudgeObjectLandStateSheet.getName(), name)) {
                        replaceReportPutValue(name, generateBaseDataService.getJudgeObjectLandStateSheet(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                    //估价对象建筑实体状况表
                    if (Objects.equal(BaseReportFieldEnum.JudgeBuildLandStateSheet.getName(), name)) {
                        replaceReportPutValue(name, generateBaseDataService.getJudgeBuildLandStateSheet(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                    //汇总表
                    if (Objects.equal(BaseReportFieldEnum.judgeSummarySheet.getName(), name)) {
                        replaceReportPutValue(name, generateBaseDataService.getJudgeSummarySheet(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                    //土地使用权登记状况表
                    if (Objects.equal(BaseReportFieldEnum.judgeObjectLandUseCertificateSheet.getName(), name)) {
                        replaceReportPutValue(name, generateBaseDataService.getjudgeObjectLandUseCertificateSheet(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                    //估价项目名称
                    if (Objects.equal(BaseReportFieldEnum.ValuationProjectName.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValuationProjectName(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //估价结果一览表
                    if (Objects.equal(BaseReportFieldEnum.JudgeBuildResultSurveySheet.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getjudgeBuildResultSurveySheet(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                    //计算过程
                    if (Objects.equal(BaseReportFieldEnum.ComputationProcess.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        SysAttachmentDto query = new SysAttachmentDto();
                        if (baseReportField != null) {
                            query.setTableId(baseReportField.getId());
                            query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                            List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                            if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                                replaceReportPutValue(name, generateBaseDataService.getComputationProcess(sysAttachmentDtoList.get(0)), bookmarkAndRegex.getType(), true, mapSet);
                            }
                        }
                    }
                    //相关参数选取与应用
                    if (Objects.equal(BaseReportFieldEnum.SelectionApplicationParameters.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getSelectionApplicationParameters(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {

                        }
                    }
                    //估价对象市场价值的确定
                    if (Objects.equal(BaseReportFieldEnum.DeterminationMarketValueValuationObject.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getDeterminationMarketValueValuationObject(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {

                        }
                    }
                    //建筑物权益状况
                    if (Objects.equal(BaseReportFieldEnum.StatusBuildingRightsInterests.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);

                    }
                    //本次估价的总体思路和评估方法的选取
                    if (Objects.equal(BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        SysAttachmentDto query = new SysAttachmentDto();
                        if (baseReportField != null) {
                            query.setTableId(baseReportField.getId());
                            query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                            List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                            if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                                replaceReportPutValue(name, generateBaseDataService.gettheGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods(sysAttachmentDtoList.get(0)), bookmarkAndRegex.getType(), true, mapSet);
                            }
                        }
                    }
                    //分类评估方法结果
                    if (Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getEvaluationMethodResult(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                    //单价调整表
                    if (Objects.equal(BaseReportFieldEnum.UnitPriceAdjustmentTable.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getUnitPriceAdjustmentTable(), bookmarkAndRegex.getType(), true, mapSet);
                        if (baseReportField != null) {
                        }
                    }

                    //估价委托书复印件
                    if (Objects.equal(BaseReportFieldEnum.JUDGEOBJECTPRINCIPALCOPYSHEET.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }

                    //估计对象位置示意图
                    if (Objects.equal(BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getEstimatedObjectLocationDiagram(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }

                    //估价对象实况照片
                    if (Objects.equal(BaseReportFieldEnum.Valuation_Target_Live_Photos.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getValuation_Target_Live_Photos(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }

                    //估价对象权属证明复印件
                    if (Objects.equal(BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }

                    //估价中引用的专用文件资料
                    if (Objects.equal(BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getSpecial_documentation_referenced_in_valuation(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }

                    //委托人 估价委托人
                    if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getPrincipal(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }

                    // 估价委托人信息
                    if (Objects.equal(BaseReportFieldEnum.PrincipalInfo.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getPrincipalInfo(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }

                    //座落
                    if (Objects.equal(BaseReportFieldEnum.Seat.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getSeat(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }

                    //财产范围说明
                    if (Objects.equal(BaseReportFieldEnum.ScopePropertyExplain.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getScopePropertyExplain(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }

                    //建筑面积及评估面积
                    if (Objects.equal(BaseReportFieldEnum.BuildingAndAssessArea.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        replaceReportPutValue(name, generateBaseDataService.getBuildingAndAssessArea(), bookmarkAndRegex.getType(), false, mapSet);
                        if (baseReportField != null) {
                        }
                    }

                    //委托人地址
                    if (Objects.equal(BaseReportFieldEnum.PrincipalAddress.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getPrincipalAddress(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }

                    //委托人法定代表人
                    if (Objects.equal(BaseReportFieldEnum.PrincipalLegalRepresentative.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getPrincipalLegalRepresentative(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        list.add(preMapSet);
        list.add(mapSet);
        System.gc();
        return list;
    }


    private void replaceReportPutValue(String name, String value, String replaceType, boolean fileFixed, Set<Map<String, Map<BaseReportFieldReplaceEnum, String>>> mapSet) {
        if (BaseReportFieldReplaceEnum.TEXT.getKey().equals(replaceType)) {
            if (fileFixed) {
                mapSet.add(getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum.TEXT_FILE,
                        String.format("${%s}", name), value));
            } else {
                mapSet.add(getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum.TEXT,
                        String.format("${%s}", name), value));
            }
        }
        if (BaseReportFieldReplaceEnum.BOOKMARK.getKey().equals(replaceType)) {
            if (fileFixed) {
                mapSet.add(getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum.BOOKMARK_FILE, name, value));
            } else {
                mapSet.add(getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum.TEXT, name, value));
            }
        }
    }

    /**
     * 报告模板map组装值
     *
     * @param baseReportFieldReplaceEnum
     * @param wordKey
     * @param value
     * @return
     */
    private Map<String, Map<BaseReportFieldReplaceEnum, String>> getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum baseReportFieldReplaceEnum, String wordKey, String value) {
        Map<String, Map<BaseReportFieldReplaceEnum, String>> stringEntryMap = Maps.newHashMap();
        Map<BaseReportFieldReplaceEnum, String> enumObjectMap = Maps.newHashMap();
        enumObjectMap.put(baseReportFieldReplaceEnum, StringUtils.defaultString(value, ""));
        stringEntryMap.put(wordKey, enumObjectMap);
        return stringEntryMap;
    }

    private List<String> specialTreatment(List<String> strings) {
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(strings)) {
            for (String s : strings) {
                String temp = s.substring(2, s.length() - 1);
                stringList.add(temp);
            }
        }
        return stringList;
    }

}
