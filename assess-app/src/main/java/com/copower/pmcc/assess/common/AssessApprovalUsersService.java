package com.copower.pmcc.assess.common;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;
import com.copower.pmcc.bpm.core.process.support.ApprovalUsersService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2019-07-24
 * @time: 13:25
 */
public class AssessApprovalUsersService extends ApprovalUsersService {
    @Autowired
    private ProjectMemberDao projectMemberDao;

    public AssessApprovalUsersService(String appKey) {
        super(appKey);
    }

    @Override
    public String project_manager(String creator, String currUserAccount, Integer projectId) {
        String pm = "";

        List<ProjectMember> projectMemberList = projectMemberDao.getProjectMemberList(projectId);

        if (CollectionUtils.isNotEmpty(projectMemberList)) {
            List<String> transform = LangUtils.transform(projectMemberList, o -> o.getUserAccountManager());
            pm = FormatUtils.transformListString(transform);
        }

        return pm;
    }

    @Override
    public String project_member(String creator, String currUserAccount, Integer projectId) {
        String members = "";

        List<ProjectMember> projectMemberList = projectMemberDao.getProjectMemberList(projectId);

        if (CollectionUtils.isNotEmpty(projectMemberList)) {
            List<String> transform = LangUtils.transform(projectMemberList, o -> o.getUserAccountMember());
            members = FormatUtils.transformListString(transform);
        }

        return members;
    }
}