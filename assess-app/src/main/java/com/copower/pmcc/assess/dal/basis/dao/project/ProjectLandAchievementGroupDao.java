package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectLandAchievementGroupExample;
import com.copower.pmcc.assess.dal.basis.entity.ProjectLandAchievementGroupWithBLOBs;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectLandAchievementGroupMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectLandAchievementGroupDao {

    @Autowired
    private ProjectLandAchievementGroupMapper mapper;


    public boolean insert(ProjectLandAchievementGroupWithBLOBs obj) {
        return mapper.insert(obj) == 1;
    }

    public boolean insertSelective(ProjectLandAchievementGroupWithBLOBs obj, ProjectLandAchievementGroupWithBLOBs.Column... selective) {
        return mapper.insertSelective(obj, selective) == 1;
    }

    public boolean updateByPrimaryKeyWithBLOBs(ProjectLandAchievementGroupWithBLOBs obj) {
        return mapper.updateByPrimaryKeyWithBLOBs(obj) == 1;
    }

    public boolean updateByPrimaryKeySelective(ProjectLandAchievementGroupWithBLOBs obj, ProjectLandAchievementGroupWithBLOBs.Column... selective) {
        return mapper.updateByPrimaryKeySelective(obj, selective) == 1;
    }

    public void batchInsert(List<ProjectLandAchievementGroupWithBLOBs> list) {
        mapper.batchInsert(list);
    }

    public ProjectLandAchievementGroupWithBLOBs getProjectLandAchievementGroupWithBLOBsById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void deleteProjectLandAchievementGroupByIds(List<Integer> ids) {
        ProjectLandAchievementGroupExample example = new ProjectLandAchievementGroupExample();
        ProjectLandAchievementGroupExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public void deleteProjectLandAchievementGroupByProjectId(Integer projectId) {
        ProjectLandAchievementGroupExample example = new ProjectLandAchievementGroupExample();
        ProjectLandAchievementGroupExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        mapper.deleteByExample(example);
    }

    public void deleteProjectLandAchievementGroupByDataTableIdAndDataTableName(Integer dataTableId, String dataTableName) {
        ProjectLandAchievementGroupExample example = new ProjectLandAchievementGroupExample();
        ProjectLandAchievementGroupExample.Criteria criteria = example.createCriteria();
        criteria.andDataTableIdEqualTo(dataTableId);
        criteria.andDataTableNameEqualTo(dataTableName);
        mapper.deleteByExample(example);
    }

    public List<ProjectLandAchievementGroupWithBLOBs> getProjectLandAchievementGroupByDataTableIdAndDataTableName(Integer dataTableId, String dataTableName) {
        ProjectLandAchievementGroupExample example = new ProjectLandAchievementGroupExample();
        ProjectLandAchievementGroupExample.Criteria criteria = example.createCriteria();
        criteria.andDataTableIdEqualTo(dataTableId);
        criteria.andDataTableNameEqualTo(dataTableName);
        return mapper.selectByExampleWithBLOBs(example);
    }

    public List<ProjectLandAchievementGroupWithBLOBs> getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(Integer projectId, Integer dataTableId, String dataTableName) {
        ProjectLandAchievementGroupExample example = new ProjectLandAchievementGroupExample();
        ProjectLandAchievementGroupExample.Criteria criteria = example.createCriteria();
        if (dataTableId != null) {
            criteria.andDataTableIdEqualTo(dataTableId);
        }
        if (StringUtils.isNotBlank(dataTableName)) {
            criteria.andDataTableNameEqualTo(dataTableName);
        }
        if (projectId != null) {
            criteria.andProjectIdEqualTo(projectId);
        }
        return mapper.selectByExampleWithBLOBs(example);
    }

    public List<ProjectLandAchievementGroupWithBLOBs> getProjectLandAchievementGroupByProjectId(Integer projectId) {
        ProjectLandAchievementGroupExample example = new ProjectLandAchievementGroupExample();
        ProjectLandAchievementGroupExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        return mapper.selectByExampleWithBLOBs(example);
    }

}
