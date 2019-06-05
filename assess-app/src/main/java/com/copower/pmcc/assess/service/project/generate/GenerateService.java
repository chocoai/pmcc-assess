package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.event.project.GenerateEvent;
import com.copower.pmcc.assess.service.event.project.ProjectPlanApprovalEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private GenerateReportInfoService generateReportGenerationService;


    /**
     * 报告生成申请提交
     *
     * @param planId
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitApply(Integer planId,Integer areaGroupId) throws Exception {
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
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
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        processInfo.setTableId(projectInfo.getId());
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessEventExecutorName(ProjectPlanApprovalEvent.class.getSimpleName());
        processInfo.setWorkStage(projectWorkStage.getWorkStageName());
        processInfo.setWorkStageId(projectWorkStage.getId());
        processInfo.setAppKey(applicationConstant.getAppKey());
        processInfo.setProcessEventExecutor(GenerateEvent.class);

        try {
            processUserDto = processControllerComponent.processStart(processInfo, projectInfo.getCreator(), false);
            GenerateReportInfo generateReportGeneration = generateReportGenerationService.getGenerateReportInfoByAreaGroupId(areaGroupId,planId);
            generateReportGeneration.setProcessInsId(processUserDto.getProcessInsId());
            generateReportGeneration.setStatus(ProjectStatusEnum.RUNING.getKey());
            generateReportGenerationService.updateGenerateReportInfo(generateReportGeneration);
        } catch (BpmException e) {
            log.info(e.getMessage());
            throw new BusinessException(e.getMessage());
        }

        //更新流程实例id
        projectPlan.setProcessInsId(processUserDto.getProcessInsId());
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
}
