package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLeaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseTradingLeaseMapper {
    long countByExample(BasicHouseTradingLeaseExample example);

    int deleteByExample(BasicHouseTradingLeaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseTradingLease record);

    int insertSelective(BasicHouseTradingLease record);

    List<BasicHouseTradingLease> selectByExample(BasicHouseTradingLeaseExample example);

    BasicHouseTradingLease selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseTradingLease record, @Param("example") BasicHouseTradingLeaseExample example);

    int updateByExample(@Param("record") BasicHouseTradingLease record, @Param("example") BasicHouseTradingLeaseExample example);

    int updateByPrimaryKeySelective(BasicHouseTradingLease record);

    int updateByPrimaryKey(BasicHouseTradingLease record);
}