package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMarket;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMarketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingMarketMapper {
    int countByExample(CaseMatchingMarketExample example);

    int deleteByExample(CaseMatchingMarketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingMarket record);

    int insertSelective(CaseMatchingMarket record);

    List<CaseMatchingMarket> selectByExample(CaseMatchingMarketExample example);

    CaseMatchingMarket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingMarket record, @Param("example") CaseMatchingMarketExample example);

    int updateByExample(@Param("record") CaseMatchingMarket record, @Param("example") CaseMatchingMarketExample example);

    int updateByPrimaryKeySelective(CaseMatchingMarket record);

    int updateByPrimaryKey(CaseMatchingMarket record);
}