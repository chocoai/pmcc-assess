package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberDetail;
import com.copower.pmcc.assess.dto.output.project.ProjectTakeNumberVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.ProjectTakeNumberDetailService;
import com.copower.pmcc.assess.service.ProjectTakeNumberService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: zch
 * @data: 2020/1/10
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "项目文号拿取")
public class ProjectTakeWordNumberAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectTakeNumberService projectTakeNumberService;
    @Autowired
    private ProjectTakeNumberDetailService projectTakeNumberDetailService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/takeNumber/taskWordNumberIndex", "", 0, "0", "");
        projectTakeNumberService.initProjectTakeNumber(projectPlanDetails);
        setParams(modelAndView, projectPlanDetails, true);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/takeNumber/taskWordNumberApproval", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, projectPlanDetails, false);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/takeNumber/taskWordNumberIndex", processInsId, boxId, taskId, agentUserAccount);
        projectTakeNumberService.initProjectTakeNumber(projectPlanDetails);
        setParams(modelAndView, projectPlanDetails, true);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/takeNumber/taskWordNumberApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setParams(modelAndView, projectPlanDetails, false);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        projectTakeNumberService.applyCommit(formData);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        projectTakeNumberService.applyCommit(formData);
    }

    private void setParams(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails, boolean init) {
        if (projectPlanDetails != null) {
            modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        }
        modelAndView.addObject("qualificationTypes", GenerateReportInfoService.getQualificationTypes());
        ProjectTakeNumberVo projectTakeNumberVo = getProjectTakeNumberVo(projectPlanDetails);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectTakeNumber.class.getSimpleName()), projectTakeNumberVo);
        List<Integer> takeNumberDetailIdLists = new ArrayList<>(1);
        List<ProjectTakeNumberDetail> projectTakeNumberDetailList = projectTakeNumberDetailService.getProjectTakeNumberDetailListByMasterId(projectTakeNumberVo.getId());
        if (CollectionUtils.isNotEmpty(projectTakeNumberDetailList)) {
            projectTakeNumberDetailList.forEach(projectTakeNumberDetail -> takeNumberDetailIdLists.add(projectTakeNumberDetail.getId()));
            if (!init){
                modelAndView.addObject("projectTakeNumberDetailList", projectTakeNumberDetailList);
            }
        }
        modelAndView.addObject("takeNumberDetailIdList", StringUtils.join(takeNumberDetailIdLists,","));
    }

    private ProjectTakeNumberVo getProjectTakeNumberVo(ProjectPlanDetails projectPlanDetails) {
        ProjectTakeNumber query = new ProjectTakeNumber();
        query.setPlanDetailsId(projectPlanDetails.getId());
        List<ProjectTakeNumber> projectTakeNumberList = projectTakeNumberService.getProjectTakeNumberListQuery(query);
        if (CollectionUtils.isNotEmpty(projectTakeNumberList)) {
            return projectTakeNumberService.getProjectTakeNumberVo(projectTakeNumberList.get(0));
        }
        return null;
    }

}
