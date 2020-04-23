package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateVillage;
import com.copower.pmcc.assess.service.basic.BasicEstateVillageService;
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
@RequestMapping(value = "/basicEstateVillage")
@Controller
public class BasicEstateVillageController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateVillageService basicEstateVillageService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateVillageById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateVillageById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateVillageService.getBasicEstateVillageById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateVillage", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateVillage(BasicEstateVillage basicEstateVillage, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateVillageService.saveAndUpdateBasicEstateVillage(basicEstateVillage, updateNull));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateVillage", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateVillage(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateVillageService.deleteBasicEstateVillage(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateVillage basicEstateVillage) {
        try {
            return basicEstateVillageService.getBootstrapTableVo(basicEstateVillage);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateVillageList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateVillageList(BasicEstateVillage basicEstateVillage) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateVillageService.basicEstateVillageList(basicEstateVillage));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
