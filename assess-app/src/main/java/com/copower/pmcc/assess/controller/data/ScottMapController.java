package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zly on 2018/5/3.
 */
@RequestMapping("/scottMap")
@Controller
public class ScottMapController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value="/Index", name="显示高德地图页面")
    public ModelAndView index(){
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/scottMap");
        return modelAndView;
    }
}
