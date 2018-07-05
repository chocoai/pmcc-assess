package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethodField;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethodFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationMethodFieldMapper {
    int countByExample(EvaluationMethodFieldExample example);

    int deleteByExample(EvaluationMethodFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationMethodField record);

    int insertSelective(EvaluationMethodField record);

    List<EvaluationMethodField> selectByExample(EvaluationMethodFieldExample example);

    EvaluationMethodField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationMethodField record, @Param("example") EvaluationMethodFieldExample example);

    int updateByExample(@Param("record") EvaluationMethodField record, @Param("example") EvaluationMethodFieldExample example);

    int updateByPrimaryKeySelective(EvaluationMethodField record);

    int updateByPrimaryKey(EvaluationMethodField record);
}