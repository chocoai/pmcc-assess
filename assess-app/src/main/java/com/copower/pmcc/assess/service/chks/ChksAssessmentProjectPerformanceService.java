package com.copower.pmcc.assess.service.chks;


import com.alibaba.fastjson.JSON;
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
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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


    public void saveAssessmentProjectPerformance(ApprovalModelDto approvalModelDto, String chksScore, String remarks, String byExaminePeople) {
        //添加主表
        AssessmentProjectPerformanceDto dto = new AssessmentProjectPerformanceDto();
        List<AssessmentProjectPerformanceDetailDto> detailDtos = Lists.newArrayList();
        dto.setAppKey(applicationConstant.getAppKey());
        dto.setProcessInsId(approvalModelDto.getProcessInsId());
        dto.setProjectId(approvalModelDto.getProjectId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(approvalModelDto.getProjectId());
        if (projectInfo != null) {
            dto.setProjectName(projectInfo.getProjectName());
        }
        dto.setActivityId(approvalModelDto.getActivityId());
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(approvalModelDto.getActivityId());
        if (boxReActivityDto != null) {
            dto.setActivityName(boxReActivityDto.getCnName());
        }
        dto.setSpotActivityId(0);//抽查节点id
        dto.setBoxId(approvalModelDto.getBoxId());
        dto.setCreator(processControllerComponent.getThisUser());
        dto.setExaminePeople(processControllerComponent.getThisUser());
        dto.setByExaminePeople(byExaminePeople);
        dto.setRemarks(remarks);
        dto.setExamineDate(new Date());
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(approvalModelDto.getProcessInsId());
        if (projectPlanDetails != null) {
            dto.setTableId(projectPlanDetails.getId());
            dto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
            //业务标识
            if (projectPlanDetails.getProjectWorkStageId() != null) {
                ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
                if (projectWorkStage != null) {
                    dto.setBusinessKey(projectWorkStage.getWorkStageName());
                }
            }
        }
        List<AssessmentProjectPerformanceDetailDto> detailDtoList = JSONObject.parseArray(chksScore, AssessmentProjectPerformanceDetailDto.class);
        if (CollectionUtils.isNotEmpty(detailDtoList)) {
            Iterator<AssessmentProjectPerformanceDetailDto> detailDtoIterator = detailDtoList.iterator();
            while (detailDtoIterator.hasNext()) {
                AssessmentProjectPerformanceDetailDto target = detailDtoIterator.next();
                //添加从表
                AssessmentProjectPerformanceDetailDto detailDto = new AssessmentProjectPerformanceDetailDto();
                BeanUtils.copyProperties(target, detailDto);
                AssessmentItemDto assessmentItem = bpmRpcBoxService.getAssessmentItem(detailDto.getContentId());
                if (assessmentItem != null) {
                    detailDto.setContent(assessmentItem.getAssessmentDes());
                }
                detailDto.setCreator(processControllerComponent.getThisUser());
                detailDtos.add(detailDto);
            }
        }
        chksRpcAssessmentService.saveAssessmentProjectPerformance(JSON.toJSONString(dto), JSON.toJSONString(detailDtos));
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
     * 保存抽查数据
     *
     * @param assessmentProjectPerformanceDto
     * @param detailDtoList2
     */
    public void saveAndUpdateChkSpotAssessment(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, List<AssessmentProjectPerformanceDetailDto> detailDtoList2) {
        assessmentProjectPerformanceDto.setAppKey(applicationConstant.getAppKey());
        assessmentProjectPerformanceDto.setCreator(processControllerComponent.getThisUser());
        assessmentProjectPerformanceDto.setExaminePeople(processControllerComponent.getThisUser());
        assessmentProjectPerformanceDto.setExamineDate(new Date());

        if (assessmentProjectPerformanceDto.getProjectId() != null) {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(assessmentProjectPerformanceDto.getProjectId());
            if (projectInfo != null) {
                assessmentProjectPerformanceDto.setProjectName(projectInfo.getProjectName());
            }
        }
        assessmentProjectPerformanceDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
        if (StringUtils.isNotBlank(assessmentProjectPerformanceDto.getProcessInsId())) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(assessmentProjectPerformanceDto.getProcessInsId());
            if (projectPlanDetails != null) {
                assessmentProjectPerformanceDto.setTableId(projectPlanDetails.getId());
                //业务标识
                if (projectPlanDetails.getProjectWorkStageId() != null) {
                    ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
                    if (projectWorkStage != null) {
                        assessmentProjectPerformanceDto.setBusinessKey(projectWorkStage.getWorkStageName());
                    }
                }
            }
        }
        List<AssessmentProjectPerformanceDetailDto> detailDtos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(detailDtoList2)) {
            Iterator<AssessmentProjectPerformanceDetailDto> detailDtoIterator = detailDtoList2.iterator();
            while (detailDtoIterator.hasNext()) {
                AssessmentProjectPerformanceDetailDto target = detailDtoIterator.next();
                //添加从表
                AssessmentProjectPerformanceDetailDto detailDto = new AssessmentProjectPerformanceDetailDto();
                BeanUtils.copyProperties(target, detailDto);
                AssessmentItemDto assessmentItem = bpmRpcBoxService.getAssessmentItem(detailDto.getContentId());
                if (assessmentItem != null) {
                    detailDto.setContent(assessmentItem.getAssessmentDes());
                }
                detailDto.setCreator(processControllerComponent.getThisUser());
                detailDtos.add(detailDto);
            }
        }
        chksRpcAssessmentService.saveAssessmentProjectPerformance(JSON.toJSONString(assessmentProjectPerformanceDto), JSON.toJSONString(detailDtos));
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
        if (CollectionUtils.isNotEmpty(assessmentProjectPerformanceDtos)){
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


}
