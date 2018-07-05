package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationThinking;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationThinkingExample;
import com.copower.pmcc.assess.dal.basis.mapper.EvaluationThinkingMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Repository(value = "evaluationThinkingDao")
public class EvaluationThinkingDao {

    @Autowired
    private EvaluationThinkingMapper evaluationThinkingMapper;

    public boolean add(EvaluationThinkingDto evaluationThinkingDto) {
        return evaluationThinkingMapper.insertSelective(change(evaluationThinkingDto)) == 1;
    }

    public int save(EvaluationThinkingDto dto){
        EvaluationThinking thinking = change(dto);
        evaluationThinkingMapper.insertSelective(thinking);
        return thinking.getId();
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

    public List<EvaluationThinking> list(String name){
        EvaluationThinkingExample evaluationThinkingExample = new EvaluationThinkingExample();
        List<EvaluationThinking> evaluationThinkings = null;
        if (name ==null || name ==""){
            evaluationThinkingExample.createCriteria().andIdIsNotNull();
            evaluationThinkings = evaluationThinkingMapper.selectByExample(evaluationThinkingExample);
        }else {
            evaluationThinkingExample.createCriteria().andNameLike("%"+ name +"%");
            evaluationThinkings = evaluationThinkingMapper.selectByExample(evaluationThinkingExample);
        }
        return evaluationThinkings;
    }

    /**
     * 根据评估方法获取匹配的评估思路
     * @param method
     * @return
     */
    public List<EvaluationThinking> getListByMethod(Integer method){
        EvaluationThinkingExample example = new EvaluationThinkingExample();
        EvaluationThinkingExample.Criteria criteria = example.createCriteria();
        criteria.andMethodLike(String.format("%s,%s,%s","%",String.valueOf(method),"%"));
        return evaluationThinkingMapper.selectByExample(example);
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
