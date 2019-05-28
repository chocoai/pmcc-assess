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
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.generate.GenerateReportGenerationVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
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
     * 获取生成的数据列表
     *
     * @param projectId
     * @return
     */
    public List<GenerateReportGenerationVo> getGenerateReportGenerationVos(Integer projectId) {
        GenerateReportGeneration where = new GenerateReportGeneration();
        where.setProjectId(projectId);
        List<GenerateReportGeneration> generationList = generateReportGenerationService.generateReportGenerationList(where);
        if (CollectionUtils.isEmpty(generationList)) return null;
        return LangUtils.transform(generationList, o -> generateReportGenerationService.getGenerateReportGenerationVo(o));
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
            generateReportGeneration.setCreator(processControllerComponent.getThisUser());
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
                BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                if(baseReportTemplate == null){
                    continue;
                }
                //房屋评估价值确认书
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_CONFIRMATION_HOUSING_VALUATION)) {
                    String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, baseDataDic.getFieldName());
                    if (StringUtils.isNotBlank(path)) {
                        this.createSysAttachment(path, generateReportGeneration, baseDataDic.getFieldName(), sysAttachmentDtoList);
                    }
                }
                //预评意见书
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT_PROPOSAL)) {
                    String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, baseDataDic.getFieldName());
                    if (StringUtils.isNotBlank(path)) {
                        this.createSysAttachment(path, generateReportGeneration, baseDataDic.getFieldName(), sysAttachmentDtoList);
                    }
                }
                //预评报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT)) {
                    //获取替换后得报告文件路径 ==>
                    String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, baseDataDic.getFieldName());
                    if (StringUtils.isNotBlank(path)) {
                        this.createSysAttachment(path, generateReportGeneration, baseDataDic.getFieldName(), sysAttachmentDtoList);
                    }
                }
                //技术报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY)) {
                    String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, baseDataDic.getFieldName());
                    if (StringUtils.isNotBlank(path)) {
                        this.createSysAttachment(path, generateReportGeneration, baseDataDic.getFieldName(), sysAttachmentDtoList);
                    }
                }
                //结果报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_RESULT)) {
                    String path = this.fullReportPath(baseReportTemplate, generateReportGeneration, baseDataDic.getFieldName());
                    Document doc = new Document(path);
