package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassifyExample;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectNumberRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
     * 预评报告编号
     * @param year
     * @param reportType
     * @return
     */
    public List<ProjectNumberRecord> getPreauditNumberList(Integer year,Integer reportType){
        ProjectNumberRecordExample example = new ProjectNumberRecordExample();
        ProjectNumberRecordExample.Criteria criteria = example.createCriteria();
        criteria.andYearEqualTo(year).andReportTypeEqualTo(reportType);
        example.setOrderByClause("number desc");
        return projectNumberRecordMapper.selectByExample(example);
    }

    /**
     * 非预评报告编号
     * @param year
     * @param reportType
     * @return
     */
    public List<ProjectNumberRecord> getUnPreauditNumberList(Integer year,Integer reportType){
        ProjectNumberRecordExample example = new ProjectNumberRecordExample();
        ProjectNumberRecordExample.Criteria criteria = example.createCriteria();
        criteria.andYearEqualTo(year).andReportTypeNotEqualTo(reportType);
        example.setOrderByClause("number desc");
        return projectNumberRecordMapper.selectByExample(example);
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
