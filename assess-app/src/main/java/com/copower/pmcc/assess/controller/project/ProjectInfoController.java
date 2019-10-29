package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.data.DataValueDefinitionService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.change.ProjectFollowService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/25
 * @time: 14:12
 */
@Controller
@RequestMapping(value = "/projectInfo", name = "项目控制器")
public class ProjectInfoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ProjectFollowService projectFollowService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private DataValueDefinitionService dataValueDefinitionService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @RequestMapping(value = "/projectIndex", name = "项目立项", method = RequestMethod.GET)
    public ModelAndView view(Integer projectClassId, Integer projectTypeId, Integer projectCategoryId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectIndex", "0", 0, "0", "");
        modelAndView.addObject("boxCnName", "项目立项");
        modelAndView.addObject("thisTitle", "项目立项");
        modelAndView.addObject("boxprocessIcon", "fa-bookmark-o");
        //单位性质 crm中获取
        modelAndView.addObject("ProjectAFFILIATED", projectInfoService.getUnitPropertiesList());
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setId(0);
        projectInfo.setProjectClassId(projectClassId);
        projectInfo.setProjectTypeId(projectTypeId);
        projectInfo.setProjectCategoryId(projectCategoryId);
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        InitiateUnitInformationVo unitInformationVo = new InitiateUnitInformationVo();
        unitInformationVo.setInfoWrite(true);
        projectInfoVo.setUnitInformationVo(unitInformationVo);
        projectInfoVo.setConsignorVo(new InitiateConsignorVo());
        projectInfoVo.setPossessorVo(new InitiatePossessorVo());
        projectInfoVo.setProjectMemberVo(new ProjectMemberVo());
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("projectInfoVoJson", JSONObject.toJSONString(projectInfoVo));
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        projectInfoService.clear();
        //获取立项事项
        ProjectWorkStage workStage = projectWorkStageService.getProjectWorkStageFirst(projectClassId, projectTypeId);
        if (workStage != null) {
            List<ProjectPhase> projectPhases = projectPhaseService.getCacheProjectPhaseByCategoryId(projectCategoryId, workStage.getId());
            if (CollectionUtils.isNotEmpty(projectPhases))
                modelAndView.addObject("projectPhaseId", projectPhases.get(0).getId());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/projectInfoEdit", name = "项目返回修改 页面")
    public ModelAndView projectInfoEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount, Integer projectId) {
        ModelAndView modelAndView = null;
        ProjectInfo projectInfo = null;
        if (StringUtils.isNotEmpty(processInsId)) {
            projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
            modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectIndex", processInsId, boxId, taskId, agentUserAccount);
        }
        if (projectId != null) {
            projectInfo = projectInfoService.getProjectInfoById(projectId);
            modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectIndex", "0", 0, "0", "");
        }
        if (projectInfo != null) {
            if (modelAndView != null) {
                ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
                modelAndView.addObject("projectInfo", projectInfoVo);
                modelAndView.addObject("projectInfoVoJson", JSONObject.toJSONString(projectInfoVo));
                modelAndView.addObject("projectId", projectInfo.getId());
            }
        }
        if (modelAndView != null) {
            modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
            //单位性质 crm中获取
            modelAndView.addObject("ProjectAFFILIATED", projectInfoService.getUnitPropertiesList());
        }
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        return modelAndView != null ? modelAndView : new ModelAndView();
    }

    @RequestMapping(value = "/projectApproval", name = "项目审批页面")
    public ModelAndView projectApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        ProjectInfoVo vo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        modelAndView.addObject("projectInfo", vo);
        modelAndView.addObject("projectId", vo.getId());
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        return modelAndView;
    }

    @RequestMapping(value = "/projectAssignApproval", name = "任务分派审批页面")
    public ModelAndView projectAssignApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectAssignApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setAssignProcessInsId(processInsId);
        List<ProjectInfo> projectInfoList = projectInfoService.getProjectInfoList(projectInfo);
        ProjectInfoVo vo = projectInfoService.getSimpleProjectInfoVo(projectInfoList.get(0));
        modelAndView.addObject("projectInfo", vo);
        modelAndView.addObject("projectId", vo.getId());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectApplySubmit", name = "项目立项", method = RequestMethod.POST)
    public HttpResult projectApplySubmit(String formData, Boolean bisNextUser) {
        try {
            projectInfoService.projectApply(projectInfoService.format(formData), true);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/projectApplyDraft", name = "项目草稿", method = RequestMethod.POST)
    public HttpResult projectApplyDraft(String formData, Boolean bisNextUser) {
        try {
            projectInfoService.projectApply(projectInfoService.format(formData), false);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/projectDataUpdate", name = "项目更新  注意这个方法不是和任何流程相关的方法", method = RequestMethod.POST)
    public HttpResult projectDataUpdate(ProjectInfo projectInfo) {
        try {
            projectInfoService.saveProjectInfo(projectInfo);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/projectClearData", name = "项目清除所属项目的数据  仅仅限于草稿这样做", method = RequestMethod.POST)
    public HttpResult projectClearData(Integer id) {
        try {
            projectInfoService.projectClearData(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/projectEditSubmit", name = "项目立项返回修改", method = RequestMethod.POST)
    public HttpResult projectEditSubmit(ApprovalModelDto approvalModelDto, String formData, Integer projectInfoId) {
        try {
            projectInfoService.projectEditApproval(approvalModelDto, formData, projectInfoId);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/projectApprovalSubmit", name = "项目立项审批", method = RequestMethod.POST)
    public HttpResult projectApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            projectInfoService.projectApproval(approvalModelDto);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/projectApprovalAssignSubmit", name = "分派项目经理审批", method = RequestMethod.POST)
    public HttpResult projectApprovalAssignSubmit(ApprovalModelDto approvalModelDto) {
        try {
            projectInfoService.projectAssignApproval(approvalModelDto);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectAssignDetails", name = "分派项目经理详情")
    public Object projectAssignDetails(String processInsId) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setAssignProcessInsId(processInsId);
        List<ProjectInfo> projectInfoList = projectInfoService.getProjectInfoList(projectInfo);
        return projectDetails(projectInfoList.get(0).getId());
    }

    @RequestMapping(value = "/projectApprovalDetails", name = "项目审批详情")
    public Object projectApprovalDetails(String processInsId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        return projectDetails(projectInfo.getId());
    }

    @RequestMapping(value = "/projectInfoDetails", name = "项目详细信息")
    public ModelAndView projectInfoDetails(Integer projectId) {
        ModelAndView modelAndView = new ModelAndView("/project/projectInfoDetails");
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        return modelAndView;
    }

    /**
     * 查看项目详情的地址入口统一，但根据不同的项目类别请求不同的地址
     *
     * @param projectId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/projectDetails", name = "项目详情")
    public Object projectDetails(Integer projectId) {
        ModelAndView modelAndView = new ModelAndView("/project/projectDetails");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        //获取请求地址，并确定最终请求的路径
        BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectTypeId());
        String uri = request.getRequestURI().replace(request.getContextPath(), "").replaceAll("^/", "");
        String detailUri = baseProjectClassify.getDetailUrl().replaceAll("^/", "");
        if (!StringUtils.equals(detailUri, uri)) {
            String forwardUrl = String.format("/%s?projectId=%s", baseProjectClassify.getDetailUrl(), projectId);
            return "forward:" + forwardUrl;//跳转到其它请求地址
        }
        ProjectStatusEnum enumByName = ProjectStatusEnum.getEnumByName(projectInfo.getProjectStatus());
        if (enumByName != null) {
            modelAndView.addObject("projectStatusEnum", enumByName.getKey());
        }
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        modelAndView.addObject("projectInfo", projectInfoVo);
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        modelAndView.addObject("thisTitle", projectInfo.getProjectName());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("projectId", projectInfo.getId());
        //取项目成员
        ProjectMemberVo projectMemberVo = projectMemberService.getProjectMember(projectInfo.getId());
        modelAndView.addObject("projectMemberVo", projectMemberVo);

        //判断当前人是否为项目经理
        modelAndView.addObject("isPM", StringUtils.equals(projectMemberVo.getUserAccountManager(), processControllerComponent.getThisUser()));
        //区域
        modelAndView.addObject("areaGroupList", schemeAreaGroupService.getAreaGroupList(projectId));
        //项目发文件
        List<DocumentTemplate> documentTemplateList = documentTemplateService.getDocumentTemplateList("", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_DISPATCH).getId());
        modelAndView.addObject("documentTemplateList", documentTemplateList);
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    @RequestMapping(value = "/csrProjectDetails", name = "不良债权项目详情")
    public ModelAndView csrProjectDetails(Integer projectId) {
        ModelAndView modelAndView = new ModelAndView("/project/projectCsrDetails");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectStatusEnum enumByName = ProjectStatusEnum.getEnumByName(projectInfo.getProjectStatus());
        if (enumByName != null) {
            modelAndView.addObject("projectStatusEnum", enumByName.getKey());
        }
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("thisTitle", projectInfo.getProjectName());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("projectId", projectInfo.getId());
        //取项目成员
        ProjectMemberVo projectMemberVo = projectMemberService.getProjectMember(projectInfo.getId());
        modelAndView.addObject("projectMemberVo", projectMemberVo);
        //判断当前人员是否关注项目
        ProjectFollow projectFollow = projectFollowService.getProjectFollowByUser(projectInfo.getId());
        modelAndView.addObject("projectFollowFlog", projectFollow == null ? 0 : 1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getPlanDetailListByPlanId", name = "取得阶段工作成果")
    public BootstrapTableVo getPlanDetailListByPlanId(String formData) {
        ProjectPlanDetails projectPlanDetails = JSON.parseObject(formData, ProjectPlanDetails.class);
        Integer projectId = projectPlanDetails.getProjectId();
        Integer planId = projectPlanDetails.getPlanId();
        String userAccount = projectPlanDetails.getExecuteUserAccount();
        String phaseName = projectPlanDetails.getProjectPhaseName();
        String stringStatus = projectPlanDetails.getStatus();
        Date startDate = projectPlanDetails.getPlanStartDate();
        Date endDate = projectPlanDetails.getPlanEndDate();
        return projectPlanDetailsService.getPlanDetailListByPlanId(projectId, planId, userAccount, phaseName, startDate, endDate, stringStatus);
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectPlanItem", name = "取得计划编制信息", method = RequestMethod.POST)
    public HttpResult getProjectPlanItem(Integer planId) {
        try {
            ProjectPlanVo projectPlanItem = projectInfoService.getProjectPlanItem(planId);
            return HttpResult.newCorrectResult(projectPlanItem);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("取得计划编制信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getValueDefinition", name = "取得价值定义信息")
    public HttpResult getValueDefinition(String entrustPurpose, String valueType) {
        try {
            DataValueDefinition valueDefinition = dataValueDefinitionService.getValueDefinition(entrustPurpose, valueType);
            return HttpResult.newCorrectResult(valueDefinition);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("取得价值定义信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getRemarkEntrustPurpose", name = "取得委托目的描述", method = RequestMethod.POST)
    public HttpResult getValueDefinition(Integer entrustAimType) {
        try {
            BaseDataDic dataDicById = baseDataDicService.getDataDicById(entrustAimType);
            return HttpResult.newCorrectResult(dataDicById);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("取得价值定义信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/finishProject", name = "完成项目", method = RequestMethod.POST)
    public HttpResult finishProject(Integer projectId) {
        try {
            projectInfoService.finishProject(projectId);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("完成项目异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getTotalPlanDetails", name = "计划总数", method = RequestMethod.POST)
    public HttpResult getTotalPlanDetails(Integer planId) {
        try {
            Integer total = projectPlanDetailsService.getTotalPlanDetails(planId);
            return HttpResult.newCorrectResult(total);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("取得计划总数异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectById", name = "根据id获取项目信息", method = RequestMethod.GET)
    public HttpResult getProjectById(Integer id) {
        try {
            return HttpResult.newCorrectResult(projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(id)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取项目信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/enterNextStage", name = "进入项目下个阶段", method = RequestMethod.POST)
    public HttpResult enterNextStage(Integer projectId) {
        try {
            projectInfoService.enterNextStage(projectId);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("进入项目下个阶段");
        }
    }
}
