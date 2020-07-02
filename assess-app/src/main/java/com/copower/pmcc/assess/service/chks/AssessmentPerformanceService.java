package com.copower.pmcc.assess.service.chks;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.*;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019-12-16.
 * 考核
 */
@Service
public class AssessmentPerformanceService {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private AssessmentCommonService assessmentCommonService;

    public BootstrapTableVo getPerformanceList(AssessmentPerformanceDto where, Integer boxId, String reActivityName, String flog) {
        //1.管理员与抽查组可查看所有质量考核数据
        //2.当前人只能查看排序比当前节点小的质量考核数据
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<Integer> activityList = Lists.newArrayList();
        boolean spotGroupUser = assessmentCommonService.isSpotGroupUser(boxId, commonService.thisUserAccount());
        if (!processControllerComponent.userIsAdmin(commonService.thisUserAccount()) && !spotGroupUser) {
            List<BoxReActivityDto> activityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
            if (CollectionUtils.isEmpty(activityDtos)) return bootstrapTableVo;
            if ("approval".equals(flog)) {
                BoxReActivityDto currActivity = bpmRpcBoxService.getBoxReActivityInfoByName(reActivityName, boxId);
                if (currActivity != null) {
                    for (BoxReActivityDto activityDto : activityDtos) {
                        if (activityDto.getSortMultilevel() <= currActivity.getSortMultilevel())
                            activityList.add(activityDto.getId());
                    }
                }
            } else {//找出该人员在该流程中排序号最大的数据
                Integer sorting = performanceService.getMaxSortingByProcessInsId(where.getProcessInsId(), commonService.thisUserAccount(), commonService.thisUserAccount());
                for (BoxReActivityDto activityDto : activityDtos) {
                    if (activityDto.getSortMultilevel() <= sorting)
                        activityList.add(activityDto.getId());
                }
            }
            if (CollectionUtils.isEmpty(activityList)) return bootstrapTableVo;
        }
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        where.setActivityId(null);
        where.setSpotId(0);
        where.setAdjustId(0);
        bootstrapTableVo = performanceService.getPerformanceDtoListByParam(where, activityList, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<AssessmentPerformanceDto> rows = bootstrapTableVo.getRows();
        if (CollectionUtils.isNotEmpty(rows)) {
            BoxReActivityDto endActivity = bpmRpcBoxService.getEndActivityByBoxId(boxId);
            List<Integer> spotBoxReActivityIds = assessmentCommonService.getSpotBoxReActivityIds(boxId);
            for (AssessmentPerformanceDto row : rows) {
                row.setCanSpot(ProjectStatusEnum.FINISH.getKey().equalsIgnoreCase(row.getExamineStatus()) && spotBoxReActivityIds.contains(row.getActivityId()) && spotGroupUser);
                row.setSpotActivityId(endActivity.getId());
            }
        }
        return bootstrapTableVo;
    }

    /**
     * 批量设置完成
     *
     * @param ids
     */
    public void batchSetFinish(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return;
        List<AssessmentPerformanceDto> list = performanceService.getPerformancesByIds(ids);
        if (CollectionUtils.isEmpty(list)) return;
        for (AssessmentPerformanceDto assessmentProjectPerformanceDto : list) {
            if (ProjectStatusEnum.FINISH.getKey().equals(assessmentProjectPerformanceDto.getExamineStatus())) {
                continue;
            }
            if (commonService.thisUserAccount().equals(assessmentProjectPerformanceDto.getExaminePeople())) {
                assessmentProjectPerformanceDto.setExamineStatus(ProjectStatusEnum.FINISH.getKey());
                performanceService.updatePerformanceDto(assessmentProjectPerformanceDto, false);
            }
        }
    }

    /**
     * 保存考核信息
     *
     * @param assessmentPerformanceDto
     * @param detailDtoList
     */
    public Integer saveAssessmentServer(AssessmentPerformanceDto assessmentPerformanceDto, List<AssessmentPerformanceDetailDto> detailDtoList, Integer planDetailsId) {
        //修改情况  处理
        if (assessmentPerformanceDto.getId() != null && assessmentPerformanceDto.getId() != 0) {
            AssessmentPerformanceDto target = getPerformanceById(assessmentPerformanceDto.getId());
            String remarks = assessmentPerformanceDto.getRemarks();
            String examineStatus = assessmentPerformanceDto.getExamineStatus();
            String examineUrl = assessmentPerformanceDto.getExamineUrl();
            String tableName = assessmentPerformanceDto.getTableName();
            Integer tableId = assessmentPerformanceDto.getTableId();
            String byExaminePeople = assessmentPerformanceDto.getByExaminePeople();
            String examinePeople = assessmentPerformanceDto.getExaminePeople();
            BeanCopyHelp.copyPropertiesIgnoreNull(target, assessmentPerformanceDto);
            if (assessmentPerformanceDto.getExamineScore() == null) {
                assessmentPerformanceDto.setExamineScore(new BigDecimal(0));
            }
            if (StringUtils.isNotBlank(examineStatus)) {
                assessmentPerformanceDto.setExamineStatus(examineStatus);
            }
            if (StringUtils.isNotBlank(remarks)) {
                assessmentPerformanceDto.setRemarks(remarks);
            }
            if (StringUtils.isNotBlank(examineUrl)) {
                assessmentPerformanceDto.setExamineUrl(examineUrl);
            }
            if (StringUtils.isNotBlank(tableName)) {
                assessmentPerformanceDto.setTableName(tableName);
            }
            if (StringUtils.isNotBlank(examinePeople)) {
                assessmentPerformanceDto.setExaminePeople(examinePeople);
            }
            if (StringUtils.isNotBlank(byExaminePeople)) {
                assessmentPerformanceDto.setByExaminePeople(byExaminePeople);
            }
            if (tableId != null) {
                assessmentPerformanceDto.setTableId(tableId);
            }
        }
        ProjectPlanDetails projectPlanDetails = null;
        if (StringUtils.isNotBlank(assessmentPerformanceDto.getProcessInsId())) {
            projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(assessmentPerformanceDto.getProcessInsId());
        }
        if (projectPlanDetails == null && planDetailsId != null) {
            projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        }
        return savePerformanceBase(assessmentPerformanceDto, detailDtoList, projectPlanDetails);
    }

    /**
     * 抽取的保存考核信息
     *
     * @param projectPerformanceDto
     * @param detailDtos
     * @param projectPlanDetails
     */
    private Integer savePerformanceBase(AssessmentPerformanceDto projectPerformanceDto, List<AssessmentPerformanceDetailDto> detailDtos, ProjectPlanDetails projectPlanDetails) {
        if (StringUtils.isBlank(projectPerformanceDto.getCreator())) {
            projectPerformanceDto.setCreator(processControllerComponent.getThisUser());
        }
        if (StringUtils.isBlank(projectPerformanceDto.getExaminePeople())) {
            projectPerformanceDto.setExaminePeople(processControllerComponent.getThisUser());
        }
        if (projectPerformanceDto.getExamineDate() == null) {
            projectPerformanceDto.setExamineDate(new Date());
        }
        projectPerformanceDto.setAppKey(applicationConstant.getAppKey());
        if (projectPerformanceDto.getSpotActivityId() == null || projectPerformanceDto.getSpotActivityId().intValue() < 0) {
            projectPerformanceDto.setSpotActivityId(0);//抽查节点id
        } else {
            if (StringUtils.isEmpty(projectPerformanceDto.getActivityName())) {
                BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(projectPerformanceDto.getSpotActivityId());
                if (boxReActivityDto != null) {
                    projectPerformanceDto.setActivityName(boxReActivityDto.getCnName());
                }
            }
        }
        if (projectPlanDetails != null) {
            if (projectPerformanceDto.getPlanDetailsId() == null) {
                projectPerformanceDto.setPlanDetailsId(projectPlanDetails.getId());
            }
            if (projectPerformanceDto.getPlanId() == null) {
                projectPerformanceDto.setPlanId(projectPlanDetails.getPlanId());
            }
            if (StringUtils.isEmpty(projectPerformanceDto.getByExaminePeople())) {
                if (StringUtils.isNotBlank(projectPlanDetails.getExecuteUserAccount())) {
                    projectPerformanceDto.setByExaminePeople(projectPlanDetails.getExecuteUserAccount());
                }
            }
            ProjectInfo projectInfo = null;
            if (projectPlanDetails.getProjectId() != null) {
                if (projectPerformanceDto.getProjectId() == null) {
                    projectPerformanceDto.setProjectId(projectPlanDetails.getProjectId());
                }
                projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
            }
            if (StringUtils.isEmpty(projectPerformanceDto.getProjectName())) {
                if (projectInfo != null) {
                    projectPerformanceDto.setProjectName(projectInfo.getProjectName());
                }
            }
        }
        return performanceService.savePerformanceBase(projectPerformanceDto, detailDtos);
    }

    /**
     * 获取考核明细数据
     *
     * @param performanceId
     * @return
     */
    public List<AssessmentPerformanceDetailDto> getPerformanceDetailsByPerformanceId(Integer performanceId) {
        List<AssessmentPerformanceDetailDto> detailDtoList = performanceService.getPerformanceDetailListByPerformanceId(performanceId);
        if (CollectionUtils.isNotEmpty(detailDtoList)) return detailDtoList;
        AssessmentPerformanceDto performanceDto = performanceService.getPerformanceById(performanceId);
        List<AssessmentItemDto> assessmentItemDtoList = bpmRpcBoxService.getAssessmentItemListByKey(performanceDto.getBoxId(), performanceDto.getActivityId(), performanceDto.getAssessmentKey());
        if (CollectionUtils.isNotEmpty(assessmentItemDtoList)) {
            for (AssessmentItemDto assessmentItemDto : assessmentItemDtoList) {
                AssessmentPerformanceDetailDto detailDto = new AssessmentPerformanceDetailDto();
                detailDto.setContent(assessmentItemDto.getAssessmentDes());
                detailDto.setContentId(assessmentItemDto.getId());
                detailDto.setPerformanceId(performanceId);
                detailDto.setMinScore(assessmentItemDto.getMinScore());
                detailDto.setMaxScore(assessmentItemDto.getMaxScore());
                detailDto.setStandardScore(assessmentItemDto.getStandardScore());
                performanceService.savePerformanceDetailDto(detailDto);
            }
            detailDtoList = performanceService.getPerformanceDetailListByPerformanceId(performanceId);
        }
        return detailDtoList;
    }

    public AssessmentPerformanceDto getPerformanceById(Integer id) {
        return performanceService.getPerformanceById(id);
    }


    public void updatePerformanceDto(AssessmentPerformanceDto assessmentPerformanceDto, boolean updateNull) {
        performanceService.updatePerformanceDto(assessmentPerformanceDto, updateNull);
    }

    public void updatePerformanceDetailDto(List<AssessmentPerformanceDetailDto> detailDtoList, boolean updateNull) {
        performanceService.updatePerformanceDetailDto(detailDtoList, updateNull);
    }

    //1.当前节点任务是否已生成，已生成则不再重复生成 2.将比当前节点序号大的节点考核任务置位无效状态
    public void generatePerformanceTask(String processInsId, Integer boxId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws Exception {
        //1.当前节点任务是否已生成，已生成则不再重复生成
        //2.将比当前节点序号大的节点考核任务置位无效状态
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        if (boxReDto != null && boxReDto.getBisLaunchCheck() == Boolean.TRUE) {
            ActivitiTaskNodeDto activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, commonService.thisUserAccount());
            if (activitiTaskNodeDto == null) return;
            BoxReActivityDto currentActivity = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, activitiTaskNodeDto.getCurrentStep());
            if (currentActivity == null || currentActivity.getBisViewChk() == Boolean.FALSE) return;
            Integer count = performanceService.getPerformanceCount(processInsId, currentActivity.getId(), null);
            if (count > 0) return;
            performanceService.deletePerformanceByProcessInsId(processInsId, currentActivity.getId());
            String checkBean = StringUtils.uncapitalize(AssessmentTaskService.class.getSimpleName());//默认生成考核任务服务方法
            checkBean = StringUtils.isNoneBlank(boxReDto.getCheckBean()) ? boxReDto.getCheckBean() : checkBean;
            checkBean = StringUtils.isNoneBlank(currentActivity.getCheckBean()) ? currentActivity.getCheckBean() : checkBean;
            AssessmentTaskInterface assessmentTaskBean = (AssessmentTaskInterface) SpringContextUtils.getBean(checkBean);
            assessmentTaskBean.createAssessmentPerformanceTask(processInsId, currentActivity.getId(), taskId, byExamineUser, projectInfo, projectPlanDetails);
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
            //0.当流程以同意的方式提交时，且审批人没有在审批时填写考核信息，则将该任务节点的考核数据的考核人设置为当前人
            //1.提交流程时，检查当前人在该流程上有无没有提交的考核任务
            //2.如果任务都处理完成，讲相关的projectTask任务删除
            //3.如果还有没处理完成的考核任务，并且projectTask表中没有为当前人生成任务，则创建一个任务
            //4.创建的任务，访问地址为该流程配置的详情地址
            if (TaskHandleStateEnum.AGREE.getValue().equalsIgnoreCase(approvalModelDto.getConclusion())) {
                AssessmentPerformanceDto query = new AssessmentPerformanceDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                query.setTaskId(approvalModelDto.getTaskId());
                query.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
                List<AssessmentPerformanceDto> performanceDtos = performanceService.getPerformancesByParam(query);
                if (CollectionUtils.isNotEmpty(performanceDtos)) {
                    for (AssessmentPerformanceDto performanceDto : performanceDtos) {
                        if (StringUtils.isBlank(performanceDto.getExaminePeople())) { //当考核任务已经设置了考核人时此时此刻不再设置考核人
                            performanceDto.setExaminePeople(processControllerComponent.getThisUser());
                        }
                        performanceService.updatePerformanceDto(performanceDto, false);
                    }
                }
                query = new AssessmentPerformanceDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setExaminePeople(processControllerComponent.getThisUser());
                performanceDtos = performanceService.getPerformancesByParam(query);
                ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
                projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
                projectResponsibilityDto.setProcessInsId(approvalModelDto.getProcessInsId());
                List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
                if (CollectionUtils.isEmpty(performanceDtos)) {
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
                        projectPlanResponsibility.setBusinessKey(AssessmentCommonService.PROJECT_TASK_BUSINESS_KEY_PERFORMANCE);
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
                //清理掉所有考核任务
                clearAssessmentProjectPerformanceAll(approvalModelDto.getProcessInsId());
            } else if (TaskHandleStateEnum.REJECT.getValue().equalsIgnoreCase(approvalModelDto.getConclusion())) {
                //清理掉当前节点生成的考核任务和当前人的待提交任务
                AssessmentPerformanceDto query = new AssessmentPerformanceDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                List<AssessmentPerformanceDto> performanceDtoList = performanceService.getPerformancesByParam(query);
                if (CollectionUtils.isNotEmpty(performanceDtoList)) {
                    performanceDtoList.forEach(o -> performanceService.deletePerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId())));
                }
                query = new AssessmentPerformanceDto();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
                query.setExaminePeople(processControllerComponent.getThisUser());
                List<AssessmentPerformanceDto> list = performanceService.getPerformancesByParam(query);
                if (CollectionUtils.isEmpty(list))
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
        AssessmentPerformanceDto query = new AssessmentPerformanceDto();
        query.setProcessInsId(processInsId);
        query.setExaminePeople(userAccount);
        List<AssessmentPerformanceDto> performanceDtoList = performanceService.getPerformancesByParam(query);
        if (CollectionUtils.isEmpty(performanceDtoList)) {
            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
            projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
            projectResponsibilityDto.setProcessInsId(processInsId);
            projectResponsibilityDto.setBusinessKey(AssessmentCommonService.PROJECT_TASK_BUSINESS_KEY_PERFORMANCE);
            List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
            if (CollectionUtils.isNotEmpty(projectTaskList)) {
                projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
            }
        }
    }

    /**
     * 清理流程所有质量考核任务及待提交任务
     *
     * @param processInsId
     */
    public void clearAssessmentProjectPerformanceAll(String processInsId) {
        AssessmentPerformanceDto query = new AssessmentPerformanceDto();
        query.setProcessInsId(processInsId);
        List<AssessmentPerformanceDto> performanceDtoList = performanceService.getPerformancesByParam(query);
        if (CollectionUtils.isNotEmpty(performanceDtoList)) {
            performanceService.deletePerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId()));
        }
        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProcessInsId(processInsId);
        projectResponsibilityDto.setBusinessKey(AssessmentCommonService.PROJECT_TASK_BUSINESS_KEY_PERFORMANCE);
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
        if (CollectionUtils.isNotEmpty(projectTaskList)) {
            projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
        }
    }

    public String pasteAll(Integer copyId, String ids) {
        StringBuilder stringBuilder = new StringBuilder(8);
        AssessmentPerformanceDto copyObj = performanceService.getPerformanceById(copyId);
        if (copyObj == null) {
            stringBuilder.append("拷贝的模板数据不存在,因此无法继续粘贴.");
            return stringBuilder.toString();
        }
        List<AssessmentPerformanceDetailDto> assessmentProjectPerformanceDetailDtoList = performanceService.getPerformanceDetailListByPerformanceId(copyId);
        if (CollectionUtils.isEmpty(assessmentProjectPerformanceDetailDtoList)) {
            stringBuilder.append("拷贝的模板数据的明细被删除,因此无法继续粘贴");
            return stringBuilder.toString();
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(ids);
        Iterator<Integer> iterator = integerList.iterator();
        //索引 用作日志的书写
        int index = 0;
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            boolean check = pasteData(copyObj, assessmentProjectPerformanceDetailDtoList, performanceService.getPerformanceById(id));
            index++;
            if (check) {
                stringBuilder.append("第").append(index).append("条数据粘贴成功 \r");
            } else {
                stringBuilder.append("第").append(index).append("条数据粘贴失败 \r");
            }
        }
        return stringBuilder.toString();
    }

    private boolean pasteData(AssessmentPerformanceDto copy, List<AssessmentPerformanceDetailDto> copyList, AssessmentPerformanceDto projectPerformanceDto) {
        if (copy == null || projectPerformanceDto == null) return false;
        if (Objects.equal(projectPerformanceDto.getExamineStatus(), ProjectStatusEnum.FINISH.getKey())) return false;
        List<AssessmentPerformanceDetailDto> performanceDetailDtos = getPerformanceDetailsByPerformanceId(projectPerformanceDto.getId());
        if (CollectionUtils.isEmpty(performanceDetailDtos)) return false;
        if (copyList.size() != performanceDetailDtos.size()) return false;

        Map<Integer, AssessmentPerformanceDetailDto> map = FormatUtils.mappingSingleEntity(copyList, o -> o.getContentId(), o -> o);
        for (AssessmentPerformanceDetailDto performanceDetailDto : performanceDetailDtos) {
            AssessmentPerformanceDetailDto source = map.get(performanceDetailDto.getContentId());
            if (source != null) {
                performanceDetailDto.setActualScore(source.getActualScore());
                performanceDetailDto.setRemark(source.getRemark());
            }
        }
        projectPerformanceDto.setRemarks(copy.getRemarks());
        projectPerformanceDto.setExaminePeople(processControllerComponent.getThisUser());
        saveAssessmentServer(projectPerformanceDto, performanceDetailDtos, projectPerformanceDto.getPlanDetailsId());
        return true;
    }

    /**
     * 获取调整数据
     *
     * @param performanceId
     * @return
     */
    public BootstrapTableVo getAdjustRecordListByPerformanceId(Integer performanceId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        AssessmentPerformanceDto where = new AssessmentPerformanceDto();
        where.setAdjustId(performanceId);
        List<AssessmentPerformanceDto> list = performanceService.getPerformancesByParam(where);
        bootstrapTableVo.setRows(list);
        bootstrapTableVo.setTotal((long) list.size());
        return bootstrapTableVo;
    }

    /**
     * 获取抽查数据
     *
     * @param performanceId
     * @return
     */
    public BootstrapTableVo getSpotRecordListByPerformanceId(Integer performanceId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        AssessmentPerformanceDto where = new AssessmentPerformanceDto();
        where.setSpotId(performanceId);
        List<AssessmentPerformanceDto> list = performanceService.getPerformancesByParam(where);
        bootstrapTableVo.setRows(list);
        bootstrapTableVo.setTotal((long) list.size());
        return bootstrapTableVo;
    }

    /**
     * 获取抽查时的考核项
     *
     * @param performanceId
     * @return
     */
    public List<AssessmentPerformanceDetailDto> getSpotPerformanceDetailList(Integer performanceId) {
        AssessmentPerformanceDto performanceDto = performanceService.getPerformanceById(performanceId);
        BoxReActivityDto activityDto = bpmRpcBoxService.getEndActivityByBoxId(performanceDto.getBoxId());
        List<AssessmentItemDto> assessmentItemDtoList = bpmRpcBoxService.getAssessmentItemListByKey(performanceDto.getBoxId(), activityDto.getId(), performanceDto.getAssessmentKey());
        List<AssessmentPerformanceDetailDto> detailDtoList = null;
        if (CollectionUtils.isEmpty(assessmentItemDtoList)) {
            assessmentItemDtoList = bpmRpcBoxService.getAssessmentItemListByKey(performanceDto.getBoxId(), activityDto.getId(), performanceDto.getAssessmentType());
        }
        detailDtoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(assessmentItemDtoList)) {
            for (AssessmentItemDto assessmentItemDto : assessmentItemDtoList) {
                AssessmentPerformanceDetailDto detailDto = new AssessmentPerformanceDetailDto();
                detailDto.setContent(assessmentItemDto.getAssessmentDes());
                detailDto.setContentId(assessmentItemDto.getId());
                detailDto.setPerformanceId(performanceId);
                detailDto.setMinScore(assessmentItemDto.getMinScore());
                detailDto.setMaxScore(assessmentItemDto.getMaxScore());
                detailDto.setStandardScore(assessmentItemDto.getStandardScore());
                detailDtoList.add(detailDto);
            }
        }
        return detailDtoList;
    }

    /**
     * 保存抽查考核内容
     *
     * @param performanceId
     * @param detailDtoList
     */
    public void saveSpotPerformance(Integer performanceId, String remark, List<AssessmentPerformanceDetailDto> detailDtoList) {
        BigDecimal total = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(detailDtoList)) {
            for (AssessmentPerformanceDetailDto detailDto : detailDtoList) {
                total = total.add(detailDto.getActualScore());
            }
        }

        AssessmentPerformanceDto performanceDto = performanceService.getPerformanceById(performanceId);
        AssessmentPerformanceDto spotPerformance = new AssessmentPerformanceDto();
        BeanUtils.copyProperties(performanceDto, spotPerformance);
        spotPerformance.setId(null);
        spotPerformance.setSpotId(performanceId);
        spotPerformance.setExaminePeople(commonService.thisUserAccount());
        spotPerformance.setByExaminePeople(performanceDto.getExaminePeople());
        spotPerformance.setExamineStatus(ProcessStatusEnum.FINISH.getValue());
        spotPerformance.setExamineDate(DateUtils.now());
        spotPerformance.setExamineScore(total);
        spotPerformance.setRemarks(remark);
        spotPerformance.setBisEffective(true);
        spotPerformance.setCreated(DateUtils.now());
        spotPerformance.setCreator(commonService.thisUserAccount());
        spotPerformance.setModified(DateUtils.now());
        Integer spotPerformanceId = performanceService.saveAndUpdatePerformanceDto(spotPerformance, false);

        if (CollectionUtils.isNotEmpty(detailDtoList)) {
            for (AssessmentPerformanceDetailDto detailDto : detailDtoList) {
                AssessmentItemDto assessmentItemDto = bpmRpcBoxService.getAssessmentItem(detailDto.getContentId());
                if (assessmentItemDto != null) {
                    detailDto.setContent(assessmentItemDto.getAssessmentDes());
                    detailDto.setContentId(assessmentItemDto.getId());
                    detailDto.setMinScore(assessmentItemDto.getMinScore());
                    detailDto.setMaxScore(assessmentItemDto.getMaxScore());
                    detailDto.setStandardScore(assessmentItemDto.getStandardScore());
                }
                detailDto.setPerformanceId(spotPerformanceId);
                detailDto.setCreator(commonService.thisUserAccount());
                performanceService.savePerformanceDetailDto(detailDto);
            }
        }
    }
}
