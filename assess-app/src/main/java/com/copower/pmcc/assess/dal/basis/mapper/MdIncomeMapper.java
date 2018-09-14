package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncome;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeMapper {
    int countByExample(MdIncomeExample example);

    int deleteByExample(MdIncomeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncome record);

    int insertSelective(MdIncome record);

    List<MdIncome> selectByExample(MdIncomeExample example);

    MdIncome selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncome record, @Param("example") MdIncomeExample example);

    int updateByExample(@Param("record") MdIncome record, @Param("example") MdIncomeExample example);

    int updateByPrimaryKeySelective(MdIncome record);

    int updateByPrimaryKey(MdIncome record);
}