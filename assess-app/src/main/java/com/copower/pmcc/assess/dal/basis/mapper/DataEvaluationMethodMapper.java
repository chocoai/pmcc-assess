package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationMethod;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationMethodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataEvaluationMethodMapper {
    long countByExample(DataEvaluationMethodExample example);

    int deleteByExample(DataEvaluationMethodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataEvaluationMethod record);

    int insertSelective(DataEvaluationMethod record);

    List<DataEvaluationMethod> selectByExample(DataEvaluationMethodExample example);

    DataEvaluationMethod selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataEvaluationMethod record, @Param("example") DataEvaluationMethodExample example);

    int updateByExample(@Param("record") DataEvaluationMethod record, @Param("example") DataEvaluationMethodExample example);

    int updateByPrimaryKeySelective(DataEvaluationMethod record);

    int updateByPrimaryKey(DataEvaluationMethod record);
}