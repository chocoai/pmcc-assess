package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping(value = "/case",name = "案例 基础")
public class CaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/estateSearch", name = "楼盘案例查询",method = {RequestMethod.GET})
    public ModelAndView estateSearch() {
        String view = "/case/estateSearch" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/houseSearch", name = "房屋案例查询",method = {RequestMethod.GET})
    public ModelAndView houseSearch() {
        String view = "/case/houseSearch" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }
}
