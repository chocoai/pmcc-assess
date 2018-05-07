package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.DeclareRecordExample;
import com.copower.pmcc.assess.dal.mapper.DeclareRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeclareRecordDao {

    @Autowired
    private DeclareRecordMapper declareRecordMapper;

    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return declareRecordMapper.selectByExample(example);
    }

}
