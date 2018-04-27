package com.copower.pmcc.assess.dal.dao;


import com.copower.pmcc.assess.dal.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.entity.DataNumberRuleExample;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class DataNumberRuleDao {
    public List<DataNumberRule> getDataNumberRule(String name) {
        DataNumberRuleExample example = new DataNumberRuleExample();
        DataNumberRuleExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotEmpty(name)){

        }


        return null;
    }
}
