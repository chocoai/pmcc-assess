package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.project.DeclareRealtyEstateCertEvent;
import com.copower.pmcc.assess.service.event.project.SurveyAssetInventoryEvent;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
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
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/assetInventoryIndex", "", 0, "0", "");
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        List<BaseDataDic> inventoryContentList = baseDataDicService.getCacheDataDicList(declareRecord.getInventoryContentKey());
        Collections.sort(inventoryContentList, Comparator.comparing(BaseDataDic::getSorting).reversed());//降序排列
        List<BaseDataDic> inventoryRightTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE);
        List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(projectPlanDetails.getId());
        if (CollectionUtils.isEmpty(list)) {
            for (BaseDataDic baseDataDic : inventoryContentList) {
                Integer projectId = projectPlanDetails.getProjectId();
                SurveyAssetInventoryContent surveyAssetInventoryContent = new SurveyAssetInventoryContent();
                surveyAssetInventoryContent.setProjectId(projectId);
                surveyAssetInventoryContent.setPlanDetailsId(projectPlanDetails.getId());
                surveyAssetInventoryContent.setInventoryContent(baseDataDic.getId());
                switch (baseDataDic.getFieldName()) {
                    case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS://登记地址与实际地址
                    case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS://房产证与土地证证载地址
                        surveyAssetInventoryContent.setRegistration(declareRecord.getSeat());
                        break;
                    case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE://登记用途与实际用途
                        if (StringUtils.isNotBlank(declareRecord.getCertUse())) {
                            surveyAssetInventoryContent.setRegistration(declareRecord.getCertUse());
                        }
                        break;
                    case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA://登记面积与实际面积
                        surveyAssetInventoryContent.setRegistration(String.valueOf(declareRecord.getFloorArea()));
                        break;
                }
                surveyAssetInventoryContentDao.save(surveyAssetInventoryContent);
            }
            list = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(projectPlanDetails.getId());
        }
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(list);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        modelAndView.addObject("inventoryRightTypeList", inventoryRightTypeList); //数据字典
        modelAndView.addObject("thisUserInfo", thisUserInfo);    //当前操作用户信息
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);
        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/assetInventoryApproval", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/assetInventoryIndex", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(projectPlanDetails, modelAndView);
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
        List<SurveyAssetInventoryContent> surveyAssetInventoryContents = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(surveyAssetInventory.getPlanDetailId());
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(surveyAssetInventoryContents);
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);
        List<BaseDataDic> inventoryRightTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE);
        modelAndView.addObject("inventoryRightTypeList", inventoryRightTypeList);
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        surveyAssetInventoryService.save(projectPlanDetails, processInsId, surveyAssetInventoryService.format(formData));
        if (StringUtils.isBlank(processInsId)) {
            SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByPlanDetailsId(projectPlanDetails.getId());
            surveyAssetInventoryService.writeBackDeclareRecord(surveyAssetInventory);
        } else {
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, DeclareRealtyEstateCertEvent.class.getSimpleName());//修改监听器
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
