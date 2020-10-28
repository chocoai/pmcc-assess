package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationMethod;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationMethodExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataEvaluationMethodMapper;
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
    private DataEvaluationMethodMapper evaluationMethodMapper;

    public boolean addMethod(DataEvaluationMethod evaluationMethod) {
        return evaluationMethodMapper.insertSelective(evaluationMethod) == 1;
    }


    public boolean updateMethod(DataEvaluationMethod evaluationMethod) {
        return evaluationMethodMapper.updateByPrimaryKeySelective(evaluationMethod) == 1;
    }

    public List<DataEvaluationMethod> getMethodAllList() {
        DataEvaluationMethodExample example = new DataEvaluationMethodExample();
        DataEvaluationMethodExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return evaluationMethodMapper.selectByExample(example);
    }

    public List<DataEvaluationMethod> getMethodList(String name) {
        DataEvaluationMethodExample example = new DataEvaluationMethodExample();
        DataEvaluationMethodExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        return evaluationMethodMapper.selectByExample(example);
    }

    public List<DataEvaluationMethod> getMethodListByMethod(Integer method) {
        DataEvaluationMethodExample example = new DataEvaluationMethodExample();
        DataEvaluationMethodExample.Criteria criteria = example.createCriteria();
        criteria.andMethodEqualTo(method);
        return evaluationMethodMapper.selectByExample(example);
    }

    public List<DataEvaluationMethod> getMethodListByMethod(Integer method, String projectType, String projectCategory) {
        DataEvaluationMethodExample example = new DataEvaluationMethodExample();
        DataEvaluationMethodExample.Criteria criteria = example.createCriteria();
        criteria.andMethodEqualTo(method);
        if (StringUtils.isNotBlank(projectType)) {
            criteria.andTypeLike(String.format("%%%s%%", projectType));
        }
        if (StringUtils.isNotBlank(projectCategory)) {
            criteria.andCategoryLike(String.format("%%%s%%", projectCategory));
        }
        return evaluationMethodMapper.selectByExample(example);
    }

    public boolean removeMethod(Integer id) {
        return evaluationMethodMapper.deleteByPrimaryKey(id) == 1;
    }

    public DataEvaluationMethod getMethod(Integer id) {
        return evaluationMethodMapper.selectByPrimaryKey(id);
    }
}
