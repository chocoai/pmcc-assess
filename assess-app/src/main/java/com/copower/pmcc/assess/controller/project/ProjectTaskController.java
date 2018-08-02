package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectTaskService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @data: 2018/1/30
 * @time: 14:06
 */
@Controller
@RequestMapping(value = "/ProjectTask")
public class ProjectTaskController {
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectTaskService projectTaskService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;

    @RequestMapping(value = "/projectTaskIndex", name = "提交工作成果公共页面")
    public ModelAndView projectTaskIndex(Integer responsibilityId) {
        String viewUrl = "projectTaskAssist";
        ProjectResponsibilityDto projectPlanResponsibility = null;
        try {
            projectPlanResponsibility = bpmRpcProjectTaskService.getProjectTaskById(responsibilityId);
        } catch (Exception e) {
            return publicService.getExplainPage("提示", "该任务不存在或已处理");
        }
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanResponsibility.getPlanDetailsId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.isNotBlank(projectPhase.getPhaseForm())) {
            viewUrl = projectPhase.getPhaseForm();
        }
        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.applyView(projectPlanDetails);
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        List<SysAttachmentDto> projectPhaseProcessTemplate = baseAttachmentService.getProjectPhaseProcessTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseProcessTemplate", projectPhaseProcessTemplate);
        List<SysAttachmentDto> projectPhaseWorkTemplate = baseAttachmentService.getProjectPhaseWorkTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseWorkTemplate", projectPhaseWorkTemplate);
        //显示数据
        Object boxCnName = modelAndView.getModel().get("boxCnName");
        if (boxCnName == null) {
            modelAndView.addObject("boxCnName", String.format("%s-成果提交", projectPlanDetails.getProjectPhaseName()));
        } else {
            modelAndView.addObject("boxCnName", String.format("%s-成果提交", boxCnName.toString()));
        }

        modelAndView.addObject("boxprocess Icon", "fa-flash");
        modelAndView.addObject("currentStepName", "成果提交");
        modelAndView.addObject("currUserName", "管理员");
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("projectId", projectPlanDetails.getProjectId());

