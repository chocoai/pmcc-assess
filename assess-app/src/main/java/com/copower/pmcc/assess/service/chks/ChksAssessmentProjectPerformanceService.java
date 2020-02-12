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
        List<Integer> activityList = null;
        if (StringUtils.isNotBlank(activityIds)) {
            activityList = FormatUtils.transformString2Integer(activityIds);
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
     * 保存考核信息
     *
     * @param assessmentProjectPerformanceDto
     * @param detailDtoList
     */
    public Integer saveAssessmentServer(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, List<AssessmentProjectPerformanceDetailDto> detailDtoList, Integer planDetailsId) {
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
        if (assessmentProjectPerformanceDto.getProjectId() != null) {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(assessmentProjectPerformanceDto.getProjectId());
            if (projectInfo != null) {
                assessmentProjectPerformanceDto.setProjectName(projectInfo.getProjectName());
            }
        }
        assessmentProjectPerformanceDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
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
            projectPerformanceDto.setPlanDetailsId(projectPlanDetails.getId());
            projectPerformanceDto.setPlanId(projectPlanDetails.getPlanId());
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
            if (StringUtils.isNotBlank(projectPlanDetails.getProjectPhaseName())) {
                projectPerformanceDto.setBusinessKey(projectPlanDetails.getProjectPhaseName());
            }
            //业务标识
            if (projectPlanDetails.getProjectWorkStageId() != null) {
                ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
                if (projectWorkStage != null) {
                    if (StringUtils.isNotBlank(projectPerformanceDto.getBusinessKey())) {
                        projectPerformanceDto.setBusinessKey(String.join("-", projectPerformanceDto.getBusinessKey(), projectWorkStage.getWorkStageName()));
                    } else {
                        projectPerformanceDto.setBusinessKey(projectWorkStage.getWorkStageName());
                    }
                }
            }
            ProjectInfo projectInfo = null;
            if (projectPlanDetails.getProjectId() != null) {
                if (projectPerformanceDto.getProjectId() == null) {
                    projectPerformanceDto.setProjectId(projectPlanDetails.getProjectId());
                }
                projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
            }
            if (projectInfo != null) {
                projectPerformanceDto.setProjectName(projectInfo.getProjectName());
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

    public List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, Integer boxReActivitiId, List<BoxReActivityDto> boxReActivityDtoList, boolean spotCheck) {
        if (spotCheck) {
            return getAssessmentProjectPerformanceNext(boxId, boxReActivityDtoList);
        }
        if (!spotCheck) {
            if (boxReActivitiId != null) {
                return getAssessmentProjectPerformanceNext(boxId, boxReActivitiId);
            }
            if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
                return getAssessmentProjectPerformanceNext(boxId, boxReActivityDtoList);
            }
        }
        return new ArrayList<>();
    }


    /**
     * 获取目标节点可以查看的节点  (筛选根据bpm中排序顺序来判断,即当低于他的顺序视为可以查看[相当于下级])
     *
     * @param boxId
     * @param boxReActivityDtos
     * @return
     */
    public List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, List<BoxReActivityDto> boxReActivityDtos) {
        Integer boxReActivitiId = null;
        if (CollectionUtils.isNotEmpty(boxReActivityDtos)) {
            Ordering<BoxReActivityDto> ordering = Ordering.from(new Comparator<BoxReActivityDto>() {
                @Override
                public int compare(BoxReActivityDto o1, BoxReActivityDto o2) {
                    if (o1.getSortMultilevel() == null || o2.getSortMultilevel() == null) {
                        return 0;
                    }
                    return o1.getSortMultilevel().compareTo(o2.getSortMultilevel());
                }
            });
            Collections.sort(boxReActivityDtos, ordering.reverse());
            //取列表中最大的
            BoxReActivityDto boxReActivityDto = boxReActivityDtos.get(0);
            boxReActivitiId = boxReActivityDto.getId();
        }
        return getAssessmentProjectPerformanceNext(boxId, boxReActivitiId);
    }

    /**
     * 获取目标节点可以查看的节点  (筛选根据bpm中排序顺序来判断,即当低于他的顺序视为可以查看[相当于下级])
     *
     * @param boxId
     * @param boxReActivitiId
     * @return
     */
    public List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, Integer boxReActivitiId) {
        boolean spotCheck = getSpotCheck(boxId, commonService.thisUserAccount());
        List<BoxReActivityDto> targetList = new ArrayList<>(0);
        List<BoxReActivityDto> boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isEmpty(boxReActivityDtoList)) {
            return targetList;
        }
        if (spotCheck) {//巡查或者抽查人员可以看到目标模型下的所有节点
            return boxReActivityDtoList;
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

    public BoxReActivityDto getBoxReActivityDtoByProcessInsId(String processInsId, Integer projectId, Integer boxId) {
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        if (boxId != null && boxId != 0) {
            query.setBoxId(boxId);
        }
        query.setProcessInsId(processInsId);
        query.setProjectId(projectId);
        query.setAppKey(applicationConstant.getAppKey());
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
            List<AssessmentProjectPerformanceDto> collect = assessmentProjectPerformanceDtoList.stream().filter(assessmentProjectPerformanceDto -> assessmentProjectPerformanceDto.getActivityId() != null && Objects.equal(commonService.thisUserAccount(), assessmentProjectPerformanceDto.getExaminePeople())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)) {
                return bpmRpcBoxService.getBoxreActivityInfoById(collect.stream().findFirst().get().getActivityId());
            }
        }
        return null;
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


    /**
     * 获取当前登陆人是否是巡查或者抽查 对象
     *
     * @param boxId   模型 id
     * @param creator 当前登陆人
     * @return
     */
    public boolean getSpotCheck(Integer boxId, String creator) {
        if (boxId == null) {
            return false;
        }
        if (StringUtils.isEmpty(creator)) {
            return false;
        }
        List<String> creates = getSpotCheckUserAccounts(boxId);
        if (CollectionUtils.isEmpty(creates)) {
            return false;
        }
        return creates.contains(creator);
    }


    /**
     * 获取流程抽查人员账号
     *
     * @param boxId
     * @return
     */
    public List<String> getSpotCheckUserAccounts(Integer boxId) {
        BoxReActivityDto boxReActivityDto = getSpotBoxReActivityDto(boxId);
        try {
            if (boxReActivityDto != null) {
                List<String> userAccounts = Lists.newArrayList();
                List<String> list = bpmRpcBoxService.getRoleUserByActivityId(boxReActivityDto.getId());
                if (CollectionUtils.isNotEmpty(list)) userAccounts.addAll(list);
                return userAccounts;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
        return null;
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
        List<BoxReActivityDto> boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
            Iterator<BoxReActivityDto> iterator = boxReActivityDtoList.iterator();
            while (iterator.hasNext()) {
                BoxReActivityDto boxReActivityDto = iterator.next();
                if (boxReActivityDto.getBisSpotCheck() != null) {
                    if (boxReActivityDto.getBisSpotCheck()) {
                        return boxReActivityDto;
                    }
                }
            }
        }
        return null;
        //return bpmRpcBoxService.getEndActivityByBoxId(boxId);
    }




    public BoxReDto getBoxReDto(String processInsId) {
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
                        if (StringUtils.isBlank(performanceDto.getExaminePeople())){
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
