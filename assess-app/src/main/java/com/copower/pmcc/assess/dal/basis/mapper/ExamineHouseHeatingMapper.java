package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseHeating;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseHeatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseHeatingMapper {
    int countByExample(ExamineHouseHeatingExample example);

    int deleteByExample(ExamineHouseHeatingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseHeating record);

    int insertSelective(ExamineHouseHeating record);

    List<ExamineHouseHeating> selectByExample(ExamineHouseHeatingExample example);

    ExamineHouseHeating selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseHeating record, @Param("example") ExamineHouseHeatingExample example);

    int updateByExample(@Param("record") ExamineHouseHeating record, @Param("example") ExamineHouseHeatingExample example);

    int updateByPrimaryKeySelective(ExamineHouseHeating record);

    int updateByPrimaryKey(ExamineHouseHeating record);
}