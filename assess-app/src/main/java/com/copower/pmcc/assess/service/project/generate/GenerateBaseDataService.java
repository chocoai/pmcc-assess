package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.*;
import com.aspose.words.Table;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.common.*;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.common.enums.basic.EnvironmentalScienceEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.common.enums.method.MethodIncomeOperationModeEnum;
import com.copower.pmcc.assess.common.enums.report.BaseReportFieldEnum;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.*;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyRightGroupDto;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.data.DataPropertyVo;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicUnitHuxingService;
import com.copower.pmcc.assess.service.data.*;
import com.copower.pmcc.assess.service.method.MdCommonService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.compile.CompileReportService;
import com.copower.pmcc.assess.service.project.declare.*;
import com.copower.pmcc.assess.service.project.scheme.*;
import com.copower.pmcc.assess.service.project.survey.*;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.ProjectDocumentDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.StringUtil;
import com.google.common.base.Objects;
import com.google.common.collect.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by kings on 2019-1-16.
 */
public class GenerateBaseDataService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    protected final String errorStr = "无";
    //spring bean
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private ProjectNumberRecordService projectNumberRecordService;
    private BaseDataDicService baseDataDicService;
    private BaseAttachmentService baseAttachmentService;
    private ProjectPlanDetailsService projectPlanDetailsService;
    private ProjectPhaseService projectPhaseService;
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    private DeclareRecordService declareRecordService;
    private SchemeSurePriceService schemeSurePriceService;
    private SchemeReimbursementService schemeReimbursementService;
    private com.copower.pmcc.assess.service.AdRpcQualificationsAppService adRpcQualificationsService;
    private PublicService publicService;
    private CompileReportService compileReportService;
    private SchemeReportFileService schemeReportFileService;
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    private SchemeInfoService schemeInfoService;
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    private MdIncomeService mdIncomeService;
    private MdMarketCompareService mdMarketCompareService;
    private DataMethodFormulaService dataMethodFormulaService;
    private GenerateCommonMethod generateCommonMethod;
    private EvaluationThinkingService evaluationThinkingService;
    private EvaluationBasisService evaluationBasisService;
    private EvaluationHypothesisService evaluationHypothesisService;
    private EvaluationPrincipleService evaluationPrincipleService;
    private DataReportAnalysisService dataReportAnalysisService;
    private DataReportAnalysisRiskService dataReportAnalysisRiskService;
    private SurveyAssetInventoryRightRecordService surveyAssetInventoryRightRecordService;
    private GenerateLoactionService generateLoactionService;
    private GenerateLandEntityService generateLandEntityService;
    private SurveyCommonService surveyCommonService;
    private GenerateHouseEntityService generateHouseEntityService;
    private ErpAreaService erpAreaService;
    private MdCommonService mdCommonService;
    private GenerateEquityService generateEquityService;
    private BasicApplyService basicApplyService;
    private SurveyAssetInventoryRightRecordCenterService surveyAssetInventoryRightRecordCenterService;
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    private BaseProjectClassifyService baseProjectClassifyService;
    private ProjectQrcodeRecordService projectQrcodeRecordService;
    private ErpRpcToolsService erpRpcToolsService;
    private ApplicationConstant applicationConstant;
    private DataSetUseFieldService dataSetUseFieldService;
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    private BasicUnitHuxingService basicUnitHuxingService;
    private BaseService baseService;
    private ErpRpcUserService erpRpcUserService;

    /**
     * 构造器必须传入的参数
     */
    private Integer projectId;
    private Integer areaId;
    private BaseReportTemplate baseReportTemplate;
    private ProjectInfoVo projectInfo = null;
    /**
     * 中间变量
     */
    private SchemeAreaGroup schemeAreaGroup = null;
    private List<SchemeJudgeObject> schemeJudgeObjectList = null;
    private List<SchemeJudgeObject> schemeJudgeObjectFullList = null;
    private List<SchemeJudgeObject> schemeJudgeObjectDeclareList = null;
    private Map<Integer, SchemeJudgeObject> schemeJudgeObjectMap = null;
    //===========================================获取的值===============================

    /**
     * 获取文号
     *
     * @return
     */
    public String getWordNumber() {
        try {
            String number = projectNumberRecordService.getReportNumber(projectId, areaId, this.baseReportTemplate.getReportType());
            if (StringUtils.isNotBlank(number)) {
                return number;
            }
        } catch (BusinessException e) {
            logger.error("获取文号异常", e);
        }
        return errorStr;
    }

    /**
     * 文号中的编号
     *
     * @return
     */
    public String getWordNumber2() {
        String value = getWordNumber();
        Pattern pattern = Pattern.compile("\\d+号$");
        if (StringUtils.isNotEmpty(value) && !Objects.equal(value, errorStr)) {
            Matcher matcher = pattern.matcher(value);
            while (matcher.find()) {
                if (StringUtils.isNotEmpty(matcher.group())) {
                    return StringUtils.remove(matcher.group(), "号");
                }
            }
        }
        return errorStr;
    }

    /**
     * 获取报告二维码
     *
     * @return
     */
    public String getReportQrcode(GenerateReportInfo generateReportInfo, String reportType) throws Exception {
        //1.先从本地查看是否已生成过二维码
        //2.如果已生成直接返回已生成的二维码
        //3.如果没有生成则调用接口生成二维码并记录数据到本地
        ProjectQrcodeRecord qrcodeRecode = projectQrcodeRecordService.getProjectQrcodeRecode(projectId, areaId, this.baseReportTemplate.getReportType());
        String qrCode = null;
        if (qrcodeRecode != null) {
            qrCode = qrcodeRecode.getQrcode();
        } else {
            AdCompanyQualificationDto qualificationDto = getCompanyQualificationForPractising();
            ProjectDocumentDto projectDocumentDto = new ProjectDocumentDto();
            projectDocumentDto.setProjectName(projectInfo.getProjectName());
            projectDocumentDto.setCustomer(getPrincipal());
            projectDocumentDto.setCompanyName(qualificationDto != null ? qualificationDto.getOrganizationName() : "");
            projectDocumentDto.setDocumentNumber(getWordNumber());
            projectDocumentDto.setReportDate(DateUtils.formatDate(generateReportInfo.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN));
            projectDocumentDto.setReportMember(projectInfo.getUserAccountManagerName());
            projectDocumentDto.setAppKey(applicationConstant.getAppKey());
            projectDocumentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportInfo.class));
            projectDocumentDto.setTableId(generateReportInfo.getId());
            projectDocumentDto.setFieldsName(generateCommonMethod.getReportFieldsName(reportType, generateReportInfo.getAreaGroupId()));
            projectDocumentDto = erpRpcToolsService.saveProjectDocument(projectDocumentDto);

            qrcodeRecode = new ProjectQrcodeRecord();
            qrcodeRecode.setProjectId(projectId);
            qrcodeRecode.setAreaId(areaId);
            qrcodeRecode.setReportType(this.baseReportTemplate.getReportType());
            qrcodeRecode.setProjectDocumentId(projectDocumentDto.getId());
            qrcodeRecode.setQrcode(projectDocumentDto.getQrcode());
            projectQrcodeRecordService.saveProjectQrcodeRecode(qrcodeRecode);
            qrCode = projectDocumentDto.getQrcode();
            projectDocumentDto.setFieldsName(generateCommonMethod.getReportFieldsName(reportType, generateReportInfo.getAreaGroupId()));
            erpRpcToolsService.saveProjectDocument(projectDocumentDto);
        }
        return toolBaseOrCode(qrCode, 100L, 100L);
    }

    /**
     * 评估类型(添加一个封面)
     *
     * @param generateReportInfo
     * @param localPath
     * @param baseAttachmentService
     * @param baseReportFieldService
     * @throws Exception
     */
    public void handleReportCover(GenerateReportInfo generateReportInfo, String localPath, BaseAttachmentService baseAttachmentService, BaseReportFieldService baseReportFieldService) throws Exception {
        String key = null;
        Document document = new Document(localPath);
        DocumentBuilder documentBuilder = new DocumentBuilder(document);
        if (Objects.equal(generateReportInfo.getAssessCategory(), AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_ASSETS.getNumber())) {
            key = AssessReportFieldConstant.COVER_ASSETS_TEMPLATE;
        }
        if (Objects.equal(generateReportInfo.getAssessCategory(), AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.getNumber())) {
            key = AssessReportFieldConstant.COVER_LAND_TEMPLATE;
        }
        if (Objects.equal(generateReportInfo.getAssessCategory(), AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.getNumber())) {
            key = AssessReportFieldConstant.COVER_HOUSE_TEMPLATE;
        }
        if (StringUtils.isEmpty(key)) {
            return;
        }
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(key);
        if (baseReportField == null) {
            return;
        }
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        String replacePath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDtoList.stream().findFirst().get().getId());
        //处理封面的内容
        replaceCover(replacePath, generateReportInfo);
        String value = RandomStringUtils.randomNumeric(15);
        documentBuilder.write(value);
        documentBuilder.insertBreak(BreakType.PAGE_BREAK);
        IReplacingCallback callback = new IReplacingCallback() {
            @Override
            public int replacing(ReplacingArgs e) throws Exception {
                DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                builder.moveTo(e.getMatchNode());
                Document document = new Document(replacePath);
                builder.insertDocument(document, 0);
                return 0;
            }
        };
        document.getRange().replace(Pattern.compile(value), callback, false);
        if (PoiUtils.isWord2003(localPath)) {
            document.save(localPath, SaveFormat.DOC);
        }
        if (PoiUtils.isWord2007(localPath)) {
            document.save(localPath, SaveFormat.DOCX);
        }
    }

    private void replaceCover(String path, GenerateReportInfo generateReportInfo) throws Exception {
        Document document = new Document(path);
        List<String> stringList = Lists.newArrayList();
        String text = null;
        try {
            text = PoiUtils.getWordContent(path);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            try {
                text = PoiUtils.getWordText(path);
            } catch (Exception e1) {
                baseService.writeExceptionInfo(e1);
                try {
                    text = PoiUtils.getWordTableContent(path);
                } catch (Exception e2) {
                    baseService.writeExceptionInfo(e2);
                }
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
        List<String> regexList = AsposeUtils.getRegexList(document, AsposeUtils.reportReplaceString);
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
                regexS.add(bookmarkCollection.get(i).getName());
            }
        }
        if (CollectionUtils.isEmpty(regexS)) {
            return;
        }
        Map<String, String> fileMap = Maps.newHashMap();
        for (String s : regexS) {
            if (Objects.equal(BaseReportFieldEnum.ReportHouseQrCode.getName(), s)) {
                fileMap.put(String.format("${%s}", s), toolOrCode(String.format("http://gjszcxt.cirea.org.cn/?number=%s", generateReportInfo.getQueryCode()), 60d, 60d));
            }
            if (Objects.equal(BaseReportFieldEnum.ReportASSETSQrCode.getName(), s)) {
                fileMap.put(String.format("${%s}", s), toolOrCode(String.format("http://47.94.11.33:8035/TongYiBianMa/Index?_TYBM_H_=%s", generateReportInfo.getRecordNo()), 80d, 80d));
            }
            if (Objects.equal(BaseReportFieldEnum.ReportLandQrCode.getName(), s)) {
                StringBuilder stringBuilder = new StringBuilder(8);
                stringBuilder.append("http://rd.wechat.com/qrcode/confirm?block_type=101&content=");
                stringBuilder.append("").append("备案号:").append(generateReportInfo.getRecordNo()).append(";").append(StringUtils.repeat("\n\r\t", 1));
                stringBuilder.append("").append("报告名称:").append(getValuationProjectName()).append(";").append(StringUtils.repeat("\n\r\t", 1));
                stringBuilder.append("").append("估价单位:").append(getEntrustmentUnit()).append(";").append(StringUtils.repeat("\n\r\t", 1));
                String reportIssuanceStr = null;
                if (generateReportInfo.getReportIssuanceDate() != null) {
                    reportIssuanceStr = DateUtils.format(generateReportInfo.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
                } else {
                    reportIssuanceStr = DateUtils.format(new Date(), DateUtils.DATE_CHINESE_PATTERN);
                }
                stringBuilder.append("").append("报告日期:").append(reportIssuanceStr).append(";").append(StringUtils.repeat("\n\r\t", 1));
                stringBuilder.append("&lang=zh_CN&scene=4");
                fileMap.put(String.format("${%s}", s), toolOrCode(String.format("%s", stringBuilder.toString()), 80d, 80d));
            }
        }
        if (!fileMap.isEmpty()) {
            AsposeUtils.replaceTextToFile(path, fileMap);
        }
    }

    private synchronized String toolOrCode(String url, double width, double height) throws Exception {
        String qrCode = erpRpcToolsService.generateQRCode(url);
        return toolBaseOrCode(qrCode, width, height);
    }

    private synchronized String toolBaseOrCode(String qrCode, double width, double height) throws Exception {
        String localPath = generateCommonMethod.getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        String imageFullPath = generateCommonMethod.getLocalPath("", "jpg");
        FileUtils.base64ToImage(qrCode, imageFullPath);
        builder.insertImage(imageFullPath, width, height);
        document.save(localPath);
        return localPath;
    }


    /**
     * 委托人
     *
     * @return
     */
    public String getPrincipal() {
        String principalStr = StringUtils.isNotBlank(projectInfo.getConsignorVo().getCsName()) ? projectInfo.getConsignorVo().getCsName() : projectInfo.getConsignorVo().getCsEntrustmentUnit();
        if (StringUtils.isNotBlank(principalStr)) {
            return principalStr;
        } else {
            return errorStr;
        }
    }

    //委托单位
    public String getEntrustmentUnit() {
        String value = "/";
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.legalPerson.getId())) {
            value = projectInfo.getConsignorVo().getCsEntrustmentUnit();
        }
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.naturalPerson.getId())) {
            value = projectInfo.getConsignorVo().getCsName();
        }
        return value;
    }

    //报告使用单位
    public String getReportUnitString() {
        if (StringUtils.isNotEmpty(projectInfo.getUnitInformationVo().getuUseUnitName())) {
            return projectInfo.getUnitInformationVo().getuUseUnitName();
        }
        return "/";
    }

    /**
     * 估价委托人信息
     *
     * @return
     */
    public String getPrincipalInfo() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(documentBuilder);
        StringBuilder stringBuilder = new StringBuilder(8);
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.legalPerson.getId())) {
            String name = projectInfo.getConsignorVo().getCsEntrustmentUnit();
            if (StringUtils.isNotBlank(name)) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "名称", name)));
            }

            String code = projectInfo.getConsignorVo().getCsSociologyCode();
            if (StringUtils.isNotBlank(code)) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "统一社会信用代码", code)));
            }

            String address = projectInfo.getConsignorVo().getCsAddress();
            if (StringUtils.isNotBlank(address)) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "住所", address)));
            }

            String people = projectInfo.getConsignorVo().getCsLegalRepresentative();
            if (StringUtils.isNotBlank(people)) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "法定代表人", people)));
            }
        }
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.naturalPerson.getId())) {
            String name = projectInfo.getConsignorVo().getCsName();
            if (StringUtils.isNotBlank(name)) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "姓名", name)));
            }

            String idCard = projectInfo.getConsignorVo().getCsIdcard();
            if (StringUtils.isNotBlank(idCard)) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "身份证号", idCard)));
            }

            String address = projectInfo.getConsignorVo().getCsAddress();
            if (StringUtils.isNotBlank(address)) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "地址", address)));
            }

        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 财产范围说明
     *
     * @return
     * @throws Exception
     */
    public String getScopePropertyExplain() throws Exception {
        StringBuffer stringBuffer = new StringBuffer(16);
        if (getSchemeAreaGroup().getPropertyScope() != null) {
            stringBuffer.append("评估(财产)范围").append(baseDataDicService.getNameById(getSchemeAreaGroup().getPropertyScope())).append(";");
        }
        if (StringUtils.isNotBlank(getSchemeAreaGroup().getScopeInclude())) {
            stringBuffer.append("包含:").append(getSchemeAreaGroup().getScopeInclude()).append(";");
        } else {
            stringBuffer.append("没有包含").append(";");
        }
        if (StringUtils.isNotBlank(getSchemeAreaGroup().getScopeNotInclude())) {
            stringBuffer.append("不包含:").append(getSchemeAreaGroup().getScopeNotInclude()).append("。");
        } else {
            stringBuffer.append("没有不包含").append("。");
        }
        if (StringUtils.isEmpty(stringBuffer.toString())) {
            stringBuffer.append(errorStr);
        }
        return stringBuffer.toString();
    }

    /**
     * 外聘专家工作概况
     *
     * @return
     */
    public String getExpertWorkOverview() {
        String s = "茅以升1916年毕业于西南交通大学（时称交通部唐山工业专门学校英文名称Tangshan Engineering College），1917年获美国康乃尔大学硕士学位，1919年获美国卡耐基理工学院（先卡耐基梅隆大学）博士学位 [1]  ，回国后历任交通大学唐山工学院教授、国立东南大学（1928年更名为国立中央大学，1949年更名为南京大学）教授、工科主任、国立河海工科大学校长、交通部唐山大学校长（今西南交通大学）、北洋工学院院长、江苏省水利厅厅长、钱塘江大桥工程处处长、交通大学唐山工学院代院长、院长、中国桥梁公司总经理、北洋大学校长、中国/北方交通大学（时含今西南交通大学和今北京交通大学）校长、铁道科学研究院院长等职。1955年选聘为中国科学院院士（学部委员）。";
        return errorStr;
    }

    /**
     * 共有权情况
     *
     * @throws Exception
     */
    public String getCo_ownership() throws Exception {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord != null) {
                    String value = null;
                    if (NumberUtils.isNumber(declareRecord.getPublicSituation())) {
                        value = baseDataDicService.getNameById(declareRecord.getPublicSituation());
                    } else {
                        value = declareRecord.getPublicSituation();
                    }
                    if (StringUtils.isNotBlank(value)) {
                        generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, value);
                    }
                }
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }


    /**
     * 功能描述: 出具报告区域名称
     *
     * @auther: zch
     * @date: 2019/2/27 15:17
     */
    public String getReportAreaName() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        if (StringUtils.isNotBlank(schemeAreaGroup.getDistrict())) {
            return erpAreaService.getSysAreaName(schemeAreaGroup.getDistrict());
        }
        return erpAreaService.getSysAreaName(schemeAreaGroup.getCity());
    }

    /**
     * 抵押价值总金额
     */
    public String getTotalAmountMortgageValue() throws Exception {
        BigDecimal decimal = getTotalRealEstate().subtract(getSchemeReimbursementKnowTotalPrice()).abs();
        String value = generateCommonMethod.getBigDecimalToInteger(decimal, 100);
        value = new BigDecimal(value).divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_DOWN).toString();
        return value;
    }

    /**
     * 抵押价值总金额大写
     */
    public String getTotalAmountMortgageValueCapitalization() throws Exception {
        BigDecimal decimal = getTotalRealEstate().subtract(getSchemeReimbursementKnowTotalPrice()).abs();
        String value = generateCommonMethod.getBigDecimalToInteger(decimal, 100);
        String s = CnNumberUtils.toUppercaseSubstring(value);
        return s;
    }

    /**
     * 估价项目名称
     */
    public String getValuationProjectName() throws Exception {
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map = generateCommonMethod.getEstateGroupByAreaId(areaId);
        if (map.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            List<SchemeJudgeObject> judgeObjects = entry.getValue();
            List<DeclareRecord> recordList = declareRecordService.getDeclareRecordListByIds(LangUtils.transform(judgeObjects, o -> o.getDeclareRecordId()));
            List<String> list = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(recordList)) {
                int size = recordList.size();
                int eachLength = size > 3 ? 3 : size;
                for (int i = 0; i < eachLength; i++) {
                    DeclareRecord record = recordList.get(i);
                    if (StringUtils.isNotEmpty(record.getStreetNumber())) {
                        list.add(record.getSeat().replace(record.getStreetNumber(), ""));
                    } else {
                        list.add(record.getSeat());
                    }
                }
                if (StringUtils.isNotEmpty(recordList.stream().findFirst().get().getStreetNumber())) {
                    stringBuilder.append(String.format("%s%s", recordList.stream().findFirst().get().getStreetNumber(), entry.getKey().getName()));
                } else {
                    stringBuilder.append(entry.getKey().getName());
                }
                stringBuilder.append(publicService.fusinString(list, false)).append("");
                if (size > 3)
                    stringBuilder.append(String.format("等%s宗", size));
                DataSetUseField setUseField = dataSetUseFieldService.getCacheSetUseFieldById(judgeObjects.get(0).getSetUse());
                if (setUseField != null) {
                    stringBuilder.append(String.format("%s%s用途房地产", setUseField.getName(), size > 3 ? "等" : ""));
                }
            }
        }
        if (getSchemeAreaGroup().getEntrustPurpose() != null) {
            stringBuilder.append(baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose()));
        }
        return stringBuilder.toString();
    }

    public String getValuationProjectName2() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        List<SchemeJudgeObject> judgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            return "";
        }
        List<String> seatList = judgeObjectList.stream().map(o -> o.getSeat()).collect(Collectors.toList());
        stringBuilder.append(this.getReportUnitString());
        stringBuilder.append("因案件执行需要涉及位于");
        stringBuilder.append(publicService.fusinString(seatList, false)).append("");
        if (judgeObjectList.size() > 3)
            stringBuilder.append(String.format("等%s宗", judgeObjectList.size()));
        DataSetUseField setUseField = dataSetUseFieldService.getCacheSetUseFieldById(judgeObjectList.get(0).getSetUse());
        if (setUseField != null) {
            stringBuilder.append(String.format("%s%s用途房地产", setUseField.getName(), judgeObjectList.size() > 3 ? "等" : ""));
        }
        if (getSchemeAreaGroup() != null) {
            String value = baseDataDicService.getNameById(getSchemeAreaGroup().getValueDefinition());
            if (StringUtils.isNotBlank(value.trim())) {
                stringBuilder.append(value).append("评估");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 证载用途
     *
     * @throws Exception
     */
    public String getSeparationCertificateUses(boolean judgeEachDesc) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            map.put(generateCommonMethod.parseIntJudgeNumber(generateCommonMethod.getNumber(schemeJudgeObject.getNumber())), schemeJudgeObject.getCertUse());
        }
        if (judgeEachDesc) {
            return generateCommonMethod.judgeSummaryDesc(map, "证载用途为", false);
        } else {
            return generateCommonMethod.judgeEachDesc2(map, "证载用途为", "", false);
        }
    }

    /**
     * 登记时间
     * RegistrationDate
     *
     * @return
     */
    public String getRegistrationDate() {
        String value = "/";
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getDeclareRecordId() == null) {
                    continue;
                }
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord == null || declareRecord.getRegistrationDate() == null) {
                    continue;
                }
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), DateUtils.format(declareRecord.getRegistrationDate(), DateUtils.DATE_CHINESE_PATTERN));
            }
        }
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc(map, "", "", false);
        }
        return value;
    }

    /**
     * 房屋性质
     *
     * @return
     */
    public String getHouseNature() {
        String value = "/";
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getDeclareRecordId() == null) {
                    continue;
                }
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord == null || StringUtils.isEmpty(declareRecord.getNature())) {
                    continue;
                }
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRecord.getNature());
            }
        }
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc(map, "", "", false);
        }
        return value;
    }


    /**
     * 房屋结构
     *
     * @throws Exception
     */
    public String getBuildingStructureCategory() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            String val = null;
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                if (StringUtils.isNotEmpty(declareRecord.getHousingStructure())) {
                    val = declareRecord.getHousingStructure();
                }
            }
            if (StringUtils.isEmpty(val)) {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply != null || basicApply.getId() != null) {
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicBuildingVo basicBuildingVo = generateBaseExamineService.getBasicBuilding();
                    if (basicBuildingVo != null) {
                        if (StringUtils.isNotEmpty(basicBuildingVo.getBuildingStructureTypeName())) {
                            if (StringUtils.isNotEmpty(basicBuildingVo.getBuildingStructureCategoryName())) {
                                val = String.format("%s%s", basicBuildingVo.getBuildingStructureTypeName(), basicBuildingVo.getBuildingStructureCategoryName());
                            } else {
                                val = String.format("%s", basicBuildingVo.getBuildingStructureTypeName());
                            }
                        }
                    }
                    if (StringUtils.isNotEmpty(basicBuildingVo.getBuildingStructureTypeName())) {
                        if (StringUtils.isNotEmpty(basicBuildingVo.getBuildingStructureCategoryName())) {
                            val = String.format("%s%s", basicBuildingVo.getBuildingStructureTypeName(), basicBuildingVo.getBuildingStructureCategoryName());
                        } else {
                            val = String.format("%s", basicBuildingVo.getBuildingStructureTypeName());
                        }
                    }
                }
            }
            if (StringUtils.isNotEmpty(val)) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), val);
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    public String getPROPERTY_TYPE() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply != null || basicApply.getId() != null) {
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicBuildingVo basicBuildingVo = generateBaseExamineService.getBasicBuilding();
                if (basicBuildingVo != null) {
                    if (StringUtils.isNotEmpty(basicBuildingVo.getPropertyTypeName())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicBuildingVo.getPropertyTypeName());
                    }
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 房屋所有权人
     *
     * @return
     */
    public String getOwnership() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null || StringUtils.isEmpty(declareRecord.getOwnership())) {
                continue;
            }
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRecord.getOwnership());
        }
        String value = "";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 填发单位
     *
     * @return
     */
    public String getFillingUnit() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            DeclareRealtyLandCert declareRealtyLandCert = null;
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                if (declareRealtyRealEstateCert != null) {
                    if (StringUtils.isNotEmpty(declareRealtyRealEstateCert.getRegistrationAuthority())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRealtyRealEstateCert.getRegistrationAuthority());
                    }
                }
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                if (realtyHouseCertById != null) {
                    if (StringUtils.isNotEmpty(realtyHouseCertById.getRegistrationAuthority())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), realtyHouseCertById.getRegistrationAuthority());
                    }
                    if (StringUtils.isEmpty(realtyHouseCertById.getRegistrationAuthority())) {
                        DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                        center.setHouseId(realtyHouseCertById.getId());
                        center.setPlanDetailsId(realtyHouseCertById.getPlanDetailsId());
                        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(center);
                        if (CollectionUtils.isNotEmpty(centerList)) {
                            if (centerList.stream().anyMatch(oo -> oo.getLandId() != null)) {
                                declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(centerList.stream().filter(oo -> oo.getLandId() != null).findFirst().get().getLandId());
                            }
                        }
                    }
                }
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
            }
            if (declareRealtyLandCert != null) {
                if (StringUtils.isNotEmpty(declareRealtyLandCert.getRegistrationAuthority())) {
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRealtyLandCert.getRegistrationAuthority());
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 附记其它
     *
     * @return
     */
    public String getAttachmentReark() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                if (declareRealtyRealEstateCert != null) {
                    if (StringUtils.isNotEmpty(declareRealtyRealEstateCert.getOtherNote())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRealtyRealEstateCert.getOtherNote());
                    }
                }
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                if (realtyHouseCertById != null) {
                    DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                    center.setHouseId(realtyHouseCertById.getId());
                    center.setPlanDetailsId(realtyHouseCertById.getPlanDetailsId());
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(center);
                    if (StringUtils.isNotEmpty(realtyHouseCertById.getOtherNote())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), realtyHouseCertById.getOtherNote());
                    }
                    if (StringUtils.isEmpty(realtyHouseCertById.getOtherNote())) {
                        if (CollectionUtils.isNotEmpty(centerList)) {
                            if (centerList.stream().anyMatch(oo -> oo.getLandId() != null)) {
                                DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(centerList.stream().filter(oo -> oo.getLandId() != null).findFirst().get().getLandId());
                                if (declareRealtyLandCert != null) {

                                }
                            }
                        }
                    }
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 获取某些土地证字段,不动产证字段
     *
     * @param baseReportFieldEnum
     * @return
     */
    public String getLandCertificateFieldValue(BaseReportFieldEnum baseReportFieldEnum) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            DeclareRealtyHouseCert declareRealtyHouseCert = null;
            DeclareRealtyLandCert declareRealtyLandCert = null;
            DeclareRealtyRealEstateCert declareRealtyRealEstateCert = null;
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                declareRealtyHouseCert = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                if (declareRealtyHouseCert != null) {
                    DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                    center.setHouseId(declareRealtyHouseCert.getId());
                    center.setPlanDetailsId(declareRealtyHouseCert.getPlanDetailsId());
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(center);
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        if (centerList.stream().anyMatch(oo -> oo.getLandId() != null)) {
                            declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(centerList.stream().filter(oo -> oo.getLandId() != null).findFirst().get().getLandId());
                        }
                    }
                }
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
            }
            if (declareRealtyLandCert == null) {
                declareRealtyLandCert = new DeclareRealtyLandCert();
            }
            if (declareRealtyRealEstateCert == null) {
                declareRealtyRealEstateCert = new DeclareRealtyRealEstateCert();
            }
            if (declareRealtyHouseCert == null) {
                declareRealtyHouseCert = new DeclareRealtyHouseCert();
            }
            switch (baseReportFieldEnum) {
                case LandCertificateField1: {
                    String value = declareRealtyLandCert.getLandCertName();
                    if (StringUtils.isEmpty(value)) {
                        value = declareRealtyRealEstateCert.getCertName();
                    }
                    if (StringUtils.isEmpty(value)) {
//                        value = declareRealtyHouseCert.getCertName();
                    }
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case LandCertificateField2: {
                    String value = declareRealtyLandCert.getOwnership();
                    if (StringUtils.isEmpty(value)) {
                        value = declareRealtyRealEstateCert.getOwnership();
                    }
                    if (StringUtils.isEmpty(value)) {
//                        value = declareRealtyHouseCert.getOwnership();
                    }
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case LandCertificateField3: {
                    String value = null;
                    if (declareRealtyLandCert.getCertUseCategory() != null) {
                        value = baseDataDicService.getNameById(declareRealtyLandCert.getCertUseCategory());
                    }
                    if (StringUtils.isEmpty(value)) {
                        if (declareRealtyRealEstateCert.getLandCertUseCategory() != null) {
                            value = baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandCertUseCategory());
                        }
                    }
                    if (StringUtils.isEmpty(value)) {
                        if (declareRealtyHouseCert.getCertUseCategory() != null) {
//                            value = baseDataDicService.getNameById(declareRealtyHouseCert.getCertUseCategory());
                        }
                    }
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case LandCertificateField4: {
                    String value = null;
                    if (declareRealtyLandCert.getLandRightNature() != null) {
                        value = baseDataDicService.getNameById(declareRealtyLandCert.getLandRightNature());
                    }
                    if (StringUtils.isEmpty(value)) {
                        if (declareRealtyRealEstateCert.getLandRightNature() != null) {
                            value = baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightNature());
                        }
                    }
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case LandCertificateField5: {
                    BigDecimal apportionmentArea = null;
                    if (apportionmentArea == null) {
                        if (declareRealtyLandCert.getApportionmentArea() != null) {
                            apportionmentArea = declareRealtyLandCert.getApportionmentArea();
                        }
                    }
                    if (apportionmentArea == null) {
                        if (declareRealtyRealEstateCert.getLandApportionArea() != null) {
                            apportionmentArea = declareRealtyRealEstateCert.getLandApportionArea();
                        }
                    }
                    if (apportionmentArea == null) {
                        if (declareRealtyHouseCert.getApportionmentArea() != null) {
//                            apportionmentArea = declareRealtyHouseCert.getApportionmentArea();
                        }
                    }
                    if (apportionmentArea != null) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), generateCommonMethod.getBigDecimalRound(apportionmentArea, 2, false));
                    }
                }
                break;
                case LandCertificateField6: {
                    Date useEndDate = null;
                    if (declareRealtyLandCert.getTerminationDate() != null) {
                        useEndDate = declareRealtyLandCert.getTerminationDate();
                    }
                    if (useEndDate == null) {
                        if (declareRealtyRealEstateCert.getTerminationDate() != null) {
                            useEndDate = declareRealtyRealEstateCert.getTerminationDate();
                        }
                    }
                    if (useEndDate == null) {
                        if (declareRealtyRealEstateCert.getUseEndDate() != null) {
                            useEndDate = declareRealtyRealEstateCert.getUseEndDate();
                        }
                    }
                    if (useEndDate == null) {
                        if (declareRealtyHouseCert.getUseEndDate() != null) {
//                            useEndDate = declareRealtyHouseCert.getUseEndDate();
                        }
                    }
                    if (useEndDate != null) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), DateUtils.format(useEndDate, DateUtils.DATE_CHINESE_PATTERN));
                    }
                }
                break;
                case LandCertificateField7: {
                    String value = null;
                    if (StringUtils.isEmpty(value)) {
                        if (StringUtils.isNotEmpty(declareRealtyLandCert.getRegistrationAuthority())) {
                            value = declareRealtyLandCert.getRegistrationAuthority();
                        }
                    }
                    if (StringUtils.isEmpty(value)) {
                        if (StringUtils.isNotEmpty(declareRealtyRealEstateCert.getRegistrationAuthority())) {
                            value = declareRealtyRealEstateCert.getRegistrationAuthority();
                        }
                    }
                    if (StringUtils.isEmpty(value)) {
                        if (StringUtils.isNotEmpty(declareRealtyHouseCert.getRegistrationAuthority())) {
//                            value = declareRealtyHouseCert.getRegistrationAuthority();
                        }
                    }
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case LandCertificateField8: {
                    Date registrationDate = null;
                    if (registrationDate == null) {
                        if (declareRealtyLandCert.getRegistrationDate() != null) {
                            registrationDate = declareRealtyLandCert.getRegistrationDate();
                        }
                    }
                    if (registrationDate == null) {
                        if (declareRealtyRealEstateCert.getRegistrationDate() != null) {
                            registrationDate = declareRealtyRealEstateCert.getRegistrationDate();
                        }
                    }
                    if (registrationDate != null) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), DateUtils.format(registrationDate, DateUtils.DATE_CHINESE_PATTERN));
                    }
                }
                break;
                case LandArea: {
                    BigDecimal apportionmentArea = null;
                    if (apportionmentArea == null) {
                        if (declareRealtyLandCert.getApportionmentArea() != null) {
                            apportionmentArea = declareRealtyLandCert.getApportionmentArea();
                        }
                    }
                    if (apportionmentArea == null) {
                        if (declareRealtyRealEstateCert.getLandApportionArea() != null) {
                            apportionmentArea = declareRealtyRealEstateCert.getLandApportionArea();
                        }
                    }
                    if (apportionmentArea == null) {
                        if (declareRealtyHouseCert.getApportionmentArea() != null) {
//                            apportionmentArea = declareRealtyHouseCert.getApportionmentArea();
                        }
                    }
                    if (apportionmentArea != null) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), generateCommonMethod.getBigDecimalRound(apportionmentArea, 2, false));
                    }
                }
                break;
                default:
                    break;
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 小微贷丘地号
     *
     * @return
     */
    public String getNetAssessmentGroundNum() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                if (realtyHouseCertById != null) {
                    if (StringUtils.isNotEmpty(realtyHouseCertById.getGroundNum())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), realtyHouseCertById.getGroundNum());
                    }
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 小微贷其它
     *
     * @return
     */
    public String getNetAssessmentOther() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                if (declareRealtyRealEstateCert != null) {
                    if (StringUtils.isNotEmpty(declareRealtyRealEstateCert.getOther())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRealtyRealEstateCert.getOther());
                    }
                }
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                if (realtyHouseCertById != null) {
                    if (StringUtils.isNotEmpty(realtyHouseCertById.getOther())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), realtyHouseCertById.getOther());
                    }
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 套内面积
     *
     * @return
     */
    public String getCoverArea() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                if (declareRealtyRealEstateCert != null) {
                    if (declareRealtyRealEstateCert.getInnerArea() != null) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), generateCommonMethod.getBigDecimalRound(declareRealtyRealEstateCert.getInnerArea(), 2, false));
                    }
                }
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                if (realtyHouseCertById != null) {
                    if (realtyHouseCertById.getInnerArea() != null) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), generateCommonMethod.getBigDecimalRound(realtyHouseCertById.getInnerArea(), 2, false));
                    }
                }
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                DeclareRealtyLandCert declareRealtyLandCert = null;
                declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
                if (declareRealtyLandCert != null) {
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 户型
     *
     * @return
     * @throws Exception
     */
    public String getDeclareRecordUnitType() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicUnitHuxing> basicUnitHuxingList = generateBaseExamineService.getBasicUnitHuxingList();
            Set<String> names = Sets.newHashSet();
            if (CollectionUtils.isNotEmpty(basicUnitHuxingList)) {
                basicUnitHuxingList.forEach(oo -> {
                    if (StringUtils.isNotEmpty(oo.getName())) {
                        names.add(oo.getName());
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(names)) {
                map.put(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject), StringUtils.join(names, "、"));
            }
        }
        if (!map.isEmpty()) {
            if (map.size() == 1) {
                stringBuilder.append(StringUtils.trim(map.entrySet().stream().findFirst().get().getValue()));
            } else {
                int i = 0;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    stringBuilder.append(entry.getKey()).append(entry.getValue());
                    i++;
                    if (i != map.size()) {
                        //插入换行符
                        stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    }
                }
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            stringBuilder.append(ControlChar.PARAGRAPH_BREAK_CHAR);
        }
        return stringBuilder.toString();
    }

    /**
     * 装修状况
     *
     * @return
     */
    public String getDecorationStatus() {
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicHouseVo basicHouseVo = generateBaseExamineService.getBasicHouse();
            if (StringUtils.isNotBlank(basicHouseVo.getDecorateSituationDescription())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicHouseVo.getDecorateSituationDescription());
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 当前年份
     *
     * @return
     */
    public String getThisYear() {
        return String.valueOf(DateUtils.getYear(DateUtils.today()));
    }

    /**
     * 楼层
     *
     * @return
     */
    public String getDeclareRecordFloor() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            if (StringUtils.isNotEmpty(declareRecord.getFloor())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRecord.getFloor());
            } else {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply != null) {
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicHouseVo basicHouseVo = generateBaseExamineService.getBasicHouse();
                    if (basicHouseVo != null) {
                        if (StringUtils.isNotEmpty(basicHouseVo.getFloor())) {
                            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicHouseVo.getFloor());
                        }
                    }
                }
            }
        }
        String value = "";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "层", true);
        }
        return value;
    }

    /**
     * 建筑面积
     *
     * @return
     */
    public String getBuildArea() {
        Map<Integer, String> map = Maps.newHashMap();
        final int newScale = 2;//保留2位数
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            String text = null;
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null && declareRecord.getFloorArea() != null) {
                text = generateCommonMethod.getBigDecimalRound(declareRecord.getFloorArea(), newScale, false);
            }
            if (StringUtils.isEmpty(text)) {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply != null) {
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicEstateVo basicEstateVo = generateBaseExamineService.getEstate();
                    if (basicEstateVo != null) {
                        if (basicEstateVo.getFloorArea() != null) {
                            text = generateCommonMethod.getBigDecimalRound(basicEstateVo.getFloorArea(), newScale, false);
                        }
                    }
                    if (StringUtils.isEmpty(text)) {
                        BasicBuildingVo basicBuildingVo = generateBaseExamineService.getBasicBuilding();
                        if (basicBuildingVo != null) {
                            if (basicBuildingVo.getBuildingArea() != null) {
                                text = generateCommonMethod.getBigDecimalRound(basicBuildingVo.getBuildingArea(), newScale, false);
                            }
                        }
                    }
                }
            }
            if (StringUtils.isNotEmpty(text)) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), text);
            }
        }
        String value = "";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 层户比
     *
     * @return
     * @throws Exception
     */
    public String getLayerNumber() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicUnit basicUnit = generateBaseExamineService.getBasicUnit();
            if (basicUnit != null && StringUtils.isNotEmpty(basicUnit.getElevatorHouseholdRatio())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicUnit.getElevatorHouseholdRatio());
            }
        }
        String value = "";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", true);
        }
        return value;
    }

    /**
     * 使用状况
     *
     * @return
     */
    public String getUsageStatus() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicHouseVo basicHouseVo = generateBaseExamineService.getBasicHouse();
            if (basicHouseVo == null) {
                continue;
            }
            if (StringUtils.isEmpty(basicHouseVo.getUseConditionName())) {
                continue;
            }
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicHouseVo.getUseConditionName());
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 总层数
     *
     * @return
     */
    public String getFloorCount() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            String val = null;
            if (declareRecord != null) {
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                    if (declareRealtyRealEstateCert != null) {
                        if (StringUtils.isNotEmpty(declareRealtyRealEstateCert.getFloorCount())) {
                            val = declareRealtyRealEstateCert.getFloorCount();
                        }
                    }
                }
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                    if (realtyHouseCertById != null) {
                        if (StringUtils.isNotEmpty(realtyHouseCertById.getFloorCount())) {
                            val = realtyHouseCertById.getFloorCount();
                        }
                    }
                }
            }
            if (StringUtils.isEmpty(val)) {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply == null || basicApply.getId() == null) {
                    continue;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                String key = generateBaseExamineService.getBasicBuilding().getFloorCount();
                if (StringUtil.isNotEmpty(key)) {
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), key);
                }
            } else {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), val);
            }
        }
        String value = "";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "总共", "层", true);
        }
        return value;
    }

    /**
     * 朝向
     *
     * @return
     * @throws Exception
     */
    public String getOrientation() throws Exception {
        return generateLoactionService.getOrientation(getSchemeJudgeObjectList());
    }

    /**
     * 获取层高
     *
     * @return
     * @throws Exception
     */
    public String getStoreyHeight() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseRoom> basicHouseRoomList = generateBaseExamineService.getBasicHouseRoomList();
            if (CollectionUtils.isEmpty(basicHouseRoomList)) {
                continue;
            }
            Map<String, BigDecimal> bigDecimalMap = Maps.newHashMap();
            basicHouseRoomList.forEach(oo -> {
                String name = StringUtils.isNotBlank(oo.getName()) ? oo.getName() : oo.getRoomType();
                if (oo.getLayerHeight() != null && StringUtils.isNotEmpty(name)) {
                    bigDecimalMap.put(name, oo.getLayerHeight());
                }
            });
            if (!bigDecimalMap.isEmpty()) {
                Set<BigDecimal> bigDecimalSet = Sets.newHashSet();
                bigDecimalMap.forEach((s, bigDecimal) -> {
                    bigDecimalSet.add(bigDecimal);
                });
                if (bigDecimalSet.size() == 1) {
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), bigDecimalSet.stream().findFirst().get().toString() + "米");
                }
                if (bigDecimalSet.size() != 1) {
                    List<String> stringList = Lists.newArrayList();
                    bigDecimalMap.forEach((s, bigDecimal) -> {
                        stringList.add(String.format("%s:%s米", s, bigDecimal.toString()));
                    });
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(stringList, "；"));
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", true);
        }
        return value;
    }

    /**
     * 获取装饰的部位
     *
     * @return
     */
    public String getOutfitDecorate1() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicBuildingOutfitVo> basicBuildingOutfitVoList = generateBaseExamineService.getBasicBuildingOutfitList();
            final String name = "墙";
            Multimap<String, String> multimap = ArrayListMultimap.create();
            if (CollectionUtils.isNotEmpty(basicBuildingOutfitVoList)) {
                basicBuildingOutfitVoList.forEach(oo -> {
                    String partName = StringUtils.isNotBlank(oo.getDecorationPartName()) ? oo.getDecorationPartName() : oo.getDecorationPart();
                    if (StringUtils.isNotEmpty(partName)) {
                        if (StringUtils.contains(partName, name)) {
                            multimap.put(partName, oo.getDecoratingMaterialName());
                        }
                    }
                });
            }
            if (!multimap.isEmpty()) {
                Set<String> stringSet = Sets.newHashSet(multimap.keys());
                if (stringSet.size() == 1) {
                    String key = stringSet.stream().findFirst().get();
                    Collection<String> stringList = multimap.get(key);
                    String string = String.format("%s", StringUtils.join(stringList, "、"));
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), string);
                }
                if (stringSet.size() != 1) {
                    List<String> stringList = Lists.newArrayList();
                    multimap.entries().forEach(stringStringEntry -> {
                        String string = String.format("%s", stringStringEntry.getValue());
                        stringList.add(string);
                    });
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(stringList, "；"));
                }
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", true);
        }
        return value;
    }

    public String getOutfitDecorate2() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicUnitDecorateVo> basicUnitDecorateVoList = generateBaseExamineService.getBasicUnitDecorateList();
            final String name = "大堂";
            Multimap<String, String> multimap = ArrayListMultimap.create();
            if (CollectionUtils.isNotEmpty(basicUnitDecorateVoList)) {
                basicUnitDecorateVoList.forEach(oo -> {
                    if (StringUtils.contains(oo.getDecorationPartName(), name)) {
                        if (StringUtils.isNotEmpty(oo.getDecoratingMaterialName()) && StringUtils.isEmpty(oo.getMaterialGradeName())) {
                            multimap.put(name, oo.getDecoratingMaterialName());
                        }
                        if (StringUtils.isNotEmpty(oo.getDecoratingMaterialName()) && StringUtils.isNotEmpty(oo.getMaterialGradeName())) {
                            multimap.put(name, String.format("%s%s", oo.getMaterialGradeName(), oo.getDecoratingMaterialName()));
                        }
                    }
                });
            }
            if (!multimap.isEmpty()) {
                Collection<String> stringList = multimap.get(name);
                String string = StringUtils.join(stringList, "、");
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), string);
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", true);
        }
        return value;
    }

    /**
     * 地基及墙面
     *
     * @return
     */
    public String getFoundationAndWall() {
        Map<Integer, String> map = Maps.newHashMap();
        final List<String> filterList = Arrays.asList("地基基础", "外抹灰", "内抹灰");
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseDamagedDegreeVo> degreeVoList = generateBaseExamineService.getDamagedDegreeVoList();
            Map<String, List<BasicHouseDamagedDegreeVo>> stringListMap = Maps.newHashMap();
            if (CollectionUtils.isNotEmpty(degreeVoList)) {
                degreeVoList.stream().forEach(oo -> {
                    List<BasicHouseDamagedDegreeVo> damagedDegreeVoList = stringListMap.get(oo.getTypeName());
                    if (CollectionUtils.isEmpty(damagedDegreeVoList)) {
                        damagedDegreeVoList = Lists.newArrayList();
                    }
                    damagedDegreeVoList.add(oo);
                    stringListMap.put(oo.getTypeName(), damagedDegreeVoList);
                });
            }
            Set<String> stringSet = Sets.newHashSet();
            if (!stringListMap.isEmpty()) {
                stringListMap.forEach((key, degreeList) -> {
                    if (CollectionUtils.isNotEmpty(degreeList)) {
                        degreeList.forEach(oo -> {
                            if (filterList.stream().anyMatch(s -> StringUtils.contains(oo.getCategoryName(), s))) {
                                if (StringUtils.isNotEmpty(oo.getEntityConditionContent())) {
                                    stringSet.add(String.join("", oo.getCategoryName(), oo.getEntityConditionName(), ",", oo.getEntityConditionContent()));
                                }
                                if (StringUtils.isNotEmpty(oo.getBasicallyIntact()) && StringUtils.isEmpty(oo.getEntityConditionContent())) {
                                    stringSet.add(String.join("", oo.getCategoryName(), oo.getEntityConditionName(), ",", oo.getBasicallyIntact()));
                                }
                            }
                        });
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(stringSet)) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(stringSet, "；"));
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "。", true);
        }
        return value;
    }

    //建造年份
    public String getBeCompletedTimeGetInteger() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            Date key = generateBaseExamineService.getBasicBuilding().getBeCompletedTime();
            if (key != null) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.valueOf(DateUtils.getYear(key)));
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "年", true);
        }
        return value;
    }

    /**
     * 楼盘名称
     *
     * @return
     * @throws Exception
     */
    public String getEstateName() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : getSchemeJudgeObjectList()) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            String key = generateBaseExamineService.getEstate().getName();
            if (StringUtils.isNotEmpty(key)) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), key);
            }
        }
        String value = "";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }


    /**
     * 分类评估单价计算试
     */
    public String getEvaluationExpression() {
        return "比较法价格*权重+收益法价格*权重";
    }

    /**
     * 分类评估方法结果
     */
    public String getEvaluationMethodResult() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int k = 0; k < schemeJudgeObjectList.size(); k++) {
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(k);
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSchemeSurePriceBySchemeJudgeObjectId(schemeJudgeObject.getId());
                if (CollectionUtils.isEmpty(schemeSurePriceItemList)) {
                    continue;
                }
                for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                    if (StringUtils.isNotBlank(schemeSurePriceItem.getMethodName()) && schemeSurePriceItem.getTrialPrice() != null) {
                        linkedHashSet.add(String.format("%s%s元", schemeSurePriceItem.getMethodName(), schemeSurePriceItem.getTrialPrice().toString()));
                    }
                }
                if (schemeSurePrice != null && schemeSurePrice.getPrice() != null) {
                    linkedHashSet.add(String.format("最终单价%s元", schemeSurePrice.getPrice().toString()));
                }
                List<Integer> integerList = Lists.newArrayList();
                if (StringUtils.isNotBlank(schemeJudgeObject.getNumber())) {
                    List<String> stringList = Lists.newArrayList(schemeJudgeObject.getNumber().split(","));
                    stringList.stream().forEach(s -> integerList.add(Integer.parseInt(s)));
                }
                String s = String.format("%s号%s 。", generateCommonMethod.convertNumber(integerList), StringUtils.join(linkedHashSet, "，"));
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(StringUtils.trimToEmpty(s)), false);
                linkedHashSet.clear();
            }
        }
        doc.save(localPath);
        return localPath;
    }

    //房地产总价
    private BigDecimal getTotalRealEstate() {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        BigDecimal temp = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimal = schemeJudgeObject.getEvaluationArea().multiply(schemeJudgeObject.getPrice());
                        temp = temp.add(bigDecimal);
                    }
                }
            }
        }
        return temp;
    }

    /**
     * 房地产总价
     *
     * @return
     */
    public String getTotalRealEstatePrice() {
        String value = generateCommonMethod.getBigDecimalRound(getTotalRealEstate(), 0, false);
        value = generateCommonMethod.getBigDecimalToInteger(new BigDecimal(value), 100);
        value = new BigDecimal(value).divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_DOWN).toString();
        return value;
    }

    /**
     * 房地产总价 大写金额
     *
     * @return
     */
    public String getCapitalizationAmount() {
        String value = generateCommonMethod.getBigDecimalRound(getTotalRealEstate(), 0, false);
        value = generateCommonMethod.getBigDecimalToInteger(new BigDecimal(value), 100);
        String s = CnNumberUtils.toUppercaseSubstring(value);
        return s;
    }

    /**
     * 法定优先受偿款
     */
    public String getStatutoryOptimumReimbursement(Integer num) {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> map = schemeReimbursementService.getSchemeReimbursementItemVoMapAndSchemeJudgeObject(schemeJudgeObjectList, projectId);
            if (!map.isEmpty()) {
                map.entrySet().stream().filter(entry -> entry.getKey().getDeclareRecordId() != null).forEach(entry -> {
                    List<SchemeReimbursementItemVo> itemVos = entry.getValue();
                    if (CollectionUtils.isNotEmpty(itemVos)) {
                        itemVos.stream().forEach(oo -> {
                            String s = schemeReimbursementService.getFullDescription(oo, num);
                            if (StringUtils.isNotBlank(s.trim())) {
                                generateCommonMethod.putStringListMap(stringListMap, entry.getKey(), s);
                            }
                        });
                    }
                });
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, "");
        if (StringUtils.isEmpty(s.trim())) {
            s = "0";
        }
        return s;
    }


    /**
     * 特别提示
     *
     * @throws Exception
     */
    public String getHotTip2(final int repeat) throws Exception {
        String value = getHotTipBank(true);
        if (StringUtils.isNotBlank(value)) {
            if (repeat != 0) {
                value = String.join("", StringUtils.repeat(" ", repeat), value);
            }
        }
        if (StringUtils.isEmpty(value)) {
            value = "/";
        }
        return value;
    }

    private String getHotTipBank(boolean lineBreak) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        List<String> stringList = Lists.newArrayList();
        List<String> stringArrayList = Lists.newArrayList();
        BaseDataDic type = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS);
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            boolean isLabelJudgeObjectShowName = schemeJudgeObjectList.size() <= 20 ;
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getDeclareRecordId() != null) {
                    String registration = getActualRegistration(type, schemeJudgeObject.getDeclareRecordId());//证载地址
                    String addressAssetInventory = getActualAddressAssetInventory(type, schemeJudgeObject.getDeclareRecordId());//现场查勘地址
                    if (StringUtils.isNotBlank(registration) && StringUtils.isNotBlank(addressAssetInventory)) {
                            String tempNumber = String.join("",schemeJudgeObject.getNumber(),"号") ;
                            if (isLabelJudgeObjectShowName){
                                tempNumber = generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject) ;
                            }
                        stringBuilder.append(String.format("%s估价对象证载地址为", tempNumber));

                        stringBuilder.append(registration);
                        stringBuilder.append("，");
                        stringBuilder.append("估价人员现场查勘地址为");
                        stringBuilder.append(addressAssetInventory);
                        stringBuilder.append("，");
                        stringBuilder.append(String.format("经%s出具《%s》确认一致。",
                                getCertificateAssetInventory(type, schemeJudgeObject.getDeclareRecordId()),
                                getCredentialAssetInventory(type, schemeJudgeObject.getDeclareRecordId())));
                        stringArrayList.add(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.toString().length());
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringArrayList)) {
            for (int i = 0; i < stringArrayList.size(); i++) {
                stringBuilder.append(stringArrayList.get(i));
                if (lineBreak) {
                    if (i != stringArrayList.size() - 1 && stringArrayList.size() != 1) {
                        //插入换行符
                        stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    }
                }
                stringList.add(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.toString().length());
            }
        }
        return StringUtils.join(stringList, "");
    }


    /**
     * 估价对象权属
     *
     * @throws Exception
     */
    public String getEquityStatusObjectSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        LinkedList<String> linkedLists = new LinkedList<String>();
        final String nullValue = "";
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            boolean isLabelJudgeObjectShowName = schemeJudgeObjectList.size() <= 20;
            builder.startTable();
            generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Arrays.asList("估价序号", "权证号", "所有权人", "共有情况", "证载用途", "房屋性质", "面积")));
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getDeclareRecordId() == null) {
                    continue;
                }
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord == null) {
                    continue;
                }
                if (isLabelJudgeObjectShowName) {
                    linkedLists.add(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject));
                } else {
                    linkedLists.add(String.join("", schemeJudgeObject.getNumber(), "号"));
                }
                linkedLists.add(StringUtils.isNotBlank(declareRecord.getName()) ? declareRecord.getName() : nullValue);
                linkedLists.add(StringUtils.isNotBlank(declareRecord.getOwnership()) ? declareRecord.getOwnership() : nullValue);
                linkedLists.add(StringUtils.isNotBlank(declareRecord.getPublicSituation()) ? declareRecord.getPublicSituation() : nullValue);
                linkedLists.add(StringUtils.isNotBlank(declareRecord.getCertUse()) ? declareRecord.getCertUse() : nullValue);
                linkedLists.add(StringUtils.isNotBlank(declareRecord.getNature()) ? declareRecord.getNature() : nullValue);
                linkedLists.add(declareRecord.getFloorArea() != null ? String.format("%s%s", declareRecord.getFloorArea().toString(), "㎡") : nullValue);
                generateCommonMethod.writeWordTitle(builder, linkedLists);
                linkedLists.clear();
            }
            builder.endTable();
        }
        String localPath = getLocalPath();
        doc.save(localPath);
        return localPath;
    }

    public String getEquityStatusObjectNumber() {
        LinkedList<String> linkedLists = new LinkedList<String>();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            if (StringUtils.isNotBlank(declareRecord.getName())) {
                linkedLists.add(declareRecord.getName());
            }
        }
        return StringUtils.join(linkedLists, "");
    }

    /**
     * 变现分析表
     */
    public String getLiquidationAnalysis() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        String localPath = getLocalPath();
        Map<String, String> map = Maps.newHashMap();
        List<SchemeLiquidationAnalysisGroup> groupByAreaId = schemeLiquidationAnalysisService.getGroupByAreaId(areaId, projectId);
        if (CollectionUtils.isNotEmpty(groupByAreaId)) {
            for (SchemeLiquidationAnalysisGroup groupItem : groupByAreaId) {
                String tablePath = createLiquidationAnalysisTable(groupItem);
                if (StringUtils.isNotBlank(tablePath)) {
                    String key = RandomStringUtils.randomNumeric(22);
                    map.put(key, tablePath);
                    builder.write(key);
                }
            }
        } else {
            String tablePath = createLiquidationAnalysisTable(new SchemeLiquidationAnalysisGroup());
            if (StringUtils.isNotBlank(tablePath)) {
                String key = RandomStringUtils.randomNumeric(22);
                map.put(key, tablePath);
                builder.write(key);
            }
        }
        doc.save(localPath);
        if (!map.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, map);
        }
        return localPath;
    }

    private String createLiquidationAnalysisTable(SchemeLiquidationAnalysisGroup groupItem) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        String localPath = getLocalPath(RandomStringUtils.randomNumeric(8));
        if (groupItem != null && StringUtils.isNotBlank(groupItem.getRecordIds())) {
            String recordIds = groupItem.getRecordIds();
            List<Integer> recordList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(recordIds));
            List<Integer> judgeNumberByDeclareIds = schemeJudgeObjectService.getJudgeNumberByDeclareIds(recordList);
            String number = generateCommonMethod.convertNumber(judgeNumberByDeclareIds);
            builder.insertHtml(generateCommonMethod.getSongWarpCssHtml3(String.format("%s%s", number, "号委估对象")));
        }
        List<SchemeLiquidationAnalysisItem> itemList = Lists.newArrayList();
        if (groupItem != null && groupItem.getId() != null) {
            List<SchemeLiquidationAnalysisItem> itemList2 = schemeLiquidationAnalysisService.getAnalysisItemListByGroupId(groupItem.getId());
            if (CollectionUtils.isNotEmpty(itemList2)) {
                itemList.addAll(itemList2);
            }
        }
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        LinkedList<String> linkedList = Lists.newLinkedList();
        int j = 0;

        Table table = builder.startTable();
        linkedList.addAll(Arrays.asList("物业类型", "税率", "计算基数", "计算公式", "税费负担方", "单位(面积/㎡ 金额/元)"));
        AsposeUtils.writeWordTitle(builder, linkedList);
        linkedList.clear();
        j++;


        linkedList.addAll(Arrays.asList("面积", "/", "/", "/", "/", schemeAreaGroupService.getAreaEvaluateArea(schemeJudgeObjectFullList).toString()));
        AsposeUtils.writeWordTitle(builder, linkedList);
        linkedList.clear();
        j++;

        linkedList.addAll(Arrays.asList("评估价", "/", "/", "/", "/", schemeAreaGroupService.getAreaEvaluatePrice(schemeJudgeObjectFullList).toString()));
        AsposeUtils.writeWordTitle(builder, linkedList);
        linkedList.clear();
        j++;

        if (CollectionUtils.isNotEmpty(itemList)) {
            for (SchemeLiquidationAnalysisItem item : itemList) {
                linkedList.add(StringUtils.isNotBlank(item.getTaxRateName()) ? item.getTaxRateName() : "空");
                if (item.getCalculationMethod() == 1 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                    linkedList.add(new BigDecimal(item.getTaxRateValue()).multiply(new BigDecimal("100")).stripTrailingZeros().toString() + "%");
                } else if (item.getCalculationMethod() == 0 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                    linkedList.add(item.getTaxRateValue() + "元/㎡");
                } else {
                    linkedList.add("空");
                }

                if (StringUtils.isNotBlank(item.getCalculateBase())) {
                    linkedList.add(item.getCalculateBase());
                } else {
                    linkedList.add("空");
                }

                if (StringUtils.isNotBlank(item.getCalculationFormula())) {
                    linkedList.add(item.getCalculationFormula());
                } else {
                    linkedList.add("空");
                }

                if (StringUtils.isNotBlank(item.getTaxesBurden())) {
                    linkedList.add(item.getTaxesBurden());
                } else {
                    linkedList.add("空");
                }

                if (item.getPrice() != null) {
                    linkedList.add(item.getPrice().toString());
                } else {
                    linkedList.add("空");
                }

                AsposeUtils.writeWordTitle(builder, linkedList);
                linkedList.clear();
                j++;
            }
        }

        if (groupItem != null && groupItem.getTotal() != null) {
            linkedList.addAll(Arrays.asList("合计费用", groupItem.getTotal().toString(), "", "", "", ""));
            AsposeUtils.writeWordTitle(builder, linkedList);
            linkedList.clear();
            mergeCellModelList.add(new MergeCellModel(j, 1, j, 5));
        }
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 评估方法 , 估价对象评估方法
     *
     * @throws Exception
     */
    public String getEvaluationMethod() throws Exception {
        List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionService.getApplicableJudgeFunctionsByAreaId(areaId);
        if (CollectionUtils.isEmpty(judgeFunctions)) return "";
        List<Integer> methodTypeList = LangUtils.transform(judgeFunctions, o -> o.getMethodType());
        List<BaseDataDic> baseMethodList = mdCommonService.getBaseMethodList(projectInfo.getProjectCategoryId());
        List<BaseDataDic> resultList = LangUtils.filter(baseMethodList, o -> methodTypeList.contains(o.getId()));
        if (CollectionUtils.isEmpty(resultList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        resultList.forEach(o -> stringBuilder.append(o.getName()).append("，"));
        return StringUtils.strip(stringBuilder.toString(), "，");
    }

    /**
     * 估价对象选择估价方法
     */
    public String getSelectionValuationMethod() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        String localPath = getLocalPath();
        List<SchemeJudgeFunction> functionList = schemeJudgeFunctionService.getApplicableJudgeFunctionsByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(functionList)) {
            Map<String, String> compareMap = Maps.newHashMap();
            Map<String, String> incomeMap = Maps.newHashMap();
            Map<String, String> costMap = Maps.newHashMap();
            Map<String, String> developmentMap = Maps.newHashMap();
            for (SchemeJudgeFunction judgeFunction : functionList) {
                if (judgeFunction.getJudgeObjectId() == null) {
                    continue;
                }
                SchemeJudgeObject judgeObject = schemeJudgeObjectMap.get(judgeFunction.getJudgeObjectId());
                if (judgeObject == null) {
                    continue;
                }
                DataSetUseField setUseField = dataSetUseFieldService.getCacheSetUseFieldById(judgeObject.getSetUse());
                String setUseName = setUseField == null ? "" : StringUtils.defaultString(setUseField.getName());
                String conent = String.format("作为%s,%s", setUseName, judgeFunction.getApplicableReason());
                if (mdCommonService.isCompareMethod(judgeFunction.getMethodType())) {
                    compareMap.put(judgeObject.getNumber(), conent);
                }
                if (mdCommonService.isIncomeMethod(judgeFunction.getMethodType())) {
                    incomeMap.put(judgeObject.getNumber(), conent);
                }
                if (mdCommonService.isCostMethod(judgeFunction.getMethodType())) {
                    costMap.put(judgeObject.getNumber(), conent);
                }
                if (mdCommonService.isDevelopmentMethod(judgeFunction.getMethodType())) {
                    developmentMap.put(judgeObject.getNumber(), conent);
                }
            }
            String compareContent = generateCommonMethod.judgeEachDescExtend(compareMap, "", "。", true);
            String incomeContent = generateCommonMethod.judgeEachDescExtend(incomeMap, "", "。", true);
            String costContent = generateCommonMethod.judgeEachDescExtend(costMap, "", "。", true);
            String deveolpmentContent = generateCommonMethod.judgeEachDescExtend(developmentMap, "", "。", true);
            if (StringUtils.isNotBlank(compareContent)) {
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(compareContent))), false);
            }
            if (StringUtils.isNotBlank(incomeContent)) {
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(incomeContent))), false);
            }
            if (StringUtils.isNotBlank(costContent)) {
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(costContent))), false);
            }
            if (StringUtils.isNotBlank(deveolpmentContent)) {
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(deveolpmentContent))), false);
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象不适用的估价方法
     */
    public String getNotSelectionValuationMethod() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        String localPath = getLocalPath();
        List<SchemeJudgeFunction> functionList = schemeJudgeFunctionService.getNotApplicableJudgeFunctionsByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(functionList)) {
            Map<String, String> compareMap = Maps.newHashMap();
            Map<String, String> incomeMap = Maps.newHashMap();
            Map<String, String> costMap = Maps.newHashMap();
            Map<String, String> developmentMap = Maps.newHashMap();
            for (SchemeJudgeFunction judgeFunction : functionList) {
                SchemeJudgeObject judgeObject = this.schemeJudgeObjectMap.get(judgeFunction.getJudgeObjectId());
                if (judgeObject == null) continue;
                DataSetUseField setUseField = dataSetUseFieldService.getCacheSetUseFieldById(judgeObject.getSetUse());
                String setUseName = setUseField == null ? "" : StringUtils.defaultString(setUseField.getName());
                String conent = String.format("作为%s,%s", setUseName, judgeFunction.getNotApplicableReason());
                if (mdCommonService.isCompareMethod(judgeFunction.getMethodType()))
                    compareMap.put(judgeObject.getNumber(), conent);
                if (mdCommonService.isIncomeMethod(judgeFunction.getMethodType()))
                    incomeMap.put(judgeObject.getNumber(), conent);
                if (mdCommonService.isCostMethod(judgeFunction.getMethodType()))
                    costMap.put(judgeObject.getNumber(), conent);
                if (mdCommonService.isDevelopmentMethod(judgeFunction.getMethodType()))
                    developmentMap.put(judgeObject.getNumber(), conent);
            }
            String compareContent = generateCommonMethod.judgeEachDescExtend(compareMap, "", "。", true);
            String incomeContent = generateCommonMethod.judgeEachDescExtend(incomeMap, "", "。", true);
            String costContent = generateCommonMethod.judgeEachDescExtend(costMap, "", "。", true);
            String deveolpmentContent = generateCommonMethod.judgeEachDescExtend(developmentMap, "", "。", true);
            if (StringUtils.isNotBlank(compareContent))
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(compareContent))), false);
            if (StringUtils.isNotBlank(incomeContent))
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(incomeContent))), false);
            if (StringUtils.isNotBlank(costContent))
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(costContent))), false);
            if (StringUtils.isNotBlank(deveolpmentContent))
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(deveolpmentContent))), false);
        }
        doc.save(localPath);
        return localPath;
    }


    /**
     * 委托目的表述
     */
    public String getStatementPurposeEntrustment() {
        String statementPurposeEntrustment = getSchemeAreaGroup().getRemarkEntrustPurpose();
        if (StringUtils.isNotEmpty(statementPurposeEntrustment)) {
            if (StringUtils.isNotEmpty(statementPurposeEntrustment.trim())) {
                return statementPurposeEntrustment;
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 委托目的
     *
     * @author: zch
     * @date: 2019/4/8 11:47
     */
    public String getDelegatePurpose() {
        String str = baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose());
        if (StringUtils.isNotBlank(str)) {
            return str;
        }
        return errorStr;
    }

    /**
     * 价值类型
     */
    public String getValueType() {
        if (getSchemeAreaGroup() != null) {
            String value = baseDataDicService.getNameById(getSchemeAreaGroup().getValueDefinition());
            if (StringUtils.isNotBlank(value.trim())) {
                return value;
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 价值类型描述
     *
     * @author: zch
     * @date: 2019/4/8 11:47
     */
    public String getValueTypeDesc() {
        String value = getSchemeAreaGroup().getValueDefinitionDesc();
        if (StringUtils.isNotEmpty(value)) {
            if (StringUtils.isNotEmpty(value.trim())) {
                return generateCommonMethod.trim(value);
            }
        }
        return " ";
    }

    /**
     * 注册估价师及注册号
     *
     * @param generateReportInfo
     * @throws Exception
     */
    public String getRegisteredRealEstateValuerAndNumber(GenerateReportInfo generateReportInfo) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        AsposeUtils.saveWord(localPath, document);
        DocumentBuilder documentBuilder = new DocumentBuilder(document);
        documentBuilder.getFont().setSize(14);
        documentBuilder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        documentBuilder.getCellFormat().getBorders().getLeft().setLineWidth(0.0);
        documentBuilder.getCellFormat().getBorders().getRight().setLineWidth(2);
        documentBuilder.getCellFormat().getBorders().getTop().setLineWidth(2.0);
        documentBuilder.getCellFormat().getBorders().getBottom().setLineWidth(2.0);
        documentBuilder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);
        //设置表格为无样式，即没有像什么边框那样的东西
        documentBuilder.getCellFormat().getBorders().setLineStyle(LineStyle.NONE);
        //设置具体宽度
        documentBuilder.getCellFormat().setWidth(20);
        documentBuilder.getCellFormat().setLeftPadding(-300);
        if (StringUtils.isEmpty(generateReportInfo.getRealEstateAppraiser())) {
            return localPath;
        }
        documentBuilder.startTable();
        List<String> stringList = FormatUtils.transformString2List(generateReportInfo.getRealEstateAppraiser());
        if (CollectionUtils.isEmpty(stringList)) {
            return localPath;
        }
        stringList = stringList.stream().distinct().collect(Collectors.toList());
        LinkedList<String> linkedList = Lists.newLinkedList();
        for (String account : stringList) {
            List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, generateReportInfo.getQualificationType());
            if (CollectionUtils.isEmpty(adPersonalQualificationDtoList)) {
                continue;
            }
            Map<String, String> stringMap = Maps.newHashMap();
            adPersonalQualificationDtoList.forEach(adPersonalQualificationDto -> {
                SysUserDto sysUserDto = erpRpcUserService.getSysUser(account);
                if (sysUserDto != null) {
                    if (StringUtils.isNotBlank(adPersonalQualificationDto.getCertificateNo())) {
                        stringMap.put(adPersonalQualificationDto.getCertificateNo(), sysUserDto.getUserName());
                    }
                }
            });
            if (stringMap.isEmpty()) {
                continue;
            }
            for (Map.Entry<String, String> stringEntry : stringMap.entrySet()) {
                linkedList.add(String.format("%s%s", StringUtils.repeat(" ", 5), stringEntry.getValue()));
                linkedList.add(String.format("注册证号:%s", stringEntry.getKey()));
                AsposeUtils.writeWordTitle(documentBuilder, linkedList);
                linkedList.clear();
            }
        }
        documentBuilder.endTable();
        AsposeUtils.saveWord(localPath, document);
        return localPath;
    }

    public String getRegisteredRealEstateValuer(GenerateReportInfo generateReportInfo) {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (StringUtils.isEmpty(generateReportInfo.getRealEstateAppraiser())) {
            return stringBuilder.toString();

        }
        List<String> stringList = FormatUtils.transformString2List(generateReportInfo.getRealEstateAppraiser());
        if (CollectionUtils.isEmpty(stringList)) {
            return stringBuilder.toString();
        }
        stringList = stringList.stream().distinct().collect(Collectors.toList());
        for (String account : stringList) {
            SysUserDto sysUserDto = erpRpcUserService.getSysUser(account);
            if (sysUserDto != null) {
                stringBuilder.append(sysUserDto.getUserName());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 估价机构信息
     *
     * @return
     * @throws Exception
     */
    public String getXIEHE_organizationInfo(AdCompanyQualificationDto qualificationDto) throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(documentBuilder);
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "机构名称", qualificationDto.getOrganizationName())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "住所", qualificationDto.getOrganizationAddress())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "法定代表人", qualificationDto.getLegalRepresentative())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "工商注册号", qualificationDto.getRegisteredNo())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "资质等级", qualificationDto.getOrganizationRank())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "资质证书编号", qualificationDto.getCertificateNo())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "资质证书有效期", qualificationDto.getCertificateEffectiveDate())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "经营范围", StringUtils.isEmpty(qualificationDto.getBusinessScopeName()) ? "" : qualificationDto.getBusinessScopeName())));
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 根据公司编号取得公司营业执照
     *
     * @return
     */
    public AdCompanyQualificationDto getCompanyQualificationForLicense() {
        return adRpcQualificationsService.getCompanyQualificationForLicense(publicService.getCurrentCompany().getCompanyId());
    }

    /**
     * 根据公司编号取得公司执业资质
     *
     * @return
     */
    public AdCompanyQualificationDto getCompanyQualificationForPractising() {
        return adRpcQualificationsService.getCompanyQualificationForPractising(publicService.getCurrentCompany().getCompanyId());
    }

    /**
     * 经营范围
     *
     * @param qualificationDto
     * @return
     * @throws Exception
     */
    public String getBusinessScope(AdCompanyQualificationDto qualificationDto) throws Exception {
        return StringUtils.isEmpty(qualificationDto.getBusinessScopeName()) ? "" : qualificationDto.getBusinessScopeName();
    }

    /**
     * 现场查勘期
     *
     * @param start
     * @param end
     * @return
     */
    public String getSurveyExamineDate(Date start, Date end) {
        if (start == null) {
            start = new Date();
        }
        if (end == null) {
            end = new Date();
        }
        return String.format("%s至%s", DateUtils.format(start, DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN));
    }

    /**
     * 现场查勘人员
     *
     * @return
     * @throws Exception
     */
    public String getSurveyExamineCreate() throws Exception {
        List<ProjectPhaseVo> projectPhaseVoList = projectPhaseService.queryProjectPhaseByCategory(projectInfo.getProjectTypeId(),
                projectInfo.getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
            //查勘通过
            if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<ProjectPlanDetails> projectPlanDetailsList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(projectPhaseVoList)) {
            projectPhaseVoList.stream().forEach(projectPhaseVo -> {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setProjectPhaseId(projectPhaseVo.getId());
                List<ProjectPlanDetails> projectPlanDetailss = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailss)) {
                    projectPlanDetailsList.addAll(projectPlanDetailss);
                }
            });
        }
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
            projectPlanDetailsList.stream().forEach(projectPlanDetails -> stringSet.add(projectPlanDetails.getExecuteUserAccount()));
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        return s;
    }

    /**
     * 作业结束时间
     *
     * @param end
     * @return
     */
    public String getHomeWorkEndTime(Date end) {
        if (end == null) {
            end = new Date();
        }
        return DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN);
    }

    /**
     * 作业开始时间
     *
     * @return
     */
    public String getHomeWorkStartTime() {
        return DateUtils.format(projectInfo.getGmtCreated(), DateUtils.DATE_CHINESE_PATTERN);
    }

    /**
     * 评估依据假设原则
     *
     * @param schemeSupportTypeEnum
     * @return
     * @throws Exception
     */
    public String getPrincipleBasisHypothesis(SchemeSupportTypeEnum schemeSupportTypeEnum) throws Exception {
        if (projectInfo == null || schemeSupportTypeEnum == null) return "";
        String result = "";
        switch (schemeSupportTypeEnum) {
            case HYPOTHESIS:
                result = evaluationHypothesisService.getReportHypothesis(this.projectInfo, areaId);
                break;
            case BASIS:
                result = evaluationBasisService.getReportBasic(this.projectInfo, areaId);
                break;
            case PRINCIPLE:
                result = evaluationPrincipleService.getReportPrinciple(this.projectInfo, areaId);
                break;
        }
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.trim(result, false)), true);
        document.save(localPath);
        return localPath;
    }

    /**
     * 变现分析与风险提示
     *
     * @param schemeSupportTypeEnum
     * @return
     * @throws Exception
     */
    public String getLiquidityRisk(SchemeSupportTypeEnum schemeSupportTypeEnum) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        String result = "";
        switch (schemeSupportTypeEnum) {
            case REPORT_ANALYSIS_CATEGORY_LIQUIDITY:
                result = dataReportAnalysisService.getReportLiquidity(this.projectInfo, areaId);
                if (StringUtils.isNotBlank(result)) {
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml(result), true);
                }
                break;
            case REPORT_ANALYSIS_CATEGORY_RISK:
                result = dataReportAnalysisRiskService.getReportRisk(areaId);
                if (StringUtils.isNotBlank(result)) {
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml(result), true);
                }
                break;
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 变现能力分析小微快贷
     *
     * @return
     * @throws Exception
     */
    public String getLiquidityRiskLittle() throws Exception {
        String result = "/";
        try {
            result = dataReportAnalysisService.getReportLiquidityLittle(this.projectInfo, areaId);
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1, "变现能力分析小微快贷");
        }
        return result;
    }

    /**
     * 报告分析
     *
     * @return
     */
    public String getReportAnalysis(String type) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(compileReportService.getReportCompile(this.areaId, type)))), true);
        document.save(localPath);
        return localPath;
    }

    /**
     * 协助工作人员
     *
     * @return
     */
    public String getAssistanceStaff(GenerateReportInfo generateReportInfo) {
        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(projectInfo.getProjectTypeId(),
                projectInfo.getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
            if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY_EXTEND, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        final List<String> stringList = new ArrayList<>();
        if (StringUtils.isNotBlank(generateReportInfo.getRealEstateAppraiser())) {
            stringList.addAll(FormatUtils.transformString2List(generateReportInfo.getRealEstateAppraiser()));
            List<String> stringList2 = stringList.stream().distinct().collect(Collectors.toList());
            stringList.clear();
            stringList.addAll(stringList2);
        }
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            projectPhaseVos.stream().forEach(projectPhaseScene -> {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhaseScene.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        projectPlanDetailsList.stream().forEach(projectPlanDetails -> {
                            String account = projectPlanDetails.getExecuteUserAccount();
                            if (StringUtils.isNotBlank(account)) {
                                if (!stringList.isEmpty()) {
                                    if (!stringList.contains(account)) {
                                        stringSet.add(publicService.getUserNameByAccount(account));
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
        return StringUtils.join(stringSet, "");
    }

    /**
     * 设定用途
     *
     * @return
     */
    public String getSetUse(boolean explainShow) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectDeclareList) {
            DataSetUseField setUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
            map.put(generateCommonMethod.parseIntJudgeNumber(generateCommonMethod.getNumber(schemeJudgeObject.getNumber())), setUseField == null ? "" : setUseField.getName());
        }
        return generateCommonMethod.judgeSummaryDesc(map, explainShow ? "设定用途为" : "", false);
    }


    /**
     * 实际用途
     *
     * @return
     */
    public String getPracticalUse() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectDeclareList) {
            map.put(generateCommonMethod.parseIntJudgeNumber(generateCommonMethod.getNumber(schemeJudgeObject.getNumber())), schemeJudgeObject.getCertUse());
        }
        return generateCommonMethod.judgeSummaryDesc(map, "实际用途为", false);
    }

    /**
     * 价值时点
     *
     * @return
     */
    public String getValueTimePoint() {
        if (getSchemeAreaGroup().getValueTimePoint() != null) {
            return DateUtils.format(getSchemeAreaGroup().getValueTimePoint(), DateUtils.DATE_CHINESE_PATTERN);
        }
        return errorStr;
    }

    /**
     * 价值时点说明
     *
     * @return
     */
    public String getValueTimePointRemark() {
        if (StringUtils.isNotBlank(getSchemeAreaGroup().getTimePointExplain())) {
            return getSchemeAreaGroup().getTimePointExplain();
        }
        return errorStr;
    }


    /**
     * 出租或占用情况
     *
     * @return
     */
    public String getRentalPossessionDesc() {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 他项权力
     *
     * @return
     */
    private Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> getSurveyAssetInventoryRightMapAndSchemeJudgeObject() {
        Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList().stream().filter(oo -> oo.getDeclareRecordId() != null).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SurveyAssetInventoryRightRecord> rightRecordList = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(schemeJudgeObject.getDeclareRecordId(), projectId);
                if (CollectionUtils.isNotEmpty(rightRecordList)) {
                    rightRecordList.stream().forEach(oo -> {
                        List<SurveyAssetInventoryRight> list = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(oo.getId());
                        if (CollectionUtils.isNotEmpty(list)) {
                            list = list.stream().distinct().collect(Collectors.toList());
                            if (CollectionUtils.isNotEmpty(list)) {
                                map.put(schemeJudgeObject, list);
                            }
                        }
                    });
                }
            }
        }
        return map;
    }

    /**
     * 他权类别
     *
     * @return
     * @throws Exception
     */
    public String getHisRightType(Boolean containDetail) throws Exception {
        List<SurveyRightGroupDto> groupDtoList = surveyAssetInventoryRightRecordService.groupRightByCategory(projectId, schemeJudgeObjectDeclareList);
        if (CollectionUtils.isEmpty(groupDtoList)) return "";
        StringBuilder stringBuilder = new StringBuilder("、《他权权证》");
        if (containDetail) {
            for (SurveyRightGroupDto surveyRightGroupDto : groupDtoList) {
                if (!StringUtils.equals(surveyRightGroupDto.getCategoryName(), "其它"))
                    stringBuilder.append(String.format("%s、", surveyRightGroupDto.getCategoryName()));
            }
        }
        return StringUtils.stripEnd(stringBuilder.toString(), "、");
    }

    /**
     * 功能描述: 资产清查实际地址
     */
    public String getActualAddressAssetInventory(BaseDataDic type, Integer declareRecordId) throws Exception {
        return getAssetInventoryCommon("actual", type, declareRecordId);
    }

    /**
     * 登记地址
     *
     * @throws Exception
     */
    public String getActualRegistration(BaseDataDic type, Integer declareRecordId) throws Exception {
        return getAssetInventoryCommon("registration", type, declareRecordId);
    }

    /**
     * 功能描述: 资产清查证明人
     */
    public String getCertificateAssetInventory(BaseDataDic type, Integer declareRecordId) throws Exception {
        return getAssetInventoryCommon("voucher", type, declareRecordId);
    }

    /**
     * 功能描述: 资产清查证明文件
     */
    public String getCredentialAssetInventory(BaseDataDic type, Integer declareRecordId) throws Exception {
        return getAssetInventoryCommon("credential", type, declareRecordId);
    }

    /**
     * 功能描述: 资产清查确认一致
     */
    public String getAssetInventoryConfirmConsistency(BaseDataDic type, Integer declareRecordId) throws Exception {
        return getAssetInventoryCommon("sureConsistent", type, declareRecordId);
    }

    /**
     * 功能描述: 资产清查一致说明
     */
    public String getAssetInventoryAgreement(BaseDataDic type, Integer declareRecordId) throws Exception {
        return getAssetInventoryCommon("differenceReason", type, declareRecordId);
    }

    /**
     * 资产清查 提取的公共方法
     *
     * @param fieldName
     * @param type
     * @throws Exception
     */
    private String getAssetInventoryCommon(String fieldName, BaseDataDic type, Integer declareRecordId) throws Exception {
        return generateCommonMethod.getAssetInventoryCommon(fieldName, type, declareRecordId, projectInfo);
    }

    /**
     * 最佳利用方式
     *
     * @return
     * @throws Exception
     */
    public String getOptimumUtilizationMode() throws Exception {
        Map<String, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : this.schemeJudgeObjectList) {
            DataBestUseDescription bestUseDescription = dataBestUseDescriptionService.getBestUseDescriptionById(schemeJudgeObject.getBestUse());
            map.put(schemeJudgeObject.getNumber(), bestUseDescription.getName());
        }
        return StringUtils.strip(generateCommonMethod.judgeEachDescExtend(map, "", ";", false), ";");
    }


    /**
     * 假设开发法适用原因
     *
     * @return
     */
    public String getDevelopmentAssistApplyReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_DEVELOPMENT, false, true, false);
    }

    /**
     * 假设开发法不适用原因
     *
     * @return
     */
    public String getDevelopmentAssistNotApplicableReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_DEVELOPMENT, false, false, true);
    }

    /**
     * 假设开发法评估思路
     *
     * @return
     */
    public String getDevelopmentAssistThink() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_DEVELOPMENT, true, false, false);
    }


    /**
     * 收益法适用原因
     *
     * @return
     */
    public String getIncomeAssistApplyReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_INCOME, false, true, false);
    }

    /**
     * 收益法不适用原因
     *
     * @return
     */
    public String getIncomeAssistNotApplicableReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_INCOME, false, false, true);
    }

    /**
     * 收益法评估思路
     *
     * @return
     */
    public String getIncomeAssistThink() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_INCOME, true, false, false);
    }

    /**
     * 市场比较法适用原因
     *
     * @return
     */
    public String getCompareAssistApplyReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_MARKET_COMPARE, false, true, false);
    }

    /**
     * 市场比较法不适用原因
     *
     * @return
     */
    public String getCompareAssistNotApplicableReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_MARKET_COMPARE, false, false, true);
    }

    /**
     * 市场比较法评估思路
     *
     * @return
     */
    public String getCompareAssistThink() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_MARKET_COMPARE, true, false, false);
    }

    /**
     * 成本法适用原因
     *
     * @return
     */
    public String getCostAssistApplyReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_COST, false, true, false);
    }

    /**
     * 成本法不适用原因
     *
     * @return
     */
    public String getCostAssistNotApplicableReason() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_COST, false, false, true);
    }

    /**
     * 成本法评估思路
     *
     * @return
     */
    public String getCostAssistThink() {
        return this.getAssistThinkAndApplicableReasonOrNotApplicableReason(AssessDataDicKeyConstant.MD_COST, true, false, false);
    }

    /**
     * 各种评估方法的取值 (example:市场比较法适用原因)
     *
     * @param key
     * @param think
     * @param applicableReason
     * @param notApplicableReason
     * @return
     */
    private String getAssistThinkAndApplicableReasonOrNotApplicableReason(String key, boolean think, boolean applicableReason, boolean notApplicableReason) {
        StringBuilder builder = new StringBuilder(8);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(key);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                if (think) {
                                    stringSet.add(schemeJudgeFunction.getThinking());
                                }
                                if (applicableReason) {
                                    stringSet.add(schemeJudgeFunction.getApplicableReason());
                                }
                                if (notApplicableReason) {
                                    stringSet.add(schemeJudgeFunction.getNotApplicableReason());
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            builder.append(StringUtils.join(stringSet, "，"));
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }


    /**
     * 功能描述: 估价对象市场价值的确定
     *
     * @auther: zch
     * @date: 2019/2/26 16:25
     */
    public String getDeterminationMarketValueValuationObject() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        StringBuilder stringBuilder = new StringBuilder(8);
        BaseDataDic mdIncome = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
        BaseDataDic mdCompare = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        final int TEN = 10;
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
        keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
        keyValueDtoList.add(new KeyValueDto("font-size", "14.0pt"));
        keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
        keyValueDtoList.add(new KeyValueDto("text-indent", "2em"));
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        Map<SchemeJudgeObject, List<SchemeSurePriceItem>> objectListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                    objectListMap.put(schemeJudgeObject, schemeSurePriceItemList);
                }
            }
        }
        if (!objectListMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, List<SchemeSurePriceItem>> entry : objectListMap.entrySet()) {
                List<SchemeSurePriceItem> schemeSurePriceItemList = entry.getValue();
                List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getChildrenJudgeObject(entry.getKey().getId());
                SchemeSurePriceItem mdIncomeItem = null;
                SchemeSurePriceItem mdCompareItem = null;
                final int size = schemeSurePriceItemList.size();
                if (schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdIncome.getName())).count() >= 1) {
                    mdIncomeItem = schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdIncome.getName())).findFirst().get();
                }
                if (schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdCompare.getName())).count() >= 1) {
                    mdCompareItem = schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdCompare.getName())).findFirst().get();
                }
                stringBuilder.delete(0, stringBuilder.toString().length());
                if (objectListMap.size() > 1) {
                    stringBuilder.append(generateCommonMethod.getSchemeJudgeObjectShowName(entry.getKey()));
                }
                if (size == 1) {
                    stringBuilder.append("通过对该区域的调查，考虑估价对象在该区域内的具体位置等因素，比较法的试算结果能反映估价对象市场价值。故最终单价=").append(generateCommonMethod.getBigDecimalRound(entry.getValue().stream().findFirst().get().getTrialPrice(), 2, false));
                }
                if (size == 2) {
                    //刚好收益法和市场比较法 选择的情况
                    if (mdIncomeItem != null && mdCompareItem != null) {
                        final int computeDifference = publicService.computeDifference(mdIncomeItem.getTrialPrice(), mdCompareItem.getTrialPrice());
                        stringBuilder.append(mdIncome.getName()).append("与").append(mdCompare.getName());
                        if (computeDifference > TEN) {
                            stringBuilder.append("测算结果有一定差异").append("，");
                            stringBuilder.append("通过对该区域的调查，考虑估价对象在该区域内的具体位置等因素，").append(mdCompare.getName()).append("的试算结果更接近市场状况。");
                            stringBuilder.append("故最终单价=");
                            double[] doubles = new double[]{mdIncomeItem.getTrialPrice().doubleValue(), mdCompareItem.getTrialPrice().doubleValue()};
                            double max = Arrays.stream(doubles).max().getAsDouble();
                            double min = Arrays.stream(doubles).min().getAsDouble();
                            double d = min / max;
                            stringBuilder.append(mdIncomeItem.getTrialPrice().toString()).append("×");
                            if (mdIncomeItem.getWeight() != null) {
                                BigDecimal bigDecimal = mdIncomeItem.getWeight().multiply(new BigDecimal(100));
                                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                                stringBuilder.append(String.format("%s%s", bigDecimal.toString(), "%"));
                            } else {
                                BigDecimal bigDecimal = new BigDecimal((d * 100)).multiply(new BigDecimal(100));
                                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                                stringBuilder.append(String.format("%s%s", bigDecimal.toString(), "%"));
                            }
                            stringBuilder.append("+").append(mdCompareItem.getTrialPrice().toString()).append("×");
                            if (mdCompareItem.getWeight() != null) {
                                BigDecimal bigDecimal = mdCompareItem.getWeight().multiply(new BigDecimal(100));
                                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                                stringBuilder.append(String.format("%s%s", bigDecimal.toString(), "%"));
                            } else {
                                BigDecimal bigDecimal = new BigDecimal(((1 - d) * 100)).multiply(new BigDecimal(100));
                                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                                stringBuilder.append(String.format("%s%s", bigDecimal.toString(), "%"));
                            }
                            stringBuilder.append("（").append("收益法价格*权重+比较法价格*权重").append("）");
                        }
                        if (computeDifference <= TEN) {
                            stringBuilder.append("测算结果相近，");
                            stringBuilder.append("通过对该区域的调查，考虑估价对象在该区域内的具体位置等因素").append("，");
                            stringBuilder.append(mdIncome.getName()).append("的试算结果与").append(mdCompare.getName());
                            stringBuilder.append("试算结果均能反映估价对象市场价值").append("。");
                            stringBuilder.append("故最终单价=");
                            stringBuilder.append(mdIncomeItem.getTrialPrice().toString()).append("×").append("50%").append("+").append(mdCompareItem.getTrialPrice().toString()).append("×").append("50%").append("（").append("收益法价格*权重+比较法价格*权重").append("）");
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                    List<String> stringList = Lists.newArrayList();
                    judgeObjectList.stream().forEach(oo -> {
                        SchemeJudgeObjectVo schemeJudgeObjectVo = schemeJudgeObjectService.getSchemeJudgeObjectVo(oo);
                        if (StringUtils.isNotBlank(schemeJudgeObjectVo.getCoefficient())) {
                            stringList.add(String.format("%s标准价基础上经%s", generateCommonMethod.getSchemeJudgeObjectShowName(oo), schemeJudgeObjectVo.getCoefficient()));
                        }
                    });
                    stringBuilder.append(StringUtils.join(stringList, "，")).append("估价对象结果如下表");
                }
                AsposeUtils.insertHtml(documentBuilder, AsposeUtils.getWarpCssHtml(stringBuilder.toString(), keyValueDtoList));
                //当为合并对象的时候,需要写入单价调整表
                if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                    List<SchemeJudgeObjectVo> voList = judgeObjectList.stream().map(oo -> schemeJudgeObjectService.getSchemeJudgeObjectVo(oo)).collect(Collectors.toList());
                    documentBuilder.insertDocument(getUnitPriceAdjustmentDocument(voList), ImportFormatMode.KEEP_DIFFERENT_STYLES);
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 单价调整表
     *
     * @throws Exception
     */
    private Document getUnitPriceAdjustmentDocument(List<SchemeJudgeObjectVo> schemeJudgeObjectList) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        generateCommonMethod.settingBuildingTable(builder);
        final String nullString = "";
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            boolean isLabelJudgeObjectShowName = schemeJudgeObjectList.size() <= 20;
            generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("估价编号", "因素", "评估面积", "评估单价", "评估总价")));
            for (SchemeJudgeObjectVo schemeJudgeObject : schemeJudgeObjectList) {
                if (isLabelJudgeObjectShowName) {
                    stringList.add(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject));
                } else {
                    stringList.add(String.join("", schemeJudgeObject.getNumber(), "号"));
                }
                stringList.add(StringUtils.isNotBlank(schemeJudgeObject.getCoefficient()) ? schemeJudgeObject.getCoefficient() : nullString);
                if (schemeJudgeObject.getEvaluationArea() != null) {
                    stringList.add(String.format("%s%s", schemeJudgeObject.getEvaluationArea().toString(), "㎡"));
                } else {
                    stringList.add(nullString);
                }
                if (schemeJudgeObject.getPrice() != null) {
                    stringList.add(String.format("%s%s", schemeJudgeObject.getPrice().toString(), "元"));
                } else {
                    stringList.add(nullString);
                }
                if (schemeJudgeObject.getPrice() != null && schemeJudgeObject.getEvaluationArea() != null) {
                    BigDecimal bigDecimal = schemeJudgeObject.getPrice().multiply(schemeJudgeObject.getEvaluationArea());
                    bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_UP);
                    stringList.add(String.format("%s%s", bigDecimal.toString(), "元"));
                } else {
                    stringList.add(nullString);
                }
                generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(stringList));
                stringList.clear();
            }
        }
        return doc;
    }


    /**
     * 主要计算过程
     *
     * @return
     * @throws Exception
     */
    public String getComputationProcess() throws Exception {
        return getFormulaString("公式");
    }

    /**
     * 相关参数选取与应用
     *
     * @return
     * @throws Exception
     */
    public String getSelectionApplicationParameters() throws Exception {
        return getFormulaString("参数");
    }

    private String getFormulaString(String type) throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        Map<String, String> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> functions = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(functions)) {
                    for (SchemeJudgeFunction function : functions) {
                        DataMethodFormula formula = dataMethodFormulaService.getDataMethodFormulaByType(function.getMethodType());
                        if (formula != null) {
                            switch (type) {
                                case "参数":
                                    map.put(schemeJudgeObject.getNumber(), formula.getRelevantParameter());
                                    break;
                                case "公式":
                                    map.put(schemeJudgeObject.getNumber(), formula.getFormula());
                                    break;
                            }

                        }
                    }
                }
            }
        }
        String strResult = generateCommonMethod.judgeEachDescExtend(map, "", "。", false);
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(strResult)), false);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象权益状况表
     *
     * @return
     * @throws Exception
     */
    public String getJudgeObjectEquitySheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        //1.先根据楼盘分组，再分别获取到楼盘下的权益信息
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> linkedHashMap = generateCommonMethod.getEstateGroupByAreaId(areaId);
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : linkedHashMap.entrySet()) {
                //根据不同项目类别确定获取数据的方法
                if (linkedHashMap.size() > 1) {//添加楼盘或估价对象编号作区分
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml("<div style='text-align:center;;font-size:16.0pt;'>" + entry.getKey().getName() + "</div>"));
                }
                if (projectInfo.getProjectCategoryName().contains("房产")) {
                    {
                        String s = null;
                        try {
                            s = generateEquityService.getLandEquity(entry.getKey(), entry.getValue());
                        } catch (Exception e) {
                            baseService.writeExceptionInfo(e, "土地权益状况未获取到");
                        }
                        if (StringUtils.isNotBlank(s)) {
                            builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml("1、土地权益状况")));
                            builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(s)), false);
                        }
                    }
                    {
                        String s = null;
                        try {
                            s = generateEquityService.getHouseEquity(entry.getValue(), projectId);
                        } catch (Exception e) {
                            baseService.writeExceptionInfo(e, "房屋权益状况未获取到");
                        }
                        if (StringUtils.isNotBlank(s)) {
                            builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml("2、房屋权益状况")));
                            builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(s)), false);
                        }
                    }
                } else if (projectInfo.getProjectCategoryName().contains("土地")) {
                    {
                        String s = null;
                        try {
                            s = generateEquityService.getLandEquityFull(entry.getKey(), entry.getValue(), projectId);
                        } catch (Exception e) {
                            baseService.writeExceptionInfo(e, "土地权益状况未获取到");
                        }
                        if (StringUtils.isNotBlank(s)) {
                            builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml("1、土地权益状况")));
                            builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(s)), false);
                        }
                    }
                }
            }
        }
        String localPath = getLocalPath();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 坐落信息
     *
     * @return
     */
    public String getSeatText() {
        Map<SchemeJudgeObject, String> map = getSeatMap();
        String value = "/";
        Map<Integer, String> stringMap = Maps.newHashMap();
        if (!map.isEmpty()) {
            map.entrySet().forEach(schemeJudgeObjectStringEntry -> {
                stringMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObjectStringEntry.getKey().getNumber()), schemeJudgeObjectStringEntry.getValue());
            });
            value = generateCommonMethod.judgeEachDesc2(stringMap, "", "", false);
        }
        return value;
    }

    private Map<SchemeJudgeObject, String> getSeatMap() {
        Map<SchemeJudgeObject, String> map = Maps.newHashMap();
        Map<SchemeJudgeObject, DeclareRecord> declareRecordMap = getDeclareRecordJudgeObjectMap();
        if (!declareRecordMap.isEmpty()) {
            declareRecordMap.forEach((schemeJudgeObject, declareRecord) -> {
                if (StringUtils.isNotBlank(declareRecord.getSeat())) {
                    map.put(schemeJudgeObject, declareRecord.getSeat());
                }
            });
        }
        return map;
    }

    private Map<SchemeJudgeObject, DeclareRecord> getDeclareRecordJudgeObjectMap() {
        List<SchemeJudgeObject> judgeObjectList = getSchemeJudgeObjectList();
        Map<SchemeJudgeObject, DeclareRecord> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            map.put(schemeJudgeObject, declareRecord);
        }
        return map;
    }

    /**
     * 权证号
     *
     * @return
     * @throws Exception
     */
    public String getSchemeJudgeObjectCertText() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<SchemeJudgeObject, String> map = Maps.newHashMap();
        Map<SchemeJudgeObject, DeclareRecord> declareRecordMap = getDeclareRecordJudgeObjectMap();
        if (!declareRecordMap.isEmpty()) {
            declareRecordMap.forEach((schemeJudgeObject, declareRecord) -> {
                if (StringUtils.isNotBlank(declareRecord.getName())) {
                    map.put(schemeJudgeObject, declareRecord.getName());
                }
            });
        }
        if (!map.isEmpty()) {
            int i = 0;
            for (Map.Entry<SchemeJudgeObject, String> entry : map.entrySet()) {
                SchemeJudgeObject schemeJudgeObject = entry.getKey();
                String s = entry.getValue();
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (map.size() != 1) {
                    stringBuilder.append(StringUtils.repeat(ControlChar.PARAGRAPH_BREAK_CHAR, 1)).append(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject));
                }
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    stringBuilder.append(s);
                }
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                    DeclareRealtyLandCert declareRealtyLandCert = null;
                    DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                    center.setHouseId(realtyHouseCertById.getId());
                    center.setPlanDetailsId(realtyHouseCertById.getPlanDetailsId());
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(center);
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        if (centerList.stream().anyMatch(oo -> oo.getLandId() != null)) {
                            declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(centerList.stream().filter(oo -> oo.getLandId() != null).findFirst().get().getLandId());
                        }
                    }
                    if (declareRealtyLandCert != null && StringUtils.isNotEmpty(declareRealtyLandCert.getLandCertName())) {
                        stringBuilder.append(String.format("房屋所有权证号:%s", s)).append(";");
                        //插入换行符
                        stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                        stringBuilder.append(String.format("国有土地使用权证号:%s", declareRealtyLandCert.getLandCertName()));
                    } else {
                        stringBuilder.append(s);
                    }
                }
                i++;
                if (i != map.size()) {
                    stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, 1)).append(ControlChar.PARAGRAPH_BREAK_CHAR);
                }
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            stringBuilder.append(ControlChar.PARAGRAPH_BREAK_CHAR);
        }
        return stringBuilder.toString();
    }


    /**
     * 法定优先受偿款
     *
     * @return
     * @throws Exception
     */
    public BigDecimal getSchemeReimbursementKnowTotalPrice() {
        BigDecimal knowTotalPrice = new BigDecimal(0);
        List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = getSchemeReimbursementItemVoList();
        if (CollectionUtils.isNotEmpty(schemeReimbursementItemVoList)) {
            for (SchemeReimbursementItemVo schemeReimbursementItemVo : schemeReimbursementItemVoList) {
                if (schemeReimbursementItemVo.getKnowTotalPrice() != null) {
                    knowTotalPrice = knowTotalPrice.add(schemeReimbursementItemVo.getKnowTotalPrice());
                }
            }
        }
        return knowTotalPrice;
    }

    private Map<SchemeReimbursementItemVo, List<SchemeJudgeObject>> getSurveyAssetInventoryRightRecordListMap(List<SchemeJudgeObject> list) {
        Map<SchemeReimbursementItemVo, List<SchemeJudgeObject>> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (SchemeJudgeObject oo : list) {
                schemeJudgeObjectList.add(oo);
            }
        }
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.OTHER_RIGHT, projectInfo.getProjectCategoryId());
        ProjectPlanDetails query = new ProjectPlanDetails();
        query.setProjectId(projectId);
        query.setProjectPhaseId(projectPhase.getId());
        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
        if (CollectionUtils.isEmpty(projectPlanDetailsList)) {
            return map;
        }
        SurveyAssetInventoryRightRecordCenter selectRightRecordCenter = new SurveyAssetInventoryRightRecordCenter();
        selectRightRecordCenter.setProjectId(projectPlanDetailsList.stream().findFirst().get().getProjectId());
        selectRightRecordCenter.setPlanDetailsId(projectPlanDetailsList.stream().findFirst().get().getId());
        selectRightRecordCenter.setProcessInsId(projectPlanDetailsList.stream().findFirst().get().getProcessInsId());
        List<SurveyAssetInventoryRightRecordCenter> centerList = surveyAssetInventoryRightRecordCenterService.getSurveyAssetInventoryRightRecordCenterList(selectRightRecordCenter);
        if (CollectionUtils.isEmpty(centerList)) {
            return map;
        }
        List<SurveyAssetInventoryRightRecord> rightRecordList = surveyAssetInventoryRightRecordCenterService.getSurveyAssetInventoryRightRecordList(centerList.stream().findFirst().get().getId(), projectId, centerList.stream().findFirst().get().getPlanDetailsId());
        if (CollectionUtils.isEmpty(rightRecordList)) {
            return map;
        }
        for (SurveyAssetInventoryRightRecord rightRecord : rightRecordList) {
            SchemeReimbursementItemVo vo = schemeReimbursementService.getSchemeReimbursementItemVoByInventoryRightRecordId(rightRecord.getId());
            if (vo != null && StringUtils.isNotBlank(rightRecord.getRecordIds())) {
                List<Integer> integerList = Lists.newArrayList(rightRecord.getRecordIds().split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
                List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
                for (Integer integer : integerList) {
                    if (schemeJudgeObjectList.stream().filter(oo -> oo.getDeclareRecordId().intValue() == integer.intValue()).count() >= 1) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.stream().filter(oo -> oo.getDeclareRecordId().intValue() == integer.intValue()).findFirst().get();
                        judgeObjectList.add(schemeJudgeObject);
                        schemeJudgeObjectList.remove(schemeJudgeObject);
                    }
                }
                if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                    map.put(vo, judgeObjectList.stream().distinct().collect(Collectors.toList()));
                }
            }
        }
        return map;
    }

    /**
     * 估价结果一览表
     *
     * @throws Exception
     */
    public String getjudgeBuildResultSurveySheet(boolean seat) throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        LinkedHashMap<BasicApply, SchemeJudgeObject> schemeJudgeObjectLinkedHashMap = Maps.newLinkedHashMap();
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        boolean mortgageFlag = Objects.equal(projectInfo.getEntrustPurpose(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId());
        LinkedList<Double> doubleLinkedList = Lists.newLinkedList(Lists.newArrayList(50d, 30d, 30d, 30d, 30d, 50d, 55d, 60d, 50d, 50d));
        if (seat) {
            doubleLinkedList.add(1, doubleLinkedList.stream().limit(1).mapToDouble(Double::doubleValue).sum() * 3);
        }
        if (!mortgageFlag) {
            doubleLinkedList.removeLast();
            doubleLinkedList.removeLast();
        }
        String localPath = getLocalPath();
        Table table = builder.startTable();
        final Integer colMax = seat ? new Integer(11) : new Integer(10);
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Map<SchemeReimbursementItemVo, List<SchemeJudgeObject>> reimbursementItemVoListMap = this.getSurveyAssetInventoryRightRecordListMap(schemeJudgeObjectList);
        LinkedList<String> strings = Lists.newLinkedList(Lists.newArrayList("估价对象", "坐落", "用途(证载)", "用途(实际)", "房屋总层数", "所在层数", "建筑面积㎡", "单价（元/㎡）", "评估总价（万元）", "法定优先受偿款(万元)", "抵押价值(万元)"));
        //有他项权力的情况
        if (!reimbursementItemVoListMap.isEmpty()) {
            List<SchemeJudgeObject> listA = Lists.newArrayList();
            List<SchemeJudgeObject> objectList = Lists.newArrayList();
            reimbursementItemVoListMap.entrySet().stream().forEach(entry -> listA.addAll(entry.getValue()));
            if (CollectionUtils.isNotEmpty(listA)) {
                objectList = Lists.newArrayList(CollectionUtils.subtract(schemeJudgeObjectList, listA.stream().distinct().collect(Collectors.toList())));
            }
            if (!seat) {
                strings.remove(1);
            }
            if (!mortgageFlag) {
                strings.removeLast();
                strings.removeLast();
            }
            generateCommonMethod.writeWordTitle(builder, doubleLinkedList, strings);
            //组遍历
            reimbursementItemVoListMap.entrySet().stream().forEach(entry -> {
                for (SchemeJudgeObject schemeJudgeObject : entry.getValue()) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply != null) {
                        schemeJudgeObjectLinkedHashMap.put(basicApply, schemeJudgeObject);
                    }
                }
                if (!schemeJudgeObjectLinkedHashMap.isEmpty()) {
                    try {
                        BigDecimal evaluationArea = new BigDecimal(0);
                        BigDecimal price = new BigDecimal(0);
                        BigDecimal total = new BigDecimal(0);
                        for (Map.Entry<BasicApply, SchemeJudgeObject> integerEntry : schemeJudgeObjectLinkedHashMap.entrySet()) {
                            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(integerEntry.getValue().getDeclareRecordId());
                            if (declareRecord != null && declareRecord.getPracticalArea() != null) {
                                evaluationArea = evaluationArea.add(declareRecord.getPracticalArea());
                            }
                            if (declareRecord != null && declareRecord.getPrice() != null) {
                                price = price.add(declareRecord.getPrice());
                            }
                            if (declareRecord != null && declareRecord.getPrice() != null && declareRecord.getPracticalArea() != null) {
                                total = total.add(declareRecord.getPracticalArea().multiply(declareRecord.getPrice()));
                            }
                            this.writeJudgeObjectResultSurveyInCell(integerEntry.getKey(), integerEntry.getValue(), builder, doubleLinkedList, seat, mortgageFlag, schemeJudgeObjectList.size() <= 20);
                        }
                        Cell cellRange0 = null;
                        for (int j = 0; j < colMax; j++) {
                            Cell cell = builder.insertCell();
                            cell.getCellFormat().setWidth(doubleLinkedList.get(j).doubleValue());
                            if (j == 0) {
                                cellRange0 = cell;
                                builder.write("小计");
                            }
                            if (j == colMax - 6) {
                                mergeCellModelList.add(new MergeCellModel(cellRange0, cell));
                            }
                            if (j == colMax - 5) {
                                builder.write(evaluationArea.toString());
                            }
                            if (j == colMax - 4) {
                                builder.write(price.toString());
                            }
                            if (j == colMax - 3) {
                                BigDecimal temp = new BigDecimal(total.toString());
                                temp = temp.divide(new BigDecimal(10000));
                                temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
                                builder.write(temp.toString());
                            }
                            BigDecimal knowTotalPrice = getSchemeReimbursementKnowTotalPrice();
                            if (j == colMax - 2) {
                                builder.write(generateCommonMethod.getBigDecimalRound(knowTotalPrice, 2, true));
                            }
                            if (j == colMax - 1) {
                                BigDecimal mortgage = total.subtract(knowTotalPrice);
                                mortgage = mortgage.divide(new BigDecimal(10000));
                                mortgage = mortgage.setScale(2, BigDecimal.ROUND_HALF_UP);
                                builder.write(mortgage.toString());
                            }
                        }
                        builder.endRow();
                    } catch (Exception e) {
                        baseService.writeExceptionInfo(e, "估价结果一览表");
                    }
                }
                schemeJudgeObjectLinkedHashMap.clear();
            });
            //分组剩余的(大部分情况下不可能,但是不能排除意外嘛)
            if (CollectionUtils.isNotEmpty(objectList)) {
                for (SchemeJudgeObject schemeJudgeObject : objectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply != null) {
                        this.writeJudgeObjectResultSurveyInCell(basicApply, schemeJudgeObject, builder, doubleLinkedList, seat, mortgageFlag, schemeJudgeObjectList.size() <= 20);
                    }
                }
            }
        }
        //无他项权力的情况
        if (reimbursementItemVoListMap.isEmpty()) {
            if (!seat) {
                strings.remove(1);
            }
            if (!mortgageFlag) {
                strings.removeLast();
            }
            generateCommonMethod.writeWordTitle(builder, doubleLinkedList, strings);
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply != null) {
                    this.writeJudgeObjectResultSurveyInCell(basicApply, schemeJudgeObject, builder, doubleLinkedList, seat, mortgageFlag, schemeJudgeObjectList.size() <= 20);
                }
            }
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 司法估价结果一览表不含坐落 以及不含法定优先仓库按
     *
     * @return
     * @throws Exception
     */
    public String getjudgeBuildResultSurveySheet2() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        LinkedHashMap<BasicApply, SchemeJudgeObject> schemeJudgeObjectLinkedHashMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply != null && schemeJudgeObject.getDeclareRecordId() != null) {
                    schemeJudgeObjectLinkedHashMap.put(basicApply, schemeJudgeObject);
                }
            }
        }
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        boolean mortgageFlag = Objects.equal(projectInfo.getEntrustPurpose(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId());
        LinkedList<String> strings = Lists.newLinkedList(Lists.newArrayList("估价对象", "用途(证载)", "用途(实际)", "房屋总层数", "所在层数", "建筑面积㎡", "单价（元/㎡）", "评估总价（万元）", "抵押价值(万元)"));
        if (!mortgageFlag) {
            strings.removeLast();
        }
        LinkedList<Double> doubleLinkedList = Lists.newLinkedList(Lists.newArrayList(50d, 30d, 30d, 30d, 30d, 50d, 55d, 60d, 50d));
        if (!mortgageFlag) {
            doubleLinkedList.removeLast();
        }
        String localPath = getLocalPath();
        Table table = builder.startTable();
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        if (!schemeJudgeObjectLinkedHashMap.isEmpty()) {
            AsposeUtils.writeWordTitle(builder, doubleLinkedList, strings);
            for (Map.Entry<BasicApply, SchemeJudgeObject> objectEntry : schemeJudgeObjectLinkedHashMap.entrySet()) {
                writeJudgeObjectResultSurveyInCell2(objectEntry.getKey(), objectEntry.getValue(), builder, doubleLinkedList, false, false, mortgageFlag, schemeJudgeObjectList.size() <= 20);
            }
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    private void writeJudgeObjectResultSurveyInCell2(BasicApply basicApply, SchemeJudgeObject schemeJudgeObject, DocumentBuilder builder, LinkedList<Double> doubleLinkedList, boolean seat, boolean reimbursement, boolean mortgageFlag, boolean isLabelJudgeObjectShowName) throws Exception {
        LinkedList<String> linkedLists = Lists.newLinkedList();
        final String nullValue = "";
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        if (isLabelJudgeObjectShowName) {
            linkedLists.add(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject));
        } else {
            linkedLists.add(String.join("", schemeJudgeObject.getNumber(), "号"));
        }
        if (seat) {
            if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getSeat())) {//1
                linkedLists.add(declareRecord.getSeat());
            } else {
                linkedLists.add(nullValue);
            }
        }
        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getCertUse())) {//2
            linkedLists.add(declareRecord.getCertUse());
        } else {
            linkedLists.add(nullValue);
        }
        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getPracticalUse())) {//3
            linkedLists.add(declareRecord.getPracticalUse());
        } else {
            linkedLists.add(nullValue);
        }
        try {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            linkedLists.add(generateBaseExamineService.getBasicBuilding().getFloorCount().toString());//4
        } catch (Exception e) {
            linkedLists.add("0");
        }
        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getFloor())) {//5
            linkedLists.add(declareRecord.getFloor());
        } else {
            linkedLists.add(nullValue);
        }
        if (declareRecord != null && declareRecord.getFloorArea() != null) {//6
            linkedLists.add(declareRecord.getFloorArea().toString());
        } else {
            linkedLists.add(nullValue);
        }
        if (schemeJudgeObject != null && schemeJudgeObject.getPrice() != null) {//7
            linkedLists.add(schemeJudgeObject.getPrice().toString());
        } else {
            linkedLists.add(nullValue);
        }
        if (schemeJudgeObject.getPrice() != null && declareRecord.getFloorArea() != null) {//8
            BigDecimal total = schemeJudgeObject.getPrice().multiply(declareRecord.getFloorArea());
            total = total.divide(new BigDecimal(10000));
            total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
            linkedLists.add(total.toString());
        } else {
            linkedLists.add(nullValue);
        }

        BigDecimal knowTotalPrice = getSchemeReimbursementKnowTotalPrice();
        if (mortgageFlag) {
            linkedLists.add(generateCommonMethod.getBigDecimalRound(knowTotalPrice, 2, true));//9
        }
        //抵押=总价-法定
        if (reimbursement) {
            if (schemeJudgeObject.getPrice() != null && declareRecord.getFloorArea() != null) {
                BigDecimal totol = schemeJudgeObject.getPrice().multiply(declareRecord.getFloorArea());
                BigDecimal mortgage = totol.subtract(knowTotalPrice);
                mortgage = mortgage.divide(new BigDecimal(10000));
                mortgage = mortgage.setScale(2, BigDecimal.ROUND_HALF_UP);
                linkedLists.add(mortgage.toString());//10
            } else {
                linkedLists.add(nullValue);
            }
        }
        generateCommonMethod.writeWordTitle(builder, doubleLinkedList, linkedLists);
    }

    /**
     * 估价结果一览表 (子表)
     *
     * @param basicApply
     * @param schemeJudgeObject
     * @param builder
     * @param seat
     * @throws Exception
     */

    private void writeJudgeObjectResultSurveyInCell(BasicApply basicApply, SchemeJudgeObject schemeJudgeObject, DocumentBuilder builder, LinkedList<Double> doubleLinkedList, boolean seat, boolean mortgageFlag, boolean isLabelJudgeObjectShowName) throws Exception {
        writeJudgeObjectResultSurveyInCell2(basicApply, schemeJudgeObject, builder, doubleLinkedList, seat, mortgageFlag, mortgageFlag, isLabelJudgeObjectShowName);
    }

    /**
     * 获取其它  某些字段数据
     *
     * @param reportFieldEnum
     * @return
     * @throws Exception
     */
    public String getJudgeObjectOtherFieldValue(BaseReportFieldEnum reportFieldEnum) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        String value = "/";
        inner:
        switch (reportFieldEnum) {
            case JudgeObjectOtherField1: {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null || basicApply.getId() == null) {
                        continue;
                    }
                    String text = generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.SCENERY);
                    if (StringUtils.isNotEmpty(text)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), text);
                    }
                }
            }
            break;
            case JudgeObjectOtherField2: {
                String text = generateLoactionService.getFaceStreet(schemeJudgeObjectList);
                if (StringUtils.isNotBlank(text)) {
                    value = text;
                }
            }
            break;
            case JudgeObjectOtherField3: {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicBuildingVo basicBuildingVo = generateBaseExamineService.getBasicBuilding();
                    if (basicBuildingVo == null) {
                        continue;
                    }
                    if (StringUtils.isEmpty(basicBuildingVo.getBetweenDistanceName())) {
                        continue;
                    }
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicBuildingVo.getBetweenDistanceName());
                }
            }
            break;
            case JudgeObjectOtherField4: {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicEstateVo basicEstateVo = generateBaseExamineService.getEstate();
                    if (basicEstateVo == null || StringUtils.isEmpty(basicEstateVo.getGreeningRate())) {
                        continue;
                    }
                    if (NumberUtils.isNumber(basicEstateVo.getGreeningRate())) {
                        String text = generateCommonMethod.getPercentileSystem(new BigDecimal(basicEstateVo.getGreeningRate()));
                        if (StringUtils.isNotEmpty(text)) {
                            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), text);
                        }
                    } else {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicEstateVo.getGreeningRate());
                    }
                }
            }
            break;
            case JudgeObjectOtherField5: {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
                    if (landStateVo == null) {
                        continue;
                    }
                    if (StringUtils.isEmpty(landStateVo.getBuildingDensity())) {
                        landStateVo.setBuildingDensity("符合规范");
                    }
                    if (NumberUtils.isNumber(landStateVo.getBuildingDensity())) {
                        String text = generateCommonMethod.getPercentileSystem(new BigDecimal(landStateVo.getBuildingDensity()));
                        if (StringUtils.isNotEmpty(text)) {
                            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), text);
                        }
                    } else {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), landStateVo.getBuildingDensity());
                    }
                }
            }
            break;
            case JudgeObjectOtherField6: {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null) {
                        continue;
                    }
                    String text = generateLoactionService.getParkingConvenience(basicApply);
                    if (StringUtils.isNotEmpty(text)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), text);
                    }
                }
            }
            break;
            case JudgeObjectOtherField7: {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicBuildingVo basicBuilding = generateBaseExamineService.getBasicBuilding();
                    if (basicBuilding == null) {
                        continue;
                    }
                    DataPropertyVo dataPropertyVo = basicBuilding.getDataProperty();
                    if (dataPropertyVo == null) {
                        continue;
                    }
                    if (StringUtils.isNotEmpty(dataPropertyVo.getName()) && dataPropertyVo.getCompanyNature() != null && dataPropertyVo.getSocialPrestige() != null) {
                        LinkedList<String> stringLinkedList = Lists.newLinkedList();
                        stringLinkedList.add(dataPropertyVo.getName());
                        stringLinkedList.add("公司性质");
                        stringLinkedList.add(dataPropertyVo.getCompanyNatureName());
                        stringLinkedList.add("社会信誉");
                        stringLinkedList.add(dataPropertyVo.getSocialPrestigeName());
                        String text = StringUtils.join(stringLinkedList, ",");
                        if (StringUtils.isNotEmpty(text)) {
                            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), text);
                        }
                    }
                }
                if (map.isEmpty()) {
                    value = "专业物管";
                }
            }
            break;
            case JudgeObjectOtherField8: {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicHouseVo basicHouseVo = generateBaseExamineService.getBasicHouse();
                    if (basicHouseVo == null) {
                        continue;
                    }
                    if (StringUtils.isEmpty(basicHouseVo.getNewDegree())) {
                        continue;
                    }
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicHouseVo.getNewDegree());
                }
            }
            break;
            default:
                break;
        }
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 厌恶设施
     *
     * @return
     * @throws Exception
     */
    public String getAversionFacility() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        String value = "/";
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList = generateBaseExamineService.getBasicMatchingEnvironmentList();
            if (CollectionUtils.isEmpty(basicMatchingEnvironmentVoList)) {
                continue;
            }
            List<String> filters = Arrays.asList("影响较大", "有重大影响");
            basicMatchingEnvironmentVoList.forEach(oo -> {
                BaseDataDic key = baseDataDicService.getCacheDataDicByFieldName(EnvironmentalScienceEnum.NATURAL.getKey());
                int i = 0;
                if (key != null) {
                    if (Objects.equal(key.getId(), oo.getType())) {
                        i++;
                    }
                }
                if (filters.contains(oo.getInfluenceDegreeName())) {
                    i++;
                }
                if (i == 2) {
                    stringSet.add(oo.getRemark());
                }
            });
            if (CollectionUtils.isNotEmpty(stringSet)) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(stringSet, "、"));
            }
            stringSet.clear();
        }
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }


    /**
     * 维护保养状况
     *
     * @return
     * @throws Exception
     */
    public String getJudgeObjectDamagedDegreeFieldValue() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        String value = "/";
        String text = generateHouseEntityService.getDamagedDegreeBase(schemeJudgeObjectList, false);
        if (StringUtils.isNotEmpty(text)) {
            value = text;
        }
        return value;
    }

    /**
     * 获取房屋的装修情况
     *
     * @param reportFieldEnum
     * @return
     */
    public String getJudgeObjectDamagedDegreeFieldB(BaseReportFieldEnum reportFieldEnum) throws Exception {
        String name = null;
        switch (reportFieldEnum) {
            case JudgeObjectDamagedDegreeField6: {
                name = "卫生间";
            }
            break;
            case JudgeObjectDamagedDegreeField7: {
                name = "厨房";
            }
            break;
            default:
                break;
        }
        String nameValue = name;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseRoom> basicHouseRoomList = generateBaseExamineService.getBasicHouseRoomList();
            StringBuilder stringBuilder = new StringBuilder(8);
            if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
                for (BasicHouseRoom basicHouseRoom : basicHouseRoomList) {
                    if (StringUtils.isNotBlank(basicHouseRoom.getRoomType())) {
                        if (basicHouseRoom.getRoomType().contains(nameValue)) {
                            List<BasicHouseRoomDecorateVo> basicHouseRoomDecorateVos = generateBaseExamineService.getBasicHouseRoomDecorateList(basicHouseRoom.getId());
                            if (CollectionUtils.isNotEmpty(basicHouseRoomDecorateVos)) {
                                basicHouseRoomDecorateVos.forEach(oo -> {
                                    stringBuilder.append(oo.getPartName());
                                    if (StringUtils.isNotEmpty(oo.getRemark())) {
                                        stringBuilder.append(oo.getRemark());
                                    } else if (StringUtils.isNotEmpty(oo.getMaterialName())) {
                                        stringBuilder.append(oo.getMaterialName());
                                    }
                                    stringBuilder.append(",");
                                });
                            }
                        }
                    }
                }
            }
            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.strip(stringBuilder.toString()));
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", true);
        }
        return value;
    }

    public String getJudgeObjectDamagedDegreeFieldA(BaseReportFieldEnum reportFieldEnum) throws Exception {
        String name = null;
        switch (reportFieldEnum) {
            case JudgeObjectDamagedDegreeField1: {
                name = "门";
            }
            break;
            case JudgeObjectDamagedDegreeField2: {
                name = "窗";
            }
            break;
            case JudgeObjectDamagedDegreeField3: {
                name = "地面";
            }
            break;
            case JudgeObjectDamagedDegreeField4: {
                name = "墙";
            }
            break;
            case JudgeObjectDamagedDegreeField5: {
                name = "天棚";
            }
            break;
            default:
                break;
        }
        String nameValue = name;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            Set<String> stringSet = Sets.newHashSet();
            StringBuilder stringBuilder = new StringBuilder(8);
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseRoom> basicHouseRoomList = generateBaseExamineService.getBasicHouseRoomList();
            if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
                for (BasicHouseRoom basicHouseRoom : basicHouseRoomList) {
                    StringBuilder stringBuilderDecorate = new StringBuilder(8);
                    if (StringUtils.isNotBlank(basicHouseRoom.getRoomType())) {
                        List<BasicHouseRoomDecorateVo> basicHouseRoomDecorateVos = generateBaseExamineService.getBasicHouseRoomDecorateList(basicHouseRoom.getId());
                        if (CollectionUtils.isNotEmpty(basicHouseRoomDecorateVos)) {
                            basicHouseRoomDecorateVos.forEach(oo -> {
                                if (StringUtils.contains(oo.getPartName(), nameValue) && !StringUtils.contains(oo.getPartName(), "卫生间") && !StringUtils.contains(oo.getPartName(), "厨房")) {
                                    if (StringUtils.isNotEmpty(oo.getRemark())) {
                                        stringBuilderDecorate.append(oo.getRemark());
                                    } else {
                                        if (StringUtils.isNotEmpty(oo.getMaterialName())) {
                                            stringBuilderDecorate.append(oo.getMaterialName());
                                        }
                                    }
                                }

                            });
                        }
                    }
                    if (stringBuilderDecorate.length() > 0) {
                        stringBuilder.append(basicHouseRoom.getRoomType()).append(stringBuilderDecorate.toString()).append(",");
                    }
                }
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                stringSet.add(StringUtils.strip(stringBuilder.toString(), ","));
            }
            stringBuilder.delete(0, stringBuilder.toString().length());
            if (CollectionUtils.isNotEmpty(stringSet)) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(stringSet, "、"));
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", true);
        }
        return value;
    }

    /**
     * 获取某些区位信息字段
     *
     * @param reportFieldEnum
     * @return
     * @throws Exception
     */
    public String getJudgeObjectLocationValue(BaseReportFieldEnum reportFieldEnum) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        LinkedList<String> stringLinkedList = Lists.newLinkedList();
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateVo basicEstateVo = generateBaseExamineService.getEstate();
            BasicHouseVo basicHouseVo = generateBaseExamineService.getBasicHouse();
            BasicEstateLandStateVo basicEstateLandStateVo = generateBaseExamineService.getBasicEstateLandState();
            switch (reportFieldEnum) {
                case JudgeObjectLoactionField1:
                    if (StringUtils.isNotEmpty(declareRecord.getSeat())) {
                        stringBuilder.append("估价对象位于");
                        stringBuilder.append(basicEstateVo.getCityName()).append(basicEstateVo.getDistrictName()).append(declareRecord.getSeat());
                        stringBuilder.append(",").append("地处").append(basicEstateVo.getCityName()).append(basicEstateVo.getDistrictName());
                    }
                    if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                        stringLinkedList.add(stringBuilder.toString());
                    }
                    stringBuilder.delete(0, stringBuilder.toString().length());
                    if (StringUtils.isNotEmpty(basicHouseVo.getFloor())) {
                        stringBuilder.append("估价对象位于所在建筑物的第").append(basicHouseVo.getFloor()).append("层").append(",");
                    }
                    if (basicHouseVo.getOrientation() != null) {
                        stringBuilder.append(basicHouseVo.getOrientationName()).append("朝向");
                    }
                    if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                        stringLinkedList.add(stringBuilder.toString());
                    }
                    stringBuilder.delete(0, stringBuilder.toString().length());
                    Map<String, String> stringMap = Maps.newHashMap();
                    if (StringUtils.isNotEmpty(basicEstateLandStateVo.getSouthTo())) {
                        stringMap.put("南临", basicEstateLandStateVo.getSouthTo());
                    }
                    if (StringUtils.isNotEmpty(basicEstateLandStateVo.getNorthTo())) {
                        stringMap.put("北临", basicEstateLandStateVo.getNorthTo());
                    }
                    if (StringUtils.isNotEmpty(basicEstateLandStateVo.getWestTo())) {
                        stringMap.put("西临", basicEstateLandStateVo.getWestTo());
                    }
                    if (StringUtils.isNotEmpty(basicEstateLandStateVo.getEastTo())) {
                        stringMap.put("东临", basicEstateLandStateVo.getEastTo());
                    }
                    if (!stringMap.isEmpty()) {
                        stringBuilder.append("估价对象所在宗地");
                        List<String> stringList = Lists.newArrayList();
                        stringMap.entrySet().forEach(stringStringEntry -> {
                            stringList.add(String.format("%s%s", stringStringEntry.getKey(), stringStringEntry.getValue()));
                        });
                        stringBuilder.append(StringUtils.join(stringList, ","));
                        if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                            stringLinkedList.add(stringBuilder.toString());
                        }
                        stringBuilder.delete(0, stringBuilder.toString().length());
                    }
                    if (CollectionUtils.isNotEmpty(stringLinkedList)) {
                        String value = StringUtils.join(stringLinkedList, "。");
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                    stringLinkedList.clear();
                    break;
                case JudgeObjectLoactionField2: {
                    List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
                    String value = generateLoactionService.getMatchingLeisurePlacePrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET, "区域内", false);
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case JudgeObjectLoactionField3: {
                    List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
                    String value = generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.TRANSIT, "", false);
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case JudgeObjectLoactionField4: {
                    List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
                    String value = generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainRoad, "区域内", false);
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case JudgeObjectLoactionField5: {
                    List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
                    String value = generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.METRO, "区域内", false);
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case JudgeObjectLoactionField6: {
                    if (StringUtils.isNotBlank(basicEstateVo.getInfrastructureName())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicEstateVo.getInfrastructureName());
                    }
//                    if (StringUtils.isNotEmpty(basicEstateLandStateVo.getDevelopmentDegreeContentName())) {
//                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicEstateLandStateVo.getDevelopmentDegreeContentName());
//                    }
                }
                break;
                case JudgeObjectLoactionField6B: {
                    if (StringUtils.isNotBlank(basicEstateVo.getInfrastructureName())) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicEstateVo.getInfrastructureName());
                    }
                }
                break;
                case JudgeObjectLoactionField7: {
                    List<BasicMatchingEducation> basicMatchingEducationList = generateBaseExamineService.getBasicMatchingEducatioListn();
                    String value = generateLoactionService.getFinanceAndMedicalAndEducation(null, null,
                            basicMatchingEducationList, "区域内");
                    if (StringUtils.isNotEmpty(value)) {
                        value = String.format("%s%s", value, "教育条件较好。");
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case JudgeObjectLoactionField8: {
                    List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
                    String value = generateLoactionService.getFinanceAndMedicalAndEducation(null, basicMatchingMedicalList,
                            null, "区域内");
                    if (StringUtils.isNotEmpty(value)) {
                        value = String.format("%s%s", value, "医疗条件较好。");
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case JudgeObjectLoactionField9: {
                    String value = generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.NATURAL);
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                case JudgeObjectLoactionField10: {
                    String value = generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.HUMANITY);
                    if (StringUtils.isNotEmpty(value)) {
                        map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), value);
                    }
                }
                break;
                default:
                    break;
            }
        }
        String value = "/";
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }


    /**
     * 估价对象区位状况表
     */
    public String getJudgeObjectAreaStatusSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();

        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> linkedHashMap = generateCommonMethod.getEstateGroupByAreaId(areaId);
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : linkedHashMap.entrySet()) {
                if (CollectionUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                List<Integer> judgeObjectIds = Lists.newArrayList(entry.getValue().stream().map(oo -> oo.getId()).collect(Collectors.toList()));
                if (CollectionUtils.isEmpty(judgeObjectIds)) {
                    continue;
                }
                BasicEstate basicEstate = entry.getKey();
                BasicApply basicApply = basicApplyService.getByBasicApplyByEstateId(basicEstate.getId());
                List<SchemeJudgeObject> judgeObjects = entry.getValue();
                if (linkedHashMap.size() > 1)
                    documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml("<div style='text-align:center;;font-size:16.0pt;'>" + basicEstate.getName() + "</div>"), true);
                StringBuilder stringBuilder = new StringBuilder(8);
                stringBuilder.append(generateCommonMethod.getIndentHtml("1、位置状况"));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("坐落:%s", generateCommonMethod.trim(generateLoactionService.getSeat(basicEstate, judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("方位:%s", generateCommonMethod.trim(generateLoactionService.getPosition(basicEstate)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("与重要场所的距离:%s", generateCommonMethod.trim(generateLoactionService.getWithImportantLocationDistance(basicApply)))));
                String faceStreet = generateLoactionService.getFaceStreet(judgeObjects);
                if (StringUtils.isNotBlank(faceStreet.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("临街（路）状况:%s", generateCommonMethod.trim(faceStreet))));
                }
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("楼层:%s", generateCommonMethod.trim(generateLoactionService.getFloor(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("朝向:%s", generateCommonMethod.trim(generateLoactionService.getOrientation(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml("2、交通状况包括"));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("道路状况:%s", generateCommonMethod.trim(generateLoactionService.getRoadCondition(judgeObjects)))));

                String transport = generateLoactionService.getAccessAvailableMeansTransport(basicApply);
                if (StringUtils.isNotEmpty(transport)) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("出入可利用的交通工具:%s", generateCommonMethod.trim(transport))));
                }

                String trafficControl = generateLoactionService.getTrafficControl(basicApply);
                if (StringUtils.isNotEmpty(trafficControl)) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("交通管制情况:%s", generateCommonMethod.trim(trafficControl))));
                }

                String parkingConvenience = generateLoactionService.getParkingConvenience(basicApply);
                if (StringUtils.isNotBlank(parkingConvenience))
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("停车方便度:%s", generateCommonMethod.trim(parkingConvenience))));

                String trafficCharges = generateLoactionService.getTrafficCharges(basicApply);
                if (StringUtils.isNotBlank(trafficCharges)) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("交通收费情况:%s", generateCommonMethod.trim(trafficCharges))));
                }

                stringBuilder.append(generateCommonMethod.getIndentHtml("3、外部基础设施"));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s", generateCommonMethod.trim(generateLoactionService.getExternalInfrastructure(basicApply)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml("4、外部公共服务设施"));
                List<String> stringArrayList = generateLoactionService.getExternalPublicServiceFacilities(basicApply, true);
                if (CollectionUtils.isNotEmpty(stringArrayList)) {
                    stringArrayList.stream().forEach(s -> stringBuilder.append(generateCommonMethod.getIndentHtml(s)));
                }
                stringBuilder.append(generateCommonMethod.getIndentHtml("5、周围环境"));
                String natural = generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.NATURAL);
                String humanity = generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.HUMANITY);
                String scenery = generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.SCENERY);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("自然要素:%s", generateCommonMethod.trim(natural))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("人文环境要素:%s", generateCommonMethod.trim(humanity))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("景观:%s", generateCommonMethod.trim(scenery))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("综述:%s", generateCommonMethod.trim(basicEstate.getLocationDescribe()))));
                documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), false);
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价土地实体状况表
     */
    public String getJudgeObjectLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        Map<BasicEstate, List<SchemeJudgeObject>> map = generateCommonMethod.getEstateGroupByAreaId(areaId);
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> schemeJudgeObjectEntry : map.entrySet()) {
            BasicEstate basicEstate = schemeJudgeObjectEntry.getKey();
            BasicApply basicApply = basicApplyService.getByBasicApplyByEstateId(basicEstate.getId());
            StringBuilder stringBuilder = new StringBuilder();
            if (map.size() > 1)
                documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml("<div style='text-align:center;;font-size:16.0pt;'>" + basicEstate.getName() + "</div>"), true);
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
            int index = 0;
            {
                String s = generateLandEntityService.getLandName(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、名称:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.fourTheFor(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、四至:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.getLandArea(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、土地面积:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.getLandUse(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、用途:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.getShapeState(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、形状:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.getTopographicTerrain(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、地势:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.getSoil(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、土壤与地质:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = baseDataDicService.getNameById(basicEstate.getInfrastructureCompleteness());
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、基础设施完备度:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.getDevelopmentDegree(landStateVo);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%d、开发程度:%s", ++index, generateCommonMethod.trim(s))));
                }
            }
            {
                String s = generateLandEntityService.getContent(basicApply);
                if (StringUtils.isNotBlank(s.trim())) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("综上所述:%s", generateCommonMethod.trim(s))));
                }
            }
            documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象建筑实体状况表
     */
    public String getJudgeBuildLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        Map<BasicEstate, List<SchemeJudgeObject>> linkedHashMap = generateCommonMethod.getEstateGroupByAreaId(areaId);
        LinkedHashMap<String, String> stringLinkedHashMap = Maps.newLinkedHashMap();
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> listEntry : linkedHashMap.entrySet()) {
                BasicEstate basicEstate = listEntry.getKey();
                List<SchemeJudgeObject> judgeObjects = listEntry.getValue();
                if (linkedHashMap.size() > 1)
                    documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml("<div style='text-align:center;;font-size:16.0pt;'>" + basicEstate.getName() + "</div>"), true);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("1、楼盘名称:%s", generateCommonMethod.trim(basicEstate.getName()))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("2、建筑年份:%s", generateCommonMethod.trim(generateHouseEntityService.getBuildingYear(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("3、工程质量:%s", generateCommonMethod.trim(generateHouseEntityService.getConstructionQuality(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("4、建筑结构:%s", generateCommonMethod.trim(generateHouseEntityService.getBuildingStructure(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("5、建筑规模:%s", generateCommonMethod.trim(generateHouseEntityService.getBuildingScale(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("6、层高:%s", generateCommonMethod.trim(generateHouseEntityService.getFloorHeight(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("7、空间布局:%s", generateCommonMethod.trim(generateHouseEntityService.getSpatialDistribution(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("8、装饰装修:%s", generateCommonMethod.trim(generateHouseEntityService.getDecoration(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("9、外观:%s", generateCommonMethod.trim(generateHouseEntityService.getAppearance(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml("10、设施设备"));
                String unitElevator = generateCommonMethod.trim(generateHouseEntityService.getUnitElevator(judgeObjects));
                if (StringUtils.isNotEmpty(unitElevator))
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("电梯:%s", unitElevator)));
                {
                    stringLinkedHashMap.put(generateHouseEntityService.getHouseEquipment(judgeObjects, ExamineHouseEquipmentTypeEnum.houseAirConditioner), "空调:");
                    stringLinkedHashMap.put(generateHouseEntityService.getHouseEquipment(judgeObjects, ExamineHouseEquipmentTypeEnum.houseNewWind), "新风:");
                    stringLinkedHashMap.put(generateHouseEntityService.getHouseEquipment(judgeObjects, ExamineHouseEquipmentTypeEnum.houseHeating), "新风:");
                    stringLinkedHashMap.put(generateHouseEntityService.getIntelligent(judgeObjects), "电力通讯网络:");
                    stringLinkedHashMap.put(generateHouseEntityService.getHouseWater(judgeObjects), "供水:");
                    stringLinkedHashMap.put(generateHouseEntityService.getHouseWaterDrain(judgeObjects), "排水:");
                    if (!stringLinkedHashMap.isEmpty()) {
                        stringLinkedHashMap.entrySet().stream().forEach(entry -> {
                            if (StringUtils.isNotBlank(entry.getKey().trim())) {
                                stringBuilder.append(generateCommonMethod.getIndentHtml((String.format("%s%s", entry.getValue(), generateCommonMethod.trim(entry.getKey())))));
                            }
                        });
                        stringLinkedHashMap.clear();
                    }
                }
                String matchingEquipment = generateHouseEntityService.getMatchingEquipment(judgeObjects);
                if (StringUtils.isNotBlank(matchingEquipment)) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("房屋配套设备设施工:%s", generateCommonMethod.trim(matchingEquipment))));
                }
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("11、建筑功能:%s", generateCommonMethod.trim(generateHouseEntityService.getBuildingFunction(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("12、新旧程度及维护使用情况")));
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateHouseEntityService.getDamagedDegree(judgeObjects)));

                String stringOther = generateHouseEntityService.getOther(judgeObjects);
                if (StringUtils.isNotBlank(stringOther))
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("13、其它:%s", generateCommonMethod.trim(stringOther))));

                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("建筑实体分析:%s", generateCommonMethod.trim(generateHouseEntityService.getBuildEntityAnalysis(judgeObjects, schemeAreaGroup)))));
                documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 户型及布局
     *
     * @return
     * @throws Exception
     */
    public String getHuxingLayout() throws Exception {
        String s = generateCommonMethod.trim(generateHouseEntityService.getSpatialDistribution(getSchemeJudgeObjectList()));
        return StringUtils.isNotBlank(s) ? s : "/";
    }

    /**
     * 建行预评数据表格 复杂word
     *
     * @return
     * @throws Exception
     */
    public String getCCB_Pre_Evaluation_Data_FormSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(documentBuilder);
        String localPath = getLocalPath();
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = Lists.newArrayList();
        schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId).forEach(schemeJudgeObject -> {
            if (schemeJudgeObject.getDeclareRecordId() != null) {
                schemeJudgeObjectList.add(schemeJudgeObject);
            }
        });
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            generateCommonMethod.getSortSchemeJudgeObject(schemeJudgeObjectList);
        }
        List<SchemeLiquidationAnalysisItem> liquidationAnalysisItemList = schemeLiquidationAnalysisService.getAnalysisItemListByAreaId(areaId);
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
            String documentPath = getCCB_Pre_Evaluation_Data_FormWriteWord(schemeJudgeObject, schemeJudgeObjectList, liquidationAnalysisItemList, schemeJudgeObjectList.size(), i);
            if (StringUtils.isNotEmpty(documentPath)) {
                String title = generateCommonMethod.getWarpCssHtml("<div style='text-align:center;font-size:16.0pt;'>" + generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject) + "</div>");
                map.put(title, documentPath);
            }
        }
        if (!map.isEmpty()) {
            if (map.size() == 1) {
                LinkedHashMap<String, String> stringMap = Maps.newLinkedHashMap();
                stringMap.put("", map.entrySet().stream().findFirst().get().getValue());
                AsposeUtils.insertBreakDocumentHandle(stringMap, localPath, "下一页", "");
            } else {
                AsposeUtils.insertBreakDocumentHandle(map, localPath, "下一页", "");
            }
        }
        File file = new File(localPath);
        if (file.isFile()) {
            doc = new Document(localPath);
            //删除空白
            doc.getMailMerge().deleteFields();
        } else {
            doc.save(localPath);
        }
        return localPath;
    }

    private void ccb_Pre_Evaluation_Data_FormWriteWord2(DocumentBuilder documentBuilder, LinkedList<String> stringLinkedList, String title, String valueB) throws Exception {
        stringLinkedList.clear();
        if (StringUtils.isNotEmpty(title)) {
            stringLinkedList.add(title);
        } else {
            stringLinkedList.add(" ");
        }
        if (StringUtils.isNotEmpty(valueB)) {
            stringLinkedList.add(valueB);
        } else {
            stringLinkedList.add(" ");
        }
        AsposeUtils.writeWordTitle(documentBuilder, stringLinkedList);
        stringLinkedList.clear();
    }

    /**
     * 建社银行预评数据表格
     *
     * @param schemeJudgeObject
     * @return
     * @throws Exception
     */
    private synchronized String getCCB_Pre_Evaluation_Data_FormWriteWord(SchemeJudgeObject schemeJudgeObject, List<SchemeJudgeObject> schemeJudgeObjectList, List<SchemeLiquidationAnalysisItem> liquidationAnalysisItemList, int size, int i) throws Exception {
        String localPath = getLocalPath();
        Map<SchemeJudgeObject, KeyValueDto> keyValueDtoMap = getAssessAssessTotalData();
        BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
        LinkedList<String> stringLinkedList = Lists.newLinkedList();
        if (basicApply != null && basicApply.getId() != null && basicApply.getId() != 0) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateVo basicEstate = generateBaseExamineService.getEstate();
            BasicBuildingVo basicBuildingVo = generateBaseExamineService.getBasicBuilding();
            Document doc = new Document();
            DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
            generateCommonMethod.settingBuildingTable(documentBuilder);
            documentBuilder.getFont().setName("宋体");
            documentBuilder.getFont().setSize(12);
            String huxingName = generateBaseExamineService.getBasicHouse().getHuxingName();
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            documentBuilder.startTable();
            //start
            {
                String val = "";
                if (StringUtils.isNotEmpty(declareRecord.getSeat())) {
                    if (StringUtils.isNotEmpty(declareRecord.getStreetNumber())) {
                        val = StringUtils.remove(declareRecord.getSeat(), declareRecord.getStreetNumber());
                    } else {
                        val = declareRecord.getSeat();
                    }
                    val = String.format("%s%s", basicEstate.getName(), val);
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "项目名称", val);
            }

            List<String> cityList = Arrays.asList("凉山");
            if (cityList.stream().anyMatch(s -> StringUtils.contains(basicEstate.getCityName(), s))) {
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "城市", basicEstate.getDistrictName());
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "行政区", basicEstate.getCityName());
            } else {
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "城市", basicEstate.getCityName());
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "行政区", basicEstate.getDistrictName());
            }
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "楼盘", basicEstate.getName());
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "楼栋号", basicBuildingVo.getBuildingNumber());
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "单元号", generateBaseExamineService.getBasicUnit().getUnitNumber());
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "户号", declareRecord.getRoomNumber());
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "产权证号", declareRecord.getName());
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "证载地址", declareRecord.getSeat());
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "房屋证载用途", declareRecord.getCertUse());
            {
                String val = "";
                if (declareRecord != null && declareRecord.getFloorArea() != null) {
                    if (generateCommonMethod.isInteger(declareRecord.getFloorArea())) {
                        val = generateCommonMethod.getBigDecimalRound(declareRecord.getFloorArea(), 0, false);
                    } else {
                        val = generateCommonMethod.getBigDecimalRound(declareRecord.getFloorArea(), 2, false);
                    }
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "建筑面积(㎡)", val);
            }
            {
                String val = "";
                if (!keyValueDtoMap.isEmpty()) {
                    for (Map.Entry<SchemeJudgeObject, KeyValueDto> schemeJudgeObjectKeyValueDtoEntry : keyValueDtoMap.entrySet()) {
                        if (Objects.equal(schemeJudgeObjectKeyValueDtoEntry.getKey().getId(), schemeJudgeObject.getId())) {
                            KeyValueDto keyValueDto = schemeJudgeObjectKeyValueDtoEntry.getValue();
                            val = ArithmeticUtils.mul(keyValueDto.getKey(), keyValueDto.getValue(), 10);
                            val = generateCommonMethod.getBigDecimalToInteger(ArithmeticUtils.createBigDecimal(val), 100);
                        }
                    }
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "登记价(元)", val);
            }
            {
                String val = "";
                if (basicBuildingVo != null && basicBuildingVo.getBeCompletedTime() != null) {
                    val = DateUtils.format(basicBuildingVo.getBeCompletedTime(), DateUtils.DATE_SHORT_PATTERN);
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "竣工日期", val);
            }
            String v1 = "室";
            String v2 = "厅";
            String v3 = "厨";
            String v4 = "卫";
            LinkedHashMap<String, String> linkedHashMap = Maps.newLinkedHashMap();
            if (StringUtils.isNotEmpty(huxingName)) {
                if (org.apache.commons.lang.StringUtils.contains(huxingName, v1)) {
                    linkedHashMap.put(v1, org.apache.commons.lang.StringUtils.substringBefore(huxingName, v1));
                }
                Pattern pattern = Pattern.compile(String.format("\\d%s", v2));
                Matcher matcher = pattern.matcher(huxingName);
                if (matcher.find()) {
                    String s = generateCommonMethod.getNumber(matcher.group());
                    if (StringUtils.isNotEmpty(s)) {
                        linkedHashMap.put(v2, s);
                    }
                }
                pattern = Pattern.compile(String.format("\\d%s", v3));
                matcher = pattern.matcher(huxingName);
                if (matcher.find()) {
                    String s = generateCommonMethod.getNumber(matcher.group());
                    if (StringUtils.isNotEmpty(s)) {
                        linkedHashMap.put(v3, s);
                    }
                }
                pattern = Pattern.compile(String.format("\\d%s", v4));
                matcher = pattern.matcher(huxingName);
                if (matcher.find()) {
                    String s = generateCommonMethod.getNumber(matcher.group());
                    if (StringUtils.isNotEmpty(s)) {
                        linkedHashMap.put(v4, s);
                    }
                }
            }
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "居室", StringUtils.isNotBlank(linkedHashMap.get(v1)) ? linkedHashMap.get(v1) : "");
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "厅", StringUtils.isNotBlank(linkedHashMap.get(v2)) ? linkedHashMap.get(v2) : "");
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "厨房", StringUtils.isNotBlank(linkedHashMap.get(v3)) ? linkedHashMap.get(v3) : "");
            ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "卫生间", StringUtils.isNotBlank(linkedHashMap.get(v4)) ? linkedHashMap.get(v4) : "");
            {
                String val = "";
                if (!keyValueDtoMap.isEmpty()) {
                    for (Map.Entry<SchemeJudgeObject, KeyValueDto> schemeJudgeObjectKeyValueDtoEntry : keyValueDtoMap.entrySet()) {
                        if (Objects.equal(schemeJudgeObjectKeyValueDtoEntry.getKey().getId(), schemeJudgeObject.getId())) {
                            val = schemeJudgeObjectKeyValueDtoEntry.getValue().getValue();
                        }
                    }
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "抵押价值单价(元/㎡)", val);
            }

            {
                String val = "";
                if (!keyValueDtoMap.isEmpty()) {
                    for (Map.Entry<SchemeJudgeObject, KeyValueDto> schemeJudgeObjectKeyValueDtoEntry : keyValueDtoMap.entrySet()) {
                        if (Objects.equal(schemeJudgeObjectKeyValueDtoEntry.getKey().getId(), schemeJudgeObject.getId())) {
                            KeyValueDto keyValueDto = schemeJudgeObjectKeyValueDtoEntry.getValue();
                            val = ArithmeticUtils.mul(keyValueDto.getKey(), keyValueDto.getValue(), 10);
                            val = generateCommonMethod.getBigDecimalToInteger(ArithmeticUtils.createBigDecimal(val), 100);
                        }
                    }
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "抵押价值(元)", val);
            }

            {
                String val = "";
                try {
                    val = getNetAssessmentNumber2(BaseReportFieldEnum.NetAssessmentOne, liquidationAnalysisItemList, schemeJudgeObjectList, schemeJudgeObject, 10);
                } catch (Exception e) {
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "抵押净值1(元)", val);
            }
            {
                String val = "";
                try {
                    val = getNetAssessmentNumber2(BaseReportFieldEnum.NetAssessmentTwo, liquidationAnalysisItemList, schemeJudgeObjectList, schemeJudgeObject, 10);
                } catch (Exception e) {
                }
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "抵押净值2(元)", val);
            }
            //最新添加的三行
            {
                String val = "--";
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "商铺类型", val);
            }
            {
                String val = "--";
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "商铺租金(/月)", val);
            }
            {
                ccb_Pre_Evaluation_Data_FormWriteWord2(documentBuilder, stringLinkedList, "房屋用途", declareRecord.getCertUse());
            }
            documentBuilder.endTable();
            if (i != size - 1) {
                if (size != 1) {
                    documentBuilder.writeln();
                    documentBuilder.insertHtml(generateCommonMethod.getSongWarpCssHtml2("下一页"), false);
                }
            }
            doc.save(localPath, SaveFormat.DOC);
            return localPath;
        }
        return null;
    }

    /**
     * 评估净值
     *
     * @param reportFieldEnum
     * @return
     */
    public String getNetAssessmentNumber(BaseReportFieldEnum reportFieldEnum) {
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = Lists.newArrayList();
        schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId).forEach(schemeJudgeObject -> {
            if (schemeJudgeObject.getDeclareRecordId() != null) {
                schemeJudgeObjectList.add(schemeJudgeObject);
            }
        });
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            generateCommonMethod.getSortSchemeJudgeObject(schemeJudgeObjectList);
        }
        List<SchemeLiquidationAnalysisItem> liquidationAnalysisItemList = schemeLiquidationAnalysisService.getAnalysisItemListByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(liquidationAnalysisItemList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getPrice() != null && schemeJudgeObject.getEvaluationArea() != null) {
                    String value = getNetAssessmentNumber2(reportFieldEnum, liquidationAnalysisItemList, schemeJudgeObjectList, schemeJudgeObject, null);
                    map.put(
                            generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())
                            , String.format("%s元", value));
                }
            }
        }
        if (!map.isEmpty()) {
            return generateCommonMethod.judgeEachDesc2(map, "", ",", false);
        }
        return "/";
    }

    public String getNetAssessmentNumber2(BaseReportFieldEnum reportFieldEnum, List<SchemeLiquidationAnalysisItem> liquidationAnalysisItemList, List<SchemeJudgeObject> schemeJudgeObjectList, SchemeJudgeObject schemeJudgeObject, Integer newScalePrice) {
        final String sellerPayment = "卖方";
        final String tradingParties = "双方";
        final String buyerPayment = "买方";
        List<SchemeLiquidationAnalysisItem> schemeLiquidationAnalysisItemList = liquidationAnalysisItemList.stream().filter(oo -> {
            if (Objects.equal(reportFieldEnum.name(), BaseReportFieldEnum.NetAssessmentTwo.name())) {
                return StringUtils.contains(oo.getTaxesBurden(), sellerPayment);
            }
            if (Objects.equal(reportFieldEnum.name(), BaseReportFieldEnum.NetAssessmentOne.name())) {
                return StringUtils.contains(oo.getTaxesBurden(), buyerPayment);
            }
            return StringUtils.contains(oo.getTaxesBurden(), tradingParties);
        }).collect(Collectors.toList());
        //待减去的资金
        BigDecimal totalTaxAmount = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(schemeLiquidationAnalysisItemList)) {
            for (SchemeLiquidationAnalysisItem analysisItem : schemeLiquidationAnalysisItemList) {
                if (analysisItem.getPrice() == null) {
                    continue;
                }
                totalTaxAmount = totalTaxAmount.add(analysisItem.getPrice());
            }
        }
        //总估价对象面积
        BigDecimal totalEvaluationArea = new BigDecimal("0");
        for (SchemeJudgeObject schemeJudgeObject1 : schemeJudgeObjectList) {
            totalEvaluationArea = totalEvaluationArea.add(schemeJudgeObject1.getEvaluationArea());
        }
        BigDecimal judgeTaxAmount = new BigDecimal("0");
        if (totalTaxAmount.intValue() > 0) {
            judgeTaxAmount = schemeJudgeObject.getEvaluationArea().divide(totalEvaluationArea, 2, RoundingMode.HALF_UP).multiply(totalTaxAmount);
        }
        BigDecimal bigDecimal = null;
        if (newScalePrice == null) {
            bigDecimal = schemeJudgeObject.getPrice().multiply(schemeJudgeObject.getEvaluationArea());
        } else {
            BigDecimal price = ArithmeticUtils.createBigDecimal(ArithmeticUtils.getBigDecimalToInteger(schemeJudgeObject.getPrice(), newScalePrice));
            bigDecimal = price.multiply(schemeJudgeObject.getEvaluationArea());
        }
        BigDecimal mortgageValue = bigDecimal.subtract(judgeTaxAmount);
        return generateCommonMethod.getBigDecimalRound(mortgageValue, false);
    }

    /**
     * 估价结果汇总表
     *
     * @throws Exception
     */
    public String getJudgeSummarySheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        builder.writeln("汇总表");
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        builder.writeln("估价结果汇总表");
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Table table = builder.startTable();
        for (int j = 0; j < 2; j++) {
            switch (j) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        switch (k) {
                            case 0:
                                builder.insertCell();
                                builder.write("估价对象及结果\\估价方法及结果");
                                mergeCellModelList.add(new MergeCellModel(0, 0, 1, 2));
                                break;
                            case 3:
                                builder.insertCell();
                                builder.write("测算结果");
                                mergeCellModelList.add(new MergeCellModel(0, 3, 0, 5));
                                break;
                            case 6:
                                builder.insertCell();
                                builder.write("估价结果");
                                //未处理
                                mergeCellModelList.add(new MergeCellModel(j, 6, j + 1, 7));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        switch (k) {
                            case 3:
                                builder.insertCell();
                                builder.write("市场比较法");
                                break;
                            case 4:
                                builder.insertCell();
                                builder.write("收益法");
                                break;
                            case 5:
                                builder.insertCell();
                                builder.write("");
                                break;
                            case 6:
                                builder.insertCell();
                                break;

                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        BigDecimal totolCompare = new BigDecimal(0);
        BigDecimal priceCompare = new BigDecimal(0);
        BigDecimal totolIncome = new BigDecimal(0);
        BigDecimal priceIncome = new BigDecimal(0);
        for (int j = 2; j < 2 + schemeJudgeObjectList.size(); j++) {
            MdMarketCompare mdMarketCompare = null;
            MdIncome mdIncome = null;
            SchemeJudgeObject judgeObject = schemeJudgeObjectList.get(j - 2);
            SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(judgeObject.getId(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE).getId());
            if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                mdMarketCompare = mdMarketCompareService.getMdMarketCompare(schemeInfo.getMethodDataId());
            }

            schemeInfo = schemeInfoService.getSchemeInfo(judgeObject.getId(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME).getId());
            if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                mdIncome = mdIncomeService.getIncomeById(schemeInfo.getMethodDataId());
            }
            int num = j % 2;
            switch (num) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 0:
                                builder.write(getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(j - 2)));
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.write("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdMarketCompare.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.write(temp.toString());
                                        totolCompare = totolCompare.add(temp);
                                    } else {
                                        builder.write("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdIncome.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.write(temp.toString());
                                        totolIncome = totolIncome.add(temp);
                                    } else {
                                        builder.write("");
                                    }
                                }
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 1:
                                builder.write("单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null) {
                                        builder.write(mdMarketCompare.getPrice().toString());
                                        priceCompare = priceCompare.add(mdMarketCompare.getPrice());
                                    } else {
                                        builder.write("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null) {
                                        builder.write(mdIncome.getPrice().toString());
                                        priceIncome = priceIncome.add(mdIncome.getPrice());
                                    }
                                } else {
                                    builder.write("");
                                }
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        for (int j = 2 + schemeJudgeObjectList.size(); j < 2 + schemeJudgeObjectList.size() + 2; j++) {
            int num = j % 2;
            switch (num) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 0:
                                builder.write("汇总平均价值");
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.write("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.write(totolCompare.toString());
                                break;
                            case 4:
                                builder.write(totolIncome.toString());
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 1:
                                builder.write("平均单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.write(priceCompare.toString());
                                break;
                            case 4:
                                builder.write(priceIncome.toString());
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 收益法租赁限制说明
     *
     * @throws Exception
     */
    public String getTenancyrestrictionRemark() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<String, String> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME).getId());
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfo, projectId, areaId);
                    String value = generateMdIncomeService.getTenancyrestrictionReamrk();
                    if (StringUtils.isNotBlank(value)) {
                        map.put(schemeJudgeObject.getNumber(), value);
                    }
                }
            }
        }
        if (map.isEmpty()) return "";
        return generateCommonMethod.judgeEachDescExtend(map, "", "。", false);
    }

    /**
     * 功能描述: 估价对象详细测算过程
     *
     * @author: zch
     * @date: 2019/3/4 10:30
     */
    public String getDetailedCalculationProcessValuationObject() throws Exception {
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        Document document = new Document();
        //测算估价方法key的集合
        final String keys = String.join(",",
                AssessDataDicKeyConstant.MD_INCOME,
                AssessDataDicKeyConstant.MD_MARKET_COMPARE,
                AssessDataDicKeyConstant.MD_COST,
                AssessDataDicKeyConstant.MD_DEVELOPMENT);
        List<BaseDataDic> dataDicList = Lists.newArrayList();
        for (String key : keys.split(",")) {
            dataDicList.add(baseDataDicService.getCacheDataDicByFieldName(key));
        }
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList(new KeyValueDto("text-align", "center"), new KeyValueDto("font-size", "16.0pt"));
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        LinkedHashMap<SchemeInfo, SchemeJudgeObject> schemeJudgeObjectLinkedHashMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                for (BaseDataDic baseDataDic : dataDicList) {
                    SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), baseDataDic.getId());
                    if (schemeInfo != null) {
                        schemeJudgeObjectLinkedHashMap.put(schemeInfo, schemeJudgeObject);
                    }
                }
            }
        }
        if (!schemeJudgeObjectLinkedHashMap.isEmpty()) {
            for (Map.Entry<SchemeInfo, SchemeJudgeObject> entry : schemeJudgeObjectLinkedHashMap.entrySet()) {
                BaseDataDic baseDataDic = dataDicList.stream().filter(oo -> Objects.equal(entry.getKey().getMethodType(), oo.getId())).findFirst().get();
                if (baseDataDic == null || StringUtils.isEmpty(baseDataDic.getFieldName())) {
                    continue;
                }
                List<Integer> numbers = Lists.newArrayList(Lists.newArrayList(entry.getValue().getNumber().split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
                switch (baseDataDic.getFieldName()) {
                    case AssessDataDicKeyConstant.MD_MARKET_COMPARE:
                        GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(entry.getValue().getId(), entry.getKey().getMethodDataId(), areaId);
                        baseDetailedCalculationProcessValuationObject(generateMdCompareService.generateCompareFile(), builder, keyValueDtoList, numbers, baseDataDic, map);
                        break;
                    case AssessDataDicKeyConstant.MD_INCOME:
                        //判断自营还是租赁
                        MdIncome mdIncome = mdIncomeService.getIncomeById(entry.getKey().getMethodDataId());
                        if (mdIncome != null) {
                            if (MethodIncomeOperationModeEnum.PROPRIETARY.getId().equals(mdIncome.getOperationMode())) {
                                GenerateMdIncomeSelfRunService generateMdIncomeSelfRunService = new GenerateMdIncomeSelfRunService(entry.getKey(), areaId);
                                baseDetailedCalculationProcessValuationObject(generateMdIncomeSelfRunService.generateCompareFile(), builder, keyValueDtoList, numbers, baseDataDic, map);
                            } else if (MethodIncomeOperationModeEnum.LEASE.getId().equals(mdIncome.getOperationMode())) {
                                GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(entry.getKey(), projectId, areaId);
                                baseDetailedCalculationProcessValuationObject(generateMdIncomeService.generateCompareFile(), builder, keyValueDtoList, numbers, baseDataDic, map);
                            }
                        }
                        break;
                    case AssessDataDicKeyConstant.MD_COST:
                        GenerateMdCostService mdCostService = new GenerateMdCostService(projectId, entry.getKey(), areaId);
                        baseDetailedCalculationProcessValuationObject(mdCostService.generateCompareFile(), builder, keyValueDtoList, numbers, baseDataDic, map);
                        break;
                    case AssessDataDicKeyConstant.MD_DEVELOPMENT:
                        GenerateMdDevelopmentService generateMdDevelopmentService = new GenerateMdDevelopmentService(projectId, entry.getKey(), areaId);
                        baseDetailedCalculationProcessValuationObject(generateMdDevelopmentService.generateCompareFile(), builder, keyValueDtoList, numbers, baseDataDic, map);
                        break;
                    default:
                        break;
                }
            }
        }
        AsposeUtils.saveWord(localPath, document);
        //开始对插入的标识符换为文档
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> stringEntry : map.entrySet()) {
                IReplacingCallback callback = new IReplacingCallback() {
                    @Override
                    public int replacing(ReplacingArgs e) throws Exception {
                        DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                        builder.moveTo(e.getMatchNode());
                        Document doc = new Document(stringEntry.getValue());
                        builder.insertDocument(doc, 0);
                        return 0;
                    }
                };
                document.getRange().replace(Pattern.compile(stringEntry.getKey()), callback, false);
            }
            AsposeUtils.saveWord(localPath, document);
        }
        return localPath;
    }

    private void baseDetailedCalculationProcessValuationObject(String reportPath, DocumentBuilder builder, List<KeyValueDto> keyValueDtoList, List<Integer> numbers, BaseDataDic baseDataDic, Map<String, String> map) throws Exception {
        File file = new File(reportPath);
        if (file.isFile()) {
            String title = generateCommonMethod.delHTMLTag(String.format("%s号:%s", generateCommonMethod.convertNumber(numbers), baseDataDic.getName()));
            AsposeUtils.insertHtml(builder, AsposeUtils.getWarpCssHtml(title, keyValueDtoList), true);
            String key = String.join("", title, RandomStringUtils.randomNumeric(16));
            map.put(key, reportPath);
            builder.writeln(key);
        }
    }

    /**
     * 估价委托书复印件
     *
     * @throws Exception
     */
    public String getJUDGEOBJECTPRINCIPALCOPYSHEET() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(projectId, AssessUploadEnum.PROJECT_PROXY.getKey(), FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
            this.imgComposingByAttachmentDtoList(attachmentDtoList, builder);
        }
        document.save(localPath);
        return localPath;
    }

    public void imgComposingByAttachmentDtoList(List<SysAttachmentDto> sysAttachmentDtoList, DocumentBuilder builder) throws Exception {
        List<String> imgPathList = Lists.newArrayList();
        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
            String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
            if (FileUtils.checkImgSuffix(imgPath)) {
                imgPathList.add(imgPath);
            }
        }
        if (imgPathList.size() == 1) {
            AsposeUtils.imageInsertToWrod(imgPathList, 1, builder);
        }
        if (imgPathList.size() > 1) {
            AsposeUtils.imageInsertToWrod(imgPathList, 2, builder);
        }
    }

    /**
     * 估计对象位置示意图
     */
    public String getEstimatedObjectLocationDiagram() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<SchemeJudgeObject> schemeJudgeObjectList = this.schemeJudgeObjectDeclareList;
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                if (schemeJudgeObjectList.size() > 1) {
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml(schemeJudgeObject.getName()), true);
                }
                List<SysAttachmentDto> sysAttachmentDtoList = schemeReportFileService.getJudgeObjectPositionFileList(schemeJudgeObject.getDeclareRecordId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    this.imgComposingByAttachmentDtoList(sysAttachmentDtoList, builder);
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 估价对象实况照片
     */
    public String getValuation_Target_Live_Photos() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        if (CollectionUtils.isNotEmpty(this.schemeJudgeObjectDeclareList)) {
            for (SchemeJudgeObject schemeJudgeObject : this.schemeJudgeObjectDeclareList) {
                List<SchemeReportFileItem> schemeReportFileItemList = schemeReportFileService.getReportListByDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                if (CollectionUtils.isNotEmpty(schemeReportFileItemList)) {
                    builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    if (this.schemeJudgeObjectDeclareList.size() > 1) {
                        builder.insertHtml(generateCommonMethod.getWarpCssHtml(schemeJudgeObject.getName()), true);
                    }
                    if (schemeReportFileItemList.size() > 1) {
                        this.imageInsertToWrod3(schemeReportFileItemList, 3, builder);
                    } else {
                        this.imageInsertToWrod3(schemeReportFileItemList, 1, builder);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    public void imageInsertToWrod3(List<SchemeReportFileItem> schemeReportFileList, Integer colCount, DocumentBuilder builder) throws Exception {
        if (CollectionUtils.isEmpty(schemeReportFileList)) throw new RuntimeException("imgPathList empty");
        if (colCount == null || colCount <= 0) throw new RuntimeException("colCount empty");
        if (builder == null) throw new RuntimeException("builder empty");
        Table table = builder.startTable();
        int rowLength = (schemeReportFileList.size() % colCount > 0 ? (schemeReportFileList.size() / colCount) + 1 : schemeReportFileList.size() / colCount) * 2;//行数
        Integer index = 0;
        //根据不同列数设置 表格与图片的宽度 总宽度为560
        int maxWidth = 325;
        int cellWidth = maxWidth / colCount;
        for (int j = 0; j < rowLength; j++) {
            //插入图片
            if (j % 2 == 0) {
                for (int k = 0; k < colCount; k++) {
                    index = j / 2 * colCount + k;
                    if (index < schemeReportFileList.size()) {
                        SchemeReportFileItem schemeReportFileItem = schemeReportFileList.get(index);
                        List<SysAttachmentDto> attachmentList = schemeReportFileService.getAttachmentListBySchemeReportFile(schemeReportFileItem);
                        if (CollectionUtils.isEmpty(attachmentList)) continue;
                        builder.insertCell();
                        String imgPath = "";
                        List<String> paths = Lists.newArrayList();
                        for (SysAttachmentDto item : attachmentList) {
                            String itemImgPath = baseAttachmentService.downloadFtpFileToLocal(item.getId());
                            if (StringUtils.isNotEmpty(itemImgPath) && FileUtils.checkImgSuffix(itemImgPath)) {
                                paths.add(baseAttachmentService.downloadFtpFileToLocal(item.getId()));
                            }
                        }
                        if (paths.size() == 0) continue;
                        imgPath = generateCommonMethod.getCombinationOfhead(paths);

                        int width = maxWidth / colCount;
                        int height = maxWidth / colCount;
                        if (schemeReportFileList.size() == 1) {
                            height = 145;
                        }
                        builder.insertImage(imgPath, RelativeHorizontalPosition.MARGIN, 0,
                                RelativeVerticalPosition.MARGIN, 0, width, height, WrapType.INLINE);
                        //设置样式
                        builder.getCellFormat().getBorders().setColor(Color.white);
                        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
                        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
                        builder.getCellFormat().setWidth(cellWidth);
                        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
//                        builder.getRowFormat().setAlignment(RowAlignment.LEFT);
                        // builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    }
                }
                builder.endRow();
            }
            //插入名称
            if (j % 2 != 0) {
                for (int k = 0; k < colCount; k++) {
                    index = j / 2 * colCount + k;
                    if (index < schemeReportFileList.size()) {
                        SchemeReportFileItem schemeReportFileItem = schemeReportFileList.get(index);
                        List<SysAttachmentDto> attachmentList = schemeReportFileService.getAttachmentListBySchemeReportFile(schemeReportFileItem);
                        if (CollectionUtils.isEmpty(attachmentList)) continue;
                        List<String> paths = Lists.newArrayList();
                        for (SysAttachmentDto item : attachmentList) {
                            String itemImgPath = baseAttachmentService.downloadFtpFileToLocal(item.getId());
                            if (StringUtils.isNotEmpty(itemImgPath) && FileUtils.checkImgSuffix(itemImgPath)) {
                                paths.add(baseAttachmentService.downloadFtpFileToLocal(item.getId()));
                            }
                        }
                        if (paths.size() == 0) continue;
                        builder.insertCell();
                        builder.getFont().setName("宋体");
                        builder.getFont().setSize(10.5);
                        builder.write(schemeReportFileItem.getFileName());
                    }
                }
                builder.endRow();
            }
        }
    }

    /**
     * 估价对象权属证明复印件
     */
    public String getCopies_the_Ownership_Certificate_the_Valuation_Object() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        Map<Integer, List<SysAttachmentDto>> ownershipCertFileList = schemeReportFileService.getOwnershipCertFileList(projectId);
        if (CollectionUtils.isNotEmpty(this.schemeJudgeObjectDeclareList)) {
            for (SchemeJudgeObject schemeJudgeObject : this.schemeJudgeObjectDeclareList) {
                List<SysAttachmentDto> sysAttachmentDtoList = ownershipCertFileList.get(schemeJudgeObject.getDeclareRecordId());
                List<String> paths = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto item : sysAttachmentDtoList) {
                        String path = baseAttachmentService.downloadFtpFileToLocal(item.getId());
                        if (FileUtils.checkImgSuffix(path)) {
                            paths.add(path);
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    if (this.schemeJudgeObjectDeclareList.size() > 1) {
                        builder.insertHtml(generateCommonMethod.getWarpCssHtml(schemeJudgeObject.getName()), true);
                    }
                    //关联的土地复印件
                    List<String> landFilePathList = schemeReportFileService.getLandFilePathList(schemeJudgeObject.getDeclareRecordId());
                    paths.addAll(landFilePathList);
                    AsposeUtils.imageInsertToWrod2(null, 1, builder, paths);

                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 估价中引用的专用文件资料
     *
     * @throws Exception
     */
    public String getSpecial_documentation_referenced_in_valuation() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<String> imgPathList = null;
        if (CollectionUtils.isNotEmpty(this.schemeJudgeObjectDeclareList)) {
            Map<Integer, List<SysAttachmentDto>> inventoryAddressFileList = schemeReportFileService.getInventoryAddressFileList(projectId);
            for (SchemeJudgeObject schemeJudgeObject : this.schemeJudgeObjectDeclareList) {
                //1.先取地址不一致附件
                List<SysAttachmentDto> addressFileList = inventoryAddressFileList.get(schemeJudgeObject.getDeclareRecordId());
                if (CollectionUtils.isNotEmpty(addressFileList)) {
                    builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    if (this.schemeJudgeObjectDeclareList.size() > 1) {
                        builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("%s%s", schemeJudgeObject.getName(), "地址不一致附件")), true);
                    }
                    this.imgComposingByAttachmentDtoList(addressFileList, builder);
                }
            }
        }

        //2.法定优先受偿款附件
        Map<Integer, List<SysAttachmentDto>> reimbursementFileList = schemeReportFileService.getReimbursementFileList(projectId);
        List<SysAttachmentDto> reimFileList = reimbursementFileList.get(1);
        if (CollectionUtils.isNotEmpty(reimFileList)) {
            builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
            builder.insertHtml(generateCommonMethod.getWarpCssHtml("法定优先受偿款附件"), true);
//            builder.writeln("法定优先受偿款附件");
//            for (SysAttachmentDto sysAttachmentDto : reimFileList) {
//                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
//                generateCommonMethod.builderInsertImage(builder, imgPath);
//
//            }
            this.imgComposingByAttachmentDtoList(reimFileList, builder);
        }

        //3.取得自定义的附件
        if (CollectionUtils.isNotEmpty(this.schemeJudgeObjectDeclareList)) {
            for (SchemeJudgeObject schemeJudgeObject : this.schemeJudgeObjectDeclareList) {
                List<SchemeReportFileCustom> reportFileCustomList = schemeReportFileService.getReportFileCustomList(schemeJudgeObject.getDeclareRecordId());
                if (CollectionUtils.isNotEmpty(reportFileCustomList)) {
                    List<SysAttachmentDto> fileList = Lists.newArrayList();
                    for (SchemeReportFileCustom schemeReportFileCustom : reportFileCustomList) {
                        fileList.addAll(schemeReportFileService.getCustomFileList(schemeReportFileCustom.getId()));
                    }
                    if (CollectionUtils.isNotEmpty(fileList)) {
                        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                        builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("<span style=\"text-indent:2em\">%s</span>", schemeJudgeObject.getName())), true);
                        this.imgComposingByAttachmentDtoList(fileList, builder);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 房地产估价机构营业执照复印件
     *
     * @throws Exception
     */
    public String getCopyBusinessLicenseRealEstateValuationAgency() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForLicense();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    this.imgComposingByAttachmentDtoList(attachmentDtoList, builder);
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * '房地产估价机构资质证书复印件
     *
     * @throws Exception
     */
    public String getCopyQualificationCertificateRealEstateValuationInstitution() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForPractising();
        if (adCompanyQualificationDto != null && StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
            List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
            if (CollectionUtils.isEmpty(attachmentDtoList)) return null;
            this.imgComposingByAttachmentDtoList(attachmentDtoList, builder);
            if (CollectionUtils.isEmpty(attachmentDtoList)) return null;
            List<String> images = Lists.newArrayList();
            for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                if (StringUtils.isNotBlank(imgPath) && FileUtils.checkImgSuffix(imgPath)) {
                    images.add(imgPath);
                }
            }
            if (CollectionUtils.isNotEmpty(images)) {
                AsposeUtils.insertImage(localPath, images, 200, 100);
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 注册房地产估价师注册证书复印件
     *
     * @param generateReportInfo
     * @return
     * @throws Exception
     */
    public String getRegisteredRealEstateValuerValuationInstitution(GenerateReportInfo generateReportInfo) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<String> stringList = null;
        if (StringUtils.isNotBlank(generateReportInfo.getRealEstateAppraiser())) {
            stringList = FormatUtils.transformString2List(generateReportInfo.getRealEstateAppraiser());
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            stringList = stringList.stream().distinct().collect(Collectors.toList());
            for (String account : stringList) {
                List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, generateReportInfo.getQualificationType());
                if (CollectionUtils.isEmpty(adPersonalQualificationDtoList)) {
                    continue;
                }
                for (AdPersonalQualificationDto adCompanyQualificationDto : adPersonalQualificationDtoList) {
                    if (StringUtils.isBlank(adCompanyQualificationDto.getStandardImageJson())) {
                        continue;
                    }
                    List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                    if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                        builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("%s%s", publicService.getUserNameByAccount(adCompanyQualificationDto.getUserAccount()), "注册证书复印件")), true);
                        this.imgComposingByAttachmentDtoList(attachmentDtoList, builder);
                    }
                }
            }
        }
        AsposeUtils.saveWord(localPath, document);
        return localPath;
    }

    /**
     * 功能描述: 申报所启用表单类型
     *
     * @auther: zch
     * @date: 2019/2/25 10:09
     */
    public String getTypesFormEnabledDeclarationOffice() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        List<DeclareRecord> declareRecordList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList.stream().forEach(schemeJudgeObject -> {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord != null) {
                    declareRecordList.add(declareRecord);
                }
            });
        }
        List<DeclareRealtyLandCert> declareRealtyLandCertList = Lists.newArrayList();
        List<DeclareRealtyHouseCert> declareRealtyHouseCertList = Lists.newArrayList();
        List<DeclareRealtyRealEstateCert> declareRealtyRealEstateCertList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            declareRecordList.stream().forEach(declareRecord -> {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    declareRealtyLandCertList.add(new DeclareRealtyLandCert());
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    declareRealtyHouseCertList.add(new DeclareRealtyHouseCert());
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    declareRealtyRealEstateCertList.add(new DeclareRealtyRealEstateCert());
                }
            });
        }
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(declareRealtyHouseCertList)) {
            stringSet.add(String.format("《%s》", DeclareTypeEnum.HOUSE.getKey()));
        }
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertList)) {
            stringSet.add(String.format("《%s》", DeclareTypeEnum.LAND.getKey()));
        }
        if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertList)) {
            stringSet.add(String.format("《%s》", DeclareTypeEnum.RealEstate.getKey()));
        }
        String s = generateCommonMethod.toSetStringSplitComma(stringSet);
        return s;
    }

    /**
     * 估价技术思路
     *
     * @return
     */
    public String getEvaluationThink() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        HashMap<Integer, String> map = Maps.newHashMap();
        List<Integer> baseJudgeId = Lists.newArrayList();//基准估价对象号
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            if (schemeJudgeObject.getStandardJudgeId() != null)
                baseJudgeId.add(schemeJudgeObject.getStandardJudgeId());
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isEmpty(schemeJudgeFunctionList)) continue;
            for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                if (map.containsKey(schemeJudgeFunction.getMethodType())) {
                    String s = map.get(schemeJudgeFunction.getMethodType());
                    map.put(schemeJudgeFunction.getMethodType(), String.format("%s,%s", s, schemeJudgeObject.getNumber()));
                } else {
                    map.put(schemeJudgeFunction.getMethodType(), schemeJudgeObject.getNumber());
                }
            }
        }

        List<Integer> baseJudgeNumber = Lists.newArrayList();//基准估价对象号
        List<Integer> otherJudgeNumber = Lists.newArrayList();//其它估价对象号
        List<SchemeJudgeObject> schemeJudgeObjectFullList = this.schemeJudgeObjectFullList ;
        for (SchemeJudgeObject judgeObject : schemeJudgeObjectFullList) {
            if (baseJudgeId.contains(judgeObject.getId())) {
                baseJudgeNumber.add(Integer.valueOf(judgeObject.getNumber()));
            } else {
                otherJudgeNumber.add(Integer.valueOf(judgeObject.getNumber()));
            }
        }
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        String reportThinking = evaluationThinkingService.getReportThinking(map, this.projectInfo, baseJudgeNumber, otherJudgeNumber);
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(reportThinking), true);
        document.save(localPath);
        return localPath;
    }


    /**
     * 估价信息描述
     *
     * @return
     * @throws Exception
     */
    public String getPrincipalDataDescribe() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            if (StringUtils.isNotEmpty(declareRecord.getOwnership())) {
                stringBuilder.append("对").append(declareRecord.getOwnership());
            }
            if (StringUtils.isNotEmpty(declareRecord.getPublicSituation())) {
                stringBuilder.append(declareRecord.getPublicSituation());
                stringBuilder.append("的位于");
            }
            if (StringUtils.isNotEmpty(declareRecord.getSeat())) {
                stringBuilder.append(declareRecord.getSeat());
            }
            if (StringUtils.isNotEmpty(declareRecord.getCertUse())) {
                stringBuilder.append(declareRecord.getCertUse()).append("用房地产");
            }
            {
                stringBuilder.append("(");
                if (declareRecord.getFloorArea() != null) {
                    stringBuilder
                            .append("建筑面积:")
                            .append(generateCommonMethod.getBigDecimalRound(declareRecord.getFloorArea(), 2, false))
                            .append("㎡");
                    stringBuilder.append(",及其分摊的");
                }
                if (StringUtils.isNotEmpty(declareRecord.getLandRightType())) {
                    stringBuilder.append(declareRecord.getLandRightType());
                }
                if (StringUtils.isNotEmpty(declareRecord.getLandRightNature())) {
                    stringBuilder.append(declareRecord.getLandRightNature());
                }
                if (StringUtils.isNotEmpty(declareRecord.getCertUse())) {
                    stringBuilder.append(declareRecord.getCertUse());
                }
                //分摊面积
                BigDecimal apportionmentArea = null;
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                    if (declareRealtyRealEstateCert != null && declareRealtyRealEstateCert.getApportionmentArea() != null) {
                        apportionmentArea = declareRealtyRealEstateCert.getApportionmentArea();
                    }
                }
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    DeclareRealtyHouseCert realtyHouseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                    if (realtyHouseCertById != null && realtyHouseCertById.getApportionmentArea() != null) {
                        apportionmentArea = realtyHouseCertById.getApportionmentArea();
                    }
                }
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
                    if (declareRealtyLandCert != null && declareRealtyLandCert.getApportionmentArea() != null) {
                        apportionmentArea = declareRealtyLandCert.getApportionmentArea();
                    }
                }
                if (apportionmentArea != null) {
                    stringBuilder
                            .append("土地使用权面积:")
                            .append(generateCommonMethod.getBigDecimalRound(apportionmentArea, 2, false))
                            .append("㎡");
                }
                stringBuilder.append(")");
            }
            stringBuilder.append("进行了抵押价值评估");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        if (!map.isEmpty()) {
            String s = generateCommonMethod.judgeEachDesc2(map, "", ",", false);
            return s;
        }
        return "";
    }

    /**
     * 估价对象描述
     *
     * @return
     * @throws Exception
     */
    public String getPrincipalDescribe() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        StringBuilder stringBuilder = new StringBuilder(16);
        StringBuffer buffer = new StringBuffer(8);
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.setDefaultDocumentBuilderSetting(documentBuilder);
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> linkedHashMap = generateCommonMethod.getEstateGroupByAreaId(areaId);
        if (!linkedHashMap.isEmpty()) {
            int i = 0;
            for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : linkedHashMap.entrySet()) {
                if (linkedHashMap.size() > 1) {
                    buffer.append(String.format("%s、", i + 1));
                }
                List<DeclareRecord> recordList = declareRecordService.getDeclareRecordListByIds(LangUtils.transform(entry.getValue(), o -> o.getDeclareRecordId()));
                List<String> list = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(recordList)) {
                    if (StringUtils.isNotEmpty(recordList.stream().findFirst().get().getStreetNumber())) {
                        buffer.append(recordList.stream().findFirst().get().getStreetNumber());
                    }
                    for (DeclareRecord declareRecord : recordList) {
                        if (StringUtils.isNotEmpty(declareRecord.getStreetNumber())) {
                            list.add(declareRecord.getSeat().replace(declareRecord.getStreetNumber(), ""));
                        } else {
                            list.add(declareRecord.getSeat());
                        }
                    }
                    buffer.append(entry.getKey().getName()).append(publicService.fusinString(list, false));
                }
                buffer.append(",");
                Map<Integer, String> certUseMap = Maps.newHashMap();
                Map<Integer, String> certUseMap2 = Maps.newHashMap();
                Map<Integer, String> practicalUseMap = Maps.newHashMap();
                Map<Integer, String> buildAreaMap = Maps.newHashMap();
                Map<Integer, String> evaluationAreaMap = Maps.newHashMap();
                Map<Integer, String> landRightNatureMap = Maps.newHashMap();
                Map<Integer, String> ownershipMap = Maps.newHashMap();
                Map<Integer, String> structureMap = Maps.newHashMap();
                for (SchemeJudgeObject schemeJudgeObject : entry.getValue()) {
                    DeclareRecord declareRecord = recordList.stream().filter(o -> o.getId().equals(schemeJudgeObject.getDeclareRecordId())).findFirst().get();
                    Integer number = generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber());
                    certUseMap.put(number, schemeJudgeObject.getCertUse());
                    certUseMap2.put(number, declareRecord.getCertUse());
                    if (StringUtils.isNotBlank(declareRecord.getPracticalUse()))
                        practicalUseMap.put(number, schemeJudgeObject.getPracticalUse());
                    buildAreaMap.put(number, String.format("%s㎡", schemeJudgeObject.getFloorArea()));
                    evaluationAreaMap.put(number, String.format("%s㎡", schemeJudgeObject.getEvaluationArea()));
                    if (StringUtils.isNotBlank(declareRecord.getLandRightNature()))
                        landRightNatureMap.put(number, declareRecord.getLandRightNature());
                    ownershipMap.put(number, declareRecord.getOwnership());
                    if (StringUtils.isNotBlank(declareRecord.getHousingStructure()))
                        structureMap.put(number, declareRecord.getHousingStructure());
                }
                if (!certUseMap2.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(certUseMap2, "证载用途", false)).append(",");//证载用途
                }
                if (!practicalUseMap.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(practicalUseMap, "实际用途", false)).append(",");//实际用途
                }
                if (!certUseMap.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(certUseMap, "设定用途", false)).append(",");//设定用途
                }
                if (!buildAreaMap.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(buildAreaMap, "建筑面积", false)).append(",");//建筑面积
                }
                if (!evaluationAreaMap.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(evaluationAreaMap, "评估面积", false)).append(",");//评估面积
                }
                if (!landRightNatureMap.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(landRightNatureMap, "权利性质", false)).append(",");//权利性质
                }
                if (!structureMap.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(structureMap, "房屋结构", false)).append(",");//房屋结构
                }
                if (!ownershipMap.isEmpty()) {
                    buffer.append(generateCommonMethod.judgeSummaryDesc(ownershipMap, "权利人", false)).append(",");//权利人
                }
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(buffer.toString())));
                buffer.delete(0, buffer.toString().length());
                i++;

            }
        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        document.save(localPath);
        return localPath;
    }

    //楼盘区位分析
    public String getLocationDescriptionBuilding() throws Exception {
        String value = "/";
        Map<Integer, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateVo basicEstate = generateBaseExamineService.getEstate();
            if (basicEstate == null || StringUtils.isBlank(basicEstate.getLocationDescribe())) {
                continue;
            }
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), basicEstate.getLocationDescribe());
        }
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", ",", false);
        }
        return value;
    }

    /**
     * 工行估价案例情况表
     *
     * @return
     * @throws Exception
     */
    protected String getICBCValuationCaseInformationSheet() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder documentBuilder = new DocumentBuilder(document);
        documentBuilder.getFont().setSize(12);
        documentBuilder.getFont().setName(AsposeUtils.ImitationSong);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BaseDataDic mdCompare = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        Map<SchemeJudgeObject, SchemeInfo> objectSchemeInfoMap = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            //市场比较法
            SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), mdCompare.getId());
            if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply != null) {
                    objectSchemeInfoMap.put(schemeJudgeObject, schemeInfo);
                }
            }
        }
        Map<String, String> stringMap = Maps.newHashMap();
        if (!objectSchemeInfoMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, SchemeInfo> schemeJudgeObjectSchemeInfoEntry : objectSchemeInfoMap.entrySet()) {
                //当多个估价对象的时候写入估价编号
                if (objectSchemeInfoMap.size() > 1) {
                    documentBuilder.writeln(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObjectSchemeInfoEntry.getKey()));
                }
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObjectSchemeInfoEntry.getKey());
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicEstateVo basicEstate = generateBaseExamineService.getEstate();
                //描述内容
                if (StringUtils.isNotBlank(basicEstate.getLocationDescribe())) {
                    AsposeUtils.insertHtml(documentBuilder, AsposeUtils.getWarpCssHtml(basicEstate.getLocationDescribe(), Lists.newArrayList(new KeyValueDto("text-indent", "2em"), new KeyValueDto(AsposeUtils.FontFamily, AsposeUtils.ImitationSong), new KeyValueDto(AsposeUtils.FontSize, "12pt"))));
                }
                List<MdMarketCompareItem> mdMarketCompareItemList = mdMarketCompareService.getCaseListByMcId(schemeJudgeObjectSchemeInfoEntry.getValue().getMethodDataId());
                if (CollectionUtils.isNotEmpty(mdMarketCompareItemList)) {
                    //写入表格
                    String path = getICBCValuationCaseInformationSheet2(mdMarketCompareItemList);
                    String key = RandomStringUtils.randomNumeric(7);
                    documentBuilder.writeln(key);
                    stringMap.put(key, path);
                }
            }
        }
        AsposeUtils.saveWord(localPath, document);
        if (!stringMap.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, stringMap);
        }
        return localPath;
    }

    /**
     * 建行个贷区位分析
     *
     * @return
     * @throws Exception
     */
    protected String getICBCDistrictAnalysisSheet() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder documentBuilder = new DocumentBuilder(document);
        documentBuilder.getFont().setSize(12);
        documentBuilder.getFont().setName(AsposeUtils.ImitationSong);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicEstateVo basicEstate = generateBaseExamineService.getEstate();
                //描述内容
                if (StringUtils.isNotBlank(basicEstate.getLocationDescribe())) {
                    String value = String.join("", generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject), basicEstate.getLocationDescribe());
                    AsposeUtils.insertHtml(documentBuilder, AsposeUtils.getWarpCssHtml(value, Lists.newArrayList(new KeyValueDto("text-indent", "2em"), new KeyValueDto(AsposeUtils.FontFamily, AsposeUtils.ImitationSong), new KeyValueDto(AsposeUtils.FontSize, "12pt"))));
                }
            }
        }
        AsposeUtils.saveWord(localPath, document);
        return localPath;
    }

    private String getICBCValuationCaseInformationSheet2(List<MdMarketCompareItem> mdMarketCompareItemList) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder documentBuilder = new DocumentBuilder(document);
        //设置具体宽度自动适应
        PreferredWidth preferredWidth = PreferredWidth.AUTO;
        documentBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        documentBuilder.getCellFormat().setPreferredWidth(preferredWidth);
        documentBuilder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setTopPadding(0);
        documentBuilder.getCellFormat().setBottomPadding(0);
        documentBuilder.getCellFormat().setLeftPadding(0);
        documentBuilder.getCellFormat().setRightPadding(0);
        documentBuilder.getFont().setSize(10.5);
        documentBuilder.getFont().setName(AsposeUtils.ImitationSong);
        List<Integer> planDetailsIdList = Lists.newArrayList();
        mdMarketCompareItemList.forEach(po -> {
            if (po.getPlanDetailsId() != null) {
                planDetailsIdList.add(po.getPlanDetailsId());
            }
        });
        if (CollectionUtils.isNotEmpty(planDetailsIdList)) {
            documentBuilder.startTable();
            LinkedList<String> stringLinkedList = Lists.newLinkedList();
            final String nullValue = "";
            int count = 0;
            stringLinkedList.addAll(Arrays.asList("序号", "名称", "位置", "所在楼层", "面积(㎡)", "单价(元/㎡)", "装修状况", "平面布局"));
            AsposeUtils.writeWordTitle(documentBuilder, stringLinkedList);
            stringLinkedList.clear();
            for (Integer id : planDetailsIdList) {
                BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(id);
                if (basicApply == null) {
                    continue;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicEstateVo basicEstate = generateBaseExamineService.getEstate();
                BasicHouseVo basicHouseVo = generateBaseExamineService.getBasicHouse();
                BasicHouseTrading basicHouseTrading = generateBaseExamineService.getBasicTrading();
                List<BasicHouseRoom> basicHouseRoomList = generateBaseExamineService.getBasicHouseRoomList();
                Map<BasicHouseRoom, List<BasicHouseRoomDecorateVo>> basicHouseRoomListMap = Maps.newHashMap();
                if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
                    basicHouseRoomList.forEach(oo -> {
                        List<BasicHouseRoomDecorateVo> decorateVos = generateBaseExamineService.getBasicHouseRoomDecorateList(oo.getId());
                        if (CollectionUtils.isNotEmpty(decorateVos)) {
                            basicHouseRoomListMap.put(oo, decorateVos);
                        }
                    });
                }
                //序号
                stringLinkedList.add(String.valueOf(++count));
                //名称
                {
                    String value = null;
                    value = basicEstate.getName();
                    if (StringUtils.isEmpty(value)) {
                        value = nullValue;
                    }
                    stringLinkedList.add(value);
                }
                //位置
                {
                    String value = null;
                    value = basicEstate.getStreet();
                    if (StringUtils.isEmpty(value)) {
                        value = nullValue;
                    }
                    stringLinkedList.add(value);
                }
                //所在楼层
                {
                    String value = null;
                    value = basicHouseVo.getFloor();
                    if (StringUtils.isEmpty(value)) {
                        value = nullValue;
                    }
                    stringLinkedList.add(value);
                }
                //面积(㎡)
                {
                    String value = null;
                    if (basicHouseVo.getArea() != null) {
                        value = generateCommonMethod.getBigDecimalRound(basicHouseVo.getArea(), 2, false);
                    }
                    if (StringUtils.isEmpty(value)) {
                        value = nullValue;
                    }
                    stringLinkedList.add(value);
                }
                //单价(元/㎡)
                {
                    String value = null;
                    if (basicHouseTrading != null && basicHouseTrading.getTradingUnitPrice() != null) {
                        value = generateCommonMethod.getBigDecimalRound(basicHouseTrading.getTradingUnitPrice(), 2, false);
                    }
                    if (StringUtils.isEmpty(value)) {
                        value = nullValue;
                    }
                    stringLinkedList.add(value);
                }
                //装修状况
                {
                    String value = basicHouseVo.getDecorateSituationName();
                    if (StringUtils.isEmpty(value)) {
                        value = nullValue;
                    }
                    stringLinkedList.add(value);
                }
                //平面布局
                {
                    String value = nullValue;
                    if (basicHouseVo != null && basicHouseVo.getHuxingId() != null) {
                        BasicUnitHuxing basicUnitHuxing = basicUnitHuxingService.getBasicUnitHuxingById(basicHouseVo.getHuxingId());
                        if (basicUnitHuxing != null) {
                            if (StringUtils.isNotBlank(basicUnitHuxing.getName())) {
                                value = basicUnitHuxing.getName();
                            }
                        }
                    }
                    stringLinkedList.add(value);
                }
                AsposeUtils.writeWordTitle(documentBuilder, stringLinkedList);
                stringLinkedList.clear();
            }
            documentBuilder.endTable();
        }
        document.save(localPath, SaveFormat.DOC);
        return localPath;
    }

    /**
     * 评估总价分述
     *
     * @return
     */
    public synchronized String getAssessPriceClassification() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<SchemeJudgeObject, String> map = Maps.newHashMap();
        Map<SchemeJudgeObject, KeyValueDto> map2 = getAssessAssessTotalData();
        if (!map2.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, KeyValueDto> entry : map2.entrySet()) {
                BigDecimal one = new BigDecimal(entry.getValue().getKey());
                BigDecimal two = new BigDecimal(entry.getValue().getValue());
                BigDecimal bigDecimal = ArithmeticUtils.createBigDecimal(generateCommonMethod.getBigDecimalToInteger(one.multiply(two), 100));
                String value = bigDecimal.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_DOWN).toString();
                map.put(entry.getKey(), value);
            }
        }
        if (!map.isEmpty()) {
            if (map.size() == 1) {
                stringBuilder.append(StringUtils.trim(map.entrySet().stream().findFirst().get().getValue()));
            } else {
                int i = 0;
                for (Map.Entry<SchemeJudgeObject, String> entry : map.entrySet()) {
                    stringBuilder.append(generateCommonMethod.getSchemeJudgeObjectShowName(entry.getKey())).append(entry.getValue());
                    i++;
                    if (i != map.size()) {
                        //插入换行符
                        stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    //评估面积
    public synchronized String getAssessArea() {
        String value = "";
        Map<Integer, String> map = new HashMap<>(2);
        Map<SchemeJudgeObject, KeyValueDto> map2 = getAssessAssessTotalData();
        if (!map2.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, KeyValueDto> entry : map2.entrySet()) {
                map.put(generateCommonMethod.parseIntJudgeNumber(entry.getKey().getNumber()), entry.getValue().getKey());
            }
        }
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc(map, "", "", false);
        }
        return value;
    }

    //评估单价
    public synchronized String getAssessPrice() {
        String value = "";
        Map<Integer, String> map = new HashMap<>(2);
        Map<SchemeJudgeObject, KeyValueDto> map2 = getAssessAssessTotalData();
        if (!map2.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, KeyValueDto> entry : map2.entrySet()) {
                map.put(generateCommonMethod.parseIntJudgeNumber(entry.getKey().getNumber()), entry.getValue().getValue());
            }
        }
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc(map, "", "", false);
        }
        return value;
    }

    /**
     * 评估总价
     *
     * @return
     */
    public synchronized String getAssessAssessTotal() {
        String value = getAssessAssessTotal2();
        if (NumberUtils.isNumber(value)) {
            value = new BigDecimal(value).divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_DOWN).toString();
            return value;
        }
        return "/";
    }

    /**
     * 评估总价大写
     *
     * @return
     */
    public synchronized String getAssessAssessTotalAssessTotalRMB() {
        String value = getAssessAssessTotal2();
        if (NumberUtils.isNumber(value)) {
            value = CnNumberUtils.toUppercaseSubstring(value);
            return value;
        }
        return "/";
    }

    private synchronized String getAssessAssessTotal2() {
        BigDecimal bigDecimal = new BigDecimal(0);
        Map<SchemeJudgeObject, KeyValueDto> map = getAssessAssessTotalData();
        if (!map.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, KeyValueDto> entry : map.entrySet()) {
                BigDecimal one = new BigDecimal(entry.getValue().getKey());
                BigDecimal two = new BigDecimal(entry.getValue().getValue());
                bigDecimal = bigDecimal.add(one.multiply(two));
            }
        }
        String s = generateCommonMethod.getBigDecimalToInteger(bigDecimal, 100);
        return s;
    }

    private synchronized Map<SchemeJudgeObject, KeyValueDto> getAssessAssessTotalData() {
        Map<SchemeJudgeObject, KeyValueDto> map = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getPrice() != null && schemeJudgeObject.getEvaluationArea() != null) {
                    KeyValueDto keyValueDto = new KeyValueDto(generateCommonMethod.getBigDecimalRound(schemeJudgeObject.getEvaluationArea(), 2, false), generateCommonMethod.getBigDecimalToInteger(schemeJudgeObject.getPrice(), 10));
                    map.put(schemeJudgeObject, keyValueDto);
                }
            }
        }
        return map;
    }


    private GenerateBaseDataService() {
    }

    public GenerateBaseDataService(ProjectInfoVo projectInfoVo, Integer areaId, BaseReportTemplate baseReportTemplate, ProjectPlan projectPlan) {
        this.projectId = projectInfoVo.getId();
        this.projectInfo = projectInfoVo;
        this.areaId = areaId;
        this.baseReportTemplate = baseReportTemplate;
        //注入bean
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.projectNumberRecordService = SpringContextUtils.getBean(ProjectNumberRecordService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeJudgeFunctionService = SpringContextUtils.getBean(SchemeJudgeFunctionService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.projectPlanDetailsService = SpringContextUtils.getBean(ProjectPlanDetailsService.class);
        this.projectPhaseService = SpringContextUtils.getBean(ProjectPhaseService.class);
        this.surveyAssetInventoryRightService = SpringContextUtils.getBean(SurveyAssetInventoryRightService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.schemeSurePriceService = SpringContextUtils.getBean(SchemeSurePriceService.class);
        this.schemeReimbursementService = SpringContextUtils.getBean(SchemeReimbursementService.class);
        this.publicService = SpringContextUtils.getBean(PublicService.class);
        this.adRpcQualificationsService = SpringContextUtils.getBean(com.copower.pmcc.assess.service.AdRpcQualificationsAppService.class);
        this.compileReportService = SpringContextUtils.getBean(CompileReportService.class);
        this.schemeReportFileService = SpringContextUtils.getBean(SchemeReportFileService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.schemeLiquidationAnalysisService = SpringContextUtils.getBean(SchemeLiquidationAnalysisService.class);
        this.mdIncomeService = SpringContextUtils.getBean(MdIncomeService.class);
        this.mdMarketCompareService = SpringContextUtils.getBean(MdMarketCompareService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);
        this.evaluationThinkingService = SpringContextUtils.getBean(EvaluationThinkingService.class);
        this.evaluationBasisService = SpringContextUtils.getBean(EvaluationBasisService.class);
        this.evaluationHypothesisService = SpringContextUtils.getBean(EvaluationHypothesisService.class);
        this.evaluationPrincipleService = SpringContextUtils.getBean(EvaluationPrincipleService.class);
        this.dataReportAnalysisService = SpringContextUtils.getBean(DataReportAnalysisService.class);
        this.dataReportAnalysisRiskService = SpringContextUtils.getBean(DataReportAnalysisRiskService.class);
        this.surveyAssetInventoryRightRecordService = SpringContextUtils.getBean(SurveyAssetInventoryRightRecordService.class);
        this.generateLoactionService = SpringContextUtils.getBean(GenerateLoactionService.class);
        this.generateLandEntityService = SpringContextUtils.getBean(GenerateLandEntityService.class);
        this.surveyCommonService = SpringContextUtils.getBean(SurveyCommonService.class);
        this.generateHouseEntityService = SpringContextUtils.getBean(GenerateHouseEntityService.class);
        this.erpAreaService = SpringContextUtils.getBean(ErpAreaService.class);
        this.mdCommonService = SpringContextUtils.getBean(MdCommonService.class);
        this.generateEquityService = SpringContextUtils.getBean(GenerateEquityService.class);
        this.basicApplyService = SpringContextUtils.getBean(BasicApplyService.class);
        this.surveyAssetInventoryRightRecordCenterService = SpringContextUtils.getBean(SurveyAssetInventoryRightRecordCenterService.class);
        this.dataBestUseDescriptionService = SpringContextUtils.getBean(DataBestUseDescriptionService.class);
        this.baseProjectClassifyService = SpringContextUtils.getBean(BaseProjectClassifyService.class);
        this.declareRealtyRealEstateCertService = SpringContextUtils.getBean(DeclareRealtyRealEstateCertService.class);
        this.declareRealtyHouseCertService = SpringContextUtils.getBean(DeclareRealtyHouseCertService.class);
        this.declareRealtyLandCertService = SpringContextUtils.getBean(DeclareRealtyLandCertService.class);
        this.projectQrcodeRecordService = SpringContextUtils.getBean(ProjectQrcodeRecordService.class);
        this.erpRpcToolsService = SpringContextUtils.getBean(ErpRpcToolsService.class);
        this.applicationConstant = SpringContextUtils.getBean(ApplicationConstant.class);
        this.declareBuildEngineeringAndEquipmentCenterService = SpringContextUtils.getBean(DeclareBuildEngineeringAndEquipmentCenterService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.basicUnitHuxingService = SpringContextUtils.getBean(BasicUnitHuxingService.class);
        this.baseService = SpringContextUtils.getBean(BaseService.class);
        this.erpRpcUserService = SpringContextUtils.getBean(ErpRpcUserService.class);
        //必须在bean之后
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(areaId);
        if (areaGroup == null) {
            areaGroup = new SchemeAreaGroup();
        }
        this.schemeAreaGroup = areaGroup;
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            judgeObjectList = new ArrayList<SchemeJudgeObject>(0);
        }
        this.schemeJudgeObjectList = judgeObjectList;
        this.schemeJudgeObjectFullList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(areaId);
        this.schemeJudgeObjectDeclareList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        this.schemeJudgeObjectMap = FormatUtils.mappingSingleEntity(this.schemeJudgeObjectList, o -> o.getId());
    }


    private String getLocalPath() {
        return generateCommonMethod.getLocalPath();
    }

    private String getLocalPath(String title) {
        return generateCommonMethod.getLocalPath(title);
    }

    /**
     * 功能描述: 委估对象名称显示
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/3/1 10:34
     */
    private String getSchemeJudgeObjectShowName(SchemeJudgeObject schemeJudgeObject) {
        return generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject);
    }

    /**
     * 功能描述: 设置默认字体
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/3/1 14:32
     */
    private DocumentBuilder getDefaultDocumentBuilderSetting(Document doc) throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        return builder;
    }

    public synchronized List<SchemeReimbursementItemVo> getSchemeReimbursementItemVoList() {
        List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = Lists.newArrayList();
        SchemeReimbursement query = new SchemeReimbursement();
        query.setAreaId(areaId);
        query.setProjectId(projectId);
        List<SchemeReimbursement> schemeReimbursementList = schemeReimbursementService.getObjectList(query);
        if (CollectionUtils.isNotEmpty(schemeReimbursementList)) {
            schemeReimbursementList.stream().forEach(schemeReimbursement -> {
                List<SchemeReimbursementItemVo> vos = schemeReimbursementService.getItemByMasterId(schemeReimbursement.getId());
                if (CollectionUtils.isNotEmpty(vos)) {
                    schemeReimbursementItemVoList.addAll(vos);
                }
            });
        }
        return schemeReimbursementItemVoList;
    }


    /**
     * 获取区域信息(组)
     *
     * @return
     */
    public SchemeAreaGroup getSchemeAreaGroup() {
        return schemeAreaGroup;
    }

    /**
     * 委估对象列表
     *
     * @return
     */
    public synchronized List<SchemeJudgeObject> getSchemeJudgeObjectList() {
        return generateCommonMethod.getSortSchemeJudgeObject(this.schemeJudgeObjectList);
    }
}
