package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-7-23.
 */
@Controller
@RequestMapping("/marketCompare")
public class MarketCompareController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private MdMarketCompareService mdMarketCompareService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketCompareIndex");
        MdMarketCompare marketCompare = mdMarketCompareService.getMdMarketCompare(id);
        List<MdMarketCompareField> fieldList = mdMarketCompareService.getFieldListByMcId(marketCompare.getId());
        MdMarketCompareItem evaluationObject = mdMarketCompareService.getEvaluationListByMcId(marketCompare.getId());
        List<MdMarketCompareItem> caseList = mdMarketCompareService.getCaseListByMcId(marketCompare.getId());
        modelAndView.addObject("marketCompareJSON", JSON.toJSONString(marketCompare) );
        modelAndView.addObject("fieldsJSON",JSON.toJSONString(fieldList));
        modelAndView.addObject("evaluationJSON",JSON.toJSONString(evaluationObject));
        modelAndView.addObject("casesJSON",JSON.toJSONString(caseList));
        return modelAndView;
    }
}
