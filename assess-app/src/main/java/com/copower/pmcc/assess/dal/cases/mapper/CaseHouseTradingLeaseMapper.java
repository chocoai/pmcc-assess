package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingLease;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingLeaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseTradingLeaseMapper {
    int countByExample(CaseHouseTradingLeaseExample example);

    int deleteByExample(CaseHouseTradingLeaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseTradingLease record);

    int insertSelective(CaseHouseTradingLease record);

    List<CaseHouseTradingLease> selectByExample(CaseHouseTradingLeaseExample example);

    CaseHouseTradingLease selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseTradingLease record, @Param("example") CaseHouseTradingLeaseExample example);

    int updateByExample(@Param("record") CaseHouseTradingLease record, @Param("example") CaseHouseTradingLeaseExample example);

    int updateByPrimaryKeySelective(CaseHouseTradingLease record);

    int updateByPrimaryKey(CaseHouseTradingLease record);
}