package com.copower.pmcc.assess.dto.output.project.survey;



/**
 * @Auther: zch
 * @Date: 2018/7/30 14:26
 * @Description:
 */
public class ExamineHouseTradingSellAndLeaseVo {
    private Integer id;
    private String tradingType;

    private Integer declareId;
    private Integer examineType;

    private String instalmentPeriodStartName;
    private String instalmentPeriodEndName;
    private String instalmentInterest;

    private String rentPaymentTimeStartName;
    private String rentPaymentTimeEndName;
    private String rentGrowthRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
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

    public String getInstalmentInterest() {
        return instalmentInterest;
    }

    public void setInstalmentInterest(String instalmentInterest) {
        this.instalmentInterest = instalmentInterest;
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

    public String getRentGrowthRate() {
        return rentGrowthRate;
    }

    public void setRentGrowthRate(String rentGrowthRate) {
        this.rentGrowthRate = rentGrowthRate;
    }
}
