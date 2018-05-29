package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EvaluationHypothesisField;
import com.copower.pmcc.assess.dal.entity.EvaluationHypothesisFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationHypothesisFieldMapper {
    int countByExample(EvaluationHypothesisFieldExample example);

    int deleteByExample(EvaluationHypothesisFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationHypothesisField record);

    int insertSelective(EvaluationHypothesisField record);

    List<EvaluationHypothesisField> selectByExample(EvaluationHypothesisFieldExample example);

    EvaluationHypothesisField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationHypothesisField record, @Param("example") EvaluationHypothesisFieldExample example);

    int updateByExample(@Param("record") EvaluationHypothesisField record, @Param("example") EvaluationHypothesisFieldExample example);

    int updateByPrimaryKeySelective(EvaluationHypothesisField record);

    int updateByPrimaryKey(EvaluationHypothesisField record);
}