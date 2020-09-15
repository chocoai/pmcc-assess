package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


@Component
@WorkFlowAnnotation(desc = "他项权利")
public class ProjectTaskRightAssist implements ProjectTaskInterface {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyAssetRightGroupService surveyAssetRightGroupService;
    @Autowired
    private SurveyAssetRightService surveyAssetRightService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightIndex", "", 0, "0", "");
        setDetail(projectPlanDetails,modelAndView) ;
        surveyAssetRightGroupService.clear(projectPlanDetails);
        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightApproval", processInsId, boxId, taskId, agentUserAccount);
        setDetail(projectPlanDetails,modelAndView) ;
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightIndex", processInsId, boxId, taskId, agentUserAccount);
        setDetail(projectPlanDetails,modelAndView) ;
        surveyAssetRightGroupService.clear(projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setDetail(projectPlanDetails,modelAndView) ;
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        try {
            surveyAssetRightService.applyCommit(projectPlanDetails, processInsId, formData);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"他项权力 更新失败!");
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        try {
            surveyAssetRightService.applyCommit(projectPlanDetails, processInsId, formData);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"他项权力 更新失败!");
        }
    }


    private void setDetail(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView){
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()),projectPlanDetails);
        //抵押评估
        modelAndView.addObject("pledgeId", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId());
    }
}
