package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zch on 2019-11-7.
 */
@Component
public class ProgrammeProcessEvent extends BaseProcessEvent {
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;

    @Override
    public void processFinishExecuteExtend(ProcessExecution processExecution, Date executeDate, String beans) {
        super.processFinishExecuteExtend(processExecution, executeDate, beans);
    }


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        //

        ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processExecution.getProcessInstanceId());
        if (projectPlan == null){
            return;
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        //更新项目状态
//        projectPlan.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());
//        projectPlanService.updateProjectPlan(projectPlan) ;
        schemeJudgeObjectService.submitProgrammeHandle(projectInfo,projectPlan,projectWorkStage) ;

    }
}
