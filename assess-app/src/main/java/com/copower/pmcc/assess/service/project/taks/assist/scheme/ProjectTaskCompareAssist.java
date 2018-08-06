package com.copower.pmcc.assess.service.project.taks.assist.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeMarketCompareApplyDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSupportInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
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
@WorkFlowAnnotation(desc = "市场比较法成果")
public class ProjectTaskCompareAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private MdMarketCompareService mdMarketCompareService;
    @Autowired
    private SchemeSupportInfoService schemeSupportInfoService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeInfoService schemeInfoService;


    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareIndex", "", 0, "0", "");

        //初始化支撑数据
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        //评估支持数据
        List<SchemeSupportInfo> supportInfoList = schemeSupportInfoService.getSupportInfoList(projectPlanDetails.getId());
        modelAndView.addObject("supportInfosJSON", JSON.toJSONString(supportInfoList));
        //市场比较法相关
        MdMarketCompare marketCompare = mdMarketCompareService.getMdMarketCompare(201);
        List<MdMarketCompareField> fieldList = mdMarketCompareService.getFieldListByMcId(marketCompare.getId());
        MdMarketCompareItem evaluationObject = mdMarketCompareService.getEvaluationListByMcId(marketCompare.getId());
        List<MdMarketCompareItem> caseList = mdMarketCompareService.getCaseListByMcId(marketCompare.getId());
        modelAndView.addObject("marketCompareJSON", JSON.toJSONString(marketCompare));
        modelAndView.addObject("fieldsJSON", JSON.toJSONString(fieldList));
        modelAndView.addObject("evaluationJSON", JSON.toJSONString(evaluationObject));
        modelAndView.addObject("casesJSON", JSON.toJSONString(caseList));
    }


    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

        SchemeMarketCompareApplyDto schemeMarketCompareApplyDto = JSON.parseObject(formData, SchemeMarketCompareApplyDto.class);
        MdMarketCompare mdMarketCompare = mdMarketCompareService.saveResult(schemeMarketCompareApplyDto.getMarketCompare());
        if (CollectionUtils.isNotEmpty(schemeMarketCompareApplyDto.getSupportInfoList())) {
            for (SchemeSupportInfo schemeSupportInfo : schemeMarketCompareApplyDto.getSupportInfoList()) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setMethodType(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        schemeInfo.setMethodDataId(mdMarketCompare.getId());
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeMarketCompareApplyDto schemeMarketCompareApplyDto = JSON.parseObject(formData, SchemeMarketCompareApplyDto.class);
        mdMarketCompareService.saveResult(schemeMarketCompareApplyDto.getMarketCompare());
        if (CollectionUtils.isNotEmpty(schemeMarketCompareApplyDto.getSupportInfoList())) {
            for (SchemeSupportInfo schemeSupportInfo : schemeMarketCompareApplyDto.getSupportInfoList()) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }
    }
}
