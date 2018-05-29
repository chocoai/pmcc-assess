package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationMethod;
import com.copower.pmcc.assess.dal.entity.EvaluationMethodExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationMethodMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodDto;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 评估技术方法
 * Created by 13426 on 2018/4/24.
 */
@Repository(value = "evaluationMethodDao")
public class EvaluationMethodDao {//evaluationMethodDao

    @Autowired
    private EvaluationMethodMapper evaluationMethodMapper;

    public boolean addEvaluationMethod(EvaluationMethodDto evaluationMethodDto) {
        return evaluationMethodMapper.insertSelective(change(evaluationMethodDto)) == 1;
    }

    public boolean removeEvaluationMethod(Integer id) {
        return evaluationMethodMapper.deleteByPrimaryKey(id) == 1;
    }

    public EvaluationMethodDto getEvaluationMethod(Integer id) {
        EvaluationMethodDto evaluationMethodDto = new EvaluationMethodDto();
        return change(evaluationMethodMapper.selectByPrimaryKey(id));
    }

    public boolean updateEvaluationMethod(EvaluationMethodDto evaluationMethodDto) {
        return evaluationMethodMapper.updateByPrimaryKeySelective(change(evaluationMethodDto)) == 1;
    }

    public List<EvaluationMethod> getEvaluationMethodList(EvaluationMethod evaluationMethod){
        EvaluationMethodExample example = new EvaluationMethodExample();
        MybatisUtils.convertObj2Example(evaluationMethod, example);
        return evaluationMethodMapper.selectByExample(example);
    }


    /**
     * 按条件查询
     *
     * @param map
     * @return
     */
    public List<EvaluationMethodDto> list(Map<String, Integer> map) {
        List<EvaluationMethodDto> evaluationMethodDtos = null;
        List<EvaluationMethod> evaluationMethods = null;
        EvaluationMethodExample evaluationMethodExample = new EvaluationMethodExample();
        if (map != null) {
            if (map.get(EvaluationMethodDto.METHOD_FIELD) != null) {//按方法查询
                Integer v = map.get(EvaluationMethodDto.METHOD_FIELD);
                evaluationMethodExample.createCriteria().andMethodEqualTo(v);
                evaluationMethodDtos = change(evaluationMethodExample);
            }
        } else {//不带任何查询条件的情况
            evaluationMethodExample.createCriteria().andIdIsNotNull();
            evaluationMethodDtos = change(evaluationMethodExample);
        }
        return evaluationMethodDtos;
    }

    /**
     * 转换
     *
     * @param evaluationMethodDto
     * @return
     */
    public EvaluationMethod change(EvaluationMethodDto evaluationMethodDto) {
        EvaluationMethod evaluationMethod = new EvaluationMethod();
        BeanUtils.copyProperties(evaluationMethodDto, evaluationMethod);
        return evaluationMethod;
    }

    /**
     * 转换
     *
     * @param evaluationMethod
     * @return
     */
    public EvaluationMethodDto change(EvaluationMethod evaluationMethod) {
        EvaluationMethodDto evaluationMethodDto = new EvaluationMethodDto();
        BeanUtils.copyProperties(evaluationMethod, evaluationMethodDto);
        return evaluationMethodDto;
    }

    /**
     * 转换
     *
     * @param evaluationMethodExample
     * @return
     */
    public List<EvaluationMethodDto> change(EvaluationMethodExample evaluationMethodExample) {
        List<EvaluationMethodDto> evaluationMethodDtos = new ArrayList<>();
        List<EvaluationMethod> evaluationMethods = evaluationMethodMapper.selectByExample(evaluationMethodExample);
        evaluationMethods.forEach(evaluationMethod -> {
            evaluationMethodDtos.add(change(evaluationMethod));
        });
        return evaluationMethodDtos;
    }
}
