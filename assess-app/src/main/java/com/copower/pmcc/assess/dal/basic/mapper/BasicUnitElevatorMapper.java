package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitElevator;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitElevatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicUnitElevatorMapper {
    int countByExample(BasicUnitElevatorExample example);

    int deleteByExample(BasicUnitElevatorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicUnitElevator record);

    int insertSelective(BasicUnitElevator record);

    List<BasicUnitElevator> selectByExample(BasicUnitElevatorExample example);

    BasicUnitElevator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicUnitElevator record, @Param("example") BasicUnitElevatorExample example);

    int updateByExample(@Param("record") BasicUnitElevator record, @Param("example") BasicUnitElevatorExample example);

    int updateByPrimaryKeySelective(BasicUnitElevator record);

    int updateByPrimaryKey(BasicUnitElevator record);
}