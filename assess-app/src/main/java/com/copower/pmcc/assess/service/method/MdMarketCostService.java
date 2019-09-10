package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.copower.pmcc.assess.dto.output.method.MdCostVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/8/9 10:43
 * @Description:成本法
 */
@Service
public class MdMarketCostService {
    @Autowired
    private MdCostConstructionDao mdCostConstructionDao;
    @Autowired
    private MdCostDao mdCostDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;


    public MdCost initExplore(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        MdCost mdCost = new MdCost();
        mdCost.setName(schemeJudgeObject.getName());
        mdCost.setCreator(commonService.thisUserAccount());
        saveAndUpdateMdCost(mdCost);
        return mdCost;
    }

    /**
     * 初始化数据
     * @param mdCost
     * @param projectPlanDetails
     * @param processInsId
     * @throws BusinessException
     */
    public void initData(MdCost mdCost, ProjectPlanDetails projectPlanDetails, String processInsId) throws BusinessException {
        if (mdCost == null) {
            return;
        }
        boolean firstInit = (mdCost.getId() == null || mdCost.getId() == 0);
        saveAndUpdateMdCost(mdCost);
        MdCostConstruction mdCostConstruction = new MdCostConstruction();
        mdCostConstruction.setPid(mdCost.getId());
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
            mdCostConstruction.setEconomicId(integerList.stream().findFirst().get());
        }
        MdCostVo mdCostVo = getMdCostVo(mdCost);
        mdCostConstruction.setId(mdCostVo.getMdCostConstruction().getId());
        saveMdCostConstruction(mdCostConstruction);

