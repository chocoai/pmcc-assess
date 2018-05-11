package com.copower.pmcc.assess.service.project.taks.assist;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectCheckContentService;
import com.copower.pmcc.assess.service.project.SurveyAssetInventoryService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


@Component
@WorkFlowAnnotation(desc = "资产清查类")
public class ProjectTaskSurveyAssist implements ProjectTaskInterface {
    @Autowired
    private ControllerComponent serviceComponent;

    @Autowired
    private ProjectCheckContentService projectCheckContentService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/survey/taskSurveyIndex", "", 0, "0", "");
        projectCheckContentService.getBaseDataDicList(modelAndView,projectPlanDetails);//获取数据字典
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/survey/taskSurveyApproval", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/survey/taskSurveyIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails,Integer boxId){
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/survey/taskSurveyApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        surveyAssetInventoryService.save(projectPlanDetails,processInsId,surveyAssetInventoryService.format(formData));
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
