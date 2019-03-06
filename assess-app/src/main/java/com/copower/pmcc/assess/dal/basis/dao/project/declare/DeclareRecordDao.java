package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRecordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public List<DeclareRecord> getDeclareRecordList(Integer projectId, Boolean bisGenerate) {
        DeclareRecordExample example = new DeclareRecordExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andBisGenerateEqualTo(bisGenerate);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DeclareRecord> getDeclareRecordList(Integer projectId, String name, String seat, Boolean bisPartIn) {
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
}
