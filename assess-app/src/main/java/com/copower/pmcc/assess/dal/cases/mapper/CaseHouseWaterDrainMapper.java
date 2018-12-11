package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterDrain;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterDrainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseWaterDrainMapper {
    int countByExample(CaseHouseWaterDrainExample example);

    int deleteByExample(CaseHouseWaterDrainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseWaterDrain record);

    int insertSelective(CaseHouseWaterDrain record);

    List<CaseHouseWaterDrain> selectByExample(CaseHouseWaterDrainExample example);

    CaseHouseWaterDrain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseWaterDrain record, @Param("example") CaseHouseWaterDrainExample example);

    int updateByExample(@Param("record") CaseHouseWaterDrain record, @Param("example") CaseHouseWaterDrainExample example);

    int updateByPrimaryKeySelective(CaseHouseWaterDrain record);

    int updateByPrimaryKey(CaseHouseWaterDrain record);
}