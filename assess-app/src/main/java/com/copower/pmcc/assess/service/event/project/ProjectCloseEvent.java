package com.copower.pmcc.assess.service.event.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.entity.ProjectClose;
import com.copower.pmcc.assess.dal.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectCloseService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/6
 * @time: 15:27
 */
@Component
public class ProjectCloseEvent extends BaseProcessEvent {
    @Autowired
    private ProjectCloseService projectCloseService;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        ProjectClose projectClose = projectCloseService.getProjectClose(processExecution.getProcessInstanceId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectClose.getProjectId());
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectInfo.getId()), ProjectStatusEnum.PAUSE.getName());
               if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
                  projectCloseService.CloseProjectAndTask(projectInfo, projectPlans);

               } else {
            //如果取消终止，则恢复相应的计划或工作任务
            if (CollectionUtils.isNotEmpty(projectPlans)) {
                for (ProjectPlan item : projectPlans) {
                    item.setProjectStatus(ProjectStatusEnum.PLAN.getName());
                    projectPlanDao.updateProjectPlan(item);
                }
            }
            bpmRpcProjectTaskService.updateProjectTaskRestart(projectInfo.getId());
        }
    }


}
