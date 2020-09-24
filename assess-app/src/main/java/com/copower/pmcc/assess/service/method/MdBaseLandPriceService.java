package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.ArithmeticUtils;
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
import com.copower.pmcc.assess.service.data.DataHousePriceIndexService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailVolumeService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.DateUtils;
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
    @Autowired
    private DataHousePriceIndexService dataHousePriceIndexService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;

    public MdBaseLandPriceDao getMdBaseLandPriceDao() {
        return mdBaseLandPriceDao;
    }

    public List<MdBaseLandPrice> getObjectList(MdBaseLandPrice mdBaseLandPrice) {
        return mdBaseLandPriceDao.getObjectList(mdBaseLandPrice);
    }

    public MdBaseLandPrice getDataByPlanDetailsId(Integer planDetailsId) {
        MdBaseLandPrice where = new MdBaseLandPrice();
        where.setPlanDetailsId(planDetailsId);
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceDao.getMdBaseLandPrice(where);
        return mdBaseLandPrice;
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
        mdBaseLandPrice.setLandLevelContent(StringUtils.isNotEmpty(mdBaseLandPrice.getLandLevelContent()) ? mdBaseLandPrice.getLandLevelContent() : null);
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
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObjectId);
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeJudgeObject.getAreaGroupId());
        DataHousePriceIndex priceIndex = dataHousePriceIndexService.getLandPriceIndexByArea(areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict(), areaGroup.getValueTimePoint());
        if (priceIndex == null) return null;
        return dataHousePriceIndexService.getCorrectionFactor(priceIndex, areaGroup.getValueTimePoint());
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

    public void calculationNumeric(MdBaseLandPrice target, BigDecimal dateAmend, BigDecimal volumeFractionAmend) {
        if (target == null) {
            return;
        }
        getFieldObjectValueHandle(MdBaseLandPrice.Column.correctionDifference, target, dateAmend, volumeFractionAmend);//不会被递归调用所以单独运行得到数据
        getFieldObjectValueHandle(MdBaseLandPrice.Column.floorPremium, target, dateAmend, volumeFractionAmend);
        getFieldObjectValueHandle(MdBaseLandPrice.Column.parcelTotalPrice, target, dateAmend, volumeFractionAmend);//不会被递归调用所以单独运行得到数据
        getFieldObjectValueHandle(MdBaseLandPrice.Column.parcelBhouPrice, target, dateAmend, volumeFractionAmend);//不会被递归调用所以单独运行得到数据
        mdBaseLandPriceDao.editMdBaseLandPrice(target);
    }

    private String getFieldObjectValueHandle(MdBaseLandPrice.Column column, MdBaseLandPrice target, BigDecimal dateAmend, BigDecimal volumeFractionAmend) {
        final BigDecimal BHOU = new BigDecimal("666.67");
        switch (column) {
            case periodAmend: {
                //e6 ROUND((1-1/(1+E7)^E9)/(1-1/(1+E7)^E8),4))
                String rewardRate = target.getRewardRate();//E7
                BigDecimal landSurplusYear = target.getLandSurplusYear();//E9
                BigDecimal legalAge = target.getLegalAge();//E8
                if (!ArithmeticUtils.checkNotNull(rewardRate)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(landSurplusYear)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(legalAge)) {
                    return "";
                }
                double temp = 1 / Math.pow(1 + Double.valueOf(rewardRate), landSurplusYear.doubleValue());
                double temp2 = 1 / Math.pow(1 + Double.valueOf(rewardRate), legalAge.doubleValue());
                double result = ArithmeticUtils.div(ArithmeticUtils.sub(1, temp), ArithmeticUtils.sub(1, temp2));
                String round = ArithmeticUtils.round(ArithmeticUtils.createBigDecimal(result), 4);
                target.setPeriodAmend(ArithmeticUtils.createBigDecimal(round));
                return round;
            }
            //ROUND(E4*E5*E6*E10*(1+E11)+E12,2))
            case parcelPrice: {//e13
                BigDecimal standardPremium = target.getStandardPremium();//e4
                BigDecimal e5 = dateAmend; //e5
                BigDecimal e10 = volumeFractionAmend;
                String periodAmend = getFieldObjectValueHandle(MdBaseLandPrice.Column.periodAmend, target, dateAmend, volumeFractionAmend);//e6
                BigDecimal areaAndSeveralAmend = target.getAreaAndSeveralAmend();//e11
                BigDecimal developCorrect = target.getDevelopCorrect();//e12
                if (!ArithmeticUtils.checkNotNull(new BigDecimal[]{standardPremium, e5, e10, areaAndSeveralAmend})) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(developCorrect)) {
                    developCorrect = ArithmeticUtils.createBigDecimal(0);
                }
                BigDecimal multiply = standardPremium.multiply(e5).multiply(ArithmeticUtils.createBigDecimal(periodAmend)).multiply(e10).multiply(ArithmeticUtils.createBigDecimal(1 + areaAndSeveralAmend.doubleValue()));
                BigDecimal result = ArithmeticUtils.addModel(multiply, developCorrect, 2);
                String value = ArithmeticUtils.getBigDecimalString(result);
                target.setParcelPrice(result);
                return value;
            }
            case parcelBhouPrice: {//e14
                //E13*667/10000
                String parcelPrice = getFieldObjectValueHandle(MdBaseLandPrice.Column.parcelPrice, target, dateAmend, volumeFractionAmend); //e13
                if (!ArithmeticUtils.checkNotNull(parcelPrice)) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.createBigDecimal(parcelPrice).multiply(BHOU);
                BigDecimal decimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                String value = ArithmeticUtils.round(decimal, 2);
                target.setParcelBhouPrice(ArithmeticUtils.createBigDecimal(value));
                return value;
            }
            case parcelTotalPrice: {//e16
                //E13*E15/10000
                String parcelPrice = getFieldObjectValueHandle(MdBaseLandPrice.Column.parcelPrice, target, dateAmend, volumeFractionAmend); //e13
                BigDecimal evaluationArea = target.getEvaluationArea();//e15
                if (!ArithmeticUtils.checkNotNull(parcelPrice)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(evaluationArea)) {
                    return "";
                }
                BigDecimal bigDecimal = ArithmeticUtils.createBigDecimal(parcelPrice).multiply(evaluationArea);
                BigDecimal decimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                String value = ArithmeticUtils.round(decimal, 2);
                target.setParcelTotalPrice(ArithmeticUtils.createBigDecimal(value));
                return value;
            }
            case floorPremium: {//e17
                //ROUND(E13/G17,2)
                String parcelPrice = getFieldObjectValueHandle(MdBaseLandPrice.Column.parcelPrice, target, dateAmend, volumeFractionAmend); //e13
                BigDecimal volumetricRate = target.getVolumetricRate();
                if (!ArithmeticUtils.checkNotNull(parcelPrice)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(volumetricRate)) {
                    return "";
                }
                BigDecimal decimal = ArithmeticUtils.divide(ArithmeticUtils.createBigDecimal(parcelPrice), volumetricRate, 2);
                target.setFloorPremium(decimal);
                String value = ArithmeticUtils.getBigDecimalString(decimal);
                return value;
            }
            case correctionDifference: {//f19
                //E13/E4-1
                BigDecimal standardPremium = target.getStandardPremium();//e4
                String parcelPrice = getFieldObjectValueHandle(MdBaseLandPrice.Column.parcelPrice, target, dateAmend, volumeFractionAmend); //e13
                if (!ArithmeticUtils.checkNotNull(parcelPrice)) {
                    return "";
                }
                if (!ArithmeticUtils.checkNotNull(standardPremium)) {
                    return "";
                }
                BigDecimal decimal = ArithmeticUtils.divide(ArithmeticUtils.createBigDecimal(parcelPrice), standardPremium, 10);
                String sub = ArithmeticUtils.sub(decimal.toString(), "1", 10);
                String mul = ArithmeticUtils.mul(sub, "100", 2);
                String value = String.format("%s%s", mul, "%");
                target.setCorrectionDifference(value);
                return value;
            }
            default:
                return "";
        }
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    public void setViewParam(Integer judgeObjectId, MdBaseLandPrice mdBaseLandPrice, ModelAndView modelAndView) {
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
            modelAndView.addObject("basicEstateLandCategoryInfo",categoryInfo);
            DataLandLevelDetail levelDetail = dataLandLevelDetailService.getDataLandLevelDetailById(categoryInfo.getLandLevel());
            if (levelDetail != null) {
                //基准地价
                DataLandLevelDetail hasStandardPremiumData = dataLandLevelDetailService.hasStandardPremiumParent(levelDetail.getId());
                modelAndView.addObject("standardPremium", hasStandardPremiumData.getPrice());
                //法定年限
                DataLandLevelDetail hasLegalAgeData = dataLandLevelDetailService.hasLegalAgeParent(levelDetail.getId());
                modelAndView.addObject("legalAge", hasLegalAgeData.getLegalAge());
                //容积率修正
                DataLandLevelDetail parentLandLevel = dataLandLevelDetailService.hasVolumeFractionAmendParent(levelDetail.getId());
                BigDecimal amendValue = dataLandLevelDetailVolumeService.getAmendByVolumetricRate(schemeJudgeObject.getSetPlotRatio(), parentLandLevel.getId());
                if (amendValue == null) {//当为null的时候取父级的
                    amendValue = dataLandLevelDetailVolumeService.getAmendByVolumetricRate(schemeJudgeObject.getSetPlotRatio(), parentLandLevel.getId());
                }
                modelAndView.addObject("volumeFractionAmend", amendValue);
                modelAndView.addObject("hasVolumeFractionAmendId", parentLandLevel.getId());
                modelAndView.addObject("volumetricRate", schemeJudgeObject.getSetPlotRatio());
            }
        }
        //期日修正系数
        BigDecimal dateAmend = mdBaseLandPrice.getDateAmend() == null ? getBaseLandPriceDateAmend(schemeJudgeObject.getId()) : mdBaseLandPrice.getDateAmend();
        modelAndView.addObject("dateAmend", dateAmend);

    }
}
