package com.copower.pmcc.assess.service.project.change;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.manage.ProjectWorkStageRestartDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectWorkStageStartEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/10/31
 * @time: 14:00
 */
@Service
public class ProjectWorkStageRestartService {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectWorkStageRestartDao projectWorkStageRestartDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BaseParameterService baseParameterServcie;

    @Transactional(rollbackFor = Exception.class)
    public void editWorkStageRestart(ApprovalModelDto approvalModelDto, ProjectWorkStageRestart projectWorkStageRestart) throws BusinessException {

        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectWorkStageRestart.getProjectRestartStageId());
        projectWorkStageRestart.setProjectRestartStageName(projectWorkStage.getWorkStageName());
        //保存申请数据
        if (!projectWorkStageRestartDao.editProjectWorkStageRestart(projectWorkStageRestart)) {
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
    public void applyWorkStageRestart(ProjectWorkStageRestart projectWorkStageRestart) throws BusinessException {

        ProjectWorkStageRestart sysStart = projectWorkStageRestartDao.getProjectWorkStageRestartByProjectId(projectWorkStageRestart.getProjectId());
        if (sysStart != null) {
            throw new BusinessException(String.format("已申请重启【%】，请不要重复申请", sysStart.getProjectRestartStageName()));
        }

        /**
         * 处理步骤
         * 1、保存申请的数据信息
         * 2、复制要重启的阶段的项目计划信息，不包括计划明细
         * 3、判断是否需要进行流程审批，如果要审批，则走相应的审批流程，反之直接发起相应的阶段计划编制流程
         */
        //保存申请数据
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectWorkStageRestart.getProjectPlanOldId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectWorkStageRestart.getProjectId());
        String workStage = projectPlanService.getProjectCurrStage(projectWorkStageRestart.getProjectId());
        projectWorkStageRestart.setProjectThisWorkStage(workStage);
        projectWorkStageRestart.setProjectRestartStageId(projectPlan.getWorkStageId());
        projectWorkStageRestart.setProcessInsId("0");
        projectWorkStageRestart.setCreator(processControllerComponent.getThisUser());
        projectWorkStageRestart.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectWorkStageRestart.setProjectRestartStageName(projectWorkStage.getWorkStageName());
        if (!projectWorkStageRestartDao.addProjectWorkStageRestart(projectWorkStageRestart)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
        String boxName = baseParameterServcie.getParameterValues(AssessCacheConstant.DETAILS_PROJECT_RESTART);

        if (StringUtils.isNotBlank(boxName)) {
            //发起流程
            Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
            //发起审批流程

            String folio = String.format("阶段重启审批【%s】%s", projectWorkStage.getWorkStageName(), projectInfo.getProjectName());

            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
            ProcessInfo processInfo = new ProcessInfo();
            ProcessUserDto processUserDto = new ProcessUserDto();
            processInfo.setProjectId(projectWorkStageRestart.getProjectId());
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectWorkStageRestart.class));
            processInfo.setTableId(projectWorkStageRestart.getId());
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setProcessEventExecutorName(ProjectWorkStageStartEvent.class.getSimpleName());
            processInfo.setWorkStageId(projectWorkStage.getId());
            //设置其它参数

            try {
                processUserDto = processControllerComponent.processStart(processInfo, "", false);
                projectWorkStageRestart.setProcessInsId(processUserDto.getProcessInsId());
                projectWorkStageRestart.setStatus(ProcessStatusEnum.RUN.getValue());
                if (!projectWorkStageRestartDao.editProjectWorkStageRestart(projectWorkStageRestart)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } catch (BpmException e) {
                throw new BusinessException(e.getMessage());
            }
            if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
                try {
                    processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
                } catch (BpmException e) {
                    throw new BusinessException("跳过节点自动提交失败");
                }
            }
        } else {
            projectPlan = projectPlanService.RestartPlan(projectWorkStageRestart);
            projectWorkStageRestart.setProjectPlanId(projectPlan.getId());
            projectWorkStageRestart.setProjectRestartStageName(projectWorkStage.getWorkStageName());
            projectWorkStageRestartDao.editProjectWorkStageRestart(projectWorkStageRestart);
        }
    }

    public void approvalWorkStageRestart(ApprovalModelDto approvalModelDto) throws BusinessException {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public ProjectWorkStageRestart getProjectWorkStageRestartItem(String processInsId) {
        return projectWorkStageRestartDao.getProjectWorkStageRestartItem(processInsId);
    }
}
