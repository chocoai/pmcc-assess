package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataCaseComparisonField;
import com.copower.pmcc.assess.dal.entity.DataCaseComparisonFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataCaseComparisonFieldMapper {
    int countByExample(DataCaseComparisonFieldExample example);

    int deleteByExample(DataCaseComparisonFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataCaseComparisonField record);

    int insertSelective(DataCaseComparisonField record);

    List<DataCaseComparisonField> selectByExample(DataCaseComparisonFieldExample example);

    DataCaseComparisonField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataCaseComparisonField record, @Param("example") DataCaseComparisonFieldExample example);

    int updateByExample(@Param("record") DataCaseComparisonField record, @Param("example") DataCaseComparisonFieldExample example);

    int updateByPrimaryKeySelective(DataCaseComparisonField record);

    int updateByPrimaryKey(DataCaseComparisonField record);
}