package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomDecorate;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomDecorateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseRoomDecorateMapper {
    int countByExample(ExamineHouseRoomDecorateExample example);

    int deleteByExample(ExamineHouseRoomDecorateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseRoomDecorate record);

    int insertSelective(ExamineHouseRoomDecorate record);

    List<ExamineHouseRoomDecorate> selectByExample(ExamineHouseRoomDecorateExample example);

    ExamineHouseRoomDecorate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseRoomDecorate record, @Param("example") ExamineHouseRoomDecorateExample example);

    int updateByExample(@Param("record") ExamineHouseRoomDecorate record, @Param("example") ExamineHouseRoomDecorateExample example);

    int updateByPrimaryKeySelective(ExamineHouseRoomDecorate record);

    int updateByPrimaryKey(ExamineHouseRoomDecorate record);
}