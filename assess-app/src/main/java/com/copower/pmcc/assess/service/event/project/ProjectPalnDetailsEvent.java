package com.copower.pmcc.assess.service.event.project;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/19
 * @time: 10:13
 */
@Component
public class ProjectPalnDetailsEvent extends BaseProcessEvent {
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws  Exception{
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsItemByProcessInsId(processExecution.getProcessInstanceId());
        projectPlanDetails.setStatus(processExecution.getProcessStatus().getValue());
        projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
    }
}
