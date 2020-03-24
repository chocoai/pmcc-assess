package com.copower.pmcc.assess.dal.basis.dao.project.xlx;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectXlxPigeonholeRecordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Repository
public class ProjectXlxPigeonholeRecordDao {

    @Autowired
    private ProjectXlxPigeonholeRecordMapper projectXlxPigeonholeRecordMapper;

    public boolean addProjectXlxPigeonholeRecord(ProjectXlxPigeonholeRecord projectXlxPigeonholeRecord) {
        return projectXlxPigeonholeRecordMapper.insertSelective(projectXlxPigeonholeRecord) == 1;
    }


    public boolean updateProjectXlxPigeonholeRecord(ProjectXlxPigeonholeRecord projectXlxPigeonholeRecord) {
        return projectXlxPigeonholeRecordMapper.updateByPrimaryKeySelective(projectXlxPigeonholeRecord) == 1;
    }

    public boolean deleteProjectXlxPigeonholeRecordByMasterId(Integer masterId) {
        ProjectXlxPigeonholeRecordExample example = new ProjectXlxPigeonholeRecordExample();
        ProjectXlxPigeonholeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId);
        return projectXlxPigeonholeRecordMapper.deleteByExample(example) > 0;
    }

    public List<ProjectXlxPigeonholeRecord> getProjectXlxPigeonholeRecordList(Integer projectId,Integer pigeonholeId) {
        ProjectXlxPigeonholeRecordExample example = new ProjectXlxPigeonholeRecordExample();
        ProjectXlxPigeonholeRecordExample.Criteria criteria = example.createCriteria();
        if (projectId != null) {
            criteria.andProjectIdEqualTo(projectId);
        }
        if(pigeonholeId!=null){
            criteria.andMasterIdEqualTo(pigeonholeId);
        }
        example.setOrderByClause("sorting");
        return projectXlxPigeonholeRecordMapper.selectByExample(example);
    }

    public boolean removeProjectXlxPigeonholeRecord(Integer id) {
        return projectXlxPigeonholeRecordMapper.deleteByPrimaryKey(id) == 1;
    }

    public ProjectXlxPigeonholeRecord getProjectXlxPigeonholeRecord(Integer id) {
        return projectXlxPigeonholeRecordMapper.selectByPrimaryKey(id);
    }
}
