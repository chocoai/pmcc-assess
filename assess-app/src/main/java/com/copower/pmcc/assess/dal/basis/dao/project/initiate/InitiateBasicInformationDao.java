package com.copower.pmcc.assess.dal.basis.dao.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateBasicInformation;
import com.copower.pmcc.assess.dal.basis.entity.InitiateBasicInformationExample;
import com.copower.pmcc.assess.dal.basis.mapper.InitiateBasicInformationMapper;
import com.copower.pmcc.assess.dto.input.project.initiate.InitiateBasicInformationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 立项信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateBasicInformationDao {

    @Autowired
    private InitiateBasicInformationMapper mapper;

    public boolean add(InitiateBasicInformationDto dto){
        return mapper.insertSelective(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiateBasicInformationDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public InitiateBasicInformationDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<InitiateBasicInformationDto> getList(){
        List<InitiateBasicInformationDto> dtos = new ArrayList<>();
        InitiateBasicInformationExample example = new InitiateBasicInformationExample();
        example.createCriteria().andIdIsNotNull();
        mapper.selectByExample(example).parallelStream().forEach(oo -> dtos.add(change(oo)));
        return dtos;
    }

    private InitiateBasicInformation change(InitiateBasicInformationDto dto){
        InitiateBasicInformation data = new InitiateBasicInformation();
        BeanUtils.copyProperties(dto,data);
        return data;
    }

    private InitiateBasicInformationDto change(InitiateBasicInformation data){
        InitiateBasicInformationDto dto = new InitiateBasicInformationDto();
        BeanUtils.copyProperties(data,dto);
        return dto;
    }
}
