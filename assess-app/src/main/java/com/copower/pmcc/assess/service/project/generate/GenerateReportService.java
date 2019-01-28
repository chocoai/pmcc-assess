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
        sysAttachmentDto.setFieldsName(String.format("%s%d",  FormatUtils.underlineToCamel(builder.toString(), false),schemeReportGeneration.getAreaGroupId()));
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
                                    logger.error(String.format("word模板:%s%s", wordKey, "替换失败!"), new Exception());
                                }
                            }
                            //(书签)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.BOOKMARK.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        bookmarkMap.put(wordKey, value.toString());
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", wordKey, "替换失败!"), new Exception());
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
                                    logger.error(String.format("word模板:%s%s", wordKey, "替换失败!"), new Exception());
                                }
                            }
                        }
                    }
                }
            }
            if (!fileFixedMap.isEmpty()) {
                AsposeUtils.replaceBookmarkToFile(tempDir, fileFixedMap);
            }
            if (!bookmarkMap.isEmpty()) {
                AsposeUtils.replaceBookmark(tempDir, bookmarkMap, false);
            }
            if (!textMap.isEmpty()) {
                AsposeUtils.replaceText(tempDir, textMap);
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
        //获取待替换文本的集合
        List<String> regexS = specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                String bookmarkName = getChinese(bookmarkCollection.get(i).getName());
                //文号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.REPORTNUMBER.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.REPORTNUMBER.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getWordNumber(), true, true, false, mapSet);
                    }
                }
                //报告出具日期
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.reportIssuanceDate.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.reportIssuanceDate.getName());
                    if (baseReportField != null) {
                        String reportIssuanceStr = null;
                        if (schemeReportGeneration.getReportIssuanceDate() != null) {
                            reportIssuanceStr = DateUtils.format(schemeReportGeneration.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                        } else {
                            reportIssuanceStr = DateUtils.format(generateBaseDataService.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                        }
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), reportIssuanceStr, true, true, false, mapSet);
                    }
                }
                //协助工作人员
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssistanceStaff.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.AssistanceStaff.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getAssistanceStaff(schemeReportGeneration.getRealEstateAppraiser()), true, true, false, mapSet);
                    }
                }
                //评估假设
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEVALUATION_HYPOTHESIS(), false, true, false, mapSet);
                    }
                }
                //评估依据
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_BASIS.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EVALUATION_BASIS.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEVALUATION_BASIS(), false, true, false, mapSet);
                    }
                }
                //评估原则
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEVALUATION_PRINCIPLE(), false, true, false, mapSet);
                    }
                }
                //报告分析
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ReportAnalysis.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ReportAnalysis.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getReportAnalysis(), false, true, false, mapSet);
                    }
                }
                //作业结束时间
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HomeWorkEndTime.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.HomeWorkEndTime.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getHomeWorkEndTime(schemeReportGeneration.getHomeWorkEndTime()), true, true, false, mapSet);
                    }
                }
                //作业开始时间
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HomeWorkStartTime.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.HomeWorkStartTime.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getHomeWorkStartTime(), true, true, false, mapSet);
                    }
                }
                //现场查勘期
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.surveyExamineDate.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.surveyExamineDate.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getSurveyExamineDate(schemeReportGeneration.getInvestigationsStartDate(), schemeReportGeneration.getInvestigationsEndDate()), true, true, false, mapSet);
                    }
                }
                AdCompanyQualificationDto qualificationDto = generateBaseDataService.getCompanyQualificationForPractising();
                if (qualificationDto != null) {
                    //房地产估价机构营业执照复印件
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency(), false, false, true, mapSet);
                        }
                    }
                    //房地产估价机构资质证书复印件
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution(), false, false, true, mapSet);
                        }
                    }
                    //机构住所
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationAddress.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_organizationAddress.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getOrganizationAddress(), true, true, false, mapSet);
                        }
                    }
                    //机构名称
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationName.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_organizationName.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getOrganizationName(), true, true, false, mapSet);
                        }
                    }
                    //房地产估价机构
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getOrganizationName(), true, true, false, mapSet);
                        }
                    }
                    //机构名称法定代表人
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_legalRepresentative.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_legalRepresentative.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getLegalRepresentative(), true, true, false, mapSet);
                        }
                    }
                    //机构工商注册号
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_registeredNo.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_registeredNo.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getRegisteredNo(), true, true, false, mapSet);
                        }
                    }
                    //机构资质等级
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_organizationRank.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_organizationRank.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getOrganizationRank(), true, true, false, mapSet);
                        }
                    }
                    //机构证书号
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_certificateNo.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_certificateNo.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getCertificateNo(), true, true, false, mapSet);
                        }
                    }
                    //证书有效期
                    if (com.google.common.base.Objects.equal(BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName(), bookmarkName)) {
                        BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName());
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), qualificationDto.getCertificateEffectiveDate(), true, true, false, mapSet);
                        }
                    }
                }
                //注册房产估价师
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuer.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.RegisteredRealEstateValuer.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getRegisteredRealEstateValuer(schemeReportGeneration.getRealEstateAppraiser()), true, true, false, mapSet);
                    }
                }
                //注册房产估价师 注册号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.registrationNumber.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.registrationNumber.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getRegistrationNumber(schemeReportGeneration.getRealEstateAppraiser()), true, true, false, mapSet);
                    }
                }
                //注册房产估价师 注册房地产估价师注册证书复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(schemeReportGeneration.getRealEstateAppraiser()), false, false, true, mapSet);
                    }
                }
                //房地产总价
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.TotalRealEstatePrice.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.TotalRealEstatePrice.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getTotalRealEstatePrice(), true, true, false, mapSet);
                    }
                }
                //大写金额
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CapitalizationAmount.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CapitalizationAmount.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCapitalizationAmount(), true, true, false, mapSet);
                    }
                }
                //假设开发法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistApplyReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DevelopmentAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getDevelopmentAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //假设开发法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistNotApplicableReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DevelopmentAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getDevelopmentAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //假设开发法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DevelopmentAssistThink.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DevelopmentAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getDevelopmentAssistThink(), true, true, false, mapSet);
                    }
                }
                //收益法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistApplyReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.IncomeAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getIncomeAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //收益法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistNotApplicableReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.IncomeAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getIncomeAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //收益法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.IncomeAssistThink.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.IncomeAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getIncomeAssistThink(), true, true, false, mapSet);
                    }
                }
                //市场比较法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistApplyReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CompareAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCompareAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //市场比较法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistNotApplicableReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CompareAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCompareAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //市场比较法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CompareAssistThink.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CompareAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCompareAssistThink(), true, true, false, mapSet);
                    }
                }
                //成本法适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistApplyReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CostAssistApplyReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCostAssistApplyReason(), true, true, false, mapSet);
                    }
                }
                //成本法不适用原因
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistNotApplicableReason.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CostAssistNotApplicableReason.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCostAssistNotApplicableReason(), true, true, false, mapSet);
                    }
                }
                //成本法评估思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CostAssistThink.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CostAssistThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCostAssistThink(), true, true, false, mapSet);
                    }
                }
                //区位
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Location.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Location.getName());
                    if (baseReportField != null) {
                        //由于区位以后可能会变,现在取权利人
                        if (false) {
                            BaseReportFieldEnum.notPowerPerson.getName();
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getNotPowerPerson(), true, true, false, mapSet);
                        }
                        if (true) {
                            BaseReportFieldEnum.powerPerson.getName();
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getPowerPerson(), true, true, false, mapSet);
                        }
                    }
                }
                //价值类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueType.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueType.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getValueType(), true, true, false, mapSet);
                    }
                }
                //价值定义
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DefinitionValue.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DefinitionValue.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getDefinitionValue(), true, true, false, mapSet);
                    }
                }
                //价值含义
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueImplication.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueImplication.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getValueImplication(), true, true, false, mapSet);
                    }
                }
                //设定用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SetUse.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SetUse.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getSetUse(), true, true, false, mapSet);
                    }
                }
                //房产类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HouseType.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.HouseType.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getSetUse(), true, true, false, mapSet);
                    }
                }
                //土地实际用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandPracticalUse.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.LandPracticalUse.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getLandPracticalUse(), true, true, false, mapSet);
                    }
                }
                //使用权类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.UseRightType.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.UseRightType.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getUseRightType(), true, true, false, mapSet);
                    }
                }
                //评估面积
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssessArea.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.AssessArea.getName());
                    if (baseReportField != null) {
                        if (baseReportField != null) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getAssessArea().toString(), true, true, false, mapSet);
                        }
                    }
                }
                //委托目的表述
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatementPurposeEntrustment.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatementPurposeEntrustment.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getStatementPurposeEntrustment(), true, true, false, mapSet);
                    }
                }
                //评估方法
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethod.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethod.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationMethod(), true, true, false, mapSet);
                    }
                }
                //土地他项权利情况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.inventoryRight.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.inventoryRight.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getInventoryRight(), true, true, false, mapSet);
                    }
                }
                //土地使用管制
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandUseControl.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.LandUseControl.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getLandUseControl(), true, true, false, mapSet);
                    }
                }
                //出租或占用情况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.rentalPossessionDesc.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.rentalPossessionDesc.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getRentalPossessionDesc(), true, true, false, mapSet);
                    }
                }
                //价值时点
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePoint.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueTimePoint.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getValueTimePoint(), true, true, false, mapSet);
                    }
                }
                //价值时点说明
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePointRemark.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueTimePointRemark.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getValueTimePointRemark(), true, true, false, mapSet);
                    }
                }
                //估价技术思路
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationThink.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationThink.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationThink(), true, true, false, mapSet);
                    }
                }
                if (true) {
                    List<SchemeJudgeObject> schemeJudgeObjectList = generateBaseDataService.getSchemeJudgeObjectList();
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                        //分类评估单价
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGory.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationPriceCateGory.getName());
                            if (baseReportField != null) {
                                replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationPriceCateGoryOne(), true, true, false, mapSet);
                            }
                        }
                        //分类评估面积
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationAreaCateGory.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationAreaCateGory.getName());
                            if (baseReportField != null) {
                                replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationAreaCateGoryOne(), true, true, false, mapSet);
                            }
                        }
                        //分类评估总价
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName());
                            if (baseReportField != null) {
                                replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationPriceCateGoryTotalOne(), true, true, false, mapSet);
                            }
                        }
                    }
                }
                //选择估价方法
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionValuationMethod.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SelectionValuationMethod.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getSelectionValuationMethod(), true, true, false, mapSet);
                    }
                }
                //分类评估单价计算式
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationExpression.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationExpression.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationExpression(), true, true, false, mapSet);
                    }
                }
                //分类评估方法结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethodResult.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationMethodResult(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT), true, true, false, mapSet);
                    }
                }
                //权重说明
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.WeightSpecification.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.WeightSpecification.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getWeightSpecification(), true, true, false, mapSet);
                    }
                }
                //价值表达结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueExpressionResult.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueExpressionResult.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getValueExpressionResult(), true, true, false, mapSet);
                    }
                }
                //法定优选受偿款
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatutoryOptimumReimbursement.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getStatutoryOptimumReimbursement(), true, true, false, mapSet);
                    }
                }
                //房屋所有权登记状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HousingOwnershipRegistrationStatementSheet.getName(), bookmarkName)) {
                    replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getHousingOwnershipRegistrationStatementSheet(), false, false, true, mapSet);
                }
                //估价对象区位状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectAreaStatusSheet.getName(), bookmarkName)) {
                    replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getJudgeObjectAreaStatusSheet(), false, false, true, mapSet);
                }
                //估价土地实体状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectLandStateSheet.getName(), bookmarkName)) {
                    replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getJudgeObjectLandStateSheet(), false, false, true, mapSet);
                }
                //估价对象建筑实体状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildLandStateSheet.getName(), bookmarkName)) {
                    replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getJudgeBuildLandStateSheet(), false, false, true, mapSet);

                }
                //估价项目名称
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValuationProjectName.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValuationProjectName.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getValuationProjectName(), true, true, false, mapSet);
                    }
                }
                //估价结果一览表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildResultSurveySheet.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.judgeBuildResultSurveySheet.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getjudgeBuildResultSurveySheet(), false, false, true, mapSet);
                    }
                }
                //计算过程
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ComputationProcess.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ComputationProcess.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getComputationProcess(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //参数选取与应用
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionApplicationParameters.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SelectionApplicationParameters.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getSelectionApplicationParameters(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //建筑物权益状况
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatusBuildingRightsInterests.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatusBuildingRightsInterests.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getStatusBuildingRightsInterests(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //本次估价的总体思路和评估方法的选取
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName());
                    SysAttachmentDto query = new SysAttachmentDto();
                    if (baseReportField != null) {
                        query.setTableId(baseReportField.getId());
                        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                            replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.gettheGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods(sysAttachmentDtoList.get(0)), false, false, true, mapSet);
                        }
                    }
                }
                //分类评估方法结果
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethodResult.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEvaluationMethodResult(AssessDataDicKeyConstant.REPORT_TYPE_RESULT), true, true, false, mapSet);
                    }
                }

                //估价委托书复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.JUDGEOBJECTPRINCIPALCOPYSHEET.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.JUDGEOBJECTPRINCIPALCOPYSHEET.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET(), false, false, true, mapSet);
                    }
                }

                //估计对象位置示意图
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getEstimatedObjectLocationDiagram(), false, false, true, mapSet);
                    }
                }

                //估价对象实况照片
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Valuation_Target_Live_Photos.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Valuation_Target_Live_Photos.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getValuation_Target_Live_Photos(), false, false, true, mapSet);
                    }
                }

                //估价对象权属证明复印件
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object(), false, false, true, mapSet);
                    }
                }

                //估价中引用的专用文件资料
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName());
                    if (baseReportField != null) {
                        replaceReportPutValue(bookmarkCollection.get(i).getName(), generateBaseDataService.getSpecial_documentation_referenced_in_valuation(), false, false, true, mapSet);
                    }
                }


            }
        }
        if (CollectionUtils.isNotEmpty(regexS)) {
            for (String name : regexS) {
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
