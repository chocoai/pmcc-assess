package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseAirConditioner;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseAirConditionerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseAirConditionerMapper {
    int countByExample(CaseHouseAirConditionerExample example);

    int deleteByExample(CaseHouseAirConditionerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseAirConditioner record);

    int insertSelective(CaseHouseAirConditioner record);

    List<CaseHouseAirConditioner> selectByExample(CaseHouseAirConditionerExample example);

    CaseHouseAirConditioner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseAirConditioner record, @Param("example") CaseHouseAirConditionerExample example);

    int updateByExample(@Param("record") CaseHouseAirConditioner record, @Param("example") CaseHouseAirConditionerExample example);

    int updateByPrimaryKeySelective(CaseHouseAirConditioner record);

    int updateByPrimaryKey(CaseHouseAirConditioner record);
}