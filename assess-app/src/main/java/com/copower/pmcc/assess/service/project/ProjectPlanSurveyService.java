package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.dao.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ProjectPlanSurveyService {

    @Autowired
    private DeclareRecordDao declareRecordDao;

    @Autowired
    private ProjectPhaseDao projectPhaseDao;

    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;


    public ModelAndView getProjectPlanSurvey(ModelAndView modelAndView, ProjectPlan projectPlan){
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
                    projectPlanDetails.setBisLastLayer(false);
                    projectPlanDetails.setSorting(i++);
                    projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
                }

                List<ProjectPlanDetails> projectPlanDetailsss = projectPlanDetailsDao.getProjectPlanDetailsByProjectIdAndName(projectId, name, workStageId);
                int j = 1;
                Integer pid = 0;
                for(ProjectPlanDetails projectPlanDetails : projectPlanDetailsss){
                    pid = projectPlanDetails.getId();

                    String projectPhaseName = "";
                    Integer projectPhaseId = 0;
                    for(ProjectPhase projectPhase : projectPhases){
                        projectPhaseName = projectPhase.getProjectPhaseName();
                        projectPhaseId = projectPhase.getId();

                        ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                        projectPlanDetail.setProjectWorkStageId(workStageId);
                        projectPlanDetail.setPlanId(planId);
                        projectPlanDetail.setProjectId(projectId);
                        projectPlanDetail.setProjectPhaseName(projectPhaseName);
                        projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        projectPlanDetail.setPid(pid);
                        projectPlanDetail.setFirstPid(pid);
                        projectPlanDetail.setProjectPhaseId(projectPhaseId);
                        projectPlanDetail.setSorting(j++);
                        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
                    }

                }

            }
            return modelAndView;

        }
    }


}
