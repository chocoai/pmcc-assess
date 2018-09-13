package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.service.cases.CaseUnitService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/appView", name = "转到新增页面 ", method = RequestMethod.GET)
    public ModelAndView appView(Integer buildingId) {
        String view = "/case/caseUnit/apply/caseUnitView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("buildingId", buildingId);
        return modelAndView;
    }

    @RequestMapping(value = "/editView", name = "转到编辑页面 ", method = RequestMethod.GET)
    public ModelAndView editView(Integer id,@RequestParam(defaultValue = "false") boolean copy) {
        String view = "/case/caseUnit/apply/caseUnitView";
        CaseUnit caseUnit = null;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        caseUnit = caseUnitService.getCaseUnitById(id) ;
        if (copy){
            //复制数据 需要把id设为null
            caseUnit.setId(null);
            //处理附件,所有附件则把附件复制后保存后的id传入页面显示
            //附件暂且不处理
        }
        modelAndView.addObject("caseUnit",caseUnit);
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
    public BootstrapTableVo getCaseUnitList(Integer buildingId) {
        CaseUnit caseUnit = new CaseUnit();
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (buildingId != null) {
                caseUnit.setBuildingId(buildingId);
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
        try {
            if (id != null) {
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
}
