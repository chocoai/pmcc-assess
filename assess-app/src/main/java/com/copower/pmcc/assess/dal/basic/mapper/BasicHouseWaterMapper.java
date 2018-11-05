package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWater;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseWaterMapper {
    int countByExample(BasicHouseWaterExample example);

    int deleteByExample(BasicHouseWaterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseWater record);

    int insertSelective(BasicHouseWater record);

    List<BasicHouseWater> selectByExample(BasicHouseWaterExample example);

    BasicHouseWater selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseWater record, @Param("example") BasicHouseWaterExample example);

    int updateByExample(@Param("record") BasicHouseWater record, @Param("example") BasicHouseWaterExample example);

    int updateByPrimaryKeySelective(BasicHouseWater record);

    int updateByPrimaryKey(BasicHouseWater record);
}