package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectPlanCount;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectPlanDetailCount;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomProjectMapper;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectPlanChildrenMapper;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectPlanMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/8
 * @time: 17:28
 */
@Repository
public class ProjectPlanDao {
    @Autowired
    private ProjectPlanMapper projectPlanMapper;
    @Autowired
    private CustomProjectMapper customProjectMapper;

    //项目计划

    public List<ProjectPlan> getProjectPlanList(Integer projectId) {
        ProjectPlanExample example = new ProjectPlanExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        example.setOrderByClause(" stage_sort");
        return projectPlanMapper.selectByExample(example);
    }

    public List<ProjectPlan> getProjectPlanList2(Integer projectId,Integer workStageId , Integer categoryId) {
        ProjectPlanExample example = new ProjectPlanExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andWorkStageIdEqualTo(workStageId).andCategoryIdEqualTo(categoryId);
        example.setOrderByClause(" stage_sort");
        return projectPlanMapper.selectByExample(example);
    }


    public List<ProjectPlan> getProjectPlan(ProjectPlan projectPlan) {
        ProjectPlanExample example = new ProjectPlanExample();
        MybatisUtils.convertObj2Example(projectPlan, example);
        return projectPlanMapper.selectByExample(example);
    }

    public ProjectPlan getProjectPlanById(Integer id) {
        return projectPlanMapper.selectByPrimaryKey(id);
    }

    public List<ProjectPlan> getProjectplanByProjectIds(List<Integer> integers) {
        ProjectPlanExample example = new ProjectPlanExample();
        example.createCriteria().andProjectIdIn(integers);
        example.setOrderByClause(" stage_sort");
        return projectPlanMapper.selectByExample(example);
    }

    public Boolean addProjectPlan(ProjectPlan projectPlan) {
        int i = projectPlanMapper.insertSelective(projectPlan);
        return i == 1;
    }

    public Boolean updateProjectPlan(ProjectPlan projectPlan) {
        int i = projectPlanMapper.updateByPrimaryKeySelective(projectPlan);
        return i >= 1;
    }

    public ProjectPlan getProjectPlanByProcessInsId(String processInsId) {
        ProjectPlanExample example = new ProjectPlanExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectPlans)) {
            return projectPlans.get(0);
        }
        return null;
    }

    //根据状态取得所有项目阶段信息

    public List<ProjectPlan> getProjectPlanByStatus(List<Integer> projectIds, String status) {
        ProjectPlanExample example = new ProjectPlanExample();
        ProjectPlanExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" stage_sort");
        if (StringUtils.isNotBlank(status)) {
            criteria.andProjectStatusEqualTo(status);
        }
        if (CollectionUtils.isNotEmpty(projectIds)) {
            criteria.andProjectIdIn(projectIds);
        }
        return projectPlanMapper.selectByExample(example);
    }

    public List<CustomProjectPlanCount> getPlanCountByMonth(String startMonth, String endMonth){
        return customProjectMapper.getPlanCountByMonth(startMonth, endMonth);
    }
}
