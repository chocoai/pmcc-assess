package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.SurveyAssetInventoryVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.SurveyAssetInventoryEvent;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Component
@WorkFlowAnnotation(desc = "资产清查成果")
public class ProjectTaskAssetInventoryAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/assetInventoryIndex", "", 0, "0", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        List<BaseDataDic> inventoryRightTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE);
        List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentService.initAssetInventoryContent(projectPlanDetails, declareRecord);
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(list);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        modelAndView.addObject("inventoryRightTypeList", inventoryRightTypeList); //数据字典
        modelAndView.addObject("thisUserInfo", thisUserInfo);    //当前操作用户信息
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);
        //证载用途
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_LOAD_UTILITY);
        modelAndView.addObject("types", types);
        //是否办证
        List<BaseDataDic> certificateTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE);
        modelAndView.addObject("certificateTypes", certificateTypes);
        //土地类型
        BaseProjectClassify houseLand = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE);
        modelAndView.addObject("houseLand",houseLand.getId());
        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/assetInventoryApproval", processInsId, boxId, taskId, agentUserAccount);
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/assetInventoryIndex", processInsId, boxId, taskId, agentUserAccount);
        SurveyAssetInventoryVo surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
        setModelViewParam(projectPlanDetails, modelAndView);
        //房产证类型
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE);
        modelAndView.addObject("types", types);
        //是否办证
        List<BaseDataDic> certificateTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE);
        modelAndView.addObject("certificateTypes", certificateTypes);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/assetInventoryApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    private void setModelViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        modelAndView.addObject("thisUserInfo", processControllerComponent.getThisUserInfo());    //当前操作用户信息
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
        List<SurveyAssetInventoryContent> surveyAssetInventoryContents = surveyAssetInventoryContentService.initAssetInventoryContent(projectPlanDetails,declareRecord);
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(surveyAssetInventoryContents);
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);
        List<BaseDataDic> inventoryRightTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE);
        modelAndView.addObject("inventoryRightTypeList", inventoryRightTypeList);
        //土地类型
        BaseProjectClassify houseLand = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE);
        modelAndView.addObject("houseLand",houseLand.getId());
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        surveyAssetInventoryService.save(projectPlanDetails, processInsId, surveyAssetInventoryService.format(formData));
        if (StringUtils.isBlank(processInsId)) {
            SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
            surveyAssetInventoryService.writeBackDeclareRecord(surveyAssetInventory);
        } else {
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SurveyAssetInventoryEvent.class.getSimpleName());//修改监听器
        }
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
