package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectPlanSetEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanDetailsDto;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanDto;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanSetDto;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.event.project.ProjectPlanApprovalEvent;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.enums.SysProjectEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.*;
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

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
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

    public List<ProjectPlan> getProjectPlanList2(Integer projectId,Integer workStageId , Integer categoryId){
        return projectPlanDao.getProjectPlanList2(projectId, workStageId, categoryId) ;
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

    public void fastSetPlan(String fields, Integer planId, String detailsSoring) throws BusinessException {
        updateDetailsSorting(detailsSoring);
        if (StringUtils.isNotBlank(fields)) {
            List<ProjectPlanSetDto> planSetDtos = JSON.parseArray(fields, ProjectPlanSetDto.class);
            StringBuilder sb = new StringBuilder();
            for (ProjectPlanSetDto item : planSetDtos) {
                String value = "";
                CustomTableTypeEnum fieldsType = CustomTableTypeEnum.TEXT;
                if (StringUtils.isNotBlank(item.getFastValue())) {
                    switch (item.getFastFileds()) {
                        case "planStartDate":
                        case "planEndDate": {
                            Date planStartDate = DateUtils.getDateFromShortString(item.getFastValue());
                            if (planStartDate != null) {
                                value = DateUtils.format(planStartDate, DateUtils.DATETIME_PATTERN);
                            }

                            break;
                        }
                        case "planHours":
                        case "proportion": {
                            value = item.getFastValue();
                            fieldsType = CustomTableTypeEnum.DECIMAL;
                            break;
                        }
                        case "executeUserAccount": {
                            value = item.getFastValue();
                            break;
                        }
                        case "executeDepartmentId": {
                            value = item.getFastValue();
                            fieldsType = CustomTableTypeEnum.INT;
                            break;
                        }
                    }
                }

                if (StringUtils.isNotBlank(value)) {
                    String dbFields = FormatUtils.camelToUnderline(item.getFastFileds());
                    ProjectPlanSetEnum planSetEnum = ProjectPlanSetEnum.create(item.getFastRange());
                    String where = String.format("plan_id=%s and bis_last_layer=1", planId);
                    switch (planSetEnum) {
                        case NULL: {
                            where += String.format(" and (%s is null or %s='')", dbFields, dbFields);
                            break;
                        }
                    }

                    String sql = String.format("UPDATE tb_project_plan_details SET %s='%s' WHERE %s;", dbFields, value, where);
                    if (!fieldsType.equals(CustomTableTypeEnum.TEXT)) {
                        sql = String.format("UPDATE tb_project_plan_details SET %s=%s WHERE %s;", dbFields, value, where);
                    }
                    sb.append(String.format("UPDATE tb_project_plan_details SET %s=NULL WHERE plan_id=%s and bis_last_layer=0;", dbFields, planId));
                    sb.append(sql);

                }
            }
            if (StringUtils.isNotBlank(sb.toString())) {
                ddlMySqlAssist.customTableDdl(sb.toString());//更新数据
            }
        }

    }

    public void saveProjectPlanDetails(String ds) throws BusinessException {
        ProjectPlanDetailsDto projectPlanDetailsDto = JSON.parseObject(ds, ProjectPlanDetailsDto.class);
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        BeanUtils.copyProperties(projectPlanDetailsDto, projectPlanDetails);
        if (projectPlanDetails.getId() != null && projectPlanDetails.getId() > 0) {
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
        } else {

            Integer planChildCount = projectPlanDetailsDao.getPlanChildCount(projectPlanDetails.getPid(), projectPlanDetails.getProjectId(), projectPlanDetails.getProjectWorkStageId());
            projectPlanDetails.setSorting(planChildCount + 1);
            projectPlanDetails.setBisLastLayer(true);
            projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            if (projectPlanDetails.getFirstPid() == 0) {
                projectPlanDetails.setFirstPid(projectPlanDetails.getPid());
            }
            projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
        }

        //更新计划明细的顺序
        String detailsSoring = projectPlanDetailsDto.getDetailsSoring();
        updateDetailsSorting(detailsSoring);
        updatePid(projectPlanDetails.getPid());
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

    @Transactional(rollbackFor = Exception.class)
    public void saveFinancialClaimProjectPlan(String formData, String appointUserAccount) throws BusinessException, BpmException {
        ProjectPlanDto projectPlanDto = JSON.parseObject(formData, ProjectPlanDto.class);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDto.getProjectId());
        ProjectPlan projectPlan = projectPlanDao.getProjectPlanById(projectPlanDto.getId());//取得项目计划
        //更新计划内容
        projectPlan.setProjectPlanStart(projectPlanDto.getProjectPlanStart());
        projectPlan.setProjectPlanEnd(projectPlanDto.getProjectPlanEnd());
        projectPlan.setPlanRemarks(projectPlanDto.getPlanRemarks());
        projectPlanDao.updateProjectPlan(projectPlan);

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());

        /**
         * 处理过程
         * 1、判断最后一个层级的责任人或责任部门是否指定
         * 2、判断根据配置，计划是否需要进行相应的计划审批，如果要审批，则发起计划审批流程
         *3、如果都已指定，且不需要审批，则责任人的任务发起相应的任务流程，指定部门则发起相应的Pengding流程，部门领导继续安排项目计划
         * 4、保存项目计划
         */

        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsLastLayer(projectPlanDto.getId(), ProcessStatusEnum.NOPROCESS.getValue());
        //数据效性验证
        StringBuilder sb = new StringBuilder();
        for (ProjectPlanDetails item : projectPlanDetails) {
            if (item.getBisEnable() == false) {
                continue;//如果停用则不发起任务
            }
            if (item.getPid() > 0) {
                if (item.getPlanStartDate() == null) {
                    sb.append(String.format("%s请设置相应的[开始时间]<br/>", item.getProjectPhaseName()));
                }
                if (item.getPlanEndDate() == null) {
                    sb.append(String.format("%s请设置相应的[结束时间]<br/>", item.getProjectPhaseName()));
                }
                if (StringUtils.isBlank(item.getExecuteUserAccount())) {
                    sb.append(String.format("%s请设置相应的[责任人]<br/>", item.getProjectPhaseName()));
                }
            }
        }

        if (sb.length() > 0) {
            throw new BusinessException(sb.toString());
        }
        bpmRpcProjectTaskService.deleteProjectTaskByPlanId(applicationConstant.getAppKey(), projectPlan.getId());
        //====验证结束
        if (StringUtils.isNotBlank(projectWorkStage.getBoxName())) {
            //发起计划复核流程
            startProjectPlanApproval(projectPlan, projectInfo.getProjectName(), appointUserAccount);
        } else {
            for (ProjectPlanDetails item : projectPlanDetails) {

                if (item.getPid() > 0) {
                    saveProjectPlanDetailsResponsibility(item, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
                    item.setStatus(ProcessStatusEnum.RUN.getValue());
                    projectPlanDetailsDao.updateProjectPlanDetails(item);
                }
            }
            String sql = String.format("UPDATE tb_project_plan_details SET status='%s' WHERE plan_id=%s and bis_last_layer=1;", ProcessStatusEnum.RUN.getValue(), projectPlan.getId());
            ddlMySqlAssist.customTableDdl(sql);//更新数据

            //如果项目所有的计划都已完成，则更新当前阶段计划完成
            projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getName());
            projectPlanDao.updateProjectPlan(projectPlan);

        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveProjectPlan(String formData, String appointUserAccount) throws BusinessException, BpmException {
        ProjectPlanDto projectPlanDto = JSON.parseObject(formData, ProjectPlanDto.class);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDto.getProjectId());
        ProjectPlan projectPlan = projectPlanDao.getProjectPlanById(projectPlanDto.getId());//取得项目计划
        if (projectPlanDto.getBisChildren().equals("0")) {
            //更新计划内容
            projectPlan.setProjectPlanStart(projectPlanDto.getProjectPlanStart());
            projectPlan.setProjectPlanEnd(projectPlanDto.getProjectPlanEnd());
            projectPlan.setPlanRemarks(projectPlanDto.getPlanRemarks());
            projectPlanDao.updateProjectPlan(projectPlan);
        }
        //更新计划明细的顺序
        String detailsSoring = projectPlanDto.getDetailsSoring();
        if (StringUtils.isNotBlank(detailsSoring)) {
            updateDetailsSorting(detailsSoring);
        }
        bpmRpcProjectTaskService.deleteProjectTaskByPlanId(applicationConstant.getAppKey(), projectPlan.getId());//删除待提交任务
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());

        if (projectPlanDto.getDetailsPlan() != null && projectPlanDto.getDetailsPlan().equals("1"))//指定了下级细分人员，则写入任务对应表中
        {
            saveProjectPlanResponsibility(projectPlan, projectPlanDto.getNextApproval(), projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.NEWPLAN);
        } else {
            /**
             * 处理过程
             * 1、判断最后一个层级的责任人或责任部门是否指定
             * 2、判断根据配置，计划是否需要进行相应的计划审批，如果要审批，则发起计划审批流程
             *3、如果都已指定，且不需要审批，则责任人的任务发起相应的任务流程，指定部门则发起相应的Pengding流程，部门领导继续安排项目计划
             * 4、保存项目计划
             */

            List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsLastLayer(projectPlanDto.getId(), ProcessStatusEnum.NOPROCESS.getValue());
            if (CollectionUtils.isEmpty(projectPlanDetails))
                throw new BusinessException("请为阶段设置相关任务");
            //数据效性验证
            StringBuilder sb = new StringBuilder();
            for (ProjectPlanDetails item : projectPlanDetails) {
                if (item.getProjectPhaseId() == null || item.getProjectPhaseId() == 0) {
                    sb.append(String.format("%s请设置相应的[所属工作内容]<br/>", item.getProjectPhaseName()));
                }
                if (item.getPlanStartDate() == null) {
                    sb.append(String.format("%s请设置相应的[开始时间]<br/>", item.getProjectPhaseName()));
                }
                if (item.getPlanEndDate() == null) {
                    sb.append(String.format("%s请设置相应的[结束时间]<br/>", item.getProjectPhaseName()));
                }
                if (StringUtils.isBlank(item.getExecuteUserAccount()) && item.getExecuteDepartmentId() == null) {
                    sb.append(String.format("%s请设置相应的[责任人或责任部门中的一个]<br/>", item.getProjectPhaseName()));
                }
            }

            if (sb.length() > 0) {
                throw new BusinessException(sb.toString());
            }
            //====验证结束
            //将任务安排给相应的责任，如果还有部门时，则需要部门安排计划后再审批
            boolean bisAllUser = true;//是否所有细都安排具体人员
            for (ProjectPlanDetails item : projectPlanDetails) {
                if (StringUtils.isBlank(item.getExecuteUserAccount()) && item.getExecuteDepartmentId() != null) {
                    bisAllUser = false;
                    List<String> departmentDA = bpmRpcBoxRoleUserService.getDepartmentFgld(item.getExecuteDepartmentId());
                    if (CollectionUtils.isNotEmpty(departmentDA)) {
                        item.setExecuteUserAccount(FormatUtils.transformListString(departmentDA));
                    }
                    saveProjectPlanDetailsResponsibility(item, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.PLAN);

                    //更新当前级的上一级为第一级
                    item.setFirstPid(item.getId());
                    projectPlanDetailsDao.updateProjectPlanDetails(item);
                }
            }
            projectPlan.setProjectStatus(ProjectStatusEnum.PLAN.getKey());
            projectPlanDao.updateProjectPlan(projectPlan);
            if (bisAllUser) {
                if (StringUtils.isNotBlank(projectWorkStage.getBoxName())) {
                    //发起计划复核流程
                    startProjectPlanApproval(projectPlan, projectInfo.getProjectName(), appointUserAccount);
                } else {
                    for (ProjectPlanDetails item : projectPlanDetails) {

                        if (StringUtils.isNotBlank(item.getExecuteUserAccount())) {
                            saveProjectPlanDetailsResponsibility(item, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
                            item.setStatus(ProcessStatusEnum.RUN.getValue());
                            projectPlanDetailsDao.updateProjectPlanDetails(item);
                        }
                    }
                    String sql = String.format("UPDATE tb_project_plan_details SET status='%s' WHERE plan_id=%s and bis_last_layer=1;", ProcessStatusEnum.RUN.getValue(), projectPlan.getId());
                    ddlMySqlAssist.customTableDdl(sql);//更新数据

                    //如果项目所有的计划都已完成，则更新当前阶段计划完成
                    projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
                    projectPlanDao.updateProjectPlan(projectPlan);

                }
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
     * 保存项目任务数据
     *
     * @param projectPlan
     * @param nextUser
     * @param projectName
     * @param workStageName
     * @param responsibileModelEnum
     */
    public void saveProjectPlanResponsibility(ProjectPlan projectPlan, String nextUser, String projectName, String workStageName, ResponsibileModelEnum responsibileModelEnum) throws BpmException {
        ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
        projectPlanResponsibility.setPlanId(projectPlan.getId());
        projectPlanResponsibility.setPlanDetailsId(0);
        projectPlanResponsibility.setPlanDetailsName(String.format("%s[%s]", workStageName, responsibileModelEnum.getName()));
        projectPlanResponsibility.setProjectId(projectPlan.getProjectId());
        projectPlanResponsibility.setProjectName(projectName);
        projectPlanResponsibility.setUserAccount(nextUser);
        projectPlanResponsibility.setModel(responsibileModelEnum.getId());
        projectPlanResponsibility.setCreator(processControllerComponent.getThisUser());
        projectPlanResponsibility.setConclusion(responsibileModelEnum.getName());//
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
        }
        projectPlanResponsibility.setProjectDetailsUrl("/" + applicationConstant.getAppKey() + "/projectInfo/projectDetails?projectId=" + projectPlanResponsibility.getProjectId());
        projectPlanResponsibility.setUrl(url);
    }

    /**
     * 功能:发起项目计划审批流程
     *
     * @param projectPlan
     * @throws BusinessException
     */
    private void startProjectPlanApproval(ProjectPlan projectPlan, String projectName, String appointUserAccount) throws BusinessException {
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());

        //发起审批流程
        String folio = String.format("计划审批【%s】%s", projectWorkStage.getWorkStageName(), projectName);
        ProcessUserDto processUserDto = new ProcessUserDto();
        Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectWorkStage.getBoxName());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectPlan.getProjectId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setTableName("tb_project_plan");
        processInfo.setTableId(projectPlan.getId());
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setWorkStage(projectWorkStage.getWorkStageName());
        processInfo.setProcessEventExecutorName(ProjectPlanApprovalEvent.class.getSimpleName());
        processInfo.setBisDraftStart(false);
        processInfo.setWorkStageId(projectWorkStage.getId());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, appointUserAccount, false);//发起流程，并返回流程实例编号
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlan.setProcessInsId(processUserDto.getProcessInsId());
        projectPlan.setStatus(ProcessStatusEnum.RUN.getValue());
        if (!projectPlanDao.updateProjectPlan(projectPlan)) {
            bpmRpcActivitiProcessManageService.closeProcess(processUserDto.getProcessInsId());
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());

        }
        if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
            try {
                processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
            } catch (BpmException e) {
                throw new BusinessException("跳过节点自动提交失败");
            }
        }
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

    public void submitPlanEdit(String formData) throws BusinessException {
        ProjectPlanDto projectPlanDto = JSON.parseObject(formData, ProjectPlanDto.class);
        ApprovalModelDto approvalModelDto = JSON.parseObject(formData, ApprovalModelDto.class);
        ProjectPlan projectPlan = projectPlanDao.getProjectPlanById(projectPlanDto.getId());//取得项目计划
        //更新计划内容
        projectPlan.setProjectPlanStart(projectPlanDto.getProjectPlanStart());
        projectPlan.setProjectPlanEnd(projectPlanDto.getProjectPlanEnd());
        projectPlan.setPlanRemarks(projectPlanDto.getPlanRemarks());
        projectPlanDao.updateProjectPlan(projectPlan);
        //更新计划明细的顺序
        String detailsSoring = projectPlanDto.getDetailsSoring();
        updateDetailsSorting(detailsSoring);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
        approvalModelDto.setWorkStageId(projectPlan.getWorkStageId());
        approvalModelDto.setWorkPhaseId(0);
        approvalModelDto.setOpinions("返回修改");
        approvalModelDto.setActivityKey(ProcessActivityEnum.EDIT.getValue());
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setCurrentStep(-1);

        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public ProjectPlan RestartPlan(ProjectWorkStageRestart projectWorkStageRestart) throws BpmException {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectWorkStageRestart.getProjectId());
        ProjectPlan projectPlan = getProjectplanById(projectWorkStageRestart.getProjectPlanOldId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        String workStageUserAccounts = projectWorkStageService.getWorkStageUserAccounts(projectWorkStage.getId(), projectPlan.getProjectId());
        if (StringUtils.isNotBlank(workStageUserAccounts)) {
            List<String> strings = FormatUtils.transformString2List(workStageUserAccounts);
            for (String s : strings) {
                saveProjectPlanResponsibility(projectPlan, s, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.NEWPLAN);
            }
        }
        return projectPlan;
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
        RLock lock = redissonClient.getLock(String.format("%s_%s_%s", applicationConstant.getAppKey(), ProjectPlanService.class.getSimpleName(), planId));
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
                            } else {//页面实施计划编制
                                String userAccounts = projectWorkStageService.getWorkStageUserAccounts(plan.getWorkStageId(), plan.getProjectId());
                                if (StringUtils.isNotBlank(userAccounts)) {
                                    List<String> strings = FormatUtils.transformString2List(userAccounts);
                                    for (String s : strings) {
                                        saveProjectPlanResponsibility(plan, s, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.NEWPLAN);
                                    }
                                } else {
                                    throw new BusinessException(projectWorkStage.getWorkStageName() + "阶段没有配置相应的责任人");
                                }
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
     * 重启阶段计划
     *
     * @param planId
     * @param reason
     */
    @Transactional(rollbackFor = Exception.class)
    public void replyProjectPlan(Integer planId, String reason) throws BusinessException {
        ProjectPlan projectPlan = this.getProjectplanById(planId);
        if (projectPlan == null) return;

        projectPlan.setProjectStatus(ProjectStatusEnum.PLAN.getKey());
        projectPlan.setRestartReason(reason);
        projectPlan.setProcessInsId("-1");
        projectPlan.setBisRestart(true);
        projectPlanDao.updateProjectPlan(projectPlan);

        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        String userAccounts = projectWorkStageService.getWorkStageUserAccounts(projectPlan.getWorkStageId(), projectPlan.getProjectId());
        if (StringUtils.isNotBlank(userAccounts)) {
            List<String> strings = FormatUtils.transformString2List(userAccounts);
            strings.forEach(o -> {
                try {
                    saveProjectPlanResponsibility(projectPlan, o, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.NEWPLAN);
                } catch (BpmException e) {
                    e.printStackTrace();
                }
            });
        } else {
            throw new BusinessException(projectWorkStage.getWorkStageName() + "阶段没有配置相应的责任人");
        }
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


}
