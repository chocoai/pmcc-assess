package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseAirConditioner;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseAirConditionerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseAirConditionerMapper {
    int countByExample(ExamineHouseAirConditionerExample example);

    int deleteByExample(ExamineHouseAirConditionerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseAirConditioner record);

    int insertSelective(ExamineHouseAirConditioner record);

    List<ExamineHouseAirConditioner> selectByExample(ExamineHouseAirConditionerExample example);

    ExamineHouseAirConditioner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseAirConditioner record, @Param("example") ExamineHouseAirConditionerExample example);

    int updateByExample(@Param("record") ExamineHouseAirConditioner record, @Param("example") ExamineHouseAirConditionerExample example);

    int updateByPrimaryKeySelective(ExamineHouseAirConditioner record);

    int updateByPrimaryKey(ExamineHouseAirConditioner record);
}