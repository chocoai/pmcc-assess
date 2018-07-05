package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.basis.entity.CsrBorrowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrBorrowerMapper {
    int countByExample(CsrBorrowerExample example);

    int deleteByExample(CsrBorrowerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrBorrower record);

    int insertSelective(CsrBorrower record);

    List<CsrBorrower> selectByExample(CsrBorrowerExample example);

    CsrBorrower selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrBorrower record, @Param("example") CsrBorrowerExample example);

    int updateByExample(@Param("record") CsrBorrower record, @Param("example") CsrBorrowerExample example);

    int updateByPrimaryKeySelective(CsrBorrower record);

    int updateByPrimaryKey(CsrBorrower record);
}