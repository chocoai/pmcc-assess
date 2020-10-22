package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicAlternativeCaseMapper {
    long countByExample(BasicAlternativeCaseExample example);

    int deleteByExample(BasicAlternativeCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicAlternativeCase record);

    int insertSelective(BasicAlternativeCase record);

    List<BasicAlternativeCase> selectByExample(BasicAlternativeCaseExample example);

    BasicAlternativeCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicAlternativeCase record, @Param("example") BasicAlternativeCaseExample example);

    int updateByExample(@Param("record") BasicAlternativeCase record, @Param("example") BasicAlternativeCaseExample example);

    int updateByPrimaryKeySelective(BasicAlternativeCase record);

    int updateByPrimaryKey(BasicAlternativeCase record);
}