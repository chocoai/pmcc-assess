package com.copower.pmcc.assess.service.project.taks.assist;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.dao.CompileReportDao;
import com.copower.pmcc.assess.dal.dao.CompileReportDetailsDao;
import com.copower.pmcc.assess.dal.dao.DataReportAnalysisFieldDao;
import com.copower.pmcc.assess.dal.entity.CompileReport;
import com.copower.pmcc.assess.dal.entity.CompileReportDetails;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.data.DataReportAnalysisFieldService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
    private DataReportAnalysisFieldService dataReportAnalysisFieldService;
    @Autowired
    private DataReportAnalysisFieldDao dataReportAnalysisFieldDao;
    @Autowired
    private CompileReportDao compileReportDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CompileReportDetailsDao compileReportDetailsDao;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/compile/taskCompileIndex", "", 0, "0", "");
        Integer category = projectPlanDetails.getReportAnalysisId();
        List<DataReportAnalysisVo> dataReportAnalysisVos = dataReportAnalysisService.getDataReportAnalysisByCategory(category);
        List<DataReportAnalysisField> dataReportAnalysisFields = dataReportAnalysisFieldDao.getAllList();

        modelAndView.addObject("dataReportAnalysisFields",dataReportAnalysisFields);
        modelAndView.addObject("dataReportAnalysisVos",dataReportAnalysisVos);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/compile/taskCompileApproval", processInsId, boxId, taskId, agentUserAccount);
        List<CompileReport> compileReports = compileReportDao.getByProcessInsId(processInsId);
        CompileReport compileReport = compileReports.get(0);
        Integer pid = compileReport.getId();
        List<CompileReportDetails> compileReportDetailss = compileReportDetailsDao.getByPid(pid);
        modelAndView.addObject("compileReportDetailss",compileReportDetailss);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/compile/taskCompileIndex", processInsId, boxId, taskId, agentUserAccount);
        Integer category = projectPlanDetails.getReportAnalysisId();
        List<DataReportAnalysisVo> dataReportAnalysisVos = dataReportAnalysisService.getDataReportAnalysisByCategory(category);
        List<DataReportAnalysisField> dataReportAnalysisFields = dataReportAnalysisFieldDao.getAllList();

        modelAndView.addObject("dataReportAnalysisFields",dataReportAnalysisFields);
        modelAndView.addObject("dataReportAnalysisVos",dataReportAnalysisVos);
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
        String s = formData;
        CompileReport compileReport = new CompileReport();
        compileReport.setProjectId(projectPlanDetails.getProjectId());
        compileReport.setPlanDetailsId(projectPlanDetails.getPlanId());
        compileReport.setProcessInsId(processInsId);
        compileReport.setCreator(commonService.thisUserAccount());
        compileReportDao.save(compileReport);   //保存主表

        CompileReportDetails compileReportDetails = JSON.parseObject(formData, CompileReportDetails.class);
        compileReportDetails.setPid(compileReport.getId());
        compileReportDetails.setCreator(commonService.thisUserAccount());
        compileReportDetailsDao.save(compileReportDetails);     //保存从表

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        //返回提交走这里
    }
}
