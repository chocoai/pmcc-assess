package com.copower.pmcc.assess.common;

import com.copower.pmcc.assess.dal.entity.ProjectMember;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.bpm.core.process.support.ApprovalUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/24
 * @time: 14:27
 */
@Component("approvalUsersService")
public class ApprovalUser extends ApprovalUsersService {
    @Autowired
    private ProjectMemberService projectMemberService;

    @Override
    public String project_manager(String currUserAccount, Integer projectId) {
        String projectManager = projectMemberService.getProjectManager(projectId);
        return projectManager;
    }
}
