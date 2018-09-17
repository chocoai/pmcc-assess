package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineInfo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.event.project.SurveyExamineTaskEvent;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

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
@WorkFlowAnnotation(desc = "查勘及案例调查信息成果")
public class ProjectTaskExamineAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineItemIndex", "", 0, "0", "");
        SurveyExamineInfo surveyExamineInfo = surveyExamineInfoService.getExploreByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("surveyExamineInfo", surveyExamineInfo);
        setViewParam(commonService.thisUserAccount(), projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineItemApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails.getExecuteUserAccount(), projectPlanDetails, modelAndView);
        return modelAndView;
    }


    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineItemIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails.getExecuteUserAccount(), projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails,Integer boxId){
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineItemApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails.getExecuteUserAccount(), projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        surveyExamineTaskService.saveExamineDataInfo(formData);
        //更新父级案例信息状态为运行中
        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        planDetails.setStatus(ProcessStatusEnum.RUN.getValue());
        projectPlanDetailsService.updateProjectPlanDetails(planDetails);

        if(StringUtils.isBlank(processInsId)){
            //更新各项表单任务状态
            surveyCommonService.updateExamineTaskStatus(projectPlanDetails.getPid(), commonService.thisUserAccount(), ProjectStatusEnum.FINISH);
        }else {
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SurveyExamineTaskEvent.class.getSimpleName());//修改监听器
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        surveyExamineTaskService.saveExamineDataInfo(formData);
    }

    /**
     * 设置视图参数
     * @param userAccount
     * @param projectPlanDetails
     * @param modelAndView
     */
    private void setViewParam(String userAccount, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        ExamineTypeEnum examineTypeEnum = ExamineTypeEnum.EXPLORE;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY_EXAMINE)) {
            examineTypeEnum = ExamineTypeEnum.CASE;
        }
        modelAndView.addObject("examineType", examineTypeEnum.getId());
        Map<String, List<SurveyExamineTaskVo>> mapTaskList = surveyCommonService.getExamineTaskByUserAccount(projectPlanDetails.getPid(), userAccount);
        modelAndView.addObject("blockTaskList", mapTaskList.get(AssessExamineTaskConstant.BLOCK));
        modelAndView.addObject("estateTaskList", mapTaskList.get(AssessExamineTaskConstant.ESTATE));
        modelAndView.addObject("buildingTaskList", mapTaskList.get(AssessExamineTaskConstant.BUILDING));
        modelAndView.addObject("unitTaskList", mapTaskList.get(AssessExamineTaskConstant.UNIT));
        modelAndView.addObject("houseTaskList", mapTaskList.get(AssessExamineTaskConstant.HOUSE));
        modelAndView.addObject("surveyExamineDataInfoVo",surveyCommonService.getExamineDataInfoVo(declareRecord.getId(),projectPlanDetails.getPid(), examineTypeEnum));
    }

}
