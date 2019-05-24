package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.*;
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
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyRightGroupDto;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.data.*;
import com.copower.pmcc.assess.service.method.MdCommonService;
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
import com.copower.pmcc.erp.common.utils.*;
import com.google.common.base.Objects;
import com.google.common.collect.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
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
    private MdCommonService mdCommonService;
    private GenerateEquityService generateEquityService;
    private BasicApplyService basicApplyService;
    private SurveyAssetInventoryRightRecordCenterService surveyAssetInventoryRightRecordCenterService;
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    private BaseProjectClassifyService baseProjectClassifyService;

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
        BigDecimal decimal = new BigDecimal(getTotalRealEstatePrice()).subtract(getSchemeReimbursementKnowTotalPrice());
        return generateCommonMethod.getBigDecimalRound(decimal, true);
    }

    /**
     * 抵押价值总金额大写
     */
    public String getTotalAmountMortgageValueCapitalization() throws Exception {
        BigDecimal decimal = new BigDecimal(getTotalRealEstatePrice()).subtract(getSchemeReimbursementKnowTotalPrice());
        return CnNumberUtils.toUppercaseSubstring(generateCommonMethod.getBigDecimalRound(decimal, false));
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
            DeclareRecord declareRecord = recordList.get(0);
            String streetNumber = declareRecord.getStreetNumber();
            List<String> list = Lists.newArrayList();
            for (DeclareRecord record : recordList) {
                list.add(record.getSeat().replace(streetNumber, ""));
            }
            stringBuilder.append(String.format("%s%s", streetNumber, entry.getKey().getName()));
            stringBuilder.append(publicService.fusinString(list, false)).append(";");
        }
        if (getSchemeAreaGroup().getEntrustPurpose() != null) {
            stringBuilder.append(baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose()));
        }
        return stringBuilder.toString();
    }


    /**
     * 证载用途
     *
     * @throws Exception
     */
    public String getSeparationCertificateUses() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectDeclareList) {
            map.put(generateCommonMethod.parseIntJudgeNumber(generateCommonMethod.getNumber(schemeJudgeObject.getNumber())), schemeJudgeObject.getCertUse());
        }
        return generateCommonMethod.judgeSummaryDesc(map, "证载用途为", false);
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
     *
     * @throws Exception
     */
    public String getHotTip() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(16);
        Document doc = new Document();
        LinkedHashSet<String> stringSet = Sets.newLinkedHashSet();
        String localPath = getLocalPath();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        int row = 0;
        {
            stringSet.add("本函内容摘自估价报告");
            stringSet.add("欲了解本次估价项目全面情况");
            stringSet.add("请详见估价结果报告");
            stringSet.add("报告使用时请特别关注估价假设和限制条件内容。");
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))));
            stringSet.clear();
            row++;
        }
        {
            Map<SchemeJudgeObject, List<SurveyAssetInventoryRight>> hashMap = getSurveyAssetInventoryRightMapAndSchemeJudgeObject();
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_TASKRIGHT_PLEDGE);
            List<Integer> integerList = Lists.newArrayList();
            if (!hashMap.isEmpty()) {
                hashMap.entrySet().stream().forEach(entry -> {
                    if (CollectionUtils.isNotEmpty(entry.getValue())) {
                        entry.getValue().stream().forEach(oo -> {
                            if (oo.getCategory() != null) {
                                if (Objects.equal(projectClassify.getId(), oo.getCategory())) {
                                    integerList.add(generateCommonMethod.parseIntJudgeNumber(entry.getKey().getNumber()));
                                }
                            }
                        });
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(integerList)) {
                stringSet.add(String.format("%s号根据委托人介绍及估价人员在", generateCommonMethod.convertNumber(integerList.stream().distinct().collect(Collectors.toList()))));
                String areaFullName = erpAreaService.getAreaFullName(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
                DataHisRightInfoPublicity infoPublicity = dataHisRightInfoPublicityService.getDataHisRightInfoPublicity(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
                String value = null;
                if (infoPublicity != null) {
                    value = infoPublicity.getContent();
                }
                stringSet.add(String.format("%s%s", areaFullName, StringUtils.defaultString(value, "房地产评估管理服务信息系统（http://fcpg.cdfgj.gov.cn/）")));
                stringSet.add("上查询了解得知，截止价值时点，估价对象已设定抵押权。");
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))));
                stringSet.clear();
                row++;
            }
        }
        {
            String addressAssetInventory = getActualAddressAssetInventory();
            String certificateAssetInventory = getCertificateAssetInventory();
            if (StringUtils.isNotBlank(addressAssetInventory) && StringUtils.isNotBlank(certificateAssetInventory)) {
                stringSet.add(String.format("估价对象现场查勘地址为%s", addressAssetInventory));
                stringSet.add(String.format("本次评估根据委托方提供的由%s", certificateAssetInventory));
                stringSet.add("本次以上地址为同一地址。");
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))));
                stringSet.clear();
                row++;
            }
            if (CollectionUtils.isNotEmpty(getSchemeReimbursementItemVoList())) {
                BigDecimal knowTotalPrice = getSchemeReimbursementKnowTotalPrice();
                stringSet.add("根据估价委托人提供的《法定优先受偿款情况说明》");
                stringSet.add("估价对象于价值时点已设定抵押权");
                stringSet.add("本次评估是抵押权存续期间的房地产估价（同行续贷）");
                stringSet.add("经过沟通");
                stringSet.add("抵押权人已经知晓法定优先受偿款对估价对象价值的影响");
                stringSet.add("且并不需要我们在抵押价值中予以扣除法定优先受偿款");
                stringSet.add(String.format("故本报告假设估价对象在价值时点法定优先受偿款合计为%s万元（大写：%s）",
                        generateCommonMethod.getBigDecimalRound(knowTotalPrice, true),
                        CnNumberUtils.toUppercaseSubstring(knowTotalPrice.toString())));
                stringSet.add("在此提请报告使用人加以关注。");
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", row + 1, StringUtils.join(stringSet, "，"))));
                stringSet.clear();
            }
        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        doc.save(localPath);
        return localPath;
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
                linkedLists.add(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject));
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
            try {
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
            } catch (Exception e) {
                logger.error("变现分析税费异常", e);
            }
        }
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        }
        builder.endTable();
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
                SchemeJudgeObject judgeObject = this.schemeJudgeObjectMap.get(judgeFunction.getJudgeObjectId());
                if (judgeObject == null) continue;
                if (mdCommonService.isCompareMethod(judgeFunction.getMethodType()))
                    compareMap.put(judgeObject.getNumber(), judgeFunction.getApplicableReason());
                if (mdCommonService.isIncomeMethod(judgeFunction.getMethodType()))
                    incomeMap.put(judgeObject.getNumber(), judgeFunction.getApplicableReason());
                if (mdCommonService.isCostMethod(judgeFunction.getMethodType()))
                    costMap.put(judgeObject.getNumber(), judgeFunction.getApplicableReason());
                if (mdCommonService.isDevelopmentMethod(judgeFunction.getMethodType()))
                    developmentMap.put(judgeObject.getNumber(), judgeFunction.getApplicableReason());
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
                if (mdCommonService.isCompareMethod(judgeFunction.getMethodType()))
                    compareMap.put(judgeObject.getNumber(), judgeFunction.getNotApplicableReason());
                if (mdCommonService.isIncomeMethod(judgeFunction.getMethodType()))
                    incomeMap.put(judgeObject.getNumber(), judgeFunction.getNotApplicableReason());
                if (mdCommonService.isCostMethod(judgeFunction.getMethodType()))
                    costMap.put(judgeObject.getNumber(), judgeFunction.getNotApplicableReason());
                if (mdCommonService.isDevelopmentMethod(judgeFunction.getMethodType()))
                    developmentMap.put(judgeObject.getNumber(), judgeFunction.getNotApplicableReason());
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
        if (StringUtils.isNotBlank(statementPurposeEntrustment.trim())) {
            return statementPurposeEntrustment;
        } else {
            return errorStr;
        }
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
        if (StringUtils.isNotBlank(value.trim())) {
            return value;
        } else {
            return errorStr;
        }
    }

    /**
     * 注册房产估价师及注册号
     *
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
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "机构名称", qualificationDto.getOrganizationName())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "住所", qualificationDto.getOrganizationAddress())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "法定代表人", qualificationDto.getLegalRepresentative())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "工商注册号", qualificationDto.getRegisteredNo())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "资质等级", qualificationDto.getOrganizationRank())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "资质证书编号", qualificationDto.getCertificateNo())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "资质证书有效期", qualificationDto.getCertificateEffectiveDate())));
        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s:%s", "经营范围", StringUtils.isEmpty(qualificationDto.getBusinessScopeName()) ? "评估房产" : qualificationDto.getBusinessScopeName())));
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
    public String getLiquidityRisk(SchemeSupportTypeEnum schemeSupportTypeEnum) throws Exception {
        if (projectInfo == null || schemeSupportTypeEnum == null) return "";
        String result = "";
        switch (schemeSupportTypeEnum) {
            case REPORT_ANALYSIS_CATEGORY_LIQUIDITY:
                result = dataReportAnalysisService.getReportLiquidity(this.projectInfo, areaId);
                break;
            case REPORT_ANALYSIS_CATEGORY_RISK:
                result = dataReportAnalysisRiskService.getReportRisk(areaId);
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
     * 设定用途
     *
     * @return
     */
    public String getSetUse() {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectDeclareList) {
            map.put(generateCommonMethod.parseIntJudgeNumber(generateCommonMethod.getNumber(schemeJudgeObject.getNumber())), schemeJudgeObject.getCertUse());
        }
        return generateCommonMethod.judgeSummaryDesc(map, "设定用途为", false);
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
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
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
     * @param:
     * @return:
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
            objectListMap.entrySet().stream().forEach(entry -> {
                List<SchemeSurePriceItem> schemeSurePriceItemList = entry.getValue();
                List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getChildrenJudgeObject(entry.getKey().getId());
                SchemeSurePriceItem mdIncomeItem = null;
                SchemeSurePriceItem mdCompareItem = null;
                if (schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdIncome.getName())).count() >= 1) {
                    mdIncomeItem = schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdIncome.getName())).findFirst().get();
                }
                if (schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdCompare.getName())).count() >= 1) {
                    mdCompareItem = schemeSurePriceItemList.stream().filter(schemeSurePriceItem -> schemeSurePriceItem.getMethodName().equals(mdCompare.getName())).findFirst().get();
                }
                Integer computeDifference = null;
                if (mdIncomeItem != null && mdCompareItem != null) {
                    computeDifference = generateCommonMethod.computeDifference(mdIncomeItem.getTrialPrice(), mdCompareItem.getTrialPrice());
                }
                if (computeDifference != null) {
                    stringBuilder.delete(0, stringBuilder.toString().length());
                    stringBuilder.append(generateCommonMethod.getSchemeJudgeObjectShowName(entry.getKey()));
                    stringBuilder.append(mdIncome.getName()).append("与").append(mdCompare.getName());
                    if (computeDifference.intValue() > TEN) {
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
                    if (computeDifference.intValue() <= TEN) {
                        stringBuilder.append("测算结果相近，");
                        stringBuilder.append("通过对该区域的调查，考虑估价对象在该区域内的具体位置等因素").append("，");
                        stringBuilder.append(mdIncome.getName()).append("的试算结果与").append(mdCompare.getName());
                        stringBuilder.append("试算结果均能反映估价对象市场价值").append("。");
                        stringBuilder.append("故最终单价=");
                        stringBuilder.append(mdIncomeItem.getTrialPrice().toString()).append("×").append("50%").append("+").append(mdCompareItem.getTrialPrice().toString()).append("×").append("50%").append("（").append("收益法价格*权重+比较法价格*权重").append("）");
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
                try {
                    documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(stringBuilder.toString())));
                    if (entry.getKey().getBisMerge() != null) {
                        if (entry.getKey().getBisMerge().booleanValue()) {
                            //当为合并对象的时候,需要写入单价调整表
                            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                                List<SchemeJudgeObjectVo> voList = judgeObjectList.stream().map(oo -> schemeJudgeObjectService.getSchemeJudgeObjectVo(oo)).collect(Collectors.toList());
                                documentBuilder.insertDocument(getUnitPriceAdjustmentDocument(voList), ImportFormatMode.KEEP_DIFFERENT_STYLES);
                            }
                        }
                    }
                } catch (Exception e) {
                }
            });
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 单价调整表
     *
     * @throws Exception
     */
    public Document getUnitPriceAdjustmentDocument(List<SchemeJudgeObjectVo> schemeJudgeObjectList) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        generateCommonMethod.settingBuildingTable(builder);
        final String nullString = "";
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("估价编号", "因素", "评估面积", "评估单价", "评估总价")));
            for (SchemeJudgeObjectVo schemeJudgeObject : schemeJudgeObjectList) {
                stringList.add(generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject));
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
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        StringBuilder stringBuilder = new StringBuilder(8);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            List<Integer> integerList = Lists.newArrayList();
            schemeJudgeObjectList.stream().forEach(oo -> integerList.add(generateCommonMethod.parseIntJudgeNumber(oo.getNumber())));
            List<String> stringList = Lists.newArrayList(AssessDataDicKeyConstant.MD_MARKET_COMPARE, AssessDataDicKeyConstant.MD_INCOME, AssessDataDicKeyConstant.MD_COST, AssessDataDicKeyConstant.MD_DEVELOPMENT, AssessDataDicKeyConstant.MD_STANDARD_ADJUSTMENT_PRICE);
            for (String methodNameEnum : stringList) {
                String formula = getDataMethodFormula(schemeJudgeObjectList, methodNameEnum, "公式");
                if (StringUtils.isNotBlank(formula.trim())) {
                    stringBuilder.append(generateCommonMethod.convertNumber(integerList)).append("号");
                    stringBuilder.append(StringUtils.trimToEmpty(formula));
                }
            }
            String indentHtml = generateCommonMethod.getIndentHtml(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.toString().length()).append(indentHtml);
        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), false);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 相关参数选取与应用
     *
     * @return
     * @throws Exception
     */
    public String getSelectionApplicationParameters() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        StringBuilder stringBuilder = new StringBuilder(8);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            List<Integer> integerList = Lists.newArrayList();
            schemeJudgeObjectList.stream().forEach(oo -> integerList.add(generateCommonMethod.parseIntJudgeNumber(oo.getNumber())));
            List<String> stringList = Lists.newArrayList(AssessDataDicKeyConstant.MD_MARKET_COMPARE, AssessDataDicKeyConstant.MD_INCOME, AssessDataDicKeyConstant.MD_COST, AssessDataDicKeyConstant.MD_DEVELOPMENT, AssessDataDicKeyConstant.MD_STANDARD_ADJUSTMENT_PRICE);
            for (String methodNameEnum : stringList) {
                String formula = getDataMethodFormula(schemeJudgeObjectList, methodNameEnum, "参数");
                if (StringUtils.isNotBlank(formula.trim())) {
                    stringBuilder.append(generateCommonMethod.convertNumber(integerList)).append("号");
                    stringBuilder.append(StringUtils.trimToEmpty(formula));
                }
            }
            String indentHtml = generateCommonMethod.getIndentHtml(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.toString().length()).append(indentHtml);
        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), false);
        doc.save(localPath);
        return localPath;
    }

    /**
     * 获取方法的参数或者公式
     *
     * @param schemeJudgeObjectList
     * @param fieldName
     * @param name
     * @return
     */
    private String getDataMethodFormula(List<SchemeJudgeObject> schemeJudgeObjectList, String fieldName, String name) {
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        BaseDataDic byFieldName = baseDataDicService.getCacheDataDicByFieldName(fieldName);
        List<DataEvaluationMethod> dataEvaluationMethodList = evaluationMethodService.getMethodAllList();
        DataEvaluationMethod evaluationMethod = null;
        DataMethodFormula dataMethodFormula = null;
        if (CollectionUtils.isNotEmpty(dataEvaluationMethodList)) {
            if (byFieldName != null) {
                if (dataEvaluationMethodList.stream().filter(dataEvaluation -> Objects.equal(byFieldName.getName(), dataEvaluation.getName())).count() >= 1) {
                    evaluationMethod = dataEvaluationMethodList.stream().filter(dataEvaluation -> Objects.equal(byFieldName.getName(), dataEvaluation.getName())).findFirst().get();
                }
            }
        }
        if (evaluationMethod != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(evaluationMethod.getMethod());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                dataMethodFormula = dataMethodFormulaList.stream().findFirst().get();
            }
        }
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (byFieldName != null) {
                            if (Objects.equal(byFieldName.getName(), schemeJudgeFunction.getName())) {
                                if (dataMethodFormula != null) {
                                    linkedHashSet.add(byFieldName.getName());
                                    if ("公式".equals(name)) {
                                        linkedHashSet.add(dataMethodFormula.getFormula());
                                    }
                                    if ("参数".equals(name)) {
                                        linkedHashSet.add(dataMethodFormula.getRelevantParameter());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String s = StringUtils.join(linkedHashSet, "");
        return StringUtils.isNotBlank(s.trim()) ? s : "";
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
                            logger.error("土地权益状况未获取到", e);
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
                            logger.error("房屋权益状况未获取到", e);
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
                            logger.error("土地权益状况未获取到", e);
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
            if (knowTotalPrice.doubleValue() > 0) {
                knowTotalPrice = knowTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
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
        LinkedList<Double> doubleLinkedList = Lists.newLinkedList(Lists.newArrayList(50d, 30d, 30d, 30d, 30d, 50d, 55d, 60d, 50d, 50d));
        if (seat) {
            doubleLinkedList.add(1, doubleLinkedList.stream().limit(1).mapToDouble(Double::doubleValue).sum() * 3);
        }
        String localPath = getLocalPath();
        Table table = builder.startTable();
        final Integer colMax = seat ? new Integer(11) : new Integer(10);
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Map<SchemeReimbursementItemVo, List<SchemeJudgeObject>> reimbursementItemVoListMap = this.getSurveyAssetInventoryRightRecordListMap(schemeJudgeObjectList);
        if (!reimbursementItemVoListMap.isEmpty()) {
            List<SchemeJudgeObject> listA = Lists.newArrayList();
            List<SchemeJudgeObject> objectList = Lists.newArrayList();
            reimbursementItemVoListMap.entrySet().stream().forEach(entry -> listA.addAll(entry.getValue()));
            if (CollectionUtils.isNotEmpty(listA)) {
                objectList = Lists.newArrayList(CollectionUtils.subtract(schemeJudgeObjectList, listA.stream().distinct().collect(Collectors.toList())));
            }
            LinkedList<String> strings = Lists.newLinkedList(Lists.newArrayList("估价对象", "坐落", "用途(证载)", "用途(实际)", "房屋总层数", "所在层数", "建筑面积㎡", "单价（元/㎡）", "评估总价（万元）", "法定优先受偿款(万元)", "抵押价值(万元)"));
            if (!seat) {
                strings.remove(1);
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
                            this.writeJudgeObjectResultSurveyInCell(new SchemeReimbursementItemVo(), integerEntry.getKey(), integerEntry.getValue(), builder, doubleLinkedList, seat);
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
                                //builder.write(price.toString());
                            }
                            if (j == colMax - 3) {
                                BigDecimal temp = new BigDecimal(total.toString());
                                temp = temp.divide(new BigDecimal(10000));
                                temp = temp.setScale(4, BigDecimal.ROUND_HALF_UP);
                                builder.write(temp.toString());
                            }
                            BigDecimal knowTotalPrice = getSchemeReimbursementKnowTotalPrice();
                            if (j == colMax - 2) {
                                builder.write(generateCommonMethod.getBigDecimalRound(knowTotalPrice, true));
                            }
                            if (j == colMax - 1) {
                                BigDecimal mortgage = total.subtract(knowTotalPrice);
                                mortgage = mortgage.divide(new BigDecimal(10000));
                                mortgage = mortgage.setScale(4, BigDecimal.ROUND_HALF_UP);
                                builder.write(mortgage.toString());
                            }
                        }
                        builder.endRow();
                    } catch (Exception e) {
                    }
                }
                schemeJudgeObjectLinkedHashMap.clear();
            });
            //分组剩余的(大部分情况下不可能,但是不能排除意外嘛)
            if (CollectionUtils.isNotEmpty(objectList)) {
                for (SchemeJudgeObject schemeJudgeObject : objectList) {
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply != null) {
                        schemeJudgeObjectLinkedHashMap.put(basicApply, schemeJudgeObject);
                    }
                }
                if (!schemeJudgeObjectLinkedHashMap.isEmpty()) {
                    for (Map.Entry<BasicApply, SchemeJudgeObject> integerEntry : schemeJudgeObjectLinkedHashMap.entrySet()) {
                        try {
                            this.writeJudgeObjectResultSurveyInCell(new SchemeReimbursementItemVo(), integerEntry.getKey(), integerEntry.getValue(), builder, doubleLinkedList, seat);
                        } catch (Exception e) {
                        }
                    }
                }
                schemeJudgeObjectLinkedHashMap.clear();
            }
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价结果一览表 (肢体)
     *
     * @param itemVo
     * @param basicApply
     * @param schemeJudgeObject
     * @param builder
     * @param seat
     * @throws Exception
     */
    private void writeJudgeObjectResultSurveyInCell(SchemeReimbursementItemVo itemVo, BasicApply basicApply, SchemeJudgeObject schemeJudgeObject, DocumentBuilder builder, LinkedList<Double> doubleLinkedList, boolean seat) throws Exception {
        LinkedList<String> linkedLists = Lists.newLinkedList();
        final String nullValue = "";
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        linkedLists.add(getSchemeJudgeObjectShowName(schemeJudgeObject));//0
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
        if (declareRecord != null && declareRecord.getPracticalArea() != null) {//6
            linkedLists.add(declareRecord.getPracticalArea().toString());
        } else {
            linkedLists.add(nullValue);
        }
        if (declareRecord != null && declareRecord.getPrice() != null) {//7
            linkedLists.add(declareRecord.getPrice().toString());
        } else {
            linkedLists.add(nullValue);
        }
        if (declareRecord.getPrice() != null && declareRecord.getPracticalArea() != null) {//8
            BigDecimal total = declareRecord.getPrice().multiply(declareRecord.getPracticalArea());
            total = total.divide(new BigDecimal(10000));
            total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
            linkedLists.add(total.toString());
        } else {
            linkedLists.add(nullValue);
        }
        //抵押=总价-法定
        if (Objects.equal(projectInfo.getEntrustPurpose(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId())) {
            BigDecimal knowTotalPrice = getSchemeReimbursementKnowTotalPrice();
            linkedLists.add(knowTotalPrice.toString());//9
            if (declareRecord.getPrice() != null && declareRecord.getPracticalArea() != null) {
                BigDecimal totol = declareRecord.getPrice().multiply(declareRecord.getPracticalArea());
                BigDecimal mortgage = totol.subtract(knowTotalPrice);
                mortgage = mortgage.divide(new BigDecimal(10000));
                mortgage = mortgage.setScale(2, BigDecimal.ROUND_HALF_UP);
                linkedLists.add(mortgage.toString());//10
            } else {
                linkedLists.add(nullValue);
            }
        } else {
            linkedLists.add(nullValue);
            linkedLists.add(nullValue);
        }
        generateCommonMethod.writeWordTitle(builder, doubleLinkedList, linkedLists);
    }

    /**
     * 获取不重复楼盘和估价对象的一一对应集合
     *
     * @throws Exception
     */
    private LinkedHashMap<BasicApply, SchemeJudgeObject> getLinkedHashMapAndBasicApplyOrSchemeJudgeObject() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(schemeJudgeObjectList, true);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
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
                                String value = declareRecord.getStreetNumber();
                                if (StringUtils.isNotEmpty(value)) {
                                    stringMap.put(value,
                                            String.format("%s,%s",
                                                    basicApply.getPlanDetailsId().toString(),
                                                    schemeJudgeObject.getId().toString()));
                                }
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
                BasicApply basicApply = basicApplyService.getByBasicApplyId(basicEstate.getApplyId());
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
                List<String> stringArrayList = generateLoactionService.getExternalPublicServiceFacilities(basicApply);
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
                stringBuilder.append(String.format("综述:%s", generateCommonMethod.trim(basicEstate.getLocationDescribe())));
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
            BasicApply basicApply = basicApplyService.getByBasicApplyId(basicEstate.getApplyId());
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
                String s = generateLandEntityService.getInfrastructureCompleteness(landStateVo);
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
                    stringBuilder.append(String.format("综上所述:%s", generateCommonMethod.trim(s)));
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
                stringBuilder.append(generateCommonMethod.getIndentHtml((String.format("电梯:%s", generateCommonMethod.trim(generateHouseEntityService.getUnitElevator(judgeObjects))))));
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
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("13、建筑功能:%s", generateCommonMethod.trim(generateHouseEntityService.getBuildingFunction(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("14、新旧程度及维护使用情况")));
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateHouseEntityService.getDamagedDegree(judgeObjects)));

                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("15、其它:%s", generateCommonMethod.trim(generateHouseEntityService.getOther(judgeObjects)))));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("建筑实体分析:%s", generateCommonMethod.trim(generateHouseEntityService.getBuildEntityAnalysis(judgeObjects, schemeAreaGroup)))));
                documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
            }
        }
        doc.save(localPath);
        return localPath;
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
            if (true) {
                SchemeInfo schemeInfo = getSchemeInfoId(AssessDataDicKeyConstant.MD_MARKET_COMPARE, schemeJudgeObjectList.get(j - 2));
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    mdMarketCompare = mdMarketCompareService.getMdMarketCompare(schemeInfo.getMethodDataId());
                }
            }
            if (true) {
                SchemeInfo schemeInfo = getSchemeInfoId(AssessDataDicKeyConstant.MD_INCOME, schemeJudgeObjectList.get(j - 2));
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
        StringBuilder stringBuilder = new StringBuilder(8);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Multimap<String, String> multimap = ArrayListMultimap.create();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                SchemeInfo schemeInfo = getSchemeInfoId(AssessDataDicKeyConstant.MD_INCOME, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfo, projectId, areaId);
                    String value = generateMdIncomeService.getTenancyrestrictionReamrk();
                    if (StringUtils.isNotBlank(value)) {
                        multimap.put(value, getSchemeJudgeObjectShowName(schemeJudgeObject));
                    }
                }
            }
        }
        if (!multimap.isEmpty()) {
            if (multimap.keys().stream().distinct().count() == 1) {
                stringBuilder.append(multimap.keys().stream().distinct().findFirst().get());
            } else {
                Set<String> strings = Sets.newHashSet();
                multimap.entries().stream().distinct().forEach(oo -> strings.add(String.format("%s%s", oo.getValue(), oo.getKey())));
                stringBuilder.append(StringUtils.join(strings, "、"));
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("不考虑估价对象租赁因素的影响。");
        }
        return stringBuilder.toString();
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
        BaseDataDic mdIncome = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
        BaseDataDic mdCompare = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        Map<String, String> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<Integer> numbers = Lists.newArrayList(Lists.newArrayList(schemeJudgeObject.getNumber().split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
                SchemeInfo schemeInfo = null;
                //市场比较法
                schemeInfo = getSchemeInfoId(AssessDataDicKeyConstant.MD_MARKET_COMPARE, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(schemeJudgeObject.getId(), schemeInfo.getMethodDataId(), areaId);
                    try {
                        String generateCompareFile = generateMdCompareService.generateCompareFile();
                        File file = new File(generateCompareFile);
                        if (file.isFile()) {
                            String key = String.format("%s号:%s", generateCommonMethod.convertNumber(numbers), mdCompare.getName());
                            builder.insertHtml(generateCommonMethod.getWarpCssHtml("<div style='text-align:center;font-size:16.0pt;'>" + key + "</div>"), true);
                            //去掉html
                            key = key.replaceAll("^<[^>]+>|<[^>]+>$", "");
                            key = String.format("%s%s", key, UUID.randomUUID().toString());
                            map.put(key, generateCompareFile);
                            builder.writeln(key);
                        }
                    } catch (Exception e) {
                    }
                }
                //收益法
                schemeInfo = getSchemeInfoId(AssessDataDicKeyConstant.MD_INCOME, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfo, projectId, areaId);
                    String generateCompareFile = generateMdIncomeService.generateCompareFile();
                    File file = new File(generateCompareFile);
                    if (file.isFile()) {
                        String key = String.format("%s号:%s", generateCommonMethod.convertNumber(numbers), mdIncome.getName());
                        builder.insertHtml(generateCommonMethod.getWarpCssHtml("<div style='text-align:center;font-size:16.0pt;'>" + key + "</div>"), true);
                        //去掉html
                        key = key.replaceAll("^<[^>]+>|<[^>]+>$", "");
                        key = String.format("%s%s", key, UUID.randomUUID().toString());
                        map.put(key, generateCompareFile);
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
     *
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
                builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                builder.insertHtml(generateCommonMethod.getWarpCssHtml(schemeJudgeObject.getName()), true);
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
                    List<Map<String, String>> imgList = Lists.newArrayList();
                    builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    builder.insertHtml(generateCommonMethod.getWarpCssHtml(schemeJudgeObject.getName()), true);
                    for (SchemeReportFileItem sysAttachmentDto : sysAttachmentDtoList) {
                        Map<String, String> imgMap = Maps.newHashMap();
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getAttachmentId());
                        String imgName = sysAttachmentDto.getFileName();
                        imgMap.put(imgPath, imgName);
                        imgList.add(imgMap);
                    }
                    if (imgList.size() == 1) {
                        AsposeUtils.imageInsertToWrod2(imgList, 1, builder);
                    }
                    if (imgList.size() > 1) {
                        AsposeUtils.imageInsertToWrod2(imgList, 2, builder);
                    }
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
     *
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
     *
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
     *
     * @throws Exception
     */
    public String getCopyQualificationCertificateRealEstateValuationInstitution() throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForPractising();
        if (adCompanyQualificationDto != null && StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
            List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
            if(CollectionUtils.isEmpty(attachmentDtoList)) return null;
            List<String> images = Lists.newArrayList();
            for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                if (StringUtils.isNotBlank(imgPath)&&FileUtils.checkImgSuffix(imgPath)) {
                    images.add(imgPath);
                }
            }
            if (CollectionUtils.isNotEmpty(images)) {
                AsposeUtils.insertImage(localPath, images, 200, 100);
            }
        }
        return localPath;
    }

    /**
     * 注册房地产估价师注册证书复印件
     *
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
                if (StringUtils.isBlank(dataQualificationVo.getUserAccount())) continue;
                for (String account : dataQualificationVo.getUserAccount().split(",")) {
                    List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                    if (CollectionUtils.isEmpty(adPersonalQualificationDtoList)) continue;
                    adPersonalQualificationDtoList.forEach(adCompanyQualificationDto -> {
                        if (StringUtils.isBlank(adCompanyQualificationDto.getStandardImageJson())) return;
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
                                    logger.error(e1.getMessage(), e1);
                                }
                            }
                        }
                    });
                }
            }
        }
        //图片特殊处理
        if (CollectionUtils.isNotEmpty(images)) {
            AsposeUtils.insertImage(localPath, images, 200, 100);
        }
        return localPath;
    }


    /**
     * 获取如收益法,市场比较法，假设开发法，成本法等的id
     *
     * @param fieldName
     * @param schemeJudgeObject
     * @return SchemeInfo
     */
    private SchemeInfo getSchemeInfoId(String fieldName, SchemeJudgeObject schemeJudgeObject) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(fieldName);
        List<DataEvaluationMethod> dataEvaluationMethodList = evaluationMethodService.getMethodAllList();
        DataEvaluationMethod dataEvaluationMethod = null;
        SchemeInfo schemeInfo = null;
        if (CollectionUtils.isNotEmpty(dataEvaluationMethodList)) {
            if (baseDataDic != null) {
                if (dataEvaluationMethodList.stream().filter(oo -> Objects.equal(baseDataDic.getName(), oo.getName())).count() >= 1) {
                    dataEvaluationMethod = dataEvaluationMethodList.stream().filter(oo -> Objects.equal(baseDataDic.getName(), oo.getName())).findFirst().get();
                }
            }
        }
        if (dataEvaluationMethod != null) {
            schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), dataEvaluationMethod.getMethod());
        }
        if (schemeInfo != null) {
            return schemeInfo;
        }
        return null;
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
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(reportThinking), true);
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
                String streetNumber = recordList.get(0).getStreetNumber();
                List<String> list = Lists.newArrayList();
                for (DeclareRecord declareRecord : recordList) {
                    list.add(declareRecord.getSeat().replace(streetNumber, ""));
                }
                buffer.append(streetNumber).append(entry.getKey().getName()).append(publicService.fusinString(list, false));
                buffer.append(",");
                Map<Integer, String> certUseMap = Maps.newHashMap();
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
                    practicalUseMap.put(number, schemeJudgeObject.getPracticalUse());
                    buildAreaMap.put(number, String.format("%s㎡", schemeJudgeObject.getFloorArea()));
                    evaluationAreaMap.put(number, String.format("%s㎡", schemeJudgeObject.getEvaluationArea()));
                    landRightNatureMap.put(number, declareRecord.getLandRightNature());
                    ownershipMap.put(number, declareRecord.getOwnership());
                    structureMap.put(number, declareRecord.getHousingStructure());
                }
                buffer.append(generateCommonMethod.judgeSummaryDesc(certUseMap, "设定用途", false)).append(",");//设定用途
                buffer.append(generateCommonMethod.judgeSummaryDesc(practicalUseMap, "实际用途", false)).append(",");//实际用途
                buffer.append(generateCommonMethod.judgeSummaryDesc(buildAreaMap, "建筑面积", false)).append(",");//建筑面积
                buffer.append(generateCommonMethod.judgeSummaryDesc(evaluationAreaMap, "评估面积", false)).append(",");//评估面积
                buffer.append(generateCommonMethod.judgeSummaryDesc(landRightNatureMap, "权利性质", false)).append(",");//权利性质
                buffer.append(generateCommonMethod.judgeSummaryDesc(ownershipMap, "权利人", false)).append(",");//权利人
                buffer.append(generateCommonMethod.judgeSummaryDesc(structureMap, "房屋结构", false)).append(",");//房屋结构
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(buffer.toString())));
                buffer.delete(0, buffer.toString().length());
                i++;

            }
        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        document.save(localPath);
        return localPath;
    }


    //------------------------------------------------------------||待删除方法 start -------------------------------------------------------//


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
        this.mdCommonService = SpringContextUtils.getBean(MdCommonService.class);
        this.generateEquityService = SpringContextUtils.getBean(GenerateEquityService.class);
        this.basicApplyService = SpringContextUtils.getBean(BasicApplyService.class);
        this.surveyAssetInventoryRightRecordCenterService = SpringContextUtils.getBean(SurveyAssetInventoryRightRecordCenterService.class);
        this.dataBestUseDescriptionService = SpringContextUtils.getBean(DataBestUseDescriptionService.class);
        this.baseProjectClassifyService = SpringContextUtils.getBean(BaseProjectClassifyService.class);
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

    public List<SchemeReimbursementItemVo> getSchemeReimbursementItemVoList() {
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
    public List<SchemeJudgeObject> getSchemeJudgeObjectList() {
        return generateCommonMethod.getSortSchemeJudgeObject(this.schemeJudgeObjectList);
    }

    public GenerateBaseExamineService getGenerateBaseExamineService(Integer planDetailsId) {
        return new GenerateBaseExamineService(planDetailsId);
    }
}
