package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.dal.dao.project.initiate.InitiateConsignorDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.InitiateConsignor;
import com.copower.pmcc.assess.dto.input.project.initiate.InitiateConsignorDto;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateConsignorService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitiateConsignorDao dao;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public int add(InitiateConsignorDto dto) {
        //对 委托人进行单独处理
        if (dto.getCsType() == InitiateConsignorDto.CSTYPEa) {
            dto.setCsUnitProperties(null);
            dto.setCsScopeOperation(null);
            dto.setCsSociologyCode(null);
            dto.setCsLegalRepresentative(null);
            dto.setCsEntrustmentUnit(null);
        }
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        return dao.add(dto);
    }

    public InitiateConsignorVo getDataByProjectId(Integer projectId) {
        return change(dao.getDataByProjectId(projectId));
    }

    public InitiateConsignorVo get(Integer id) {
        return change(dao.get(id));
    }

    public InitiateConsignorDto getById(Integer id) {
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(InitiateConsignorDto dto) {
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        return dao.update(dto);
    }


    private List<InitiateConsignorVo> getVoList() {
        List<InitiateConsignorVo> vos = new ArrayList<>();
        dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }


    private InitiateConsignorVo change(InitiateConsignor initiateConsignor) {
        if (initiateConsignor == null) return null;
        InitiateConsignorVo vo = new InitiateConsignorVo();
        BeanUtils.copyProperties(initiateConsignor, vo);
        if (!StringUtils.isEmpty(initiateConsignor.getCsUnitProperties())) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.valueOf(initiateConsignor.getCsUnitProperties()));
            if (baseDataDic != null)
                vo.setCsUnitPropertiesName(baseDataDic.getName());
        }
        if (!StringUtils.isEmpty(initiateConsignor.getCsEntrustmentUnit())) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.valueOf(initiateConsignor.getCsEntrustmentUnit()));
            if (baseDataDic != null)
                vo.setCsEntrustmentUnitName(baseDataDic.getName());
        }
        return vo;
    }


}
