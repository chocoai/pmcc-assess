package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationPrinciple;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationPrincipleExample;
import com.copower.pmcc.assess.dal.basis.mapper.EvaluationPrincipleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 3.1.2.11	评估原则
 * Created by 13426 on 2018/4/27.
 */
@Repository
public class EvaluationPrincipleDao {
    @Autowired
    private EvaluationPrincipleMapper evaluationPrincipleMapper;

    public boolean addPrinciple(EvaluationPrinciple evaluationPrinciple) {
        return evaluationPrincipleMapper.insertSelective(evaluationPrinciple) == 1;
    }


    public boolean updatePrinciple(EvaluationPrinciple evaluationPrinciple) {
        return evaluationPrincipleMapper.updateByPrimaryKey(evaluationPrinciple) == 1;
    }

    public List<EvaluationPrinciple> getPrincipleList(String name) {
        EvaluationPrincipleExample example = new EvaluationPrincipleExample();
        EvaluationPrincipleExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike(String.format("%%%s%%",name));
        }
        return evaluationPrincipleMapper.selectByExample(example);
    }

    public List<EvaluationPrinciple> getPrincipleList(String method,String purpose) {
        EvaluationPrincipleExample example = new EvaluationPrincipleExample();
        EvaluationPrincipleExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(method)){
            criteria.andMethodLike(String.format("%%%s%%",method));
        }
        if(StringUtils.isNotBlank(purpose)){
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%",purpose));
        }
        return evaluationPrincipleMapper.selectByExample(example);
    }

    public boolean removePrinciple(Integer id) {
        return evaluationPrincipleMapper.deleteByPrimaryKey(id) == 1;
    }

    public EvaluationPrinciple getPrinciple(Integer id) {
        return evaluationPrincipleMapper.selectByPrimaryKey(id);
    }
}
