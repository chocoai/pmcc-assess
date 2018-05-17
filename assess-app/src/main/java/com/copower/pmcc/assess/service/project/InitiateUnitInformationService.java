package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiateUnitInformationDao;
import com.copower.pmcc.assess.dto.input.project.InitiateUnitInformationDto;
import com.copower.pmcc.assess.dto.output.project.InitiateUnitInformationVo;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 报告使用单位信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateUnitInformationService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitiateUnitInformationDao dao;

    @Transactional
    public int add(InitiateUnitInformationDto dto){
        if (dto.getCreator()==null)dto.setCreator(commonService.thisUserAccount());
        return dao.add(dto);
    }

    public InitiateUnitInformationVo get(Integer id){
        return change(dao.get(id));
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }

    @Transactional
    public boolean update(InitiateUnitInformationDto dto){
        if (dto.getCreator()==null)dto.setCreator(commonService.thisUserAccount());
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
