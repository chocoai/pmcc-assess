package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingSurface;
import com.copower.pmcc.assess.service.basic.BasicBuildingSurfaceService;
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
 * @Date: 2018/10/30 14:25
 * @Description:层面结构
 */
@RequestMapping(value = "/basicBuildingSurface")
@Controller
public class BasicBuildingSurfaceController {

    @Autowired
    private BasicBuildingSurfaceService basicBuildingSurfaceService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingSurfaceById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingSurfaceById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingSurfaceService.getBasicBuildingSurfaceById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuildingSurface", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingSurfaceService.saveAndUpdateBasicBuildingSurface(basicBuildingSurface));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuildingSurface", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicBuildingSurface(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingSurfaceService.deleteBasicBuildingSurface(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicBuildingSurface basicBuildingSurface){
        try {
            return basicBuildingSurfaceService.getBootstrapTableVo(basicBuildingSurface);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicBuildingSurfaceList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicBuildingSurfaceList(BasicBuildingSurface basicBuildingSurface){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingSurfaceService.basicBuildingSurfaceList(basicBuildingSurface));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

}
