package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsCenter;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEconomicIndicatorsCenterService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/11/22 18:28
 * @Description:
 */
@RequestMapping(value = "/declareBuildEconomicIndicatorsCenter")
@Controller
public class DeclareBuildEconomicIndicatorsCenterController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildEconomicIndicatorsCenterService declareBuildEconomicIndicatorsCenterService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildEconomicIndicatorsCenterById", method = {RequestMethod.GET}, name = "获取规划经济指标 中间表")
    public HttpResult getById(Integer id) {
        DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter = null;
        try {
            if (id != null) {
                declareBuildEconomicIndicatorsCenter = declareBuildEconomicIndicatorsCenterService.getDeclareBuildEconomicIndicatorsCenterById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildEconomicIndicatorsCenter);
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareBuildEconomicIndicatorsCenter", method = {RequestMethod.POST}, name = "更新规划经济指标 中间表")
    public HttpResult saveAndUpdate(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter) {
        try {
            Integer id = declareBuildEconomicIndicatorsCenterService.saveAndUpdateDeclareBuildEconomicIndicatorsCenter(declareBuildEconomicIndicatorsCenter);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareBuildEconomicIndicatorsCenter", method = {RequestMethod.GET}, name = "规划经济指标 中间表 list")
    public HttpResult list(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter) {
        try {
            if (declareBuildEconomicIndicatorsCenter != null) {
                return HttpResult.newCorrectResult(declareBuildEconomicIndicatorsCenterService.declareBuildEconomicIndicatorsCenterList(declareBuildEconomicIndicatorsCenter));
            }else {
                return HttpResult.newErrorResult("异常");
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
    
}
