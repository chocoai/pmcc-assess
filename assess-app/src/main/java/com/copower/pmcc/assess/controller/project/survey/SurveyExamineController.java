package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping("/surveyExamine")
public class SurveyExamineController {

    @ResponseBody
    @GetMapping(name = "获取版块信息",value = "/getBlockById")
    public HttpResult getBlockById(){

        return null;
    }
}
