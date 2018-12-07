package com.copower.pmcc.assess.controller.assess;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kings on 2018-5-17.
 */
@Controller
@RequestMapping(value = "/map", name = "高德地图相关处理")
public class MapController {

    @RequestMapping(value = "/positionPicker", name = "当前位置定位")
    public ModelAndView positionPicker() {
        ModelAndView modelAndView = new ModelAndView("/base/mapPositionPicker");
        return modelAndView;
    }

    @RequestMapping(value = "/estateTagging", name = "楼盘标注")
    public ModelAndView estateTagging() {
        ModelAndView modelAndView = new ModelAndView("base/estateTaggingView");
        return modelAndView;
    }

    @RequestMapping(value = "/mapMarkerEstate", name = "楼盘地图标注")
    public ModelAndView mapMarkerEstate(String estateName,String click) {
        ModelAndView modelAndView = new ModelAndView("base/mapMarkerEstate");
        modelAndView.addObject("estateName",estateName);
        modelAndView.addObject("click",click);
        return modelAndView;
    }
}
