package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.ProjectTakeNumberService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectQrcodeRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportGroupService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.dto.SysSymbolListDto;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private ProjectQrcodeRecordService projectQrcodeRecordService;
    @Autowired
    private GenerateReportGroupService generateReportGroupService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void processFinishExecute(ProcessExecution processExecution) throws Exception {
        if (!processExecution.getProcessStatus().isFinish()) return;
        ProjectTakeNumber projectTakeNumber = projectTakeNumberService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        projectTakeNumber.setStatus(ProcessStatusEnum.FINISH.getValue());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectTakeNumber.getProjectId());
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfo.getProjectCategoryId());
        SysSymbolListDto symbolListDto = projectNumberRecordService.getReportNumber(projectInfo, 0, 0,
                assessProjectType, projectTakeNumber.getReportType(), true, projectTakeNumber.getErpRuleId());
        projectTakeNumber.setNumberValue(symbolListDto.getSymbol());
        projectTakeNumber.setAssessProjectType(assessProjectType.getKey());
        projectTakeNumberService.editData(projectTakeNumber);
        erpRpcToolsService.bindSymbol(applicationConstant.getAppKey(), projectTakeNumber.getNumberValue(), projectInfo.getPublicProjectId(), projectTakeNumber.getId(), FormatUtils.entityNameConvertToTableName(ProjectTakeNumber.class));

        //如果需要生成二维码则需将二维码生成并记录
        if (Boolean.TRUE.equals(projectTakeNumber.getBisQrcode())) {
            String unit = projectInfoService.getEntrustmentUnit(projectInfo.getId());//获取委托单位
            GenerateReportGroup generateReportGroup = generateReportGroupService.getGenerateReportGroupById(projectTakeNumber.getReportGroupId());
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(projectTakeNumber.getReportType());
            String qrcode = projectQrcodeRecordService.getReportQrcode(generateReportGroup, baseDataDic.getFieldName(), projectTakeNumber.getNumberValue(), unit);
            projectTakeNumber.setQrcode(qrcode);
            projectTakeNumberService.editData(projectTakeNumber);
        }

        //将生成的文号更新到erp项目中
        SysProjectDto projectDto = erpRpcProjectService.getProjectInfoById(projectInfo.getPublicProjectId());
        if (projectDto != null) {
            if (StringUtils.isBlank(projectDto.getProjectDocumentNumber())) {
                projectDto.setProjectDocumentNumber(projectTakeNumber.getNumberValue());
            } else {
                projectDto.setProjectDocumentNumber(projectDto.getProjectDocumentNumber() + "," + projectTakeNumber.getNumberValue());
            }
            erpRpcProjectService.saveProject(projectDto);
        }
    }
}
