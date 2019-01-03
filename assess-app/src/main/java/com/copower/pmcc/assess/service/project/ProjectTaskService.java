package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.input.project.ProjectTaskDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.event.project.ProjectTaskEvent;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:45
 */
@Service
public class ProjectTaskService {
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

    @Transactional(rollbackFor = Exception.class)
    public void submitTask(ProjectTaskDto projectTaskDto) throws Exception {

        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectTaskDto.getProjectDetailsId());
        projectPlanDetails.setTaskSubmitTime(new Date());
        projectPlanDetails.setBisStart(true);
        projectPlanDetails.setActualHours(projectTaskDto.getActualHours());
        projectPlanDetails.setTaskRemarks(projectTaskDto.getTaskRemarks());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        String boxName = projectPhase.getBoxName();
        if (projectPhase.getBisUseBox() || projectTaskDto.getMustUseBox()) {
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
                processUserDto = processControllerComponent.processStart(processInfo, projectTaskDto.getNextApproval(), false);

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
            bean.applyCommit(projectPlanDetails, processUserDto.getProcessInsId(), projectTaskDto.getFormData());
        } catch (Exception e) {
            if (StringUtils.isNotBlank(processUserDto.getProcessInsId())) {
                bpmRpcActivitiProcessManageService.closeProcess(processUserDto.getProcessInsId());
            }
            throw new BusinessException(e.getMessage());
        }

        //不走流程时更新任务状态
        if (!projectPhase.getBisUseBox() && !projectTaskDto.getMustUseBox()) {
            projectPlanDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);

            ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
            projectPlanDetailsWhere.setPlanId(projectPlanDetails.getPlanId());
            projectPlanDetailsWhere.setStatus(ProcessStatusEnum.RUN.getValue());
            projectPlanDetailsWhere.setBisLastLayer(true);
            List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
            if (CollectionUtils.isEmpty(projectPlanDetailsList)) {
                projectPlanService.updatePlanStatus(projectPlanDetails.getPlanId()); //结束当前阶段进入下一阶段
            }
        }
        bpmRpcProjectTaskService.deleteProjectTask(projectTaskDto.getResponsibilityId());

        //更新当前数据为最新
        if (projectPlanDetails.getReturnDetailsId() > 0) {
            ProjectPlanDetails projectPlanDetailsById = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getReturnDetailsId());
            projectPlanDetailsById.setBisNew(false);
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetailsById);
        }

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
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());

        ProjectTaskInterface bean = (ProjectTaskInterface) SpringContextUtils.getBean(approvalModelDto.getViewUrl());
        bean.approvalCommit(projectPlanDetails, approvalModelDto.getProcessInsId(), formData);

        approvalModelDto.setWorkStageId(projectWorkStage.getId());
        approvalModelDto.setWorkPhaseId(projectPlanDetails.getProjectPhaseId());
        approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
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
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
        }

        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
