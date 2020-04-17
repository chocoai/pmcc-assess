package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicCommonQuoteFieldInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicCommonQuoteFieldInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicCommonQuoteFieldInfoMapper {
    int countByExample(BasicCommonQuoteFieldInfoExample example);

    int deleteByExample(BasicCommonQuoteFieldInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicCommonQuoteFieldInfo record);

    int insertSelective(BasicCommonQuoteFieldInfo record);

    List<BasicCommonQuoteFieldInfo> selectByExample(BasicCommonQuoteFieldInfoExample example);

    BasicCommonQuoteFieldInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicCommonQuoteFieldInfo record, @Param("example") BasicCommonQuoteFieldInfoExample example);

    int updateByExample(@Param("record") BasicCommonQuoteFieldInfo record, @Param("example") BasicCommonQuoteFieldInfoExample example);

    int updateByPrimaryKeySelective(BasicCommonQuoteFieldInfo record);

    int updateByPrimaryKey(BasicCommonQuoteFieldInfo record);
}