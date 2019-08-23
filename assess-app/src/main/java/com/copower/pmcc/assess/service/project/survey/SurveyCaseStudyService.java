package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyCaseStudyDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zly on 2018/5/15.
 * 案例
 */
@Service
public class SurveyCaseStudyService {
    @Autowired
    private SurveyCaseStudyDao surveyCaseStudyDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;

    /**
     * 数据保存
     *
     * @param surveyCaseStudy
     * @return
     * @throws BusinessException
     */
    public boolean saveSurveyCaseStudy(SurveyCaseStudy surveyCaseStudy) throws BusinessException {
        if (surveyCaseStudy == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyCaseStudy.getId() != null && surveyCaseStudy.getId() > 0) {
            return surveyCaseStudyDao.updateSurveyCaseStudy(surveyCaseStudy);
        } else {
            surveyCaseStudy.setCreator(commonService.thisUserAccount());
            return surveyCaseStudyDao.addSurveyCaseStudy(surveyCaseStudy);
        }
    }

    public boolean updateSurveyCaseStudy(SurveyCaseStudy surveyCaseStudy) {
        if (surveyCaseStudy == null) {
            return false;
        }
        return surveyCaseStudyDao.updateSurveyCaseStudy(surveyCaseStudy);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public boolean deleteSurveyCaseStudy(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return surveyCaseStudyDao.deleteSurveyCaseStudy(id);

    }

    /**
     * 获取数据
     *
     * @param processInsId
     * @return
     */
    public SurveyCaseStudy getSurveyCaseStudy(String processInsId) {
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudyDao.getSurveyCaseStudy(processInsId);
        return surveyCaseStudy;
    }


    /**
     * 保存案例任务
     *
     * @param projectPlanDetails
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveCaseTask(ProjectPlanDetails projectPlanDetails, Integer planDetailsId, Integer transactionType, String examineFormType) throws BusinessException {
        //添加子项节点
        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        planDetails.setId(0);
        planDetails.setPid(projectPlanDetails.getPid());
        planDetails.setProjectPhaseId(null);
        int sort = 1;
        List<ProjectPlanDetails> childrenPlanDetailsList = projectPlanDetailsService.getChildrenPlanDetailsList(projectPlanDetails.getPid());
        if (CollectionUtils.isNotEmpty(childrenPlanDetailsList)) {
            ProjectPlanDetails lastDetails = childrenPlanDetailsList.get(childrenPlanDetailsList.size() - 1);
            sort = lastDetails.getSorting() + 1;
        }
        if (StringUtils.isEmpty(projectPlanDetails.getProjectPhaseName())) {
            planDetails.setProjectPhaseName(String.format("案例%s(%s)", sort, baseDataDicService.getNameById(transactionType)));
        }else {
            planDetails.setProjectPhaseName(projectPlanDetails.getProjectPhaseName());
        }
        planDetails.setPlanStartDate(new Date());
        planDetails.setPlanEndDate(new Date());
        planDetails.setPlanHours(new BigDecimal("1"));
        planDetails.setProportion(projectPlanDetails.getProportion());
        planDetails.setBisStart(false);
        planDetails.setBisLastLayer(false);
        planDetails.setSorting(sort);
        planDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        planDetails.setCreator(commonService.thisUserAccount());
        projectPlanDetailsService.saveProjectPlanDetails(planDetails);
        //分派任务到当前人
        surveyExamineTaskService.examineTaskAssignment(planDetails.getId(), examineFormType, ExamineTypeEnum.CASE, transactionType);
    }

    /**
     * 保存案例任务
     * @param pid
     * @param projectPhaseName
     * @param transactionType
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveCaseTaskExtend(Integer pid,String projectPhaseName, Integer transactionType) throws BusinessException {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(pid);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE);
        ProjectWorkStage workStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectPlanDetails taskPlanDetails = new ProjectPlanDetails();
        BeanUtils.copyProperties(projectPlanDetails, taskPlanDetails);
        taskPlanDetails.setId(null);
        taskPlanDetails.setPid(projectPlanDetails.getId());
        SysUserDto sysUser = erpRpcUserService.getSysUser(commonService.thisUserAccount());
        taskPlanDetails.setProjectPhaseId(projectPhase.getId());
        taskPlanDetails.setExecuteUserAccount(commonService.thisUserAccount());
        taskPlanDetails.setExecuteDepartmentId(sysUser.getDepartmentId());
        taskPlanDetails.setBisLastLayer(true);
        taskPlanDetails.setStatus(ProcessStatusEnum.RUN.getValue());
        taskPlanDetails.setCreator(commonService.thisUserAccount());
        taskPlanDetails.setProjectPhaseName(String.format("%s(%s)",projectPhaseName,baseDataDicService.getNameById(transactionType)));
        projectPlanDetailsService.saveProjectPlanDetails(taskPlanDetails);
        //添加任务
        ProjectResponsibilityDto projectTask = new ProjectResponsibilityDto();
        projectTask.setProjectId(projectPlanDetails.getProjectId());
        projectTask.setProjectName(projectInfo.getProjectName());
        projectTask.setPlanEndTime(projectPlanDetails.getPlanEndDate());
        projectTask.setPlanId(projectPlanDetails.getPlanId());
        projectTask.setPlanDetailsId(taskPlanDetails.getId());
        projectTask.setModel(ResponsibileModelEnum.TASK.getId());
        projectTask.setConclusion(ResponsibileModelEnum.TASK.getName());
        projectTask.setUserAccount(commonService.thisUserAccount());
        projectTask.setBisEnable(true);
        projectTask.setAppKey(applicationConstant.getAppKey());
        projectTask.setPlanDetailsName(String.format("%s[%s]", workStage.getWorkStageName(), taskPlanDetails.getProjectPhaseName()));
        projectPlanService.updateProjectTaskUrl(ResponsibileModelEnum.TASK, projectTask);
        projectTask.setCreator(commonService.thisUserAccount());
        bpmRpcProjectTaskService.saveProjectTask(projectTask);
    }

    /**
     * 删除案例任务
     *
     * @param planDetailsId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteCaseTask(Integer planDetailsId) throws Exception {
        //先验证是否满足删除条件
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(planDetailsId);
        if (basicApplyBatch != null) {
            throw new BusinessException("任务已开始填写，不允许删除");
        }
        //需删除该计划任务下的子项任务，项目待提交的任务
        List<ProjectPlanDetails> list = projectPlanDetailsService.getPlanDetailsListRecursion(planDetailsId, true);
        surveyExamineTaskService.deletePlanDetailsAndTask(list);
        projectPlanDetailsService.deleteProjectPlanDetails(planDetailsId);
    }

    public SurveyCaseStudy getSurveyCaseStudy(Integer planDetailsId) {
        SurveyCaseStudy where = new SurveyCaseStudy();
        where.setPlanDetailsId(planDetailsId);
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudyDao.getSurveyCaseStudy(where);
        return surveyCaseStudy;
    }


}
