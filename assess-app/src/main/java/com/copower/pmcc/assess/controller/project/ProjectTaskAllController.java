package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanTaskAll;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.input.project.ProjectTaskAllBackDto;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.manage.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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
 * @data: 2017/10/19
 * @time: 13:35
 */
@Controller
@RequestMapping(value = "/projectTaskAll", name = "项目工作成果整体复核")
public class ProjectTaskAllController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;

    @RequestMapping(value = "/projectTaskAllIndex", name = "整体复核页面")
    public ModelAndView projectTaskAllIndex(Integer planId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskAll/taskAllIndex", "", 0, "0", "");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);

        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-整体复核");
        modelAndView.addObject("boxprocessIcon", "fa-flash");
        modelAndView.addObject("currentStep", "成果提交");
        modelAndView.addObject("currUserName", "管理员");
        modelAndView.addObject("planId", planId);
        modelAndView.addObject("planName", projectPlan.getPlanName());
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");

        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoById(projectPlan.getProjectId()));

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        modelAndView.addObject("bisSelectUser", checkNextNodeSelectUser(projectWorkStage.getReviewBoxName(), -1));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectTaskAllSubmit", name = "工作成果整体复核提交", method = RequestMethod.POST)
    public HttpResult projectTaskAllSubmit(String conclusion, Integer planId, String backData, String appointUserAccount) {
        try {
            List<ProjectTaskAllBackDto> taskAllBackDtos = JSON.parseArray(backData, ProjectTaskAllBackDto.class);
            projectTaskAllService.startTaskAllApproval(conclusion, planId, taskAllBackDtos, appointUserAccount);
        } catch (Exception ex) {
            return HttpResult.newErrorResult(ex.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectTaskAllApproval", name = "整体复核页面")
    public ModelAndView projectTaskAllApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskAll/taskAllApproval", processInsId, boxId, taskId, agentUserAccount);

        ProjectPlanTaskAll projectPlanTaskAll = projectTaskAllService.getObjectByProcessInsId(processInsId);
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanTaskAll.getProjectPlanId());
        modelAndView.addObject("planId", projectPlan.getId());
        modelAndView.addObject("planName", projectPlan.getPlanName());
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-整体复核");
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");

        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoById(projectPlan.getProjectId()));

        return modelAndView;
    }

    @RequestMapping(value = "/projectTaskAllEdit", name = "整体复核返回修改")
    public ModelAndView projectTaskAllEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskAll/taskAllIndex", processInsId, boxId, taskId, agentUserAccount);

        ProjectPlanTaskAll projectPlanTaskAll = projectTaskAllService.getObjectByProcessInsId(processInsId);
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanTaskAll.getProjectPlanId());
        modelAndView.addObject("planId", projectPlan.getId());
        modelAndView.addObject("planName", projectPlan.getPlanName());
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-整体复核返回修改");
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");

        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoById(projectPlan.getProjectId()));
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        modelAndView.addObject("bisSelectUser", checkNextNodeSelectUser(projectWorkStage.getReviewBoxName(), -1));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectTaskAllApprovalSubmit", name = "工作成果整体复核提交", method = RequestMethod.POST)
    public HttpResult projectTaskAllApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            projectTaskAllService.projectTaskAllApprovalSubmit(approvalModelDto);
        } catch (Exception ex) {
            return HttpResult.newErrorResult(ex.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/projectTaskAllEditSubmit", name = "工作成果整体复核提交", method = RequestMethod.POST)
    public HttpResult projectTaskAllEditSubmit(ApprovalModelDto approvalModelDto, String conclusion, Integer planId, String backData) {
        try {
            List<ProjectTaskAllBackDto> taskAllBackDtos = JSON.parseArray(backData, ProjectTaskAllBackDto.class);
            projectTaskAllService.projectTaskAllEditSubmit(approvalModelDto, conclusion, planId, taskAllBackDtos);
        } catch (Exception ex) {
            return HttpResult.newErrorResult(ex.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectTaskAllDetails", name = "整体复核详情")
    public ModelAndView projectTaskAllDetails(String processInsId, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskAll/taskAllApproval", processInsId, boxId, "-1", "");
        ProjectPlanTaskAll projectPlanTaskAll = projectTaskAllService.getObjectByProcessInsId(processInsId);
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanTaskAll.getProjectPlanId());
        modelAndView.addObject("planId", projectPlan.getId());
        modelAndView.addObject("planName", projectPlan.getPlanName());
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-整体复核");
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");

        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoById(projectPlan.getProjectId()));
        return modelAndView;
    }

    @RequestMapping(value = "/projectTaskAllDetailsById", name = "整体复核详情")
    public ModelAndView projectTaskAllDetailsById(Integer taskAllId) {
        ProjectPlanTaskAll projectPlanTaskAll = projectTaskAllService.getObjectById(taskAllId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskAll/taskAllApproval", projectPlanTaskAll.getProcessInsId(), 0, "-1", "");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanTaskAll.getProjectPlanId());
        modelAndView.addObject("planId", projectPlan.getId());
        modelAndView.addObject("planName", projectPlan.getPlanName());
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-整体复核");
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");

        return modelAndView;
    }

    public String checkNextNodeSelectUser(String boxName, Integer currentStep) {
        return processControllerComponent.checkNextNodeSelectUser(boxName, currentStep) ? "1" : "";
    }
}
