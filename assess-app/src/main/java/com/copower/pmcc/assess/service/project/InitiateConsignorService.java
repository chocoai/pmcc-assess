package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiateConsignorDao;
import com.copower.pmcc.assess.dto.input.project.InitiateConsignorDto;
import com.copower.pmcc.assess.dto.output.project.InitiateConsignorVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *  委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateConsignorService {

    @Autowired
    private InitiateConsignorDao dao;

    @Transactional
    public int add(InitiateConsignorDto dto){
        return dao.add(dto);
    }

    public InitiateConsignorVo get(Integer id){
        return change(dao.get(id));
    }

    public InitiateConsignorDto getById(Integer id){
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }

    @Transactional
    public boolean update(InitiateConsignorDto dto){
        return dao.update(dto);
    }


    private List<InitiateConsignorVo> getVoList(){
        List<InitiateConsignorVo> vos = new ArrayList<>();
        dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }

    private InitiateConsignorDto change(InitiateConsignorVo vo){
        InitiateConsignorDto dto = new InitiateConsignorDto();
        BeanUtils.copyProperties(vo,dto);
        return dto;
    }

    private InitiateConsignorVo change(InitiateConsignorDto dto){
        InitiateConsignorVo vo = new InitiateConsignorVo();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
}
