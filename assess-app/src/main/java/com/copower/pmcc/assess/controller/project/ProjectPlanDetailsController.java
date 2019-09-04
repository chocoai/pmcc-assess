package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

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
    public HttpResult replyProjectPlanDetails(Integer planDetailsId, String formData) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.replyProjectPlanDetails(planDetailsId, formData);
            return HttpResult.newCorrectResult(projectPlanDetailsService.getPlanDetailListByProjectPlanDetailId(projectPlanDetailsVo.getId()));
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("重启任务", e);
            return HttpResult.newErrorResult("重启任务异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "调整责任人", value = "/updateExecuteUser")
    public HttpResult updateExecuteUser(Integer planDetailsId, String newExecuteUser) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.updateExecuteUser(planDetailsId, newExecuteUser);
            return HttpResult.newCorrectResult(projectPlanDetailsVo);
        } catch (Exception e) {
            logger.error("调整责任人", e);
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "批量调整责任人", value = "/batchUpdateExecuteUser")
    public HttpResult batchUpdateExecuteUser(String planDetailsIds, String newExecuteUser) {
        try {
            String[] split = planDetailsIds.split(",");
            List<String> strings = Arrays.asList(split);
            List<Integer> transform = LangUtils.transform(strings, p -> Integer.valueOf(p));
            projectPlanDetailsService.batchUpdateExecuteUser(transform, newExecuteUser);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("调整责任人", e);
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "任务信息粘贴", value = "/taskPaste")
    public HttpResult taskPaste(Integer copyPlanDetailsId, Integer pastePlanDetailsId) {
        try {
            projectPlanDetailsService.taskPaste(copyPlanDetailsId, pastePlanDetailsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("项目详情粘贴数据", e);
            return HttpResult.newErrorResult("粘贴数据异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "获取数据", value = "/getProjectPlanDetailsById")
    public HttpResult replyProjectPlanDetails(Integer id) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getPlanDetailListByProjectPlanDetailId(id);
            return HttpResult.newCorrectResult(projectPlanDetailsVo);
        } catch (Exception e) {
            logger.error("获取数据", e);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }
}
