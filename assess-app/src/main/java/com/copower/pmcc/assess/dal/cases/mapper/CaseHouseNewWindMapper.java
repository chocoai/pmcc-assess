package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseNewWind;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseNewWindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseNewWindMapper {
    int countByExample(CaseHouseNewWindExample example);

    int deleteByExample(CaseHouseNewWindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseNewWind record);

    int insertSelective(CaseHouseNewWind record);

    List<CaseHouseNewWind> selectByExample(CaseHouseNewWindExample example);

    CaseHouseNewWind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseNewWind record, @Param("example") CaseHouseNewWindExample example);

    int updateByExample(@Param("record") CaseHouseNewWind record, @Param("example") CaseHouseNewWindExample example);

    int updateByPrimaryKeySelective(CaseHouseNewWind record);

    int updateByPrimaryKey(CaseHouseNewWind record);
}