package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangpc on 2019-9-30.
 */
@Controller
@RequestMapping("/baseConfig")
public class BaseConfigController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @GetMapping(value = "/systemConfig", name = "系统配置")
    public ModelAndView systemConfig() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/config/systemConfig");
        return modelAndView;
    }

    @GetMapping(value = "/basicConfig", name = "基础配置")
    public ModelAndView basicConfig() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/config/basicConfig");
        return modelAndView;
    }

    @GetMapping(value = "/reportConfig", name = "报告配置")
    public ModelAndView reportConfig() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/config/reportConfig");
        return modelAndView;
    }
}
