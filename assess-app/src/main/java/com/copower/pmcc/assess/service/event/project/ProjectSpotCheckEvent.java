package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectPlanCount;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectPlanDetailCount;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        try {
            ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
            if (processStatusEnum == null) return;
            if (processStatusEnum.isFinish()) {
                //将抽查的工时得分写入到考核系统中
                ProjectSpotCheck spotCheck = projectSpotCheckService.getSpotCheckByProcessInsId(processExecution.getProcessInstanceId());
                if (spotCheck == null) return;

                //先将本月上次抽查计算的分数设置为无效
                AssessmentPerformanceDto assessmentPerformanceDto = new AssessmentPerformanceDto();
                assessmentPerformanceDto.setAssessmentKey("SpotAssessment");
                assessmentPerformanceDto.setByExaminePeople(spotCheck.getBySpotUser());
                assessmentPerformanceDto.setBisEffective(true);
                Date startDate = DateUtils.convertDate(spotCheck.getSpotMonth() + "-01");
                Date endDate = DateUtils.convertDate(DateUtils.getLastDayOfMonth(DateUtils.getYear(startDate), DateUtils.getMonth(startDate)));
                List<AssessmentPerformanceDto> performances = performanceService.getPerformancesByExamineDate(assessmentPerformanceDto, startDate, endDate);
                if (CollectionUtils.isNotEmpty(performances)) {
                    for (AssessmentPerformanceDto performance : performances) {
                        performance.setBisEffective(false);
                        performanceService.updatePerformanceDto(performance,false);
                    }
                }
                List<ProjectSpotCheck> spotChecks = projectSpotCheckService.getFinishSpotCheckListByMonth(spotCheck.getBySpotUser(), spotCheck.getSpotMonth());
                AssessmentPerformanceDto performanceDto = new AssessmentPerformanceDto();
                performanceDto.setAppKey(applicationConstant.getAppKey());
                performanceDto.setProcessInsId(processExecution.getProcessInstanceId());
                performanceDto.setByExaminePeople(spotCheck.getBySpotUser());
                performanceDto.setExaminePeople(spotCheck.getCreator());
                performanceDto.setExamineScore(getWorkHoursScore(spotChecks));//需计算
                performanceDto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
                performanceDto.setAssessmentKey("SpotAssessment");
                performanceDto.setBusinessKey("抽查考核工分");
                performanceDto.setExamineStatus(ProcessStatusEnum.FINISH.getValue());
                performanceDto.setBisEffective(true);
                performanceDto.setBisQualified(true);
                performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
                performanceDto.setExamineScore(getQualityScore(spotChecks));//需计算
                performanceDto.setAssessmentType(AssessmentTypeEnum.QUALITY.getValue());
                performanceDto.setBusinessKey("抽查考核工分");
                performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "ProjectSpotCheckEvent");
        }
    }

    /**
     * 计算本月工时得分
     *
     * @param spotChecks 本月抽查批次
     * @return
     */
    private BigDecimal getWorkHoursScore(List<ProjectSpotCheck> spotChecks) {
        if (CollectionUtils.isEmpty(spotChecks)) return BigDecimal.ZERO;
        List<ProjectSpotCheckItem> spotCheckItemAll = Lists.newArrayList();
        for (ProjectSpotCheck spotCheck : spotChecks) {
            List<ProjectSpotCheckItem> spotCheckItems = projectSpotCheckService.getProjectSpotCheckItemsBySpotId(spotCheck.getId());
            if (CollectionUtils.isNotEmpty(spotCheckItems))
                spotCheckItemAll.addAll(spotCheckItems);
        }
        List<ProjectSpotCheckItemGroup> itemGroupList = projectSpotCheckService.getProjectSpotCheckItemGroupList(LangUtils.transform(spotCheckItemAll, o -> o.getId()));
        if (CollectionUtils.isEmpty(itemGroupList)) return BigDecimal.ZERO;
        BigDecimal standardScore = BigDecimal.ZERO;//标准得分
        BigDecimal score = BigDecimal.ZERO;//实际得分
        for (ProjectSpotCheckItemGroup spotCheckItemGroup : itemGroupList) {
            standardScore = standardScore.add(spotCheckItemGroup.getStandardTotalScore());
            score = score.add(spotCheckItemGroup.getTotalScore());
        }
        BigDecimal ratio = score.divide(standardScore, 2, RoundingMode.HALF_UP);//得出系数
        //找出本月应该获取的分值
        String startMonth = spotChecks.get(0).getSpotMonth();
        String endMonth = DateUtils.format(DateUtils.addMonth(DateUtils.convertDate(startMonth + "-01"), 1), DateUtils.MONTH_PATTERN);
        List<CustomProjectPlanCount> projectPlanCounts = projectPlanService.getPlanCountByMonth(startMonth, endMonth);
        BigDecimal monthTotalScore = BigDecimal.ZERO;//本月标准总得分
        for (CustomProjectPlanCount projectPlanCount : projectPlanCounts) {
            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanCount.getWorkStageId());
            if (projectWorkStage != null && projectWorkStage.getCeReviewScore() != null) {
                monthTotalScore = monthTotalScore.add(projectWorkStage.getCeReviewScore().multiply(new BigDecimal(projectPlanCount.getPlanCount())));
            }
        }
        return ratio.multiply(monthTotalScore).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算本月质量得分
     *
     * @param spotChecks 本月抽查批次
     * @return
     */
    private BigDecimal getQualityScore(List<ProjectSpotCheck> spotChecks) {
        if (CollectionUtils.isEmpty(spotChecks)) return BigDecimal.ZERO;
        List<Integer> spotCheckIds = LangUtils.transform(spotChecks, o -> o.getId());
        List<AssessmentPerformanceDto> performanceDtoList = performanceService.getPerformancesBySpotBatchIds(spotCheckIds);
        if (CollectionUtils.isEmpty(performanceDtoList)) return BigDecimal.ZERO;
        BigDecimal standardScore = BigDecimal.ZERO;//标准得分
        BigDecimal score = BigDecimal.ZERO;//实际得分
        for (AssessmentPerformanceDto performanceDto : performanceDtoList) {
            standardScore = standardScore.add(performanceDto.getStandardScore());
            score = score.add(performanceDto.getExamineScore());
        }
        BigDecimal ratio = score.divide(standardScore, 2, RoundingMode.HALF_UP);//得出系数
        //找出本月完成的工作事项，将工作事项分组并统计数量，最后统计出应该得到的质量分
        String startMonth = spotChecks.get(0).getSpotMonth();
        String endMonth = DateUtils.format(DateUtils.addMonth(DateUtils.convertDate(startMonth + "-01"), 1), DateUtils.MONTH_PATTERN);
        List<CustomProjectPlanDetailCount> planDetailCounts = projectPlanDetailsService.getPlanDetailsCountByMonth(startMonth, endMonth);
        if (CollectionUtils.isEmpty(planDetailCounts)) return BigDecimal.ZERO;
        BigDecimal monthTotalScore = BigDecimal.ZERO;//本月标准总得分
        for (CustomProjectPlanDetailCount planDetailCount : planDetailCounts) {
            //找出标准得分 找到模型下抽查的质量分
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(planDetailCount.getProjectPhaseId());
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(projectPhase.getBoxName());
            if (boxReDto == null) continue;
            BoxReActivityDto reActivityDto = bpmRpcBoxService.getEndActivityByBoxId(boxReDto.getId());
            List<AssessmentItemDto> assessmentItemList = bpmRpcBoxService.getAssessmentItemListByKey(reActivityDto.getBoxId(), reActivityDto.getId(), AssessmentTypeEnum.QUALITY.getValue());
            if (CollectionUtils.isNotEmpty(assessmentItemList)) {
                for (AssessmentItemDto assessmentItemDto : assessmentItemList) {
                    if (assessmentItemDto.getStandardScore() == null) continue;
                    monthTotalScore = monthTotalScore.add(assessmentItemDto.getStandardScore().multiply(new BigDecimal(planDetailCount.getPlanDetailsCount())));
                }
            }
        }
        return ratio.multiply(monthTotalScore).setScale(2, RoundingMode.HALF_UP);
    }


    private void exScore(ProjectSpotCheck spotCheck) {
        //1.计算得分，先找出抽查月份的该人员的所有抽查数据，并根据抽查的数据计算出该人员应该得到的分
        //2.通过实际得到的分数，与应该得到的分数进行比较得出一个系数
        //3.找出本月完成的阶段数据，计算出本月正常应该得到分值乘以系数可得到该人员的最终得分

        //本月该人员被抽查的所有批次
        List<ProjectSpotCheck> listAll = Lists.newArrayList(spotCheck);
        List<ProjectSpotCheck> list = projectSpotCheckService.getFinishSpotCheckListByMonth(spotCheck.getBySpotUser(), spotCheck.getSpotMonth());
        if (CollectionUtils.isNotEmpty(list))
            listAll.addAll(list);

        AssessmentPerformanceDto where = new AssessmentPerformanceDto();

        List<AssessmentPerformanceDto> performanceDtoList = performanceService.getPerformancesByParam(where);
        if (CollectionUtils.isEmpty(performanceDtoList)) return;


    }
}
