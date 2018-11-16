package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectChangeLogDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMemberHistory;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.manage.ProjectMemberChangeService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Red
 * @data: 2018/2/6
 * @time: 10:53
 */
@Component
public class ProjectMemberChangeProcessEvent extends BaseProcessEvent {
    @Autowired
    private ProjectMemberChangeService projectMemberChangeService;
    @Autowired
    private ProjectChangeLogDao projectChangeLogDao;
    @Autowired
    private ProjectMemberDao projectMemberDao;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);

        if (StringUtils.equals(processExecution.getProcessStatus().getValue(), ProcessStatusEnum.FINISH.getValue())) {
            ProjectChangeLog costsProjectChangeLog = projectMemberChangeService.getProjectChangeLog(processExecution.getProcessInstanceId());
            String newRecord = costsProjectChangeLog.getNewRecord();
            String oldRecord = costsProjectChangeLog.getOldRecord();
            ProjectMember newMember = JSON.parseObject(newRecord, ProjectMember.class);
            ProjectMember oldMember = JSON.parseObject(oldRecord, ProjectMember.class);

            //更新项目成员
            projectMemberDao.updateProjectMember(newMember);


            //记录历史信息
            ProjectMemberHistory projectMemberHistory = new ProjectMemberHistory();
            projectMemberHistory.setProjectId(costsProjectChangeLog.getProjectId());
            projectMemberHistory.setUserAccountManagerOld(oldMember.getUserAccountManager());
            projectMemberHistory.setUserAccountManagerNew(newMember.getUserAccountManager());
            //成员变更
            projectMemberHistory.setUserAccountMemberOld(oldMember.getUserAccountMember());
            projectMemberHistory.setUserAccountMemberNew(newMember.getUserAccountMember());
            projectMemberHistory.setCreator(costsProjectChangeLog.getCreator());
            projectMemberDao.saveProjectMemberHistory(projectMemberHistory);
            //ok

            //更新log状态
            costsProjectChangeLog.setStatus(processExecution.getProcessStatus().getValue());
            projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);
        }
    }



}
