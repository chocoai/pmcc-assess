package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSell;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:06
 * @Description:
 */
public class BasicHouseTradingSellVo extends BasicHouseTradingSell {
    private String tradingType;
    private String instalmentPeriodStartName;

    private String instalmentPeriodEndName;

    private String instalmentInterest;

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    public String getInstalmentPeriodStartName() {
        return instalmentPeriodStartName;
    }

    public void setInstalmentPeriodStartName(String instalmentPeriodStartName) {
        this.instalmentPeriodStartName = instalmentPeriodStartName;
    }

    public String getInstalmentPeriodEndName() {
        return instalmentPeriodEndName;
    }

    public void setInstalmentPeriodEndName(String instalmentPeriodEndName) {
        this.instalmentPeriodEndName = instalmentPeriodEndName;
    }

    @Override
    public String getInstalmentInterest() {
        return instalmentInterest;
    }

    @Override
    public void setInstalmentInterest(String instalmentInterest) {
        this.instalmentInterest = instalmentInterest;
    }
}
