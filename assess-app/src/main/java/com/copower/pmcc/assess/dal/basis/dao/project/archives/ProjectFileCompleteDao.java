package com.copower.pmcc.assess.dal.basis.dao.project.archives;

import com.copower.pmcc.assess.dal.basis.entity.ProjectFileComplete;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFileCompleteExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectFileCompleteMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 */
@Repository
public class ProjectFileCompleteDao {

    @Autowired
    private ProjectFileCompleteMapper projectFileCompleteMapper;

    public ProjectFileComplete getProjectFileCompleteById(Integer id) {
        return projectFileCompleteMapper.selectByPrimaryKey(id);
    }

    public Integer addProjectFileComplete(ProjectFileComplete projectFileComplete) {
        projectFileCompleteMapper.insertSelective(projectFileComplete);
        return projectFileComplete.getId();
    }

    public boolean updateProjectFileComplete(ProjectFileComplete projectFileComplete, boolean updateNull) {
        return updateNull ? projectFileCompleteMapper.updateByPrimaryKey(projectFileComplete) == 1 : projectFileCompleteMapper.updateByPrimaryKeySelective(projectFileComplete) == 1;
    }

    public boolean deleteProjectFileComplete(Integer id) {
        return projectFileCompleteMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<ProjectFileComplete> getProjectFileCompleteIds(List<Integer> ids) {
        ProjectFileCompleteExample example = new ProjectFileCompleteExample();
        ProjectFileCompleteExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        criteria.andIdIn(ids);
        return projectFileCompleteMapper.selectByExample(example);
    }

    public List<ProjectFileComplete> getProjectFileCompleteList(ProjectFileComplete projectFileComplete) {
        ProjectFileCompleteExample example = new ProjectFileCompleteExample();
        ProjectFileCompleteExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        MybatisUtils.convertObj2Criteria(projectFileComplete, criteria);
        return projectFileCompleteMapper.selectByExample(example);
    }


}
