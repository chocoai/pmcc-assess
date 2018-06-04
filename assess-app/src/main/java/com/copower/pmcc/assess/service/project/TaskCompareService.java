package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.dao.*;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.*;
import com.copower.pmcc.assess.dto.output.project.SurveyCaseStudyDetailVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.assess.service.data.CaseComparisonFieldService;
import com.copower.pmcc.assess.service.data.CaseComparisonService;
import com.copower.pmcc.assess.service.data.HousePriceIndexService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
    @Autowired
    private MethodMarketCompareFactorService methodMarketCompareFactorService;
    @Autowired
    private MethodMarketCompareIndexService methodMarketCompareIndexService;
    @Autowired
    private MethodMarketCompareCalculationService methodMarketCompareCalculationService;
    @Autowired
    private MethodMarketCompareResultService methodMarketCompareResultService;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private HousePriceIndexService housePriceIndexService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private CaseComparisonService caseComparisonService;
    @Autowired
    private CaseComparisonFieldService caseComparisonFieldService;

    public ModelAndView getTaskCompare(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {

        Integer projectPlanDetailsId = projectPlanDetails.getPid();
        ProjectPlanDetails projectPlanDetails1 = projectPlanDetailsDao.getProjectPlanDetailsItemById(projectPlanDetailsId);   //根据pid查上级

        Integer schemeEvaluationObjectId = projectPlanDetails1.getEvaluationId();     //查出委估对象主键id
        SchemeEvaluationObject schemeEvaluationObject = schemeEvaluationObjectDao.getSchemeEvaluationObjectById(schemeEvaluationObjectId);  //查出委估对象
        //取出委估对象数据
        Integer schemeEvaluationObjectProjectId = schemeEvaluationObject.getProjectId();
        Integer schemeEvaluationObjectAreaGroupId = schemeEvaluationObject.getAreaGroupId();
        Integer groupNumber = schemeEvaluationObject.getGroupNumber();

        //查出数据
        List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectDao.getByProjectIdAndAreaGroupId(schemeEvaluationObjectProjectId, schemeEvaluationObjectAreaGroupId, groupNumber);
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
        List<SurveyCaseStudyDetailVo> surveyCaseStudyDetailVo = getVoList(surveyCaseStudyDetails);
        List<HousePriceIndex> housePriceIndexs = housePriceIndexService.getData();

        modelAndView.addObject("surveyLocaleExploreDetail", surveyLocaleExploreDetail);  //现场查勘
        modelAndView.addObject("surveyCaseStudyDetails", surveyCaseStudyDetailVo);      //案例调查
        modelAndView.addObject("schemeEvaluationObject", schemeEvaluationObject);
        modelAndView.addObject("housePriceIndexs", housePriceIndexs);    //指数
        return modelAndView;
    }

    //提交时保存数据
    public void saveData(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) {
        MethodMarketCompareCommonDto methodMarketCompareCommonDto = JSON.parseObject(formData, MethodMarketCompareCommonDto.class);  //json 转对象
        //提取各自对象
        List<MethodMarketCompareFactorDto> methodMarketCompareFactorDtos = methodMarketCompareCommonDto.getMethodMarketCompareFactorDtos();
        List<MethodMarketCompareIndexDto> methodMarketCompareIndexDtos = methodMarketCompareCommonDto.getMethodMarketCompareIndexDtos();
        List<MethodMarketCompareCalculationDto> methodMarketCompareCalculationDtos = methodMarketCompareCommonDto.getMethodMarketCompareCalculationDtos();
        List<MethodMarketCompareResultDto> methodMarketCompareResultDtos = methodMarketCompareCommonDto.getMethodMarketCompareResultDtos();

        methodMarketCompareFactorService.save(methodMarketCompareFactorDtos);   //因素表
        methodMarketCompareIndexService.save(methodMarketCompareIndexDtos);     //指数表
        methodMarketCompareCalculationService.save(methodMarketCompareCalculationDtos); //测算表
        methodMarketCompareResultService.save(methodMarketCompareResultDtos);   //结果表

        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setEvaluationObjectId(methodMarketCompareResultDtos.get(0).getEvaluationObjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getPlanId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setCreator(commonService.thisUserAccount());
        schemeInfoService.addReturnID(schemeInfo);  //主表
    }

    //审批获取数据
    public ModelAndView getApprovalView(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {

        Integer projectPlanDetailsId = projectPlanDetails.getPid();
        ProjectPlanDetails projectPlanDetails1 = projectPlanDetailsDao.getProjectPlanDetailsItemById(projectPlanDetailsId);
        Integer schemeEvaluationObjectId = projectPlanDetails1.getEvaluationId();
        List<MethodMarketCompareFactor> methodMarketCompareFactors = methodMarketCompareFactorService.getDataByEvaluationObjectId(schemeEvaluationObjectId);
        List<MethodMarketCompareIndex> methodMarketCompareIndexs = methodMarketCompareIndexService.getDataByEvaluationObjectId(schemeEvaluationObjectId);
        List<MethodMarketCompareCalculation> methodMarketCompareCalculations = methodMarketCompareCalculationService.getDataByEvaluationObjectId(schemeEvaluationObjectId);
        List<MethodMarketCompareResult> methodMarketCompareResults = methodMarketCompareResultService.getDataByEvaluationObjectId(schemeEvaluationObjectId);
        List<HousePriceIndex> housePriceIndexs = housePriceIndexService.getData();

        getDynamic(modelAndView, schemeEvaluationObjectId);

        modelAndView.addObject("methodMarketCompareFactors", methodMarketCompareFactors);
        modelAndView.addObject("methodMarketCompareIndexs", methodMarketCompareIndexs);
        modelAndView.addObject("methodMarketCompareCalculations", methodMarketCompareCalculations);
        modelAndView.addObject("methodMarketCompareResults", methodMarketCompareResults);
        modelAndView.addObject("housePriceIndexs", housePriceIndexs);
        modelAndView.addObject("schemeEvaluationObjectId", schemeEvaluationObjectId);

        return modelAndView;
    }

    private void getDynamic(ModelAndView modelAndView, Integer schemeEvaluationObjectId) {
        SchemeEvaluationObject schemeEvaluationObject = schemeEvaluationObjectDao.getSchemeEvaluationObjectById(schemeEvaluationObjectId);  //查出委估对象
        //取出委估对象数据
        Integer schemeEvaluationObjectProjectId = schemeEvaluationObject.getProjectId();
        Integer schemeEvaluationObjectAreaGroupId = schemeEvaluationObject.getAreaGroupId();
        Integer groupNumber = schemeEvaluationObject.getGroupNumber();

        //查出数据
        List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectDao.getByProjectIdAndAreaGroupId(schemeEvaluationObjectProjectId, schemeEvaluationObjectAreaGroupId, groupNumber);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjects.get(0);
        Integer declareRecordId = schemeJudgeObject.getDeclareRecordId();
        List<SurveyLocaleExplore> surveyLocaleExplores = surveyLocaleExploreDao.getByDeclareRecordId(declareRecordId);

        SurveyLocaleExplore surveyLocaleExplore = surveyLocaleExplores.get(0);
        Integer surveyLocaleExplorePlanDetailsId = surveyLocaleExplore.getPlanDetailsId();
        //最终的现场查勘数据
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetails = surveyLocaleExploreDetailDao.getSurveyLocaleExploreDetail(surveyLocaleExplorePlanDetailsId);
        SurveyLocaleExploreDetail surveyLocaleExploreDetail = surveyLocaleExploreDetails.get(0);
        Integer tableId = surveyLocaleExploreDetail.getDynamicTableId();
        String tableName = surveyLocaleExploreDetail.getDynamicTableName();
        Map<String, Object> map = formConfigureService.getObjectSingle(tableName, tableId);

        List<SurveyCaseStudy> surveyCaseStudies = surveyCaseStudyDao.getByDeclareRecordId(declareRecordId);
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudies.get(0);
        Integer surveyCaseStudyPlanDetailsId = surveyCaseStudy.getPlanDetailsId();

        List<Map> list = new ArrayList<Map>();
        list.add(map);
        //最终的案例调查数据
        List<SurveyCaseStudyDetail> surveyCaseStudyDetails = surveyCaseStudyDetailDao.getSurveyCaseStudyDetail(surveyCaseStudyPlanDetailsId);
        Map<String, Object> map1 = new HashMap<>();
        for (SurveyCaseStudyDetail surveyCaseStudyDetail : surveyCaseStudyDetails) {
            Integer tableId1 = surveyCaseStudyDetail.getDynamicTableId();
            String tableName1 = surveyCaseStudyDetail.getDynamicTableName();
            map1 = formConfigureService.getObjectSingle(tableName1, tableId1);
            list.add(map1);
        }
        List<DataCaseComparison> dataCaseComparisons = caseComparisonService.getAll();      //原始的动态表单
        List<DataCaseComparison> dataCaseComparisonss = new ArrayList<>();      //替换后的动态表单
        for (DataCaseComparison dataCaseComparison : dataCaseComparisons) {
            Integer caseId = dataCaseComparison.getId();
            List<DataCaseComparisonField> caseComparisonFields = caseComparisonFieldService.getData(caseId);
            for (DataCaseComparisonField dataCaseComparisonField : caseComparisonFields) {
                for (Map map2 : list) {
                    Iterator<Map.Entry<String, Object>> it = map2.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Object> entry = it.next();
                        if (entry.getKey().equals(dataCaseComparisonField.getFieldName())) {
                            dataCaseComparison.setuExplain(dataCaseComparison.getuExplain().replaceAll(dataCaseComparisonField.getuName(), String.valueOf(entry.getValue())));  //替换
                        }
                    }
                }
            }
            dataCaseComparisonss.add(dataCaseComparison);
        }
    }


    private List<SurveyCaseStudyDetailVo> getVoList(List<SurveyCaseStudyDetail> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyCaseStudyDetailVo surveyCaseStudyDetailVo = new SurveyCaseStudyDetailVo();
            BeanUtils.copyProperties(p, surveyCaseStudyDetailVo);
            if (p.getCaseType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getCaseType());
                if (baseDataDic != null)
                    surveyCaseStudyDetailVo.setCaseTypeName(baseDataDic.getName());
            }
            return surveyCaseStudyDetailVo;
        });
    }

    public void editData(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) {
        MethodMarketCompareCommonDto methodMarketCompareCommonDto = JSON.parseObject(formData, MethodMarketCompareCommonDto.class);  //json 转对象
        //提取各自对象
        List<MethodMarketCompareFactorDto> methodMarketCompareFactorDtos = methodMarketCompareCommonDto.getMethodMarketCompareFactorDtos();
        List<MethodMarketCompareIndexDto> methodMarketCompareIndexDtos = methodMarketCompareCommonDto.getMethodMarketCompareIndexDtos();
        List<MethodMarketCompareCalculationDto> methodMarketCompareCalculationDtos = methodMarketCompareCommonDto.getMethodMarketCompareCalculationDtos();
        List<MethodMarketCompareResultDto> methodMarketCompareResultDtos = methodMarketCompareCommonDto.getMethodMarketCompareResultDtos();

        methodMarketCompareFactorService.updata(methodMarketCompareFactorDtos);   //因素表
        methodMarketCompareIndexService.updata(methodMarketCompareIndexDtos);     //指数表
        methodMarketCompareCalculationService.updata(methodMarketCompareCalculationDtos); //测算表
        methodMarketCompareResultService.updata(methodMarketCompareResultDtos);   //结果表
    }
}
