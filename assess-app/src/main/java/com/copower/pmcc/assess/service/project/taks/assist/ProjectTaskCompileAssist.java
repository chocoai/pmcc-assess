package com.copower.pmcc.assess.service.project.taks.assist;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.dao.project.compile.CompileReportDao;
import com.copower.pmcc.assess.dal.dao.project.compile.CompileReportDetailsDao;
import com.copower.pmcc.assess.dal.dao.data.DataReportAnalysisFieldDao;
import com.copower.pmcc.assess.dal.entity.CompileReport;
import com.copower.pmcc.assess.dal.entity.CompileReportDetails;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.CompileReportApplyDto;
import com.copower.pmcc.assess.dto.output.project.CompileReportDetailsVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.assess.service.project.CompileReportService;
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
@WorkFlowAnnotation(desc = "报告编制成果")
public class ProjectTaskCompileAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;
    @Autowired
    private DataReportAnalysisFieldDao dataReportAnalysisFieldDao;
    @Autowired
    private CompileReportDao compileReportDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CompileReportDetailsDao compileReportDetailsDao;
    @Autowired
    private CompileReportService compileReportService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/compile/taskCompileIndex", "", 0, "0", "");
        Integer category = projectPlanDetails.getReportAnalysisCategory();
        compileReportService.initReportDetails(projectPlanDetails.getId());
        List<CompileReportDetailsVo> compileReportDetailsList = compileReportService.getCompileReportDetailsList(projectPlanDetails.getId(), category);
        modelAndView.addObject("compileReportDetailsList",compileReportDetailsList);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/compile/taskCompileApproval", processInsId, boxId, taskId, agentUserAccount);
        Integer category = projectPlanDetails.getReportAnalysisCategory();
        List<CompileReportDetailsVo> compileReportDetailsList = compileReportService.getCompileReportDetailsList(projectPlanDetails.getId(), category);
        modelAndView.addObject("compileReportDetailsList",compileReportDetailsList);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/compile/taskCompileIndex", processInsId, boxId, taskId, agentUserAccount);
        Integer category = projectPlanDetails.getReportAnalysisCategory();
        List<CompileReportDetailsVo> compileReportDetailsList = compileReportService.getCompileReportDetailsList(projectPlanDetails.getId(), category);
        modelAndView.addObject("compileReportDetailsList",compileReportDetailsList);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails,Integer boxId){
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/compile/taskCompileApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        CompileReport compileReport = new CompileReport();
        compileReport.setProjectId(projectPlanDetails.getProjectId());
        compileReport.setPlanDetailsId(projectPlanDetails.getId());
        compileReport.setProcessInsId(processInsId);
        compileReport.setCreator(commonService.thisUserAccount());
        compileReportDao.save(compileReport);   //保存主表

        CompileReportApplyDto compileReportApplyDto = JSON.parseObject(formData, CompileReportApplyDto.class);
        List<CompileReportDetails> compileReportDetailsList = compileReportApplyDto.getCompileReportDetailsList();
        if(CollectionUtils.isNotEmpty(compileReportDetailsList)){
            for (CompileReportDetails compileReportDetails : compileReportDetailsList) {
                compileReportService.saveReportDetails(compileReportDetails);
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
        List<CompileReportDetails> compileReportDetailsList = compileReportApplyDto.getCompileReportDetailsList();
        if(CollectionUtils.isNotEmpty(compileReportDetailsList)){
            for (CompileReportDetails compileReportDetails : compileReportDetailsList) {
                compileReportService.saveReportDetails(compileReportDetails);
            }
        }
    }
}
