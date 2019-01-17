package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyCaseStudyDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
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
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public void saveCaseTask(ProjectPlanDetails projectPlanDetails, Integer planDetailsId, String examineFormType) throws BusinessException {
        if (projectPlanDetails.getId() != null && projectPlanDetails.getId() > 0) {
            projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);//仅保存案例名称
        } else {
            //添加子项节点
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            planDetails.setId(0);
            planDetails.setPid(projectPlanDetails.getPid());
            planDetails.setProjectPhaseName(projectPlanDetails.getProjectPhaseName());
            planDetails.setPlanStartDate(projectPlanDetails.getPlanStartDate());
            planDetails.setPlanEndDate(projectPlanDetails.getPlanEndDate());
            planDetails.setPlanHours(projectPlanDetails.getPlanHours());
            planDetails.setProportion(projectPlanDetails.getProportion());
            planDetails.setSorting(1);
            planDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            planDetails.setCreator(commonService.thisUserAccount());
            projectPlanDetailsService.saveProjectPlanDetails(planDetails);
            //分派任务到当前人
            surveyExamineTaskService.examineTaskAssignment(planDetails.getId(), examineFormType,ExamineTypeEnum.CASE);
        }
    }

    /**
     * 删除案例任务
     *
     * @param planDetailsId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteCaseTask(Integer planDetailsId) throws BusinessException {
        //先验证是否满足删除条件
        //需删除该计划任务下的子项任务，项目待提交的任务
        List<ProjectPlanDetails> list = projectPlanDetailsService.getPlanDetailsListRecursion(planDetailsId, true);
        if (CollectionUtils.isNotEmpty(list)) {
            //检查任务是否允许删除
            for (ProjectPlanDetails projectPlanDetails : list) {
                if (!StringUtils.equals(projectPlanDetails.getStatus(), ProcessStatusEnum.NOPROCESS.getValue()))
                    throw new BusinessException("任务已开始不允许删除");
            }
            surveyExamineTaskService.deletePlanDetailsAndTask(list);
        }
        projectPlanDetailsService.deleteProjectPlanDetails(planDetailsId);
    }

    public SurveyCaseStudy getSurveyCaseStudy(Integer planDetailsId) {
        SurveyCaseStudy where = new SurveyCaseStudy();
        where.setPlanDetailsId(planDetailsId);
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudyDao.getSurveyCaseStudy(where);
        return surveyCaseStudy;
    }

    /**
     * 初始化
     *
     * @param projectId
     * @param planDetailsId
     * @param declareId
     */
    public SurveyCaseStudy initCaseStudy(Integer projectId, Integer planDetailsId, Integer declareId) {
        SurveyCaseStudy where = new SurveyCaseStudy();
        where.setProjectId(projectId);
        where.setPlanDetailsId(planDetailsId);
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudyDao.getSurveyCaseStudy(where);
        if (surveyCaseStudy != null) return surveyCaseStudy;
        surveyCaseStudy = new SurveyCaseStudy();
        surveyCaseStudy.setProjectId(projectId);
        surveyCaseStudy.setPlanDetailsId(planDetailsId);
        surveyCaseStudy.setDeclareId(declareId);
        surveyCaseStudy.setJsonContent(surveyCommonService.getDeclareCertJson(projectId, declareId));
        surveyCaseStudy.setCreator(commonService.thisUserAccount());
        surveyCaseStudyDao.addSurveyCaseStudy(surveyCaseStudy);
        return surveyCaseStudy;
    }

    /**
     * 复制案例
     *
     * @param planDetailsId
     */
    @Transactional
    public void copyCaseStudy(Integer planDetailsId) throws BusinessException {
        //1.先复制planDetails数据 2.复制调查信息数据 3.复制所关联的附件信息
        //分派等相关任务还需手动操作
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        projectPlanDetails.setId(null);
        projectPlanDetails.setProjectPhaseName(String.format("%s-复制", projectPlanDetails.getProjectPhaseName()));
        projectPlanDetails.setSorting(projectPlanDetails.getSorting() + 100);
        projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        projectPlanDetails.setCreator(commonService.thisUserAccount());
        projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tableList = surveyCommonService.getTableList();
        //同步examine表数据
        for (String tableName : tableList) {
            stringBuilder.append(surveyCommonService.getSynchronizeSql(tableName, planDetailsId, projectPlanDetails.getId(), projectPlanDetails.getDeclareRecordId()));
        }
        jdbcTemplate.execute(stringBuilder.toString());

//        List<ProjectPlanDetails> childrenPlanDetailsList = projectPlanDetailsService.getChildrenPlanDetailsList(planDetailsId);
//        if(CollectionUtils.isNotEmpty(childrenPlanDetailsList)){
//
//            for (ProjectPlanDetails planDetails : childrenPlanDetailsList) {
//                planDetails.setId(null);
//                planDetails.setPid(projectPlanDetails.getId());
//                planDetails.setBisStart(false);
//                planDetails.setProcessInsId("0");
//                planDetails.setTaskSubmitTime(null);
//                planDetails.setActualHours(null);
//                planDetails.setStatus(ProcessStatusEnum.NOPROCESS.getName());
//                planDetails.setCreator(commonService.thisUserAccount());
//                projectPlanDetailsService.saveProjectPlanDetails(planDetails);
//
//            }
//
//        }

        //关联附件信息
    }
}
