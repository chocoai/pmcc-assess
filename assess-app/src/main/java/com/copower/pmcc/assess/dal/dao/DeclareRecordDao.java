package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.constant.DeclareRecordConstant;
import com.copower.pmcc.assess.dal.custom.entity.DeclareRecordRowMapper;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.DeclareRecordExample;
import com.copower.pmcc.assess.dal.mapper.DeclareRecordMapper;
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

    /*所有记录*/
    public List<DeclareRecord> queryAll(){
        List<DeclareRecord> declareRecords = jdbcTemplate.query(DeclareRecordConstant.SQLSELECT4,new DeclareRecordRowMapper());
        return declareRecords;
    }

    public List<DeclareRecord> queryDeclareRecords(){
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProvinceIsNotNull();
        List<DeclareRecord> declareRecords = mapper.selectByExample(example);
        return declareRecords;
    }

    /*相同省*/
    public List<DeclareRecord> queryProvinceAll(){
        List<DeclareRecord> declareRecords = jdbcTemplate.query(DeclareRecordConstant.SQLSELECT3,new DeclareRecordRowMapper());
        return declareRecords;
    }

    /*相同省 市*/
    public List<DeclareRecord> queryProvinceCityAll(){
        List<DeclareRecord> declareRecords = jdbcTemplate.query(DeclareRecordConstant.SQLSELECT2,new DeclareRecordRowMapper());
        return declareRecords;
    }

    /*相同省 市 县*/
    public List<DeclareRecord> queryProvinceCityDistrictAll(){
        List<DeclareRecord> declareRecords = jdbcTemplate.query(DeclareRecordConstant.SQLSELECT1,new DeclareRecordRowMapper());
        return declareRecords;
    }

    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return mapper.selectByExample(example);
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
