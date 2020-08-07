package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreGroupVo;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.*;
import com.copower.pmcc.assess.service.project.ProjectAssessmentBonusService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/projectAssessmentBonus")
public class ProjectAssessmentBonusController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectAssessmentBonusService projectAssessmentBonusService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;


    @RequestMapping(value = "/taskIndex", name = "通过外勤记录发起任务 ", method = {RequestMethod.GET})
    public ModelAndView taskIndex() {
        String view = "/project/assessment/assessmentBonusIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/index", name = "外勤加分调整页面（项目经理）")
    public ModelAndView index(Integer bonusId, Integer responsibilityId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/bonusIndex");
        ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusById(bonusId);
        modelAndView.addObject("projectAssessmentBonus", projectAssessmentBonus);
        modelAndView.addObject("projectManager", commonService.thisUserAccount());
        modelAndView.addObject("responsibilityId", responsibilityId);
        return modelAndView;
    }

    @RequestMapping(value = "/apply", name = "外勤加分提交页面")
    public ModelAndView apply(Integer bonusId, Integer responsibilityId) throws BusinessException {
        String boxKey = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_LEGWORK_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxKey);
        if (boxReDto == null || boxReDto.getId() == null) {
            throw new BusinessException("流程模型参数未配置!");
        }
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/bonusApply", boxReDto.getId());
        ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusById(bonusId);
        modelAndView.addObject("projectAssessmentBonus", projectAssessmentBonus);
        modelAndView.addObject("responsibilityId", responsibilityId);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", name = "返回修改页面")
    public ModelAndView edit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/bonusApply", processInsId, boxId, taskId, agentUserAccount);
        ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusByProcessInsId(processInsId);
        modelAndView.addObject("projectAssessmentBonus", projectAssessmentBonus);
        return modelAndView;
    }


    @RequestMapping(value = "/approval", name = "外勤加分审核页面")
    public ModelAndView approval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/bonusApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusByProcessInsId(processInsId);
        modelAndView.addObject("projectAssessmentBonus", projectAssessmentBonus);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "详情页面")
    public ModelAndView detail(String processInsId,  Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/bonusApproval", processInsId, boxId, "-1", "");
        ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusByProcessInsId(processInsId);
        modelAndView.addObject("projectAssessmentBonus", projectAssessmentBonus);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/getAssessmentBonusItems", name = "获取数据列表")
    public BootstrapTableVo getAssessmentBonusItems(Integer bonusId, String projectManager) {
        return projectAssessmentBonusService.getAssessmentBonusItems(bonusId, projectManager);
    }

    @ResponseBody
    @PostMapping(value = "/projectManagerCommit", name = "项目经理提交")
    public HttpResult projectManagerCommit(String formData, Integer responsibilityId) {
        try {
            List<ProjectAssessmentBonusItem> bonusItems = JSON.parseArray(formData, ProjectAssessmentBonusItem.class);
            if (CollectionUtils.isNotEmpty(bonusItems)) {
                for (ProjectAssessmentBonusItem bonusItem : bonusItems) {
                    bonusItem.setStatus(ProjectStatusEnum.FINISH.getKey());
                    projectAssessmentBonusService.saveAssessmentBonusItem(bonusItem);
                }
            }
            bpmRpcProjectTaskService.deleteProjectTask(responsibilityId);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "外勤考核流程提交")
    public HttpResult applyCommit(Integer bonusId, String formData, Integer responsibilityId) {
        //提交前先验证是否所有项目经理都提交
        if (!projectAssessmentBonusService.isAllFinish(bonusId)) {
            return HttpResult.newErrorResult("还有未提交的任务");
        }
        try {
            List<ProjectAssessmentBonusItem> bonusItems = JSON.parseArray(formData, ProjectAssessmentBonusItem.class);
            if (CollectionUtils.isNotEmpty(bonusItems)) {
                bonusItems.forEach(o -> projectAssessmentBonusService.saveAssessmentBonusItem(o));
            }
            ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusById(bonusId);
            ProcessInfo processInfo = new ProcessInfo();
            String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_LEGWORK_PROCESS_KEY);
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);

            processInfo.setFolio(String.format("%s【外勤考核】", projectAssessmentBonus.getTitle()));//流程描述
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectAssessmentBonus.class));
            processInfo.setProcessEventExecutor(ProjectAssessmentBonusEvent.class);
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setTableId(bonusId);
            processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
            processInfo.setStartUser(commonService.thisUserAccount());
            ProcessUserDto processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
            bpmRpcProjectTaskService.deleteProjectTask(responsibilityId);

            projectAssessmentBonus.setProcessInsId(processUserDto.getProcessInsId());
            projectAssessmentBonusService.saveAssessmentBonus(projectAssessmentBonus);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/editCommit", name = "返回修改提交")
    public HttpResult editCommit(String formData, ApprovalModelDto approvalModelDto) {
        try {
            List<ProjectAssessmentBonusItem> bonusItems = JSON.parseArray(formData, ProjectAssessmentBonusItem.class);
            if (CollectionUtils.isNotEmpty(bonusItems)) {
                bonusItems.forEach(o -> projectAssessmentBonusService.saveAssessmentBonusItem(o));
            }
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/approvalCommit", name = "审批提交")
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            logger.error("提交审批失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/launchAssessmentBonusTask", name = "外勤加分考核")
    @ResponseBody
    public HttpResult launchAssessmentBonusTask(String formData) {
        try {
            ProjectAssessmentBonus assessmentBonus = JSONObject.parseObject(formData,ProjectAssessmentBonus.class) ;
            projectAssessmentBonusService.launchAssessmentBonusTask(assessmentBonus);
            return HttpResult.newCorrectResult(200,assessmentBonus);
        } catch (Exception e) {
            logger.error("外勤加分考核失败", e);
            return HttpResult.newErrorResult(500,e);
        }
    }

    @PostMapping(value = "/afreshAssessmentBonusTask", name = "外勤加分考核 重新发起 会删除之前在作业过程中的所有数据")
    @ResponseBody
    public HttpResult afreshAssessmentBonusTask(String formData) {
        try {
            ProjectAssessmentBonus assessmentBonus = JSONObject.parseObject(formData,ProjectAssessmentBonus.class) ;
            projectAssessmentBonusService.afreshAssessmentBonusTask(assessmentBonus);
            return HttpResult.newCorrectResult(200,assessmentBonus);
        } catch (Exception e) {
            logger.error("外勤加分考核失败", e);
            return HttpResult.newErrorResult(500,e);
        }
    }

    @GetMapping(value = "/getHrLegworkDtoList", name = "获取 外勤记录 用作页面检测是否可以发起考核流程")
    @ResponseBody
    public HttpResult getHrLegworkDtoList(String formData) {
        try {
            ProjectAssessmentBonus assessmentBonus = JSONObject.parseObject(formData,ProjectAssessmentBonus.class) ;
            return HttpResult.newCorrectResult(200, projectAssessmentBonusService.getHrLegworkDtoList(assessmentBonus));
        } catch (Exception e) {
            logger.error("外勤加分考核失败", e);
            return HttpResult.newErrorResult(500,e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAssessmentBonusItemHistorys", name = "获取历史数据列表")
    public BootstrapTableVo getAssessmentBonusItemHistorys(Integer itemId) {
        return projectAssessmentBonusService.getAssessmentBonusItemHistorys(itemId);
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectAssessmentBonusDataList", name = "获取外勤考核记录",method = {RequestMethod.GET})
    public BootstrapTableVo getProjectAssessmentBonusDataList(String processInsId, String title, String status, String creator, Integer year, Integer month){
        return projectAssessmentBonusService.getProjectAssessmentBonusDataList(processInsId, title, status, creator, year, month);
    }
}
