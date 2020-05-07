package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicAlternativeCaseDao basicAlternativeCaseDao;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/realEstate/detail/unit";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject(StringUtils.uncapitalize(BasicUnit.class.getSimpleName()), basicUnitService.getBasicUnitById(id));
        return modelAndView;
    }

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
            return HttpResult.newCorrectResult(basicUnitService.saveAndUpdate(basicUnit, true));
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
    @RequestMapping(value = "/basicUnitList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitList(BasicUnit basicUnit) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.getBasicUnitList(basicUnit));
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
    @RequestMapping(value = "/getCaseUnitList", method = {RequestMethod.GET}, name = "获取案例 单元列表")
    public BootstrapTableVo getCaseUnitList(Integer buildingId) {
        if (buildingId == null) return null;
        BasicUnit basicUnit = new BasicUnit();
        basicUnit.setBuildingId(buildingId);
        basicUnit.setBisCase(true);
        BootstrapTableVo vo = basicUnitService.getBootstrapTableVo(basicUnit);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseUnit", method = {RequestMethod.GET}, name = "单元-- 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String unitNumber, Integer caseBuildingId) {
        try {
            List<CustomCaseEntity> caseEntities = basicUnitService.autoCompleteCaseUnit(unitNumber, caseBuildingId);
            return HttpResult.newCorrectResult(caseEntities);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteFromAlternative", name = "引用备选案例数据", method = {RequestMethod.GET})
    public HttpResult quoteFromAlternative(Integer id, Integer tableId) {
        try {
            BasicAlternativeCase alternativeCase = basicAlternativeCaseDao.getBasicAlternativeCaseById(id);
            BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailDao.getInfoById(alternativeCase.getBatchDetailId());
            ArrayList<String> ignoreList = Lists.newArrayList("estateId", "buildingId");
            BasicUnit basicUnit = (BasicUnit) basicUnitService.copyBasicEntityIgnore(applyBatchDetail.getTableId(), tableId, true, ignoreList);
            return HttpResult.newCorrectResult(basicUnit);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteCaseUnit", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseUnit(Integer sourceId, Integer targetId) {
        try {
            ArrayList<String> ignoreList = Lists.newArrayList("estateId", "buildingId");
            BasicUnit basicUnit = (BasicUnit) basicUnitService.copyBasicEntityIgnore(sourceId, targetId, true, ignoreList);
            basicUnit.setQuoteId(sourceId);
            basicUnit.setBisCase(false);
            basicUnitService.saveAndUpdate(basicUnit, false);
            return HttpResult.newCorrectResult(basicUnit);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
