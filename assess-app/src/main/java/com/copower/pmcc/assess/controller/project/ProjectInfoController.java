package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.ProjectFollow;
import com.copower.pmcc.assess.dal.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.input.project.ProjectInfoDto;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.base.BaseParameterServcie;
import com.copower.pmcc.assess.service.project.ProjectFollowService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
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
 * @time: 14:12
 */
@Controller
@RequestMapping(value = "/projectInfo", name = "项目控制器")
public class ProjectInfoController {
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ProjectFollowService projectFollowService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;


    @RequestMapping(value = "/projectIndex", name = "项目立项", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = controllerComponent.baseFormModelAndView("/project/init/projectIndex", "0", 0, "0", "");
        modelAndView.addObject("boxCnName", "项目立项223366");
        modelAndView.addObject("thisTitle", "项目立项223366");
        modelAndView.addObject("boxprocessIcon", "fa-bookmark-o");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectApplySubmit", name = "保存项目", method = RequestMethod.POST)
    public HttpResult projectApplySubmit(ProjectInfoDto projectInfoDto) {
        try {
            projectInfoService.projectApply(projectInfoDto);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectInfoEdit", name = "项目返回修改")
    public ModelAndView projectInfoEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = controllerComponent.baseFormModelAndView("/project/init/projectIndex", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        //modelAndView.addObject("projectId", projectInfo.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/projectApproval", name = "项目审批页面")
    public ModelAndView projectApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = controllerComponent.baseFormModelAndView("/project/init/projectApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoByProcessInsId(processInsId);
        //modelAndView.addObject("projectId", projectInfo.getId());
        return modelAndView;
    }


    /**
     * 审批提交
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/projectApprovalSubmit", method = RequestMethod.POST)
    public HttpResult projectApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            projectInfoService.projectApproval(approvalModelDto);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 返回修改
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/projectEditSubmit", method = RequestMethod.POST)
    public HttpResult projectEditSubmit(ApprovalModelDto approvalModelDto, ProjectInfoDto projectInfoDto) {
        try {
            projectInfoService.projectEditApproval(approvalModelDto, projectInfoDto);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectDetails", name = "项目详情")
    public ModelAndView projectDetails(Integer projectId) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("/project/projectDetails");

        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);

        ProjectStatusEnum enumByName = ProjectStatusEnum.getEnumByName(projectInfo.getProjectStatus());
        modelAndView.addObject("projectStatusEnum", enumByName.getKey());
        modelAndView.addObject("projectInfo", projectInfo);

        modelAndView.addObject("thisTitle", projectInfo.getProjectName());
        //项目当前责任人信息
        List<KeyValueDto> keyValueDtos = getKeyValueDtos(projectId);

        modelAndView.addObject("keyValueDtos", keyValueDtos);
        modelAndView.addObject("projectFlog", "1");
        modelAndView.addObject("projectId", projectInfo.getId());
        //取项目成员
        ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfo.getId());
        modelAndView.addObject("projectMemberVo", projectMemberVo);

        //变更项目成员的取值
        if (projectInfo.getDepartmentId() != null) {
            List<String> managerUserAccounts = bpmRpcBoxRoleUserService.getDepartmentPM(projectInfo.getDepartmentId());
            modelAndView.addObject("managerUserAccounts", FormatUtils.transformListString(managerUserAccounts));
            List<String> memberUserAccounts = bpmRpcBoxRoleUserService.getDepartmentPA(projectInfo.getDepartmentId());
            modelAndView.addObject("memberUserAccounts", FormatUtils.transformListString(memberUserAccounts));
        }
        //判断当前人员是否关注项目
        ProjectFollow projectFollow = projectFollowService.getProjectFollowByUser(projectInfo.getId());
        modelAndView.addObject("projectFollowFlog", projectFollow == null ? 0 : 1);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectTaskByProjectId", name = "取得项目工作成果", method = RequestMethod.GET)
    public BootstrapTableVo getProjectPlanByProjectId(Integer projecId) {

        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByProjectid(projecId);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
        bootstrapTableVo.setRows(projectPlanDetailsVos);
        return bootstrapTableVo;

    }

    private List<KeyValueDto> getKeyValueDtos(Integer projectId) {
        List<ProjectResponsibilityDto> projectPlanResponsibilityList = bpmRpcProjectTaskService.getProjectTaskList(Lists.newArrayList(projectId));
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(projectPlanResponsibilityList)) {
            keyValueDtos = LangUtils.transform(projectPlanResponsibilityList, o -> {
                KeyValueDto keyValueDto = new KeyValueDto();
                //TASK(0, "责任人提交工作成果"), PLAN(1, "部门负责人细分计划"), ALLTASK(2, "整体复核工作成果"), NEWPLAN(3, "细分下级计划责任人");
                String modelName = "";
                switch (o.getModel()) {
                    case 0: {
                        keyValueDto.setExplain("label-warning");
                        break;
                    }
                    case 1: {
                        keyValueDto.setExplain("label-info");
                        break;
                    }
                    case 2: {
                        keyValueDto.setExplain("label-success");
                        break;
                    }
                    case 3: {
                        keyValueDto.setExplain("label-info");
                        break;
                    }
                }
                keyValueDto.setKey(o.getPlanDetailsName());
                SysUserDto sysUser = erpRpcUserService.getSysUser(o.getUserAccount());
                if (sysUser != null) {
                    keyValueDto.setValue(sysUser.getUserName());
                }
                return keyValueDto;
            });
        }

        return keyValueDtos;
    }

}
