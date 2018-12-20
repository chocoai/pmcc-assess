package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.event.project.DeclareRealtyEstateCertEvent;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "资产申报成果")
public class ProjectTaskDeclareAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareApplyService declareApplyService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareIndex", "", 0, "0", "");
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareIndex", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        DeclareApply declareApply = new DeclareApply();
        declareApply.setProjectId(projectPlanDetails.getProjectId());
        declareApply.setPlanDetailsId(projectPlanDetails.getId());
        declareApply.setProcessInsId(processInsId);
        declareApplyService.saveDeclareApply(declareApply);
        bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, DeclareRealtyEstateCertEvent.class.getSimpleName());//修改监听器
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
