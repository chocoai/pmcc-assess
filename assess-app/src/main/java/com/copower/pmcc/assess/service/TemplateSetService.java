package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dto.output.CrmTreeDto;
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
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/21
 * @time: 14:59
 */
@Service
public class TemplateSetService {
    @Autowired
    private CrmRpcCustomerService crmRpcCustomerService;

    public CrmTreeDto getCrmTree() {
        CrmCustomerDto crmCustomerDto = new CrmCustomerDto();
        List<CrmCustomerDto> customerList = crmRpcCustomerService.getCustomerList(crmCustomerDto);

        List<CrmTreeDto> crmTreeDtos = new ArrayList<>();

        CrmTreeDto crmTreeDto = new CrmTreeDto();
        crmTreeDto.setId(0);
        crmTreeDto.setText("公司模板");
        crmTreeDto.setpId(-1);
        crmTreeDtos.add(crmTreeDto);

        List<CrmCustomerDto> filter = LangUtils.filter(customerList, o -> o.getPid() == 0);//取第一级客户信息
        for (CrmCustomerDto item : filter) {
            crmTreeDto = new CrmTreeDto();
            crmTreeDto.setId(item.getId());
            crmTreeDto.setText(item.getName());
            crmTreeDto.setpId(-1);
            List<CrmTreeDto> crmTreeChildeDto = getCrmTreeChildeDto(item.getId(), customerList);
            if(crmTreeChildeDto.size()>0) {
                crmTreeDto.setNodes(crmTreeChildeDto);
            }
            crmTreeDtos.add(crmTreeDto);
        }

        crmTreeDto = new CrmTreeDto();
        crmTreeDto.setId(-1);
        crmTreeDto.setText("客户模板");
        crmTreeDto.setpId(0);
        crmTreeDto.setNodes(crmTreeDtos);
        return crmTreeDto;

    }

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
                if(crmTreeChildeDto.size()>0) {
                    crmTreeDto.setNodes(crmTreeChildeDto);
                }
                crmTreeDtos.add(crmTreeDto);
            }

        }
        return crmTreeDtos;
    }
}
