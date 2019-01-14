package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectNumberRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectNumberRecordDao {
    @Autowired
    private ProjectNumberRecordMapper projectNumberRecordMapper;

    public ProjectNumberRecord getProjectNumberRecord(Integer id) {
        return projectNumberRecordMapper.selectByPrimaryKey(id);
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
