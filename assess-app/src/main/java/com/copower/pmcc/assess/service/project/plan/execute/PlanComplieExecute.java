package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.survey.ProjectPlanSurveyService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
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
 * 房报告编写计划执行时对应方法
 */
@Component
@WorkFlowAnnotation(desc = "报告编写自动")
public class PlanComplieExecute implements ProjectPlanExecuteInterface {
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
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(ProjectPlan projectPlan, ProjectWorkStage projectWorkStage) throws BusinessException, BpmException {
        //获取项目下区域，再针对区域添加市场背景描述与分析任务
        List<SchemeAreaGroup> areaGroupList = schemeAreaGroupService.getAreaGroupEnableByProjectId(projectPlan.getProjectId());
        if (CollectionUtils.isEmpty(areaGroupList)) return;
        int i = 0;
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        SysUserDto projectManager = erpRpcUserService.getSysUser(projectMemberService.getProjectManager(projectInfo.getId()));
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.REPORT_ANALYSIS_CATEGORY_MARKET, projectInfo.getProjectCategoryId());
        for (SchemeAreaGroup schemeAreaGroup : areaGroupList) {
            //添加子项任务
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
            projectPlanDetails.setBisLastLayer(true);
            projectPlanDetails.setPlanRemarks(schemeAreaGroup.getAreaName());
            projectPlanDetails.setPid(0);
            projectPlanDetails.setSorting(i++);
            projectPlanDetails.setAreaId(schemeAreaGroup.getId());
            projectPlanDetails.setStatus(ProjectStatusEnum.RUNING.getKey());
            projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
            projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
        }
        projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
        projectPlan.setBisAutoComplete(true);
        projectPlanService.updateProjectPlan(projectPlan);
    }

}
