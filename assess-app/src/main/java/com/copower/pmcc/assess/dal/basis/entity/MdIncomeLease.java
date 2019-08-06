package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeLease {
    private Integer id;

    private Integer incomeId;

    private Integer sectionId;

    private Integer mcId;

    private String rentalIncomeRemark;

    private BigDecimal rentalIncome;

    private BigDecimal rentals;

    private String rentalsRemark;

    private Integer monthNumber;

    private BigDecimal deposit;

    private String depositRemark;

    private BigDecimal depositRate;

    private String depositRateRemark;

    private BigDecimal otherIncome;

    private String otherIncomeRemark;

    private BigDecimal grossIncome;

    private String additionalCapture;

    private String additionalCaptureRemark;

    private Integer sorting;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public String getRentalIncomeRemark() {
        return rentalIncomeRemark;
    }

    public void setRentalIncomeRemark(String rentalIncomeRemark) {
        this.rentalIncomeRemark = rentalIncomeRemark == null ? null : rentalIncomeRemark.trim();
    }

    public BigDecimal getRentalIncome() {
        return rentalIncome;
    }

    public void setRentalIncome(BigDecimal rentalIncome) {
        this.rentalIncome = rentalIncome;
    }

    public BigDecimal getRentals() {
        return rentals;
    }

    public void setRentals(BigDecimal rentals) {
        this.rentals = rentals;
    }

    public String getRentalsRemark() {
        return rentalsRemark;
    }

    public void setRentalsRemark(String rentalsRemark) {
        this.rentalsRemark = rentalsRemark == null ? null : rentalsRemark.trim();
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getDepositRemark() {
        return depositRemark;
    }

    public void setDepositRemark(String depositRemark) {
        this.depositRemark = depositRemark == null ? null : depositRemark.trim();
    }

    public BigDecimal getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public String getDepositRateRemark() {
        return depositRateRemark;
    }

    public void setDepositRateRemark(String depositRateRemark) {
        this.depositRateRemark = depositRateRemark == null ? null : depositRateRemark.trim();
    }

    public BigDecimal getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(BigDecimal otherIncome) {
        this.otherIncome = otherIncome;
    }

    public String getOtherIncomeRemark() {
        return otherIncomeRemark;
    }

    public void setOtherIncomeRemark(String otherIncomeRemark) {
        this.otherIncomeRemark = otherIncomeRemark == null ? null : otherIncomeRemark.trim();
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public String getAdditionalCapture() {
        return additionalCapture;
    }

    public void setAdditionalCapture(String additionalCapture) {
        this.additionalCapture = additionalCapture == null ? null : additionalCapture.trim();
    }

    public String getAdditionalCaptureRemark() {
        return additionalCaptureRemark;
    }

    public void setAdditionalCaptureRemark(String additionalCaptureRemark) {
        this.additionalCaptureRemark = additionalCaptureRemark == null ? null : additionalCaptureRemark.trim();
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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