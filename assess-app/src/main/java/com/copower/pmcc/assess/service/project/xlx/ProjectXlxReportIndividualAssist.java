package com.copower.pmcc.assess.service.project.xlx;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividual;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.assets.AssetsCustomizeDataFieldService;
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
 * @data: 2020/03/20
 * @time: 09:45
 */
@Component
@WorkFlowAnnotation(desc = "报告信息【兴良信】")
public class ProjectXlxReportIndividualAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectXlxReportIndividualService projectXlxReportIndividualService;
    @Autowired
    private AssetsCustomizeDataFieldService assetsCustomizeDataFieldService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectXlxPigeonholeService projectXlxPigeonholeService;

    private final String applyViewName = "/customer/xlx/reportIndividual/taskProjectXlxReportIndividualIndex";
    private final String approvalViewName = "/customer/xlx/reportIndividual/taskProjectXlxReportIndividualApproval";

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(applyViewName, "", 0, "0", "");
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(approvalViewName, processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(applyViewName, processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(approvalViewName, projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setParams(modelAndView, projectPlanDetails);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        ProjectXlxReportIndividual oo = JSONObject.parseObject(formData, ProjectXlxReportIndividual.class);
        if (StringUtils.isNotBlank(processInsId)){
            oo.setProcessInsId(processInsId);
        }
        projectXlxReportIndividualService.saveAndUpdateProjectXlxReportIndividual(oo, true);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        ProjectXlxReportIndividual oo = JSONObject.parseObject(formData, ProjectXlxReportIndividual.class);
        if (StringUtils.isNotBlank(processInsId)){
            oo.setProcessInsId(processInsId);
        }
        projectXlxReportIndividualService.saveAndUpdateProjectXlxReportIndividual(oo, true);
    }

    private void setParams(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {
        assetsCustomizeDataFieldService.setParams(modelAndView, projectPlanDetails);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectXlxReportIndividual.class.getSimpleName()), projectXlxReportIndividualService.getProjectXlxReportIndividualByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("assessProjectType", projectXlxReportIndividualService.getAssessProjectTypeEnum(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()).getProjectCategoryId()).getKey());
        modelAndView.addObject("pigeonholeDate",projectXlxPigeonholeService.getPigeonholeDateByProjectId(projectPlanDetails.getProjectId()));
    }
}
