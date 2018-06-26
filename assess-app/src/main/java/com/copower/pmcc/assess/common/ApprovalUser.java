package com.copower.pmcc.assess.common;

import com.copower.pmcc.assess.dal.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.entity.ProjectMember;
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
    //尽量使用dao完成项目经理的获取，否则可能出现对象的循环引用
    @Autowired
    private ProjectMemberDao projectMemberDao;

    @Override
    public String project_manager(String currUserAccount, Integer projectId) {
        ProjectMember projectMemberItem = projectMemberDao.getProjectMemberItem(projectId);
        if(projectMemberItem!=null) return projectMemberItem.getUserAccountManager();
        return null;
    }
}
