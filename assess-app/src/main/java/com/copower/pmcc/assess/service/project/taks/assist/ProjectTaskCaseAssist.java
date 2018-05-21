package com.copower.pmcc.assess.service.project.taks.assist;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyCaseStudy;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.event.project.SurveyLocaleExploreEvent;
import com.copower.pmcc.assess.service.project.SurveyCaseStudyDetailService;
import com.copower.pmcc.assess.service.project.SurveyCaseStudyService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
@WorkFlowAnnotation(desc = "案例调查成果")
public class ProjectTaskCaseAssist implements ProjectTaskInterface {
    @Autowired
    private ControllerComponent serviceComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/case/taskCaseIndex", "", 0, "0", "");
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/case/taskCaseApproval", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/case/taskCaseIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails,Integer boxId){
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/case/taskCaseApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        SurveyCaseStudy surveyCaseStudy=new SurveyCaseStudy();
        surveyCaseStudy.setProjectId(projectPlanDetails.getProjectId());
        surveyCaseStudy.setPlanDetailsId(projectPlanDetails.getId());
        surveyCaseStudy.setProcessInsId(processInsId);
        surveyCaseStudy.setCreator(commonService.thisUserAccount());
        surveyCaseStudy.setDeclareRecordId(projectPlanDetails.getDeclareRecordId());
        bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SurveyLocaleExploreEvent.class.getSimpleName());//修改监听器
        surveyCaseStudyService.save(surveyCaseStudy);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
