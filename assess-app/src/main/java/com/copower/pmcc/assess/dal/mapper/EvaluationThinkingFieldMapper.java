package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EvaluationThinkingField;
import com.copower.pmcc.assess.dal.entity.EvaluationThinkingFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationThinkingFieldMapper {
    int countByExample(EvaluationThinkingFieldExample example);

    int deleteByExample(EvaluationThinkingFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationThinkingField record);

    int insertSelective(EvaluationThinkingField record);

    List<EvaluationThinkingField> selectByExample(EvaluationThinkingFieldExample example);

    EvaluationThinkingField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationThinkingField record, @Param("example") EvaluationThinkingFieldExample example);

    int updateByExample(@Param("record") EvaluationThinkingField record, @Param("example") EvaluationThinkingFieldExample example);

    int updateByPrimaryKeySelective(EvaluationThinkingField record);

    int updateByPrimaryKey(EvaluationThinkingField record);
}