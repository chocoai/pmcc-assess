package com.copower.pmcc.assess.controller.project.init;

import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.assess.service.project.initiate.InitiateContactsService;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zch
 * @Date: 2018/12/25 10:25
 * @Description:ERP中用户与用户中的联系人
 */
@RequestMapping(value = "/initiateCrmCustomer")
@RestController
public class InitiateCrmCustomerController {
    @Autowired
    private BaseService baseService;

    @Autowired
    private CrmCustomerService crmCustomerService;

    @Autowired
    private InitiateContactsService initiateContactsService;

    @GetMapping(value = "/getCrmCustomerDto")
    public HttpResult getCrmCustomerDto(Integer customerId){
        CrmCustomerDto crmCustomerDto = null;
        try {
            crmCustomerDto = crmCustomerService.getCustomer(customerId);
            return HttpResult.newCorrectResult(200,crmCustomerDto);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500,"未获取到数据");
        }
    }

    @GetMapping(value = "/getCustomerLinkmanPageList")
    public BootstrapTableVo getBootstrapTableVo(String searchCrm, Integer customerId){
        BootstrapTableVo vo = crmCustomerService.getCustomerLinkmanPageList(customerId,searchCrm);
        return vo;
    }

    @PostMapping(value = "/writeCustomerLinkmanInContacts",name = "把从ERP中获取的联系人写入本地的数据库")
    public HttpResult writeCustomerLinkmanInContacts(Integer customerId, Integer cType,@RequestParam(value = "cPid",defaultValue = "0") Integer cPid){
        try {
            initiateContactsService.writeContacts(customerId, cType, cPid);
            return HttpResult.newCorrectResult(200,customerId);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500,"写入失败!");
        }
    }
}
