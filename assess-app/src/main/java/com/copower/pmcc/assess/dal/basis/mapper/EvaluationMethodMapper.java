package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethod;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethodExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationMethodMapper {
    int countByExample(EvaluationMethodExample example);

    int deleteByExample(EvaluationMethodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationMethod record);

    int insertSelective(EvaluationMethod record);

    List<EvaluationMethod> selectByExample(EvaluationMethodExample example);

    EvaluationMethod selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationMethod record, @Param("example") EvaluationMethodExample example);

    int updateByExample(@Param("record") EvaluationMethod record, @Param("example") EvaluationMethodExample example);

    int updateByPrimaryKeySelective(EvaluationMethod record);

    int updateByPrimaryKey(EvaluationMethod record);
}