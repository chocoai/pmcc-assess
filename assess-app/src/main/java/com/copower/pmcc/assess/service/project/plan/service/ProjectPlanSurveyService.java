package com.copower.pmcc.assess.service.project.plan.service;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    public ModelAndView getProjectPlanSurvey(ModelAndView modelAndView, ProjectPlan projectPlan) {
        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();

        List<ProjectPhase> projectPhases = projectPhaseDao.getProjectPhase(workStageId);
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordByProjectId(projectId);

        ProjectPlanDetails projectPlanDetails=new ProjectPlanDetails();
        projectPlanDetails.setProjectId(projectId);
        projectPlanDetails.setPlanId(planId);
        List<ProjectPlanDetails> planDetails = projectPlanDetailsDao.getListObject(projectPlanDetails);

        if (declareRecords.size() == 0) {
            return modelAndView;
        }
        if (CollectionUtils.isNotEmpty(planDetails)) {
            return modelAndView;
        }
        //初始化计划数据
        initPlanDetails(planId, projectId, workStageId, projectPhases, declareRecords);
        return modelAndView;
    }

    @Transactional
    private void initPlanDetails(Integer planId, Integer projectId, Integer workStageId, List<ProjectPhase> projectPhases, List<DeclareRecord> declareRecords) {
        String name = "";
        int i = 1;
        for (DeclareRecord declareRecord : declareRecords) {
            name = declareRecord.getName();

            ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
            projectPlanDetails.setProjectWorkStageId(workStageId);
            projectPlanDetails.setPlanId(planId);
            projectPlanDetails.setProjectId(projectId);
            projectPlanDetails.setProjectPhaseName(name);
            projectPlanDetails.setDeclareRecordId(declareRecord.getId());
            projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            projectPlanDetails.setBisLastLayer(false);
            projectPlanDetails.setSorting(i++);
            projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

            int j = 0;
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                projectPlanDetail.setProjectWorkStageId(workStageId);
                projectPlanDetail.setPlanId(planId);
                projectPlanDetail.setProjectId(projectId);
                projectPlanDetail.setProjectPhaseName(projectPhase.getProjectPhaseName());
                projectPlanDetail.setStatus(ProcessStatusEnum.RUN.getValue());
                projectPlanDetail.setPid(projectPlanDetails.getId());
                projectPlanDetail.setDeclareRecordId(declareRecord.getId());
                projectPlanDetail.setFirstPid(projectPlanDetails.getId());
                projectPlanDetail.setProjectPhaseId(projectPhase.getId());
                projectPlanDetail.setSorting(j++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
            }
        }
    }
}
