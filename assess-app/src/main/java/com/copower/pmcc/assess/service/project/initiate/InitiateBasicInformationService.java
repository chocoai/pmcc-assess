package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.dal.dao.project.initiate.InitiateBasicInformationDao;
import com.copower.pmcc.assess.dto.input.project.InitiateBasicInformationDto;
import com.copower.pmcc.assess.dto.output.project.InitiateBasicInformationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 立项信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateBasicInformationService {

    @Autowired
    private InitiateBasicInformationDao dao;

    public boolean add(InitiateBasicInformationDto dto){
        return dao.add(dto);
    }

    public InitiateBasicInformationVo get(Integer id){
        return change(dao.get(id));
    }

    public boolean remove(Integer id){
        return dao.remove(id);
    }

    public boolean update(InitiateBasicInformationDto dto){
        return dao.update(dto);
    }

    private List<InitiateBasicInformationVo> getVoList(){
        List<InitiateBasicInformationVo> vos = new ArrayList<>();
        dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }

    private InitiateBasicInformationDto change(InitiateBasicInformationVo vo){
        InitiateBasicInformationDto dto = new InitiateBasicInformationDto();
        BeanUtils.copyProperties(vo,dto);
        return dto;
    }

    private InitiateBasicInformationVo change(InitiateBasicInformationDto dto){
        InitiateBasicInformationVo vo = new InitiateBasicInformationVo();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
}
