package com.copower.pmcc.assess.service;

import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2018-5-4.
 */
@Service
public class CrmCustomerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
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

    public List<CrmCustomerDto> getCustomerList(CrmCustomerDto crmCustomerDto){
        return crmRpcCustomerService.getCustomerList(crmCustomerDto);
    }

    public List<CrmCustomerLinkmanDto> getCustomerLinkmanPageList(Integer customerId,Integer pageIndex,Integer pageSize,String search){
        return crmRpcCustomerService.getCustomerLinkmanPageList(customerId,pageIndex,pageSize,search);
    }

    /**
     *
     * 功能描述: 暂时CRM系统中没有提供类似的方法
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/8/29 15:14
     */
    public List<CrmCustomerLinkmanDto> getCustomerLinkmanList(CrmCustomerLinkmanDto crmCustomerLinkmanDto){
        return  null;
    }

    /**
     * 根据客户id获取联系人信息
     * @param customerId
     * @return
     */
    public List<CrmCustomerLinkmanDto> getCustomerLinkmanList(Integer customerId) {
        return crmRpcCustomerService.getCustomerLinkmanList(customerId);
    }

    public void updateCrmCustomer(CrmCustomerLinkmanDto crmCustomerLinkmanDto){
        try {
            crmRpcCustomerService.updateCustomerLinkman(crmCustomerLinkmanDto);
        }catch (Exception e1){
            try {
                logger.error("exception:  ===>"+e1.getMessage());
                throw e1;
            }catch (Exception e){

            }
        }
    }

    /**
     * 添加联系人到CRM中
     * @param crmCustomerLinkmanDto
     */
    public void saveCrmCustomer(CrmCustomerLinkmanDto crmCustomerLinkmanDto){
        try {
            crmRpcCustomerService.addCustomerLinkman(crmCustomerLinkmanDto);
        }catch (Exception e1){
            try {
                logger.error("exception:  ===>"+e1.getMessage());
                throw e1;
            }catch (Exception e){

            }
        }
    }
}
