package com.copower.pmcc.assess.controller.project.plan;

import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-8-23.
 */
@Controller
@RequestMapping("/projectPlanDetails")
public class ProjectPlanDetailsController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @ResponseBody
    @PostMapping(name = "是否所有计划任务已完成", value = "/isAllFinish")
    public HttpResult isAllFinish(Integer planDetailsId) {
        try {
            return HttpResult.newCorrectResult(projectPlanDetailsService.isAllFinish(planDetailsId));
        } catch (Exception e) {
            logger.error("是否所有计划任务已完成", e);
            return HttpResult.newErrorResult("是否所有计划任务已完成异常");
        }
    }
}
