package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseHeating;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseHeatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseHeatingMapper {
    int countByExample(CaseHouseHeatingExample example);

    int deleteByExample(CaseHouseHeatingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseHeating record);

    int insertSelective(CaseHouseHeating record);

    List<CaseHouseHeating> selectByExample(CaseHouseHeatingExample example);

    CaseHouseHeating selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseHeating record, @Param("example") CaseHouseHeatingExample example);

    int updateByExample(@Param("record") CaseHouseHeating record, @Param("example") CaseHouseHeatingExample example);

    int updateByPrimaryKeySelective(CaseHouseHeating record);

    int updateByPrimaryKey(CaseHouseHeating record);
}