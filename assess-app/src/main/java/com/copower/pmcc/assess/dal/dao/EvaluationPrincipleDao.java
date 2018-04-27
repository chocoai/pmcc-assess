package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationPrinciple;
import com.copower.pmcc.assess.dal.mapper.EvaluationPrincipleMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 13426 on 2018/4/27.
 */
@Repository
public class EvaluationPrincipleDao {

    @Autowired
    private EvaluationPrincipleMapper evaluationPrincipleMapper;

    public boolean add(EvaluationPrincipleDto evaluationPrincipleDto){
       return evaluationPrincipleMapper.insertSelective(change(evaluationPrincipleDto))==1;
    }

    public boolean remove(Integer id){
        return evaluationPrincipleMapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(EvaluationPrincipleDto evaluationPrincipleDto){
        return evaluationPrincipleMapper.updateByPrimaryKey(change(evaluationPrincipleDto))==1;
    }

    public EvaluationPrincipleDto get(Integer id){
        return change(evaluationPrincipleMapper.selectByPrimaryKey(id));
    }

    public EvaluationPrinciple change(EvaluationPrincipleDto evaluationPrincipleDto){
        EvaluationPrinciple evaluationPrinciple = new EvaluationPrinciple();
        BeanUtils.copyProperties(evaluationPrincipleDto, evaluationPrinciple);
        return evaluationPrinciple;
    }

    public EvaluationPrincipleDto change(EvaluationPrinciple evaluationPrinciple){
        EvaluationPrincipleDto evaluationPrincipleDto = new EvaluationPrincipleDto();
        BeanUtils.copyProperties(evaluationPrinciple, evaluationPrincipleDto);
        return evaluationPrincipleDto;
    }
}
