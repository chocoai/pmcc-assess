package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBaseHouseMapper {
    int countByExample(CaseBaseHouseExample example);

    int deleteByExample(CaseBaseHouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBaseHouse record);

    int insertSelective(CaseBaseHouse record);

    List<CaseBaseHouse> selectByExample(CaseBaseHouseExample example);

    CaseBaseHouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBaseHouse record, @Param("example") CaseBaseHouseExample example);

    int updateByExample(@Param("record") CaseBaseHouse record, @Param("example") CaseBaseHouseExample example);

    int updateByPrimaryKeySelective(CaseBaseHouse record);

    int updateByPrimaryKey(CaseBaseHouse record);
}