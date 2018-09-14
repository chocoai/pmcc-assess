package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseTradingMapper {
    int countByExample(CaseHouseTradingExample example);

    int deleteByExample(CaseHouseTradingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseTrading record);

    int insertSelective(CaseHouseTrading record);

    List<CaseHouseTrading> selectByExample(CaseHouseTradingExample example);

    CaseHouseTrading selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseTrading record, @Param("example") CaseHouseTradingExample example);

    int updateByExample(@Param("record") CaseHouseTrading record, @Param("example") CaseHouseTradingExample example);

    int updateByPrimaryKeySelective(CaseHouseTrading record);

    int updateByPrimaryKey(CaseHouseTrading record);
}