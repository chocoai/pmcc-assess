package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiatePossessorDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.InitiatePossessor;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Lazy
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private InitiateContactsService initiateContactsService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass()) ;

    public Integer saveAndUpdate(InitiatePossessor initiatePossessor) {
        if (initiatePossessor == null) {
            return null;
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(initiatePossessor.getpAddress())) {
            String[] strings = initiatePossessor.getpAddress().split(",");
            Set<String> stringSet = Sets.newHashSet(strings);
            stringSet = stringSet.stream().filter(s -> org.apache.commons.lang3.StringUtils.isNotBlank(s)).collect(Collectors.toSet());
            initiatePossessor.setpAddress(org.apache.commons.lang3.StringUtils.join(stringSet,""));
        }
        if (initiatePossessor.getId() == null || initiatePossessor.getId() == 0) {
            //对资产占有人信息 进行单独处理
            if (InitiateContactsEnum.naturalPerson.getId().equals(initiatePossessor.getpType())) {
                initiatePossessor.setpUnitProperties(null);
                initiatePossessor.setpScopeOperation(null);
                initiatePossessor.setpSociologyCode(null);
                initiatePossessor.setpLegalRepresentative(null);
                initiatePossessor.setpEntrustmentUnit(null);
            }
            if (org.apache.commons.lang3.StringUtils.isEmpty(initiatePossessor.getCreator())) {
                initiatePossessor.setCreator(commonService.thisUserAccount());
            }
            Integer id = dao.add(initiatePossessor);
            initiateContactsService.update(id, InitiateContactsEnum.POSSESSOR.getId());
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(InitiatePossessor.class), id);
            return id;
        } else {
            dao.update(initiatePossessor);
            return initiatePossessor.getId();
        }
    }

    public InitiatePossessor get(Integer id) {
        return dao.get(id);
    }

    public InitiatePossessorVo getDataByProjectId(Integer projectId) {
        InitiatePossessor query = new InitiatePossessor();
        query.setProjectId(projectId);
        List<InitiatePossessor> initiatePossessorList = initiatePossessorList(query);
        if (!ObjectUtils.isEmpty(initiatePossessorList)) {
            return getInitiatePossessorVo(initiatePossessorList.get(0));
        }
        return null;
    }

    public List<InitiatePossessor> initiatePossessorList(InitiatePossessor query) {

        return dao.initiatePossessorList(query);
    }

    public boolean remove(Integer id) {
        initiateContactsService.remove(id, InitiateContactsEnum.POSSESSOR.getId());
        return dao.remove(id);
    }

    public InitiatePossessorVo getInitiatePossessorVo(InitiatePossessor possessor) {
        if (possessor == null) {
            return null;
        }
        InitiatePossessorVo vo = new InitiatePossessorVo();
        BeanUtils.copyProperties(possessor, vo);
        List<CrmBaseDataDicDto> crmBaseDataDicDtos = new ArrayList<>();
        try {
            crmBaseDataDicDtos = crmRpcBaseDataDicService.getUnitPropertiesList();
        } catch (Exception e) {
            //crm 未知错误
            logger.error(e.getMessage(),e);
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

        if (CollectionUtils.isNotEmpty(crmBaseDataDicDtos)) {
            for (CrmBaseDataDicDto dicDto : crmBaseDataDicDtos) {
                if (org.apache.commons.lang3.StringUtils.isEmpty(possessor.getpUnitProperties())){
                    continue;
                }
                if (Objects.equal(possessor.getpUnitProperties(), dicDto.getId().toString())) {
                    vo.setpUnitPropertiesName(dicDto.getName());
                    break;
                }
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(possessor.getpEntrustmentUnit())) {
            if (NumberUtils.isNumber(possessor.getpEntrustmentUnit())) {
                vo.setpEntrustmentUnitName(baseDataDicService.getNameById(Integer.valueOf(possessor.getpEntrustmentUnit())));
            }
        }
        return vo;
    }

    public void clear() {
        initiateContactsService.remove(0, InitiateContactsEnum.POSSESSOR.getId());
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setCreater(commonService.thisUserAccount());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(InitiatePossessor.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto attachmentDto : sysAttachmentDtoList) {
                baseAttachmentService.deleteAttachmentByDto(attachmentDto);
            }
        }
    }
}
