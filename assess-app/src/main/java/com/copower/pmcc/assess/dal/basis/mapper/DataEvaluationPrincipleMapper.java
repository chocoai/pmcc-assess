package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrinciple;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrincipleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataEvaluationPrincipleMapper {
    int countByExample(DataEvaluationPrincipleExample example);

    int deleteByExample(DataEvaluationPrincipleExample example);

    int insert(DataEvaluationPrinciple record);

    int insertSelective(DataEvaluationPrinciple record);

    List<DataEvaluationPrinciple> selectByExample(DataEvaluationPrincipleExample example);

    int updateByExampleSelective(@Param("record") DataEvaluationPrinciple record, @Param("example") DataEvaluationPrincipleExample example);

    int updateByExample(@Param("record") DataEvaluationPrinciple record, @Param("example") DataEvaluationPrincipleExample example);
}