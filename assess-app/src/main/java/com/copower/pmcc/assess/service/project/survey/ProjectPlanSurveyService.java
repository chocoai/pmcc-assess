package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectPlanSurveyService {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    /**
     * 初始化阶段任务
     *
     * @param projectPlan
     */
    @Transactional(rollbackFor = Exception.class)
    public void initPlanDetails(ProjectPlan projectPlan) {
        //判断该阶段是否已有任务
        List<ProjectPlanDetailsVo> planDetailsVoList = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(projectPlan.getId());
        if (CollectionUtils.isNotEmpty(planDetailsVoList)) return;

        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();
        List<ProjectPhase> projectPhases = projectPhaseService.getCacheProjectPhaseByCategoryId(projectPlan.getCategoryId(), workStageId);
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        //案例调查任务与项目挂钩
        ProjectPhase caseProjectPhase = null;
        if (CollectionUtils.isEmpty(projectPhases)) return;
        List<ProjectPhase> filter = LangUtils.filter(projectPhases, o -> StringUtils.equals(o.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY));
        if (CollectionUtils.isNotEmpty(filter)) {
            projectPhases.removeAll(filter);
            caseProjectPhase = filter.get(0);
            ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
            projectPlanDetail.setProjectWorkStageId(workStageId);
            projectPlanDetail.setPlanId(planId);
            projectPlanDetail.setProjectId(projectId);
            projectPlanDetail.setProjectPhaseName(caseProjectPhase.getProjectPhaseName());
            projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            projectPlanDetail.setPlanHours(caseProjectPhase.getPhaseTime());
            projectPlanDetail.setPid(0);
            projectPlanDetail.setFirstPid(0);
            projectPlanDetail.setProjectPhaseId(caseProjectPhase.getId());
            projectPlanDetail.setSorting(0);
            projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
        }
        int i = 1;
        ProjectPlanDetails projectPlanDetails = null;
        for (DeclareRecord declareRecord : declareRecords) {
            projectPlanDetails = new ProjectPlanDetails();
            projectPlanDetails.setProjectWorkStageId(workStageId);
            projectPlanDetails.setPlanId(planId);
            projectPlanDetails.setProjectId(projectId);
            projectPlanDetails.setProjectPhaseName(declareRecord.getName());
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
                projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetail.setPlanHours(projectPhase.getPhaseTime());
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
