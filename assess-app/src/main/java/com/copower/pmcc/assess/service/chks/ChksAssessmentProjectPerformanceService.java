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
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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
public class ChksAssessmentProjectPerformanceService {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ChksRpcAssessmentService chksRpcAssessmentService;
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

    public ChksBootstrapTableVo getChksBootstrapTableVo(AssessmentProjectPerformanceDto where, Integer boxId, Integer activityId) {
        //1.管理员与抽查组可查看所有质量考核数据
        //2.当前人只能查看排序比当前节点小的质量考核数据
        List<Integer> activityList = Lists.newArrayList();
        if (!processControllerComponent.userIsAdmin(commonService.thisUserAccount()) && !isSpotGroupUser(boxId, commonService.thisUserAccount())) {
            List<BoxReActivityDto> activityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
            BoxReActivityDto currActivity = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
            if (CollectionUtils.isNotEmpty(activityDtos) || currActivity != null) {
                for (BoxReActivityDto activityDto : activityDtos) {
                    if (activityDto.getSortMultilevel() <= currActivity.getSortMultilevel())
                        activityList.add(activityDto.getId());
                }
            }
        }
        ChksBootstrapTableVo chksBootstrapTableVo = new ChksBootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        AssessmentProjectPerformanceDtoAndDetail dtoAndDetail = chksRpcAssessmentService.getAssessmentProjectPerformanceDtosByExample(where, activityList, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (dtoAndDetail != null) {
            chksBootstrapTableVo = dtoAndDetail.getChksBootstrapTableVo();
        }
        return chksBootstrapTableVo;
    }

    /**
     * 是否为抽查组成员
     *
     * @param boxId       模型 id
     * @param userAccount 当前登陆人
     * @return
     */
    private boolean isSpotGroupUser(Integer boxId, String userAccount) {
        if (boxId == null || StringUtils.isEmpty(userAccount)) {
            return false;
        }
        try {
            BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getEndActivityByBoxId(boxId);
            List<String> list = bpmRpcBoxService.getRoleUserByActivityId(boxReActivityDto.getId());
            if (CollectionUtils.isEmpty(list)) {
                return false;
            }
            return list.contains(userAccount);
        } catch (BpmException e) {
            return false;
        }
    }

    /**
     * 保存考核信息
     *
     * @param assessmentProjectPerformanceDto
     * @param detailDtoList
     */
    public Integer saveAssessmentServer(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, List<AssessmentProjectPerformanceDetailDto> detailDtoList, Integer planDetailsId) {
        //修改情况  处理
        if (assessmentProjectPerformanceDto.getId() != null && assessmentProjectPerformanceDto.getId() != 0) {
            AssessmentProjectPerformanceDto target = getAssessmentProjectPerformanceById(assessmentProjectPerformanceDto.getId());
            String remarks = assessmentProjectPerformanceDto.getRemarks();
            String examineStatus = assessmentProjectPerformanceDto.getExamineStatus();
            String examineUrl = assessmentProjectPerformanceDto.getExamineUrl();
            String tableName = assessmentProjectPerformanceDto.getTableName();
            Integer tableId = assessmentProjectPerformanceDto.getTableId();
            String byExaminePeople = assessmentProjectPerformanceDto.getByExaminePeople();
            String examinePeople = assessmentProjectPerformanceDto.getExaminePeople();
            BeanCopyHelp.copyPropertiesIgnoreNull(target, assessmentProjectPerformanceDto);
            if (assessmentProjectPerformanceDto.getValidScore() == null) {
                assessmentProjectPerformanceDto.setValidScore(new BigDecimal(0));
            }
            if (assessmentProjectPerformanceDto.getExamineScore() == null) {
                assessmentProjectPerformanceDto.setExamineScore(new BigDecimal(0));
            }
            if (StringUtils.isNotBlank(examineStatus)) {
                assessmentProjectPerformanceDto.setExamineStatus(examineStatus);
            }
            if (StringUtils.isNotBlank(remarks)) {
                assessmentProjectPerformanceDto.setRemarks(remarks);
            }
            if (StringUtils.isNotBlank(examineUrl)) {
                assessmentProjectPerformanceDto.setExamineUrl(examineUrl);
            }
            if (StringUtils.isNotBlank(tableName)) {
                assessmentProjectPerformanceDto.setTableName(tableName);
            }
            if (StringUtils.isNotBlank(examinePeople)) {
                assessmentProjectPerformanceDto.setExaminePeople(examinePeople);
            }
            if (StringUtils.isNotBlank(byExaminePeople)) {
                assessmentProjectPerformanceDto.setByExaminePeople(byExaminePeople);
            }
            if (tableId != null) {
                assessmentProjectPerformanceDto.setTableId(tableId);
            }
        }
        ProjectPlanDetails projectPlanDetails = null;
        if (StringUtils.isNotBlank(assessmentProjectPerformanceDto.getProcessInsId())) {
            projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(assessmentProjectPerformanceDto.getProcessInsId());
        }
        if (projectPlanDetails == null) {
            if (planDetailsId != null) {
                projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            }
        }
        return saveAssessmentProjectPerformanceBase(assessmentProjectPerformanceDto, detailDtoList, projectPlanDetails);
    }

    /**
     * 抽取的保存考核信息
     *
     * @param projectPerformanceDto
     * @param detailDtos
     * @param projectPlanDetails
     */
    private Integer saveAssessmentProjectPerformanceBase(AssessmentProjectPerformanceDto projectPerformanceDto, List<AssessmentProjectPerformanceDetailDto> detailDtos, ProjectPlanDetails projectPlanDetails) {
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
            Iterator<AssessmentProjectPerformanceDetailDto> detailDtoIterator = detailDtos.iterator();
            while (detailDtoIterator.hasNext()) {
                AssessmentProjectPerformanceDetailDto performanceDetailDto = detailDtoIterator.next();
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
        return chksRpcAssessmentService.saveAssessmentProjectPerformanceBase(projectPerformanceDto, detailDtos);
    }

    public Integer saveAssessmentProjectPerformance(ApprovalModelDto approvalModelDto, String chksScore, String remarks, String byExaminePeople) {
        //添加主表
        AssessmentProjectPerformanceDto dto = new AssessmentProjectPerformanceDto();
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
        return saveAssessmentProjectPerformanceBase(dto, JSONObject.parseArray(chksScore, AssessmentProjectPerformanceDetailDto.class), projectPlanDetails);
    }


    /**
     * 获取目标节点可以查看的节点  (筛选根据bpm中排序顺序来判断,即当低于他的顺序视为可以查看[相当于下级])
     *
     * @param boxId
     * @param boxReActivitiId
     * @return
     */
    public List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, Integer boxReActivitiId) {
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
            List<AssessmentItemDto> assessmentItemList = bpmRpcBoxService.getAssessmentItemList(boxId, boxReActivitiId, assessmentKey);
            if (CollectionUtils.isNotEmpty(assessmentItemList)) {
                return assessmentItemList;
            }
            assessmentItemList = bpmRpcBoxService.getAssessmentItemList(boxId, boxReActivitiId);
            Iterator<AssessmentItemDto> iterator = assessmentItemList.iterator();
            while (iterator.hasNext()) {
                AssessmentItemDto next = iterator.next();
                if (!Objects.equal(next.getAssessmentKey(), assessmentKey)) {
                    iterator.remove();
                }
            }
            return assessmentItemList;
        }
    }

