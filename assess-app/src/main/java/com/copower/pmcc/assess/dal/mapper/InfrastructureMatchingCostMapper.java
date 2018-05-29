package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.InfrastructureMatchingCost;
import com.copower.pmcc.assess.dal.entity.InfrastructureMatchingCostExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfrastructureMatchingCostMapper {
    int countByExample(InfrastructureMatchingCostExample example);

    int deleteByExample(InfrastructureMatchingCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InfrastructureMatchingCost record);

    int insertSelective(InfrastructureMatchingCost record);

    List<InfrastructureMatchingCost> selectByExample(InfrastructureMatchingCostExample example);

    InfrastructureMatchingCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InfrastructureMatchingCost record, @Param("example") InfrastructureMatchingCostExample example);

    int updateByExample(@Param("record") InfrastructureMatchingCost record, @Param("example") InfrastructureMatchingCostExample example);

    int updateByPrimaryKeySelective(InfrastructureMatchingCost record);

    int updateByPrimaryKey(InfrastructureMatchingCost record);
}