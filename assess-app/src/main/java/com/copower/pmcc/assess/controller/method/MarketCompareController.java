package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kings on 2018-7-23.
 */
@Controller
@RequestMapping("/marketCompare")
public class MarketCompareController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketCompareIndex");
        return modelAndView;
    }
}
