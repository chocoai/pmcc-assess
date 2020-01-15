package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRuleExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataNumberRuleMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataNumberRuleDao {

    @Autowired
    private DataNumberRuleMapper dataNumberRuleMapper;

    public List<DataNumberRule> getDataNumberRule(String assessProjectType,Integer reportType) {
        DataNumberRuleExample example = new DataNumberRuleExample();
        DataNumberRuleExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(assessProjectType)){
            criteria.andAssessProjectTypeEqualTo(assessProjectType);
        }
        if (reportType != null) {
            criteria.andReportTypeEqualTo(reportType);
        }
        criteria.andBisEnableEqualTo(true);
        example.setOrderByClause("id desc");
        List<DataNumberRule> dataNumberRules = dataNumberRuleMapper.selectByExample(example);

        return dataNumberRules;
    }

    public List<DataNumberRule> getDataNumberRule(DataNumberRule dataNumberRule) {
        DataNumberRuleExample example = new DataNumberRuleExample();
        MybatisUtils.convertObj2Example(dataNumberRule,example);
        return dataNumberRuleMapper.selectByExample(example);
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

    public DataNumberRule getDataNumberRuleById(Integer id) {
        return dataNumberRuleMapper.selectByPrimaryKey(id);
    }

}
