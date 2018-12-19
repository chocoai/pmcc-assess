package com.copower.pmcc.assess.controller.assess;

import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kings on 2018-5-17.
 */
@Controller
@RequestMapping(value = "/map", name = "高德地图相关处理")
public class MapController {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
    public ModelAndView houseTagging(Integer attachmentId, String lng, String lat, String deg, Boolean readonly) {
        ModelAndView modelAndView = new ModelAndView("base/houseTaggingView");
        if (attachmentId != null) {
            String huxingImg = baseAttachmentService.getViewImageUrl(attachmentId);
            modelAndView.addObject("huxingImg", huxingImg);
        }
        modelAndView.addObject("attachmentId", attachmentId);
        modelAndView.addObject("lng", StringUtils.isEmpty(lng) ? "" : lng);
        modelAndView.addObject("lat", StringUtils.isEmpty(lat) ? "" : lat);
        modelAndView.addObject("deg", StringUtils.isEmpty(deg) ? "" : deg);
        modelAndView.addObject("readonly", readonly == null ? true : readonly);
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
