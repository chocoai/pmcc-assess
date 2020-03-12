package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssessmentCommonService {
    public final static String PROJECT_TASK_BUSINESS_KEY_PERFORMANCE = "assessment.performance";
    public final static String PROJECT_TASK_BUSINESS_KEY_WORK_HOURS = "assessment.work.hours";
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;
    @Autowired
    private AssessmentWorkHoursService assessmentWorkHoursService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private ApplicationConstant applicationConstant;


    /**
     * 进入审批页面时，生成质量考核任务，工时考核任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void generateAssessmentTask(String processInsId, Integer boxId, String taskId, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws BpmException {
        BootstrapTableVo tableVo = bpmRpcProcessInsManagerService.getApprovalLogForApp(applicationConstant.getAppKey(), processInsId, 0, 1000);
        List<BoxApprovalLogVo> rows = tableVo.getRows();
        String byExamineUser = rows.get(0).getCreator();
        chksAssessmentProjectPerformanceService.generateAssessmentTask(processInsId, boxId, taskId, byExamineUser, projectInfo, projectPlanDetails);
        assessmentWorkHoursService.generateWorkHoursTask(processInsId, boxId, taskId, byExamineUser, projectInfo, projectPlanDetails);
    }

    /**
     * 提交审批流程时创建或清除待提交任务，处理质量考核任务，工时考核任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void createProjectTask(ApprovalModelDto approvalModelDto, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
        chksAssessmentProjectPerformanceService.createAssessmentProjectTask(approvalModelDto,projectInfo,projectPlanDetails);
        assessmentWorkHoursService.createAssessmentProjectTask(approvalModelDto,projectInfo,projectPlanDetails);
    }
}
