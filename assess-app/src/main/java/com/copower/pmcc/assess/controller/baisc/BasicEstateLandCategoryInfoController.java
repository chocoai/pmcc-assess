package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfo;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
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
@RequestMapping(value = "/basicEstateLandCategoryInfo")
@Controller
public class BasicEstateLandCategoryInfoController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateLandCategoryInfoById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateLandCategoryInfoById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateLandCategoryInfo", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateLandCategoryInfo(String formData,@RequestParam(defaultValue = "false") boolean updateNull){
        try {
            BasicEstateLandCategoryInfo basicEstateLandCategoryInfo = JSONObject.parseObject(formData,BasicEstateLandCategoryInfo.class) ;
            return HttpResult.newCorrectResult(200,basicEstateLandCategoryInfoService.saveAndUpdateBasicEstateLandCategoryInfo(basicEstateLandCategoryInfo,updateNull));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateLandCategoryInfo", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateLandCategoryInfo(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateLandCategoryInfoService.deleteBasicEstateLandCategoryInfo(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo){
        try {
            return basicEstateLandCategoryInfoService.getBootstrapTableVo(basicEstateLandCategoryInfo);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateLandCategoryInfoList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateLandCategoryInfoList(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo){
        try {
            return HttpResult.newCorrectResult(200,basicEstateLandCategoryInfoService.basicEstateLandCategoryInfoList(basicEstateLandCategoryInfo));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
