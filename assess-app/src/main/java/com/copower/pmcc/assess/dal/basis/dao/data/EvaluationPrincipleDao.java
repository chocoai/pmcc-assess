package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationBasisExample;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrinciple;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrincipleExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataEvaluationPrincipleMapper;
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
    private DataEvaluationPrincipleMapper evaluationPrincipleMapper;

    public boolean addPrinciple(DataEvaluationPrinciple evaluationPrinciple) {
        return evaluationPrincipleMapper.insertSelective(evaluationPrinciple) == 1;
    }


    public boolean updatePrinciple(DataEvaluationPrinciple evaluationPrinciple) {
        return evaluationPrincipleMapper.updateByPrimaryKeySelective(evaluationPrinciple) == 1;
    }

    public List<DataEvaluationPrinciple> getPrincipleList(String name) {
        DataEvaluationPrincipleExample example = new DataEvaluationPrincipleExample();
        DataEvaluationPrincipleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        example.setOrderByClause("sorting");
        return evaluationPrincipleMapper.selectByExample(example);
    }

    public List<DataEvaluationPrinciple> getEnablePrincipleList(String type, String category, String purpose) {
        DataEvaluationPrincipleExample example = new DataEvaluationPrincipleExample();
        DataEvaluationPrincipleExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeLike(String.format("%%%s%%", type));
        }
        if (StringUtils.isNotBlank(category)) {
            criteria.andCategoryLike(String.format("%%%s%%", category));
        }
        if (StringUtils.isNotBlank(purpose)) {
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%", purpose));
        }
        example.setOrderByClause("sorting");
        return evaluationPrincipleMapper.selectByExample(example);
    }

    public boolean removePrinciple(Integer id) {
        return evaluationPrincipleMapper.deleteByPrimaryKey(id) == 1;
    }

    public DataEvaluationPrinciple getPrinciple(Integer id) {
        return evaluationPrincipleMapper.selectByPrimaryKey(id);
    }
}
