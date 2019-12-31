package com.copower.pmcc.assess.service.chks;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.*;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Objects;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
                if (performanceDetailDto.getPerformanceId() != null) {
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

    /**
     * 获取目标节点可以查看的节点  (筛选根据bpm中排序顺序来判断,即当低于他的顺序视为可以查看[相当于下级])
     *
     * @param boxId
     * @param boxReActivitiId
     * @param spotCheck       是否是抽查或者巡查
     * @return
     */
    public List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, Integer boxReActivitiId, boolean spotCheck) {
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

    /**
     * 获取抽查人员填写的考核项
     *
     * @param boxId
     * @return
     */
    public List<AssessmentItemDto> getSpotCheckAssessmentItems(Integer boxId) {
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getEndActivityByBoxId(boxId);
        return bpmRpcBoxService.getAssessmentItemList(boxId, boxReActivityDto.getId());
    }

    public List<AssessmentItemDto> getAssessmentItemTemplate(boolean spotCheck, Integer boxId, Integer boxReActivitiId) {
        if (spotCheck) {
            return getSpotCheckAssessmentItems(boxId);
        } else {
            return bpmRpcBoxService.getAssessmentItemList(boxId, boxReActivitiId);
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
                if (CollectionUtils.isNotEmpty(vo.getDetailList())) {
                    Iterator<AssessmentProjectPerformanceDetailDto> detailDtoIterator = vo.getDetailList().iterator();
                    while (detailDtoIterator.hasNext()) {
                        AssessmentProjectPerformanceDetailDto performanceDetailDto = detailDtoIterator.next();

                        if (performanceDetailDto.getContentId() != null) {
                            AssessmentItemDto assessmentItem = bpmRpcBoxService.getAssessmentItem(performanceDetailDto.getContentId());
                            if (assessmentItem != null) {
                                performanceDetailDto.setMinScore(assessmentItem.getMinScore());
                                performanceDetailDto.setMaxScore(assessmentItem.getMaxScore());
                            }
                        }
                    }
                }
            }
        }
        return assessmentProjectPerformanceDtos;
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
     * 获取节点模型数据
     *
     * @param boxReActivitiId
     * @param boxId
     * @param isDetail        是否从详情中来的
     * @return
     * @throws Exception
     */
    public BoxReActivityDto getFilterBoxReActivityDto(Integer boxReActivitiId, Integer boxId, boolean isDetail) {
        String creator = processControllerComponent.getThisUser();
        //由于从详情中来的节点是错误的,所以只能自己获得节点信息
        if (isDetail) {
            List<BoxReActivityDto> boxReActivityDtoList = getAssessmentProjectPerformanceNext(boxId, boxReActivitiId, false);
            if (CollectionUtils.isNotEmpty(boxReActivityDtoList)) {
                for (BoxReActivityDto boxReActivityDto : boxReActivityDtoList) {
                    try {
                        List<String> stringList = bpmRpcBoxService.getRoleUserByActivityId(boxReActivityDto.getId());
                        if (CollectionUtils.isEmpty(stringList)) {
                            continue;
                        }
                        if (stringList.contains(creator)) {
                            return boxReActivityDto;
                        }
                    } catch (BpmException e) {
                        baseService.writeExceptionInfo(e);
                    }

                }
            }
            if (boxReActivitiId != null) {
                BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(boxReActivitiId);
                if (boxReActivityDto != null) {
                    return boxReActivityDto;
                }
            }
            return null;
        } else {
            return bpmRpcBoxService.getBoxreActivityInfoById(boxReActivitiId);
        }
    }


    /**
     * 获取流程抽查人员账号
     *
     * @param boxId
     * @return
     */
    public List<String> getSpotCheckUserAccounts(Integer boxId) {
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getEndActivityByBoxId(boxId);
        try {
            List<String> userAccounts = Lists.newArrayList();
            List<String> list = bpmRpcBoxService.getRoleUserByActivityId(boxReActivityDto.getId());
            if (CollectionUtils.isNotEmpty(list)) userAccounts.addAll(list);
            return userAccounts;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
        return null;
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
        AssessmentProjectPerformanceQuery query = new AssessmentProjectPerformanceQuery();
        query.setProjectId(projectPlanDetails.getProjectId());
        query.setPlanDetailsId(projectPlanDetails.getId());
        //所有的考核记录
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = getAssessmentProjectPerformanceDtoList(query);
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
        List<BoxApprovalLogVo> boxApprovalLogVoList = getBoxApprovalLogVoList(processInsId);
        if (CollectionUtils.isNotEmpty(boxApprovalLogVoList)) {
            Iterator<BoxApprovalLogVo> iterator = boxApprovalLogVoList.iterator();
            while (iterator.hasNext()) {
                BoxApprovalLogVo boxApprovalLogVo = iterator.next();
                if (boxApprovalLogVo.getBisApply() != null) {
                    if (boxApprovalLogVo.getBisApply()) {
                        iterator.remove();//删除  申请人日志
                    }
                }
            }
        }
        //无 审批记录
        if (CollectionUtils.isEmpty(boxApprovalLogVoList)) {
            return;
        }
        Collection<AssessmentProjectPerformanceDto> collection = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList) && CollectionUtils.isNotEmpty(thisProjectPerformanceDtoList)) {
            collection = CollectionUtils.subtract(assessmentProjectPerformanceDtoList, thisProjectPerformanceDtoList);
        }
        //过去的考核记录
        List<AssessmentProjectPerformanceDto> pastProjectPerformanceDtoList = Lists.newArrayList(collection);

        LinkedHashMap<String, String> creatorMaps = Maps.newLinkedHashMap();//审批人 人员和流程实例id的 key-value map
        Iterator<BoxApprovalLogVo> boxApprovalLogVoIterator = boxApprovalLogVoList.iterator();
        while (boxApprovalLogVoIterator.hasNext()) {
            BoxApprovalLogVo boxApprovalLogVo = boxApprovalLogVoIterator.next();
            creatorMaps.put(boxApprovalLogVo.getCreator(), boxApprovalLogVo.getProcessInsId());
        }

        BiFunction<List<AssessmentProjectPerformanceDto>, LinkedHashMap<String, String>, Multimap<Integer, String>> biFunction = new BiFunction<List<AssessmentProjectPerformanceDto>, LinkedHashMap<String, String>, Multimap<Integer, String>>() {
            @Override
            public Multimap<Integer, String> apply(List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtos, LinkedHashMap<String, String> stringStringLinkedHashMap) {
                Multimap<Integer, String> integerStringMap = HashMultimap.create();
                Iterator<Map.Entry<String, String>> entryIterator = stringStringLinkedHashMap.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, String> stringEntry = entryIterator.next();
                    if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtos)) {
                        //当人员和流程实例id 在考核记录记录中找不到则返回true
                        Iterator<AssessmentProjectPerformanceDto> iterator = assessmentProjectPerformanceDtos.iterator();
                        while (iterator.hasNext()) {
                            AssessmentProjectPerformanceDto oo = iterator.next();
                            boolean check = Objects.equal(stringEntry.getKey(), oo.getExaminePeople()) && Objects.equal(stringEntry.getValue(), oo.getProcessInsId());
                            if (!check) {
                                integerStringMap.put(oo.getPlanDetailsId(), stringEntry.getKey());
                            }
                        }
                    } else {
                        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(stringEntry.getValue());
                        if (planDetails != null) {
                            integerStringMap.put(planDetails.getId(), stringEntry.getKey());
                        }
                    }
                }
                return integerStringMap;
            }
        };
        BiPredicate<List<AssessmentProjectPerformanceDto>, LinkedHashMap<String, String>> biPredicate = new BiPredicate<List<AssessmentProjectPerformanceDto>, LinkedHashMap<String, String>>() {
            @Override
            public boolean test(List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtos, LinkedHashMap<String, String> stringStringLinkedHashMap) {
                int count = 0;//定义一个未匹配到的计数器,当没有匹配到一次 计数器自动加1
                if (CollectionUtils.isEmpty(assessmentProjectPerformanceDtos)) {
                    return false;
                }
                Iterator<Map.Entry<String, String>> entryIterator = stringStringLinkedHashMap.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, String> stringEntry = entryIterator.next();
                    //当人员和流程实例id 在考核记录记录中找不到则返回true ,就说明没有匹配到
                    if (assessmentProjectPerformanceDtos.stream().noneMatch(oo -> Objects.equal(stringEntry.getKey(), oo.getExaminePeople()) && Objects.equal(stringEntry.getValue(), oo.getProcessInsId()))) {
                        count++;
                    }

                }
                return count == 0;
            }
        };
        boolean testCheck = biPredicate.test(thisProjectPerformanceDtoList, creatorMaps);
        //1:有审批记录,并且每次都有考核记录那么此次考核通过，以往考核记录自动失效
        //当本次的审批人在本次的考核记录中都能找到对应的考核记录
        //通过
        if (testCheck) {
            if (CollectionUtils.isNotEmpty(pastProjectPerformanceDtoList)) {
                Iterator<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoIterator = pastProjectPerformanceDtoList.iterator();
                while (assessmentProjectPerformanceDtoIterator.hasNext()) {
                    AssessmentProjectPerformanceDto performanceDto = assessmentProjectPerformanceDtoIterator.next();
                    performanceDto.setEffective(false);//过去数据全部设为无效
                    chksRpcAssessmentService.updateAssessmentProjectPerformanceDto(performanceDto, true);
                }
            }
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
            Multimap<Integer, String> integerStringMap = biFunction.apply(thisProjectPerformanceDtoList, creatorMaps);
            if (!integerStringMap.isEmpty()) {
                Collection<Map.Entry<Integer, String>> entryCollection = integerStringMap.entries();
                Iterator<Map.Entry<Integer, String>> listIterator = entryCollection.iterator();
                while (listIterator.hasNext()) {
                    Map.Entry<Integer, String> integerStringEntry = listIterator.next();
                    ProjectPlanDetails target = projectPlanDetailsService.getProjectPlanDetailsById(integerStringEntry.getKey());
                    if (target == null) {
                        continue;
                    }
                    appendTask(target, projectInfo, projectWorkStage, integerStringEntry.getValue());
                }
            }
        }
    }


    /**
     * 发起任务
     *
     * @param target
     * @param projectInfo
     * @param projectWorkStage
     * @param account
     * @throws Exception
     */
    private void appendTask(ProjectPlanDetails target, ProjectInfo projectInfo, ProjectWorkStage projectWorkStage, String account) throws Exception {
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setPlanEndDate(new Date());
        projectPlanDetails.setPlanStartDate(new Date());
        projectPlanDetails.setCreator(account);
        projectPlanDetails.setExecuteUserAccount(account);
        projectPlanDetails.setPlanId(target.getPlanId());
        projectPlanDetails.setProjectId(target.getProjectId());
        projectPlanDetails.setProjectWorkStageId(target.getProjectWorkStageId());
        projectPlanDetails.setProjectPhaseName(String.join("-", projectWorkStage.getWorkStageName(), "[考核计划]", target.getProjectPhaseName(), "时间:", DateUtils.format(new Date(), DateUtils.HOUR_MINUTE_CHINESE_PATTERN)));
        projectPlanDetails.setSorting(10);
        projectPlanDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);

        ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
        projectPlanResponsibility.setPlanId(projectPlanDetails.getPlanId());
        projectPlanResponsibility.setPlanDetailsId(projectPlanDetails.getId());
        projectPlanResponsibility.setPlanDetailsName(projectPlanDetails.getProjectPhaseName());
        projectPlanResponsibility.setProjectId(projectPlanDetails.getProjectId());
        projectPlanResponsibility.setProjectName(projectInfo.getProjectName());
        projectPlanResponsibility.setUserAccount(account);
        projectPlanResponsibility.setModel(ResponsibileModelEnum.TASK.getId());
        projectPlanResponsibility.setConclusion(ResponsibileModelEnum.TASK.getName());
        projectPlanResponsibility.setPlanEndTime(new Date());
        projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
