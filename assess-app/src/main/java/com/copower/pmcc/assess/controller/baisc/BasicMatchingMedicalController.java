package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingMedical;
import com.copower.pmcc.assess.service.basic.BasicMatchingMedicalService;
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
 * @Date: 2018/11/6 11:53
 * @Description:
 */
@RequestMapping(value = "/basicMatchingMedical")
@Controller
public class BasicMatchingMedicalController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicMatchingMedicalService basicMatchingMedicalService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicMatchingMedicalById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingMedicalById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMedicalService.getBasicMatchingMedicalById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicMatchingMedical", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMedicalService.saveAndUpdateBasicMatchingMedical(basicMatchingMedical));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicMatchingMedical", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicMatchingMedical(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMedicalService.deleteBasicMatchingMedical(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicMatchingMedical basicMatchingMedical, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicMatchingMedical != null){
                if (!approval) {
                    basicMatchingMedical.setCreator(commonService.thisUserAccount());
                }
            }
            return basicMatchingMedicalService.getBootstrapTableVo(basicMatchingMedical);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicMatchingMedicalList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicMatchingMedicalList(BasicMatchingMedical basicMatchingMedical){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingMedicalService.basicMatchingMedicalList(basicMatchingMedical));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
