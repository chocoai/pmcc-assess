package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyCaseStudyDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudy;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
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

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public boolean deleteSurveyCaseStudy(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
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
    public void saveCaseTask(ProjectPlanDetails projectPlanDetails, Integer planDetailsId) throws BusinessException {
        if (projectPlanDetails.getId() != null && projectPlanDetails.getId() > 0) {
            projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
        } else {
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
        List<ProjectPlanDetails> list = projectPlanDetailsService.getPlanDetailsListRecursion(planDetailsId,true);
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
        surveyCaseStudy.setJsonContent(surveyCommonService.getDeclareCertJson(projectId,declareId));
        surveyCaseStudy.setCreator(commonService.thisUserAccount());
        surveyCaseStudyDao.addSurveyCaseStudy(surveyCaseStudy);
        return surveyCaseStudy;
    }
}
