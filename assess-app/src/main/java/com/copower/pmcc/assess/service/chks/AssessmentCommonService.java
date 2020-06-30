package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssessmentCommonService {
    private final static Logger logger = LoggerFactory.getLogger(AssessmentCommonService.class);
    public final static String BPM_ASSESSMENT_PERFORMANCE_KEY = "performance";//质量考核
    public final static String BPM_ASSESSMENT_WORK_HOURS_KEY = "work.hours";// 工时考核


    public final static String PROJECT_TASK_BUSINESS_KEY_PERFORMANCE = "assessment.performance";//质量考核任务
    public final static String PROJECT_TASK_BUSINESS_KEY_WORK_HOURS = "assessment.work.hours";//工时考核任务
    @Autowired
    private AssessmentPerformanceService chksAssessmentProjectPerformanceService;
    @Autowired
    private AssessmentWorkHoursService assessmentWorkHoursService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;


    /**
     * 进入审批页面时，生成质量考核任务，工时考核任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void generateAssessmentTask(String processInsId, Integer boxId, String taskId, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) throws BpmException {
        try {
            BootstrapTableVo tableVo = bpmRpcProcessInsManagerService.getApprovalLogForApp(applicationConstant.getAppKey(), processInsId, 0, 1000);
            List<BoxApprovalLogVo> rows = tableVo.getRows();
            String byExamineUser = null;
            BoxApprovalLogVo approvalLogVo = getPreBoxApprovalLogVo(rows, taskId);
            if (approvalLogVo != null)
                byExamineUser = approvalLogVo.getCreator();
            chksAssessmentProjectPerformanceService.generateAssessmentTask(processInsId, boxId, taskId, byExamineUser, projectInfo, projectPlanDetails);
            assessmentWorkHoursService.generateWorkHoursTask(processInsId, boxId, taskId, byExamineUser, projectInfo, projectPlanDetails);
        } catch (Exception e) {
            logger.error("生成考核任务异常：" + e.getMessage(), e);
        }
    }

    //获取前一条审核日志
    private BoxApprovalLogVo getPreBoxApprovalLogVo(List<BoxApprovalLogVo> rows, String taskId) throws BpmException {
        if (CollectionUtils.isNotEmpty(rows)) {
            ActivitiTaskNodeDto activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, commonService.thisUserAccount());
            if (activitiTaskNodeDto == null) return null;
            for (BoxApprovalLogVo row : rows) {
                if (TaskHandleStateEnum.AGREE.getDesc().equalsIgnoreCase(row.getConclusion()) && row.getSorting() < activitiTaskNodeDto.getCurrentStep())
                    return row;
            }
        }
        return null;
    }

    /**
     * 提交审批流程时创建或清除待提交任务，处理质量考核任务，工时考核任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void createProjectTask(ApprovalModelDto approvalModelDto, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
        try {
            chksAssessmentProjectPerformanceService.createAssessmentProjectTask(approvalModelDto, projectInfo, projectPlanDetails);
            assessmentWorkHoursService.createAssessmentProjectTask(approvalModelDto, projectInfo, projectPlanDetails);
        } catch (Exception e) {
            logger.error("审批生成待提交任务异常：" + e.getMessage(), e);
        }
    }

    /**
     * 是否为抽查组成员
     *
     * @param boxId       模型 id
     * @param userAccount 当前登陆人
     * @return
     */
    public boolean isSpotGroupUser(Integer boxId, String userAccount) {
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
     * 获取抽查节点数据
     *
     * @param boxId
     * @return
     */
    public List<Integer> getSpotBoxReActivityIds(Integer boxId) {
        List<BoxReActivityDto> reActivityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (CollectionUtils.isEmpty(reActivityDtos)) return null;
        List<Integer> list = Lists.newArrayList();
        for (BoxReActivityDto reActivityDto : reActivityDtos) {
            if (reActivityDto.getBisSpotCheck() == Boolean.TRUE)
                list.add(reActivityDto.getId());
        }
        return list;
    }
}
