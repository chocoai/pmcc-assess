package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseFaceStreet;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseFaceStreetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseFaceStreetMapper {
    int countByExample(CaseHouseFaceStreetExample example);

    int deleteByExample(CaseHouseFaceStreetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseFaceStreet record);

    int insertSelective(CaseHouseFaceStreet record);

    List<CaseHouseFaceStreet> selectByExample(CaseHouseFaceStreetExample example);

    CaseHouseFaceStreet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseFaceStreet record, @Param("example") CaseHouseFaceStreetExample example);

    int updateByExample(@Param("record") CaseHouseFaceStreet record, @Param("example") CaseHouseFaceStreetExample example);

    int updateByPrimaryKeySelective(CaseHouseFaceStreet record);

    int updateByPrimaryKey(CaseHouseFaceStreet record);
}