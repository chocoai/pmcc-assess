package com.copower.pmcc.assess.controller.project.declare;

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
    @RequestMapping(value = "/getEconomicIndicatorsByPid", method = {RequestMethod.GET}, name = "获取经济指标")
    public HttpResult getEconomicIndicatorsByPid(Integer pid) {
        try {
            List<DeclareBuildEconomicIndicators> economicIndicators = declareBuildEconomicIndicatorsService.getEconomicIndicatorsByPid(pid);
            return HttpResult.newCorrectResult(economicIndicators);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }
}
