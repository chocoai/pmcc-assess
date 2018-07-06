package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseWater;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseWaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseWaterMapper {
    int countByExample(ExamineHouseWaterExample example);

    int deleteByExample(ExamineHouseWaterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseWater record);

    int insertSelective(ExamineHouseWater record);

    List<ExamineHouseWater> selectByExample(ExamineHouseWaterExample example);

    ExamineHouseWater selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseWater record, @Param("example") ExamineHouseWaterExample example);

    int updateByExample(@Param("record") ExamineHouseWater record, @Param("example") ExamineHouseWaterExample example);

    int updateByPrimaryKeySelective(ExamineHouseWater record);

    int updateByPrimaryKey(ExamineHouseWater record);
}