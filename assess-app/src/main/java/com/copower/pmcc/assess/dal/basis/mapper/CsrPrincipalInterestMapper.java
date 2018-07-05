package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrPrincipalInterest;
import com.copower.pmcc.assess.dal.basis.entity.CsrPrincipalInterestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrPrincipalInterestMapper {
    int countByExample(CsrPrincipalInterestExample example);

    int deleteByExample(CsrPrincipalInterestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrPrincipalInterest record);

    int insertSelective(CsrPrincipalInterest record);

    List<CsrPrincipalInterest> selectByExample(CsrPrincipalInterestExample example);

    CsrPrincipalInterest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrPrincipalInterest record, @Param("example") CsrPrincipalInterestExample example);

    int updateByExample(@Param("record") CsrPrincipalInterest record, @Param("example") CsrPrincipalInterestExample example);

    int updateByPrimaryKeySelective(CsrPrincipalInterest record);

    int updateByPrimaryKey(CsrPrincipalInterest record);
}