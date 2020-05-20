package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dto.input.CrmTreeDto;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
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
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/5/21
 * @time: 14:59
 */
@Service
public class TemplateSetService {
    @Autowired
    private CrmRpcCustomerService crmRpcCustomerService;
    @Autowired
    private PublicService publicService;

    public Object getCrmTree() {
        CrmCustomerDto crmCustomerDto = new CrmCustomerDto();
        crmCustomerDto.setCompanyId(publicService.getCurrentCompany().getCompanyId());
        List<CrmCustomerDto> customerList = crmRpcCustomerService.getCustomerList(crmCustomerDto);

        if (CollectionUtils.isNotEmpty(customerList)) {
            return customerList;
        }

        List<ZtreeDto> treeDtos = new ArrayList<>();

        ZtreeDto dto = new ZtreeDto();
        dto.setId(0);
        dto.setName("公司模板");
        dto.setPid(-1);
        treeDtos.add(dto);

        List<CrmCustomerDto> filter = LangUtils.filter(customerList, o -> o.getPid() == 0);//取第一级客户信息
        for (CrmCustomerDto item : filter) {
            ZtreeDto ztreeDto = new ZtreeDto();
            ztreeDto.setId(item.getId());
            ztreeDto.setName(item.getName());
            ztreeDto.setPid(-1);
            treeDtos.add(ztreeDto);
            List<CrmTreeDto> crmTreeChildeDto = getCrmTreeChildeDto(item.getId(), customerList);

            if (CollectionUtils.isNotEmpty(crmTreeChildeDto)) {
                for (CrmTreeDto item2 : crmTreeChildeDto) {
                    ZtreeDto ztreeDto2 = new ZtreeDto();
                    ztreeDto2.setId(item2.getId());
                    ztreeDto2.setPid(item2.getpId());
                    ztreeDto2.setName(item2.getText());
                    treeDtos.add(ztreeDto2);
                }
            }

        }


        return treeDtos;

    }

    ;

    private List<CrmTreeDto> getCrmTreeChildeDto(Integer pid, List<CrmCustomerDto> crmCustomerDtos) {
        List<CrmTreeDto> crmTreeDtos = new ArrayList<>();
        List<CrmCustomerDto> filter = LangUtils.filter(crmCustomerDtos, o -> o.getPid() == pid);
        if (CollectionUtils.isNotEmpty(filter)) {

            for (CrmCustomerDto item : filter) {
                CrmTreeDto crmTreeDto = new CrmTreeDto();
                crmTreeDto.setId(item.getId());
                crmTreeDto.setText(item.getName());
                crmTreeDto.setpId(item.getPid());
                List<CrmTreeDto> crmTreeChildeDto = getCrmTreeChildeDto(item.getId(), crmCustomerDtos);
                if (crmTreeChildeDto.size() > 0) {
                    crmTreeDto.setNodes(crmTreeChildeDto);
                    crmTreeChildeDto.forEach(o -> crmTreeDtos.add(o));
                }
                crmTreeDtos.add(crmTreeDto);
            }

        }
        return crmTreeDtos;
    }
}

