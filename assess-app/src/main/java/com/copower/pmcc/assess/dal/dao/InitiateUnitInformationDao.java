package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dal.entity.InitiateUnitInformationExample;
import com.copower.pmcc.assess.dal.mapper.InitiateUnitInformationMapper;
import com.copower.pmcc.assess.dto.input.project.InitiateUnitInformationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 报告使用单位信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateUnitInformationDao {
    @Autowired
    private InitiateUnitInformationMapper mapper;

    public boolean add(InitiateUnitInformationDto dto){
        return mapper.insertSelective(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiateUnitInformationDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public InitiateUnitInformationDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<InitiateUnitInformationDto> getList(){
        List<InitiateUnitInformationDto> dtos = new ArrayList<>();
        InitiateUnitInformationExample example = new InitiateUnitInformationExample();
        example.createCriteria().andIdIsNotNull();
        mapper.selectByExample(example).parallelStream().forEach(oo -> dtos.add(change(oo)));
        return dtos;
    }

    private InitiateUnitInformation change(InitiateUnitInformationDto dto){
        InitiateUnitInformation data = new InitiateUnitInformation();
        BeanUtils.copyProperties(dto,data);
        return data;
    }

    private InitiateUnitInformationDto change(InitiateUnitInformation data){
        InitiateUnitInformationDto dto = new InitiateUnitInformationDto();
        BeanUtils.copyProperties(data,dto);
        return dto;
    }
}
