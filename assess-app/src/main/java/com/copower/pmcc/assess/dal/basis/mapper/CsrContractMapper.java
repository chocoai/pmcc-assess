package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrContract;
import com.copower.pmcc.assess.dal.basis.entity.CsrContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrContractMapper {
    int countByExample(CsrContractExample example);

    int deleteByExample(CsrContractExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrContract record);

    int insertSelective(CsrContract record);

    List<CsrContract> selectByExample(CsrContractExample example);

    CsrContract selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrContract record, @Param("example") CsrContractExample example);

    int updateByExample(@Param("record") CsrContract record, @Param("example") CsrContractExample example);

    int updateByPrimaryKeySelective(CsrContract record);

    int updateByPrimaryKey(CsrContract record);
}