package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrBorrowerMortgageMapper {
    int countByExample(CsrBorrowerMortgageExample example);

    int deleteByExample(CsrBorrowerMortgageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrBorrowerMortgage record);

    int insertSelective(CsrBorrowerMortgage record);

    List<CsrBorrowerMortgage> selectByExample(CsrBorrowerMortgageExample example);

    CsrBorrowerMortgage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrBorrowerMortgage record, @Param("example") CsrBorrowerMortgageExample example);

    int updateByExample(@Param("record") CsrBorrowerMortgage record, @Param("example") CsrBorrowerMortgageExample example);

    int updateByPrimaryKeySelective(CsrBorrowerMortgage record);

    int updateByPrimaryKey(CsrBorrowerMortgage record);
}