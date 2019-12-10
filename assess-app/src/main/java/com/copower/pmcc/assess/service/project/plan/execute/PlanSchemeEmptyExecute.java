package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-12-28.
 * 直接自动跳过计划编制
 */
@Component
@WorkFlowAnnotation(desc = "评估方案自动(空)")
public class PlanSchemeEmptyExecute implements ProjectPlanExecuteInterface {
    @Autowired
    private ProjectPlanService projectPlanService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(ProjectPlan projectPlan, ProjectWorkStage projectWorkStage) throws BusinessException, BpmException {
        if (projectPlan != null) {
            projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
            projectPlan.setBisAutoComplete(true);
            projectPlanService.updateProjectPlan(projectPlan);
        }
    }
}
