package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdBaseLandPriceDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private DataHousePriceIndexDetailDao dataHousePriceIndexDetailDao;

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
        if (mdBaseLandPrice.getId() != null && mdBaseLandPrice.getId().intValue() > 0) {
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
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
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

}
