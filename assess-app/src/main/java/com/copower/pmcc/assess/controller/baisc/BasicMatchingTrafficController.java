package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTraffic;
import com.copower.pmcc.assess.service.basic.BasicMatchingTrafficService;
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
 * @Date: 2018/11/6 11:55
 * @Description:
 */
@RequestMapping(value = "/basicMatchingTraffic")
@Controller
public class BasicMatchingTrafficController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicMatchingTrafficService basicMatchingTrafficService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicMatchingTrafficById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingTrafficById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingTrafficService.getBasicMatchingTrafficById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicMatchingTraffic", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicMatchingTraffic(BasicMatchingTraffic basicMatchingTraffic){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingTrafficService.saveAndUpdateBasicMatchingTraffic(basicMatchingTraffic));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicMatchingTraffic", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicMatchingTraffic(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingTrafficService.deleteBasicMatchingTraffic(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeIds", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult removeIds(String ids){
        try {
            basicMatchingTrafficService.remove(ids);
            return HttpResult.newCorrectResult(200,ids);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicMatchingTraffic basicMatchingTraffic, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicMatchingTraffic != null){
                if (!approval) {
                    basicMatchingTraffic.setCreator(commonService.thisUserAccount());
                }
            }
            return basicMatchingTrafficService.getBootstrapTableVo(basicMatchingTraffic);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicMatchingTrafficList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicMatchingTrafficList(BasicMatchingTraffic basicMatchingTraffic){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingTrafficService.basicMatchingTrafficList(basicMatchingTraffic));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
