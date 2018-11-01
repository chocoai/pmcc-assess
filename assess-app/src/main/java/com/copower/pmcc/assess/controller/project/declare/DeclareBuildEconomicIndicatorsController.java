package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.service.project.declare.DeclareBuildEconomicIndicatorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
