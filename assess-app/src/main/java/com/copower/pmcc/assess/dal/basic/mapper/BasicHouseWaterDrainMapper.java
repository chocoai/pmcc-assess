package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWaterDrain;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWaterDrainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseWaterDrainMapper {
    int countByExample(BasicHouseWaterDrainExample example);

    int deleteByExample(BasicHouseWaterDrainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseWaterDrain record);

    int insertSelective(BasicHouseWaterDrain record);

    List<BasicHouseWaterDrain> selectByExample(BasicHouseWaterDrainExample example);

    BasicHouseWaterDrain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseWaterDrain record, @Param("example") BasicHouseWaterDrainExample example);

    int updateByExample(@Param("record") BasicHouseWaterDrain record, @Param("example") BasicHouseWaterDrainExample example);

    int updateByPrimaryKeySelective(BasicHouseWaterDrain record);

    int updateByPrimaryKey(BasicHouseWaterDrain record);
}