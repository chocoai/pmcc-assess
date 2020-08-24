package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectChangeLogDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectStateChangeService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProjectStopChangeEvent extends BaseProcessEvent {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectStateChangeService simpleChangeService;
    @Autowired
    private ProjectChangeLogDao projectChangeLogDao;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        ProjectChangeLog data = simpleChangeService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        data.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectChangeLogDao.modifyProjectChangeLog(data);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(data.getProjectId());
        projectInfo.setProjectStatus(ProjectStatusEnum.CLOSE.getKey());
        projectInfoService.saveProjectInfo(projectInfo);
        //删除待提交任务
        bpmRpcProjectTaskService.deleteProjectTaskByProjectid(applicationConstant.getAppKey(),projectInfo.getId());
        //关闭流程
        bpmRpcActivitiProcessManageService.closeProcess(projectInfo.getProcessInsId());
    }
}
