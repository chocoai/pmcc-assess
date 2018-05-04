package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.InitiateConsignor;
import com.copower.pmcc.assess.dal.entity.InitiateConsignorExample;
import com.copower.pmcc.assess.dal.mapper.InitiateConsignorMapper;
import com.copower.pmcc.assess.dto.input.project.InitiateConsignorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateConsignorDao {

    @Autowired
    private InitiateConsignorMapper mapper;

    public boolean add(InitiateConsignorDto dto){
        return mapper.insertSelective(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiateConsignorDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public InitiateConsignorDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<InitiateConsignorDto> getList(){
        List<InitiateConsignorDto> dtos = new ArrayList<>();
        InitiateConsignorExample example = new InitiateConsignorExample();
        example.createCriteria().andIdIsNotNull();
        mapper.selectByExample(example).parallelStream().forEach(oo -> dtos.add(change(oo)));
        return dtos;
    }

    private InitiateConsignor change(InitiateConsignorDto dto){
        InitiateConsignor data = new InitiateConsignor();
        BeanUtils.copyProperties(dto,data);
        return data;
    }

    private InitiateConsignorDto change(InitiateConsignor data){
        InitiateConsignorDto dto = new InitiateConsignorDto();
        BeanUtils.copyProperties(data,dto);
        return dto;
    }
}
