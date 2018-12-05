package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTagging;
import com.copower.pmcc.assess.service.basic.BasicEstateTaggingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: wangpc
 * @Date: 2018/10/24 15:56
 * @Description:楼盘信息地图标注
 */
@RequestMapping(value = "/basicEstateTagging")
@Controller
public class BasicEstateTaggingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;

    @RequestMapping(value = "/index", name = "楼盘相关信息地图标注", method = {RequestMethod.GET})
    public ModelAndView index(Integer estateId, String estateName) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/modelView/estateTaggingView", "0", 0, "0", "");
        modelAndView.addObject("estateId", estateId == null ? 0 : estateId);
        modelAndView.addObject("estateName", estateName);
        modelAndView.addObject("thisTitle", "楼盘信息标注");
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "楼盘相关信息地图标注详情", method = {RequestMethod.GET})
    public ModelAndView detail(Integer estateId, String estateName) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/modelView/estateTaggingDetail", "0", 0, "0", "");
        modelAndView.addObject("estateId", estateId == null ? 0 : estateId);
        modelAndView.addObject("estateName", estateName);
        modelAndView.addObject("thisTitle", "楼盘信息标注");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/addBasicEstateTagging", name = "新增标注", method = {RequestMethod.POST})
    public HttpResult addBasicEstateTagging(BasicEstateTagging basicEstateTagging) {
        try {
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateTagging", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateTagging(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicEstateTaggingService.deleteBasicEstateTagging(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    //?source=0&estateId=1&buildingNumber=1&unitNumber=1&houseNumber=1101
    //0 过程库 1案例库
}
