package com.copower.pmcc.assess.service;

import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2018-5-4.
 */
@Service
public class CrmCustomerService {
    @Autowired
    private CrmRpcCustomerService crmRpcCustomerService;

    /**
     * 根据客户id获取客户信息
     * @param id
     * @return
     */
    public CrmCustomerDto getCustomer(Integer id) {
        return crmRpcCustomerService.getCustomer(id);
    }

    /**
     * 根据客户id获取联系人信息
     * @param customerId
     * @return
     */
    public List<CrmCustomerLinkmanDto> getCustomerLinkmanList(Integer customerId) {
        return crmRpcCustomerService.getCustomerLinkmanList(customerId);
    }
}
