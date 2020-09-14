package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicDataHandleEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDetailDto;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zch on 2020-2-7.
 * 生成查勘[案例]考核任务
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

    private final String WORK_HOURS_ESTATE = "work.hours.estate";
    private final String WORK_HOURS_BUILDING = "work.hours.building";
    private final String WORK_HOURS_UNIT = "work.hours.unit";
    private final String WORK_HOURS_HOUSE = "work.hours.house";

    @Override
    public void createAssessmentPerformanceTask(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws Exception {
        if (activityId == null) {
            return;
        }
        BoxReActivityDto activityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(activityDto.getBoxId());
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId());
        if (basicApplyBatch == null) return;
        List<BasicApplyBatchDetail> basicApplyBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailsByPlanDetailsId(projectPlanDetails.getId());
        //过滤处理，去掉不应该生成的数据，引用数据不生成，同项目中相同数据不生成
        basicApplyBatchDetailList = LangUtils.filter(basicApplyBatchDetailList, o -> {
            if (BasicDataHandleEnum.REFERENCE.getKey().equalsIgnoreCase(o.getModifyType())) {
                return false;
            } else if (BasicDataHandleEnum.SAME.getKey().equalsIgnoreCase(o.getModifyType())) {
                return false;
            }
            return true;
        });
        if (CollectionUtils.isEmpty(basicApplyBatchDetailList)) return;
        //只取本次申请的
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
        Map<String, String> workHoursMap = Maps.newHashMap();
        List<String> transform = LangUtils.transform(basicApplyBatchDetailList, o -> o.getTableName());
        if (transform.contains(BasicFormClassifyEnum.ESTATE.getTableName()))
            workHoursMap.put(WORK_HOURS_ESTATE, "楼盘信息");
        if (transform.contains(BasicFormClassifyEnum.BUILDING.getTableName()))
            workHoursMap.put(WORK_HOURS_BUILDING, "楼栋信息");
        if (transform.contains(BasicFormClassifyEnum.UNIT.getTableName()))
            workHoursMap.put(WORK_HOURS_UNIT, "单元信息");
        if (transform.contains(BasicFormClassifyEnum.HOUSE.getTableName()))
            workHoursMap.put(WORK_HOURS_HOUSE, "房屋信息");
        for (Map.Entry<String, String> entry : workHoursMap.entrySet()) {
            List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemListByKey(boxReDto.getId(), activityId, entry.getKey());
            if (CollectionUtils.isEmpty(assessmentItemDtos)) return;//没有配置考核模板则不生成考核任务
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
                dto.setBusinessKey(activityDto.getCnName() + "/" + entry.getValue());
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
                dto.setProjectPhaseId(projectPlanDetails.getProjectPhaseId());
            }
            dto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
            dto.setAssessmentKey(entry.getKey());
            dto.setBisEffective(true);
            dto.setCreator(commonService.thisUserAccount());
            Integer performanceId = performanceService.saveAndUpdatePerformanceDto(dto, true);

            //明细项的考核内容同时生成出来，并后台计算一次工时得分
            for (AssessmentItemDto assessmentItemDto : assessmentItemDtos) {
                AssessmentPerformanceDetailDto detailDto = new AssessmentPerformanceDetailDto();
                detailDto.setContent(assessmentItemDto.getAssessmentDes());
                detailDto.setContentId(assessmentItemDto.getId());
                detailDto.setPerformanceId(performanceId);
                detailDto.setMinScore(assessmentItemDto.getMinScore());
                detailDto.setMaxScore(assessmentItemDto.getMaxScore());
                detailDto.setStandardScore(assessmentItemDto.getStandardScore());
                switch (entry.getKey()) {
                    case WORK_HOURS_ESTATE://楼盘默认取标准分
                        detailDto.setActualScore(assessmentItemDto.getStandardScore());
                        break;
                    case WORK_HOURS_BUILDING://楼栋根据本次填写的数量计算分数
                        List<BasicApplyBatchDetail> filterBuilding = LangUtils.filter(basicApplyBatchDetailList, o ->
                                o.getType().equalsIgnoreCase(BasicFormClassifyEnum.BUILDING.getKey()) ||
                                        o.getType().equalsIgnoreCase(BasicFormClassifyEnum.BUILDING_BASE.getKey()));
                        if (CollectionUtils.isNotEmpty(filterBuilding)) {
                            BigDecimal actualScoreBuilding = assessmentItemDto.getStandardScore().multiply(new BigDecimal("1").add(new BigDecimal("0.1").multiply(new BigDecimal(filterBuilding.size() - 1))));
                            actualScoreBuilding = actualScoreBuilding.compareTo(assessmentItemDto.getMaxScore()) > 0 ? assessmentItemDto.getMaxScore() : actualScoreBuilding;
                            detailDto.setActualScore(actualScoreBuilding);
                        }
                        break;
                    case WORK_HOURS_UNIT://单元根据本次填写的数量计算分数
                        List<BasicApplyBatchDetail> filterUnit = LangUtils.filter(basicApplyBatchDetailList, o -> o.getTableName().equalsIgnoreCase(BasicFormClassifyEnum.UNIT.getTableName()));
                        if (CollectionUtils.isNotEmpty(filterUnit)) {
                            BigDecimal actualScoreUnit = assessmentItemDto.getStandardScore().multiply(new BigDecimal("1").add(new BigDecimal("0.1").multiply(new BigDecimal(filterUnit.size() - 1))));
                            actualScoreUnit = actualScoreUnit.compareTo(assessmentItemDto.getMaxScore()) > 0 ? assessmentItemDto.getMaxScore() : actualScoreUnit;
                            detailDto.setActualScore(actualScoreUnit);
                        }
                        break;
                    case WORK_HOURS_HOUSE://房屋根据本次填写的数量计算分数
                        List<BasicApplyBatchDetail> filterHouse = LangUtils.filter(basicApplyBatchDetailList, o -> o.getTableName().equalsIgnoreCase(BasicFormClassifyEnum.HOUSE.getTableName()));
                        if (CollectionUtils.isNotEmpty(filterHouse)) {
                            BigDecimal actualScoreHouse = assessmentItemDto.getStandardScore().multiply(new BigDecimal("1").add(new BigDecimal("0.1").multiply(new BigDecimal(filterHouse.size() - 1))));
                            actualScoreHouse = actualScoreHouse.compareTo(assessmentItemDto.getMaxScore()) > 0 ? assessmentItemDto.getMaxScore() : actualScoreHouse;
                            detailDto.setActualScore(actualScoreHouse);
                        }
                        break;
                }
                performanceService.savePerformanceDetailDto(detailDto);
            }
        }
    }

    private void saveAssessmentPerformanceDto(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails, BoxReDto boxReDto, String tableName, Integer tableId, String assessmentKey, String examineUrl, String businessKey, Integer spotActivityId) {
        if (StringUtils.isNotBlank(assessmentKey)) {
            String[] strings = assessmentKey.split("\\.");
            assessmentKey = strings.length > 0 ? strings[0] : assessmentKey;
        }
        List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemListByKey(boxReDto.getId(), activityId, assessmentKey);
        if (CollectionUtils.isEmpty(assessmentItemDtos)) return;//没有配置考核模板则不生成考核任务
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
            dto.setProjectPhaseId(projectPlanDetails.getProjectPhaseId());
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
