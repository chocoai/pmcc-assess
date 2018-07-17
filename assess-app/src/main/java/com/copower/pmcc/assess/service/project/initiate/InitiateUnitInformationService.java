package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateUnitInformationDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dto.input.project.initiate.InitiateUnitInformationDto;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.base.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
    @Lazy
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;

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
//        if (!StringUtils.isEmpty(unitInformation.getuUseUnit())) {
//            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.valueOf(unitInformation.getuUseUnit()));
//            if (baseDataDic != null)
//                vo.setuUseUnitName(baseDataDic.getName());
//        }
        List<CrmBaseDataDicDto> crmBaseDataDicDtos = getUnitPropertiesList();
        if (!StringUtils.isEmpty(unitInformation.getuUseUnit())) {
            if (!ObjectUtils.isEmpty(crmBaseDataDicDtos)) {
                for (CrmBaseDataDicDto dicDto : crmBaseDataDicDtos) {
                    if (Objects.equal(dicDto.getId(),Integer.parseInt(unitInformation.getuUnitProperties()))){
                        vo.setuUnitPropertiesName(dicDto.getName());
                        break;
                    }
                }
            }
        }
        return vo;
    }

    /**
     * 单位性质 crm
     *
     * @return
     */
    private List<CrmBaseDataDicDto> getUnitPropertiesList() {
        List<CrmBaseDataDicDto> crmBaseDataDicDtos = crmRpcBaseDataDicService.getUnitPropertiesList();
        return crmBaseDataDicDtos;
    }
}
