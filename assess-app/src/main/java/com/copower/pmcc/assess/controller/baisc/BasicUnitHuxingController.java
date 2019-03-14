package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.service.basic.BasicUnitHuxingService;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:27
 * @Description:
 */
@RequestMapping(value = "/basicUnitHuxing")
@RestController
public class BasicUnitHuxingController {
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getBasicUnitHuxingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitHuxingById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitHuxingService.getBasicUnitHuxingVo(basicUnitHuxingService.getBasicUnitHuxingById(id)));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateBasicUnitHuxing", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteBasicUnitHuxing", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitHuxing(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitHuxingService.deleteBasicUnitHuxing(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnitHuxing basicUnitHuxing, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval) {
        try {
            return basicUnitHuxingService.getBootstrapTableVo(basicUnitHuxing);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @GetMapping(value = "/getSelectHuxingList", name = "获取选择户型数据列表")
    public BootstrapTableVo getSelectHuxingList(Integer basicApplyId, Integer caseUnitId) {
        try {
            return basicUnitHuxingService.getSelectHuxingList(basicApplyId, caseUnitId);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @RequestMapping(value = "/basicUnitHuxingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitHuxingService.basicUnitHuxingList(basicUnitHuxing));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


}
