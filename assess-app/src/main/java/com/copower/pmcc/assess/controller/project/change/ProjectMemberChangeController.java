package com.copower.pmcc.assess.controller.project.change;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.input.project.MemberChangeDto;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.change.ProjectMemberChangeService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/06 09:34
 */

@Controller
@RequestMapping("/member.change")
public class ProjectMemberChangeController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectMemberChangeService projectMemberChangeService;

    private void pageModel(ModelAndView modelAndView, Integer projectId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectId);
    }

    @GetMapping(value = "/applyView", name = "项目成员变更申请页面")
    public ModelAndView applyView(Integer projectId) throws BusinessException {
        //获取参数配置的成员变更模型
        String boxKey = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_MEMBER_CHANGE_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxKey);

        if (boxReDto == null || boxReDto.getId() == null) {
            throw new BusinessException("项目成员变更流程模型参数未配置!");
        }

        Integer boxId = boxReDto.getId();

        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/change/member_change/apply", boxId);

        pageModel(modelAndView, projectId);

        return modelAndView;
    }

    @GetMapping(value = "/approvalView", name = "审批视图详情")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/change/member_change/approval", processInsId, boxId, taskId, agentUserAccount);


        ProjectChangeLog costsProjectChangeLog = projectMemberChangeService.getProjectChangeLog(processInsId);
        pageModel(modelAndView, costsProjectChangeLog.getProjectId());

        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);

        return modelAndView;
    }

    @GetMapping(value = "/editView", name = "返回修改视图")
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/change/member_change/apply", processInsId, boxId, taskId, agentUserAccount);

        ProjectChangeLog costsProjectChangeLog = projectMemberChangeService.getProjectChangeLog(processInsId);
        pageModel(modelAndView, costsProjectChangeLog.getProjectId());

        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);

        return modelAndView;

    }

    @GetMapping(value = "/detailView", name = "详情页")
    public ModelAndView detailView(Integer boxId, String processInsId) {
        return approvalView(boxId, processInsId, "-1", "");
    }

    @PostMapping(value = "/memberList", name = "成员列表")
    @ResponseBody
    public HttpResult memberList(Integer projectId, String processInsId) {
        try {
            List<MemberChangeDto> memberList = projectMemberChangeService.buildMemberList(projectId, processInsId);
            return HttpResult.newCorrectResult(memberList);
        } catch (Exception e) {
            log.error("获取成员列表异常", e);
            return HttpResult.newErrorResult(e);
        }
    }


    @PostMapping(value = "/addMember", name = "添加项目成员")
    @ResponseBody
    public HttpResult addMember(Integer projectId, String processInsId, String members) {
        try {
            if (StringUtils.isBlank(processInsId)) {
                processInsId = "0";
            }

            projectMemberChangeService.addMember(projectId, processInsId, members);

            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("添加项目成员异常", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/removeMember", name = "移除项目成员")
    @ResponseBody
    public HttpResult removeMember(Integer projectId, String processInsId, String member) {
        try {
            projectMemberChangeService.checkUserTask();

            if (StringUtils.isBlank(processInsId)) {
                processInsId = "0";
            }
            projectMemberChangeService.removeMember(projectId, processInsId, member);

            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("移除项目成员异常", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/replaceMember", name = "替换项目成员")
    @ResponseBody
    public HttpResult replaceMember(Integer projectId, String processInsId, String oldMember, String newMember) {
        try {
            projectMemberChangeService.checkUserTask();

            if (StringUtils.isBlank(processInsId)) {
                processInsId = "0";
            }
            projectMemberChangeService.replaceMember(projectId, processInsId, oldMember, newMember);

            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("替换项目成员异常", e);
            return HttpResult.newErrorResult(e);
        }
    }



    @PostMapping(value = "/replaceManage", name = "替换项目经理")
    @ResponseBody
    public HttpResult replaceManage(Integer projectId, String processInsId, String newManage) {
        try {
            projectMemberChangeService.checkUserTask();

            if (StringUtils.isBlank(processInsId)) {
                processInsId = "0";
            }
            projectMemberChangeService.replaceManage(projectId, processInsId, newManage);

            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("替换项目经理异常", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/applyCommit", name = "申请提交")
    @ResponseBody
    public HttpResult applyCommit(Integer projectId, String processInsId, String changeReason) {
        try {
            projectMemberChangeService.apply(projectId, processInsId, changeReason);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("成员变更申请提交异常", e);
            return HttpResult.newErrorResult(e);
        }
    }


    @PostMapping(value = "/approvalCommit", name = "审批提交")
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {

        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            log.error("提交审批失败", e);
            return HttpResult.newErrorResult(e);
        }
    }


    @PostMapping(value = "/editCommit", name = "修改提交")
    @ResponseBody
    public HttpResult editCommit(Integer projectId, String changeReason, String approvalModelDto) {
        try {
            ApprovalModelDto approval = JSON.parseObject(approvalModelDto, ApprovalModelDto.class);
            //提交数据
            projectMemberChangeService.editCommit(projectId, changeReason, approval);

            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("修改失败", e);
            return HttpResult.newErrorResult(e);
        }
    }
}
