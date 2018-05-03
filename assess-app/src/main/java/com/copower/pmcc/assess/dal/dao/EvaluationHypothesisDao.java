package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationHypothesis;
import com.copower.pmcc.assess.dal.entity.EvaluationHypothesisExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationHypothesisMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationHypothesisDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.2.12	评估假设
 * Created by 13426 on 2018/4/28.
 */
@Repository
public class EvaluationHypothesisDao {

    @Autowired
    private EvaluationHypothesisMapper evaluationHypothesisMapper;

    public boolean add(EvaluationHypothesisDto evaluationHypothesisDto) {
        return evaluationHypothesisMapper.insert(change(evaluationHypothesisDto)) == 1;
    }

    public boolean update(EvaluationHypothesisDto evaluationHypothesisDto) {
        return evaluationHypothesisMapper.updateByPrimaryKey(change(evaluationHypothesisDto)) == 1;
    }

    public List<EvaluationHypothesisDto> list(String name) {
        List<EvaluationHypothesisDto> evaluationHypothesisDtos = new ArrayList<>();
        EvaluationHypothesisExample evaluationHypothesisExample = new EvaluationHypothesisExample();
        String methodStr ="%"+ name + "%";
        if (name == null || name=="") {
            evaluationHypothesisExample.createCriteria().andIdIsNotNull();
            evaluationHypothesisMapper.selectByExample(evaluationHypothesisExample).stream().parallel().forEach(evaluationHypothesis -> evaluationHypothesisDtos.add(change(evaluationHypothesis)));
        }else {
            evaluationHypothesisExample.createCriteria().andNameLike(methodStr);
            evaluationHypothesisMapper.selectByExample(evaluationHypothesisExample).stream().parallel().forEach(evaluationHypothesis -> evaluationHypothesisDtos.add(change(evaluationHypothesis)));
        }
        return evaluationHypothesisDtos;
    }

    public boolean remove(Integer id) {
        return evaluationHypothesisMapper.deleteByPrimaryKey(id) == 1;
    }

    public EvaluationHypothesisDto get(Integer id) {
        return change(evaluationHypothesisMapper.selectByPrimaryKey(id));
    }

    private EvaluationHypothesis change(EvaluationHypothesisDto evaluationHypothesisDto) {
        EvaluationHypothesis evaluationHypothesis = new EvaluationHypothesis();
        BeanUtils.copyProperties(evaluationHypothesisDto, evaluationHypothesis);
        return evaluationHypothesis;
    }

    private EvaluationHypothesisDto change(EvaluationHypothesis evaluationHypothesis) {
        EvaluationHypothesisDto evaluationHypothesisDto = new EvaluationHypothesisDto();
        BeanUtils.copyProperties(evaluationHypothesis, evaluationHypothesisDto);
        return evaluationHypothesisDto;
    }
}
