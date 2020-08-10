package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhaseExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectPhaseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/08/31 12:25
 */
@Repository
public class ProjectPhaseDao {
    @Autowired
    private ProjectPhaseMapper projectPhaseMapper;

    public List<ProjectPhase> getProjectPhaseByCategoryId(Integer categoryId) {
        ProjectPhaseExample example = new ProjectPhaseExample();
        example.createCriteria().andBisEnableEqualTo(true).andProjectCategoryIdEqualTo(categoryId);
        example.setOrderByClause("work_stage_id,phase_sort");
        return projectPhaseMapper.selectByExample(example);

    }

    public List<ProjectPhase> getProjectPhaseListByIds(List<Integer> ids) {
        ProjectPhaseExample example = new ProjectPhaseExample();
        ProjectPhaseExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (CollectionUtils.isNotEmpty(ids)) {
            criteria.andIdIn(ids);
        }
        example.setOrderByClause("work_stage_id,phase_sort");
        return projectPhaseMapper.selectByExample(example);

    }


    public ProjectPhase getProjectPhaseByCategoryId(Integer categoryId, String phaseKey) {
        ProjectPhaseExample example = new ProjectPhaseExample();
        example.createCriteria().andProjectCategoryIdEqualTo(categoryId).andPhaseKeyEqualTo(phaseKey);
        List<ProjectPhase> projectPhases = projectPhaseMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectPhases))
            return projectPhases.get(0);
        return null;
    }


    public ProjectPhase getProjectPhaseById(Integer id) {
        return projectPhaseMapper.selectByPrimaryKey(id);
    }

    public ProjectPhase getProjectPhaseByKey(String key) {
        ProjectPhaseExample example = new ProjectPhaseExample();
        example.createCriteria().andPhaseKeyEqualTo(key);
        List<ProjectPhase> projectPhases = projectPhaseMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectPhases))
            return projectPhases.get(0);
        return null;
    }

    public ProjectPhase getProjectPhaseByKey(String key, Integer categoryId) {
        ProjectPhaseExample example = new ProjectPhaseExample();
        example.createCriteria().andPhaseKeyEqualTo(key).andProjectCategoryIdEqualTo(categoryId);
        List<ProjectPhase> projectPhases = projectPhaseMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectPhases))
            return projectPhases.get(0);
        return null;
    }

    public boolean createProjectPhase(ProjectPhase projectPhase) {
        return projectPhaseMapper.insertSelective(projectPhase) == 1;
    }

    public boolean updateProjectPhaseById(ProjectPhase projectPhase) {
        return projectPhaseMapper.updateByPrimaryKeySelective(projectPhase) == 1;
    }


    public List<ProjectPhase> getCustomProjectPhase(ProjectPhase projectPhase) {

        ProjectPhaseExample example = new ProjectPhaseExample();
        ProjectPhaseExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(projectPhase.getProjectPhaseName())) {
            criteria.andProjectPhaseNameLike(projectPhase.getProjectPhaseName());
        }
        if (projectPhase.getProjectTypeId() != null) {
            criteria.andProjectTypeIdEqualTo(projectPhase.getProjectTypeId());
        }

        if (projectPhase.getProjectCategoryId() != null) {
            criteria.andProjectCategoryIdEqualTo(projectPhase.getProjectCategoryId());
        }
        example.setOrderByClause("work_stage_id,phase_sort");
        return projectPhaseMapper.selectByExample(example);
    }

    public List<ProjectPhase> getProjectPhaseList(ProjectPhase projectPhase) {
        ProjectPhaseExample example = new ProjectPhaseExample();
        MybatisUtils.convertObj2Example(projectPhase, example);
        return projectPhaseMapper.selectByExample(example);
    }

    /**
     * 获取
     *
     * @param workStageId
     * @return
     */
    public List<ProjectPhase> getProjectPhaseByWorkStageId(Integer workStageId) {
        ProjectPhaseExample example = new ProjectPhaseExample();
        example.createCriteria().andBisEnableEqualTo(true).andWorkStageIdEqualTo(workStageId);
        example.setOrderByClause("work_stage_id,phase_sort");
        return projectPhaseMapper.selectByExample(example);

    }
}
