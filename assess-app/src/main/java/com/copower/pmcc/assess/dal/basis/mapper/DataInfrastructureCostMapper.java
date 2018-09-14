package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCost;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCostExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataInfrastructureCostMapper {
    int countByExample(DataInfrastructureCostExample example);

    int deleteByExample(DataInfrastructureCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataInfrastructureCost record);

    int insertSelective(DataInfrastructureCost record);

    List<DataInfrastructureCost> selectByExample(DataInfrastructureCostExample example);

    DataInfrastructureCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataInfrastructureCost record, @Param("example") DataInfrastructureCostExample example);

    int updateByExample(@Param("record") DataInfrastructureCost record, @Param("example") DataInfrastructureCostExample example);

    int updateByPrimaryKeySelective(DataInfrastructureCost record);

    int updateByPrimaryKey(DataInfrastructureCost record);
}