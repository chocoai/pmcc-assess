package com.copower.pmcc.assess.service.chks;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDetailDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDtoAndDetail;
import com.copower.pmcc.chks.api.dto.ChksBootstrapTableVo;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public ChksBootstrapTableVo getChksBootstrapTableVo(AssessmentProjectPerformanceDto where) {
        ChksBootstrapTableVo chksBootstrapTableVo = new ChksBootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        AssessmentProjectPerformanceDtoAndDetail dtoAndDetail = chksRpcAssessmentService.getAssessmentProjectPerformanceDtosByExample(where, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (dtoAndDetail != null) {
            chksBootstrapTableVo = dtoAndDetail.getChksBootstrapTableVo();
        }
        return chksBootstrapTableVo;
    }

    /**
     * 保存抽查数据
     *
     * @param assessmentProjectPerformanceDto
     * @param detailDtoList
     */
    public void saveAndUpdateChkSpotAssessment(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, List<AssessmentProjectPerformanceDetailDto> detailDtoList, Integer planDetailsId) {
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
        }
        if (projectPlanDetails != null) {
            if (projectPerformanceDto.getTableId() == null) {
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
    private List<BoxReActivityDto> getAssessmentProjectPerformanceNext(Integer boxId, Integer boxReActivitiId, boolean spotCheck) {
        List<BoxReActivityDto> targetList = new ArrayList<>(0);
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(boxReActivitiId);
        if (boxReActivityDto == null) {
            return targetList;
        }
        if (boxReActivityDto.getSortMultilevel() == null) {
            return targetList;
        }
        List<BoxReActivityDto> boxReActivityDtoList = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isEmpty(boxReActivityDtoList)) {
            return targetList;
        }
        if (spotCheck) {//巡查或者抽查人员可以看到目标模型下的所有节点
            return boxReActivityDtoList;
        }
        // 将 模型下所有节点中的我们目标节点给删除,并且对列表中的进行排序筛选
        Iterator<BoxReActivityDto> boxReActivityDtoIterator = boxReActivityDtoList.iterator();
        while (boxReActivityDtoIterator.hasNext()) {
            BoxReActivityDto next = boxReActivityDtoIterator.next();
            if (com.google.common.base.Objects.equal(next.getId(), boxReActivityDto.getId())) {
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


    /**
     * 获取抽查人员保存后的考核数据
     *
     * @param boxId
     * @param activityId
     * @param spotActivityId
     * @param processInsId
     * @param projectId
     * @return
     */
    public List<AssessmentProjectPerformanceDto> getChkSpotAssessmentBySpotActivityId(Integer boxId, Integer activityId, Integer spotActivityId, String processInsId, Integer projectId) {
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtos = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(boxId, activityId, spotActivityId, applicationConstant.getAppKey(), processInsId, projectId);
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
            }
        }
        return assessmentProjectPerformanceDtos;
    }


    /**
     * 获取此节点的下级的考核记录
     *
     * @param boxId
     * @param boxReActivitiId
     * @param spotCheck
     * @param processInsId
     * @param projectId
     * @return
     */
    public List<AssessmentProjectPerformanceDto> getAssessmentProjectPerformanceDtoList(Integer boxId, Integer boxReActivitiId, boolean spotCheck, String processInsId, Integer projectId) {
        List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoList = new ArrayList<>(0);
        List<BoxReActivityDto> activityDtoList = getAssessmentProjectPerformanceNext(boxId, boxReActivitiId, spotCheck);
        if (CollectionUtils.isEmpty(activityDtoList)) {
            return assessmentProjectPerformanceDtoList;
        }
        Iterator<BoxReActivityDto> iterator = activityDtoList.iterator();
        while (iterator.hasNext()) {
            BoxReActivityDto reActivityDto = iterator.next();
            List<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtos = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(boxId, reActivityDto.getId(), null, applicationConstant.getAppKey(), processInsId, projectId);
            if (CollectionUtils.isEmpty(assessmentProjectPerformanceDtos)) {
                continue;
            }
            assessmentProjectPerformanceDtoList.addAll(assessmentProjectPerformanceDtos);
        }
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtoList)) {
            Iterator<AssessmentProjectPerformanceDto> assessmentProjectPerformanceDtoIterator = assessmentProjectPerformanceDtoList.iterator();
            while (assessmentProjectPerformanceDtoIterator.hasNext()) {
                AssessmentProjectPerformanceDto vo = assessmentProjectPerformanceDtoIterator.next();
                if (StringUtils.isNotBlank(vo.getByExaminePeople())) {
                    vo.setByExaminePeopleName(publicService.getUserNameByAccount(vo.getByExaminePeople()));
                }
                if (StringUtils.isNotBlank(vo.getExaminePeople())) {
                    vo.setExaminePeopleName(publicService.getUserNameByAccount(vo.getExaminePeople()));
                }
                if (vo.getSpotActivityId() != null) {
                    if (vo.getSpotActivityId() != 0) {
                        assessmentProjectPerformanceDtoIterator.remove();
                    }
                }
            }
        }
        return assessmentProjectPerformanceDtoList;
    }

    public Map<String, List<AssessmentProjectPerformanceDto>> getAssessmentProjectPerformanceDtoMap(Integer boxId, Integer boxReActivitiId, boolean spotCheck, String processInsId, Integer projectId) {
        LinkedHashMap<String, List<AssessmentProjectPerformanceDto>> listLinkedHashMap = new LinkedHashMap<>();
        List<AssessmentProjectPerformanceDto> dtoList = getAssessmentProjectPerformanceDtoList(boxId, boxReActivitiId, spotCheck, processInsId, projectId);
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
        return listLinkedHashMap;
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

    public void updateAssessmentProjectPerformanceDto(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, boolean updateNull) {
        chksRpcAssessmentService.updateAssessmentProjectPerformanceDto(assessmentProjectPerformanceDto, updateNull);
    }

    public void updateAssessmentProjectPerformanceDetailDto(List<AssessmentProjectPerformanceDetailDto> detailDtoList, boolean updateNull) {
        chksRpcAssessmentService.updateAssessmentProjectPerformanceDetailDto(detailDtoList, updateNull);
    }


}
