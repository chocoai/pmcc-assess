package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.method.MdBaseLandPriceService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "基准地价法成果")
public class ProjectTaskBaseLandPriceAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private MdBaseLandPriceService mdBaseLandPriceService;
    @Autowired
    private DataLandLevelDetailDao dataLandLevelDetailDao;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private DataHousePriceIndexDetailDao dataHousePriceIndexDetailDao;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDao dataAllocationCorrectionCoefficientVolumeRatioDao;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailDao dataAllocationCorrectionCoefficientVolumeRatioDetailDao;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceIndex", "", 0, "0", "");
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceApproval", processInsId, boxId, taskId, agentUserAccount);
        MdBaseLandPrice data = mdBaseLandPriceService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("master", data);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceIndex", processInsId, boxId, taskId, agentUserAccount);
        MdBaseLandPrice data = mdBaseLandPriceService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("master", data);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskBaseLandPriceApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        mdBaseLandPriceService.applyCommit(formData, processInsId);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        mdBaseLandPriceService.applyCommit(formData, processInsId);

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
        //基准地价
        BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        DataLandLevelDetail dataLandLevelDetailById = dataLandLevelDetailDao.getDataLandLevelDetailById(landStateByEstateId.getLandLevel());
        modelAndView.addObject("standardPremium", dataLandLevelDetailById.getPrice());

        //期日修正系数
        ProjectInfo projectInfoById = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        //评估基准日
        Date valuationDate = projectInfoById.getValuationDate();
        //找到评估基准日对应的土地因素
        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);
        List<DataHousePriceIndex> dataHouseIndexList = Lists.newArrayList();
        dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), declareRecord.getDistrict(), dataDic.getId());
        if (CollectionUtils.isEmpty(dataHouseIndexList)) {
            dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), null, dataDic.getId());
        }
        if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
            Integer masterId = dataHouseIndexList.get(0).getId();
            DataHousePriceIndexDetail dataHousePriceIndexDetail = new DataHousePriceIndexDetail();
            dataHousePriceIndexDetail.setHousePriceId(masterId);
            List<DataHousePriceIndexDetail> detailList = dataHousePriceIndexDetailDao.getDataHousePriceIndexDetailList(dataHousePriceIndexDetail);
            //最早月份的指数
            DataHousePriceIndexDetail firstIndex = detailList.get(0);
            if (CollectionUtils.isNotEmpty(detailList)) {
                for (DataHousePriceIndexDetail item : detailList) {
                    if (item.getStartDate().compareTo(valuationDate) != 1 && item.getEndDate().compareTo(valuationDate) != -1) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        modelAndView.addObject("DateAmend", df.format((float) Float.parseFloat(item.getIndexNumber().toString()) / (float) Float.parseFloat(firstIndex.getIndexNumber().toString())));
                        break;
                    }
                }
            }
        }

        //法定年限（证载用途后跟的年份）
        String landCertUse = declareRecord.getLandCertUse();
        Pattern pattern = Pattern.compile("(?<=\\()\\S+(?=\\))");
        Matcher matcher = pattern.matcher(landCertUse);
        while (matcher.find()) {
            modelAndView.addObject("legalAge", matcher.group());
        }

        //剩余使用年限（待确认）
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(schemeJudgeObject.getAreaGroupId());
        if (declareRecord.getLandUseEndDate() != null && areaGroup.getValueTimePoint() != null) {
            BigDecimal landSurplusYear = new BigDecimal(DateUtils.diffDate(declareRecord.getLandUseEndDate(), areaGroup.getValueTimePoint()));
            landSurplusYear = landSurplusYear.divide(new BigDecimal(DateUtils.DAYS_PER_YEAR), 2, BigDecimal.ROUND_HALF_UP);
            modelAndView.addObject("landSurplusYear", landSurplusYear);
        }

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

        //宗地面积(取证载面积，待确认)
        BigDecimal floorArea = schemeJudgeObject.getFloorArea();
        modelAndView.addObject("floorArea", floorArea);

        //区域及个别修正系数(待确认)
    }


}
