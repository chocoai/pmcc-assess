package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnit;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
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
 * @Description:
 */
@RequestMapping(value = "/basicUnit")
@Controller
public class BasicUnitController {
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private PublicBasicService publicBasicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.getBasicUnitById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnit", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnit(BasicUnit basicUnit) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.saveAndUpdateBasicUnit(basicUnit));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnit", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnit(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.deleteBasicUnit(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnit basicUnit) {
        try {
            return basicUnitService.getBootstrapTableVo(basicUnit);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitList(BasicUnit basicUnit) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.basicUnitList(basicUnit));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitByApplyId(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.getBasicUnitByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addUnit", name = "添加单元信息", method = {RequestMethod.POST})
    public HttpResult addUnit(String unitNumber) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.addUnit(unitNumber));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("添加楼盘及土地基本信息异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/appWriteUnit", name = "过程数据转移", method = {RequestMethod.POST})
    public HttpResult appWriteUnit(Integer caseUnitId, String unitPartInMode) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitService.appWriteUnit(caseUnitId, unitPartInMode));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }
}
