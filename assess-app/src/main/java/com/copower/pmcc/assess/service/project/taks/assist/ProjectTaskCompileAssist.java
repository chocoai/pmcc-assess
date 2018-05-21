package com.copower.pmcc.assess.service.project.taks.assist;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.dao.DataReportAnalysisFieldDao;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.data.DataReportAnalysisFieldService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.assess.service.project.ProjectCheckContentService;
import com.copower.pmcc.assess.service.project.SurveyAssetInventoryService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Component
@WorkFlowAnnotation(desc = "报告编制成果")
public class ProjectTaskCompileAssist implements ProjectTaskInterface {
    @Autowired
    private ControllerComponent serviceComponent;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;
    @Autowired
    private DataReportAnalysisFieldService dataReportAnalysisFieldService;
    @Autowired
    private DataReportAnalysisFieldDao dataReportAnalysisFieldDao;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/compile/taskCompileIndex", "", 0, "0", "");
        Integer category = projectPlanDetails.getReportAnalysisId();
        List<DataReportAnalysisVo> dataReportAnalysisVos = dataReportAnalysisService.getDataReportAnalysisByCategory(category);

        List<DataReportAnalysisField> dataReportAnalysisFields = null;
        for(DataReportAnalysisVo dataReportAnalysisVo :dataReportAnalysisVos){
            Integer analysisId = dataReportAnalysisVo.getId();
            dataReportAnalysisFields = dataReportAnalysisFieldService.getDataReportAnalysisField(analysisId);

        }

        modelAndView.addObject("dataReportAnalysisFields",dataReportAnalysisFields);
        modelAndView.addObject("dataReportAnalysisVos",dataReportAnalysisVos);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/compile/taskCompileApproval", processInsId, boxId, taskId, agentUserAccount);

        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/compile/taskCompileIndex", processInsId, boxId, taskId, agentUserAccount);

        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails,Integer boxId){
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/compile/taskCompileApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
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
        //返回提交走这里
    }
}
