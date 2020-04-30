package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataLoanBenchmarkInterestRate {
    private Integer id;

    private Date adjustTime;

    private BigDecimal lessThanSixMonthLoanTermTax;

    private BigDecimal betweenSixMonthAndOneYearLoanTermTax;

    private BigDecimal betweenOneAndThreeYearLoanTermTax;

    private BigDecimal betweenThreeAndFiveYearLoanTermTax;

    private BigDecimal moreThanFiveYearLoanTermTax;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(Date adjustTime) {
        this.adjustTime = adjustTime;
    }

    public BigDecimal getLessThanSixMonthLoanTermTax() {
        return lessThanSixMonthLoanTermTax;
    }

    public void setLessThanSixMonthLoanTermTax(BigDecimal lessThanSixMonthLoanTermTax) {
        this.lessThanSixMonthLoanTermTax = lessThanSixMonthLoanTermTax;
    }

    public BigDecimal getBetweenSixMonthAndOneYearLoanTermTax() {
        return betweenSixMonthAndOneYearLoanTermTax;
    }

    public void setBetweenSixMonthAndOneYearLoanTermTax(BigDecimal betweenSixMonthAndOneYearLoanTermTax) {
        this.betweenSixMonthAndOneYearLoanTermTax = betweenSixMonthAndOneYearLoanTermTax;
    }

    public BigDecimal getBetweenOneAndThreeYearLoanTermTax() {
        return betweenOneAndThreeYearLoanTermTax;
    }

    public void setBetweenOneAndThreeYearLoanTermTax(BigDecimal betweenOneAndThreeYearLoanTermTax) {
        this.betweenOneAndThreeYearLoanTermTax = betweenOneAndThreeYearLoanTermTax;
    }

    public BigDecimal getBetweenThreeAndFiveYearLoanTermTax() {
        return betweenThreeAndFiveYearLoanTermTax;
    }

    public void setBetweenThreeAndFiveYearLoanTermTax(BigDecimal betweenThreeAndFiveYearLoanTermTax) {
        this.betweenThreeAndFiveYearLoanTermTax = betweenThreeAndFiveYearLoanTermTax;
    }

    public BigDecimal getMoreThanFiveYearLoanTermTax() {
        return moreThanFiveYearLoanTermTax;
    }

    public void setMoreThanFiveYearLoanTermTax(BigDecimal moreThanFiveYearLoanTermTax) {
        this.moreThanFiveYearLoanTermTax = moreThanFiveYearLoanTermTax;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}