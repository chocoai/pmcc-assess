package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.entity.DataNumberRuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataNumberRuleMapper {
    int countByExample(DataNumberRuleExample example);

    int deleteByExample(DataNumberRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataNumberRule record);

    int insertSelective(DataNumberRule record);

    List<DataNumberRule> selectByExample(DataNumberRuleExample example);

    DataNumberRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataNumberRule record, @Param("example") DataNumberRuleExample example);

    int updateByExample(@Param("record") DataNumberRule record, @Param("example") DataNumberRuleExample example);

    int updateByPrimaryKeySelective(DataNumberRule record);

    int updateByPrimaryKey(DataNumberRule record);
}