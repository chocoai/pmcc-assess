package com.copower.pmcc.assess.job;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.chks.AssessmentCommonService;
import com.copower.pmcc.assess.service.chks.AssessmentPerformanceService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 项目自动完成
 */
@Component
public class ProjectAutoFinishJob {
    private final static Logger logger = LoggerFactory.getLogger(ProjectAutoFinishJob.class);
    private final static String JOB_RUNNING_LOCK_KEY = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_JOB_PROJECT_AUTO_FINISH, "lock");
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private AssessmentPerformanceService performanceService;

    /**
     * 执行任务
     */
    public void excute() throws BusinessException {
        RLock lock = redissonClient.getLock(JOB_RUNNING_LOCK_KEY);
        // 尝试加锁,最多等待10秒,上锁以后20*60秒自动解锁
        boolean res = false;
        try {
            res = lock.tryLock(10, 20 * 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.debug("get the lock error;" + e.getMessage(), e);
        }
        //加锁不成功,不执行逻辑
        if (!res) {
            logger.debug("----ProjectAutoFinishJob, Did not get the lock");
            return;
        }
        logger.info("----ProjectAutoFinishJob, start---------");
        projectAutoFinish();
        clearAssessmentTask();
        logger.info("----ProjectAutoFinishJob, end---------");
    }

    //项目自动完成
    private void projectAutoFinish() {
        //1.先找出项目状态为正常的项目，再检查该项目的所有阶段是否完成，如果阶段都完成则将项目状态置为完成状态
        ProjectInfo where = new ProjectInfo();
        where.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());
        List<ProjectInfo> projectInfoList = projectInfoService.getProjectInfoList(where);
        if (CollectionUtils.isEmpty(projectInfoList)) return;
        for (ProjectInfo projectInfo : projectInfoList) {
            List<ProjectPlan> projectPlanList = projectPlanService.getProjectPlanList(projectInfo.getId());
            if (CollectionUtils.isEmpty(projectInfoList)) continue;
            List<ProjectPlan> filter = LangUtils.filter(projectPlanList, o -> ProjectStatusEnum.FINISH.getKey().equals(o.getProjectStatus()));
            if (CollectionUtils.isNotEmpty(filter) && filter.size() == projectPlanList.size()) {
                projectInfo.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
                projectInfoService.updateProjectInfo(projectInfo);
            }
        }
    }

    //清除无效的考核任务
    private void clearAssessmentTask() {
        ProjectResponsibilityDto responsibilityDto = new ProjectResponsibilityDto();
        responsibilityDto.setAppKey(applicationConstant.getAppKey());
        responsibilityDto.setBusinessKey(AssessmentCommonService.PROJECT_TASK_BUSINESS_KEY_PERFORMANCE);
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(responsibilityDto);
        if (CollectionUtils.isNotEmpty(projectTaskList)) {
            for (ProjectResponsibilityDto projectResponsibilityDto : projectTaskList) {
                if (StringUtils.isBlank(projectResponsibilityDto.getProcessInsId())) continue;
                if (StringUtils.isBlank(projectResponsibilityDto.getUserAccount())) continue;
                performanceService.clearAssessmentProjectTask(projectResponsibilityDto.getProcessInsId(), projectResponsibilityDto.getUserAccount());
            }
        }
    }
}
