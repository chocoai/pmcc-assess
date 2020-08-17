package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseRoomMapper {
    long countByExample(BasicHouseRoomExample example);

    int deleteByExample(BasicHouseRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseRoom record);

    int insertSelective(@Param("record") BasicHouseRoom record, @Param("selective") BasicHouseRoom.Column ... selective);

    List<BasicHouseRoom> selectByExample(BasicHouseRoomExample example);

    BasicHouseRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseRoom record, @Param("example") BasicHouseRoomExample example, @Param("selective") BasicHouseRoom.Column ... selective);

    int updateByExample(@Param("record") BasicHouseRoom record, @Param("example") BasicHouseRoomExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicHouseRoom record, @Param("selective") BasicHouseRoom.Column ... selective);

    int updateByPrimaryKey(BasicHouseRoom record);

    int batchInsert(@Param("list") List<BasicHouseRoom> list);

    int batchInsertSelective(@Param("list") List<BasicHouseRoom> list, @Param("selective") BasicHouseRoom.Column ... selective);
}