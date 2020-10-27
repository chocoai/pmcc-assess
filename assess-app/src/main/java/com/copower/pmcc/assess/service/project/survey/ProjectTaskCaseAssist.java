package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "案例调查")
public class ProjectTaskCaseAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskSurveyCaseIndex", "", 0, "0", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskSurveyCaseApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskSurveyCaseIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskSurveyCaseApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        modelAndView.addObject("applyBatch", basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId()));
//        modelAndView.addObject("formClassifyList", getFormClassifyList(projectPlanDetails.getProjectId()));
        modelAndView.addObject("formClassifyList", getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        List<BaseDataDic> buildingStatusList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_SURVEY_BUILDING_STATUS);
        modelAndView.addObject("buildingStatusList", buildingStatusList);
        modelAndView.addObject("userAccount", processControllerComponent.getThisUser());
    }

    private List<BaseDataDic> getFormClassifyList() {
        List<BaseDataDic> resultList = Lists.newArrayList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_MULTIPLE);
        if (baseDataDic != null) {
            resultList.add(baseDataDic);
        }
        return resultList;
    }


    private List<BaseDataDic> getFormClassifyList(Integer projectId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY);
        List<BaseDataDic> resultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dataDicList)) return resultList;
        AssessProjectTypeEnum projectTypeEnum = projectInfoService.getAssessProjectType(projectInfo.getProjectCategoryId());
        if (projectTypeEnum == null) return resultList;
        if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE == projectTypeEnum) {
            resultList = LangUtils.filter(dataDicList, o -> {
                return AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_SINGEL.equals(o.getFieldName()) || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_MULTIPLE.equals(o.getFieldName());
            });
        }
        if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND == projectTypeEnum) {
            resultList = LangUtils.filter(dataDicList, o -> {
                return AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(o.getFieldName()) || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(o.getFieldName());
            });
        }
        return resultList;
    }
}
