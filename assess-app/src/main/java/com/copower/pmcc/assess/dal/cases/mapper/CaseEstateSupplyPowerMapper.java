package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyPower;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyPowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateSupplyPowerMapper {
    int countByExample(CaseEstateSupplyPowerExample example);

    int deleteByExample(CaseEstateSupplyPowerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateSupplyPower record);

    int insertSelective(CaseEstateSupplyPower record);

    List<CaseEstateSupplyPower> selectByExample(CaseEstateSupplyPowerExample example);

    CaseEstateSupplyPower selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateSupplyPower record, @Param("example") CaseEstateSupplyPowerExample example);

    int updateByExample(@Param("record") CaseEstateSupplyPower record, @Param("example") CaseEstateSupplyPowerExample example);

    int updateByPrimaryKeySelective(CaseEstateSupplyPower record);

    int updateByPrimaryKey(CaseEstateSupplyPower record);
}