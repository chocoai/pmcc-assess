package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.survey.ProjectPlanSurveyService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-12-28.
 * 房地产评估简单版中现场查勘计划执行时对应方法
 */
@Component
@WorkFlowAnnotation(desc = "现场查勘")
public class PlanSurveyExecute implements ProjectPlanExecuteInterface {
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanSurveyService projectPlanSurveyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(ProjectPlan projectPlan, ProjectWorkStage projectWorkStage) throws BusinessException, BpmException {
        //1.根据申报数据生成任务数据
        projectPlanSurveyService.initSurveyPlanDetails(projectPlan);
        projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
        projectPlan.setBisAutoComplete(true);
        projectPlanService.updateProjectPlan(projectPlan);
    }

}
