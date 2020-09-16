package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdBaseLandPriceDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailVolumeService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 基准地价系数修正法
 *
 * @author noOne
 */

@Service
public class MdBaseLandPriceService {
    @Autowired
    private MdBaseLandPriceDao mdBaseLandPriceDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private DataHousePriceIndexDetailDao dataHousePriceIndexDetailDao;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private DataLandLevelDetailVolumeService dataLandLevelDetailVolumeService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;

    public MdBaseLandPriceDao getMdBaseLandPriceDao(){
        return mdBaseLandPriceDao;
    }

    public List<MdBaseLandPrice> getObjectList(MdBaseLandPrice mdBaseLandPrice) {
        return mdBaseLandPriceDao.getObjectList(mdBaseLandPrice);
    }


    public MdBaseLandPrice getDataByPlanDetailsId(Integer planDetailsId) {
        MdBaseLandPrice where = new MdBaseLandPrice();
        where.setPlanDetailsId(planDetailsId);
        return mdBaseLandPriceDao.getMdBaseLandPrice(where);
    }


    public MdBaseLandPrice getDataByProcessInsId(String processInsId) {
        MdBaseLandPrice where = new MdBaseLandPrice();
        where.setProcessInsId(processInsId);
        return mdBaseLandPriceDao.getMdBaseLandPrice(where);
    }

    public void applyCommit(String formData, String processInsId) {
        MdBaseLandPrice mdBaseLandPrice = JSON.parseObject(formData, MdBaseLandPrice.class);
        mdBaseLandPrice.setProcessInsId(processInsId);
        this.saveMdBaseLandPrice(mdBaseLandPrice);
    }


    public void saveMdBaseLandPrice(MdBaseLandPrice mdBaseLandPrice) {
        mdBaseLandPrice.setLandLevelContent(StringUtils.isNotEmpty(mdBaseLandPrice.getLandLevelContent())?mdBaseLandPrice.getLandLevelContent():null);
        if (mdBaseLandPrice.getId() != null && mdBaseLandPrice.getId() > 0) {
            mdBaseLandPriceDao.editMdBaseLandPrice(mdBaseLandPrice);
        } else {
            mdBaseLandPrice.setCreator(processControllerComponent.getThisUser());
            mdBaseLandPriceDao.addMdBaseLandPrice(mdBaseLandPrice);
        }
    }

    public MdBaseLandPrice getSingleObject(Integer id) {
        return mdBaseLandPriceDao.getMdBaseLandPrice(id);
    }


    /**
     * 获取年期修正系数
     *
     * @param rewardRate  报酬率
     * @param legalAge    法定年限
     * @param surplusYear 剩余年限
     * @return
     */
    public BigDecimal getPeriodAmend(BigDecimal rewardRate, BigDecimal legalAge, BigDecimal surplusYear) {
        if (rewardRate == null || legalAge == null || surplusYear == null) return null;

        BigDecimal pow1 = new BigDecimal(Math.pow(rewardRate.add(new BigDecimal("1")).doubleValue(), surplusYear.doubleValue()));
        BigDecimal temp1 = new BigDecimal("1").subtract(new BigDecimal("1").divide(pow1, 4, BigDecimal.ROUND_HALF_UP));

        BigDecimal pow2 = new BigDecimal(Math.pow(rewardRate.add(new BigDecimal("1")).doubleValue(), legalAge.doubleValue()));
        BigDecimal temp2 = new BigDecimal("1").subtract(new BigDecimal("1").divide(pow2, 4, BigDecimal.ROUND_HALF_UP));

        return temp1.divide(temp2, 4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取 基准地价期日修正
     */
    public BigDecimal getBaseLandPriceDateAmend(Integer schemeJudgeObjectId) {
        StringBuilder s = new StringBuilder();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObjectId);
        ProjectInfo projectInfoById = projectInfoService.getProjectInfoById(schemeJudgeObject.getProjectId());
        BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());

        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());

        //评估基准日
        Date valuationDate = projectInfoById.getValuationDate();
        //找到评估基准日对应的土地因素
        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);
        List<DataHousePriceIndex> dataHouseIndexList = Lists.newArrayList();
        dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(basicEstate.getProvince(), basicEstate.getCity(), basicEstate.getDistrict(), dataDic.getId());
        if (CollectionUtils.isEmpty(dataHouseIndexList)) {
            dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(basicEstate.getProvince(), basicEstate.getCity(), null, dataDic.getId());
        }
        if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
            DataHousePriceIndex housePriceIndex = dataHouseIndexList.get(0);
            DataHousePriceIndexDetail dataHousePriceIndexDetail = new DataHousePriceIndexDetail();
            dataHousePriceIndexDetail.setHousePriceId(housePriceIndex.getId());
            List<DataHousePriceIndexDetail> detailList = dataHousePriceIndexDetailDao.getDataHousePriceIndexDetailList(dataHousePriceIndexDetail);

            if (CollectionUtils.isNotEmpty(detailList)) {
                //基期
                Date basePeriod = housePriceIndex.getBasePeriod();
                BigDecimal basePeriodIndex = null;
                if(basePeriod!=null){
                    for (DataHousePriceIndexDetail item : detailList) {
                        //基期对应的指数
                        if (item.getStartDate().compareTo(basePeriod) != 1 && item.getStartDate().compareTo(basePeriod) != -1) {
                            basePeriodIndex = item.getIndexNumber();
                        }
                    }
                }

                if(basePeriodIndex!=null){
                    //找到自身对应指数：基期指数
                    for (DataHousePriceIndexDetail item : detailList) {
                        if (item.getStartDate().compareTo(valuationDate) != 1 && item.getEndDate().compareTo(valuationDate) != -1) {
                            return item.getIndexNumber().divide(basePeriodIndex, 4, BigDecimal.ROUND_HALF_UP);
                        }
                    }
                }

            }
        }
        return null;
    }

    public MdBaseLandPrice initObject(Integer judgeObjectId) {
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        MdBaseLandPrice obj = new MdBaseLandPrice();
        obj.setName(schemeJudgeObject.getName());
        obj.setCreator(processControllerComponent.getThisUser());
        mdBaseLandPriceDao.addMdBaseLandPrice(obj);
        obj.setJudgeObjectId(judgeObjectId);
        return obj;
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    public void setViewParam(Integer judgeObjectId, ModelAndView modelAndView) {
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
                DataLandLevelDetail parentLandLevel = dataLandLevelDetailService.hasVolumeFractionAmendParent(levelDetail.getId());
                BigDecimal amendValue = dataLandLevelDetailVolumeService.getAmendByVolumetricRate(levelDetail.getVolumeRate(), parentLandLevel.getId());
                if (amendValue == null){
                    //当为null的时候取父级的
                    amendValue = dataLandLevelDetailVolumeService.getAmendByVolumetricRate(parentLandLevel.getVolumeRate(), parentLandLevel.getId());
                }
                String volumeFractionAmend = "未配置";
                if (amendValue != null) {
                    volumeFractionAmend = String.format("%.2f", amendValue);
                }
                modelAndView.addObject("volumeFractionAmend", volumeFractionAmend);
                modelAndView.addObject("hasVolumeFractionAmendId", parentLandLevel.getId());
                modelAndView.addObject("volumetricRate", categoryInfo.getPlotRatio());
            }

        }
        //期日修正系数
        BigDecimal dateAmend = getBaseLandPriceDateAmend(schemeJudgeObject.getId());
        modelAndView.addObject("dateAmend", dateAmend);

    }
}
