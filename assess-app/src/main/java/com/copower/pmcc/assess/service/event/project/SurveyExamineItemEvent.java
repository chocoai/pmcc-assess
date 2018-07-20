package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineItem;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineItemService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
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
public class SurveyExamineItemEvent extends BaseProcessEvent {
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyExamineItemService surveyExamineItemService;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private CommonService commonService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        SurveyExamineItem surveyExamineItem = surveyExamineItemService.getExamineItemByProcessInsId(processExecution.getProcessInstanceId());
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(surveyExamineItem.getPlanDetailsId());
        surveyCommonService.updateExamineTaskStatus(projectPlanDetails.getId(), commonService.thisUserAccount(), ProjectStatusEnum.FINISH);
        if (surveyCommonService.isAllTaskFinish(projectPlanDetails.getId())) {
            projectPlanDetails.setStatus(ProjectStatusEnum.FINISH.getKey());
            projectPlanDetailsService.updateProjectPlanDetails(projectPlanDetails);
            if (projectPlanDetailsService.isAllPlanDetailsFinish(projectPlanDetails.getPlanId())) {
                projectTaskAllService.startTaskAllApproval(projectPlanDetails.getPlanId());
            }
        }
    }
}
