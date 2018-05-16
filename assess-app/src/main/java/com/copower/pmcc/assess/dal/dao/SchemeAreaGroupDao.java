package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.mapper.SchemeAreaGroupMapper;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Repository
public class SchemeAreaGroupDao {

    @Autowired
    private SchemeAreaGroupMapper mapper;

    public SchemeAreaGroup get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public int add(SchemeAreaGroupDto dto){
        mapper.insertSelective(change(dto));
        return dto.getId();
    }

    public boolean update(SchemeAreaGroupDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public SchemeAreaGroupDto change(SchemeAreaGroup oo){
        SchemeAreaGroupDto dto = new SchemeAreaGroupDto();
        BeanUtils.copyProperties(oo,dto);
        return dto;
    }

    public SchemeAreaGroup change(SchemeAreaGroupDto dto){
        SchemeAreaGroup oo = new SchemeAreaGroupDto();
        BeanUtils.copyProperties(dto,oo);
        return oo;
    }
}
