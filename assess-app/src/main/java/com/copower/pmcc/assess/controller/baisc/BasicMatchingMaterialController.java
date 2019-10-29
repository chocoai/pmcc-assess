package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMaterial;
import com.copower.pmcc.assess.service.basic.BasicMatchingMaterialService;
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
 * @Date: 2018/11/6 11:51
 * @Description:
 */
@RequestMapping(value = "/basicMatchingMaterial")
@Controller
public class BasicMatchingMaterialController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicMatchingMaterialService basicMatchingMaterialService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicMatchingMaterialById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingMaterialById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMaterialService.getBasicMatchingMaterialById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicMatchingMaterial", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMaterialService.saveAndUpdateBasicMatchingMaterial(basicMatchingMaterial,true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicMatchingMaterial", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicMatchingMaterial(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMaterialService.deleteBasicMatchingMaterial(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicMatchingMaterial basicMatchingMaterial, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            return basicMatchingMaterialService.getBootstrapTableVo(basicMatchingMaterial);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicMatchingMaterialList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicMatchingMaterialList(BasicMatchingMaterial basicMatchingMaterial){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMaterialService.basicMatchingMaterialList(basicMatchingMaterial));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
