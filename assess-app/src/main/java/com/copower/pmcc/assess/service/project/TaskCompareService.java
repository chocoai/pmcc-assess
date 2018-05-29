package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.*;
import com.copower.pmcc.assess.dal.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zly on 2018/5/28.
 */
@Service
public class TaskCompareService {

    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SchemeEvaluationObjectDao schemeEvaluationObjectDao;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private SurveyLocaleExploreDao surveyLocaleExploreDao;
    @Autowired
    private SurveyLocaleExploreDetailDao surveyLocaleExploreDetailDao;
    @Autowired
    private SurveyCaseStudyDao surveyCaseStudyDao;
    @Autowired
    private SurveyCaseStudyDetailDao surveyCaseStudyDetailDao;

    public ModelAndView getTaskCompare(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {

        Integer projectPlanDetailsId = projectPlanDetails.getPid();
        ProjectPlanDetails projectPlanDetails1= projectPlanDetailsDao.getProjectPlanDetailsItemById(projectPlanDetailsId);   //根据pid查上级

        Integer schemeEvaluationObjectId = projectPlanDetails1.getEvaluationId();     //查出委估对象主键id
        SchemeEvaluationObject schemeEvaluationObject = schemeEvaluationObjectDao.getSchemeEvaluationObjectById(schemeEvaluationObjectId);  //查出委估对象
        //取出委估对象数据
        Integer schemeEvaluationObjectProjectId = schemeEvaluationObject.getProjectId();
        Integer schemeEvaluationObjectAreaGroupId = schemeEvaluationObject.getAreaGroupId();
        Integer groupNumber = schemeEvaluationObject.getGroupNumber();

        //查出数据
        List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectDao.getByProjectIdAndAreaGroupId(schemeEvaluationObjectProjectId,schemeEvaluationObjectAreaGroupId,groupNumber);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjects.get(0);
        Integer declareRecordId = schemeJudgeObject.getDeclareRecordId();
        List<SurveyLocaleExplore> surveyLocaleExplores = surveyLocaleExploreDao.getByDeclareRecordId(declareRecordId);

        SurveyLocaleExplore surveyLocaleExplore = surveyLocaleExplores.get(0);
        Integer surveyLocaleExplorePlanDetailsId = surveyLocaleExplore.getPlanDetailsId();
        //最终的现场查勘数据
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetails = surveyLocaleExploreDetailDao.getSurveyLocaleExploreDetail(surveyLocaleExplorePlanDetailsId);
        SurveyLocaleExploreDetail surveyLocaleExploreDetail = surveyLocaleExploreDetails.get(0);

        List<SurveyCaseStudy> surveyCaseStudies = surveyCaseStudyDao.getByDeclareRecordId(declareRecordId);
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudies.get(0);
        Integer surveyCaseStudyPlanDetailsId = surveyCaseStudy.getPlanDetailsId();
        //最终的案例调查数据
        List<SurveyCaseStudyDetail> surveyCaseStudyDetails = surveyCaseStudyDetailDao.getSurveyCaseStudyDetail(surveyCaseStudyPlanDetailsId);

        modelAndView.addObject("surveyLocaleExploreDetail",surveyLocaleExploreDetail);  //现场查勘
        modelAndView.addObject("surveyCaseStudyDetails",surveyCaseStudyDetails);      //案例调查
        modelAndView.addObject("schemeEvaluationObject",schemeEvaluationObject);
        return modelAndView;
    }
}
