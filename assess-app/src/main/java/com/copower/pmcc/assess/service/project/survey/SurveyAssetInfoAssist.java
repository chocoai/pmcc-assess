package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.event.project.SurveyAssetInfoEvent;
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
 * @author zch
 * @see com.copower.pmcc.assess.proxy.face.ProjectTaskInterface
 */

@Component
@WorkFlowAnnotation(desc = "新资产清查成果")
public class SurveyAssetInfoAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    private final String applyViewName = "/project/stageSurvey/taskSurveyAssetInfoIndex";
    private final String approvalViewName = "/project/stageSurvey/taskSurveyAssetInfoApproval";

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(applyViewName, "", 0, "0", "");
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(approvalViewName, processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(applyViewName, processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(approvalViewName, projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }


    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        SurveyAssetInfo surveyAssetInfo = JSONObject.parseObject(formData, SurveyAssetInfo.class);
        if (StringUtils.isNotBlank(processInsId)) {
            surveyAssetInfo.setProcessInsId(processInsId);
        }
        surveyAssetInfoService.saveAndUpdateSurveyAssetInfo(surveyAssetInfo, false);
        surveyAssetInfoService.submit(surveyAssetInfo) ;
        if (StringUtils.isNotBlank(processInsId)) {
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SurveyAssetInfoEvent.class.getSimpleName());//修改监听器
        }else {
            surveyAssetInfoService.writeBackDeclareRecord(surveyAssetInfo);
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SurveyAssetInfo surveyAssetInfo = JSONObject.parseObject(formData, SurveyAssetInfo.class);
        if (StringUtils.isNotBlank(processInsId)) {
            surveyAssetInfo.setProcessInsId(processInsId);
        }
        surveyAssetInfoService.saveAndUpdateSurveyAssetInfo(surveyAssetInfo, false);
        surveyAssetInfoService.submit(surveyAssetInfo) ;
    }

    private void setModelViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        SurveyAssetInfo surveyAssetInfo = surveyAssetInfoService.getSurveyAssetInfoByPlanDetailsId(projectPlanDetails);
        //统计信息
        surveyAssetInfoService.statistics(surveyAssetInfo);

        modelAndView.addObject(StringUtils.uncapitalize(SurveyAssetInfo.class.getSimpleName()), surveyAssetInfo);
        modelAndView.addObject("thisUserInfo", processControllerComponent.getThisUserInfo());    //当前操作用户信息
    }

}
