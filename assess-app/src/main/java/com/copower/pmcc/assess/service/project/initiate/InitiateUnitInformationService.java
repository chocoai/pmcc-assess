package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateUnitInformationDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
    @Lazy
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private CrmRpcCustomerService crmRpcCustomerService;
    @Autowired
    private InitiateContactsService initiateContactsService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 回写 crm data
     *
     * @param initiateUnitInformation
     * @throws Exception
     */
    public void roundWrite(InitiateUnitInformation initiateUnitInformation) throws Exception {
        if (initiateUnitInformation == null) {
            return;
        }
        CrmCustomerDto crmCustomerDto = new CrmCustomerDto();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(initiateUnitInformation.getuUseUnit())) {
            if (NumberUtils.isNumber(initiateUnitInformation.getuUseUnit())) {
                if (initiateUnitInformation.getInfoWrite() != null) {
                    if (initiateUnitInformation.getInfoWrite().booleanValue()) {
                        //更新单元信息
                        crmCustomerDto.setId(Integer.parseInt(initiateUnitInformation.getuUseUnit()));
                        crmCustomerDto.setLegalRepresentative(initiateUnitInformation.getuLegalRepresentative());
                        crmCustomerDto.setAddress(initiateUnitInformation.getuAddress());
                        crmCustomerDto.setBusinessScope(initiateUnitInformation.getuScopeOperation());
                        crmCustomerDto.setCertificateNumber(initiateUnitInformation.getuCertificateNumber());
                        if (NumberUtils.isNumber(initiateUnitInformation.getuUnitProperties())) {
                            crmCustomerDto.setUnitProperties(Integer.parseInt(initiateUnitInformation.getuUnitProperties()));
                        }
                        crmRpcCustomerService.updateCustomer(crmCustomerDto);
                    }
                }
            }
        }
        initiateContactsService.writeCrmCustomerDto(initiateUnitInformation.getProjectId(), InitiateContactsEnum.UNIT_INFORMATION.getId());
    }

    public Integer saveAndUpdate(InitiateUnitInformation initiateUnitInformation) throws Exception {
        if (initiateUnitInformation == null) {
            return null;
        }
        if (initiateUnitInformation.getId() == null || initiateUnitInformation.getId().intValue() == 0) {
            initiateUnitInformation.setCreator(commonService.thisUserAccount());
            Integer id = dao.add(initiateUnitInformation);
            initiateContactsService.update(id, InitiateContactsEnum.UNIT_INFORMATION.getId());
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(InitiateUnitInformation.class), id);
            return id;
        } else {
            dao.update(initiateUnitInformation);
            return initiateUnitInformation.getId();
        }
    }


    public InitiateUnitInformationVo getDataByProjectId(Integer projectId) {
        if (projectId == null) {
            return null;
        }
        InitiateUnitInformation initiateUnitInformation = new InitiateUnitInformation();
        initiateUnitInformation.setProjectId(projectId);
        List<InitiateUnitInformation> initiateUnitInformationList = initiateUnitInformationList(initiateUnitInformation);
        if (!ObjectUtils.isEmpty(initiateUnitInformationList)) {
            return getInitiateUnitInformationVo(initiateUnitInformationList.get(0));
        }
        return null;
    }

    public InitiateUnitInformation get(Integer id) {
        return dao.get(id);
    }

    public boolean remove(Integer id) {
        initiateContactsService.remove(id, InitiateContactsEnum.UNIT_INFORMATION.getId());
        return dao.remove(id);
    }


    public List<InitiateUnitInformation> initiateUnitInformationList(InitiateUnitInformation query) {

        return dao.initiateUnitInformationList(query);
    }

    public InitiateUnitInformationVo getInitiateUnitInformationVo(InitiateUnitInformation unitInformation) {
        if (unitInformation == null) {
            return null;
        }
        InitiateUnitInformationVo vo = new InitiateUnitInformationVo();
        BeanUtils.copyProperties(unitInformation, vo);
        if (!StringUtils.isEmpty(unitInformation.getuUseUnit())) {
            if (NumberUtils.isNumber(unitInformation.getuUseUnit())) {
                CrmCustomerDto customer = null;
                try {
                    if (NumberUtils.isNumber(unitInformation.getuUseUnit())) {
                        customer = crmRpcCustomerService.getCustomer(Integer.valueOf(unitInformation.getuUseUnit()));
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                if (customer != null) {
                    vo.setuUseUnitName(customer.getName());
                }
            }
        }
        List<CrmBaseDataDicDto> crmBaseDataDicDtos = new ArrayList<>();
        try {
            crmBaseDataDicDtos = crmRpcBaseDataDicService.getUnitPropertiesList();
        } catch (Exception e) {
            //crm 未知错误
            logger.error(e.getMessage(), e);
            List<BaseDataDic> cacheDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_INITIATE_UNIT_TYPE);
            if (CollectionUtils.isNotEmpty(cacheDataDicList)) {
                for (BaseDataDic baseDataDic : cacheDataDicList) {
                    CrmBaseDataDicDto crmBaseDataDicDto = new CrmBaseDataDicDto();
                    crmBaseDataDicDto.setId(baseDataDic.getId());
                    crmBaseDataDicDto.setName(baseDataDic.getName());
                    crmBaseDataDicDtos.add(crmBaseDataDicDto);
                }
            }
        }

        if (!StringUtils.isEmpty(unitInformation.getuUnitProperties())) {
            if (!ObjectUtils.isEmpty(crmBaseDataDicDtos)) {
                for (CrmBaseDataDicDto dicDto : crmBaseDataDicDtos) {
                    if (Objects.equal(dicDto.getId(), Integer.parseInt(unitInformation.getuUnitProperties()))) {
                        vo.setuUnitPropertiesName(dicDto.getName());
                        break;
                    }
                }
            }
        }
        return vo;
    }

    public void clear() {
        initiateContactsService.remove(0, InitiateContactsEnum.UNIT_INFORMATION.getId());
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setCreater(commonService.thisUserAccount());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(InitiateUnitInformation.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto attachmentDto : sysAttachmentDtoList) {
                baseAttachmentService.deleteAttachmentByDto(attachmentDto);
            }
        }
    }
}
