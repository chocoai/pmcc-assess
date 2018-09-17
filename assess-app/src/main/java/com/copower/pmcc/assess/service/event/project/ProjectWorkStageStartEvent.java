package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectWorkStageRestartDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStageRestart;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.manage.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/10/31
 * @time: 14:24
 */
@Component
public class ProjectWorkStageStartEvent extends BaseProcessEvent {
    @Autowired
    private ProjectWorkStageRestartDao projectWorkStageRestartDao;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
            //如果审批结束，则启用相应的计划
            String processInsId = processExecution.getProcessInstanceId();
            ProjectWorkStageRestart projectWorkStageRestart = projectWorkStageRestartDao.getProjectWorkStageRestartItem(processInsId);
            ProjectPlan projectPlan = projectPlanService.RestartPlan(projectWorkStageRestart);
            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectWorkStageRestart.getProjectRestartStageId());
            projectWorkStageRestart.setProjectPlanId(projectPlan.getId());
            projectWorkStageRestart.setProjectRestartStageName(projectWorkStage.getWorkStageName());
            projectWorkStageRestartDao.editProjectWorkStageRestart(projectWorkStageRestart);

        }
    }


}
