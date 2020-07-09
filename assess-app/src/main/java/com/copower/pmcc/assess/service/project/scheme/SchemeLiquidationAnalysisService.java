package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ComputeDataTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseHuxingPriceDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisItemDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisJudgeDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeLiquidationAnalysisApplyDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeLiquidationAnalysisGroupDto;
import com.copower.pmcc.assess.dto.output.project.scheme.ProjectTaskLiquidationAnalysisGroupAndPriceVo;
import com.copower.pmcc.assess.dto.output.project.scheme.ProjectTaskLiquidationAnalysisVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeLiquidationAnalysisGroupVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private SchemeLiquidationAnalysisGroupDao schemeLiquidationAnalysisGroupDao;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeLiquidationAnalysisJudgeDao schemeLiquidationAnalysisJudgeDao;
    @Autowired
    private BasicHouseHuxingPriceDao basicHouseHuxingPriceDao;

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

    public List<SchemeLiquidationAnalysisItem> getAnalysisItemListByGroupId(Integer groupId) {
        SchemeLiquidationAnalysisItem item = new SchemeLiquidationAnalysisItem();
        item.setGroupId(groupId);
        List<SchemeLiquidationAnalysisItem> list = schemeLiquidationAnalysisItemDao.getObjectList(item);
        return list;

    }

    //调整原始数据
    public void adjustLiquidationAnalysis() {
        //所有变现分析主表
        List<SchemeLiquidationAnalysis> liquidationAnalysisList = schemeLiquidationAnalysisDao.getObjectList(new SchemeLiquidationAnalysis());

        for (SchemeLiquidationAnalysis liquidationAnalysisItem : liquidationAnalysisList) {
            List<SchemeLiquidationAnalysisGroupVo> groupList = this.getSchemeLiquidationAnalysisGroupList(liquidationAnalysisItem.getPlanDetailsId(), liquidationAnalysisItem.getAreaId());
            if (CollectionUtils.isEmpty(groupList)) {
                //添加分组表
                SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup = new SchemeLiquidationAnalysisGroup();
                BeanUtils.copyProperties(liquidationAnalysisItem, schemeLiquidationAnalysisGroup);
                List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(liquidationAnalysisItem.getProjectId());
                List<Integer> allRecordIds = LangUtils.transform(declareRecordList, p -> p.getId());
                String records = StringUtils.join(allRecordIds.toArray(), ",");
                schemeLiquidationAnalysisGroup.setRecordIds(records);
                schemeLiquidationAnalysisGroupDao.addSchemeLiquidationAnalysisGroup(schemeLiquidationAnalysisGroup);
                //关联子表
                List<SchemeLiquidationAnalysisItem> analysisItemList = this.getAnalysisItemList(liquidationAnalysisItem.getPlanDetailsId());
                if (CollectionUtils.isNotEmpty(analysisItemList)) {
                    for (SchemeLiquidationAnalysisItem analysisItem : analysisItemList) {
                        SchemeLiquidationAnalysisItem schemeLiquidationAnalysisItem = schemeLiquidationAnalysisItemDao.getSchemeLiquidationAnalysisItem(analysisItem.getId());
                        schemeLiquidationAnalysisItem.setGroupId(schemeLiquidationAnalysisGroup.getId());
                        schemeLiquidationAnalysisItemDao.editSchemeLiquidationAnalysisItem(schemeLiquidationAnalysisItem);
                    }
                }
            }
        }
    }

    /**
     * 初始化所有相关税费信息
     *
     * @param areaId
     * @param planDetailsId
     */
    @Transactional(rollbackFor = Exception.class)
    public void initTaxAllocation(Integer areaId, Integer planDetailsId, Integer groupId) {
        SchemeLiquidationAnalysisItem analysisItem = new SchemeLiquidationAnalysisItem();
        analysisItem.setAreaId(areaId);
        analysisItem.setPlanDetailsId(planDetailsId);
        analysisItem.setGroupId(groupId);
        analysisItem.setCreator(commonService.thisUserAccount());
        //增值税
        DataTaxRateAllocation allocationSales = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX);
        if (allocationSales != null) {
            analysisItem.setTaxRateId(allocationSales.getId());
            analysisItem.setTaxRateValue(String.valueOf(allocationSales.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(allocationSales.getType()));
            analysisItem.setCalculateBase(allocationSales.getCalculateBase());
            analysisItem.setCalculationFormula(allocationSales.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(allocationSales.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //城建税
        DataTaxRateAllocation allocationConstruction = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX);
        if (allocationConstruction != null) {
            analysisItem.setTaxRateId(allocationConstruction.getId());
            analysisItem.setTaxRateValue(String.valueOf(allocationConstruction.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(allocationConstruction.getType()));
            analysisItem.setCalculateBase(allocationConstruction.getCalculateBase());
            analysisItem.setCalculationFormula(allocationConstruction.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(allocationConstruction.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //教育税附加
        DataTaxRateAllocation education = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_EDUCATION_FEE_PLUS, null, null, null);
        if (education != null) {
            analysisItem.setTaxRateId(education.getId());
            analysisItem.setTaxRateValue(String.valueOf(education.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(education.getType()));
            analysisItem.setCalculateBase(education.getCalculateBase());
            analysisItem.setCalculationFormula(education.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(education.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_EDUCATION_FEE_PLUS);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //地方教育税附加
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
        DataTaxRateAllocation localEducation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL, areaGroup.getProvince(), areaGroup.getCity(), null);
        if (localEducation != null) {
            analysisItem.setTaxRateId(localEducation.getId());
            analysisItem.setTaxRateValue(String.valueOf(localEducation.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(localEducation.getType()));
            analysisItem.setCalculateBase(localEducation.getCalculateBase());
            analysisItem.setCalculationFormula(localEducation.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(localEducation.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //印花税
        DataTaxRateAllocation allocationStamp = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY);
        if (allocationStamp != null) {
            analysisItem.setTaxRateId(allocationStamp.getId());
            analysisItem.setTaxRateValue(String.valueOf(allocationStamp.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(allocationStamp.getType()));
            analysisItem.setCalculateBase(allocationStamp.getCalculateBase());
            analysisItem.setCalculationFormula(allocationStamp.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(allocationStamp.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //土地增值税
        DataTaxRateAllocation landIncrement = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_INCREMENT_TAX);
        if (landIncrement != null) {
            analysisItem.setTaxRateId(landIncrement.getId());
            analysisItem.setTaxRateValue(String.valueOf(landIncrement.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(landIncrement.getType()));
            analysisItem.setCalculateBase(landIncrement.getCalculateBase());
            analysisItem.setCalculationFormula(landIncrement.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(landIncrement.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_INCREMENT_TAX);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //交易手续费
        DataTaxRateAllocation transactionCharges = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_TRANSACTION_CHARGES);
        if (transactionCharges != null) {
            analysisItem.setTaxRateId(transactionCharges.getId());
            analysisItem.setTaxRateValue(String.valueOf(transactionCharges.getAmount()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.ABSOLUTE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(transactionCharges.getType()));
            analysisItem.setCalculateBase(transactionCharges.getCalculateBase());
            analysisItem.setCalculationFormula(transactionCharges.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(transactionCharges.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_TRANSACTION_CHARGES);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //其他税费
        DataTaxRateAllocation otherTaxesFee = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_OTHER_TAXES_FEE);
        if (otherTaxesFee != null) {
            analysisItem.setTaxRateId(otherTaxesFee.getId());
            analysisItem.setTaxRateValue(String.valueOf(otherTaxesFee.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(otherTaxesFee.getType()));
            analysisItem.setCalculateBase(otherTaxesFee.getCalculateBase());
            analysisItem.setCalculationFormula(otherTaxesFee.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(otherTaxesFee.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_OTHER_TAXES_FEE);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //企业所得税
        DataTaxRateAllocation corporateIncome = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX);
        if (corporateIncome != null) {
            analysisItem.setTaxRateId(corporateIncome.getId());
            analysisItem.setTaxRateValue(String.valueOf(corporateIncome.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(corporateIncome.getType()));
            analysisItem.setCalculateBase(corporateIncome.getCalculateBase());
            analysisItem.setCalculationFormula(corporateIncome.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(corporateIncome.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //契税
        DataTaxRateAllocation deedTax = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_DEED_TAX);
        if (deedTax != null) {
            analysisItem.setTaxRateId(deedTax.getId());
            analysisItem.setTaxRateValue(String.valueOf(deedTax.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(deedTax.getType()));
            analysisItem.setCalculateBase(deedTax.getCalculateBase());
            analysisItem.setCalculationFormula(deedTax.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(deedTax.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_DEED_TAX);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }

        //预计处置费用
        DataTaxRateAllocation disposalFee = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_DISPOSAL_FEE);
        if (disposalFee != null) {
            analysisItem.setTaxRateId(disposalFee.getId());
            analysisItem.setTaxRateValue(String.valueOf(disposalFee.getTaxRate()));
            analysisItem.setCalculationMethod(ComputeDataTypeEnum.RELATIVE.getId());
            analysisItem.setTaxRateName(baseDataDicService.getNameById(disposalFee.getType()));
            analysisItem.setCalculateBase(disposalFee.getCalculateBase());
            analysisItem.setCalculationFormula(disposalFee.getCalculationFormula());
            analysisItem.setTaxesBurden(baseDataDicService.getNameById(disposalFee.getTaxesBurden()));
            setBurdenScale(analysisItem);
            analysisItem.setTypeKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_DISPOSAL_FEE);
            schemeLiquidationAnalysisItemDao.addSchemeLiquidationAnalysisItem(analysisItem);
        }
    }

    //设置承担方比例
    public void setBurdenScale(SchemeLiquidationAnalysisItem analysisItem) {
        BaseDataDic dicSeller = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TAXES_BURDEN_SELLER);
        BaseDataDic dicBuyer = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TAXES_BURDEN_BUYER);
        BaseDataDic dicBoth = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TAXES_BURDEN_BOTH);
        //承担方为空的话默认设置承担方为卖方承担
        if (StringUtils.isEmpty(analysisItem.getTaxesBurden())) {
            analysisItem.setTaxesBurden(dicSeller.getName());
        }
        //默认设置比例
        if (dicSeller.getName().equals(analysisItem.getTaxesBurden())) {
            analysisItem.setSellerScale(new BigDecimal("1"));
            analysisItem.setBuyerScale(new BigDecimal("0"));
        }
        if (dicBuyer.getName().equals(analysisItem.getTaxesBurden())) {
            analysisItem.setBuyerScale(new BigDecimal("1"));
            analysisItem.setSellerScale(new BigDecimal("0"));
        }
        if (dicBoth.getName().equals(analysisItem.getTaxesBurden())) {
            analysisItem.setSellerScale(new BigDecimal("0.5"));
            analysisItem.setBuyerScale(new BigDecimal("0.5"));
        }
    }

    public void fixAnalysisOldData() {
        SchemeLiquidationAnalysisItem analysisItem = new SchemeLiquidationAnalysisItem();
        List<SchemeLiquidationAnalysisItem> itemList = schemeLiquidationAnalysisItemDao.getObjectList(analysisItem);
        if (CollectionUtils.isNotEmpty(itemList)) {
            for (SchemeLiquidationAnalysisItem item : itemList) {
                setBurdenScale(item);
                schemeLiquidationAnalysisItemDao.editSchemeLiquidationAnalysisItem(item);
            }
        }

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

    public void commit(String formData, String processInsId) {
        SchemeLiquidationAnalysisApplyDto analysisApplyDto = JSON.parseObject(formData, SchemeLiquidationAnalysisApplyDto.class);
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = this.getSchemeLiquidationAnalysis(analysisApplyDto.getId());
        BeanUtils.copyProperties(analysisApplyDto, schemeLiquidationAnalysis);
        schemeLiquidationAnalysisDao.editSchemeLiquidationAnalysis(schemeLiquidationAnalysis);

        List<SchemeLiquidationAnalysisGroupDto> schemeLiquidationAnalysisGroupDtos = analysisApplyDto.getTaskLiquidationAnalysisGroups();
        if (CollectionUtils.isNotEmpty(schemeLiquidationAnalysisGroupDtos)) {
            for (SchemeLiquidationAnalysisGroupDto groupItem : schemeLiquidationAnalysisGroupDtos) {
                this.addAnalysisGroupFromDto(groupItem);
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

    //=========================SchemeLiquidationAnalysisGroup============

    public List<SchemeLiquidationAnalysisGroupVo> getSchemeLiquidationAnalysisGroupList(Integer planDetailsId, Integer projectId) {
        SchemeLiquidationAnalysisGroup surveyAssetInventoryAnalysisRecord = new SchemeLiquidationAnalysisGroup();
        surveyAssetInventoryAnalysisRecord.setProjectId(projectId);
        surveyAssetInventoryAnalysisRecord.setPlanDetailsId(planDetailsId);
        List<SchemeLiquidationAnalysisGroup> schemeLiquidationAnalysisGroups = schemeLiquidationAnalysisGroupDao.getObjectList(surveyAssetInventoryAnalysisRecord);
        if (CollectionUtils.isNotEmpty(schemeLiquidationAnalysisGroups))
            return LangUtils.transform(schemeLiquidationAnalysisGroups, o -> this.getSchemeLiquidationAnalysisGroupVo(o));
        return null;
    }

    public void removeLiquidationAnalysisGroup(Integer id) {
        schemeLiquidationAnalysisGroupDao.deleteSchemeLiquidationAnalysisGroup(id);
        //删除明细
        SchemeLiquidationAnalysisItem schemeLiquidationAnalysisItem = new SchemeLiquidationAnalysisItem();
        schemeLiquidationAnalysisItem.setGroupId(id);
        List<SchemeLiquidationAnalysisItem> itemList = schemeLiquidationAnalysisItemDao.getObjectList(schemeLiquidationAnalysisItem);
        itemList.forEach(oo -> schemeLiquidationAnalysisItemDao.deleteSchemeLiquidationAnalysisItem(oo.getId()));

        SchemeLiquidationAnalysisJudge data = new SchemeLiquidationAnalysisJudge();
        data.setGroupId(id);
        List<SchemeLiquidationAnalysisJudge> judgeList = schemeLiquidationAnalysisJudgeDao.getSchemeLiquidationAnalysisJudgeList(data);
        judgeList.forEach(p -> schemeLiquidationAnalysisJudgeDao.deleteInfo(p.getId()));
    }

    public List<SchemeLiquidationAnalysisGroup> getGroupByAreaId(Integer areaId, Integer projectId) {
        SchemeLiquidationAnalysisGroup surveyAssetInventoryAnalysisRecord = new SchemeLiquidationAnalysisGroup();
        surveyAssetInventoryAnalysisRecord.setProjectId(projectId);
        surveyAssetInventoryAnalysisRecord.setAreaId(areaId);
        List<SchemeLiquidationAnalysisGroup> schemeLiquidationAnalysisGroups = schemeLiquidationAnalysisGroupDao.getObjectList(surveyAssetInventoryAnalysisRecord);
        return schemeLiquidationAnalysisGroups;
    }

    public SchemeLiquidationAnalysisGroup getLiquidationAnalysisGroupById(Integer id) {
        return schemeLiquidationAnalysisGroupDao.getSchemeLiquidationAnalysisGroup(id);
    }

    public void saveLiquidationAnalysisGroup(SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup) {
        if (schemeLiquidationAnalysisGroup.getId() == null || schemeLiquidationAnalysisGroup.getId() <= 0) {
            schemeLiquidationAnalysisGroup.setCreator(processControllerComponent.getThisUser());
            schemeLiquidationAnalysisGroupDao.addSchemeLiquidationAnalysisGroup(schemeLiquidationAnalysisGroup);
        } else {
            schemeLiquidationAnalysisGroupDao.editSchemeLiquidationAnalysisGroup(schemeLiquidationAnalysisGroup);
        }
    }

    public ProjectTaskLiquidationAnalysisGroupAndPriceVo augmentHuxingAreaAndPrice(String huxingPriceIds, Integer groupId, String oldGroupArea, String oldGroupPrice) {
        List<Integer> huxingIdsList = FormatUtils.transformString2Integer(huxingPriceIds);
        if (CollectionUtils.isEmpty(huxingIdsList)) return null;
        //不存在则添加
        SchemeLiquidationAnalysisGroup analysisGroup = schemeLiquidationAnalysisGroupDao.getSchemeLiquidationAnalysisGroup(groupId);
        String groupHuxingPriceIds = analysisGroup.getHuxingPriceIds();
        StringBuilder builder = new StringBuilder();
        if (!StringUtils.isEmpty(groupHuxingPriceIds)) {
            builder.append(groupHuxingPriceIds);
        }
        for (Integer huxingPriceId : huxingIdsList) {
            if (StringUtils.isNotEmpty(groupHuxingPriceIds)) {
                if (!groupHuxingPriceIds.contains(String.valueOf(huxingPriceId))) {
                    builder.append(",").append(huxingPriceId);
                }
            } else {
                builder.append(huxingPriceId).append(",");
            }

        }
        analysisGroup.setHuxingPriceIds(builder.toString());
        schemeLiquidationAnalysisGroupDao.editSchemeLiquidationAnalysisGroup(analysisGroup);

        return getGroupAndPriceVoByString(groupId);
    }


    public String idsListToString(List<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            StringBuilder s = new StringBuilder();
            for (Integer id : ids) {
                s.append(id).append(",");
            }
            return s.deleteCharAt(s.length() - 1).toString();
        }
        return "";
    }

    public ProjectTaskLiquidationAnalysisGroupAndPriceVo getGroupAndPriceVoByJsonStr(String judgeObjectIds, Integer areaId, Integer groupId) {
        List<Integer> schemeJudgeObjList = FormatUtils.transformString2Integer(judgeObjectIds);
        addSchemeLiquidationAnalysisJudges(schemeJudgeObjList, areaId, groupId);
        return this.getGroupAndPriceVoBySchemeList(schemeJudgeObjList, groupId);
    }

    public ProjectTaskLiquidationAnalysisGroupAndPriceVo getGroupAndPriceVoByString(Integer groupId) {
        List<Integer> schemeJudgeObjList = getSchemeJudgeObjIds(groupId);
        return this.getGroupAndPriceVoBySchemeList(schemeJudgeObjList, groupId);
    }

    public ProjectTaskLiquidationAnalysisGroupAndPriceVo getGroupAndPriceVoBySchemeList(List<Integer> schemeJudgeObjList, Integer groupId) {
        ProjectTaskLiquidationAnalysisGroupAndPriceVo groupAndPriceVo = new ProjectTaskLiquidationAnalysisGroupAndPriceVo();
        BigDecimal groupArea = new BigDecimal("0");
        BigDecimal groupPrice = new BigDecimal("0");
        if (CollectionUtils.isEmpty(schemeJudgeObjList)) {
            groupAndPriceVo.setGroupArea(groupArea);
            groupAndPriceVo.setGroupPrice(groupPrice);
            return groupAndPriceVo;
        }

        List<SchemeLiquidationAnalysisJudge> judgeList = schemeLiquidationAnalysisJudgeDao.getListByGroupId(groupId);
        List<Integer> transform = LangUtils.transform(judgeList, o -> o.getJudgeObjectId());
        List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getListByIds(transform);
        //应该获取最终测算好的价格与面积
        if (CollectionUtils.isNotEmpty(judgeObjects)) {
            for (SchemeJudgeObject judgeObject : judgeObjects) {
                if (judgeObject.getBisMerge() == Boolean.TRUE) {
                    List<SchemeJudgeObject> listByPid = schemeJudgeObjectService.getListByPid(judgeObject.getId());
                    if (CollectionUtils.isNotEmpty(listByPid)) {
                        for (SchemeJudgeObject schemeJudgeObjectVo : listByPid) {
                            if (schemeJudgeObjectVo.getEvaluationArea() != null) {
                                groupArea = groupArea.add(schemeJudgeObjectVo.getEvaluationArea());
                                if (schemeJudgeObjectVo.getPrice() != null) {
                                    groupPrice = groupPrice.add(schemeJudgeObjectVo.getEvaluationArea().multiply(schemeJudgeObjectVo.getPrice()));
                                }
                            }
                        }
                    }
                } else {
                    if (judgeObject.getEvaluationArea() != null) {
                        groupArea = groupArea.add(judgeObject.getEvaluationArea());
                        if (judgeObject.getPrice() != null) {
                            groupPrice = groupPrice.add(judgeObject.getEvaluationArea().multiply(judgeObject.getPrice()));
                        }
                    }
                }
            }
        }
        //户型差异调查表
        SchemeLiquidationAnalysisGroup analysisGroup = schemeLiquidationAnalysisGroupDao.getSchemeLiquidationAnalysisGroup(groupId);
        if (StringUtils.isNotEmpty(analysisGroup.getHuxingPriceIds())) {
            List<String> idsStr = Arrays.asList(analysisGroup.getHuxingPriceIds().split(","));
            ArrayList<Integer> completeIds = Lists.newArrayList();
            for (String id : idsStr) {
                if (NumberUtils.isNumber(id)) {
                    completeIds.add(Integer.valueOf(id));
                }
            }
            if (CollectionUtils.isNotEmpty(completeIds)) {
                List<BasicHouseHuxingPrice> huxingPriceList = basicHouseHuxingPriceDao.getListByIds(completeIds, "");
                if (CollectionUtils.isNotEmpty(huxingPriceList)) {
                    for (BasicHouseHuxingPrice item : huxingPriceList) {
                        if (item.getArea() != null) {
                            groupArea = groupArea.add(item.getArea());
                        }
                        if (item.getPrice() != null) {
                            groupPrice = groupPrice.add(item.getPrice());
                        }
                    }
                }
            }
        }

        groupAndPriceVo.setGroupArea(groupArea);
        groupAndPriceVo.setGroupPrice(groupPrice);
        return groupAndPriceVo;
    }

    public void addSchemeLiquidationAnalysisJudges(List<Integer> schemeJudgeObjIds, Integer areaId, Integer groupId) {
        //判断是否存在后再添加
        SchemeLiquidationAnalysisJudge oldData = new SchemeLiquidationAnalysisJudge();
        oldData.setGroupId(groupId);
        List<SchemeLiquidationAnalysisJudge> oldList = schemeLiquidationAnalysisJudgeDao.getSchemeLiquidationAnalysisJudgeList(oldData);
        List<Integer> existIds = LangUtils.transform(oldList, o -> o.getJudgeObjectId());
        if (CollectionUtils.isEmpty(schemeJudgeObjIds)) return;
        for (Integer judgeObjectId : schemeJudgeObjIds) {
            if (existIds.contains(judgeObjectId)) continue;
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            SchemeLiquidationAnalysisJudge schemeLiquidationAnalysisJudge = new SchemeLiquidationAnalysisJudge();
            schemeLiquidationAnalysisJudge.setAreaId(areaId);
            schemeLiquidationAnalysisJudge.setGroupId(groupId);
            schemeLiquidationAnalysisJudge.setJudgeObjectId(judgeObjectId);
            schemeLiquidationAnalysisJudge.setName(schemeJudgeObject.getName());
            schemeLiquidationAnalysisJudge.setCertName(schemeJudgeObject.getCertName());
            schemeLiquidationAnalysisJudge.setOwnership(schemeJudgeObject.getOwnership());
            schemeLiquidationAnalysisJudge.setSeat(schemeJudgeObject.getSeat());
            schemeLiquidationAnalysisJudge.setCreator(commonService.thisUserAccount());
            schemeLiquidationAnalysisJudgeDao.addSchemeLiquidationAnalysisJudge(schemeLiquidationAnalysisJudge);

        }
    }

    public void addAnalysisGroupFromDto(SchemeLiquidationAnalysisGroupDto analysisGroupDto) {

        SchemeLiquidationAnalysisGroup analysisGroup = this.getLiquidationAnalysisGroupById(analysisGroupDto.getId());
        BeanUtils.copyProperties(analysisGroupDto, analysisGroup);
        this.saveLiquidationAnalysisGroup(analysisGroup);
        //修改子表
        List<SchemeLiquidationAnalysisItem> analysisItemList = analysisGroupDto.getAnalysisItemList();
        if (CollectionUtils.isNotEmpty(analysisItemList)) {
            for (SchemeLiquidationAnalysisItem analysisItem : analysisItemList) {
                if (analysisItem.getId() != null) {
                    schemeLiquidationAnalysisItemDao.editSchemeLiquidationAnalysisItem(analysisItem);
                }
            }
        }
    }

    public SchemeLiquidationAnalysisGroupVo getSchemeLiquidationAnalysisGroupVo(SchemeLiquidationAnalysisGroup oo) {
        SchemeLiquidationAnalysisGroupVo vo = new SchemeLiquidationAnalysisGroupVo();
        if (oo == null) {
            return null;
        }
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        SchemeLiquidationAnalysisJudge oldData = new SchemeLiquidationAnalysisJudge();
        oldData.setGroupId(oo.getId());
        List<SchemeLiquidationAnalysisJudge> SchemeLiquidationAnalysisJudges = schemeLiquidationAnalysisJudgeDao.getSchemeLiquidationAnalysisJudgeList(oldData);

        if (CollectionUtils.isNotEmpty(SchemeLiquidationAnalysisJudges)) {
            StringBuilder stringBuilder = new StringBuilder(8);
            SchemeLiquidationAnalysisJudges.forEach(o -> stringBuilder.append(o.getName()).append(","));
            vo.setRecordNames(StringUtils.strip(stringBuilder.toString(), ","));
        }
        return vo;
    }

    //获取选择的SchemeJudgeObject的id集合
    public List<Integer> getSchemeJudgeObjIds(Integer groupId) {
        SchemeLiquidationAnalysisJudge data = new SchemeLiquidationAnalysisJudge();
        data.setGroupId(groupId);
        List<SchemeLiquidationAnalysisJudge> dataList = schemeLiquidationAnalysisJudgeDao.getSchemeLiquidationAnalysisJudgeList(data);
        if (CollectionUtils.isEmpty(dataList)) return null;
        return LangUtils.transform(dataList, o -> o.getJudgeObjectId());
    }


    public ProjectTaskLiquidationAnalysisGroupAndPriceVo cutHuxingPrice(String huxingPriceId, Integer groupId, String oldGroupArea, String oldGroupPrice) {
        SchemeLiquidationAnalysisGroup analysisGroup = schemeLiquidationAnalysisGroupDao.getSchemeLiquidationAnalysisGroup(groupId);
        String replace = analysisGroup.getHuxingPriceIds().replace(huxingPriceId, "");
        List<String> idsStr = Arrays.asList(replace.split(","));

        ArrayList<Integer> completeIds = Lists.newArrayList();
        for (String id : idsStr) {
            if (NumberUtils.isNumber(id)) {
                completeIds.add(Integer.valueOf(id));
            }
        }
        String s = idsListToString(completeIds);
        analysisGroup.setHuxingPriceIds(s);
        schemeLiquidationAnalysisGroupDao.editSchemeLiquidationAnalysisGroup(analysisGroup);

        return getGroupAndPriceVoByString(groupId);
    }

    public BootstrapTableVo getHuxingPricesByGroupId(Integer groupId, String houseNumber) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        SchemeLiquidationAnalysisGroup analysisGroup = schemeLiquidationAnalysisGroupDao.getSchemeLiquidationAnalysisGroup(groupId);
        List<BasicHouseHuxingPrice> huxingPriceList = Lists.newArrayList();
        if (StringUtils.isNotEmpty(analysisGroup.getHuxingPriceIds())) {
            ArrayList<Integer> completeIds = Lists.newArrayList();
            List<String> idsStr = Arrays.asList(analysisGroup.getHuxingPriceIds().split(","));
            for (String id : idsStr) {
                if (NumberUtils.isNumber(id)) {
                    completeIds.add(Integer.valueOf(id));
                }
            }
            huxingPriceList = basicHouseHuxingPriceDao.getListByIds(completeIds, houseNumber);
        }

        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(huxingPriceList) ? new ArrayList<BasicHouseHuxingPrice>() : huxingPriceList);
        return vo;

    }
}
