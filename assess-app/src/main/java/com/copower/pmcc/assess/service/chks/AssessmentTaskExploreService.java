package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());
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
    public void createAssessmentPerformanceTask(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws Exception {
        if (activityId == null) {
            return;
        }
        BoxReActivityDto activityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(activityDto.getBoxId());
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId());
        if (basicApplyBatch == null) {
            return;
        }
        List<BasicApplyBatchDetail> basicApplyBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isEmpty(basicApplyBatchDetailList)) {
            return;
        }
        Iterator<BasicApplyBatchDetail> basicApplyBatchDetailIterator = basicApplyBatchDetailList.iterator();
        while (basicApplyBatchDetailIterator.hasNext()) {
            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailIterator.next();
            LinkedList<String> linkedList = Lists.newLinkedList();
            linkedList.add(String.join("", "/basicApplyBatch/informationDetail?", "applyBatchId=", basicApplyBatch.getId().toString()));
            linkedList.add(String.join("=", "planDetailsId", basicApplyBatch.getPlanDetailsId().toString()));
            linkedList.add(String.join("=", "formClassify", basicApplyBatch.getClassify().toString()));
            linkedList.add(String.join("=", "formType", basicApplyBatch.getType().toString()));
            linkedList.add(String.join("=", "tbId", basicApplyBatchDetail.getTableId().toString()));
            linkedList.add(String.join("=", "tbType", basicApplyBatchDetail.getType()));
            linkedList.add(String.join("=", "isHistory", Boolean.FALSE.toString()));
            String businessKey = basicApplyBatchDetailService.getFullNameByBatchDetailId(basicApplyBatchDetail.getId());
            saveAssessmentPerformanceDto(processInsId, activityId, taskId, byExamineUser, projectInfo, projectPlanDetails, boxReDto, basicApplyBatchDetail.getTableName(), basicApplyBatchDetail.getTableId(), basicApplyBatchDetail.getType(), StringUtils.join(linkedList, "&"), businessKey, null);
        }

        //添加工时考核任务
        List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemListByKey(boxReDto.getId(), activityId, AssessmentTypeEnum.WORK_HOURS.getValue());
        if(CollectionUtils.isEmpty(assessmentItemDtos)) return;//没有配置考核模板则不生成考核任务
        AssessmentPerformanceDto dto = new AssessmentPerformanceDto();
        dto.setProcessInsId(processInsId);
        dto.setAppKey(applicationConstant.getAppKey());
        if (projectInfo != null) {
            dto.setProjectId(projectInfo.getId());
            dto.setProjectName(projectInfo.getProjectName());
        }
        dto.setTaskId(taskId);
        dto.setBoxId(boxReDto.getId());
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
        dto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
        dto.setAssessmentKey(AssessmentTypeEnum.WORK_HOURS.getValue());
        dto.setBisEffective(true);
        dto.setCreator(commonService.thisUserAccount());
        performanceService.saveAndUpdatePerformanceDto(dto, true);
    }

    private void saveAssessmentPerformanceDto(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails, BoxReDto boxReDto, String tableName, Integer tableId, String assessmentKey, String examineUrl, String businessKey, Integer spotActivityId) {
        if (StringUtils.isNotBlank(assessmentKey)) {
            String[] strings = assessmentKey.split("\\.");
            assessmentKey = strings.length > 0 ? strings[0] : assessmentKey;
        }
        List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemListByKey(boxReDto.getId(), activityId, assessmentKey);
        if(CollectionUtils.isEmpty(assessmentItemDtos)) return;//没有配置考核模板则不生成考核任务
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
        dto.setBisEffective(true);
        dto.setCreator(commonService.thisUserAccount());
        dto.setSourceViewUrl(examineUrl);
        dto.setAssessmentType(AssessmentTypeEnum.QUALITY.getValue());
        dto.setAssessmentKey(assessmentKey);
        if (spotActivityId != null) {
            dto.setSpotActivityId(spotActivityId);
        }
        performanceService.saveAndUpdatePerformanceDto(dto, true);
    }
}
