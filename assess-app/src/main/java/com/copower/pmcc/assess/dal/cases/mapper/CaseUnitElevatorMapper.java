package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevator;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseUnitElevatorMapper {
    int countByExample(CaseUnitElevatorExample example);

    int deleteByExample(CaseUnitElevatorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseUnitElevator record);

    int insertSelective(CaseUnitElevator record);

    List<CaseUnitElevator> selectByExample(CaseUnitElevatorExample example);

    CaseUnitElevator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseUnitElevator record, @Param("example") CaseUnitElevatorExample example);

    int updateByExample(@Param("record") CaseUnitElevator record, @Param("example") CaseUnitElevatorExample example);

    int updateByPrimaryKeySelective(CaseUnitElevator record);

    int updateByPrimaryKey(CaseUnitElevator record);
}