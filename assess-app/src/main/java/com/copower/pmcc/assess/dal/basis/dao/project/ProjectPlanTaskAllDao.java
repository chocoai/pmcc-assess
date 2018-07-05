package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanTaskAll;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanTaskAllExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectPlanTaskAllMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/10/17
 * @time: 17:21
 */
@Repository
public class ProjectPlanTaskAllDao {
    @Autowired
    private ProjectPlanTaskAllMapper projectPlanTaskAllMapper;

    public Boolean addObject(ProjectPlanTaskAll projectPlanTaskAll) {
        int i = projectPlanTaskAllMapper.insertSelective(projectPlanTaskAll);
        return i == 1;
    }

    public Boolean updateObject(ProjectPlanTaskAll projectPlanTaskAll) {
        int i = projectPlanTaskAllMapper.updateByPrimaryKeySelective(projectPlanTaskAll);
        return i >= 0;
    }

    public ProjectPlanTaskAll getObjectByProcessInsId(String processInsId) {
        ProjectPlanTaskAllExample example = new ProjectPlanTaskAllExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectPlanTaskAll> projectPlanTaskAlls = projectPlanTaskAllMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectPlanTaskAlls)) {
            return projectPlanTaskAlls.get(0);
        }
        return null;
    }

    public ProjectPlanTaskAll getObjectById(Integer id) {
        return projectPlanTaskAllMapper.selectByPrimaryKey(id);
    }

    public List<ProjectPlanTaskAll> getObjectByProjectid(Integer projectid) {
        ProjectPlanTaskAllExample example = new ProjectPlanTaskAllExample();
        example.createCriteria().andProjectIdEqualTo(projectid);
        return projectPlanTaskAllMapper.selectByExample(example);
    }

}
