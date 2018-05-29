package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EvaluationThinking;
import com.copower.pmcc.assess.dal.entity.EvaluationThinkingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationThinkingMapper {
    int countByExample(EvaluationThinkingExample example);

    int deleteByExample(EvaluationThinkingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationThinking record);

    int insertSelective(EvaluationThinking record);

    List<EvaluationThinking> selectByExample(EvaluationThinkingExample example);

    EvaluationThinking selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationThinking record, @Param("example") EvaluationThinkingExample example);

    int updateByExample(@Param("record") EvaluationThinking record, @Param("example") EvaluationThinkingExample example);

    int updateByPrimaryKeySelective(EvaluationThinking record);

    int updateByPrimaryKey(EvaluationThinking record);
}