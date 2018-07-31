package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @ResponseBody
    @RequestMapping(value = "/saveResult", name = "保存市场比较法结果", method = RequestMethod.POST)
    public HttpResult saveResult(String formData) {
        try {
            MarketCompareResultDto marketCompareResultDto=JSON.parseObject(formData,MarketCompareResultDto.class);
            MdMarketCompare marketCompare = mdMarketCompareService.saveResult(marketCompareResultDto);
            return HttpResult.newCorrectResult(marketCompare);
        } catch (Exception e) {
            return HttpResult.newErrorResult("保存失败");
        }
    }
}
