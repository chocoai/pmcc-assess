package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseTradingMapper {
    long countByExample(BasicHouseTradingExample example);

    int deleteByExample(BasicHouseTradingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseTrading record);

    int insertSelective(BasicHouseTrading record);

    List<BasicHouseTrading> selectByExample(BasicHouseTradingExample example);

    BasicHouseTrading selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseTrading record, @Param("example") BasicHouseTradingExample example);

    int updateByExample(@Param("record") BasicHouseTrading record, @Param("example") BasicHouseTradingExample example);

    int updateByPrimaryKeySelective(BasicHouseTrading record);

    int updateByPrimaryKey(BasicHouseTrading record);
}