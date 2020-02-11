package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.AssessmentTaskInterface;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
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
    @Override
    public void createAssessmentTask(String processInsId, Integer activityId,String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
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
        dto.setActivityId(activityId);
        dto.setActivityName(activityDto.getCnName());
        dto.setSorting(activityDto.getSortMultilevel());
        dto.setByExaminePeople(byExamineUser);
        dto.setExaminePeople(commonService.thisUserAccount());
        dto.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
        if(projectPlanDetails!=null){
            dto.setPlanId(projectPlanDetails.getPlanId());
            dto.setPlanDetailsId(projectPlanDetails.getId());
        }
        dto.setCreator(commonService.thisUserAccount());
        dto.setValidScore(new BigDecimal(0));
        dto.setBusinessKey("默认考核类型");
        chksRpcAssessmentService.saveAndUpdateAssessmentProjectPerformanceDto(dto, true);
    }
}
