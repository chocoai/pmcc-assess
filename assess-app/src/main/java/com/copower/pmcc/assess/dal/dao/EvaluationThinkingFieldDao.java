package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationThinkingField;
import com.copower.pmcc.assess.dal.entity.EvaluationThinkingFieldExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationThinkingFieldMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/4/26.
 */
@Repository(value = "evaluationThinkingFieldDao")
public class EvaluationThinkingFieldDao {

    @Autowired
    private EvaluationThinkingFieldMapper evaluationThinkingFieldMapper;

    public EvaluationThinkingFieldDto get(Integer id){
        return change(evaluationThinkingFieldMapper.selectByPrimaryKey(id));
    }

    public List<EvaluationThinkingFieldDto> list(Integer thinkId){
        EvaluationThinkingFieldExample evaluationThinkingFieldExample = new EvaluationThinkingFieldExample();
        evaluationThinkingFieldExample.createCriteria().andIdIsNotNull().andThinkingIdLessThanOrEqualTo(thinkId);
        List<EvaluationThinkingField> evaluationThinkingFields = evaluationThinkingFieldMapper.selectByExample(evaluationThinkingFieldExample);
        List<EvaluationThinkingFieldDto> evaluationThinkingFieldDtos = new ArrayList<>();
        evaluationThinkingFields.forEach(evaluationThinkingField -> {
            evaluationThinkingFieldDtos.add(change(evaluationThinkingField));
        });
        return evaluationThinkingFieldDtos;
    }

    public boolean add(EvaluationThinkingFieldDto evaluationThinkingFieldDto) {
        return evaluationThinkingFieldMapper.insertSelective(change(evaluationThinkingFieldDto)) == 1;
    }

    public boolean remove(Integer id) {
        return evaluationThinkingFieldMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean removeMethod(Integer method) {
        EvaluationThinkingFieldExample evaluationThinkingFieldExample = new EvaluationThinkingFieldExample();
        if (!(method == null)) {
            evaluationThinkingFieldExample.createCriteria().andIdIsNotNull().andThinkingIdEqualTo(method);
            return evaluationThinkingFieldMapper.deleteByExample(evaluationThinkingFieldExample)==1;
        }
        return false;
    }

    public boolean update(EvaluationThinkingFieldDto evaluationThinkingFieldDto){
        return evaluationThinkingFieldMapper.updateByPrimaryKey(change(evaluationThinkingFieldDto))==1;
    }

    public EvaluationThinkingField change(EvaluationThinkingFieldDto evaluationThinkingFieldDto) {
        EvaluationThinkingField evaluationThinkingField = new EvaluationThinkingField();
        BeanUtils.copyProperties(evaluationThinkingFieldDto, evaluationThinkingField);
        return evaluationThinkingField;
    }

    public EvaluationThinkingFieldDto change(EvaluationThinkingField evaluationThinkingField) {
        EvaluationThinkingFieldDto evaluationThinkingFieldDto = new EvaluationThinkingFieldDto();
        BeanUtils.copyProperties(evaluationThinkingField, evaluationThinkingFieldDto);
        return evaluationThinkingFieldDto;
    }
}
