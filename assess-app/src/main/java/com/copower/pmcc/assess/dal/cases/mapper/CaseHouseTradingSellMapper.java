package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingSell;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingSellExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseTradingSellMapper {
    int countByExample(CaseHouseTradingSellExample example);

    int deleteByExample(CaseHouseTradingSellExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseTradingSell record);

    int insertSelective(CaseHouseTradingSell record);

    List<CaseHouseTradingSell> selectByExample(CaseHouseTradingSellExample example);

    CaseHouseTradingSell selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseTradingSell record, @Param("example") CaseHouseTradingSellExample example);

    int updateByExample(@Param("record") CaseHouseTradingSell record, @Param("example") CaseHouseTradingSellExample example);

    int updateByPrimaryKeySelective(CaseHouseTradingSell record);

    int updateByPrimaryKey(CaseHouseTradingSell record);
}