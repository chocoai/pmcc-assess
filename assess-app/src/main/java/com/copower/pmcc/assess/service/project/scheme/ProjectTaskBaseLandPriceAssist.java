package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdBaseLandPriceService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


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
    private MdBaseLandPriceService mdBaseLandPriceService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeInfoService schemeInfoService;


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
        mdBaseLandPriceService.setViewParam(projectPlanDetails.getJudgeObjectId(),mdBaseLandPrice, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceApproval", processInsId, boxId, taskId, agentUserAccount);
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", mdBaseLandPrice);
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        modelAndView.addObject("number", schemeJudgeObject.getNumber());
        mdBaseLandPriceService.setViewParam(projectPlanDetails.getJudgeObjectId(),mdBaseLandPrice, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceIndex", processInsId, boxId, taskId, agentUserAccount);
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", mdBaseLandPrice);
        mdBaseLandPriceService.setViewParam(projectPlanDetails.getJudgeObjectId(),mdBaseLandPrice, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", mdBaseLandPrice);
        mdBaseLandPriceService.setViewParam(projectPlanDetails.getJudgeObjectId(),mdBaseLandPrice, modelAndView);
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
}
