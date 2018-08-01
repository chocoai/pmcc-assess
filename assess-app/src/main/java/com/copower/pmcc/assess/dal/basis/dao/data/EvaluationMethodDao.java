package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethod;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethodExample;
import com.copower.pmcc.assess.dal.basis.mapper.EvaluationMethodMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估技术方法
 * Created by 13426 on 2018/4/24.
 */
@Repository(value = "evaluationMethodDao")
public class EvaluationMethodDao {//evaluationMethodDao
    @Autowired
    private EvaluationMethodMapper evaluationMethodMapper;

    public boolean addMethod(EvaluationMethod evaluationMethod) {
        return evaluationMethodMapper.insertSelective(evaluationMethod) == 1;
    }


    public boolean updateMethod(EvaluationMethod evaluationMethod) {
        return evaluationMethodMapper.updateByPrimaryKey(evaluationMethod) == 1;
    }

    public List<EvaluationMethod> getMethodAllList() {
        EvaluationMethodExample example = new EvaluationMethodExample();
        return evaluationMethodMapper.selectByExample(example);
    }

    public List<EvaluationMethod> getMethodList(String name) {
        EvaluationMethodExample example = new EvaluationMethodExample();
        EvaluationMethodExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike(String.format("%%%s%%",name));
        }
        return evaluationMethodMapper.selectByExample(example);
    }

    public List<EvaluationMethod> getMethodListByMethod(Integer method) {
        EvaluationMethodExample example = new EvaluationMethodExample();
        EvaluationMethodExample.Criteria criteria = example.createCriteria();
        criteria.andMethodEqualTo(method);
        return evaluationMethodMapper.selectByExample(example);
    }

    public boolean removeMethod(Integer id) {
        return evaluationMethodMapper.deleteByPrimaryKey(id) == 1;
    }

    public EvaluationMethod getMethod(Integer id) {
        return evaluationMethodMapper.selectByPrimaryKey(id);
    }
}
