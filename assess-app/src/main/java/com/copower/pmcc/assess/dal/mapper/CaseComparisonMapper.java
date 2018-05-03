package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.CaseComparison;
import com.copower.pmcc.assess.dal.entity.CaseComparisonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseComparisonMapper {
    int countByExample(CaseComparisonExample example);

    int deleteByExample(CaseComparisonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseComparison record);

    int insertSelective(CaseComparison record);

    List<CaseComparison> selectByExample(CaseComparisonExample example);

    CaseComparison selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseComparison record, @Param("example") CaseComparisonExample example);

    int updateByExample(@Param("record") CaseComparison record, @Param("example") CaseComparisonExample example);

    int updateByPrimaryKeySelective(CaseComparison record);

    int updateByPrimaryKey(CaseComparison record);
}