package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EvaluationPrincipleField;
import com.copower.pmcc.assess.dal.entity.EvaluationPrincipleFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluationPrincipleFieldMapper {
    int countByExample(EvaluationPrincipleFieldExample example);

    int deleteByExample(EvaluationPrincipleFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationPrincipleField record);

    int insertSelective(EvaluationPrincipleField record);

    List<EvaluationPrincipleField> selectByExample(EvaluationPrincipleFieldExample example);

    EvaluationPrincipleField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationPrincipleField record, @Param("example") EvaluationPrincipleFieldExample example);

    int updateByExample(@Param("record") EvaluationPrincipleField record, @Param("example") EvaluationPrincipleFieldExample example);

    int updateByPrimaryKeySelective(EvaluationPrincipleField record);

    int updateByPrimaryKey(EvaluationPrincipleField record);
}