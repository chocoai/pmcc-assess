package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ComputeDataTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeLiquidationAnalysisApplyDto;
import com.copower.pmcc.assess.dto.output.project.scheme.ProjectTaskLiquidationAnalysisVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private CommonService commonService;

    public List<SchemeLiquidationAnalysisItem> getAnalysisItemList(Integer planDetailsId) {
        SchemeLiquidationAnalysisItem item = new SchemeLiquidationAnalysisItem();
        item.setPlanDetailsId(planDetailsId);
        List<SchemeLiquidationAnalysisItem> list = schemeLiquidationAnalysisItemDao.getObjectList(item);
        return list;

    }

    public List<SchemeLiquidationAnalysisItem> getAnalysisItemListByAreaId(Integer areaId) {
        SchemeLiquidationAnalysisItem item = new SchemeLiquidationAnalysisItem();
        item.setAreaId(areaId);
        List<SchemeLiquidationAnalysisItem> list = schemeLiquidationAnalysisItemDao.getObjectList(item);
        return list;

    }

    /**
     * 初始化所有相关税费信息
     *
     * @param areaId
     * @param planDetailsId
     */
    @Transactional(rollbackFor = Exception.class)
    public void initTaxAllocation(Integer areaId, Integer planDetailsId) {
        SchemeLiquidationAnalysisItem analysisItem = new SchemeLiquidationAnalysisItem();
        analysisItem.setAreaId(areaId);
        analysisItem.setPlanDetailsId(planDetailsId);
        analysisItem.setCreator(commonService.thisUserAccount());
        //增值税
        DataTaxRateAllocation allocationSales = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX);
        analysisItem.setTaxRateId(allocationSales.getId());
        analysisItem.setTaxRateValue(String.valueOf(allocationSales.getTaxRate()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(allocationSales.getType()));
        analysisItem.setCalculateBase(allocationSales.getCalculateBase());
        analysisItem.setCalculationFormula(allocationSales.getCalculationFormula());
        analysisItem.setTaxesBurden(allocationSales.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        //城建税
        DataTaxRateAllocation allocationConstruction = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX);
        analysisItem.setTaxRateId(allocationConstruction.getId());
        analysisItem.setTaxRateValue(String.valueOf(allocationConstruction.getTaxRate()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(allocationConstruction.getType()));
        analysisItem.setCalculateBase(allocationConstruction.getCalculateBase());
        analysisItem.setCalculationFormula(allocationConstruction.getCalculationFormula());
        analysisItem.setTaxesBurden(allocationConstruction.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        //地方教育税附加
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(areaId);
        DataTaxRateAllocation localEducation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL, areaGroup.getProvince(), areaGroup.getCity(), null);
        analysisItem.setTaxRateId(localEducation.getId());
        analysisItem.setTaxRateValue(String.valueOf(localEducation.getTaxRate()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(localEducation.getType()));
        analysisItem.setCalculateBase(localEducation.getCalculateBase());
        analysisItem.setCalculationFormula(localEducation.getCalculationFormula());
        analysisItem.setTaxesBurden(localEducation.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        //印花税
        DataTaxRateAllocation allocationStamp = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY);
        analysisItem.setTaxRateId(allocationStamp.getId());
        analysisItem.setTaxRateValue(String.valueOf(allocationStamp.getTaxRate()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(allocationStamp.getType()));
        analysisItem.setCalculateBase(allocationStamp.getCalculateBase());
        analysisItem.setCalculationFormula(allocationStamp.getCalculationFormula());
        analysisItem.setTaxesBurden(allocationStamp.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        //土地增值税
        DataTaxRateAllocation landIncrement = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_INCREMENT_TAX);
        analysisItem.setTaxRateId(landIncrement.getId());
        analysisItem.setTaxRateValue(String.valueOf(landIncrement.getTaxRate()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(landIncrement.getType()));
        analysisItem.setCalculateBase(landIncrement.getCalculateBase());
        analysisItem.setCalculationFormula(landIncrement.getCalculationFormula());
        analysisItem.setTaxesBurden(landIncrement.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        //交易手续费
        DataTaxRateAllocation transactionCharges = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_TRANSACTION_CHARGES);
        analysisItem.setTaxRateId(transactionCharges.getId());
        analysisItem.setTaxRateValue(String.valueOf(transactionCharges.getAmount()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.ABSOLUTE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(transactionCharges.getType()));
        analysisItem.setCalculateBase(transactionCharges.getCalculateBase());
        analysisItem.setCalculationFormula(transactionCharges.getCalculationFormula());
        analysisItem.setTaxesBurden(transactionCharges.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        //其他税费
        DataTaxRateAllocation otherTaxesFee = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_OTHER_TAXES_FEE);
        analysisItem.setTaxRateId(otherTaxesFee.getId());
        analysisItem.setTaxRateValue(String.valueOf(otherTaxesFee.getTaxRate()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(otherTaxesFee.getType()));
        analysisItem.setCalculateBase(otherTaxesFee.getCalculateBase());
        analysisItem.setCalculationFormula(otherTaxesFee.getCalculationFormula());
        analysisItem.setTaxesBurden(otherTaxesFee.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        //企业所得税
        DataTaxRateAllocation corporateIncome = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX);
        analysisItem.setTaxRateId(corporateIncome.getId());
        analysisItem.setTaxRateValue(String.valueOf(corporateIncome.getTaxRate()));
        analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
        analysisItem.setTaxRateName(baseDataDicService.getNameById(corporateIncome.getType()));
        analysisItem.setCalculateBase(corporateIncome.getCalculateBase());
        analysisItem.setCalculationFormula(corporateIncome.getCalculationFormula());
        analysisItem.setTaxesBurden(corporateIncome.getTaxesBurden());
        schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
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

    public void saveLiquidationAnalysis(SchemeLiquidationAnalysis schemeLiquidationAnalysis) {
        if (schemeLiquidationAnalysis.getId() == null || schemeLiquidationAnalysis.getId() <= 0) {
            schemeLiquidationAnalysis.setCreator(processControllerComponent.getThisUser());
            schemeLiquidationAnalysisDao.addSchemeLiquidationAnalysis(schemeLiquidationAnalysis);
        } else {
            schemeLiquidationAnalysisDao.editSchemeLiquidationAnalysis(schemeLiquidationAnalysis);
        }
    }

    public void commit(String formData, String processInsId) throws BusinessException {
        SchemeLiquidationAnalysisApplyDto analysisApplyDto = JSON.parseObject(formData, SchemeLiquidationAnalysisApplyDto.class);
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = this.getSchemeLiquidationAnalysis(analysisApplyDto.getId());
//        schemeLiquidationAnalysis.setTotal(analysisApplyDto.getTotal());
//        schemeLiquidationAnalysis.setProcessInsId(processInsId);
        BeanUtils.copyProperties(analysisApplyDto, schemeLiquidationAnalysis);
        schemeLiquidationAnalysisDao.editSchemeLiquidationAnalysis(schemeLiquidationAnalysis);

        //修改子表
        List<SchemeLiquidationAnalysisItem> analysisItemList = analysisApplyDto.getAnalysisItemList();
        if (CollectionUtils.isNotEmpty(analysisItemList)) {
            for (SchemeLiquidationAnalysisItem analysisItem : analysisItemList) {
                if (analysisItem.getId() != null) {
                    if (Objects.equal(analysisItem.getAreaId(), schemeLiquidationAnalysis.getAreaId())) {
                        analysisItem.setAreaId(schemeLiquidationAnalysis.getAreaId());
                    }
                    schemeLiquidationAnalysisItemDao.editSchemeLiquidationAnalysisItem(analysisItem);
                }
            }
        }
    }

    public SchemeLiquidationAnalysis getDataByPlanDetailsId(Integer planDetailsId) {
        SchemeLiquidationAnalysis where = new SchemeLiquidationAnalysis();
        where.setPlanDetailsId(planDetailsId);
        return schemeLiquidationAnalysisDao.getSchemeLiquidationAnalysis(where);
    }

    public SchemeLiquidationAnalysis getDataByAreaId(Integer areaId) {
        SchemeLiquidationAnalysis where = new SchemeLiquidationAnalysis();
        where.setAreaId(areaId);
        return schemeLiquidationAnalysisDao.getSchemeLiquidationAnalysis(where);
    }

    public SchemeLiquidationAnalysis getSchemeLiquidationAnalysis(Integer id) {
        return schemeLiquidationAnalysisDao.getSchemeLiquidationAnalysis(id);
    }


}
