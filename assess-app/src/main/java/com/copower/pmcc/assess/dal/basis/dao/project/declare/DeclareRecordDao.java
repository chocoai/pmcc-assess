package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeclareRecordDao {

    @Autowired
    private DeclareRecordMapper mapper;

    public List<DeclareRecord> getDeclareRecordListByIds(List<Integer> ids) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<DeclareRecord> getDeclareRecordListByProjectId(Integer projectId) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DeclareRecord> getPartInDeclareRecordsByProjectId(Integer projectId) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andBisPartInEqualTo(true);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DeclareRecord> getDeclareRecordListByDataTableId(String dataTableName, Integer dataTableId, Integer projectId) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andDataTableIdEqualTo(dataTableId).andDataTableNameEqualTo(dataTableName);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DeclareRecord> getDeclareRecordList(Integer projectId, Boolean bisGenerate) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andBisGenerateEqualTo(bisGenerate);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DeclareRecord> getDeclareRecordList(DeclareRecord declareRecord) {
        DeclareRecordExample example = new DeclareRecordExample();
        MybatisUtils.convertObj2Example(declareRecord, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DeclareRecord> getDeclareRecordList(Integer projectId, String name, String seat, Boolean bisPartIn, String province, String city, String district) {
        DeclareRecordExample example = new DeclareRecordExample();
        DeclareRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if (StringUtils.isNotBlank(seat)) {
            criteria.andSeatLike(String.format("%%%s%%", seat));
        }
        if (bisPartIn != null) {
            criteria.andBisPartInEqualTo(bisPartIn);
        }
        if (StringUtils.isNotBlank(province)) {
            criteria.andProvinceEqualTo(province);
        }
        if (StringUtils.isNotBlank(city)) {
            criteria.andCityEqualTo(city);
        }
        if (StringUtils.isNotBlank(district)) {
            criteria.andDistrictEqualTo(district);
        }
        return mapper.selectByExample(example);
    }

    public boolean updateDeclareRecord(DeclareRecord declareRecord) {
        return mapper.updateByPrimaryKeySelective(declareRecord) > 0;
    }

    public Integer saveReturnId(DeclareRecord declareRecord) {
        mapper.insertSelective(declareRecord);
        return declareRecord.getId();
    }

    public boolean addDeclareRecord(DeclareRecord declareRecord) {
        return mapper.insertSelective(declareRecord) > 0;
    }

    public boolean deleteDeclareRecord(Integer id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    public DeclareRecord getDeclareRecordById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public Integer getCountByProjectId(Integer projectId){
        DeclareRecordExample example = new DeclareRecordExample();
        DeclareRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        return mapper.countByExample(example);
    }
}
