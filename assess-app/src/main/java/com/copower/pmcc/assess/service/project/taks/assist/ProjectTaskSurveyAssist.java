package com.copower.pmcc.assess.service.project.taks.assist;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.SurveyAssetTemplateDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplate;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectCheckContentService;
import com.copower.pmcc.assess.service.project.SurveyAssetInventoryService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Component
@WorkFlowAnnotation(desc = "资产清查成果")
public class ProjectTaskSurveyAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectCheckContentService projectCheckContentService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyAssetTemplateDao surveyAssetTemplateDao;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskSurveyIndex", "", 0, "0", "");
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CHECK_CONTENT);

//        for (BaseDataDic baseDataDic : baseDataDicList) {
//            Integer inventoryContent = baseDataDic.getId();
//            Integer projectId = projectPlanDetails.getProjectId();
//            Integer planDetailId = projectPlanDetails.getPlanId();
//            SurveyAssetTemplate surveyAssetTemplate = new SurveyAssetTemplate();
//            surveyAssetTemplate.setProjectId(projectId);
//            surveyAssetTemplate.setPlanDetailId(planDetailId);
//            surveyAssetTemplate.setInventoryContent(inventoryContent);
//            surveyAssetTemplateDao.save(surveyAssetTemplate);
//        }
        List<SurveyAssetTemplate> surveyAssetTemplate = surveyAssetTemplateDao.getSurveyAssetTemplate(0);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        modelAndView.addObject("checkContentList", baseDataDicList); //数据字典
        modelAndView.addObject("thisUserInfo", thisUserInfo);    //当前操作用户信息
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskSurveyApproval", processInsId, boxId, taskId, agentUserAccount);
        projectCheckContentService.getBaseDataDicList(modelAndView, projectPlanDetails);
        surveyAssetInventoryService.getSurveyAssetInventoryByProcessInsId(modelAndView, processInsId);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskSurveyIndex", processInsId, boxId, taskId, agentUserAccount);
        projectCheckContentService.getBaseDataDicList(modelAndView, projectPlanDetails);
        surveyAssetInventoryService.getSurveyAssetInventoryByProcessInsId(modelAndView, processInsId);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/taskSurveyApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        surveyAssetInventoryService.save(projectPlanDetails, processInsId, surveyAssetInventoryService.format(formData));
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        //返回提交走这里
        surveyAssetInventoryService.save(projectPlanDetails, processInsId, surveyAssetInventoryService.format(formData));
    }
}
