package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/19 15:33
 * @Description:
 */
@RequestMapping(value = "/trafficHubTest")
@Controller
public class TrafficHubTestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/matchingTrafficHub";//交通枢纽信息
//        String view = "/task/survey/examine/residence/apply/matchingTransit";//公交
//        String view = "/task/survey/examine/residence/apply/matchingMainRoad";//主干道
//        String view = "/task/survey/examine/residence/apply/matchingMetro";//地铁
        String view = "/task/survey/examine/residence/apply/matchingMainConversion";//主要转换
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

}
