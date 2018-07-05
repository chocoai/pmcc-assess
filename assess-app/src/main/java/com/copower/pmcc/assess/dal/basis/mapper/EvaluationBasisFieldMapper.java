package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasisField;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasisFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationBasisFieldMapper {
    int countByExample(EvaluationBasisFieldExample example);

    int deleteByExample(EvaluationBasisFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationBasisField record);

    int insertSelective(EvaluationBasisField record);

    List<EvaluationBasisField> selectByExample(EvaluationBasisFieldExample example);

    EvaluationBasisField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationBasisField record, @Param("example") EvaluationBasisFieldExample example);

    int updateByExample(@Param("record") EvaluationBasisField record, @Param("example") EvaluationBasisFieldExample example);

    int updateByPrimaryKeySelective(EvaluationBasisField record);

    int updateByPrimaryKey(EvaluationBasisField record);
}