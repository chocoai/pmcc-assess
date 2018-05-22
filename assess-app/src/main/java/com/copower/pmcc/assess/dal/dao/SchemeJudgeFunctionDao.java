package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeFunctionExample;
import com.copower.pmcc.assess.dal.mapper.SchemeJudgeFunctionMapper;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeFunctionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估方法
 * Created by 13426 on 2018/5/21.
 */
@Repository
public class SchemeJudgeFunctionDao {
    @Autowired
    private SchemeJudgeFunctionMapper mapper;

    public boolean add(SchemeJudgeFunctionDto dto){
        return mapper.insertSelective(change(dto)) ==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(SchemeJudgeFunctionDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public SchemeJudgeFunctionDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public SchemeJudgeFunctionDto get(String creator,Integer methodType, Integer judgeObjectId){
        SchemeJudgeFunctionExample example = new SchemeJudgeFunctionExample();
        example.createCriteria().andIdIsNotNull().andCreatorEqualTo(creator).andMethodTypeEqualTo(methodType).andJudgeObjectIdEqualTo(judgeObjectId);
        //应该只有一个
        List<SchemeJudgeFunction> schemeJudgeFunctions = mapper.selectByExample(example);
        SchemeJudgeFunctionDto dto = change(schemeJudgeFunctions.get(0));
        return dto;
    }

    public SchemeJudgeFunctionDto change(SchemeJudgeFunction oo){
        SchemeJudgeFunctionDto dto = new SchemeJudgeFunctionDto();
        BeanUtils.copyProperties(oo,dto);
        return dto;
    }

    public SchemeJudgeFunction change(SchemeJudgeFunctionDto dto){
        SchemeJudgeFunction oo = new SchemeJudgeFunction();
        BeanUtils.copyProperties(dto,oo);
        return oo;
    }
}
