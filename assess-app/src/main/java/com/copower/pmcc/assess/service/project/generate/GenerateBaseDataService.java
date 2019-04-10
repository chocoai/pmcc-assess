package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.CnNumberUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.*;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.assess.service.project.compile.CompileReportService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.*;
import com.copower.pmcc.assess.service.project.survey.*;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.Reflections;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
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
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
    private SchemeSurePriceService schemeSurePriceService;
    private SchemeReimbursementService schemeReimbursementService;
    private com.copower.pmcc.assess.service.AdRpcQualificationsAppService adRpcQualificationsService;
    private PublicService publicService;
    private CompileReportService compileReportService;
    private SchemeReportFileService schemeReportFileService;
    private DataQualificationService dataQualificationService;
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    private SchemeInfoService schemeInfoService;
    private EvaluationMethodService evaluationMethodService;
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    private MdIncomeService mdIncomeService;
    private MdMarketCompareService mdMarketCompareService;
    private DataHisRightInfoPublicityService dataHisRightInfoPublicityService;
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
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
    private TaskExecutor taskExecutor;
    private GenerateEquityService generateEquityService;

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
    //===========================================获取的值===============================

    //报告出具日期
    public Date getReportIssuanceDate() {
        return new Date();
    }

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
     * 委托人
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

    /**
     * 估价委托人信息
     * @return
     */
    public String getPrincipalInfo() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(documentBuilder);
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("<p>");
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.legalPerson.getId())) {
            String name = projectInfo.getConsignorVo().getCsEntrustmentUnit();
            if (StringUtils.isEmpty(name)) {
                name = "无";
            }
            stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "名称", name)));
            String code = projectInfo.getConsignorVo().getCsSociologyCode();
            if (StringUtils.isEmpty(code)) {
                code = "无";
            }
            stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "统一社会信用代码", code)));
            String address = projectInfo.getConsignorVo().getCsAddress();
            if (StringUtils.isEmpty(address)) {
                address = "无";
            }
            stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "住所", address)));
            String people = projectInfo.getConsignorVo().getCsLegalRepresentative();
            if (StringUtils.isEmpty(people)) {
                people = "无";
            }
            stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "法定代表人", people)));
        }
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.naturalPerson.getId())) {
            String name = projectInfo.getConsignorVo().getCsName();
            if (StringUtils.isEmpty(name)) {
                name = "无";
            }
            stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "姓名", name)));
            String idCard = projectInfo.getConsignorVo().getCsIdcard();
            if (StringUtils.isEmpty(idCard)) {
                idCard = "无";
            }
            stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "身份证号", idCard)));
            String address = projectInfo.getConsignorVo().getCsAddress();
            if (StringUtils.isEmpty(address)) {
                address = "无";
            }
            stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "地址", address)));
        }
        stringBuilder.append("</p>");
        documentBuilder.insertHtml(stringBuilder.toString(), true);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 财产范围说明
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
        }
        if (StringUtils.isNotBlank(getSchemeAreaGroup().getScopeNotInclude())) {
            stringBuffer.append("不包含:").append(getSchemeAreaGroup().getScopeNotInclude()).append("。");
        }
        if (StringUtils.isEmpty(stringBuffer.toString())) {
            stringBuffer.append(errorStr);
        }
        return stringBuffer.toString();
    }

    /**
     * 建筑面积及评估面积
     */
    public String getBuildingAndAssessArea() {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        String s = generateCommonMethod.getBuildingAndAssessArea(schemeJudgeObjectList);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }


    /**
     * 座落
     * @throws Exception
     */
    public String getSeat() throws Exception {
        LinkedHashSet<String> stringSet = Sets.newLinkedHashSet();
        StringBuffer buffer = new StringBuffer(8);
        StringBuffer stringBuffer = new StringBuffer(8);
        List<String> seats = Lists.newArrayList();
        final String zero = "0";
        LinkedHashMap<String, List<SchemeJudgeObject>> linkedHashMap = generateCommonMethod.getSchemeJudgeObjectLinkedHashMap(
                generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true),
                projectInfo);
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry<String, List<SchemeJudgeObject>> entry : linkedHashMap.entrySet()) {
                //楼盘名称
                String estateName = entry.getKey();
                List<SchemeJudgeObject> schemeJudgeObjectList = entry.getValue();
                List<DeclareRecord> declareRecordList = Lists.newArrayList();
                //当估价对象不存在的情况下不予以拼接
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    schemeJudgeObjectList.stream().forEach(schemeJudgeObject -> declareRecordList.add(declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId())));
                    if (CollectionUtils.isEmpty(declareRecordList)) {
                        continue;
                    }
                    declareRecordList.stream().forEach(declareRecord -> {
                        if (StringUtils.isNotBlank(declareRecord.getAttachedNumber())) {
                            stringBuffer.append("附").append(declareRecord.getAttachedNumber()).append("号");
                        }
                        if (StringUtils.isNotBlank(declareRecord.getBuildingNumber())) {
                            stringBuffer.append(declareRecord.getBuildingNumber());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("栋");
                        if (StringUtils.isNotBlank(declareRecord.getUnit())) {
                            stringBuffer.append(declareRecord.getUnit());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("单元");
                        if (StringUtils.isNotBlank(declareRecord.getFloor())) {
                            stringBuffer.append(declareRecord.getFloor());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("层");
                        if (StringUtils.isNotBlank(declareRecord.getRoomNumber())) {
                            stringBuffer.append(declareRecord.getRoomNumber());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("号");
                        seats.add(stringBuffer.toString());
                        stringBuffer.delete(0, stringBuffer.toString().length());
                    });
                    buffer.append(estateName);
                    if (CollectionUtils.isNotEmpty(seats)) {
                        String s = publicService.fusinString(seats);
                        if (StringUtils.isNotBlank(s)) {
                            buffer.append(s);
                        }
                    }
                    seats.clear();
                    stringSet.add(buffer.toString());
                    buffer.delete(0, buffer.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetStringMerge(stringSet, ";");
        return s;
    }

    /**
     * 外聘专家工作概况
     * @return
     */
    public String getExpertWorkOverview() {
        String s = "茅以升1916年毕业于西南交通大学（时称交通部唐山工业专门学校英文名称Tangshan Engineering College），1917年获美国康乃尔大学硕士学位，1919年获美国卡耐基理工学院（先卡耐基梅隆大学）博士学位 [1]  ，回国后历任交通大学唐山工学院教授、国立东南大学（1928年更名为国立中央大学，1949年更名为南京大学）教授、工科主任、国立河海工科大学校长、交通部唐山大学校长（今西南交通大学）、北洋工学院院长、江苏省水利厅厅长、钱塘江大桥工程处处长、交通大学唐山工学院代院长、院长、中国桥梁公司总经理、北洋大学校长、中国/北方交通大学（时含今西南交通大学和今北京交通大学）校长、铁道科学研究院院长等职。1955年选聘为中国科学院院士（学部委员）。";
        return errorStr;
    }

    /**
     * 共有权情况
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
     * 功能描述: 变现比率
     * @auther: zch
     * @date: 2019/2/27 15:17
     */
    public String getLiquidRatios() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, schemeJudgeObject.getLiquidRatio());
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, "");
        if (StringUtils.isEmpty(s)) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 功能描述: 出具报告区域名称
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
        BigDecimal decimal = new BigDecimal(getTotalRealEstatePrice()).subtract(new BigDecimal(getStatutoryPriorityAmountTotal()));
        return generateCommonMethod.getBigDecimalRound(decimal, true);
    }

    /**
     * 抵押价值总金额大写
     */
    public String getTotalAmountMortgageValueCapitalization() throws Exception {
        BigDecimal decimal = new BigDecimal(getTotalRealEstatePrice()).subtract(new BigDecimal(getStatutoryPriorityAmountTotal()));
        return CnNumberUtils.toUppercaseSubstring(generateCommonMethod.getBigDecimalRound(decimal, false));
    }

    /**
     * 估价项目名称
     */
    public String getValuationProjectName() throws Exception {
        String s = getSeat();
        if (getSchemeAreaGroup().getEntrustPurpose() != null) {
            s = String.format("%s%s", s, baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose()));
        }
        return s;
    }


    /**
     * 证载用途
     * @throws Exception
     */
    public String getSeparationCertificateUses() throws Exception {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getCertUse())).collect(Collectors.toList());
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), schemeJudgeObjectList.get(i).getCertUse());
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }


    /**
     * 估价对象的总价
     * @return
     */
    public String getTotalValueValuationObject() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimal = schemeJudgeObject.getEvaluationArea().multiply(schemeJudgeObject.getPrice());
                        stringSet.add(String.format("%s:%s万元", getSchemeJudgeObjectShowName(schemeJudgeObject), generateCommonMethod.getBigDecimalRound(bigDecimal, true)));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        return s;
    }


    /**
     * 分类评估单价计算试
     */
    public String getEvaluationExpression() {
        return "市场比较法价格*权重+收益法价格*权重";
    }

    /**
     * 单价调整表
     * @throws Exception
     */
    public String getUnitPriceAdjustmentTable() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        List<SchemeJudgeObjectVo> schemeJudgeObjectList = getSchemeJudgeObjectList().stream().map(schemeJudgeObject -> {
            return schemeJudgeObjectService.getSchemeJudgeObjectVo(schemeJudgeObject);
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            Table table = builder.startTable();
            for (int i = 0; i < 5; i++) {
                builder.insertCell();
                if (i == 0) builder.writeln("权证号");
                if (i == 1) builder.writeln("楼层");
                if (i == 2) builder.writeln("房号");
                if (i == 3) builder.writeln("价格");
                if (i == 4) builder.writeln("因素");
            }
            builder.endRow();
            for (SchemeJudgeObjectVo schemeJudgeObject : schemeJudgeObjectList) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord == null) {
                    declareRecord = new DeclareRecord();
                }
                for (int i = 0; i < 5; i++) {
                    builder.insertCell();
                    if (i == 0) {
                        if (StringUtils.isNotBlank(declareRecord.getName())) {
                            builder.writeln(declareRecord.getName());
                        }
                    }
                    if (i == 1) {
                        if (StringUtils.isNotBlank(declareRecord.getFloor())) {
                            builder.writeln(declareRecord.getFloor());
                        }
                    }
                    if (i == 2) {
                        if (StringUtils.isNotBlank(declareRecord.getRoomNumber())) {
                            builder.writeln(declareRecord.getRoomNumber());
                        }
                    }
                    if (i == 3) {
                        if (declareRecord.getPrice() != null) {
                            builder.writeln(declareRecord.getPrice().toString());
                        }
                    }
                    if (i == 4) {
                        if (StringUtils.isNotBlank(schemeJudgeObject.getCoefficient())) {
                            builder.writeln(schemeJudgeObject.getCoefficient());
                        }
                    }
                }
                builder.endRow();
            }
            builder.endTable();
        }
        generateCommonMethod.settingBuildingTable(builder);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 分类评估方法结果
     */
    public String getEvaluationMethodResult() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int k = 0; k < schemeJudgeObjectList.size(); k++) {
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(k);
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSchemeSurePriceBySchemeJudgeObjectId(schemeJudgeObject.getId());
                if (CollectionUtils.isEmpty(schemeSurePriceItemList)) {
                    continue;
                }
                builder.writeln(String.format("%s", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                    if (StringUtils.isNotBlank(schemeSurePriceItem.getMethodName()) && schemeSurePriceItem.getTrialPrice() != null) {
                        builder.writeln(String.format("%s%s元", schemeSurePriceItem.getMethodName(), schemeSurePriceItem.getTrialPrice().toString()));
                    }
                }
                if (schemeSurePrice != null && schemeSurePrice.getPrice() != null) {
                    builder.writeln(String.format("最终单价%s元", schemeSurePrice.getPrice().toString()));
                }
                builder.writeln();
            }
        }
        doc.save(localPath);
        return localPath;
    }

    //房地产总价
    private BigDecimal getTotalRealEstate() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
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
        return generateCommonMethod.getBigDecimalRound(getTotalRealEstate(), true);
    }

    /**
     * 房地产总价 大写金额
     *
     * @return
     */
    public String getCapitalizationAmount() {
        String s = CnNumberUtils.toUppercaseSubstring(generateCommonMethod.getBigDecimalRound(getTotalRealEstate(), false));
        return s;
    }

    /**
     * 法定优先受偿款
     */
    public String getStatutoryOptimumReimbursement() {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> map = schemeReimbursementService.getSchemeReimbursementItemVoMapAndSchemeJudgeObject(schemeJudgeObjectList, projectId);
            if (!map.isEmpty()) {
                map.entrySet().stream().filter(entry -> entry.getKey().getDeclareRecordId() != null).forEach(entry -> {
                    List<SchemeReimbursementItemVo> itemVos = entry.getValue();
                    if (CollectionUtils.isNotEmpty(itemVos)) {
                        itemVos.stream().forEach(oo -> {
                            String s = schemeReimbursementService.getFullDescription(oo);
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
            s = errorStr;
        }
        return s;
    }

    /**
     * 特别提示
     * @throws Exception
     */
    public String getHotTip() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(16);
        Document doc = new Document();
        LinkedHashSet<String> stringSet = Sets.newLinkedHashSet();
        String localPath = getLocalPath();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        int row = 0;
        if (true) {
            stringSet.add("本函内容摘自估价报告");
            stringSet.add("欲了解本次估价项目全面情况");
            stringSet.add("请详见估价结果报告");
            stringSet.add("报告使用时请特别关注估价假设和限制条件内容。");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))).append("</p>");
            stringSet.clear();
            row++;
        }
        if (true) {
            Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> hashMap = getSurveyAssetInventoryRightMapAndSchemeJudgeObject();
            final String mortgage = "抵押权";
            List<Integer> integerList = Lists.newArrayList();
            if (!hashMap.isEmpty()) {
                hashMap.entrySet().stream().forEach(entry -> {
                    if (CollectionUtils.isNotEmpty(entry.getValue())) {
                        entry.getValue().stream().forEach(oo -> {
                            if (oo.getCategory() != null) {
                                if (Objects.equal(mortgage, baseDataDicService.getNameById(oo.getCategory()))) {
                                    integerList.add(generateCommonMethod.parseIntJudgeNumber(entry.getKey().getNumber()));
                                }
                            }
                        });
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(integerList)) {
                stringSet.add(String.format("%s号有%s", StringUtils.join(integerList.stream().distinct().collect(Collectors.toList()), "、"), mortgage));
                stringSet.add("根据委托人介绍及估价人员在");
                stringSet.add(erpAreaService.getAreaFullName(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict()));
                DataHisRightInfoPublicity infoPublicity = dataHisRightInfoPublicityService.getDataHisRightInfoPublicity(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
                String value = null;
                if (infoPublicity != null) {
                    value = infoPublicity.getContent();
                }
                if (StringUtils.isNotBlank(value)) {
                    stringSet.add(String.format("房地产评估管理服务信息系统 %s", value));
                } else {
                    stringSet.add(String.format("房地产评估管理服务信息系统 %s", "（http://fcpg.cdfgj.gov.cn/）"));
                }
                stringSet.add("上查询了解得知，截止价值时点，估价对象已设定");
                stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))).append("</p>");
                stringSet.clear();
                row++;
            }
        }
        String addressAssetInventory = getActualAddressAssetInventory();
        String certificateAssetInventory = getCertificateAssetInventory();
        if (StringUtils.isNotBlank(addressAssetInventory) && StringUtils.isNotBlank(certificateAssetInventory)) {
            stringSet.add(String.format("估价对象现场查勘地址为%s", addressAssetInventory));
            stringSet.add(String.format("本次评估根据委托方提供的由%s", certificateAssetInventory));
            stringSet.add("本次以上地址为同一地址。");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))).append("</p>");
            stringSet.clear();
            row++;
        }
        List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = getSchemeReimbursementItemVoList();
        if (CollectionUtils.isNotEmpty(schemeReimbursementItemVoList)) {
            BigDecimal bigDecimal = new BigDecimal(0);
            if (CollectionUtils.isNotEmpty(schemeReimbursementItemVoList)) {
                for (SchemeReimbursementItemVo schemeReimbursementItemVo : schemeReimbursementItemVoList) {
                    if (schemeReimbursementItemVo.getNotSetUpTotalPrice() != null) {
                        bigDecimal = bigDecimal.add(schemeReimbursementItemVo.getNotSetUpTotalPrice());
                    }
                }
            }
            String s = generateCommonMethod.getBigDecimalRound(bigDecimal, false);
            stringSet.add("根据估价委托人提供的《法定优先受偿款情况说明》");
            stringSet.add("估价对象于价值时点已设定抵押权");
            stringSet.add("本次评估是抵押权存续期间的房地产估价（同行续贷）");
            stringSet.add("经过沟通");
            stringSet.add("抵押权人已经知晓法定优先受偿款对估价对象价值的影响");
            stringSet.add("且并不需要我们在抵押价值中予以扣除法定优先受偿款");
            stringSet.add(String.format("故本报告假设估价对象在价值时点法定优先受偿款为%s元（大写：%s ）", s, CnNumberUtils.toUppercaseSubstring(generateCommonMethod.getBigDecimalRound(bigDecimal, false))));
            stringSet.add("在此提请报告使用人加以关注。");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))).append("</p>");
            stringSet.clear();
        }
        documentBuilder.insertHtml(stringBuilder.toString(), true);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象权属
     * @throws Exception
     */
    public String getEquityStatusObjectSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId).stream().filter(schemeJudgeObject -> schemeJudgeObject.getDeclareRecordId() != null).collect(Collectors.toList());
        int rowLength = schemeJudgeObjectList.size() + 1;
        int cellLength = 7;
        for (int i = 0; i < rowLength; i++) {
            SchemeJudgeObject schemeJudgeObject = null;
            DeclareRecord declareRecord = null;
            if (i != 0) {
                schemeJudgeObject = schemeJudgeObjectList.get(i - 1);
                declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            }
            for (int j = 0; j < cellLength + 1; j++) {
                if (j < cellLength) {
                    builder.insertCell();
                }
                if (i == 0) {
                    switch (j) {
                        case 0:
                            builder.writeln("估价序号");
                            break;
                        case 1:
                            builder.writeln("权证号");
                            break;
                        case 2:
                            builder.writeln("所有权人");
                            break;
                        case 3:
                            builder.writeln("共有情况");
                            break;
                        case 4:
                            builder.writeln("证载用途");
                            break;
                        case 5:
                            builder.writeln("房屋性质");
                            break;
                        case 6:
                            builder.writeln("面积");
                            break;
                        case 7:
                            builder.endRow();
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (j) {
                        case 0:
                            builder.writeln(String.format("%s%s", schemeJudgeObject.getNumber(), "号"));
                            break;
                        case 1:
                            builder.writeln(String.format("%s%s", declareRecord.getName(), ""));
                            break;
                        case 2:
                            builder.writeln(String.format("%s%s", declareRecord.getOwnership(), ""));
                            break;
                        case 3:
                            if (true) {
                                String v = "";
                                if (StringUtils.isNotBlank(declareRecord.getPublicSituation())) {
                                    if (NumberUtils.isNumber(declareRecord.getPublicSituation())) {
                                        v = baseDataDicService.getNameById(declareRecord.getPublicSituation());
                                        if (StringUtils.isEmpty(v)) {
                                            v = declareRecord.getPublicSituation();
                                        }
                                    } else {
                                        v = declareRecord.getPublicSituation();
                                    }
                                }
                                builder.writeln(String.format("%s%s", v, ""));
                            }
                            break;
                        case 4:
                            builder.writeln(String.format("%s%s", declareRecord.getCertUse(), ""));
                            break;
                        case 5:
                            String v = "";
                            if (StringUtils.isNotBlank(declareRecord.getNature())) {
                                if (NumberUtils.isNumber(declareRecord.getNature())) {
                                    v = baseDataDicService.getNameById(declareRecord.getNature());
                                    if (StringUtils.isEmpty(v)) {
                                        v = declareRecord.getNature();
                                    }
                                } else {
                                    v = declareRecord.getNature();
                                }
                            }
                            builder.writeln(String.format("%s%s", v, ""));
                            break;
                        case 6:
                            if (declareRecord.getFloorArea() != null) {
                                builder.writeln(String.format("%s%s", declareRecord.getFloorArea().toString(), "㎡"));
                            }
                            break;
                        case 7:
                            builder.endRow();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        String localPath = getLocalPath();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 变现分析表
     */
    public String getLiquidationAnalysis() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        String localPath = getLocalPath();
        createLiquidationAnalysisTable(builder);
        doc.save(localPath);
        return localPath;
    }

    public void createLiquidationAnalysisTable(DocumentBuilder builder) throws Exception {
        List<SchemeLiquidationAnalysisItem> itemList = schemeLiquidationAnalysisService.getAnalysisItemListByAreaId(areaId);
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByAreaId(areaId);
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Table table = builder.startTable();
        //物业类型、税率、计算基数、计算公式、税费负担方、商业
        int rowLength = 3 + itemList.size() + 1;
        int cellLength = 6;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < cellLength + 1; j++) {
                //目的是自动插入单元格并且确保只插入每行6个(列6个),6+1原因是最后一个索引用做结束行
                if (j < cellLength) {
                    builder.insertCell();
                }
                if (i == 0) {
                    switch (j) {
                        case 0:
                            builder.writeln("物业类型");
                            break;
                        case 1:
                            builder.writeln("税率");
                            break;
                        case 2:
                            builder.writeln("计算基数");
                            break;
                        case 3:
                            builder.writeln("计算公式");
                            break;
                        case 4:
                            builder.writeln("税费负担方");
                            break;
                        case 5:
                            builder.writeln("商业");
                            break;
                        case 6:
                            builder.endRow();
                            break;
                        default:
                            break;
                    }
                }
                if (i == 1) {
                    switch (j) {
                        case 0:
                            builder.writeln("面积");
                            break;
                        case 1:
                            builder.writeln("/");
                            break;
                        case 2:
                            builder.writeln("/");
                            break;
                        case 3:
                            builder.writeln("/");
                            break;
                        case 4:
                            builder.writeln("/");
                            break;
                        case 5:
                            builder.writeln(schemeAreaGroupService.getAreaEvaluateArea(schemeJudgeObjectFullList).toString());
                            break;
                        case 6:
                            builder.endRow();
                            break;
                        default:
                            break;
                    }
                }
                if (i == 2) {
                    switch (j) {
                        case 0:
                            builder.writeln("评估价");
                            break;
                        case 1:
                            builder.writeln("/");
                            break;
                        case 2:
                            builder.writeln("/");
                            break;
                        case 3:
                            builder.writeln("/");
                            break;
                        case 4:
                            builder.writeln("/");
                            break;
                        case 5:
                            builder.writeln(schemeAreaGroupService.getAreaEvaluatePrice(schemeJudgeObjectFullList).toString());
                            break;
                        case 6:
                            builder.endRow();
                            break;
                        default:
                            break;
                    }
                }
                if (i >= 3 && i < 3 + itemList.size()) {
                    SchemeLiquidationAnalysisItem item = itemList.get(i - 3);
                    switch (j) {
                        case 0:
                            builder.writeln(StringUtils.isNotBlank(item.getTaxRateName()) ? item.getTaxRateName() : "空");
                            break;
                        case 1:
                            if (item.getCalculationMethod() == 1 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                                builder.writeln(new BigDecimal(item.getTaxRateValue()).multiply(new BigDecimal("100")).stripTrailingZeros().toString() + "%");
                            } else if (item.getCalculationMethod() == 0 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                                builder.writeln(item.getTaxRateValue() + "元/㎡");
                            } else {
                                builder.writeln("空");
                            }
                            break;
                        case 2:
                            if (StringUtils.isNotBlank(item.getCalculateBase())) {
                                builder.writeln(item.getCalculateBase());
                            } else {
                                builder.writeln("空");
                            }
                            break;
                        case 3:
                            if (StringUtils.isNotBlank(item.getCalculationFormula())) {
                                builder.writeln(item.getCalculationFormula());
                            } else {
                                builder.writeln("空");
                            }
                            break;
                        case 4:
                            if (StringUtils.isNotBlank(item.getTaxesBurden())) {
                                builder.writeln(item.getTaxesBurden());
                            } else {
                                builder.writeln("空");
                            }
                            break;
                        case 5:
                            if (!StringUtils.isEmpty(item.getPrice().toString())) {
                                builder.writeln(item.getPrice().toString());
                            } else {
                                builder.writeln("空");
                            }
                            break;
                        case 6:
                            builder.endRow();
                            break;
                        default:
                            break;
                    }
                }
                if (i >= 3 + itemList.size() && i < 3 + itemList.size() + 1) {
                    switch (j) {
                        case 0:
                            builder.writeln("合计费用");
                            break;
                        case 1:
                            mergeCellModelList.add(new MergeCellModel(i, j, i, 5));
                            if (schemeLiquidationAnalysis.getTotal() != null) {
                                builder.writeln(schemeLiquidationAnalysis.getTotal().toString());
                            } else {
                                builder.writeln("无");
                            }
                            break;
                        case 2:
                            builder.writeln("");
                            break;
                        case 3:
                            builder.writeln("");
                            break;
                        case 4:
                            builder.writeln("");
                            break;
                        case 5:
                            builder.writeln("");
                            break;
                        case 6:
                            builder.endRow();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        }
        builder.endTable();
    }


    /**
     * 评估方法 , 估价对象评估方法
     * @throws Exception
     */
    public String getEvaluationMethodValuationObject() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (int i = 0; i < schemeJudgeFunctionList.size(); i++) {
                        stringSet.add(schemeJudgeFunctionList.get(i).getName());
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetStringMerge(stringSet, ",");
        return s;
    }

    /**
     * 估价对象选择估价方法
     */
    public String getSelectionValuationMethod() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        StringBuilder stringBuilder = new StringBuilder(8);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    stringBuilder.append(getSchemeJudgeObjectShowName(schemeJudgeObject));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (StringUtils.isNotBlank(schemeJudgeFunction.getApplicableReason())) {
                            stringBuilder.append(schemeJudgeFunction.getName()).append("估价方法:");
                            stringBuilder.append(schemeJudgeFunction.getApplicableReason()).append(";");
                        }
                    }
                    builder.writeln(stringBuilder.toString());
                    stringBuilder.delete(0, builder.toString().length());
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }


    /**
     * 委托目的表述
     */
    public String getStatementPurposeEntrustment() {
        String statementPurposeEntrustment = getSchemeAreaGroup().getRemarkEntrustPurpose();
        if (StringUtils.isNotBlank(statementPurposeEntrustment.trim())) {
            return statementPurposeEntrustment;
        } else {
            return errorStr;
        }
    }

    /**
     *
     * 功能描述: 委托目的
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
     *
     * 功能描述: 价值类型描述
     * @author: zch
     * @date: 2019/4/8 11:47
     */
    public String getValueTypeDesc() {
        String value = getSchemeAreaGroup().getValueDefinitionDesc();
        if (StringUtils.isNotBlank(value.trim())) {
            return value;
        } else {
            return errorStr;
        }
    }


    /**
     * 注册房产估价师
     * @param str
     * @return
     */
    public String getRegisteredRealEstateValuer(String str) {
        String[] strings = str.split(",");
        Set<String> stringSet = Sets.newLinkedHashSet();
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccountName())) {
                    stringSet.add(dataQualificationVo.getUserAccountName());
                }
            }
        }
        String s = generateCommonMethod.toSetStringMerge(stringSet, " ");
        if (StringUtils.isEmpty(s.trim())) {
            return errorStr;
        }
        return s;
    }

    /**
     * 注册房产估价师及注册号
     * @param generateReportGeneration
     * @throws Exception
     */
    public String getRegisteredRealEstateValuerAndNumber(GenerateReportGeneration generateReportGeneration) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        StringBuilder stringBuilder = new StringBuilder(8);
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(document);
        String[] strings = generateReportGeneration.getRealEstateAppraiser().split(",");
        stringBuilder.append("<p>");
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccountName())) {
                    stringBuilder.append(dataQualificationVo.getUserAccountName());
                }
                stringBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;");
                stringBuilder.append("注册证号:");
                for (String account : dataQualificationVo.getUserAccount().split(",")) {
                    List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                    if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                        adPersonalQualificationDtoList.stream().forEach(adPersonalQualificationDto -> stringBuilder.append(adPersonalQualificationDto.getCertificateNo()));
                    }
                }
                stringBuilder.append("<br>");
            }
        }
        stringBuilder.append("</p>");
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        document.save(localPath);
        return localPath;
    }

    /**
     * 注册房产估价师 编号
     * @param str
     * @throws Exception
     */
    public String getRegistrationNumber(String str) throws Exception {
        Set<String> stringSet = Sets.newLinkedHashSet();
        String[] strings = str.split(",");
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                    for (String account : dataQualificationVo.getUserAccount().split(",")) {
                        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                            adPersonalQualificationDtoList.stream().forEach(adPersonalQualificationDto -> stringSet.add(adPersonalQualificationDto.getCertificateNo()));
                        }
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetStringMerge(stringSet, ",");
        if (StringUtils.isNotBlank(s.trim())) {
            return s;
        }
        return errorStr;
    }

    /**
     * 房地产估价机构信息
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
        stringBuilder.append("<p>");
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "机构名称", qualificationDto.getOrganizationName())));
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "住所", qualificationDto.getOrganizationAddress())));
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "法定代表人", qualificationDto.getLegalRepresentative())));
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "工商注册号", qualificationDto.getRegisteredNo())));
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "资质等级", qualificationDto.getOrganizationRank())));
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "资质证书编号", qualificationDto.getCertificateNo())));
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "资质证书有效期", qualificationDto.getCertificateEffectiveDate())));
        stringBuilder.append(generateCommonMethod.getWarpCssHtml(String.format("%s:%s", "经营范围", StringUtils.isEmpty(qualificationDto.getBusinessScopeName()) ? "评估房产" : qualificationDto.getBusinessScopeName())));
        stringBuilder.append("</p>");
        documentBuilder.insertHtml(stringBuilder.toString(), true);
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
        return StringUtils.isEmpty(qualificationDto.getBusinessScopeName()) ? "评估房产" : qualificationDto.getBusinessScopeName();
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
        return String.format("%s%s", DateUtils.format(start, DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN));
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
            //案例同样通过
            if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
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
    public String getPrincipleBasisHypothesis(SchemeSupportTypeEnum schemeSupportTypeEnum, Integer areaId) throws Exception {
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
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(result), true);
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
    public String getLiquidityRisk(SchemeSupportTypeEnum schemeSupportTypeEnum, Integer areaId) throws Exception {
        if (projectInfo == null || schemeSupportTypeEnum == null) return "";
        String result = "";
        switch (schemeSupportTypeEnum) {
            case REPORT_ANALYSIS_CATEGORY_LIQUIDITY:
                result = dataReportAnalysisService.getReportLiquidity(this.projectInfo, areaId);
                break;
            case REPORT_ANALYSIS_CATEGORY_RISK:
                result = dataReportAnalysisRiskService.getReportRisk();
                break;
        }
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(result), true);
        document.save(localPath);
        return localPath;
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
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.trim(compileReportService.getReportCompile(this.areaId, type))), true);
        document.save(localPath);
        return localPath;
    }

    /**
     * 协助工作人员
     *
     * @return
     */
    public String getAssistanceStaff(String str) {
        String[] ids = str.split(",");
        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(projectInfo.getProjectTypeId(),
                projectInfo.getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
            if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        for (String id : ids) {
            if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
                projectPhaseVos.stream().forEach(projectPhaseScene -> {
                    DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(projectId);
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            projectPlanDetailsList.stream().forEach(projectPlanDetails -> {
                                if (StringUtils.isNotBlank(projectPlanDetails.getExecuteUserAccount())) {
                                    if (dataQualificationVo != null) {
                                        if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                                            for (String account : dataQualificationVo.getUserAccount().split(",")) {
                                                if (Objects.equal(account, projectPlanDetails.getExecuteUserAccount())) {

                                                } else {
                                                    stringSet.add(publicService.getUserNameByAccount(projectPlanDetails.getExecuteUserAccount()));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        return s;
    }

    /**
     * 权利人
     *
     * @return
     */
    public String getPowerPerson() {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true);
        return generateCommonMethod.getPowerPerson(schemeJudgeObjectList);
    }


    /**
     * 设定用途
     *
     * @return
     */
    public String getSetUse() {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        String s = generateCommonMethod.getSetUses(schemeJudgeObjectList);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }


    /**
     * 实际用途
     *
     * @return
     */
    public String getPracticalUse() {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        String s = generateCommonMethod.getPracticalUse(schemeJudgeObjectList);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 建筑结构类别
     *
     * @return
     * @throws Exception
     */
    public String getBuildingStructureCategory() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        return generateCommonMethod.getBuildingStructureCategory(schemeJudgeObjectList, projectInfo);
    }

    /**
     * 税费负担
     *
     * @return
     * @throws Exception
     */
    public String getTaxBurden() throws Exception {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicTrading().getTaxBurden() != null) {
                                    String key = baseDataDicService.getNameById(generateBaseExamineService.getBasicTrading().getTaxBurden());
                                    generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, key);
                                }
                            }
                        }
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
     * 付款方式
     *
     * @return
     * @throws Exception
     */
    public String getPaymentMethod() throws Exception {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicTrading().getPaymentMethod() != null) {
                                    String key = baseDataDicService.getNameById(generateBaseExamineService.getBasicTrading().getPaymentMethod());
                                    generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, key);
                                }
                            }
                        }
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
     * 使用权类型
     *
     * @return
     */
    public String getUseRightType() {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true);
        return generateCommonMethod.getUseRightType(schemeJudgeObjectList);
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
                generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, schemeJudgeObject.getRentalPossessionDesc());
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
     * 他权有无租赁权
     *
     * @return
     * @throws Exception
     */
    public String getHisRightHasLease() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        final String temp = "他权有无租赁权:";
        stringBuilder.append(temp);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE_HOUSE_LEASEHOLD);
        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = Lists.newArrayList();
        Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> map = getSurveyAssetInventoryRightMapAndSchemeJudgeObject();
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                List<SurveyAssetInventoryRight> list = entry.getValue();
                if (CollectionUtils.isNotEmpty(list)) {
                    surveyAssetInventoryRightList.addAll(list);
                }
            });
        }
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                    if (Objects.equal(inventoryRight.getCategory(), baseDataDic.getId())) {
                        stringBuilder.append("有");
                        break;
                    }
                }
            }
        }
        if (Objects.equal(stringBuilder.toString(), temp)) {
            stringBuilder.append("无");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * 功能描述: 有无他项权
     *
     * @auther: zch
     * @date: 2019/2/26 15:10
     */
    public String getThereAnyOtherRight() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> map = getSurveyAssetInventoryRightMapAndSchemeJudgeObject();
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                boolean flag = false;
                List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = entry.getValue();
                if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                    for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                        if (StringUtils.isNotBlank(inventoryRight.getNumber())) {
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    stringSet.add(String.format("%s%s", entry.getKey().getName(), ":有他项权"));
                } else {
                    stringSet.add(String.format("%s%s", entry.getKey().getName(), ":无他项权"));
                }
            });
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        return s;
    }

    /**
     * 他权类别
     *
     * @return
     * @throws Exception
     */
    public String getHisRightType() throws Exception {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> map = getSurveyAssetInventoryRightMapAndSchemeJudgeObject();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE);
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = entry.getValue();
                if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                    for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                        if (projectInfo.getEntrustPurposeName().equals(baseDataDic.getName())) {
                            String key = baseDataDicService.getNameById(inventoryRight.getType());
                            if (StringUtils.isNotBlank(key)) {
                                generateCommonMethod.putStringListMap(stringListMap, entry.getKey(), key);
                            }
                        }
                    }
                }
            });
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 他权其它
     *
     * @return
     * @throws Exception
     */
    public String getRightOther() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> map = getSurveyAssetInventoryRightMapAndSchemeJudgeObject();
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        Set<String> stringSet = Sets.newHashSet();
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = entry.getValue();
                if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                    for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                        if (inventoryRight.getCategory() == null) {
                            continue;
                        }
                        if (StringUtils.isEmpty(inventoryRight.getNumber()) || StringUtils.isEmpty(inventoryRight.getRegisterArea())) {
                            continue;
                        }
                        if (StringUtils.isEmpty(inventoryRight.getObligee()) || StringUtils.isEmpty(inventoryRight.getObligor())) {
                            continue;
                        }
                        stringBuilder.append(baseDataDicService.getNameById(inventoryRight.getCategory()));
                        stringBuilder.append("-").append(inventoryRight.getNumber());
                        stringBuilder.append("-").append(inventoryRight.getRegisterArea()).append("㎡");
                        stringBuilder.append("-").append(inventoryRight.getObligee());
                        stringBuilder.append("-").append(inventoryRight.getObligor());
                        stringSet.add(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.toString().length());
                    }
                    if (CollectionUtils.isNotEmpty(stringSet)) {
                        generateCommonMethod.putStringListMap(stringListMap, entry.getKey(), StringUtils.join(stringSet, "、"));
                        stringSet.clear();
                    }
                }
            });
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 功能描述: 资产清查实际地址
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:02
     */
    public String getActualAddressAssetInventory() throws Exception {
        //getActual == > actual
        String s = getAssetInventoryCommon("actual");
        return s;
    }

    /**
     * 功能描述: 资产清查证明人
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:10
     */
    public String getCertificateAssetInventory() throws Exception {
        //getVoucher == > voucher
        String s = getAssetInventoryCommon("voucher");
        return s;
    }

    /**
     * 功能描述: 资产清查确认一致
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:16
     */
    public String getAssetInventoryConfirmConsistency() throws Exception {
        //getSureConsistent == > sureConsistent
        String s = getAssetInventoryCommon("sureConsistent");
        return s;
    }

    /**
     * 功能描述: 资产清查一致说明
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:22
     */
    public String getAssetInventoryAgreement() throws Exception {
        //getDifferenceReason == > differenceReason
        String s = getAssetInventoryCommon("differenceReason");
        return s;
    }

    /**
     * 资产清查 提取的公共方法
     *
     * @param fieldName
     * @return
     * @throws Exception
     */
    private String getAssetInventoryCommon(String fieldName) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(16);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                List<SurveyAssetInventoryContent> surveyAssetInventoryContentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(projectPlanDetails.getId());
                                if (CollectionUtils.isNotEmpty(surveyAssetInventoryContentList)) {
                                    for (SurveyAssetInventoryContent surveyAssetInventoryContent : surveyAssetInventoryContentList) {
                                        if (Objects.equal("不一致", surveyAssetInventoryContent.getAreConsistent())) {
                                            stringBuilder.append(getSchemeJudgeObjectShowName(schemeJudgeObject)).append(":");
                                            String value = (String) Reflections.getFieldValue(surveyAssetInventoryContent, fieldName);
                                            stringBuilder.append(value);
                                            stringSet.add(stringBuilder.toString());
                                            stringBuilder.delete(0, stringBuilder.toString().length());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String s = StringUtils.join(stringSet, "、");
        return s;
    }

    /**
     * 担保物权设立情况
     *
     * @return
     * @throws Exception
     */
    public String getCollateralFound() throws Exception {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, schemeJudgeObject.getCollateralFound());
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 最佳利用描述
     *
     * @return
     * @throws Exception
     */
    public String getOptimumUtilizationDescription() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getBestUse() != null) {
                    String key = baseDataDicService.getNameById(schemeJudgeObject.getBestUse());
                    generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, key);
                }
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, "");
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 法定优先受偿款总金额
     *
     * @return
     * @throws Exception
     */
    public String getStatutoryPriorityAmountTotal() throws Exception {
        BigDecimal bigDecimal = new BigDecimal(0);
        List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = getSchemeReimbursementItemVoList();
        if (CollectionUtils.isNotEmpty(schemeReimbursementItemVoList)) {
            for (SchemeReimbursementItemVo schemeReimbursementItemVo : schemeReimbursementItemVoList) {
                if (schemeReimbursementItemVo.getNotSetUpTotalPrice() != null) {
                    bigDecimal = bigDecimal.add(schemeReimbursementItemVo.getNotSetUpTotalPrice());
                }
            }
        }
        if (bigDecimal.doubleValue() > 0) {
            bigDecimal = new BigDecimal(generateCommonMethod.getBigDecimalRound(bigDecimal, true));
        }
        return bigDecimal.toString();
    }

    /**
     * 法定优先受偿款金额
     *
     * @return
     * @throws Exception
     */
    public String getStatutoryPriorityAmount() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> map = schemeReimbursementService.getSchemeReimbursementItemVoMapAndSchemeJudgeObject(getSchemeJudgeObjectList(), projectId);
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = entry.getValue();
                if (CollectionUtils.isNotEmpty(schemeReimbursementItemVoList)) {
                    schemeReimbursementItemVoList = schemeReimbursementItemVoList.stream().distinct().collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(schemeReimbursementItemVoList)) {
                        BigDecimal bigDecimal = new BigDecimal(0);
                        for (SchemeReimbursementItemVo schemeReimbursementItemVo : schemeReimbursementItemVoList) {
                            if (schemeReimbursementItemVo.getNotSetUpTotalPrice() != null) {
                                bigDecimal = bigDecimal.add(schemeReimbursementItemVo.getNotSetUpTotalPrice());
                            }
                        }
                        stringSet.add(String.format("%s:%s、", generateCommonMethod.getSchemeJudgeObjectShowName(entry.getKey()), bigDecimal.toString()));

                    }
                }
            });
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = "0";
        }
        return s;
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

    private String getAssistThinkAndApplicableReasonOrNotApplicableReason(String key, boolean think, boolean applicableReason, boolean notApplicableReason) {
        StringBuilder builder = new StringBuilder(8);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(key);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                if (think) {
                                    builder.append(schemeJudgeFunction.getThinking()).append("-");
                                }
                                if (applicableReason) {
                                    builder.append(schemeJudgeFunction.getApplicableReason()).append("-");
                                }
                                if (notApplicableReason) {
                                    builder.append(schemeJudgeFunction.getNotApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        if (StringUtils.isNotBlank(s.trim())) {
            s = errorStr;
        }
        return s;
    }


    /**
     * 功能描述: 估价对象市场价值的确定
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 16:25
     */
    public String getDeterminationMarketValueValuationObject() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.writeln(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    StringBuilder stringBuilder = new StringBuilder(16);
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("与");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("测算结果相近，通过对该区域的调查，考虑估价对象在该区域内的具体位置等因素，");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("的试算结果与");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("试算结果均能反映估价对象市场价值，");
                    //权重说明
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
                            if (schemeSurePrice != null) {
                                stringBuilder.append(schemeSurePrice.getWeightExplain());
                            }
                        }
                    }
                    builder.writeln(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());

                    stringBuilder.append(getSchemeJudgeObjectShowName(schemeJudgeObject)).append("的单价=").append(getEvaluationExpression());
                    List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                    if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                        for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                            if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdCompare.getName())) {
                                if (schemeSurePriceItem.getTrialPrice() != null && schemeSurePriceItem.getWeight() != null) {
                                    java.math.BigDecimal bigDecimal = schemeSurePriceItem.getWeight().multiply(new BigDecimal(100));
                                    bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                                    stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(String.format("%s%s", bigDecimal.toString(), "%"));
                                    stringBuilder.append("+");
                                }
                            }
                            if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdIncome.getName())) {
                                if (schemeSurePriceItem.getTrialPrice() != null && schemeSurePriceItem.getWeight() != null) {
                                    java.math.BigDecimal bigDecimal = schemeSurePriceItem.getWeight().multiply(new BigDecimal(100));
                                    bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                                    stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(String.format("%s%s", bigDecimal.toString(), "%"));
                                }
                            }
                        }
                    }
                    builder.writeln(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 主要计算过程
     *
     * @return
     * @throws Exception
     */
    public String getComputationProcess() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();

        DataEvaluationMethod dataEvaluationMethodMdCompare = evaluationMethodService.getMethodAllList().stream().filter(dataEvaluation -> {
            if (Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), dataEvaluation.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        DataEvaluationMethod dataEvaluationMethodMdIncome = evaluationMethodService.getMethodAllList().stream().filter(dataEvaluation -> {
            if (Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), dataEvaluation.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        DataMethodFormula compareFormula = null;
        DataMethodFormula mdIncomeFormula = null;

        if (dataEvaluationMethodMdCompare != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethodMdCompare.getMethod());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                compareFormula = dataMethodFormulaList.get(0);
            }
        }
        if (dataEvaluationMethodMdIncome != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethodMdIncome.getMethod());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                mdIncomeFormula = dataMethodFormulaList.get(0);
            }
        }

        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                documentBuilder.writeln(getSchemeJudgeObjectShowName(schemeJudgeObject));
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdCompare.getName())) {
                            documentBuilder.writeln(CalculationMethodNameEnum.MdCompare.getName());
                            if (compareFormula != null && StringUtils.isNotBlank(compareFormula.getFormula())) {
                                documentBuilder.writeln(String.format("%s:%s", "公式", compareFormula.getFormula()));
                            } else {
                                documentBuilder.writeln(String.format("%s:%s", "公式", "无"));
                            }
                        }
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdIncome.getName())) {
                            documentBuilder.writeln(CalculationMethodNameEnum.MdIncome.getName());
                            if (mdIncomeFormula != null && StringUtils.isNotBlank(mdIncomeFormula.getFormula())) {
                                documentBuilder.writeln(String.format("%s:%s", "公式", mdIncomeFormula.getFormula()));
                            } else {
                                documentBuilder.writeln(String.format("%s:%s", "公式", "无"));
                            }
                        }
                    }
                }
            }
        }
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
        if (linkedHashMap.isEmpty()) return "";
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : linkedHashMap.entrySet()) {
            //根据不同项目类别确定获取数据的方法
            if (linkedHashMap.size() > 1) {//添加楼盘或估价对象编号作区分
                builder.insertHtml(generateCommonMethod.getWarpCssHtml("<div style='text-align:center;;font-size:16.0pt;'>"+entry.getKey().getName()+"</div>"));
            }
            if (projectInfo.getProjectCategoryName().contains("房产")) {
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml("1、土地权益状况")));
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateEquityService.getLandEquity(entry.getKey(), entry.getValue()))), false);
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml("2、房屋权益状况")));
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(generateEquityService.getHouseEquity(entry.getValue(), projectId))), false);
            } else if (projectInfo.getProjectCategoryName().contains("土地")) {
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml("1、土地权益状况")));
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateEquityService.getLandEquityFull(entry.getKey(), entry.getValue(), projectId)), false);
            }
        }
        String localPath = getLocalPath();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 法定优先受偿款
     * @param schemeReimbursementItemVoList
     * @return
     * @throws Exception
     */
    private BigDecimal getSchemeReimbursementSetUpTotalPrice(List<SchemeReimbursementItemVo> schemeReimbursementItemVoList) {
        BigDecimal notSetUpTotalPrice = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeReimbursementItemVoList)) {
            for (SchemeReimbursementItemVo schemeReimbursementItemVo : schemeReimbursementItemVoList) {
                if (schemeReimbursementItemVo.getNotSetUpTotalPrice() != null) {
                    notSetUpTotalPrice = notSetUpTotalPrice.add(schemeReimbursementItemVo.getNotSetUpTotalPrice());
                }
            }
            if (notSetUpTotalPrice.doubleValue() > 0) {
                notSetUpTotalPrice = notSetUpTotalPrice.divide(new BigDecimal(10000));
                notSetUpTotalPrice = notSetUpTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }
        return notSetUpTotalPrice;
    }

    /**
     * 估价结果一览表
     * @throws Exception
     */
    public String getjudgeBuildResultSurveySheet() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        Map<SchemeJudgeObject, List<SchemeReimbursementItemVo>> schemeJudgeObjectListMap = schemeReimbursementService.getSchemeReimbursementItemVoMapAndSchemeJudgeObject(schemeJudgeObjectList, projectId);
        schemeJudgeObjectList = generateCommonMethod.getSortSchemeJudgeObject(schemeJudgeObjectList);
        LinkedHashMap<BasicApply, SchemeJudgeObject> schemeJudgeObjectLinkedHashMap = Maps.newLinkedHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null) {
                continue;
            }
            schemeJudgeObjectLinkedHashMap.put(basicApply, schemeJudgeObject);
        }
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        generateCommonMethod.settingBuildingTable(builder);
        String localPath = getLocalPath();
        Table table = builder.startTable();
        final int colMax = 11;
        for (int j = 0; j < colMax; j++) {
            builder.insertCell();
            if (j == 0) builder.writeln("估价对象");
            if (j == 1) builder.writeln("坐落");
            if (j == 2) builder.writeln("用途(证载)");
            if (j == 3) builder.writeln("用途(实际)");
            if (j == 4) builder.writeln("房屋总层数");
            if (j == 5) builder.writeln("所在层数");
            if (j == 6) builder.writeln("建筑面积㎡");
            if (j == 7) builder.writeln("单价（元/㎡）");
            if (j == 8) builder.writeln("评估总价（万元）");
            if (j == 9) builder.writeln("法定优先受偿款(万元)");
            if (j == 10) builder.writeln("抵押价值(万元)");
        }
        builder.endRow();
        if (!schemeJudgeObjectLinkedHashMap.isEmpty()) {
            for (Map.Entry<BasicApply, SchemeJudgeObject> integerEntry : schemeJudgeObjectLinkedHashMap.entrySet()) {
                for (int j = 0; j < colMax; j++) {
                    builder.insertCell();
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(integerEntry.getValue().getDeclareRecordId());
                    if (j == 0) {
                        builder.writeln(getSchemeJudgeObjectShowName(integerEntry.getValue()));
                    }
                    //抵押=总价-法定
                    if (Objects.equal(projectInfo.getEntrustPurpose(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId())) {
                        List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = Lists.newArrayList();
                        if (!schemeJudgeObjectListMap.isEmpty()) {
                            schemeReimbursementItemVoList = schemeJudgeObjectListMap.get(integerEntry.getValue());
                        }
                        BigDecimal notSetUpTotalPrice = getSchemeReimbursementSetUpTotalPrice(schemeReimbursementItemVoList);
                        if (j == 9) {
                            builder.writeln(notSetUpTotalPrice.toString());
                        }
                        if (declareRecord.getPrice() != null && declareRecord.getPracticalArea() != null) {
                            BigDecimal totol = declareRecord.getPrice().multiply(declareRecord.getPracticalArea());
                            BigDecimal mortgage = totol.subtract(notSetUpTotalPrice);
                            mortgage = mortgage.divide(new BigDecimal(10000));
                            mortgage = mortgage.setScale(2, BigDecimal.ROUND_HALF_UP);
                            if (j == 10) {
                                builder.writeln(mortgage.toString());
                            }
                        }
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(integerEntry.getKey());
                    if (j == 1) {
                        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getSeat())) {
                            builder.writeln(declareRecord.getSeat());
                        }
                    }
                    if (j == 2) {
                        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getCertUse())) {
                            builder.writeln(declareRecord.getCertUse());
                        }
                    }
                    if (j == 3) {
                        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getPracticalUse())) {
                            builder.writeln(declareRecord.getPracticalUse());
                        }
                    }
                    if (j == 4) {
                        try {
                            builder.writeln(generateBaseExamineService.getBasicBuilding().getFloorCount().toString());
                        } catch (Exception e) {
                            builder.writeln("0");
                        }
                    }
                    if (j == 5) {
                        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getFloor())) {
                            builder.writeln(declareRecord.getFloor());
                        }
                    }
                    if (j == 6) {
                        if (declareRecord != null && declareRecord.getPracticalArea() != null) {
                            builder.writeln(declareRecord.getPracticalArea().toString());
                        }
                    }
                    if (j == 7) {
                        if (declareRecord != null && declareRecord.getPrice() != null) {
                            builder.writeln(declareRecord.getPrice().toString());
                        }
                    }
                    if (j == 8) {
                        if (declareRecord.getPrice() != null && declareRecord.getPracticalArea() != null) {
                            BigDecimal total = declareRecord.getPrice().multiply(declareRecord.getPracticalArea());
                            total = total.divide(new BigDecimal(10000));
                            total = total.setScale(4, BigDecimal.ROUND_HALF_UP);
                            builder.writeln(total.toString());
                        }
                    }
                }
                builder.endRow();
            }
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 获取不重复楼盘和估价对象的一一对应集合
     * @throws Exception
     */
    private LinkedHashMap<BasicApply, SchemeJudgeObject> getLinkedHashMapAndBasicApplyOrSchemeJudgeObject() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(schemeJudgeObjectList, true);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
        LinkedHashMap<BasicApply, SchemeJudgeObject> schemeJudgeObjectLinkedHashMap = Maps.newLinkedHashMap();
        Map<String, String> stringMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && projectPhase != null) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setProjectPhaseId(projectPhase.getId());
                query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                List<ProjectPlanDetails> projectDetails = projectPlanDetailsService.getProjectDetails(query);
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (CollectionUtils.isNotEmpty(projectDetails) && declareRecord != null) {
                    for (ProjectPlanDetails projectPlanDetails : projectDetails) {
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        BasicApply basicApply = generateBaseExamineService.getBasicApply();
                        if (basicApply != null && basicApply.getId() != null) {
                            BasicEstate basicEstate = generateBaseExamineService.getEstate();
                            if (basicEstate != null && StringUtils.isNotBlank(basicEstate.getName())) {
                                String value = StringUtils.isNotBlank(declareRecord.getStreetNumber()) ? declareRecord.getStreetNumber() : "街道无";
                                stringMap.put(String.format("%s%s", basicEstate.getName(), value),
                                        String.format("%s,%s", basicApply.getPlanDetailsId().toString(), schemeJudgeObject.getId().toString()));
                            }
                        }
                    }
                }
            }
        }
        if (!stringMap.isEmpty()) {
            for (Map.Entry<String, String> stringEntry : stringMap.entrySet()) {
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(Integer.parseInt(stringEntry.getValue().split(",")[0]));
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(Integer.parseInt(stringEntry.getValue().split(",")[1]));
                schemeJudgeObjectLinkedHashMap.put(generateBaseExamineService.getBasicApply(), schemeJudgeObject);
            }
        }
        return schemeJudgeObjectLinkedHashMap;
    }


    /**
     * 估价对象区位状况表
     */
    public String getJudgeObjectAreaStatusSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        StringBuilder stringBuilder = new StringBuilder(8);
        LinkedHashMap<BasicApply, SchemeJudgeObject> schemeJudgeObjectLinkedHashMap = getLinkedHashMapAndBasicApplyOrSchemeJudgeObject();
        for (Map.Entry<BasicApply, SchemeJudgeObject> schemeJudgeObjectEntry : schemeJudgeObjectLinkedHashMap.entrySet()) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectEntry.getValue();
            List<Integer> judgeObjectIds = Lists.newArrayList();
            if (schemeJudgeObject.getBisMerge()) {
                List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                schemeJudgeObjects.add(schemeJudgeObject);
                schemeJudgeObjects.stream().forEach(schemeJudgeObject1 -> judgeObjectIds.add(schemeJudgeObject1.getId()));
            } else {
                judgeObjectIds.add(schemeJudgeObject.getId());
            }
            BasicApply basicApply = schemeJudgeObjectEntry.getKey();
            stringBuilder.append("1:位置状况").append("\r");
            stringBuilder.append(String.format("坐落:%s", generateLoactionService.getSeat(schemeJudgeObject.getDeclareRecordId(), basicApply.getId()))).append("\r");
            stringBuilder.append(String.format("方位:%s", generateLoactionService.getPosition(basicApply.getId()))).append("\r");
            stringBuilder.append(String.format("与重要场所的距离:")).append("\r");
            stringBuilder.append(generateLoactionService.getWithImportantLocationDistance(basicApply.getId())).append("\r");
            stringBuilder.append(String.format("临街（路）状况:")).append("\r");
            stringBuilder.append(generateLoactionService.getFaceStreet(basicApply)).append("\r");
            stringBuilder.append(String.format("楼层:%s", generateLoactionService.getFloor(judgeObjectIds))).append("\r");
            stringBuilder.append(String.format("朝向:%s", generateLoactionService.getOrientation(basicApply))).append("\r");
            stringBuilder.append("2:交通状况包括").append("\r");
            stringBuilder.append(String.format("道路状况:")).append("\r");
            stringBuilder.append(generateLoactionService.getRoadCondition(basicApply)).append("\r");
            stringBuilder.append(String.format("出入可利用的交通工具:")).append("\r");
            stringBuilder.append(generateLoactionService.getAccessAvailableMeansTransport(basicApply)).append("\r");
            stringBuilder.append(String.format("交通管制情况:")).append("\r");
            stringBuilder.append(generateLoactionService.getTrafficControl(basicApply)).append("\r");
            stringBuilder.append(String.format("停车方便度:")).append("\r");
            stringBuilder.append(generateLoactionService.getParkingConvenience(basicApply)).append("\r");
            stringBuilder.append(String.format("交通收费情况:")).append("\r");
            stringBuilder.append(generateLoactionService.getTrafficCharges(basicApply)).append("\r");
            stringBuilder.append("3:外部基础设施").append("\r");
            stringBuilder.append(String.format("%s", generateLoactionService.getExternalInfrastructure(basicApply))).append("\r");
            stringBuilder.append("4:外部公共服务设施").append("\r");
            stringBuilder.append(String.format("%s", generateLoactionService.getExternalPublicServiceFacilities(basicApply))).append("\r");
            stringBuilder.append("5:周围环境").append("\r");
            stringBuilder.append("5.1:自然要素").append("\r");
            stringBuilder.append(String.format("%s", generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.NATURAL))).append("\r");
            stringBuilder.append("5.2:人文环境要素").append("\r");
            stringBuilder.append(String.format("%s", generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.HUMANITY))).append("\r");
            stringBuilder.append("5.3:景观").append("\r");
            stringBuilder.append(String.format("%s", generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.SCENERY))).append("\r");
            stringBuilder.append("6:综述").append("\r");
            stringBuilder.append(String.format("%s", generateLoactionService.content(schemeJudgeObject, basicApply)));
            if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
                documentBuilder.writeln(stringBuilder.toString());
                documentBuilder.writeln("\r");
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
        LinkedHashMap<BasicApply, SchemeJudgeObject> schemeJudgeObjectLinkedHashMap = getLinkedHashMapAndBasicApplyOrSchemeJudgeObject();
        documentBuilder.writeln("估价土地实体状况表");
        for (Map.Entry<BasicApply, SchemeJudgeObject> schemeJudgeObjectEntry : schemeJudgeObjectLinkedHashMap.entrySet()) {
            BasicApply basicApply = schemeJudgeObjectEntry.getKey();
            documentBuilder.writeln(String.format("1、名称:%s", generateLandEntityService.getLandName(basicApply)));
            documentBuilder.writeln(String.format("2、四至:%s", generateLandEntityService.fourTheFor(basicApply)));
            documentBuilder.writeln(String.format("3、土地面积:%s", generateLandEntityService.getLandArea(basicApply)));
            documentBuilder.writeln(String.format("4、用途:%s", generateLandEntityService.getLandUse(basicApply)));
            documentBuilder.writeln(String.format("5、形状:%s", generateLandEntityService.getShapeState(basicApply)));
            documentBuilder.writeln(String.format("6、地势:%s", generateLandEntityService.getTopographicTerrain(basicApply)));
            documentBuilder.writeln(String.format("7、土壤与地质:%s", generateLandEntityService.getSoil(basicApply)));
            documentBuilder.writeln(String.format("8、基础设施完备度:%s", generateLandEntityService.getInfrastructureCompleteness(basicApply)));
            documentBuilder.writeln(String.format("9、开发程度:%s", generateLandEntityService.getDevelopmentDegree(basicApply)));
            documentBuilder.writeln(String.format("10、综上所述:"));
            documentBuilder.writeln(String.format("%s", generateLandEntityService.getContent(basicApply, schemeJudgeObjectEntry.getValue())));
            documentBuilder.writeln();
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
        LinkedHashMap<String, List<SchemeJudgeObject>> linkedHashMap = generateCommonMethod.getLinkedHashMapEstateNameSchemeJudgeObjectList(areaId);
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry<String, List<SchemeJudgeObject>> listEntry : linkedHashMap.entrySet()) {
                List<Integer> integerList = listEntry.getValue().stream().map(oo -> oo.getId()).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                documentBuilder.writeln(String.format("1、楼盘名称:%s", listEntry.getKey()));
                documentBuilder.writeln(String.format("2、建筑年份:%s", generateHouseEntityService.getBuildingYear(integerList)));
                documentBuilder.writeln(String.format("3、工程质量:%s", generateHouseEntityService.getConstructionQuality(integerList)));
                documentBuilder.writeln(String.format("4、建筑结构:%s", generateHouseEntityService.getBuildingStructure(integerList)));
                documentBuilder.writeln(String.format("5、建筑规模:%s", generateHouseEntityService.getBuildingScale(integerList)));
                documentBuilder.writeln(String.format("6、层高:%s", generateHouseEntityService.getFloorHeight(integerList)));
                documentBuilder.writeln(String.format("7、空间布局:%s", generateHouseEntityService.getSpatialDistribution(integerList)));
                documentBuilder.writeln(String.format("8、装饰装修:%s", generateHouseEntityService.getDecoration(integerList)));
                documentBuilder.writeln(String.format("9、外观:%s", generateHouseEntityService.getAppearance(integerList)));
                documentBuilder.writeln("10、设施设备");
                documentBuilder.writeln(String.format("电梯:%s", generateHouseEntityService.getUnitElevator(integerList)));
                String s2 = generateHouseEntityService.getTenPointTwo(integerList);
                if (StringUtils.isNotBlank(s2.trim())){
                    documentBuilder.writeln(String.format("非工业与仓储的其他设施:"));
                    documentBuilder.writeln(String.format("%s", s2));
                }
                String s1 = generateHouseEntityService.getTenPointThree(integerList);
                if (StringUtils.isNotBlank(s1.trim())) {
                    documentBuilder.writeln(String.format("房屋配套设备设施工:%s", s1));
                }
                documentBuilder.writeln(String.format("11、建筑功能:%s", generateHouseEntityService.getBuildingFunction(integerList)));
                documentBuilder.writeln(String.format("12、新旧程度及维护使用情况", ""));
                documentBuilder.writeln(String.format("%s", generateHouseEntityService.getThirteen(integerList)));
                documentBuilder.writeln(String.format("13、其它:%s", generateHouseEntityService.getOther(integerList)));
                documentBuilder.writeln(String.format("14、建筑实体分析:%s", generateHouseEntityService.getContent(integerList, schemeAreaGroup)));
                documentBuilder.writeln();
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价结果汇总表
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
                                builder.writeln("估价对象及结果\\估价方法及结果");
                                mergeCellModelList.add(new MergeCellModel(0, 0, 1, 2));
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln("测算结果");
                                mergeCellModelList.add(new MergeCellModel(0, 3, 0, 5));
                                break;
                            case 6:
                                builder.insertCell();
                                builder.writeln("估价结果");
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
                                builder.writeln("市场比较法");
                                break;
                            case 4:
                                builder.insertCell();
                                builder.writeln("收益法");
                                break;
                            case 5:
                                builder.insertCell();
                                builder.writeln("");
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
            if (true) {
                SchemeInfo schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdCompare, schemeJudgeObjectList.get(j - 2));
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    mdMarketCompare = mdMarketCompareService.getMdMarketCompare(schemeInfo.getMethodDataId());
                }
            }
            if (true) {
                SchemeInfo schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdCompare, schemeJudgeObjectList.get(j - 2));
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    mdIncome = mdIncomeService.getIncomeById(schemeInfo.getMethodDataId());
                }
            }
            int num = j % 2;
            switch (num) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 0:
                                builder.writeln(getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(j - 2)));
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.writeln("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdMarketCompare.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.writeln(temp.toString());
                                        totolCompare = totolCompare.add(temp);
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdIncome.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.writeln(temp.toString());
                                        totolIncome = totolIncome.add(temp);
                                    } else {
                                        builder.writeln("");
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
                                builder.writeln("单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null) {
                                        builder.writeln(mdMarketCompare.getPrice().toString());
                                        priceCompare = priceCompare.add(mdMarketCompare.getPrice());
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null) {
                                        builder.writeln(mdIncome.getPrice().toString());
                                        priceIncome = priceIncome.add(mdIncome.getPrice());
                                    }
                                } else {
                                    builder.writeln("");
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
                                builder.writeln("汇总平均价值");
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.writeln("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.writeln(totolCompare.toString());
                                break;
                            case 4:
                                builder.writeln(totolIncome.toString());
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
                                builder.writeln("平均单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.writeln(priceCompare.toString());
                                break;
                            case 4:
                                builder.writeln(priceIncome.toString());
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
     * @throws Exception
     */
    public String getTenancyrestrictionRemark() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                SchemeInfo schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdIncome, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfo, projectId, areaId);
                    String value = generateMdIncomeService.getTenancyrestrictionReamrk();
                    if (StringUtils.isNotBlank(value)) {
                        if (!Objects.equal(errorStr, value)) {
                            stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObject), value));
                        }
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 功能描述: 估价对象详细测算过程
     * @author: zch
     * @date: 2019/3/4 10:30
     */
    public String getDetailedCalculationProcessValuationObject() throws Exception {
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        Map<String, String> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                builder.writeln(getSchemeJudgeObjectShowName(schemeJudgeObject));
                SchemeInfo schemeInfo = null;
                //市场比较法
                builder.writeln(CalculationMethodNameEnum.MdCompare.getName());
                schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdCompare, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(schemeInfo.getMethodDataId(), new Date(), areaId);
                    try {
                        String temp = generateMdCompareService.generateCompareFile();
                        File file = new File(temp);
                        if (file.isFile()) {
                            String key = String.format("%s%s%s", getSchemeJudgeObjectShowName(schemeJudgeObject), CalculationMethodNameEnum.MdCompare.getName(), UUID.randomUUID().toString());
                            map.put(key, temp);
                            builder.writeln(key);
                        }
                    } catch (Exception e) {
                    }
                }
                //收益法
                builder.writeln(CalculationMethodNameEnum.MdIncome.getName());
                schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdIncome, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfo, projectId, areaId);
                    String temp = generateMdIncomeService.generateCompareFile();
                    File file = new File(temp);
                    if (file.isFile()) {
                        String key = String.format("%s%s%s", getSchemeJudgeObjectShowName(schemeJudgeObject), CalculationMethodNameEnum.MdIncome.getName(), UUID.randomUUID().toString());
                        map.put(key, temp);
                        builder.writeln(key);
                    }
                }
            }
        }
        document.save(localPath, SaveFormat.DOC);
        if (!map.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, map);
        }
        return localPath;
    }

    /**
     * 估价委托书复印件
     * @throws Exception
     */
    public String getJUDGEOBJECTPRINCIPALCOPYSHEET() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        SysAttachmentDto sysAttachmentDto = schemeReportFileService.getProjectProxyFileList(projectId);
        if (sysAttachmentDto != null) {
            String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
            List<String> images = Lists.newArrayList();
            if (FileUtils.checkImgSuffix(imgPath)) {
                images.add(imgPath);
            }
            AsposeUtils.imageInsertToWrod(images, 1, builder);
        }
        document.save(localPath);
        return localPath;
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
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("<span style=\"text-indent:2em\">%s</span>", schemeJudgeObject.getName())), true);
                List<String> imgPathList = Lists.newArrayList();
                List<SysAttachmentDto> sysAttachmentDtoList = schemeReportFileService.getJudgeObjectPositionFileList(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        imgPathList.add(imgPath);
                    }
                }
                AsposeUtils.imageInsertToWrod(imgPathList, 1, builder);
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
                List<SchemeReportFileItem> sysAttachmentDtoList = schemeReportFileService.getLiveSituationSelect(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    List<String> imgPathList = Lists.newArrayList();
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("<span style=\"text-indent:2em\">%s</span>", schemeJudgeObject.getName())), true);
                    for (SchemeReportFileItem sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getAttachmentId());
                        imgPathList.add(imgPath);
                    }
                    AsposeUtils.imageInsertToWrod(imgPathList, 3, builder);
                }
            }
        }
        document.save(localPath);
        return localPath;
    }


    /**
     * 估价对象权属证明复印件
     */
    public String getCopies_the_Ownership_Certificate_the_Valuation_Object() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        Map<Integer, List<SysAttachmentDto>> ownershipCertFileList = schemeReportFileService.getOwnershipCertFileList(areaId);
        if (CollectionUtils.isNotEmpty(this.schemeJudgeObjectDeclareList)) {
            for (SchemeJudgeObject schemeJudgeObject : this.schemeJudgeObjectDeclareList) {
                List<SysAttachmentDto> sysAttachmentDtoList = ownershipCertFileList.get(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    List<String> imgPathList = Lists.newArrayList();
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("<span style=\"text-indent:2em\">%s</span>", schemeJudgeObject.getName())), true);
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        imgPathList.add(imgPath);
                    }
                    AsposeUtils.imageInsertToWrod(imgPathList, 1, builder);
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 估价中引用的专用文件资料
     * @throws Exception
     */
    public String getSpecial_documentation_referenced_in_valuation() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<String> imgPathList = null;
        if (CollectionUtils.isNotEmpty(this.schemeJudgeObjectDeclareList)) {
            Map<Integer, List<SysAttachmentDto>> inventoryAddressFileList = schemeReportFileService.getInventoryAddressFileList(areaId);
            for (SchemeJudgeObject schemeJudgeObject : this.schemeJudgeObjectDeclareList) {
                //1.先取地址不一致附件
                List<SysAttachmentDto> addressFileList = inventoryAddressFileList.get(schemeJudgeObject.getId());
                if (CollectionUtils.isEmpty(addressFileList)) continue;
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("<span style=\"text-indent:2em\">%s</span>", schemeJudgeObject.getName())), true);
                if (CollectionUtils.isNotEmpty(addressFileList)) {
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml("<span style=\"text-indent:2em\">地址不一致附件</span>"), true);
                    for (SysAttachmentDto sysAttachmentDto : addressFileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        imgPathList.add(imgPath);
                    }
                    AsposeUtils.imageInsertToWrod(imgPathList, 2, builder);
                }
            }
        }

        //2.法定优先受偿款附件

        Map<Integer, List<SysAttachmentDto>> reimbursementFileList = schemeReportFileService.getReimbursementFileList(areaId);
        List<SysAttachmentDto> reimFileList = reimbursementFileList.get(1);
        if (CollectionUtils.isNotEmpty(reimFileList)) {
            builder.writeln("法定优先受偿款附件");
            for (SysAttachmentDto sysAttachmentDto : reimFileList) {
                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                generateCommonMethod.builderInsertImage(builder, imgPath);
            }
        }

        //3.取得自定义的附件
        List<SchemeReportFileCustom> reportFileCustomList = schemeReportFileService.getReportFileCustomList(areaId);
        if (CollectionUtils.isNotEmpty(reportFileCustomList)) {
            for (SchemeReportFileCustom schemeReportFileCustom : reportFileCustomList) {
                List<SysAttachmentDto> fileList = schemeReportFileService.getCustomFileList(schemeReportFileCustom.getId());
                if (CollectionUtils.isNotEmpty(fileList)) {
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml(String.format("<span style=\"text-indent:2em\">%s</span>", schemeReportFileCustom.getName())), true);
                    imgPathList = Lists.newArrayList();
                    for (SysAttachmentDto sysAttachmentDto : fileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        imgPathList.add(imgPath);
                    }
                    AsposeUtils.imageInsertToWrod(imgPathList, 2, builder);
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 房地产估价机构营业执照复印件
     * @throws Exception
     */
    public String getCopyBusinessLicenseRealEstateValuationAgency() throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForLicense();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                List<String> images = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                images.add(imgPath);
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(images)) {
                    AsposeUtils.insertImage(localPath, images, 200, 100);
                }
            }
        }
        return localPath;
    }

    /**
     * '房地产估价机构资质证书复印件
     * @throws Exception
     */
    public String getCopyQualificationCertificateRealEstateValuationInstitution() throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForPractising();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                List<String> images = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                images.add(imgPath);
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(images)) {
                    AsposeUtils.insertImage(localPath, images, 200, 100);
                }
            }
        }
        return localPath;
    }

    /**
     * 注册房地产估价师注册证书复印件
     * @param str
     * @return
     * @throws Exception
     */
    public String getRegisteredRealEstateValuerValuationInstitution(String str) throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        List<String> images = Lists.newArrayList();
        String[] strings = str.split(",");
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                    for (String account : dataQualificationVo.getUserAccount().split(",")) {
                        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                            adPersonalQualificationDtoList.stream().forEach(adCompanyQualificationDto -> {
                                if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                                    List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                                    if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                                        for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                                            try {
                                                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                                                if (StringUtils.isNotBlank(imgPath)) {
                                                    if (FileUtils.checkImgSuffix(imgPath)) {
                                                        images.add(imgPath);
                                                    }
                                                }
                                            } catch (Exception e1) {
                                            }
                                        }
                                    }

                                }
                            });
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(images)) {
            AsposeUtils.insertImage(localPath, images, 200, 100);
        }
        return localPath;
    }


    /**
     * 获取如收益法,市场比较法，假设开发法，成本法等的id
     * @param methodNameEnum
     * @param schemeJudgeObject
     * @return SchemeInfo
     */
    private SchemeInfo getSchemeInfoId(CalculationMethodNameEnum methodNameEnum, SchemeJudgeObject schemeJudgeObject) {
        DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(oo -> {
            if (oo.getName().equals(methodNameEnum.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        if (dataEvaluationMethod != null) {
            SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), dataEvaluationMethod.getMethod());
            if (schemeInfo != null) {
                return schemeInfo;
            }
        }
        return null;
    }

    /**
     * 他权信息公示
     * @throws Exception
     */
    public String getHisRightInfoPublicity() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        DataHisRightInfoPublicity dataHisRightInfoPublicity = dataHisRightInfoPublicityService.getDataHisRightInfoPublicity(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
        if (dataHisRightInfoPublicity != null) {
            String value = dataHisRightInfoPublicity.getContent();
            if (StringUtils.isNotBlank(value.trim())) {
                return value;
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 申报所启用表单类型
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
            else if (schemeJudgeObject.getBisMerge() == Boolean.FALSE && schemeJudgeObject.getBisSplit() == Boolean.FALSE && schemeJudgeObject.getBisEnable() == Boolean.TRUE) {
                baseJudgeId.add(schemeJudgeObject.getId());
            }
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
        for (SchemeJudgeObject judgeObject : this.schemeJudgeObjectFullList) {
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
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.trim(reportThinking)), true);
        document.save(localPath);
        return localPath;
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
        StringBuffer stringBuffer = new StringBuffer(8);
        List<String> seats = Lists.newArrayList();
        final String zero = "0";
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.setDefaultDocumentBuilderSetting(documentBuilder);
        LinkedHashMap<String, List<SchemeJudgeObject>> linkedHashMap = generateCommonMethod.getSchemeJudgeObjectLinkedHashMap(
                generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true),
                projectInfo);
        if (!linkedHashMap.isEmpty()) {
            int i = 0;
            for (Map.Entry<String, List<SchemeJudgeObject>> entry : linkedHashMap.entrySet()) {
                //楼盘名称
                String estateName = entry.getKey();
                List<SchemeJudgeObject> schemeJudgeObjectList = entry.getValue();
                List<DeclareRecord> declareRecordList = Lists.newArrayList();
                //当估价对象不存在的情况下不予以拼接
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    schemeJudgeObjectList.stream().forEach(schemeJudgeObject -> declareRecordList.add(declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId())));
                    if (CollectionUtils.isEmpty(declareRecordList)) {
                        continue;
                    }
                    declareRecordList.stream().forEach(declareRecord -> {
                        if (StringUtils.isNotBlank(declareRecord.getAttachedNumber())) {
                            stringBuffer.append("附").append(declareRecord.getAttachedNumber()).append("号");
                        }
                        if (StringUtils.isNotBlank(declareRecord.getBuildingNumber())) {
                            stringBuffer.append(declareRecord.getBuildingNumber());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("栋");
                        if (StringUtils.isNotBlank(declareRecord.getUnit())) {
                            stringBuffer.append(declareRecord.getUnit());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("单元");
                        if (StringUtils.isNotBlank(declareRecord.getFloor())) {
                            stringBuffer.append(declareRecord.getFloor());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("层");
                        if (StringUtils.isNotBlank(declareRecord.getRoomNumber())) {
                            stringBuffer.append(declareRecord.getRoomNumber());
                        } else {
                            stringBuffer.append(zero);
                        }
                        stringBuffer.append("号");
                        seats.add(stringBuffer.toString());
                        stringBuffer.delete(0, stringBuffer.toString().length());
                    });
                    buffer.append(estateName);
                    if (CollectionUtils.isNotEmpty(seats)) {
                        String s = publicService.fusinString(seats);
                        if (StringUtils.isNotBlank(s)) {
                            buffer.append(s);
                        }
                    }
                    seats.clear();
                    buffer.append(",");
                    //设定用途
                    buffer.append(BaseReportFieldEnum.SetUse.getName()).append(generateCommonMethod.getSetUses(schemeJudgeObjectList));
                    //实际用途
                    buffer.append(",").append(BaseReportFieldEnum.PracticalUse.getName()).append(generateCommonMethod.getPracticalUse(schemeJudgeObjectList));
                    buffer.append(",").append("建筑面积").append(generateCommonMethod.getBuildingAndAssessArea(schemeJudgeObjectList));
                    buffer.append(",").append("评估面积").append(generateCommonMethod.getBuildingAndAssessArea(schemeJudgeObjectList));
                    buffer.append(",").append(BaseReportFieldEnum.LandUseRightType.getName()).append(generateCommonMethod.getUseRightType(schemeJudgeObjectList));
                    buffer.append(",").append(BaseReportFieldEnum.PowerPerson.getName()).append(generateCommonMethod.getPowerPerson(schemeJudgeObjectList));
                    buffer.append(",").append("房屋结构").append(generateCommonMethod.getBuildingStructureCategory(schemeJudgeObjectList, projectInfo));
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", i + 1, buffer.toString())).append("</p>");
                    buffer.delete(0, buffer.toString().length());
                    i++;
                }
            }
        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        document.save(localPath);
        return localPath;
    }


    //------------------------------------------------------------||待删除方法 start -------------------------------------------------------//

    /**
     * 区位(2019-01-28 修改之后)
     *
     * @return
     * @throws Exception
     */
    @Deprecated
    public String getLocation_() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        try {
            SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
            builder.append(schemeAreaGroup.getAreaName());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                builder.append(getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(0))).append(":");
                builder.append(schemeJudgeObjectList.get(0).getSeat());
                if (schemeJudgeObjectList.size() > 1) {
                    builder.append("等");
                }
            }
        } catch (Exception e) {
            logger.error("(区位)拼接异常!");
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }


    /**
     * 土地他项权利
     *
     * @return
     */
    @Deprecated
    public String getInventoryRight() throws Exception {
        return getHisRightType();
    }

    /**
     * 土地使用管制
     *
     * @return
     */
    @Deprecated
    public String getLandUseControl() throws Exception {
        String s = getSeparationCertificateUses();
        return s;
    }


    /**
     * 评估方法总括
     *
     * @return
     */
    @Deprecated
    public String getSummaryEvaluationMethod() {
        StringBuilder builder = new StringBuilder(128);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            builder.append(getSchemeJudgeObjectShowName(schemeJudgeObject));
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                builder.append(":").append(schemeJudgeFunctionList.get(0).getName());
                builder.append("等");
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return " ";
    }

    /**
     * 价值表达结果
     *
     * @return
     */
    @Deprecated
    public String getValueExpressionResult() {
        return "抵押价值特殊处理";
    }

    /**
     * 分类评估面积
     *
     * @return
     */
    @Deprecated
    public String getEvaluationAreaCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getEvaluationArea().toString())) {
                        stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(i)), schemeJudgeObjectList.get(i).getEvaluationArea().toString()));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        return s;
    }

    /**
     * 功能描述: 估价对象的单价
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 17:15
     */
    @Deprecated
    public String getUnitPriceValuator() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                    stringBuilder.append(schemeJudgeObject.getName()).append("的单价=").append(getEvaluationExpression());
                    for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                        if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdCompare.getName())) {
                            if (schemeSurePriceItem.getTrialPrice() != null && schemeSurePriceItem.getWeight() != null) {
                                stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                                stringBuilder.append("+");
                            }
                        }
                        if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdIncome.getName())) {
                            stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                        }
                    }
                }
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 分类评估单价
     *
     * @return
     */
    @Deprecated
    public String getEvaluationPriceCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPrice().toString())) {
                        stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(i)), schemeJudgeObjectList.get(i).getPrice().toString()));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        return s;
    }


    //评估面积
    @Deprecated
    public String getAssessArea() {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null) {
                    generateCommonMethod.putStringListMap(stringListMap, schemeJudgeObject, schemeJudgeObject.getEvaluationArea().toString());
                }
            }
        }
        String s = generateCommonMethod.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isNotBlank(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 委托人地址
     *
     * @return
     */
    @Deprecated
    public String getPrincipalAddress() {
        String str = projectInfo.getConsignorVo().getCsAddress();
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return errorStr;
        }
    }

    /**
     * 委托人法定代表人
     *
     * @return
     */
    @Deprecated
    public String getPrincipalLegalRepresentative() {
        String str = projectInfo.getConsignorVo().getCsLegalRepresentative();
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return errorStr;
        }
    }

    /**
     * 权重说明
     *
     * @return
     */
    @Deprecated
    public String getWeightSpecification() {
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setDeclareRecordId(schemeJudgeObjectList.get(i).getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetailsList.get(0).getId());
                    if (schemeSurePrice != null) {
                        stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(i)), schemeSurePrice.getWeightExplain()));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetStringSplitSpace(stringSet);
        return s;
    }

    //------------------------------------------------------------||待删除方法 end -------------------------------------------------------//


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
        this.surveyAssetInventoryDao = SpringContextUtils.getBean(SurveyAssetInventoryDao.class);
        this.schemeSurePriceService = SpringContextUtils.getBean(SchemeSurePriceService.class);
        this.schemeReimbursementService = SpringContextUtils.getBean(SchemeReimbursementService.class);
        this.publicService = SpringContextUtils.getBean(PublicService.class);
        this.adRpcQualificationsService = SpringContextUtils.getBean(com.copower.pmcc.assess.service.AdRpcQualificationsAppService.class);
        this.compileReportService = SpringContextUtils.getBean(CompileReportService.class);
        this.schemeReportFileService = SpringContextUtils.getBean(SchemeReportFileService.class);
        this.dataQualificationService = SpringContextUtils.getBean(DataQualificationService.class);
        this.declareRealtyLandCertService = SpringContextUtils.getBean(DeclareRealtyLandCertService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.evaluationMethodService = SpringContextUtils.getBean(EvaluationMethodService.class);
        this.schemeLiquidationAnalysisService = SpringContextUtils.getBean(SchemeLiquidationAnalysisService.class);
        this.mdIncomeService = SpringContextUtils.getBean(MdIncomeService.class);
        this.mdMarketCompareService = SpringContextUtils.getBean(MdMarketCompareService.class);
        this.dataHisRightInfoPublicityService = SpringContextUtils.getBean(DataHisRightInfoPublicityService.class);
        this.surveyAssetInventoryContentService = SpringContextUtils.getBean(SurveyAssetInventoryContentService.class);
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
        this.taskExecutor = SpringContextUtils.getBean(TaskExecutor.class);
        this.generateEquityService = SpringContextUtils.getBean(GenerateEquityService.class);
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

    public List<SchemeReimbursementItemVo> getSchemeReimbursementItemVoList() {
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getSchemeReimbursementByAreaId(areaId, projectId);
        List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = Lists.newArrayList();
        if (schemeReimbursement != null) {
            schemeReimbursementItemVoList = schemeReimbursementService.getItemByMasterId(schemeReimbursement.getId());
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
    public List<SchemeJudgeObject> getSchemeJudgeObjectList() {
        return generateCommonMethod.getSortSchemeJudgeObject(this.schemeJudgeObjectList);
    }

    public GenerateBaseExamineService getGenerateBaseExamineService(Integer planDetailsId) {
        return new GenerateBaseExamineService(planDetailsId);
    }
}
