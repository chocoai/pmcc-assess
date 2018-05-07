package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.InitiateContacts;
import com.copower.pmcc.assess.dal.entity.InitiateContactsExample;
import com.copower.pmcc.assess.dal.mapper.InitiateContactsMapper;
import com.copower.pmcc.assess.dto.input.project.InitiateContactsDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateContactsDao {

    @Autowired
    private InitiateContactsMapper mapper;

    public boolean add(InitiateContactsDto dto){
        return mapper.insertSelective(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiateContactsDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public InitiateContactsDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<InitiateContactsDto> getList(){
        List<InitiateContactsDto> dtos = new ArrayList<>();
        InitiateContactsExample example = new InitiateContactsExample();
        example.createCriteria().andIdIsNotNull();
        mapper.selectByExample(example).parallelStream().forEach(oo -> dtos.add(change(oo)));
        return dtos;
    }

    public List<InitiateContactsDto> getList(Integer cPid){
        List<InitiateContactsDto> dtos = new ArrayList<>();
        InitiateContactsExample example = new InitiateContactsExample();
        example.createCriteria().andCPidEqualTo(cPid);
        mapper.selectByExample(example).parallelStream().forEach(oo -> dtos.add(change(oo)));
        return dtos;
    }

    private InitiateContacts change(InitiateContactsDto dto){
        InitiateContacts data = new InitiateContacts();
        BeanUtils.copyProperties(dto,data);
        return data;
    }

    private InitiateContactsDto change(InitiateContacts data){
        InitiateContactsDto dto = new InitiateContactsDto();
        BeanUtils.copyProperties(data,dto);
        return dto;
    }
}