        modelAndView.addObject("responsibilityId", responsibilityId);
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitTask", name = "提交工作成果数据", method = RequestMethod.POST)
    public HttpResult submitTask(String formData, String taskRemarks, String actualHours, Integer projectDetailsId, Integer responsibilityId, String nextApproval) {
        try {
            projectTaskService.submitTask(formData, taskRemarks, actualHours, projectDetailsId, nextApproval, responsibilityId);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectTaskApproval", name = "工作成果审批")
    public ModelAndView projectTaskApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        String viewUrl = "projectTaskAssist";

        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.isNotBlank(projectPhase.getPhaseForm())) {
            viewUrl = projectPhase.getPhaseForm();
        }
        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.approvalView(processInsId, taskId, boxId, projectPlanDetails, agentUserAccount);
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        List<SysAttachmentDto> projectPhaseProcessTemplate = baseAttachmentService.getProjectPhaseProcessTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseProcessTemplate", projectPhaseProcessTemplate);
        List<SysAttachmentDto> projectPhaseWorkTemplate = baseAttachmentService.getProjectPhaseWorkTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseWorkTemplate", projectPhaseWorkTemplate);
        //显示数据
        Object boxCnName = modelAndView.getModel().get("boxCnName");
        if (boxCnName == null) {
            modelAndView.addObject("boxCnName", String.format("%s-成果审批", projectPlanDetails.getProjectPhaseName()));
        } else {
            modelAndView.addObject("boxCnName", String.format("%s-成果审批", boxCnName.toString()));
        }

        modelAndView.addObject("viewUrl", viewUrl);
        modelAndView.addObject("projectId", projectPlanDetails.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitTaskApproval", name = "审批提交的工作成果数据", method = RequestMethod.POST)
    public HttpResult submitTaskApproval(ApprovalModelDto approvalModelDto, String formData) {
        try {
            projectTaskService.submitTaskApproval(approvalModelDto, formData);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectTaskEdit", name = "工作成果返回修改")
    public ModelAndView projectTaskEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        String viewUrl = "projectTaskAssist";

        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.isNotBlank(projectPhase.getPhaseForm())) {
            viewUrl = projectPhase.getPhaseForm();
        }
        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.returnEditView(processInsId, taskId, boxId, projectPlanDetails, agentUserAccount);
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        List<SysAttachmentDto> projectPhaseProcessTemplate = baseAttachmentService.getProjectPhaseProcessTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseProcessTemplate", projectPhaseProcessTemplate);
        List<SysAttachmentDto> projectPhaseWorkTemplate = baseAttachmentService.getProjectPhaseWorkTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseWorkTemplate", projectPhaseWorkTemplate);
        //显示数据
        Object boxCnName = modelAndView.getModel().get("boxCnName");
        if (boxCnName == null) {
            modelAndView.addObject("boxCnName", String.format("%s-成果修改", projectPlanDetails.getProjectPhaseName()));
        } else {
            modelAndView.addObject("boxCnName", String.format("%s-成果修改", boxCnName.toString()));
        }

        modelAndView.addObject("viewUrl", viewUrl);
        modelAndView.addObject("projectId", projectPlanDetails.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @RequestMapping(value = "/projectTaskDetails", name = "工作成果详情")
    public ModelAndView projectTaskDetails(String processInsId, Integer boxId) {
        String viewUrl = "projectTaskAssist";

        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        return getProjectTaskDetails(boxId, viewUrl, projectPlanDetails);
    }

    @RequestMapping(value = "/projectTaskDetailsById", name = "工作成果详情", method = RequestMethod.GET)
    public ModelAndView projectTaskDetailsById(Integer projectDetailsId) {
        String viewUrl = "projectTaskAssist";
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectDetailsId);
        int boxId = 0;
        if(StringUtils.isNotBlank(projectPlanDetails.getProcessInsId())&&!projectPlanDetails.getProcessInsId().equals("0")){
            BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(projectPlanDetails.getProcessInsId());
            boxId = boxRuDto.getBoxId();
        }
        return getProjectTaskDetails(boxId, viewUrl, projectPlanDetails);
    }

    private ModelAndView getProjectTaskDetails(Integer boxId, String viewUrl, ProjectPlanDetails projectPlanDetails) {
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.isNotBlank(projectPhase.getPhaseForm())) {
            viewUrl = projectPhase.getPhaseForm();
        }
        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.detailsView(projectPlanDetails, boxId);
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        List<SysAttachmentDto> projectPhaseProcessTemplate = baseAttachmentService.getProjectPhaseProcessTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseProcessTemplate", projectPhaseProcessTemplate);
        List<SysAttachmentDto> projectPhaseWorkTemplate = baseAttachmentService.getProjectPhaseWorkTemplate(projectPhase.getId());
        modelAndView.addObject("projectPhaseWorkTemplate", projectPhaseWorkTemplate);
        //显示数据
        Object boxCnName = modelAndView.getModel().get("boxCnName");
        if (boxCnName == null) {
            modelAndView.addObject("boxCnName", String.format("%s-成果详情", projectPlanDetails.getProjectPhaseName()));
        } else {
            modelAndView.addObject("boxCnName", String.format("%s-成果详情", boxCnName.toString()));
        }

        modelAndView.addObject("viewUrl", viewUrl);
        modelAndView.addObject("projectId", projectPlanDetails.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitTaskEdit", name = "提交返回修改", method = RequestMethod.POST)
    public HttpResult submitTaskEdit(ApprovalModelDto approvalModelDto, String formData, String taskRemarks, String actualHours) {
        try {
            projectTaskService.submitPlanEdit(approvalModelDto, formData, taskRemarks, actualHours);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
