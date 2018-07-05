package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasisField;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasisFieldExample;
import com.copower.pmcc.assess.dal.basis.mapper.EvaluationBasisFieldMapper;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估依据 字段
 * Created by 13426 on 2018/4/28.
 */
@Repository
public class EvaluationBasisFieldDao {

    @Autowired
    private EvaluationBasisFieldMapper mapper;

    public boolean add(EvaluationBasisFieldDto dto){
        boolean flag = true;
        EvaluationBasisFieldExample example = new EvaluationBasisFieldExample();
        example.createCriteria().andNameEqualTo(dto.getName()).andBasisIdEqualTo(dto.getBasisId());
        if (mapper.selectByExample(example).size()>0)flag=false;
        if (flag){
            return mapper.insertSelective(change(dto))==1;
        }
        return false;
    }

    public void delete(String field,Integer basisID){
        EvaluationBasisFieldExample example = new EvaluationBasisFieldExample();
        example.createCriteria().andIdIsNotNull().andNameEqualTo(field).andBasisIdEqualTo(basisID);
        List<EvaluationBasisField> fieldList = mapper.selectByExample(example);
        for (EvaluationBasisField field1:fieldList){
            mapper.deleteByPrimaryKey(field1.getId());
        }
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(EvaluationBasisFieldDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public EvaluationBasisFieldDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<EvaluationBasisFieldDto> list(Integer basisId){
        List<EvaluationBasisFieldDto> dtos = new ArrayList<>();
        EvaluationBasisFieldExample example = new EvaluationBasisFieldExample();
        if (basisId!=null){
            example.createCriteria().andIdIsNotNull().andBasisIdEqualTo(basisId);
            mapper.selectByExample(example).parallelStream().forEach(evaluationBasisField -> dtos.add(change(evaluationBasisField)));
        }
//        else {
//            example.createCriteria().andIdIsNotNull();
//            mapper.selectByExample(example).parallelStream().forEach(evaluationBasisField -> dtos.add(change(evaluationBasisField)));
//        }
        return dtos;
    }

    public EvaluationBasisFieldDto change(EvaluationBasisField e){
        EvaluationBasisFieldDto dto = new EvaluationBasisFieldDto();
        BeanUtils.copyProperties(e,dto);
        return dto;
    }

    public EvaluationBasisField change(EvaluationBasisFieldDto e){
        EvaluationBasisField ee = new EvaluationBasisField();
        BeanUtils.copyProperties(e,ee);
        return ee;
    }
}
