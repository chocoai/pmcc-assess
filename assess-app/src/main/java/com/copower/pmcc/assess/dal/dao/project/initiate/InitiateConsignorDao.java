package com.copower.pmcc.assess.dal.dao.project.initiate;

import com.copower.pmcc.assess.dal.entity.InitiateConsignor;
import com.copower.pmcc.assess.dal.entity.InitiateConsignorExample;
import com.copower.pmcc.assess.dal.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dal.entity.InitiateUnitInformationExample;
import com.copower.pmcc.assess.dal.mapper.InitiateConsignorMapper;
import com.copower.pmcc.assess.dto.input.project.InitiateConsignorDto;
import org.apache.commons.collections.CollectionUtils;
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
public class InitiateConsignorDao {

    @Autowired
    private InitiateConsignorMapper mapper;

    public int add(InitiateConsignorDto dto){
        InitiateConsignor consignor = change(dto);
        mapper.insertSelective(consignor);
        return consignor.getId();
    }

    public InitiateConsignor getDataByProjectId(Integer projectId){
        InitiateConsignorExample example = new InitiateConsignorExample();
        example.createCriteria().andIdIsNotNull().andProjectIdEqualTo(projectId);
        List<InitiateConsignor> consignors = mapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(consignors))
            return consignors.get(0);
        return null;
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
