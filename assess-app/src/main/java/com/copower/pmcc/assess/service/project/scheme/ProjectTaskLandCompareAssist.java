package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeMarketCompareApplyDto;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "土地比较法")
public class ProjectTaskLandCompareAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private MdMarketCompareService mdMarketCompareService;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskMarketCompareIndex", "", 0, "0", "");
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        if (info == null) {
            MdMarketCompare marketCompare = mdMarketCompareService.initExplore(judgeObject,true);
            if (marketCompare != null) {
                SchemeInfo schemeInfo = new SchemeInfo();
                schemeInfo.setProjectId(projectPlanDetails.getProjectId());
                schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
                schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
                schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_LAND_COMPARE).getId());
                schemeInfo.setMethodDataId(marketCompare.getId());
                try {
                    schemeInfoService.saveSchemeInfo(schemeInfo);
                    info = schemeInfo;
                } catch (BusinessException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        setViewParam(info,judgeObject, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskMarketCompareApproval", processInsId, boxId, taskId, agentUserAccount);
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        setViewParam(info,judgeObject, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskMarketCompareIndex", processInsId, boxId, taskId, agentUserAccount);
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        setViewParam(info,judgeObject, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskMarketCompareApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        setViewParam(info,judgeObject, modelAndView);
        return modelAndView;
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(SchemeInfo info,SchemeJudgeObject judgeObject, ModelAndView modelAndView) {
        MdMarketCompare marketCompare = mdMarketCompareService.getMdMarketCompare(info.getMethodDataId());
        List<DataSetUseField> fieldList = mdMarketCompareService.getLandFieldListByApplyId(judgeObject.getBasicApplyId());
        MdMarketCompareItem evaluationObject = mdMarketCompareService.getEvaluationByMcId(marketCompare.getId());
        List<MdMarketCompareItem> caseList = mdMarketCompareService.getCaseListByMcId(marketCompare.getId());
        Integer projectPhaseId = projectPhaseService.getSceneExplorePhase(true).getId();
        List<BasicApply> standardJudgeList = mdMarketCompareService.getStandardJudgeList(judgeObject.getDeclareRecordId(),projectPhaseId);
        modelAndView.addObject("standardJudgesJSON", JSON.toJSONString(CollectionUtils.isEmpty(standardJudgeList) ? Lists.newArrayList() : standardJudgeList));
        modelAndView.addObject("marketCompareJSON", JSON.toJSONString(marketCompare));
        modelAndView.addObject("fieldsJSON", JSON.toJSONString(fieldList));
        modelAndView.addObject("evaluationJSON", JSON.toJSONString(evaluationObject));
        modelAndView.addObject("casesJSON", JSON.toJSONString(caseList));
        modelAndView.addObject("mcId", marketCompare.getId());
        modelAndView.addObject("setUse", judgeObject.getSetUse());
        modelAndView.addObject("judgeObject", judgeObject);
        modelAndView.addObject("isLand", true);
    }


    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeMarketCompareApplyDto schemeMarketCompareApplyDto = JSON.parseObject(formData, SchemeMarketCompareApplyDto.class);
        mdMarketCompareService.saveResult(schemeMarketCompareApplyDto.getMarketCompare());
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeMarketCompareApplyDto schemeMarketCompareApplyDto = JSON.parseObject(formData, SchemeMarketCompareApplyDto.class);
        mdMarketCompareService.saveResult(schemeMarketCompareApplyDto.getMarketCompare());
    }
}
