package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataCaseComparison;
import com.copower.pmcc.assess.dal.entity.DataCaseComparisonExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataCaseComparisonMapper {
    int countByExample(DataCaseComparisonExample example);

    int deleteByExample(DataCaseComparisonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataCaseComparison record);

    int insertSelective(DataCaseComparison record);

    List<DataCaseComparison> selectByExample(DataCaseComparisonExample example);

    DataCaseComparison selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataCaseComparison record, @Param("example") DataCaseComparisonExample example);

    int updateByExample(@Param("record") DataCaseComparison record, @Param("example") DataCaseComparisonExample example);

    int updateByPrimaryKeySelective(DataCaseComparison record);

    int updateByPrimaryKey(DataCaseComparison record);
}