package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilding;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBuildingMapper {
    int countByExample(ExamineBuildingExample example);

    int deleteByExample(ExamineBuildingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBuilding record);

    int insertSelective(ExamineBuilding record);

    List<ExamineBuilding> selectByExample(ExamineBuildingExample example);

    ExamineBuilding selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBuilding record, @Param("example") ExamineBuildingExample example);

    int updateByExample(@Param("record") ExamineBuilding record, @Param("example") ExamineBuildingExample example);

    int updateByPrimaryKeySelective(ExamineBuilding record);

    int updateByPrimaryKey(ExamineBuilding record);
}