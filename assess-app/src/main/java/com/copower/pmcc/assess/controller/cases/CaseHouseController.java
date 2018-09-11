package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.service.cases.CaseHouseService;
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
 * @Date: 2018/9/11 18:16
 * @Description:
 */
@RequestMapping(value = "/caseHouse")
@Controller
public class CaseHouseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseService caseHouseService;

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseById", method = {RequestMethod.GET}, name = "获取案例 房屋")
    public HttpResult getCaseHouseById(Integer id) {
        CaseHouse caseHouse = null;
        try {
            if (id != null) {
                caseHouse = caseHouseService.getCaseHouseById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseHouse);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseList", method = {RequestMethod.GET}, name = "获取案例 房屋列表")
    public BootstrapTableVo getCaseHouseList(Integer unitId) {
        CaseHouse caseHouse = new CaseHouse();
        BootstrapTableVo vo = null;
        try {
            if (unitId != null) {
                caseHouse.setUnitId(unitId);
            }
            vo = caseHouseService.getCaseHouseListVos(caseHouse);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseById", method = {RequestMethod.POST}, name = "删除案例 房屋")
    public HttpResult deleteCaseHouseById(Integer id) {
        try {
            if (id != null) {
                caseHouseService.deleteCaseHouse(id);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouse", method = {RequestMethod.POST}, name = "更新案例 房屋")
    public HttpResult saveAndUpdateCaseHouse(CaseHouse caseHouse) {
        try {
            caseHouseService.saveAndUpdateCaseHouse(caseHouse);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
