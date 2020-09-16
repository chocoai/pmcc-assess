package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailVolumeService;
import com.copower.pmcc.assess.service.method.MdBaseLandPriceService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: HUHAO
 * @Date: 2018/9/7 10:00
 * @Description:基准地价法
 */
@RequestMapping(value = "/baseLandPrice")
@RestController
public class MdBaseLandPriceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private DataLandLevelDetailVolumeService dataLandLevelDetailVolumeService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private MdBaseLandPriceService mdBaseLandPriceService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/index", name = "基准地价法测算")
    public ModelAndView index(Integer dataId, Integer judgeObjectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketBaseLandPriceIndex");
        MdBaseLandPrice mdBaseLandPrice = null;
        if (dataId != null && dataId != 0) {
            mdBaseLandPrice = mdBaseLandPriceService.getMdBaseLandPriceDao().getMdBaseLandPrice(dataId);
        } else {
            mdBaseLandPrice = mdBaseLandPriceService.initObject(judgeObjectId);
        }
        modelAndView.addObject("master", mdBaseLandPrice);
        modelAndView.addObject("apply", "apply");
        setViewParam(judgeObjectId, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "基准地价法 详情")
    public ModelAndView detail(Integer dataId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketBaseLandPriceDetail");
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceService.getMdBaseLandPriceDao().getMdBaseLandPrice(dataId);
        modelAndView.addObject("master", mdBaseLandPrice);
        setViewParam(mdBaseLandPrice.getJudgeObjectId(), modelAndView);
        return modelAndView;
    }


    @RequestMapping(value = "/getLandIndexId", name = "获取土地指数表id", method = RequestMethod.GET)
    public HttpResult getLandIndexId(Integer judgeObjectId) {
        try {
            Integer landIndexId = null;
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());

            //找到委估对象对应的土地指数
            BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);
            List<DataHousePriceIndex> dataHouseIndexList = null;
            dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(basicEstate.getProvince(), basicEstate.getCity(), basicEstate.getDistrict(), dataDic.getId());
            if (CollectionUtils.isEmpty(dataHouseIndexList)) {
                dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(basicEstate.getProvince(), basicEstate.getCity(), null, dataDic.getId());
            }
            if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
                landIndexId = dataHouseIndexList.get(0).getId();
            }
            return HttpResult.newCorrectResult(landIndexId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }


    @RequestMapping(value = "/saveResult", name = "保存数据", method = RequestMethod.POST)
    public HttpResult saveResult(String formData) {
        try {
            MdBaseLandPrice mdBaseLandPrice = JSON.parseObject(formData, MdBaseLandPrice.class);
            mdBaseLandPriceService.saveMdBaseLandPrice(mdBaseLandPrice);
            return HttpResult.newCorrectResult(200,mdBaseLandPrice);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }


    @RequestMapping(value = "/getLevelDetailId", name = "获取土地级别id", method = RequestMethod.GET)
    public HttpResult getLevelDetailId(Integer judgeObjectId) {
        try {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
            return HttpResult.newCorrectResult(landStateByEstateId.getLandLevel());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(Integer judgeObjectId, ModelAndView modelAndView) {
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        modelAndView.addObject("judgeObject", schemeJudgeObject);
        BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());

        if (basicApply == null) {
            return;
        }
        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        if (basicEstate == null) {
            return;
        }
        BasicEstateLandCategoryInfo categoryInfo = null;
        if (basicApply.getLandCategoryId() != null) {
            categoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoById(basicApply.getLandCategoryId());
        } else {
            List<BasicEstateLandCategoryInfo> categoryInfoList = basicEstateLandCategoryInfoService.getListByEstateId(basicEstate.getId());
            categoryInfo = categoryInfoList.get(0);
            BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
            modelAndView.addObject("volumetricRate", landStateByEstateId.getPlotRatio());
        }


        if (categoryInfo != null) {
            modelAndView.addObject("landFactorTotalScore", categoryInfo.getLandFactorTotalScore());
            modelAndView.addObject("landLevelContent", categoryInfo.getLandLevelContentResult());
            modelAndView.addObject("levelDetailId", categoryInfo.getLandLevel());
            DataLandLevelDetail levelDetail = dataLandLevelDetailService.getDataLandLevelDetailById(categoryInfo.getLandLevel());
            if (levelDetail != null) {
                modelAndView.addObject("landLevelId", levelDetail.getLandLevelId());
                //基准地价
                DataLandLevelDetail hasStandardPremiumData = dataLandLevelDetailService.hasStandardPremiumParent(levelDetail.getId());
                modelAndView.addObject("standardPremium", hasStandardPremiumData.getPrice());
                //法定年限
                DataLandLevelDetail hasLegalAgeData = dataLandLevelDetailService.hasLegalAgeParent(levelDetail.getId());
                modelAndView.addObject("legalAge", hasLegalAgeData.getLegalAge());
                //容积率修正
                DataLandLevelDetail hasVolumeFractionAmendData = dataLandLevelDetailService.hasVolumeFractionAmendParent(levelDetail.getId());
                BigDecimal amendValue = dataLandLevelDetailVolumeService.getAmendByVolumetricRate(levelDetail.getVolumeRate(), hasVolumeFractionAmendData.getId());
                String volumeFractionAmend = "未配置";
                if (amendValue != null) {
                    volumeFractionAmend = String.format("%.2f", amendValue);
                }
                modelAndView.addObject("volumeFractionAmend", volumeFractionAmend);
                modelAndView.addObject("hasVolumeFractionAmendId", hasVolumeFractionAmendData.getId());
                modelAndView.addObject("volumetricRate", categoryInfo.getPlotRatio());
            }

        }

        //期日修正系数
        BigDecimal dateAmend = mdBaseLandPriceService.getBaseLandPriceDateAmend(schemeJudgeObject.getId());
        modelAndView.addObject("dateAmend", dateAmend);

    }
}
