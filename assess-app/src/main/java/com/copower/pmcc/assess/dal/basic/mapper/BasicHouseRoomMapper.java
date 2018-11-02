package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseRoomMapper {
    int countByExample(BasicHouseRoomExample example);

    int deleteByExample(BasicHouseRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseRoom record);

    int insertSelective(BasicHouseRoom record);

    List<BasicHouseRoom> selectByExample(BasicHouseRoomExample example);

    BasicHouseRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseRoom record, @Param("example") BasicHouseRoomExample example);

    int updateByExample(@Param("record") BasicHouseRoom record, @Param("example") BasicHouseRoomExample example);

    int updateByPrimaryKeySelective(BasicHouseRoom record);

    int updateByPrimaryKey(BasicHouseRoom record);
}