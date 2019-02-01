package com.copower.pmcc.assess.service.project.plan.execute;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.proxy.face.ProjectPlanExecuteInterface;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.survey.ProjectPlanSurveyService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-12-28.
 * 房地产评估简单版中现场查勘计划执行时对应方法
 */
@Component
@WorkFlowAnnotation(desc = "现场查勘自动")
public class PlanSurveyExecute implements ProjectPlanExecuteInterface {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanSurveyService projectPlanSurveyService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;


    @Override
    public void execute(ProjectPlan projectPlan, ProjectWorkStage projectWorkStage) throws BusinessException {
        //1.根据申报数据生成任务数据
        projectPlanSurveyService.initPlanDetails(projectPlan);
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setBisLastLayer(true);
        where.setPlanId(projectPlan.getId());
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectDetails(where);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        String projectManager = projectMemberService.getProjectManager(projectInfo.getId());
        if (CollectionUtils.isNotEmpty(planDetailsList)) {
            for (ProjectPlanDetails projectPlanDetails : planDetailsList) {
                projectPlanDetails.setExecuteUserAccount(projectManager);
                SysUserDto sysUser = erpRpcUserService.getSysUser(projectManager);
                if (sysUser != null) {
                    projectPlanDetails.setExecuteDepartmentId(sysUser.getDepartmentId());
                }
                projectPlanDetails.setPlanStartDate(new Date());
                projectPlanDetails.setPlanEndDate(new Date());
                projectPlanDetails.setBisEnable(true);
                projectPlanDetails.setProcessInsId("0");
                projectPlanDetails.setStatus(ProjectStatusEnum.RUNING.getKey());
                projectPlanDetails.setCreator(commonService.thisUserAccount());
                projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
                projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
            }
        }
        projectPlan.setProjectStatus(ProjectStatusEnum.TASK.getKey());
        projectPlan.setBisAutoComplete(true);
        projectPlanService.updateProjectPlan(projectPlan);
    }

}
