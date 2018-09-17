package com.copower.pmcc.assess.controller.project.plan;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanHistory;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.manage.ProjectPlanHistoryService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/28
 * @time: 10:07
 */
@Controller
@RequestMapping(value = "/projectPlanHistory", name = "计划总计划变更")
public class ProjectPlanHistoryController {
    @Autowired
    private ProjectPlanHistoryService projectPlanHistoryService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;

    @RequestMapping(value = "/projectPlanHistoryIndex", name = "申请页面")
    public ModelAndView projectPlanHistoryIndex(String projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView
                ("/project/planHistory/projectPlanHistoryIndex", "0",0, "0","");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(Integer.valueOf(projectId));
        modelAndView.addObject("projectInfo", projectInfo);
        List<ProjectPlan> projectPlans =projectPlanService.getProjectplanByProjectId(projectInfo.getId(),"");
        List<ProjectPlan> filter = LangUtils.filter(projectPlans, o -> {
            return o.getBisRestart().equals(false);
        });
        modelAndView.addObject("projectPlans", filter);
        modelAndView.addObject("projectId", projectId);

        modelAndView.addObject("boxCnName", projectInfo.getProjectName() + "-总计划设置");
        modelAndView.addObject("boxprocessIcon", "fa-flash");
        modelAndView.addObject("currentStepName", "总计划设置");
        modelAndView.addObject("currUserName", processControllerComponent.getThisUserInfo().getUserName());
        return modelAndView;
    }


    @RequestMapping(value = "/projectPlanHistoryApproval", name = "审批页面")
    public ModelAndView projectPlanHistoryApproval(String processInsId, String taskId, Integer boxId, String
            agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView
                ("/project/planHistory/projectPlanHistoryApproval",
                        processInsId, boxId, taskId, agentUserAccount);
        List<ProjectPlanHistory> projectPlanHistories = projectPlanHistoryService.getProjectPlanHistoryList
                (processInsId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanHistories.get(0).getProjectId());
        modelAndView.addObject("projectInfo", projectInfo);
        modelAndView.addObject("projectPlanHistories", projectPlanHistories);
        modelAndView.addObject("projectId", projectInfo.getId());

        return modelAndView;
    }

    @RequestMapping(value = "/projectPlanHistoryDetails", name = "详情页面")
    public ModelAndView projectPlanHistoryDetails(String processInsId, Integer boxId) {
        ModelAndView modelAndView = projectPlanHistoryApproval(processInsId, "-1", boxId, "");
        modelAndView.addObject("flog", "details");
        return modelAndView;
    }

    @RequestMapping(value = "/projectPlanHistoryEdit", name = "返回修改")
    public ModelAndView projectPlanHistoryEdit(String processInsId, String taskId, Integer boxId, String
            agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView
                ("/project/planHistory/projectPlanHistoryEdit",
                        processInsId, boxId, taskId, agentUserAccount);
        List<ProjectPlanHistory> projectPlanHistories = projectPlanHistoryService.getProjectPlanHistoryList
                (processInsId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanHistories.get(0).getProjectId());
        modelAndView.addObject("projectInfo", projectInfo);
        modelAndView.addObject("projectPlanHistories", projectPlanHistories);
        modelAndView.addObject("projectId", projectInfo.getId());

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveProjectPlanHistory", name = "提交发起流程", method = RequestMethod.POST)
    public HttpResult saveProjectPlanHistory(Integer projectId, String planString) {
        try {
            projectPlanHistoryService.saveProjectPlanHistory(projectId, planString);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveEditProjectPlanHistory", name = "返回修改提交", method = RequestMethod.POST)
    public HttpResult saveEditProjectPlanHistory(ApprovalModelDto approvalModelDto, String planString) {
        try {
            projectPlanHistoryService.saveEditProjectPlanHistory(approvalModelDto, planString);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/submitProjectPlanHistory", name = "审批提交", method = RequestMethod.POST)
    public HttpResult submitProjectPlanHistory(ApprovalModelDto approvalModelDto) {
        try {
            projectPlanHistoryService.approvalSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
