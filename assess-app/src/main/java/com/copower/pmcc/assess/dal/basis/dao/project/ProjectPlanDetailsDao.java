package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetailsExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectPlanDetailsMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
     * @return
     */
    public List<ProjectPlanDetails> getListObject(ProjectPlanDetails projectPlanDetails) {

        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        MybatisUtils.convertObj2Example(projectPlanDetails, example);
        example.setOrderByClause("sorting,project_phase_id");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsList(Integer planId, Integer pid, String search) {

        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();

        ProjectPlanDetailsExample.Criteria criteria = example.createCriteria().andPlanIdEqualTo(planId);
        if (pid != null) {
            criteria.andPidEqualTo(pid);
        }
        if (StringUtils.isNotBlank(search)) {
            criteria.andProjectPhaseNameLike(search);
        }
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public ProjectPlanDetails getProjectPlanDetailsById(Integer id) {
        return projectPlanDetailsMapper.selectByPrimaryKey(id);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByPlanId(Integer planId, String executeUserAccount, String projectPhaseName, List<String> ststusList) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        ProjectPlanDetailsExample.Criteria criteria = example.createCriteria();
        ProjectPlanDetailsExample.Criteria criteria1 = example.createCriteria();
        criteria.andPlanIdEqualTo(planId);
        criteria1.andPlanIdEqualTo(planId);
        if (StringUtils.isNotBlank(projectPhaseName)) {
            criteria.andProjectPhaseNameLike(String.join("", "%", projectPhaseName, "%"));
            criteria1.andPlanRemarksLike(String.join("", "%", projectPhaseName, "%"));
        }
        if (StringUtils.isNotBlank(executeUserAccount)) {
            criteria.andExecuteUserAccountEqualTo(executeUserAccount);
            criteria1.andExecuteUserAccountEqualTo(executeUserAccount);
        }
        if (CollectionUtils.isNotEmpty(ststusList)) {
            criteria.andStatusIn(ststusList);
            criteria1.andStatusIn(ststusList);
        }
        example.or(criteria1);

        example.setOrderByClause("sorting");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByPlanId(Integer planId) {
        return getProjectPlanDetailsByPlanId(planId, null, null, null);
    }

    public List<ProjectPlanDetails> getRootProjectPlanDetailsByPlanId(Integer planId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPidEqualTo(0).andPlanIdEqualTo(planId);
        example.setOrderByClause("sorting");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByPid(Integer pid) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPidEqualTo(pid);
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

    public Boolean addProjectPlanDetails(ProjectPlanDetails projectPlanDetails) {
        int i = projectPlanDetailsMapper.insertSelective(projectPlanDetails);
        return i == 1;
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByProjectId(Integer projectId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public Boolean updateProjectPlanDetails(ProjectPlanDetails projectPlanDetails) {
        int i = projectPlanDetailsMapper.updateByPrimaryKeySelective(projectPlanDetails);
        return i == 1;
    }

    public Boolean updateProjectPlanDetailsAndNull(ProjectPlanDetails projectPlanDetails) {
        int i = projectPlanDetailsMapper.updateByPrimaryKeySelective(projectPlanDetails);
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

    public List<ProjectPlanDetails> getProjectPlanDetailsLastLayer(Integer planId, String status) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        ProjectPlanDetailsExample.Criteria criteria = example.createCriteria();
        criteria.andBisEnableEqualTo(true).andPlanIdEqualTo(planId).andBisLastLayerEqualTo(true);
        if (StringUtils.isNotBlank(status)) {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause(" sorting");
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public Boolean deleteProjectPlanDetails(Integer id) {
        return projectPlanDetailsMapper.deleteByPrimaryKey(id) == 1;
    }

    public Boolean deletePlanDetailsByPlanId(Integer planId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        ProjectPlanDetailsExample.Criteria criteria = example.createCriteria();
        criteria.andPlanIdEqualTo(planId);
        return projectPlanDetailsMapper.deleteByExample(example) > 0;
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByIds(List<Integer> ids) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        ProjectPlanDetailsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return projectPlanDetailsMapper.selectByExample(example);
    }

    public Integer getTotalPlanDetails(Integer planId) {
        ProjectPlanDetailsExample example = new ProjectPlanDetailsExample();
        example.createCriteria().andPlanIdEqualTo(planId);
        return projectPlanDetailsMapper.countByExample(example);
    }
}
