package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiateUnitInformationDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dto.input.project.InitiateUnitInformationDto;
import com.copower.pmcc.assess.dto.output.project.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Autowired
    private BaseDataDicService baseDataDicService;

    public int add(InitiateUnitInformationDto dto) {
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        return dao.add(dto);
    }

    public InitiateUnitInformationVo getDataByProjectId(Integer projectId) {
        InitiateUnitInformation initiateUnitInformation = dao.getDataByProjectId(projectId);
        return change(initiateUnitInformation);
    }

    public InitiateUnitInformationVo get(Integer id) {
        return change(dao.get(id));
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(InitiateUnitInformationDto dto) {
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        return dao.update(dto);
    }

    private InitiateUnitInformationVo change(InitiateUnitInformation unitInformation) {
        if (unitInformation == null) return null;
        InitiateUnitInformationVo vo = new InitiateUnitInformationVo();
        BeanUtils.copyProperties(unitInformation, vo);

        if (!StringUtils.isEmpty(unitInformation.getuUnitProperties())) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.valueOf(unitInformation.getuUnitProperties()));
            if (baseDataDic != null)
                vo.setuUnitPropertiesName(baseDataDic.getName());
        }
        if (!StringUtils.isEmpty(unitInformation.getuUseUnit())) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.valueOf(unitInformation.getuUseUnit()));
            if (baseDataDic != null)
                vo.setuUseUnitName(baseDataDic.getName());
        }
        return vo;
    }
}
