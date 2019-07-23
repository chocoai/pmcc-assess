package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
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
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
        switch (processStatusEnum) {
            case FINISH:
                ProjectPlan projectPlan = projectPlanService.getProjectplanByProcessInsId(processExecution.getProcessInstanceId());
                projectPlan.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
                projectPlan.setFinishDate(new Date());
                projectPlanService.updateProjectPlan(projectPlan);

                updateDocNumberToErp(projectPlan.getProjectId());
                break;
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
        Integer publicProjectId = projectInfo.getPublicProjectId();
        if (publicProjectId == null) return;
        SysProjectDto sysProjectDto = erpRpcProjectService.getProjectInfoByProjectId(publicProjectId, applicationConstant.getAppKey());
        if (sysProjectDto != null && sysProjectDto.getId() > 0) {
            sysProjectDto.setStatus(ProjectStatusEnum.FINISH.getKey());
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
            if (baseDataDic == null) return;
            List<String> reportNumberList = projectNumberRecordService.getReportNumberList(projectId, baseDataDic.getId());
            if (CollectionUtils.isEmpty(reportNumberList)) return;
            String s = StringUtils.join(reportNumberList, ',');
            sysProjectDto.setProjectDocumentNumber(s);
            erpRpcProjectService.saveProject(sysProjectDto);
        }
    }

}
