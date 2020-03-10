package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.AssessmentTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
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
    private ChksRpcAssessmentService chksRpcAssessmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;

    @Override
    public void createAssessmentTask(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        if (boxRuDto == null) return;
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxRuDto.getBoxId());
        AssessmentProjectPerformanceDto dto = new AssessmentProjectPerformanceDto();
        dto.setProcessInsId(processInsId);
        dto.setAppKey(applicationConstant.getAppKey());
        if (projectInfo != null) {
            dto.setProjectId(projectInfo.getId());
            dto.setProjectName(projectInfo.getProjectName());
        }
        dto.setTaskId(taskId);
        dto.setBoxId(boxReDto.getId());
        BoxReActivityDto activityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
        if (activityDto.getBisSpotCheck() != null) {
            //当发现该节点是被抽查节点,那么写入抽查节点的节点id
            if (Objects.equal(activityDto.getBisSpotCheck(), Boolean.TRUE)) {
                BoxReActivityDto spotReActivityDto = chksAssessmentProjectPerformanceService.getSpotBoxReActivityDto(activityDto.getBoxId());
                dto.setSpotActivityId(spotReActivityDto.getId());
                //这里不考虑 获取的 BoxReActivityDto 是否存在boxId问题
            }
        }
        dto.setActivityId(activityId);
        dto.setActivityName(activityDto.getCnName());
        dto.setSorting(activityDto.getSortMultilevel());
        dto.setByExaminePeople(byExamineUser);
//        dto.setExaminePeople(commonService.thisUserAccount());
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
        dto.setBusinessKey("默认考核类型");
        chksRpcAssessmentService.saveAndUpdateAssessmentProjectPerformanceDto(dto, true);
    }
}
