package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoom;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseRoomMapper {
    int countByExample(CaseHouseRoomExample example);

    int deleteByExample(CaseHouseRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseRoom record);

    int insertSelective(CaseHouseRoom record);

    List<CaseHouseRoom> selectByExample(CaseHouseRoomExample example);

    CaseHouseRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseRoom record, @Param("example") CaseHouseRoomExample example);

    int updateByExample(@Param("record") CaseHouseRoom record, @Param("example") CaseHouseRoomExample example);

    int updateByPrimaryKeySelective(CaseHouseRoom record);

    int updateByPrimaryKey(CaseHouseRoom record);
}