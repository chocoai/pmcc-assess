package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
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
 * @Date: 2018/10/24 15:56
 * @Description:楼盘
 */
@RequestMapping(value = "/basicEstate")
@Controller
public class BasicEstateController {
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private PublicBasicService publicBasicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.getBasicEstateById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstate", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstate(BasicEstate basicEstate) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.saveAndUpdateBasicEstate(basicEstate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstate", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstate(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.deleteBasicEstate(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstate basicEstate) {
        try {
            return basicEstateService.getBootstrapTableVo(basicEstate);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateList(BasicEstate basicEstate) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.basicEstateList(basicEstate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getBasicEstateByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateByApplyId(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.getBasicEstateMapByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addEstateAndLandstate", name = "添加楼盘及土地基本信息", method = {RequestMethod.POST})
    public HttpResult addEstateAndLandstate(String estateName, String province, String city) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.addEstateAndLandstate(estateName, province, city));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("添加楼盘及土地基本信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/appWriteEstate", name = "过程数据转移", method = {RequestMethod.POST})
    public HttpResult appWriteEstate(Integer caseEstateId, String estatePartInMode,Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.appWriteEstate(caseEstateId, estatePartInMode,applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
