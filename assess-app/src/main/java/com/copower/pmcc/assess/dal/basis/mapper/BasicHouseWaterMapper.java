package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWater;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWaterExample;
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