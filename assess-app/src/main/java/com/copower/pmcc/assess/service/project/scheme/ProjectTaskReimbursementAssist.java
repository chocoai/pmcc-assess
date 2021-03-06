package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
@WorkFlowAnnotation(desc = "法定优先受偿款")
public class ProjectTaskReimbursementAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeReimbursementService schemeReimbursementService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BaseService baseService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementIndex", "", 0, "0", "");
        try {
            writeSchemeReimbursement(projectPlanDetails, schemeAreaGroupService.getSchemeAreaGroup(projectPlanDetails.getAreaId()),modelAndView,false);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"生成法定优先受偿款初始数据失败!");
        }
        return modelAndView;
    }

    /**
     * 详情处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementApproval", processInsId, boxId, taskId, agentUserAccount);
        settingModel(modelAndView,projectPlanDetails);
        return modelAndView;
    }

    /**
     * 返回修改处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementIndex", processInsId, boxId, taskId, agentUserAccount);
        try {
            writeSchemeReimbursement(projectPlanDetails, schemeAreaGroupService.getSchemeAreaGroup(projectPlanDetails.getAreaId()),modelAndView,false);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"生成法定优先受偿款初始数据失败!");
        }
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        settingModel(modelAndView,projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeReimbursementService.applyCommit(formData, processInsId);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeReimbursementService.applyCommit(formData, processInsId);
    }

    /**
     * 设置详情或者审批参数
     * @param modelAndView
     * @param projectPlanDetails
     */
    private void settingModel(ModelAndView modelAndView,ProjectPlanDetails projectPlanDetails){
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getSchemeReimbursement(projectPlanDetails.getId(),projectPlanDetails.getAreaId());
        modelAndView.addObject("master", schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.getSchemeAreaGroup(projectPlanDetails.getAreaId()));
    }

    /**
     * 生成法定优先受偿款初始数据
     *
     * @param projectPlanDetails
     * @param schemeAreaGroup
     * @throws Exception
     */
    private void writeSchemeReimbursement(ProjectPlanDetails projectPlanDetails, SchemeAreaGroup schemeAreaGroup,ModelAndView modelAndView ,boolean repeat) throws Exception {
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getSchemeReimbursement(projectPlanDetails.getId(),schemeAreaGroup.getId());
        if (schemeReimbursement == null){
            schemeReimbursement = new SchemeReimbursement() ;
            schemeReimbursement.setPlanDetailsId(projectPlanDetails.getId());
            schemeReimbursement.setProjectId(projectPlanDetails.getProjectId());
            schemeReimbursement.setAreaId(schemeAreaGroup.getId());
            schemeReimbursement.setProcessInsId(StringUtils.isNotBlank(projectPlanDetails.getProcessInsId())?projectPlanDetails.getProcessInsId():"0");
            schemeReimbursement.setStatus(ProcessStatusEnum.RUN.getValue());
            schemeReimbursementService.saveSchemeReimbursement(schemeReimbursement);
            schemeReimbursementService.init(schemeReimbursement,schemeAreaGroup);//初始化数据
        }
        modelAndView.addObject("master", schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroup);
    }
}
