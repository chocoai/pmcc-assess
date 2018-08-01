package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationThinking;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationThinkingExample;
import com.copower.pmcc.assess.dal.basis.mapper.EvaluationThinkingMapper;
import org.apache.commons.lang3.StringUtils;
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

    public boolean addThinking(EvaluationThinking evaluationThinking) {
        return evaluationThinkingMapper.insertSelective(evaluationThinking) == 1;
    }


    public boolean updateThinking(EvaluationThinking evaluationThinking) {
        return evaluationThinkingMapper.updateByPrimaryKey(evaluationThinking) == 1;
    }

    public List<EvaluationThinking> getThinkingList(String name) {
        EvaluationThinkingExample example = new EvaluationThinkingExample();
        EvaluationThinkingExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike(String.format("%%%s%%",name));
        }
        return evaluationThinkingMapper.selectByExample(example);
    }

    public List<EvaluationThinking> getThinkingListByMethod(String method) {
        EvaluationThinkingExample example = new EvaluationThinkingExample();
        EvaluationThinkingExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(method)){
            criteria.andMethodLike(String.format("%%%s%%",method));
        }
        return evaluationThinkingMapper.selectByExample(example);
    }

    public boolean removeThinking(Integer id) {
        return evaluationThinkingMapper.deleteByPrimaryKey(id) == 1;
    }

    public EvaluationThinking getThinking(Integer id) {
        return evaluationThinkingMapper.selectByPrimaryKey(id);
    }
}
