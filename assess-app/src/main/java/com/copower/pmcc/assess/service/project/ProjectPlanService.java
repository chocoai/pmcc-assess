package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
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
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private CommonService commonService;

    public ProjectPlan getProjectplanByProcessInsId(String processInsId) {
        return projectPlanDao.getProjectPlanByProcessInsId(processInsId);
    }

    public ProjectPlan getProjectplanById(Integer id) {
        return projectPlanDao.getProjectPlanById(id);
    }

    public List<ProjectPlan> getProjectplanByProjectId(Integer projectId, String status) {
        return projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), status);
    }

    public List<ProjectPlan> getProjectPlanList2(Integer projectId, Integer workStageId, Integer categoryId) {
        return projectPlanDao.getProjectPlanList2(projectId, workStageId, categoryId);
    }

    public List<ProjectPlan> getProjectPlanList(Integer projectId) {
        return projectPlanDao.getProjectPlanList(projectId);
    }

    public ProjectPlan getProjectPlan(Integer projectId, Integer stageSort) {
        ProjectPlan where = new ProjectPlan();
        where.setProjectId(projectId);
        where.setStageSort(stageSort);
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlan(where);
        if (CollectionUtils.isNotEmpty(projectPlans)) return projectPlans.get(0);
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean initProjectPlanDetails(Integer planId) throws BusinessException {
        ProjectPlan projectPlan = projectPlanDao.getProjectPlanById(planId);

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        if (!projectWorkStage.getBisLoadDefalut()) {
            return false;//如果不加载默认工作事项，则不进行添加
        }
        List<ProjectPhase> projectPhases = projectPhaseService.getCacheProjectPhaseByCategoryId(projectPlan.getCategoryId());
        List<ProjectPhase> filter = LangUtils.filter(projectPhases, o -> {
            return o.getWorkStageId().equals(projectPlan.getWorkStageId());
        });

        for (ProjectPhase item : filter) {
            ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
            projectPlanDetails.setProjectPhaseName(item.getProjectPhaseName());
            projectPlanDetails.setPlanHours(item.getPhaseTime());
            projectPlanDetails.setPlanId(projectPlan.getId());
            projectPlanDetails.setProjectId(projectPlan.getProjectId());
            projectPlanDetails.setProjectPhaseId(item.getId());
            projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            projectPlanDetails.setSorting(item.getPhaseSort());
            projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
            projectPlanDetails.setFirstPid(0);
            if (item.getPid() > 0) {
                projectPlanDetails.setPid(item.getPid());
            }
            projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
        }
        // ddlMySqlAssist.customTableDdl(sql);//更新数据
        //更新PID
        List<ProjectPlanDetails> projectPlanDetailsByPlanAndPid = projectPlanDetailsDao.getProjectPlanDetailsByPlanAndPid(projectPlan.getId());
        if (CollectionUtils.isNotEmpty(projectPlanDetailsByPlanAndPid)) {
            for (ProjectPlanDetails item : projectPlanDetailsByPlanAndPid) {

                ProjectPlanDetails projectPlanDetailsPid = new ProjectPlanDetails();
                projectPlanDetailsPid.setPlanId(item.getPlanId());
                projectPlanDetailsPid.setProjectPhaseId(item.getPid());

                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetailsPid);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    item.setPid(projectPlanDetailsList.get(0).getId());
                    item.setBisLastLayer(true);
                    projectPlanDetailsDao.updateProjectPlanDetails(item);
                }
                updatePid(item.getPid());
            }
        }
        return true;
    }

    public void updateDetailsSorting(String detailsSoring) {
        List<KeyValueDto> keyValueDtos = JSON.parseArray(detailsSoring, KeyValueDto.class);

        StringBuilder stringBuilder = new StringBuilder();
        String sql = "UPDATE tb_project_plan_details SET sorting=%s WHERE id=%s;";

        for (KeyValueDto item : keyValueDtos) {
            int sorting = 0;
            Integer pid = Integer.valueOf(item.getExplain());
            if (Integer.valueOf(item.getExplain()) > 0) {
                sorting = Integer.valueOf(item.getValue()) - pid * 100;
            } else {
                sorting = Integer.valueOf(item.getValue()) - 1000;
            }
            String format = String.format(sql, sorting, item.getKey());
            stringBuilder.append(format);
        }
        if (StringUtils.isNotBlank(stringBuilder.toString())) {
            ddlMySqlAssist.customTableDdl(stringBuilder.toString());//更新数据
        }
    }

    private void updatePid(Integer pid) {
        if (pid > 0) {
            //更新相应父级信息
            ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
            projectPlanDetailsWhere.setPid(pid);
            List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);

            if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                ProjectPlanDetails projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(pid);
                projectPlanDetails.setBisLastLayer(false);
                projectPlanDetails.setBisEnable(true);
                projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
                updatePid(projectPlanDetails.getPid());
            }
        }
    }

    public void deletePlan(Integer detailsId) throws BusinessException {

        ProjectPlanDetails projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(detailsId);
        int pid = projectPlanDetails.getPid();

        //删除当前行，如果当前行是目录下的最后一行，则将上级设置为一级
        if (!projectPlanDetailsDao.deleteProjectPlanDetails(detailsId)) {
            throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
        }
        if (pid > 0) {
            ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
            projectPlanDetailsWhere.setPid(pid);
            List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
            if (CollectionUtils.isEmpty(projectPlanDetailsList)) {
                projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(pid);
                projectPlanDetails.setBisLastLayer(true);
                projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
            }
        }

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
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
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

    /**
     * @param projectId
     * @return
     */
    public Boolean isAllPlanFinish(Integer projectId) {
        if (projectId == null) return false;
        List<ProjectPlan> projectPlanList = projectPlanDao.getProjectPlanList(projectId);
        if (CollectionUtils.isEmpty(projectPlanList)) {
            for (ProjectPlan projectPlan : projectPlanList) {
                if (!ProjectStatusEnum.FINISH.getKey().equalsIgnoreCase(projectPlan.getStatus()))
                    return false;
            }
        }
        return true;
    }
}
