package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectQrcodeRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectQrcodeRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectQrcodeRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectQrcodeRecordDao {
    @Autowired
    private ProjectQrcodeRecordMapper projectQrcodeRecordMapper;

    public ProjectQrcodeRecord getProjectQrcodeRecord(Integer id) {
        return projectQrcodeRecordMapper.selectByPrimaryKey(id);
    }

    public List<ProjectQrcodeRecord> getProjectQrcodeRecordList(ProjectQrcodeRecord projectQrcodeRecord) {
        ProjectQrcodeRecordExample example = new ProjectQrcodeRecordExample();
        MybatisUtils.convertObj2Example(projectQrcodeRecord, example);
        return projectQrcodeRecordMapper.selectByExample(example);
    }



    public ProjectQrcodeRecord getProjectQrcodeRecord(Integer projectId, Integer areaId, Integer reportType) {
        ProjectQrcodeRecordExample example = new ProjectQrcodeRecordExample();
        ProjectQrcodeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId).andAreaIdEqualTo(areaId).andReportTypeEqualTo(reportType);
        example.setOrderByClause("id desc");
        List<ProjectQrcodeRecord> numberRecordList = projectQrcodeRecordMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isEmpty(numberRecordList)) return null;
        return numberRecordList.get(0);
    }

    public boolean addProjectQrcodeRecord(ProjectQrcodeRecord projectQrcodeRecord) {
        int i = projectQrcodeRecordMapper.insertSelective(projectQrcodeRecord);
        return i > 0;
    }

    public boolean editProjectQrcodeRecord(ProjectQrcodeRecord projectQrcodeRecord) {
        int i = projectQrcodeRecordMapper.updateByPrimaryKeySelective(projectQrcodeRecord);
        return i > 0;
    }

    public boolean deleteProjectQrcodeRecord(Integer id) {
        int i = projectQrcodeRecordMapper.deleteByPrimaryKey(id);
        return i > 0;
    }


}
