package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dto.input.CrmTreeDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerRelationDto;
import com.copower.pmcc.crm.api.dto.ZtreeDto;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/21
 * @time: 14:59
 */
@Service
public class TemplateSetService {
    @Autowired
    private CrmRpcCustomerService crmRpcCustomerService;
    @Autowired
    private PublicService publicService;
    private Integer relationId=26;

    public List<ZtreeDto> getCrmTree() {
        CrmCustomerDto crmCustomerDto = new CrmCustomerDto();
        crmCustomerDto.setCompanyId(publicService.getCurrentCompany().getCompanyId());

        List<CrmCustomerRelationDto> relationDtos = crmRpcCustomerService.getCrmCustomerRelationListByPid(relationId);
        List<ZtreeDto> treeDtos = new ArrayList<>();

        ZtreeDto dto = new ZtreeDto();
        dto.setId(0);
        dto.setName("公司模板");
        dto.setPid(-1);
        treeDtos.add(dto);

        if(CollectionUtils.isNotEmpty(relationDtos)){
            for (CrmCustomerRelationDto relationDto : relationDtos) {
                ZtreeDto ztreeDto2 = new ZtreeDto();
                ztreeDto2.setId(relationDto.getId());
                ztreeDto2.setPid(relationDto.getPid());
                ztreeDto2.setName(relationDto.getName());
                treeDtos.add(ztreeDto2);
            }

        }
        return treeDtos;

    };
}

