package com.copower.pmcc.assess.service.project.taks.assist;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.event.project.SurveyLocaleExploreEvent;
import com.copower.pmcc.assess.service.project.survey.SurveyLocaleExploreService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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
@WorkFlowAnnotation(desc = "现场查勘成果")
public class ProjectTaskExploreAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyLocaleExploreService surveyLocaleExploreService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExploreIndex", "", 0, "0", "");
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentPlan = projectPlanDetailsDao.getProjectPlanDetailsItemById(id);
        modelAndView.addObject("parentPlan",parentPlan);
        //初始化查勘任务

        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExploreApproval", processInsId, boxId, taskId, agentUserAccount);
        //审批走这里
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentProject = projectPlanDetailsDao.getProjectPlanDetailsItemById(id);
        modelAndView.addObject("parentProject",parentProject);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExploreIndex", processInsId, boxId, taskId, agentUserAccount);
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentProject = projectPlanDetailsDao.getProjectPlanDetailsItemById(id);
        modelAndView.addObject("parentProject",parentProject);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExploreApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentProject = projectPlanDetailsDao.getProjectPlanDetailsItemById(id);
        modelAndView.addObject("parentProject",parentProject);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        SurveyLocaleExplore surveyLocaleExplore=new SurveyLocaleExplore();
        surveyLocaleExplore.setProjectId(projectPlanDetails.getProjectId());
        surveyLocaleExplore.setPlanDetailsId(projectPlanDetails.getId());
        surveyLocaleExplore.setProcessInsId(processInsId);
        surveyLocaleExplore.setCreator(commonService.thisUserAccount());
        surveyLocaleExplore.setDeclareRecordId(projectPlanDetails.getDeclareRecordId());
        bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SurveyLocaleExploreEvent.class.getSimpleName());//修改监听器
        surveyLocaleExploreService.save(surveyLocaleExplore);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
