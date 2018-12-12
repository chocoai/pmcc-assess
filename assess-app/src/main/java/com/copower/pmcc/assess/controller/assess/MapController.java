package com.copower.pmcc.assess.controller.assess;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTagging;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private BasicUnitService basicUnitService;
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
    public ModelAndView houseTagging(@RequestParam(defaultValue = "4506") String sysAttachmentId, Integer unitId, String click) {
        ModelAndView modelAndView = new ModelAndView("base/houseTaggingView");
        if (NumberUtils.isNumber(sysAttachmentId)) {
            String huxingImg = baseAttachmentService.getViewImageUrl(Integer.parseInt(sysAttachmentId));
            modelAndView.addObject("huxingImg", huxingImg);
        }
        BasicEstateTagging tagging = null;
        try {
            tagging = basicUnitService.getBasicEstateTagging(unitId);
        } catch (Exception e1) {
            logger.error("", e1);
        }

        if (tagging == null) {
            tagging = new BasicEstateTagging();
            tagging.setLng("104.084335");
            tagging.setLat("30.590403");
        }
        if (NumberUtils.isNumber(sysAttachmentId)) {
            tagging.setAttachmentId(Integer.parseInt(sysAttachmentId));
        }
        modelAndView.addObject("click", click);
        modelAndView.addObject("tagging", tagging);
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
