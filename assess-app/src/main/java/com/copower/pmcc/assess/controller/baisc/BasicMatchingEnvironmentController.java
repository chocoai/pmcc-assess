package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEnvironment;
import com.copower.pmcc.assess.service.basic.BasicMatchingEnvironmentService;
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
 * @Date: 2018/11/6 11:45
 * @Description:
 */
@RequestMapping(value = "/basicMatchingEnvironment")
@Controller
public class BasicMatchingEnvironmentController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicMatchingEnvironmentService basicMatchingEnvironmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicMatchingEnvironmentById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingEnvironmentById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEnvironmentService.getBasicMatchingEnvironmentById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicMatchingEnvironment", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicMatchingEnvironment(BasicMatchingEnvironment basicMatchingEnvironment){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEnvironmentService.saveAndUpdateBasicMatchingEnvironment(basicMatchingEnvironment,true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicMatchingEnvironment", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicMatchingEnvironment(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEnvironmentService.deleteBasicMatchingEnvironment(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicMatchingEnvironment basicMatchingEnvironment,@RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            return basicMatchingEnvironmentService.getBootstrapTableVo(basicMatchingEnvironment);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicMatchingEnvironmentList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicMatchingEnvironmentList(BasicMatchingEnvironment basicMatchingEnvironment){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEnvironmentService.basicMatchingEnvironmentList(basicMatchingEnvironment));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
