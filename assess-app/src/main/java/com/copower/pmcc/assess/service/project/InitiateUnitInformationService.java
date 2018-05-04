package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiateUnitInformationDao;
import com.copower.pmcc.assess.dto.input.project.InitiateUnitInformationDto;
import com.copower.pmcc.assess.dto.output.project.InitiateUnitInformationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 报告使用单位信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateUnitInformationService {
    @Autowired
    private InitiateUnitInformationDao dao;

    public boolean add(InitiateUnitInformationDto dto){
        return dao.add(dto);
    }

    public InitiateUnitInformationVo get(Integer id){
        return change(dao.get(id));
    }

    public boolean remove(Integer id){
        return dao.remove(id);
    }

    public boolean update(InitiateUnitInformationDto dto){
        return dao.update(dto);
    }

    private List<InitiateUnitInformationVo> getVoList(){
        List<InitiateUnitInformationVo> vos = new ArrayList<>();
        dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }

    private InitiateUnitInformationDto change(InitiateUnitInformationVo vo){
        InitiateUnitInformationDto dto = new InitiateUnitInformationDto();
        BeanUtils.copyProperties(vo,dto);
        return dto;
    }

    private InitiateUnitInformationVo change(InitiateUnitInformationDto dto){
        InitiateUnitInformationVo vo = new InitiateUnitInformationVo();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
}
