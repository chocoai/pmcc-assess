package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EvaluationBasis;
import com.copower.pmcc.assess.dal.entity.EvaluationBasisExample;
import com.copower.pmcc.assess.dal.mapper.EvaluationBasisMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Repository
public class EvaluationBasisDao {

    @Autowired
    private EvaluationBasisMapper mapper;

    public boolean add(EvaluationBasisDto evaluationBasisDto){
        return mapper.insertSelective(change(evaluationBasisDto))==1;
    }

    public boolean update(EvaluationBasisDto evaluationBasisDto){
        return  mapper.updateByPrimaryKey(change(evaluationBasisDto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public EvaluationBasisDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<EvaluationBasisDto> list(Integer method){
        EvaluationBasisExample example = new EvaluationBasisExample();
        List<EvaluationBasisDto> vos = new ArrayList<>();
        String methodStr = method + "";
        if (method==null){
            example.createCriteria().andIdIsNotNull();
            mapper.selectByExample(example).parallelStream().forEach(evaluationBasis -> vos.add(change(evaluationBasis)));
        }else {
            example.createCriteria().andIdIsNotNull().andMethodEqualTo(methodStr);
            mapper.selectByExample(example).parallelStream().forEach(evaluationBasis -> vos.add(change(evaluationBasis)));
        }
        return vos;
    }



    public EvaluationBasis change(EvaluationBasisDto evaluationBasisDto){
        EvaluationBasis evaluationBasis =  new EvaluationBasis();
        BeanUtils.copyProperties(evaluationBasisDto,evaluationBasis);
        return evaluationBasis;
    }

    public EvaluationBasisDto change(EvaluationBasis evaluationbasis){
        EvaluationBasisDto evaluationbasisdto =  new EvaluationBasisDto();
        BeanUtils.copyProperties(evaluationbasis,evaluationbasisdto);
        return evaluationbasisdto;
    }
}
