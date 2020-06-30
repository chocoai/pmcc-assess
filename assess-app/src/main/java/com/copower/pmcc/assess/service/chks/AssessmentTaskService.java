package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 生成考核任务默认类
 * Created by wangpc on 2020/2/4.
 */
@Component
public class AssessmentTaskService implements AssessmentTaskInterface {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    public void createAssessmentTask(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        if (boxRuDto == null) return;
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxRuDto.getBoxId());
        AssessmentPerformanceDto dto = new AssessmentPerformanceDto();
        dto.setProcessInsId(processInsId);
        dto.setAppKey(applicationConstant.getAppKey());
        if (projectInfo != null) {
            dto.setProjectId(projectInfo.getId());
            dto.setProjectName(projectInfo.getProjectName());
        }
        dto.setTaskId(taskId);
        dto.setBoxId(boxReDto.getId());
        BoxReActivityDto activityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
        dto.setActivityId(activityId);
        if (activityDto != null) {
            dto.setReActivityName(activityDto.getName());
            dto.setActivityName(activityDto.getCnName());
            dto.setSorting(activityDto.getSortMultilevel());
            dto.setBusinessKey(activityDto.getCnName());
        }
        dto.setByExaminePeople(byExamineUser);
        dto.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
        if (projectPlanDetails != null) {
            dto.setPlanId(projectPlanDetails.getPlanId());
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
            if (projectPlan != null && StringUtils.isNotBlank(projectPlan.getPlanName())) {
                dto.setPlanName(String.join("-", projectPlan.getPlanName(), projectPlanDetails.getProjectPhaseName()));
            } else {
                dto.setPlanName(projectPlanDetails.getProjectPhaseName());
            }
            dto.setPlanDetailsId(projectPlanDetails.getId());
        }
        dto.setCreator(commonService.thisUserAccount());
        dto.setValidScore(new BigDecimal(0));
        performanceService.saveAndUpdatePerformanceDto(dto, true);
    }
}
