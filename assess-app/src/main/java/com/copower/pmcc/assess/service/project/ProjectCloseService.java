package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectCloseDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanTaskAllDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.base.BaseParameterServcie;
import com.copower.pmcc.assess.service.event.project.ProjectCloseEvent;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.enums.SysProjectEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/25
 * @time: 14:48
 */
@Service
public class ProjectCloseService {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProjectCloseDao projectCloseDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private ProjectPauseService projectPauseService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanTaskAllDao projectPlanTaskAllDao;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BaseParameterServcie baseParameterServcie;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ApplicationConstant applicationConstant;

    public ProjectClose getProjectClose(String processInsId) {
        return projectCloseDao.getProjectClose(processInsId);
    }

    public List<ProjectClose> getProjectCloseList(ProjectClose projectClose) {
        return projectCloseDao.getProjectCloseList(projectClose);
    }

    public void saveProjectClose(Integer projectId, String reason) throws BusinessException {
        ProjectClose projectClose = new ProjectClose();
        projectClose.setProjectId(projectId);
        projectClose.setStatus(ProcessStatusEnum.RUN.getValue());
        List<ProjectClose> projectCloseList = getProjectCloseList(projectClose);
        if (CollectionUtils.isNotEmpty(projectCloseList)) {
            throw new BusinessException("已发起终止项目流程，正在审批中，请不要重复发起");
        }
        String boxName = baseParameterServcie.getParameterValues(AssessCacheConstant.DETAILS_PROJECT_STOP);
        String processInsId = "0";
        ProjectInfo projectInfo = projectInfoDao.getProjectInfoById(projectId);
        if (StringUtils.isNotBlank(boxName)) {
            Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
            SysUserDto sysUser = processControllerComponent.getThisUserInfo();

            projectClose = new ProjectClose();
            projectClose.setProjectId(projectId);
            projectClose.setReason(reason);
            projectClose.setCreator(processControllerComponent.getThisUser());
            projectClose.setProjectName(projectInfo.getProjectName());
            projectClose.setActivityName(projectPlanService.getProjectCurrStage(projectId));

            if (!projectCloseDao.addProjectClose(projectClose)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            //发起流程
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
            ProcessUserDto processUserDto = new ProcessUserDto();
            String folio = String.format("%s发起【项目终止】,%s", sysUser.getUserName(), projectInfo.getProjectName());
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName("tb_project_close");
            processInfo.setTableId(projectClose.getId());
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setProjectId(projectId);
            processInfo.setProcessEventExecutorName(ProjectCloseEvent.class.getSimpleName());

            try {
                processUserDto = processControllerComponent.processStart(processInfo, "", false);
            } catch (BpmException e) {
                throw new BusinessException(e.getMessage());
            }
            projectClose.setProcessInsId(processUserDto.getProcessInsId());
            projectClose.setStatus(ProcessStatusEnum.RUN.getValue());
            if (!projectCloseDao.editProjectClose(projectClose)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            projectPauseService.pauseProject(projectId);//发起申请时就暂停相应的任务
            if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
                try {
                    processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
                } catch (BpmException e) {
                    throw new BusinessException("跳过节点自动提交失败");
                }
            }
            processInsId = processUserDto.getProcessInsId();
        } else {
            List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanByStatus(Lists.newArrayList(projectInfo.getId()), ProjectStatusEnum.PAUSE.getName());
            CloseProjectAndTask(projectInfo, projectPlans);
        }
        //更新附件
        BaseAttachment sysAttachment = new BaseAttachment();
        sysAttachment.setProcessInsId("0");
        sysAttachment.setCreater(processControllerComponent.getThisUser());
        sysAttachment.setTableName("tb_project_close");
        BaseAttachment sysAttachmentNew = new BaseAttachment();
        sysAttachmentNew.setProcessInsId(processInsId);
        sysAttachmentNew.setTableId(projectClose.getId());
        sysAttachmentNew.setProjectId(projectId);
        baseAttachmentDao.updateAttachementByExample(sysAttachment, sysAttachmentNew);
    }

    public void CloseProjectAndTask(ProjectInfo projectInfo, List<ProjectPlan> projectPlans) {
        //关闭工作计划
        if (CollectionUtils.isNotEmpty(projectPlans)) {
            for (ProjectPlan item : projectPlans) {
                item.setProjectStatus(ProjectStatusEnum.CLOSE.getName());
                projectPlanDao.updateProjectPlan(item);
            }
        }
        //关闭工作成果
        bpmRpcProjectTaskService.deleteProjectTaskByProjectid(projectInfo.getId());
        //关闭项目

        //关闭所有与项目相关的审批流程
        if (!projectInfo.getProcessInsId().equals("0")) {
            if (!bpmRpcActivitiProcessManageService.isEnded(projectInfo.getProcessInsId())) {
                bpmRpcActivitiProcessManageService.closeProcess(projectInfo.getProcessInsId());
            }
        } else {
            projectInfo.setProjectStatus(ProjectStatusEnum.CLOSE.getName());
            projectInfo.setStatus(ProcessStatusEnum.CLOSE.getValue());
            projectInfoService.updateProjectInfo(projectInfo);
        }
        //关闭审批中的工作成果
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByProjectid(projectInfo.getId());
        if(CollectionUtils.isNotEmpty(projectPlanDetailsVos)){
            List<ProjectPlanDetailsVo> filter = LangUtils.filter(projectPlanDetailsVos, o -> {
                return ProcessStatusEnum.RUN.getValue().equals(o.getStatus());
            });
            if (CollectionUtils.isNotEmpty(filter)) {
                for (ProjectPlanDetailsVo item : filter) {
                    if (!bpmRpcActivitiProcessManageService.isEnded(item.getProcessInsId())) {
                        bpmRpcActivitiProcessManageService.closeProcess(item.getProcessInsId());
                    }

                }
            }
        }
        //关闭正在审批的整体提交审批的工作成果
        List<ProjectPlanTaskAll> projectPlanTaskAlls = projectPlanTaskAllDao.getObjectByProjectid(projectInfo.getId());
        if(CollectionUtils.isNotEmpty(projectPlanTaskAlls)){
            List<ProjectPlanTaskAll> filter1 = LangUtils.filter(projectPlanTaskAlls, o -> {
                return ProcessStatusEnum.RUN.getValue().equals(o.getStatus());
            });

            if (CollectionUtils.isNotEmpty(filter1)) {
                for (ProjectPlanTaskAll item : filter1) {
                    if (!bpmRpcActivitiProcessManageService.isEnded(item.getProcessInsId())) {
                        bpmRpcActivitiProcessManageService.closeProcess(item.getProcessInsId());
                    }

                }
            }
        }

        SysProjectDto sysProjectDto = erpRpcProjectService.getProjectInfoByProjectId(projectInfo.getId(), applicationConstant.getAppKey());
        if (sysProjectDto.getId() > 0) {
            sysProjectDto.setStatus(SysProjectEnum.CLOSE.getValue());
            erpRpcProjectService.saveProject(sysProjectDto);
        }
    }

    public void saveEditProjectClose(String reason, Integer boxId, String processInsId, String taskId, String activityName, String activityCnName) throws BusinessException {

        ProjectClose projectClose = getProjectClose(processInsId);
        projectClose.setReason(reason);
        if (!projectCloseDao.editProjectClose(projectClose)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
        List<BoxReActivityDto> boxReActivityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        ApprovalModelDto approvalModelDto = new ApprovalModelDto();//设置提交审批所需要的参数信息
        approvalModelDto.setBoxId(boxId);
        approvalModelDto.setProcessInsId(processInsId);
        approvalModelDto.setTaskId(taskId);
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setOpinions("返回修改");

        try {
            List<String> roleUserByActivityId = bpmRpcBoxService.getRoleUserByActivityId(boxReActivityDtos.get(0).getActivityId());
            approvalModelDto.setNextApproval(roleUserByActivityId);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }

        approvalModelDto.setCurrentStep(-1);
        approvalModelDto.setBisNext("0");
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void approvalSubmit(ApprovalModelDto approvalModelDto) throws BusinessException {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }

    }

}
