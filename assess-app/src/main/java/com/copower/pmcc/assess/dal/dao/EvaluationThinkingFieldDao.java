package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationThinkingField;
import com.copower.pmcc.assess.dal.entity.EvaluationThinkingFieldExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationThinkingFieldMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估技术思路 字段
 * Created by 13426 on 2018/4/26.
 */
@Repository(value = "evaluationThinkingFieldDao")
public class EvaluationThinkingFieldDao {

    @Autowired
    private EvaluationThinkingDao thinkingDao;

    @Autowired
    private EvaluationThinkingFieldMapper mapper;

    public EvaluationThinkingFieldDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<EvaluationThinkingField> schemeassistservice(Integer id){
        EvaluationThinkingDto dto = thinkingDao.get(id);
        EvaluationThinkingFieldExample example = new EvaluationThinkingFieldExample();
        example.createCriteria().andIdIsNotNull().andThinkingIdEqualTo(dto.getId());
        return mapper.selectByExample(example);
    }

    public List<EvaluationThinkingFieldDto> list(Integer thinkId){
        EvaluationThinkingFieldExample evaluationThinkingFieldExample = new EvaluationThinkingFieldExample();
        evaluationThinkingFieldExample.createCriteria().andIdIsNotNull().andThinkingIdEqualTo(thinkId);
        List<EvaluationThinkingField> evaluationThinkingFields = mapper.selectByExample(evaluationThinkingFieldExample);
        List<EvaluationThinkingFieldDto> evaluationThinkingFieldDtos = new ArrayList<>();
        evaluationThinkingFields.forEach(evaluationThinkingField -> {
            evaluationThinkingFieldDtos.add(change(evaluationThinkingField));
        });
        return evaluationThinkingFieldDtos;
    }

    public boolean add(EvaluationThinkingFieldDto evaluationThinkingFieldDto) {
        boolean flag = true;
        EvaluationThinkingFieldExample example = new EvaluationThinkingFieldExample();
        example.createCriteria().andNameEqualTo(evaluationThinkingFieldDto.getName());
        if (mapper.selectByExample(example).size()>0)flag = false;
        if (flag) return mapper.insertSelective(change(evaluationThinkingFieldDto)) == 1;
        return false;
    }

    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean removeMethod(Integer method) {
        EvaluationThinkingFieldExample evaluationThinkingFieldExample = new EvaluationThinkingFieldExample();
        if (!(method == null)) {
            evaluationThinkingFieldExample.createCriteria().andIdIsNotNull().andThinkingIdEqualTo(method);
            return mapper.deleteByExample(evaluationThinkingFieldExample)==1;
        }
        return false;
    }

    public boolean update(EvaluationThinkingFieldDto evaluationThinkingFieldDto){
        return mapper.updateByPrimaryKey(change(evaluationThinkingFieldDto))==1;
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
