package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysis;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePrice;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

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
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskLiquidationAnalysisIndex", "", 0, "0", "");
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByPlanDetailsId(projectPlanDetails.getId());
        if (schemeLiquidationAnalysis == null) {
            schemeLiquidationAnalysis = new SchemeLiquidationAnalysis();
            schemeLiquidationAnalysis.setProjectId(projectPlanDetails.getProjectId());
            schemeLiquidationAnalysis.setPlanDetailsId(projectPlanDetails.getId());
            schemeLiquidationAnalysis.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeLiquidationAnalysisService.saveLiquidationAnalysis(schemeLiquidationAnalysis);
        }
        modelAndView.addObject("master",  schemeLiquidationAnalysis);
        modelAndView.addObject("judgeObjectName", projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid()).getProjectPhaseName());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        modelAndView.addObject("judgeObject", judgeObject);
        SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("schemeSurePrice", schemeSurePrice);
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
        modelAndView.addObject("judgeObjectName", projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid()).getProjectPhaseName());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        modelAndView.addObject("judgeObject", judgeObject);
        SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("schemeSurePrice", schemeSurePrice);
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
        modelAndView.addObject("judgeObjectName", projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid()).getProjectPhaseName());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        modelAndView.addObject("judgeObject", judgeObject);
        SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("schemeSurePrice", schemeSurePrice);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskLiquidationAnalysisApproval",projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", schemeLiquidationAnalysis);
        modelAndView.addObject("judgeObjectName", projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid()).getProjectPhaseName());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        modelAndView.addObject("judgeObject", judgeObject);
        SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("schemeSurePrice", schemeSurePrice);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeLiquidationAnalysisService.commit(formData, projectPlanDetails, processInsId);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeLiquidationAnalysisService.commit(formData, projectPlanDetails, processInsId);
    }
}
