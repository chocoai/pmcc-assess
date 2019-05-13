package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.method.MdBaseLandPriceDao;
import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPrice;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 基准地价系数修正法
 *
 * @author noOne
 */

@Service
public class MdBaseLandPriceService {
    @Autowired
    private MdBaseLandPriceDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public List<MdBaseLandPrice> getObjectList(MdBaseLandPrice mdBaseLandPrice) {
        return schemeReimbursementDao.getObjectList(mdBaseLandPrice);
    }


    public MdBaseLandPrice getDataByProcessInsId(String processInsId) {
        MdBaseLandPrice where = new MdBaseLandPrice();
        where.setProcessInsId(processInsId);
        return schemeReimbursementDao.getMdBaseLandPrice(where);
    }

    public void applyCommit(String formData, String processInsId) {
        MdBaseLandPrice mdBaseLandPrice = JSON.parseObject(formData, MdBaseLandPrice.class);
        mdBaseLandPrice.setProcessInsId(processInsId);
        this.saveMdBaseLandPrice(mdBaseLandPrice);
    }


    public void saveMdBaseLandPrice(MdBaseLandPrice mdBaseLandPrice) {
        if (mdBaseLandPrice.getId() != null && mdBaseLandPrice.getId().intValue() > 0) {
            schemeReimbursementDao.editMdBaseLandPrice(mdBaseLandPrice);
        } else {
            mdBaseLandPrice.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementDao.addMdBaseLandPrice(mdBaseLandPrice);
        }
    }

    public MdBaseLandPrice getSingleObject(Integer id) {
        return schemeReimbursementDao.getMdBaseLandPrice(id);
    }


    /**
     * 获取年期修正系数
     *
     * @param rewardRate  报酬率
     * @param legalAge    法定年限
     * @param surplusYear 剩余年限
     * @return
     */
    public Double getPeriodAmend(String rewardRate, String legalAge, String surplusYear) {
        Double legalAgeValue = Double.valueOf(legalAge);
        Double surplusYearValue = Double.valueOf(surplusYear);
        Double rewardRateValue;
        //rewardRate是否是百分数
        if (rewardRate.substring(rewardRate.length() - 1, rewardRate.length()).equals("%")) {
            Double rewardRateTemp = Double.valueOf(rewardRate.substring(0, rewardRate.length() - 1));
            rewardRateValue = rewardRateTemp / 100;
        } else {
            rewardRateValue = Double.valueOf(rewardRate);
        }
        double temp1 = 1 / Math.pow(1 + rewardRateValue, surplusYearValue);
        double temp2 = 1 / Math.pow(1 + rewardRateValue, legalAgeValue);
        return 1 - temp1 / 1 - temp2;
    }

}
