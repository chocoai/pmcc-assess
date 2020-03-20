package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequent;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommission;
import com.copower.pmcc.assess.service.ProjectSubsequentService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectXlxCommissionService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProjectXlxCommissionEvent extends BaseProcessEvent {
    @Autowired
    private ProjectXlxCommissionService projectXlxCommissionService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        ProjectXlxCommission commission = projectXlxCommissionService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        commission.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectXlxCommissionService.editData(commission);

    }
}
