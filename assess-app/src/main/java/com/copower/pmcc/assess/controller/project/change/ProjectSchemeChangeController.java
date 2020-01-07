package com.copower.pmcc.assess.controller.project.change;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectStateChangeService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/21 10:00
 * @Description:
 */
@RequestMapping(value = "/project.scheme.change")
@Controller
public class ProjectSchemeChangeController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectStateChangeService stateChangeService;

    @GetMapping(value = "/applyView", name = "项目信息变更申请")
    public ModelAndView apply(Integer projectId) throws BusinessException {
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_SCHEME_CHANGE_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/scheme_change/apply", boxReDto.getId());
        List<SchemeAreaGroupVo> areaGroups = schemeAreaGroupService.getSchemeAreaGroupVos(projectId);//获取分组信息
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("areaGroups", areaGroups);
        modelAndView.addObject("entrustmentPurposes", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE));//委托目的
        modelAndView.addObject("valueTypes", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE));//价值类型
        modelAndView.addObject("oldData", JSONObject.toJSONString(areaGroups));
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "方案信息变更申请")
    public HttpResult applyCommit(ProjectChangeLog costsProjectChangeLog) {
        try {
            stateChangeService.applyCommit(costsProjectChangeLog, BaseParameterEnum.PROJECT_SCHEME_CHANGE_PROCESS_KEY, ProjectChangeTypeEnum.SCHEME_CHANGE);
        } catch (Exception e) {
            logger.error("方案信息变更申请异常", e);
            e.printStackTrace();
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approvalView", name = "项目信息审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/scheme_change/approval", processInsId, boxId, taskId, agentUserAccount);
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(costsProjectChangeLog.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        List<SchemeAreaGroup> areaGroups = JSON.parseArray(costsProjectChangeLog.getNewRecord(), SchemeAreaGroup.class);
        List<SchemeAreaGroupVo> transform = LangUtils.transform(areaGroups, o -> schemeAreaGroupService.getSchemeAreaGroupVo(o));
        modelAndView.addObject("areaGroups", transform);
        return modelAndView;
    }


    @RequestMapping(value = "/approvalCommit", name = "审批提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            stateChangeService.approvalCommit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("提交失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/detailView", name = "详情页")
    public ModelAndView detailView(Integer boxId, String processInsId) throws Exception {
        return approvalView(boxId, processInsId, "-1", "");
    }

    @RequestMapping(value = "/editView", name = "返回修改视图", method = RequestMethod.GET)
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/scheme_change/apply", processInsId, boxId, taskId, agentUserAccount);
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);

        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(costsProjectChangeLog.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);

        List<SchemeAreaGroup> areaGroups = JSON.parseArray(costsProjectChangeLog.getNewRecord(), SchemeAreaGroup.class);
        List<SchemeAreaGroupVo> transform = LangUtils.transform(areaGroups, o -> schemeAreaGroupService.getSchemeAreaGroupVo(o));
        modelAndView.addObject("areaGroups", transform);
        modelAndView.addObject("entrustmentPurposes", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE));//委托目的
        modelAndView.addObject("valueTypes", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE));//价值类型

        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editCommit(ProjectChangeLog costsProjectChangeLog, ApprovalModelDto approvalModelDto) {
        try {
            stateChangeService.updateCommit(costsProjectChangeLog, approvalModelDto, ProjectChangeTypeEnum.SCHEME_CHANGE);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("修改失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @ResponseBody
    @PostMapping(value = "/isChanging", name = "判断是否有变更已在流程中,只允许同时存在一个变更流程")
    public HttpResult informationChange(Integer projectId) {
        boolean flag = stateChangeService.isChanging(projectId, ProjectChangeTypeEnum.SCHEME_CHANGE);
        if (flag) {
            return HttpResult.newCorrectResult();
        } else {
            return HttpResult.newErrorResult("项目正在变更中");
        }
    }


}
