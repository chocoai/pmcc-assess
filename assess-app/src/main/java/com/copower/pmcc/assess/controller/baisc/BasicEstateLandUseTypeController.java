package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseType;
import com.copower.pmcc.assess.service.basic.BasicEstateLandUseTypeService;
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
@RequestMapping(value = "/basicEstateLandUseType")
@Controller
public class BasicEstateLandUseTypeController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateLandUseTypeService basicEstateLandUseTypeService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateLandUseTypeById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateLandUseTypeById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateLandUseTypeService.getBasicEstateLandUseTypeById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateLandUseType", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateLandUseType(BasicEstateLandUseType basicEstateLandUseType, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateLandUseTypeService.saveAndUpdateBasicEstateLandUseType(basicEstateLandUseType, updateNull));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateLandUseType", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateLandUseType(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateLandUseTypeService.deleteBasicEstateLandUseType(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandUseType basicEstateLandUseType) {
        try {
            return basicEstateLandUseTypeService.getBootstrapTableVo(basicEstateLandUseType);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateLandUseTypeList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateLandUseTypeList(BasicEstateLandUseType basicEstateLandUseType) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateLandUseTypeService.basicEstateLandUseTypeList(basicEstateLandUseType));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
