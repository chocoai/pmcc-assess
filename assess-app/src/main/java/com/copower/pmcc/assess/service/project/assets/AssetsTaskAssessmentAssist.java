package com.copower.pmcc.assess.service.project.assets;

import com.copower.pmcc.assess.common.enums.assets.AssetsCustomizeDataFieldTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
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
@WorkFlowAnnotation(desc = "资产-评定估算成果")
public class AssetsTaskAssessmentAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private AssetsCustomizeDataFieldService assetsCustomizeDataFieldService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskAssessmentIndex", "", 0, "0", "");
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskAssessmentApproval", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskAssessmentIndex", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskAssessmentApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    private void setParams(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {
        for (AssetsCustomizeDataFieldTypeEnum typeEnum : AssetsCustomizeDataFieldTypeEnum.values()) {
            modelAndView.addObject(StringUtils.uncapitalize(typeEnum.name()), assetsCustomizeDataFieldService.getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(projectPlanDetails.getId(),String.valueOf(typeEnum.getFileType())));
        }
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        if (projectPlanDetails.getProjectPhaseId() != null){
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId()) ;
            modelAndView.addObject(StringUtils.uncapitalize(ProjectPhase.class.getSimpleName()), projectPhase);
        }
    }
}
