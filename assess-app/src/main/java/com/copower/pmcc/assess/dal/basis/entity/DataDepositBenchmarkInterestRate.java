package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataDepositBenchmarkInterestRate {
    private Integer id;

    private Date adjustTime;

    private BigDecimal demandDeposit;

    private BigDecimal depositWithdrawLumpSumThreeMonth;

    private BigDecimal depositWithdrawLumpSumHalfYear;

    private BigDecimal depositWithdrawLumpSumOneYear;

    private BigDecimal depositWithdrawLumpSumTwoYear;

    private BigDecimal depositWithdrawLumpSumThreeYear;

    private BigDecimal depositWithdrawLumpSumFiveYear;

    private BigDecimal depositInstallmentsWithdrawLumpSumOneYear;

    private BigDecimal depositInstallmentsWithdrawLumpSumTwoYear;

    private BigDecimal depositInstallmentsWithdrawLumpSumThreeYear;

    private BigDecimal timeDemandOptionalDeposit;

    private BigDecimal agreementDeposit;

    private BigDecimal callDepositOneDay;

    private BigDecimal callDepositSevenDay;

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

    public BigDecimal getDemandDeposit() {
        return demandDeposit;
    }

    public void setDemandDeposit(BigDecimal demandDeposit) {
        this.demandDeposit = demandDeposit;
    }

    public BigDecimal getDepositWithdrawLumpSumThreeMonth() {
        return depositWithdrawLumpSumThreeMonth;
    }

    public void setDepositWithdrawLumpSumThreeMonth(BigDecimal depositWithdrawLumpSumThreeMonth) {
        this.depositWithdrawLumpSumThreeMonth = depositWithdrawLumpSumThreeMonth;
    }

    public BigDecimal getDepositWithdrawLumpSumHalfYear() {
        return depositWithdrawLumpSumHalfYear;
    }

    public void setDepositWithdrawLumpSumHalfYear(BigDecimal depositWithdrawLumpSumHalfYear) {
        this.depositWithdrawLumpSumHalfYear = depositWithdrawLumpSumHalfYear;
    }

    public BigDecimal getDepositWithdrawLumpSumOneYear() {
        return depositWithdrawLumpSumOneYear;
    }

    public void setDepositWithdrawLumpSumOneYear(BigDecimal depositWithdrawLumpSumOneYear) {
        this.depositWithdrawLumpSumOneYear = depositWithdrawLumpSumOneYear;
    }

    public BigDecimal getDepositWithdrawLumpSumTwoYear() {
        return depositWithdrawLumpSumTwoYear;
    }

    public void setDepositWithdrawLumpSumTwoYear(BigDecimal depositWithdrawLumpSumTwoYear) {
        this.depositWithdrawLumpSumTwoYear = depositWithdrawLumpSumTwoYear;
    }

    public BigDecimal getDepositWithdrawLumpSumThreeYear() {
        return depositWithdrawLumpSumThreeYear;
    }

    public void setDepositWithdrawLumpSumThreeYear(BigDecimal depositWithdrawLumpSumThreeYear) {
        this.depositWithdrawLumpSumThreeYear = depositWithdrawLumpSumThreeYear;
    }

    public BigDecimal getDepositWithdrawLumpSumFiveYear() {
        return depositWithdrawLumpSumFiveYear;
    }

    public void setDepositWithdrawLumpSumFiveYear(BigDecimal depositWithdrawLumpSumFiveYear) {
        this.depositWithdrawLumpSumFiveYear = depositWithdrawLumpSumFiveYear;
    }

    public BigDecimal getDepositInstallmentsWithdrawLumpSumOneYear() {
        return depositInstallmentsWithdrawLumpSumOneYear;
    }

    public void setDepositInstallmentsWithdrawLumpSumOneYear(BigDecimal depositInstallmentsWithdrawLumpSumOneYear) {
        this.depositInstallmentsWithdrawLumpSumOneYear = depositInstallmentsWithdrawLumpSumOneYear;
    }

    public BigDecimal getDepositInstallmentsWithdrawLumpSumTwoYear() {
        return depositInstallmentsWithdrawLumpSumTwoYear;
    }

    public void setDepositInstallmentsWithdrawLumpSumTwoYear(BigDecimal depositInstallmentsWithdrawLumpSumTwoYear) {
        this.depositInstallmentsWithdrawLumpSumTwoYear = depositInstallmentsWithdrawLumpSumTwoYear;
    }

    public BigDecimal getDepositInstallmentsWithdrawLumpSumThreeYear() {
        return depositInstallmentsWithdrawLumpSumThreeYear;
    }

    public void setDepositInstallmentsWithdrawLumpSumThreeYear(BigDecimal depositInstallmentsWithdrawLumpSumThreeYear) {
        this.depositInstallmentsWithdrawLumpSumThreeYear = depositInstallmentsWithdrawLumpSumThreeYear;
    }

    public BigDecimal getTimeDemandOptionalDeposit() {
        return timeDemandOptionalDeposit;
    }

    public void setTimeDemandOptionalDeposit(BigDecimal timeDemandOptionalDeposit) {
        this.timeDemandOptionalDeposit = timeDemandOptionalDeposit;
    }

    public BigDecimal getAgreementDeposit() {
        return agreementDeposit;
    }

    public void setAgreementDeposit(BigDecimal agreementDeposit) {
        this.agreementDeposit = agreementDeposit;
    }

    public BigDecimal getCallDepositOneDay() {
        return callDepositOneDay;
    }

    public void setCallDepositOneDay(BigDecimal callDepositOneDay) {
        this.callDepositOneDay = callDepositOneDay;
    }

    public BigDecimal getCallDepositSevenDay() {
        return callDepositSevenDay;
    }

    public void setCallDepositSevenDay(BigDecimal callDepositSevenDay) {
        this.callDepositSevenDay = callDepositSevenDay;
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