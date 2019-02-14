package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.ChineseToPy;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-5-23.
 */
@Service
public class GenerateReportService {
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
        String[] strings = ids.split(",");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportGeneration.getProjectPlanId());
        if (projectPlan == null) {
            return;
        }
        if (generateReportGeneration.getId() == null || generateReportGeneration.getId().equals(0)) {
            GenerateReportGeneration query = generateReportGenerationService.getGenerateReportGenerationByAreaGroupId(generateReportGeneration.getAreaGroupId(), generateReportGeneration.getProjectPlanId());
            if (query != null) {
                generateReportGeneration.setId(query.getId());
            }
            if (query == null) {
                generateReportGeneration.setCreator(processControllerComponent.getThisUser());
                generateReportGenerationService.addGenerateReportGeneration(generateReportGeneration);
            }
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(generateReportGeneration.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGeneration.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto attachmentDto : sysAttachmentDtoList) {
                baseAttachmentService.deleteAttachmentByDto(attachmentDto);
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
                            this.createSysAttachment(path, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
                        }
                    }
                }
                //技术报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
                        }
                    }
                }
                //结果报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_RESULT)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
                        }
                    }
                }
            }
        }
        generateReportGenerationService.updateGenerateReportGeneration(generateReportGeneration);
    }

    /**
     * 上传到erp形成附件id
     *
     * @param path
     * @return
     */
    private void createSysAttachment(String path, GenerateReportGeneration generateReportGeneration, String reportType) throws Exception {
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
                                    bookmarkMap.put(wordKey, value);
                                }
                                //(书签替换附件)
                                if (Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.BOOKMARK_FILE.getKey())) {
                                    if (StringUtils.isNotBlank(value)) {
                                        bookmarkFileMap.put(wordKey, value);
                                    } else {
                                        logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                        logger.info(System.getProperty("file.encoding"));
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
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(generateReportGeneration.getProjectId(), generateReportGeneration.getAreaGroupId(), baseReportTemplate.getId(), projectPlan);
        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = Sets.newHashSet();
        //获取待替换文本的集合
        List<String> regexS = specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setChineseName(getChinese(bookmarkCollection.get(i).getName())).setName(bookmarkCollection.get(i).getName()).setType(BaseReportFieldReplaceEnum.BOOKMARK.getKey());
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
                String name = StringUtils.isNotBlank(bookmarkAndRegex.getChineseName()) ? bookmarkAndRegex.getChineseName() : bookmarkAndRegex.getName();
                //文号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.REPORTNUMBER.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getWordNumber(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //报告类别
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ReportingCategories.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        if (StringUtils.isNotBlank(reportType)) {
                            BaseDataDic reportTypeDic = baseDataDicService.getCacheDataDicByFieldName(reportType);
                            replaceReportPutValue(name, reportTypeDic.getName(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                }
                //报告出具日期
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.reportIssuanceDate.getName(), name)) {
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
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssistanceStaff.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getAssistanceStaff(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //评估假设
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.HYPOTHESIS), bookmarkAndRegex.getType(), true, preMapSet);
                    }
                }
                //评估依据
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_BASIS.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.BASIS), bookmarkAndRegex.getType(), true, preMapSet);
                    }
                }
                //评估原则
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.PRINCIPLE), bookmarkAndRegex.getType(), true, preMapSet);
                    }
                }
                //变现能力分析
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY), bookmarkAndRegex.getType(), true, preMapSet);
                    }
                }
                //风险提示
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_RISK.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_RISK), bookmarkAndRegex.getType(), true, preMapSet);
                    }
                }
                //作业结束时间
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HomeWorkEndTime.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getHomeWorkEndTime(generateReportGeneration.getHomeWorkEndTime()), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //作业开始时间
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HomeWorkStartTime.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getHomeWorkStartTime(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //现场查勘期
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.surveyExamineDate.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSurveyExamineDate(generateReportGeneration.getInvestigationsStartDate(), generateReportGeneration.getInvestigationsEndDate()), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //现场查勘人员
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.surveyExamineCreate.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSurveyExamineCreate(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                AdCompanyQualificationDto qualificationDto = generateBaseDataService.getCompanyQualificationForPractising();
                if (qualificationDto != null) {
                    //房地产估价机构营业执照复印件
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                    //房地产估价机构资质证书复印件
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                    //机构住所
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationAddress.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationAddress(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //机构名称
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationName.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationName(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //房地产估价机构
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationName(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //机构名称法定代表人
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_legalRepresentative.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getLegalRepresentative(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //机构工商注册号
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_registeredNo.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getRegisteredNo(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //机构资质等级
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationRank.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationRank(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //机构证书号
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_certificateNo.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getCertificateNo(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                    //证书有效期
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getCertificateEffectiveDate(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                }
                //注册房产估价师
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuer.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRegisteredRealEstateValuer(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //注册房产估价师及注册号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerAndNumber.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, String.format("%s及%s", generateBaseDataService.getRegisteredRealEstateValuer(generateReportGeneration.getRealEstateAppraiser()), generateBaseDataService.getRegistrationNumber(generateReportGeneration.getRealEstateAppraiser())), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //注册房产估价师 注册号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.registrationNumber.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRegistrationNumber(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //注册房产估价师 注册房地产估价师注册证书复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(generateReportGeneration.getRealEstateAppraiser()), bookmarkAndRegex.getType(), true, mapSet);
                    }
                }
                //房地产总价
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.TotalRealEstatePrice.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getTotalRealEstatePrice(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //房地产总价大写金额
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CapitalizationAmount.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCapitalizationAmount(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //房地产总价大写
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Capital_capitalization_total_price_real_estate.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCapitalizationAmount(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //收益法模板
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.MdIncome.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        String s = generateBaseDataService.getMdIncomeSheet();
                        if (StringUtils.isNotBlank(s)) {
                            replaceReportPutValue(name, s, bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                }
                //市场比较法模板
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.MdCompare.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        if (false) {
                            replaceReportPutValue(name, generateBaseDataService.getMdCompareSheet(), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                }
                //假设开发法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //假设开发法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //假设开发法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //收益法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getIncomeAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //收益法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getIncomeAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //收益法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getIncomeAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //市场比较法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCompareAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //市场比较法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCompareAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //市场比较法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCompareAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //成本法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCostAssistApplyReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //成本法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCostAssistNotApplicableReason(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //成本法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCostAssistThink(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //区位
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Location.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getLocation_(), bookmarkAndRegex.getType(), false, mapSet);
                        //由于区位以后可能会变,现在取权利人
                        if (false) {
                            BaseReportFieldEnum.notPowerPerson.getName();
                            replaceReportPutValue(name, generateBaseDataService.getNotPowerPerson(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                        if (false) {
                            BaseReportFieldEnum.powerPerson.getName();
                            replaceReportPutValue(name, generateBaseDataService.getPowerPerson(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                }
                //价值类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueType.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueType(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //价值类型描述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTypeDesc.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueTypeDesc(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //价值内涵
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueConnotation.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueImplication(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //价值内涵描述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueImplicationDesc.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueImplicationDesc(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //设定用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SetUse.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSetUse(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //证载用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CertificationPurpose.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryCertificateUses(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //证载用途分述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SeparationCertificateUses.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSeparationCertificateUses(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //证载用途总括
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SummaryCertificateUses.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryCertificateUses(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //房产类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HouseType.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSetUse(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //土地实际用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandPracticalUse.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getLandPracticalUse(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //使用权类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.UseRightType.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getUseRightType(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //评估面积
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssessArea.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getAssessArea().toString(), bookmarkAndRegex.getType(), false, mapSet);
                        }
                    }
                }
                //委托目的表述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatementPurposeEntrustment.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getStatementPurposeEntrustment(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //委托目的
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DelegatePurpose.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDelegatePurpose(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //评估方法
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethod.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryEvaluationMethod(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //评估方法总括
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SummaryEvaluationMethod.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryEvaluationMethod(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //评估方法分述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssessmentMethods.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getAssessmentMethods(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //土地他项权利情况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.inventoryRight.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getInventoryRight(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //土地使用管制
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandUseControl.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getLandUseControl(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //出租或占用情况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.rentalPossessionDesc.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRentalPossessionDesc(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //价值时点
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePoint.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueTimePoint(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //价值时点说明
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePointRemark.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueTimePointRemark(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //估价技术思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEvaluationThink(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                if (true) {
                    List<SchemeJudgeObject> schemeJudgeObjectList = generateBaseDataService.getSchemeJudgeObjectList();
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                        //分类评估单价
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGory.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getEvaluationPriceCateGoryOne(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //分类评估面积
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationAreaCateGory.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getEvaluationAreaCateGoryOne(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                        //分类评估总价
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName(), name)) {
                            BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getEvaluationPriceCateGoryTotalOne(), bookmarkAndRegex.getType(), false, mapSet);
                            }
                        }
                    }
                }
                //选择估价方法
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionValuationMethod.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSelectionValuationMethod(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //分类评估单价计算式
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationExpression.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEvaluationExpression(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //分类评估方法结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
//                        replaceReportPutValue(name, generateBaseDataService.getEvaluationMethodResult(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT),bookmarkAndRegex.getType(), true, mapSet);
                    }
                }
                //权重说明
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.WeightSpecification.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getWeightSpecification(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //价值表达结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueExpressionResult.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueExpressionResult(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //法定优先受偿款
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getStatutoryOptimumReimbursement(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //房屋所有权登记状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HousingOwnershipRegistrationStatementSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getHousingOwnershipRegistrationStatementSheet(), bookmarkAndRegex.getType(), true, mapSet);
                }
                //估价对象区位状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectAreaStatusSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getJudgeObjectAreaStatusSheet(), bookmarkAndRegex.getType(), true, mapSet);
                }
                //估价土地实体状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectLandStateSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getJudgeObjectLandStateSheet(), bookmarkAndRegex.getType(), true, mapSet);
                }
                //估价对象建筑实体状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildLandStateSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getJudgeBuildLandStateSheet(), bookmarkAndRegex.getType(), true, mapSet);
                }
                //汇总表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeSummarySheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getJudgeSummarySheet(), bookmarkAndRegex.getType(), true, mapSet);
                }
                //土地使用权登记状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectLandUseCertificateSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getjudgeObjectLandUseCertificateSheet(), bookmarkAndRegex.getType(), true, mapSet);
                }
                //估价项目名称
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValuationProjectName.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValuationProjectName(), bookmarkAndRegex.getType(), false, mapSet);
                    }
                }
                //估价结果一览表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildResultSurveySheet.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getjudgeBuildResultSurveySheet(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                }
                //计算过程
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ComputationProcess.getName(), name)) {
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
                //参数选取与应用
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionApplicationParameters.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(name, generateBaseDataService.getSelectionApplicationParameters(sysAttachmentDtoList.get(0)), bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                }
                //建筑物权益状况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatusBuildingRightsInterests.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
//                            replaceReportPutValue(name, generateBaseDataService.getStatusBuildingRightsInterests(sysAttachmentDtoList.get(0)),bookmarkAndRegex.getType(), true, mapSet);
                        }
                    }
                }
                //本次估价的总体思路和评估方法的选取
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName(), name)) {
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
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
//                        replaceReportPutValue(name, generateBaseDataService.getEvaluationMethodResult(AssessDataDicKeyConstant.REPORT_TYPE_RESULT),bookmarkAndRegex.getType(), true, mapSet);
                    }
                }

                //估价委托书复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.JUDGEOBJECTPRINCIPALCOPYSHEET.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                }

                //估计对象位置示意图
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEstimatedObjectLocationDiagram(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                }

                //估价对象实况照片
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Valuation_Target_Live_Photos.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValuation_Target_Live_Photos(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                }

                //估价对象权属证明复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                }

                //估价中引用的专用文件资料
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSpecial_documentation_referenced_in_valuation(), bookmarkAndRegex.getType(), true, mapSet);
                    }
                }

                //委托人
                if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getPrincipal(), bookmarkAndRegex.getType(), false, mapSet);
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
            }
        }
        list.add(preMapSet);
        list.add(mapSet);
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
        enumObjectMap.put(baseReportFieldReplaceEnum, StringUtils.defaultString(value,""));
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

    /**
     * 利用 ascii 码 配合正则 提取中文
     *
     * @param paramValue
     * @return
     */
    public String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }


}
