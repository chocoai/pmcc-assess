package com.copower.pmcc.assess.service.project.plan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanDetailsDto;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanFinancialClaimFastDto;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.csr.CsrBorrowerService;
import com.copower.pmcc.assess.service.event.project.ProjectTaskEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.regexp.RE;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 10:42
 */
@Service
public class ProjectPlanFinancialClaimService {
    @Autowired
    private CsrBorrowerService csrBorrowerService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;

    public BootstrapTableVo getProjectPlanDetailsByProjectId(Integer projectId, Integer planId) {

        /**
         * 变更处理方法：人为构造JSOn
         * 1、根据计划编号取得所有PID为0的计划（通过分页方式进行读取），如果没有取到，则将相应的人员添加到数据库中
         * 2、根据取得的数据取各自对应的计划明细内容，并构建相应的JSON字段，字段名称根据具体内容的成果服务名称确定
         * 3、通过JSON将PID为0的记录序列化为JSON，然后将明细的字段添加到JSON当中
         * 4、将一次大循环的JSON添加到JSON数组，JSON数组通过StringBuilder进行构建
         * 5、将字段串转化为JSONObject类进行返回
         */
        //        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
        //        if (CollectionUtils.isEmpty(projectPlanDetailsVos)) {
        //            InitProjectPlanDetails(projectId, planId);
        //            projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
        //        }

        //return projectPlanDetailsVos;
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectPlanDetails> projectPlanDetailsList = getProjectPlanDetailsByPage(planId, bootstrapTableVo);
        if (CollectionUtils.isEmpty(projectPlanDetailsList)) {
            insertProjectPlanDetailsByBorrowers(projectId, planId);
            //添加客户到明细表中
            projectPlanDetailsList = getProjectPlanDetailsByPage(planId, bootstrapTableVo);
        }
        StringBuilder stringBuilder = new StringBuilder();

        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
            stringBuilder.append("[");
            for (ProjectPlanDetails item : projectPlanDetailsList) {
                String string = JSON.toJSONString(item);
                string = string.replaceAll("}", ",");
                stringBuilder.append(string);
                List<ProjectPlanDetails> detailsList = projectPlanDetailsDao.getProjectPlanDetailsList(planId, item.getId(), "");
                if (CollectionUtils.isEmpty(detailsList)) {
                    //添加数据
                    insertProjectPlanDetailsByPid(item);
                    detailsList = projectPlanDetailsDao.getProjectPlanDetailsList(planId, item.getId(), "");
                }
                String details = "";
                for (ProjectPlanDetails detailsItem : detailsList) {

                    ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getProjectPlanDetailsVo(detailsItem);
                    details = getString(details, projectPlanDetailsVo);
                }
                details = details.substring(0, details.length() - 1);
                stringBuilder.append(details);
                stringBuilder.append("},");
            }
        }
        String string = stringBuilder.toString();
        if (StringUtils.isNotBlank(string)) {
            string = string.substring(0, string.length() - 1);
        }
        stringBuilder.append("]");
        JSONArray objects = JSON.parseArray(String.format("%s]", string));
        bootstrapTableVo.setRows(objects);
        return bootstrapTableVo;

    }

    private String getString(String details, ProjectPlanDetailsVo projectPlanDetailsVo) {
        if (StringUtils.isNotBlank(projectPlanDetailsVo.getExecuteUserName())) {
            details += (String.format("\"%sexecuteUserName\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), projectPlanDetailsVo.getExecuteUserName()));
        } else {
            details += (String.format("\"%sexecuteUserName\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), ""));
        }
        if (projectPlanDetailsVo.getPlanStartDate() != null) {
            details += (String.format("\"%splanStartDate\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), DateUtils.format(projectPlanDetailsVo.getPlanStartDate(), DateUtils.DATE_PATTERN)));
        } else {
            details += (String.format("\"%splanStartDate\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), ""));
        }
        if (projectPlanDetailsVo.getPlanEndDate() != null) {
            details += (String.format("\"%splanEndDate\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), DateUtils.format(projectPlanDetailsVo.getPlanEndDate(), DateUtils.DATE_PATTERN)));
        } else {
            details += (String.format("\"%splanEndDate\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), ""));
        }

        if (projectPlanDetailsVo.getId() != null) {
            details += (String.format("\"%sid\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), projectPlanDetailsVo.getId()));
        } else {
            details += (String.format("\"%sid\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), "0"));
        }
        if (projectPlanDetailsVo.getExecuteUserAccount() != null) {
            details += (String.format("\"%sexecuteUserAccount\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), projectPlanDetailsVo.getExecuteUserAccount()));
        } else {
            details += (String.format("\"%sexecuteUserAccount\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), ""));
        }
        if (projectPlanDetailsVo.getPlanHours() != null) {
            details += (String.format("\"%splanHours\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), projectPlanDetailsVo.getPlanHours()));
        } else {
            details += (String.format("\"%splanHours\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), "0"));
        }
        if (projectPlanDetailsVo.getPlanRemarks() != null) {
            details += (String.format("\"%splanRemarks\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), projectPlanDetailsVo.getPlanRemarks()));
        } else {
            details += (String.format("\"%splanRemarks\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), ""));
        }
        if (projectPlanDetailsVo.getProjectPhaseName() != null) {
            details += (String.format("\"%sprojectPhaseName\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), projectPlanDetailsVo.getProjectPhaseName()));
        } else {
            details += (String.format("\"%sprojectPhaseName\":\"%s\",", projectPlanDetailsVo.getDeclareFormName(), ""));
        }
        return details;
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByPage(Integer planId, BootstrapTableVo bootstrapTableVo) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        String search = requestBaseParam.getSearch();
        if (StringUtils.isNotBlank(search)) {
            search = String.format("%%%s%%", search);
        }
        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getProjectPlanDetailsList(planId, 0, search);
        bootstrapTableVo.setTotal(page.getTotal());
        return projectPlanDetailsList;
    }

    @Transactional(rollbackFor = Exception.class)
    private void insertProjectPlanDetailsByBorrowers(Integer projectId, Integer planId) {
        //取得当前项目的所有客户信息
        List<CsrBorrowerVo> csrBorrowers = csrBorrowerService.getCsrBorrowerByProjectId(projectId);
        //对每一个客户添加相应的默认工作任务事项
        StringBuilder stringBuilder = new StringBuilder();
        String sqlTemp = "insert into tb_project_plan_details (project_phase_name, plan_id, project_id,project_phase_id, status, " + "sorting,project_work_stage_id, first_pid, pid, bis_last_layer) " +
                "values ('%s',%s,%s,%s,'%s',0,%s,1,0,false);";

        ProjectPlan projectPlan = projectPlanDao.getProjectplanById(planId);
        if (CollectionUtils.isNotEmpty(csrBorrowers)) {
            for (CsrBorrowerVo item : csrBorrowers) {

                String string = String.format(sqlTemp, item.getName(), planId, projectId, item.getId(), ProcessStatusEnum.NOPROCESS.getValue(), projectPlan.getWorkStageId());
                stringBuilder.append(string);
            }
        }
        String string = stringBuilder.toString();
        if (StringUtils.isNotBlank(string)) {
            ddlMySqlAssist.customTableDdl(string);//更新数据
        }
    }

    @Transactional(rollbackFor = Exception.class)
    private void insertProjectPlanDetailsByPid(ProjectPlanDetails projectPlanDetails) {
        StringBuilder stringBuilder = new StringBuilder();
        String sqlTemp = "insert into tb_project_plan_details (project_phase_name,plan_hours, plan_id, project_id,project_phase_id, status, sorting,project_work_stage_id, first_pid, pid, " +
                "bis_last_layer) values ('%s',%s,%s,%s,%s,'%s',%s,%s,%s,%s,%s);";
        ProjectPlan projectPlan = projectPlanDao.getProjectplanById(projectPlanDetails.getPlanId());
        List<ProjectPhase> projectPhases = projectPhaseService.getCacheProjectPhaseByCategoryId(projectPlan.getCategoryId());
        List<ProjectPhase> filter = LangUtils.filter(projectPhases, o -> {
            return o.getWorkStageId().equals(projectPlan.getWorkStageId());
        });

        if (CollectionUtils.isNotEmpty(filter)) {
            for (ProjectPhase item : filter) {

                String string = String.format(sqlTemp, String.format("%s|%s", projectPlanDetails.getProjectPhaseName(), item.getProjectPhaseName()), item.getPhaseTime(), projectPlanDetails
                        .getPlanId(), projectPlanDetails.getProjectId(), item.getId(), ProcessStatusEnum.NOPROCESS.getValue(), item.getPhaseSort(), projectPlan.getWorkStageId(), 0,
                        projectPlanDetails.getId(), true);
                stringBuilder.append(string);
            }
        }
        String string = stringBuilder.toString();
        if (StringUtils.isNotBlank(string)) {
            ddlMySqlAssist.customTableDdl(string);//更新数据
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void fastSetPlan(ProjectPlanFinancialClaimFastDto projectPlanFinancialClaimFastDto) throws BusinessException {
        if (projectPlanFinancialClaimFastDto == null) {
            throw new BusinessException("没有要保存的数据");
        }
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsService.getPlanDetailsByPlanId(projectPlanFinancialClaimFastDto.getPlanId());
        if (StringUtils.isNotBlank(projectPlanFinancialClaimFastDto.getCustomerList())) {
            List<String> strings = FormatUtils.transformString2List(projectPlanFinancialClaimFastDto.getCustomerList());
            projectPlanDetails = LangUtils.filter(projectPlanDetails, o -> {
                return strings.contains(String.valueOf(o.getPid()));
            });
        }
        if (StringUtils.isNotBlank(projectPlanFinancialClaimFastDto.getPhaseList())) {
            List<String> strings = FormatUtils.transformString2List(projectPlanFinancialClaimFastDto.getPhaseList());
            projectPlanDetails = LangUtils.filter(projectPlanDetails, o -> {
                return strings.contains(String.valueOf(o.getProjectPhaseId()));
            });
        }
        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
            for (ProjectPlanDetails item : projectPlanDetails) {
                item.setPlanStartDate(DateUtils.parse(projectPlanFinancialClaimFastDto.getPlanStartDate()));
                item.setPlanEndDate(DateUtils.parse(projectPlanFinancialClaimFastDto.getPlanEndDate()));
                item.setPlanHours(projectPlanFinancialClaimFastDto.getPlanHours());
                item.setExecuteUserAccount(projectPlanFinancialClaimFastDto.getExecuteUserAccount());
                try {
                    projectPlanDetailsDao.updateProjectPlanDetails(item);
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
            }
        }

    }

    public void updateProjectPlanDetails(ProjectPlanDetails projectPlanDetails) throws BusinessException {
        projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
    }

    public void saveFinancialClaimProjectPlan(String formData) throws BusinessException {
        projectPlanService.saveFinancialClaimProjectPlan(formData, commonService.thisUserAccount());
    }

    public List<ProjectPlanDetailsVo> getProjectDetailsTask(ProjectPlanDetails projectPlanDetails) {
        List<ProjectPlanDetailsVo> projectDetailsTask = new ArrayList<>();

        if (projectPlanDetails.getReturnDetailsId() > 0) {
            ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getProjectPlanDetailsVo(projectPlanDetails);
            projectDetailsTask.add(projectPlanDetailsVo);
        } else {
            ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
            projectPlanDetailsWhere.setPid(projectPlanDetails.getPid());
            projectPlanDetailsWhere.setExecuteUserAccount(projectPlanDetails.getExecuteUserAccount());
            projectDetailsTask = projectPlanDetailsService.getProjectDetailsTask(projectPlanDetailsWhere);
        }

        return projectDetailsTask;
    }

    public void updatePlanTaskActualHours(Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
        try {
            if (projectPlanDetails != null) {
                projectPlanDetails.setTaskRemarks(taskRemarks);
                projectPlanDetails.setActualHours(new BigDecimal(actualHours));
                projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public void submitTask(String projectDetailsIds, Integer projectDetailsId) throws BusinessException {
        /**
         * 处理步聚
         * 1、根据配置查询是否需 要进行模型审批，如果不审批则完成相应的工作成果提交
         * 2、保存相应的业务数据
         *          */

        List<String> strings = FormatUtils.transformString2List(projectDetailsIds);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectDetailsId);

        ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
        projectPlanDetailsWhere.setPid(projectPlanDetails.getPid());
        projectPlanDetailsWhere.setExecuteUserAccount(projectPlanDetails.getExecuteUserAccount());
        List<ProjectPlanDetails> projectDetails = projectPlanDetailsService.getProjectDetails(projectPlanDetailsWhere);

        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());

        for (ProjectPlanDetails item : projectDetails) {

            if (!strings.contains(item.getId().toString())) {
                //如果当前事项不属于当前提交的表单内容，则不执行以下操作
                continue;
            }
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(item.getProjectPhaseId());
            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(item.getProjectWorkStageId());
            ProcessUserDto processUserDto = new ProcessUserDto();
            ProcessInfo processInfo = new ProcessInfo();
            String boxName = projectPhase.getBoxName();
            if (StringUtils.isNotBlank(boxName)) {
                //发起相应的流程
                String folio = item.getProjectPhaseName() + "【成果提交】" + projectInfo.getProjectName();
                Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(boxName);
                BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
                processInfo.setProjectId(item.getProjectId());
                processInfo.setProcessName(boxReDto.getProcessName());
                processInfo.setGroupName(boxReDto.getGroupName());
                processInfo.setFolio(folio);//流程描述
                processInfo.setTableName("tb_project_plan_details");
                processInfo.setTableId(item.getId());
                processInfo.setBoxId(boxReDto.getId());
                processInfo.setWorkStage(projectWorkStage.getWorkStageName());
                processInfo.setProcessEventExecutorName(ProjectTaskEvent.class.getSimpleName());
                processInfo.setWorkStageId(projectWorkStage.getId());

                try {
                    processUserDto = processControllerComponent.processStart(processInfo, commonService.thisUserAccount(), false);

                } catch (BpmException e) {
                    throw new BusinessException(e.getMessage());
                }

                //更新业务
                item.setProcessInsId(processUserDto.getProcessInsId());
                item.setStatus(ProcessStatusEnum.RUN.getValue());
                projectPlanDetailsDao.updateProjectPlanDetails(item);

                if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
                    try {
                        processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
                    } catch (BpmException e) {
                        throw new BusinessException("跳过节点自动提交失败");
                    }
                }

            } else {
                item.setStatus(ProcessStatusEnum.FINISH.getValue());
                projectPlanDetailsDao.updateProjectPlanDetails(item);

            }
            //保存业务数据

            //更新任务状态
            bpmRpcProjectTaskService.deleteProjectTaskByPlanDetailsId(item.getId());
            //更新当前数据为最新
            if (item.getReturnDetailsId() > 0) {
                ProjectPlanDetails projectPlanDetailsById = projectPlanDetailsService.getProjectPlanDetailsById(item.getReturnDetailsId());
                projectPlanDetailsById.setBisNew(false);
                projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetailsById);
            }

        }

        projectPlanDetailsWhere = new ProjectPlanDetails();
        projectPlanDetailsWhere.setPlanId(projectPlanDetails.getPlanId());
        projectPlanDetailsWhere.setStatus(ProcessStatusEnum.RUN.getValue());
        projectPlanDetailsWhere.setBisLastLayer(true);
        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
        if (CollectionUtils.isEmpty(projectPlanDetailsList)) {
            //任务都执行则发起相应的整体复核流程
            projectTaskAllService.startTaskAllApproval(projectPlanDetails.getPlanId());
        }

    }
}
