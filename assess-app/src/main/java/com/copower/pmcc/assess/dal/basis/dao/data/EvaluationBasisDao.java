package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationBasis;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationBasisExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataEvaluationBasisMapper;
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
    private DataEvaluationBasisMapper evaluationBasisMapper;

    public boolean addBasis(DataEvaluationBasis evaluationBasis) {
        return evaluationBasisMapper.insertSelective(evaluationBasis) == 1;
    }


    public boolean updateBasis(DataEvaluationBasis evaluationBasis) {
        return evaluationBasisMapper.updateByPrimaryKey(evaluationBasis) == 1;
    }

    public List<DataEvaluationBasis> getBasisList(String name) {
        DataEvaluationBasisExample example = new DataEvaluationBasisExample();
        DataEvaluationBasisExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        return evaluationBasisMapper.selectByExample(example);
    }

    public List<DataEvaluationBasis> getEnableBasisList(String type, String category, String method, String purpose) {
        DataEvaluationBasisExample example = new DataEvaluationBasisExample();
        DataEvaluationBasisExample.Criteria criteria = example.createCriteria();
        criteria.andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeLike(String.format("%%%s%%", type));
        }
        if (StringUtils.isNotBlank(category)) {
            criteria.andCategoryLike(String.format("%%%s%%", category));
        }
        if (StringUtils.isNotBlank(method)) {
            criteria.andMethodLike(String.format("%%%s%%", method));
        }
        if (StringUtils.isNotBlank(purpose)) {
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%", purpose));
        }
        return evaluationBasisMapper.selectByExample(example);
    }

    public boolean removeBasis(Integer id) {
        return evaluationBasisMapper.deleteByPrimaryKey(id) == 1;
    }

    public DataEvaluationBasis getBasis(Integer id) {
        return evaluationBasisMapper.selectByPrimaryKey(id);
    }
}
