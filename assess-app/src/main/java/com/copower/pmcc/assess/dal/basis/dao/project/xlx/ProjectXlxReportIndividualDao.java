package com.copower.pmcc.assess.dal.basis.dao.project.xlx;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividual;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividualExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectXlxReportIndividualMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class ProjectXlxReportIndividualDao {

    @Autowired
    private ProjectXlxReportIndividualMapper mapper;

    public boolean updateProjectXlxReportIndividual(ProjectXlxReportIndividual oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteProjectXlxReportIndividualById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteProjectXlxReportIndividualByIds(List<Integer> ids) {
        ProjectXlxReportIndividualExample example = new ProjectXlxReportIndividualExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public ProjectXlxReportIndividual getProjectXlxReportIndividualById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveProjectXlxReportIndividual(ProjectXlxReportIndividual oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<ProjectXlxReportIndividual> getProjectXlxReportIndividualByIds(List<Integer> ids) {
        ProjectXlxReportIndividualExample example = new ProjectXlxReportIndividualExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<ProjectXlxReportIndividual> getProjectXlxReportIndividualListByExample(ProjectXlxReportIndividual oo) {
        ProjectXlxReportIndividualExample example = new ProjectXlxReportIndividualExample();
        ProjectXlxReportIndividualExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<ProjectXlxReportIndividual> getProjectXlxReportIndividualListLikeQuery(ProjectXlxReportIndividual oo) {
        ProjectXlxReportIndividualExample example = new ProjectXlxReportIndividualExample();
        ProjectXlxReportIndividualExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(oo.getProjectName())) {
            criteria.andProjectNameLike(String.format("%%%s%%", oo.getProjectName()));
        }
        if (StringUtils.isNotBlank(oo.getNumberValue())) {
            criteria.andNumberValueLike(String.format("%%%s%%", oo.getNumberValue()));
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }
}
