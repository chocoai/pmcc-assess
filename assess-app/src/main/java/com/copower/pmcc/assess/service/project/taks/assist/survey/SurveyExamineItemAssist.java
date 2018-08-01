package com.copower.pmcc.assess.service.project.taks.assist.survey;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineInfo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "调查成果")
public class SurveyExamineItemAssist implements ProjectTaskInterface {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private HttpServletResponse response;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExamineItemIndex", "", 0, "0", "");
        SurveyExamineInfo surveyExamineInfo = surveyExamineInfoService.getExploreByPlanDetailsId(projectPlanDetails.getId());
        if (surveyExamineInfo == null) {//还未安排任务，需先设置任务
            try {
                response.sendRedirect(String.format("/pmcc-assess/surveyExamine/assignment?planDetailsId=%s", projectPlanDetails.getId()));
            } catch (IOException e) {
                logger.error("调查成果重定向异常", e);
            }
            return modelAndView;
        }
        modelAndView.addObject("surveyExamineInfo", surveyExamineInfo);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);

        ExamineTypeEnum examineTypeEnum = ExamineTypeEnum.EXPLORE;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY)) {
            examineTypeEnum = ExamineTypeEnum.CASE;
        }
        modelAndView.addObject("examineType", examineTypeEnum.getId());
        Map<String, List<SurveyExamineTaskVo>> mapTaskList = surveyCommonService.getExamineTaskByUserAccount(projectPlanDetails.getId(), commonService.thisUserAccount());
        modelAndView.addObject("blockTaskList", mapTaskList.get(AssessExamineTaskConstant.BLOCK));
        modelAndView.addObject("estateTaskList", mapTaskList.get(AssessExamineTaskConstant.ESTATE));
        modelAndView.addObject("buildingTaskList", mapTaskList.get(AssessExamineTaskConstant.BUILDING));
        modelAndView.addObject("unitTaskList", mapTaskList.get(AssessExamineTaskConstant.UNIT));
        modelAndView.addObject("houseTaskList", mapTaskList.get(AssessExamineTaskConstant.HOUSE));
        modelAndView.addObject("surveyExamineDataInfoVo", surveyCommonService.getExamineDataInfoVo(projectPlanDetails.getDeclareRecordId(), examineTypeEnum));
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExamineItemApproval", processInsId, boxId, taskId, agentUserAccount);

        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExploreIndex", processInsId, boxId, taskId, agentUserAccount);

        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {
        if (!org.springframework.util.StringUtils.isEmpty(formData)){

        }
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskExamineItemApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        ExamineTypeEnum examineTypeEnum = ExamineTypeEnum.EXPLORE;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY)) {
            examineTypeEnum = ExamineTypeEnum.CASE;
        }
        Map<String, List<SurveyExamineTaskVo>> mapTaskList = surveyCommonService.getExamineTaskAll(projectPlanDetails.getId());
        modelAndView.addObject("blockTaskList", mapTaskList.get(AssessExamineTaskConstant.BLOCK));
        modelAndView.addObject("estateTaskList", mapTaskList.get(AssessExamineTaskConstant.ESTATE));
        modelAndView.addObject("buildingTaskList", mapTaskList.get(AssessExamineTaskConstant.BUILDING));
        modelAndView.addObject("unitTaskList", mapTaskList.get(AssessExamineTaskConstant.UNIT));
        modelAndView.addObject("houseTaskList", mapTaskList.get(AssessExamineTaskConstant.HOUSE));
        modelAndView.addObject("surveyExamineDataInfoVo", surveyCommonService.getExamineDataInfoVo(declareRecord.getId(),examineTypeEnum));
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        if (!org.springframework.util.StringUtils.isEmpty(formData)){

        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        if (!org.springframework.util.StringUtils.isEmpty(formData)){

        }
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        if (!org.springframework.util.StringUtils.isEmpty(formData)){

        }
    }
}
