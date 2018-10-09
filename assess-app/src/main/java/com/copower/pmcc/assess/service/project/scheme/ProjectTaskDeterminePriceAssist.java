package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaJudgeObjectVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
@WorkFlowAnnotation(desc = "确定单价")
public class ProjectTaskDeterminePriceAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeDeteminePriceService schemeDeteminePriceService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDeterminePriceIndex", "", 0, "0", "");
        List<SchemeAreaJudgeObjectVo> areaJudgeObjectVos = schemeDeteminePriceService.getAreaJudgeObjectList(projectPlanDetails.getProjectId());
        modelAndView.addObject("areaJudgeObjectVos", areaJudgeObjectVos);
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDeterminePriceApproval", processInsId, boxId, taskId, agentUserAccount);
        List<SchemeAreaJudgeObjectVo> areaJudgeObjectVos = schemeDeteminePriceService.getAreaJudgeObjectList(projectPlanDetails.getProjectId());
        modelAndView.addObject("areaJudgeObjectVos", areaJudgeObjectVos);
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDeterminePriceIndex", processInsId, boxId, taskId, agentUserAccount);
        List<SchemeAreaJudgeObjectVo> areaJudgeObjectVos = schemeDeteminePriceService.getAreaJudgeObjectList(projectPlanDetails.getProjectId());
        modelAndView.addObject("areaJudgeObjectVos", areaJudgeObjectVos);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDeterminePriceApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        List<SchemeAreaJudgeObjectVo> areaJudgeObjectVos = schemeDeteminePriceService.getAreaJudgeObjectList(projectPlanDetails.getProjectId());
        modelAndView.addObject("areaJudgeObjectVos", areaJudgeObjectVos);
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
