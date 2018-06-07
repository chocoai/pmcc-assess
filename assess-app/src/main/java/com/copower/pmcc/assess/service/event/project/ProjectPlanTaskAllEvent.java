package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.entity.ProjectPlanTaskAll;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/10/17
 * @time: 17:41
 */
@Component
public class ProjectPlanTaskAllEvent extends BaseProcessEvent {

    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
            ProjectPlanTaskAll projectPlanTaskAll = projectTaskAllService.getObjectByProcessInsId(processExecution.getProcessInstanceId());
            //将下阶段设置为可编辑计划
            try {
                projectPlanService.updatePlanStatus(projectPlanTaskAll.getProjectPlanId());
            } catch (BusinessException e) {
                //
            }

        }
    }
}
