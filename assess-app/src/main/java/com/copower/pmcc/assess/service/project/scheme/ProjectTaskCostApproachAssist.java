package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdCostApproachService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "成本逼近法")
public class ProjectTaskCostApproachAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private MdCostApproachService mdCostApproachService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeInfoService schemeInfoService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachIndex", "", 0, "0", "");
        MdCostApproach mdCostApproach = mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId());
        if (mdCostApproach == null) {
            mdCostApproach = new MdCostApproach();
            mdCostApproach.setPlanDetailsId(projectPlanDetails.getId());
            mdCostApproach.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            mdCostApproachService.saveMdCostApproach(mdCostApproach);
            SchemeInfo schemeInfo = new SchemeInfo();
            schemeInfo.setProjectId(projectPlanDetails.getProjectId());
            schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
            schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST_APPROACH).getId());
            schemeInfo.setMethodDataId(mdCostApproach.getId());
            mdCostApproachService.initTaxeItem(mdCostApproach);
            try {
                schemeInfoService.saveSchemeInfo(schemeInfo);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }

        modelAndView.addObject("master", mdCostApproach);
        modelAndView.addObject("apply", "apply");
        mdCostApproachService.setViewParam(mdCostApproach, projectPlanDetails.getJudgeObjectId(), modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachApproval", processInsId, boxId, taskId, agentUserAccount);
        MdCostApproach data = mdCostApproachService.getMdCostApproachVo(mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("master", data);
        List<MdCostApproachTaxes> list = mdCostApproachService.getMdCostApproachTaxesListByMasterId(data.getId());
        modelAndView.addObject("taxesVos", list);
        mdCostApproachService.setViewParam(data, projectPlanDetails.getJudgeObjectId(), modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachIndex", processInsId, boxId, taskId, agentUserAccount);
        MdCostApproach data = mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("master", data);
        List<MdCostApproachTaxes> list = mdCostApproachService.getMdCostApproachTaxesListByMasterId(data.getId());
        modelAndView.addObject("taxesVos", list);
        mdCostApproachService.setViewParam(data, projectPlanDetails.getJudgeObjectId(), modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        MdCostApproach data = mdCostApproachService.getMdCostApproachVo(mdCostApproachService.getDataByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("master", data);
        List<MdCostApproachTaxes> list = mdCostApproachService.getMdCostApproachTaxesListByMasterId(data.getId());
        modelAndView.addObject("taxesVos", list);
        mdCostApproachService.setViewParam(data, projectPlanDetails.getJudgeObjectId(), modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        mdCostApproachService.applyCommit(formData, processInsId);
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        mdCostApproachService.applyCommit(formData, processInsId);
    }


}
