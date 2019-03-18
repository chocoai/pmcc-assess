package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysis;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
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
            schemeLiquidationAnalysisService.initTaxAllocation(projectPlanDetails.getAreaId(), projectPlanDetails.getId());
        }
        modelAndView.addObject("master", schemeLiquidationAnalysis);
        return modelAndView;
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
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
        List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(projectPlanDetails.getAreaId());
        BigDecimal groupArea = new BigDecimal("0");
        BigDecimal groupPrice = new BigDecimal("0");
        //应该获取最终测算好的价格与面积
        if (CollectionUtils.isNotEmpty(judgeObjects)) {
            for (SchemeJudgeObject judgeObject : judgeObjects) {
                groupArea = groupArea.add(judgeObject.getEvaluationArea());

            }
        }
    }
}
