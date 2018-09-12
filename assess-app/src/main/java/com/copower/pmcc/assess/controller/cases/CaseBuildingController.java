package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/9/11 18:02
 * @Description:
 */
@RequestMapping(value = "/caseBuilding")
@Controller
public class CaseBuildingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseBuildingService caseBuildingService;

    @RequestMapping(value = "/appView", name = "转到新增页面 ", method = RequestMethod.GET)
    public ModelAndView appView(Integer estateId) {
        String view = "/case/caseBuild/apply/caseBuildingView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("estateId", estateId);
        return modelAndView;
    }

    @RequestMapping(value = "/editView", name = "转到编辑页面 ", method = RequestMethod.GET)
    public ModelAndView editView(Integer id) {
        String view = "/case/caseBuild/apply/caseBuildingView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("caseBuilding",caseBuildingService.getCaseBuildingById(id));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingById", method = {RequestMethod.GET}, name = "获取案例 楼栋")
    public HttpResult getCaseBuildingById(Integer id) {
        CaseBuilding caseBuilding = null;
        try {
            if (id != null) {
                caseBuilding = caseBuildingService.getCaseBuildingById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuilding);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingList", method = {RequestMethod.GET}, name = "获取案例 楼栋列表")
    public BootstrapTableVo getCaseBuildingList(Integer estateId) {
        CaseBuilding caseBuilding = new CaseBuilding();
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (estateId != null) {
                caseBuilding.setEstateId(estateId);
                vo = caseBuildingService.getCaseBuildingListVos(caseBuilding);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseBuildingById", method = {RequestMethod.POST}, name = "删除案例 楼栋")
    public HttpResult deleteCaseBuildingById(Integer id) {
        try {
            if (id != null) {
                caseBuildingService.deleteCaseBuilding(id);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseBuilding", method = {RequestMethod.POST}, name = "更新案例 楼栋")
    public HttpResult saveAndUpdateCaseBuilding(CaseBuilding caseBuilding) {
        try {
            caseBuildingService.saveAndUpdateCaseBuilding(caseBuilding);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
