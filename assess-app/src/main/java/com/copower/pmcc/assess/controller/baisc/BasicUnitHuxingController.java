package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.service.basic.BasicUnitHuxingService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
 * @Date: 2018/11/5 16:27
 * @Description:
 */
@RequestMapping(value = "/basicUnitHuxing")
@Controller
public class BasicUnitHuxingController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitHuxingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitHuxingById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitHuxingService.getBasicUnitHuxingVo(basicUnitHuxingService.getBasicUnitHuxingById(id)));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnitHuxing", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing){
        try {
            return HttpResult.newCorrectResult(200,basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnitHuxing", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitHuxing(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitHuxingService.deleteBasicUnitHuxing(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnitHuxing basicUnitHuxing){
        try {
            if (basicUnitHuxing != null){
                basicUnitHuxing.setCreator(commonService.thisUserAccount());
            }
            return basicUnitHuxingService.getBootstrapTableVo(basicUnitHuxing);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitHuxingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing){
        try {
            return HttpResult.newCorrectResult(200,basicUnitHuxingService.basicUnitHuxingList(basicUnitHuxing));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
