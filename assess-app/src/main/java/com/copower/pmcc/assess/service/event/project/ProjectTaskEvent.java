package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/13
 * @time: 11:18
 */
@Component
public class ProjectTaskEvent extends BaseProcessEvent {
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        String processInstanceId = processExecution.getProcessInstanceId();
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInstanceId);
        if (projectPlanDetailsService.isAllPlanDetailsFinish(projectPlanDetails.getPlanId())) {
            //任务都执行则发起相应的整体复核流程
            projectTaskAllService.startTaskAllApproval(projectPlanDetails.getPlanId());
        }
    }
}
