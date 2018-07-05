package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSuspend;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPauseService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:项目暂停或启动控制器
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 16:10
 */
@Controller
@RequestMapping(value = "/ProjectSuspend", name = "项目暂停或启动")
public class ProjectPauseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPauseService projectPauseService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/suspendIndex", name = "申请页")
    public ModelAndView suspendIndex(String projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/suspend/suspendIndex", "0", 0, "0", "");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(Integer.valueOf(projectId));
        ProjectSuspend projectSuspend = new ProjectSuspend();
        projectSuspend.setProjectId(projectInfo.getId());
        projectSuspend.setId(0);
        modelAndView.addObject("projectSuspend", projectSuspend);
        modelAndView.addObject("projectId", projectId);
        modelAndView.addObject("boxCnName", projectInfo.getProjectName() + "-项目暂停");
        modelAndView.addObject("boxprocessIcon", "fa-list-alt");
        modelAndView.addObject("currUserName", processControllerComponent.getThisUserInfo().getUserName());
        return modelAndView;
    }

    @RequestMapping(value = "/suspendApproval", name = "审批页")
    public ModelAndView suspendApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/suspend/suspendApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectSuspend projectSuspend = projectPauseService.getProjectSuspendByProcessInsId(processInsId);
        modelAndView.addObject("projectSuspend", projectSuspend);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectSuspend.getProjectId());
        modelAndView.addObject("processInsId", processInsId);
        modelAndView.addObject("projectFlog", "");
        return modelAndView;
    }

    @RequestMapping(value = "/suspendDetails", name = "详情页面")
    public ModelAndView suspendDetails(String processInsId, Integer boxId) {
        ModelAndView modelAndView = suspendApproval(processInsId, "-1", boxId, "");
        modelAndView.addObject("flog", "details");
        return modelAndView;
    }

    @RequestMapping(value = "/suspendDetailsById", name = "详情页面")
    public ModelAndView suspendDetailsById(Integer id) {
        ProjectSuspend projectSuspend = projectPauseService.getProjectSuspendById(id);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/suspend/suspendApproval", projectSuspend.getProcessInsId(), 0, "-1", "");
        modelAndView.addObject("projectSuspend", projectSuspend);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectSuspend.getProjectId());
        modelAndView.addObject("projectFlog", "");
        modelAndView.addObject("flog", "details");
        return modelAndView;
    }

    @RequestMapping(value = "/suspendEditIndex", name = "返回修改")
    public ModelAndView suspendEditIndex(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/suspend/suspendIndex", processInsId, boxId, taskId, agentUserAccount);
        ProjectSuspend projectSuspend = projectPauseService.getProjectSuspendByProcessInsId(processInsId);
        modelAndView.addObject("projectSuspend", projectSuspend);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectSuspend.getProjectId());
        modelAndView.addObject("processInsId", processInsId);
        modelAndView.addObject("projectFlog", "");

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/savesuspend", name = "提交发起流程", method = RequestMethod.POST)
    public HttpResult savesuspend(Integer projectId, String reason) {
        try {
            projectPauseService.saveProjectSuspend(projectId, reason);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveEditsuspend", name = "返回修改提交", method = RequestMethod.POST)
    public HttpResult saveEditsuspend(ApprovalModelDto approvalModelDto, String reason) {
        try {
            projectPauseService.saveEditProjectSuspend(approvalModelDto, reason);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/submitsuspend", name = "审批提交", method = RequestMethod.POST)
    public HttpResult submitsuspend(ApprovalModelDto approvalModelDto) {
        try {
            projectPauseService.submitProjectSuspend(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/startProject", name = "启动项目", method = RequestMethod.POST)
    public HttpResult startProject(Integer projectId) {
        try {
            projectPauseService.startProject(projectId);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectSuspendHistory", name = "取得暂停申请记录", method = RequestMethod.GET)
    public BootstrapTableVo getProjectSuspendHistory(Integer projectId) {
        return projectPauseService.getProjectSuspendHistory(projectId);
    }
}
