package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateNetwork;
import com.copower.pmcc.assess.service.basic.BasicEstateNetworkService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:37
 * @Description:
 */
@RequestMapping(value = "/basicEstateNetwork")
@Controller
public class BasicEstateNetworkController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateNetworkService basicEstateNetworkService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateNetworkById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateNetworkById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateNetworkService.getBasicEstateNetworkById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateNetwork", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork){
        try {
            return HttpResult.newCorrectResult(200,basicEstateNetworkService.saveAndUpdateBasicEstateNetwork(basicEstateNetwork));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateNetwork", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateNetwork(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateNetworkService.deleteBasicEstateNetwork(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateNetwork basicEstateNetwork, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            return basicEstateNetworkService.getBootstrapTableVo(basicEstateNetwork);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateNetworkList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateNetworkList(BasicEstateNetwork basicEstateNetwork){
        try {
            return HttpResult.newCorrectResult(200,basicEstateNetworkService.basicEstateNetworkList(basicEstateNetwork));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
