package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationThinkingField;
import com.copower.pmcc.assess.dal.entity.EvaluationThinkingFieldExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationThinkingFieldMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 13426 on 2018/4/26.
 */
@Repository
public class EvaluationThinkingFieldDao {

    @Autowired
    private EvaluationThinkingFieldMapper evaluationThinkingFieldMapper;

    public boolean add(EvaluationThinkingFieldDao evaluationThinkingFieldDao) {
        return evaluationThinkingFieldMapper.insertSelective(change(evaluationThinkingFieldDao)) == 1;
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

    public boolean update(EvaluationThinkingFieldDao evaluationThinkingFieldDao){
        return evaluationThinkingFieldMapper.updateByPrimaryKey(change(evaluationThinkingFieldDao))==1;
    }

    public EvaluationThinkingField change(EvaluationThinkingFieldDao evaluationThinkingFieldDao) {
        EvaluationThinkingField evaluationThinkingField = new EvaluationThinkingField();
        BeanUtils.copyProperties(evaluationThinkingFieldDao, evaluationThinkingField);
        return evaluationThinkingField;
    }

    public EvaluationThinkingFieldDao change(EvaluationThinkingField evaluationThinkingField) {
        EvaluationThinkingFieldDao evaluationThinkingFieldDao = new EvaluationThinkingFieldDao();
        BeanUtils.copyProperties(evaluationThinkingField, evaluationThinkingFieldDao);
        return evaluationThinkingFieldDao;
    }
}
