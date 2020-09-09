package com.copower.pmcc.assess.service.project.change;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.manage.ProjectPlanHistoryDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanHistory;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanHistoryDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectPlanHistoryEvent;
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
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/28
 * @time: 9:24
 */
@Service
public class ProjectPlanHistoryService {
    @Autowired
    private ProjectPlanHistoryDao projectPlanHistoryDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BaseParameterService baseParameterServcie;

    public List<ProjectPlanHistory> getProjectPlanHistoryList(String processInsId) {
        return projectPlanHistoryDao.getProjectPlanHistoryByProcessInsId(processInsId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveProjectPlanHistory(Integer projectId, String planString) throws BusinessException {

        List<ProjectPlanHistory> projectPlanHistories = projectPlanHistoryDao.getProjectPlanHistoryByProjectId(projectId);
if(CollectionUtils.isNotEmpty(projectPlanHistories))
{
    throw new BusinessException("已申请计划变更，请不要重复申请");
}
projectPlanHistories=new ArrayList<>();
        SysUserDto sysUser = processControllerComponent.getThisUserInfo();
        List<ProjectPlanHistoryDto> projectPlanHistoryDtos = JSON.parseArray(planString, ProjectPlanHistoryDto.class);
        List<ProjectPlan> projectPlans = projectPlanService.getProjectPlanListByProjectId(Integer.valueOf(projectId), "");
        projectPlans = LangUtils.filter(projectPlans, o -> {
            return o.getBisRestart().equals(false);
        });
        ProjectPlanHistory projectPlanHistory;
        if (CollectionUtils.isEmpty(projectPlanHistoryDtos)) {
            throw new BusinessException("没有修改的项目计划内容");
        }
        for (ProjectPlanHistoryDto dt : projectPlanHistoryDtos) {
            projectPlanHistory = new ProjectPlanHistory();
            projectPlanHistory.setProjectId(projectId);
            projectPlanHistory.setPlanId(dt.getPlanId());
            if (StringUtils.isNotBlank(dt.getPlanStart())) {
                projectPlanHistory.setAfterPlanStart(DateUtils.parse(dt.getPlanStart()));
            }
            if (StringUtils.isNotBlank(dt.getPlanEnd())) {
                projectPlanHistory.setAfterPlanEnd(DateUtils.parse(dt.getPlanEnd()));
            }
            projectPlanHistory.setAfterPlanRemarks(dt.getPlanRemarks());
            for (ProjectPlan item : projectPlans) {
                if (dt.getPlanId().equals(item.getId())) {
                    projectPlanHistory.setBeforePlanStart(item.getProjectPlanStart());
                    projectPlanHistory.setBeforePlanEnd(item.getProjectPlanEnd());
                    projectPlanHistory.setBeforePlanRemarks(item.getPlanRemarks());
                    projectPlanHistory.setProjectPhaseName(item.getPlanName());
                }
            }

            if (!projectPlanHistoryDao.addPlanHistory(projectPlanHistory)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }

            projectPlanHistories.add(projectPlanHistory);
        }
        String boxName = baseParameterServcie.getParameterValues(AssessCacheConstant.DETAILS_PROJECT_PLAN);
        String processInsId = "0";
        if (StringUtils.isNotBlank(boxName)) {
            //发起流程
            int boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
            String folio = String.format("%s发起【%s】流程，%s", sysUser.getUserName(), boxReDto.getCnName(), projectInfo.getProjectName());
            ProcessInfo processInfo = new ProcessInfo();
            ProcessUserDto processUserDto = new ProcessUserDto();
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanHistory.class));
            processInfo.setTableId(0);
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setProcessEventExecutorName(ProjectPlanHistoryEvent.class.getSimpleName());

            try {
                processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(),processInfo, "", false);
            } catch (BpmException e) {
                throw new BusinessException(e.getMessage());
            }
            for (ProjectPlanHistory item : projectPlanHistories) {
                item.setProcessInsId(processUserDto.getProcessInsId());
                item.setStatus(ProcessStatusEnum.RUN.getValue());
                if (!projectPlanHistoryDao.editPlanHistory(item)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            }

            if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
                try {
                    processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
                } catch (BpmException e) {
                    throw new BusinessException("跳过节点自动提交失败");
                }
            }
        } else {
            for (ProjectPlanHistory item : projectPlanHistories) {
                item.setProcessInsId(processInsId);
                item.setStatus(ProcessStatusEnum.FINISH.getValue());
                if (!projectPlanHistoryDao.editPlanHistory(item)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
                for (ProjectPlan plan : projectPlans) {
                    if (plan.getId().equals(item.getPlanId())) {
                        plan.setProjectPlanStart(item.getAfterPlanStart());
                        plan.setProjectPlanEnd(item.getAfterPlanEnd());
                        plan.setPlanRemarks(item.getAfterPlanRemarks());
                        projectPlanService.updateProjectPlan(plan);
                    }
                }
            }

        }
        //更新附件
        SysAttachmentDto sysAttachment = new SysAttachmentDto();
        sysAttachment.setProcessInsId("0");
        sysAttachment.setCreater(processControllerComponent.getThisUser());
        sysAttachment.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanHistory.class));
        SysAttachmentDto sysAttachmentNew = new SysAttachmentDto();
        sysAttachmentNew.setProcessInsId(processInsId);
        sysAttachmentNew.setTableId(0);
        sysAttachmentNew.setProjectId(projectId);
        baseAttachmentService.updateAttachementByExample(sysAttachment, sysAttachmentNew);

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveEditProjectPlanHistory(ApprovalModelDto approvalModelDto, String planString) throws BusinessException {

        List<ProjectPlanHistoryDto> projectPlanHistoryDtos = JSON.parseArray(planString, ProjectPlanHistoryDto.class);

        List<ProjectPlanHistory> projectPlanHistories = projectPlanHistoryDao.getProjectPlanHistoryByProcessInsId(approvalModelDto.getProcessInsId());
        for (ProjectPlanHistory item : projectPlanHistories) {
            for (ProjectPlanHistoryDto dt : projectPlanHistoryDtos) {
                if (dt.getPlanId().equals(item.getPlanId())) {
                    if (StringUtils.isNotBlank(dt.getPlanStart())) {
                        item.setAfterPlanStart(DateUtils.parse(dt.getPlanStart()));
                    }
                    if (StringUtils.isNotBlank(dt.getPlanEnd())) {
                        item.setAfterPlanEnd(DateUtils.parse(dt.getPlanEnd()));
                    }
                    item.setAfterPlanRemarks(dt.getPlanRemarks());
                    if (!projectPlanHistoryDao.editPlanHistory(item)) {
                        throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                    }
                }
            }
        }
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setOpinions("返回修改");

        approvalModelDto.setCurrentStep(-1);
        approvalModelDto.setBisNext("0");
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public void approvalSubmit(ApprovalModelDto approvalModelDto) throws BusinessException {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
