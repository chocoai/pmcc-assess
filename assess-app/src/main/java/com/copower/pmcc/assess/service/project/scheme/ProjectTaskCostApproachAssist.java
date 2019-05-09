package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandApproximationMethodSettingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataLandApproximationMethodSettingVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandApproximationMethodSettingService;
import com.copower.pmcc.assess.service.method.MdCostApproachService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "成本逼近法成果")
public class ProjectTaskCostApproachAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private MdCostApproachService mdCostApproachService;
    @Autowired
    private DataLandApproximationMethodSettingDao dataLandApproximationMethodSettingDao;
    @Autowired
    private DataLandApproximationMethodSettingService dataLandApproximationMethodSettingService;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDao dataAllocationCorrectionCoefficientVolumeRatioDao;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailDao dataAllocationCorrectionCoefficientVolumeRatioDetailDao;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachIndex", "", 0, "0", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachApproval", processInsId, boxId, taskId, agentUserAccount);
        MdCostApproach data = mdCostApproachService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("master", data);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachIndex", processInsId, boxId, taskId, agentUserAccount);
        MdCostApproach data = mdCostApproachService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("master", data);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskCostApproachApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        mdCostApproachService.applyCommit(formData, processInsId);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        mdCostApproachService.applyCommit(formData, processInsId);
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        modelAndView.addObject("number", schemeJudgeObject.getNumber());
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
        BasicEstate basicEstate = null;
        try {
            basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            if (basicEstate == null) {
                return;
            }
        } catch (Exception e) {
            logger.error(String.format("没有获取到数据 ==> %s", e.getMessage()));
        }
        List<DataLandApproximationMethodSetting> dataLandApproximationMethodSettingList = Lists.newArrayList();
        List<DataLandApproximationMethodSettingVo> vosList = Lists.newArrayList();
        dataLandApproximationMethodSettingList = dataLandApproximationMethodSettingDao.getDataLandApproximationMethodSettingList(declareRecord.getProvince(), declareRecord.getCity(), declareRecord.getDistrict());
        if (CollectionUtils.isEmpty(dataLandApproximationMethodSettingList)) {
            dataLandApproximationMethodSettingList = dataLandApproximationMethodSettingDao.getDataLandApproximationMethodSettingList(declareRecord.getProvince(), declareRecord.getCity(), null);
        }
        if (CollectionUtils.isNotEmpty(dataLandApproximationMethodSettingList)) {
            dataLandApproximationMethodSettingList.forEach(oo -> vosList.add(dataLandApproximationMethodSettingService.getDataLandApproximationMethodSettingVo(oo)));
            modelAndView.addObject("vosList", vosList);
        }
        //土地剩余年限
        BigDecimal landRemainingYear = schemeJudgeObject.getLandRemainingYear();
        modelAndView.addObject("landRemainingYear", landRemainingYear);
        //宗地面积(取证载面积，待确认)
        BigDecimal floorArea = schemeJudgeObject.getFloorArea();
        modelAndView.addObject("floorArea", floorArea);
        //容积率
        String volumetricRate = basicEstate.getVolumetricRate();
        modelAndView.addObject("volumetricRate", volumetricRate);
        //根据容积率找到配置中对应的容积率修正
        List<DataAllocationCorrectionCoefficientVolumeRatio> coefficientVolumeRatioList = Lists.newArrayList();
        coefficientVolumeRatioList = dataAllocationCorrectionCoefficientVolumeRatioDao.getDataAllocationCorrectionCoefficientVolumeRatioList(declareRecord.getProvince(), declareRecord.getCity(), declareRecord.getDistrict());
        if (CollectionUtils.isEmpty(coefficientVolumeRatioList)) {
            coefficientVolumeRatioList = dataAllocationCorrectionCoefficientVolumeRatioDao.getDataAllocationCorrectionCoefficientVolumeRatioList(declareRecord.getProvince(), declareRecord.getCity(), null);
        }
        if (CollectionUtils.isNotEmpty(coefficientVolumeRatioList)) {
            Integer masterId = coefficientVolumeRatioList.get(0).getId();
            DataAllocationCorrectionCoefficientVolumeRatioDetail coefficientVolumeRatioDetail = new DataAllocationCorrectionCoefficientVolumeRatioDetail();
            coefficientVolumeRatioDetail.setAllocationVolumeRatioId(masterId);
            List<DataAllocationCorrectionCoefficientVolumeRatioDetail> detailList = dataAllocationCorrectionCoefficientVolumeRatioDetailDao.getDataAllocationCorrectionCoefficientVolumeRatioDetailList(coefficientVolumeRatioDetail);
            for (DataAllocationCorrectionCoefficientVolumeRatioDetail detailItem : detailList) {
                if (detailItem.getPlotRatio().compareTo(new BigDecimal(volumetricRate)) == 0) {
                    modelAndView.addObject("volumeFractionAmend", detailItem.getCorrectionFactor());
                    break;
                }
            }
        }
        //宗地个别因素修正(待确认)
        //代征地比例(待确认)
    }
}
