package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private CommonService commonService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        //更新案例任务状态
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processExecution.getProcessInstanceId());
        ProjectPlanDetails planDetailsParent = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        List<ProjectPlanDetails> detailsList = projectPlanDetailsService.getPlanDetailsListRecursion(planDetailsParent.getId(), false);
        boolean isAllFinish = true;
        if (CollectionUtils.isNotEmpty(detailsList)) {
            for (ProjectPlanDetails details : detailsList) {
                if (!StringUtils.equals(details.getStatus(), ProcessStatusEnum.FINISH.getValue())) {
                    isAllFinish = false;
                }
            }
        }
        if(isAllFinish){
            planDetailsParent.setStatus(ProcessStatusEnum.FINISH.getValue());
            projectPlanDetailsService.updateProjectPlanDetails(planDetailsParent);
        }
        //更新各项表单任务状态
        surveyCommonService.updateExamineTaskStatus(projectPlanDetails.getId(), commonService.thisUserAccount(), ProjectStatusEnum.FINISH);
    }
}
