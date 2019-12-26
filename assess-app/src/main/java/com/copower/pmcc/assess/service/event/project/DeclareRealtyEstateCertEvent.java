package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.declare.DeclareApplyService;
import com.copower.pmcc.assess.service.project.survey.ProjectPlanSurveyService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-9-29
 * 监听 在建工程和设备安装以及房产证 土地证 以及 不动产证
 */
@Component
public class DeclareRealtyEstateCertEvent extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareApplyService declareApplyService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanSurveyService projectPlanSurveyService;
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        DeclareApply declareApply = declareApplyService.getDeclareApplyByProcessInsId(processExecution.getProcessInstanceId());
        if (declareApply != null) {
            declareApplyService.writeToDeclareRecord(declareApply);
        }
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(declareApply.getPlanDetailsId());
        if (projectPlanDetails != null && projectPlanDetails.getBisRestart() == Boolean.TRUE) {
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
            if (projectPlan != null)
                projectPlanSurveyService.appendSurveyPlanDetails(projectPlan.getProjectId(), projectPlan.getStageSort());
        }
        super.processFinishExecute(processExecution);//数据写入record记录表中后再执行进入下阶段

        if (projectPlanDetails != null && projectPlanDetails.getId() != null) {
        }
    }
}
