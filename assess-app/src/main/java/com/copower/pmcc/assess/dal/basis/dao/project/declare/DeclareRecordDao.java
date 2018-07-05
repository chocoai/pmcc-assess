package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeclareRecordDao {

    @Autowired
    private DeclareRecordMapper mapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DeclareRecord getByID(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    /*所有记录*/
    public List<DeclareRecord> queryAll(Integer projectID){
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<DeclareRecord> declareRecords = mapper.selectByExample(example);
        return declareRecords;
    }

    public List<DeclareRecord> queryDeclareRecords(){
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProvinceIsNotNull();
        List<DeclareRecord> declareRecords = mapper.selectByExample(example);
        return declareRecords;
    }


    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return mapper.selectByExample(example);
    }

    public boolean updateDeclareRecord(DeclareRecord declareRecord) {
        return mapper.updateByPrimaryKeySelective(declareRecord) > 0;
    }

    public boolean addDeclareRecord(DeclareRecord declareRecord) {
        return mapper.insertSelective(declareRecord) > 0;
    }

    public boolean deleteDeclareRecord(Integer id){
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    public List<DeclareRecord> getDeclareRecordById(Integer id) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andIdEqualTo(id);
        return mapper.selectByExample(example);
    }
}
