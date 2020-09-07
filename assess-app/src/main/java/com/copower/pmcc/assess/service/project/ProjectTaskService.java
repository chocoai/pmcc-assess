package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectTaskReturnRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectTaskDto;
import com.copower.pmcc.assess.dto.output.project.ProjectTaskReturnRecordVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.chks.AssessmentCommonService;
import com.copower.pmcc.assess.service.event.EmptyProcessEvent;
import com.copower.pmcc.assess.service.event.project.ProjectTaskEvent;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.executor.ProcessEventExecutor;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:45
 */
@Service
public class ProjectTaskService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectTaskReturnRecordDao projectTaskReturnRecordDao;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private AssessmentCommonService assessmentCommonService;

    @Transactional(rollbackFor = Exception.class)
    public void submitTask(ProjectTaskDto projectTaskDto) throws Exception {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectTaskDto.getProjectDetailsId());
        projectPlanDetails.setTaskSubmitTime(new Date());
        projectPlanDetails.setBisStart(true);
        if (projectPlanDetails.getActualHours() != null)
            projectPlanDetails.setActualHours(projectTaskDto.getActualHours().add(projectPlanDetails.getActualHours()));
        else
            projectPlanDetails.setActualHours(projectTaskDto.getActualHours());
        projectPlanDetails.setTaskRemarks(projectTaskDto.getTaskRemarks());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        String boxName = projectPhase.getBoxName();
        projectPlanDetails.setSubmitUser(processControllerComponent.getThisUser());
        if (projectPhase.getBisUseBox() == Boolean.TRUE || projectTaskDto.getMustUseBox() == Boolean.TRUE) {
            //发起相应的流程
            String folio = projectPlanDetails.getProjectPhaseName() + "【成果提交】" + projectInfo.getProjectName();
            Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(boxName);
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
            processInfo.setProjectId(projectPlanDetails.getProjectId());
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
            processInfo.setTableId(projectPlanDetails.getId());
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setWorkStage(projectWorkStage.getWorkStageName());
            processInfo.setProcessEventExecutorName(ProjectTaskEvent.class.getSimpleName());
            processInfo.setWorkStageId(projectWorkStage.getId());
            try {
                processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, projectTaskDto.getNextApproval(), false);
                publicService.setRedisProcessExecutorName(processUserDto.getProcessInsId(), ProjectTaskEvent.class.getSimpleName());
            } catch (BpmException e) {
                throw new BusinessException(e.getMessage());
            }

            //更新附件
            SysAttachmentDto sysAttachment = new SysAttachmentDto();
            sysAttachment.setProcessInsId("0");
            sysAttachment.setCreater(processControllerComponent.getThisUser());
            sysAttachment.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
            sysAttachment.setTableId(projectTaskDto.getProjectDetailsId());
            SysAttachmentDto sysAttachmentNew = new SysAttachmentDto();
            sysAttachmentNew.setProcessInsId(processUserDto.getProcessInsId());
            baseAttachmentService.updateAttachementByExample(sysAttachment, sysAttachmentNew);
            //更新业务
            setSubmitUserAll(projectPlanDetails, processUserDto.getProcessInsId());
            projectPlanDetails.setProcessInsId(processUserDto.getProcessInsId());
            projectPlanDetails.setStatus(ProcessStatusEnum.RUN.getValue());
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
        }
        //保存业务数据
        String viewUrl = "projectTaskAssist";
        if (StringUtils.isNotBlank(projectPhase.getPhaseForm())) {
            viewUrl = projectPhase.getPhaseForm();
        }
        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(viewUrl);
        try {
            boolean hangup = false;
            //需要放在所在业务bean的下面  例如申报等会有一些操作是下个阶段所需要的
            if (StringUtils.isNotBlank(processUserDto.getProcessInsId())) {
                if (projectPhase.getBisWait() != null && projectPhase.getBisWait()) {//可以挂起任务
                    hangup = true;
                }
            }
            if (hangup) {
                projectPlanDetails.setStatus(ProcessStatusEnum.HANGUP.getValue());//将任务设为挂起
                projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
                bean.applyCommit(projectPlanDetails, processUserDto.getProcessInsId(), projectTaskDto.getFormData());
                //执行监听器逻辑代码
                String executorName = publicService.getRedisProcessExecutorName(processUserDto.getProcessInsId());
                if (StringUtils.isNotBlank(executorName)) {
                    ProcessEventExecutor processEventExecutor = (ProcessEventExecutor) SpringContextUtils.getBean(executorName);
                    if (processEventExecutor != null) {
                        ProcessExecution processExecution = new ProcessExecution();
                        processExecution.setProcessInstanceId(processUserDto.getProcessInsId());
                        processExecution.setProcessStatus(ProcessStatusEnum.FINISH);
                        processEventExecutor.processFinishExecute(processExecution);
                    }
                }
                //更新为空监听器
                bpmRpcActivitiProcessManageService.setProcessEventExecutor(processUserDto.getProcessInsId(), EmptyProcessEvent.class.getSimpleName());
            } else {
                bean.applyCommit(projectPlanDetails, processUserDto.getProcessInsId(), projectTaskDto.getFormData());
            }
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (StringUtils.isNotBlank(processUserDto.getProcessInsId())) {
                bpmRpcActivitiProcessManageService.closeProcess(processUserDto.getProcessInsId());
            }
            throw new BusinessException(e.getMessage());
        }

        //不走流程时更新任务状态
        if (!projectPhase.getBisUseBox() && !projectTaskDto.getMustUseBox()) {
            boolean isReStart = projectPlanDetails.getBisRestart();
            projectPlanDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
            projectPlanDetails.setBisRestart(false);
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
            if (isReStart == Boolean.FALSE) {
                projectPlanService.enterNextStage(projectPlanDetails.getPlanId()); //结束当前阶段进入下一阶段
            }
        }
        bpmRpcProjectTaskService.deleteProjectTask(projectTaskDto.getResponsibilityId());
        projectMemberService.autoAddFinishTaskMember(projectPlanDetails);
        if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
            try {
                processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
            } catch (BpmException e) {
                throw new BusinessException("跳过节点自动提交失败");
            }
        }
    }

    public void submitTaskApproval(ApprovalModelDto approvalModelDto, String formData) throws BusinessException {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsItemByProcessInsId(approvalModelDto.getProcessInsId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(approvalModelDto.getProjectId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(approvalModelDto.getViewUrl());
        bean.approvalCommit(projectPlanDetails, approvalModelDto.getProcessInsId(), formData);
        if (projectWorkStage != null) {
            approvalModelDto.setWorkStageId(projectWorkStage.getId());
            approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
        }
        if (projectPhase != null) {
            approvalModelDto.setWorkPhaseId(projectPhase.getId());
            approvalModelDto.setWorkPhase(projectPhase.getProjectPhaseName());
        }
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            assessmentCommonService.createProjectTask(approvalModelDto, projectInfo, projectPlanDetails);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    public void submitPlanEdit(ApprovalModelDto approvalModelDto, String formData, String taskRemarks, String actualHours) throws BusinessException {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(approvalModelDto.getProcessInsId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
        approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
        approvalModelDto.setWorkStageId(projectPlanDetails.getProjectWorkStageId());
        approvalModelDto.setWorkPhaseId(0);
        approvalModelDto.setOpinions("返回修改");
        approvalModelDto.setActivityKey(ProcessActivityEnum.EDIT.getValue());
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setCurrentStep(-1);
        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(approvalModelDto.getViewUrl());
        bean.returnEditCommit(projectPlanDetails, approvalModelDto.getProcessInsId(), formData);
        if (StringUtils.isNotBlank(actualHours)) {
            projectPlanDetails.setActualHours(new BigDecimal(actualHours));
            projectPlanDetails.setTaskRemarks(taskRemarks);
        }
        projectPlanDetails.setSubmitUser(processControllerComponent.getThisUser());
        setSubmitUserAll(projectPlanDetails, approvalModelDto.getProcessInsId());
        projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
        projectMemberService.autoAddFinishTaskMember(projectPlanDetails);
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public BootstrapTableVo getDataLandLevelListVos(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectTaskReturnRecord projectTaskReturnRecord = new ProjectTaskReturnRecord();
        projectTaskReturnRecord.setPlanDetailsId(planDetailsId);
        List<ProjectTaskReturnRecord> projectTaskReturnRecordList = projectTaskReturnRecordDao.getProjectTaskReturnRecordList(projectTaskReturnRecord);
        List<ProjectTaskReturnRecordVo> vos = LangUtils.transform(projectTaskReturnRecordList, o -> getProjectTaskReturnRecordVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<ProjectTaskReturnRecordVo>() : vos);
        return vo;
    }

    public ProjectTaskReturnRecordVo getProjectTaskReturnRecordVo(ProjectTaskReturnRecord projectTaskReturnRecord) {
        if (projectTaskReturnRecord == null) {
            return null;
        }
        ProjectTaskReturnRecordVo vo = new ProjectTaskReturnRecordVo();
        BeanUtils.copyProperties(projectTaskReturnRecord, vo);
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(projectTaskReturnRecord.getId(), String.valueOf(projectTaskReturnRecord.getPlanDetailsId()), FormatUtils.entityNameConvertToTableName(ProjectTaskReturnRecord.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        SysUserDto sysUser = erpRpcUserService.getSysUser(projectTaskReturnRecord.getReturnPerson());
        if (sysUser != null)
            vo.setReturnPersonName(sysUser.getUserName());
        return vo;
    }

    /**
     * 设置该任务的所有提交人
     *
     * @param projectPlanDetails
     */
    private void setSubmitUserAll(ProjectPlanDetails projectPlanDetails, String processInsId) {
        if (projectPlanDetails == null) return;
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        String currUser = processControllerComponent.getThisUser();
        if (StringUtils.isBlank(projectPlanDetails.getSubmitUserAll())) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(currUser);
            keyValueDto.setValue(processInsId);
            keyValueDtos.add(keyValueDto);
        } else {
            String submitUserAll = projectPlanDetails.getSubmitUserAll();
            List<KeyValueDto> dtoList = JSON.parseArray(submitUserAll, KeyValueDto.class);
            if (CollectionUtils.isNotEmpty(dtoList)) {
                List<String> userList = LangUtils.transform(dtoList, o -> o.getKey());
                if (userList.contains(currUser)) {//只更新流程实例id
                    dtoList.forEach(o -> {
                        if (o.getKey().equals(currUser)) {
                            o.setValue(processInsId);
                        }
                    });
                    keyValueDtos.addAll(dtoList);
                } else {
                    KeyValueDto keyValueDto = new KeyValueDto();
                    keyValueDto.setKey(currUser);
                    keyValueDto.setValue(processInsId);
                    keyValueDtos.add(keyValueDto);
                }
            } else {
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(currUser);
                keyValueDto.setValue(processInsId);
                keyValueDtos.add(keyValueDto);
            }
        }
        projectPlanDetails.setSubmitUserAll(JSON.toJSONString(keyValueDtos));
    }
}
