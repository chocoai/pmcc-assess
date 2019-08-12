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
     * 初始化阶段任务
     *
     * @param projectPlan
     */
    @Transactional(rollbackFor = Exception.class)
    public void initPlanDetails(ProjectPlan projectPlan) {
        //判断该阶段是否已有任务
        List<ProjectPlanDetailsVo> planDetailsVoList = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(projectPlan.getId());
        if (CollectionUtils.isNotEmpty(planDetailsVoList)) return;

        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();
        List<ProjectPhase> projectPhases = projectPhaseService.getCacheProjectPhaseByCategoryId(projectPlan.getCategoryId(), workStageId);
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordList(projectId, false);
        //案例调查任务和他项权利任务与项目挂钩
        if (CollectionUtils.isEmpty(projectPhases)) return;
        List<ProjectPhase> filter = LangUtils.filter(projectPhases,
                o -> StringUtils.equals(o.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY)
                || StringUtils.equals(o.getPhaseKey(), AssessPhaseKeyConstant.OTHER_RIGHT)
                        || StringUtils.equals(o.getPhaseKey(), AssessPhaseKeyConstant.SCENE_EXPLORE_CIP));
        if (CollectionUtils.isNotEmpty(filter)) {//案例调查任务
            projectPhases.removeAll(filter);
            String projectManager = projectMemberService.getProjectManager(projectId);
            for (ProjectPhase projectPhase : filter) {
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
                projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetail.setPlanHours(projectPhase.getPhaseTime());
                projectPlanDetail.setPid(0);
                projectPlanDetail.setFirstPid(0);
                projectPlanDetail.setProjectPhaseId(projectPhase.getId());
                projectPlanDetail.setSorting(10000);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
            }
        }
        generatePlanDetails(planId, projectId, workStageId, projectPhases, declareRecords);
    }

    /**
     * 追加清查查勘任务
     */
    public void appendPlanDetails(Integer projectId, Integer currStageSort) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        List<ProjectPhase> projectPhases = Lists.newArrayList();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
        if (projectPhase != null) projectPhases.add(projectPhase);
        projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
        if (projectPhase != null) projectPhases.add(projectPhase);

        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordList(projectId, false);
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            ProjectPlan projectPlan = projectPlanService.getProjectPlan(projectId, currStageSort + 1);
            generatePlanDetails(projectPlan.getId(), projectId, projectPlan.getWorkStageId(), projectPhases, declareRecords);
        }
    }

    private void generatePlanDetails(Integer planId, Integer projectId, Integer workStageId, List<ProjectPhase> projectPhases, List<DeclareRecord> declareRecords) {
        int i = 1;
        ProjectPlanDetails projectPlanDetails = null;
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(workStageId);
        String projectManager = projectMemberService.getProjectManager(projectId);
        for (DeclareRecord declareRecord : declareRecords) {
            projectPlanDetails = new ProjectPlanDetails();
            projectPlanDetails.setProjectWorkStageId(workStageId);
            projectPlanDetails.setPlanId(planId);
            projectPlanDetails.setProjectId(projectId);
            projectPlanDetails.setProjectPhaseName(declareRecord.getName());
            projectPlanDetails.setDeclareRecordId(declareRecord.getId());
            projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            projectPlanDetails.setBisLastLayer(false);
            projectPlanDetails.setSorting(i++);
            projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
            int j = 0;
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                projectPlanDetail.setProjectWorkStageId(workStageId);
                projectPlanDetail.setPlanId(planId);
                projectPlanDetail.setProjectId(projectId);
                projectPlanDetail.setProjectPhaseName(projectPhase.getProjectPhaseName());
                projectPlanDetail.setPlanHours(projectPhase.getPhaseTime());
                projectPlanDetail.setPid(projectPlanDetails.getId());
                projectPlanDetail.setDeclareRecordId(declareRecord.getId());
                projectPlanDetail.setFirstPid(projectPlanDetails.getId());
                projectPlanDetail.setProjectPhaseId(projectPhase.getId());
                projectPlanDetail.setSorting(j++);
                projectPlanDetail.setExecuteUserAccount(projectManager);
                SysUserDto sysUser = erpRpcUserService.getSysUser(projectManager);
                if (sysUser != null) {
                    projectPlanDetail.setExecuteDepartmentId(sysUser.getDepartmentId());
                }
                projectPlanDetail.setPlanStartDate(new Date());
                projectPlanDetail.setPlanEndDate(new Date());
                projectPlanDetail.setBisEnable(true);
                projectPlanDetail.setProcessInsId("0");
                projectPlanDetail.setStatus(ProjectStatusEnum.RUNING.getKey());
                projectPlanDetail.setCreator(commonService.thisUserAccount());
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
                try {
                    projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetail, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
                } catch (BpmException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            declareRecord.setBisGenerate(true);
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
