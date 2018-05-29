package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetailsExample;
import com.copower.pmcc.assess.dal.mapper.ProjectPlanDetailsMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangpc on 2017/9/18.
 */
@Repository
public class ProjectPlanDetailsDao {
    @Autowired
    private ProjectPlanDetailsMapper projectPlanDetailsMapper;

    /**
     * 获取数据列表
     *
     * @param planId
     * @param projectId
     * @return
     */
    public List<ProjectPlanDetails> getListObject(Integer planId, Integer projectId) {
        if (planId == null || projectId == null)
            return null;
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andPlanIdEqualTo(planId);
        example.setOrderByClause("sorting");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    //项目计划明细
    public List<ProjectPlanDetails> getProjectPlanDetailsList(ProjectPlanDetails projectPlan) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.setOrderByClause("sorting,project_phase_id");
        MybatisUtils.convertObj2Example(projectPlan, example);
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public ProjectPlanDetails getProjectPlanDetailsItemById(Integer id) {
        return projectPlanDetailsMapper.selectByPrimaryKey(id);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByPlanId(Integer planId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPlanIdEqualTo(planId);
        example.setOrderByClause("sorting");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByPId(Integer pId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPidEqualTo(pId);
        example.setOrderByClause("sorting");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByDeclareId(List<Integer> declareRecordIds, Integer projectPhaseId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        ProjectPlanDetailsExample.Criteria criteria = example.createCriteria();
        criteria.andDeclareRecordIdIn(declareRecordIds);
        if (projectPhaseId != null) {
            criteria.andProjectPhaseIdEqualTo(projectPhaseId);
        }
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public ProjectPlanDetails getProjectPlanDetailsItemByProcessInsId(String processInsId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
            return projectPlanDetails.get(0);
        }
        return null;
    }

    public Integer getPlanChildCount(Integer pid, Integer projectId, Integer workStageId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPidEqualTo(pid).andProjectIdEqualTo(projectId).andProjectWorkStageIdEqualTo(workStageId);
        return projectPlanDetailsMapper.countByExample(example);
    }

    public Boolean addProjectPlanDetails(ProjectPlanDetails projectPlan) {
        int i = projectPlanDetailsMapper.insertSelective(projectPlan);
        return i == 1;
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByProjectid(Integer projectId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public Boolean updateProjectPlanDetails(ProjectPlanDetails projectPlan) {
        int i = projectPlanDetailsMapper.updateByPrimaryKeySelective(projectPlan);
        return i == 1;
    }

    //项目中心使用的方法
    public Integer getProjectDetailsCount(String userAccount, Date dates, Date datee) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andExecuteUserAccountEqualTo(userAccount).andPlanStartDateGreaterThanOrEqualTo(dates).andPlanEndDateLessThanOrEqualTo(datee);
        return projectPlanDetailsMapper.countByExample(example);
    }

    /**
     * 根据计划编号，取得计划所有的上子级计划
     *
     * @param planId
     * @return
     */
    public List<ProjectPlanDetails> getProjectPlanDetailsByPlanAndPid(Integer planId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPlanIdEqualTo(planId).andPidGreaterThan(0);
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsLastLayer(Integer planId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andBisEnableEqualTo(true).andPlanIdEqualTo(planId).andBisLastLayerEqualTo(true);
        example.setOrderByClause(" sorting");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public Boolean deleteProjectPlanDetails(Integer id) {
        return projectPlanDetailsMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByProjectIdAndName(Integer projectId, String name, Integer workStageId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andProjectPhaseNameEqualTo(name).andProjectWorkStageIdEqualTo(workStageId);
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByPid(Integer pid) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPidEqualTo(pid);
        return projectPlanDetailsMapper.selectByExample(example);
    }
}
