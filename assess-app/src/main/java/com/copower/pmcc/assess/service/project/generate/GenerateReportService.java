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
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
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
        String tempDir = null;
        SysAttachmentDto query = new SysAttachmentDto();
        query.setTableId(baseReportTemplate.getId());
        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDtoList.get(0).getId());
            Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = Sets.newHashSet();
            Document document = new Document(tempDir);
            //获取待替换文本的集合
            List<String> regexS = generateCommonMethod.specialTreatment(AsposeUtils.getRegexList(document, null));
            //获取所有书签集合
            BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
            if (bookmarkCollection.getCount() >= 1) {
                for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                    BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                    String name = AsposeUtils.getChinese(bookmarkCollection.get(i).getName());
                    if (StringUtils.isEmpty(name)) {
                        name = bookmarkCollection.get(i).getName();
                    }
                    regexDto.setName(name).setChineseName(name).setType(BaseReportFieldReplaceEnum.TEXT.getKey());
                    bookmarkAndRegexDtoHashSet.add(regexDto);
                }
            }
            if (CollectionUtils.isNotEmpty(regexS)) {
                for (String name : regexS) {
                    BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                    regexDto.setName(name).setChineseName(name).setType(BaseReportFieldReplaceEnum.TEXT.getKey());
                    bookmarkAndRegexDtoHashSet.add(regexDto);
                }
            }
            //由于模板变动某些数据提取不到,直接再次把枚举的所有数据填充一次(必须的特别市2019-03-12之后描述委估对象方式变化了,变为后台直接整段话描述)
            for (BaseReportFieldEnum baseReportFieldEnum : BaseReportFieldEnum.values()) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setName(baseReportFieldEnum.getName()).setChineseName(baseReportFieldEnum.getName()).setType(BaseReportFieldReplaceEnum.TEXT.getKey());
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportGeneration.getProjectPlanId());
            ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(generateReportGeneration.getProjectId()));
            GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, generateReportGeneration.getAreaGroupId(), baseReportTemplate, projectPlan);
            if (CollectionUtils.isNotEmpty(bookmarkAndRegexDtoHashSet)) {
                tempDir = generateCompareFile(bookmarkAndRegexDtoHashSet, generateBaseDataService, tempDir, generateReportGeneration, reportType);
            }
        }
        return tempDir;
    }


    /**
     * 获取报告模板替换后的模板
     *
     * @param localPath
     * @param generateReportGeneration
     * @throws Exception
     */
    public String generateCompareFile(Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet, GenerateBaseDataService generateBaseDataService, String localPath, GenerateReportGeneration generateReportGeneration, String reportType) throws Exception {
        Map<String, String> textMap = Maps.newHashMap();
        Map<String, String> preMap = Maps.newHashMap();
        Map<String, String> bookmarkMap = Maps.newHashMap();
        Map<String, String> fileMap = Maps.newHashMap();
        for (BookmarkAndRegexDto bookmarkAndRegex : bookmarkAndRegexDtoHashSet) {
            try {
                String name = StringUtils.isNotBlank(bookmarkAndRegex.getChineseName()) ? bookmarkAndRegex.getChineseName() : bookmarkAndRegex.getName();
                if (StringUtils.isEmpty(name)) {
                    continue;
                }
                //文号
                if (Objects.equal(BaseReportFieldEnum.ReportNumber.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWordNumber());
                    }
                }
                //报告类别
                if (Objects.equal(BaseReportFieldEnum.ReportingCategories.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        if (StringUtils.isNotBlank(reportType)) {
                            BaseDataDic reportTypeDic = baseDataDicService.getCacheDataDicByFieldName(reportType);
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportTypeDic.getName());
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
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportIssuanceStr);
                    }
                }
                //协助工作人员
                if (Objects.equal(BaseReportFieldEnum.AssistanceStaff.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssistanceStaff(generateReportGeneration.getRealEstateAppraiser()));
                    }
                }
                //评估假设
                if (Objects.equal(BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.HYPOTHESIS));
                    }
                }
                //评估依据
                if (Objects.equal(BaseReportFieldEnum.EVALUATION_BASIS.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.BASIS));
                    }
                }
                //评估原则
                if (Objects.equal(BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.PRINCIPLE));
                    }
                }
                //变现能力分析
                if (Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY));
                    }
                }
                //风险提示
                if (Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_RISK.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_RISK));
                    }
                }
                //特别提示
                if (Objects.equal(BaseReportFieldEnum.HotTip.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip());
                    if (baseReportField != null) {
                    }
                }
                //作业结束时间
                if (Objects.equal(BaseReportFieldEnum.HomeWorkEndTime.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkEndTime(generateReportGeneration.getHomeWorkEndTime()));
                    }
                }
                //作业开始时间
                if (Objects.equal(BaseReportFieldEnum.HomeWorkStartTime.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkStartTime());
                    }
                }
                //现场查勘期
                if (Objects.equal(BaseReportFieldEnum.surveyExamineDate.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineDate(generateReportGeneration.getInvestigationsStartDate(), generateReportGeneration.getInvestigationsEndDate()));
                    }
                }
                //现场查勘人员
                if (Objects.equal(BaseReportFieldEnum.surveyExamineCreate.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineCreate());
                    }
                }
                AdCompanyQualificationDto qualificationDto = generateBaseDataService.getCompanyQualificationForPractising();
                if (qualificationDto != null) {
                    //房地产估价机构营业执照复印件
                    if (Objects.equal(BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency());
                        }
                    }
                    //房地产估价机构资质证书复印件
                    if (Objects.equal(BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution());
                        }
                    }
                    //机构住所
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationAddress.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationAddress());
                        }
                    }
                    //机构名称
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationName.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationName());
                        }
                    }
                    //房地产估价机构
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationName());
                        }
                    }
                    //机构名称法定代表人
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_legalRepresentative.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getLegalRepresentative());
                        if (baseReportField != null) {
                        }
                    }
                    //机构工商注册号
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_registeredNo.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getRegisteredNo());
                        }
                    }
                    //机构资质等级
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationRank.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationRank());
                        }
                    }
                    //机构证书号
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_certificateNo.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getCertificateNo());
                        }
                    }
                    //证书有效期
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        if (baseReportField != null) {
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getCertificateEffectiveDate());
                        }
                    }
                    //房地产估价机构信息
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationInfo.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getXIEHE_organizationInfo(qualificationDto));
                        if (baseReportField != null) {
                        }
                    }
                    //经营范围
                    if (Objects.equal(BaseReportFieldEnum.BusinessScope.getName(), name)) {
                        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBusinessScope(qualificationDto));
                        if (baseReportField != null) {
                        }
                    }
                }
                //注册房产估价师
                if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuer.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuer(generateReportGeneration.getRealEstateAppraiser()));
                    }
                }
                //注册房产估价师及注册号
                if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerAndNumber.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerAndNumber(generateReportGeneration));
                    }
                }
                //注册房产估价师 注册号
                if (Objects.equal(BaseReportFieldEnum.registrationNumber.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegistrationNumber(generateReportGeneration.getRealEstateAppraiser()));
                    }
                }
                //注册房产估价师 注册房地产估价师注册证书复印件
                if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(generateReportGeneration.getRealEstateAppraiser()));
                    }
                }
                //房地产总价
                if (Objects.equal(BaseReportFieldEnum.TotalRealEstatePrice.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalRealEstatePrice());
                    }
                }
                //房地产总价大写金额
                if (Objects.equal(BaseReportFieldEnum.CapitalizationAmount.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCapitalizationAmount());
                    if (baseReportField != null) {
                    }
                }
                //外聘专家工作概况
                if (Objects.equal(BaseReportFieldEnum.ExpertWorkOverview.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getExpertWorkOverview());
                    if (baseReportField != null) {
                    }
                }
                //共有权情况
                if (Objects.equal(BaseReportFieldEnum.Co_ownership.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCo_ownership());
                    if (baseReportField != null) {
                    }
                }
                //估价对象详细测算过程( 收益法 , 市场比较法)
                if (Objects.equal(BaseReportFieldEnum.DetailedCalculationProcessValuationObject.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDetailedCalculationProcessValuationObject());
                    if (baseReportField != null) {
                    }
                }
                //收益法租赁限制说明
                if (Objects.equal(BaseReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTenancyrestrictionRemark());
                    if (baseReportField != null) {
                    }
                }

                //替代原则
                if (Objects.equal(BaseReportFieldEnum.SubstitutionPrinciple.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSubstitutionPrinciple());
                    if (baseReportField != null) {
                    }
                }
                //未定事项假设
                if (Objects.equal(BaseReportFieldEnum.UncertaintyAssumption.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUncertaintyAssumption());
                    if (baseReportField != null) {
                    }
                }
                //背离事实假设
                if (Objects.equal(BaseReportFieldEnum.DeviationFromFactualAssumptions.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeviationFromFactualAssumptions());
                    if (baseReportField != null) {
                    }
                }
                //不相一致假设
                if (Objects.equal(BaseReportFieldEnum.InconsistentHypothesis.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getInconsistentHypothesis());
                    if (baseReportField != null) {
                    }
                }
                //变现分析通用性
                if (Objects.equal(BaseReportFieldEnum.UniversalityLiquidityAnalysis.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUniversalityLiquidityAnalysis());
                    if (baseReportField != null) {
                    }
                }
                //变现分析独立使用性
                if (Objects.equal(BaseReportFieldEnum.IndependentUsabilityCashFlowAnalysis.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getIndependentUsabilityCashFlowAnalysis());
                    if (baseReportField != null) {
                    }
                }
                //变现分析可分割转让性
                if (Objects.equal(BaseReportFieldEnum.LiquidityAnalysisSeparableTransferability.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityAnalysisSeparableTransferability());
                    if (baseReportField != null) {
                    }
                }
                //变现分析房地产区位
                if (Objects.equal(BaseReportFieldEnum.CashAnalysisRealEstateLocation.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCashAnalysisRealEstateLocation());
                    if (baseReportField != null) {
                    }
                }
                //变现分析房地产开发程度
                if (Objects.equal(BaseReportFieldEnum.CashAnalysisDegreeRealEstateDevelopment.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCashAnalysisDegreeRealEstateDevelopment());
                    if (baseReportField != null) {
                    }
                }
                //变现分析房地产价值大小
                if (Objects.equal(BaseReportFieldEnum.CashAnalysisValueRealEstate.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCashAnalysisValueRealEstate());
                    if (baseReportField != null) {
                    }
                }
                //变现分析房地产市场状况
                if (Objects.equal(BaseReportFieldEnum.CashAnalysisRealEstateMarket.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCashAnalysisRealEstateMarket());
                    if (baseReportField != null) {
                    }
                }
                //变现分析其他
                if (Objects.equal(BaseReportFieldEnum.CashAnalysisOthers.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCashAnalysisOthers());
                    if (baseReportField != null) {
                    }
                }
                //变现拍卖底价规定
                if (Objects.equal(BaseReportFieldEnum.BasePriceRegulationCashAuction.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBasePriceRegulationCashAuction());
                    if (baseReportField != null) {
                    }
                }
                //变现时间长短
                if (Objects.equal(BaseReportFieldEnum.HowLongLiquidationTime.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHowLongLiquidationTime());
                    if (baseReportField != null) {
                    }
                }
                //变现综合分析
                if (Objects.equal(BaseReportFieldEnum.Cash_inComprehensiveAnalysis.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCash_inComprehensiveAnalysis());
                    if (baseReportField != null) {
                    }
                }
                //风险区域规划、城市发展战略、城市规划、土地利用对房地产的影响
                if (Objects.equal(BaseReportFieldEnum.ThreeMajorRiskPlanning.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getThreeMajorRiskPlanning());
                    if (baseReportField != null) {
                    }
                }
                //风险相邻物业抵押影响
                if (Objects.equal(BaseReportFieldEnum.TheImpactMortgageRiskyNeighbouringProperty.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTheImpactMortgageRiskyNeighbouringProperty());
                    if (baseReportField != null) {
                    }
                }
                //风险租赁影响
                if (Objects.equal(BaseReportFieldEnum.ImpactRiskLeasing.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getImpactRiskLeasingy());
                    if (baseReportField != null) {
                    }
                }
                //风险整体变现影响
                if (Objects.equal(BaseReportFieldEnum.OverallLiquidityImpactRisk.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOverallLiquidityImpactRisk());
                    if (baseReportField != null) {
                    }
                }
                //风险不可抗力影响
                if (Objects.equal(BaseReportFieldEnum.RiskForceMajeureEffect.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRiskForceMajeureEffect());
                    if (baseReportField != null) {
                    }
                }
                //风险政策变化影响
                if (Objects.equal(BaseReportFieldEnum.ImpactRiskPolicyChange.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getImpactRiskPolicyChange());
                    if (baseReportField != null) {
                    }
                }
                //风险抵押物特殊事件影响
                if (Objects.equal(BaseReportFieldEnum.ImpactSpecialEventsRiskMortgage.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getImpactSpecialEventsRiskMortgage());
                    if (baseReportField != null) {
                    }
                }
                //风险抵押物使用不当
                if (Objects.equal(BaseReportFieldEnum.ImproperUseRiskCollateral.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getImproperUseRiskCollaterale());
                    if (baseReportField != null) {
                    }
                }
                //风险抵押物功能及替代物
                if (Objects.equal(BaseReportFieldEnum.FunctionsSubstitutesRiskMortgage.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFunctionsSubstitutesRiskMortgage());
                    if (baseReportField != null) {
                    }
                }
                //风险抵押物环境影响
                if (Objects.equal(BaseReportFieldEnum.EnvironmentalImpactRiskMortgage.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEnvironmentalImpactRiskMortgage());
                    if (baseReportField != null) {
                    }
                }
                //风险抵押期间因抵押物原因而引起的房地产信贷风险关注点
                if (Objects.equal(BaseReportFieldEnum.ConcernRealEstateCreditRiskCausedMortgageReasonsDuringRiskMortgagePeriod.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getConcernRealEstateCreditRiskCausedMortgageReasonsDuringRiskMortgagePeriod());
                    if (baseReportField != null) {
                    }
                }
                //依据不足假设
                if (Objects.equal(BaseReportFieldEnum.InsufficientHypothesis.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getInsufficientHypothesis());
                    if (baseReportField != null) {
                    }
                }
                //变现比率
                if (Objects.equal(BaseReportFieldEnum.LiquidRatios.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidRatios());
                    if (baseReportField != null) {
                    }
                }
                //出具报告城市
                if (Objects.equal(BaseReportFieldEnum.ReportCity.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportCity());
                    if (baseReportField != null) {
                    }
                }
                //假设开发法适用原因
                if (Objects.equal(BaseReportFieldEnum.DevelopmentAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDevelopmentAssistApplyReason());
                    }
                }
                //假设开发法不适用原因
                if (Objects.equal(BaseReportFieldEnum.DevelopmentAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDevelopmentAssistNotApplicableReason());
                    }
                }
                //假设开发法评估思路
                if (Objects.equal(BaseReportFieldEnum.DevelopmentAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDevelopmentAssistThink());
                    }
                }
                //收益法适用原因
                if (Objects.equal(BaseReportFieldEnum.IncomeAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getIncomeAssistApplyReason());
                    }
                }
                //收益法不适用原因
                if (Objects.equal(BaseReportFieldEnum.IncomeAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getIncomeAssistNotApplicableReason());
                    }
                }
                //收益法评估思路
                if (Objects.equal(BaseReportFieldEnum.IncomeAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getIncomeAssistThink());
                    }
                }
                //市场比较法适用原因
                if (Objects.equal(BaseReportFieldEnum.CompareAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCompareAssistApplyReason());
                    }
                }
                //市场比较法不适用原因
                if (Objects.equal(BaseReportFieldEnum.CompareAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCompareAssistNotApplicableReason());
                    }
                }
                //市场比较法评估思路
                if (Objects.equal(BaseReportFieldEnum.CompareAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCompareAssistThink());
                    }
                }
                //成本法适用原因
                if (Objects.equal(BaseReportFieldEnum.CostAssistApplyReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCostAssistApplyReason());
                    }
                }
                //成本法不适用原因
                if (Objects.equal(BaseReportFieldEnum.CostAssistNotApplicableReason.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCostAssistNotApplicableReason());
                    }
                }
                //成本法评估思路
                if (Objects.equal(BaseReportFieldEnum.CostAssistThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCostAssistThink());
                    }
                }
                //区位
                if (Objects.equal(BaseReportFieldEnum.Location.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLocation_());
                    }
                }
                //权利人
                if (Objects.equal(BaseReportFieldEnum.PowerPerson.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPowerPerson());
                    if (baseReportField != null) {
                    }
                }
                //价值类型
                if (Objects.equal(BaseReportFieldEnum.ValueType.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueType());
                    }
                }
                //价值类型描述
                if (Objects.equal(BaseReportFieldEnum.ValueTypeDesc.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTypeDesc());
                    }
                }
                //价值内涵
                if (Objects.equal(BaseReportFieldEnum.ValueConnotation.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueImplication());
                    }
                }
                //价值内涵描述
                if (Objects.equal(BaseReportFieldEnum.ValueImplicationDesc.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueImplicationDesc());
                    }
                }
                //设定用途
                if (Objects.equal(BaseReportFieldEnum.SetUse.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse());
                    if (baseReportField != null) {
                    }
                }
                //证载用途 / 证载用途分述
                if (Objects.equal(BaseReportFieldEnum.CertificationPurpose.getName(), name) || Objects.equal(BaseReportFieldEnum.SeparationCertificateUses.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses());
                    if (baseReportField != null) {
                    }
                }
                //证载用途总括
                if (Objects.equal(BaseReportFieldEnum.SummaryCertificateUses.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSummaryCertificateUses());
                    }
                }
                //房产类型
                if (Objects.equal(BaseReportFieldEnum.HouseType.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse());
                    }
                }
                //土地 实际用途
                if (Objects.equal(BaseReportFieldEnum.PracticalUse.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPracticalUse());
                    if (baseReportField != null) {
                    }
                }
                //建筑结构类别
                if (Objects.equal(BaseReportFieldEnum.BuildingStructureCategory.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildingStructureCategory());
                    if (baseReportField != null) {
                    }
                }
                //土地使用权类型
                if (Objects.equal(BaseReportFieldEnum.LandUseRightType.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUseRightType());
                    if (baseReportField != null) {
                    }
                }
                //评估面积
                if (Objects.equal(BaseReportFieldEnum.AssessArea.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessArea());
                    if (baseReportField != null) {
                    }
                }
                //委托目的表述
                if (Objects.equal(BaseReportFieldEnum.StatementPurposeEntrustment.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatementPurposeEntrustment());
                    }
                }
                //委托目的
                if (Objects.equal(BaseReportFieldEnum.DelegatePurpose.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDelegatePurpose());
                    }
                }
                //评估方法 ,估价对象评估方法
                if (Objects.equal(BaseReportFieldEnum.EvaluationMethod.getName(), name) || Objects.equal(BaseReportFieldEnum.EvaluationMethodValuationObject.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethodValuationObject());
                    if (baseReportField != null) {
                    }
                }
                //评估方法总括
                if (Objects.equal(BaseReportFieldEnum.SummaryEvaluationMethod.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSummaryEvaluationMethod());
                    }
                }
                //评估方法分述
                if (Objects.equal(BaseReportFieldEnum.AssessmentMethods.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessmentMethods());
                    }
                }
                //技术思路通用模板
                if (Objects.equal(BaseReportFieldEnum.GeneralTemplateTechnicalIdea.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getGeneralTemplateTechnicalIdea());
                    if (baseReportField != null) {
                    }
                }
                //土地他项权利情况
                if (Objects.equal(BaseReportFieldEnum.inventoryRight.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getInventoryRight());
                    }
                }
                //他权有无租赁权
                if (Objects.equal(BaseReportFieldEnum.HisRightHasLease.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightHasLease());
                    if (baseReportField != null) {
                    }
                }
                //土地使用管制
                if (Objects.equal(BaseReportFieldEnum.LandUseControl.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandUseControl());
                    }
                }
                //出租或占用情况
                if (Objects.equal(BaseReportFieldEnum.rentalPossessionDesc.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRentalPossessionDesc());
                    }
                }
                //价值时点
                if (Objects.equal(BaseReportFieldEnum.ValueTimePoint.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePoint());
                    }
                }
                //估价时点
                if (Objects.equal(BaseReportFieldEnum.dateValue.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePoint());
                    if (baseReportField != null) {
                    }
                }
                //评估基准日
                if (Objects.equal(BaseReportFieldEnum.ValueTimePoint2.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePoint());
                    if (baseReportField != null) {
                    }
                }
                //价值时点说明
                if (Objects.equal(BaseReportFieldEnum.ValueTimePointRemark.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePointRemark());
                    }
                }
                //评估基准日说明
                if (Objects.equal(BaseReportFieldEnum.ValueTimePointRemark2.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePointRemark());
                    if (baseReportField != null) {
                    }
                }
                //申报所启用表单类型
                if (Objects.equal(BaseReportFieldEnum.TypesFormEnabledDeclarationOffice.getName(), name) || Objects.equal(BaseReportFieldEnum.DecalreFormTypeAll.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTypesFormEnabledDeclarationOffice());
                    if (baseReportField != null) {
                    }
                }
                //税费负担
                if (Objects.equal(BaseReportFieldEnum.TaxBurden.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTaxBurden());
                    if (baseReportField != null) {
                    }
                }
                //付款方式
                if (Objects.equal(BaseReportFieldEnum.PaymentMethod.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPaymentMethod());
                    if (baseReportField != null) {
                    }
                }
                //估价技术思路
                if (Objects.equal(BaseReportFieldEnum.EvaluationThink.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationThink());
                    if (baseReportField != null) {
                    }
                }
                //分类评估单价
                if (Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGory.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationPriceCateGoryOne());
                    }
                }
                //分类评估面积
                if (Objects.equal(BaseReportFieldEnum.EvaluationAreaCateGory.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationAreaCateGoryOne());
                    if (baseReportField != null) {
                    }
                }
                //分类评估总价
                if (Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationPriceCateGoryTotalOne());
                    }
                }
                //估价对象的总价
                if (Objects.equal(BaseReportFieldEnum.TotalValueValuationObject.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalValueValuationObject());
                    if (baseReportField != null) {
                    }
                }
                //估价对象的单价
                if (Objects.equal(BaseReportFieldEnum.UnitPriceValuator.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUnitPriceValuator());
                    if (baseReportField != null) {
                    }
                }
                //估价对象选择估价方法
                if (Objects.equal(BaseReportFieldEnum.SelectionValuationMethod.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionValuationMethod());
                    if (baseReportField != null) {
                    }
                }
                //分类评估单价计算试
                if (Objects.equal(BaseReportFieldEnum.EvaluationExpression.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationExpression());
                    }
                }
                //权重说明
                if (Objects.equal(BaseReportFieldEnum.WeightSpecification.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWeightSpecification());
                    }
                }
                //价值表达结果
                if (Objects.equal(BaseReportFieldEnum.ValueExpressionResult.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueExpressionResult());
                    }
                }
                //变现分析税费
                if (Objects.equal(BaseReportFieldEnum.LIQUIDATION_ANALYSIS.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidationAnalysis(name));
                    }
                }
                //法定优先受偿款
                if (Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement());
                    if (baseReportField != null) {
                    }
                }
                //抵押价值总金额
                if (Objects.equal(BaseReportFieldEnum.totalAmountMortgageValue.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValue());
                    if (baseReportField != null) {
                    }
                }
                //抵押价值总金额大写
                if (Objects.equal(BaseReportFieldEnum.totalAmountMortgageValueCapitalization.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValueCapitalization());
                    if (baseReportField != null) {
                    }
                }
                //他权信息公示
                if (Objects.equal(BaseReportFieldEnum.HisRightInfoPublicity.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightInfoPublicity());
                    if (baseReportField != null) {
                    }
                }
                //他权类别
                if (Objects.equal(BaseReportFieldEnum.HisRightType.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightType());
                    if (baseReportField != null) {
                    }
                }
                //有无他项权
                if (Objects.equal(BaseReportFieldEnum.ThereAnyOtherRight.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getThereAnyOtherRight());
                    if (baseReportField != null) {
                    }
                }
                //他权其它
                if (Objects.equal(BaseReportFieldEnum.RightOther.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRightOther());
                    if (baseReportField != null) {
                    }
                }
                //担保物权设立情况
                if (Objects.equal(BaseReportFieldEnum.collateralFound.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCollateralFound());
                    if (baseReportField != null) {
                    }
                }
                //最佳利用描述
                if (Objects.equal(BaseReportFieldEnum.OptimumUtilizationDescription.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOptimumUtilizationDescription());
                    if (baseReportField != null) {
                    }
                }
                //资产清查实际地址
                if (Objects.equal(BaseReportFieldEnum.ActualAddressAssetInventory.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getActualAddressAssetInventory());
                    if (baseReportField != null) {
                    }
                }
                //资产清查证明人
                if (Objects.equal(BaseReportFieldEnum.CertificateAssetInventory.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCertificateAssetInventory());
                    if (baseReportField != null) {
                    }
                }
                //资产清查确认一致
                if (Objects.equal(BaseReportFieldEnum.AssetInventoryConfirmConsistency.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssetInventoryConfirmConsistency());
                    if (baseReportField != null) {
                    }
                }
                //资产清查一致说明
                if (Objects.equal(BaseReportFieldEnum.AssetInventoryAgreement.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssetInventoryAgreement());
                    if (baseReportField != null) {
                    }
                }
                //法定优先受偿款总金额
                if (Objects.equal(BaseReportFieldEnum.StatutoryPriorityAmountTotal.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryPriorityAmountTotal());
                    if (baseReportField != null) {
                    }
                }
                //法定优先受偿款金额
                if (Objects.equal(BaseReportFieldEnum.StatutoryPriorityAmount.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryPriorityAmount());
                    if (baseReportField != null) {
                    }
                }
                //房屋所有权登记状况表
                if (Objects.equal(BaseReportFieldEnum.HousingOwnershipRegistrationStatementSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHousingOwnershipRegistrationStatementSheet());
                }
                //估价对象区位状况表
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectAreaStatusSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectAreaStatusSheet());
                }
                //估价对象权属状况表
                if (Objects.equal(BaseReportFieldEnum.EquityStatusValuatedObject.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusValuatedObjects());
                }
                //估价土地实体状况表
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLandStateSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLandStateSheet());
                }
                //估价对象建筑实体状况表
                if (Objects.equal(BaseReportFieldEnum.JudgeBuildLandStateSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeBuildLandStateSheet());
                }
                //汇总表
                if (Objects.equal(BaseReportFieldEnum.judgeSummarySheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeSummarySheet());
                }
                //土地使用权登记状况表
                if (Objects.equal(BaseReportFieldEnum.judgeObjectLandUseCertificateSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getjudgeObjectLandUseCertificateSheet());
                }
                //估价结果一览表
                if (Objects.equal(BaseReportFieldEnum.JudgeBuildResultSurveySheet.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getjudgeBuildResultSurveySheet());
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
                            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getComputationProcess(sysAttachmentDtoList.get(0)));
                        }
                    }
                }
                //相关参数选取与应用
                if (Objects.equal(BaseReportFieldEnum.SelectionApplicationParameters.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionApplicationParameters());
                    if (baseReportField != null) {
                    }
                }
                //估价对象市场价值的确定
                if (Objects.equal(BaseReportFieldEnum.DeterminationMarketValueValuationObject.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeterminationMarketValueValuationObject());
                    if (baseReportField != null) {
                    }
                }
                //估价对象描述
                if (Objects.equal(BaseReportFieldEnum.PrincipalDescribe.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalDescribe());
                    if (baseReportField != null) {
                    }
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
                            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.gettheGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods(sysAttachmentDtoList.get(0)));
                        }
                    }
                }
                //分类评估方法结果
                if (Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethodResult());
                    }
                }
                //单价调整表
                if (Objects.equal(BaseReportFieldEnum.UnitPriceAdjustmentTable.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUnitPriceAdjustmentTable());
                    if (baseReportField != null) {
                    }
                }
                //估价委托书复印件
                if (Objects.equal(BaseReportFieldEnum.JUDGEOBJECTPRINCIPALCOPYSHEET.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET());
                    }
                }
                //估计对象位置示意图
                if (Objects.equal(BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEstimatedObjectLocationDiagram());
                    }
                }
                //估价对象实况照片
                if (Objects.equal(BaseReportFieldEnum.Valuation_Target_Live_Photos.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuation_Target_Live_Photos());
                    }
                }
                //估价对象权属证明复印件
                if (Objects.equal(BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object());
                    }
                }
                //估价中引用的专用文件资料
                if (Objects.equal(BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSpecial_documentation_referenced_in_valuation());
                    }
                }
                //委托人 估价委托人
                if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipal());
                    }
                }
                // 估价委托人信息
                if (Objects.equal(BaseReportFieldEnum.PrincipalInfo.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalInfo());
                    if (baseReportField != null) {
                    }
                }
                //座落
                if (Objects.equal(BaseReportFieldEnum.Seat.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeat());
                    if (baseReportField != null) {
                    }
                }
                //财产范围说明
                if (Objects.equal(BaseReportFieldEnum.ScopePropertyExplain.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getScopePropertyExplain());
                    if (baseReportField != null) {
                    }
                }
                //建筑面积及评估面积
                if (Objects.equal(BaseReportFieldEnum.BuildingAndAssessArea.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildingAndAssessArea());
                    if (baseReportField != null) {
                    }
                }
                //委托人地址
                if (Objects.equal(BaseReportFieldEnum.PrincipalAddress.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalAddress());
                    }
                }
                //委托人法定代表人
                if (Objects.equal(BaseReportFieldEnum.PrincipalLegalRepresentative.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalLegalRepresentative());
                    }
                }
                //估价项目名称
                if (Objects.equal(BaseReportFieldEnum.ValuationProjectName.getName(), name)) {
                    BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByName(name);
                    if (baseReportField != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuationProjectName());
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return replaceWord(localPath, textMap, preMap, bookmarkMap, fileMap);
    }

    private String replaceWord(String localPath, Map<String, String> textMap, Map<String, String> preMap, Map<String, String> bookmarkMap, Map<String, String> fileMap) throws Exception {
        //替换
        if (!fileMap.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, fileMap);
            AsposeUtils.replaceText(localPath, textMap);
        }
        if (!textMap.isEmpty()) {
            AsposeUtils.replaceText(localPath, textMap);
        }
        if (!bookmarkMap.isEmpty()) {
            AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
        }
        if (!preMap.isEmpty()) {
            AsposeUtils.replaceText(localPath, preMap);
            if (!textMap.isEmpty()) {
                for (Map.Entry<String, String> entry : textMap.entrySet()) {
                    preMap.put(entry.getKey(), entry.getValue());
                }
            }
            AsposeUtils.replaceText(localPath, preMap);
        }
        System.gc();
        return localPath;
    }

}
