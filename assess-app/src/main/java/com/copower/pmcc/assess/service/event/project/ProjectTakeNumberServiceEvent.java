package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.service.ProjectTakeNumberService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.SysSymbolListDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ProjectTakeNumberServiceEvent extends BaseProcessEvent {
    @Autowired
    private ProjectTakeNumberService projectTakeNumberService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        ProjectTakeNumber projectTakeNumber = projectTakeNumberService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        projectTakeNumber.setStatus(ProcessStatusEnum.FINISH.getValue());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectTakeNumber.getProjectId());
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfo.getProjectCategoryId());
        SysSymbolListDto symbolListDto = projectNumberRecordService.getReportNumber(projectInfo, 0, assessProjectType, projectTakeNumber.getReportType(), true);
        erpRpcToolsService.updateSymbolUsed(applicationConstant.getAppKey(), symbolListDto.getSymbol());
        projectTakeNumber.setNumberValue(symbolListDto.getSymbol());
        projectTakeNumber.setAssessProjectType(assessProjectType.getKey());
        projectTakeNumberService.editData(projectTakeNumber);
    }
}
