package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiatePossessorDao;
import com.copower.pmcc.assess.dto.input.project.InitiatePossessorDto;
import com.copower.pmcc.assess.dto.output.project.InitiatePossessorVo;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产占有人信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiatePossessorService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitiatePossessorDao dao;

    public int add(InitiatePossessorDto dto){
        if (dto.getpType()==InitiatePossessorDto.PTYPEa){//对资产占有人信息 进行单独处理
            dto.setpUnitProperties(null);
            dto.setpScopeOperation(null);
            dto.setpSociologyCode(null);
            dto.setpLegalRepresentative(null);
            dto.setpEntrustmentUnit(null);
        }else {
        }
        if (dto.getCreator()==null)dto.setCreator(commonService.thisUserAccount());
        return dao.add(dto);
    }

    public InitiatePossessorVo get(Integer id){
        return change(dao.get(id));
    }

    public InitiatePossessorDto getById(Integer id){
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }

    @Transactional
    public boolean update(InitiatePossessorDto dto){
        if (dto.getCreator()==null)dto.setCreator(commonService.thisUserAccount());
        return dao.update(dto);
    }

    private List<InitiatePossessorVo> getVoList(){
        List<InitiatePossessorVo> vos = new ArrayList<>();
        dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }

    private InitiatePossessorDto change(InitiatePossessorVo vo){
        InitiatePossessorDto dto = new InitiatePossessorDto();
        BeanUtils.copyProperties(vo,dto);
        return dto;
    }

    private InitiatePossessorVo change(InitiatePossessorDto dto){
        InitiatePossessorVo vo = new InitiatePossessorVo();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
}
