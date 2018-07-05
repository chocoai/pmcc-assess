package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasis;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasisExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationBasisMapper {
    int countByExample(EvaluationBasisExample example);

    int deleteByExample(EvaluationBasisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationBasis record);

    int insertSelective(EvaluationBasis record);

    List<EvaluationBasis> selectByExample(EvaluationBasisExample example);

    EvaluationBasis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationBasis record, @Param("example") EvaluationBasisExample example);

    int updateByExample(@Param("record") EvaluationBasis record, @Param("example") EvaluationBasisExample example);

    int updateByPrimaryKeySelective(EvaluationBasis record);

    int updateByPrimaryKey(EvaluationBasis record);
}