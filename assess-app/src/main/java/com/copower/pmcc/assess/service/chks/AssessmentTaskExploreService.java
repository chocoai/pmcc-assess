package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zch on 2020-2-7.
 * 生成查勘考核任务
 */
@Component(value = "assessmentTaskExploreService")
public class AssessmentTaskExploreService implements AssessmentTaskInterface {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    public void createAssessmentTask(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
        if (activityId == null) {
            return;
        }
        BoxReActivityDto activityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(activityDto.getBoxId());

        BasicApplyBatch basicApplyBatch = null;
        try {
            basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
        } catch (Exception e) {

        }
        if (basicApplyBatch == null) {
            try {
                basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId());
            } catch (Exception ex) {
            }
        }
        if (basicApplyBatch == null) {
            return;
        }
        List<BasicApplyBatchDetail> basicApplyBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isEmpty(basicApplyBatchDetailList)) {
            return;
        }
        Integer tempId = basicApplyBatch.getEstateId();
        if (!basicApplyBatchDetailList.stream().anyMatch(basicApplyBatchDetail -> Objects.equal(basicApplyBatchDetail.getTableId(), tempId))) {
            BasicApplyBatchDetail batchDetail = new BasicApplyBatchDetail();
            batchDetail.setTableId(basicApplyBatch.getEstateId());
            batchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
            basicApplyBatchDetailList.add(batchDetail);
        }
        Iterator<BasicApplyBatchDetail> basicApplyBatchDetailIterator = basicApplyBatchDetailList.iterator();
        while (basicApplyBatchDetailIterator.hasNext()) {
            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailIterator.next();
            LinkedList<String> linkedList = Lists.newLinkedList();
            linkedList.add(String.join("", "/basicApplyBatch/informationDetail?", "applyBatchId=", basicApplyBatch.getId().toString()));
            linkedList.add(String.join("=", "planDetailsId", basicApplyBatch.getPlanDetailsId().toString()));
            linkedList.add(String.join("=", "formClassify", basicApplyBatch.getClassify().toString()));
            linkedList.add(String.join("=", "formType", basicApplyBatch.getType().toString()));
            linkedList.add(String.join("=", "tableId", basicApplyBatchDetail.getTableId().toString()));
            linkedList.add(String.join("=", "tableName", basicApplyBatchDetail.getTableName()));
            linkedList.add(String.join("=", "isHistory", Boolean.FALSE.toString()));
            String tbType = "";
            String businessKey = basicApplyBatchDetailService.getFullNameByBatchDetailId(basicApplyBatchDetail.getId());
            linkedList.add(String.join("=", "tbType", tbType));
            saveAssessmentProjectPerformanceDto(processInsId, activityId, taskId, byExamineUser, projectInfo, projectPlanDetails, boxReDto, basicApplyBatchDetail.getTableName(), basicApplyBatchDetail.getTableId(), tbType, StringUtils.join(linkedList, "&"), businessKey, null);
        }
    }

    private void saveAssessmentProjectPerformanceDto(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails, BoxReDto boxReDto, String tableName, Integer tableId, String assessmentKey, String examineUrl, String businessKey, Integer spotActivityId) {
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
            dto.setSorting(activityDto.getSortMultilevel());
            dto.setActivityName(activityDto.getCnName());
            dto.setBusinessKey(activityDto.getCnName() + "/" + businessKey);
        }
        dto.setByExaminePeople(byExamineUser);
        dto.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
        dto.setTableId(tableId);
        dto.setTableName(tableName);
        if (projectPlanDetails != null) {
            dto.setPlanId(projectPlanDetails.getPlanId());
            dto.setPlanDetailsId(projectPlanDetails.getId());
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
            if (projectPlan != null && StringUtils.isNotBlank(projectPlan.getPlanName())) {
                dto.setPlanName(String.join("-", projectPlan.getPlanName(), projectPlanDetails.getProjectPhaseName()));
            } else {
                dto.setPlanName(projectPlanDetails.getProjectPhaseName());
            }
        }
        dto.setCreator(commonService.thisUserAccount());
        dto.setValidScore(new BigDecimal(0));
        dto.setExamineUrl(examineUrl);
        dto.setAssessmentKey(assessmentKey);
        dto.setBusinessKey(businessKey);
        if (spotActivityId != null) {
            dto.setSpotActivityId(spotActivityId);
        }
        Integer id = performanceService.saveAndUpdatePerformanceDto(dto, true);
        if (id != null) {
            examineUrl = String.join("", examineUrl, "&", "assessmentPerformanceId=", id.toString());
            dto.setExamineUrl(examineUrl);
            dto.setId(id);
            performanceService.saveAndUpdatePerformanceDto(dto, false);
        }
    }
}
