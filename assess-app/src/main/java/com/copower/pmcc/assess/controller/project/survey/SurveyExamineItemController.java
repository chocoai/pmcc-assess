package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineItem;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineItemService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineTaskService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping("/surveyExamineItem")
public class SurveyExamineItemController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyExamineItemService surveyExamineItemService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @RequestMapping(value = "/examineItemApproval", name = "审批视图", method = {RequestMethod.GET})
    public ModelAndView examineItemApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        String view = "/task/survey/taskExamineItemApproval";
        return getDetailExamineItemView(processInsId, taskId, boxId, agentUserAccount, view);
    }

    @RequestMapping(value = "/examineItemEdit", name = "返回修改视图", method = {RequestMethod.GET})
    public ModelAndView examineItemEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        String view = "/task/survey/taskExamineItemIndex";
        return getDetailExamineItemView(processInsId, taskId, boxId, agentUserAccount, view);
    }

    @RequestMapping(value = "/examineItemDetails", name = "详情视图", method = {RequestMethod.GET})
    public ModelAndView examineItemDetails(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        String view = "/task/survey/taskExamineItemApproval";
        return getDetailExamineItemView(processInsId, "-1", boxId, agentUserAccount, view);
    }

    private ModelAndView getDetailExamineItemView(String processInsId, String taskId, Integer boxId, String agentUserAccount, String view) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(view,processInsId,boxId,taskId,agentUserAccount);
        SurveyExamineItem surveyExamineItem = surveyExamineItemService.getExamineItemByProcessInsId(processInsId);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(surveyExamineItem.getPlanDetailsId());
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        ExamineTypeEnum examineTypeEnum = ExamineTypeEnum.EXPLORE;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY)) {
            examineTypeEnum = ExamineTypeEnum.CASE;
        }
        Map<String, List<SurveyExamineTaskVo>> mapTaskList = surveyCommonService.getExamineTaskByUserAccount(projectPlanDetails.getId(), surveyExamineItem.getCreator());
        modelAndView.addObject("blockTaskList", mapTaskList.get(AssessExamineTaskConstant.BLOCK));
        modelAndView.addObject("estateTaskList", mapTaskList.get(AssessExamineTaskConstant.ESTATE));
        modelAndView.addObject("buildingTaskList", mapTaskList.get(AssessExamineTaskConstant.BUILDING));
        modelAndView.addObject("unitTaskList", mapTaskList.get(AssessExamineTaskConstant.UNIT));
        modelAndView.addObject("houseTaskList", mapTaskList.get(AssessExamineTaskConstant.HOUSE));
        modelAndView.addObject("surveyExamineDataInfoVo",surveyCommonService.getExamineDataInfoVo(declareRecord.getId(),projectPlanDetails.getPid(), examineTypeEnum));

        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(name = "保存调查信息", value = "/saveExamineDataInfo")
    public HttpResult saveExamineDataInfo(String formData) {
        try {
            surveyExamineTaskService.saveExamineDataInfo(formData);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存调查信息", e);
            return HttpResult.newErrorResult("保存调查信息异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "提交调查信息", value = "/submitExamineDataInfo")
    public HttpResult submitExamineDataInfo(String formData,Integer planDetailsId,Integer responsibilityId) {
        try {
            surveyExamineItemService.submitExamineDataInfo(formData,planDetailsId,responsibilityId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存调查信息", e);
            return HttpResult.newErrorResult("保存调查信息异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "返回修改提交调查信息", value = "/submitEditExamineDataInfo")
    public HttpResult submitEditExamineDataInfo(String formData,ApprovalModelDto approvalModelDto) {
        try {
            surveyExamineItemService.submitEditExamineDataInfo(formData,approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存调查信息", e);
            return HttpResult.newErrorResult("保存调查信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/taskApproval", name = "任务审批", method = RequestMethod.POST)
    public HttpResult taskApproval(ApprovalModelDto approvalModelDto) {
        try {
            surveyExamineItemService.taskApproval(approvalModelDto);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
