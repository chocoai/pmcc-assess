package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBouseWaterDrain;
import com.copower.pmcc.assess.dal.cases.entity.CaseBouseWaterDrainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBouseWaterDrainMapper {
    int countByExample(CaseBouseWaterDrainExample example);

    int deleteByExample(CaseBouseWaterDrainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBouseWaterDrain record);

    int insertSelective(CaseBouseWaterDrain record);

    List<CaseBouseWaterDrain> selectByExample(CaseBouseWaterDrainExample example);

    CaseBouseWaterDrain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBouseWaterDrain record, @Param("example") CaseBouseWaterDrainExample example);

    int updateByExample(@Param("record") CaseBouseWaterDrain record, @Param("example") CaseBouseWaterDrainExample example);

    int updateByPrimaryKeySelective(CaseBouseWaterDrain record);

    int updateByPrimaryKey(CaseBouseWaterDrain record);
}