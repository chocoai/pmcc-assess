package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.ProjectTaskLiquidationAnalysisVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Service
public class SchemeLiquidationAnalysisService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataTaxRateAllocationService dataTaxRateAllocationService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeLiquidationAnalysisDao schemeLiquidationAnalysisDao;
    @Autowired
    private SchemeLiquidationAnalysisItemDao schemeLiquidationAnalysisItemDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;

    public List<ProjectTaskLiquidationAnalysisVo> editPageTaxAllocation(Integer mainId) {
        SchemeLiquidationAnalysisItem item = new SchemeLiquidationAnalysisItem();
        item.setMainId(mainId);
        List<SchemeLiquidationAnalysisItem> list = schemeLiquidationAnalysisItemDao.getObjectList(item);
        return LangUtils.transform(list, o -> getProjectTaskLiquidationAnalysisVo(o));

    }

    public List<ProjectTaskLiquidationAnalysisVo> getTaxAllocation(Integer projectPlanDetailsId, Integer judgeObjectId) {
        List<ProjectTaskLiquidationAnalysisVo> list = new ArrayList<>();
        SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetailsId);
        BigDecimal price = new BigDecimal("0");
        if (schemeSurePrice != null) {
            if (schemeSurePrice.getPrice() != null) {
                price = schemeSurePrice.getPrice();
            }
        }
        //增值税
        DataTaxRateAllocation allocationSales = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX);
        ProjectTaskLiquidationAnalysisVo allocationSalesVo = getProjectTaskLiquidationAnalysisVo(allocationSales, price);
        list.add(allocationSalesVo);
        //城建税
        DataTaxRateAllocation allocationConstruction = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX);
        list.add(getProjectTaskLiquidationAnalysisVo(allocationConstruction, allocationSalesVo.getMoney()));
        //地方教育税附加
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(judgeObject.getAreaGroupId());
        DataTaxRateAllocation localEducation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL, areaGroup.getProvince(), areaGroup.getCity(), null);
        list.add(getProjectTaskLiquidationAnalysisVo(localEducation, allocationSalesVo.getMoney()));
        //印花税
        DataTaxRateAllocation allocationStamp = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY);
        list.add(getProjectTaskLiquidationAnalysisVo(allocationStamp, price));
        //土地增值税
        DataTaxRateAllocation landIncrement = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_INCREMENT_TAX);
        list.add(getProjectTaskLiquidationAnalysisVo(landIncrement, price));
        //交易手续费
        DataTaxRateAllocation transactionCharges = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_TRANSACTION_CHARGES);
        list.add(getTransactionChargesVo(transactionCharges, judgeObject.getEvaluationArea()));
        //其他税费
        DataTaxRateAllocation otherTaxesFee = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_OTHER_TAXES_FEE);
        list.add(getProjectTaskLiquidationAnalysisVo(otherTaxesFee, price));
        //企业所得税
        DataTaxRateAllocation corporateIncome = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX);
        list.add(getProjectTaskLiquidationAnalysisVo(corporateIncome, price));
        return list;
    }

    public ProjectTaskLiquidationAnalysisVo getProjectTaskLiquidationAnalysisVo(DataTaxRateAllocation dataTaxRateAllocation, BigDecimal price) {
        ProjectTaskLiquidationAnalysisVo vo = new ProjectTaskLiquidationAnalysisVo();
        BeanUtils.copyProperties(dataTaxRateAllocation, vo);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(dataTaxRateAllocation.getType());
        vo.setTypeName(baseDataDic.getName());
        if (dataTaxRateAllocation.getTaxRate() != null) {
            vo.setRate(dataTaxRateAllocation.getTaxRate().multiply(new BigDecimal("100")).stripTrailingZeros().toString() + "%");
            vo.setMoney(price.multiply(dataTaxRateAllocation.getTaxRate()));
        }
        vo.setRemark("");
        return vo;
    }


    public ProjectTaskLiquidationAnalysisVo getTransactionChargesVo(DataTaxRateAllocation dataTaxRateAllocation, BigDecimal area) {
        if (area == null) {
            area = new BigDecimal("0");
        }
        ProjectTaskLiquidationAnalysisVo vo = new ProjectTaskLiquidationAnalysisVo();
        BeanUtils.copyProperties(dataTaxRateAllocation, vo);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(dataTaxRateAllocation.getType());
        vo.setTypeName(baseDataDic.getName());
        if (dataTaxRateAllocation.getAmount() != null) {
            vo.setRate(dataTaxRateAllocation.getAmount().toString() + "元/㎡");
            vo.setMoney(area.multiply(dataTaxRateAllocation.getAmount()));
        }
        vo.setRemark("");
        return vo;
    }

    public ProjectTaskLiquidationAnalysisVo getProjectTaskLiquidationAnalysisVo(SchemeLiquidationAnalysisItem item) {
        ProjectTaskLiquidationAnalysisVo vo = new ProjectTaskLiquidationAnalysisVo();
        vo.setType(item.getTaxRateId());
        vo.setTypeName(item.getTaxRateName());
        vo.setRemark(item.getRemark());
        if (item.getTaxRateValue() != null) {
            vo.setRate(item.getTaxRateValue());
        }
        vo.setMoney(item.getPrice());
        return vo;
    }

    public void saveLiquidationAnalysis(SchemeLiquidationAnalysis schemeLiquidationAnalysis) {
        if (schemeLiquidationAnalysis.getId() == null && schemeLiquidationAnalysis.getId() <= 0) {
            schemeLiquidationAnalysis.setCreator(processControllerComponent.getThisUser());
            schemeLiquidationAnalysisDao.addSchemeLiquidationAnalysis(schemeLiquidationAnalysis);
        }else{
            schemeLiquidationAnalysisDao.editSchemeLiquidationAnalysis(schemeLiquidationAnalysis);
        }
    }

    public void commit(String formData, ProjectPlanDetails projectPlanDetails, String processInsId) throws BusinessException {
        JSONObject jsonObject = JSON.parseObject(formData);
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = getDataByPlanDetailsId(projectPlanDetails.getId());
        BigDecimal decimalTotal = new BigDecimal(jsonObject.getString("total"));
        schemeLiquidationAnalysis.setTotal(decimalTotal);
        schemeLiquidationAnalysis.setProcessInsId(processInsId);
        schemeLiquidationAnalysisDao.editSchemeLiquidationAnalysis(schemeLiquidationAnalysis);
        //保存明细
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(judgeObject.getAreaGroupId());

        saveItem(null, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX, projectPlanDetails, schemeLiquidationAnalysis);
        saveItem(null, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX, projectPlanDetails, schemeLiquidationAnalysis);
        saveItem(null, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY, projectPlanDetails, schemeLiquidationAnalysis);
        saveItem(null, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_INCREMENT_TAX, projectPlanDetails, schemeLiquidationAnalysis);
        saveItem(null, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_TRANSACTION_CHARGES, projectPlanDetails, schemeLiquidationAnalysis);
        saveItem(null, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_OTHER_TAXES_FEE, projectPlanDetails, schemeLiquidationAnalysis);
        saveItem(null, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX, projectPlanDetails, schemeLiquidationAnalysis);
        saveItem(areaGroup, jsonObject, AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL, projectPlanDetails, schemeLiquidationAnalysis);
    }

    public SchemeLiquidationAnalysis getDataByPlanDetailsId(Integer planDetailsId) {
        SchemeLiquidationAnalysis where = new SchemeLiquidationAnalysis();
        where.setPlanDetailsId(planDetailsId);
        return schemeLiquidationAnalysisDao.getSchemeLiquidationAnalysis(where);
    }


    public void saveItem(SchemeAreaGroup areaGroup, JSONObject jsonObject, String type, ProjectPlanDetails projectPlanDetails, SchemeLiquidationAnalysis master) throws BusinessException {
        DataTaxRateAllocation taxRate = dataTaxRateAllocationService.getTaxRateByKey(type);
        if (areaGroup != null) {
            taxRate = dataTaxRateAllocationService.getTaxRateByKey(type, areaGroup.getProvince(), areaGroup.getCity(), null);
        }
        ProjectTaskLiquidationAnalysisVo vo = getProjectTaskLiquidationAnalysisVo(taxRate, null);
        String price = jsonObject.getString("price_" + vo.getType());
        String remark = jsonObject.getString("remark_" + vo.getType());
        String method = jsonObject.getString("calculationMethod_" + vo.getType());
        SchemeLiquidationAnalysisItem exist = isExist(master.getId(), vo.getType());
        if (exist == null) {
            SchemeLiquidationAnalysisItem item = new SchemeLiquidationAnalysisItem();
            item.setRemark(remark);
            item.setPrice(new BigDecimal(price));
            /*
            item.setCalculationMethod(Integer.valueOf(method));
            */
            item.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            item.setPlanDetailsId(projectPlanDetails.getId());
            item.setCreator(processControllerComponent.getThisUser());
            item.setMainId(master.getId());
            item.setTaxRateId(vo.getType());
            item.setTaxRateName(vo.getTypeName());
            if (vo.getTaxRate() != null) {
                item.setTaxRateValue(vo.getTaxRate().multiply(new BigDecimal("100")).stripTrailingZeros().toString() + "%");
            } else {
                item.setTaxRateValue(vo.getAmount().toString() + "元/㎡");
            }
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(item);
        } else {
            exist.setRemark(remark);
            if (StringUtils.isNotBlank(price)) {
                exist.setPrice(new BigDecimal(price));
            }
            schemeLiquidationAnalysisItemDao.editSchemeLiquidationAnalysisItem(exist);
        }

    }

    public SchemeLiquidationAnalysisItem isExist(Integer mainId, Integer type) {
        SchemeLiquidationAnalysisItem item = new SchemeLiquidationAnalysisItem();
        item.setMainId(mainId);
        item.setTaxRateId(type);
        return schemeLiquidationAnalysisItemDao.getSchemeLiquidationAnalysisItem(item);
    }

}
