package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationHypothesis;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationHypothesisExample;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrincipleExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataEvaluationHypothesisMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 3.1.2.12	评估假设
 * Created by 13426 on 2018/4/28.
 */
@Repository
public class EvaluationHypothesisDao {

    @Autowired
    private DataEvaluationHypothesisMapper evaluationHypothesisMapper;

    public boolean addHypothesis(DataEvaluationHypothesis evaluationHypothesis) {
        return evaluationHypothesisMapper.insertSelective(evaluationHypothesis) == 1;
    }


    public boolean updateHypothesis(DataEvaluationHypothesis evaluationHypothesis) {
        return evaluationHypothesisMapper.updateByPrimaryKeySelective(evaluationHypothesis) == 1;
    }

    public List<DataEvaluationHypothesis> getHypothesisList(String name) {
        DataEvaluationHypothesisExample example = new DataEvaluationHypothesisExample();
        DataEvaluationHypothesisExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike(String.format("%%%s%%",name));
        }
        example.setOrderByClause("sorting");
        return evaluationHypothesisMapper.selectByExample(example);
    }

    public List<DataEvaluationHypothesis> getEnableHypothesisList(String type,String category,String purpose) {
        DataEvaluationHypothesisExample example = new DataEvaluationHypothesisExample();
        DataEvaluationHypothesisExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeLike(String.format("%%%s%%", type));
        }
        if (StringUtils.isNotBlank(category)) {
            criteria.andCategoryLike(String.format("%%%s%%", category));
        }
        if(StringUtils.isNotBlank(purpose)){
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%",purpose));
        }
        example.setOrderByClause("sorting");
        return evaluationHypothesisMapper.selectByExample(example);
    }

    public boolean removeHypothesis(Integer id) {
        return evaluationHypothesisMapper.deleteByPrimaryKey(id) == 1;
    }

    public DataEvaluationHypothesis getHypothesis(Integer id) {
        return evaluationHypothesisMapper.selectByPrimaryKey(id);
    }

    public DataEvaluationHypothesis getSingleObject(DataEvaluationHypothesis dataEvaluationHypothesis) {
        DataEvaluationHypothesisExample example = new DataEvaluationHypothesisExample();
        MybatisUtils.convertObj2Example(dataEvaluationHypothesis, example);
        List<DataEvaluationHypothesis> list = evaluationHypothesisMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }
}
