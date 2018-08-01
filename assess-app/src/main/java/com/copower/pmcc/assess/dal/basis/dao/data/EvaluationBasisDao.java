package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasis;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasisExample;
import com.copower.pmcc.assess.dal.basis.mapper.EvaluationBasisMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Repository
public class EvaluationBasisDao {
    @Autowired
    private EvaluationBasisMapper evaluationBasisMapper;

    public boolean addBasis(EvaluationBasis evaluationBasis) {
        return evaluationBasisMapper.insertSelective(evaluationBasis) == 1;
    }


    public boolean updateBasis(EvaluationBasis evaluationBasis) {
        return evaluationBasisMapper.updateByPrimaryKey(evaluationBasis) == 1;
    }

    public List<EvaluationBasis> getBasisList(String name) {
        EvaluationBasisExample example = new EvaluationBasisExample();
        EvaluationBasisExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike(String.format("%%%s%%",name));
        }
        return evaluationBasisMapper.selectByExample(example);
    }

    public List<EvaluationBasis> getBasisList(String method,String purpose) {
        EvaluationBasisExample example = new EvaluationBasisExample();
        EvaluationBasisExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(method)){
            criteria.andMethodLike(String.format("%%%s%%",method));
        }
        if(StringUtils.isNotBlank(purpose)){
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%",purpose));
        }
        return evaluationBasisMapper.selectByExample(example);
    }

    public boolean removeBasis(Integer id) {
        return evaluationBasisMapper.deleteByPrimaryKey(id) == 1;
    }

    public EvaluationBasis getBasis(Integer id) {
        return evaluationBasisMapper.selectByPrimaryKey(id);
    }
}
