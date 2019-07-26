package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        List<ProjectPhase> filter = LangUtils.filter(projectPhases, o -> StringUtils.equals(o.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY)
                || StringUtils.equals(o.getPhaseKey(), AssessPhaseKeyConstant.OTHER_RIGHT));
        if (CollectionUtils.isNotEmpty(filter)) {//案例调查任务
            projectPhases.removeAll(filter);
            for (ProjectPhase projectPhase : filter) {
                ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                projectPlanDetail.setProjectWorkStageId(workStageId);
                projectPlanDetail.setPlanId(planId);
                projectPlanDetail.setProjectId(projectId);
                projectPlanDetail.setProjectPhaseName(projectPhase.getProjectPhaseName());
                projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetail.setPlanHours(projectPhase.getPhaseTime());
                projectPlanDetail.setPid(0);
                projectPlanDetail.setFirstPid(0);
                projectPlanDetail.setProjectPhaseId(projectPhase.getId());
                projectPlanDetail.setSorting(10000);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
            }
        }
        generatePlanDetails(planId, projectId, workStageId, projectPhases, declareRecords,false);
    }

    private void generatePlanDetails(Integer planId, Integer projectId, Integer workStageId, List<ProjectPhase> projectPhases, List<DeclareRecord> declareRecords,boolean appendTask)  {
        LinkedHashMap<DeclareRecord, List<ProjectPlanDetails>> listLinkedHashMap = Maps.newLinkedHashMap();
        int i = 1;
        ProjectPlanDetails projectPlanDetails = null;
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
            List<ProjectPlanDetails> projectPlanDetailsList = Lists.newArrayList();
            int j = 0;
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                projectPlanDetail.setProjectWorkStageId(workStageId);
                projectPlanDetail.setPlanId(planId);
                projectPlanDetail.setProjectId(projectId);
                projectPlanDetail.setProjectPhaseName(projectPhase.getProjectPhaseName());
                projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetail.setPlanHours(projectPhase.getPhaseTime());
                projectPlanDetail.setPid(projectPlanDetails.getId());
                projectPlanDetail.setDeclareRecordId(declareRecord.getId());
                projectPlanDetail.setFirstPid(projectPlanDetails.getId());
                projectPlanDetail.setProjectPhaseId(projectPhase.getId());
                projectPlanDetail.setSorting(j++);
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
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
                projectPlanDetailsList.add(projectPlanDetail);
            }
            declareRecord.setBisGenerate(true);
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
            if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                listLinkedHashMap.put(declareRecord, projectPlanDetailsList);
            }
        }
        //在bpm里面追加任务
        if (!listLinkedHashMap.isEmpty()) {
            try {
                projectPlanService.repairTreatmentTask(listLinkedHashMap, workStageId, projectId, projectPhases,appendTask);
            } catch (BpmException e) {
                logger.error(e.getMessage(), e);
            }
        }
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
            if (projectPlan != null)
                generatePlanDetails(projectPlan.getId(), projectId, projectPlan.getWorkStageId(), projectPhases, declareRecords,true);
        }
    }
}
