package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectPlanCount;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanDetailsDto;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanSetDto;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/29
 * @time: 9:17
 */
@Service
public class ProjectPlanService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private ProjectInfoService projectInfoService;

    public ProjectPlan getProjectplanByProcessInsId(String processInsId) {
        return projectPlanDao.getProjectPlanByProcessInsId(processInsId);
    }

    public ProjectPlan getProjectplanById(Integer id) {
        return projectPlanDao.getProjectPlanById(id);
    }

    public List<ProjectPlan> getProjectplanByProjectId(Integer projectId, String status) {
        return projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), status);
    }

    public List<ProjectPlan> getProjectPlanList(Integer projectId) {
        return projectPlanDao.getProjectPlanList(projectId);
    }

    /**
     * 保存项目任务数据
     *
     * @param item
     * @param projectName
     * @param workStageName
     * @param responsibileModelEnum
     */
    public void saveProjectPlanDetailsResponsibility(ProjectPlanDetails item, String projectName, String workStageName, ResponsibileModelEnum responsibileModelEnum) throws BpmException {
        ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
        projectPlanResponsibility.setPlanId(item.getPlanId());
        projectPlanResponsibility.setPlanDetailsId(item.getId());
        projectPlanResponsibility.setPlanDetailsName(String.format("%s[%s]", workStageName, item.getProjectPhaseName()));
        projectPlanResponsibility.setProjectId(item.getProjectId());
        projectPlanResponsibility.setProjectName(projectName);
        projectPlanResponsibility.setProcessInsId(item.getProcessInsId());
        projectPlanResponsibility.setUserAccount(item.getExecuteUserAccount());
        projectPlanResponsibility.setModel(responsibileModelEnum.getId());
        try {
            projectPlanResponsibility.setCreator(processControllerComponent.getThisUser());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        projectPlanResponsibility.setConclusion(responsibileModelEnum.getName());//
        projectPlanResponsibility.setPlanEndTime(item.getPlanEndDate());
        projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
        updateProjectTaskUrl(responsibileModelEnum, projectPlanResponsibility);
        bpmRpcProjectTaskService.saveProjectTaskExtend(projectPlanResponsibility);
    }

    /**
     * 设置项目任务的url
     *
     * @param responsibileModelEnum
     * @param projectPlanResponsibility
     */
    public void updateProjectTaskUrl(ResponsibileModelEnum responsibileModelEnum, ProjectResponsibilityDto projectPlanResponsibility) {
        if (projectPlanResponsibility == null)
            return;
        String url = new String();
        ///
        switch (responsibileModelEnum) {
            case TASK:
                url = "/" + applicationConstant.getAppKey() + "/ProjectTask/projectTaskIndex";
                break;
            case PLAN:
            case NEWPLAN:
                url = "/" + applicationConstant.getAppKey() + "/ProjectPlan/planIndex?planId=" + projectPlanResponsibility.getPlanId();
                break;
            case ALLTASK:
                url = "/" + applicationConstant.getAppKey() + "/projectTaskAll/projectTaskAllIndex?planId=" + projectPlanResponsibility.getPlanId();
                break;
            case DETAIL:
                url = "/" + applicationConstant.getAppKey() + "/ProjectTask/projectTaskDetailsById?planDetailsId=" + projectPlanResponsibility.getPlanDetailsId();
                break;
            default:
                break;
        }
        projectPlanResponsibility.setProjectDetailsUrl("/" + applicationConstant.getAppKey() + "/projectCenter/projectInfo?projectId=" + projectPlanResponsibility.getProjectId());
        projectPlanResponsibility.setUrl(url);
    }


    public String getProjectCurrStage(Integer projectId) {
        List<ProjectPlan> projectPlanByStatus = projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), ProjectStatusEnum.PLAN.getName());
        List<ProjectPlan> projectPlanByStatus1 = projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), ProjectStatusEnum.TASK.getName());
        List<String> transform = LangUtils.transform(projectPlanByStatus, o -> {
            return o.getPlanName() + "[安排计划]";
        });
        List<String> transform1 = LangUtils.transform(projectPlanByStatus1, o -> {
            return o.getPlanName() + "[提交工作成果]";
        });
        if (CollectionUtils.isEmpty(transform)) {
            transform = new ArrayList<>();
        }
        if (CollectionUtils.isNotEmpty(transform1)) {
            for (String s : transform1) {
                transform.add(s);
            }
        }
        if (CollectionUtils.isNotEmpty(transform)) {
            return FormatUtils.transformListString(transform);
        } else {
            return "";
        }
    }

    public Boolean updateProjectPlan(ProjectPlan projectPlan) {
        return projectPlanDao.updateProjectPlan(projectPlan);
    }

    /**
     * 进入下个阶段
     *
     * @param planId 当前阶段所处的总计划
     */
    @Transactional(rollbackFor = Exception.class)
    public void enterNextStage(Integer planId) throws BusinessException {
        //先获取分布式锁 保证进入下个阶段只有一个线程执行
        String prefix = CacheConstant.getCostsKeyPrefix(String.format("%s_%s_%s", applicationConstant.getAppKey(), ProjectPlanService.class.getSimpleName(), planId), "lock");
        RLock lock = redissonClient.getLock(prefix);
        boolean res = false;
        try { // 尝试加锁,最多等待10秒,上锁以后20秒自动解锁
            res = lock.tryLock(10, 20, TimeUnit.SECONDS);
            if (!res) {//加锁不成功,不执行逻辑
                logger.debug("----enterNextStage, Did not get the lock");
                return;
            }
            boolean isAllFinish = projectPlanDetailsService.isAllPlanDetailsFinish(planId);
            if (!isAllFinish) {
                return;
            }
            //1.将当前阶段设置结束，并清理所有任务
            ProjectPlan projectPlan = projectPlanDao.getProjectPlanById(planId);
            projectPlan.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
            projectPlan.setFinishDate(new Date());
            int currStageSort = projectPlan.getStageSort().intValue();
            projectPlanDao.updateProjectPlan(projectPlan);
            bpmRpcProjectTaskService.deleteProjectTaskByPlanId(applicationConstant.getAppKey(), planId);

            //2.检查是否存在同级别的阶段
            ProjectPlan projectPlanWhere = new ProjectPlan();
            projectPlanWhere.setProjectId(projectPlan.getProjectId());
            projectPlanWhere.setBisRestart(false);
            List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlan(projectPlanWhere);
            List<ProjectPlan> filter = LangUtils.filter(projectPlans, o -> {
                return (o.getStageSort().equals(currStageSort) && !o.getProjectStatus().equals(ProjectStatusEnum.FINISH.getKey()) && !o.getProjectStatus().equals(ProjectStatusEnum.CLOSE.getKey()));
            });

            //3.当前同级阶段都完成任务，则进入下一阶段
            if (CollectionUtils.isEmpty(filter)) {
                List<ProjectPlan> nextProjectPlans = getNextProjectPlans(currStageSort, projectPlans);
                if (CollectionUtils.isNotEmpty(nextProjectPlans)) {
                    for (ProjectPlan plan : nextProjectPlans) {
                        if (StringUtils.equals(ProjectStatusEnum.WAIT.getKey(), plan.getProjectStatus())) {
                            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(plan.getWorkStageId());
                            if (projectWorkStage.getStageForm().endsWith("Execute")) {//后台自动执行计划内容
                                ProjectPlanExecuteInterface bean = (ProjectPlanExecuteInterface) SpringContextUtils.getBean(projectWorkStage.getStageForm());
                                bean.execute(plan, projectWorkStage);
                            } else {
                                plan.setProjectStatus(ProjectStatusEnum.PLAN.getKey());
                                projectPlanDao.updateProjectPlan(plan);
                            }
                        }
                    }
                } else {
                    ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
                    projectInfo.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
                    projectInfoService.updateProjectInfo(projectInfo);
                }
            }
        } catch (InterruptedException e) {
            logger.error("get the lock error;" + e.getMessage(), e);
        } catch (BpmException e) {
            logger.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取下个阶段
     *
     * @param currStageSort
     * @param projectPlans
     * @return
     */
    private List<ProjectPlan> getNextProjectPlans(int currStageSort, List<ProjectPlan> projectPlans) {
        Ordering<Integer> orderingBig = new Ordering<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return left - right;
            }
        };
        List<Integer> stageSortList = LangUtils.transform(projectPlans, p -> p.getStageSort());
        stageSortList = orderingBig.sortedCopy(stageSortList);
        Integer nextStageSort = 0;
        for (Integer integer : stageSortList) {
            if (integer.intValue() > currStageSort) {
                nextStageSort = integer.intValue();
                break;
            }
        }
        List<ProjectPlan> nextStagePlanList = Lists.newArrayList();
        for (ProjectPlan plan : projectPlans) {
            if (nextStageSort.equals(plan.getStageSort().intValue())) {
                nextStagePlanList.add(plan);
            }
        }
        return nextStagePlanList;
    }

    /**
     * 获取当前项目阶段
     *
     * @param projectId
     * @return
     */
    public List<ProjectPlan> getCurrentProjectPlans(Integer projectId) {
        List<ProjectPlan> list = Lists.newArrayList();
        list.addAll(projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), ProjectStatusEnum.PLAN.getKey()));
        list.addAll(projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), ProjectStatusEnum.TASK.getKey()));
        return list;
    }

    public List<CustomProjectPlanCount> getPlanCountByMonth(String startMonth, String endMonth){
        return projectPlanDao.getPlanCountByMonth(startMonth, endMonth);
    }
}
