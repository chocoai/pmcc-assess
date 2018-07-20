package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingLeisurePlaceService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/7/20 18:18
 * @Description:休闲场所-包含-购物-娱乐-餐饮
 */
@RequestMapping(value = "/examineMatchingLeisurePlace")
@Controller
public class ExamineMatchingLeisurePlaceController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExamineMatchingLeisurePlaceService examineMatchingLeisurePlaceService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Deprecated
    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/task/survey/examine/residence/apply/matchingMarket" ; //购物商场
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

}
