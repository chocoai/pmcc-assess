package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ProjectMember;
import com.copower.pmcc.assess.dal.entity.ProjectMemberExample;
import com.copower.pmcc.assess.dal.entity.ProjectMemberHistory;
import com.copower.pmcc.assess.dal.mapper.ProjectMemberHistoryMapper;
import com.copower.pmcc.assess.dal.mapper.ProjectMemberMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:00
 */
@Repository
public class ProjectMemberDao {
    @Autowired
    private ProjectMemberMapper projectMemberMapper;
    @Autowired
    private ProjectMemberHistoryMapper projectMemberHistoryMapper;


    public List<ProjectMember> getProjectMemberList(Integer projectId) {
        ProjectMemberExample example = new ProjectMemberExample();
        ProjectMemberExample.Criteria criteria = example.createCriteria().andProjectIdEqualTo(projectId)
                .andBisEnableEqualTo(true);
        List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(example);
        return projectMembers;
    }

    public ProjectMember getProjectMemberItem(Integer projectId) {
        ProjectMemberExample example = new ProjectMemberExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andBisEnableEqualTo(true);
        List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectMembers)) {
            return projectMembers.get(0);
        }
        return null;
    }

    public ProjectMember get(Integer id){
        ProjectMember projectMember = projectMemberMapper.selectByPrimaryKey(id);
        return projectMember;
    }

    public Boolean saveProjectMember(ProjectMember projectMember) {
        int i = projectMemberMapper.insertSelective(projectMember);
        return i == 1;
    }

    public int saveProjectMemeberID(ProjectMember projectMember){
        int i = projectMemberMapper.insertSelective(projectMember);
        return projectMember.getId();
    }

    public Boolean updateProjectMember(ProjectMember projectMember) {
        int i = projectMemberMapper.updateByPrimaryKey(projectMember);
        return i >= 0;
    }


    public Integer getUserAccountProjectCount(String userAccount, List<Integer> projectIds) {
        ProjectMemberExample example = new ProjectMemberExample();
        ProjectMemberExample.Criteria criteria1 = example.or().andUserAccountManagerLike(userAccount);
        ProjectMemberExample.Criteria criteria = example.or().andUserAccountMemberLike(userAccount);
        criteria1.andProjectIdIn(projectIds).andBisEnableEqualTo(true);
        criteria.andProjectIdIn(projectIds).andBisEnableEqualTo(true);
        return projectMemberMapper.countByExample(example);
    }

    public Boolean saveProjectMemberHistory(ProjectMemberHistory projectMemberHistory)
    {
        int i = projectMemberHistoryMapper.insertSelective(projectMemberHistory);
        return i==1;
    }

}
