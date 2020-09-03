package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseCaseSummaryMapper {
    long countByExample(BasicHouseCaseSummaryExample example);

    int deleteByExample(BasicHouseCaseSummaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseCaseSummary record);

    int insertSelective(BasicHouseCaseSummary record);

    List<BasicHouseCaseSummary> selectByExample(BasicHouseCaseSummaryExample example);

    BasicHouseCaseSummary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseCaseSummary record, @Param("example") BasicHouseCaseSummaryExample example);

    int updateByExample(@Param("record") BasicHouseCaseSummary record, @Param("example") BasicHouseCaseSummaryExample example);

    int updateByPrimaryKeySelective(BasicHouseCaseSummary record);

    int updateByPrimaryKey(BasicHouseCaseSummary record);
}