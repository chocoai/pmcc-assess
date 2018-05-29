package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EvaluationPrinciple;
import com.copower.pmcc.assess.dal.entity.EvaluationPrincipleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationPrincipleMapper {
    int countByExample(EvaluationPrincipleExample example);

    int deleteByExample(EvaluationPrincipleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationPrinciple record);

    int insertSelective(EvaluationPrinciple record);

    List<EvaluationPrinciple> selectByExample(EvaluationPrincipleExample example);

    EvaluationPrinciple selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationPrinciple record, @Param("example") EvaluationPrincipleExample example);

    int updateByExample(@Param("record") EvaluationPrinciple record, @Param("example") EvaluationPrincipleExample example);

    int updateByPrimaryKeySelective(EvaluationPrinciple record);

    int updateByPrimaryKey(EvaluationPrinciple record);
}