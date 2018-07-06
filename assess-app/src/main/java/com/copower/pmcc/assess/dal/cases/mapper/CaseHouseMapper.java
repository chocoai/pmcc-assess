package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseMapper {
    int countByExample(CaseHouseExample example);

    int deleteByExample(CaseHouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouse record);

    int insertSelective(CaseHouse record);

    List<CaseHouse> selectByExample(CaseHouseExample example);

    CaseHouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouse record, @Param("example") CaseHouseExample example);

    int updateByExample(@Param("record") CaseHouse record, @Param("example") CaseHouseExample example);

    int updateByPrimaryKeySelective(CaseHouse record);

    int updateByPrimaryKey(CaseHouse record);
}