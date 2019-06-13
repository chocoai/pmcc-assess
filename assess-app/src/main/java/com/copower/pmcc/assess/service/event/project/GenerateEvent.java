package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/2/6
 * @time: 10:53
 */
@Component
public class GenerateEvent extends BaseProcessEvent {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        //先不关闭项目
//        ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
//        switch (processStatusEnum) {
//            case FINISH:
//                ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processExecution.getProcessInstanceId());
//                projectPlan.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
//                projectPlan.setFinishDate(new Date());
//                projectPlanService.updateProjectPlan(projectPlan);
//
//                ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
//                projectInfo.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
//                projectInfoService.updateProjectInfo(projectInfo);
//                break;
//        }
    }

}
