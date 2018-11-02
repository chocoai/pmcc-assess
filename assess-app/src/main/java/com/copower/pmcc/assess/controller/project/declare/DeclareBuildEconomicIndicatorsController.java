package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicators;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEconomicIndicatorsService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/28 09:12
 * @Description:经济指标
 */
@RequestMapping(value = "/economicIndicators")
@Controller
public class DeclareBuildEconomicIndicatorsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildEconomicIndicatorsService declareBuildEconomicIndicatorsService;

    @ResponseBody
    @RequestMapping(value = "/saveEconomicIndicatorsList", method = {RequestMethod.POST}, name = "保存经济指标")
    public HttpResult saveAndUpdate(Integer pid, Integer planDetailsId, String formData) {
        try {
            List<DeclareBuildEconomicIndicators> list = JSON.parseArray(formData, DeclareBuildEconomicIndicators.class);
            declareBuildEconomicIndicatorsService.saveEconomicIndicatorsList(pid, planDetailsId, list);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getEntityListByPid", method = {RequestMethod.POST}, name = "获取经济指标")
    public HttpResult getEntityListByPid(Integer pid) {
        try {
            List<DeclareBuildEconomicIndicators> indicatorsList = declareBuildEconomicIndicatorsService.getEntityListByPid(pid);
            return HttpResult.newCorrectResult(indicatorsList);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
