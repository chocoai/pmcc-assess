package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
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
    private ProjectPhaseService projectPhaseService;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(ProjectPlan projectPlan,ProjectWorkStage projectWorkStage) throws BusinessException, BpmException {
        //自动执行
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        List<ProjectPhase> phaseList = projectPhaseService.getCacheProjectPhaseByCategoryId(projectInfo.getProjectCategoryId(), projectPlan.getWorkStageId());
        String projectManager = projectMemberService.getProjectManager(projectInfo.getId());
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
                projectPlanDetails.setExecuteUserAccount(projectManager);
                SysUserDto sysUser = erpRpcUserService.getSysUser(projectManager);
                if (sysUser != null) {
                    projectPlanDetails.setExecuteDepartmentId(sysUser.getDepartmentId());
                }
                projectPlanDetails.setBisEnable(true);
                projectPlanDetails.setSorting(projectPhase.getPhaseSort());
                projectPlanDetails.setProcessInsId("0");
                projectPlanDetails.setBisLastLayer(true);
                projectPlanDetails.setPlanStartDate(new Date());
                projectPlanDetails.setPlanEndDate(new Date());
                projectPlanDetails.setPlanHours(projectPhase.getPhaseTime());
                projectPlanDetails.setStatus(ProjectStatusEnum.RUNING.getKey());
                projectPlanDetails.setCreator(projectManager);
                projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);

                projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
            }
        }
        projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
        projectPlan.setBisAutoComplete(true);
        projectPlanService.updateProjectPlan(projectPlan);
    }

}
