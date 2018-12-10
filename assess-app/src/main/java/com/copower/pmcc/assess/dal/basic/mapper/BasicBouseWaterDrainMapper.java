package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicBouseWaterDrain;
import com.copower.pmcc.assess.dal.basic.entity.BasicBouseWaterDrainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBouseWaterDrainMapper {
    int countByExample(BasicBouseWaterDrainExample example);

    int deleteByExample(BasicBouseWaterDrainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBouseWaterDrain record);

    int insertSelective(BasicBouseWaterDrain record);

    List<BasicBouseWaterDrain> selectByExample(BasicBouseWaterDrainExample example);

    BasicBouseWaterDrain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBouseWaterDrain record, @Param("example") BasicBouseWaterDrainExample example);

    int updateByExample(@Param("record") BasicBouseWaterDrain record, @Param("example") BasicBouseWaterDrainExample example);

    int updateByPrimaryKeySelective(BasicBouseWaterDrain record);

    int updateByPrimaryKey(BasicBouseWaterDrain record);
}