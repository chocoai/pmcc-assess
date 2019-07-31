package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.event.project.SurveyExamineTaskEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "查勘及案例调查信息成果")
public class ProjectTaskExamineAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private SurveySceneExploreService surveySceneExploreService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("", "", 0, "0", "");
        modelAndView.setViewName(getViewUrlAndSetParam(true, projectPlanDetails, modelAndView, commonService.thisUserAccount()));
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.setViewName(getViewUrlAndSetParam(false, projectPlanDetails, modelAndView, projectPlanDetails.getExecuteUserAccount()));
        return modelAndView;
    }


    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.setViewName(getViewUrlAndSetParam(true, projectPlanDetails, modelAndView, commonService.thisUserAccount()));
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        modelAndView.setViewName(getViewUrlAndSetParam(false, projectPlanDetails, modelAndView, projectPlanDetails.getExecuteUserAccount()));
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        try {
            surveyExamineTaskService.saveExamineDataInfo(formData, projectPlanDetails);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        if (StringUtils.isBlank(processInsId)) {
            //更新各项表单任务状态
            surveyCommonService.updateExamineTaskStatus(projectPlanDetails.getPid(), commonService.thisUserAccount(), ProjectStatusEnum.FINISH);
            planDetails.setStatus(ProcessStatusEnum.FINISH.getValue());
        } else {
            //修改监听器
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SurveyExamineTaskEvent.class.getSimpleName());
            planDetails.setStatus(ProcessStatusEnum.RUN.getValue());
            projectPlanDetailsService.updateProjectPlanDetails(planDetails); //更新父级案例信息状态为运行中
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        try {
            surveyExamineTaskService.saveExamineDataInfo(formData, projectPlanDetails);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
    }

    /**
     * 设置页面并且设置参数
     *
     * @param apply
     * @param projectPlanDetails
     * @return
     */
    private String getViewUrlAndSetParam(boolean apply, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView, String userAccount) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectCategoryId());
        String view = null;
        final String landView = apply ? "/project/stageSurvey/taskExamineLandInfoIndex" : "/project/stageSurvey/taskExamineLandInfoApproval";
        final String houseView = apply ? "/project/stageSurvey/taskExamineInfoIndex" : "/project/stageSurvey/taskExamineInfoApproval";
        SurveyExamineInfo surveyExamineInfo = surveyExamineInfoService.getExamineInfoByPlanDetailsId(projectPlanDetails.getPid());
        if (projectClassify != null && StringUtils.isNotBlank(projectClassify.getFieldName())) {
            switch (projectClassify.getFieldName()) {
                //土地 项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE:
                    view = getLandExamineView(declareRecord, surveyExamineInfo, landView);
                    if (StringUtils.isEmpty(view)) {
                        //处理为默认房产
                        view = houseView;
                    }
                    break;
                //土地简单 项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE:
                    view = getLandExamineView(declareRecord, surveyExamineInfo, landView);
                    if (StringUtils.isEmpty(view)) {
                        //处理为默认房产
                        view = houseView;
                    }
                    break;
                //房产项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE:
                    view = houseView;
                    break;
                //简单房产项目类型
                case AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE:
                    view = houseView;
                    break;
                default:
                    break;
            }
        }
        //公共需要添加的参数
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        modelAndView.addObject("surveyExamineInfo", surveyExamineInfo);
        modelAndView.addObject("declareRecord", declareRecord);
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        setIndustryExamineView(userAccount, projectPlanDetails, modelAndView);
        try {
            setIndustryExamineParam(declareRecord, surveyExamineInfo, projectPlanDetails, modelAndView);
        } catch (Exception e) {
            logger.error("参数设置异常!", e);
        }
        return view;
    }

    /**
     * 处理是否为纯土地问题
     *
     * @param declareRecord
     * @param surveyExamineInfo
     * @return
     */
    private String getLandExamineView(final DeclareRecord declareRecord, final SurveyExamineInfo surveyExamineInfo, final String landView) {
        //查勘
        if (declareRecord != null && StringUtils.isNotEmpty(declareRecord.getType())) {
            if (Objects.equal(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND_BASE_TRANSACTION).getId(), new Integer(declareRecord.getType()))) {
                return landView;
            }
            if (Objects.equal(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND_BASE_LEASE).getId(), new Integer(declareRecord.getType()))) {
                return landView;
            }
        } else {
            //交易案例
            if (Objects.equal(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND_BASE_TRANSACTION).getId(), surveyExamineInfo.getTransactionType())) {
                return landView;
            }
            if (Objects.equal(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND_BASE_LEASE).getId(), surveyExamineInfo.getTransactionType())) {
                return landView;
            }
        }
        return null;
    }

    /**
     * 设置工业和非工业视图 view
     *
     * @param userAccount
     * @param projectPlanDetails
     * @param modelAndView
     */
    private void setIndustryExamineView(String userAccount, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        Map<String, List<SurveyExamineTaskVo>> mapTaskList = surveyCommonService.getExamineTaskByUserAccount(projectPlanDetails.getPid(), userAccount);
        modelAndView.addObject("estateTaskList", mapTaskList.get(AssessExamineTaskConstant.ESTATE));
        modelAndView.addObject("buildingTaskList", mapTaskList.get(AssessExamineTaskConstant.BUILDING));
        modelAndView.addObject("unitTaskList", mapTaskList.get(AssessExamineTaskConstant.UNIT));
        modelAndView.addObject("houseTaskList", mapTaskList.get(AssessExamineTaskConstant.HOUSE));
    }

    /**
     * 具体设置工业与非工业 具体要使用的参数
     *
     * @param declareRecord
     * @param projectPlanDetails
     * @param modelAndView
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    private void setIndustryExamineParam(DeclareRecord declareRecord, SurveyExamineInfo surveyExamineInfo, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) throws Exception {
        ExamineTypeEnum examineTypeEnum = ExamineTypeEnum.EXPLORE;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE)) {
            examineTypeEnum = ExamineTypeEnum.CASE;
        }
        BasicApply basicApply = null;
        SurveyCaseStudy surveyCaseStudy = null;
        SurveySceneExplore surveySceneExplore = null;
        BasicEstate basicEstate = null;
        BasicBuilding basicBuilding = null;
        BasicUnit basicUnit = null;
        BasicHouse basicHouse = null;
        BasicEstateLandState landState = null;
        BasicHouseTrading basicHouseTrading = null;
        try {
            //申请楼栋 entity
            basicApply = basicApplyService.getBasicApplyByPlanDetailsId(projectPlanDetails.getPid());
            if (basicApply != null) {
                //案例 entity
                surveyCaseStudy = surveyCaseStudyService.getSurveyCaseStudy(projectPlanDetails.getId());
                //查勘 entity
                surveySceneExplore = surveySceneExploreService.getSurveySceneExplore(projectPlanDetails.getId());
                basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                if (basicEstate != null) {
                    landState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
                }
                basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
                basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
                basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
                if (basicHouse != null) {
                    basicHouseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
                }
            }
        } catch (Exception e) {
            //允许异常
            logger.error(e.getMessage(), e);
        }
        if (basicApply == null) {
            basicApply = new BasicApply();
            if (surveyExamineInfo != null) {
                basicApply.setType(BasicApplyTypeEnum.getEnumByKey(surveyExamineInfo.getExamineFormType()).getId());
            }
            basicApply.setProcessInsId(projectPlanDetails.getProcessInsId());
            basicApply.setPlanDetailsId(projectPlanDetails.getPid());
            basicApplyService.saveBasicApply(basicApply);
        }
        if (basicEstate == null) {
            basicEstate = new BasicEstate();
            basicEstate.setApplyId(basicApply.getId());
            if (declareRecord != null) {
                basicEstate.setProvince(declareRecord.getProvince());
                basicEstate.setCity(declareRecord.getCity());
                basicEstate.setDistrict(declareRecord.getDistrict());
            }
            basicEstateService.saveAndUpdateBasicEstate(basicEstate);
            landState = new BasicEstateLandState();
            landState.setEstateId(basicEstate.getId());
            landState.setApplyId(basicApply.getId());
            basicEstateLandStateService.saveAndUpdateBasicEstateLandState(landState);
        }
        if (basicBuilding == null) {
            basicBuilding = new BasicBuilding();
            basicBuilding.setEstateId(basicEstate.getId());
            basicBuilding.setApplyId(basicApply.getId());
            basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding);
        }
        if (BasicApplyTypeEnum.RESIDENCE.getKey().equals(surveyExamineInfo.getExamineFormType()) ||
                BasicApplyTypeEnum.INDUSTRY.getKey().equals(surveyExamineInfo.getExamineFormType())) {
            if (basicUnit == null) {
                basicUnit = new BasicUnit();
                basicUnit.setApplyId(basicApply.getId());
                basicUnit.setBuildingId(basicBuilding.getId());
                basicUnitService.saveAndUpdateBasicUnit(basicUnit);
            }
            if (basicHouse == null) {
                basicHouse = new BasicHouse();
                basicHouse.setUnitId(basicUnit.getId());
                basicHouse.setApplyId(basicApply.getId());
                basicHouseService.saveAndUpdateBasicHouse(basicHouse);
                //添加交易信息
                basicHouseTrading = new BasicHouseTrading();
                basicHouseTrading.setHouseId(basicHouse.getId());
                basicHouseTrading.setApplyId(basicApply.getId());
                if (surveyExamineInfo != null) {
                    basicHouseTrading.setTradingType(surveyExamineInfo.getTransactionType());
                }
                basicHouseTrading.setCreator(commonService.thisUserAccount());
                basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading);
                //添加完损度信息
                basicHouseService.initDemagedDegree(basicHouse);
            }
        }

        //案例
        if (examineTypeEnum.getId().equals(ExamineTypeEnum.CASE.getId())) {
            if (surveyCaseStudy == null) {
                surveyCaseStudy = new SurveyCaseStudy();
                surveyCaseStudy.setPlanDetailsId(projectPlanDetails.getId());
                surveyCaseStudy.setProjectId(projectPlanDetails.getProjectId());
                surveyCaseStudy.setDeclareId(projectPlanDetails.getDeclareRecordId());
                surveyCaseStudy.setBasicApplyId(basicApply.getId());
                surveyCaseStudy.setProcessInsId(projectPlanDetails.getProcessInsId());
                surveyCaseStudyService.saveSurveyCaseStudy(surveyCaseStudy);
            }
        }
        //查勘
        if (examineTypeEnum.getId().equals(ExamineTypeEnum.EXPLORE.getId())) {
            if (surveySceneExplore == null) {
                surveySceneExplore = new SurveySceneExplore();
                surveySceneExplore.setPlanDetailsId(projectPlanDetails.getId());
                surveySceneExplore.setProjectId(projectPlanDetails.getProjectId());
                surveySceneExplore.setDeclareId(projectPlanDetails.getDeclareRecordId());
                surveySceneExplore.setBasicApplyId(basicApply.getId());
                surveySceneExplore.setProcessInsId(projectPlanDetails.getProcessInsId());
                surveySceneExploreService.saveSurveySceneExplore(surveySceneExplore);
            }
        }
        if (surveySceneExplore != null) {
            modelAndView.addObject("surveySceneExplore", surveySceneExplore);
            modelAndView.addObject("surveySceneExploreJson", JSON.toJSONString(surveySceneExplore));
        }
        if (surveyCaseStudy != null) {
            modelAndView.addObject("surveyCaseStudy", surveyCaseStudy);
            modelAndView.addObject("surveyCaseStudyJson", JSON.toJSONString(surveyCaseStudy));
        }
        if (basicEstate != null) {
            modelAndView.addObject("basicEstate", basicEstateService.getBasicEstateVo(basicEstate));
        }
        if (landState != null) {
            modelAndView.addObject("basicEstateLandState", basicEstateLandStateService.getBasicEstateLandStateVo(landState));
        }
        if (basicBuilding != null) {
            modelAndView.addObject("basicBuilding", basicBuildingService.getBasicBuildingVo(basicBuilding));
        }
        if (basicUnit != null) {
            modelAndView.addObject("basicUnit", basicUnit);
        }
        if (basicHouse != null) {
            modelAndView.addObject("basicHouse", basicHouseService.getBasicHouseVo(basicHouse));
        }
        if (basicHouseTrading != null) {
            modelAndView.addObject("basicHouseTrading", basicHouseTradingService.getBasicHouseTradingVo(basicHouseTrading));
        }
        if (basicApply != null) {
            modelAndView.addObject("basicApply", basicApplyService.getBasicApplyVo(basicApply));
        }
    }
}
