package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        try {
            ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
            if (processStatusEnum == null) return;
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processExecution.getProcessInstanceId());
            if (projectPlanDetails == null) return;
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
            SysProjectDto sysProjectDto = erpRpcProjectService.getProjectInfoByProjectId(projectPlanDetails.getProjectId(), BaseConstant.ASSESS_APP_KEY);
            List<ProjectNumberRecord> reportNumberList = null;
            if (sysProjectDto != null && sysProjectDto.getId() > 0) {
                sysProjectDto.setStatus(ProjectStatusEnum.FINISH.getKey());
                reportNumberList = projectNumberRecordService.getReportNumberRecordList(projectPlanDetails.getProjectId(), null, null);
            }
            switch (processStatusEnum) {
                case FINISH:
                    projectPlan.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
                    projectPlan.setFinishDate(new Date());
                    projectPlanService.updateProjectPlan(projectPlan);
                    if (CollectionUtils.isEmpty(reportNumberList)) return;
                    reportNumberList = LangUtils.filter(reportNumberList, o -> o.getAreaId() > 0);
                    List<String> transform = LangUtils.transform(reportNumberList, p -> p.getNumberValue());
                    String s = StringUtils.join(transform, ',');
                    sysProjectDto.setProjectDocumentNumber(s);
                    erpRpcProjectService.saveProject(sysProjectDto);
                    for (ProjectNumberRecord record : reportNumberList) {
                        erpRpcToolsService.bindSymbol(applicationConstant.getAppKey(), record.getNumberValue(), projectInfo.getPublicProjectId(), record.getId(), FormatUtils.entityNameConvertToTableName(ProjectNumberRecord.class));
                        erpRpcToolsService.updateSymbolUsed(applicationConstant.getAppKey(), record.getNumberValue());
                    }
                    break;
                case CLOSE:
                    break;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "生成报告Event");
        }
    }
}
