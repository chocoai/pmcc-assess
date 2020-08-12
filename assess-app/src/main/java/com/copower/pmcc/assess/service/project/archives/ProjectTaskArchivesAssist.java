package com.copower.pmcc.assess.service.project.archives;

import com.copower.pmcc.assess.common.enums.archives.ArchivesFileTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangpc on 2019-10-22.
 */
@Component
@WorkFlowAnnotation(desc = "项目归档")
public class ProjectTaskArchivesAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageArchives/taskArchivesIndex", "", 0, "-1", "");
        setModelParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageArchives/taskArchivesApproval", processInsId, boxId, taskId, agentUserAccount);
        setModelParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }


    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageArchives/taskArchivesIndex", processInsId, boxId, taskId, agentUserAccount);
        setModelParam(projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId), modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageArchives/taskArchivesApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setModelParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

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

    private void setModelParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        modelAndView.addObject("projectPlanDetails",projectPlanDetails) ;
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(ArchivesFileTypeEnum.values().length) ;
        for (ArchivesFileTypeEnum fileTypeEnum:ArchivesFileTypeEnum.values()){
            keyValueDtoList.add(new KeyValueDto(fileTypeEnum.getKey(),fileTypeEnum.getName())) ;
        }
        modelAndView.addObject("ArchivesFileTypeList",keyValueDtoList) ;
    }


}
