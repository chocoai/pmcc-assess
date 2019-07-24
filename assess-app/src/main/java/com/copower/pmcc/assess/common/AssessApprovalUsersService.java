package com.copower.pmcc.assess.common;

import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.bpm.core.process.support.ApprovalUsersService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2019-07-24
 * @time: 13:25
 */
public class AssessApprovalUsersService extends ApprovalUsersService {
    @Autowired
    private ProjectMemberService projectMemberService;

    public AssessApprovalUsersService(String appKey) {
        super(appKey);
    }

    @Override
    public String project_manager(String creator, String currUserAccount, Integer projectId) {
        String pm = "";

        ProjectMemberVo projectMember = projectMemberService.getProjectMember(projectId);
        if (projectMember != null) {
            pm = projectMember.getUserAccountManager();
        }

        return pm;
    }

    @Override
    public String project_member(String creator, String currUserAccount, Integer projectId) {
        String members = "";

        ProjectMemberVo projectMember = projectMemberService.getProjectMember(projectId);
        if (projectMember != null) {
            members = projectMember.getUserAccountMember();
        }

        return members;
    }
}