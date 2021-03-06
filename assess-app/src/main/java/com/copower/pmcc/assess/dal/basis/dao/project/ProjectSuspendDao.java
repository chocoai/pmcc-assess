package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSuspend;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSuspendExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectSuspendMapper;
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
public class ProjectSuspendDao {
    @Autowired
    private ProjectSuspendMapper projectSuspendMapper;

    public Boolean addSuspend(ProjectSuspend projectSuspend) {
        int i = projectSuspendMapper.insertSelective(projectSuspend);
        return i == 1;
    }

    public ProjectSuspend getProjectSuspendById(Integer id)
    {
        return projectSuspendMapper.selectByPrimaryKey(id);
    }
    public Boolean editSuspend(ProjectSuspend projectSuspend) {
        int i = projectSuspendMapper.updateByPrimaryKeySelective(projectSuspend);
        return i >= 0;
    }


    public ProjectSuspend getProjectSuspend(Integer projectId,String status) {
        ProjectSuspendExample example = new ProjectSuspendExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andStatusEqualTo(status);
        List<ProjectSuspend> projectSuspends = projectSuspendMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectSuspends)) {
            return projectSuspends.get(0);
        }
        return null;
    }

    /**
     * 所有申请了暂停的记录
     * @param projectId
     * @return
     */
    public List<ProjectSuspend> getProjectSuspendHistory(Integer projectId) {
        ProjectSuspendExample example = new ProjectSuspendExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return projectSuspendMapper.selectByExample(example);
    }



    public ProjectSuspend getProjectSuspendProcessInsId(String processInsId) {
        ProjectSuspendExample example = new ProjectSuspendExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectSuspend> projectSuspends = projectSuspendMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectSuspends)) {
            return projectSuspends.get(0);
        }
        return null;

    }
}
