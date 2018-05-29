package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.InfrastructureCost;
import com.copower.pmcc.assess.dal.entity.InfrastructureCostExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfrastructureCostMapper {
    int countByExample(InfrastructureCostExample example);

    int deleteByExample(InfrastructureCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InfrastructureCost record);

    int insertSelective(InfrastructureCost record);

    List<InfrastructureCost> selectByExample(InfrastructureCostExample example);

    InfrastructureCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InfrastructureCost record, @Param("example") InfrastructureCostExample example);

    int updateByExample(@Param("record") InfrastructureCost record, @Param("example") InfrastructureCostExample example);

    int updateByPrimaryKeySelective(InfrastructureCost record);

    int updateByPrimaryKey(InfrastructureCost record);
}