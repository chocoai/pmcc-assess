package com.copower.pmcc.assess.service.project.change;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectSuspendDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSuspend;
import com.copower.pmcc.assess.dto.output.project.ProjectSuspendVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectPauseEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:20
 */
@Service
public class ProjectPauseService {

    @Autowired
    private ProjectSuspendDao projectSuspendDao;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BaseParameterService baseParameterServcie;
    @Autowired
    private ApplicationConstant applicationConstant;

    @Transactional(rollbackFor = Exception.class)
    public void saveProjectSuspend(Integer projectId, String suspendReason) throws BusinessException {

        ProjectSuspend projectSuspend = projectSuspendDao.getProjectSuspend(projectId, ProcessStatusEnum.RUN.getValue());
        if (projectSuspend != null) {
            throw new BusinessException("项目已申请暂停，请不要重复申请");
        }

        ProjectInfo projectInfo = projectInfoDao.getProjectInfoById(projectId);
        projectInfo.setProjectStatus(ProjectStatusEnum.PAUSE_APPLY.getKey());
        if (!projectInfoDao.updateProjectInfo(projectInfo)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
        //记录暂停记录
        projectSuspend = new ProjectSuspend();
        projectSuspend.setProjectId(projectId);
        projectSuspend.setSuspendReason(suspendReason);
        projectSuspend.setCreator(processControllerComponent.getThisUser());
        projectSuspend.setSuspendUserAccount(processControllerComponent.getThisUser());
        projectSuspendDao.addSuspend(projectSuspend);
        bpmRpcProjectTaskService.updateProjectTaskPause(applicationConstant.getAppKey(),projectId);
        String boxName = baseParameterServcie.getParameterValues(AssessCacheConstant.DETAILS_PROJECT_SUSPEND);
        String processInsId = "0";
        if (StringUtils.isNotBlank(boxName)) {
            //发起流程
            int boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
            BoxReDto boxReInfoByBoxId = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
            SysUserDto sysUser = processControllerComponent.getThisUserInfo();
            String folio = String.format("%s发起【%s】,%s", sysUser.getUserName(), boxReInfoByBoxId.getCnName(), projectInfo.getProjectName());
            ProcessUserDto processUserDto = new ProcessUserDto();
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setProcessName(boxReInfoByBoxId.getProcessName());
            processInfo.setGroupName(boxReInfoByBoxId.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectSuspend.class));
            processInfo.setTableId(projectSuspend.getId());
            processInfo.setBoxId(boxReInfoByBoxId.getId());
            processInfo.setProjectId(projectId);
            processInfo.setProcessEventExecutorName(ProjectPauseEvent.class.getSimpleName());

            try {
                processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(),processInfo, "", false);
            } catch (BpmException e) {
                throw new BusinessException(e.getMessage());
            }
            projectSuspend.setProcessInsId(processUserDto.getProcessInsId());
            projectSuspend.setStatus(ProcessStatusEnum.RUN.getValue());
            if (!projectSuspendDao.editSuspend(projectSuspend)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            processInsId = processUserDto.getProcessInsId();

            if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
                try {
                    processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
                } catch (BpmException e) {
                    throw new BusinessException("跳过节点自动提交失败");
                }
            }
        } else {
            //如果不走流程，则直接更新状态
            projectSuspend.setSuspendUserAccount(processControllerComponent.getThisUser());
            projectSuspend.setSupendDate(new Date());
            projectSuspendDao.editSuspend(projectSuspend);
            projectInfo.setProjectStatus(ProjectStatusEnum.PAUSE.getKey());
            projectInfoDao.updateProjectInfo(projectInfo);

        }
        //更新附件
        SysAttachmentDto sysAttachment = new SysAttachmentDto();
        sysAttachment.setProcessInsId("0");
        sysAttachment.setCreater(processControllerComponent.getThisUser());
        sysAttachment.setTableName(FormatUtils.entityNameConvertToTableName(ProjectSuspend.class));
        SysAttachmentDto sysAttachmentNew = new SysAttachmentDto();
        sysAttachmentNew.setProcessInsId(processInsId);
        sysAttachmentNew.setTableId(projectSuspend.getId());
        sysAttachmentNew.setProjectId(projectId);
        baseAttachmentService.updateAttachementByExample(sysAttachment, sysAttachmentNew);
    }

    public void saveEditProjectSuspend(ApprovalModelDto approvalModelDto, String suspendReason) throws BusinessException {

        ProjectSuspend projectSuspend = projectSuspendDao.getProjectSuspendProcessInsId(approvalModelDto.getProcessInsId());
        projectSuspend.setSuspendReason(suspendReason);
        if (!projectSuspendDao.editSuspend(projectSuspend)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setOpinions("返回修改");
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void submitProjectSuspend(ApprovalModelDto approvalModelDto) throws BusinessException {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void startProject(Integer projectId) throws BusinessException {
        ProjectSuspend projectSuspend = projectSuspendDao.getProjectSuspend(projectId, ProcessStatusEnum.FINISH.getValue());
        projectSuspend.setBisEnable(false);
        projectSuspend.setRestartDate(new Date());
        projectSuspend.setRestartUserAccount(processControllerComponent.getThisUser());
        if (!projectSuspendDao.editSuspend(projectSuspend)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }

        ProjectInfo projectInfo = projectInfoDao.getProjectInfoById(projectId);
        projectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());
        if (!projectInfoDao.updateProjectInfo(projectInfo)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }

        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), ProjectStatusEnum.PAUSE.getName());
        if (CollectionUtils.isNotEmpty(projectPlans)) {
            for (ProjectPlan item : projectPlans) {
                item.setProjectStatus(ProjectStatusEnum.PLAN.getKey());
                projectPlanDao.updateProjectPlan(item);
            }
        }

        bpmRpcProjectTaskService.updateProjectTaskRestart(applicationConstant.getAppKey(),projectId);
    }

    public BootstrapTableVo getProjectSuspendHistory(Integer projectId) {

        List<ProjectSuspend> projectSuspends = projectSuspendDao.getProjectSuspendHistory(projectId);
        if (CollectionUtils.isEmpty(projectSuspends)) {
            return new BootstrapTableVo();
        }
        List<Integer> integers = LangUtils.transform(projectSuspends, projectSuspend -> projectSuspend.getId());
        List<SysAttachmentDto> sysAttachments = baseAttachmentService.getAttachmentListByTableName(FormatUtils.entityNameConvertToTableName(ProjectSuspend.class), integers);
        List<ProjectSuspendVo> projectSuspendVos = LangUtils.transform(projectSuspends, projectSuspend -> {

            ProjectSuspendVo projectSuspendVo = new ProjectSuspendVo();
            BeanUtils.copyProperties(projectSuspend, projectSuspendVo);
            SysUserDto sysUser = erpRpcUserService.getSysUser(projectSuspend.getCreator());
            projectSuspendVo.setSuspendUserName(sysUser.getUserName());
            projectSuspendVo.setStatus(ProcessStatusEnum.create(projectSuspend.getStatus()).getName());
            List<KeyValueDto> keyValueDtos = new ArrayList<>();
            List<SysAttachmentDto> filter = LangUtils.filter(sysAttachments, input -> {
                if (input.getTableId().equals(projectSuspend.getId())) {
                    return true;
                } else {
                    return false;
                }
            });
            if (CollectionUtils.isNotEmpty(filter)) {
                for (SysAttachmentDto item : filter) {
                    KeyValueDto keyValueDto = new KeyValueDto();
                    keyValueDto.setKey(String.valueOf(item.getId()));
                    keyValueDto.setExplain(item.getFileExtension());
                    keyValueDto.setValue(String.format("%s.%s(%s)", item.getFileName(), item.getFileExtension(), item.getFileSize()));
                    keyValueDtos.add(keyValueDto);
                }
            }
            projectSuspendVo.setFiles(keyValueDtos);
            return projectSuspendVo;
        });
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setRows(projectSuspendVos);
        return bootstrapTableVo;
    }

    public ProjectSuspend getProjectSuspendByProcessInsId(String processInsId) {
        return projectSuspendDao.getProjectSuspendProcessInsId(processInsId);
    }

    public ProjectSuspend getProjectSuspendById(Integer id) {
        return projectSuspendDao.getProjectSuspendById(id);
    }

    /**
     * 暂停任务后，将不能再发起当前项目相关的流程信息，所以将流程的所有信息设置为暂停状态
     *
     * @param projectId
     */
    public void pauseProject(Integer projectId) {
        /**
         * 1、暂停计划
         * 2、暂停工作内容事项
         */

        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectId), ProjectStatusEnum.PLAN.getName());
        if (CollectionUtils.isNotEmpty(projectPlans)) {
            for (ProjectPlan item : projectPlans) {
                item.setProjectStatus(ProjectStatusEnum.PAUSE.getKey());
                projectPlanDao.updateProjectPlan(item);
            }
        }
        //工作事项

        bpmRpcProjectTaskService.updateProjectTaskPause(applicationConstant.getAppKey(),projectId);

    }

}
