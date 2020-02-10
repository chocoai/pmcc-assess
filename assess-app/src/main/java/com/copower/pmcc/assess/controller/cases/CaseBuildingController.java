package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.assess.service.cases.CaseEstateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private PublicBasicService publicBasicService;

    @RequestMapping(value = "/detailView", name = "详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/house/detail/building";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(id);
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
        if (caseEstate.getType() != null) {
            modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(caseEstate.getType()).getKey());
        }
        BasicBuilding building = new BasicBuilding();
        building.setDisplayCaseId(id);
        List<BasicBuilding> buildings = basicBuildingDao.getBasicBuildingList(building);
        if (CollectionUtils.isNotEmpty(buildings)) {
            building = buildings.get(0);
        } else {
            basicBuildingDao.addBasicBuilding(building);
        }
        caseBuildingService.quoteCaseBuildToBasic(id, building.getId());
        building.setDisplayCaseId(id);
        basicBuildingDao.updateBasicBuilding(building, false);

        modelAndView.addObject(StringUtils.uncapitalize(BasicBuilding.class.getSimpleName()), publicBasicService.getBasicBuildingById(building.getId()));

        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/getBuildingList", method = {RequestMethod.GET}, name = "楼栋--列表")
    public BootstrapTableVo getBootstrapTableVo(Integer estateId, String buildName) {
        if (estateId == null) return null;
        BootstrapTableVo vo = caseBuildingService.getCaseBuildingListVos(estateId, buildName);
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

    @ResponseBody
    @RequestMapping(value = "/quoteCaseBuildToBasic", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseBuildToBasic(Integer id, Integer tableId) {
        try {
            return HttpResult.newCorrectResult(caseBuildingService.quoteCaseBuildToBasic(id, tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingById", method = {RequestMethod.GET}, name = "获取案例 楼盘")
    public HttpResult getCaseBuildingById(Integer caseBuildingId) {
        CaseBuilding caseBuilding = null;
        try {
            if (caseBuildingId != null) {
                caseBuilding = caseBuildingService.getCaseBuildingById(caseBuildingId);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuilding);
    }

}
