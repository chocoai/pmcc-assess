package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEducation;
import com.copower.pmcc.assess.service.basic.BasicMatchingEducationService;
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
 * @Date: 2018/11/6 11:44
 * @Description:
 */
@RequestMapping(value = "/basicMatchingEducation")
@Controller
public class BasicMatchingEducationController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicMatchingEducationService basicMatchingEducationService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicMatchingEducationById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingEducationById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEducationService.getBasicMatchingEducationById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicMatchingEducation", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEducationService.saveAndUpdateBasicMatchingEducation(basicMatchingEducation));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicMatchingEducation", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicMatchingEducation(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEducationService.deleteBasicMatchingEducation(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeIds", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult removeIds(String ids){
        try {
            basicMatchingEducationService.removeIds(ids);
            return HttpResult.newCorrectResult(200,ids);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicMatchingEducation basicMatchingEducation,@RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            return basicMatchingEducationService.getBootstrapTableVo(basicMatchingEducation);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicMatchingEducationList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicMatchingEducationList(BasicMatchingEducation basicMatchingEducation){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingEducationService.basicMatchingEducationList(basicMatchingEducation));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
