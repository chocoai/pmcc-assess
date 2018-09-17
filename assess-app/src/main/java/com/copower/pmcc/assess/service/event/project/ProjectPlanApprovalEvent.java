package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.manage.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/19
 * @time: 10:11
 */
@Component
public class ProjectPlanApprovalEvent extends BaseProcessEvent {

    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);

        ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processExecution.getProcessInstanceId());
        projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getName());
        projectPlanService.updateProjectPlan(projectPlan);
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsLastLayer(projectPlan.getId(), ProcessStatusEnum.NOPROCESS.getValue());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        for (ProjectPlanDetails item : projectPlanDetails) {
            projectPlanService.saveProjectPlanDetailsResponsibility(item, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
        }
    }
}
