package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationThinking;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationThinkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataEvaluationThinkingMapper {
    int countByExample(DataEvaluationThinkingExample example);

    int deleteByExample(DataEvaluationThinkingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataEvaluationThinking record);

    int insertSelective(DataEvaluationThinking record);

    List<DataEvaluationThinking> selectByExample(DataEvaluationThinkingExample example);

    DataEvaluationThinking selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataEvaluationThinking record, @Param("example") DataEvaluationThinkingExample example);

    int updateByExample(@Param("record") DataEvaluationThinking record, @Param("example") DataEvaluationThinkingExample example);

    int updateByPrimaryKeySelective(DataEvaluationThinking record);

    int updateByPrimaryKey(DataEvaluationThinking record);
}