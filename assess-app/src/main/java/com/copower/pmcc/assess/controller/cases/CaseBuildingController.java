package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.assess.service.cases.CaseEstateService;
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
 * @Date: 2018/10/28 14:29
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
    @Autowired
    private CaseEstateService caseEstateService;

    @RequestMapping(value = "/detailView", name = "详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) {
        String view = "/case/caseBuild/caseBuildingView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(id);
        modelAndView.addObject("caseBuilding", caseBuildingService.getCaseBuildingVo(caseBuilding));
        modelAndView.addObject("caseEstate", caseEstateService.getCaseEstateById(caseBuilding.getEstateId()));
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/getBuildingList", method = {RequestMethod.GET}, name = "楼栋--列表")
    public BootstrapTableVo getBootstrapTableVo(Integer estateId) {
        if (estateId == null) return null;
        CaseBuilding caseBuilding = new CaseBuilding();
        caseBuilding.setEstateId(estateId);
        BootstrapTableVo vo = caseBuildingService.getCaseBuildingListVos(caseBuilding);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseBuilding", method = {RequestMethod.GET}, name = "楼栋-- 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String buildingNumber, Integer estateId) {
        try {
            List<CustomCaseEntity> caseEntities = caseBuildingService.autoCompleteCaseBuilding(buildingNumber, estateId);
            return HttpResult.newCorrectResult(caseEntities);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }
}
