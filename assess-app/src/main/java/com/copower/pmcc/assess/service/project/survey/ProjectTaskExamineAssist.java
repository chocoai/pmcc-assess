package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.event.project.SurveyExamineTaskEvent;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
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
    private DataExamineTaskService dataExamineTaskService;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineInfoIndex", "", 0, "0", "");
        setViewParam(commonService.thisUserAccount(), projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineInfoApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails.getExecuteUserAccount(), projectPlanDetails, modelAndView);
        return modelAndView;
    }


    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineInfoIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails.getExecuteUserAccount(), projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageSurvey/taskExamineInfoApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails.getExecuteUserAccount(), projectPlanDetails, modelAndView);
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
            //更新父级案例信息状态为运行中
            projectPlanDetailsService.updateProjectPlanDetails(planDetails);
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
            logger.error("", e1);
        }
    }

    /**
     * 设置视图参数
     *
     * @param userAccount
     * @param projectPlanDetails
     * @param modelAndView
     */
    private void setViewParam(String userAccount, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        SurveyExamineInfo surveyExamineInfo = surveyExamineInfoService.getExploreByPlanDetailsId(projectPlanDetails.getPid());
        modelAndView.addObject("surveyExamineInfo", surveyExamineInfo);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        modelAndView.addObject("declareRecord", declareRecord);
        try {
            setSurveyCaseStudyOrSurveySceneExploreValue(declareRecord, projectPlanDetails, modelAndView);
        } catch (Exception e1) {
            logger.error("设置数据异常!", e1);
        }
        Map<String, List<SurveyExamineTaskVo>> mapTaskList = surveyCommonService.getExamineTaskByUserAccount(projectPlanDetails.getPid(), userAccount);
        modelAndView.addObject("estateTaskList", mapTaskList.get(AssessExamineTaskConstant.ESTATE));
        modelAndView.addObject("buildingTaskList", mapTaskList.get(AssessExamineTaskConstant.BUILDING));
        modelAndView.addObject("unitTaskList", mapTaskList.get(AssessExamineTaskConstant.UNIT));
        modelAndView.addObject("houseTaskList", mapTaskList.get(AssessExamineTaskConstant.HOUSE));
        this.industry(projectPlanDetails, modelAndView);
    }

    /**
     * 区分是否是非工业
     *
     * @param projectPlanDetails
     * @param modelAndView
     */
    private void industry(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        if (projectPlanDetails == null) {
            return;
        }
        SurveyExamineTask query = new SurveyExamineTask();
        query.setPlanDetailsId(projectPlanDetails.getPid());
        //工业与非工业楼盘是不同的因此查询出楼盘的DataExamineTask可以区分是否是工业与非工业
        query.setName("楼盘");
        List<SurveyExamineTask> surveyExamineTaskList = surveyExamineTaskService.getSurveyExamineTaskList(query);
        if (!ObjectUtils.isEmpty(surveyExamineTaskList)) {
            SurveyExamineTask surveyExamineTask = surveyExamineTaskList.get(0);
            Integer id = surveyExamineTask.getDataTaskId();
            DataExamineTask dataExamineTask = dataExamineTaskService.getCacheDataExamineTaskById(id);
            if (dataExamineTask != null) {
                DataExamineTask examineTask = dataExamineTaskService.getCacheDataExamineTaskById(dataExamineTask.getPid());
                if (examineTask != null) {
                    modelAndView.addObject("dataExamineTask", examineTask);
                }
            }
        }
    }

    private void setSurveyCaseStudyOrSurveySceneExploreValue(DeclareRecord declareRecord, ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) throws Exception {
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
            SurveyExamineInfo surveyExamineInfo = surveyExamineInfoService.getExploreByPlanDetailsId(projectPlanDetails.getPid());
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
            basicHouseService.addHouseAndTrading(null, basicApply.getId());
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
            basicHouseService.addHouseAndTrading(null, basicApply.getId());
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
            modelAndView.addObject("basicApply", basicApply);
        }
    }
}
