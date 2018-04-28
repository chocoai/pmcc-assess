package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationHypothesisField;
import com.copower.pmcc.assess.dal.entity.EvaluationHypothesisFieldExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationHypothesisFieldMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationHypothesisFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/4/28.
 * 3.1.2.12	评估依据 字段
 */
@Repository
public class EvaluationHypothesisFieldDao {

    @Autowired
    private EvaluationHypothesisFieldMapper evaluationHypothesisFieldMapper;

    public boolean add(EvaluationHypothesisFieldDto evaluationHypothesisFieldDto){
        return evaluationHypothesisFieldMapper.insertSelective(change(evaluationHypothesisFieldDto))==1;
    }

    public boolean remove(Integer id){
        return evaluationHypothesisFieldMapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(EvaluationHypothesisFieldDto evaluationHypothesisFieldDto){
        return evaluationHypothesisFieldMapper.updateByPrimaryKey(change(evaluationHypothesisFieldDto))==1;
    }

    public EvaluationHypothesisFieldDto get(Integer id){
        return change(evaluationHypothesisFieldMapper.selectByPrimaryKey(id));
    }

    public List<EvaluationHypothesisFieldDto>  list(){
        EvaluationHypothesisFieldExample evaluationHypothesisFieldExample = new EvaluationHypothesisFieldExample();
        evaluationHypothesisFieldExample.createCriteria().andIdIsNotNull();
        List<EvaluationHypothesisFieldDto> evaluationHypothesisFieldDtos = new ArrayList<>();
        evaluationHypothesisFieldMapper.selectByExample(evaluationHypothesisFieldExample).stream().parallel().forEach(evaluationHypothesisField -> evaluationHypothesisFieldDtos.add(change(evaluationHypothesisField)));
        return evaluationHypothesisFieldDtos;
    }

    public List<EvaluationHypothesisFieldDto>  list(Integer hypothesisId){
        EvaluationHypothesisFieldExample evaluationHypothesisFieldExample = new EvaluationHypothesisFieldExample();
        evaluationHypothesisFieldExample.createCriteria().andIdIsNotNull().andHypothesisIdEqualTo(hypothesisId);
        List<EvaluationHypothesisFieldDto> evaluationHypothesisFieldDtos = new ArrayList<>();
        evaluationHypothesisFieldMapper.selectByExample(evaluationHypothesisFieldExample).stream().parallel().forEach(evaluationHypothesisField -> evaluationHypothesisFieldDtos.add(change(evaluationHypothesisField)));
        return evaluationHypothesisFieldDtos;
    }

    public EvaluationHypothesisFieldDto change(EvaluationHypothesisField evaluationHypothesisField){
        EvaluationHypothesisFieldDto evaluationHypothesisFieldDto = new EvaluationHypothesisFieldDto();
        BeanUtils.copyProperties(evaluationHypothesisField,evaluationHypothesisFieldDto);
        return  evaluationHypothesisFieldDto;
    }

    public EvaluationHypothesisField change(EvaluationHypothesisFieldDto evaluationHypothesisFieldDto){
        EvaluationHypothesisField evaluationhypothesisfield = new EvaluationHypothesisField();
        BeanUtils.copyProperties(evaluationHypothesisFieldDto,evaluationhypothesisfield);
        return  evaluationhypothesisfield;
    }
}
