package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ProjectPlanSurveyService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;

    /**
     * 初始化查勘阶段任务
     *
     * @param projectPlan
     */
    @Transactional(rollbackFor = Exception.class)
    public void initSurveyPlanDetails(ProjectPlan projectPlan) {
        //判断该阶段是否已有任务
        List<ProjectPlanDetailsVo> planDetailsVoList = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(projectPlan.getId());
        if (CollectionUtils.isNotEmpty(planDetailsVoList)) return;

        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();

        ProjectPhase otherRightPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.OTHER_RIGHT, projectPlan.getCategoryId());
        ProjectPhase assetInventoryPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.ASSET_INVENTORY, projectPlan.getCategoryId());
        ProjectPhase sceneExplorePhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPlan.getCategoryId());
        List<ProjectPhase> projectPhases = Lists.newArrayList(sceneExplorePhase, assetInventoryPhase, otherRightPhase);
        if (CollectionUtils.isEmpty(projectPhases)) return;
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(workStageId);
        //添加任务
        String projectManager = projectMemberService.getProjectManager(projectId);
        for (ProjectPhase projectPhase : projectPhases) {
            ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
            projectPlanDetail.setProjectWorkStageId(workStageId);
            projectPlanDetail.setPlanId(planId);
            projectPlanDetail.setProjectId(projectId);
            projectPlanDetail.setExecuteUserAccount(projectManager);
            SysUserDto sysUser = erpRpcUserService.getSysUser(projectManager);
            if (sysUser != null) {
                projectPlanDetail.setExecuteDepartmentId(sysUser.getDepartmentId());
            }
            projectPlanDetail.setProjectPhaseName(projectPhase.getProjectPhaseName());
            projectPlanDetail.setPlanStartDate(new Date());
            projectPlanDetail.setPlanEndDate(new Date());
            projectPlanDetail.setBisEnable(true);
            projectPlanDetail.setProcessInsId("0");
            projectPlanDetail.setBisLastLayer(true);
            projectPlanDetail.setStatus(ProcessStatusEnum.RUN.getValue());
            projectPlanDetail.setPlanHours(projectPhase.getPhaseTime());
            projectPlanDetail.setPid(0);
            projectPlanDetail.setProjectPhaseId(projectPhase.getId());
            projectPlanDetail.setSorting(10000 + projectPhase.getPhaseSort());
            projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
            try {
                projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetail, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
            } catch (BpmException e) {
                logger.error("查勘添加task任务" + e.getMessage(), e);
            }
        }
    }
}
