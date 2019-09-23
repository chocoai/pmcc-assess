package com.copower.pmcc.assess.service.project.assets;

import com.copower.pmcc.assess.common.enums.assets.AssetsCustomizeDataFieldTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
@WorkFlowAnnotation(desc = "资产-申报成果")
public class AssetsTaskDeclareAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private AssetsCustomizeDataFieldService assetsCustomizeDataFieldService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskDeclareIndex", "", 0, "0", "");
        setParams(modelAndView, projectPlanDetails);
        setInitParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskDeclareApproval", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskDeclareIndex", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, projectPlanDetails);
        setInitParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assets/taskDeclareApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
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
            modelAndView.addObject(StringUtils.uncapitalize(typeEnum.name()), typeEnum.getFileType());
        }
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        if (projectPlanDetails.getProjectPhaseId() != null){
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId()) ;
            modelAndView.addObject(StringUtils.uncapitalize(ProjectPhase.class.getSimpleName()), projectPhase);
        }
    }

    /**
     * 初始化参数
     * @param modelAndView
     * @param projectPlanDetails
     */
    private void setInitParams(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {
        AssetsCustomizeDataField customizeDataField = new AssetsCustomizeDataField();
        customizeDataField.setType(String.valueOf(AssetsCustomizeDataFieldTypeEnum.CustomizeDataField.getFileType()));
        customizeDataField.setPlanDetailsId(projectPlanDetails.getId());
        customizeDataField.setProjectId(projectPlanDetails.getProjectId());
        List<AssetsCustomizeDataField> assetsCustomizeDataFieldList = assetsCustomizeDataFieldService.getDataAssetsAppraisalDicListByExample(customizeDataField);
        if (CollectionUtils.isNotEmpty(assetsCustomizeDataFieldList)) {
            modelAndView.addObject(StringUtils.uncapitalize(AssetsCustomizeDataField.class.getSimpleName()), assetsCustomizeDataFieldList.stream().findFirst().get());
        } else {
            assetsCustomizeDataFieldService.saveDataAssetsAppraisalDic(customizeDataField);
            modelAndView.addObject(StringUtils.uncapitalize(AssetsCustomizeDataField.class.getSimpleName()), customizeDataField);
        }

    }

}
