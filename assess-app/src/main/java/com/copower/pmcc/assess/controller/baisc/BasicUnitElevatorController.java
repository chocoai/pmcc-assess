package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitElevator;
import com.copower.pmcc.assess.service.basic.BasicUnitElevatorService;
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
 * @Date: 2018/11/5 16:25
 * @Description:
 */
@RequestMapping(value = "/basicUnitElevator")
@Controller
public class BasicUnitElevatorController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitElevatorService basicUnitElevatorService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitElevatorById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitElevatorById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitElevatorService.getBasicUnitElevatorById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnitElevator", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnitElevator(BasicUnitElevator basicUnitElevator) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitElevatorService.saveAndUpdateBasicUnitElevator(basicUnitElevator, true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnitElevator", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitElevator(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitElevatorService.deleteBasicUnitElevator(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnitElevator basicUnitElevator, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval) {
        try {
            return basicUnitElevatorService.getBootstrapTableVo(basicUnitElevator);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitElevatorList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitElevatorList(BasicUnitElevator basicUnitElevator) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitElevatorService.basicUnitElevatorList(basicUnitElevator));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }
}
