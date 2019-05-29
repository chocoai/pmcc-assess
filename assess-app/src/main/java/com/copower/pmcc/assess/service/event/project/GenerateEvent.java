package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGeneration;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportGenerationService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/2/6
 * @time: 10:53
 */
@Component
public class GenerateEvent extends BaseProcessEvent {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private GenerateReportGenerationService generateReportGenerationService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        GenerateReportGeneration generateReportGeneration = generateReportGenerationService.getGenerateReportGenerationByProcessInsId(processExecution.getProcessInstanceId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(generateReportGeneration.getProjectId());
        //结果报告
        createQuickMark(projectInfo, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        //技术报告
        createQuickMark(projectInfo, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
        //预评报告
        createQuickMark(projectInfo, generateReportGeneration, AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);

    }

    public void createQuickMark(ProjectInfo projectInfo, GenerateReportGeneration generateReportGeneration, String fieldName) throws Exception {
        //报告类型
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(fieldName);
        //报告文号
        String resultReportNumber = projectNumberRecordService.getReportNumber(projectInfo.getId(), generateReportGeneration.getAreaGroupId(), resultReport.getId());
        //报告附件id
        Integer attachmentId = 0;
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(generateReportGeneration.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGeneration.class));
        sysAttachmentDto.setFileName(resultReport.getName());
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            attachmentId = attachmentList.get(0).getId();
        }
        projectInfoService.finishProjectDocument(projectInfo, generateReportGeneration.getReportIssuanceDate(), resultReportNumber, attachmentId);
    }
}
