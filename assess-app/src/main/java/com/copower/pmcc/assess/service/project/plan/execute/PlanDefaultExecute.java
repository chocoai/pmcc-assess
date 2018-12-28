package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kings on 2018-12-28.
 * 房地产评估简单版中现场查勘计划执行时对应方法
 */
@Component
@WorkFlowAnnotation(desc = "计划自动(默认)")
public class PlanDefaultExecute implements ProjectPlanExecuteInterface {
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;


    @Override
    public void execute(ProjectPlan projectPlan,ProjectWorkStage projectWorkStage) throws BusinessException {
        //自动执行
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        List<ProjectPhase> phaseList = projectPhaseService.getCacheProjectPhaseByCategoryId(projectInfo.getProjectCategoryId(), projectPlan.getWorkStageId());
        if (CollectionUtils.isNotEmpty(phaseList)) {
            for (ProjectPhase projectPhase : phaseList) {
                //1.写入projectPlanDetails表 2.写入bpm任务表
                ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                projectPlanDetails.setPid(0);
                projectPlanDetails.setProjectPhaseName(projectPhase.getProjectPhaseName());
                projectPlanDetails.setProjectId(projectInfo.getId());
                projectPlanDetails.setPlanId(projectPlan.getId());
                projectPlanDetails.setProjectWorkStageId(projectWorkStage.getId());
                projectPlanDetails.setProjectPhaseId(projectPhase.getId());
                projectPlanDetails.setExecuteUserAccount(commonService.thisUserAccount());
                projectPlanDetails.setExecuteDepartmentId(commonService.thisUser().getDepartmentId());
                projectPlanDetails.setBisEnable(true);
                projectPlanDetails.setSorting(projectPhase.getPhaseSort());
                projectPlanDetails.setProcessInsId("0");
                projectPlanDetails.setStatus(ProjectStatusEnum.RUNING.getKey());
                projectPlanDetails.setCreator(commonService.thisUserAccount());
                projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);

                projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
            }
        }
        projectPlan.setProjectStatus(ProjectStatusEnum.FINISH.getName());
        projectPlanService.updateProjectPlan(projectPlan);
    }

}
