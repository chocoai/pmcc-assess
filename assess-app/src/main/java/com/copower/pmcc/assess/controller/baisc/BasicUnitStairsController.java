package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitStairs;
import com.copower.pmcc.assess.service.basic.BasicUnitStairsService;
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
 * @Date: 2018/11/6 11:39
 * @Description:
 */
@RequestMapping(value = "/basicUnitStairs")
@Controller
public class BasicUnitStairsController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitStairsService basicUnitStairsService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitStairsById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitStairsById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitStairsService.getBasicUnitStairsById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnitStairs", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnitStairs(BasicUnitStairs basicUnitStairs,@RequestParam(defaultValue = "false") boolean updateNull){
        try {
            return HttpResult.newCorrectResult(200,basicUnitStairsService.saveAndUpdateBasicUnitStairs(basicUnitStairs,updateNull));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnitStairs", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitStairs(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicUnitStairsService.deleteBasicUnitStairs(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnitStairs basicUnitStairs){
        try {
            return basicUnitStairsService.getBootstrapTableVo(basicUnitStairs);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitStairsList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitStairsList(BasicUnitStairs basicUnitStairs){
        try {
            return HttpResult.newCorrectResult(200,basicUnitStairsService.basicUnitStairsList(basicUnitStairs));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
