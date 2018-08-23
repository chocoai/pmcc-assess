package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureMatchingCost;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureMatchingCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataInfrastructureMatchingCostMapper {
    int countByExample(DataInfrastructureMatchingCostExample example);

    int deleteByExample(DataInfrastructureMatchingCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataInfrastructureMatchingCost record);

    int insertSelective(DataInfrastructureMatchingCost record);

    List<DataInfrastructureMatchingCost> selectByExample(DataInfrastructureMatchingCostExample example);

    DataInfrastructureMatchingCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataInfrastructureMatchingCost record, @Param("example") DataInfrastructureMatchingCostExample example);

    int updateByExample(@Param("record") DataInfrastructureMatchingCost record, @Param("example") DataInfrastructureMatchingCostExample example);

    int updateByPrimaryKeySelective(DataInfrastructureMatchingCost record);

    int updateByPrimaryKey(DataInfrastructureMatchingCost record);
}