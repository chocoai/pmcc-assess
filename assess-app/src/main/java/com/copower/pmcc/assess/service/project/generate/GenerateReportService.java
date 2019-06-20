package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
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
    private GenerateReportInfoService generateReportInfoService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<SchemeAreaGroup> getAreaGroupList(Integer projectId) {
        return schemeAreaGroupService.getAreaGroupList(projectId);
    }

    /**
     * 创建报告模板
     *
     * @param ids
     * @param generateReportInfo
     * @return
     * @throws Exception
     */
//    @Transactional(rollbackFor = {Exception.class})
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
            String path = this.fullReportPath(baseReportTemplate, generateReportInfo, baseDataDic.getFieldName());
            if (StringUtils.isNotBlank(path)) {
                this.createSysAttachment(path, generateReportInfo, baseDataDic.getFieldName(), sysAttachmentDtoList);
            }
        }
    }

    /**
     * 上传到erp形成附件id
     *
     * @param path
     * @return
     */
    private void createSysAttachment(String path, GenerateReportInfo generateReportInfo, String reportType, List<SysAttachmentDto> sysAttachmentDtoList) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(generateReportInfo.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportInfo.class));
        File file = new File(path);
        sysAttachmentDto.setFileExtension(file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()));
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        sysAttachmentDto.setFileSize(new Long(file.length()).toString());
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setFieldsName(generateCommonMethod.getReportFieldsName(reportType, generateReportInfo.getAreaGroupId()));
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
     * @param generateReportInfo
     * @return
     * @throws Exception
     */
    private String fullReportPath(BaseReportTemplate baseReportTemplate, GenerateReportInfo generateReportInfo, String reportType) throws Exception {
        String dir = null;
        SysAttachmentDto query = new SysAttachmentDto();
        query.setTableId(baseReportTemplate.getId());
        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            dir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDtoList.stream().findFirst().get().getId());
        }
        if (PoiUtils.isWord2003(dir)){
            Document doc = new Document(dir);
            Document clone = doc.deepClone();
//            dir = generateCommonMethod.getLocalPath(null,"docx") ;
//            clone.save(dir, SaveFormat.DOCX);
        }
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(generateReportInfo.getProjectPlanId());
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(generateReportInfo.getProjectId()));
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectInfoVo, generateReportInfo.getAreaGroupId(), baseReportTemplate, projectPlan);
        //count 计数器,防止  枚举虽然定义了，但是没有写对应的方法，因此递归设置最多的次数
        int count = 0;
        //最大递归次数 , 最好是不要过大 (ps max-count 就是递归次数)
        final int max = 4;
        generateReplaceWord(dir, generateBaseDataService, generateReportInfo, reportType, count, max);
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
    private String generateReplaceWord(String tempDir, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, String reportType, int count, final int max) throws Exception {
        List<String> names = Lists.newArrayList();
        //基础报告
        for (BaseReportFieldEnum baseReportFieldEnum : BaseReportFieldEnum.values()) {
            names.add(baseReportFieldEnum.getName());
        }
        //收益法
        Arrays.asList(BaseReportFieldMdIncomeEnum.values()).forEach(oo -> {
            names.add(oo.getName());
        });
        //基准地
        Arrays.asList(BaseReportFieldMdBaseLandPriceEnum.values()).forEach(oo -> {
            names.add(oo.getName());
        });
        //比较法
        Arrays.asList(BaseReportFieldCompareEnum.values()).forEach(oo -> {
            names.add(oo.getName());
        });
        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = getBookmarkAndRegexDtoHashSet(tempDir);
        Set<BookmarkAndRegexDto> compareHashSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(bookmarkAndRegexDtoHashSet)) {
            bookmarkAndRegexDtoHashSet.forEach(bookmarkAndRegex -> {
                String name = StringUtils.isNotBlank(bookmarkAndRegex.getChineseName()) ? bookmarkAndRegex.getChineseName() : bookmarkAndRegex.getName();
                //必须在枚举中存在的我们才收集
                if (names.stream().anyMatch(s -> Objects.equal(s, name))) {
                    compareHashSet.add(bookmarkAndRegex);
                }
            });
        }
        if (CollectionUtils.isNotEmpty(compareHashSet)) {
            count++;
            //替换
            generateReplaceMethod(compareHashSet, generateBaseDataService, tempDir, generateReportInfo, reportType);
            System.gc();
            if (count >= max) {
                return tempDir;
            }
            //递归回去 判断是否可以跳出循环
            return generateReplaceWord(tempDir, generateBaseDataService, generateReportInfo, reportType, count, max);
        } else {
            return tempDir;
        }
    }

    private Set<BookmarkAndRegexDto> getBookmarkAndRegexDtoHashSet(String tempDir) throws Exception {
        Document document = new Document(tempDir);
        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = Sets.newHashSet();
        List<String> stringList = Lists.newArrayList();
        String text = PoiUtils.getWordTableContent(tempDir);
        if (StringUtils.isNotEmpty(text)) {
            //取出word中表格数据
            Matcher m = Pattern.compile("\\$\\{.*?\\}").matcher(text);
            while (m.find()) {
                stringList.add(m.group());
            }
        }
        //获取普通段落
        List<String> regexList = AsposeUtils.getRegexList(document, null);
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
        return bookmarkAndRegexDtoHashSet;
    }


    /**
     * 替换报告中的关键字
     *
     * @param localPath
     * @param generateBaseDataService
     * @throws Exception
     */
    private void generateReplaceMethod(Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet, GenerateBaseDataService generateBaseDataService, String localPath, GenerateReportInfo generateReportInfo, String reportType) throws Exception {
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
                //报告编号
                if (Objects.equal(BaseReportFieldEnum.ReportNumber2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWordNumber2());
                }
                //报告二维码
                if (Objects.equal(BaseReportFieldEnum.ReportQrcode.getName(), name)) {
                    generateCommonMethod.putValue(true, true, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportQrcode(generateReportInfo, reportType));
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportQrcode(generateReportInfo, reportType));
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
                    if (generateReportInfo.getReportIssuanceDate() != null) {
                        reportIssuanceStr = DateUtils.format(generateReportInfo.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                    } else {
                        reportIssuanceStr = DateUtils.format(generateBaseDataService.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                    }
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportIssuanceStr);
                }
                //协助工作人员
                if (Objects.equal(BaseReportFieldEnum.AssistanceStaff.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssistanceStaff(generateReportInfo.getRealEstateAppraiser()));
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
                //建行个贷变现能力分析
                if (Objects.equal(BaseReportFieldEnum.ANALYSIS_CATEGORY_LIQUIDITY2.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRisk2());
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
                    List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
                    keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
                    keyValueDtoList.add(new KeyValueDto("font-size", "14.0pt"));
                    keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip(keyValueDtoList));
                }
                //建行个贷特别提示
                if (Objects.equal(BaseReportFieldEnum.HotTip2.getName(), name)) {
                    List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
                    keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
                    keyValueDtoList.add(new KeyValueDto("font-size", "9.0pt"));
                    keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip(keyValueDtoList));
                }
                if (Objects.equal(BaseReportFieldEnum.Atypism2.getName(), name)) {
                    List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
                    keyValueDtoList.add(new KeyValueDto("font-family", "宋体"));
                    keyValueDtoList.add(new KeyValueDto("font-size", "10pt"));
                    keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
                    generateCommonMethod.putValue(false, false, true, preMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip(keyValueDtoList));
                }
                //作业结束时间
                if (Objects.equal(BaseReportFieldEnum.HomeWorkEndTime.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkEndTime(generateReportInfo.getHomeWorkEndTime()));
                }
                //作业开始时间
                if (Objects.equal(BaseReportFieldEnum.HomeWorkStartTime.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkStartTime());
                }
                //现场查勘期
                if (Objects.equal(BaseReportFieldEnum.surveyExamineDate.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineDate(generateReportInfo.getInvestigationsStartDate(), generateReportInfo.getInvestigationsEndDate()));
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
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerAndNumber(generateReportInfo));
                }
                //注册房地产估价师注册证书复印件
                if (Objects.equal(BaseReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(generateReportInfo.getRealEstateAppraiser()));
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
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(true));
                }
                if (Objects.equal(BaseReportFieldEnum.SetUse2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(false));
                }
                //坐落
                if (Objects.equal(BaseReportFieldEnum.Seat2.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSchemeJudgeObjectSeatList(true));
                }
                if (Objects.equal(BaseReportFieldEnum.Seat.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSchemeJudgeObjectSeatList(false));
                }
                //建行个贷权证号
                if (Objects.equal(BaseReportFieldEnum.CERT_NAME1.getName(), name)) {
                    List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
                    keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
                    keyValueDtoList.add(new KeyValueDto("font-size", "9.0pt"));
                    keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSchemeJudgeObjectCertNameList(keyValueDtoList));
                }
                if (Objects.equal(BaseReportFieldEnum.CERT_NAME2.getName(), name)) {
                    List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
                    keyValueDtoList.add(new KeyValueDto("font-family", "宋体"));
                    keyValueDtoList.add(new KeyValueDto("font-size", "10.0pt"));
                    keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSchemeJudgeObjectCertNameList(keyValueDtoList));
                }
                //证载用途
                if (Objects.equal(BaseReportFieldEnum.CertificationPurpose.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses(true));
                }
                if (Objects.equal(BaseReportFieldEnum.CertificationPurpose2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses(false));
                }
                //房产类型
                if (Objects.equal(BaseReportFieldEnum.HouseType.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(true));
                }
                //土地 实际用途
                if (Objects.equal(BaseReportFieldEnum.PracticalUse.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPracticalUse());
                }
                //评估面积
                if (Objects.equal(BaseReportFieldEnum.AssessArea.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessArea());
                }
                //评估单价
                if (Objects.equal(BaseReportFieldEnum.AssessPrice.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessPrice());
                }
                //评估总价分述
                if (Objects.equal(BaseReportFieldEnum.AssessPriceClassification.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessPriceClassification());
                }
                //评估总价
                if (Objects.equal(BaseReportFieldEnum.AssessTotal.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessAssessTotal());
                }
                //评估总价大写
                if (Objects.equal(BaseReportFieldEnum.AssessTotalRMB.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessAssessTotalAssessTotalRMB());
                }
                //登记时间
                if (Objects.equal(BaseReportFieldEnum.RegistrationDate.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegistrationDate());
                }
                //房屋结构 建筑结构
                if (Objects.equal(BaseReportFieldEnum.housingStructure.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildingStructureCategory());
                }
                if (Objects.equal(BaseReportFieldEnum.housingStructure2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildingStructureCategory());
                }
                //总层数
                if (Objects.equal(BaseReportFieldEnum.floorCount.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFloorCount());
                }
                if (Objects.equal(BaseReportFieldEnum.floorCount2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFloorCount());
                }
                //楼盘名称
                if (Objects.equal(BaseReportFieldEnum.estateName.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEstateName());
                }
                //房屋性质
                if (Objects.equal(BaseReportFieldEnum.houseNature.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHouseNature());
                }
                //房屋所有权人
                if (Objects.equal(BaseReportFieldEnum.ownership.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOwnership());
                }
                //户型
                if (Objects.equal(BaseReportFieldEnum.unitType.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordUnitType());
                }
                //装修状况
                if (Objects.equal(BaseReportFieldEnum.DecorationStatus.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDecorationStatus());
                }
                //填发单位
                if (Objects.equal(BaseReportFieldEnum.FillingUnit.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFillingUnit());
                }
                //附记
                if (Objects.equal(BaseReportFieldEnum.AttachmentReark.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAttachmentReark());
                }
                //建成年代
                if (Objects.equal(BaseReportFieldEnum.BeCompletedTimeGetInteger.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBeCompletedTimeGetInteger());
                }
                //楼层
                if (Objects.equal(BaseReportFieldEnum.floor.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordFloor());
                }
                if (Objects.equal(BaseReportFieldEnum.floor2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordFloor());
                }
                //当前年份
                if (Objects.equal(BaseReportFieldEnum.ThisYear.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getThisYear());
                }
                //建筑面积
                if (Objects.equal(BaseReportFieldEnum.BuildArea.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildArea());
                }
                //套内建筑面积
                if (Objects.equal(BaseReportFieldEnum.CoverArea.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCoverArea());
                }
                //委托单位
                if (Objects.equal(BaseReportFieldEnum.EntrustedUnit.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEntrustmentUnit());
                }
                //档案保管号
                if (Objects.equal(BaseReportFieldEnum.ArchivesDepositNumber.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentGroundNum());
                }
                //层户数
                if (Objects.equal(BaseReportFieldEnum.LayerNumber.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLayerNumber());
                }
                //朝向
                if (Objects.equal(BaseReportFieldEnum.Orientation.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOrientation());
                }
                if (Objects.equal(BaseReportFieldEnum.StoreyHeight.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStoreyHeight());
                }
                //建行个贷其它
                if (Objects.equal(BaseReportFieldEnum.NetAssessmentOther.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentOther());
                }
                //建行个贷丘地号
                if (Objects.equal(BaseReportFieldEnum.NetAssessmentGroundNum.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentGroundNum());
                }
                if (Objects.equal(BaseReportFieldEnum.exteriorWallDecorate.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOutfitDecorate1());
                }
                if (Objects.equal(BaseReportFieldEnum.LobbyDecorate.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOutfitDecorate2());
                }
                //地基及墙面
                if (Objects.equal(BaseReportFieldEnum.FoundationAndWall.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFoundationAndWall());
                }
                //使用状况
                if (Objects.equal(BaseReportFieldEnum.UsageStatus.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUsageStatus());
                }
                if (Objects.equal(BaseReportFieldEnum.UsageStatus2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUsageStatus());
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
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(0));
                }
                if (Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement1.getName(), name)) {
                    String text = generateCommonMethod.getNumber(BaseReportFieldEnum.StatutoryOptimumReimbursement1.name());
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(Integer.parseInt(text)));
                }
                if (Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement2.getName(), name)) {
                    String text = generateCommonMethod.getNumber(BaseReportFieldEnum.StatutoryOptimumReimbursement2.name());
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(Integer.parseInt(text)));
                }
                if (Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement3.getName(), name)) {
                    String text = generateCommonMethod.getNumber(BaseReportFieldEnum.StatutoryOptimumReimbursement3.name());
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(Integer.parseInt(text)));
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
                //建行预评数据表格
                if (Objects.equal(BaseReportFieldEnum.CCB_Pre_Evaluation_Data_Form.getName(), name)) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCCB_Pre_Evaluation_Data_FormSheet());
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
                //估价信息描述
                if (Objects.equal(BaseReportFieldEnum.PrincipalDataDescribe.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalDataDescribe());
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
                //户型及布局
                if (Objects.equal(BaseReportFieldEnum.HuxingLayout.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHuxingLayout());
                }
                //获取某些土地证字段信息
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField1.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField1));
                }
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField2));
                }
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField3.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField3));
                }
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField4.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField4));
                }
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField5.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField5));
                }
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField6.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField6));
                }
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField7.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField7));
                }
                if (Objects.equal(BaseReportFieldEnum.LandCertificateField8.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(BaseReportFieldEnum.LandCertificateField8));
                }
                //获取某些区位字段信息
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField1.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField1));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField2));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField3.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField3));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField4.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField4));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField5.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField5));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField6.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField6));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField7.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField7));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField8.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField8));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField9.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField9));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectLoactionField10.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(BaseReportFieldEnum.JudgeObjectLoactionField10));
                }
                //获取房屋的装修情况
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField1.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(BaseReportFieldEnum.JudgeObjectDamagedDegreeField1));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(BaseReportFieldEnum.JudgeObjectDamagedDegreeField2));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField3.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(BaseReportFieldEnum.JudgeObjectDamagedDegreeField3));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField4.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(BaseReportFieldEnum.JudgeObjectDamagedDegreeField4));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField5.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(BaseReportFieldEnum.JudgeObjectDamagedDegreeField5));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField6.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldB(BaseReportFieldEnum.JudgeObjectDamagedDegreeField6));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField7.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldB(BaseReportFieldEnum.JudgeObjectDamagedDegreeField7));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectDamagedDegreeField8.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldValue());
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField1.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField1));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField2.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField2));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField3.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField3));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField4.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField4));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField5.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField5));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField6.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField6));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField7.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField7));
                }
                if (Objects.equal(BaseReportFieldEnum.JudgeObjectOtherField8.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(BaseReportFieldEnum.JudgeObjectOtherField8));
                }
                if (Objects.equal(BaseReportFieldEnum.NetAssessmentOne.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentNumber(BaseReportFieldEnum.NetAssessmentOne));
                }
                if (Objects.equal(BaseReportFieldEnum.NetAssessmentTwo.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentNumber(BaseReportFieldEnum.NetAssessmentTwo));
                }
                //厌恶设施
                if (Objects.equal(BaseReportFieldEnum.AversionFacility.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAversionFacility());
                }
                //报告使用单位
                if (Objects.equal(BaseReportFieldEnum.ReportUnitString.getName(), name)) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportUnitString());
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        replaceWord(localPath, textMap, preMap, bookmarkMap, fileMap);
    }

    private void replaceWord(String localPath, Map<String, String> textMap, Map<String, String> preMap, Map<String, String> bookmarkMap, Map<String, String> fileMap) throws Exception {
        Map<String, String> errorMap2 = Maps.newHashMap() ;
        List<String> typeList = Arrays.asList("\r","\n") ;
        {
            if (!textMap.isEmpty()){
                textMap.forEach((key,value) -> {
                    typeList.forEach(s -> {
                        if (StringUtils.contains(value,s)){
                            errorMap2.put(key,value) ;
                        }
                    });
                });
            }
            if (!errorMap2.isEmpty()) {
                try {
                    replaceHandleError(errorMap2, localPath);
                } catch (Exception e) {
                    String error = e.getMessage();
                    logger.info(error,e);
                    //docx 结尾的会出错
                }
            }
        }
        if (!preMap.isEmpty()) {
            Map<String, String> errorMap = AsposeUtils.replaceText(localPath, preMap);
            if (!errorMap.isEmpty()) {
                errorMap2.putAll(errorMap);
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

}
