package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.chks.ChksRuningEnum;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.ProjectTaskDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectTaskService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
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
public class ProjectTaskController extends BaseController {
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
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcToolsService bpmRpcToolsService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;


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
        modelAndView.addObject("projectPhase", projectPhase);
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
        modelAndView.addObject("projectPlanResponsibility", projectPlanResponsibility);
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitTask", name = "提交工作成果数据", method = RequestMethod.POST)
    public HttpResult submitTask(ProjectTaskDto projectTaskDto) {
        try {
            projectTaskService.submitTask(projectTaskDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "提交工作成果数据异常");
            return HttpResult.newErrorResult("提交工作成果数据异常");
        }
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
        //取代理人
        if (StringUtils.isNotBlank(agentUserAccount)) {
            List<String> assignorList = bpmRpcToolsService.getAssignorListByAgent(commonService.thisUserAccount());
            List<String> strings = FormatUtils.transformString2List(agentUserAccount);
            if (CollectionUtils.isNotEmpty(assignorList) && CollectionUtils.isNotEmpty(strings)) {
                Collection intersection = CollectionUtils.intersection(assignorList, strings);
                if (CollectionUtils.isNotEmpty(intersection))
                    agentUserAccount = String.valueOf(Lists.newArrayList(intersection).get(0));
            }
        }

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
        //获取相应的考核项
        BoxReDto boxReDto = null;
        if (boxId != null && boxId != 0) {
            boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        }
        if (boxReDto != null && boxReDto.getBisLaunchCheck() != null && boxReDto.getBisLaunchCheck()) {
            Object activityId = modelAndView.getModel().get("activityId");
            if (activityId != null) {
                Integer boxReActivitiId = (Integer) activityId;
                setCheckParams(boxId, boxReActivitiId, projectPlanDetails, modelAndView, projectPhase);

            }
        }
        modelAndView.addObject("BoxApprovalLogDtoList", chksAssessmentProjectPerformanceService.getFilterBoxApprovalLogVoList(processInsId));

        modelAndView.addObject("viewUrl", viewUrl);
        modelAndView.addObject("projectId", projectPlanDetails.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitTaskApproval", name = "审批提交的工作成果数据", method = RequestMethod.POST)
    public HttpResult submitTaskApproval(ApprovalModelDto approvalModelDto, String formData, String chksScore, String chksRemarks, String byExaminePeople) {
        try {
            projectTaskService.submitTaskApproval(approvalModelDto, formData, chksScore, chksRemarks, byExaminePeople);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
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
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @RequestMapping(value = "/projectTaskDetails", name = "工作成果详情")
    public ModelAndView projectTaskDetails(String processInsId, Integer boxId) {
        String viewUrl = "projectTaskAssist";

        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        return getProjectTaskDetails(boxId, viewUrl, projectPlanDetails, null);
    }

    @RequestMapping(value = "/projectTaskDetailsById", name = "工作成果详情", method = RequestMethod.GET)
    public ModelAndView projectTaskDetailsById(Integer planDetailsId, String responsibilityId) {
        String viewUrl = "projectTaskAssist";
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        int boxId = 0;
        if (StringUtils.isNotBlank(projectPlanDetails.getProcessInsId()) && !projectPlanDetails.getProcessInsId().equals("0")) {
            BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(projectPlanDetails.getProcessInsId());
            boxId = boxRuDto.getBoxId();
        }
        return getProjectTaskDetails(boxId, viewUrl, projectPlanDetails, responsibilityId);
    }

    private ModelAndView getProjectTaskDetails(Integer boxId, String viewUrl, ProjectPlanDetails projectPlanDetails, String responsibilityId) {
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
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        if (boxId != null && boxId != 0) {
            //获取相应的考核项
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
            if (boxReDto.getBisLaunchCheck() != null && boxReDto.getBisLaunchCheck()) {
                setCheckParams(boxId, null, projectPlanDetails, modelAndView, projectPhase);
            }
        }
        if (StringUtils.isNotBlank(responsibilityId)) {
            if (NumberUtils.isNumber(responsibilityId)) {
                ProjectResponsibilityDto projectResponsibilityDto = bpmRpcProjectTaskService.getProjectTaskById(Integer.parseInt(responsibilityId));
                if (projectResponsibilityDto != null) {
                    modelAndView.addObject(StringUtils.uncapitalize(ProjectResponsibilityDto.class.getSimpleName()), projectResponsibilityDto);
                }
            }
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitTaskEdit", name = "提交返回修改", method = RequestMethod.POST)
    public HttpResult submitTaskEdit(ApprovalModelDto approvalModelDto, String formData, String taskRemarks, String actualHours) {
        try {
            projectTaskService.submitPlanEdit(approvalModelDto, formData, taskRemarks, actualHours);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/loadReturnRecordList", name = "取得任务重启信息", method = RequestMethod.GET)
    public BootstrapTableVo loadReturnRecordList(Integer planDetailsId) {
        BootstrapTableVo bootstrapTableVo = projectTaskService.getDataLandLevelListVos(planDetailsId);
        return bootstrapTableVo;
    }

    /**
     * @param boxId
     * @param boxReActivitiId 注意此参数可能是错误的
     * @param modelAndView
     */
    private void setCheckParams(Integer boxId, Integer boxReActivitiId, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView, ProjectPhase projectPhase) {
        //巡查人或者抽查人  可以看到当前模型下 所有人的考核记录
        boolean spotCheck = chksAssessmentProjectPerformanceService.getSpotCheck(boxId, processControllerComponent.getThisUser());
        BoxReActivityDto boxReActivityDto = null;
        if (boxReActivitiId != null) {
            boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(boxReActivitiId);
        }
        List<BoxReActivityDto> boxReActivityDtoList = null;
        if (boxReActivityDto == null) {
            boxReActivityDtoList = chksAssessmentProjectPerformanceService.getFilterBoxReActivityDto(projectPlanDetails.getProcessInsId());
        }
        BoxReActivityDto spotReActivityDto = chksAssessmentProjectPerformanceService.getSpotBoxReActivityDto(boxId);
        if (boxReActivityDto == null && spotReActivityDto == null && CollectionUtils.isEmpty(boxReActivityDtoList)) {
            return;
        }
        if (!spotCheck) {
            if (boxReActivityDto == null && CollectionUtils.isEmpty(boxReActivityDtoList)) {
                return;
            }
        }
        //抽查或者巡查标识符
        modelAndView.addObject("spotCheck", spotCheck);
        //考核能否进行
        modelAndView.addObject("bisCheck", chksAssessmentProjectPerformanceService.getChksRuningEnum(CollectionUtils.isEmpty(boxReActivityDtoList) ? Lists.newArrayList(boxReActivityDto) : boxReActivityDtoList, projectPhase, boxId, projectPlanDetails.getProcessInsId(), boxReActivitiId != null).getKey());
        //考核标识符
        modelAndView.addObject("showCheck", ChksRuningEnum.CHKS_SHOW_ENUM_RUN.getKey());
        modelAndView.addObject(StringUtils.uncapitalize(BoxReDto.class.getSimpleName()), bpmRpcBoxService.getBoxReInfoByBoxId(boxId));
        modelAndView.addObject("boxReActivityDto", boxReActivityDto);//普通考核节点 审批
        modelAndView.addObject("boxReActivityDtoList", boxReActivityDtoList);//普通考核节点 发起的详情task任务
        modelAndView.addObject("spotReActivityDto", spotReActivityDto);//抽查节点
        //当前节点  可以查看的权限节点信息列表
        modelAndView.addObject("activityDtoList", chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceNext(boxId, boxReActivitiId, boxReActivityDtoList, spotCheck));
        if (spotCheck) {
            modelAndView.addObject("spotAssessmentProjectPerformanceList", chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceDtoMap(boxId, projectPlanDetails.getProcessInsId()));
        }
    }

}
