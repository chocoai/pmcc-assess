package com.copower.pmcc.assess.service.project.taks.assist.scheme;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationHypothesisDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.data.EvaluationBasisService;
import com.copower.pmcc.assess.service.data.EvaluationHypothesisService;
import com.copower.pmcc.assess.service.data.EvaluationPrincipleService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
@WorkFlowAnnotation(desc = "收益法成果")
public class ProjectTaskIncomeAssist implements ProjectTaskInterface {
    @Autowired
    private ControllerComponent serviceComponent;
    @Autowired
    private EvaluationHypothesisService hypothesisService;
    @Autowired
    private EvaluationPrincipleService principleService;
    @Autowired
    private EvaluationBasisService basisService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        List<EvaluationHypothesisDto> hypothesisDtos = hypothesisService.listN(null);
        List<EvaluationBasisDto> basisDtos = basisService.listN(null);
        List<EvaluationPrincipleDto> principleDtos = principleService.listN(null);
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/scheme/taskIncomeIndex", "", 0, "0", "");
        modelAndView.addObject("hypothesisList",hypothesisDtos);
        modelAndView.addObject("principleList",principleDtos);
        modelAndView.addObject("basisList",basisDtos);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/scheme/taskIncomeApproval", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/scheme/taskIncomeIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails,Integer boxId){
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/scheme/taskIncomeApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
