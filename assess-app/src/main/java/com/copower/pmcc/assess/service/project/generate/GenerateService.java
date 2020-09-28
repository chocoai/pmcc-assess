package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.event.project.GenerateEvent;
import com.copower.pmcc.assess.service.event.project.ProjectPlanApprovalEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kings on 2019-2-12.
 */
@Service
public class GenerateService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;


    /**
     * 报告生成申请提交
     *
     * @param planId
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitApply(Integer planId) throws Exception {
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
        //判断是否已发起流程
        if (ProjectStatusEnum.RUNING.getKey().equals(projectPlan.getStatus()))
            throw new BusinessException("审核盖章申请已发起，请不要重复操作");
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        ProcessUserDto processUserDto = null;
        //发起相应的流程
        String folio = "【报告生成】" + projectInfo.getProjectName();
        Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectWorkStage.getBoxName());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setStartUser(commonService.thisUserAccount());
        processInfo.setProjectId(projectInfo.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlan.class));
        processInfo.setTableId(planId);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessEventExecutorName(ProjectPlanApprovalEvent.class.getSimpleName());
        processInfo.setWorkStage(projectWorkStage.getWorkStageName());
        processInfo.setWorkStageId(projectWorkStage.getId());
        processInfo.setAppKey(applicationConstant.getAppKey());
        processInfo.setProcessEventExecutor(GenerateEvent.class);
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, projectInfo.getCreator(), false);
        } catch (BpmException e) {
            log.info(e.getMessage());
            throw new BusinessException(e.getMessage());
        }

        //更新流程实例id
        projectPlan.setProcessInsId(processUserDto.getProcessInsId());
        projectPlan.setStatus(ProjectStatusEnum.RUNING.getKey());
        projectPlanService.updateProjectPlan(projectPlan);
    }

    /**
     * 报告生成审批
     *
     * @param approvalModelDto
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitApproval(ApprovalModelDto approvalModelDto) throws BpmException {
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    /**
     * 报告生成返回修改
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitEditApproval(ApprovalModelDto approvalModelDto) throws Exception {
        processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
    }


    /**
     * 重新拿号
     *
     * @param projectId
     * @param areaId
     * @param reportType
     */
    @Transactional(rollbackFor = Exception.class)
    public void reGetDocumentNumber(Integer projectId, Integer areaId,Integer groupId, Integer reportType) {
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfoService.getProjectInfoById(projectId).getProjectCategoryId());
        ProjectNumberRecord numberRecord = projectNumberRecordService.getProjectNumberRecord(projectId, areaId,groupId, assessProjectType, reportType);
        if (numberRecord != null) {
            numberRecord.setBisDelete(true);
            projectNumberRecordService.updateProjectNumberRecord(numberRecord);
        }
    }

    /**
     * 获取项目生成报告中文号记录数据
     *
     * @param projectId
     * @return
     */
    public List<ProjectNumberRecord> getProjectNumberRecords(Integer projectId) {
        List<ProjectNumberRecord> numberRecordList = projectNumberRecordService.getReportNumberRecordList(projectId, null, null);
        if (CollectionUtils.isEmpty(numberRecordList)) return null;
        numberRecordList = LangUtils.filter(numberRecordList, o -> o.getAreaId() > 0);
        return numberRecordList;
    }

    /**
     * 更新为已使用状态
     */
    public void updateSymbolUsed(Integer projectId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        List<ProjectNumberRecord> projectNumberRecords = getProjectNumberRecords(projectId);
        if (CollectionUtils.isNotEmpty(projectNumberRecords)) {
            projectNumberRecords.forEach(o -> {
                erpRpcToolsService.bindSymbol(applicationConstant.getAppKey(), o.getNumberValue(), projectInfo.getPublicProjectId(), o.getId(), FormatUtils.entityNameConvertToTableName(ProjectNumberRecord.class));
                erpRpcToolsService.updateSymbolUsed(applicationConstant.getAppKey(), o.getNumberValue());
            });
        }
    }
}
