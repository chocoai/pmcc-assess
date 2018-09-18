package com.copower.pmcc.assess.dal.basis.dao.project.manage;

import com.copower.pmcc.assess.dal.basis.entity.ProjectFollow;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFollowExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectFollowMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 13:05
 */
@Repository
public class ProjectFollowDao {
    @Autowired
    private ProjectFollowMapper projectFollowMapper;

    public Boolean addFollow(ProjectFollow projectFollow) {
        int i = projectFollowMapper.insertSelective(projectFollow);
        return i == 1;
    }

    public Boolean editFollow(ProjectFollow projectFollow) {
        int i = projectFollowMapper.updateByPrimaryKeySelective(projectFollow);
        return i>=0;

    }

    /**
     * 根据人员取得人员关注的项目信息
     *
     * @param userAccount
     * @return
     */
    public List<ProjectFollow> getProjectFollowByUserAccount(String userAccount) {
        ProjectFollowExample example = new ProjectFollowExample();
        example.createCriteria().andBisEnableEqualTo(true).andUserAccountEqualTo(userAccount);
        return projectFollowMapper.selectByExample(example);
    }



    /**
     * 根据人员和项目编号取得相应的项目关注信息
     *
     * @param userAccount
     * @param projectId
     * @return
     */
    public ProjectFollow getProjectFollowByUserAccountAndProjectId(String userAccount, Integer projectId) {
        ProjectFollowExample example = new ProjectFollowExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andUserAccountEqualTo(userAccount)
                .andBisEnableEqualTo(true);

        List<ProjectFollow> projectFollows = projectFollowMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectFollows)) {
            return projectFollows.get(0);
        }
        return null;
    }

}
