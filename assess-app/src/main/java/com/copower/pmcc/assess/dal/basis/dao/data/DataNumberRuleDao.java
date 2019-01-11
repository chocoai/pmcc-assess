package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRuleExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataNumberRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataNumberRuleDao {

    @Autowired
    private DataNumberRuleMapper dataNumberRuleMapper;

    public List<DataNumberRule> getDataNumberRule(Integer reportType) {
        DataNumberRuleExample example = new DataNumberRuleExample();
        DataNumberRuleExample.Criteria criteria = example.createCriteria();
        if (reportType != null) {
            criteria.andReportTypeEqualTo(reportType);
        }
        example.setOrderByClause("id desc");
        List<DataNumberRule> dataNumberRules = dataNumberRuleMapper.selectByExample(example);

        return dataNumberRules;
    }

    public Boolean update(DataNumberRule dataNumberRule) {
        int i = dataNumberRuleMapper.updateByPrimaryKeySelective(dataNumberRule);
        return i > 0;
    }

    public Boolean save(DataNumberRule dataNumberRule) {
        int i = dataNumberRuleMapper.insertSelective(dataNumberRule);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = dataNumberRuleMapper.deleteByPrimaryKey(id);
        return i > 0;
    }
}
