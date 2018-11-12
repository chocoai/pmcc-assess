package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.service.basic.BasicUnitDecorateService;
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
 * @Date: 2018/11/5 16:23
 * @Description:
 */
@RequestMapping(value = "/basicUnitDecorate")
@Controller
public class BasicUnitDecorateController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitDecorateById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitDecorateById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitDecorateService.getBasicUnitDecorateById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnitDecorate", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate){
        try {
            return HttpResult.newCorrectResult(200,basicUnitDecorateService.saveAndUpdateBasicUnitDecorate(basicUnitDecorate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnitDecorate", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitDecorate(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitDecorateService.deleteBasicUnitDecorate(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnitDecorate basicUnitDecorate, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicUnitDecorate != null){
                if (!approval) {
                    basicUnitDecorate.setCreator(commonService.thisUserAccount());
                }
            }
            return basicUnitDecorateService.getBootstrapTableVo(basicUnitDecorate);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitDecorateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitDecorateList(BasicUnitDecorate basicUnitDecorate){
        try {
            return HttpResult.newCorrectResult(200,basicUnitDecorateService.basicUnitDecorateList(basicUnitDecorate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
}
