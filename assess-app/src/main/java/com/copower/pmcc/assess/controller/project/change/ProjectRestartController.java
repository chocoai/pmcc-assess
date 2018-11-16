package com.copower.pmcc.assess.controller.project.change;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectStateChangeService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: huhao
 * @data: 2018-9-3
 *
 */
@Controller
@RequestMapping(value = "/projectRestart")
public class ProjectRestartController  extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectStateChangeService stateChangeService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/apply", name = "项目重启申请")
    public ModelAndView apply(Integer projectId) throws BusinessException {
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_RESTART_CHANGE_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/restart_change/apply", boxReDto.getId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectInfo.getId());
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "项目重启申请提交")
    public HttpResult applyCommit(ProjectChangeLog costsProjectChangeLog) {
        try {
            stateChangeService.applyCommit(costsProjectChangeLog,BaseParameterEnum.PROJECT_RESTART_CHANGE_PROCESS_KEY, ProjectChangeTypeEnum.RESTART_CHANGE);
        } catch (BusinessException e) {
            log.error("修改项目信息异常", e);
            e.printStackTrace();
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approvalView", name = "项目重启审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/restart_change/approval", processInsId, boxId, taskId, agentUserAccount);
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(costsProjectChangeLog.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectInfo.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/detailsView", name = "详情页", method = RequestMethod.GET)
    public ModelAndView detailsView(Integer boxId, String processInsId) {
        return approvalView(boxId, processInsId, "-1", "");
    }

    @RequestMapping(value = "/approvalCommit", name = "审批提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            stateChangeService.approvalCommit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("提交失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/editView", name = "返回修改视图", method = RequestMethod.GET)
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/restart_change/apply", processInsId, boxId, taskId, agentUserAccount);
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(costsProjectChangeLog.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectInfo.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editCommit(String businessDataJson, ApprovalModelDto approvalModelDto) {
        try {
            ProjectChangeLog costsProjectChangeLog = JSON.parseObject(businessDataJson, ProjectChangeLog.class);
            stateChangeService.editCommit(costsProjectChangeLog, approvalModelDto,ProjectChangeTypeEnum.RESTART_CHANGE);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("修改失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @ResponseBody
    @PostMapping(value="/isChanging",name="判断是否有变更已在流程中,只允许同时存在一个变更流程")
    public HttpResult informationChange(Integer projectId){
        boolean flag = stateChangeService.isChanging(projectId,ProjectChangeTypeEnum.RESTART_CHANGE);
        if(flag){
            return HttpResult.newCorrectResult();
        }else{
            return HttpResult.newErrorResult("项目正在重启中");
        }
    }

}
