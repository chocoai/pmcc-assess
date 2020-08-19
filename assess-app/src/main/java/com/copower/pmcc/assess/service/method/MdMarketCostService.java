package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.report.ReportFieldCostMethodEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdEconomicIndicatorsApplyDto;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.copower.pmcc.assess.dto.output.method.MdCostVo;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MdEconomicIndicatorsService mdEconomicIndicatorsService;
    @Autowired
    private MdCalculatingMethodEngineeringCostService mdCalculatingMethodEngineeringCostService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private MdDevelopmentInfrastructureChildrenService mdDevelopmentInfrastructureChildrenService;
    @Autowired
    private ResidueRatioService residueRatioService;

    @Transactional(rollbackFor = {Exception.class})
    public void copyConstructionById(Integer copyId, Integer masterId, StringBuilder stringBuilder)throws Exception{
        MdCost copy1 = getByMdCostId(copyId) ;
        MdCost target1 = getByMdCostId(masterId) ;

        if (copy1 == null) {
            stringBuilder.append("目标数据不存在");
            throw new Exception("目标数据不存在");
        }
        if (target1 == null) {
            stringBuilder.append("拷贝异常");
            throw new Exception("拷贝异常");
        }

        Integer copyPlanDetailsId = copy1.getPlanDetailsId();
        Integer targetPlanDetailsId = target1.getPlanDetailsId();
        ProjectPlanDetails copyPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(copyPlanDetailsId);
        ProjectPlanDetails targetPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(targetPlanDetailsId);

        MdCostVo copy = getMdCostVo(copy1) ;
        MdCostVo target = getMdCostVo(target1) ;

        //工程费 copy
        mdCalculatingMethodEngineeringCostService.copyMdCalculatingMethodEngineeringCost(targetPlanDetails, copyPlanDetails);

        MdEconomicIndicators economicIndicators = new MdEconomicIndicators();
        //经济指标 copy
        mdEconomicIndicatorsService.copyDataEconomicIndicators(copy.getMdCostConstruction().getEconomicId(), economicIndicators);
        ToolResidueRatio toolResidueRatio = new ToolResidueRatio() ;
        residueRatioService.copyDataToolResidueRatio(copy.getMdCostConstruction().getResidueRatioId(),toolResidueRatio);

        Integer copyBranchId = copy.getMdCostConstruction().getId();
        copy.getMdCostConstruction().setId(null);
        copy.getMdCostConstruction().setPid(null);
        copy.getMdCostConstruction().setCenterId(null);
        copy.getMdCostConstruction().setEconomicId(null);
        BeanCopyHelp.copyPropertiesIgnoreNull(copy.getMdCostConstruction(), target.getMdCostConstruction());
        target.getMdCostConstruction().setEconomicId(economicIndicators.getId());
        target.getMdCostConstruction().setResidueRatioId(toolResidueRatio.getId());
        saveMdCostConstruction(target.getMdCostConstruction()) ;

        //基础设施配套费 copy
        mdDevelopmentInfrastructureChildrenService.copyData(targetPlanDetails, copyPlanDetails, copyBranchId, target.getMdCostConstruction().getId());
        copy1.setId(null);
        copy1.setPlanDetailsId(null);
        copy1.setCreator(null);
        BeanCopyHelp.copyPropertiesIgnoreNull(copy1, target1);
        saveAndUpdateMdCost(target1) ;
    }


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
     *
     * @param mdCost
     * @param projectPlanDetails
     * @param processInsId
     * @throws BusinessException
     */
    public void initData(MdCost mdCost, ProjectPlanDetails projectPlanDetails, String processInsId) throws BusinessException {
        if (mdCost == null) {
            return;
        }
        mdCost.setPlanDetailsId(projectPlanDetails.getId());
        boolean firstInit = (mdCost.getId() == null || mdCost.getId() == 0);
        saveAndUpdateMdCost(mdCost);
        MdCostVo mdCostVo = getMdCostVo(mdCost);
        mdCostVo.getMdCostConstruction().setPid(mdCostVo.getId());

        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = Lists.newArrayList();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        Map<Integer, Integer> mapEconomicId = new HashMap<>(2);
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRecord.getDataTableName())) {
            query.setHouseId(declareRecord.getDataTableId());
            query.setType(DeclareRealtyHouseCert.class.getSimpleName());
            List<DeclareBuildEngineeringAndEquipmentCenter> centerList2 = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centerList2)) {
                centerList.addAll(centerList2) ;
            }
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
            query.setRealEstateId(declareRecord.getDataTableId());
            query.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
            List<DeclareBuildEngineeringAndEquipmentCenter> centerList2 = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centerList2)) {
                centerList.addAll(centerList2) ;
            }
        }
        if (CollectionUtils.isNotEmpty(centerList)) {
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                mdCostVo.getMdCostConstruction().setCenterId(center.getId());
                if (center.getIndicatorId() == null) {
                    continue;
                }
                mapEconomicId.put(center.getIndicatorId(), center.getId());
            }
        }
        if (!mapEconomicId.isEmpty()) {
            boolean tempFlag = false;
            if (mdCostVo.getMdCostConstruction().getEconomicId() != null) {
                MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = mdEconomicIndicatorsService.getEconomicIndicatorsInfo(mdCostVo.getMdCostConstruction().getEconomicId());
                tempFlag = mdEconomicIndicatorsApplyDto.getEconomicIndicators() != null && mdEconomicIndicatorsApplyDto.getEconomicIndicators().getId() != null;
            }
            if (!tempFlag){
                mapEconomicId.forEach((integer, integer2) -> {
                    mdCostVo.getMdCostConstruction().setEconomicId(integer);
                    mdCostVo.getMdCostConstruction().setCenterId(integer2);
                });
            }
        }
        saveMdCostConstruction(mdCostVo.getMdCostConstruction());

        if (firstInit) {
            initDataBind(mdCostVo,projectPlanDetails,processInsId) ;
        }

    }
    private void initDataBind(MdCostVo mdCostVo,ProjectPlanDetails projectPlanDetails,String processInsId)throws BusinessException{
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
        schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST).getId());
        schemeInfo.setProcessInsId(StringUtils.isNotEmpty(processInsId) ? processInsId : "0");
        schemeInfo.setMethodDataId(mdCostVo.getId());
        schemeInfoService.saveSchemeInfo(schemeInfo);

        MdEconomicIndicatorsApplyDto mdEconomicIndicators = mdEconomicIndicatorsService.getEconomicIndicatorsInfo(mdCostVo.getMdCostConstruction().getEconomicId());
        if (mdEconomicIndicators == null) {
            return;
        }
        if (mdEconomicIndicators.getEconomicIndicators() == null) {
            return;
        }
        if (mdEconomicIndicators.getEconomicIndicators().getAssessUseLandArea() != null) {
            mdCostVo.getMdCostConstruction().setDevelopLandAreaTax(mdEconomicIndicators.getEconomicIndicators().getAssessUseLandArea());
        }
        if (mdEconomicIndicators.getEconomicIndicators().getAssessTotalBuildArea() != null) {
            mdCostVo.getMdCostConstruction().setDevelopBuildAreaTax(mdEconomicIndicators.getEconomicIndicators().getAssessTotalBuildArea());
        }
        saveMdCostConstruction(mdCostVo.getMdCostConstruction());
    }

    public void saveAndUpdateMdCost(MdCost mdCost) {
        if (mdCost.getId() == null || mdCost.getId() == 0) {
            mdCost.setCreator(commonService.thisUserAccount());
            mdCostDao.addMdCost(mdCost);
        } else {
            mdCostDao.updateMdCost(mdCost);
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


    public void saveAndUpdateMdCostData(String formData , ProjectPlanDetails projectPlanDetails) {
        JSONObject jsonObject = JSON.parseObject(formData);
        MdCost mdCost = new MdCost();
        MdCostConstruction mdCostConstruction = JSONObject.parseObject(formData, MdCostConstruction.class);
        mdCost.setType((String) jsonObject.get("type"));
        mdCost.setId(mdCostConstruction.getPid());
        mdCost.setPrice(mdCostConstruction.getConstructionAssessmentPriceCorrecting());

        saveAndUpdateMdCost(mdCost);

        mdCostConstruction.setJsonContent(formData);
        saveMdCostConstructionAndUpdate(mdCostConstruction);

        mdCalculatingMethodEngineeringCostService.clearOver(projectPlanDetails,mdCost.getType());


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
        MdCost mdCost = mdCostDao.getMdCostById(id);
        return mdCost;
    }

    public List<MdCost> getMdCostList(MdCost mdCost) {
        return mdCostDao.getMdCostList(mdCost);
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


    public MdCostConstructionVo getMdCostConstructionVo(MdCostConstruction oo) {
        MdCostConstructionVo vo = new MdCostConstructionVo();
        if (oo == null) {
            return vo;
        }
        BeanUtils.copyProperties(oo, vo);

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

    public String calculationNumeric(MdCostConstruction target) {
        mdCostConstructionDao.updateEstateNetwork(target);
        String value = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionAssessmentPriceCorrecting, target);
        if (target.getId() != null && target.getId() != 0) {
            mdCostConstructionDao.updateEstateNetwork(target);
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
    public synchronized String getFieldObjectValue(ReportFieldCostMethodEnum key, MdCostConstruction target) {
        if (target.getLandPurchasePrice() == null){
            target.setLandPurchasePrice(ArithmeticUtils.createBigDecimal(0));
        }
        if (target.getLandGetRelevant() == null){
            target.setLandGetRelevant(ArithmeticUtils.createBigDecimal(0));
        }
        if (target.getAdditionalCostLandAcquisition() == null){
            target.setAdditionalCostLandAcquisition(ArithmeticUtils.createBigDecimal(0));
        }
        switch (key) {
            case MarketCost_developYearNumberTax: {
                return ArithmeticUtils.getBigDecimalString(target.getDevelopYearNumberTax());
            }
            case MarketCost_UnitAreaLandPrice: {
                String start = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landPurchasePrice, target);
                if (!ArithmeticUtils.checkNotNull(start)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getDevelopLandAreaTax())) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.divide(ArithmeticUtils.createBigDecimal(start), target.getDevelopLandAreaTax(), 2);
                return ArithmeticUtils.getBigDecimalString(bigDecimal);
            }
            case MarketCost_landPurchasePrice: {
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getLandPurchasePrice(), target.getDevelopLandAreaTax()))) {
                    return "";
                }
                BigDecimal start = ArithmeticUtils.divide(ArithmeticUtils.multiply(target.getLandPurchasePrice(), target.getDevelopLandAreaTax()), ArithmeticUtils.createBigDecimal(10000), 2);
                return ArithmeticUtils.getBigDecimalString(start);
            }
            case MarketCost_landGetRelevant: {
                String start = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landPurchasePrice, target);
                if (!ArithmeticUtils.checkNotNull(start)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getLandGetRelevant())) {
                    return "";
                }
                return ArithmeticUtils.mul(start, target.getLandGetRelevant().toString(), 2);
            }
            case MarketCost_landGetRelevantRate: {
                return ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant());
            }
            case MarketCost_additionalCostLandAcquisition: {
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getAdditionalCostLandAcquisition(), target.getDevelopLandAreaTax()))) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(target.getAdditionalCostLandAcquisition(), target.getDevelopLandAreaTax());
                BigDecimal start = ArithmeticUtils.divide(bigDecimal, ArithmeticUtils.createBigDecimal(10000), 10);
                return ArithmeticUtils.getBigDecimalString(start);
            }
            case MarketCost_reconnaissanceDesignRate: {
                return ArithmeticUtils.getBigDecimalString(target.getReconnaissanceDesign());
            }
            case MarketCost_reconnaissanceDesign: {
                String start = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionInstallationEngineeringFee, target);
                if (!ArithmeticUtils.checkNotNull(start)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getReconnaissanceDesign())) {
                    return "";
                }
                return ArithmeticUtils.mul(start, target.getReconnaissanceDesign().toString(), 2);
            }
            case MarketCost_constructionInstallationEngineeringFee: {
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getDevelopBuildAreaTax(), target.getConstructionInstallationEngineeringFee()))) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.multiply(target.getConstructionInstallationEngineeringFee(), target.getDevelopBuildAreaTax(), 2);
                BigDecimal start = ArithmeticUtils.divide(bigDecimal, ArithmeticUtils.createBigDecimal(10000), 2);
                return ArithmeticUtils.getBigDecimalString(start);
            }
            case MarketCost_infrastructureCost: {
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getDevelopBuildAreaTax(), target.getInfrastructureCost()))) {
                    return "";
                }
                String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getInfrastructureCost().toString()).toString();
                return ArithmeticUtils.div(two, "10000", 2);
            }
            case MarketCost_infrastructureMatchingCost: {
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getDevelopBuildAreaTax(), target.getInfrastructureMatchingCost()))) {
                    return "";
                }
                String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getInfrastructureMatchingCost().toString()).toString();
                return ArithmeticUtils.div(two, "10000", 2);
            }
            case MarketCost_devDuring: {
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getDevelopBuildAreaTax(), target.getDevDuring()))) {
                    return "";
                }
                String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getDevDuring().toString()).toString();
                return ArithmeticUtils.div(two, "10000", 2);
            }
            case MarketCost_otherEngineeringCost: {
                if (!ArithmeticUtils.checkNotNullList(Arrays.asList(target.getDevelopBuildAreaTax(), target.getOtherEngineeringCost()))) {
                    return "";
                }
                String two = ArithmeticUtils.mul(target.getDevelopBuildAreaTax().toString(), target.getOtherEngineeringCost().toString()).toString();
                return ArithmeticUtils.div(two, "10000", 2);
            }
            case MarketCost_constructionSubtotal: {
                String v1 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_reconnaissanceDesign, target);
                String v2 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionInstallationEngineeringFee, target);
                String v3 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_infrastructureCost, target);
                String v4 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_infrastructureMatchingCost, target);
                String v5 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_devDuring, target);
                String v6 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_otherEngineeringCost, target);
                String[] strings = new String[]{v1, v2, v3, v4, v5, v6};
                if (!ArithmeticUtils.checkNotNull(strings)) {
                    return "";
                }
                String value = ArithmeticUtils.add(strings);
                target.setConstructionSubtotal(ArithmeticUtils.round(value, 2));
                return value;
            }
            case MarketCost_unforeseenExpenses: {
                String v = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionSubtotal, target);
                if (!ArithmeticUtils.checkNotNull(v)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getUnforeseenExpenses())) {
                    return "";
                }
                String value = ArithmeticUtils.mul(v, target.getUnforeseenExpenses().toString(), 2);
                return value;
            }
            case MarketCost_unforeseenExpenses2: {
                return getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_unforeseenExpenses, target);
            }
            case MarketCost_unforeseenExpensesRate: {
                return ArithmeticUtils.getBigDecimalString(target.getUnforeseenExpenses());
            }
            case MarketCost_managementExpense: {
                String e17 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionSubtotal, target);
                String e18 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_unforeseenExpenses, target);
                String e9 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landGetCostTotal, target);
                String[] strings = new String[]{e17, e18, e9};
                if (!ArithmeticUtils.checkNotNull(strings)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getManagementExpense())) {
                    return "";
                }
                String v1 = ArithmeticUtils.add(strings);
                String d19 = target.getManagementExpense().toString();
                return ArithmeticUtils.mul(v1, d19, 2);
            }
            case MarketCost_managementExpenseRate: {
                return ArithmeticUtils.getBigDecimalString(target.getManagementExpense());
            }
            case MarketCost_salesFee: {
                String v = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionAssessmentValue, target);
                String v1 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_salesFeeRate, target);
                if (!ArithmeticUtils.checkNotNull(new String[]{v, v1})) {
                    return "";
                }
                return ArithmeticUtils.mul(v, v1, 2);
            }
            case MarketCost_salesTaxAndAdditionalTotal: {
                String v = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionAssessmentValue, target);
                if (!ArithmeticUtils.checkNotNull(v)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(target.getSalesTaxAndAdditional())) {
                    return "";
                }
                String v1 = target.getSalesTaxAndAdditional().toString();
                return ArithmeticUtils.mul(v, v1, 2);
            }
            case MarketCost_salesFeeRate: {
                return ArithmeticUtils.getBigDecimalString(target.getSalesFee());
            }
            case MarketCost_interestInvestment: {
                try {
                    double e9 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landGetCostTotal, target));
                    double e11 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_reconnaissanceDesign, target));
                    double e12 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionInstallationEngineeringFee, target));
                    double e13 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_infrastructureCost, target));
                    double e14 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_infrastructureMatchingCost, target));
                    double e15 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_devDuring, target));
                    double e16 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_otherEngineeringCost, target));
                    double e18 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_unforeseenExpenses, target));
                    double d21 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_interestInvestmentRate, target));
                    double h3 = target.getDevelopYearNumberTax().doubleValue();

                    double e19 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_managementExpense, target));
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
                    double d21 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_interestInvestmentRate, target));
                    double h3 = target.getDevelopYearNumberTax().doubleValue();
                    double a = Math.pow(1 + d21, h3 / 2);
                    double c = ArithmeticUtils.mul(d20, a - 1, 4);
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
                    String v = ArithmeticUtils.mul(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_investmentProfitRate, target), target.getSalesFee().toString(), 5).toString();
                    return v;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue2Rate: {//G24
                try {
                    double d20 = target.getSalesFee().doubleValue();
                    double d22 = target.getSalesTaxAndAdditional().doubleValue();
                    double g21 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_interestInvestmentCorrectRate, target));
                    double g23 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_investmentProfitCorrectRate, target));
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
                    double e9 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landGetCostTotal, target));
                    double e17 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_unforeseenExpenses, target));
                    double e19 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_managementExpense, target));
                    String value = String.valueOf(ArithmeticUtils.mul(d23, ArithmeticUtils.add(new double[]{e9, e17, e18, e19}), 2));
                    target.setInvestmentProfit(value);
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue2: {//E24
                try {
                    double e21 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_interestInvestment, target));
                    double e23 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_investmentProfit, target));
                    double e9 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landGetCostTotal, target));
                    double e17 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionSubtotal, target));
                    double e18 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_unforeseenExpenses, target));
                    double e19 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_managementExpense, target));
                    double value = ArithmeticUtils.add(new double[]{e9, e17, e18, e19, e21, e23});
                    target.setConstructionAssessmentValue2(ArithmeticUtils.createBigDecimal(value));
                    return String.valueOf(value);
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentValue: {//e25
                try {
                    double e24 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionAssessmentValue2, target));
                    double g24 = Double.valueOf(getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionAssessmentValue2Rate, target));
                    double value = 0;
                    BigDecimal bigDecimal = new BigDecimal(e24).divide(new BigDecimal(1 - g24), 2, RoundingMode.HALF_UP);
//                    value = ArithmeticUtils.div(e24, 1 - g24, 0);
                    target.setConstructionAssessmentValue(String.valueOf(bigDecimal));
                    return String.valueOf(bigDecimal);
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
                    String v1 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landPurchasePrice, target);
                    String v2 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landGetRelevant, target);
                    String v3 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_additionalCostLandAcquisition, target);
                    double[] doubles = new double[]{Double.valueOf(v1), Double.valueOf(v2), Double.valueOf(v3)};
                    String value = String.valueOf(ArithmeticUtils.add(doubles));
                    target.setLandGetCostTotal(ArithmeticUtils.round(value, 2));
                    return value;
                } catch (Exception e) {
                    return "";
                }
            }
            case MarketCost_constructionAssessmentPriceCorrecting: {
                try {
                    String e25 = getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionAssessmentValue, target);
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
