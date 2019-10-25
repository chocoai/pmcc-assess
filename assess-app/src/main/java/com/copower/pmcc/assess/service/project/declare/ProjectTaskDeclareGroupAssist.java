package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "申报分组成果")
public class ProjectTaskDeclareGroupAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareGroupIndex", "", 0, "0", "");
        setParams(modelAndView,projectPlanDetails) ;
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareGroupApproval", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView,projectPlanDetails) ;
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareGroupIndex", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView,projectPlanDetails) ;
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareGroupApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setParams(modelAndView,projectPlanDetails) ;
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

    private void setParams(ModelAndView modelAndView,ProjectPlanDetails projectPlanDetails){
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()),projectPlanDetails) ;
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlan.class.getSimpleName()), projectPlanService.getProjectplanById(projectPlanDetails.getPlanId())) ;
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()))) ;
    }

}
