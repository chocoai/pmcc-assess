package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationHypothesis;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationHypothesisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataEvaluationHypothesisMapper {
    int countByExample(DataEvaluationHypothesisExample example);

    int deleteByExample(DataEvaluationHypothesisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataEvaluationHypothesis record);

    int insertSelective(DataEvaluationHypothesis record);

    List<DataEvaluationHypothesis> selectByExample(DataEvaluationHypothesisExample example);

    DataEvaluationHypothesis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataEvaluationHypothesis record, @Param("example") DataEvaluationHypothesisExample example);

    int updateByExample(@Param("record") DataEvaluationHypothesis record, @Param("example") DataEvaluationHypothesisExample example);

    int updateByPrimaryKeySelective(DataEvaluationHypothesis record);

    int updateByPrimaryKey(DataEvaluationHypothesis record);
}