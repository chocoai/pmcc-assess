package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFollow;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.input.project.initiate.InitiateContactsDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateContactsVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.change.ProjectFollowService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    private ErpAreaService erpAreaService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private CrmCustomerService crmCustomerService;

    @RequestMapping(value = "/projectIndex", name = "项目立项", method = RequestMethod.GET)
    public ModelAndView view(Integer projectClassId, Integer projectTypeId, Integer projectCategoryId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectIndex", "0", 0, "0", "");
        modelAndView.addObject("boxCnName", "项目立项");
        modelAndView.addObject("thisTitle", "项目立项");
        modelAndView.addObject("boxprocessIcon", "fa-bookmark-o");

        modelAndView.addObject("ProjectAFFILIATED", projectInfoService.getUnitPropertiesList());//单位性质 crm中获取
        modelAndView.addObject("InitiateContactsMap", projectInfoService.getTypeInitiateContactsMap());//联系人类别
        modelAndView.addObject("list_entrustment_purpose", projectInfoService.list_entrustment_purpose());//委托目的
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject("project_initiate_urgency", projectInfoService.project_initiate_urgency());//紧急程度
        modelAndView.addObject("value_type", projectInfoService.value_type());//价值类型
        modelAndView.addObject("departmentAssess", erpRpcDepartmentService.getDepartmentAssess());//可选的执业部门

        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setId(0);
        projectInfo.setProjectClassId(projectClassId);
        projectInfo.setProjectTypeId(projectTypeId);
        projectInfo.setProjectCategoryId(projectCategoryId);
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfo);
        projectInfoService.init();
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @RequestMapping(value = "/projectInfoEdit", name = "项目返回修改 页面")
    public ModelAndView projectInfoEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectIndex", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfo);
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("projectId", projectInfoVo.getId());
        modelAndView.addObject("ProjectAFFILIATED", projectInfoService.getUnitPropertiesList());//单位性质 crm中获取
        modelAndView.addObject("InitiateContactsMap", projectInfoService.getTypeInitiateContactsMap());//联系人类别
        modelAndView.addObject("list_entrustment_purpose", projectInfoService.list_entrustment_purpose());//委托目的
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject("project_initiate_urgency", projectInfoService.project_initiate_urgency());//紧急程度
        modelAndView.addObject("value_type", projectInfoService.value_type());//价值类型

        return modelAndView;
    }

    @RequestMapping(value = "/projectApproval", name = "项目审批页面")
    public ModelAndView projectApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        ProjectInfoVo vo = projectInfoService.getProjectInfoVoView(projectInfo);
        modelAndView.addObject("projectInfo", vo);
        modelAndView.addObject("projectId", vo.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/projectAssignApproval", name = "任务分派审批页面")
    public ModelAndView projectAssignApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageInit/projectAssignApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setAssignProcessInsId(processInsId);
        List<ProjectInfo> projectInfoList = projectInfoService.getProjectInfoList(projectInfo);
        ProjectInfoVo vo = projectInfoService.getProjectInfoVoView(projectInfoList.get(0));
        modelAndView.addObject("projectInfo", vo);
        modelAndView.addObject("projectId", vo.getId());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectApplySubmit", name = "项目立项", method = RequestMethod.POST)
    public HttpResult projectApplySubmit(String formData, Boolean bisNextUser) {
        try {
            projectInfoService.projectApply(projectInfoService.format(formData), bisNextUser);
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
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
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
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfo);
        modelAndView.addObject("projectInfo", projectInfoVo);
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        modelAndView.addObject("projectPlanList", projectPlanList);
        modelAndView.addObject("thisTitle", projectInfo.getProjectName());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("projectId", projectInfo.getId());
        //取项目成员
        ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfo.getId());
        modelAndView.addObject("projectMemberVo", projectMemberVo);

        //判断当前人员是否关注项目
        ProjectFollow projectFollow = projectFollowService.getProjectFollowByUser(projectInfo.getId());
        modelAndView.addObject("projectFollowFlog", projectFollow == null ? 0 : 1);
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
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfo);
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("thisTitle", projectInfo.getProjectName());
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("projectId", projectInfo.getId());
        //取项目成员
        ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfo.getId());
        modelAndView.addObject("projectMemberVo", projectMemberVo);
        //判断当前人员是否关注项目
        ProjectFollow projectFollow = projectFollowService.getProjectFollowByUser(projectInfo.getId());
        modelAndView.addObject("projectFollowFlog", projectFollow == null ? 0 : 1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectTaskByProjectId", name = "取得项目工作成果", method = RequestMethod.GET)
    public BootstrapTableVo getProjectPlanByProjectId(Integer projecId) {
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByProjectId(projecId);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
        bootstrapTableVo.setRows(projectPlanDetailsVos);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/getPlanDetailListByPlanId", name = "取得阶段工作成果", method = RequestMethod.GET)
    public BootstrapTableVo getPlanDetailListByPlanId(Integer projecId, Integer planId) {
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getPlanDetailListByPlanId(projecId, planId);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
        bootstrapTableVo.setRows(projectPlanDetailsVos);
        return bootstrapTableVo;
    }


    @ResponseBody
    @RequestMapping(value = "/getProjectContactsVos", name = "取得联系人列表 crm中取得以及更改之后直接从数据库获取", method = {RequestMethod.GET})
    public BootstrapTableVo listContactsVo(Integer crmId, Integer type, Integer pid, String crmContacts, String searchCrm, Integer customerId) {
        BootstrapTableVo vo = null;
        //crm查询
        if (Objects.equal("crmContacts",crmContacts)) {
            vo = projectInfoService.crmContacts(customerId,searchCrm);
        }else {
            vo = projectInfoService.listContactsVo(crmId, type, pid);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/findCrmProjectContactsVos", method = {RequestMethod.POST, RequestMethod.GET}, name = "crm中获取客户的联系人")
    public HttpResult findContactsTotal( Integer customerId,String total){
        BootstrapTableVo vo = null;
        if (Objects.equal("total",total)){
            if (customerId != null){
                return HttpResult.newCorrectResult(crmCustomerService.getCustomerLinkmanList(customerId).size());
            }
        }
        return HttpResult.newCorrectResult(vo);
    }

    @ResponseBody
    @RequestMapping(value = "/Contacts/save", method = {RequestMethod.POST, RequestMethod.GET}, name = "联系人 增加与修改")
    public HttpResult addContacts(InitiateContactsDto dto, String formData) {
        List<InitiateContactsDto> dtos = null;
        try {
            if (dto.getId() != null && dto.getId().intValue() != 0) {//不再使用专门的 update controller
                projectInfoService.updateContacts(dto);
            } else {
                projectInfoService.addContacts(dto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        try {
            if (!org.springframework.util.StringUtils.isEmpty(formData)) {
                JSONObject jsonObject = JSON.parseObject(formData);
                String jsonContent = jsonObject.getString("contacts");
                dtos = JSONObject.parseArray(jsonContent, InitiateContactsDto.class);
                if (!ObjectUtils.isEmpty(dtos)) {
                    projectInfoService.addContacts(dtos);
                }
            }
        } catch (Exception e1) {
            logger.error(e1.getMessage());
            return HttpResult.newErrorResult(e1.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/Contacts/delete", name = "联系人 删除", method = RequestMethod.POST)
    public HttpResult deleteContacts(@RequestParam(value = "id") Integer id) {
        try {
            projectInfoService.removeContacts(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/Contacts/get", name = "联系人 获取", method = RequestMethod.GET)
    public HttpResult getContacts(@RequestParam(value = "id") Integer id) {
        try {
            InitiateContactsVo contactsVo = projectInfoService.getInitiateContacts(id);
            return HttpResult.newCorrectResult(contactsVo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCRMList", name = "CRM 获取", method = RequestMethod.POST)
    public Object getCRMList(Integer crmId) {
        if (crmId != null) {
            CrmCustomerDto crmCustomerDto = projectInfoService.getCRM(crmId);
            try {
                if (crmCustomerDto != null)
                    return crmCustomerDto;
            } catch (Exception e) {
                logger.error(e.getMessage());
                return HttpResult.newErrorResult(e.getMessage());
            }
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/UnitInformation/get", method = {RequestMethod.POST}, name = "报告使用单位 获取")
    public Object unitinformationGet(Integer id) {
        try {
            if (id != null) {
                return projectInfoService.getInitiateUnitInformation(id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/Possessor/get", method = {RequestMethod.POST}, name = "占有人 获取")
    public Object possessorGet(Integer id) {
        try {
            if (id != null) {
                return projectInfoService.getInitiatePossessor(id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/Consignor/get", method = {RequestMethod.POST}, name = "委托 获取")
    public Object consignorGet(Integer id) {
        try {
            if (id != null) {
                return projectInfoService.getInitiateConsignor(id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getUnitPropertiesList", method = {RequestMethod.POST}, name = "单位性质")
    public Object getUnitPropertiesList() {
        try {
            List<CrmBaseDataDicDto> crmBaseDataDicDtos = projectInfoService.getUnitPropertiesList();
            if (!ObjectUtils.isEmpty(crmBaseDataDicDtos)) {
                return crmBaseDataDicDtos;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
