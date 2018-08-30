package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationBasis;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationBasisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataEvaluationBasisMapper {
    int countByExample(DataEvaluationBasisExample example);

    int deleteByExample(DataEvaluationBasisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataEvaluationBasis record);

    int insertSelective(DataEvaluationBasis record);

    List<DataEvaluationBasis> selectByExample(DataEvaluationBasisExample example);

    DataEvaluationBasis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataEvaluationBasis record, @Param("example") DataEvaluationBasisExample example);

    int updateByExample(@Param("record") DataEvaluationBasis record, @Param("example") DataEvaluationBasisExample example);

    int updateByPrimaryKeySelective(DataEvaluationBasis record);

    int updateByPrimaryKey(DataEvaluationBasis record);
}