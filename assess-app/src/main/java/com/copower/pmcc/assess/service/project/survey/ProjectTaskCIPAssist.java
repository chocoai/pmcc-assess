package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.event.project.ProjectTaskCIPEven;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "在建工程查勘成果")
public class ProjectTaskCIPAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveySceneExploreService surveySceneExploreService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCIPIndex", "", 0, "0", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("formClassifyList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY));
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        surveySceneExploreService.deleteUnfinishedData();//删除未完成数据
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCIPApproval", processInsId, boxId, taskId, agentUserAccount);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("formClassifyList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY));
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCIPIndex", processInsId, boxId, taskId, agentUserAccount);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("formClassifyList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY));
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCIPApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("formClassifyList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY));
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        if(StringUtils.isNotEmpty(processInsId)) {
            //修改监听器
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(projectPlanDetails.getProcessInsId(), ProjectTaskCIPEven.class.getSimpleName());
        }else {
            surveyCommonService.updateDeclarePracticalUse(projectPlanDetails);
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
