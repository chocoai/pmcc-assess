package com.copower.pmcc.assess.controller.project.change;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.output.ProjectInfoChangeVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectStateChangeService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/11/21 10:00
 * @Description:
 */
@RequestMapping(value = "/project.information.change")
@Controller
public class ProjectInfoChangeController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectStateChangeService stateChangeService;

    @GetMapping(value = "/applyView", name = "项目信息变更申请")
    public ModelAndView apply(Integer projectId) throws BusinessException {
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_INFORMATION_CHANGE_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/information_change/apply", boxReDto.getId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfo) ;
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("projectInfoVoJson", JSONObject.toJSONString(projectInfoVo));
        modelAndView.addObject("projectId", projectInfo.getId());
//        modelAndView.addObject("ProjectAFFILIATED", crmRpcBaseDataDicService.getUnitPropertiesList());
        modelAndView.addObject("ProjectAFFILIATED", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_INITIATE_UNIT_TYPE));
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "项目信息申请提交")
    public HttpResult applyCommit(ProjectChangeLog costsProjectChangeLog) {
        try {
            stateChangeService.applyCommit(costsProjectChangeLog, BaseParameterEnum.PROJECT_INFORMATION_CHANGE_PROCESS_KEY, ProjectChangeTypeEnum.INFO_CHANGE);
        } catch (BusinessException e) {
            logger.error("修改项目信息异常", e);
            e.printStackTrace();
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approvalView", name = "项目信息审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/information_change/approval", processInsId, boxId, taskId, agentUserAccount);
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);
        ProjectInfoChangeVo projectInfoChangeVo = JSON.parseObject(costsProjectChangeLog.getNewRecord(), ProjectInfoChangeVo.class);
        ProjectInfoVo simpleProjectInfoVo = stateChangeService.getSimpleProjectInfoVo(projectInfoChangeVo);
        modelAndView.addObject("projectInfo", simpleProjectInfoVo);
        ProjectInfoVo oldProjectInfoVo = JSON.parseObject(costsProjectChangeLog.getOldRecord(), ProjectInfoVo.class);
        modelAndView.addObject("oldProjectInfo", oldProjectInfoVo);
        List<Map<String, Object>> voMaps = stateChangeService.compareTwoClass(oldProjectInfoVo, simpleProjectInfoVo);

        ProjectInfo newData = JSON.parseObject(projectInfoChangeVo.getProjectInfo(), ProjectInfo.class);
        ProjectInfo oldData = JSON.parseObject(costsProjectChangeLog.getOldRecord(), ProjectInfo.class);
        List<Map<String, Object>> dataMaps = stateChangeService.compareTwoClass(oldData, newData);
        voMaps.addAll(dataMaps);
        String changeContent = stateChangeService.getChangeFields(voMaps);
        modelAndView.addObject("changeContent", changeContent);
        modelAndView.addObject("projectId", simpleProjectInfoVo.getId());
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/change/information_change/apply", processInsId, boxId, taskId, agentUserAccount);
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("costsProjectChangeLog", costsProjectChangeLog);
        ProjectInfoChangeVo projectInfoChangeVo = JSON.parseObject(costsProjectChangeLog.getNewRecord(), ProjectInfoChangeVo.class);
        ProjectInfoVo simpleProjectInfoVo = stateChangeService.getSimpleProjectInfoVo(projectInfoChangeVo);
        modelAndView.addObject("projectInfo", simpleProjectInfoVo);
        modelAndView.addObject("projectInfoVoJson", JSONObject.toJSONString(simpleProjectInfoVo));
        modelAndView.addObject("projectId", simpleProjectInfoVo.getId());
//        modelAndView.addObject("ProjectAFFILIATED", crmRpcBaseDataDicService.getUnitPropertiesList());
        modelAndView.addObject("ProjectAFFILIATED", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_INITIATE_UNIT_TYPE));
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());

        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editCommit(ProjectChangeLog costsProjectChangeLog, ApprovalModelDto approvalModelDto) {
        try {
            stateChangeService.updateCommit(costsProjectChangeLog, approvalModelDto, ProjectChangeTypeEnum.INFO_CHANGE);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("修改失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @ResponseBody
    @PostMapping(value = "/isChanging", name = "判断是否有变更已在流程中,只允许同时存在一个变更流程")
    public HttpResult informationChange(Integer projectId) {
        boolean flag = stateChangeService.isChanging(projectId, ProjectChangeTypeEnum.INFO_CHANGE);
        if (flag) {
            return HttpResult.newCorrectResult();
        } else {
            return HttpResult.newErrorResult("项目正在重启中");
        }
    }


}
