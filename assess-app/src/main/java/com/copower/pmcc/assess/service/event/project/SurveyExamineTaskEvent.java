package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/6
 * @time: 10:53
 */
@Component
public class SurveyExamineTaskEvent extends ProjectTaskEvent {
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        //更新案例任务状态
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processExecution.getProcessInstanceId());
        //更新各项表单任务状态
        surveyCommonService.updateExamineTaskStatus(projectPlanDetails.getPid(), commonService.thisUserAccount(), ProjectStatusEnum.FINISH);

        ProjectPlanDetails parentPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        if (parentPlanDetails != null && parentPlanDetails.getProjectPhaseId() == null) {
            parentPlanDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
            projectPlanDetailsService.updateProjectPlanDetails(parentPlanDetails); //更新父级案例信息状态为完成
        }
        super.processFinishExecute(processExecution);
    }
}
