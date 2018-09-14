package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingSell;

/**
 * @Auther: zch
 * @Date: 2018/9/13 18:00
 * @Description:
 */
public class CaseHouseTradingSellVo extends CaseHouseTradingSell {
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
