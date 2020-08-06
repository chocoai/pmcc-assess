package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseRoomDecorateMapper {
    long countByExample(BasicHouseRoomDecorateExample example);

    int deleteByExample(BasicHouseRoomDecorateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseRoomDecorate record);

    int insertSelective(BasicHouseRoomDecorate record);

    List<BasicHouseRoomDecorate> selectByExample(BasicHouseRoomDecorateExample example);

    BasicHouseRoomDecorate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseRoomDecorate record, @Param("example") BasicHouseRoomDecorateExample example);

    int updateByExample(@Param("record") BasicHouseRoomDecorate record, @Param("example") BasicHouseRoomDecorateExample example);

    int updateByPrimaryKeySelective(BasicHouseRoomDecorate record);

    int updateByPrimaryKey(BasicHouseRoomDecorate record);
}