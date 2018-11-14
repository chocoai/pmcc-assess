package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetwork;
import com.copower.pmcc.assess.service.cases.CaseEstateNetworkService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/9/17 11:26
 * @Description:
 */
@RequestMapping(value = "/caseEstateNetwork")
@Controller
public class CaseEstateNetworkController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseEstateNetworkService caseEstateNetworkService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateNetworkById", method = {RequestMethod.GET}, name = "获取通信网络")
    public HttpResult getById(Integer id) {
        CaseEstateNetwork caseEstateNetwork = null;
        try {
            if (id != null) {
                caseEstateNetwork = caseEstateNetworkService.getEstateNetworkById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseEstateNetwork);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateNetworkList", method = {RequestMethod.GET}, name = "获取通信网络列表")
    public BootstrapTableVo getCaseEstateNetworkList(String name, Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseEstateNetwork caseEstateNetwork = new CaseEstateNetwork();
            if (!StringUtils.isEmpty(name)) {
                caseEstateNetwork.setName(name);
            }
            if (estateId != null) {
                caseEstateNetwork.setEstateId(estateId);
            }
            vo = caseEstateNetworkService.getCaseEstateNetworkList(caseEstateNetwork);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseEstateNetworkById", method = {RequestMethod.POST}, name = "删除通信网络")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(caseEstateNetworkService.deleteEstateNetwork(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseEstateNetwork", method = {RequestMethod.POST}, name = "更新通信网络")
    public HttpResult save(CaseEstateNetwork caseEstateNetwork) {
        try {
            if (caseEstateNetwork.getId() == null || caseEstateNetwork.getId().equals(0)) {
                caseEstateNetworkService.saveCaseEstateNetwork(caseEstateNetwork);
            } else {
                caseEstateNetworkService.updateEstateNetwork(caseEstateNetwork);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
