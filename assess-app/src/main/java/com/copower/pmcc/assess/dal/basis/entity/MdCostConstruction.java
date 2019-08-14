package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostConstruction {
    private Integer id;

    private BigDecimal constructionAssessmentPriceCorrecting;

    private String constructionAssessmentValue;

    private String investmentProfit;

    private String interestInvestment;

    private String constructionSubtotal;

    private String landGetCostTotal;

    private String jsonContent;

    private BigDecimal developLandAreaTax;

    private BigDecimal developBuildAreaTax;

    private BigDecimal developYearNumberTax;

    private Integer mcId;

    private BigDecimal landPurchasePrice;

    private String landPurchasePriceExplain;

    private BigDecimal landGetRelevant;

    private String landGetRelevantExplain;

    private BigDecimal reconnaissanceDesign;

    private BigDecimal constructionInstallationEngineeringFee;

    private BigDecimal infrastructureCost;

    private BigDecimal infrastructureMatchingCost;

    private String infrastructureMatchingCostExplain;

    private BigDecimal devDuring;

    private BigDecimal otherEngineeringCost;

    private BigDecimal unforeseenExpenses;

    private String unforeseenExpensesExplain;

    private BigDecimal managementExpense;

    private String managementExpenseExplain;

    private BigDecimal salesFee;

    private String salesFeeExplain;

    private BigDecimal interestInvestmentTax;

    private String interestInvestmentTaxExplain;

    private BigDecimal salesTaxAndAdditional;

    private String salesTaxAndAdditionalExplain;

    private BigDecimal investmentProfitTax;

    private String investmentProfitTaxExplain;

    private BigDecimal constructionAssessmentValue2;

    private String creator;

    private Date gmtModified;

    private Date gmtCreated;

    private Integer pid;

    private BigDecimal additionalCostLandAcquisition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getConstructionAssessmentPriceCorrecting() {
        return constructionAssessmentPriceCorrecting;
    }

    public void setConstructionAssessmentPriceCorrecting(BigDecimal constructionAssessmentPriceCorrecting) {
        this.constructionAssessmentPriceCorrecting = constructionAssessmentPriceCorrecting;
    }

    public String getConstructionAssessmentValue() {
        return constructionAssessmentValue;
    }

    public void setConstructionAssessmentValue(String constructionAssessmentValue) {
        this.constructionAssessmentValue = constructionAssessmentValue == null ? null : constructionAssessmentValue.trim();
    }

    public String getInvestmentProfit() {
        return investmentProfit;
    }

    public void setInvestmentProfit(String investmentProfit) {
        this.investmentProfit = investmentProfit == null ? null : investmentProfit.trim();
    }

    public String getInterestInvestment() {
        return interestInvestment;
    }

    public void setInterestInvestment(String interestInvestment) {
        this.interestInvestment = interestInvestment == null ? null : interestInvestment.trim();
    }

    public String getConstructionSubtotal() {
        return constructionSubtotal;
    }

    public void setConstructionSubtotal(String constructionSubtotal) {
        this.constructionSubtotal = constructionSubtotal == null ? null : constructionSubtotal.trim();
    }

    public String getLandGetCostTotal() {
        return landGetCostTotal;
    }

    public void setLandGetCostTotal(String landGetCostTotal) {
        this.landGetCostTotal = landGetCostTotal == null ? null : landGetCostTotal.trim();
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }

    public BigDecimal getDevelopLandAreaTax() {
        return developLandAreaTax;
    }

    public void setDevelopLandAreaTax(BigDecimal developLandAreaTax) {
        this.developLandAreaTax = developLandAreaTax;
    }

    public BigDecimal getDevelopBuildAreaTax() {
        return developBuildAreaTax;
    }

    public void setDevelopBuildAreaTax(BigDecimal developBuildAreaTax) {
        this.developBuildAreaTax = developBuildAreaTax;
    }

    public BigDecimal getDevelopYearNumberTax() {
        return developYearNumberTax;
    }

    public void setDevelopYearNumberTax(BigDecimal developYearNumberTax) {
        this.developYearNumberTax = developYearNumberTax;
    }

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public BigDecimal getLandPurchasePrice() {
        return landPurchasePrice;
    }

    public void setLandPurchasePrice(BigDecimal landPurchasePrice) {
        this.landPurchasePrice = landPurchasePrice;
    }

    public String getLandPurchasePriceExplain() {
        return landPurchasePriceExplain;
    }

    public void setLandPurchasePriceExplain(String landPurchasePriceExplain) {
        this.landPurchasePriceExplain = landPurchasePriceExplain == null ? null : landPurchasePriceExplain.trim();
    }

    public BigDecimal getLandGetRelevant() {
        return landGetRelevant;
    }

    public void setLandGetRelevant(BigDecimal landGetRelevant) {
        this.landGetRelevant = landGetRelevant;
    }

    public String getLandGetRelevantExplain() {
        return landGetRelevantExplain;
    }

    public void setLandGetRelevantExplain(String landGetRelevantExplain) {
        this.landGetRelevantExplain = landGetRelevantExplain == null ? null : landGetRelevantExplain.trim();
    }

    public BigDecimal getReconnaissanceDesign() {
        return reconnaissanceDesign;
    }

    public void setReconnaissanceDesign(BigDecimal reconnaissanceDesign) {
        this.reconnaissanceDesign = reconnaissanceDesign;
    }

    public BigDecimal getConstructionInstallationEngineeringFee() {
        return constructionInstallationEngineeringFee;
    }

    public void setConstructionInstallationEngineeringFee(BigDecimal constructionInstallationEngineeringFee) {
        this.constructionInstallationEngineeringFee = constructionInstallationEngineeringFee;
    }

    public BigDecimal getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(BigDecimal infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public BigDecimal getInfrastructureMatchingCost() {
        return infrastructureMatchingCost;
    }

    public void setInfrastructureMatchingCost(BigDecimal infrastructureMatchingCost) {
        this.infrastructureMatchingCost = infrastructureMatchingCost;
    }

    public String getInfrastructureMatchingCostExplain() {
        return infrastructureMatchingCostExplain;
    }

    public void setInfrastructureMatchingCostExplain(String infrastructureMatchingCostExplain) {
        this.infrastructureMatchingCostExplain = infrastructureMatchingCostExplain == null ? null : infrastructureMatchingCostExplain.trim();
    }

    public BigDecimal getDevDuring() {
        return devDuring;
    }

    public void setDevDuring(BigDecimal devDuring) {
        this.devDuring = devDuring;
    }

    public BigDecimal getOtherEngineeringCost() {
        return otherEngineeringCost;
    }

    public void setOtherEngineeringCost(BigDecimal otherEngineeringCost) {
        this.otherEngineeringCost = otherEngineeringCost;
    }

    public BigDecimal getUnforeseenExpenses() {
        return unforeseenExpenses;
    }

    public void setUnforeseenExpenses(BigDecimal unforeseenExpenses) {
        this.unforeseenExpenses = unforeseenExpenses;
    }

    public String getUnforeseenExpensesExplain() {
        return unforeseenExpensesExplain;
    }

    public void setUnforeseenExpensesExplain(String unforeseenExpensesExplain) {
        this.unforeseenExpensesExplain = unforeseenExpensesExplain == null ? null : unforeseenExpensesExplain.trim();
    }

    public BigDecimal getManagementExpense() {
        return managementExpense;
    }

    public void setManagementExpense(BigDecimal managementExpense) {
        this.managementExpense = managementExpense;
    }

    public String getManagementExpenseExplain() {
        return managementExpenseExplain;
    }

    public void setManagementExpenseExplain(String managementExpenseExplain) {
        this.managementExpenseExplain = managementExpenseExplain == null ? null : managementExpenseExplain.trim();
    }

    public BigDecimal getSalesFee() {
        return salesFee;
    }

    public void setSalesFee(BigDecimal salesFee) {
        this.salesFee = salesFee;
    }

    public String getSalesFeeExplain() {
        return salesFeeExplain;
    }

    public void setSalesFeeExplain(String salesFeeExplain) {
        this.salesFeeExplain = salesFeeExplain == null ? null : salesFeeExplain.trim();
    }

    public BigDecimal getInterestInvestmentTax() {
        return interestInvestmentTax;
    }

    public void setInterestInvestmentTax(BigDecimal interestInvestmentTax) {
        this.interestInvestmentTax = interestInvestmentTax;
    }

    public String getInterestInvestmentTaxExplain() {
        return interestInvestmentTaxExplain;
    }

    public void setInterestInvestmentTaxExplain(String interestInvestmentTaxExplain) {
        this.interestInvestmentTaxExplain = interestInvestmentTaxExplain == null ? null : interestInvestmentTaxExplain.trim();
    }

    public BigDecimal getSalesTaxAndAdditional() {
        return salesTaxAndAdditional;
    }

    public void setSalesTaxAndAdditional(BigDecimal salesTaxAndAdditional) {
        this.salesTaxAndAdditional = salesTaxAndAdditional;
    }

    public String getSalesTaxAndAdditionalExplain() {
        return salesTaxAndAdditionalExplain;
    }

    public void setSalesTaxAndAdditionalExplain(String salesTaxAndAdditionalExplain) {
        this.salesTaxAndAdditionalExplain = salesTaxAndAdditionalExplain == null ? null : salesTaxAndAdditionalExplain.trim();
    }

    public BigDecimal getInvestmentProfitTax() {
        return investmentProfitTax;
    }

    public void setInvestmentProfitTax(BigDecimal investmentProfitTax) {
        this.investmentProfitTax = investmentProfitTax;
    }

    public String getInvestmentProfitTaxExplain() {
        return investmentProfitTaxExplain;
    }

    public void setInvestmentProfitTaxExplain(String investmentProfitTaxExplain) {
        this.investmentProfitTaxExplain = investmentProfitTaxExplain == null ? null : investmentProfitTaxExplain.trim();
    }

    public BigDecimal getConstructionAssessmentValue2() {
        return constructionAssessmentValue2;
    }

    public void setConstructionAssessmentValue2(BigDecimal constructionAssessmentValue2) {
        this.constructionAssessmentValue2 = constructionAssessmentValue2;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getAdditionalCostLandAcquisition() {
        return additionalCostLandAcquisition;
    }

    public void setAdditionalCostLandAcquisition(BigDecimal additionalCostLandAcquisition) {
        this.additionalCostLandAcquisition = additionalCostLandAcquisition;
    }
}