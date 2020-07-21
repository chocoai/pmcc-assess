package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.method.MdDevelopmentTypeEnum;
import com.copower.pmcc.assess.common.enums.report.ReportFieldDevelopmentEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdEconomicIndicatorsApplyDto;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.service.tool.ToolRewardRateService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/8/24 18:48
 * @Description:假设开发法
 */
@Service
public class MdDevelopmentService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdDevelopmentDao mdDevelopmentDao;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdCalculatingMethodEngineeringCostService mdCalculatingMethodEngineeringCostService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private MdDevelopmentInfrastructureChildrenService mdDevelopmentInfrastructureChildrenService;
    @Autowired
    private ToolRewardRateService toolRewardRateService;
    @Autowired
    private MdEconomicIndicatorsService mdEconomicIndicatorsService;

    @Transactional(rollbackFor = {Exception.class})
    public void copyDevelopmentById(Integer copyId, Integer masterId, StringBuilder stringBuilder) throws Exception {
        MdDevelopment copy = getMdDevelopmentById(copyId);
        MdDevelopment target = getMdDevelopmentById(masterId);
        if (copy == null) {
            stringBuilder.append("目标数据不存在");
            throw new Exception("目标数据不存在");
        }
        if (target == null) {
            stringBuilder.append("拷贝异常");
            throw new Exception("拷贝异常");
        }
        Integer copyPlanDetailsId = copy.getPlanDetailsId();
        Integer targetPlanDetailsId = target.getPlanDetailsId();
        ProjectPlanDetails copyPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(copyPlanDetailsId);
        ProjectPlanDetails targetPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(targetPlanDetailsId);

        //工程费 copy
        mdCalculatingMethodEngineeringCostService.copyMdCalculatingMethodEngineeringCost(targetPlanDetails, copyPlanDetails);

        //基础设施配套费 copy
        mdDevelopmentInfrastructureChildrenService.copyData(targetPlanDetails, copyPlanDetails, copyId, masterId);

        ToolRewardRate toolRewardRate = new ToolRewardRate();
        //土地还原率 copy
        toolRewardRateService.copyData(copy.getRewardRateId(), toolRewardRate);

        MdEconomicIndicators economicIndicators = new MdEconomicIndicators();
        //经济指标 copy
        mdEconomicIndicatorsService.copyDataEconomicIndicators(copy.getEconomicId(), economicIndicators);
        copy.setId(null);
        copy.setCenterId(null);
        copy.setPlanDetailsId(null);
        copy.setEconomicId(null);
        copy.setRewardRateId(null);
        BeanCopyHelp.copyPropertiesIgnoreNull(copy, target);
        target.setRewardRateId(toolRewardRate.getId());
        target.setEconomicId(economicIndicators.getId());
        saveAndUpdateMdDevelopment(target);
        stringBuilder.append("拷贝成功!");
    }


    /**
     * 初始化数据
     *
     * @param target
     * @param projectPlanDetails
     * @param processInsId
     * @throws BusinessException
     */
    public void initData(MdDevelopment target, ProjectPlanDetails projectPlanDetails, String processInsId) throws BusinessException {
        if (target == null) {
            return;
        }
        Map<Integer, Integer> mapEconomicId = new HashMap<>(2);
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = Lists.newArrayList();
        boolean firstInit = (target.getId() == null || target.getId() == 0);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        if (schemeJudgeObject != null) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRecord.getDataTableName())) {
                query.setHouseId(declareRecord.getDataTableId());
                query.setType(DeclareRealtyHouseCert.class.getSimpleName());
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList2 = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList2)) {
                    centerList.addAll(centerList2);
                }
            }
            if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
                query.setRealEstateId(declareRecord.getDataTableId());
                query.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList2 = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList2)) {
                    centerList.addAll(centerList2);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(centerList)) {
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                target.setCenterId(center.getId());
                if (center.getIndicatorId() == null) {
                    continue;
                }
                mapEconomicId.put(center.getIndicatorId(), center.getId());
            }
        }
        if (!mapEconomicId.isEmpty()) {
            boolean tempFlag = false;
            if (target.getEconomicId() != null) {
                MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = mdEconomicIndicatorsService.getEconomicIndicatorsInfo(target.getEconomicId());
                tempFlag = mdEconomicIndicatorsApplyDto.getEconomicIndicators() != null && mdEconomicIndicatorsApplyDto.getEconomicIndicators().getId() != null;
            }
            if (!tempFlag) {
                mapEconomicId.forEach((integer, integer2) -> {
                    target.setEconomicId(integer);
                    target.setCenterId(integer2);
                });
            }
        }
        saveAndUpdateMdDevelopment(target);
        if (firstInit) {
            SchemeInfo schemeInfo = new SchemeInfo();
            schemeInfo.setProjectId(projectPlanDetails.getProjectId());
            schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
            schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT).getId());
            schemeInfo.setProcessInsId(org.apache.commons.lang3.StringUtils.isNotEmpty(processInsId) ? processInsId : "0");
            schemeInfo.setMethodDataId(target.getId());
            schemeInfoService.saveSchemeInfo(schemeInfo);
        }
    }

    /**
     * 计算假设开发法的测算方法
     *
     * @param key
     * @param target
     * @return
     */
    public String getFieldObjectValue(ReportFieldDevelopmentEnum key, MdDevelopment target) {
        try {
            return getFieldObjectValueHandle(key, target);
        } catch (Exception e) {
            return "0";
        }
    }

    public String getFieldObjectValueReport(ReportFieldDevelopmentEnum key, MdDevelopment target) {
        try {
            return getFieldObjectValueHandle(key, target);
        } catch (Exception e) {
            return null;
        }
    }

    public void calculationNumeric(MdDevelopment target) {
        if (target == null) {
            return;
        }
        saveAndUpdateMdDevelopment(target);
        getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_Price, target);
        saveAndUpdateMdDevelopment(target);
    }


    /**
     * 计算假设开发法的测算方法
     * 运算类的数据获取方法
     *
     * @param key
     * @param target
     * @return
     */
    private String getFieldObjectValueHandle(ReportFieldDevelopmentEnum key, MdDevelopment target) {
        switch (key) {
            case Development_projectConstructionPeriod: {
                if (!ArithmeticUtils.checkNotNull(new BigDecimal[]{target.getDevelopedYear(), target.getRemainingDevelopmentYear()})) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.add(target.getDevelopedYear(), target.getRemainingDevelopmentYear());
                target.setProjectConstructionPeriod(bigDecimal);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_total_saleableArea: {//f18
                BigDecimal unsaleableBuildingArea = new BigDecimal(0);
                if (!ArithmeticUtils.checkNotNull(new BigDecimal[]{target.getSaleableArea()})) {
                    return "";
                }
                if (target.getUnsaleableBuildingArea() != null) {
                    unsaleableBuildingArea = new BigDecimal(target.getUnsaleableBuildingArea().toString());
                }
                BigDecimal bigDecimal = ArithmeticUtils.addModel(target.getSaleableArea(), unsaleableBuildingArea, null, null);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_Land_Area: {//f18
                return ArithmeticUtils.getBigDecimalString(target.getLandArea());
            }
            case Development_constructionInstallationEngineeringFee: {//d21 or e21
                String f18 = target.getPlannedBuildingArea() == null ? "" : target.getPlannedBuildingArea().toString();
                if (!ArithmeticUtils.checkNotNull(f18)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getConstructionInstallationEngineeringFee())) {
                    return "";
                }
                BigDecimal v = ArithmeticUtils.multiply(target.getConstructionInstallationEngineeringFee(), ArithmeticUtils.createBigDecimal(f18));
                BigDecimal bigDecimal = ArithmeticUtils.div(v, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_reconnaissanceDesignTotal: {//d20 or e20
                //=F18*F21*F20/10000
                String f18 = target.getPlannedBuildingArea() == null ? "" : target.getPlannedBuildingArea().toString();
                if (!ArithmeticUtils.checkNotNull(f18)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getConstructionInstallationEngineeringFee())) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getReconnaissanceDesign())) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getConstructionInstallationEngineeringFee());
                bigDecimal = ArithmeticUtils.multiply(bigDecimal, target.getReconnaissanceDesign());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_infrastructureCostTotal: {//d22 or e22
                //F18*F22/10000
                String f18 = target.getPlannedBuildingArea() == null ? "" : target.getPlannedBuildingArea().toString();
                if (!ArithmeticUtils.checkNotNull(f18)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getInfrastructureCost())) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getInfrastructureCost());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_infrastructureMatchingCostTotal: {//d23 or e23
                //F18*F23/10000
                String f18 = target.getPlannedBuildingArea() == null ? "" : target.getPlannedBuildingArea().toString();
                if (!ArithmeticUtils.checkNotNull(f18)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getInfrastructureMatchingCost())) {
                    target.setInfrastructureMatchingCost(ArithmeticUtils.createBigDecimal(0));
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getInfrastructureMatchingCost());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_devDuringTotal: {//d24 or e24
                //F18*F24/10000
                String f18 = target.getPlannedBuildingArea() == null ? "" : target.getPlannedBuildingArea().toString();
                if (!ArithmeticUtils.checkNotNull(f18)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getDevDuring())) {
                    target.setDevDuring(ArithmeticUtils.createBigDecimal(0));
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getDevDuring());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_otherEngineeringCostTotal: {//d25 or e25
                //F18*F25/10000
                String f18 = target.getPlannedBuildingArea() == null ? "" : target.getPlannedBuildingArea().toString();
                if (!ArithmeticUtils.checkNotNull(f18)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getOtherEngineeringCost())) {
                    target.setOtherEngineeringCost(ArithmeticUtils.createBigDecimal(0));
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getOtherEngineeringCost());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_constructionSubTotal: {//d26或者 e26
                //ROUND(SUM(D20:E25),2)
                String d20 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_reconnaissanceDesignTotal, target);
                String d21 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_constructionInstallationEngineeringFee, target);
                String d22 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_infrastructureCostTotal, target);
                String d23 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_infrastructureMatchingCostTotal, target);
                String d24 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_devDuringTotal, target);
                String d25 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_otherEngineeringCostTotal, target);
                String[] strings = new String[]{d20, d21, d22, d23, d24, d25};
                if (!ArithmeticUtils.checkNotNull(strings)) {
                    return "";
                }
                BigDecimal[] bigDecimals = new BigDecimal[strings.length];
                Transformer<String, BigDecimal> transformer = new Transformer<String, BigDecimal>() {
                    @Override
                    public BigDecimal transform(String s) {
                        return ArithmeticUtils.createBigDecimal(s);
                    }
                };
                for (int i = 0; i < strings.length; i++) {
                    bigDecimals[i] = transformer.transform(strings[i]);
                }
                BigDecimal value = ArithmeticUtils.add(bigDecimals);
                target.setConstructionCostSubtotal(ArithmeticUtils.round(value, 2, BigDecimal.ROUND_HALF_UP));
                return String.valueOf(value);
            }
            case Development_unforeseenExpensesTotal: {//d27 or e27
                //SUM(D20:E25)*F27
                String d26 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_constructionSubTotal, target);
                if (!ArithmeticUtils.checkNotNull(d26)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getUnforeseenExpenses())) {
                    target.setUnforeseenExpenses(ArithmeticUtils.createBigDecimal(0));
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(d26), target.getUnforeseenExpenses());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_LandGetCost: {//d28
                //1+D29+D30
                if (!ArithmeticUtils.checkNotNull(new BigDecimal[]{target.getDeedTaxRate(), target.getTransactionTaxRate()})) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.add(ArithmeticUtils.createBigDecimal(1), target.getDeedTaxRate());
                bigDecimal = ArithmeticUtils.add(bigDecimal, target.getTransactionTaxRate());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_managementExpenseCorrectRate: {//d32
                //=G32*D28
                String d28 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_LandGetCost, target);
                if (!ArithmeticUtils.checkNotNull(d28)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getManagementExpense())) {
                    target.setManagementExpense(ArithmeticUtils.createBigDecimal(0));
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(d28), target.getManagementExpense());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_managementExpenseTotal: {//f32
                //(SUM(D26+D27)+F31)*G32
                String d26 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_constructionSubTotal, target);
                String d27 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_unforeseenExpensesTotal, target);
                if (!ArithmeticUtils.checkNotNull(Arrays.asList(d26, d27))) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getLandGetRelevant())) {
                    target.setLandGetRelevant(ArithmeticUtils.createBigDecimal(0));
                }
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getManagementExpense()))) {
                    return "";
                }
                BigDecimal[] doubles = new BigDecimal[]{ArithmeticUtils.createBigDecimal(d26), ArithmeticUtils.createBigDecimal(d27), target.getLandGetRelevant()};
                BigDecimal bigDecimal = ArithmeticUtils.add(doubles);
                bigDecimal = ArithmeticUtils.multiply(bigDecimal, target.getManagementExpense());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_salesFeeTotal: {//f33
                //=IF(E16=" "," ",E16*G33)
                BigDecimal e16 = target.getTotalSaleableAreaPrice();
                if (!ArithmeticUtils.checkNotNull(e16)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getSalesFee())) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(e16, target.getSalesFee());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_interestInvestmentCorrectRate: {//d34
                //=ROUND(((1+G34)^D3-1)*D28+((1+G34)^(D3/2)-1)*D32,4)
                BigDecimal g34 = target.getInterestInvestmentTax();
                String d3 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_projectConstructionPeriod, target);
                String d28 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_LandGetCost, target);
                String d32 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_managementExpenseCorrectRate, target);
                if (!ArithmeticUtils.checkNotNull(Arrays.asList(d3, d28, d32))) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(g34)) {
                    return "";
                }
                double c1 = (Math.pow(1 + g34.doubleValue(), Double.valueOf(d3)) - 1) * Double.valueOf(d28);
                double c2 = (Math.pow(1 + g34.doubleValue(), Double.valueOf(d3) / 2) - 1) * Double.valueOf(d32);
                double c = c1 + c2;
                return String.valueOf(c);
            }
            case Development_interestInvestment: {//f34
                //D21+D23+D24+D25+D27+F32+F33)  *  ( (1+G34)^(D3/2)-1)   +(SUM(D20+D22)+E31) * (  (1+G34)^(D3)-1 )    )
                BigDecimal g34 = target.getInterestInvestmentTax();
                String d20 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_reconnaissanceDesignTotal, target);
                String d21 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_constructionInstallationEngineeringFee, target);
                String d22 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_infrastructureCostTotal, target);
                String d23 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_infrastructureMatchingCostTotal, target);
                String d24 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_devDuringTotal, target);
                String d25 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_otherEngineeringCostTotal, target);
                String d27 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_unforeseenExpensesTotal, target);
                String f33 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_salesFeeTotal, target);
                String f32 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_managementExpenseTotal, target);
                String d3 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_projectConstructionPeriod, target);
                if (Objects.equal(target.getType(), MdDevelopmentTypeEnum.developmentEngineering.getKey())) {
                    d3 = target.getRemainingDevelopmentYear().toString();
                }
                BigDecimal f31 = target.getLandGetRelevant();
                List<String> stringList = Arrays.asList(d21, d23, d24, d25, d27, f32, f33);
                if (!ArithmeticUtils.checkNotNull(stringList)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(g34)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(d3)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(d22)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(d20)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(f31)) {
                    return "";
                }
                List<BigDecimal> bigDecimalList = stringList.stream().map(s -> ArithmeticUtils.createBigDecimal(s)).collect(Collectors.toList());
                BigDecimal a = ArithmeticUtils.add(bigDecimalList);
                double b = Math.pow(1 + g34.doubleValue(), Double.valueOf(d3) / 2) - 1;
                BigDecimal c1 = ArithmeticUtils.multiply(a, ArithmeticUtils.createBigDecimal(b));
                double c2 = (Double.valueOf(d20) + Double.valueOf(d22) + f31.doubleValue()) * (Math.pow(1 + g34.doubleValue(), Double.valueOf(d3)) - 1);
                BigDecimal c = ArithmeticUtils.add(c1.toString(), String.valueOf(c2));
                target.setInterestInvestment(ArithmeticUtils.round(c, 2, BigDecimal.ROUND_HALF_UP));
                return c.toString();
            }
            case Development_investmentProfitCorrectRate: {//d35
                //(D28+D32)*G35
                String d28 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_LandGetCost, target);
                String d32 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_managementExpenseCorrectRate, target);
                BigDecimal g35 = target.getInvestmentProfitTax();
                if (!ArithmeticUtils.checkNotNull(Arrays.asList(d28, d32))) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(g35)) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.add(ArithmeticUtils.createBigDecimal(d28), ArithmeticUtils.createBigDecimal(d32));
                bigDecimal = ArithmeticUtils.multiply(bigDecimal, g35);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_investmentProfit: {//f35
                //(D26+D27+F32+F33+E31)*G35
                String d26 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_constructionSubTotal, target);
                String d27 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_unforeseenExpensesTotal, target);
                String f33 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_salesFeeTotal, target);
                String f32 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_managementExpenseTotal, target);
                BigDecimal f31 = target.getLandGetRelevant();
                BigDecimal g35 = target.getInvestmentProfitTax();
                if (!ArithmeticUtils.checkNotNull(f31)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(g35)) {
                    return "";
                }
                String[] strings = new String[]{d26, d27, f32, f33, f31.toString()};
                if (!ArithmeticUtils.checkNotNull(strings)) {
                    return "";
                }
                BigDecimal a = ArithmeticUtils.createBigDecimal(ArithmeticUtils.add(strings));
                BigDecimal bigDecimal = ArithmeticUtils.multiply(a, g35);
                target.setInvestmentProfit(ArithmeticUtils.round(bigDecimal, 2, BigDecimal.ROUND_HALF_UP));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_projectDevelopmentIncomeCorrectRate: {//D36
                //=F37+F38+F39
                BigDecimal[] bigDecimals = new BigDecimal[3];
                if (target.getSalesTaxAndAdditional() != null) {
                    bigDecimals[0] = target.getSalesTaxAndAdditional();
                } else {
                    bigDecimals[0] = ArithmeticUtils.createBigDecimal(0);
                }
                if (target.getLandValueAddedTax() != null) {
                    bigDecimals[1] = target.getLandValueAddedTax();
                } else {
                    bigDecimals[1] = ArithmeticUtils.createBigDecimal(0);
                }
                if (target.getProjectDevelopmentIncomeTax() != null) {
                    bigDecimals[2] = target.getProjectDevelopmentIncomeTax();
                } else {
                    bigDecimals[2] = ArithmeticUtils.createBigDecimal(0);
                }
                BigDecimal bigDecimal = ArithmeticUtils.add(bigDecimals);
                return bigDecimal.toString();
            }
            case Development_projectDevelopmentIncomeValue: {//f36
                //E16*D36
                String d36 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_projectDevelopmentIncomeCorrectRate, target);
                BigDecimal e16 = target.getTotalSaleableAreaPrice();
                if (!ArithmeticUtils.checkNotNull(d36)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(e16)) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(d36), e16);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_LandPriceCorrectRate: {//h40
                //=D29+D30+D32+D35+D34
                BigDecimal d29 = target.getDeedTaxRate();
                BigDecimal d30 = target.getTransactionTaxRate();
                String d32 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_managementExpenseCorrectRate, target);
                String d34 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_interestInvestmentCorrectRate, target);
                String d35 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_investmentProfitCorrectRate, target);
                if (!ArithmeticUtils.checkNotNull(new BigDecimal[]{d29, d30})) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(new String[]{d32, d34, d35})) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.add(new BigDecimal[]{
                        ArithmeticUtils.createBigDecimal(d35),
                        ArithmeticUtils.createBigDecimal(d34),
                        ArithmeticUtils.createBigDecimal(d32),
                        d30,
                        d29});
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_LandPriceCorrectValue: {// f40
                //=E16-D26-D27-E31-SUM(F32:F35)-F36)
                BigDecimal e16 = target.getTotalSaleableAreaPrice();
                String d26 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_constructionSubTotal, target);
                String d27 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_unforeseenExpensesTotal, target);
                String f32 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_managementExpenseTotal, target);
                BigDecimal f31 = target.getLandGetRelevant();
                String f33 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_salesFeeTotal, target);
                String f36 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_projectDevelopmentIncomeValue, target);
                String f34 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_interestInvestment, target);
                String f35 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_investmentProfit, target);
                if (!ArithmeticUtils.checkNotNull(new String[]{d26, d27, f32, f33, f36, f34, f35})) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(new BigDecimal[]{e16, f31})) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.add(new BigDecimal[]{
                        ArithmeticUtils.createBigDecimal(d26),
                        ArithmeticUtils.createBigDecimal(d27),
                        f31,
                        ArithmeticUtils.createBigDecimal(f32),
                        ArithmeticUtils.createBigDecimal(f33),
                        ArithmeticUtils.createBigDecimal(f34),
                        ArithmeticUtils.createBigDecimal(f35),
                        ArithmeticUtils.createBigDecimal(f36),
                });
                bigDecimal = ArithmeticUtils.sub(e16.toString(), bigDecimal.toString());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_LandPriceValue: {//e40
                //=F40/(1+H40)
                String f40 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_LandPriceCorrectValue, target);
                String h40 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_LandPriceCorrectRate, target);
                if (!ArithmeticUtils.checkNotNull(new String[]{f40, h40})) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.add(String.valueOf(1), h40);
                bigDecimal = ArithmeticUtils.div(ArithmeticUtils.createBigDecimal(f40), bigDecimal);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_assessPrice: {//d41
                //=E40/F18*10000
                String e40 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_LandPriceValue, target);
                String f18 = null;
                if (Objects.equal(target.getType(), MdDevelopmentTypeEnum.developmentLand.getKey())) {
                    f18 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_Land_Area, target);
                } else {
                    f18 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_total_saleableArea, target);
                }
                if (!ArithmeticUtils.checkNotNull(new String[]{e40, f18})) {
                    target.setAssessPrice(ArithmeticUtils.createBigDecimal(0));
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.div(ArithmeticUtils.createBigDecimal(e40), ArithmeticUtils.createBigDecimal(f18));
                bigDecimal = ArithmeticUtils.multiply(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                target.setAssessPrice(ArithmeticUtils.round(bigDecimal, 2, BigDecimal.ROUND_HALF_UP));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_LandPriceCoefficient: {//d43
                //=IF(E43=0," ",ROUND((1-1/(1+E43)^G43)/(1-1/(1+E43)^F43),4))
                BigDecimal e43 = target.getRemunerationRate();
                BigDecimal g43 = target.getRemainingYears();
                BigDecimal f43 = target.getStatutoryLife();
                if (!ArithmeticUtils.checkNotNull(e43)) {
                    e43 = ArithmeticUtils.createBigDecimal(0);
                }
                if (!ArithmeticUtils.checkNotNull(f43)) {
                    f43 = ArithmeticUtils.createBigDecimal(0);
                }
                if (!ArithmeticUtils.checkNotNull(g43)) {
                    g43 = ArithmeticUtils.createBigDecimal(0);
                }
                double a = 1 - 1 / Math.pow(1 + e43.doubleValue(), g43.doubleValue());
                double b = 1 - 1 / Math.pow(1 + e43.doubleValue(), f43.doubleValue());//特殊处理一下
                //当b= 0的时候
                if (b == 0 && a != 0) {
                    double c = a;
                    a = b;
                    b = c;
                }
                if (b == 0 && a == 0) {
                    return "0";
                }
                BigDecimal bigDecimal = ArithmeticUtils.div(ArithmeticUtils.createBigDecimal(a), ArithmeticUtils.createBigDecimal(b));
                return ArithmeticUtils.round(bigDecimal.toString(), 4);
            }
            case Development_Price: {//d47
                //=D41*D43*D44*D45+D46
                String d41 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_assessPrice, target);
                String d43 = getFieldObjectValueHandle(ReportFieldDevelopmentEnum.Development_LandPriceCoefficient, target);
                BigDecimal d44 = target.getAmendmentStatusRights();
                BigDecimal d45 = target.getOtherAmendments();
                BigDecimal d46 = target.getDevelopmentDegreeRevision();
                //必要得  这个一定不能为null ,核心数据
                if (!ArithmeticUtils.checkNotNull(d41)) {
                    target.setPrice(ArithmeticUtils.createBigDecimal(0));
                    return target.getPrice().toBigInteger().toString();
                }

                BigDecimal result = ArithmeticUtils.createBigDecimal(d41);

                if (ArithmeticUtils.checkNotNull(d43) && !Objects.equal("0", d43)) {
                    result = ArithmeticUtils.multiply(result, ArithmeticUtils.createBigDecimal(d43));
                }

                if (ArithmeticUtils.checkNotNull(d44) && d44.doubleValue() != 0) {
                    result = ArithmeticUtils.multiply(result, d44);
                }

                if (ArithmeticUtils.checkNotNull(d45) && d45.doubleValue() != 0) {
                    result = ArithmeticUtils.multiply(result, d45);
                }

                if (ArithmeticUtils.checkNotNull(d46) && d46.doubleValue() != 0) {
                    result = ArithmeticUtils.add(result, d46);
                }

                result = ArithmeticUtils.round(result, 2, BigDecimal.ROUND_HALF_UP);
                target.setPrice(result);
                return ArithmeticUtils.getBigDecimalString(result);
            }
            default:
                return "";
        }
    }

    public MdDevelopment initExplore(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        MdDevelopment mdDevelopment = new MdDevelopment();
        mdDevelopment.setName(schemeJudgeObject.getName());
        mdDevelopment.setCreator(commonService.thisUserAccount());
        saveAndUpdateMdDevelopment(mdDevelopment);
        return mdDevelopment;
    }

    public MdDevelopment getMdDevelopmentById(Integer id) {
        return mdDevelopmentDao.getMdDevelopmentById(id);
    }

    public void saveAndUpdateData(String formData, ProjectPlanDetails projectPlanDetails) {
        MdDevelopment mdDevelopment = JSONObject.parseObject(formData, MdDevelopment.class);
        mdDevelopment.setContent(formData);
        this.saveAndUpdateMdDevelopment(mdDevelopment);
        mdCalculatingMethodEngineeringCostService.clearOver(projectPlanDetails, mdDevelopment.getType());


    }

    public void saveAndUpdateMdDevelopment(MdDevelopment oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentDao.addMdDevelopment(oo);
        } else {
            mdDevelopmentDao.updateMdDevelopment(oo);
        }
    }

    public MdDevelopmentVo getMdDevelopmentVo(MdDevelopment oo) {
        if (oo == null) {
            return null;
        }
        MdDevelopmentVo vo = new MdDevelopmentVo();
        BeanUtils.copyProperties(oo, vo);
        if (StringUtils.isNotEmpty(oo.getConstructionInstallationEngineeringFeeIds())) {
            List<Integer> integerList = FormatUtils.transformString2Integer(oo.getConstructionInstallationEngineeringFeeIds());
            if (CollectionUtils.isNotEmpty(integerList)) {
                for (Integer integer : integerList) {
                    MdArchitecturalObj architecturalObj = mdArchitecturalObjService.getMdArchitecturalObjById(integer);
                    if (architecturalObj == null) {
                        continue;
                    }
                    vo.getConstructionInstallationEngineeringFeeDtos().add(new KeyValueDto(architecturalObj.getId().toString(), architecturalObj.getPrice() == null ? "0" : architecturalObj.getPrice().setScale(2, BigDecimal.ROUND_UP).toString()));
                }
            }
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(oo.getParcelSettingOuter())) {
            List<Integer> ids = FormatUtils.transformString2Integer(oo.getParcelSettingOuter());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(ids)) {
                List<String> stringList = Lists.newArrayList();
                for (Integer integer : ids) {
                    stringList.add(baseDataDicService.getNameById(integer));
                }
                vo.setParcelSettingOuterName(org.apache.commons.lang3.StringUtils.join(stringList, "，"));
            }
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(oo.getParcelSettingInner())) {
            List<Integer> ids = FormatUtils.transformString2Integer(oo.getParcelSettingInner());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(ids)) {
                List<String> stringList = Lists.newArrayList();
                for (Integer integer : ids) {
                    stringList.add(baseDataDicService.getNameById(integer));
                }
                vo.setParcelSettingInnerName(org.apache.commons.lang3.StringUtils.join(stringList, "，"));
            }
        }

        return vo;
    }


}
