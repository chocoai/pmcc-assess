package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseFaceStreet;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseFaceStreetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseFaceStreetMapper {
    int countByExample(BasicHouseFaceStreetExample example);

    int deleteByExample(BasicHouseFaceStreetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseFaceStreet record);

    int insertSelective(BasicHouseFaceStreet record);

    List<BasicHouseFaceStreet> selectByExample(BasicHouseFaceStreetExample example);

    BasicHouseFaceStreet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseFaceStreet record, @Param("example") BasicHouseFaceStreetExample example);

    int updateByExample(@Param("record") BasicHouseFaceStreet record, @Param("example") BasicHouseFaceStreetExample example);

    int updateByPrimaryKeySelective(BasicHouseFaceStreet record);

    int updateByPrimaryKey(BasicHouseFaceStreet record);
}