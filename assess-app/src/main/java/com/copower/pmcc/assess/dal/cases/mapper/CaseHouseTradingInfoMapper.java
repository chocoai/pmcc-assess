package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingInfo;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseTradingInfoMapper {
    int countByExample(CaseHouseTradingInfoExample example);

    int deleteByExample(CaseHouseTradingInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseTradingInfo record);

    int insertSelective(CaseHouseTradingInfo record);

    List<CaseHouseTradingInfo> selectByExample(CaseHouseTradingInfoExample example);

    CaseHouseTradingInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseTradingInfo record, @Param("example") CaseHouseTradingInfoExample example);

    int updateByExample(@Param("record") CaseHouseTradingInfo record, @Param("example") CaseHouseTradingInfoExample example);

    int updateByPrimaryKeySelective(CaseHouseTradingInfo record);

    int updateByPrimaryKey(CaseHouseTradingInfo record);
}