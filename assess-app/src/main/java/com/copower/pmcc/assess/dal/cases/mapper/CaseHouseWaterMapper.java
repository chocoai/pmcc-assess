package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWater;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseWaterMapper {
    int countByExample(CaseHouseWaterExample example);

    int deleteByExample(CaseHouseWaterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseWater record);

    int insertSelective(CaseHouseWater record);

    List<CaseHouseWater> selectByExample(CaseHouseWaterExample example);

    CaseHouseWater selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseWater record, @Param("example") CaseHouseWaterExample example);

    int updateByExample(@Param("record") CaseHouseWater record, @Param("example") CaseHouseWaterExample example);

    int updateByPrimaryKeySelective(CaseHouseWater record);

    int updateByPrimaryKey(CaseHouseWater record);
}