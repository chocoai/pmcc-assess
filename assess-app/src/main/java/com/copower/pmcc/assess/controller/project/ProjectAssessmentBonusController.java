package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.*;
import com.copower.pmcc.assess.service.project.ProjectAssessmentBonusService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/index", name = "外勤加分调整页面（项目经理）")
    public ModelAndView index(Integer bonusId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/bonusIndex");
        ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusById(bonusId);
        modelAndView.addObject("projectAssessmentBonus", projectAssessmentBonus);
        modelAndView.addObject("projectManager", commonService.thisUserAccount());
        return modelAndView;
    }

    @RequestMapping(value = "/apply", name = "外勤加分提交页面")
    public ModelAndView apply(Integer bonusId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/bonusApply");
        ProjectAssessmentBonus projectAssessmentBonus = projectAssessmentBonusService.getAssessmentBonusById(bonusId);
        modelAndView.addObject("projectAssessmentBonus", projectAssessmentBonus);
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

    @ResponseBody
    @RequestMapping(value = "/getAssessmentBonusItems", name = "获取数据列表")
    public BootstrapTableVo getAssessmentBonusItems(Integer bonusId, String projectManager) {
        return projectAssessmentBonusService.getAssessmentBonusItems(bonusId, projectManager);
    }

    @ResponseBody
    @PostMapping(value = "/projectManagerCommit", name = "项目经理提交")
    public HttpResult projectManagerCommit(Integer bonusId, String projectManager) {
        try {
            List<ProjectAssessmentBonusItem> bonusItems = projectAssessmentBonusService.getAssessmentBonusItemsByManager(bonusId, projectManager);
            if (CollectionUtils.isNotEmpty(bonusItems)) {
                for (ProjectAssessmentBonusItem bonusItem : bonusItems) {
                    bonusItem.setStatus(ProjectStatusEnum.FINISH.getKey());
                    projectAssessmentBonusService.saveAssessmentBonusItem(bonusItem);
                }
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "外勤考核流程提交")
    public HttpResult applyCommit(Integer bonusId) {
        //提交前先验证是否所有项目经理都提交
        if(!projectAssessmentBonusService.isAllFinish(bonusId)){
            return HttpResult.newErrorResult("还有未提交的任务");
        }
        try {
            ProcessInfo processInfo = new ProcessInfo();
            String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.CASE_BASE_INFO_BATCH_APPLY_KEY);
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);

            processInfo.setFolio(String.format("%s【变更提交】%s"));//流程描述
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectAssessmentBonus.class));
            processInfo.setProcessEventExecutor(ProjectAssessmentBonusEvent.class);
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setTableId(bonusId);
            processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
            processInfo.setStartUser(commonService.thisUserAccount());
            processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return HttpResult.newCorrectResult();
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
}
