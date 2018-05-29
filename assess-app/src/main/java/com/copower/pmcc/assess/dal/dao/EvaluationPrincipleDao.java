package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationPrinciple;
import com.copower.pmcc.assess.dal.entity.EvaluationPrincipleExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationPrincipleMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.2.11	评估原则
 * Created by 13426 on 2018/4/27.
 */
@Repository
public class EvaluationPrincipleDao {

    @Autowired
    private EvaluationPrincipleMapper mapper;

    public boolean add(EvaluationPrincipleDto evaluationPrincipleDto) {
        return mapper.insertSelective(change(evaluationPrincipleDto)) == 1;
    }

    public int save(EvaluationPrincipleDto evaluationPrincipleDto){
        EvaluationPrinciple evaluationPrinciple = change(evaluationPrincipleDto);
        mapper.insertSelective(evaluationPrinciple);
        return evaluationPrinciple.getId();
    }


    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(EvaluationPrincipleDto evaluationPrincipleDto) {
        return mapper.updateByPrimaryKey(change(evaluationPrincipleDto)) == 1;
    }

    public List<EvaluationPrincipleDto> list(String name) {
        String methodStr = "%"+name + "%";
        List<EvaluationPrinciple> evaluationPrinciples = null;
        EvaluationPrincipleExample evaluationPrincipleExample = new EvaluationPrincipleExample();
        if (name == null || name=="") {
            evaluationPrincipleExample.createCriteria().andIdIsNotNull();
            evaluationPrinciples = mapper.selectByExample(evaluationPrincipleExample);
            return change(evaluationPrinciples);
        } else {
            evaluationPrincipleExample.createCriteria().andNameLike(methodStr);
            evaluationPrinciples = mapper.selectByExample(evaluationPrincipleExample);
            return change(evaluationPrinciples);
        }
    }

    public EvaluationPrincipleDto get(Integer id) {
        return change(mapper.selectByPrimaryKey(id));
    }

    public EvaluationPrinciple change(EvaluationPrincipleDto evaluationPrincipleDto) {
        EvaluationPrinciple evaluationPrinciple = new EvaluationPrinciple();
        BeanUtils.copyProperties(evaluationPrincipleDto, evaluationPrinciple);
        return evaluationPrinciple;
    }

    public EvaluationPrincipleDto change(EvaluationPrinciple evaluationPrinciple) {
        EvaluationPrincipleDto evaluationPrincipleDto = new EvaluationPrincipleDto();
        BeanUtils.copyProperties(evaluationPrinciple, evaluationPrincipleDto);
        return evaluationPrincipleDto;
    }

    public List<EvaluationPrincipleDto> change(List<EvaluationPrinciple> evaluationPrinciples) {
        List<EvaluationPrincipleDto> evaluationPrincipleDtos = new ArrayList<>();
        evaluationPrinciples.forEach(evaluationPrinciple -> evaluationPrincipleDtos.add(change(evaluationPrinciple)));
        return evaluationPrincipleDtos;
    }
}
