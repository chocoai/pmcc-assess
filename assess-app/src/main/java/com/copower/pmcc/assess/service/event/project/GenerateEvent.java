package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
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
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        try {
            ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
            if (processStatusEnum == null) return;
            switch (processStatusEnum) {
                case FINISH:
                    ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processExecution.getProcessInstanceId());
                    if (projectPlan == null) return;
                    projectPlan.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
                    projectPlan.setFinishDate(new Date());
                    projectPlanService.updateProjectPlan(projectPlan);
                    updateDocNumberToErp(projectPlan.getProjectId());
                    break;
            }
        }catch (Exception e){
            baseService.writeExceptionInfo(e,"生成报告Event");
        }
    }

    /**
     * 更新結果报告的报告文号到erp项目中
     *
     * @param projectId
     */
    public void updateDocNumberToErp(Integer projectId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        if (projectInfo == null) return;
        SysProjectDto sysProjectDto = erpRpcProjectService.getProjectInfoByProjectId(projectId, BaseConstant.ASSESS_APP_KEY);
        if (sysProjectDto != null && sysProjectDto.getId() > 0) {
            sysProjectDto.setStatus(ProjectStatusEnum.FINISH.getKey());
            List<String> reportNumberList = projectNumberRecordService.getReportNumberList(projectId, null);
            if (CollectionUtils.isEmpty(reportNumberList)) return;
            String s = StringUtils.join(reportNumberList, ',');
            sysProjectDto.setProjectDocumentNumber(s);
            erpRpcProjectService.saveProject(sysProjectDto);
        }
    }

}
