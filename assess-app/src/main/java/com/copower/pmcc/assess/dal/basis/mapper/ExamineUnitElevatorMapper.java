package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitElevator;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitElevatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineUnitElevatorMapper {
    int countByExample(ExamineUnitElevatorExample example);

    int deleteByExample(ExamineUnitElevatorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineUnitElevator record);

    int insertSelective(ExamineUnitElevator record);

    List<ExamineUnitElevator> selectByExample(ExamineUnitElevatorExample example);

    ExamineUnitElevator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineUnitElevator record, @Param("example") ExamineUnitElevatorExample example);

    int updateByExample(@Param("record") ExamineUnitElevator record, @Param("example") ExamineUnitElevatorExample example);

    int updateByPrimaryKeySelective(ExamineUnitElevator record);

    int updateByPrimaryKey(ExamineUnitElevator record);
}