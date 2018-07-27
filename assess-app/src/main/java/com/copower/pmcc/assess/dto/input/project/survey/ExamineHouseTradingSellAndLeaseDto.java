package com.copower.pmcc.assess.dto.input.project.survey;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/7/27 18:34
 * @Description:
 */
public class ExamineHouseTradingSellAndLeaseDto {
    private String tradingType;
    private Integer id;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date instalmentPeriodStart;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date instalmentPeriodEnd;

    private String instalmentInterest;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date rentPaymentTimeStart;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date rentPaymentTimeEnd;


    private Integer tradingId;


    private String rentGrowthRate;

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstalmentPeriodStart() {
        return instalmentPeriodStart;
    }

    public void setInstalmentPeriodStart(Date instalmentPeriodStart) {
        this.instalmentPeriodStart = instalmentPeriodStart;
    }

    public Date getInstalmentPeriodEnd() {
        return instalmentPeriodEnd;
    }

    public void setInstalmentPeriodEnd(Date instalmentPeriodEnd) {
        this.instalmentPeriodEnd = instalmentPeriodEnd;
    }

    public String getInstalmentInterest() {
        return instalmentInterest;
    }

    public void setInstalmentInterest(String instalmentInterest) {
        this.instalmentInterest = instalmentInterest;
    }

    public Date getRentPaymentTimeStart() {
        return rentPaymentTimeStart;
    }

    public void setRentPaymentTimeStart(Date rentPaymentTimeStart) {
        this.rentPaymentTimeStart = rentPaymentTimeStart;
    }

    public Date getRentPaymentTimeEnd() {
        return rentPaymentTimeEnd;
    }

    public void setRentPaymentTimeEnd(Date rentPaymentTimeEnd) {
        this.rentPaymentTimeEnd = rentPaymentTimeEnd;
    }

    public Integer getTradingId() {
        return tradingId;
    }

    public void setTradingId(Integer tradingId) {
        this.tradingId = tradingId;
    }

    public String getRentGrowthRate() {
        return rentGrowthRate;
    }

    public void setRentGrowthRate(String rentGrowthRate) {
        this.rentGrowthRate = rentGrowthRate;
    }
}
