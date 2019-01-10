package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "/selectCase", name = "选择案例", method = RequestMethod.POST)
    public HttpResult selectCase(Integer mcId, String planDetailsIdString) {
        try {
            mdMarketCompareService.selectCase(mcId,planDetailsIdString);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult("保存失败");
        }
    }
}
