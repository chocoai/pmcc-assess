package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EvaluationHypothesis;
import com.copower.pmcc.assess.dal.entity.EvaluationHypothesisExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationHypothesisMapper {
    int countByExample(EvaluationHypothesisExample example);

    int deleteByExample(EvaluationHypothesisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationHypothesis record);

    int insertSelective(EvaluationHypothesis record);

    List<EvaluationHypothesis> selectByExample(EvaluationHypothesisExample example);

    EvaluationHypothesis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationHypothesis record, @Param("example") EvaluationHypothesisExample example);

    int updateByExample(@Param("record") EvaluationHypothesis record, @Param("example") EvaluationHypothesisExample example);

    int updateByPrimaryKeySelective(EvaluationHypothesis record);

    int updateByPrimaryKey(EvaluationHypothesis record);
}