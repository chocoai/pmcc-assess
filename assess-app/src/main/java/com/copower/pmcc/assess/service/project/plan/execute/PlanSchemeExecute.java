package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by kings on 2018-12-28.
 * 直接自动跳过计划编制
 */
@Component
@WorkFlowAnnotation(desc = "评估方案")
public class PlanSchemeExecute implements ProjectPlanExecuteInterface {
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
        if (projectPlan != null) {
            //产生一个设置方案的任务

            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
            SysUserDto projectManager = erpRpcUserService.getSysUser(projectMemberService.getProjectManager(projectInfo.getId()));
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.ASSESS_SCHEME_PROGRAMME_SETTING, projectInfo.getProjectCategoryId());

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
            projectPlanDetails.setPlanRemarks("评估方案设置(设置市场比较法,收益法等)");
            projectPlanDetails.setBisLastLayer(true);
            projectPlanDetails.setPid(0);
            projectPlanDetails.setSorting(1);
            projectPlanDetails.setStatus(ProjectStatusEnum.RUNING.getKey());
            projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
            projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);

            projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
            projectPlan.setBisAutoComplete(true);
            projectPlanService.updateProjectPlan(projectPlan);
        }
    }
}
