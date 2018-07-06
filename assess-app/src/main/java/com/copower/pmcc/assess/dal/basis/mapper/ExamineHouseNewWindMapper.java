package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseNewWind;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseNewWindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseNewWindMapper {
    int countByExample(ExamineHouseNewWindExample example);

    int deleteByExample(ExamineHouseNewWindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseNewWind record);

    int insertSelective(ExamineHouseNewWind record);

    List<ExamineHouseNewWind> selectByExample(ExamineHouseNewWindExample example);

    ExamineHouseNewWind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseNewWind record, @Param("example") ExamineHouseNewWindExample example);

    int updateByExample(@Param("record") ExamineHouseNewWind record, @Param("example") ExamineHouseNewWindExample example);

    int updateByPrimaryKeySelective(ExamineHouseNewWind record);

    int updateByPrimaryKey(ExamineHouseNewWind record);
}