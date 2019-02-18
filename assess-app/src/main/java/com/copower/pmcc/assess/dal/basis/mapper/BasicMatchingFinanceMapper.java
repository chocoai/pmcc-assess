package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingFinance;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingFinanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicMatchingFinanceMapper {
    int countByExample(BasicMatchingFinanceExample example);

    int deleteByExample(BasicMatchingFinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicMatchingFinance record);

    int insertSelective(BasicMatchingFinance record);

    List<BasicMatchingFinance> selectByExample(BasicMatchingFinanceExample example);

    BasicMatchingFinance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicMatchingFinance record, @Param("example") BasicMatchingFinanceExample example);

    int updateByExample(@Param("record") BasicMatchingFinance record, @Param("example") BasicMatchingFinanceExample example);

    int updateByPrimaryKeySelective(BasicMatchingFinance record);

    int updateByPrimaryKey(BasicMatchingFinance record);
}