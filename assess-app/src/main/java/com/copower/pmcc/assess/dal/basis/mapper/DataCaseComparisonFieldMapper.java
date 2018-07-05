package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataCaseComparisonField;
import com.copower.pmcc.assess.dal.basis.entity.DataCaseComparisonFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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