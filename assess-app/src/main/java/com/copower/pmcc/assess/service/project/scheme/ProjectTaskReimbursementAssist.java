package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursement;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
@WorkFlowAnnotation(desc = "法定优先受偿款")
public class ProjectTaskReimbursementAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SchemeReimbursementService schemeReimbursementService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementIndex", "", 0, "0", "");
        SchemeReimbursement master = schemeReimbursementService.applyInit(projectPlanDetails);
        modelAndView.addObject("master", master);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
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
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByProcessInsId(projectPlanDetails.getProcessInsId());
        modelAndView.addObject("master", schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
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
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByProcessInsId(projectPlanDetails.getProcessInsId());
        modelAndView.addObject("master", schemeReimbursement == null ? new SchemeReimbursement() : schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByProcessInsId(projectPlanDetails.getProcessInsId());
        modelAndView.addObject("master", schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
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
}
