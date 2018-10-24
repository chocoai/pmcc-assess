package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseMapper {
    int countByExample(BasicHouseExample example);

    int deleteByExample(BasicHouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouse record);

    int insertSelective(BasicHouse record);

    List<BasicHouse> selectByExample(BasicHouseExample example);

    BasicHouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouse record, @Param("example") BasicHouseExample example);

    int updateByExample(@Param("record") BasicHouse record, @Param("example") BasicHouseExample example);

    int updateByPrimaryKeySelective(BasicHouse record);

    int updateByPrimaryKey(BasicHouse record);
}