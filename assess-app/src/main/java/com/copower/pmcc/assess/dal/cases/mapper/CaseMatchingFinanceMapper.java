package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingFinance;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingFinanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingFinanceMapper {
    int countByExample(CaseMatchingFinanceExample example);

    int deleteByExample(CaseMatchingFinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingFinance record);

    int insertSelective(CaseMatchingFinance record);

    List<CaseMatchingFinance> selectByExample(CaseMatchingFinanceExample example);

    CaseMatchingFinance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingFinance record, @Param("example") CaseMatchingFinanceExample example);

    int updateByExample(@Param("record") CaseMatchingFinance record, @Param("example") CaseMatchingFinanceExample example);

    int updateByPrimaryKeySelective(CaseMatchingFinance record);

    int updateByPrimaryKey(CaseMatchingFinance record);
}