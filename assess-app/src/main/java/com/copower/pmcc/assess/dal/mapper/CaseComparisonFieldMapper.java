package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.CaseComparisonField;
import com.copower.pmcc.assess.dal.entity.CaseComparisonFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseComparisonFieldMapper {
    int countByExample(CaseComparisonFieldExample example);

    int deleteByExample(CaseComparisonFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseComparisonField record);

    int insertSelective(CaseComparisonField record);

    List<CaseComparisonField> selectByExample(CaseComparisonFieldExample example);

    CaseComparisonField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseComparisonField record, @Param("example") CaseComparisonFieldExample example);

    int updateByExample(@Param("record") CaseComparisonField record, @Param("example") CaseComparisonFieldExample example);

    int updateByPrimaryKeySelective(CaseComparisonField record);

    int updateByPrimaryKey(CaseComparisonField record);
}