    /**
     * 获取考核数据
     *
     * @param query
     * @return
     */
    public List<AssessmentProjectPerformanceDto> getAssessmentProjectPerformanceDtoList(AssessmentProjectPerformanceQuery query) {
        query.setAppKey(applicationConstant.getAppKey());
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtos = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtos)) {
            Iterator<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoIterator = assessmentProjectPerformanceDtos.iterator();
            while (assessmentProjectPerformanceDtoIterator.hasNext()) {
                AssessmentProjectPerformanceDto vo = assessmentProjectPerformanceDtoIterator.next();
                if (StringUtils.isNotBlank(vo.getByExaminePeople())) {
                    vo.setByExaminePeopleName(publicService.getUserNameByAccount(vo.getByExaminePeople()));
                }
                if (StringUtils.isNotBlank(vo.getExaminePeople())) {
                    vo.setExaminePeopleName(publicService.getUserNameByAccount(vo.getExaminePeople()));
                }
                boolean remove = false;
                if (CollectionUtils.isNotEmpty(vo.getDetailList())) {
                    Iterator<AssessmentProjectPerformanceDetailDto> detailDtoIterator = vo.getDetailList().iterator();
                    while (detailDtoIterator.hasNext()) {
                        AssessmentProjectPerformanceDetailDto performanceDetailDto = detailDtoIterator.next();
                        if (performanceDetailDto.getContentId() != null) {
                            AssessmentItemDto assessmentItem = bpmRpcBoxService.getAssessmentItem(performanceDetailDto.getContentId());
                            if (assessmentItem != null) {
                                if (StringUtils.isNotBlank(query.getAssessmentKey())) {
                                    if (!Objects.equal(query.getAssessmentKey(), assessmentItem.getAssessmentKey())) {
                                        remove = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (remove) {
                    assessmentProjectPerformanceDtoIterator.remove();
                }
            }
        }
        chksRpcAssessmentService.sortAssessmentProjectPerformanceDtoList(assessmentProjectPerformanceDtos);
        return assessmentProjectPerformanceDtos;
    }

    public List<AssessmentProjectPerformanceDetailDto> getAssessmentProjectPerformanceDetailByPerformanceIdList(Integer performanceId) {
        return chksRpcAssessmentService.getAssessmentProjectPerformanceDetailByPerformanceIdList(performanceId);
    }

    public AssessmentProjectPerformanceDto getAssessmentProjectPerformanceById(Integer id) {
        return chksRpcAssessmentService.getAssessmentProjectPerformanceById(id);
    }


    public void updateAssessmentProjectPerformanceDto(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, boolean updateNull) {
        chksRpcAssessmentService.updateAssessmentProjectPerformanceDto(assessmentProjectPerformanceDto, updateNull);
    }

    public void updateAssessmentProjectPerformanceDetailDto(List<AssessmentProjectPerformanceDetailDto> detailDtoList, boolean updateNull) {
        chksRpcAssessmentService.updateAssessmentProjectPerformanceDetailDto(detailDtoList, updateNull);
    }

    public void deleteAssessmentProjectPerformanceDetailByIds(List<Integer> ids) {
        chksRpcAssessmentService.deleteAssessmentProjectPerformanceDetailByIds(ids);
    }

    public void deleteAssessmentProjectPerformanceByIds(List<Integer> ids) {
        chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(ids);
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
     * 当处于详情的时候对于流程节点列表的获取
     *
     * @param processInsId
     * @return
     */
    public List<BoxReActivityDto> getActivityDtoListByProcessInsId(String processInsId, Integer boxId) {
        List<BoxReActivityDto> boxReActivityDtoList = new ArrayList<>();
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProcessInsId(processInsId);
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
            Iterator<AssessmentProjectPerformanceDto> iterator = assessmentProjectPerformanceDtoList.iterator();
            while (iterator.hasNext()) {
                AssessmentProjectPerformanceDto performanceDto = iterator.next();
                if (Objects.equal(performanceDto.getExaminePeople(), processControllerComponent.getThisUser())) {
                    Integer activityId = performanceDto.getActivityId();
                    if (activityId == null) {
                        continue;
                    }
                    BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
                    if (boxReActivityDto == null) {
                        continue;
                    }
                    if (boxId == null) {
                        boxId = boxReActivityDto.getBoxId();
                    }
                    //目标人能够查看的下级节点绩效数据
                    List<BoxReActivityDto> boxReActivityDtos = getAssessmentProjectPerformanceNext(boxId, activityId);
                    if (CollectionUtils.isNotEmpty(boxReActivityDtos)) {
                        boxReActivityDtoList.addAll(boxReActivityDtos);
                    }
                    boxReActivityDtoList.add(boxReActivityDto);
                }
            }
        }
        //去重
        if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
            Map<Integer, BoxReActivityDto> map = new HashMap<>();
            Iterator<BoxReActivityDto> iterator = boxReActivityDtoList.iterator();
            while (iterator.hasNext()) {
                BoxReActivityDto boxReActivityDto = iterator.next();
                map.put(boxReActivityDto.getId(), boxReActivityDto);
            }
            boxReActivityDtoList = map.values().stream().collect(Collectors.toList());
        }
        return boxReActivityDtoList;
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
            BoxReActivityDto currentActivity = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, activitiTaskNodeDto.getCurrentStep());
            if (currentActivity == null || currentActivity.getBisViewChk() == Boolean.FALSE) return;
            List<AssessmentItemDto> assessmentItemList = bpmRpcBoxService.getAssessmentItemList(boxId, currentActivity.getId());
            if (CollectionUtils.isEmpty(assessmentItemList)) return;
            Integer count = chksRpcAssessmentService.getAssessmentProjectPerformanceCount(applicationConstant.getAppKey(), projectInfo.getId(), processInsId, currentActivity.getId(), taskId);
            if (count > 0) return;
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
                AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                query.setTaskId(approvalModelDto.getTaskId());
                query.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
                List<AssessmentProjectPerformanceDto> performanceDtos = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
                if (CollectionUtils.isNotEmpty(performanceDtos)) {
                    for (AssessmentProjectPerformanceDto performanceDto : performanceDtos) {
                        if (StringUtils.isBlank(performanceDto.getExaminePeople())) { //当考核任务已经设置了考核人时此时此刻不再设置考核人
                            performanceDto.setExaminePeople(processControllerComponent.getThisUser());
                        }
                        chksRpcAssessmentService.updateAssessmentProjectPerformanceDto(performanceDto, false);
                    }
                }
                query = new AssessmentProjectPerformanceQuery();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setExaminePeople(processControllerComponent.getThisUser());
                performanceDtos = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
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
                        projectPlanResponsibility.setProjectId(projectPlanDetails.getProjectId());
                        projectPlanResponsibility.setPlanId(projectPlanDetails.getPlanId());
                        projectPlanResponsibility.setPlanDetailsId(projectPlanDetails.getId());
                        projectPlanResponsibility.setBusinessKey(AssessmentCommonService.PROJECT_TASK_BUSINESS_KEY_PERFORMANCE);
                        projectPlanResponsibility.setPlanDetailsName(String.format("(考核)%s[%s]", approvalModelDto.getWorkStage(), approvalModelDto.getWorkPhase()));
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
                AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
                if (CollectionUtils.isNotEmpty(performanceDtoList)) {
                    performanceDtoList.forEach(o -> chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId())));
                }
                query = new AssessmentProjectPerformanceQuery();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
                query.setExaminePeople(processControllerComponent.getThisUser());
                List<AssessmentProjectPerformanceDto> list = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
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
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProcessInsId(processInsId);
        query.setExaminePeople(userAccount);
        List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
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
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProcessInsId(processInsId);
        List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(performanceDtoList)) {
            chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId()));
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
        AssessmentProjectPerformanceDto copyObj = chksRpcAssessmentService.getAssessmentProjectPerformanceById(copyId);
        if (copyObj == null) {
            stringBuilder.append("拷贝的模板数据不存在,因此无法继续粘贴,请咨询管理员.");
            return stringBuilder.toString();
        }
        List<AssessmentProjectPerformanceDetailDto> assessmentProjectPerformanceDetailDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDetailByPerformanceIdList(copyId);
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
            boolean check = pasteData(copyObj, assessmentProjectPerformanceDetailDtoList, chksRpcAssessmentService.getAssessmentProjectPerformanceById(id));
            index++;
            if (check) {
                stringBuilder.append("第").append(index).append("条数据粘贴成功 \r");
            } else {
                stringBuilder.append("第").append(index).append("条数据粘贴失败 \r");
            }
        }
        return stringBuilder.toString();
    }

    private boolean pasteData(AssessmentProjectPerformanceDto copy, List<AssessmentProjectPerformanceDetailDto> copyList, AssessmentProjectPerformanceDto projectPerformanceDto) {
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
        List<AssessmentProjectPerformanceDetailDto> detailByPerformanceIdList = chksRpcAssessmentService.getAssessmentProjectPerformanceDetailByPerformanceIdList(projectPerformanceDto.getId());
        if (CollectionUtils.isNotEmpty(detailByPerformanceIdList)) {
            List<Integer> integerList = detailByPerformanceIdList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            chksRpcAssessmentService.deleteAssessmentProjectPerformanceDetailByIds(integerList);
        }
        List<AssessmentProjectPerformanceDetailDto> detailDtoList = new ArrayList<>(copyList.size());
        for (int i = 0; i < copyList.size(); i++) {
            AssessmentItemDto assessmentItemDto = assessmentItemDtoList.get(i);
            AssessmentProjectPerformanceDetailDto assessmentProjectPerformanceDetailDto = copyList.get(i);
            AssessmentProjectPerformanceDetailDto detailDto = new AssessmentProjectPerformanceDetailDto();
            BeanUtils.copyProperties(assessmentProjectPerformanceDetailDto, detailDto);
            detailDto.setId(null);
            detailDto.setPerformanceId(projectPerformanceDto.getId());
            detailDto.setContentId(assessmentItemDto.getId());
            detailDto.setCreator(processControllerComponent.getThisUser());
            Integer integer = chksRpcAssessmentService.saveAssessmentProjectPerformanceDetailDto(detailDto);
            detailDto.setId(integer);
            detailDtoList.add(detailDto);
        }
        projectPerformanceDto.setRemarks(copy.getRemarks());
        projectPerformanceDto.setExaminePeople(processControllerComponent.getThisUser());
        saveAssessmentServer(projectPerformanceDto, detailDtoList, projectPerformanceDto.getPlanDetailsId());
        return true;
    }
}
