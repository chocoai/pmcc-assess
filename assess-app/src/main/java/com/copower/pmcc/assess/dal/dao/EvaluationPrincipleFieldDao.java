package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationPrincipleField;
import com.copower.pmcc.assess.dal.entity.EvaluationPrincipleFieldExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationPrincipleFieldMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.2.11	评估原则 字段
 * Created by 13426 on 2018/4/27.
 */
@Repository
public class EvaluationPrincipleFieldDao {

    @Autowired
    private EvaluationPrincipleFieldMapper mapper;

    public boolean add(EvaluationPrincipleFieldDto evaluationPrincipleFieldDto){
        boolean flag = true;
        EvaluationPrincipleFieldExample example = new EvaluationPrincipleFieldExample();
        example.createCriteria().andNameEqualTo(evaluationPrincipleFieldDto.getName());
        if (mapper.selectByExample(example).size()>0)flag = false;
        if (flag) return mapper.insertSelective(change(evaluationPrincipleFieldDto))==1;
        return false;
    }

    public boolean update(EvaluationPrincipleFieldDto evaluationPrincipleFieldDto){
        return mapper.updateByPrimaryKey(change(evaluationPrincipleFieldDto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public EvaluationPrincipleFieldDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<EvaluationPrincipleFieldDto> list(){
        EvaluationPrincipleFieldExample evaluationPrincipleFieldExample = new EvaluationPrincipleFieldExample();
        evaluationPrincipleFieldExample.createCriteria().andIdIsNotNull();
        List<EvaluationPrincipleFieldDto> evaluationPrincipleFieldDtos = new ArrayList<>();
        mapper.selectByExample(evaluationPrincipleFieldExample).stream().parallel().forEach(evaluationPrincipleField -> evaluationPrincipleFieldDtos.add(change(evaluationPrincipleField)));
        return evaluationPrincipleFieldDtos;
    }

    public List<EvaluationPrincipleFieldDto> list(Integer principleId){
        EvaluationPrincipleFieldExample evaluationPrincipleFieldExample = new EvaluationPrincipleFieldExample();
        evaluationPrincipleFieldExample.createCriteria().andPrincipleIdEqualTo(principleId);
        List<EvaluationPrincipleFieldDto> evaluationPrincipleFieldDtos = new ArrayList<>();
        mapper.selectByExample(evaluationPrincipleFieldExample).stream().parallel().forEach(evaluationPrincipleField -> evaluationPrincipleFieldDtos.add(change(evaluationPrincipleField)));
        return evaluationPrincipleFieldDtos;
    }

    private EvaluationPrincipleField change(EvaluationPrincipleFieldDto evaluationPrincipleFieldDto){
        EvaluationPrincipleField evaluationPrincipleField = new EvaluationPrincipleField();
        BeanUtils.copyProperties(evaluationPrincipleFieldDto,evaluationPrincipleField);
        return evaluationPrincipleField;
    }

    private EvaluationPrincipleFieldDto change(EvaluationPrincipleField evaluationPrincipleField){
        EvaluationPrincipleFieldDto evaluationPrincipleFieldDto1 = new EvaluationPrincipleFieldDto();
        BeanUtils.copyProperties(evaluationPrincipleField,evaluationPrincipleFieldDto1);
        return evaluationPrincipleFieldDto1;
    }
}
