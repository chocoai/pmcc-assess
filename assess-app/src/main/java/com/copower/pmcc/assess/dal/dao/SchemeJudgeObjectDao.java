package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeObjectExample;
import com.copower.pmcc.assess.dal.mapper.SchemeJudgeObjectMapper;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 估价对象
 * Created by 13426 on 2018/5/21.
 */
@Repository
public class SchemeJudgeObjectDao {

    @Autowired
    private SchemeJudgeObjectMapper mapper;

    public boolean add(SchemeJudgeObjectDto dto){
        return mapper.insertSelective(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(SchemeJudgeObjectDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public SchemeJudgeObjectDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public SchemeJudgeObjectDto change(SchemeJudgeObject oo){
        SchemeJudgeObjectDto dto = new SchemeJudgeObjectDto();
        BeanUtils.copyProperties(oo,dto);
        return dto;
    }

    public SchemeJudgeObject change(SchemeJudgeObjectDto dto){
        SchemeJudgeObject oo = new SchemeJudgeObject();
        BeanUtils.copyProperties(dto,oo);
        return oo;
    }

    public List<SchemeJudgeObject> list(String groupId){
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andIdIsNotNull().andGroupIdEqualTo(groupId).andFlagEqualTo("0");
        return mapper.selectByExample(example);
    }
}
