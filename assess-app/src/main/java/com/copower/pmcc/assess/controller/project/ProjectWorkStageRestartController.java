package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.entity.ProjectWorkStageRestart;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.assess.service.project.ProjectWorkStageRestartService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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
 * @data: 2017/10/31
 * @time: 14:36
 */
@Controller
@RequestMapping(value = "/ProjectWorkStageRestart", name = "项目阶段重启功能实现")
public class ProjectWorkStageRestartController {
    @Autowired
    private ProjectWorkStageRestartService projectWorkStageRestartService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/restartApply", name = "申请页面")
    public ModelAndView restartApply(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/workStageRestart/restartApply", "0", 0, "0", "");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectWorkStageRestart projectWorkStageRestart = new ProjectWorkStageRestart();
        projectWorkStageRestart.setProjectId(projectId);
        projectWorkStageRestart.setProjectThisWorkStage(projectPlanService.getProjectCurrStage(projectId));
        projectWorkStageRestart.setProjectName(projectInfo.getProjectName());
        projectWorkStageRestart.setId(0);
        modelAndView.addObject("projectWorkStageRestart", projectWorkStageRestart);
        modelAndView.addObject("projectId", projectId);
        //取已完成和正在执行的项目计划
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanListByFinish(projectId);
        List<ProjectPlan> filter = LangUtils.filter(projectPlans, o -> {
            return (o.getProjectStatus().equals(ProjectStatusEnum.FINISH.getName()) || o.getProjectStatus().equals(ProjectStatusEnum.TASK.getName())) && o.getStageSort()>1;
        });
        List<KeyValueDto> keyValueDtos = LangUtils.transform(filter, projectPlan -> {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(projectPlan.getId()));
            keyValueDto.setValue(projectPlan.getPlanName());
            return keyValueDto;
        });
        modelAndView.addObject("keyValueDtos", keyValueDtos);
        modelAndView.addObject("boxCnName", projectInfo.getProjectName() + "-阶段重启");
        modelAndView.addObject("boxprocessIcon", "fa-flash");
        modelAndView.addObject("currentStepName", "阶段重启");
        modelAndView.addObject("currUserName", processControllerComponent.getThisUserInfo().getUserName());
        return modelAndView;
    }

    @RequestMapping(value = "/restartApproval", name = "审批")
    public ModelAndView restartApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/workStageRestart/restartApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectWorkStageRestart projectWorkStageRestart = projectWorkStageRestartService.getProjectWorkStageRestartItem(processInsId);
        modelAndView.addObject("projectWorkStageRestart", projectWorkStageRestart);
        return modelAndView;
    }

    @RequestMapping(value = "/restartDetails", name = "详情")
    public ModelAndView restartDetails(String processInsId, Integer boxId) {
        ModelAndView modelAndView = restartApproval(processInsId, "-1", boxId, "");
        modelAndView.addObject("flog", "details");
        return modelAndView;
    }

    @RequestMapping(value = "/restartEdit", name = "返回修改")
    public ModelAndView restartEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/workStageRestart/restartApply", processInsId, boxId, taskId, agentUserAccount);
        ProjectWorkStageRestart projectWorkStageRestart = projectWorkStageRestartService.getProjectWorkStageRestartItem(processInsId);
        modelAndView.addObject("projectWorkStageRestart", projectWorkStageRestart);

        //取已完成和正在执行的项目计划
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanListByFinish(projectWorkStageRestart.getProjectId());
        List<KeyValueDto> keyValueDtos = LangUtils.transform(projectPlans, projectPlan -> {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(projectPlan.getId()));
            keyValueDto.setValue(projectPlan.getPlanName());
            return keyValueDto;
        });
        modelAndView.addObject("keyValueDtos", keyValueDtos);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/applyWorkStageRestart", name = "提交发起流程", method = RequestMethod.POST)
    public HttpResult applyWorkStageRestart(ProjectWorkStageRestart projectWorkStageRestart) {
        try {
            projectWorkStageRestartService.applyWorkStageRestart(projectWorkStageRestart);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/editWorkStageRestart", name = "返回修改提交", method = RequestMethod.POST)
    public HttpResult editWorkStageRestart(ApprovalModelDto approvalModelDto, ProjectWorkStageRestart projectWorkStageRestart) {
        try {
            projectWorkStageRestartService.editWorkStageRestart(approvalModelDto, projectWorkStageRestart);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/approvalWorkStageRestart", name = "审批提交", method = RequestMethod.POST)
    public HttpResult approvalWorkStageRestart(ApprovalModelDto approvalModelDto) {
        try {
            projectWorkStageRestartService.approvalWorkStageRestart(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
