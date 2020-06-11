package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdDevelopment {
    private Integer id;

    private String parcelSettingOuter;

    private String parcelSettingInner;

    private String name;

    private BigDecimal area;

    private BigDecimal price;

    private String type;

    private Integer economicId;

    private Integer planDetailsId;

    private BigDecimal constructionCostSubtotal;

    private BigDecimal interestInvestment;

    private BigDecimal investmentProfit;

    private BigDecimal assessPrice;

    private BigDecimal projectConstructionPeriod;

    private BigDecimal developedYear;

    private BigDecimal remainingDevelopmentYear;

    private Integer rewardRateId;

    private BigDecimal totalSaleableAreaPrice;

    private BigDecimal saleableArea;

    private BigDecimal plannedBuildingArea;

    private BigDecimal unsaleableBuildingArea;

    private BigDecimal transferArea;

    private BigDecimal reconnaissanceDesign;

    private String constructionInstallationEngineeringFeeIds;

    private BigDecimal constructionInstallationEngineeringFee;

    private BigDecimal infrastructureCost;

    private BigDecimal infrastructureMatchingCost;

    private BigDecimal devDuring;

    private BigDecimal otherEngineeringCost;

    private BigDecimal unforeseenExpenses;

    private String reconnaissanceDesignExplain;

    private String infrastructureCostExplain;

    private String infrastructureMatchingCostExplain;

    private String devDuringExplain;

    private String otherEngineeringCostExplain;

    private String unforeseenExpensesExplain;

    private BigDecimal deedTaxRate;

    private String deedTaxRateExplain;

    private BigDecimal transactionTaxRate;

    private String transactionTaxRateExplain;

    private BigDecimal managementExpense;

    private String managementExpenseExplain;

    private BigDecimal landGetRelevant;

    private String landGetRelevantExplain;

    private BigDecimal salesFee;

    private String salesFeeExplain;

    private BigDecimal interestInvestmentTax;

    private String interestInvestmentTaxExplain;

    private BigDecimal investmentProfitTax;

    private String investmentProfitTaxExplain;

    private BigDecimal salesTaxAndAdditional;

    private String salesTaxAndAdditionalExplain;

    private BigDecimal landValueAddedTax;

    private String landValueAddedTaxExplain;

    private BigDecimal projectDevelopmentIncomeTax;

    private String projectDevelopmentIncomeTaxExplain;

    private BigDecimal remunerationRate;

    private BigDecimal statutoryLife;

    private BigDecimal remainingYears;

    private BigDecimal amendmentStatusRights;

    private String amendmentStatusRightsExplain;

    private BigDecimal otherAmendments;

    private String otherAmendmentsExplain;

    private BigDecimal developmentDegreeRevision;

    private String developmentDegreeRevisionExplain;

    private Integer centerId;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private BigDecimal landArea;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParcelSettingOuter() {
        return parcelSettingOuter;
    }

    public void setParcelSettingOuter(String parcelSettingOuter) {
        this.parcelSettingOuter = parcelSettingOuter == null ? null : parcelSettingOuter.trim();
    }

    public String getParcelSettingInner() {
        return parcelSettingInner;
    }

    public void setParcelSettingInner(String parcelSettingInner) {
        this.parcelSettingInner = parcelSettingInner == null ? null : parcelSettingInner.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getEconomicId() {
        return economicId;
    }

    public void setEconomicId(Integer economicId) {
        this.economicId = economicId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public BigDecimal getConstructionCostSubtotal() {
        return constructionCostSubtotal;
    }

    public void setConstructionCostSubtotal(BigDecimal constructionCostSubtotal) {
        this.constructionCostSubtotal = constructionCostSubtotal;
    }

    public BigDecimal getInterestInvestment() {
        return interestInvestment;
    }

    public void setInterestInvestment(BigDecimal interestInvestment) {
        this.interestInvestment = interestInvestment;
    }

    public BigDecimal getInvestmentProfit() {
        return investmentProfit;
    }

    public void setInvestmentProfit(BigDecimal investmentProfit) {
        this.investmentProfit = investmentProfit;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
    }

    public BigDecimal getProjectConstructionPeriod() {
        return projectConstructionPeriod;
    }

    public void setProjectConstructionPeriod(BigDecimal projectConstructionPeriod) {
        this.projectConstructionPeriod = projectConstructionPeriod;
    }

    public BigDecimal getDevelopedYear() {
        return developedYear;
    }

    public void setDevelopedYear(BigDecimal developedYear) {
        this.developedYear = developedYear;
    }

    public BigDecimal getRemainingDevelopmentYear() {
        return remainingDevelopmentYear;
    }

    public void setRemainingDevelopmentYear(BigDecimal remainingDevelopmentYear) {
        this.remainingDevelopmentYear = remainingDevelopmentYear;
    }

    public Integer getRewardRateId() {
        return rewardRateId;
    }

    public void setRewardRateId(Integer rewardRateId) {
        this.rewardRateId = rewardRateId;
    }

    public BigDecimal getTotalSaleableAreaPrice() {
        return totalSaleableAreaPrice;
    }

    public void setTotalSaleableAreaPrice(BigDecimal totalSaleableAreaPrice) {
        this.totalSaleableAreaPrice = totalSaleableAreaPrice;
    }

    public BigDecimal getSaleableArea() {
        return saleableArea;
    }

    public void setSaleableArea(BigDecimal saleableArea) {
        this.saleableArea = saleableArea;
    }

    public BigDecimal getPlannedBuildingArea() {
        return plannedBuildingArea;
    }

    public void setPlannedBuildingArea(BigDecimal plannedBuildingArea) {
        this.plannedBuildingArea = plannedBuildingArea;
    }

    public BigDecimal getUnsaleableBuildingArea() {
        return unsaleableBuildingArea;
    }

    public void setUnsaleableBuildingArea(BigDecimal unsaleableBuildingArea) {
        this.unsaleableBuildingArea = unsaleableBuildingArea;
    }

    public BigDecimal getTransferArea() {
        return transferArea;
    }

    public void setTransferArea(BigDecimal transferArea) {
        this.transferArea = transferArea;
    }

    public BigDecimal getReconnaissanceDesign() {
        return reconnaissanceDesign;
    }

    public void setReconnaissanceDesign(BigDecimal reconnaissanceDesign) {
        this.reconnaissanceDesign = reconnaissanceDesign;
    }

    public String getConstructionInstallationEngineeringFeeIds() {
        return constructionInstallationEngineeringFeeIds;
    }

    public void setConstructionInstallationEngineeringFeeIds(String constructionInstallationEngineeringFeeIds) {
        this.constructionInstallationEngineeringFeeIds = constructionInstallationEngineeringFeeIds == null ? null : constructionInstallationEngineeringFeeIds.trim();
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

    public String getReconnaissanceDesignExplain() {
        return reconnaissanceDesignExplain;
    }

    public void setReconnaissanceDesignExplain(String reconnaissanceDesignExplain) {
        this.reconnaissanceDesignExplain = reconnaissanceDesignExplain == null ? null : reconnaissanceDesignExplain.trim();
    }

    public String getInfrastructureCostExplain() {
        return infrastructureCostExplain;
    }

    public void setInfrastructureCostExplain(String infrastructureCostExplain) {
        this.infrastructureCostExplain = infrastructureCostExplain == null ? null : infrastructureCostExplain.trim();
    }

    public String getInfrastructureMatchingCostExplain() {
        return infrastructureMatchingCostExplain;
    }

    public void setInfrastructureMatchingCostExplain(String infrastructureMatchingCostExplain) {
        this.infrastructureMatchingCostExplain = infrastructureMatchingCostExplain == null ? null : infrastructureMatchingCostExplain.trim();
    }

    public String getDevDuringExplain() {
        return devDuringExplain;
    }

    public void setDevDuringExplain(String devDuringExplain) {
        this.devDuringExplain = devDuringExplain == null ? null : devDuringExplain.trim();
    }

    public String getOtherEngineeringCostExplain() {
        return otherEngineeringCostExplain;
    }

    public void setOtherEngineeringCostExplain(String otherEngineeringCostExplain) {
        this.otherEngineeringCostExplain = otherEngineeringCostExplain == null ? null : otherEngineeringCostExplain.trim();
    }

    public String getUnforeseenExpensesExplain() {
        return unforeseenExpensesExplain;
    }

    public void setUnforeseenExpensesExplain(String unforeseenExpensesExplain) {
        this.unforeseenExpensesExplain = unforeseenExpensesExplain == null ? null : unforeseenExpensesExplain.trim();
    }

    public BigDecimal getDeedTaxRate() {
        return deedTaxRate;
    }

    public void setDeedTaxRate(BigDecimal deedTaxRate) {
        this.deedTaxRate = deedTaxRate;
    }

    public String getDeedTaxRateExplain() {
        return deedTaxRateExplain;
    }

    public void setDeedTaxRateExplain(String deedTaxRateExplain) {
        this.deedTaxRateExplain = deedTaxRateExplain == null ? null : deedTaxRateExplain.trim();
    }

    public BigDecimal getTransactionTaxRate() {
        return transactionTaxRate;
    }

    public void setTransactionTaxRate(BigDecimal transactionTaxRate) {
        this.transactionTaxRate = transactionTaxRate;
    }

    public String getTransactionTaxRateExplain() {
        return transactionTaxRateExplain;
    }

    public void setTransactionTaxRateExplain(String transactionTaxRateExplain) {
        this.transactionTaxRateExplain = transactionTaxRateExplain == null ? null : transactionTaxRateExplain.trim();
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

    public BigDecimal getLandValueAddedTax() {
        return landValueAddedTax;
    }

    public void setLandValueAddedTax(BigDecimal landValueAddedTax) {
        this.landValueAddedTax = landValueAddedTax;
    }

    public String getLandValueAddedTaxExplain() {
        return landValueAddedTaxExplain;
    }

    public void setLandValueAddedTaxExplain(String landValueAddedTaxExplain) {
        this.landValueAddedTaxExplain = landValueAddedTaxExplain == null ? null : landValueAddedTaxExplain.trim();
    }

    public BigDecimal getProjectDevelopmentIncomeTax() {
        return projectDevelopmentIncomeTax;
    }

    public void setProjectDevelopmentIncomeTax(BigDecimal projectDevelopmentIncomeTax) {
        this.projectDevelopmentIncomeTax = projectDevelopmentIncomeTax;
    }

    public String getProjectDevelopmentIncomeTaxExplain() {
        return projectDevelopmentIncomeTaxExplain;
    }

    public void setProjectDevelopmentIncomeTaxExplain(String projectDevelopmentIncomeTaxExplain) {
        this.projectDevelopmentIncomeTaxExplain = projectDevelopmentIncomeTaxExplain == null ? null : projectDevelopmentIncomeTaxExplain.trim();
    }

    public BigDecimal getRemunerationRate() {
        return remunerationRate;
    }

    public void setRemunerationRate(BigDecimal remunerationRate) {
        this.remunerationRate = remunerationRate;
    }

    public BigDecimal getStatutoryLife() {
        return statutoryLife;
    }

    public void setStatutoryLife(BigDecimal statutoryLife) {
        this.statutoryLife = statutoryLife;
    }

    public BigDecimal getRemainingYears() {
        return remainingYears;
    }

    public void setRemainingYears(BigDecimal remainingYears) {
        this.remainingYears = remainingYears;
    }

    public BigDecimal getAmendmentStatusRights() {
        return amendmentStatusRights;
    }

    public void setAmendmentStatusRights(BigDecimal amendmentStatusRights) {
        this.amendmentStatusRights = amendmentStatusRights;
    }

    public String getAmendmentStatusRightsExplain() {
        return amendmentStatusRightsExplain;
    }

    public void setAmendmentStatusRightsExplain(String amendmentStatusRightsExplain) {
        this.amendmentStatusRightsExplain = amendmentStatusRightsExplain == null ? null : amendmentStatusRightsExplain.trim();
    }

    public BigDecimal getOtherAmendments() {
        return otherAmendments;
    }

    public void setOtherAmendments(BigDecimal otherAmendments) {
        this.otherAmendments = otherAmendments;
    }

    public String getOtherAmendmentsExplain() {
        return otherAmendmentsExplain;
    }

    public void setOtherAmendmentsExplain(String otherAmendmentsExplain) {
        this.otherAmendmentsExplain = otherAmendmentsExplain == null ? null : otherAmendmentsExplain.trim();
    }

    public BigDecimal getDevelopmentDegreeRevision() {
        return developmentDegreeRevision;
    }

    public void setDevelopmentDegreeRevision(BigDecimal developmentDegreeRevision) {
        this.developmentDegreeRevision = developmentDegreeRevision;
    }

    public String getDevelopmentDegreeRevisionExplain() {
        return developmentDegreeRevisionExplain;
    }

    public void setDevelopmentDegreeRevisionExplain(String developmentDegreeRevisionExplain) {
        this.developmentDegreeRevisionExplain = developmentDegreeRevisionExplain == null ? null : developmentDegreeRevisionExplain.trim();
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
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

    public BigDecimal getLandArea() {
        return landArea;
    }

    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}