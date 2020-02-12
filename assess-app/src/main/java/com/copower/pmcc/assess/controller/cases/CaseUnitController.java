package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.cases.*;
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
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private PublicBasicService publicBasicService;


    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/house/detail/unit";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        CaseUnit caseUnit = caseUnitService.getCaseUnitById(id);
        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
        if (caseEstate.getType() != null) {
            modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(caseEstate.getType()).getKey());
        }

        BasicUnit unit = new BasicUnit();
        unit.setDisplayCaseId(id);
        List<BasicUnit> units = basicUnitDao.getBasicUnitList(unit);
        if (CollectionUtils.isNotEmpty(units)) {
            unit = units.get(0);
        } else {
            basicUnitDao.addBasicUnit(unit);
        }
        caseUnitService.quoteCaseUnitToBasic(id, unit.getId());
        unit.setDisplayCaseId(id);
        basicUnitDao.updateBasicUnit(unit, false);

        modelAndView.addObject(StringUtils.uncapitalize(BasicUnit.class.getSimpleName()), publicBasicService.getBasicUnitById(unit.getId()));
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
    public BootstrapTableVo getCaseUnitList(Integer caseBuildingId, String unitName) {
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            vo = caseUnitService.getCaseUnitListVos(caseBuildingId, unitName);
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
    public HttpResult autoCompleteCaseEstate(String unitNumber, Integer caseBuildingId) {
        try {
            List<CustomCaseEntity> caseEntities = caseUnitService.autoCompleteCaseUnit(unitNumber, caseBuildingId);
            return HttpResult.newCorrectResult(caseEntities);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteCaseUnitToBasic", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseUnitToBasic(Integer id, Integer tableId) {
        try {
            return HttpResult.newCorrectResult(caseUnitService.quoteCaseUnitToBasic(id, tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
