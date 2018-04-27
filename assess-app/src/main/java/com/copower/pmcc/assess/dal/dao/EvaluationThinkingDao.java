package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationThinking;
import com.copower.pmcc.assess.dal.entity.EvaluationThinkingExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationThinkingMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 13426 on 2018/4/26.
 */
@Repository(value = "evaluationThinkingDao")
public class EvaluationThinkingDao {

    @Autowired
    private EvaluationThinkingMapper evaluationThinkingMapper;

    public boolean add(EvaluationThinkingDto evaluationThinkingDto) {
        return evaluationThinkingMapper.insertSelective(change(evaluationThinkingDto)) == 1;
    }

    public boolean remove(Integer id){
        return evaluationThinkingMapper.deleteByPrimaryKey(id)==1;
    }

    public EvaluationThinkingDto get(Integer id){
        EvaluationThinking evaluationThinking = evaluationThinkingMapper.selectByPrimaryKey(id);
        return change(evaluationThinking);
    }

    public boolean update(EvaluationThinkingDto evaluationThinkingDto){
        return evaluationThinkingMapper.updateByPrimaryKey(change(evaluationThinkingDto))==1;
    }

    public List<EvaluationThinking> list(String method){
        EvaluationThinkingExample evaluationThinkingExample = new EvaluationThinkingExample();
        List<EvaluationThinking> evaluationThinkings = null;
        if (method==null || method==""){
            evaluationThinkingExample.createCriteria().andNameIsNotNull();
            evaluationThinkings = evaluationThinkingMapper.selectByExample(evaluationThinkingExample);
        }else {
            evaluationThinkingExample.createCriteria().andNameIsNotNull().andMethodLike("%"+method+"%");
            evaluationThinkings = evaluationThinkingMapper.selectByExample(evaluationThinkingExample);
        }
        return evaluationThinkings;
    }

    public EvaluationThinking change(EvaluationThinkingDto evaluationThinkingDto) {
        EvaluationThinking evaluationThinking = new EvaluationThinking();
        BeanUtils.copyProperties(evaluationThinkingDto, evaluationThinking);
        return evaluationThinking;
    }

    public EvaluationThinkingDto change(EvaluationThinking evaluationThinking){
        EvaluationThinkingDto evaluationThinkingDto = new EvaluationThinkingDto();
        BeanUtils.copyProperties(evaluationThinking, evaluationThinkingDto);
        return evaluationThinkingDto;
    }
}
