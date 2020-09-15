package com.copower.pmcc.assess.service.project.compile;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.compile.CompileReportDao;
import com.copower.pmcc.assess.dal.basis.entity.CompileReport;
import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetail;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.input.project.compile.CompileReportApplyDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Component
@WorkFlowAnnotation(desc = "市场背景描述与分析")
public class ProjectTaskCompileAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CompileReportDao compileReportDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CompileReportService compileReportService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageCompile/taskCompileIndex", "", 0, "0", "");
        CompileReport compileReport = compileReportService.getCompileReportByPlanDetailsId(projectPlanDetails.getId());
        if (compileReport == null) {
            compileReport = new CompileReport();
            compileReport.setProjectId(projectPlanDetails.getProjectId());
            compileReport.setPlanDetailsId(projectPlanDetails.getId());
            compileReport.setCreator(commonService.thisUserAccount());
            compileReportDao.addCompileReport(compileReport);
        }
        List<SchemeAreaGroup> areaGroupList = schemeAreaGroupService.getAreaGroupEnableByProjectId(projectPlanDetails.getProjectId());
        modelAndView.addObject("areaGroupList", areaGroupList);
        compileReportService.initReportDetail(projectPlanDetails);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageCompile/taskCompileApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageCompile/taskCompileIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageCompile/taskCompileApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        List<CompileReportDetail> compileReportDetailsList = compileReportService.getReportDetailList(projectPlanDetails.getId());
        modelAndView.addObject("compileReportDetailsJSON", JSON.toJSONString(compileReportDetailsList));
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        CompileReport compileReport = compileReportService.getCompileReportByPlanDetailsId(projectPlanDetails.getId());
        compileReport.setProcessInsId(processInsId);
        compileReportDao.updateCompileReport(compileReport);   //保存主表

        CompileReportApplyDto compileReportApplyDto = JSON.parseObject(formData, CompileReportApplyDto.class);
        List<CompileReportDetail> compileReportDetailList = compileReportApplyDto.getCompileReportDetailList();
        if (CollectionUtils.isNotEmpty(compileReportDetailList)) {
            for (CompileReportDetail compileReportDetail : compileReportDetailList) {
                compileReportService.saveReportDetail(compileReportDetail);
            }
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        //返回提交走这里
        CompileReportApplyDto compileReportApplyDto = JSON.parseObject(formData, CompileReportApplyDto.class);
        List<CompileReportDetail> compileReportDetailList = compileReportApplyDto.getCompileReportDetailList();
        if (CollectionUtils.isNotEmpty(compileReportDetailList)) {
            for (CompileReportDetail compileReportDetail : compileReportDetailList) {
                compileReportService.saveReportDetail(compileReportDetail);
            }
        }
    }
}
