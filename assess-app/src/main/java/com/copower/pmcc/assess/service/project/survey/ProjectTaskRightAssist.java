package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightRecordVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@Component
@WorkFlowAnnotation(desc = "他项权利成果")
public class ProjectTaskRightAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveyAssetInventoryRightRecordService surveyAssetInventoryRightRecordService;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInventoryRightRecordCenterService surveyAssetInventoryRightRecordCenterService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightIndex", "", 0, "0", "");
        List<BaseProjectClassify> inventoryRightTypeList = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_TASKRIGHT_CATEGORY);
        modelAndView.addObject("inventoryRightTypeList",inventoryRightTypeList);
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        modelAndView.addObject("declareRecordList",declareRecordService.getDeclareRecordByProjectId(projectPlanDetails.getProjectId()));
        //抵押评估
        Integer pledgeId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId();
        modelAndView.addObject("pledgeId",pledgeId);
        this.clear(projectPlanDetails);
        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightApproval", processInsId, boxId, taskId, agentUserAccount);
        this.setDetail(projectPlanDetails,modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightIndex", processInsId, boxId, taskId, agentUserAccount);
        this.clear(projectPlanDetails);
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        modelAndView.addObject("declareRecordList",declareRecordService.getDeclareRecordByProjectId(projectPlanDetails.getProjectId()));
        //抵押评估
        Integer pledgeId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId();
        modelAndView.addObject("pledgeId",pledgeId);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskRightApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        this.setDetail(projectPlanDetails,modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        try {
            surveyAssetInventoryRightRecordCenterService.updateSonRecord(projectPlanDetails,processInsId,formData);
        } catch (Exception e) {
            logger.error("更新失败!",e);
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        try {
            surveyAssetInventoryRightRecordCenterService.updateSonRecord(projectPlanDetails,processInsId,formData);
        } catch (Exception e) {
            logger.error("更新失败!",e);
        }
    }

    /**
     * 清除无效数据
     * @param projectPlanDetails
     */
    private void clear(ProjectPlanDetails projectPlanDetails){
        SurveyAssetInventoryRightRecord query = new SurveyAssetInventoryRightRecord();
        query.setProjectId(projectPlanDetails.getProjectId());
        query.setPlanDetailsId(projectPlanDetails.getId());
        query.setCreator(commonService.thisUserAccount());
        List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = surveyAssetInventoryRightRecordService.surveyAssetInventoryRightRecordList(query);
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)){
            for (SurveyAssetInventoryRightRecord rightRecord:surveyAssetInventoryRightRecordList){
                SurveyAssetInventoryRight select = new SurveyAssetInventoryRight();
                select.setInventoryRightRecordId(rightRecord.getId());
                long count = surveyAssetInventoryRightService.getSurveyAssetInventoryRightList(select).stream().count();
                if (count == 0){
                    surveyAssetInventoryRightRecordService.clear(rightRecord);
                }
            }
        }
    }

    /**
     * 设置详情参数
     * @param projectPlanDetails
     * @param modelAndView
     */
    private void setDetail(ProjectPlanDetails projectPlanDetails,ModelAndView modelAndView){
        SurveyAssetInventoryRightRecord query = new SurveyAssetInventoryRightRecord();
        query.setProjectId(projectPlanDetails.getProjectId());
        query.setPlanDetailsId(projectPlanDetails.getId());
        query.setCreator(commonService.thisUserAccount());
        List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = surveyAssetInventoryRightRecordService.surveyAssetInventoryRightRecordList(query);
        if (CollectionUtils.isEmpty(surveyAssetInventoryRightRecordList)){
            surveyAssetInventoryRightRecordList = Lists.newArrayList();
        }
        List<SurveyAssetInventoryRightRecordVo> rightRecordVos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)){
            rightRecordVos = surveyAssetInventoryRightRecordList.stream().map(oo -> surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordVo(oo)).collect(Collectors.toList());
        }
        modelAndView.addObject("surveyAssetInventoryRightRecordList",rightRecordVos);
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        modelAndView.addObject("declareRecordList",declareRecordService.getDeclareRecordByProjectId(projectPlanDetails.getProjectId()));
    }
}
