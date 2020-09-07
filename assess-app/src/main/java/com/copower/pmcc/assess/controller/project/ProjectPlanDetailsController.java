package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kings on 2018-8-23.
 */
@RestController
@RequestMapping("/projectPlanDetails")
public class ProjectPlanDetailsController {
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseService baseService;

    @PostMapping(name = "重启任务", value = "/replyProjectPlanDetails")
    public HttpResult replyProjectPlanDetails(Integer planDetailsId, String formData) {
        try {
            projectPlanDetailsService.replyProjectPlanDetails(planDetailsId, formData);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "重启任务");
            return HttpResult.newErrorResult("重启任务异常");
        }
    }

    @PostMapping(name = "调整责任人", value = "/updateExecuteUser")
    public HttpResult updateExecuteUser(Integer planDetailsId, String newExecuteUser) {
        try {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.updateExecuteUser(planDetailsId, newExecuteUser);
            return HttpResult.newCorrectResult(projectPlanDetailsVo);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "调整责任人");
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }

    @PostMapping(name = "批量调整责任人", value = "/batchUpdateExecuteUser")
    public HttpResult batchUpdateExecuteUser(String planDetailsIds, String newExecuteUser) {
        try {
            String[] split = planDetailsIds.split(",");
            List<String> strings = Arrays.asList(split);
            List<Integer> transform = LangUtils.transform(strings, p -> Integer.valueOf(p));
            projectPlanDetailsService.batchUpdateExecuteUser(transform, newExecuteUser);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "调整责任人异常");
            return HttpResult.newErrorResult("调整责任人异常");
        }
    }

    @PostMapping(name = "添加任务", value = "/saveProjectStagePlan")
    public HttpResult saveProjectStagePlan(String formData) {
        try {
            ProjectPlanDetails projectPlanDetails = JSONObject.parseObject(formData, ProjectPlanDetails.class);
            projectPlanDetailsService.addPlanDetailsTask(projectPlanDetails);
            return HttpResult.newCorrectResult(projectPlanDetails);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "添加任务");
            return HttpResult.newErrorResult("添加任务异常");
        }
    }

    @PostMapping(name = "删除任务", value = "/deletePlanDetailsByIds")
    public HttpResult deletePlanDetailsByIds(String planDetailsIds) {
        try {
            List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(planDetailsIds));
            projectPlanDetailsService.deletePlanDetailsByIds(list);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "删除任务");
            return HttpResult.newErrorResult("删除任务异常");
        }
    }

    @RequestMapping(value = "/getProjectPhaseListByPlanId", name = "获取该阶段下的工作事项", method = RequestMethod.GET)
    @ResponseBody
    public BootstrapTableVo getProjectPhaseListByPlanId(Integer planId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectPhase> projectPhaseList = projectPlanDetailsService.getProjectPhaseListByPlanId(planId);
        return new BootstrapTableVo(page.getTotal(), projectPhaseList);
    }

    @PostMapping(name = "绑定区域", value = "/bindAreaGroup")
    public HttpResult bindAreaGroup(Integer planDetailsId, Integer areaGroupId,String areaName) {
        try {
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            if (planDetails != null) {
                planDetails.setAreaId(areaGroupId);
                planDetails.setPlanRemarks(areaName);
                projectPlanDetailsService.saveProjectPlanDetails(planDetails);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "绑定区域");
            return HttpResult.newErrorResult("绑定区域异常");
        }
    }
}
