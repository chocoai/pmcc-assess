package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.manage.ProjectPlanHistoryDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanHistory;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/28
 * @time: 9:51
 */
@Component
public class ProjectPlanHistoryEvent extends BaseProcessEvent {
    @Autowired
    private ProjectPlanHistoryDao projectPlanHistoryDao;
    @Autowired
    private ProjectPlanDao projectPlanDao;

    @Override
    public void processFinishExecute(ProcessExecution processExecution)throws  Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        String processInsId = processExecution.getProcessInstanceId();
        ProjectPlanHistory projectPlanHistory = new ProjectPlanHistory();
        projectPlanHistory.setStatus(processExecution.getProcessStatus().getValue());
        projectPlanHistoryDao.editPlanHistoryByProcessInsId(processInsId, projectPlanHistory);
        //审批流程结束则更新相应的项目计划
        List<ProjectPlanHistory> projectPlanHistories = projectPlanHistoryDao.getProjectPlanHistoryByProcessInsId(processInsId);
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {

            List<ProjectPlan> projectPlans = projectPlanDao.getProjectplanByProjectIds(Lists.newArrayList(projectPlanHistories.get(0).getProjectId()));
            for (ProjectPlanHistory item : projectPlanHistories) {
                item.setStatus(ProcessStatusEnum.FINISH.getValue());
                projectPlanHistoryDao.editPlanHistory(item);
                for (ProjectPlan plan : projectPlans) {
                    if (plan.getId().equals(item.getPlanId())) {
                        plan.setProjectPlanStart(item.getAfterPlanStart());
                        plan.setProjectPlanEnd(item.getAfterPlanEnd());
                        plan.setPlanRemarks(item.getAfterPlanRemarks());
                        projectPlanDao.updateProjectPlan(plan);
                    }
                }
            }
        } else {
            for (ProjectPlanHistory item : projectPlanHistories) {
                item.setStatus(ProcessStatusEnum.FINISH.getValue());
                projectPlanHistoryDao.editPlanHistory(item);
            }
        }
    }
}
