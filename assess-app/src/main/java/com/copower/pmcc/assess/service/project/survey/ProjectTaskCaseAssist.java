package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.SurveyCaseStudyEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "案例调查成果")
public class ProjectTaskCaseAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCaseIndex", "", 0, "0", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        this.assignment(projectPlanDetails, declareRecord, modelAndView);
        return modelAndView;
    }

    /**
     * 分派 传输数据
     *
     * @param projectPlanDetails
     * @param declareRecord
     * @param modelAndView
     */
    private void assignment(ProjectPlanDetails projectPlanDetails, DeclareRecord declareRecord, ModelAndView modelAndView) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectCategoryId());
        //确认调查类型 查勘或案例
        Integer examineType = ExamineTypeEnum.EXPLORE.getId();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY)) {
            examineType = ExamineTypeEnum.CASE.getId();
        }
        SurveyExamineInfo surveyExamineInfo = surveyExamineInfoService.getExamineInfoByPlanDetailsId(projectPlanDetails.getId());
        if (surveyExamineInfo == null) {
            //清空数据
            surveyExamineTaskService.deleteTaskByPlanDetailsId(projectPlanDetails.getId());
        }
        List<BaseDataDic> transactionTypeList = new ArrayList<>();
        if (projectClassify != null && StringUtils.isNotBlank(projectClassify.getFieldName())) {
            switch (projectClassify.getFieldName()) {
                //房产项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE:
                    transactionTypeList = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE);
                    break;
                //简单房产项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE:
                    transactionTypeList = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE);
                    break;
                //土地项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE:
                    transactionTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND);
                    break;
                //土地简单项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE:
                    transactionTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND);
                    break;
                default:
                    break;
            }
        }
        modelAndView.addObject("examineType", examineType);
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("transactionTypeList", transactionTypeList);
        modelAndView.addObject("surveyExamineInfo", surveyExamineInfo);
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCaseApproval", processInsId, boxId, taskId, agentUserAccount);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("surveyCaseStudy", surveyCaseStudyService.getSurveyCaseStudy(processInsId));
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCaseIndex", processInsId, boxId, taskId, agentUserAccount);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("surveyCaseStudy", surveyCaseStudyService.getSurveyCaseStudy(processInsId));
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskCaseApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("surveyCaseStudy", surveyCaseStudyService.getSurveyCaseStudy(projectPlanDetails.getId()));
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        SurveyCaseStudy surveyCaseStudy = JSON.parseObject(formData, SurveyCaseStudy.class);
        surveyCaseStudy.setProcessInsId(processInsId);
        surveyCaseStudyService.saveSurveyCaseStudy(surveyCaseStudy);
        //同步数据到其它相关证书
        if (StringUtils.isBlank(processInsId)) {

        } else {
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SurveyCaseStudyEvent.class.getSimpleName());//修改监听器
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
