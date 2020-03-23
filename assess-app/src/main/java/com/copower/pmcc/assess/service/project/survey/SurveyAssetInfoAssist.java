package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zch
 * @see com.copower.pmcc.assess.proxy.face.ProjectTaskInterface
 */

@Component
@WorkFlowAnnotation(desc = "新资产清查成果")
public class SurveyAssetInfoAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    private final String applyViewName = "/project/stageSurvey/taskSurveyAssetInfoIndex";
    private final String approvalViewName = "/project/stageSurvey/taskSurveyAssetInfoApproval";

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(applyViewName, "", 0, "0", "");

        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(approvalViewName, processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(applyViewName, processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(approvalViewName, projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }


    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
    }

    private void setModelViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
    }

}
