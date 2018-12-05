package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.service.cases.*;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 18:01
 * @Description:
 */
@RequestMapping(value = "/caseUnit")
@Controller
public class CaseUnitController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseUnitDecorateService caseUnitDecorateService;
    @Autowired
    private CaseUnitElevatorService caseUnitElevatorService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseEstateService caseEstateService;


    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) {
        String view = "/case/caseUnit/caseUnitView";
        CaseUnit caseUnit = null;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        caseUnit = caseUnitService.getCaseUnitById(id);
        modelAndView.addObject("caseUnit", caseUnit);
        modelAndView.addObject("hasUnitDecorateData",caseUnitDecorateService.hasUnitDecorateData(id));
        modelAndView.addObject("hasUnitElevatorData",caseUnitElevatorService.hasUnitElevatorData(id));
        modelAndView.addObject("hasUnitHuxingData",caseUnitHuxingService.hasUnitHuxingData(id));
        CaseBuildingMain caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(caseUnit.getBuildingMainId());
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuildingMain.getEstateId());
        modelAndView.addObject("caseBuildingMain", caseBuildingMain);
        modelAndView.addObject("caseEstate", caseEstate);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseUnitById", method = {RequestMethod.GET}, name = "获取案例 单元")
    public HttpResult getCaseUnitById(Integer id) {
        CaseUnit caseUnit = null;
        try {
            if (id != null) {
                caseUnit = caseUnitService.getCaseUnitById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseUnit);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseUnitList", method = {RequestMethod.GET}, name = "获取案例 单元列表")
    public BootstrapTableVo getCaseUnitList(Integer caseBuildingMainId) {
        CaseUnit caseUnit = new CaseUnit();
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (caseBuildingMainId != null) {
                caseUnit.setBuildingMainId(caseBuildingMainId);
                vo = caseUnitService.getCaseUnitListVos(caseUnit);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseUnitById", method = {RequestMethod.POST}, name = "删除案例 单元")
    public HttpResult deleteCaseUnitById(Integer id) {
        CaseUnit caseUnit = null;
        List<CaseHouse> caseHouseList = null;
        CaseHouse caseHouse = new CaseHouse();
        try {
            if (id != null) {
                caseHouse.setUnitId(id);
                caseHouseList = caseHouseService.getCaseHouseList(caseHouse);
                if (caseHouseList.size() >= 1) {
                    return HttpResult.newErrorResult("请删除此单元下的房屋之后在删除此单元! remove fail");
                }
                caseUnit = caseUnitService.getCaseUnitById(id);
                caseUnitService.deleteCaseUnit(id);
                return HttpResult.newCorrectResult(caseUnit.getBuildingId());
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseUnit", method = {RequestMethod.POST}, name = "更新案例 单元")
    public HttpResult saveAndUpdateCaseUnit(CaseUnit caseUnit) {
        try {
            caseUnitService.saveAndUpdateCaseUnit(caseUnit);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initAndUpdateSon", method = {RequestMethod.POST}, name = "初始化子类")
    public HttpResult initAndUpdateSon() {
        try {
            caseUnitService.initAndUpdateSon(0, null);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseUnit", method = {RequestMethod.GET}, name = "单元-- 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String unitNumber, Integer caseBuildingMainId, Integer maxRows) {
        try {
            List<CustomCaseEntity> caseEntities = caseUnitService.autoCompleteCaseUnit(unitNumber, caseBuildingMainId, maxRows);
            return HttpResult.newCorrectResult(caseEntities);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }
}