//                        DocumentBuilder builder = new DocumentBuilder(doc);
//                        //“目录”两个字居中显示、加粗、搜宋体
//                        builder.getCurrentParagraph().getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
//                        builder.setBold(true);
//                        builder.writeln("目录");
//                        //清清除所有样式设置
//                        builder.getParagraphFormat().clearFormatting();
//                        //插入目录，这是固定的
//                        builder.insertTableOfContents("\\o \"1-3\" \\h \\z \\u");
//
//                        //将光标移到目录书签
//                        builder.moveToBookmark("TOC");
//                        builder.insertBreak(BreakType.PAGE_BREAK);

                    doc.updateFields();// 更新域
                    doc.save(path);
                    if (StringUtils.isNotBlank(path)) {
                        this.createSysAttachment(path, generateReportGeneration, baseDataDic.getFieldName(), sysAttachmentDtoList);
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
        List<String> FieldsName = Lists.newArrayList();
        for (String s : reportType.split("\\.")) {
            FieldsName.add(s.toUpperCase());
        }
        sysAttachmentDto.setFieldsName(String.format("%s%d", StringUtils.join(FieldsName, "_"), generateReportGeneration.getAreaGroupId()));
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
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWordNumber());
                }
                //报告类别
                if (Objects.equal(BaseReportFieldEnum.ReportingCategories.getName(), name)) {
                    if (StringUtils.isNotBlank(reportType)) {
                        BaseDataDic reportTypeDic = baseDataDicService.getCacheDataDicByFieldName(reportType);
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportTypeDic.getName());
                    }
                }
                //报告出具日期
                if (Objects.equal(BaseReportFieldEnum.ReportIssuanceDate.getName(), name)) {
                    String reportIssuanceStr = null;
                    if (generateReportGeneration.getReportIssuanceDate() != null) {
                        reportIssuanceStr = DateUtils.format(generateReportGeneration.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                    } else {
                        reportIssuanceStr = DateUtils.format(generateBaseDataService.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                    }
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportIssuanceStr);
                }
                //协助工作人员
                if (Objects.equal(BaseReportFieldEnum.AssistanceStaff.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssistanceStaff(generateReportGeneration.getRealEstateAppraiser()));
                }
                //评估假设
                if (Objects.equal(BaseReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), name)) {
                    String hypothesis = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.HYPOTHESIS);
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, hypothesis);
                }
                //评估依据
                if (Objects.equal(BaseReportFieldEnum.EVALUATION_BASIS.getName(), name)) {
                    String basic = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.BASIS);
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, basic);
                }
                //评估原则
                if (Objects.equal(BaseReportFieldEnum.EVALUATION_PRINCIPLE.getName(), name)) {
                    String principle = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.PRINCIPLE);
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, principle);
                }
                //变现能力分析
                if (Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRisk(SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_LIQUIDITY));
                }
                //风险提示
                if (Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_RISK.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRisk(SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_RISK));
                }
                //社会经济发展概况
                if (Objects.equal(BaseReportFieldEnum.BACKGROUND_ANALYSIS_DEVELOPMENT.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_DEVELOPMENT));
                }
                //房地产市场总体概况
                if (Objects.equal(BaseReportFieldEnum.BACKGROUND_ANALYSIS_GENERAL.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_GENERAL));
                }
                //同类房地产市场状况
                if (Objects.equal(BaseReportFieldEnum.BACKGROUND_ANALYSIS_MARKET.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_MARKET));
                }
                //同类房地产市场版块状况
                if (Objects.equal(BaseReportFieldEnum.BACKGROUND_ANALYSIS_BLOCK.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_BLOCK));
                }
                //背景估价对象区域物业总体状况
                if (Objects.equal(BaseReportFieldEnum.BACKGROUND_ANALYSIS_PROPERTY.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_PROPERTY));
                }
                //特别提示
                if (Objects.equal(BaseReportFieldEnum.HotTip.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip());
                }
                //作业结束时间
                if (Objects.equal(BaseReportFieldEnum.HomeWorkEndTime.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkEndTime(generateReportGeneration.getHomeWorkEndTime()));
                }
                //作业开始时间
                if (Objects.equal(BaseReportFieldEnum.HomeWorkStartTime.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkStartTime());
                }
                //现场查勘期
                if (Objects.equal(BaseReportFieldEnum.surveyExamineDate.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineDate(generateReportGeneration.getInvestigationsStartDate(), generateReportGeneration.getInvestigationsEndDate()));
                }
                //现场查勘人员
                if (Objects.equal(BaseReportFieldEnum.surveyExamineCreate.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineCreate());
                }
                AdCompanyQualificationDto qualificationDto = generateBaseDataService.getCompanyQualificationForPractising();
                if (qualificationDto != null) {
                    //房地产估价机构营业执照复印件
                    if (Objects.equal(BaseReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), name)) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency());
                    }
                    //房地产估价机构资质证书复印件
                    if (Objects.equal(BaseReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), name)) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution());
                    }
                    //机构住所
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationAddress.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationAddress());
                    }
                    //机构名称
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationName.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationName());
                    }
                    //房地产估价机构
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_RealEstateValuationAgency.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationName());
                    }
                    //机构名称法定代表人
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_legalRepresentative.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getLegalRepresentative());
                    }
                    //机构工商注册号
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_registeredNo.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getRegisteredNo());
                    }
                    //机构资质等级
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationRank.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getOrganizationRank());
                    }
                    //机构证书号
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_certificateNo.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getCertificateNo());
                    }
                    //证书有效期
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_certificateEffectiveDate.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, qualificationDto.getCertificateEffectiveDate());
                    }
                    //房地产估价机构信息
                    if (Objects.equal(BaseReportFieldEnum.XIEHE_organizationInfo.getName(), name)) {
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getXIEHE_organizationInfo(qualificationDto));
                    }
                    //经营范围
                    if (Objects.equal(BaseReportFieldEnum.BusinessScope.getName(), name)) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBusinessScope(qualificationDto));
                    }
                }
                //注册房产估价师及注册号
                if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerAndNumber.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerAndNumber(generateReportGeneration));
                }
                //注册房地产估价师注册证书复印件
                if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(generateReportGeneration.getRealEstateAppraiser()));
                }
                //房地产总价
                if (Objects.equal(BaseReportFieldEnum.TotalRealEstatePrice.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalRealEstatePrice());
                }
                //房地产总价大写金额
                if (Objects.equal(BaseReportFieldEnum.CapitalizationAmount.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCapitalizationAmount());
                }
                //外聘专家工作概况
                if (Objects.equal(BaseReportFieldEnum.ExpertWorkOverview.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getExpertWorkOverview());
                }
                //共有权情况
                if (Objects.equal(BaseReportFieldEnum.Co_ownership.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCo_ownership());
                }
                //估价对象详细测算过程( 收益法 , 市场比较法)
                if (Objects.equal(BaseReportFieldEnum.DetailedCalculationProcessValuationObject.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDetailedCalculationProcessValuationObject());
                }
                //收益法租赁限制说明
                if (Objects.equal(BaseReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTenancyrestrictionRemark());
                }
                //出具报告城市
                if (Objects.equal(BaseReportFieldEnum.ReportArea.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAreaName());
                }
                //价值类型
                if (Objects.equal(BaseReportFieldEnum.ValueType.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueType());
                }
                //价值类型描述
                if (Objects.equal(BaseReportFieldEnum.ValueTypeDesc.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTypeDesc());
                }
                //设定用途
                if (Objects.equal(BaseReportFieldEnum.SetUse.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse());
                }
                //证载用途
                if (Objects.equal(BaseReportFieldEnum.CertificationPurpose.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses());
                }
                //房产类型
                if (Objects.equal(BaseReportFieldEnum.HouseType.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse());
                }
                //土地 实际用途
                if (Objects.equal(BaseReportFieldEnum.PracticalUse.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPracticalUse());
                }
                //评估面积
                if (Objects.equal(BaseReportFieldEnum.AssessArea.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessArea());
                }
                //委托目的
                if (Objects.equal(BaseReportFieldEnum.DelegatePurpose.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDelegatePurpose());
                }
                //委托目的表述
                if (Objects.equal(BaseReportFieldEnum.StatementPurposeEntrustment.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatementPurposeEntrustment());
                }
                //评估方法 ,估价对象评估方法
                if (Objects.equal(BaseReportFieldEnum.EvaluationMethod.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethod());
                }
                //出租或占用情况
                if (Objects.equal(BaseReportFieldEnum.rentalPossessionDesc.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRentalPossessionDesc());
                }
                //价值时点
                if (Objects.equal(BaseReportFieldEnum.ValueTimePoint.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePoint());
                }
                //价值时点说明
                if (Objects.equal(BaseReportFieldEnum.ValueTimePointRemark.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePointRemark());
                }
                //申报启用表单类型
                if (Objects.equal(BaseReportFieldEnum.DecalreFormTypeAll.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTypesFormEnabledDeclarationOffice());
                }
                //估价技术思路
                if (Objects.equal(BaseReportFieldEnum.EvaluationThink.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationThink());
                }
                //估价对象适用的估价方法
                if (Objects.equal(BaseReportFieldEnum.SelectionValuationMethod.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionValuationMethod());
                }
                //估价对象不适用的估价方法
                if (Objects.equal(BaseReportFieldEnum.NotSelectionValuationMethod.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNotSelectionValuationMethod());
                }
                //分类评估单价计算试
                if (Objects.equal(BaseReportFieldEnum.EvaluationExpression.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationExpression());
                }
                //变现分析税费
                if (Objects.equal(BaseReportFieldEnum.LIQUIDATION_ANALYSIS.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidationAnalysis());
                }
                //法定优先受偿款
                if (Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement());
                }
                //抵押价值总金额
                if (Objects.equal(BaseReportFieldEnum.totalAmountMortgageValue.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValue());
                }
                //抵押价值总金额大写
                if (Objects.equal(BaseReportFieldEnum.totalAmountMortgageValueCapitalization.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValueCapitalization());
                }
                //他权类别
                if (Objects.equal(BaseReportFieldEnum.HisRightType.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightType(false));
                }
                //他权类别及明细
                if (Objects.equal(BaseReportFieldEnum.HisRightTypeAndDetail.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightType(true));
                }
                //最佳利用方式
                if (Objects.equal(BaseReportFieldEnum.BestUseDesc.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOptimumUtilizationMode());
                }
                //法定优先受偿款总金额
                if (Objects.equal(BaseReportFieldEnum.StatutoryPriorityAmountTotal.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSchemeReimbursementKnowTotalPrice().toString());
                }
                //估价对象区位状况表
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectAreaStatusSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectAreaStatusSheet());
                }
                //估价土地实体状况表
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLandStateSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLandStateSheet());
                }
                //估价对象建筑实体状况表
                if (Objects.equal(BaseReportFieldEnum.JudgeBuildLandStateSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeBuildLandStateSheet());
                }
                //估价对象权益状况表
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectEquitySheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectEquitySheet());
                }
                //汇总表
                if (Objects.equal(BaseReportFieldEnum.judgeSummarySheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeSummarySheet());
                }
                //估价结果一览表
                if (Objects.equal(BaseReportFieldEnum.JudgeBuildResultSurveySheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getjudgeBuildResultSurveySheet(true));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeBuildResultSurveySheet2.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getjudgeBuildResultSurveySheet(false));
                }
                //相关参数选取与应用
                if (Objects.equal(BaseReportFieldEnum.SelectionApplicationParameters.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionApplicationParameters());
                }
                //主要计算过程
                if (Objects.equal(BaseReportFieldEnum.ComputationProcess.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getComputationProcess());
                }
                //估价对象市场价值的确定
                if (Objects.equal(BaseReportFieldEnum.DeterminationMarketValueValuationObject.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeterminationMarketValueValuationObject());
                }
                //估价对象描述
                if (Objects.equal(BaseReportFieldEnum.PrincipalDescribe.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalDescribe());
                }
                //估价对象权属
                if (Objects.equal(BaseReportFieldEnum.EquityStatusObjectSheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, null, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusObjectSheet());
                }
                //分类评估方法结果
                if (Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethodResult());
                }
                //估价委托书复印件
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectPrincipalCopySheet.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET());
                }
                //估计对象位置示意图
                if (Objects.equal(BaseReportFieldEnum.EstimatedObjectLocationDiagram.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEstimatedObjectLocationDiagram());
                }
                //估价对象实况照片
                if (Objects.equal(BaseReportFieldEnum.Valuation_Target_Live_Photos.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuation_Target_Live_Photos());
                }
                //估价对象权属证明复印件
                if (Objects.equal(BaseReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object());
                }
                //估价中引用的专用文件资料
                if (Objects.equal(BaseReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSpecial_documentation_referenced_in_valuation());
                }
                //委托人 估价委托人
                if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipal());
                }
                // 估价委托人信息
                if (Objects.equal(BaseReportFieldEnum.PrincipalInfo.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalInfo());
                }
                //财产范围说明
                if (Objects.equal(BaseReportFieldEnum.ScopePropertyExplain.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getScopePropertyExplain());
                }
                //估价项目名称
                if (Objects.equal(BaseReportFieldEnum.ValuationProjectName.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuationProjectName());
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return replaceWord(localPath, textMap, preMap, bookmarkMap, fileMap);
    }

    private String replaceWord(String localPath, Map<String, String> textMap, Map<String, String> preMap, Map<String, String> bookmarkMap, Map<String, String> fileMap) throws Exception {
        if (!preMap.isEmpty()) {
            AsposeUtils.replaceText(localPath, preMap);
            if (!textMap.isEmpty()) {
                for (Map.Entry<String, String> entry : textMap.entrySet()) {
                    preMap.put(entry.getKey(), entry.getValue());
                }
            }
            AsposeUtils.replaceText(localPath, preMap);
        }
        if (!fileMap.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, fileMap);
            if (!textMap.isEmpty()) {
                AsposeUtils.replaceText(localPath, textMap);
            }
        }
        if (!textMap.isEmpty()) {
            AsposeUtils.replaceText(localPath, textMap);
        }
        if (!bookmarkMap.isEmpty()) {
            AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
        }
        System.gc();
        return localPath;
    }

}
