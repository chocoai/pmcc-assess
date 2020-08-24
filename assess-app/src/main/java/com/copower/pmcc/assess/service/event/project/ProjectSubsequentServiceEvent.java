package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequent;
import com.copower.pmcc.assess.service.ProjectSubsequentService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProjectSubsequentServiceEvent extends BaseProcessEvent {
    @Autowired
    private ProjectSubsequentService projectSubsequentService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        ProjectSubsequent data = projectSubsequentService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        data.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectSubsequentService.editData(data);
    }
}
