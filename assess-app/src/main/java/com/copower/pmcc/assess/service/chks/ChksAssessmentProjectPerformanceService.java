package com.copower.pmcc.assess.service.chks;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.AssessmentTaskInterface;
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
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Objects;
import com.google.common.collect.*;
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
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BaseService baseService;
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

    public ChksBootstrapTableVo getChksBootstrapTableVo(AssessmentProjectPerformanceDto where, String activityIds) {
        List<Integer> activityList = new ArrayList<>();
        if (StringUtils.isNotBlank(activityIds)) {
            activityList.addAll(FormatUtils.transformString2Integer(activityIds));
        }
        //超级管理员的情况
        boolean isAdmin = processControllerComponent.userIsAdmin(processControllerComponent.getThisUser());
        isAdmin = false;
        if (isAdmin) {
            if (StringUtils.isNotBlank(where.getProcessInsId())) {
                BoxReDto boxReDto = getBoxReDtoByProcessInsId(where.getProcessInsId());
                if (boxReDto != null) {
                    List<BoxReActivityDto> boxReActivityDtoList = null;
                    if (where.getActivityId() != null) {
                        boxReActivityDtoList = getAssessmentProjectPerformanceNext(boxReDto.getId(), where.getActivityId());
                    } else {
                        boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxReDto.getId());
                    }
                    if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
                        activityList.addAll(boxReActivityDtoList.stream().map(boxReActivityDto -> boxReActivityDto.getActivityId()).collect(Collectors.toList()));
                    }
                }
            }
        }
        //抽查人员 把 被抽查组的流程id取出来
        if (where.getBoxId() != null) {
            boolean spotCheck = getSpotCheck(where.getBoxId(), processControllerComponent.getThisUser());
            if (spotCheck) {
                List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(new AssessmentProjectPerformanceQuery(where.getBoxId()));
                if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
                    Iterator<AssessmentProjectPerformanceDto> iterator = assessmentProjectPerformanceDtoList.iterator();
                    while (iterator.hasNext()) {
                        AssessmentProjectPerformanceDto projectPerformanceDto = iterator.next();
                        if (projectPerformanceDto.getSpotActivityId() == null || projectPerformanceDto.getSpotActivityId() == 0) {
                            continue;
                        }
                        activityList.add(projectPerformanceDto.getActivityId());
                    }
                }
            }
        }
        if (where.getActivityId() == null && CollectionUtils.isEmpty(activityList)) {
            where.setExaminePeople(processControllerComponent.getThisUser());
        }
        //去重
        if (CollectionUtils.isNotEmpty(activityList)) {
            activityList = activityList.stream().distinct().collect(Collectors.toList());
        }
        where.setAppKey(applicationConstant.getAppKey());
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
     * 获取当前登陆人是否是巡查或者抽查 对象
     *
     * @param boxId   模型 id
     * @param creator 当前登陆人
     * @return
     */
    private boolean getSpotCheck(Integer boxId, String creator) {
        if (boxId == null) {
            return false;
        }
        if (StringUtils.isEmpty(creator)) {
            return false;
        }
        BoxReActivityDto boxReActivityDto = getSpotBoxReActivityDto(boxId);
        List<String> creates = null;
        try {
            creates = bpmRpcBoxService.getRoleUserByActivityId(boxReActivityDto.getId());
        } catch (BpmException e) {
        }
        if (CollectionUtils.isEmpty(creates)) {
            return false;
        }
        return creates.contains(creator);
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
        //节点是被抽查节点  那么可以查看所有的节点
        if (boxReActivityDto.getBisSpotCheck() != null) {
            if (Objects.equal(boxReActivityDto.getBisSpotCheck(), Boolean.TRUE)) {
//                return boxReActivityDtoList;
            }
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

    public List<KeyValueDto> getExaminePeopleList(String processInsId, Integer boxId) {
        List<String> accountList = new ArrayList<>();
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery(boxId);
        query.setProcessInsId(processInsId);
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
            Iterator<AssessmentProjectPerformanceDto> iterator = assessmentProjectPerformanceDtoList.iterator();
            while (iterator.hasNext()) {
                AssessmentProjectPerformanceDto next = iterator.next();
                String examinePeople = next.getExaminePeople();
                if (StringUtils.isBlank(examinePeople)) {
                    continue;
                }
                if (!accountList.contains(examinePeople)) {
                    accountList.add(examinePeople);
                }
            }
        }
        List<KeyValueDto> keyValueDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(accountList)) {
            Iterator<String> iterator = accountList.iterator();
            while (iterator.hasNext()) {
                String s = iterator.next();
                keyValueDtoList.add(new KeyValueDto(s, publicService.getUserNameByAccount(s)));
            }
        }
        return keyValueDtoList;
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
//        List<BoxReActivityDto> boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
//        if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
//            Iterator<BoxReActivityDto> iterator = boxReActivityDtoList.iterator();
//            while (iterator.hasNext()) {
//                BoxReActivityDto boxReActivityDto = iterator.next();
//                if (boxReActivityDto.getBisSpotCheck() != null) {
//                    if (boxReActivityDto.getBisSpotCheck()) {
//                        return boxReActivityDto;
//                    }
//                }
//            }
//        }
//        return null;
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


    /**
     * 获取最初的申请人
     *
     * @param processInsId
     * @return
     */
    private BoxApprovalLogVo getBisApplyBoxApprovalLogVoList(String processInsId) {
        List<BoxApprovalLogVo> boxApprovalLogVoList = getBoxApprovalLogVoList(processInsId);
        if (CollectionUtils.isNotEmpty(boxApprovalLogVoList)) {
            Iterator<BoxApprovalLogVo> iterator = boxApprovalLogVoList.iterator();
            while (iterator.hasNext()) {
                BoxApprovalLogVo boxApprovalLogVo = iterator.next();
                if (boxApprovalLogVo.getBisApply() != null) {
                    if (boxApprovalLogVo.getBisApply()) {
                        return boxApprovalLogVo;
                    }
                }
            }
        }
        return null;
    }

    //1.当前节点任务是否已生成，已生成则不再重复生成 2.将比当前节点序号大的节点考核任务置位无效状态
    public void generateAssessmentTask(String processInsId, Integer boxId, String taskId, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws BpmException {
        //1.当前节点任务是否已生成，已生成则不再重复生成
        //2.将比当前节点序号大的节点考核任务置位无效状态
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        if (boxReDto == null) {
            return;
        }
        if (boxReDto.getBisLaunchCheck() != Boolean.TRUE) {
            return;
        }
        if (boxReDto.getBisLaunchCheck() != Boolean.TRUE) {
            return;
        }
        BoxApprovalLogVo boxApprovalLogVo = getBisApplyBoxApprovalLogVoList(processInsId);
        ActivitiTaskNodeDto activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, commonService.thisUserAccount());
        BoxReActivityDto currentActivity = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, activitiTaskNodeDto.getCurrentStep());
        if (currentActivity == null || currentActivity.getBisViewChk() == Boolean.FALSE) {
            return;
        }
        List<AssessmentItemDto> assessmentItemList = bpmRpcBoxService.getAssessmentItemList(boxId, currentActivity.getId());
        if (CollectionUtils.isEmpty(assessmentItemList)) {
            return;
        }
        List<BoxReActivityDto> reActivityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isNotEmpty(reActivityDtos)) {
            List<BoxReActivityDto> filter = LangUtils.filter(reActivityDtos, o -> {
                return o.getSortMultilevel() > currentActivity.getSortMultilevel();
            });
            if (CollectionUtils.isNotEmpty(filter)) {
                AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
                query.setProcessInsId(processInsId);
                query.setActivityIds(LangUtils.transform(filter, o -> o.getId()));
                List<AssessmentProjectPerformanceDto> performanceDtos = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
                if (CollectionUtils.isNotEmpty(performanceDtos))
                    chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(LangUtils.transform(performanceDtos, o -> o.getId()));
            }
        }

        //第一次生成考核任务
        int count = chksRpcAssessmentService.getAssessmentProjectPerformanceCount(applicationConstant.getAppKey(), projectInfo.getId(), processInsId, currentActivity.getId(), taskId);
        if (count > 0) {
            return;
        }
        String checkBean = StringUtils.uncapitalize(AssessmentTaskService.class.getSimpleName());//默认生成考核任务服务方法
        checkBean = StringUtils.isNoneBlank(boxReDto.getCheckBean()) ? boxReDto.getCheckBean() : checkBean;
        checkBean = StringUtils.isNoneBlank(currentActivity.getCheckBean()) ? currentActivity.getCheckBean() : checkBean;
        AssessmentTaskInterface assessmentTaskBean = (AssessmentTaskInterface) SpringContextUtils.getBean(checkBean);
        assessmentTaskBean.createAssessmentTask(processInsId, currentActivity.getId(), taskId, boxApprovalLogVo.getCreator(), projectInfo, projectPlanDetails);

        //第二次生成考核任务
        count = chksRpcAssessmentService.getAssessmentProjectPerformanceCount(applicationConstant.getAppKey(), projectInfo.getId(), processInsId, currentActivity.getId(), taskId);
        if (count > 0) {
            return;
        }
        //先清理掉当前节点的任务
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProcessInsId(processInsId);
        query.setActivityId(currentActivity.getId());
        List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(performanceDtoList)) {
            performanceDtoList.forEach(o -> chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId())));
        }
        checkBean = StringUtils.uncapitalize(AssessmentTaskService.class.getSimpleName());//默认生成考核任务服务方法
        checkBean = StringUtils.isNoneBlank(boxReDto.getCheckBean()) ? boxReDto.getCheckBean() : checkBean;
        checkBean = StringUtils.isNoneBlank(currentActivity.getCheckBean()) ? currentActivity.getCheckBean() : checkBean;
        assessmentTaskBean = (AssessmentTaskInterface) SpringContextUtils.getBean(checkBean);
        assessmentTaskBean.createAssessmentTask(processInsId, currentActivity.getId(), taskId, boxApprovalLogVo.getCreator(), projectInfo, projectPlanDetails);
    }


    public void generateAssessmentTask(String processInsId, Integer boxId, String taskId) throws BpmException {
        generateAssessmentTask(processInsId, boxId, taskId, null, null);
    }

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
                        //当考核任务已经设置了考核人时此时此刻不再设置考核人
                        if (StringUtils.isBlank(performanceDto.getExaminePeople())) {
                            performanceDto.setExaminePeople(processControllerComponent.getThisUser());
                            performanceDto.setExaminePeopleName(processControllerComponent.getThisUserInfo().getUserName());
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
                //清理掉当前节点生成的考核任务
                AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
                query.setProcessInsId(approvalModelDto.getProcessInsId());
                query.setActivityId(approvalModelDto.getActivityId());
                List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
                if (CollectionUtils.isNotEmpty(performanceDtoList)) {
                    performanceDtoList.forEach(o -> chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId())));
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
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProcessInsId(processInsId);
        query.setExaminePeople(userAccount);
        List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isEmpty(performanceDtoList)) {
            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
            projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
            projectResponsibilityDto.setProcessInsId(processInsId);
            List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
            if (CollectionUtils.isNotEmpty(projectTaskList)) {
                projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
            }
        }
    }

    /**
     * 清理流程所有考核任务
     *
     * @param processInsId
     */
    public void clearAssessmentProjectPerformanceAll(String processInsId) {
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProcessInsId(processInsId);
        List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(performanceDtoList)) {
            performanceDtoList.forEach(o -> chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId())));
        }
        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProcessInsId(processInsId);
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
        if (CollectionUtils.isNotEmpty(projectTaskList)) {
            projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
        }
    }
}
