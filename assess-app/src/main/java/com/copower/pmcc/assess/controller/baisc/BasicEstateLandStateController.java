package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandState;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:
 */
@RequestMapping(value = "/basicEstateLandState")
@Controller
public class BasicEstateLandStateController {
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateLandStateById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateLandStateById(Integer id){
        try {
            return HttpResult.newCorrectResult(basicEstateLandStateService.getBasicEstateLandStateById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateLandState", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateLandState(BasicEstateLandState basicEstateLandState){
        try {
            return HttpResult.newCorrectResult(basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateLandState", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateLandState(Integer id){
        try {
            return HttpResult.newCorrectResult(basicEstateLandStateService.deleteBasicEstateLandState(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandState basicEstateLandState){
        try {
            return basicEstateLandStateService.getBootstrapTableVo(basicEstateLandState);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateLandStateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateLandStateList(BasicEstateLandState basicEstateLandState){
        try {
            return HttpResult.newCorrectResult(basicEstateLandStateService.basicEstateLandStateList(basicEstateLandState));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
}
