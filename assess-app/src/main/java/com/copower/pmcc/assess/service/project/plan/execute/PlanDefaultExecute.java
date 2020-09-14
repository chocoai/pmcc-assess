package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-12-28.
 * 房地产评估简单版中现场查勘计划执行时对应方法
 */
@Component
@WorkFlowAnnotation(desc = "计划自动(默认)")
public class PlanDefaultExecute implements ProjectPlanExecuteInterface {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(ProjectPlan projectPlan, ProjectWorkStage projectWorkStage) throws BusinessException, BpmException {
        if (projectWorkStage.getBisLoadDefalut() == Boolean.TRUE) {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
            SysUserDto projectManager = erpRpcUserService.getSysUser(projectMemberService.getProjectManager(projectInfo.getId()));
            List<ProjectPhase> projectPhaseList = projectPhaseService.getCacheProjectPhaseByCategoryId(projectInfo.getProjectCategoryId(), projectWorkStage.getId());
            if (CollectionUtils.isEmpty(projectPhaseList)) return;
            for (ProjectPhase projectPhase : projectPhaseList) {
                ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
                projectPlanDetails.setPlanId(projectPlan.getId());
                projectPlanDetails.setProjectId(projectPlan.getProjectId());
                projectPlanDetails.setProjectPhaseName(projectPhase.getProjectPhaseName());
                projectPlanDetails.setProjectPhaseId(projectPhase.getId());
                projectPlanDetails.setExecuteUserAccount(projectManager.getUserAccount());
                projectPlanDetails.setExecuteDepartmentId(projectManager.getDepartmentId());
                projectPlanDetails.setPlanStartDate(new Date());
                projectPlanDetails.setPlanEndDate(new Date());
                projectPlanDetails.setBisEnable(true);
                projectPlanDetails.setProcessInsId("0");
                projectPlanDetails.setPid(0);
                projectPlanDetails.setBisLastLayer(true);
                projectPlanDetails.setSorting(projectPhase.getPhaseSort());
                projectPlanDetails.setStatus(ProjectStatusEnum.RUNING.getKey());
                projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
                projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
            }
        }

        if (projectPlan != null) {
            projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
            projectPlan.setBisAutoComplete(true);
            projectPlanService.updateProjectPlan(projectPlan);
        }
    }

}
