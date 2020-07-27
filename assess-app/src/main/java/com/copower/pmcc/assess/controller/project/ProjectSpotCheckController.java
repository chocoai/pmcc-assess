package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectAssessmentBonusEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.ProjectReviewScoreService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/projectSpotCheck")
public class ProjectSpotCheckController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectReviewScoreService projectReviewScoreService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;

    @RequestMapping(value = "/apply", name = "申请页面")
    public ModelAndView apply(Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/reviewScoreApply");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));

        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        if (CollectionUtils.isNotEmpty(projectPlanList)) {
            for (ProjectPlanVo projectPlanVo : projectPlanList) {
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(projectPlanVo.getWorkStageId()));
                ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanVo.getWorkStageId());
                keyValueDto.setValue(String.valueOf(projectWorkStage.getManagerReviewScore()));
                keyValueDto.setExplain(projectPlanVo.getPlanName());
                keyValueDtos.add(keyValueDto);
            }
        }
        modelAndView.addObject("keyValueDtos", keyValueDtos);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", name = "返回修改页面")
    public ModelAndView edit(String processInsId, String taskId, Integer boxId, String agentUserAccount, Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApply", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        return modelAndView;
    }


    @RequestMapping(value = "/approval", name = "审核页面")
    public ModelAndView approval(String processInsId, String taskId, Integer boxId, String agentUserAccount, Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/reviewScoreApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getHistroyList", name = "获取历史数据列表")
    public BootstrapTableVo getHistroyList(Integer masterId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        List<ProjectReviewScoreItem> scoreItems = projectReviewScoreService.getHistoryReviewScoreItemsByMasterId(masterId);
        vo.setTotal((long) scoreItems.size());
        vo.setRows(scoreItems);
        return vo;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "外勤考核流程提交")
    public HttpResult applyCommit(Integer bonusId) {
        //一个项目只能提交一次

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