//        projectPlanService.updateProjectTaskUrl(ResponsibileModelEnum.DETAIL, projectPlanResponsibility);
        projectPlanResponsibility.setCreator(account);


        projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails,projectInfo.getProjectName(),projectWorkStage.getWorkStageName(),ResponsibileModelEnum.TASK);

        List<ProjectResponsibilityDto> projectResponsibilityDtoList = bpmRpcProjectTaskService.getProjectTaskByUserAccountAndAppKeyForProject(account, applicationConstant.getAppKey(), projectInfo.getId());
        if (CollectionUtils.isNotEmpty(projectResponsibilityDtoList)) {
            for (ProjectResponsibilityDto responsibilityDto : projectResponsibilityDtoList) {
                if (Objects.equal(projectPlanDetails.getId(), responsibilityDto.getPlanDetailsId())) {
                    projectPlanResponsibility.setId(responsibilityDto.getId());
                }
            }
        }
        if (projectPlanResponsibility.getId() != null) {
            projectPlanResponsibility.setUrl(String.join("", "/pmcc-assess/ProjectTask/projectTaskDetailsById?planDetailsId=", target.getId().toString()));
            projectPlanResponsibility.setPlanDetailsId(target.getId());
            bpmRpcProjectTaskService.updateProjectTask(projectPlanResponsibility);
        }
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

    /**
     * 获取审批日志 ,方法不合理的写法 但是木有办法啊，在此时此刻没有提供合适的接口
     *
     * @param processInsId
     * @return
     */
    public List<BoxApprovalLogVo> getBoxApprovalLogVoList(String processInsId) {
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


}
