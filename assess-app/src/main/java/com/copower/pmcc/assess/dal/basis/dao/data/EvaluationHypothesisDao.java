package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationHypothesis;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationHypothesisExample;
import com.copower.pmcc.assess.dal.basis.mapper.EvaluationHypothesisMapper;
import org.apache.commons.lang3.StringUtils;
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

    public boolean addHypothesis(EvaluationHypothesis evaluationHypothesis) {
        return evaluationHypothesisMapper.insertSelective(evaluationHypothesis) == 1;
    }


    public boolean updateHypothesis(EvaluationHypothesis evaluationHypothesis) {
        return evaluationHypothesisMapper.updateByPrimaryKey(evaluationHypothesis) == 1;
    }

    public List<EvaluationHypothesis> getHypothesisList(String name) {
        EvaluationHypothesisExample example = new EvaluationHypothesisExample();
        EvaluationHypothesisExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike(String.format("%%%s%%",name));
        }
        return evaluationHypothesisMapper.selectByExample(example);
    }

    public List<EvaluationHypothesis> getHypothesisList(String method,String purpose) {
        EvaluationHypothesisExample example = new EvaluationHypothesisExample();
        EvaluationHypothesisExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(method)){
            criteria.andMethodLike(String.format("%%%s%%",method));
        }
        if(StringUtils.isNotBlank(purpose)){
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%",purpose));
        }
        return evaluationHypothesisMapper.selectByExample(example);
    }

    public boolean removeHypothesis(Integer id) {
        return evaluationHypothesisMapper.deleteByPrimaryKey(id) == 1;
    }

    public EvaluationHypothesis getHypothesis(Integer id) {
        return evaluationHypothesisMapper.selectByPrimaryKey(id);
    }

}
