package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLease;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:07
 * @Description:
 */
public class BasicHouseTradingLeaseVo extends BasicHouseTradingLease {
    private String tradingType;
    private String rentPaymentTimeStartName;

    private String rentPaymentTimeEndName;

    private String rentGrowthRate;

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    public String getRentPaymentTimeStartName() {
        return rentPaymentTimeStartName;
    }

    public void setRentPaymentTimeStartName(String rentPaymentTimeStartName) {
        this.rentPaymentTimeStartName = rentPaymentTimeStartName;
    }

    public String getRentPaymentTimeEndName() {
        return rentPaymentTimeEndName;
    }

    public void setRentPaymentTimeEndName(String rentPaymentTimeEndName) {
        this.rentPaymentTimeEndName = rentPaymentTimeEndName;
    }

    @Override
    public String getRentGrowthRate() {
        return rentGrowthRate;
    }

    @Override
    public void setRentGrowthRate(String rentGrowthRate) {
        this.rentGrowthRate = rentGrowthRate;
    }
}
