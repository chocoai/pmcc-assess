package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailVolumeService;
import com.copower.pmcc.assess.service.method.MdBaseLandPriceService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "基准地价法")
public class ProjectTaskBaseLandPriceAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private MdBaseLandPriceService mdBaseLandPriceService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private DataLandLevelDetailVolumeService dataLandLevelDetailVolumeService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;


    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceIndex", "", 0, "0", "");
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceService.getDataByPlanDetailsId(projectPlanDetails.getId());
        if (mdBaseLandPrice == null) {
            mdBaseLandPrice = new MdBaseLandPrice();
            mdBaseLandPrice.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            mdBaseLandPrice.setPlanDetailsId(projectPlanDetails.getId());
            mdBaseLandPriceService.saveMdBaseLandPrice(mdBaseLandPrice);
            SchemeInfo schemeInfo = new SchemeInfo();
            schemeInfo.setProjectId(projectPlanDetails.getProjectId());
            schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
            schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_BASE_LAND_PRICE).getId());
            schemeInfo.setMethodDataId(mdBaseLandPrice.getId());
            try {
                schemeInfoService.saveSchemeInfo(schemeInfo);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        modelAndView.addObject("master", mdBaseLandPrice);
        modelAndView.addObject("apply", "apply");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceApproval", processInsId, boxId, taskId, agentUserAccount);
        MdBaseLandPrice data = mdBaseLandPriceService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", data);
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        modelAndView.addObject("number", schemeJudgeObject.getNumber());
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceIndex", processInsId, boxId, taskId, agentUserAccount);
        MdBaseLandPrice data = mdBaseLandPriceService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", data);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        MdBaseLandPrice data = mdBaseLandPriceService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", data);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        mdBaseLandPriceService.applyCommit(formData, processInsId);
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        mdBaseLandPriceService.applyCommit(formData, processInsId);

    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        modelAndView.addObject("judgeObject", schemeJudgeObject);
        BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());

        if (basicApply == null) {
            return;
        }
        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        if (basicEstate == null) {
            return;
        }
        BasicEstateLandCategoryInfo categoryInfo = null;
        if(basicApply.getLandCategoryId()!=null){
            categoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoById(basicApply.getLandCategoryId());
        }else{
            List<BasicEstateLandCategoryInfo> categoryInfoList = basicEstateLandCategoryInfoService.getListByEstateId(basicEstate.getId());
            categoryInfo = categoryInfoList.get(0);
            BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
            modelAndView.addObject("volumetricRate", landStateByEstateId.getPlotRatio());
        }


        if (categoryInfo!=null) {
            modelAndView.addObject("landFactorTotalScore",categoryInfo.getLandFactorTotalScore());
            modelAndView.addObject("landLevelContent", categoryInfo.getLandLevelContentResult());
            modelAndView.addObject("levelDetailId",categoryInfo.getLandLevel());
            DataLandLevelDetail levelDetail = dataLandLevelDetailService.getDataLandLevelDetailById(categoryInfo.getLandLevel());
            if(levelDetail!=null){
                modelAndView.addObject("landLevelId", levelDetail.getLandLevelId());
                //基准地价
                DataLandLevelDetail hasStandardPremiumData = dataLandLevelDetailService.hasStandardPremiumParent(levelDetail.getId());
                modelAndView.addObject("standardPremium", hasStandardPremiumData.getPrice());
                //法定年限
                DataLandLevelDetail hasLegalAgeData = dataLandLevelDetailService.hasLegalAgeParent(levelDetail.getId());
                modelAndView.addObject("legalAge", hasLegalAgeData.getLegalAge());
                //容积率修正
                DataLandLevelDetail hasVolumeFractionAmendData = dataLandLevelDetailService.hasVolumeFractionAmendParent(levelDetail.getId());
                BigDecimal amendValue = dataLandLevelDetailVolumeService.getAmendByVolumetricRate(levelDetail.getVolumeRate(), hasVolumeFractionAmendData.getId());
                String volumeFractionAmend = "未配置";
                if (amendValue != null) {
                    volumeFractionAmend = String.format("%.2f", amendValue);
                }
                modelAndView.addObject("volumeFractionAmend", volumeFractionAmend);
                modelAndView.addObject("hasVolumeFractionAmendId", hasVolumeFractionAmendData.getId());
                modelAndView.addObject("volumetricRate", categoryInfo.getPlotRatio());
            }

        }

        //期日修正系数
        BigDecimal dateAmend = mdBaseLandPriceService.getBaseLandPriceDateAmend(schemeJudgeObject.getId());
        modelAndView.addObject("dateAmend", dateAmend);

    }

}
