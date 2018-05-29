package com.copower.pmcc.assess.service.project.taks.assist.scheme;

import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationHypothesisDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationBasisVo;
import com.copower.pmcc.assess.dto.output.data.EvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.data.EvaluationPrincipleVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.data.EvaluationBasisService;
import com.copower.pmcc.assess.service.data.EvaluationHypothesisService;
import com.copower.pmcc.assess.service.data.EvaluationPrincipleService;
import com.copower.pmcc.assess.service.project.SchemeInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private EvaluationHypothesisService hypothesisService;
    @Autowired
    private EvaluationPrincipleService principleService;
    @Autowired
    private EvaluationBasisService basisService;

    @Autowired
    private SchemeInfoService schemeInfoService;


    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskIncomeIndex", "", 0, "0", "");
        List<EvaluationHypothesisDto> hypothesisDtos = hypothesisService.listN(null);
        List<EvaluationBasisDto> basisDtos = basisService.listN(null);
        List<EvaluationPrincipleDto> principleDtos = principleService.listN(null);
        modelAndView.addObject("hypothesisList",hypothesisDtos);
        modelAndView.addObject("principleList",principleDtos);
        modelAndView.addObject("basisList",basisDtos);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskIncomeApproval", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    /**
     * 返回修改
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskIncomeIndex", processInsId, boxId, taskId, agentUserAccount);
        List<EvaluationHypothesisVo> hypothesisDtos = hypothesisService.listNs(null);
        List<EvaluationBasisVo> basisVos = basisService.listNs(null);
        List<EvaluationPrincipleVo> principleVos = principleService.listNs(null);
        modelAndView.addObject("hypothesisList",hypothesisDtos);
        modelAndView.addObject("principleList",principleVos);
        modelAndView.addObject("basisList",basisVos);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {
        if (!StringUtils.isEmpty(formData)){
        }
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails,Integer boxId){
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskIncomeApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    /**
     * save
     * @param projectPlanDetails
     * @param processInsId
     * @param formData
     * @throws BusinessException
     */
    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        if (!StringUtils.isEmpty(formData)){
            schemeInfoService.saveChange(projectPlanDetails.getId(),processInsId,formData);
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        if (!StringUtils.isEmpty(formData)){
        }
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        if (!StringUtils.isEmpty(formData)){
        }
    }
}
