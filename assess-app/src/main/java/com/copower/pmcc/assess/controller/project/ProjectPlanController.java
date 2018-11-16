package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.ProjectPlanSetEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:37
 */
@Controller
@RequestMapping(value = "/ProjectPlan", name = "项目计划内容")
public class ProjectPlanController {
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;

    @RequestMapping(value = "/planIndex", name = "项目计划进入页")
    public ModelAndView planIndex(Integer planId) {
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());


        String viewUrl = "projectPlanAssist";
        if (StringUtils.isNotBlank(projectWorkStage.getStageForm())) {
            //如果不为空，则进入相应的计划页面
            viewUrl = projectWorkStage.getStageForm();
        }
        ProjectPlanInterface bean = (ProjectPlanInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.applyView(projectPlan);
        modelAndView.addObject("panelTitle", projectWorkStage.getWorkStageName());
        modelAndView.addObject("projectPlan", projectPlan);
        modelAndView.addObject("planId", planId);
        modelAndView.addObject("detailsPlan", projectWorkStage.getAllowIssued() ? "1" : "0");//是否下级可以调整计划

        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProjectId(projectPlan.getProjectId());
        projectResponsibilityDto.setPlanId(projectPlan.getId());
        projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
        projectResponsibilityDto.setModel(1);
        List<ProjectResponsibilityDto> planResponsibilities = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
        modelAndView.addObject("bisChildren", "0");
        String planDetailsIds = "";
        if (CollectionUtils.isNotEmpty(planResponsibilities)) {
            for (ProjectResponsibilityDto item : planResponsibilities) {
                if (item.getPlanDetailsId() > 0) {
                    planDetailsIds += item.getPlanDetailsId() + ",";
                }
            }
            if (StringUtils.isNotBlank(planDetailsIds)) {
                planDetailsIds = planDetailsIds.substring(0, planDetailsIds.length() - 1);
                modelAndView.addObject("bisChildren", "1");
            }

            modelAndView.addObject("detailsPlan", "0");
        }
        modelAndView.addObject("planDetailsIds", planDetailsIds);
        //显示数据
        modelAndView.addObject("boxCnName", projectInfo.getProjectName() + "-" + projectPlan.getPlanName());
        modelAndView.addObject("boxprocessIcon", "fa-list-alt");
        modelAndView.addObject("currentStepName", "计划编制");
        modelAndView.addObject("currUserName", processControllerComponent.getThisUserInfo().getUserName());

        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");


        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfo);
        List<ProjectPhase> projectPhases = getProjectPhases(projectPlan, projectInfoVo);
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("projectPhases", projectPhases);
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (ProjectPlanSetEnum item : ProjectPlanSetEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(item.getValue().toString());
            keyValueDto.setValue(item.getDescribe());
            keyValueDtos.add(keyValueDto);
        }
        modelAndView.addObject("fastSet", keyValueDtos);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectPlanDetailsByPlanApply", name = "取得项目详情计划", method = RequestMethod.GET)
    public BootstrapTableVo getProjectPlanDetailsByPlanApply(Integer planId) {

        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
        if (CollectionUtils.isEmpty(projectPlanDetailsVos)) {
            try {
                Boolean aBoolean = projectPlanService.InitProjectPlanDetails(planId);
                if (aBoolean)//如果加载默认成功，则再添加详情
                {
                    projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
                } else {
                    projectPlanDetailsVos = new ArrayList<>();
                }
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
        bootstrapTableVo.setRows(projectPlanDetailsVos);
        return bootstrapTableVo;

    }

    @ResponseBody
    @RequestMapping(value = "/getProjectPlan", name = "取得项目详情计划", method = RequestMethod.GET)
    public BootstrapTableVo getProjectPlan(Integer planId) {

        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanId(planId);
        if (CollectionUtils.isEmpty(projectPlanDetailsVos)) {
            try {
                Boolean aBoolean = projectPlanService.InitProjectPlanDetails(planId);
                if (aBoolean)//如果加载默认成功，则再添加详情
                {
                    projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanId(planId);
                } else {
                    projectPlanDetailsVos = new ArrayList<>();
                }
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
        bootstrapTableVo.setRows(projectPlanDetailsVos);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveProjectPlanDetails", name = "保存项目详情计划", method = RequestMethod.POST)
    public HttpResult saveProjectPlanDetails(String ds, Integer planId) {
        try {
            projectPlanService.saveProjectPlanDetails(ds);
            List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
            BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
            bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
            bootstrapTableVo.setRows(projectPlanDetailsVos);
            return HttpResult.newCorrectResult(bootstrapTableVo);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/fastSetPlan", name = "取得项目详情计划", method = RequestMethod.POST)
    public HttpResult fastSetPlan(String fields, Integer planId, String detailsSoring) {
        try {
            projectPlanService.fastSetPlan(fields, planId, detailsSoring);
            List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
            BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
            bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
            bootstrapTableVo.setRows(projectPlanDetailsVos);
            return HttpResult.newCorrectResult(bootstrapTableVo);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deletePlan", name = "删除项", method = RequestMethod.POST)
    public HttpResult deletePlan(Integer planDetailsId, Integer planId) {
        try {
            projectPlanService.deletePlan(planDetailsId);
            List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
            BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
            bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
            bootstrapTableVo.setRows(projectPlanDetailsVos);
            return HttpResult.newCorrectResult(bootstrapTableVo);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveProjectPlan", name = "保存项目计划", method = RequestMethod.POST)
    public HttpResult saveProjectPlan(String formData, String appointUserAccount) {
        try {
            projectPlanService.saveProjectPlan(formData, appointUserAccount);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/planApproval", name = "项目计划审批页")
    public ModelAndView planApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {

        ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processInsId);

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        String viewUrl = "projectPlanAssist";
        if (StringUtils.isNotBlank(projectWorkStage.getStageForm())) {
            //如果不为空，则进入相应的计划页面
            viewUrl = projectWorkStage.getStageForm();
        }
        ProjectPlanInterface bean = (ProjectPlanInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.approvalView(projectPlan, taskId, boxId, agentUserAccount);

        modelAndView.addObject("projectPlan", projectPlan);
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("reviewMark", viewUrl);
        //显示数据
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-" + "计划审批");
        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoById(projectPlan.getProjectId()));
        return modelAndView;
    }

    @RequestMapping(value = "/planEdit", name = "项目计划审批页")
    public ModelAndView planEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {

        ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processInsId);

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        String viewUrl = "projectPlanAssist";
        if (StringUtils.isNotBlank(projectWorkStage.getStageForm())) {
            //如果不为空，则进入相应的计划页面
            viewUrl = projectWorkStage.getStageForm();
        }
        ProjectPlanInterface bean = (ProjectPlanInterface) SpringContextUtils.getBean(viewUrl);

        ModelAndView modelAndView = bean.approvalEdit(projectPlan, taskId, boxId, agentUserAccount);
        modelAndView.addObject("detailsPlan", "0");//是否下级可以调整计划
        modelAndView.addObject("projectPlan", projectPlan);
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("reviewMark", viewUrl);
        modelAndView.addObject("planId", projectPlan.getId());
        //显示数据
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-" + "返回修改");
        ProjectInfoVo projectInfo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlan.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfo);

        List<ProjectPhase> projectPhases = getProjectPhases(projectPlan, projectInfo);
        modelAndView.addObject("projectPhases", projectPhases);
        return modelAndView;
    }

    private List<ProjectPhase> getProjectPhases(ProjectPlan projectPlan, ProjectInfoVo projectInfo) {
        List<ProjectPhase> projectPhases = projectPhaseService.getCacheProjectPhaseByCategoryId(projectPlan.getCategoryId());
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            projectPhases = LangUtils.filter(projectPhases, o -> {
                return o.getWorkStageId() == projectPlan.getWorkStageId();
            });
        } else {
            //获取该类别的默认事项
            projectPhases = projectPhaseService.getDefaultProjectPhaseByTypeId(projectInfo.getProjectTypeId());
            projectPhases = CollectionUtils.isEmpty(projectPhases) ? new ArrayList<>() : projectPhases;
        }
        return projectPhases;
    }

    @RequestMapping(value = "/planDetails", name = "项目计划详情")
    public ModelAndView planIndex(String processInsId, Integer boxId) {

        ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processInsId);

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        String viewUrl = "projectPlanAssist";
        if (StringUtils.isNotBlank(projectWorkStage.getStageForm())) {
            //如果不为空，则进入相应的计划页面
            viewUrl = projectWorkStage.getStageForm();
        }
        ProjectPlanInterface bean = (ProjectPlanInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.detailsView(projectPlan, boxId);
        modelAndView.addObject("projectPlan", projectPlan);
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("reviewMark", viewUrl);
        //显示数据
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-" + "计划详情");

        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoById(projectPlan.getProjectId()));
        return modelAndView;
    }

    @RequestMapping(value = "/planDetailsById", name = "项目计划详情")
    public ModelAndView planDetailsById(Integer planId) {

        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        String viewUrl = "projectPlanAssist";
        if (StringUtils.isNotBlank(projectWorkStage.getStageForm())) {
            //如果不为空，则进入相应的计划页面
            viewUrl = projectWorkStage.getStageForm();
        }
        ProjectPlanInterface bean = (ProjectPlanInterface) SpringContextUtils.getBean(viewUrl);
        ModelAndView modelAndView = bean.detailsView(projectPlan, 0);
        modelAndView.addObject("projectPlan", projectPlan);
        modelAndView.addObject("projectId", projectPlan.getProjectId());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("reviewMark", viewUrl);
        //显示数据
        modelAndView.addObject("boxCnName", projectPlan.getPlanName() + "-" + "计划详情");
        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlan.getProjectId())));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitPlanApproval", name = "提交计划审批", method = RequestMethod.POST)
    public HttpResult submitPlanApproval(ApprovalModelDto approvalModelDto) {
        try {
            ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(approvalModelDto.getProcessInsId());
            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
            approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
            approvalModelDto.setWorkStageId(projectPlan.getWorkStageId());
            approvalModelDto.setWorkPhaseId(0);
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);

        } catch (BpmException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/submitPlanEdit", name = "提交返回修改", method = RequestMethod.POST)
    public HttpResult submitPlanEdit(String formData) {
        try {
            projectPlanService.submitPlanEdit(formData);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/copyPlanDetails", name = "拷贝计划详细内容", method = RequestMethod.POST)
    public HttpResult copyPlanDetails(Integer planDetailsId) {
        try {
            projectPlanService.copyPlanDetails(planDetailsId, true);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
