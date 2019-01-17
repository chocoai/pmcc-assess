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
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
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
public class ProjectTaskLiquidationAnalysisService {
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

    public List<ProjectTaskLiquidationAnalysisVo> editPageTaxAllocation(String mainId) {
        SchemeLiquidationAnalysisItem item = new SchemeLiquidationAnalysisItem();
        item.setMainId(Integer.valueOf(mainId));
        List<SchemeLiquidationAnalysisItem> list = schemeLiquidationAnalysisItemDao.getObjectList(item);
        return LangUtils.transform(list, o -> getProjectTaskLiquidationAnalysisVo(o));

    }

    public List<ProjectTaskLiquidationAnalysisVo> getTaxAllocation(String judgeObjectId) {
        ArrayList<DataTaxRateAllocation> list = new ArrayList<>();
        //增值税
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX));
        //城建税
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX));
        //印花税
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY));
        //土地增值税
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LAND_INCREMENT_TAX));
        //交易手续费
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_TRANSACTION_CHARGES));
        //其他税费
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_OTHER_TAXES_FEE));
        //企业所得税
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX));
        //地方教育税附加
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(Integer.valueOf(judgeObjectId));
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(judgeObject.getAreaGroupId());
        list.add(dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL, areaGroup.getProvince(), areaGroup.getCity(), null));
        //SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(Integer.valueOf(projectPlanDetailsId));
        List<ProjectTaskLiquidationAnalysisVo> transform = LangUtils.transform(list, o -> getProjectTaskLiquidationAnalysisVo(o));
        return transform;
    }

    public ProjectTaskLiquidationAnalysisVo getProjectTaskLiquidationAnalysisVo(DataTaxRateAllocation dataTaxRateAllocation) {
        ProjectTaskLiquidationAnalysisVo vo = new ProjectTaskLiquidationAnalysisVo();
        BeanUtils.copyProperties(dataTaxRateAllocation, vo);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(dataTaxRateAllocation.getType());
        vo.setTypeName(baseDataDic.getName());
       /* if (dataTaxRateAllocation.getTaxRate() != null) {
            vo.setMoney(price.multiply(dataTaxRateAllocation.getTaxRate()));
        }*/
        return vo;
    }

    public ProjectTaskLiquidationAnalysisVo getProjectTaskLiquidationAnalysisVo(SchemeLiquidationAnalysisItem item) {
        ProjectTaskLiquidationAnalysisVo vo = new ProjectTaskLiquidationAnalysisVo();
        vo.setType(item.getTaxRateId());
        vo.setTypeName(item.getTaxRateName());
        vo.setRemark(item.getRemark());
        if (item.getTaxRateValue() != null) {
            vo.setTaxRate(new BigDecimal(item.getTaxRateValue()));
        }
        vo.setPrice(item.getPrice());
        return vo;
    }

    public void commit(String formData, ProjectPlanDetails projectPlanDetails, String processInsId) throws BusinessException {
        JSONObject jsonObject = JSON.parseObject(formData);
        String id = jsonObject.getString("id");
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = new SchemeLiquidationAnalysis();
        if (StringUtils.isNotBlank(id)) {
            schemeLiquidationAnalysis.setId(Integer.valueOf(id));
        }
        String total = jsonObject.getString("total");
        BigDecimal decimalTotal = new BigDecimal(total);
        schemeLiquidationAnalysis.setTotal(decimalTotal);
        if (schemeLiquidationAnalysis.getId() == null || schemeLiquidationAnalysis.getId() == 0) {
            schemeLiquidationAnalysis.setProjectId(projectPlanDetails.getProjectId());
            schemeLiquidationAnalysis.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeLiquidationAnalysis.setPlanDetailsId(projectPlanDetails.getId());
            schemeLiquidationAnalysis.setCreator(processControllerComponent.getThisUser());
            schemeLiquidationAnalysis.setProcessInsId(processInsId);
            schemeLiquidationAnalysis.setStatus(ProcessStatusEnum.RUN.getValue());
            schemeLiquidationAnalysisDao.addSchemeLiquidationAnalysis(schemeLiquidationAnalysis);
        } else {
            schemeLiquidationAnalysisDao.editSchemeLiquidationAnalysis(schemeLiquidationAnalysis);
        }
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
        ProjectTaskLiquidationAnalysisVo vo = getProjectTaskLiquidationAnalysisVo(taxRate);
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
                item.setTaxRateValue(vo.getTaxRate().toString());
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
