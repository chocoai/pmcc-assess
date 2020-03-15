package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
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
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceQuery;
import com.copower.pmcc.chks.api.dto.AssessmentProjectWorkHoursDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentWorkHoursService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 获取工时考核数据项
     *
     * @param processInsId
     * @param userAccount
     * @param reActivityName
     * @return
     */
    public BootstrapTableVo getWorkingHoursList(String processInsId, String userAccount, String reActivityName, String flog) {
        List<AssessmentProjectWorkHoursDto> hoursDtoList = getWorkingHoursListByProcessInsId(processInsId, userAccount, reActivityName, flog);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal((long) hoursDtoList.size());
        bootstrapTableVo.setRows(hoursDtoList);
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
            Integer count = chksRpcAssessmentWorkHoursService.getAssessmentWorkHoursCount(processInsId, currentActivity.getName(),ProjectStatusEnum.RUNING.getKey());
            if (count > 0) return;
            chksRpcAssessmentWorkHoursService.deleteAssessmentWorkHoursByProcessInsId(processInsId,currentActivity.getId());
            AssessmentProjectWorkHoursDto dto = new AssessmentProjectWorkHoursDto();
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
            chksRpcAssessmentWorkHoursService.addAssessmentWorkHours(dto);
        }
    }

    /**
     * 清理流程所有工时考核任务和待提交任务
     *
     * @param processInsId
     */
    public void clearAssessmentWorkHoursAll(String processInsId) {
        List<AssessmentProjectWorkHoursDto> workHoursDtos = chksRpcAssessmentWorkHoursService.getWorkingHoursListByProcessInsId(processInsId);
        if (CollectionUtils.isNotEmpty(workHoursDtos)) {
            chksRpcAssessmentWorkHoursService.deleteAssessmentWorkHoursByIds(LangUtils.transform(workHoursDtos, p -> p.getId()));
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
     * 获取考核工时数据列表
     *
     * @param processInsId
     * @return
     */
    public List<AssessmentProjectWorkHoursDto> getWorkingHoursListByProcessInsId(String processInsId, String userAccount, String reActivityName, String flog) {
        if (StringUtils.isBlank(processInsId) || StringUtils.isBlank(userAccount)) return null;
        //1.当前人为管理员，考核组人员可查看所有数据
        //2.详情页面，当前人为该考核数据参与人，取该人员在所有数据中最大的排序号，数据小于等于该排序号的数据
        //3.审批页面，数据小于当前节点排序的数据
        List<AssessmentProjectWorkHoursDto> list = chksRpcAssessmentWorkHoursService.getWorkingHoursListByProcessInsId(processInsId);
        if (CollectionUtils.isEmpty(list)) return null;
        if (processControllerComponent.userIsAdmin(userAccount)) return list;
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        try {
            BoxReActivityDto activityDto = bpmRpcBoxService.getEndActivityByBoxId(boxRuDto.getBoxId());
            List<String> users = bpmRpcBoxService.getRoleUserByActivityId(activityDto.getId());
            if (CollectionUtils.isNotEmpty(users) && users.contains(userAccount)) return list;
        } catch (BpmException e) {
            e.printStackTrace();
            return null;
        }
        List<AssessmentProjectWorkHoursDto> resultList = Lists.newArrayList();
        ListIterator<AssessmentProjectWorkHoursDto> listIterator = list.listIterator();
        if (listIterator.hasNext()) {
            AssessmentProjectWorkHoursDto next = listIterator.next();
            if (userAccount.equals(next.getByExaminePeople())) {
                resultList.add(next);
                listIterator.remove();
            }
        }
        Integer currentStep = -1;
        if ("details".equals(flog)) {//详情
            for (AssessmentProjectWorkHoursDto dto : list) {
                if (userAccount.equals(dto.getExaminePeople()) || userAccount.equals(dto.getByExaminePeople()) && dto.getSorting() >= currentStep) {
                    currentStep = dto.getSorting();
                }
            }
        } else {
            BoxReActivityDto activityDto = bpmRpcBoxService.getBoxReActivityInfoByName(reActivityName, boxRuDto.getBoxId());
            currentStep = activityDto.getSortMultilevel();
        }
        Integer finalCurrentStep = currentStep;
        List<AssessmentProjectWorkHoursDto> filter = LangUtils.filter(list, o -> o.getSorting() <= finalCurrentStep);
        resultList.addAll(filter);
        LangUtils.sort(resultList, (o1, o2) -> {
            return o1.getSorting().compareTo(o2.getSorting());
        });
        return resultList;
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
                AssessmentProjectWorkHoursDto query = new AssessmentProjectWorkHoursDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                query.setTaskId(approvalModelDto.getTaskId());
                query.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
                List<AssessmentProjectWorkHoursDto> workingHoursList = chksRpcAssessmentWorkHoursService.getWorkingHoursList(query);
                if (CollectionUtils.isNotEmpty(workingHoursList)) {
                    for (AssessmentProjectWorkHoursDto workHoursDto : workingHoursList) {
                        if (StringUtils.isBlank(workHoursDto.getExaminePeople())) { //当考核任务已经设置了考核人时此时此刻不再设置考核人
                            workHoursDto.setExaminePeople(processControllerComponent.getThisUser());
                            chksRpcAssessmentWorkHoursService.updateAssessmentWorkHours(workHoursDto);
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
                clearAssessmentWorkHoursAll(approvalModelDto.getProcessInsId());  //清理掉所有考核任务
            } else if (TaskHandleStateEnum.REJECT.getValue().equalsIgnoreCase(approvalModelDto.getConclusion())) {
                //清理掉当前节点生成的考核任务
                AssessmentProjectWorkHoursDto query = new AssessmentProjectWorkHoursDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                List<AssessmentProjectWorkHoursDto> workingHoursList = chksRpcAssessmentWorkHoursService.getWorkingHoursList(query);
                if (CollectionUtils.isNotEmpty(workingHoursList)) {
                    chksRpcAssessmentWorkHoursService.deleteAssessmentWorkHoursByIds(LangUtils.transform(workingHoursList, p -> p.getId()));
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
        AssessmentProjectWorkHoursDto query = new AssessmentProjectWorkHoursDto();
        query.setProcessInsId(processInsId);
        query.setExaminePeople(userAccount);
        List<AssessmentProjectWorkHoursDto> workingHoursList = chksRpcAssessmentWorkHoursService.getWorkingHoursList(query);
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
    public void saveAssessmentProjectWorkHours(Integer id, BigDecimal examineScore, String remarks) {
        AssessmentProjectWorkHoursDto dto = new AssessmentProjectWorkHoursDto();
        dto.setId(id);
        dto.setExamineScore(examineScore);
        dto.setRemarks(remarks);
        dto.setExamineStatus(ProjectStatusEnum.FINISH.getKey());
        dto.setExaminePeople(commonService.thisUserAccount());
        dto.setExamineDate(DateUtils.now());
        chksRpcAssessmentWorkHoursService.updateAssessmentWorkHours(dto);
    }
}
