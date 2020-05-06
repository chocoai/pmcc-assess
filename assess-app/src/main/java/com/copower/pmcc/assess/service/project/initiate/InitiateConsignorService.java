package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateConsignorDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignor;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateConsignorService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitiateConsignorDao dao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Lazy
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private InitiateContactsService initiateContactsService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public Integer saveAndUpdateInitiateConsignor(InitiateConsignor initiateConsignor) {
        if (initiateConsignor == null){
            return null;
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(initiateConsignor.getCsAddress())) {
            String[] strings = initiateConsignor.getCsAddress().split(",");
            Set<String> stringSet = Sets.newHashSet(strings);
            stringSet = stringSet.stream().filter(s -> org.apache.commons.lang3.StringUtils.isNotBlank(s)).collect(Collectors.toSet());
            initiateConsignor.setCsAddress(org.apache.commons.lang3.StringUtils.join(stringSet,""));
        }
        if (initiateConsignor.getId() == null || initiateConsignor.getId() == 0) {
            initiateConsignor.setCreator(commonService.thisUserAccount());
            //对 委托人进行单独处理
            if (InitiateContactsEnum.naturalPerson.getId().equals(initiateConsignor.getCsType())) {
                initiateConsignor.setCsUnitProperties(null);
                initiateConsignor.setCsScopeOperation(null);
                initiateConsignor.setCsSociologyCode(null);
                initiateConsignor.setCsLegalRepresentative(null);
                initiateConsignor.setCsEntrustmentUnit(null);
            }
            Integer id = dao.add(initiateConsignor);
            initiateContactsService.update(id, InitiateContactsEnum.CONSIGNOR.getId());
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(InitiateConsignor.class), id);
            return id;
        }else {
            dao.update(initiateConsignor);
            return initiateConsignor.getId();
        }
    }


    public List<InitiateConsignor> initiateConsignorList(InitiateConsignor query) {

        return dao.initiateConsignorList(query);
    }

    public InitiateConsignorVo getDataByProjectId(Integer projectId) {
        InitiateConsignor consignor = new InitiateConsignor();
        consignor.setProjectId(projectId);
        List<InitiateConsignor> initiateConsignors = initiateConsignorList(consignor);
        if (CollectionUtils.isNotEmpty(initiateConsignors)) {
            return getInitiateConsignorVo(initiateConsignors.get(0));
        }
        return null;
    }

    public InitiateConsignorVo get(Integer id) {
        return getInitiateConsignorVo(dao.get(id));
    }

    public boolean remove(Integer id) {
        initiateContactsService.remove(id, InitiateContactsEnum.CONSIGNOR.getId());
        return dao.remove(id);
    }



    public InitiateConsignorVo getInitiateConsignorVo(InitiateConsignor initiateConsignor) {
        if (initiateConsignor == null) {
            return null;
        }
        InitiateConsignorVo vo = new InitiateConsignorVo();
        BeanUtils.copyProperties(initiateConsignor, vo);
        List<CrmBaseDataDicDto> crmBaseDataDicDtos = new ArrayList<>();
        try {
            crmBaseDataDicDtos = crmRpcBaseDataDicService.getUnitPropertiesList();
        } catch (Exception e) {
            List<BaseDataDic> cacheDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_INITIATE_UNIT_TYPE);
            if (CollectionUtils.isNotEmpty(cacheDataDicList)){
                for (BaseDataDic baseDataDic:cacheDataDicList){
                    CrmBaseDataDicDto crmBaseDataDicDto = new CrmBaseDataDicDto();
                    crmBaseDataDicDto.setId(baseDataDic.getId());
                    crmBaseDataDicDto.setName(baseDataDic.getName());
                    crmBaseDataDicDtos.add(crmBaseDataDicDto);
                }
            }
        }
        if (!ObjectUtils.isEmpty(crmBaseDataDicDtos)) {
            for (CrmBaseDataDicDto dicDto : crmBaseDataDicDtos) {
                if (!StringUtils.isEmpty(initiateConsignor.getCsUnitProperties())) {
                    if (dicDto.getId().equals(Integer.parseInt(initiateConsignor.getCsUnitProperties()))) {
                        vo.setCsUnitPropertiesName(dicDto.getName());
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(initiateConsignor.getCsEntrustmentUnit())) {
            if (NumberUtils.isNumber(initiateConsignor.getCsEntrustmentUnit())) {
                vo.setCsEntrustmentUnitName(baseDataDicService.getNameById(Integer.valueOf(initiateConsignor.getCsEntrustmentUnit())));
            }
        }
        return vo;
    }


    public void clear() {
        initiateContactsService.remove(0, InitiateContactsEnum.CONSIGNOR.getId());
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setCreater(commonService.thisUserAccount());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(InitiateConsignor.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto attachmentDto : sysAttachmentDtoList) {
                baseAttachmentService.deleteAttachmentByDto(attachmentDto);
            }
        }
    }


}
