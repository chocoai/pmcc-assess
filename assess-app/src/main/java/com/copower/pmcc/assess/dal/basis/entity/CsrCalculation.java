package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class CsrCalculation {
    private Integer id;

    private Integer csrProjectId;

    private String borrowerId;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String appraisalMethod;

    private String pawnArea;

    private String pawnPrice;

    private String pawnAmount;

    private String pawnRealizationCoefficient;

    private String pawnRealization;

    private String pawnRealizationAmount;

    private String disposeAuctionCoefficient;

    private String disposeAuction;

    private String disposeLawsuitCoefficient;

    private String disposeLawsuit;

    private String disposeExecuteCoefficient;

    private String disposeExecute;

    private String disposeAuthenticateCofficient;

    private String disposeAuthenticate;

    private String dispoetOtherCofficient;

    private String dispoetOther;

    private String dispoetAmount;

    private String taxStampCofficient;

    private String taxStamp;

    private String taxAddedvalueCofficient;

    private String taxAddedvalue;

    private String taxLandCofficient;

    private String taxLand;

    private String taxOther;

    private String taxAmount;

    private String costAmount;

    private String pawnRealizationValueAmount;

    private String recoveryIncomeOrdinary;

    private String recoveryIncomeAll;

    private String recoveryIncomeThirdParty;

    private String recoveryIncomAmount;

    private String recoveryBadLoans;

    private String recoveryLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCsrProjectId() {
        return csrProjectId;
    }

    public void setCsrProjectId(Integer csrProjectId) {
        this.csrProjectId = csrProjectId;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId == null ? null : borrowerId.trim();
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

    public String getAppraisalMethod() {
        return appraisalMethod;
    }

    public void setAppraisalMethod(String appraisalMethod) {
        this.appraisalMethod = appraisalMethod == null ? null : appraisalMethod.trim();
    }

    public String getPawnArea() {
        return pawnArea;
    }

    public void setPawnArea(String pawnArea) {
        this.pawnArea = pawnArea == null ? null : pawnArea.trim();
    }

    public String getPawnPrice() {
        return pawnPrice;
    }

    public void setPawnPrice(String pawnPrice) {
        this.pawnPrice = pawnPrice == null ? null : pawnPrice.trim();
    }

    public String getPawnAmount() {
        return pawnAmount;
    }

    public void setPawnAmount(String pawnAmount) {
        this.pawnAmount = pawnAmount == null ? null : pawnAmount.trim();
    }

    public String getPawnRealizationCoefficient() {
        return pawnRealizationCoefficient;
    }

    public void setPawnRealizationCoefficient(String pawnRealizationCoefficient) {
        this.pawnRealizationCoefficient = pawnRealizationCoefficient == null ? null : pawnRealizationCoefficient.trim();
    }

    public String getPawnRealization() {
        return pawnRealization;
    }

    public void setPawnRealization(String pawnRealization) {
        this.pawnRealization = pawnRealization == null ? null : pawnRealization.trim();
    }

    public String getPawnRealizationAmount() {
        return pawnRealizationAmount;
    }

    public void setPawnRealizationAmount(String pawnRealizationAmount) {
        this.pawnRealizationAmount = pawnRealizationAmount == null ? null : pawnRealizationAmount.trim();
    }

    public String getDisposeAuctionCoefficient() {
        return disposeAuctionCoefficient;
    }

    public void setDisposeAuctionCoefficient(String disposeAuctionCoefficient) {
        this.disposeAuctionCoefficient = disposeAuctionCoefficient == null ? null : disposeAuctionCoefficient.trim();
    }

    public String getDisposeAuction() {
        return disposeAuction;
    }

    public void setDisposeAuction(String disposeAuction) {
        this.disposeAuction = disposeAuction == null ? null : disposeAuction.trim();
    }

    public String getDisposeLawsuitCoefficient() {
        return disposeLawsuitCoefficient;
    }

    public void setDisposeLawsuitCoefficient(String disposeLawsuitCoefficient) {
        this.disposeLawsuitCoefficient = disposeLawsuitCoefficient == null ? null : disposeLawsuitCoefficient.trim();
    }

    public String getDisposeLawsuit() {
        return disposeLawsuit;
    }

    public void setDisposeLawsuit(String disposeLawsuit) {
        this.disposeLawsuit = disposeLawsuit == null ? null : disposeLawsuit.trim();
    }

    public String getDisposeExecuteCoefficient() {
        return disposeExecuteCoefficient;
    }

    public void setDisposeExecuteCoefficient(String disposeExecuteCoefficient) {
        this.disposeExecuteCoefficient = disposeExecuteCoefficient == null ? null : disposeExecuteCoefficient.trim();
    }

    public String getDisposeExecute() {
        return disposeExecute;
    }

    public void setDisposeExecute(String disposeExecute) {
        this.disposeExecute = disposeExecute == null ? null : disposeExecute.trim();
    }

    public String getDisposeAuthenticateCofficient() {
        return disposeAuthenticateCofficient;
    }

    public void setDisposeAuthenticateCofficient(String disposeAuthenticateCofficient) {
        this.disposeAuthenticateCofficient = disposeAuthenticateCofficient == null ? null : disposeAuthenticateCofficient.trim();
    }

    public String getDisposeAuthenticate() {
        return disposeAuthenticate;
    }

    public void setDisposeAuthenticate(String disposeAuthenticate) {
        this.disposeAuthenticate = disposeAuthenticate == null ? null : disposeAuthenticate.trim();
    }

    public String getDispoetOtherCofficient() {
        return dispoetOtherCofficient;
    }

    public void setDispoetOtherCofficient(String dispoetOtherCofficient) {
        this.dispoetOtherCofficient = dispoetOtherCofficient == null ? null : dispoetOtherCofficient.trim();
    }

    public String getDispoetOther() {
        return dispoetOther;
    }

    public void setDispoetOther(String dispoetOther) {
        this.dispoetOther = dispoetOther == null ? null : dispoetOther.trim();
    }

    public String getDispoetAmount() {
        return dispoetAmount;
    }

    public void setDispoetAmount(String dispoetAmount) {
        this.dispoetAmount = dispoetAmount == null ? null : dispoetAmount.trim();
    }

    public String getTaxStampCofficient() {
        return taxStampCofficient;
    }

    public void setTaxStampCofficient(String taxStampCofficient) {
        this.taxStampCofficient = taxStampCofficient == null ? null : taxStampCofficient.trim();
    }

    public String getTaxStamp() {
        return taxStamp;
    }

    public void setTaxStamp(String taxStamp) {
        this.taxStamp = taxStamp == null ? null : taxStamp.trim();
    }

    public String getTaxAddedvalueCofficient() {
        return taxAddedvalueCofficient;
    }

    public void setTaxAddedvalueCofficient(String taxAddedvalueCofficient) {
        this.taxAddedvalueCofficient = taxAddedvalueCofficient == null ? null : taxAddedvalueCofficient.trim();
    }

    public String getTaxAddedvalue() {
        return taxAddedvalue;
    }

    public void setTaxAddedvalue(String taxAddedvalue) {
        this.taxAddedvalue = taxAddedvalue == null ? null : taxAddedvalue.trim();
    }

    public String getTaxLandCofficient() {
        return taxLandCofficient;
    }

    public void setTaxLandCofficient(String taxLandCofficient) {
        this.taxLandCofficient = taxLandCofficient == null ? null : taxLandCofficient.trim();
    }

    public String getTaxLand() {
        return taxLand;
    }

    public void setTaxLand(String taxLand) {
        this.taxLand = taxLand == null ? null : taxLand.trim();
    }

    public String getTaxOther() {
        return taxOther;
    }

    public void setTaxOther(String taxOther) {
        this.taxOther = taxOther == null ? null : taxOther.trim();
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount == null ? null : taxAmount.trim();
    }

    public String getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(String costAmount) {
        this.costAmount = costAmount == null ? null : costAmount.trim();
    }

    public String getPawnRealizationValueAmount() {
        return pawnRealizationValueAmount;
    }

    public void setPawnRealizationValueAmount(String pawnRealizationValueAmount) {
        this.pawnRealizationValueAmount = pawnRealizationValueAmount == null ? null : pawnRealizationValueAmount.trim();
    }

    public String getRecoveryIncomeOrdinary() {
        return recoveryIncomeOrdinary;
    }

    public void setRecoveryIncomeOrdinary(String recoveryIncomeOrdinary) {
        this.recoveryIncomeOrdinary = recoveryIncomeOrdinary == null ? null : recoveryIncomeOrdinary.trim();
    }

    public String getRecoveryIncomeAll() {
        return recoveryIncomeAll;
    }

    public void setRecoveryIncomeAll(String recoveryIncomeAll) {
        this.recoveryIncomeAll = recoveryIncomeAll == null ? null : recoveryIncomeAll.trim();
    }

    public String getRecoveryIncomeThirdParty() {
        return recoveryIncomeThirdParty;
    }

    public void setRecoveryIncomeThirdParty(String recoveryIncomeThirdParty) {
        this.recoveryIncomeThirdParty = recoveryIncomeThirdParty == null ? null : recoveryIncomeThirdParty.trim();
    }

    public String getRecoveryIncomAmount() {
        return recoveryIncomAmount;
    }

    public void setRecoveryIncomAmount(String recoveryIncomAmount) {
        this.recoveryIncomAmount = recoveryIncomAmount == null ? null : recoveryIncomAmount.trim();
    }

    public String getRecoveryBadLoans() {
        return recoveryBadLoans;
    }

    public void setRecoveryBadLoans(String recoveryBadLoans) {
        this.recoveryBadLoans = recoveryBadLoans == null ? null : recoveryBadLoans.trim();
    }

    public String getRecoveryLimit() {
        return recoveryLimit;
    }

    public void setRecoveryLimit(String recoveryLimit) {
        this.recoveryLimit = recoveryLimit == null ? null : recoveryLimit.trim();
    }
}