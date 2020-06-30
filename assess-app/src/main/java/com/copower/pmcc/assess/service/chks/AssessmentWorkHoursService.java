package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.*;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.AssessmentWorkHoursDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentWorkHoursService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class AssessmentWorkHoursService {
    @Autowired
    private ChksRpcAssessmentWorkHoursService chksRpcAssessmentWorkHoursService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private AssessmentCommonService assessmentCommonService;

    /**
     * 获取工时考核数据项
     *
     * @param processInsId
     * @param userAccount
     * @param reActivityName
     * @return
     */
    public BootstrapTableVo getWorkingHoursList(String processInsId, String userAccount, String reActivityName, String flog) {
        if (StringUtils.isBlank(processInsId) || StringUtils.isBlank(userAccount)) return null;
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<AssessmentWorkHoursDto> resultList = Lists.newArrayList();
        List<AssessmentWorkHoursDto> list = chksRpcAssessmentWorkHoursService.getWorkHoursListByProcessInsId(processInsId);
        if (CollectionUtils.isEmpty(list)) return null;
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        boolean isGroupUser = assessmentCommonService.isSpotGroupUser(boxRuDto.getBoxId(), commonService.thisUserAccount());
        //1.超级管理员，抽查组可查看所有数据
        //2.审批页面时，节点为可查看考核数据情况下，可查看比当前节点序号小或等的考核数据
        //3.详情页面时，找个当前人在整个流程考核中作为考核人或被考核人的最大序号
        if (processControllerComponent.userIsAdmin(userAccount) || isGroupUser) {
            resultList.addAll(list);
        } else {
            ListIterator<AssessmentWorkHoursDto> listIterator = list.listIterator();
            if ("details".equals(flog)) {//详情
                Integer currentSorting = chksRpcAssessmentWorkHoursService.getMaxSortingByProcessInsId(processInsId, commonService.thisUserAccount(), commonService.thisUserAccount());
                for (AssessmentWorkHoursDto dto : list) {
                    if (userAccount.equals(dto.getExaminePeople()) || userAccount.equals(dto.getByExaminePeople()) && dto.getSorting() >= currentSorting) {
                        currentSorting = dto.getSorting();
                    }
                }
            } else if ("approval".equals(flog)) {//审批
                BoxReActivityDto activityDto = bpmRpcBoxService.getBoxReActivityInfoByName(reActivityName, boxRuDto.getBoxId());
                if (listIterator.hasNext()) {
                    AssessmentWorkHoursDto next = listIterator.next();
                    if (next.getSorting() <= activityDto.getSortMultilevel()) {
                        resultList.add(next);
                    }
                }
            }
        }
        List<Integer> spotBoxReActivityIds = assessmentCommonService.getSpotBoxReActivityIds(boxRuDto.getBoxId());
        Integer adjustSorting = chksRpcAssessmentWorkHoursService.getMaxSortingByProcessInsId(processInsId, null, commonService.thisUserAccount());
        for (AssessmentWorkHoursDto dto : resultList) {
            if (ProjectStatusEnum.RUNING.getKey().equalsIgnoreCase(dto.getExamineStatus()) && commonService.thisUserAccount().equals(dto.getExaminePeople()) || ("approval".equals(flog) && StringUtils.isBlank(dto.getExaminePeople()))) {
                dto.setCanFill(true);
            }
            if (ProjectStatusEnum.FINISH.getKey().equalsIgnoreCase(dto.getExamineStatus()) && (isGroupUser || dto.getSorting() <= adjustSorting)) {
                dto.setCanAdjust(true);
            }
            if (ProjectStatusEnum.FINISH.getKey().equalsIgnoreCase(dto.getExamineStatus()) && spotBoxReActivityIds.contains(dto.getActivityId()) && isGroupUser) {
                dto.setCanSpot(true);
            }
        }
        bootstrapTableVo.setTotal((long) resultList.size());
        bootstrapTableVo.setRows(resultList);
        return bootstrapTableVo;
    }

    /**
     * 生成工时考核任务
     *
     * @param processInsId
     * @param boxId
     */
    public void generateWorkHoursTask(String processInsId, Integer boxId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws BpmException {
        //先清理比该节点序号大的节点工时考核任务
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        if (boxReDto != null && boxReDto.getBisLaunchCheck() == Boolean.TRUE) {
            ActivitiTaskNodeDto activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, commonService.thisUserAccount());
            BoxReActivityDto currentActivity = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, activitiTaskNodeDto.getCurrentStep());
            if (currentActivity == null || currentActivity.getBisViewChk() == Boolean.FALSE) return;
            List<AssessmentItemDto> assessmentItemList = bpmRpcBoxService.getAssessmentItemListByKey(boxId, currentActivity.getId(), AssessmentCommonService.BPM_ASSESSMENT_WORK_HOURS_KEY);
            if (CollectionUtils.isEmpty(assessmentItemList)) return;
            //1.生成工时考核任务，先查看该节点是否生成了任务，有则直接返回
            //2.没有生成则为当前节点生成对应的任务
            Integer count = chksRpcAssessmentWorkHoursService.getWorkHoursCount(processInsId, currentActivity.getName(), ProjectStatusEnum.RUNING.getKey());
            if (count > 0) return;
            chksRpcAssessmentWorkHoursService.deleteWorkHoursByProcessInsId(processInsId, currentActivity.getId());
            AssessmentWorkHoursDto dto = new AssessmentWorkHoursDto();
            dto.setProcessInsId(processInsId);
            dto.setAppKey(applicationConstant.getAppKey());
            if (projectInfo != null) {
                dto.setProjectId(projectInfo.getId());
                dto.setProjectName(projectInfo.getProjectName());
            }
            dto.setTaskId(taskId);
            dto.setBoxId(boxId);
            dto.setActivityId(currentActivity.getId());
            dto.setReActivityName(currentActivity.getName());
            dto.setActivityName(currentActivity.getCnName());
            dto.setSorting(currentActivity.getSortMultilevel());
            dto.setByExaminePeople(byExamineUser);
            dto.setAssessmentKey(AssessmentCommonService.BPM_ASSESSMENT_WORK_HOURS_KEY);
            dto.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
            if (projectPlanDetails != null) {
                dto.setPlanId(projectPlanDetails.getPlanId());
                ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
                if (projectPlan != null) {
                    dto.setPlanName(String.join("-", projectPlan.getPlanName(), projectPlanDetails.getProjectPhaseName()));
                }
                dto.setPlanDetailsId(projectPlanDetails.getId());
            }
            dto.setCreator(commonService.thisUserAccount());
            dto.setValidScore(new BigDecimal(0));
            chksRpcAssessmentWorkHoursService.addWorkHours(dto);
        }
    }

    /**
     * 清理流程所有工时考核任务和待提交任务
     *
     * @param processInsId
     */
    public void clearWorkHoursAll(String processInsId) {
        List<AssessmentWorkHoursDto> workHoursDtos = chksRpcAssessmentWorkHoursService.getWorkHoursListByProcessInsId(processInsId);
        if (CollectionUtils.isNotEmpty(workHoursDtos)) {
            chksRpcAssessmentWorkHoursService.deleteWorkHoursByIds(LangUtils.transform(workHoursDtos, p -> p.getId()));
        }
        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProcessInsId(processInsId);
        projectResponsibilityDto.setBusinessKey(AssessmentCommonService.PROJECT_TASK_BUSINESS_KEY_WORK_HOURS);
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
        if (CollectionUtils.isNotEmpty(projectTaskList)) {
            projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
        }
    }

    /**
     * 提交流程时处理质量考核任务及待处理任务
     *
     * @param approvalModelDto
     * @param projectInfo
     * @param projectPlanDetails
     */
    public void createAssessmentProjectTask(ApprovalModelDto approvalModelDto, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(approvalModelDto.getBoxId());
        if (boxReDto.getBisLaunchCheck() == Boolean.TRUE) {
            if (TaskHandleStateEnum.AGREE.getValue().equalsIgnoreCase(approvalModelDto.getConclusion())) {
                AssessmentWorkHoursDto query = new AssessmentWorkHoursDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                query.setTaskId(approvalModelDto.getTaskId());
                query.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
                List<AssessmentWorkHoursDto> workingHoursList = chksRpcAssessmentWorkHoursService.getWorkHoursList(query);
                if (CollectionUtils.isNotEmpty(workingHoursList)) {
                    for (AssessmentWorkHoursDto workHoursDto : workingHoursList) {
                        if (StringUtils.isBlank(workHoursDto.getExaminePeople())) { //当考核任务已经设置了考核人时此时此刻不再设置考核人
                            workHoursDto.setExaminePeople(processControllerComponent.getThisUser());
                            chksRpcAssessmentWorkHoursService.updateWorkHours(workHoursDto);
                        }
                    }
                }
                ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
                projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
                projectResponsibilityDto.setProcessInsId(approvalModelDto.getProcessInsId());
                List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
                if (CollectionUtils.isEmpty(workingHoursList)) {
                    if (CollectionUtils.isNotEmpty(projectTaskList)) {
                        projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
                    }
                } else {
                    if (CollectionUtils.isEmpty(projectTaskList)) {
                        ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
                        projectPlanResponsibility.setProcessInsId(approvalModelDto.getProcessInsId());
                        if (projectInfo != null)
                            projectPlanResponsibility.setProjectId(projectInfo.getId());
                        if (projectPlanDetails != null) {
                            projectPlanResponsibility.setPlanId(projectPlanDetails.getPlanId());
                            projectPlanResponsibility.setPlanDetailsId(projectPlanDetails.getId());
                        }
                        StringBuilder stringBuilder = new StringBuilder("(考核)");
                        if (StringUtils.isNotBlank(approvalModelDto.getWorkStage()))
                            stringBuilder.append(approvalModelDto.getWorkStage());
                        if (StringUtils.isNotBlank(approvalModelDto.getWorkPhase()))
                            stringBuilder.append("[").append(approvalModelDto.getWorkPhase()).append("]");
                        projectPlanResponsibility.setPlanDetailsName(stringBuilder.toString());
                        projectPlanResponsibility.setProjectName(projectInfo.getProjectName());
                        projectPlanResponsibility.setUserAccount(processControllerComponent.getThisUser());
                        projectPlanResponsibility.setModel(ResponsibileModelEnum.TASK.getId());
                        projectPlanResponsibility.setCreator(processControllerComponent.getThisUser());
                        projectPlanResponsibility.setConclusion(String.format("(考核)%s-%s", processControllerComponent.getThisUser(), approvalModelDto.getProcessInsId()));//
                        projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
                        projectPlanResponsibility.setUrl(String.format("/%s%s?boxId=%s&processInsId=%s", applicationConstant.getAppKey(), boxReDto.getProcessDisplayUrl(), approvalModelDto.getBoxId(), approvalModelDto.getProcessInsId()));
                        bpmRpcProjectTaskService.saveProjectTaskExtend(projectPlanResponsibility);
                    }
                }
            } else if (TaskHandleStateEnum.BACK.getValue().equalsIgnoreCase(approvalModelDto.getConclusion())) {
                clearWorkHoursAll(approvalModelDto.getProcessInsId());  //清理掉所有考核任务
            } else if (TaskHandleStateEnum.REJECT.getValue().equalsIgnoreCase(approvalModelDto.getConclusion())) {
                //清理掉当前节点生成的考核任务
                AssessmentWorkHoursDto query = new AssessmentWorkHoursDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                List<AssessmentWorkHoursDto> workingHoursList = chksRpcAssessmentWorkHoursService.getWorkHoursList(query);
                if (CollectionUtils.isNotEmpty(workingHoursList)) {
                    chksRpcAssessmentWorkHoursService.deleteWorkHoursByIds(LangUtils.transform(workingHoursList, p -> p.getId()));
                }
                clearAssessmentProjectTask(approvalModelDto.getProcessInsId(), processControllerComponent.getThisUser());
            }
        }
    }

    /**
     * 清理当前人考核ProjectTask任务
     *
     * @param processInsId
     * @param userAccount
     */
    public void clearAssessmentProjectTask(String processInsId, String userAccount) {
        AssessmentWorkHoursDto query = new AssessmentWorkHoursDto();
        query.setProcessInsId(processInsId);
        query.setExaminePeople(userAccount);
        List<AssessmentWorkHoursDto> workingHoursList = chksRpcAssessmentWorkHoursService.getWorkHoursList(query);
        if (CollectionUtils.isEmpty(workingHoursList)) {
            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
            projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
            projectResponsibilityDto.setProcessInsId(processInsId);
            projectResponsibilityDto.setBusinessKey(AssessmentCommonService.PROJECT_TASK_BUSINESS_KEY_WORK_HOURS);
            List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
            if (CollectionUtils.isNotEmpty(projectTaskList)) {
                projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
            }
        }
    }


    /**
     * 保存工时考核得分
     *
     * @param id
     * @param examineScore
     * @param remarks
     */
    public void saveWorkHours(Integer id, Integer spotId, Integer adjustId, BigDecimal examineScore, String remarks) {
        AssessmentWorkHoursDto dto = new AssessmentWorkHoursDto();
        if (spotId != null && spotId > 0) {
            AssessmentWorkHoursDto workHoursDto = chksRpcAssessmentWorkHoursService.getWorkHoursById(spotId);
            if (workHoursDto != null) {
                BeanUtils.copyProperties(workHoursDto, dto);
                dto.setId(null);
                dto.setSpotId(spotId);
                dto.setExamineScore(examineScore);
                dto.setRemarks(remarks);
                dto.setExamineStatus(ProjectStatusEnum.FINISH.getKey());
                dto.setByExaminePeople(workHoursDto.getExaminePeople());
                dto.setExaminePeople(commonService.thisUserAccount());
                dto.setExamineDate(DateUtils.now());
                chksRpcAssessmentWorkHoursService.addWorkHours(dto);
            }
        } else if (adjustId != null && adjustId > 0) {
            AssessmentWorkHoursDto workHoursDto = chksRpcAssessmentWorkHoursService.getWorkHoursById(adjustId);
            if (workHoursDto != null) {
                BeanUtils.copyProperties(workHoursDto, dto);
                dto.setId(null);
                dto.setAdjustId(adjustId);
                dto.setExamineScore(examineScore);
                dto.setRemarks(remarks);
                dto.setExamineStatus(ProjectStatusEnum.FINISH.getKey());
                dto.setByExaminePeople(workHoursDto.getByExaminePeople());
                dto.setExaminePeople(commonService.thisUserAccount());
                dto.setExamineDate(DateUtils.now());
                chksRpcAssessmentWorkHoursService.addWorkHours(dto);
            }
        } else {
            dto.setId(id);
            dto.setExamineScore(examineScore);
            dto.setRemarks(remarks);
            dto.setExamineStatus(ProjectStatusEnum.FINISH.getKey());
            dto.setExaminePeople(commonService.thisUserAccount());
            dto.setExamineDate(DateUtils.now());
            chksRpcAssessmentWorkHoursService.updateWorkHours(dto);
        }
    }

    /**
     * 获取抽查数据
     *
     * @param id
     * @return
     */
    public BootstrapTableVo getWorkingHoursSpotListById(Integer id) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        return chksRpcAssessmentWorkHoursService.getWorkHoursSpotListById(id, requestBaseParam.getLimit(), requestBaseParam.getLimit());
    }

    /**
     * 获取历史数据
     *
     * @param processInsId
     * @return
     */
    public BootstrapTableVo getWorkHoursHistoryList(String processInsId, Integer activityId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        return chksRpcAssessmentWorkHoursService.getWorkHoursListAllByActivityId(processInsId, activityId, requestBaseParam.getLimit(), requestBaseParam.getLimit());
    }
}
