package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
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

    @ResponseBody
    @PostMapping(name = "重启任务", value = "/replyProjectPlanDetails")
    public HttpResult replyProjectPlanDetails(Integer planDetailsId, String reason) {
        try {
            projectPlanDetailsService.replyProjectPlanDetails(planDetailsId, reason);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("重启任务", e);
            return HttpResult.newErrorResult("重启任务异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "调整责任人", value = "/updateExecuteUser")
    public HttpResult updateExecuteUser(Integer planDetailsId, String newExecuteUser) {
        try {
            projectPlanDetailsService.updateExecuteUser(planDetailsId, newExecuteUser);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("调整责任人", e);
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "任务信息粘贴", value = "/taskPaste")
    public HttpResult taskPaste(Integer copyPlanDetailsId, String pastePlanDetailsIds) {
        try {
            projectPlanDetailsService.taskPaste(copyPlanDetailsId, pastePlanDetailsIds);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("调整责任人", e);
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }
}
