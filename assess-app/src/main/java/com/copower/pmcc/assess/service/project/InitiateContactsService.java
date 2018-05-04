package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiateContactsDao;
import com.copower.pmcc.assess.dto.input.project.InitiateContactsDto;
import com.copower.pmcc.assess.dto.output.project.InitiateContactsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateContactsService {
    @Autowired
    private InitiateContactsDao dao;

    public boolean add(InitiateContactsDto dto){
        return dao.add(dto);
    }

    public InitiateContactsVo get(Integer id){
        return change(dao.get(id));
    }

    public boolean remove(Integer id){
        return dao.remove(id);
    }

    public boolean update(InitiateContactsDto dto){
        return dao.update(dto);
    }

    private List<InitiateContactsVo> getVoList(){
        List<InitiateContactsVo> vos = new ArrayList<>();
        dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }

    private InitiateContactsDto change(InitiateContactsVo vo){
        InitiateContactsDto dto = new InitiateContactsDto();
        BeanUtils.copyProperties(vo,dto);
        return dto;
    }

    private InitiateContactsVo change(InitiateContactsDto dto){
        InitiateContactsVo vo = new InitiateContactsVo();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
}
