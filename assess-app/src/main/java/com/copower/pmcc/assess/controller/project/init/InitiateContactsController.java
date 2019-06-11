package com.copower.pmcc.assess.controller.project.init;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.InitiateContacts;
import com.copower.pmcc.assess.service.project.initiate.InitiateContactsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/25 09:58
 * @Description:联系人
 */
@RequestMapping(value = "/initiateContacts")
@RestController
public class InitiateContactsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonService commonService;

    @Autowired
    private InitiateContactsService initiateContactsService;

    @PostMapping(value = "/delete", name = "联系人 删除")
    public HttpResult deleteContacts(@RequestParam(value = "id") Integer id) {
        try {
            initiateContactsService.remove(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @PostMapping(value = "/clear", name = "清除联系人")
    public HttpResult clear(InitiateContacts initiateContacts) {
        try {
            initiateContactsService.clear(initiateContacts);
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "获取 JSON BootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(@RequestParam(name = "approval", defaultValue = "false") Boolean approval, InitiateContacts initiateContacts) {
        if (initiateContacts == null){
            return  null;
        }
        if (approval) {
            return initiateContactsService.getBootstrapTableVo(initiateContacts);
        }else {
            //非审批情况 会加上当前申请人进行查询联系人
            initiateContacts.setCreator(commonService.thisUserAccount());
            return initiateContactsService.getBootstrapTableVo(initiateContacts);
        }
    }

    @PostMapping(value = "/saveAndUpdate", name = "新增或者修改")
    public HttpResult saveAndUpdate(InitiateContacts initiateContacts) {
        try {
            initiateContactsService.saveUpdateInitiateContacts(initiateContacts);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @PostMapping(value = "/saveAndUpdateList", name = "新增或者修改")
    public HttpResult saveAndUpdate(String formData) {
        try {
            List<InitiateContacts> initiateContactsList = JSON.parseArray(formData ,InitiateContacts.class ) ;
            if (CollectionUtils.isNotEmpty(initiateContactsList)){
                initiateContactsList.forEach(initiateContacts -> {
                    initiateContactsService.saveUpdateInitiateContacts(initiateContacts);
                });
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @GetMapping(value = "/get", name = "联系人 获取")
    public HttpResult getContacts(@RequestParam(value = "id") Integer id) {
        try {
            InitiateContacts contacts = initiateContactsService.get(id);
            return HttpResult.newCorrectResult(200, contacts);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/copyContacts", name = "拷贝联系人到其它地方")
    public HttpResult copyContacts(String ids,InitiateContacts initiateContacts){
        try {
            initiateContactsService.copyContacts(ids,initiateContacts);
            return HttpResult.newCorrectResult(200, initiateContacts);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }
}
