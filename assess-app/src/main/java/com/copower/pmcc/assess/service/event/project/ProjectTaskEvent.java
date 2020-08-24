package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
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
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        String processInstanceId = processExecution.getProcessInstanceId();
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInstanceId);
        if (projectPlanDetails == null) return;
        if (projectPlanDetails.getBisRestart() == Boolean.FALSE) {//非重启任务
            projectPlanService.enterNextStage(projectPlanDetails.getPlanId()); //结束当前阶段进入下一阶段
        } else {
            projectPlanDetails.setBisRestart(false);
            projectPlanDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
        }
    }
}
