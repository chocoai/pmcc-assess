package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
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
    private SchemeReportGenerationService schemeReportGenerationService;
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
     * @param schemeReportGeneration
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public void createReportWord(String ids, SchemeReportGeneration schemeReportGeneration) throws Exception {
        if (StringUtils.isEmpty(ids) || schemeReportGeneration.getProjectPlanId() == null) {
            return;
        }
        String[] strings = ids.split(",");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(schemeReportGeneration.getProjectPlanId());
        if (projectPlan == null) {
            return;
        }
        if (schemeReportGeneration.getId() == null || schemeReportGeneration.getId().equals(1)) {
            SchemeReportGeneration query = schemeReportGenerationService.getSchemeReportGenerationByAreaGroupId(schemeReportGeneration.getAreaGroupId(), schemeReportGeneration.getProjectPlanId());
            if (query != null) {
                schemeReportGeneration.setId(query.getId());
            }
            if (query == null) {
                schemeReportGeneration.setCreator(processControllerComponent.getThisUser());
                schemeReportGenerationService.addSchemeReportGeneration(schemeReportGeneration);
            }
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(schemeReportGeneration.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SchemeReportGeneration.class));
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
                        String path = this.fullReportPath(baseReportTemplate, schemeReportGeneration);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, schemeReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
                        }
                    }
                }
                //技术报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, schemeReportGeneration);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, schemeReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
                        }
                    }
                }
                //结果报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_RESULT)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, schemeReportGeneration);
                        if (StringUtils.isNotBlank(path)) {
                            this.createSysAttachment(path, schemeReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
                        }
                    }
                }
            }
        }
        schemeReportGenerationService.updateSchemeReportGeneration(schemeReportGeneration);
    }

    /**
     * 上传到erp形成附件id
     *
     * @param path
     * @return
     */
    private void createSysAttachment(String path, SchemeReportGeneration schemeReportGeneration, String reportType) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(schemeReportGeneration.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SchemeReportGeneration.class));
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
        sysAttachmentDto.setFieldsName(String.format("%s%d", FormatUtils.underlineToCamel(builder.toString(), false), schemeReportGeneration.getAreaGroupId()));
        String[] strings = path.split("\\\\");
        sysAttachmentDto.setFileName(strings[strings.length - 1]);
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
     * @param schemeReportGeneration
     * @return
     * @throws Exception
     */
    private String fullReportPath(BaseReportTemplate baseReportTemplate, SchemeReportGeneration schemeReportGeneration) throws Exception {
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
            //书签map
            Map<String, String> bookmarkMap = Maps.newHashMap();
            Map<String, String> fileFixedMap = Maps.newHashMap();
            Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = getReportMap(baseReportTemplate, new Document(tempDir), schemeReportGeneration);
            if (CollectionUtils.isNotEmpty(mapSet)) {
                Iterator<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> iterator = mapSet.iterator();
                while (iterator.hasNext()) {
                    //这一层实际只有一个值
                    Map<String, Map<BaseReportFieldReplaceEnum, Object>> mapMap = iterator.next();
                    Iterator<Map.Entry<String, Map<BaseReportFieldReplaceEnum, Object>>> entryIterator = mapMap.entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry<String, Map<BaseReportFieldReplaceEnum, Object>> mapEntry = entryIterator.next();
                        Map<BaseReportFieldReplaceEnum, Object> baseReportFieldReplaceEnumObjectMap = mapEntry.getValue();
                        String wordKey = mapEntry.getKey();
                        Iterator<Map.Entry<BaseReportFieldReplaceEnum, Object>> it = baseReportFieldReplaceEnumObjectMap.entrySet().iterator();
                        //最终要得这一层
                        while (it.hasNext()) {
                            Map.Entry<BaseReportFieldReplaceEnum, Object> enumObjectEntry = it.next();
                            BaseReportFieldReplaceEnum replaceEnum = enumObjectEntry.getKey();
                            Object value = enumObjectEntry.getValue();
                            //文本(字符串)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.TEXT.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        textMap.put(wordKey, value.toString());
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                }
                            }
                            //(书签)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.BOOKMARK.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        bookmarkMap.put(wordKey, value.toString());
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                    logger.info(System.getProperty("file.encoding"));
                                }
                            }
                            //附件(子模板)需要替换才能合并到目标文档 (如配置好的模板)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        String dir = value.toString();
                                        Document docDelete = new Document(dir);
                                        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(docDelete);
                                        if (bookmarkCollection.getCount() >= 1) {
                                            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                                                docDelete.getRange().getBookmarks().remove(bookmarkCollection.get(i).getName());
                                            }
                                            docDelete.save(dir);
                                        }
                                        fileFixedMap.put(wordKey, dir);
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                }
                            }
                            //固定word (或者已经处理好了的)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE_FIXED.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        fileFixedMap.put(wordKey, value.toString());
                                    }
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
                //必要的,因为模板里面会插入可能又待替换模板
                AsposeUtils.replaceText(tempDir, textMap);
            }
            if (!fileFixedMap.isEmpty()) {
                AsposeUtils.replaceBookmarkToFile(tempDir, fileFixedMap);
            }
            if (!bookmarkMap.isEmpty()) {
                AsposeUtils.replaceBookmark(tempDir, bookmarkMap, false);
            }
        }
        return tempDir;
    }


    /**
     * 获取报告模板数据数据
     *
     * @param baseReportTemplate
     * @param schemeReportGeneration
     * @param document
     * @return 如:文号,<文号,四川协和预评（2019）0001号>
     * @throws Exception
     */
    private Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> getReportMap(BaseReportTemplate baseReportTemplate, Document document, SchemeReportGeneration schemeReportGeneration) throws Exception {
        Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = Sets.newHashSet();
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(schemeReportGeneration.getProjectPlanId());
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(schemeReportGeneration.getProjectId(), schemeReportGeneration.getAreaGroupId(), baseReportTemplate.getId(), projectPlan);
        List<BaseReportField> fieldList = baseReportFieldService.query(new BaseReportField());
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
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.REPORTNUMBER.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getWordNumber(), true, true, false, mapSet);
                    }
                }
                //报告出具日期
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.reportIssuanceDate.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.reportIssuanceDate.getName());
                    if (baseReportField != null) {
                        String reportIssuanceStr = null;
                        if (schemeReportGeneration.getReportIssuanceDate() != null) {
                            reportIssuanceStr = DateUtils.format(schemeReportGeneration.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                        } else {
                            reportIssuanceStr = DateUtils.format(generateBaseDataService.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                        }
                        replaceReportPutValue(name, reportIssuanceStr, true, true, false, mapSet);
                    }
                }
                //协助工作人员
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssistanceStaff.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.AssistanceStaff.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getAssistanceStaff(schemeReportGeneration.getRealEstateAppraiser()), true, true, false, mapSet);
                    }
                }
                //评估假设
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEVALUATION_HYPOTHESIS(), true, true, false, mapSet);
                    }
                }
                //评估依据
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_BASIS.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EVALUATION_BASIS.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEVALUATION_BASIS(), true, true, false, mapSet);
                    }
                }
                //评估原则
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEVALUATION_PRINCIPLE(), true, true, false, mapSet);
                    }
                }
                //报告分析
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ReportAnalysis.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ReportAnalysis.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getReportAnalysis(), true, true, false, mapSet);
                    }
                }
                //作业结束时间
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HomeWorkEndTime.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.HomeWorkEndTime.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getHomeWorkEndTime(schemeReportGeneration.getHomeWorkEndTime()), true, true, false, mapSet);
                    }
                }
                //作业开始时间
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HomeWorkStartTime.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.HomeWorkStartTime.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getHomeWorkStartTime(), true, true, false, mapSet);
                    }
                }
                //现场查勘期
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.surveyExamineDate.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.surveyExamineDate.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSurveyExamineDate(schemeReportGeneration.getInvestigationsStartDate(), schemeReportGeneration.getInvestigationsEndDate()), true, true, false, mapSet);
                    }
                }
                //现场查勘人员
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.surveyExamineCreate.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.surveyExamineCreate.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSurveyExamineCreate(), true, true, false, mapSet);
                    }
                }
                AdCompanyQualificationDto qualificationDto = generateBaseDataService.getCompanyQualificationForPractising();
                if (qualificationDto != null) {
                    //房地产估价机构营业执照复印件
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency(), false, false, true, mapSet);
                        }
                    }
                    //房地产估价机构资质证书复印件
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution(), false, false, true, mapSet);
                        }
                    }
                    //机构住所
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationAddress.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_organizationAddress.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationAddress(), true, true, false, mapSet);
                        }
                    }
                    //机构名称
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationName.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_organizationName.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationName(), true, true, false, mapSet);
                        }
                    }
                    //房地产估价机构
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationName(), true, true, false, mapSet);
                        }
                    }
                    //机构名称法定代表人
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_legalRepresentative.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_legalRepresentative.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getLegalRepresentative(), true, true, false, mapSet);
                        }
                    }
                    //机构工商注册号
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_registeredNo.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_registeredNo.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getRegisteredNo(), true, true, false, mapSet);
                        }
                    }
                    //机构资质等级
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationRank.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_organizationRank.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getOrganizationRank(), true, true, false, mapSet);
                        }
                    }
                    //机构证书号
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_certificateNo.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_certificateNo.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getCertificateNo(), true, true, false, mapSet);
                        }
                    }
                    //证书有效期
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName(), name)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(name, qualificationDto.getCertificateEffectiveDate(), true, true, false, mapSet);
                        }
                    }
                }
                //注册房产估价师
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuer.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.RegisteredRealEstateValuer.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRegisteredRealEstateValuer(schemeReportGeneration.getRealEstateAppraiser()), true, true, false, mapSet);
                    }
                }
                //注册房产估价师及注册号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerAndNumber.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.RegisteredRealEstateValuerAndNumber.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, String.format("%s及%s", generateBaseDataService.getRegisteredRealEstateValuer(schemeReportGeneration.getRealEstateAppraiser()), generateBaseDataService.getRegistrationNumber(schemeReportGeneration.getRealEstateAppraiser())), true, true, false, mapSet);
                    }
                }
                //注册房产估价师 注册号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.registrationNumber.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.registrationNumber.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRegistrationNumber(schemeReportGeneration.getRealEstateAppraiser()), true, true, false, mapSet);
                    }
                }
                //注册房产估价师 注册房地产估价师注册证书复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(schemeReportGeneration.getRealEstateAppraiser()), false, false, true, mapSet);
                    }
                }
                //房地产总价
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.TotalRealEstatePrice.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.TotalRealEstatePrice.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getTotalRealEstatePrice(), true, true, false, mapSet);
                    }
                }
                //大写金额
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CapitalizationAmount.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CapitalizationAmount.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCapitalizationAmount(), true, true, false, mapSet);
                    }
                }
                //假设开发法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DevelopmentAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //假设开发法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DevelopmentAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //假设开发法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistThink.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DevelopmentAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDevelopmentAssistThink(), true, true, false, mapSet);
                    }
                }
                //收益法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.IncomeAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getIncomeAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //收益法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.IncomeAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getIncomeAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //收益法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistThink.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.IncomeAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getIncomeAssistThink(), true, true, false, mapSet);
                    }
                }
                //市场比较法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CompareAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCompareAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //市场比较法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CompareAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCompareAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //市场比较法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistThink.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CompareAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCompareAssistThink(), true, true, false, mapSet);
                    }
                }
                //成本法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CostAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCostAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //成本法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CostAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCostAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //成本法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistThink.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CostAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCostAssistThink(), true, true, false, mapSet);
                    }
                }
                //区位
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Location.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Location.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getLocation_(), true, true, false, mapSet);
                        //由于区位以后可能会变,现在取权利人
                        if (false) {
                            BaseReportFieldEnum.notPowerPerson.getName();
                            replaceReportPutValue(name, generateBaseDataService.getNotPowerPerson(), true, true, false, mapSet);
                        }
                        if (false) {
                            BaseReportFieldEnum.powerPerson.getName();
                            replaceReportPutValue(name, generateBaseDataService.getPowerPerson(), true, true, false, mapSet);
                        }
                    }
                }
                //价值类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueType.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueType.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueType(), true, true, false, mapSet);
                    }
                }
                //价值定义
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DefinitionValue.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DefinitionValue.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getDefinitionValue(), true, true, false, mapSet);
                    }
                }
                //价值含义
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueImplication.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueImplication.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueImplication(), true, true, false, mapSet);
                    }
                }
                //设定用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SetUse.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SetUse.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSetUse(), true, true, false, mapSet);
                    }
                }
                //证载用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CertificationPurpose.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CertificationPurpose.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryCertificateUses(), true, true, false, mapSet);
                    }
                }
                //证载用途分述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SeparationCertificateUses.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SeparationCertificateUses.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSeparationCertificateUses(), true, true, false, mapSet);
                    }
                }
                //证载用途总括
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SummaryCertificateUses.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SummaryCertificateUses.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryCertificateUses(), true, true, false, mapSet);
                    }
                }
                //房产类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HouseType.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.HouseType.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSetUse(), true, true, false, mapSet);
                    }
                }
                //土地实际用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandPracticalUse.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.LandPracticalUse.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getLandPracticalUse(), true, true, false, mapSet);
                    }
                }
                //使用权类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.UseRightType.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.UseRightType.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getUseRightType(), true, true, false, mapSet);
                    }
                }
                //评估面积
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssessArea.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.AssessArea.getName());
                    if (baseReportField != null) {
                        if (baseReportField != null) {
                            replaceReportPutValue(name, generateBaseDataService.getAssessArea().toString(), true, true, false, mapSet);
                        }
                    }
                }
                //委托目的表述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatementPurposeEntrustment.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatementPurposeEntrustment.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getStatementPurposeEntrustment(), true, true, false, mapSet);
                    }
                }
                //评估方法
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethod.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethod.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryEvaluationMethod(), true, true, false, mapSet);
                    }
                }
                //评估方法总括
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SummaryEvaluationMethod.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SummaryEvaluationMethod.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSummaryEvaluationMethod(), true, true, false, mapSet);
                    }
                }
                //评估方法分述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssessmentMethods.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.AssessmentMethods.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getAssessmentMethods(), true, true, false, mapSet);
                    }
                }
                //土地他项权利情况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.inventoryRight.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.inventoryRight.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getInventoryRight(), true, true, false, mapSet);
                    }
                }
                //土地使用管制
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandUseControl.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.LandUseControl.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getLandUseControl(), true, true, false, mapSet);
                    }
                }
                //出租或占用情况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.rentalPossessionDesc.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.rentalPossessionDesc.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getRentalPossessionDesc(), true, true, false, mapSet);
                    }
                }
                //价值时点
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePoint.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueTimePoint.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueTimePoint(), true, true, false, mapSet);
                    }
                }
                //价值时点说明
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePointRemark.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueTimePointRemark.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueTimePointRemark(), true, true, false, mapSet);
                    }
                }
                //估价技术思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationThink.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEvaluationThink(), true, true, false, mapSet);
                    }
                }
                if (true) {
                    List<SchemeJudgeObject> schemeJudgeObjectList = generateBaseDataService.getSchemeJudgeObjectList();
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                        //分类评估单价
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGory.getName(), name)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationPriceCateGory.getName());
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getEvaluationPriceCateGoryOne(), true, true, false, mapSet);
                            }
                        }
                        //分类评估面积
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationAreaCateGory.getName(), name)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationAreaCateGory.getName());
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getEvaluationAreaCateGoryOne(), true, true, false, mapSet);
                            }
                        }
                        //分类评估总价
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName(), name)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName());
                            if (baseReportField != null) {
                                replaceReportPutValue(name, generateBaseDataService.getEvaluationPriceCateGoryTotalOne(), true, true, false, mapSet);
                            }
                        }
                    }
                }
                //选择估价方法
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionValuationMethod.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SelectionValuationMethod.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSelectionValuationMethod(), true, true, false, mapSet);
                    }
                }
                //分类评估单价计算式
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationExpression.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationExpression.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEvaluationExpression(), true, true, false, mapSet);
                    }
                }
                //分类评估方法结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethodResult.getName());
                    if (baseReportField != null) {
//                        replaceReportPutValue(name, generateBaseDataService.getEvaluationMethodResult(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT), false, false, true, mapSet);
                    }
                }
                //权重说明
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.WeightSpecification.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.WeightSpecification.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getWeightSpecification(), true, true, false, mapSet);
                    }
                }
                //价值表达结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueExpressionResult.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueExpressionResult.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValueExpressionResult(), true, true, false, mapSet);
                    }
                }
                //法定优选受偿款
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatutoryOptimumReimbursement.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getStatutoryOptimumReimbursement(), true, true, false, mapSet);
                    }
                }
                //房屋所有权登记状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HousingOwnershipRegistrationStatementSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getHousingOwnershipRegistrationStatementSheet(), false, false, true, mapSet);
                }
                //估价对象区位状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectAreaStatusSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getJudgeObjectAreaStatusSheet(), false, false, true, mapSet);
                }
                //估价土地实体状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectLandStateSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getJudgeObjectLandStateSheet(), false, false, true, mapSet);
                }
                //估价对象建筑实体状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildLandStateSheet.getName(), name)) {
                    replaceReportPutValue(name, generateBaseDataService.getJudgeBuildLandStateSheet(), false, false, true, mapSet);

                }
                //估价项目名称
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValuationProjectName.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValuationProjectName.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValuationProjectName(), true, true, false, mapSet);
                    }
                }
                //估价结果一览表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildResultSurveySheet.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.judgeBuildResultSurveySheet.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getjudgeBuildResultSurveySheet(), false, false, true, mapSet);
                    }
                }
                //计算过程
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ComputationProcess.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ComputationProcess.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(name, generateBaseDataService.getComputationProcess(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //参数选取与应用
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionApplicationParameters.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SelectionApplicationParameters.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(name, generateBaseDataService.getSelectionApplicationParameters(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //建筑物权益状况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatusBuildingRightsInterests.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatusBuildingRightsInterests.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
//                            replaceReportPutValue(name, generateBaseDataService.getStatusBuildingRightsInterests(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //本次估价的总体思路和评估方法的选取
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(name, generateBaseDataService.gettheGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //分类评估方法结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethodResult.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEvaluationMethodResult(AssessDataDicKeyConstant.REPORT_TYPE_RESULT), false, false, true, mapSet);
                    }
                }

                //估价委托书复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.JUDGEOBJECTPRINCIPALCOPYSHEET.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.JUDGEOBJECTPRINCIPALCOPYSHEET.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET(), false, false, true, mapSet);
                    }
                }

                //估计对象位置示意图
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getEstimatedObjectLocationDiagram(), false, false, true, mapSet);
                    }
                }

                //估价对象实况照片
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Valuation_Target_Live_Photos.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Valuation_Target_Live_Photos.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getValuation_Target_Live_Photos(), false, false, true, mapSet);
                    }
                }

                //估价对象权属证明复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object(), false, false, true, mapSet);
                    }
                }

                //估价中引用的专用文件资料
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getSpecial_documentation_referenced_in_valuation(), false, false, true, mapSet);
                    }
                }

                //委托人
                if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), name)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.PRINCIPAL.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(name, generateBaseDataService.getPrincipal(), true, true, false, mapSet);
                    }
                }
            }
        }
        return mapSet;
    }


    private void replaceReportPutValue(String name, String value, boolean text, boolean bookmark, boolean fileFixed, Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> mapSet) {
        if (text) {
            mapSet.add(getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum.TEXT,
                    String.format("${%s}", name), value));
        }
        if (bookmark) {
            mapSet.add(getBaseReportFieldReplaceEnumMap(
                    BaseReportFieldReplaceEnum.BOOKMARK, name, value));
        }
        if (fileFixed) {
            mapSet.add(getBaseReportFieldReplaceEnumMap(
                    BaseReportFieldReplaceEnum.FILE_FIXED, name, value));
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
    private Map<String, Map<BaseReportFieldReplaceEnum, Object>> getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum baseReportFieldReplaceEnum, String wordKey, Object value) {
        Map<String, Map<BaseReportFieldReplaceEnum, Object>> stringEntryMap = new HashMap<String, Map<BaseReportFieldReplaceEnum, Object>>(1);
        Map<BaseReportFieldReplaceEnum, Object> enumObjectMap = new HashMap<BaseReportFieldReplaceEnum, Object>(1);
        enumObjectMap.put(baseReportFieldReplaceEnum, value);
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

    private BaseReportField whereBaseReportFieldByName(List<BaseReportField> baseReportFields, String name) {
        if (CollectionUtils.isNotEmpty(baseReportFields)) {
            for (BaseReportField baseReportField : baseReportFields) {
                if (Objects.equal(name, baseReportField.getName())) {
                    return baseReportField;
                }
            }
        }
        return null;
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
