package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.EvaluationBasisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 13426 on 2018/4/28.
 */
@RequestMapping(value = "/evaluationBasis")
@Controller
public class EvaluationBasisController {

    @Autowired
    private EvaluationBasisService service;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ControllerComponent controllerComponent;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/evaluationBasisView");
        modelAndView.addObject("useList", baseDataDics);
        return modelAndView;
    }
}
