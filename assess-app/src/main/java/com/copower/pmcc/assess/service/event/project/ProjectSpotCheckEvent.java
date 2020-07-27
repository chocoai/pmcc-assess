package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectReviewScoreService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectSpotCheckEvent extends BaseProcessEvent {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectReviewScoreService projectReviewScoreService;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectMemberService projectMemberService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        try {
            ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
            if (processStatusEnum == null) return;
            if (processStatusEnum.isFinish()) {
                //将项目经理的工时得分写入到考核系统中，
                ProjectReviewScore reviewScore = projectReviewScoreService.getReviewScoreByProcessInsId(processExecution.getProcessInstanceId());
                if (reviewScore == null) return;
                ProjectReviewScoreItem reviewScoreItem = projectReviewScoreService.getEnableReviewScoreItemsByMasterId(reviewScore.getId());
                if (reviewScoreItem == null) return;
                AssessmentPerformanceDto performanceDto = new AssessmentPerformanceDto();
                performanceDto.setProjectId(reviewScore.getProjectId());
                performanceDto.setAppKey(applicationConstant.getAppKey());
                performanceDto.setProjectName(reviewScore.getProjectName());
                performanceDto.setProcessInsId(processExecution.getProcessInstanceId());
                performanceDto.setByExaminePeople(projectMemberService.getProjectManager(reviewScore.getProjectId()));
                performanceDto.setExaminePeople(reviewScoreItem.getCreator());
                performanceDto.setExamineScore(reviewScoreItem.getTotalScore());
                performanceDto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
                performanceDto.setBusinessKey("项目经理复核工分");
                performanceDto.setExamineStatus(ProcessStatusEnum.FINISH.getValue());
                performanceDto.setBisEffective(true);
                performanceDto.setBisQualified(true);
                performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "ProjectAssessmentBonusEvent");
        }
    }
}
