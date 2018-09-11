package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.service.cases.CaseEstateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/9/11 17:13
 * @Description:
 */
@RequestMapping(value = "/caseEstate")
@Service
public class CaseEstateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseEstateService caseEstateService;

    @RequestMapping(value = "/view", name = "转到index页面 ",method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/case/caseEstateView" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateById", method = {RequestMethod.GET}, name = "获取案例 楼盘")
    public HttpResult getCaseEstateById(Integer id) {
        CaseEstate caseEstate = null;
        try {
            if (id != null) {
                caseEstate = caseEstateService.getCaseEstateById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseEstate);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseEstateById", method = {RequestMethod.POST}, name = "删除案例 楼盘")
    public HttpResult deleteCaseEstateById(Integer id) {
        try {
            if (id != null) {
                caseEstateService.deleteCaseEstate(id);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseEstate", method = {RequestMethod.POST}, name = "更新案例 楼盘")
    public HttpResult saveAndUpdateCaseEstate(CaseEstate caseEstate) {
        try {
            caseEstateService.saveAndUpdateCaseEstate(caseEstate);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
    
}
