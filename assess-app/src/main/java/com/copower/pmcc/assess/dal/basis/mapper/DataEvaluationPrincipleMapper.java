package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrinciple;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrincipleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataEvaluationPrincipleMapper {
    int countByExample(DataEvaluationPrincipleExample example);

    int deleteByExample(DataEvaluationPrincipleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataEvaluationPrinciple record);

    int insertSelective(DataEvaluationPrinciple record);

    List<DataEvaluationPrinciple> selectByExample(DataEvaluationPrincipleExample example);

    DataEvaluationPrinciple selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataEvaluationPrinciple record, @Param("example") DataEvaluationPrincipleExample example);

    int updateByExample(@Param("record") DataEvaluationPrinciple record, @Param("example") DataEvaluationPrincipleExample example);

    int updateByPrimaryKeySelective(DataEvaluationPrinciple record);

    int updateByPrimaryKey(DataEvaluationPrinciple record);
}