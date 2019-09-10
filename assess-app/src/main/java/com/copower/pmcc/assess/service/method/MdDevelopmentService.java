package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private MdDevelopmentInfrastructureChildrenService mdDevelopmentInfrastructureChildrenService;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 初始化数据
     * @param target
     * @param projectPlanDetails
     * @param processInsId
     * @throws BusinessException
     */
    public void initData(MdDevelopment target, ProjectPlanDetails projectPlanDetails, String processInsId)throws BusinessException {
        if (target == null) {
            return;
        }
        boolean firstInit = (target.getId() == null || target.getId() == 0);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        List<Integer> integerList = Lists.newArrayList();
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRecord.getDataTableName())) {
            query.setHouseId(declareRecord.getDataTableId());
            query.setType(DeclareRealtyHouseCert.class.getSimpleName());
            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centerList)) {
                List<Integer> integerList2 = centerList.stream().filter(oo -> oo.getIndicatorId() != null).map(oo -> oo.getIndicatorId()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(integerList2)) {
                    integerList.addAll(integerList2);
                }
            }
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
            query.setRealEstateId(declareRecord.getDataTableId());
            query.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centerList)) {
                List<Integer> integerList2 = centerList.stream().filter(oo -> oo.getIndicatorId() != null).map(oo -> oo.getIndicatorId()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(integerList2)) {
                    integerList.addAll(integerList2);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(integerList)) {
            target.setEconomicId(integerList.stream().findFirst().get());
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
    public String getFieldObjectValue(BaseReportFieldEnum key, MdDevelopment target) {
        try {
            return getFieldObjectValueHandle(key, target);
        } catch (Exception e) {
            return "0";
        }
    }

    public String calculationNumeric(MdDevelopment target) {
        String value = new MdDevelopmentService().getFieldObjectValueHandle(BaseReportFieldEnum.Development_Price, target);
        if (target.getId() != null && target.getId() != 0) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mdDevelopmentDao.updateMdDevelopment(target);
                }
            });
        }
        return value;
    }


    /**
     * 计算假设开发法的测算方法
     * 运算类的数据获取方法
     *
     * @param key
     * @param target
     * @return
     */
    private String getFieldObjectValueHandle(BaseReportFieldEnum key, MdDevelopment target) {
        switch (key) {
            case Development_projectConstructionPeriod: {
                BigDecimal bigDecimal = ArithmeticUtils.add(target.getDevelopedYear(), target.getRemainingDevelopmentYear());
                target.setProjectConstructionPeriod(bigDecimal);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_total_saleableArea: {//f18
                BigDecimal bigDecimal = ArithmeticUtils.addModel(target.getSaleableArea(), target.getUnsaleableBuildingArea(), null, null);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_constructionInstallationEngineeringFee: {//d21 or e21
                String f18 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_total_saleableArea, target);
                BigDecimal v = ArithmeticUtils.multiply(target.getConstructionInstallationEngineeringFee(), ArithmeticUtils.createBigDecimal(f18));
                BigDecimal bigDecimal = ArithmeticUtils.div(v, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_reconnaissanceDesign: {//d20 or e20
                //=F18*F21*F20/10000
                String f18 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_total_saleableArea, target);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getConstructionInstallationEngineeringFee());
                bigDecimal = ArithmeticUtils.multiply(bigDecimal, target.getReconnaissanceDesign());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_infrastructureCost: {//d22 or e22
                //F18*F22/10000
                String f18 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_total_saleableArea, target);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getInfrastructureCost());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_infrastructureMatchingCost: {//d23 or e23
                //F18*F23/10000
                String f18 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_total_saleableArea, target);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getInfrastructureMatchingCost());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_devDuring: {//d24 or e24
                //F18*F24/10000
                String f18 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_total_saleableArea, target);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getDevDuring());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_otherEngineeringCost: {//d25 or e25
                //F18*F25/10000
                String f18 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_total_saleableArea, target);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(f18), target.getOtherEngineeringCost());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_constructionSubtotal: {//d26或者 e26
                //ROUND(SUM(D20:E25),2)
                String d20 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_reconnaissanceDesign, target);
                String d21 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_constructionInstallationEngineeringFee, target);
                String d22 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_infrastructureCost, target);
                String d23 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_infrastructureMatchingCost, target);
                String d24 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_devDuring, target);
                String d25 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_otherEngineeringCost, target);
                double[] doubles = new double[]{Double.valueOf(d20), Double.valueOf(d21), Double.valueOf(d22), Double.valueOf(d23), Double.valueOf(d24), Double.valueOf(d25)};
                double value = ArithmeticUtils.add(doubles);
                target.setConstructionCostSubtotal(ArithmeticUtils.round(ArithmeticUtils.createBigDecimal(value), 2, BigDecimal.ROUND_HALF_UP));
                return String.valueOf(value);
            }
            case Development_unforeseenExpenses: {//d27 or e27
                //SUM(D20:E25)*F27
                String d26 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_constructionSubtotal, target);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(d26), target.getUnforeseenExpenses());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_LandGetCost: {//d28
                //1+D29+D30
                BigDecimal bigDecimal = ArithmeticUtils.add(ArithmeticUtils.createBigDecimal(1), target.getDeedTaxRate());
                bigDecimal = ArithmeticUtils.add(bigDecimal, target.getTransactionTaxRate());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_managementExpenseCorrectRate: {//d32
                //=G32*D28
                String d28 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_LandGetCost, target);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(d28), target.getManagementExpense());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_managementExpense: {//f32
                //(SUM(D26+D27)+F31)*G32
                String d26 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_constructionSubtotal, target);
                String d27 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_unforeseenExpenses, target);
                BigDecimal[] doubles = new BigDecimal[]{ArithmeticUtils.createBigDecimal(d26), ArithmeticUtils.createBigDecimal(d27), target.getLandGetRelevant()};
                BigDecimal bigDecimal = ArithmeticUtils.add(doubles);
                bigDecimal = ArithmeticUtils.multiply(bigDecimal, target.getManagementExpense());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_salesFee: {//f33
                //=IF(E16=" "," ",E16*G33)
                BigDecimal e16 = target.getTotalSaleableAreaPrice();
                BigDecimal bigDecimal = ArithmeticUtils.multiply(e16, target.getSalesFee());
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_interestInvestmentCorrectRate: {//d34
                //=ROUND(((1+G34)^D3-1)*D28+((1+G34)^(D3/2)-1)*D32,4)
                BigDecimal g34 = target.getInterestInvestmentTax();
                String d3 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_projectConstructionPeriod, target);
                String d28 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_LandGetCost, target);
                String d32 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_managementExpenseCorrectRate, target);
                double c1 = (Math.pow(1 + g34.doubleValue(), Double.valueOf(d3)) - 1) * Double.valueOf(d28);
                double c2 = (Math.pow(1 + g34.doubleValue(), Double.valueOf(d3) / 2) - 1) * Double.valueOf(d32);
                double c = c1 + c2;
                return String.valueOf(c);
            }
            case Development_interestInvestment: {//f34
                //D21+D23+D24+D25+D27+F32+F33)  *  ( (1+G34)^(D3/2)-1)   +(SUM(D20+D22)+E31) * (  (1+G34)^(D3)-1 )    )
                BigDecimal g34 = target.getInterestInvestmentTax();
                String d20 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_reconnaissanceDesign, target);
                String d21 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_constructionInstallationEngineeringFee, target);
                String d22 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_infrastructureCost, target);
                String d23 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_infrastructureMatchingCost, target);
                String d24 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_devDuring, target);
                String d25 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_otherEngineeringCost, target);
                String d27 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_unforeseenExpenses, target);
                String f33 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_salesFee, target);
                String f32 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_managementExpense, target);
                String d3 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_projectConstructionPeriod, target);
                BigDecimal f31 = target.getLandGetRelevant();
                BigDecimal a = ArithmeticUtils.add(new BigDecimal[]{
                        ArithmeticUtils.createBigDecimal(d21),
                        ArithmeticUtils.createBigDecimal(d23),
                        ArithmeticUtils.createBigDecimal(d24),
                        ArithmeticUtils.createBigDecimal(d25),
                        ArithmeticUtils.createBigDecimal(d27),
                        ArithmeticUtils.createBigDecimal(f32),
                        ArithmeticUtils.createBigDecimal(f33)});
                double b = Math.pow(1 + g34.doubleValue(), Double.valueOf(d3) / 2) - 1;
                BigDecimal c1 = ArithmeticUtils.multiply(a, ArithmeticUtils.createBigDecimal(b));
                double c2 = (Double.valueOf(d20) + Double.valueOf(d22) + f31.doubleValue()) * (Math.pow(1 + g34.doubleValue(), Double.valueOf(d3)) - 1);
                BigDecimal c = ArithmeticUtils.add(c1.toString(), String.valueOf(c2));
                target.setInterestInvestment(ArithmeticUtils.round(c, 2, BigDecimal.ROUND_HALF_UP));
                return c.toString();
            }
            case Development_investmentProfitCorrectRate: {//d35
                //(D28+D32)*G35
                String d28 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_LandGetCost, target);
                String d32 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_managementExpenseCorrectRate, target);
                BigDecimal g35 = target.getInvestmentProfitTax();
                BigDecimal bigDecimal = ArithmeticUtils.add(ArithmeticUtils.createBigDecimal(d28), ArithmeticUtils.createBigDecimal(d32));
                bigDecimal = ArithmeticUtils.multiply(bigDecimal, g35);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_investmentProfit: {//f35
                //(D26+D27+F32+F33+E31)*G35
                String d26 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_constructionSubtotal, target);
                String d27 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_unforeseenExpenses, target);
                String f33 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_salesFee, target);
                String f32 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_managementExpense, target);
                BigDecimal f31 = target.getLandGetRelevant();
                BigDecimal a = ArithmeticUtils.add(new BigDecimal[]{
                        ArithmeticUtils.createBigDecimal(d26),
                        ArithmeticUtils.createBigDecimal(d27),
                        ArithmeticUtils.createBigDecimal(f32),
                        ArithmeticUtils.createBigDecimal(f33),
                        f31});
                BigDecimal g35 = target.getInvestmentProfitTax();
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
                String d36 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_projectDevelopmentIncomeCorrectRate, target);
                BigDecimal e16 = target.getTotalSaleableAreaPrice();
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(d36), e16);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_LandPriceCorrectRate: {//h40
                //=D29+D30+D32+D35+D34
                BigDecimal d29 = target.getDeedTaxRate();
                BigDecimal d30 = target.getTransactionTaxRate();
                String d32 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_managementExpenseCorrectRate, target);
                String d34 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_interestInvestmentCorrectRate, target);
                String d35 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_investmentProfitCorrectRate, target);
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
                String d26 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_constructionSubtotal, target);
                String d27 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_unforeseenExpenses, target);
                String f32 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_managementExpense, target);
                BigDecimal f31 = target.getLandGetRelevant();
                String f33 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_salesFee, target);
                String f36 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_projectDevelopmentIncomeValue, target);
                String f34 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_interestInvestment, target);
                String f35 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_investmentProfit, target);

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
                String f40 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_LandPriceCorrectValue, target);
                String h40 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_LandPriceCorrectRate, target);
                BigDecimal bigDecimal = ArithmeticUtils.add(String.valueOf(1), h40);
                bigDecimal = ArithmeticUtils.div(ArithmeticUtils.createBigDecimal(f40), bigDecimal);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case Development_assessPrice: {//d41
                //=E40/F18*10000
                String e40 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_LandPriceValue, target);
                String f18 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_total_saleableArea, target);
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
                double a = 1 - 1 / Math.pow(1 + e43.doubleValue(), g43.doubleValue());
                double b = 1 - 1 / Math.pow(1 + e43.doubleValue(), f43.doubleValue());
                BigDecimal bigDecimal = ArithmeticUtils.div(ArithmeticUtils.createBigDecimal(a), ArithmeticUtils.createBigDecimal(b));
                return ArithmeticUtils.round(bigDecimal.toString(), 4);
            }
            case Development_Price: {//d47
                //=D41*D43*D44*D45+D46
                String d41 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_assessPrice, target);
                String d43 = getFieldObjectValueHandle(BaseReportFieldEnum.Development_LandPriceCoefficient, target);
                BigDecimal d44 = target.getAmendmentStatusRights();
                BigDecimal d45 = target.getOtherAmendments();
                BigDecimal d46 = target.getDevelopmentDegreeRevision();
                double d = ArithmeticUtils.mul(new double[]{Double.valueOf(d41), Double.valueOf(d43), d44.doubleValue(), d45.doubleValue()});
                BigDecimal bigDecimal = ArithmeticUtils.add(ArithmeticUtils.createBigDecimal(d), d46);
                bigDecimal = ArithmeticUtils.round(bigDecimal, 2, BigDecimal.ROUND_HALF_UP);
                target.setPrice(bigDecimal);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            default:
                return "0";
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

    public void saveAndUpdateData(String formData,ProjectPlanDetails projectPlanDetails){
        MdDevelopment mdDevelopment = JSONObject.parseObject(formData, MdDevelopment.class);

        this.saveAndUpdateMdDevelopment(mdDevelopment);

        MdDevelopmentInfrastructureChildren infrastructureChildren = new MdDevelopmentInfrastructureChildren();
        infrastructureChildren.setPlanDetailsId(projectPlanDetails.getId());
        infrastructureChildren.setPid(0);
        infrastructureChildren.setCreator(commonService.thisUserAccount());
        List<MdDevelopmentInfrastructureChildren> childrenList = mdDevelopmentInfrastructureChildrenService.getMdDevelopmentInfrastructureChildrenListByExample(infrastructureChildren);
        if (CollectionUtils.isNotEmpty(childrenList)) {
            for (MdDevelopmentInfrastructureChildren po : childrenList) {
                po.setPid(mdDevelopment.getId());
                mdDevelopmentInfrastructureChildrenService.saveMdDevelopmentInfrastructureChildren(po);
            }
        }
    }

    public void saveAndUpdateMdDevelopment(MdDevelopment oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentDao.addMdDevelopment(oo);
        } else {
            mdDevelopmentDao.updateMdDevelopment(oo);
        }
    }

    public MdDevelopmentVo getMdDevelopmentVo(MdDevelopment mdDevelopment, boolean format) {
        if (mdDevelopment == null) {
            return null;
        }
        MdDevelopmentVo vo = new MdDevelopmentVo();
        BeanUtils.copyProperties(mdDevelopment, vo);
        if (StringUtils.isNotEmpty(vo.getContent())) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSON.parseObject(vo.getContent());
            } catch (Exception e) {
                logger.error("解析错误", e);
            }
            if (jsonObject != null) {
                if (jsonObject.get("f20") != null) {
                    vo.setF20(changeHundred((String) jsonObject.get("f20"), format));
                }
                if (jsonObject.get("f21") != null) {
                    vo.setF21((String) jsonObject.get("f21"));
                }
                if (jsonObject.get("f22") != null) {
                    vo.setF22((String) jsonObject.get("f22"));
                }
                if (jsonObject.get("f23") != null) {
                    vo.setF23((String) jsonObject.get("f23"));
                }
                if (jsonObject.get("f23Explain") != null) {
                    vo.setF23Explain((String) jsonObject.get("f23Explain"));
                }
                if (jsonObject.get("f24") != null) {
                    vo.setF24((String) jsonObject.get("f24"));
                }
                if (jsonObject.get("f24Value") != null) {
                    vo.setF24Value((String) jsonObject.get("f24Value"));
                }
                if (jsonObject.get("f25") != null) {
                    vo.setF25(changeHundred((String) jsonObject.get("f25"), format));
                }
                if (jsonObject.get("f27") != null) {
                    vo.setF27(changeHundred((String) jsonObject.get("f27"), format));
                }
                if (jsonObject.get("f29") != null) {
                    vo.setF29(changeHundred((String) jsonObject.get("f29"), format));
                }
                if (jsonObject.get("f29Explain") != null) {
                    vo.setF29Explain((String) jsonObject.get("f29Explain"));
                }

                if (jsonObject.get("f30") != null) {
                    vo.setF30(changeHundred((String) jsonObject.get("f30"), format));
                }
                if (jsonObject.get("f30Explain") != null) {
                    vo.setF30Explain((String) jsonObject.get("f30Explain"));
                }
                if (jsonObject.get("g32") != null) {
                    vo.setG32(changeHundred((String) jsonObject.get("g32"), format));
                }
                if (jsonObject.get("g32Explain") != null) {
                    vo.setG32Explain((String) jsonObject.get("g32Explain"));
                }
                if (jsonObject.get("f31") != null) {
                    vo.setF31((String) jsonObject.get("f31"));
                }
                if (jsonObject.get("f31Explain") != null) {
                    vo.setF31Explain((String) jsonObject.get("f31Explain"));
                }
                if (jsonObject.get("g33") != null) {
                    vo.setG33(changeHundred((String) jsonObject.get("g33"), format));
                }
                if (jsonObject.get("g33Explain") != null) {
                    vo.setG33Explain((String) jsonObject.get("g33Explain"));
                }
                if (jsonObject.get("g34") != null) {
                    vo.setG34(changeHundred((String) jsonObject.get("g34"), format));
                }
                if (jsonObject.get("g34Explain") != null) {
                    vo.setG34Explain((String) jsonObject.get("g34Explain"));
                }
                if (jsonObject.get("g35") != null) {
                    vo.setG35(changeHundred((String) jsonObject.get("g35"), format));
                }
                if (jsonObject.get("g35Explain") != null) {
                    vo.setG35Explain((String) jsonObject.get("g35Explain"));
                }
                if (jsonObject.get("f37") != null) {
                    vo.setF37(changeHundred((String) jsonObject.get("f37"), format));
                }
                if (jsonObject.get("f37Explain") != null) {
                    vo.setF37Explain((String) jsonObject.get("f37Explain"));
                }
                if (jsonObject.get("f38") != null) {
                    vo.setF38(changeHundred((String) jsonObject.get("f38"), format));
                }
                if (jsonObject.get("f38Explain") != null) {
                    vo.setF38Explain((String) jsonObject.get("f38Explain"));
                }
                if (jsonObject.get("f39") != null) {
                    vo.setF39(changeHundred((String) jsonObject.get("f39"), format));
                }
                if (jsonObject.get("f39Explain") != null) {
                    vo.setF39Explain((String) jsonObject.get("f39Explain"));
                }
                if (jsonObject.get("e43") != null) {
                    vo.setE43(changeHundred((String) jsonObject.get("e43"), format));
                }
                if (jsonObject.get("e43Explain") != null) {
                    vo.setE43Explain((String) jsonObject.get("e43Explain"));
                }

                if (jsonObject.get("f43") != null) {
                    vo.setF43((String) jsonObject.get("f43"));
                }
                if (jsonObject.get("g43") != null) {
                    vo.setG43((String) jsonObject.get("g43"));
                }
                if (jsonObject.get("d44") != null) {
                    vo.setD44((String) jsonObject.get("d44"));
                }
                if (jsonObject.get("d44Explain") != null) {
                    vo.setD44Explain(changeHundred((String) jsonObject.get("d44Explain"), format));
                }
                if (jsonObject.get("d45") != null) {
                    vo.setD45((String) jsonObject.get("d45"));
                }
                if (jsonObject.get("d45Explain") != null) {
                    vo.setD45Explain(changeHundred((String) jsonObject.get("d45Explain"), format));
                }
                if (jsonObject.get("d46") != null) {
                    vo.setD46((String) jsonObject.get("d46"));
                }
                if (jsonObject.get("d46Explain") != null) {
                    vo.setD46Explain(changeHundred((String) jsonObject.get("d46Explain"), format));
                }
//                if (jsonObject.get("unsaleableBuildingArea") != null) {
//                    vo.setUnsaleableBuildingArea((String) jsonObject.get("unsaleableBuildingArea"));
//                }
            }
        }

        if (StringUtils.isNotEmpty(mdDevelopment.getConstructionInstallationEngineeringFeeIds())) {
            List<Integer> integerList = FormatUtils.transformString2Integer(mdDevelopment.getConstructionInstallationEngineeringFeeIds());
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

        return vo;
    }

    private String changeHundred(String value, boolean format) {
        if (format) {
            if (NumberUtils.isNumber(value)) {
                BigDecimal bigDecimal = new BigDecimal(value).multiply(new BigDecimal(100));
                return String.format("%s%s", bigDecimal.setScale(2, BigDecimal.ROUND_UP).toString(), "%");
            }
        }
        return value;
    }

}
