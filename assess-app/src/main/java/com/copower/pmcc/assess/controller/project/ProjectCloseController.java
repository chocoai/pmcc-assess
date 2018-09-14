package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectClose;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.project.manage.ProjectCloseService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/25
 * @time: 15:58
 */
@Controller
@RequestMapping(value = "/projectClose", name = "项目终止")
public class ProjectCloseController {

    @Autowired
    private ProjectCloseService projectCloseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/closeIndex", name = "项目终止申请页")
    public ModelAndView closeIndex(String projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/close/closeIndex","0", 0, "0","");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(Integer.valueOf(projectId));
        ProjectClose projectClose = new ProjectClose();
        projectClose.setProjectId(projectInfo.getId());
        projectClose.setId(0);
        projectClose.setProjectName(projectInfo.getProjectName());
        modelAndView.addObject("projectId",projectId);
        modelAndView.addObject("projectInfo", projectInfo);
        modelAndView.addObject("projectClose", projectClose);

        modelAndView.addObject("processInsId",0);
        modelAndView.addObject("boxCnName", projectInfo.getProjectName() + "-项目终止申请");
        modelAndView.addObject("boxprocessIcon", "fa-flash");
        modelAndView.addObject("currentStepName", "终止申请");
        modelAndView.addObject("currUserName", processControllerComponent.getThisUserInfo().getUserName());
        return modelAndView;
    }


    @RequestMapping(value = "/closeApproval", name = "项目审批页")
    public ModelAndView closeApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/close/closeApproval",
                processInsId, boxId, taskId, agentUserAccount);
        ProjectClose projectClose = projectCloseService.getProjectClose(processInsId);
        modelAndView.addObject("projectClose", projectClose);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectClose.getProjectId());
        modelAndView.addObject("projectInfo", projectInfo);

        modelAndView.addObject("processInsId",processInsId);
        modelAndView.addObject("projectFlog", "");
        modelAndView.addObject("boxCnName", projectInfo.getProjectName() + "-项目终止审批");
        return modelAndView;
    }

    @RequestMapping(value = "/closeDetails", name = "详情页面")
    public ModelAndView closeDetails(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = closeApproval(processInsId, "-1", boxId, agentUserAccount);
        modelAndView.addObject("flog", "details");
        return modelAndView;
    }

    @RequestMapping(value = "/closeEditIndex", name = "返回修改")
    public ModelAndView closeEditIndex(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/close/closeIndex",
                processInsId, boxId, taskId, agentUserAccount);
        ProjectClose projectClose = projectCloseService.getProjectClose(processInsId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectClose.getProjectId());
        modelAndView.addObject("projectInfo", projectInfo);
        modelAndView.addObject("projectClose", projectClose);

        modelAndView.addObject("processInsId",processInsId);
        modelAndView.addObject("projectFlog", "");
        modelAndView.addObject("boxCnName", projectInfo.getProjectName() + "-返回修改");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveClose", name = "提交发起流程", method = RequestMethod.POST)
    public HttpResult saveClose(Integer projectId, String reason) {
        try {

            projectCloseService.saveProjectClose(projectId, reason);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveEditClose", name = "返回修改提交", method = RequestMethod.POST)
    public HttpResult saveEditClose(String reason, Integer boxId, String processInsId, String taskId, String
            activityName, String activityCnName) {
        try {
            projectCloseService.saveEditProjectClose(reason, boxId, processInsId, taskId, activityName, activityCnName);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/submitClose", name = "审批提交", method = RequestMethod.POST)
    public HttpResult submitClose(ApprovalModelDto approvalModelDto) {
        try {
            projectCloseService.approvalSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
