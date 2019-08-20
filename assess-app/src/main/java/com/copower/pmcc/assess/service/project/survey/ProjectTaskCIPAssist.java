package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
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


    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCIPIndex", "", 0, "0", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        SurveySceneExplore surveySceneExplore = surveySceneExploreService.getSurveySceneExplore(projectPlanDetails.getId());
        if (surveySceneExplore != null) {
            modelAndView.addObject("surveySceneExplore", surveySceneExplore);
        }
        modelAndView.addObject("applyBatch", basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getPid()));
        surveySceneExploreService.deleteUnfinishedData();//删除未完成数据
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCIPApproval", processInsId, boxId, taskId, agentUserAccount);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        SurveySceneExplore surveySceneExplore = surveySceneExploreService.getSurveySceneExplore(projectPlanDetails.getId());
        modelAndView.addObject("surveySceneExplore", surveySceneExplore);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getInfoById(surveySceneExplore.getBatchApplyId()));
        BasicApply basicApply = new BasicApply();
        basicApply.setType(3);
        modelAndView.addObject("basicApply", basicApply);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCIPIndex", processInsId, boxId, taskId, agentUserAccount);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        SurveySceneExplore surveySceneExplore = surveySceneExploreService.getSurveySceneExplore(projectPlanDetails.getId());
        modelAndView.addObject("surveySceneExplore", surveySceneExplore);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getInfoById(surveySceneExplore.getBatchApplyId()));
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
        SurveySceneExplore surveySceneExplore = surveySceneExploreService.getSurveySceneExplore(projectPlanDetails.getId());
        modelAndView.addObject("surveySceneExplore", surveySceneExplore);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getInfoById(surveySceneExplore.getBatchApplyId()));
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        SurveySceneExplore surveySceneExplore = JSON.parseObject(formData, SurveySceneExplore.class);
        surveySceneExplore.setProcessInsId(processInsId);
        surveySceneExplore.setCreator(processControllerComponent.getThisUser());
        surveySceneExploreService.saveSurveySceneExplore(surveySceneExplore);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
