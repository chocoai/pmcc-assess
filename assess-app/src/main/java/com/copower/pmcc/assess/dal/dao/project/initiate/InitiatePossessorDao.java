package com.copower.pmcc.assess.dal.dao.project.initiate;

import com.copower.pmcc.assess.dal.entity.InitiatePossessor;
import com.copower.pmcc.assess.dal.entity.InitiatePossessorExample;
import com.copower.pmcc.assess.dal.entity.InitiatePossessor;
import com.copower.pmcc.assess.dal.entity.InitiatePossessorExample;
import com.copower.pmcc.assess.dal.mapper.InitiatePossessorMapper;
import com.copower.pmcc.assess.dto.input.project.InitiatePossessorDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产占有人信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiatePossessorDao {

    @Autowired
    private InitiatePossessorMapper mapper;

    public int add(InitiatePossessorDto dto){
        InitiatePossessor initiatePossessor = change(dto);
        mapper.insertSelective(initiatePossessor);
        return initiatePossessor.getId();
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiatePossessorDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public InitiatePossessor getDataByProjectId(Integer projectId){
        InitiatePossessorExample example = new InitiatePossessorExample();
        example.createCriteria().andIdIsNotNull().andProjectIdEqualTo(projectId);
        List<InitiatePossessor> consignors = mapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(consignors))
            return consignors.get(0);
        return null;
    }
    
    public InitiatePossessorDto get(Integer id){
        return change(mapper.selectByPrimaryKey(id));
    }

    public List<InitiatePossessorDto> getList(){
        List<InitiatePossessorDto> dtos = new ArrayList<>();
        InitiatePossessorExample example = new InitiatePossessorExample();
        example.createCriteria().andIdIsNotNull();
        mapper.selectByExample(example).parallelStream().forEach(oo -> dtos.add(change(oo)));
        return dtos;
    }

    private InitiatePossessor change(InitiatePossessorDto dto){
        InitiatePossessor data = new InitiatePossessor();
        BeanUtils.copyProperties(dto,data);
        return data;
    }

    private InitiatePossessorDto change(InitiatePossessor data){
        InitiatePossessorDto dto = new InitiatePossessorDto();
        BeanUtils.copyProperties(data,dto);
        return dto;
    }
}