        if (firstInit) {
            SchemeInfo schemeInfo = new SchemeInfo();
            schemeInfo.setProjectId(projectPlanDetails.getProjectId());
            schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
            schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST).getId());
            schemeInfo.setProcessInsId(StringUtils.isNotEmpty(processInsId) ? processInsId : "0");
            schemeInfo.setMethodDataId(mdCost.getId());
            schemeInfoService.saveSchemeInfo(schemeInfo);
        }

    }

    public void saveAndUpdateMdCost(MdCost mdCost) {
        if (mdCost.getId() == null || mdCost.getId() == 0) {
            mdCost.setCreator(commonService.thisUserAccount());
            mdCostDao.addEstateNetwork(mdCost);
        } else {
            mdCostDao.updateEstateNetwork(mdCost);
        }
    }

    public void saveMdCostConstruction(MdCostConstruction mdCostConstruction) {
        if (mdCostConstruction.getId() == null || mdCostConstruction.getId() == 0) {
            mdCostConstruction.setCreator(commonService.thisUserAccount());
            mdCostConstructionDao.addEstateNetwork(mdCostConstruction);
        } else {
            mdCostConstructionDao.updateEstateNetwork(mdCostConstruction);
        }
    }


    public void saveAndUpdateMdCostData(String formData) {
        JSONObject jsonObject = JSON.parseObject(formData);
        MdCost mdCost = new MdCost();
        MdCostConstruction mdCostConstruction = JSONObject.parseObject(formData, MdCostConstruction.class);
        mdCost.setType((String) jsonObject.get("type"));
        mdCost.setId(mdCostConstruction.getPid());
        mdCost.setPrice(mdCostConstruction.getConstructionAssessmentPriceCorrecting());

        saveAndUpdateMdCost(mdCost);

        mdCostConstruction.setJsonContent(formData);
        saveMdCostConstructionAndUpdate(mdCostConstruction);


    }

    public void saveMdCostConstructionAndUpdate(MdCostConstruction mdCostConstruction) {
        if (mdCostConstruction.getId() == null || mdCostConstruction.getId() == 0) {
            mdCostConstruction.setCreator(commonService.thisUserAccount());
            mdCostConstructionDao.addEstateNetwork(mdCostConstruction);
        } else {
            mdCostConstructionDao.updateEstateNetwork(mdCostConstruction);
        }
    }

    public MdCost getByMdCostId(int id) {
        MdCost mdCost = mdCostDao.getEstateNetworkById(id);
        return mdCost;
    }

    public List<MdCost> getMdCostList(MdCost mdCost) {
        return mdCostDao.getEstateNetworkList(mdCost);
    }


    public MdCostConstruction getMdCostConstruction(Integer id) {
        return mdCostConstructionDao.getEstateNetworkById(id);
    }

    public List<MdCostConstruction> getMdCostConstructionList(MdCostConstruction mdCostConstruction) {
        return mdCostConstructionDao.getEstateNetworkList(mdCostConstruction);
    }


    public MdCostVo getMdCostVo(MdCost mdCost) {
        MdCostVo mdCostVo = new MdCostVo();
        if (mdCost == null) {
            return mdCostVo;
        }
        BeanUtils.copyProperties(mdCost, mdCostVo);
        if (mdCost.getId() != null && mdCost.getId() != 0) {
            MdCostConstruction query = new MdCostConstruction();
            query.setPid(mdCost.getId());
            List<MdCostConstruction> list = this.getMdCostConstructionList(query);
            if (CollectionUtils.isNotEmpty(list)) {
                mdCostVo.setMdCostConstruction(getMdCostConstructionVo(list.stream().findFirst().get()));
            }
        }
        return mdCostVo;
    }


    public MdCostConstructionVo getMdCostConstructionVo(MdCostConstruction mdCostConstruction) {
        MdCostConstructionVo vo = new MdCostConstructionVo();
        if (mdCostConstruction == null) {
            return vo;
        }
        BeanUtils.copyProperties(mdCostConstruction, vo);
        return vo;
    }

    public String calculationNumeric(MdCostConstruction target) {
        String value = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentPriceCorrecting, target);
        if (target.getId() != null && target.getId() != 0) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mdCostConstructionDao.updateEstateNetwork(target);
                }
            });
        }
        return value;
    }

    /**
     * 运算类的数据获取方法
     *
     * @param key
     * @param target
     * @return
     */
    public String getFieldObjectValue(BaseReportFieldEnum key, MdCostConstruction target) {
        switch (key) {
            case MarketCost_developYearNumberTax: {
                return ArithmeticUtils.getBigDecimalString(target.getDevelopYearNumberTax());
            }
            case MarketCost_UnitAreaLandPrice: {
                try {
                    String start = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landPurchasePrice, target);
                    BigDecimal bigDecimal = ArithmeticUtils.divide(ArithmeticUtils.createBigDecimal(start), target.getDevelopLandAreaTax(), 2);
                    return ArithmeticUtils.getBigDecimalString(bigDecimal);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_landPurchasePrice: {
                try {
                    BigDecimal start = ArithmeticUtils.divide(ArithmeticUtils.multiply(target.getLandPurchasePrice(), target.getDevelopLandAreaTax()), ArithmeticUtils.createBigDecimal(10000), 2);
                    return ArithmeticUtils.getBigDecimalString(start);
                } catch (Exception e) {
                    return "0";
                }
            }
            case MarketCost_landGetRelevant: {
                try {
                    String start = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landPurchasePrice, target);
                    return ArithmeticUtils.mul(start, target.getLandGetRelevant().toString(), 2);
                } catch (Exception e) {
                    return "0";
                }
            }
            case MarketCost_landGetRelevantRate: {
                return ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant());
            }
            case MarketCost_additionalCostLandAcquisition: {
                try {
                    BigDecimal bigDecimal = ArithmeticUtils.div(target.getAdditionalCostLandAcquisition(), target.getDevelopLandAreaTax());
                    BigDecimal start = ArithmeticUtils.multiply(bigDecimal, ArithmeticUtils.createBigDecimal(10000), 2);
                    return ArithmeticUtils.getBigDecimalString(start);
                } catch (Exception e) {
                    return "0";
                }
            }
            case MarketCost_reconnaissanceDesignRate: {
                return ArithmeticUtils.getBigDecimalString(target.getReconnaissanceDesign());
            }
            case MarketCost_reconnaissanceDesign: {
                try {
                    String start = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionInstallationEngineeringFee, target);
                    String string = ArithmeticUtils.mul(start, target.getReconnaissanceDesign().toString(), 2);
                    return string;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionInstallationEngineeringFee: {
                try {
                    BigDecimal bigDecimal = ArithmeticUtils.multiply(target.getConstructionInstallationEngineeringFee(), target.getDevelopBuildAreaTax(), 2);
                    BigDecimal start = ArithmeticUtils.divide(bigDecimal, ArithmeticUtils.createBigDecimal(10000), 2);
                    return ArithmeticUtils.getBigDecimalString(start);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_infrastructureCost: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getInfrastructureCost().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_infrastructureMatchingCost: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getInfrastructureMatchingCost().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_devDuring: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getDevDuring().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_otherEngineeringCost: {
                try {
                    String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getOtherEngineeringCost().toString()).toString();
                    return ArithmeticUtils.div(two, "10000", 2);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionSubtotal: {
                try {
                    String v1 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_reconnaissanceDesign, target);
                    String v2 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionInstallationEngineeringFee, target);
                    String v3 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureCost, target);
                    String v4 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureMatchingCost, target);
                    String v5 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_devDuring, target);
                    String v6 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_otherEngineeringCost, target);
                    double[] doubles = new double[]{Double.valueOf(v1), Double.valueOf(v2), Double.valueOf(v3), Double.valueOf(v4), Double.valueOf(v5), Double.valueOf(v6)};
                    double value = ArithmeticUtils.add(doubles);
                    target.setConstructionSubtotal(String.valueOf(value));
                    return String.valueOf(value);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_unforeseenExpenses: {
                String v = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target);
                String value = ArithmeticUtils.mul(v, target.getUnforeseenExpenses().toString(), 2);
                return value;
            }
            case MarketCost_unforeseenExpenses2: {
                return getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target);
            }
            case MarketCost_unforeseenExpensesRate: {
                return ArithmeticUtils.getBigDecimalString(target.getUnforeseenExpenses());
            }
            case MarketCost_managementExpense: {
                try {
                    double e17 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double v1 = ArithmeticUtils.add(new double[]{e17, e18, e9});
                    double d19 = target.getManagementExpense().doubleValue();
                    double v = ArithmeticUtils.mul(v1, d19, 2);
                    return String.valueOf(v);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_managementExpenseRate: {
                return ArithmeticUtils.getBigDecimalString(target.getManagementExpense());
            }
            case MarketCost_salesFee: {
                return String.join("", getFieldObjectValue(BaseReportFieldEnum.MarketCost_salesFeeRate, target), "v");
            }
            case MarketCost_salesFeeRate: {
                return ArithmeticUtils.getBigDecimalString(target.getSalesFee());
            }
            case MarketCost_interestInvestment: {
                try {
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double e11 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_reconnaissanceDesign, target));
                    double e12 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionInstallationEngineeringFee, target));
                    double e13 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureCost, target));
                    double e14 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_infrastructureMatchingCost, target));
                    double e15 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_devDuring, target));
                    double e16 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_otherEngineeringCost, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double d21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestmentRate, target));
                    double h3 = target.getDevelopYearNumberTax().doubleValue();

                    double e19 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_managementExpense, target));
                    double a = Math.pow(1 + d21, h3);
                    double c1 = ArithmeticUtils.mul(e9 + e11 + e13, a - 1);
                    double b = Math.pow(1 + d21, h3 / 2);
                    double c2 = ArithmeticUtils.mul(ArithmeticUtils.add(new double[]{e12, e14, e15, e16, e18, e19}), b - 1);
                    String value = ArithmeticUtils.add(String.valueOf(c1), String.valueOf(c2), 2);
                    target.setInterestInvestment(value);
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_interestInvestmentCorrectRate: {//G21
                try {
                    double d20 = target.getSalesFee().doubleValue();
                    double d21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestmentRate, target));
                    double h3 = target.getDevelopYearNumberTax().doubleValue();
                    double a = Math.pow(1 + d21, h3 / 2);
                    double c = ArithmeticUtils.mul(d20, a - 1, 5);
                    return String.valueOf(c);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_interestInvestmentRate: {
                return ArithmeticUtils.getBigDecimalString(target.getInterestInvestmentTax());
            }
            case MarketCost_salesTaxAndAdditional: {
                return "无";
            }
            case MarketCost_salesTaxAndAdditionalRate: {
                return ArithmeticUtils.getBigDecimalString(target.getSalesTaxAndAdditional());
            }
            case MarketCost_investmentProfitCorrectRate: {//G23
                try {
                    String v = ArithmeticUtils.mul(getFieldObjectValue(BaseReportFieldEnum.MarketCost_investmentProfitRate, target), target.getSalesFee().toString(), 5).toString();
                    return v;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue2Rate: {//G24
                try {
                    double d20 = target.getSalesFee().doubleValue();
                    double d22 = target.getSalesTaxAndAdditional().doubleValue();
                    double g21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestmentCorrectRate, target));
                    double g23 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_investmentProfitCorrectRate, target));
                    double[] doubles = new double[]{Double.valueOf(d20), Double.valueOf(d22), Double.valueOf(g21), Double.valueOf(g23)};
                    return String.valueOf(ArithmeticUtils.add(doubles));
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_investmentProfitRate: {
                return ArithmeticUtils.getBigDecimalString(target.getInvestmentProfitTax());
            }
            case MarketCost_investmentProfit: {//E23
                try {
                    double d23 = target.getInvestmentProfitTax().doubleValue();
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double e17 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double e19 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_managementExpense, target));
                    String value = String.valueOf(ArithmeticUtils.mul(d23, ArithmeticUtils.add(new double[]{e9, e17, e18, e19}), 2));
                    target.setInvestmentProfit(value);
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue2: {//E24
                try {
                    double e21 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_interestInvestment, target));
                    double e23 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_investmentProfit, target));
                    double e9 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetCostTotal, target));
                    double e17 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_unforeseenExpenses, target));
                    double e19 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_managementExpense, target));
                    double value = ArithmeticUtils.add(new double[]{e9, e17, e18, e19, e21, e23});
                    target.setConstructionAssessmentValue2(ArithmeticUtils.createBigDecimal(value));
                    return String.valueOf(value);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue: {//e25
                try {
                    double e24 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentValue2, target));
                    double g24 = Double.valueOf(getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentValue2Rate, target));
                    double value = ArithmeticUtils.div(e24, 1 - g24, 2);
                    target.setConstructionAssessmentValue(String.valueOf(value));
                    return String.valueOf(value);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_developBuildArea: {
                return ArithmeticUtils.getBigDecimalString(target.getDevelopBuildAreaTax());
            }
            case MarketCost_EstateLandPrice: {
                return ArithmeticUtils.div(target.getLandGetCostTotal(), target.getDevelopBuildAreaTax().toString(), 2);
            }
            case MarketCost_AssessUseLandArea: {
                return ArithmeticUtils.getBigDecimalString(target.getDevelopLandAreaTax());
            }
            case MarketCost_landGetCostTotal: {
                try {
                    String v1 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landPurchasePrice, target);
                    String v2 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_landGetRelevant, target);
                    String v3 = target.getAdditionalCostLandAcquisition().toString();
                    double[] doubles = new double[]{Double.valueOf(v1), Double.valueOf(v2), Double.valueOf(v3)};
                    String value = String.valueOf(ArithmeticUtils.add(doubles));
                    target.setLandGetCostTotal(value);
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentPriceCorrecting: {
                try {
                    String e25 = getFieldObjectValue(BaseReportFieldEnum.MarketCost_constructionAssessmentValue, target);
                    String f3 = target.getDevelopBuildAreaTax().toString();
                    BigDecimal v = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(e25), ArithmeticUtils.createBigDecimal(10000));
                    BigDecimal decimal = ArithmeticUtils.divide(v, ArithmeticUtils.createBigDecimal(f3), 2);
                    BigDecimal residueRatio = target.getResidueRatio();
                    if (residueRatio != null) {
                        decimal = ArithmeticUtils.multiply(decimal, target.getResidueRatio(), 2);
                    }
                    target.setConstructionAssessmentPriceCorrecting(decimal);
                    return decimal.toString();
                } catch (Exception e) {
                    return "";
                }
            }
            default: {
                return "0";
            }
        }
    }


}
