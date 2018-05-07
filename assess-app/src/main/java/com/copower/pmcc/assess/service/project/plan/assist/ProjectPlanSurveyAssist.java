package com.copower.pmcc.assess.service.project.plan.assist;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.dao.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:45
 */
@Component
@WorkFlowAnnotation(desc = "现场查勘计划设置类")
public class ProjectPlanSurveyAssist implements ProjectPlanInterface {
    @Autowired
    private ControllerComponent serviceComponent;

    @Autowired
    private DeclareRecordDao declareRecordDao;

    @Autowired
    private ProjectPhaseDao projectPhaseDao;

    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;

    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSurveyIndex", "", 0, "-1", "");
            getProjectPlanSurvey(modelAndView,projectPlan);
            return modelAndView;
        }




    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSurveyApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSurveyIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSurveyApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }


    public ModelAndView getProjectPlanSurvey(ModelAndView modelAndView,ProjectPlan projectPlan){
        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();


        List<ProjectPhase> projectPhases = projectPhaseDao.getProjectPhase(workStageId);
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordByProjectId(projectId);
        ;
        if (declareRecords.size() == 0) {
            return modelAndView;
        } else {
            String name = "";
            int i = 1;
            for (DeclareRecord declareRecord : declareRecords) {
                name = declareRecord.getName();

                List<ProjectPlanDetails> projectPlanDetailss = projectPlanDetailsDao.getProjectPlanDetailsByProjectIdAndName(projectId, name, workStageId);
                if (projectPlanDetailss != null && projectPlanDetailss.size() > 0) {
                    return modelAndView;
                }else{
                    ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                    projectPlanDetails.setProjectWorkStageId(workStageId);
                    projectPlanDetails.setPlanId(planId);
                    projectPlanDetails.setProjectId(projectId);
                    projectPlanDetails.setProjectPhaseName(name);
                    projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                    projectPlanDetails.setSorting(i++);
                    projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
                }

                List<ProjectPlanDetails> projectPlanDetailsss = projectPlanDetailsDao.getProjectPlanDetailsByProjectIdAndName(projectId, name, workStageId);
                int j = 1;
                Integer pid = 0;
                for(ProjectPlanDetails projectPlanDetails : projectPlanDetailsss){
                    pid = projectPlanDetails.getId();

                    String projectPhaseName = "";
                    for(ProjectPhase projectPhase : projectPhases){
                        projectPhaseName = projectPhase.getProjectPhaseName();

                        ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                        projectPlanDetail.setProjectWorkStageId(workStageId);
                        projectPlanDetail.setPlanId(planId);
                        projectPlanDetail.setProjectId(projectId);
                        projectPlanDetail.setProjectPhaseName(projectPhaseName);
                        projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        projectPlanDetail.setPid(pid);
                        projectPlanDetail.setSorting(j++);
                        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
                }

                }

            }
            return modelAndView;

        }
    }
}
