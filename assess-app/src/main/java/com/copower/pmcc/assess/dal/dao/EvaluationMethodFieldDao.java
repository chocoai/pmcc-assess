package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationMethod;
import com.copower.pmcc.assess.dal.entity.EvaluationMethodExample;
import com.copower.pmcc.assess.dal.entity.EvaluationMethodField;
import com.copower.pmcc.assess.dal.entity.EvaluationMethodFieldExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationMethodFieldMapper;
import com.copower.pmcc.assess.dal.mapper.EvaluationMethodMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估技术方法 字段
 * Created by 13426 on 2018/4/24.
 */
@Repository(value = "evaluationMethodFieldDao")
public class EvaluationMethodFieldDao {

    @Autowired
    private EvaluationMethodFieldMapper evaluationMethodFieldMapper;

    @Autowired
    private EvaluationMethodMapper methodMapper;

    @Deprecated
    public List<EvaluationMethodField> schemeassistservice(Integer method){
        List<EvaluationMethodField> fieldList = new ArrayList<>();
        List<EvaluationMethod> methods ;
        EvaluationMethodExample methodExample = new EvaluationMethodExample();
        methodExample.createCriteria().andIdIsNotNull().andMethodEqualTo(""+ method);
        methods = methodMapper.selectByExample(methodExample);
        for (EvaluationMethod evaluationMethod:methods){
            Integer id = evaluationMethod.getId();
            List<EvaluationMethodField> fields = list(id);
            fieldList.addAll(fields);
        }
        return fieldList;
    }

    public boolean add(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        boolean flag = true;
        EvaluationMethodFieldExample example = new EvaluationMethodFieldExample();
        example.createCriteria().andNameEqualTo(evaluationMethodFieldDto.getName());
        if (evaluationMethodFieldMapper.selectByExample(example).size() > 0) flag = false;
        if (flag) {
            return evaluationMethodFieldMapper.insertSelective(change(evaluationMethodFieldDto)) == 1;
        }
        return false;
    }

    public boolean remove(Integer id) {
        return evaluationMethodFieldMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean removeMethod(Integer id) {
        EvaluationMethodFieldExample evaluationMethodFieldExample = new EvaluationMethodFieldExample();
        evaluationMethodFieldExample.createCriteria().andMethodIdEqualTo(id);
        int i = evaluationMethodFieldMapper.deleteByExample(evaluationMethodFieldExample);
        return i > 0;
    }

    public boolean update(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        return evaluationMethodFieldMapper.updateByPrimaryKey(change(evaluationMethodFieldDto)) == 1;
    }

    public EvaluationMethodFieldDto get(Integer id) {
        return change(evaluationMethodFieldMapper.selectByPrimaryKey(id));
    }

    public List<EvaluationMethodField> list(Integer methodId) {
        List<EvaluationMethodField> evaluationMethodFields = new ArrayList<>();
        EvaluationMethodFieldExample evaluationMethodFieldExample = new EvaluationMethodFieldExample();
        evaluationMethodFieldExample.createCriteria().andMethodIdEqualTo(methodId);
        evaluationMethodFields = evaluationMethodFieldMapper.selectByExample(evaluationMethodFieldExample);
        return evaluationMethodFields;
    }

    /**
     * 转换
     *
     * @param evaluationMethodField
     * @return
     */
    public EvaluationMethodFieldDto change(EvaluationMethodField evaluationMethodField) {
        EvaluationMethodFieldDto evaluationMethodFieldDto = new EvaluationMethodFieldDto();
        BeanUtils.copyProperties(evaluationMethodField, evaluationMethodFieldDto);
        return evaluationMethodFieldDto;
    }

    /**
     * 转换
     *
     * @param evaluationMethodFieldDto
     * @return
     */
    public EvaluationMethodField change(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        EvaluationMethodField evaluationMethodField = new EvaluationMethodField();
        BeanUtils.copyProperties(evaluationMethodFieldDto, evaluationMethodField);
        return evaluationMethodField;
    }
}
