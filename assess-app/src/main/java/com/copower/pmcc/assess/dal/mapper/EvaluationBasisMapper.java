package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EvaluationBasis;
import com.copower.pmcc.assess.dal.entity.EvaluationBasisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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