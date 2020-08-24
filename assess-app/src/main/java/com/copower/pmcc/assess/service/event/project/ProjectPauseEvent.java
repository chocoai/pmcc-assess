package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectSuspendDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSuspend;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:58
 */
@Component
public class ProjectPauseEvent extends BaseProcessEvent {
    @Autowired
    private ProjectSuspendDao projectSuspendDao;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ApplicationConstant applicationConstant;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        ProjectSuspend projectSuspend = projectSuspendDao.getProjectSuspendProcessInsId(processExecution.getProcessInstanceId());
        ProjectInfo projectInfo = projectInfoDao.getProjectInfoById(projectSuspend.getProjectId());
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
            projectSuspend.setSupendDate(new Date());
            projectSuspendDao.editSuspend(projectSuspend);
            projectInfo.setProjectStatus(ProjectStatusEnum.PAUSE.getKey());
            projectInfoDao.updateProjectInfo(projectInfo);

        }
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.CLOSE)) {
            List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectInfo.getId()), ProjectStatusEnum.PAUSE.getName());
            projectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());
            projectInfoDao.updateProjectInfo(projectInfo);
            //如果取消终止，则恢复相应的计划或工作任务
            if (CollectionUtils.isNotEmpty(projectPlans)) {
                for (ProjectPlan item : projectPlans) {
                    item.setProjectStatus(ProjectStatusEnum.PLAN.getKey());
                    projectPlanDao.updateProjectPlan(item);
                }
            }
            bpmRpcProjectTaskService.updateProjectTaskRestart(applicationConstant.getAppKey(),projectInfo.getId());
        }
    }
}
