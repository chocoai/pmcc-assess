package com.copower.pmcc.assess.controller.assess;

import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kings on 2018-5-17.
 */
@Controller
@RequestMapping(value = "/map", name = "高德地图相关处理")
public class MapController {

    @Autowired
    private BaseAttachmentService baseAttachmentService;

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

    @GetMapping(value = "/houseTagging", name = "房屋标注")
    public ModelAndView houseTagging(@RequestParam(defaultValue = "4506") String sysAttachmentId) {
        ModelAndView modelAndView = new ModelAndView("base/houseTaggingView");
        if (NumberUtils.isNumber(sysAttachmentId)) {
            String huxingImg = baseAttachmentService.getViewImageUrl(Integer.parseInt(sysAttachmentId));
            modelAndView.addObject("huxingImg",huxingImg);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/mapMarkerEstate", name = "楼盘地图标注")
    public ModelAndView mapMarkerEstate(String estateName, String click) {
        ModelAndView modelAndView = new ModelAndView("base/mapMarkerEstate");
        modelAndView.addObject("estateName", estateName);
        modelAndView.addObject("click", click);
        return modelAndView;
    }
}
