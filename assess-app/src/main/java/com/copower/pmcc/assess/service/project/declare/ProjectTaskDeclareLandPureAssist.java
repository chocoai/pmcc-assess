package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2019/4/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "资产申报(纯土地)")
public class ProjectTaskDeclareLandPureAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclarePublicService declarePublicService;
    @Autowired
    private ErpAreaService erpAreaService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareLandPureIndex", "", 0, "0", "");
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareLandPureApproval", processInsId, boxId, taskId, agentUserAccount);
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareLandPureIndex", processInsId, boxId, taskId, agentUserAccount);
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageDeclare/taskDeclareLandPureApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
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

    }

}
