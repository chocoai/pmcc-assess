package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/assetInventoryIndex", "", 0, "0", "");

        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CHECK_CONTENT);
        List<BaseDataDic> otherRightTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.OTHER_RIGHT_TYPE);
        List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(0);
        if(list.size() == 0){
            for (BaseDataDic baseDataDic : baseDataDicList) {
                Integer inventoryContent = baseDataDic.getId();
                Integer projectId = projectPlanDetails.getProjectId();
                Integer planDetailId = projectPlanDetails.getPlanId();
                SurveyAssetInventoryContent surveyAssetInventoryContent = new SurveyAssetInventoryContent();
                surveyAssetInventoryContent.setProjectId(projectId);
                surveyAssetInventoryContent.setPlanDetailsId(planDetailId);
                surveyAssetInventoryContent.setInventoryContent(inventoryContent);
                surveyAssetInventoryContentDao.save(surveyAssetInventoryContent);
            }
        }
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentProject = projectPlanDetailsDao.getProjectPlanDetailsById(id);

        List<SurveyAssetInventoryContent> surveyAssetInventoryContents = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(0);
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(surveyAssetInventoryContents);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        modelAndView.addObject("otherRightTypeList", otherRightTypeList); //数据字典
        modelAndView.addObject("thisUserInfo", thisUserInfo);    //当前操作用户信息
        modelAndView.addObject("parentProject",parentProject);
        modelAndView.addObject("surveyAssetInventoryContentVos",surveyAssetInventoryContentVos);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/assetInventoryApproval", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/assetInventoryIndex", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/survey/assetInventoryApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    private void setModelViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CHECK_CONTENT);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentProject = projectPlanDetailsDao.getProjectPlanDetailsById(id);
        modelAndView.addObject("checkContentList",baseDataDicList); //数据字典
        modelAndView.addObject("thisUserInfo",thisUserInfo);    //当前操作用户信息
        modelAndView.addObject("parentProject",parentProject);  //识别的项目名称

        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
        List<BaseDataDic> otherRightTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.OTHER_RIGHT_TYPE);
        List<SurveyAssetInventoryContent> surveyAssetInventoryContents = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(surveyAssetInventory.getPlanDetailId());
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(surveyAssetInventoryContents);

        modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
        modelAndView.addObject("surveyAssetInventoryContentVos",surveyAssetInventoryContentVos);
        modelAndView.addObject("otherRightTypeList",otherRightTypeList);
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
