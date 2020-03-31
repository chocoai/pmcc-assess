package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitCommonPart;
import com.copower.pmcc.assess.service.basic.BasicUnitCommonPartService;
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
@RequestMapping(value = "/basicUnitCommonPart")
@Controller
public class BasicUnitCommonPartController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitCommonPartService basicUnitCommonPartService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitCommonPartById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitCommonPartById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitCommonPartService.getBasicUnitCommonPartById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnitCommonPart", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnitCommonPart(BasicUnitCommonPart basicUnitCommonPart){
        try {
            return HttpResult.newCorrectResult(200,basicUnitCommonPartService.saveAndUpdateBasicUnitCommonPart(basicUnitCommonPart,true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnitCommonPart", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitCommonPart(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitCommonPartService.deleteBasicUnitCommonPart(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnitCommonPart basicUnitCommonPart, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            return basicUnitCommonPartService.getBootstrapTableVo(basicUnitCommonPart);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitCommonPartList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitCommonPartList(BasicUnitCommonPart basicUnitCommonPart){
        try {
            return HttpResult.newCorrectResult(200,basicUnitCommonPartService.basicUnitCommonPartList(basicUnitCommonPart));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
}
