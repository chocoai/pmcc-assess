package com.copower.pmcc.assess.service.chks;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.common.enums.chks.ChksCustomizeEnum;
import com.copower.pmcc.assess.common.enums.chks.ChksRuningEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
import com.copower.pmcc.assess.proxy.face.AssessmentTaskInterface;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
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
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
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
    private ProjectPlanService projectPlanService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    @Autowired
    private ChksCustomerAssessmentPlanDetailService chksCustomerAssessmentPlanDetailService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
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
    public void saveAssessmentServer(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, List<AssessmentProjectPerformanceDetailDto> detailDtoList, Integer planDetailsId) {
        if (assessmentProjectPerformanceDto.getId() != null && assessmentProjectPerformanceDto.getId() != 0) {
            AssessmentProjectPerformanceDto target = chksRpcAssessmentService.getAssessmentProjectPerformanceById(assessmentProjectPerformanceDto.getId());
            String remarks = assessmentProjectPerformanceDto.getRemarks();
            String examineStatus = assessmentProjectPerformanceDto.getExamineStatus();
            String examineUrl = assessmentProjectPerformanceDto.getExamineUrl();
            String tableName = assessmentProjectPerformanceDto.getTableName();
            Integer tableId = assessmentProjectPerformanceDto.getTableId();
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
        saveAssessmentProjectPerformanceBase(assessmentProjectPerformanceDto, detailDtoList, projectPlanDetails);
    }

    /**
     * 抽取的保存考核信息
     *
     * @param projectPerformanceDto
     * @param detailDtos
     * @param projectPlanDetails
     */
    private void saveAssessmentProjectPerformanceBase(AssessmentProjectPerformanceDto projectPerformanceDto, List<AssessmentProjectPerformanceDetailDto> detailDtos, ProjectPlanDetails projectPlanDetails) {
        projectPerformanceDto.setCreator(processControllerComponent.getThisUser());
        projectPerformanceDto.setExaminePeople(processControllerComponent.getThisUser());
        projectPerformanceDto.setExamineDate(new Date());
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
        chksRpcAssessmentService.saveAssessmentProjectPerformanceBase(projectPerformanceDto, detailDtos);
    }

    public void saveAssessmentProjectPerformance(ApprovalModelDto approvalModelDto, String chksScore, String remarks, String byExaminePeople) {
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
        saveAssessmentProjectPerformanceBase(dto, JSONObject.parseArray(chksScore, AssessmentProjectPerformanceDetailDto.class), projectPlanDetails);
    }

    public List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, Integer boxReActivitiId, List<BoxReActivityDto> boxReActivityDtoList, boolean spotCheck) {
        if (spotCheck) {
            return getAssessmentProjectPerformanceNext(boxId, null, spotCheck);
        }
        if (!spotCheck) {
            if (boxReActivitiId != null) {
                return getAssessmentProjectPerformanceNext(boxId, Lists.newArrayList(bpmRpcBoxService.getBoxreActivityInfoById(boxReActivitiId)), spotCheck);
            }
            if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
                return getAssessmentProjectPerformanceNext(boxId, boxReActivityDtoList, spotCheck);
            }
        }
        return new ArrayList<>();
    }

    /**
     * 获取目标节点可以查看的节点  (筛选根据bpm中排序顺序来判断,即当低于他的顺序视为可以查看[相当于下级])
     *
     * @param boxId
     * @param boxReActivityDtos
     * @param spotCheck         是否是抽查或者巡查
     * @return
     */
    public List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, List<BoxReActivityDto> boxReActivityDtos, boolean spotCheck) {
        List<BoxReActivityDto> targetList = new ArrayList<>(0);
        List<BoxReActivityDto> boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isEmpty(boxReActivityDtoList)) {
            return targetList;
        }
        if (spotCheck) {//巡查或者抽查人员可以看到目标模型下的所有节点
            return boxReActivityDtoList;
        }
        if (CollectionUtils.isEmpty(boxReActivityDtos)) {
            return targetList;
        }
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
            return bpmRpcBoxService.getAssessmentItemList(boxId, boxReActivitiId, assessmentKey);
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

    /**
     * 所有的考核记录
     *
     * @param projectId
     * @param planDetailsId
     * @return
     */
    public List<AssessmentProjectPerformanceDto> getUseAssessmentProjectPerformanceDtoList(Integer projectId, Integer planDetailsId) {
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProjectId(projectId);
        query.setPlanDetailsId(planDetailsId);
        //所有的考核记录
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(query);
        return assessmentProjectPerformanceDtoList;
    }

    /**
     * 确定流程结束   考核是否完成,假如没有那么会生成一个task任务
     *
     * @param processInsId 必须传入最新的流程实例id
     */
    public void checkTaskChksActivity(String processInsId) throws Exception {
        if (StringUtils.isEmpty(processInsId)) {
            return;
        }
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        if (projectPlanDetails == null) {
            return;
        }
        //所有的考核记录
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getUseAssessmentProjectPerformanceDtoList(projectPlanDetails.getProjectId(), projectPlanDetails.getId());
        //本次的考核记录
        List<AssessmentProjectPerformanceDto> thisProjectPerformanceDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
            //首页剔除抽查考核的数据
            Iterator<AssessmentProjectPerformanceDto> iterator = assessmentProjectPerformanceDtoList.iterator();
            while (iterator.hasNext()) {
                AssessmentProjectPerformanceDto performanceDto = iterator.next();
                if (performanceDto.getSpotActivityId() != null && performanceDto.getSpotActivityId() != 0) {
                    iterator.remove();
                }
            }
        }
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
            thisProjectPerformanceDtoList = assessmentProjectPerformanceDtoList.stream().filter(oo -> com.google.common.base.Objects.equal(oo.getProcessInsId(), processInsId)).collect(Collectors.toList());
        }
        //无本次考核记录
        if (CollectionUtils.isEmpty(thisProjectPerformanceDtoList)) {
            //不作处理
        }
        //审批日志记录
        List<BoxApprovalLogVo> boxApprovalLogVoList = getFilterBoxApprovalLogVoList(processInsId);
        //无 审批记录
        if (CollectionUtils.isEmpty(boxApprovalLogVoList)) {
            return;
        }
        //需要特殊处理的工作事项
        List<ProjectPhase> projectPhaseList = getChksRuningEnumKeys();
        ProjectPhase targetPhase = null;
        if (CollectionUtils.isNotEmpty(projectPhaseList)) {
            Iterator<ProjectPhase> projectPhaseIterator = projectPhaseList.iterator();
            while (projectPhaseIterator.hasNext()) {
                ProjectPhase projectPhase = projectPhaseIterator.next();
                if (Objects.equal(projectPhase.getId(), projectPlanDetails.getProjectPhaseId())) {
                    targetPhase = new ProjectPhase();
                    BeanUtils.copyProperties(projectPhase, targetPhase);
                }
            }
        }
        Collection<AssessmentProjectPerformanceDto> collection = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList) && CollectionUtils.isNotEmpty(thisProjectPerformanceDtoList)) {
            collection = CollectionUtils.subtract(assessmentProjectPerformanceDtoList, thisProjectPerformanceDtoList);
        }
        //过去的考核记录
        List<AssessmentProjectPerformanceDto> pastProjectPerformanceDtoList = Lists.newArrayList(collection);

        //审批人 流程实例id以及 activityId ==> key-(key-value) map
        LinkedListMultimap<String, KeyValueDto> creatorMaps = LinkedListMultimap.create();
        Iterator<BoxApprovalLogVo> boxApprovalLogVoIterator = boxApprovalLogVoList.iterator();
        while (boxApprovalLogVoIterator.hasNext()) {
            BoxApprovalLogVo boxApprovalLogVo = boxApprovalLogVoIterator.next();
            KeyValueDto keyValueDto = new KeyValueDto(boxApprovalLogVo.getProcessInsId(), boxApprovalLogVo.getActivityId().toString());
            keyValueDto.setExplain(boxApprovalLogVo.getActivityName());
            creatorMaps.put(boxApprovalLogVo.getCreator(), keyValueDto);
        }

        /**
         * LinkedListMultimap<Integer, KeyValueDto>>  planDetailsId,<create,activityId>
         */
        BiFunction<List<AssessmentProjectPerformanceDto>, LinkedListMultimap<String, KeyValueDto>, LinkedListMultimap<Integer, KeyValueDto>> biFunction = new BiFunction<List<AssessmentProjectPerformanceDto>, LinkedListMultimap<String, KeyValueDto>, LinkedListMultimap<Integer, KeyValueDto>>() {
            @Override
            public LinkedListMultimap<Integer, KeyValueDto> apply(List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtos, LinkedListMultimap<String, KeyValueDto> stringStringLinkedHashMap) {
                LinkedListMultimap<Integer, KeyValueDto> integerStringMap = LinkedListMultimap.create();
                Iterator<Map.Entry<String, KeyValueDto>> entryIterator = stringStringLinkedHashMap.entries().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, KeyValueDto> stringEntry = entryIterator.next();
                    if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtos)) {
                        //当人员和流程实例id 在考核记录记录中找不到则返回true
                        Iterator<AssessmentProjectPerformanceDto> iterator = assessmentProjectPerformanceDtos.iterator();
                        while (iterator.hasNext()) {
                            AssessmentProjectPerformanceDto oo = iterator.next();
                            boolean check = Objects.equal(stringEntry.getKey(), oo.getExaminePeople()) && Objects.equal(stringEntry.getValue().getKey(), oo.getProcessInsId()) && Objects.equal(oo.getActivityId().toString(), stringEntry.getValue().getValue());
                            if (!check) {
                                KeyValueDto keyValueDto = new KeyValueDto(stringEntry.getKey(), stringEntry.getValue().getValue());
                                keyValueDto.setExplain(stringEntry.getValue().getExplain());
                                integerStringMap.put(oo.getPlanDetailsId(), keyValueDto);
                            }
                        }
                    } else {
                        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(stringEntry.getValue().getKey());
                        if (planDetails != null) {
                            KeyValueDto keyValueDto = new KeyValueDto(stringEntry.getKey(), stringEntry.getValue().getValue());
                            keyValueDto.setExplain(stringEntry.getValue().getExplain());
                            integerStringMap.put(planDetails.getId(), keyValueDto);
                        }
                    }
                }
                return integerStringMap;
            }
        };

        BiPredicate<List<AssessmentProjectPerformanceDto>, LinkedListMultimap<String, KeyValueDto>> biPredicate = new BiPredicate<List<AssessmentProjectPerformanceDto>, LinkedListMultimap<String, KeyValueDto>>() {
            @Override
            public boolean test(List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtos, LinkedListMultimap<String, KeyValueDto> stringStringLinkedHashMap) {
                int count = 0;//定义一个未匹配到的计数器,当没有匹配到一次 计数器自动加1
                if (CollectionUtils.isEmpty(assessmentProjectPerformanceDtos)) {
                    return false;
                }
                Iterator<Map.Entry<String, KeyValueDto>> entryIterator = stringStringLinkedHashMap.entries().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, KeyValueDto> stringEntry = entryIterator.next();
                    //当人员和流程实例id 在考核记录记录中找不到则返回true ,就说明没有匹配到
                    boolean tempFlag = assessmentProjectPerformanceDtos.stream().noneMatch(oo -> {
                        boolean flagA = Objects.equal(stringEntry.getKey(), oo.getExaminePeople());
                        boolean flagB = Objects.equal(stringEntry.getValue().getKey(), oo.getProcessInsId());
                        boolean flagC = Objects.equal(stringEntry.getValue().getValue(), oo.getActivityId().toString());
                        return flagA && flagB && flagC;
                    });
                    if (tempFlag) {
                        count++;
                    }
                }
                return count == 0;
            }
        };
        //过去的考核记录 全部失效
        if (CollectionUtils.isNotEmpty(pastProjectPerformanceDtoList)) {
            Iterator<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoIterator = pastProjectPerformanceDtoList.iterator();
            while (assessmentProjectPerformanceDtoIterator.hasNext()) {
                AssessmentProjectPerformanceDto performanceDto = assessmentProjectPerformanceDtoIterator.next();
                performanceDto.setEffective(false);//过去数据全部设为无效
                chksRpcAssessmentService.updateAssessmentProjectPerformanceDto(performanceDto, true);
            }
        }
        boolean testCheck = biPredicate.test(thisProjectPerformanceDtoList, creatorMaps);
        //1:有审批记录,并且每次都有考核记录那么此次考核通过，以往考核记录自动失效
        //当本次的审批人在本次的考核记录中都能找到对应的考核记录
        //通过
        if (testCheck) {
            return;
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
        if (projectInfo == null) {
            return;
        }
        if (projectWorkStage == null) {
            return;
        }
        //2:部分跳过或者全部跳过但是保留有审批人数据  有审批记录却没有考核记录的对审批人发起考核任务并且到详情页面考核
        if (!testCheck) {
            //获取那些保留了审批记录却没有 考核记录的人员
            LinkedListMultimap<Integer, KeyValueDto> integerStringMap = biFunction.apply(thisProjectPerformanceDtoList, creatorMaps);
            if (!integerStringMap.isEmpty()) {
                Collection<Map.Entry<Integer, KeyValueDto>> entryCollection = integerStringMap.entries();
                Iterator<Map.Entry<Integer, KeyValueDto>> listIterator = entryCollection.iterator();
                while (listIterator.hasNext()) {
                    Map.Entry<Integer, KeyValueDto> integerStringEntry = listIterator.next();
                    ProjectPlanDetails target = projectPlanDetailsService.getProjectPlanDetailsById(integerStringEntry.getKey());
                    if (target == null) {
                        continue;
                    }
                    if (targetPhase == null) {
                        appendTask(target, projectInfo, projectWorkStage, integerStringEntry.getValue(), null);
                    }
                    if (targetPhase != null) {
                        switch (targetPhase.getPhaseKey()) {
                            case AssessPhaseKeyConstant.ASSET_DECLARE:
                                //申报状态下
                                handleDeclareTask(target, projectInfo, projectWorkStage, integerStringEntry.getValue());
                                break;
                            case AssessPhaseKeyConstant.CASE_STUDY_EXTEND://穿透一下
                            case AssessPhaseKeyConstant.SCENE_EXPLORE:
                                //查勘状态下
                                handleExploreTask(target, projectInfo, projectWorkStage, integerStringEntry.getValue());
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 查勘
     *
     * @param target
     * @param projectInfo
     * @param projectWorkStage
     * @param keyValueDto
     * @throws Exception
     */
    private void handleExploreTask(ProjectPlanDetails target, ProjectInfo projectInfo, ProjectWorkStage projectWorkStage, KeyValueDto keyValueDto) throws Exception {
        ProjectPlanDetails projectPlanDetailsForm = appendTask(target, projectInfo, projectWorkStage, keyValueDto, null);
        if (StringUtils.isBlank(target.getProcessInsId())) {
            return;
        }
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(target.getProcessInsId());
        if (basicApplyBatch == null) {
            return;
        }
        List<BasicApplyBatchDetail> basicApplyBatchDetailList = basicApplyBatchDetailService.getBuildingBatchDetailsByBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isEmpty(basicApplyBatchDetailList)) {
            return;
        }
        BasicApplyBatchDetail batchDetail = new BasicApplyBatchDetail();
        batchDetail.setTableId(basicApplyBatch.getEstateId());
        batchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        basicApplyBatchDetailList.add(batchDetail);
        Iterator<BasicApplyBatchDetail> basicApplyBatchDetailIterator = basicApplyBatchDetailList.iterator();
        while (basicApplyBatchDetailIterator.hasNext()) {
            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailIterator.next();
            String tableName = basicApplyBatchDetail.getTableName();
            LinkedList<String> linkedList = Lists.newLinkedList();
            linkedList.add(String.format("basicApplyBatch/informationDetail?%s", tableName));
            linkedList.add(String.join("", "tableId=", basicApplyBatchDetail.getTableId().toString()));
            linkedList.add(String.join("", "formClassify=", basicApplyBatch.getClassify().toString()));
            linkedList.add(String.join("", "formType=", basicApplyBatch.getType().toString()));
            linkedList.add(String.join("", "planDetailsId=", basicApplyBatch.getPlanDetailsId().toString()));
            linkedList.add(String.join("", "applyBatchId=", basicApplyBatch.getId().toString()));
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                linkedList.add("tbType=building");
            }
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                linkedList.add("tbType=house");
            }
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                linkedList.add("tbType=unit");
            }
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicEstate.class))) {
                linkedList.add("tbType=estate");
            }
            appendTask(target, projectInfo, projectWorkStage, keyValueDto, StringUtils.join(linkedList, "&"));
        }
    }

    /**
     * 申报
     *
     * @param target
     * @param projectInfo
     * @param projectWorkStage
     * @param keyValueDto
     * @throws Exception
     */
    private void handleDeclareTask(ProjectPlanDetails target, ProjectInfo projectInfo, ProjectWorkStage projectWorkStage, KeyValueDto keyValueDto) throws Exception {
        DeclareRealtyHouseCert realtyHouseCert = new DeclareRealtyHouseCert();
        realtyHouseCert.setPlanDetailsId(target.getId());
        realtyHouseCert.setEnable(DeclareTypeEnum.MasterData.getKey());
        List<DeclareRealtyHouseCertVo> declareRealtyHouseCertVoList = declareRealtyHouseCertService.lists(realtyHouseCert);
        DeclareRealtyLandCert realtyLandCert = new DeclareRealtyLandCert();
        realtyLandCert.setPlanDetailsId(target.getId());
        realtyLandCert.setEnable(DeclareTypeEnum.MasterData.getKey());
        List<DeclareRealtyLandCertVo> declareRealtyLandCertVoList = declareRealtyLandCertService.lists(realtyLandCert);
        DeclareRealtyRealEstateCert realtyRealEstateCert = new DeclareRealtyRealEstateCert();
        realtyRealEstateCert.setPlanDetailsId(target.getId());
        realtyRealEstateCert.setEnable(DeclareTypeEnum.MasterData.getKey());
        List<DeclareRealtyRealEstateCertVo> declareRealtyRealEstateCertVoList = declareRealtyRealEstateCertService.landLevels(realtyRealEstateCert);
        int sizeTotal = declareRealtyHouseCertVoList.size() + declareRealtyLandCertVoList.size() + declareRealtyRealEstateCertVoList.size();
        if (sizeTotal == 0) {
            return;
        }
        ProjectPlanDetails projectPlanDetails = appendTask(target, projectInfo, projectWorkStage, keyValueDto, null);
        if (projectPlanDetails == null) {
            return;
        }
        BiFunction<Integer, String, ChksCustomerAssessmentPlanDetail> biFunction = new BiFunction<Integer, String, ChksCustomerAssessmentPlanDetail>() {
            @Override
            public ChksCustomerAssessmentPlanDetail apply(Integer integer, String s) {
                ChksCustomerAssessmentPlanDetail assessmentPlanDetail = new ChksCustomerAssessmentPlanDetail();
                assessmentPlanDetail.setTableId(integer);
                assessmentPlanDetail.setTableName(s);
                assessmentPlanDetail.setBisEnable(true);
                assessmentPlanDetail.setProjectId(projectInfo.getId());
                assessmentPlanDetail.setProjectName(projectInfo.getProjectName());
                assessmentPlanDetail.setCreator(keyValueDto.getKey());
                assessmentPlanDetail.setType(ChksCustomizeEnum.declare.getKey());
                assessmentPlanDetail.setMasterId(projectPlanDetails.getId());
                assessmentPlanDetail.setProcessInsId(target.getProcessInsId());
                assessmentPlanDetail.setPlanId(target.getPlanId());
                assessmentPlanDetail.setUrl(ChksCustomerAssessmentPlanDetailService.applyUrl);
                if (StringUtils.isNotBlank(keyValueDto.getValue())) {
                    if (NumberUtils.isNumber(keyValueDto.getValue())) {
                        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(Integer.parseInt(keyValueDto.getValue()));
                        if (boxReActivityDto != null) {
                            assessmentPlanDetail.setActivityId(boxReActivityDto.getId());
                            assessmentPlanDetail.setBoxId(boxReActivityDto.getBoxId());
                            assessmentPlanDetail.setActivityName(boxReActivityDto.getCnName());
                        }
                    }
                }
                chksCustomerAssessmentPlanDetailService.saveAndUpdateChksCustomerAssessmentPlanDetail(assessmentPlanDetail, true);
                return assessmentPlanDetail;
            }
        };
        if (CollectionUtils.isNotEmpty(declareRealtyHouseCertVoList)) {
            Iterator<DeclareRealtyHouseCertVo> houseCertVoIterator = declareRealtyHouseCertVoList.iterator();
            while (houseCertVoIterator.hasNext()) {
                DeclareRealtyHouseCertVo realtyHouseCertVo = houseCertVoIterator.next();
                ChksCustomerAssessmentPlanDetail assessmentPlanDetail = biFunction.apply(realtyHouseCertVo.getId(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
            }
        }
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertVoList)) {
            Iterator<DeclareRealtyLandCertVo> declareRealtyLandCertVoIterator = declareRealtyLandCertVoList.iterator();
            while (declareRealtyLandCertVoIterator.hasNext()) {
                DeclareRealtyLandCertVo realtyLandCertVo = declareRealtyLandCertVoIterator.next();
                ChksCustomerAssessmentPlanDetail assessmentPlanDetail = biFunction.apply(realtyLandCertVo.getId(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
            }
        }
        if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertVoList)) {
            ListIterator<DeclareRealtyRealEstateCertVo> realtyRealEstateCertVoListIterator = declareRealtyRealEstateCertVoList.listIterator();
            while (realtyRealEstateCertVoListIterator.hasNext()) {
                DeclareRealtyRealEstateCertVo realEstateCertVo = realtyRealEstateCertVoListIterator.next();
                ChksCustomerAssessmentPlanDetail assessmentPlanDetail = biFunction.apply(realEstateCertVo.getId(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class));
            }
        }
    }


    /**
     * 发起任务
     *
     * @param target
     * @param projectInfo
     * @param projectWorkStage
     * @throws Exception
     */
    private ProjectPlanDetails appendTask(ProjectPlanDetails target, ProjectInfo projectInfo, ProjectWorkStage projectWorkStage, KeyValueDto keyValueDto, String url) throws Exception {
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (StringUtils.isNotBlank(keyValueDto.getExplain())) {
            linkedList.add(keyValueDto.getExplain());
        }
        if (StringUtils.isBlank(keyValueDto.getExplain())) {
            if (StringUtils.isNotBlank(keyValueDto.getValue())) {
                if (NumberUtils.isNumber(keyValueDto.getValue())) {
                    BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(Integer.parseInt(keyValueDto.getValue()));
                    if (boxReActivityDto != null) {
                        linkedList.add(boxReActivityDto.getCnName());
                    }
                }
            }
        }
        String account = keyValueDto.getKey();
        if (StringUtils.isNotBlank(projectWorkStage.getWorkStageName())) {
            linkedList.add(projectWorkStage.getWorkStageName());
        }
        if (StringUtils.isNotBlank(target.getProjectPhaseName())) {
            linkedList.add(target.getProjectPhaseName());
        }
        linkedList.add("[考核计划]");
        linkedList.add(DateUtils.format(new Date(), DateUtils.HOUR_MINUTE_CHINESE_PATTERN));
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setPlanEndDate(new Date());
        projectPlanDetails.setPlanStartDate(new Date());
        projectPlanDetails.setCreator(account);
        projectPlanDetails.setExecuteUserAccount(account);
        projectPlanDetails.setPlanId(target.getPlanId());
        projectPlanDetails.setProjectId(target.getProjectId());
        projectPlanDetails.setProjectPhaseId(target.getProjectPhaseId());
        projectPlanDetails.setProjectWorkStageId(target.getProjectWorkStageId());
        projectPlanDetails.setProjectPhaseName(StringUtils.join(linkedList, "-"));
        projectPlanDetails.setSorting(linkedList.hashCode());
        projectPlanDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
        ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
        projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
        List<ProjectResponsibilityDto> projectResponsibilityDtoList = bpmRpcProjectTaskService.getProjectTaskByUserAccountAndAppKeyForProject(account, applicationConstant.getAppKey(), projectInfo.getId());
        if (CollectionUtils.isNotEmpty(projectResponsibilityDtoList)) {
            for (ProjectResponsibilityDto responsibilityDto : projectResponsibilityDtoList) {
                if (Objects.equal(projectPlanDetails.getId(), responsibilityDto.getPlanDetailsId())) {
                    BeanUtils.copyProperties(responsibilityDto, projectPlanResponsibility);
                    projectPlanResponsibility.setPlanId(projectPlanDetails.getPlanId());
                    projectPlanResponsibility.setPlanDetailsId(target.getId());
                    projectPlanResponsibility.setPlanDetailsName(projectPlanDetails.getProjectPhaseName());
                    projectPlanResponsibility.setProjectId(projectPlanDetails.getProjectId());
                    projectPlanResponsibility.setProjectName(projectInfo.getProjectName());
                    projectPlanResponsibility.setUserAccount(account);
                    projectPlanResponsibility.setPlanEndTime(new Date());
                    projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
                    if (StringUtils.isBlank(url)) {
                        projectPlanService.updateProjectTaskUrl(ResponsibileModelEnum.DETAIL, projectPlanResponsibility);
                    } else {
                        projectPlanResponsibility.setUrl(url);
                    }
                    projectPlanResponsibility.setCreator(account);
                }
            }
        }
        if (projectPlanResponsibility.getId() != null) {
            bpmRpcProjectTaskService.updateProjectTask(projectPlanResponsibility);
        }
        return projectPlanDetails;
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
     * 增加节点排序
     *
     * @param boxId
     * @param processInsId
     * @return
     */
    public LinkedHashMap<KeyValueDto, List<AssessmentProjectPerformanceDto>> getAssessmentProjectPerformanceDtoMap(Integer boxId, String processInsId) {
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery(boxId);
        query.setProcessInsId(processInsId);
        List<AssessmentProjectPerformanceDto> dtoList = getAssessmentProjectPerformanceDtoList(query);
        if (CollectionUtils.isNotEmpty(dtoList)) {
            Iterator<AssessmentProjectPerformanceDto> iterator = dtoList.iterator();
            while (iterator.hasNext()) {
                AssessmentProjectPerformanceDto assessmentProjectPerformanceDto = iterator.next();
                if (assessmentProjectPerformanceDto.getSpotActivityId() != null && assessmentProjectPerformanceDto.getSpotActivityId() != 0) {
                    iterator.remove();
                }
            }
        }
        LinkedHashMap<KeyValueDto, List<AssessmentProjectPerformanceDto>> listLinkedHashMap = conversionProjectPerformanceDtoMap(dtoList);
        LinkedHashMap<KeyValueDto, List<AssessmentProjectPerformanceDto>> keyValueDtoListLinkedHashMap = new LinkedHashMap<KeyValueDto, List<AssessmentProjectPerformanceDto>>();
        if (!listLinkedHashMap.isEmpty()) {
            List<Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>>> entryArrayList = new ArrayList<Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>>>(listLinkedHashMap.entrySet());
            Ordering<Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>>> ordering = Ordering.from(new Comparator<Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>>>() {
                @Override
                public int compare(Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>> o1, Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>> o2) {
                    BoxReActivityDto boxReActivityDtoA1 = bpmRpcBoxService.getBoxreActivityInfoById(Integer.parseInt(o1.getKey().getValue()));
                    BoxReActivityDto boxReActivityDtoA2 = bpmRpcBoxService.getBoxreActivityInfoById(Integer.parseInt(o2.getKey().getValue()));
                    if (boxReActivityDtoA1 == null || boxReActivityDtoA2 == null) {
                        return 0;
                    }
                    Integer a = boxReActivityDtoA1.getSortMultilevel();
                    Integer b = boxReActivityDtoA2.getSortMultilevel();
                    if (a == null) {
                        a = boxReActivityDtoA1.getSorting();
                    }
                    if (b == null) {
                        b = boxReActivityDtoA2.getSorting();
                    }
                    if (a == null || b == null) {
                        return 0;
                    }
                    return Integer.compare(a, b);
                }
            });
            Collections.sort(entryArrayList, ordering);
            Iterator<Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>>> iterator = entryArrayList.iterator();
            while (iterator.hasNext()) {
                Map.Entry<KeyValueDto, List<AssessmentProjectPerformanceDto>> entry = iterator.next();
                keyValueDtoListLinkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return keyValueDtoListLinkedHashMap;
    }

    public LinkedHashMap<KeyValueDto, List<AssessmentProjectPerformanceDto>> conversionProjectPerformanceDtoMap(List<AssessmentProjectPerformanceDto> dtoList) {
        LinkedHashMap<KeyValueDto, List<AssessmentProjectPerformanceDto>> keyValueDtoListMap = new LinkedHashMap<>();
        LinkedHashMap<String, List<AssessmentProjectPerformanceDto>> listLinkedHashMap = new LinkedHashMap<>();
        if (CollectionUtils.isNotEmpty(dtoList)) {
            Iterator<AssessmentProjectPerformanceDto> iterator = dtoList.iterator();
            while (iterator.hasNext()) {
                AssessmentProjectPerformanceDto assessmentProjectPerformanceDto = iterator.next();
                if (listLinkedHashMap.containsKey(assessmentProjectPerformanceDto.getActivityName())) {
                    listLinkedHashMap.get(assessmentProjectPerformanceDto.getActivityName()).add(assessmentProjectPerformanceDto);
                } else {
                    List<AssessmentProjectPerformanceDto> projectPerformanceDtos = new ArrayList<>();
                    projectPerformanceDtos.add(assessmentProjectPerformanceDto);
                    listLinkedHashMap.put(assessmentProjectPerformanceDto.getActivityName(), projectPerformanceDtos);
                }
            }
        }
        if (!listLinkedHashMap.isEmpty()) {
            Iterator<Map.Entry<String, List<AssessmentProjectPerformanceDto>>> entryIterator = listLinkedHashMap.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, List<AssessmentProjectPerformanceDto>> entry = entryIterator.next();
                AssessmentProjectPerformanceDto performanceDto = entry.getValue().get(0);
                KeyValueDto keyValueDto = new KeyValueDto(entry.getKey(), performanceDto.getActivityId().toString());
                keyValueDto.setExplain(performanceDto.getExaminePeople());
                keyValueDtoListMap.put(keyValueDto, entry.getValue());
            }
        }
        return keyValueDtoListMap;
    }

    /**
     * 获取节点 id
     *
     * @param processInsId
     * @return
     */
    public List<Integer> getBoxReActivitiId(String processInsId, String creator) {
        List<Integer> integerList = new ArrayList<>();
        List<BoxApprovalLogVo> boxApprovalLogVoList = getBoxApprovalLogVoList(processInsId);
        if (CollectionUtils.isEmpty(boxApprovalLogVoList)) {
            return null;
        }
        if (StringUtils.isBlank(creator)) {
            creator = processControllerComponent.getThisUser();
        }
        Iterator<BoxApprovalLogVo> iterator = boxApprovalLogVoList.iterator();
        while (iterator.hasNext()) {
            BoxApprovalLogVo next = iterator.next();
            if (Objects.equal(creator, next.getCreator())) {
                if (Objects.equal(processInsId, next.getProcessInsId())) {
                    integerList.add(next.getActivityId());
                }
            }
        }
        return integerList;
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

    /**
     * 获取节点模型数据
     *
     * @param processInsId
     * @return
     * @throws Exception
     */
    public List<BoxReActivityDto> getFilterBoxReActivityDto(String processInsId) {
        final List<BoxReActivityDto> boxReActivityDtoList = new ArrayList<>();
        if (StringUtils.isNotBlank(processInsId)) {
            List<Integer> integerList = getBoxReActivitiId(processInsId, null);
            if (CollectionUtils.isNotEmpty(integerList)) {
                integerList.forEach(boxReActivitiId -> {
                    BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(boxReActivitiId);
                    if (boxReActivityDto != null) {
                        boxReActivityDtoList.add(boxReActivityDto);
                    }
                });
            }
        }
        return boxReActivityDtoList;
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
     * 获取需要特殊处理的考核  工作事项
     *
     * @return
     */
    private List<ProjectPhase> getChksRuningEnumKeys() {
        final List<ProjectPhase> projectPhaseList = new ArrayList<>();
        final List<String> keys = Arrays.asList(AssessPhaseKeyConstant.ASSET_DECLARE, AssessPhaseKeyConstant.SCENE_EXPLORE, AssessPhaseKeyConstant.CASE_STUDY_EXTEND);
        ProjectPhase select = new ProjectPhase();
        keys.forEach(s -> {
            select.setPhaseKey(s);
            List<ProjectPhase> phaseList = projectPhaseService.getProjectPhaseList(select);
            if (CollectionUtils.isNotEmpty(phaseList)) {
                projectPhaseList.addAll(phaseList);
            }
        });
        return projectPhaseList;
    }

    /**
     * 获取考核标识
     *
     * @param processInsId
     * @param boxId
     * @return
     */
    public ChksRuningEnum getChksRuningEnum(List<BoxReActivityDto> boxReActivityDtoList, ProjectPhase projectPhase, Integer boxId, String processInsId, boolean approval) {
        final List<ProjectPhase> projectPhaseList = getChksRuningEnumKeys();
        if (CollectionUtils.isNotEmpty(projectPhaseList)) {
            for (ProjectPhase target : projectPhaseList) {
                if (Objects.equal(target.getId(), projectPhase.getId())) {
                    return ChksRuningEnum.CHKS_FINSH_ENUM_RUN;
                }
            }
        }
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery(boxId);
        query.setProcessInsId(processInsId);
        query.setEffective(true);
        int j = 0;
        if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
            Iterator<BoxReActivityDto> boxReActivityDtoIterator = boxReActivityDtoList.listIterator();
            while (boxReActivityDtoIterator.hasNext()) {
                int i = 0;
                BoxReActivityDto boxReActivityDto = boxReActivityDtoIterator.next();
                query.setActivityId(boxReActivityDto.getId());
                ChksRuningEnum target = getChksRuningEnumHandle(boxReActivityDto, boxId, processInsId, approval);
                if (target != null && target.getKey() == ChksRuningEnum.CHKS_FINSH_ENUM_RUN.getKey()) {
                    i++;
                }
                //本次所有的考核记录
                List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(query);
                if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
                    i++;
                }
                //暂时设为false,为以后bpm中会配置此考核是否按照常规考核进行
                if (false) {
                    i++;
                }
                if (i != 0) {
                    j++;
                }
            }
        }
        if (j == 0) {
            return ChksRuningEnum.CHKS_RUNING_ENUM_RUN;
        } else {
            return ChksRuningEnum.CHKS_FINSH_ENUM_RUN;
        }
    }

    /**
     * 获取上一个节点是否考核数据的标识
     *
     * @param boxReActivityDto
     * @param boxId
     * @param processInsId
     * @param approval
     * @return
     */
    private ChksRuningEnum getChksRuningEnumHandle(BoxReActivityDto boxReActivityDto, Integer boxId, String processInsId, boolean approval) {
        if (!approval) {
            return null;
        }
        if (StringUtils.isBlank(processInsId)) {
            return null;
        }
        if (boxReActivityDto == null) {
            return null;
        }
        if (boxId == null) {
            return null;
        }
        List<BoxReActivityDto> boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isEmpty(boxReActivityDtoList)) {
            return null;
        }
        if (boxReActivityDtoList.size() < 2) {//当前情况下只考虑在审批情况下,并且至少得两个节点才判断 否则没有意义
            return null;
        }
        Ordering<BoxReActivityDto> ordering = Ordering.from(new Comparator<BoxReActivityDto>() {
            @Override
            public int compare(BoxReActivityDto o1, BoxReActivityDto o2) {
                return o1.getSortMultilevel().compareTo(o2.getSortMultilevel());
            }
        });
        int targetId = 0;
        Collections.sort(boxReActivityDtoList, ordering);
        ListIterator<BoxReActivityDto> iterator = boxReActivityDtoList.listIterator();
        while (iterator.hasNext()) {
            BoxReActivityDto next = iterator.next();
            if (Objects.equal(next.getId(), boxReActivityDto.getId())) {
                targetId = boxReActivityDtoList.indexOf(next);
                break;
            }
        }
        if (targetId == 0) {
            return null;
        }
        int temp = targetId - 1;
        if (temp < 0) {
            return null;
        }
        BoxReActivityDto target = null;
        try {
            target = boxReActivityDtoList.get(temp);
        } catch (Exception e) {
            //可能会有数据越界的情况,虽然大概率不会
            return null;
        }
        if (target == null) {
            return null;
        }
        if (Objects.equal(target.getId(), boxReActivityDto.getId())) {
            return null;
        }
        if (target.getSortMultilevel() <= 0) {
            return null;
        }
        AssessmentProjectPerformanceQuery select = new AssessmentProjectPerformanceQuery(boxId);
        select.setActivityId(target.getId());
        select.setProcessInsId(processInsId);
        select.setEffective(true);
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(select);
        if (CollectionUtils.isEmpty(assessmentProjectPerformanceDtoList)) {
            return ChksRuningEnum.CHKS_FINSH_ENUM_RUN;
        }
        return null;
    }

    /**
     * 获取过滤后的审批日志
     *
     * @param processInsId
     * @return
     */
    public List<BoxApprovalLogVo> getFilterBoxApprovalLogVoList(String processInsId) {
        final List<String> filterActivityKey = Arrays.asList("edit", "start");
        List<BoxApprovalLogVo> boxApprovalLogDtoList = getBoxApprovalLogVoList(processInsId);
        BoxApprovalLogVo boxApprovalLogVoMax = null;
        if (CollectionUtils.isNotEmpty(boxApprovalLogDtoList)) {
            if (boxApprovalLogDtoList.size() != 1) {
                Integer id = boxApprovalLogDtoList.stream().map(boxApprovalLogVo -> boxApprovalLogVo.getId()).max(Integer::compareTo).get();
                boxApprovalLogVoMax = boxApprovalLogDtoList.stream().filter(boxApprovalLogVo -> Objects.equal(id, boxApprovalLogVo.getId())).findFirst().get();
            }
        }
        if (CollectionUtils.isNotEmpty(boxApprovalLogDtoList)) {
            Iterator<BoxApprovalLogVo> iterator = boxApprovalLogDtoList.iterator();
            while (iterator.hasNext()) {
                BoxApprovalLogVo boxApprovalLogVo = iterator.next();
                if (boxApprovalLogVo.getBisApply() != null) {
                    if (boxApprovalLogVo.getBisApply()) {
                        iterator.remove();//删除  申请人日志
                        if (boxApprovalLogVoMax != null) {
                            if (Objects.equal(boxApprovalLogVo.getId(), boxApprovalLogVoMax.getId())) {//假如和我们的目标一致那么整个过滤计划马上终止
                                break;
                            }
                        }
                        continue;
                    }
                }
                if (filterActivityKey.contains(boxApprovalLogVo.getActivityNameKey())) {
                    iterator.remove();
                    if (boxApprovalLogVoMax != null) {
                        if (Objects.equal(boxApprovalLogVo.getId(), boxApprovalLogVoMax.getId())) {//假如和我们的目标一致那么整个过滤计划马上终止
                            break;
                        }
                    }
                    continue;
                }
                if (boxApprovalLogVoMax != null) {
                    int number = boxApprovalLogVoMax.getSorting().intValue();
                    int comparisons = boxApprovalLogVo.getSorting().intValue();
                    if (comparisons > number) {
                        iterator.remove();
                        continue;
                    }
                }
            }
        }
        return boxApprovalLogDtoList;
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
        if (CollectionUtils.isNotEmpty(boxApprovalLogDtoList)) {
            Iterator<BoxApprovalLogVo> iterator = boxApprovalLogDtoList.iterator();
            while (iterator.hasNext()) {
                BoxApprovalLogVo boxApprovalLogVo = iterator.next();
                if (boxApprovalLogVo.getBisApply() != null) {
                    if (boxApprovalLogVo.getBisApply()) {
                        iterator.remove();//删除  申请人日志
                        continue;
                    }
                }
            }
        }
        return boxApprovalLogDtoList;
    }

    //1.当前节点任务是否已生成，已生成则不再重复生成 2.将比当前节点序号大的节点考核任务置位无效状态
    public void generateAssessmentTask(String processInsId, Integer boxId, String taskId, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws BpmException {
        //1.当前节点任务是否已生成，已生成则不再重复生成
        //2.将比当前节点序号大的节点考核任务置位无效状态
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        if (boxReDto != null && boxReDto.getBisLaunchCheck() == Boolean.TRUE) {
            if (boxReDto != null && boxReDto.getBisLaunchCheck() == Boolean.TRUE) {
                ActivitiTaskNodeDto activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, commonService.thisUserAccount());
                BoxReActivityDto currentActivity = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, activitiTaskNodeDto.getCurrentStep());
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
                Integer count = chksRpcAssessmentService.getAssessmentProjectPerformanceCount(processInsId, currentActivity.getId(), taskId);
                if (count > 0) return;
                String checkBean = StringUtils.uncapitalize(AssessmentTaskService.class.getSimpleName());//默认生成考核任务服务方法
                checkBean = StringUtils.isNoneBlank(boxReDto.getCheckBean()) ? boxReDto.getCheckBean() : checkBean;
                checkBean = StringUtils.isNoneBlank(currentActivity.getCheckBean()) ? currentActivity.getCheckBean() : checkBean;
                AssessmentTaskInterface assessmentTaskBean = (AssessmentTaskInterface) SpringContextUtils.getBean(checkBean);
                BootstrapTableVo tableVo = bpmRpcProcessInsManagerService.getApprovalLogForApp(applicationConstant.getAppKey(), processInsId, 0, 1000);
                List<BoxApprovalLogVo> rows = tableVo.getRows();
                assessmentTaskBean.createAssessmentTask(processInsId, currentActivity.getId(), taskId, rows.get(0).getCreator(), projectInfo, projectPlanDetails);
                if (count > 0) {
                    return;
                } else {
                    //先清理掉当前节点的任务
                    AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
                    query.setProcessInsId(processInsId);
                    query.setActivityId(currentActivity.getId());
                    List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
                    if (CollectionUtils.isNotEmpty(performanceDtoList)) {
                        performanceDtoList.forEach(o -> chksRpcAssessmentService.deleteAssessmentProjectPerformanceByIds(LangUtils.transform(performanceDtoList, p -> p.getId())));
                    }

                    checkBean = "assessmentTaskService";//默认生成考核任务服务方法
                    checkBean = StringUtils.isNoneBlank(boxReDto.getCheckBean()) ? boxReDto.getCheckBean() : checkBean;
                    checkBean = StringUtils.isNoneBlank(currentActivity.getCheckBean()) ? currentActivity.getCheckBean() : checkBean;
                    assessmentTaskBean = (AssessmentTaskInterface) SpringContextUtils.getBean(checkBean);
                    tableVo = bpmRpcProcessInsManagerService.getApprovalLogForApp(applicationConstant.getAppKey(), processInsId, 0, 1000);
                    rows = tableVo.getRows();
                    assessmentTaskBean.createAssessmentTask(processInsId, currentActivity.getId(), taskId, rows.get(0).getCreator(), projectInfo, projectPlanDetails);
                }

            }
        }
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
                        performanceDto.setExaminePeople(processControllerComponent.getThisUser());
                        performanceDto.setExaminePeopleName(processControllerComponent.getThisUserInfo().getUserName());
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
