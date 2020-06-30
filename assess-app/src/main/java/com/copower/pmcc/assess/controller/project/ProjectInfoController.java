package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.QueryProjectInfo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.chks.AssessmentCommonService;
import com.copower.pmcc.assess.service.data.DataValueDefinitionService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectCenterService projectCenterService;
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
    @Autowired
    private AssessmentCommonService assessmentCommonService;

    @RequestMapping(value = "/projectIndex", name = "项目立项", method = RequestMethod.GET)
    public ModelAndView view(Integer projectClassId, Integer projectTypeId, Integer projectCategoryId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(projectInfoService.getProjectInfoView(), "0", 0, "0", "");
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
            modelAndView = processControllerComponent.baseFormModelAndView(projectInfoService.getProjectInfoView(), processInsId, boxId, taskId, agentUserAccount);
        }
        if (projectId != null) {
            projectInfo = projectInfoService.getProjectInfoById(projectId);
            modelAndView = processControllerComponent.baseFormModelAndView(projectInfoService.getProjectInfoView(), "0", 0, "0", "");
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
    public ModelAndView projectApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) throws BpmException {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        ProjectInfoVo vo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        modelAndView.addObject("projectInfo", vo);
        modelAndView.addObject("projectId", vo.getId());
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        assessmentCommonService.generateAssessmentTask(processInsId, boxId, taskId, projectInfo, null);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectApplySubmit", name = "项目立项提交", method = RequestMethod.POST)
    public HttpResult projectApplySubmit(String formData, Boolean bisNextUser, @RequestParam(defaultValue = "false") boolean mustUseBox, Boolean bisAssign) {
        try {
            projectInfoService.projectApply(projectInfoService.format(formData), true, mustUseBox, bisAssign);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "项目立项");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/projectApplyDraft", name = "项目草稿", method = RequestMethod.POST)
    public HttpResult projectApplyDraft(String formData, Boolean bisNextUser, @RequestParam(defaultValue = "false") boolean mustUseBox) {
        try {
            projectInfoService.projectApply(projectInfoService.format(formData), false, mustUseBox, false);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "项目草稿");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/projectDataUpdate", name = "项目更新  注意这个方法不是和任何流程相关的方法", method = RequestMethod.POST)
    public HttpResult projectDataUpdate(ProjectInfo projectInfo) {
        try {
            projectInfoService.saveProjectInfo(projectInfo);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "项目更新  注意这个方法不是和任何流程相关的方法");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/projectClearData", name = "项目清除所属项目的数据  仅仅限于草稿这样做", method = RequestMethod.POST)
    public HttpResult projectClearData(Integer id) {
        try {
            projectInfoService.projectClearData(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "项目清除所属项目的数据  仅仅限于草稿这样做");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/projectEditSubmit", name = "项目立项返回修改提交", method = RequestMethod.POST)
    public HttpResult projectEditSubmit(ApprovalModelDto approvalModelDto, String formData, Integer projectInfoId) {
        try {
            projectInfoService.projectEditApproval(approvalModelDto, formData, projectInfoId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "项目立项返回修改");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/projectApprovalSubmit", name = "项目立项审批提交", method = RequestMethod.POST)
    public HttpResult projectApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            projectInfoService.projectApproval(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "项目立项审批");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/projectApprovalDetails", name = "项目审批详情")
    public ModelAndView projectApprovalDetails(Integer boxId, String processInsId, String taskId) throws BpmException {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectApproval", processInsId, boxId, "-1", "");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        ProjectInfoVo vo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        modelAndView.addObject("projectInfo", vo);
        modelAndView.addObject("projectId", vo.getId());
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        return modelAndView;
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
        ModelAndView modelAndView = new ModelAndView("/project/stageInit/projectApproval");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
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
        modelAndView.addObject("areaGroupList", schemeAreaGroupService.getSchemeAreaGroupVos(projectId));
        //项目发文件
        List<DocumentTemplate> documentTemplateList = documentTemplateService.getDocumentTemplateList("", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_DISPATCH).getId());
        modelAndView.addObject("documentTemplateList", documentTemplateList);
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
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
            baseService.writeExceptionInfo(e, "取得计划编制信息异常");
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
            baseService.writeExceptionInfo(e, "取得价值定义信息异常");
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
            baseService.writeExceptionInfo(e, "取得委托目的描述");
            return HttpResult.newErrorResult("取得委托目的描述");
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
            baseService.writeExceptionInfo(e, "完成项目异常");
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
            baseService.writeExceptionInfo(e, "取得计划总数异常");
            return HttpResult.newErrorResult("取得计划总数异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectById", name = "根据id获取项目信息", method = RequestMethod.GET)
    public HttpResult getProjectById(Integer id) {
        try {
            return HttpResult.newCorrectResult(projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(id)));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "根据id获取项目信息");
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
            baseService.writeExceptionInfo(e, "进入项目下个阶段");
            return HttpResult.newErrorResult("进入项目下个阶段");
        }
    }

    @RequestMapping(value = "/assignTask", name = "项目分派页面")
    public ModelAndView assignTask(Integer projectId) throws BpmException {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/projectInfoDetails");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectInfoVo vo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        modelAndView.addObject("projectInfo", vo);
        modelAndView.addObject("sysUrl", baseParameterService.getParameterValues(BaseParameterEnum.SYS_URL_KEY.getParameterKey()));
        modelAndView.addObject("isProjectAssign",true);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/assignTaskSubmit", name = "项目立项提交", method = RequestMethod.POST)
    public HttpResult assignTaskSubmit(String formData, Boolean bisAssign) {
        try {
            projectInfoService.assignTaskSubmit(projectInfoService.format(formData), bisAssign);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "项目立项");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectNumber", name = "根据人员获取项目个数", method = RequestMethod.GET)
    public HttpResult getProjectNumber(String account) {
        try {
            if (StringUtils.isEmpty(account)) {
                return HttpResult.newCorrectResult(0);
            }
            RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
            Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
            QueryProjectInfo queryProjectInfo = new QueryProjectInfo();
            queryProjectInfo.setQueryManager(account);
            queryProjectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());
            projectInfoDao.getProjectListByUserAccount(queryProjectInfo);
            return HttpResult.newCorrectResult(page.getTotal());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "根据人员获取项目个数");
            return HttpResult.newErrorResult("获取项目个数异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectByAccount", name = "根据人员获取项目", method = RequestMethod.GET)
    public BootstrapTableVo getProjectByAccount(String account) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        if (StringUtils.isBlank(account)) return vo;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        QueryProjectInfo queryProjectInfo = new QueryProjectInfo();
        queryProjectInfo.setQueryManager(account);
        queryProjectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());
        List<ProjectInfo> list = projectInfoDao.getProjectListByUserAccount(queryProjectInfo);
        List<ProjectInfoVo> projectInfoVos = projectCenterService.getProjectInfoVos(list);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(projectInfoVos) ? projectInfoVos : new ArrayList<ProjectInfoVo>());
        return vo;
    }

}
