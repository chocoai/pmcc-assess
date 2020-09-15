package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
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
@WorkFlowAnnotation(desc = "资产申报")
public class ProjectTaskDeclareAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclarePublicService declarePublicService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private InitiateConsignorService initiateConsignorService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareApplyDetailService declareApplyExtensionService;
    @Autowired
    private DeclareApplyService declareApplyService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareIndex", "", 0, "0", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        declarePublicService.applyCommitTask(projectPlanDetails, processInsId, formData);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        declarePublicService.editCommitTask(projectPlanDetails, processInsId, formData);
    }


    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        DeclareApply declare = declarePublicService.getDeclareApplyByProjectId(projectPlanDetails.getProjectId());
        if (declare == null) {
            declare = new DeclareApply();
            declare.setPlanDetailsId(projectPlanDetails.getId());
            declare.setProjectId(projectPlanDetails.getProjectId());
            declareApplyService.saveDeclareApply(declare);
        }
        modelAndView.addObject("declare", declare);
        modelAndView.addObject("declareApplyExtensionList", declareApplyExtensionService.getDeclareApplyDetailListByDeclareId(declare.getId()));
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        modelAndView.addObject(StringUtils.uncapitalize(DeclareApply.class.getSimpleName()), declare);
        InitiateConsignorVo consignor = initiateConsignorService.getDataByProjectId(projectPlanDetails.getProjectId());
        modelAndView.addObject("consignor", StringUtils.isEmpty(consignor.getCsEntrustmentUnit()) ? consignor.getCsName() : consignor.getCsEntrustmentUnit());
        List<BaseDataDic> certificateTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE);
        modelAndView.addObject("certificateTypes", certificateTypes);
    }
}
