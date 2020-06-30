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
                Integer sorting = performanceService.getMaxSortingByProcessInsId(where.getProcessInsId(), commonService.thisUserAccount(),commonService.thisUserAccount());
                for (BoxReActivityDto activityDto : activityDtos) {
                    if (activityDto.getSortMultilevel() <= sorting)
                        activityList.add(activityDto.getId());
                }
            }
            if (CollectionUtils.isEmpty(activityList)) return bootstrapTableVo;
        }
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        where.setActivityId(null);
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
     * @param ids
     */
    public void batchSetFinish(List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)) return;
        List<AssessmentPerformanceDto> list = performanceService.getPerformancesByIds(ids);
        if(CollectionUtils.isEmpty(list)) return;
        for (AssessmentPerformanceDto assessmentProjectPerformanceDto : list) {
            if(ProjectStatusEnum.FINISH.getKey().equals(assessmentProjectPerformanceDto.getExamineStatus())){
                continue;
            }
            if(commonService.thisUserAccount().equals(assessmentProjectPerformanceDto.getExaminePeople())){
                assessmentProjectPerformanceDto.setExamineStatus(ProjectStatusEnum.FINISH.getKey());
                performanceService.updatePerformanceDto(assessmentProjectPerformanceDto,false);
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
            if (assessmentPerformanceDto.getValidScore() == null) {
                assessmentPerformanceDto.setValidScore(new BigDecimal(0));
            }
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
            if (projectPerformanceDto.getTableId() == null) {
                //table id暂时这样设置
                projectPerformanceDto.setTableId(projectPlanDetails.getId());
            }
            if (StringUtils.isEmpty(projectPerformanceDto.getTableName())) {
                projectPerformanceDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
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
        if (CollectionUtils.isNotEmpty(detailDtos)) {
            Iterator<AssessmentPerformanceDetailDto> detailDtoIterator = detailDtos.iterator();
            while (detailDtoIterator.hasNext()) {
                AssessmentPerformanceDetailDto performanceDetailDto = detailDtoIterator.next();
                if (performanceDetailDto.getPerformanceId() != null && performanceDetailDto.getPerformanceId() != 0) {
                    projectPerformanceDto.setId(performanceDetailDto.getPerformanceId());
                }
                performanceDetailDto.setCreator(processControllerComponent.getThisUser());
                if (performanceDetailDto.getContentId() != null) {
                    AssessmentItemDto assessmentItem = bpmRpcBoxService.getAssessmentItem(performanceDetailDto.getContentId());
                    if (assessmentItem != null) {
                        performanceDetailDto.setContent(assessmentItem.getAssessmentDes());
                    }
                }
            }
        }
        return performanceService.savePerformanceBase(projectPerformanceDto, detailDtos);
    }

    public Integer savePerformance(ApprovalModelDto approvalModelDto, String chksScore, String remarks, String byExaminePeople) {
        //添加主表
        AssessmentPerformanceDto dto = new AssessmentPerformanceDto();
        dto.setProcessInsId(approvalModelDto.getProcessInsId());
        dto.setProjectId(approvalModelDto.getProjectId());
        dto.setActivityId(approvalModelDto.getActivityId());
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(approvalModelDto.getActivityId());
        if (boxReActivityDto != null) {
            dto.setActivityName(boxReActivityDto.getCnName());
        }
        dto.setBoxId(approvalModelDto.getBoxId());
        dto.setByExaminePeople(byExaminePeople);
        dto.setRemarks(remarks);
        ProjectPlanDetails projectPlanDetails = null;
        if (StringUtils.isNotBlank(approvalModelDto.getProcessInsId())) {
            projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(approvalModelDto.getProcessInsId());
        }
        return savePerformanceBase(dto, JSONObject.parseArray(chksScore, AssessmentPerformanceDetailDto.class), projectPlanDetails);
    }


    /**
     * 获取目标节点可以查看的节点  (筛选根据bpm中排序顺序来判断,即当低于他的顺序视为可以查看[相当于下级])
     *
     * @param boxId
     * @param boxReActivitiId
     * @return
     */
    public List<BoxReActivityDto> getPerformanceNext(Integer boxId, Integer boxReActivitiId) {
        List<BoxReActivityDto> targetList = new ArrayList<>(0);
        List<BoxReActivityDto> boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isEmpty(boxReActivityDtoList)) {
            return targetList;
        }
        BoxReActivityDto boxReActivityDto = null;
        if (boxReActivitiId != null) {
            boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(boxReActivitiId);
        }
        if (boxReActivityDto == null) {
            return targetList;
        }
        if (boxReActivityDto.getSortMultilevel() == null) {
            return targetList;
        }
        // 将 模型下所有节点中的我们目标节点给删除,并且对列表中的进行排序筛选
        Iterator<BoxReActivityDto> boxReActivityDtoIterator = boxReActivityDtoList.iterator();
        while (boxReActivityDtoIterator.hasNext()) {
            BoxReActivityDto next = boxReActivityDtoIterator.next();

            if (com.google.common.base.Objects.equal(next.getId(), boxReActivityDto.getId())) {//把集合中的自己删去
                boxReActivityDtoIterator.remove();
            }

            //没有在bpm中配置多级排序 那么则此节点删除
            if (next.getSortMultilevel() == null) {
                boxReActivityDtoIterator.remove();
            }

            //必须用int判断
            int sortMultilevel = boxReActivityDto.getSortMultilevel().intValue();
            int resultNumLevel = next.getSortMultilevel().intValue();
            if (resultNumLevel > sortMultilevel) {
                boxReActivityDtoIterator.remove();
            }
        }
        //将筛选剩余的放入集合中
        if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
            targetList.addAll(boxReActivityDtoList);
        }
        targetList.add(boxReActivityDto);
        return targetList;
    }


    public List<AssessmentItemDto> getAssessmentItemTemplate(Integer boxId, Integer boxReActivitiId, String assessmentKey) {
        if (StringUtils.isBlank(assessmentKey)) {
            return bpmRpcBoxService.getAssessmentItemList(boxId, boxReActivitiId);
        } else {
            List<AssessmentItemDto> assessmentItemList = bpmRpcBoxService.getAssessmentItemListByKey(boxId, boxReActivitiId, assessmentKey);
            return assessmentItemList;
        }
    }

    public List<AssessmentPerformanceDetailDto> getPerformanceDetailsByPerformanceId(Integer performanceId) {
        return performanceService.getPerformanceDetailByPerformanceIdList(performanceId);
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

    public void deletePerformanceDetailByIds(List<Integer> ids) {
        performanceService.deletePerformanceDetailByIds(ids);
    }

    public void deletePerformanceByIds(List<Integer> ids) {
        performanceService.deletePerformanceByIds(ids);
    }

    public void deleteResponsibilityById(String responsibilityId) {
        if (NumberUtils.isNumber(responsibilityId)) {
            bpmRpcProjectTaskService.deleteProjectTask(Integer.parseInt(responsibilityId));
        }
    }


    /**
     * 获取抽查人员的考核节点
     *
     * @param boxId
     * @return
     */
    public BoxReActivityDto getSpotBoxReActivityDto(Integer boxId) {
        return bpmRpcBoxService.getEndActivityByBoxId(boxId);
    }


    public BoxReDto getBoxReDtoByProcessInsId(String processInsId) {
        List<BoxApprovalLogVo> boxApprovalLogVoList = getBoxApprovalLogVoList(processInsId);
        if (CollectionUtils.isNotEmpty(boxApprovalLogVoList)) {
            Iterator<BoxApprovalLogVo> boxApprovalLogVoIterator = boxApprovalLogVoList.iterator();
            while (boxApprovalLogVoIterator.hasNext()) {
                BoxApprovalLogVo boxApprovalLogVo = boxApprovalLogVoIterator.next();
                if (boxApprovalLogVo.getBoxId() == null) {
                    continue;
                }
                BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxApprovalLogVo.getBoxId());
                if (boxReDto == null) {
                    continue;
                }
                return boxReDto;
            }
        }
        return null;
    }

    /**
     * 获取审批日志
     *
     * @param processInsId
     * @return
     */
    private List<BoxApprovalLogVo> getBoxApprovalLogVoList(String processInsId) {
        List<BoxApprovalLogVo> boxApprovalLogDtoList = new ArrayList<>();
        BootstrapTableVo bootstrapTableVo = bpmRpcProcessInsManagerService.getApprovalLogForApp(applicationConstant.getAppKey(), processInsId, 1, 10000);
        if (bootstrapTableVo != null) {
            if (CollectionUtils.isNotEmpty(bootstrapTableVo.getRows())) {
                Iterator it = bootstrapTableVo.getRows().iterator();
                while (it.hasNext()) {
                    Object object = it.next();
                    BoxApprovalLogVo boxApprovalLogDto = (BoxApprovalLogVo) object;
                    boxApprovalLogDtoList.add(boxApprovalLogDto);
                }
            }
        }
        return boxApprovalLogDtoList;
    }

    //1.当前节点任务是否已生成，已生成则不再重复生成 2.将比当前节点序号大的节点考核任务置位无效状态
    public void generateAssessmentTask(String processInsId, Integer boxId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws BpmException {
        //1.当前节点任务是否已生成，已生成则不再重复生成
        //2.将比当前节点序号大的节点考核任务置位无效状态
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        if (boxReDto != null && boxReDto.getBisLaunchCheck() == Boolean.TRUE) {
            ActivitiTaskNodeDto activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, commonService.thisUserAccount());
            if (activitiTaskNodeDto == null) return;
            BoxReActivityDto currentActivity = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, activitiTaskNodeDto.getCurrentStep());
            if (currentActivity == null || currentActivity.getBisViewChk() == Boolean.FALSE) return;
            Integer count = performanceService.getPerformanceCount(processInsId, currentActivity.getId(), ProjectStatusEnum.RUNING.getKey());
            if (count > 0) return;
            performanceService.deletePerformanceByProcessInsId(processInsId, currentActivity.getId());
            String checkBean = StringUtils.uncapitalize(AssessmentTaskService.class.getSimpleName());//默认生成考核任务服务方法
            checkBean = StringUtils.isNoneBlank(boxReDto.getCheckBean()) ? boxReDto.getCheckBean() : checkBean;
            checkBean = StringUtils.isNoneBlank(currentActivity.getCheckBean()) ? currentActivity.getCheckBean() : checkBean;
            AssessmentTaskInterface assessmentTaskBean = (AssessmentTaskInterface) SpringContextUtils.getBean(checkBean);
            assessmentTaskBean.createAssessmentTask(processInsId, currentActivity.getId(), taskId, byExamineUser, projectInfo, projectPlanDetails);
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
            stringBuilder.append("拷贝的模板数据不存在,因此无法继续粘贴,请咨询管理员.");
            return stringBuilder.toString();
        }
        List<AssessmentPerformanceDetailDto> assessmentProjectPerformanceDetailDtoList = performanceService.getPerformanceDetailByPerformanceIdList(copyId);
        if (CollectionUtils.isEmpty(assessmentProjectPerformanceDetailDtoList)) {
            stringBuilder.append("拷贝的模板数据的明细被删除,因此无法继续粘贴,请咨询管理员");
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
        if (copy == null || projectPerformanceDto == null) {
            return false;
        }
        if (Objects.equal(projectPerformanceDto.getExamineStatus(), ProjectStatusEnum.FINISH.getKey())) {
            return false;
        }
        List<AssessmentItemDto> assessmentItemDtoList = getAssessmentItemTemplate(projectPerformanceDto.getBoxId(), projectPerformanceDto.getActivityId(), projectPerformanceDto.getAssessmentKey());
        if (CollectionUtils.isEmpty(assessmentItemDtoList)) {
            return false;
        }
        if (copyList.size() != assessmentItemDtoList.size()) {
            return false;
        }
        List<AssessmentPerformanceDetailDto> detailByPerformanceIdList = performanceService.getPerformanceDetailByPerformanceIdList(projectPerformanceDto.getId());
        if (CollectionUtils.isNotEmpty(detailByPerformanceIdList)) {
            List<Integer> integerList = detailByPerformanceIdList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            performanceService.deletePerformanceDetailByIds(integerList);
        }
        List<AssessmentPerformanceDetailDto> detailDtoList = new ArrayList<>(copyList.size());
        for (int i = 0; i < copyList.size(); i++) {
            AssessmentItemDto assessmentItemDto = assessmentItemDtoList.get(i);
            AssessmentPerformanceDetailDto assessmentProjectPerformanceDetailDto = copyList.get(i);
            AssessmentPerformanceDetailDto detailDto = new AssessmentPerformanceDetailDto();
            BeanUtils.copyProperties(assessmentProjectPerformanceDetailDto, detailDto);
            detailDto.setId(null);
            detailDto.setPerformanceId(projectPerformanceDto.getId());
            detailDto.setContentId(assessmentItemDto.getId());
            detailDto.setCreator(processControllerComponent.getThisUser());
            Integer integer = performanceService.savePerformanceDetailDto(detailDto);
            detailDto.setId(integer);
            detailDtoList.add(detailDto);
        }
        projectPerformanceDto.setRemarks(copy.getRemarks());
        projectPerformanceDto.setExaminePeople(processControllerComponent.getThisUser());
        saveAssessmentServer(projectPerformanceDto, detailDtoList, projectPerformanceDto.getPlanDetailsId());
        return true;
    }

    /**
     * 获取抽查数据
     * @param id
     * @return
     */
    public BootstrapTableVo getPerformanceSpotListById(Integer id){
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        return performanceService.getPerformanceSpotListById(id,requestBaseParam.getLimit(),requestBaseParam.getLimit());
    }
}
