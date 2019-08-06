package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectNumberRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectNumberRecordDao {
    @Autowired
    private ProjectNumberRecordMapper projectNumberRecordMapper;

    public ProjectNumberRecord getProjectNumberRecord(Integer id) {
        return projectNumberRecordMapper.selectByPrimaryKey(id);
    }

    public List<ProjectNumberRecord> getProjectNumberRecordList(ProjectNumberRecord projectNumberRecord) {
        ProjectNumberRecordExample example = new ProjectNumberRecordExample();
        MybatisUtils.convertObj2Example(projectNumberRecord, example);
        return projectNumberRecordMapper.selectByExample(example);
    }

    /**
     * 报告编号
     *
     * @param projectId
     * @param reportType
     * @return
     */
    public List<ProjectNumberRecord> getNumberList(Integer projectId, Integer reportType) {
        ProjectNumberRecordExample example = new ProjectNumberRecordExample();
        ProjectNumberRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId).andReportTypeEqualTo(reportType);
        example.setOrderByClause("number desc");
        return projectNumberRecordMapper.selectByExample(example);
    }

    public ProjectNumberRecord getProjectNumberRecord(Integer projectId, Integer areaId, Integer year, Integer reportType) {
        ProjectNumberRecordExample example = new ProjectNumberRecordExample();
        ProjectNumberRecordExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false).andProjectIdEqualTo(projectId).andAreaIdEqualTo(areaId).andYearEqualTo(year).andReportTypeEqualTo(reportType);
        example.setOrderByClause("number desc");
        List<ProjectNumberRecord> numberRecordList = projectNumberRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(numberRecordList)) return null;
        return numberRecordList.get(0);
    }

    public boolean addProjectNumberRecord(ProjectNumberRecord projectNumberRecord) {
        int i = projectNumberRecordMapper.insertSelective(projectNumberRecord);
        return i > 0;
    }

    public boolean editProjectNumberRecord(ProjectNumberRecord projectNumberRecord) {
        int i = projectNumberRecordMapper.updateByPrimaryKeySelective(projectNumberRecord);
        return i > 0;
    }

    public boolean deleteProjectNumberRecord(Integer id) {
        int i = projectNumberRecordMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
