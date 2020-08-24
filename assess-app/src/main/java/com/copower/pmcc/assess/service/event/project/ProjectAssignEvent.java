package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/21
 * @time: 14:59
 */
@Component
public class ProjectAssignEvent extends BaseProcessEvent {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        ProjectInfo projectInfo=new ProjectInfo();
        projectInfo.setAssignProcessInsId(processExecution.getProcessInstanceId());
        List<ProjectInfo> projectInfoList = projectInfoService.getProjectInfoList(projectInfo);
        if(CollectionUtils.isNotEmpty(projectInfoList)) {
            ProjectInfo projectInfo1 = projectInfoList.get(0);
            projectInfo1.setAssignStatus(processExecution.getProcessStatus().getValue());
            projectInfo1.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            projectInfoService.updateProjectInfo(projectInfo1);
        }
    }
}
