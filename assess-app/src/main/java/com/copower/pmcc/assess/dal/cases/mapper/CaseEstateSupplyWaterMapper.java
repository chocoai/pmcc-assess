package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyWater;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyWaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateSupplyWaterMapper {
    int countByExample(CaseEstateSupplyWaterExample example);

    int deleteByExample(CaseEstateSupplyWaterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateSupplyWater record);

    int insertSelective(CaseEstateSupplyWater record);

    List<CaseEstateSupplyWater> selectByExample(CaseEstateSupplyWaterExample example);

    CaseEstateSupplyWater selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateSupplyWater record, @Param("example") CaseEstateSupplyWaterExample example);

    int updateByExample(@Param("record") CaseEstateSupplyWater record, @Param("example") CaseEstateSupplyWaterExample example);

    int updateByPrimaryKeySelective(CaseEstateSupplyWater record);

    int updateByPrimaryKey(CaseEstateSupplyWater record);
}