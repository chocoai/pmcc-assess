package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheck;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckScore;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectReviewScoreService;
import com.copower.pmcc.assess.service.project.ProjectSpotCheckService;
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
    private ProjectSpotCheckService projectSpotCheckService;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;
    @Autowired
    private ApplicationConstant applicationConstant;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        try {
            ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
            if (processStatusEnum == null) return;
            if (processStatusEnum.isFinish()) {
                //将抽查的工时得分写入到考核系统中，
                ProjectSpotCheck spotCheck = projectSpotCheckService.getSpotCheckByProcessInsId(processExecution.getProcessInstanceId());
                if (spotCheck == null) return;
                AssessmentPerformanceDto performanceDto = new AssessmentPerformanceDto();
                performanceDto.setAppKey(applicationConstant.getAppKey());
                performanceDto.setProcessInsId(processExecution.getProcessInstanceId());
                performanceDto.setByExaminePeople(spotCheck.getBySpotUser());
                performanceDto.setExaminePeople(spotCheck.getCreator());
                performanceDto.setExamineScore(spotCheck.getWorkHourScore());//需计算
                performanceDto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
                performanceDto.setBusinessKey("抽查考核工分");
                performanceDto.setExamineStatus(ProcessStatusEnum.FINISH.getValue());
                performanceDto.setBisEffective(true);
                performanceDto.setBisQualified(true);
                performanceService.saveAndUpdatePerformanceDto(performanceDto, false);

                performanceDto.setExamineScore(spotCheck.getQualityScore());//需计算
                performanceDto.setAssessmentType(AssessmentTypeEnum.QUALITY.getValue());
                performanceDto.setBusinessKey("抽查考核工分");
                performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "ProjectSpotCheckEvent");
        }
    }
}
