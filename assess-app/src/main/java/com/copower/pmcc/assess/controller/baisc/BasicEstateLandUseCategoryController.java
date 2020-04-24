package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseCategory;
import com.copower.pmcc.assess.service.basic.BasicEstateLandUseCategoryService;
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
 * @Date: 2018/11/6 11:39
 * @Description:
 */
@RequestMapping(value = "/basicEstateLandUseCategory")
@Controller
public class BasicEstateLandUseCategoryController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateLandUseCategoryService basicEstateLandUseCategoryService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateLandUseCategoryById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateLandUseCategoryById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateLandUseCategoryService.getBasicEstateLandUseCategoryById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateLandUseCategory", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateLandUseCategory(String formData) {
        try {
            BasicEstateLandUseCategory basicEstateLandUseCategory = JSON.parseObject(formData, BasicEstateLandUseCategory.class);
            return HttpResult.newCorrectResult(200, basicEstateLandUseCategoryService.saveAndUpdateBasicEstateLandUseCategory(basicEstateLandUseCategory, false));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateLandUseCategory", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateLandUseCategory(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateLandUseCategoryService.deleteBasicEstateLandUseCategory(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandUseCategory basicEstateLandUseCategory) {
        try {
            return basicEstateLandUseCategoryService.getBootstrapTableVo(basicEstateLandUseCategory);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateLandUseCategoryList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateLandUseCategoryList(BasicEstateLandUseCategory basicEstateLandUseCategory) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateLandUseCategoryService.basicEstateLandUseCategoryList(basicEstateLandUseCategory));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
