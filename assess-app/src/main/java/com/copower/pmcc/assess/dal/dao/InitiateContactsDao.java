package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.dal.entity.InitiateContacts;
import com.copower.pmcc.assess.dal.entity.InitiateContactsExample;
import com.copower.pmcc.assess.dal.mapper.InitiateContactsMapper;
import com.copower.pmcc.assess.dto.input.project.InitiateContactsDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateContactsDao {

    @Autowired
    private InitiateContactsMapper mapper;

    public int save(InitiateContactsDto dto){
        InitiateContacts contacts = change(dto);
        mapper.insertSelective(contacts);
        return contacts.getId();
    }

    public boolean add(InitiateContactsDto dto){
        return mapper.insertSelective(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiateContactsDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public void update(int pid, int cType, String createPeople){
        List<InitiateContactsDto> dtos = getList(InitiateContactsEnum.Zero.getNum(), cType,createPeople);
        if (!ObjectUtils.isEmpty(dtos)){
            for (InitiateContactsDto dto:dtos){
                dto.setcPid(pid);
                update(dto);
            }

        }
    }

    public InitiateContactsDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }


    public List<InitiateContactsDto> getList(Integer cPid,Integer cType,String createPeople){
        List<InitiateContactsDto> dtos = new ArrayList<>();
        InitiateContactsExample example = new InitiateContactsExample();
        InitiateContactsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!ObjectUtils.isEmpty(cPid)){//主表(关联的主表,如:委托人id)
            criteria.andCPidEqualTo(cPid);
        }
        if (!ObjectUtils.isEmpty(cType)){//类型如:委托人,资产占有人,报告使用单位
            criteria.andCTypeEqualTo(cType);
        }
        if (!StringUtils.isEmpty(createPeople)){//当前用户
            criteria.andCreatorEqualTo(createPeople);
        }
        List<InitiateContacts> contacts = mapper.selectByExample(example);
        contacts.parallelStream().forEach(oo -> dtos.add(change(oo)));
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
