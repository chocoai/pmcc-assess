package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseRoomMapper {
    int countByExample(ExamineHouseRoomExample example);

    int deleteByExample(ExamineHouseRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseRoom record);

    int insertSelective(ExamineHouseRoom record);

    List<ExamineHouseRoom> selectByExample(ExamineHouseRoomExample example);

    ExamineHouseRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseRoom record, @Param("example") ExamineHouseRoomExample example);

    int updateByExample(@Param("record") ExamineHouseRoom record, @Param("example") ExamineHouseRoomExample example);

    int updateByPrimaryKeySelective(ExamineHouseRoom record);

    int updateByPrimaryKey(ExamineHouseRoom record);
}