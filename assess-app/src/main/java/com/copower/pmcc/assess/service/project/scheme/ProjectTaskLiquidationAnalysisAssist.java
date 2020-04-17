package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisJudgeDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "变现分析税费")
public class ProjectTaskLiquidationAnalysisAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DataTaxRateAllocationService dataTaxRateAllocationService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeLiquidationAnalysisJudgeDao schemeLiquidationAnalysisJudgeDao;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskLiquidationAnalysisIndex", "", 0, "0", "");
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByPlanDetailsId(projectPlanDetails.getId());
        if (schemeLiquidationAnalysis == null) {
            schemeLiquidationAnalysis = new SchemeLiquidationAnalysis();
            schemeLiquidationAnalysis.setProjectId(projectPlanDetails.getProjectId());
            schemeLiquidationAnalysis.setPlanDetailsId(projectPlanDetails.getId());
            schemeLiquidationAnalysis.setAreaId(projectPlanDetails.getAreaId());
            schemeLiquidationAnalysisService.saveLiquidationAnalysis(schemeLiquidationAnalysis);
            SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup = new SchemeLiquidationAnalysisGroup();
            schemeLiquidationAnalysisGroup.setProjectId(projectPlanDetails.getProjectId());
            schemeLiquidationAnalysisGroup.setPlanDetailsId(projectPlanDetails.getId());
            schemeLiquidationAnalysisGroup.setAreaId(projectPlanDetails.getAreaId());
            schemeLiquidationAnalysisService.saveLiquidationAnalysisGroup(schemeLiquidationAnalysisGroup);
            schemeLiquidationAnalysisService.initTaxAllocation(projectPlanDetails.getAreaId(), projectPlanDetails.getId(), schemeLiquidationAnalysisGroup.getId());
        }
        modelAndView.addObject("master", schemeLiquidationAnalysis);
        setModelParam(modelAndView, projectPlanDetails);
        refreshAnalysisJudge(projectPlanDetails.getAreaId());
        return modelAndView;
    }

    /**
     * 刷新关联的估价对象数据
     * 估价对象数据可能已发生变化
     * @param areaId
     */
    @Transactional(rollbackFor = Exception.class)
    public void refreshAnalysisJudge(Integer areaId){
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        List<Integer> list = LangUtils.transform(judgeObjectList, o -> o.getId());
        List<SchemeLiquidationAnalysisJudge> analysisJudgeList = schemeLiquidationAnalysisJudgeDao.getListByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(analysisJudgeList) && CollectionUtils.isNotEmpty(list)) {
            analysisJudgeList.forEach(o -> {
                if (!list.contains(o.getJudgeObjectId())){
                    schemeLiquidationAnalysisJudgeDao.deleteInfo(o.getId());
                    return;
                }
                SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(o.getJudgeObjectId());
                if(judgeObject!=null){
                    o.setName(judgeObject.getName());
                    schemeLiquidationAnalysisJudgeDao.updateSchemeLiquidationAnalysisJudge(o);
                }
            });
        }
    }

    /**
     * 详情处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskLiquidationAnalysisApproval", processInsId, boxId, taskId, agentUserAccount);
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", schemeLiquidationAnalysis);
        setModelParam(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    /**
     * 返回修改处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskLiquidationAnalysisIndex", processInsId, boxId, taskId, agentUserAccount);
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", schemeLiquidationAnalysis);
        setModelParam(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskLiquidationAnalysisApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", schemeLiquidationAnalysis);
        setModelParam(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeLiquidationAnalysisService.commit(formData, processInsId);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeLiquidationAnalysisService.commit(formData, processInsId);
    }

    private void setModelParam(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {
        //增值税率
        DataTaxRateAllocation allocationSales = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX);
        String salesTax = String.valueOf(allocationSales.getTaxRate());
        modelAndView.addObject("salesTax", salesTax);
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectPlanDetails.getProjectId());
        modelAndView.addObject("declareRecordList", declareRecordList);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.getSchemeAreaGroup(projectPlanDetails.getAreaId()));
        List<BaseDataDic> taxesBurdenList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_TAXES_BURDEN);
        modelAndView.addObject("taxesBurdenList", taxesBurdenList);
    }
}
