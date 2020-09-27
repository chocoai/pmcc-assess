package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MdCostConstruction implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 宗地外设定
     */
    private String parcelSettingOuter;

    /**
     * 宗地内设定
     */
    private String parcelSettingInner;

    /**
     * 建筑安装工程费ids
     */
    private String constructionInstallationEngineeringFeeIds;

    /**
     * 在建工程单位价
     */
    private BigDecimal constructionAssessmentPriceCorrecting;

    /**
     * 在建工程评估价值
     */
    private String constructionAssessmentValue;

    /**
     * 投资利润
     */
    private String investmentProfit;

    /**
     * 投资利息
     */
    private String interestInvestment;

    /**
     * 建设成本小计
     */
    private String constructionSubtotal;

    /**
     * 土地取得成本合计
     */
    private String landGetCostTotal;

    /**
     * 开发土地面积
     */
    private BigDecimal developLandAreaTax;

    /**
     * 开发建筑面积(㎡)
     */
    private BigDecimal developBuildAreaTax;

    /**
     * 开发期
     */
    private BigDecimal developYearNumberTax;

    /**
     * 经济指标id
     */
    private Integer economicId;

    /**
     * 基准地价法id
     */
    private Integer baseLandId;

    /**
     * 成本逼近法id
     */
    private Integer approachId;

    /**
     * 比较法id
     */
    private Integer mcId;

    /**
     * 土地购买价格
     */
    private BigDecimal landPurchasePrice;

    /**
     * 土地购买价格说明
     */
    private String landPurchasePriceExplain;

    /**
     * 土地取得相关税费率
     */
    private BigDecimal landGetRelevant;

    /**
     * 土地取得相关税费率说明
     */
    private String landGetRelevantExplain;

    /**
     * 勘察设计和前期工程费率
     */
    private BigDecimal reconnaissanceDesign;

    /**
     * 建筑安装工程费
     */
    private BigDecimal constructionInstallationEngineeringFee;

    /**
     * 基础设施建设费
     */
    private BigDecimal infrastructureCost;

    /**
     * 公共配套设施建设费
     */
    private BigDecimal infrastructureMatchingCost;

    /**
     * 公共配套设施建设费 说明
     */
    private String infrastructureMatchingCostExplain;

    /**
     * 开发期间税费
     */
    private BigDecimal devDuring;

    /**
     * 其它工程费
     */
    private BigDecimal otherEngineeringCost;

    /**
     * 不可预见费率
     */
    private BigDecimal unforeseenExpenses;

    /**
     * 不可预见费率说明
     */
    private String unforeseenExpensesExplain;

    /**
     * 管理费率
     */
    private BigDecimal managementExpense;

    /**
     * 管理费率说明
     */
    private String managementExpenseExplain;

    /**
     * 销售费率
     */
    private BigDecimal salesFee;

    /**
     * 销售费率说明
     */
    private String salesFeeExplain;

    /**
     * 投资利息率
     */
    private BigDecimal interestInvestmentTax;

    /**
     * 投资利息率说明
     */
    private String interestInvestmentTaxExplain;

    /**
     * 销售税金及附加率
     */
    private BigDecimal salesTaxAndAdditional;

    /**
     * 销售税金及附加率说明
     */
    private String salesTaxAndAdditionalExplain;

    /**
     * 开发利润率
     */
    private BigDecimal investmentProfitTax;

    /**
     * 开发利润率说明
     */
    private String investmentProfitTaxExplain;

    /**
     * 在建工程评估价值2
     */
    private BigDecimal constructionAssessmentValue2;

    /**
     * 土地取得附加成本
     */
    private BigDecimal additionalCostLandAcquisition;

    /**
     * 基础设施建设费说明
     */
    private String infrastructureCostExplain;

    /**
     * 开发期间税费说明
     */
    private String devDuringExplain;

    /**
     * 其它工程费说明
     */
    private String otherEngineeringCostExplain;

    /**
     * 土地取得附加成本说明
     */
    private String additionalCostLandAcquisitionExplain;

    /**
     * 勘察设计和前期工程费率说明
     */
    private String reconnaissanceDesignExplain;

    /**
     * 成新率id
     */
    private Integer residueRatioId;

    /**
     * 成新率备注说明
     */
    private String residueRatioRemark;

    /**
     * 成新率
     */
    private BigDecimal residueRatio;

    /**
     * 成本法id
     */
    private Integer pid;

    /**
     * 申报对象中间表id
     */
    private Integer centerId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * json数据
     */
    private String jsonContent;

    /**
     * tb_md_cost_construction
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 宗地外设定
     * @return parcel_setting_outer 宗地外设定
     */
    public String getParcelSettingOuter() {
        return parcelSettingOuter;
    }

    /**
     * 宗地外设定
     * @param parcelSettingOuter 宗地外设定
     */
    public void setParcelSettingOuter(String parcelSettingOuter) {
        this.parcelSettingOuter = parcelSettingOuter == null ? null : parcelSettingOuter.trim();
    }

    /**
     * 宗地内设定
     * @return parcel_setting_inner 宗地内设定
     */
    public String getParcelSettingInner() {
        return parcelSettingInner;
    }

    /**
     * 宗地内设定
     * @param parcelSettingInner 宗地内设定
     */
    public void setParcelSettingInner(String parcelSettingInner) {
        this.parcelSettingInner = parcelSettingInner == null ? null : parcelSettingInner.trim();
    }

    /**
     * 建筑安装工程费ids
     * @return construction_installation_engineering_fee_ids 建筑安装工程费ids
     */
    public String getConstructionInstallationEngineeringFeeIds() {
        return constructionInstallationEngineeringFeeIds;
    }

    /**
     * 建筑安装工程费ids
     * @param constructionInstallationEngineeringFeeIds 建筑安装工程费ids
     */
    public void setConstructionInstallationEngineeringFeeIds(String constructionInstallationEngineeringFeeIds) {
        this.constructionInstallationEngineeringFeeIds = constructionInstallationEngineeringFeeIds == null ? null : constructionInstallationEngineeringFeeIds.trim();
    }

    /**
     * 在建工程单位价
     * @return construction_assessment_price_correcting 在建工程单位价
     */
    public BigDecimal getConstructionAssessmentPriceCorrecting() {
        return constructionAssessmentPriceCorrecting;
    }

    /**
     * 在建工程单位价
     * @param constructionAssessmentPriceCorrecting 在建工程单位价
     */
    public void setConstructionAssessmentPriceCorrecting(BigDecimal constructionAssessmentPriceCorrecting) {
        this.constructionAssessmentPriceCorrecting = constructionAssessmentPriceCorrecting;
    }

    /**
     * 在建工程评估价值
     * @return construction_assessment_value 在建工程评估价值
     */
    public String getConstructionAssessmentValue() {
        return constructionAssessmentValue;
    }

    /**
     * 在建工程评估价值
     * @param constructionAssessmentValue 在建工程评估价值
     */
    public void setConstructionAssessmentValue(String constructionAssessmentValue) {
        this.constructionAssessmentValue = constructionAssessmentValue == null ? null : constructionAssessmentValue.trim();
    }

    /**
     * 投资利润
     * @return investment_profit 投资利润
     */
    public String getInvestmentProfit() {
        return investmentProfit;
    }

    /**
     * 投资利润
     * @param investmentProfit 投资利润
     */
    public void setInvestmentProfit(String investmentProfit) {
        this.investmentProfit = investmentProfit == null ? null : investmentProfit.trim();
    }

    /**
     * 投资利息
     * @return interest_investment 投资利息
     */
    public String getInterestInvestment() {
        return interestInvestment;
    }

    /**
     * 投资利息
     * @param interestInvestment 投资利息
     */
    public void setInterestInvestment(String interestInvestment) {
        this.interestInvestment = interestInvestment == null ? null : interestInvestment.trim();
    }

    /**
     * 建设成本小计
     * @return construction_subtotal 建设成本小计
     */
    public String getConstructionSubtotal() {
        return constructionSubtotal;
    }

    /**
     * 建设成本小计
     * @param constructionSubtotal 建设成本小计
     */
    public void setConstructionSubtotal(String constructionSubtotal) {
        this.constructionSubtotal = constructionSubtotal == null ? null : constructionSubtotal.trim();
    }

    /**
     * 土地取得成本合计
     * @return land_get_cost_total 土地取得成本合计
     */
    public String getLandGetCostTotal() {
        return landGetCostTotal;
    }

    /**
     * 土地取得成本合计
     * @param landGetCostTotal 土地取得成本合计
     */
    public void setLandGetCostTotal(String landGetCostTotal) {
        this.landGetCostTotal = landGetCostTotal == null ? null : landGetCostTotal.trim();
    }

    /**
     * 开发土地面积
     * @return develop_land_area_tax 开发土地面积
     */
    public BigDecimal getDevelopLandAreaTax() {
        return developLandAreaTax;
    }

    /**
     * 开发土地面积
     * @param developLandAreaTax 开发土地面积
     */
    public void setDevelopLandAreaTax(BigDecimal developLandAreaTax) {
        this.developLandAreaTax = developLandAreaTax;
    }

    /**
     * 开发建筑面积(㎡)
     * @return develop_build_area_tax 开发建筑面积(㎡)
     */
    public BigDecimal getDevelopBuildAreaTax() {
        return developBuildAreaTax;
    }

    /**
     * 开发建筑面积(㎡)
     * @param developBuildAreaTax 开发建筑面积(㎡)
     */
    public void setDevelopBuildAreaTax(BigDecimal developBuildAreaTax) {
        this.developBuildAreaTax = developBuildAreaTax;
    }

    /**
     * 开发期
     * @return develop_year_number_tax 开发期
     */
    public BigDecimal getDevelopYearNumberTax() {
        return developYearNumberTax;
    }

    /**
     * 开发期
     * @param developYearNumberTax 开发期
     */
    public void setDevelopYearNumberTax(BigDecimal developYearNumberTax) {
        this.developYearNumberTax = developYearNumberTax;
    }

    /**
     * 经济指标id
     * @return economic_id 经济指标id
     */
    public Integer getEconomicId() {
        return economicId;
    }

    /**
     * 经济指标id
     * @param economicId 经济指标id
     */
    public void setEconomicId(Integer economicId) {
        this.economicId = economicId;
    }

    /**
     * 基准地价法id
     * @return base_land_id 基准地价法id
     */
    public Integer getBaseLandId() {
        return baseLandId;
    }

    /**
     * 基准地价法id
     * @param baseLandId 基准地价法id
     */
    public void setBaseLandId(Integer baseLandId) {
        this.baseLandId = baseLandId;
    }

    /**
     * 成本逼近法id
     * @return approach_id 成本逼近法id
     */
    public Integer getApproachId() {
        return approachId;
    }

    /**
     * 成本逼近法id
     * @param approachId 成本逼近法id
     */
    public void setApproachId(Integer approachId) {
        this.approachId = approachId;
    }

    /**
     * 比较法id
     * @return mc_id 比较法id
     */
    public Integer getMcId() {
        return mcId;
    }

    /**
     * 比较法id
     * @param mcId 比较法id
     */
    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    /**
     * 土地购买价格
     * @return land_purchase_price 土地购买价格
     */
    public BigDecimal getLandPurchasePrice() {
        return landPurchasePrice;
    }

    /**
     * 土地购买价格
     * @param landPurchasePrice 土地购买价格
     */
    public void setLandPurchasePrice(BigDecimal landPurchasePrice) {
        this.landPurchasePrice = landPurchasePrice;
    }

    /**
     * 土地购买价格说明
     * @return land_purchase_price_explain 土地购买价格说明
     */
    public String getLandPurchasePriceExplain() {
        return landPurchasePriceExplain;
    }

    /**
     * 土地购买价格说明
     * @param landPurchasePriceExplain 土地购买价格说明
     */
    public void setLandPurchasePriceExplain(String landPurchasePriceExplain) {
        this.landPurchasePriceExplain = landPurchasePriceExplain == null ? null : landPurchasePriceExplain.trim();
    }

    /**
     * 土地取得相关税费率
     * @return land_get_relevant 土地取得相关税费率
     */
    public BigDecimal getLandGetRelevant() {
        return landGetRelevant;
    }

    /**
     * 土地取得相关税费率
     * @param landGetRelevant 土地取得相关税费率
     */
    public void setLandGetRelevant(BigDecimal landGetRelevant) {
        this.landGetRelevant = landGetRelevant;
    }

    /**
     * 土地取得相关税费率说明
     * @return land_get_relevant_explain 土地取得相关税费率说明
     */
    public String getLandGetRelevantExplain() {
        return landGetRelevantExplain;
    }

    /**
     * 土地取得相关税费率说明
     * @param landGetRelevantExplain 土地取得相关税费率说明
     */
    public void setLandGetRelevantExplain(String landGetRelevantExplain) {
        this.landGetRelevantExplain = landGetRelevantExplain == null ? null : landGetRelevantExplain.trim();
    }

    /**
     * 勘察设计和前期工程费率
     * @return reconnaissance_design 勘察设计和前期工程费率
     */
    public BigDecimal getReconnaissanceDesign() {
        return reconnaissanceDesign;
    }

    /**
     * 勘察设计和前期工程费率
     * @param reconnaissanceDesign 勘察设计和前期工程费率
     */
    public void setReconnaissanceDesign(BigDecimal reconnaissanceDesign) {
        this.reconnaissanceDesign = reconnaissanceDesign;
    }

    /**
     * 建筑安装工程费
     * @return construction_installation_engineering_fee 建筑安装工程费
     */
    public BigDecimal getConstructionInstallationEngineeringFee() {
        return constructionInstallationEngineeringFee;
    }

    /**
     * 建筑安装工程费
     * @param constructionInstallationEngineeringFee 建筑安装工程费
     */
    public void setConstructionInstallationEngineeringFee(BigDecimal constructionInstallationEngineeringFee) {
        this.constructionInstallationEngineeringFee = constructionInstallationEngineeringFee;
    }

    /**
     * 基础设施建设费
     * @return infrastructure_cost 基础设施建设费
     */
    public BigDecimal getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     * 基础设施建设费
     * @param infrastructureCost 基础设施建设费
     */
    public void setInfrastructureCost(BigDecimal infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /**
     * 公共配套设施建设费
     * @return infrastructure_matching_cost 公共配套设施建设费
     */
    public BigDecimal getInfrastructureMatchingCost() {
        return infrastructureMatchingCost;
    }

    /**
     * 公共配套设施建设费
     * @param infrastructureMatchingCost 公共配套设施建设费
     */
    public void setInfrastructureMatchingCost(BigDecimal infrastructureMatchingCost) {
        this.infrastructureMatchingCost = infrastructureMatchingCost;
    }

    /**
     * 公共配套设施建设费 说明
     * @return infrastructure_matching_cost_explain 公共配套设施建设费 说明
     */
    public String getInfrastructureMatchingCostExplain() {
        return infrastructureMatchingCostExplain;
    }

    /**
     * 公共配套设施建设费 说明
     * @param infrastructureMatchingCostExplain 公共配套设施建设费 说明
     */
    public void setInfrastructureMatchingCostExplain(String infrastructureMatchingCostExplain) {
        this.infrastructureMatchingCostExplain = infrastructureMatchingCostExplain == null ? null : infrastructureMatchingCostExplain.trim();
    }

    /**
     * 开发期间税费
     * @return dev_during 开发期间税费
     */
    public BigDecimal getDevDuring() {
        return devDuring;
    }

    /**
     * 开发期间税费
     * @param devDuring 开发期间税费
     */
    public void setDevDuring(BigDecimal devDuring) {
        this.devDuring = devDuring;
    }

    /**
     * 其它工程费
     * @return other_engineering_cost 其它工程费
     */
    public BigDecimal getOtherEngineeringCost() {
        return otherEngineeringCost;
    }

    /**
     * 其它工程费
     * @param otherEngineeringCost 其它工程费
     */
    public void setOtherEngineeringCost(BigDecimal otherEngineeringCost) {
        this.otherEngineeringCost = otherEngineeringCost;
    }

    /**
     * 不可预见费率
     * @return unforeseen_expenses 不可预见费率
     */
    public BigDecimal getUnforeseenExpenses() {
        return unforeseenExpenses;
    }

    /**
     * 不可预见费率
     * @param unforeseenExpenses 不可预见费率
     */
    public void setUnforeseenExpenses(BigDecimal unforeseenExpenses) {
        this.unforeseenExpenses = unforeseenExpenses;
    }

    /**
     * 不可预见费率说明
     * @return unforeseen_expenses_explain 不可预见费率说明
     */
    public String getUnforeseenExpensesExplain() {
        return unforeseenExpensesExplain;
    }

    /**
     * 不可预见费率说明
     * @param unforeseenExpensesExplain 不可预见费率说明
     */
    public void setUnforeseenExpensesExplain(String unforeseenExpensesExplain) {
        this.unforeseenExpensesExplain = unforeseenExpensesExplain == null ? null : unforeseenExpensesExplain.trim();
    }

    /**
     * 管理费率
     * @return management_expense 管理费率
     */
    public BigDecimal getManagementExpense() {
        return managementExpense;
    }

    /**
     * 管理费率
     * @param managementExpense 管理费率
     */
    public void setManagementExpense(BigDecimal managementExpense) {
        this.managementExpense = managementExpense;
    }

    /**
     * 管理费率说明
     * @return management_expense_explain 管理费率说明
     */
    public String getManagementExpenseExplain() {
        return managementExpenseExplain;
    }

    /**
     * 管理费率说明
     * @param managementExpenseExplain 管理费率说明
     */
    public void setManagementExpenseExplain(String managementExpenseExplain) {
        this.managementExpenseExplain = managementExpenseExplain == null ? null : managementExpenseExplain.trim();
    }

    /**
     * 销售费率
     * @return sales_fee 销售费率
     */
    public BigDecimal getSalesFee() {
        return salesFee;
    }

    /**
     * 销售费率
     * @param salesFee 销售费率
     */
    public void setSalesFee(BigDecimal salesFee) {
        this.salesFee = salesFee;
    }

    /**
     * 销售费率说明
     * @return sales_fee_explain 销售费率说明
     */
    public String getSalesFeeExplain() {
        return salesFeeExplain;
    }

    /**
     * 销售费率说明
     * @param salesFeeExplain 销售费率说明
     */
    public void setSalesFeeExplain(String salesFeeExplain) {
        this.salesFeeExplain = salesFeeExplain == null ? null : salesFeeExplain.trim();
    }

    /**
     * 投资利息率
     * @return interest_investment_tax 投资利息率
     */
    public BigDecimal getInterestInvestmentTax() {
        return interestInvestmentTax;
    }

    /**
     * 投资利息率
     * @param interestInvestmentTax 投资利息率
     */
    public void setInterestInvestmentTax(BigDecimal interestInvestmentTax) {
        this.interestInvestmentTax = interestInvestmentTax;
    }

    /**
     * 投资利息率说明
     * @return interest_investment_tax_explain 投资利息率说明
     */
    public String getInterestInvestmentTaxExplain() {
        return interestInvestmentTaxExplain;
    }

    /**
     * 投资利息率说明
     * @param interestInvestmentTaxExplain 投资利息率说明
     */
    public void setInterestInvestmentTaxExplain(String interestInvestmentTaxExplain) {
        this.interestInvestmentTaxExplain = interestInvestmentTaxExplain == null ? null : interestInvestmentTaxExplain.trim();
    }

    /**
     * 销售税金及附加率
     * @return sales_tax_and_additional 销售税金及附加率
     */
    public BigDecimal getSalesTaxAndAdditional() {
        return salesTaxAndAdditional;
    }

    /**
     * 销售税金及附加率
     * @param salesTaxAndAdditional 销售税金及附加率
     */
    public void setSalesTaxAndAdditional(BigDecimal salesTaxAndAdditional) {
        this.salesTaxAndAdditional = salesTaxAndAdditional;
    }

    /**
     * 销售税金及附加率说明
     * @return sales_tax_and_additional_explain 销售税金及附加率说明
     */
    public String getSalesTaxAndAdditionalExplain() {
        return salesTaxAndAdditionalExplain;
    }

    /**
     * 销售税金及附加率说明
     * @param salesTaxAndAdditionalExplain 销售税金及附加率说明
     */
    public void setSalesTaxAndAdditionalExplain(String salesTaxAndAdditionalExplain) {
        this.salesTaxAndAdditionalExplain = salesTaxAndAdditionalExplain == null ? null : salesTaxAndAdditionalExplain.trim();
    }

    /**
     * 开发利润率
     * @return investment_profit_tax 开发利润率
     */
    public BigDecimal getInvestmentProfitTax() {
        return investmentProfitTax;
    }

    /**
     * 开发利润率
     * @param investmentProfitTax 开发利润率
     */
    public void setInvestmentProfitTax(BigDecimal investmentProfitTax) {
        this.investmentProfitTax = investmentProfitTax;
    }

    /**
     * 开发利润率说明
     * @return investment_profit_tax_explain 开发利润率说明
     */
    public String getInvestmentProfitTaxExplain() {
        return investmentProfitTaxExplain;
    }

    /**
     * 开发利润率说明
     * @param investmentProfitTaxExplain 开发利润率说明
     */
    public void setInvestmentProfitTaxExplain(String investmentProfitTaxExplain) {
        this.investmentProfitTaxExplain = investmentProfitTaxExplain == null ? null : investmentProfitTaxExplain.trim();
    }

    /**
     * 在建工程评估价值2
     * @return construction_assessment_value2 在建工程评估价值2
     */
    public BigDecimal getConstructionAssessmentValue2() {
        return constructionAssessmentValue2;
    }

    /**
     * 在建工程评估价值2
     * @param constructionAssessmentValue2 在建工程评估价值2
     */
    public void setConstructionAssessmentValue2(BigDecimal constructionAssessmentValue2) {
        this.constructionAssessmentValue2 = constructionAssessmentValue2;
    }

    /**
     * 土地取得附加成本
     * @return additional_cost_land_acquisition 土地取得附加成本
     */
    public BigDecimal getAdditionalCostLandAcquisition() {
        return additionalCostLandAcquisition;
    }

    /**
     * 土地取得附加成本
     * @param additionalCostLandAcquisition 土地取得附加成本
     */
    public void setAdditionalCostLandAcquisition(BigDecimal additionalCostLandAcquisition) {
        this.additionalCostLandAcquisition = additionalCostLandAcquisition;
    }

    /**
     * 基础设施建设费说明
     * @return infrastructure_cost_explain 基础设施建设费说明
     */
    public String getInfrastructureCostExplain() {
        return infrastructureCostExplain;
    }

    /**
     * 基础设施建设费说明
     * @param infrastructureCostExplain 基础设施建设费说明
     */
    public void setInfrastructureCostExplain(String infrastructureCostExplain) {
        this.infrastructureCostExplain = infrastructureCostExplain == null ? null : infrastructureCostExplain.trim();
    }

    /**
     * 开发期间税费说明
     * @return dev_during_explain 开发期间税费说明
     */
    public String getDevDuringExplain() {
        return devDuringExplain;
    }

    /**
     * 开发期间税费说明
     * @param devDuringExplain 开发期间税费说明
     */
    public void setDevDuringExplain(String devDuringExplain) {
        this.devDuringExplain = devDuringExplain == null ? null : devDuringExplain.trim();
    }

    /**
     * 其它工程费说明
     * @return other_engineering_cost_explain 其它工程费说明
     */
    public String getOtherEngineeringCostExplain() {
        return otherEngineeringCostExplain;
    }

    /**
     * 其它工程费说明
     * @param otherEngineeringCostExplain 其它工程费说明
     */
    public void setOtherEngineeringCostExplain(String otherEngineeringCostExplain) {
        this.otherEngineeringCostExplain = otherEngineeringCostExplain == null ? null : otherEngineeringCostExplain.trim();
    }

    /**
     * 土地取得附加成本说明
     * @return additional_cost_land_acquisition_explain 土地取得附加成本说明
     */
    public String getAdditionalCostLandAcquisitionExplain() {
        return additionalCostLandAcquisitionExplain;
    }

    /**
     * 土地取得附加成本说明
     * @param additionalCostLandAcquisitionExplain 土地取得附加成本说明
     */
    public void setAdditionalCostLandAcquisitionExplain(String additionalCostLandAcquisitionExplain) {
        this.additionalCostLandAcquisitionExplain = additionalCostLandAcquisitionExplain == null ? null : additionalCostLandAcquisitionExplain.trim();
    }

    /**
     * 勘察设计和前期工程费率说明
     * @return reconnaissance_design_explain 勘察设计和前期工程费率说明
     */
    public String getReconnaissanceDesignExplain() {
        return reconnaissanceDesignExplain;
    }

    /**
     * 勘察设计和前期工程费率说明
     * @param reconnaissanceDesignExplain 勘察设计和前期工程费率说明
     */
    public void setReconnaissanceDesignExplain(String reconnaissanceDesignExplain) {
        this.reconnaissanceDesignExplain = reconnaissanceDesignExplain == null ? null : reconnaissanceDesignExplain.trim();
    }

    /**
     * 成新率id
     * @return residue_ratio_id 成新率id
     */
    public Integer getResidueRatioId() {
        return residueRatioId;
    }

    /**
     * 成新率id
     * @param residueRatioId 成新率id
     */
    public void setResidueRatioId(Integer residueRatioId) {
        this.residueRatioId = residueRatioId;
    }

    /**
     * 成新率备注说明
     * @return residue_ratio_remark 成新率备注说明
     */
    public String getResidueRatioRemark() {
        return residueRatioRemark;
    }

    /**
     * 成新率备注说明
     * @param residueRatioRemark 成新率备注说明
     */
    public void setResidueRatioRemark(String residueRatioRemark) {
        this.residueRatioRemark = residueRatioRemark == null ? null : residueRatioRemark.trim();
    }

    /**
     * 成新率
     * @return residue_ratio 成新率
     */
    public BigDecimal getResidueRatio() {
        return residueRatio;
    }

    /**
     * 成新率
     * @param residueRatio 成新率
     */
    public void setResidueRatio(BigDecimal residueRatio) {
        this.residueRatio = residueRatio;
    }

    /**
     * 成本法id
     * @return pid 成本法id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 成本法id
     * @param pid 成本法id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 申报对象中间表id
     * @return center_id 申报对象中间表id
     */
    public Integer getCenterId() {
        return centerId;
    }

    /**
     * 申报对象中间表id
     * @param centerId 申报对象中间表id
     */
    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return gmt_modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 创建时间
     * @return gmt_created 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * json数据
     * @return json_content json数据
     */
    public String getJsonContent() {
        return jsonContent;
    }

    /**
     * json数据
     * @param jsonContent json数据
     */
    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }

    public static MdCostConstruction.Builder builder() {
        return new MdCostConstruction.Builder();
    }

    /**
     * tb_md_cost_construction
     */
    public static class Builder {
        /**
         * tb_md_cost_construction
         */
        private MdCostConstruction obj;

        public Builder() {
            this.obj = new MdCostConstruction();
        }

        /**
         * 
         * @param id 
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 宗地外设定
         * @param parcelSettingOuter 宗地外设定
         */
        public Builder parcelSettingOuter(String parcelSettingOuter) {
            obj.setParcelSettingOuter(parcelSettingOuter);
            return this;
        }

        /**
         * 宗地内设定
         * @param parcelSettingInner 宗地内设定
         */
        public Builder parcelSettingInner(String parcelSettingInner) {
            obj.setParcelSettingInner(parcelSettingInner);
            return this;
        }

        /**
         * 建筑安装工程费
         * @param constructionInstallationEngineeringFee 建筑安装工程费
         */
        public Builder constructionInstallationEngineeringFee(BigDecimal constructionInstallationEngineeringFee) {
            obj.setConstructionInstallationEngineeringFee(constructionInstallationEngineeringFee);
            return this;
        }

        /**
         * 建筑安装工程费ids
         * @param constructionInstallationEngineeringFeeIds 建筑安装工程费ids
         */
        public Builder constructionInstallationEngineeringFeeIds(String constructionInstallationEngineeringFeeIds) {
            obj.setConstructionInstallationEngineeringFeeIds(constructionInstallationEngineeringFeeIds);
            return this;
        }

        /**
         * 在建工程单位价
         * @param constructionAssessmentPriceCorrecting 在建工程单位价
         */
        public Builder constructionAssessmentPriceCorrecting(BigDecimal constructionAssessmentPriceCorrecting) {
            obj.setConstructionAssessmentPriceCorrecting(constructionAssessmentPriceCorrecting);
            return this;
        }

        /**
         * 在建工程评估价值
         * @param constructionAssessmentValue 在建工程评估价值
         */
        public Builder constructionAssessmentValue(String constructionAssessmentValue) {
            obj.setConstructionAssessmentValue(constructionAssessmentValue);
            return this;
        }

        /**
         * 在建工程评估价值2
         * @param constructionAssessmentValue2 在建工程评估价值2
         */
        public Builder constructionAssessmentValue2(BigDecimal constructionAssessmentValue2) {
            obj.setConstructionAssessmentValue2(constructionAssessmentValue2);
            return this;
        }

        /**
         * 投资利润
         * @param investmentProfit 投资利润
         */
        public Builder investmentProfit(String investmentProfit) {
            obj.setInvestmentProfit(investmentProfit);
            return this;
        }

        /**
         * 开发利润率
         * @param investmentProfitTax 开发利润率
         */
        public Builder investmentProfitTax(BigDecimal investmentProfitTax) {
            obj.setInvestmentProfitTax(investmentProfitTax);
            return this;
        }

        /**
         * 开发利润率说明
         * @param investmentProfitTaxExplain 开发利润率说明
         */
        public Builder investmentProfitTaxExplain(String investmentProfitTaxExplain) {
            obj.setInvestmentProfitTaxExplain(investmentProfitTaxExplain);
            return this;
        }

        /**
         * 投资利息
         * @param interestInvestment 投资利息
         */
        public Builder interestInvestment(String interestInvestment) {
            obj.setInterestInvestment(interestInvestment);
            return this;
        }

        /**
         * 投资利息率
         * @param interestInvestmentTax 投资利息率
         */
        public Builder interestInvestmentTax(BigDecimal interestInvestmentTax) {
            obj.setInterestInvestmentTax(interestInvestmentTax);
            return this;
        }

        /**
         * 投资利息率说明
         * @param interestInvestmentTaxExplain 投资利息率说明
         */
        public Builder interestInvestmentTaxExplain(String interestInvestmentTaxExplain) {
            obj.setInterestInvestmentTaxExplain(interestInvestmentTaxExplain);
            return this;
        }

        /**
         * 建设成本小计
         * @param constructionSubtotal 建设成本小计
         */
        public Builder constructionSubtotal(String constructionSubtotal) {
            obj.setConstructionSubtotal(constructionSubtotal);
            return this;
        }

        /**
         * 土地取得成本合计
         * @param landGetCostTotal 土地取得成本合计
         */
        public Builder landGetCostTotal(String landGetCostTotal) {
            obj.setLandGetCostTotal(landGetCostTotal);
            return this;
        }

        /**
         * 开发土地面积
         * @param developLandAreaTax 开发土地面积
         */
        public Builder developLandAreaTax(BigDecimal developLandAreaTax) {
            obj.setDevelopLandAreaTax(developLandAreaTax);
            return this;
        }

        /**
         * 开发建筑面积(㎡)
         * @param developBuildAreaTax 开发建筑面积(㎡)
         */
        public Builder developBuildAreaTax(BigDecimal developBuildAreaTax) {
            obj.setDevelopBuildAreaTax(developBuildAreaTax);
            return this;
        }

        /**
         * 开发期
         * @param developYearNumberTax 开发期
         */
        public Builder developYearNumberTax(BigDecimal developYearNumberTax) {
            obj.setDevelopYearNumberTax(developYearNumberTax);
            return this;
        }

        /**
         * 经济指标id
         * @param economicId 经济指标id
         */
        public Builder economicId(Integer economicId) {
            obj.setEconomicId(economicId);
            return this;
        }

        /**
         * 基准地价法id
         * @param baseLandId 基准地价法id
         */
        public Builder baseLandId(Integer baseLandId) {
            obj.setBaseLandId(baseLandId);
            return this;
        }

        /**
         * 成本逼近法id
         * @param approachId 成本逼近法id
         */
        public Builder approachId(Integer approachId) {
            obj.setApproachId(approachId);
            return this;
        }

        /**
         * 比较法id
         * @param mcId 比较法id
         */
        public Builder mcId(Integer mcId) {
            obj.setMcId(mcId);
            return this;
        }

        /**
         * 土地购买价格
         * @param landPurchasePrice 土地购买价格
         */
        public Builder landPurchasePrice(BigDecimal landPurchasePrice) {
            obj.setLandPurchasePrice(landPurchasePrice);
            return this;
        }

        /**
         * 土地购买价格说明
         * @param landPurchasePriceExplain 土地购买价格说明
         */
        public Builder landPurchasePriceExplain(String landPurchasePriceExplain) {
            obj.setLandPurchasePriceExplain(landPurchasePriceExplain);
            return this;
        }

        /**
         * 土地取得相关税费率
         * @param landGetRelevant 土地取得相关税费率
         */
        public Builder landGetRelevant(BigDecimal landGetRelevant) {
            obj.setLandGetRelevant(landGetRelevant);
            return this;
        }

        /**
         * 土地取得相关税费率说明
         * @param landGetRelevantExplain 土地取得相关税费率说明
         */
        public Builder landGetRelevantExplain(String landGetRelevantExplain) {
            obj.setLandGetRelevantExplain(landGetRelevantExplain);
            return this;
        }

        /**
         * 勘察设计和前期工程费率
         * @param reconnaissanceDesign 勘察设计和前期工程费率
         */
        public Builder reconnaissanceDesign(BigDecimal reconnaissanceDesign) {
            obj.setReconnaissanceDesign(reconnaissanceDesign);
            return this;
        }

        /**
         * 勘察设计和前期工程费率说明
         * @param reconnaissanceDesignExplain 勘察设计和前期工程费率说明
         */
        public Builder reconnaissanceDesignExplain(String reconnaissanceDesignExplain) {
            obj.setReconnaissanceDesignExplain(reconnaissanceDesignExplain);
            return this;
        }

        /**
         * 基础设施建设费
         * @param infrastructureCost 基础设施建设费
         */
        public Builder infrastructureCost(BigDecimal infrastructureCost) {
            obj.setInfrastructureCost(infrastructureCost);
            return this;
        }

        /**
         * 基础设施建设费说明
         * @param infrastructureCostExplain 基础设施建设费说明
         */
        public Builder infrastructureCostExplain(String infrastructureCostExplain) {
            obj.setInfrastructureCostExplain(infrastructureCostExplain);
            return this;
        }

        /**
         * 公共配套设施建设费
         * @param infrastructureMatchingCost 公共配套设施建设费
         */
        public Builder infrastructureMatchingCost(BigDecimal infrastructureMatchingCost) {
            obj.setInfrastructureMatchingCost(infrastructureMatchingCost);
            return this;
        }

        /**
         * 公共配套设施建设费 说明
         * @param infrastructureMatchingCostExplain 公共配套设施建设费 说明
         */
        public Builder infrastructureMatchingCostExplain(String infrastructureMatchingCostExplain) {
            obj.setInfrastructureMatchingCostExplain(infrastructureMatchingCostExplain);
            return this;
        }

        /**
         * 开发期间税费
         * @param devDuring 开发期间税费
         */
        public Builder devDuring(BigDecimal devDuring) {
            obj.setDevDuring(devDuring);
            return this;
        }

        /**
         * 开发期间税费说明
         * @param devDuringExplain 开发期间税费说明
         */
        public Builder devDuringExplain(String devDuringExplain) {
            obj.setDevDuringExplain(devDuringExplain);
            return this;
        }

        /**
         * 其它工程费
         * @param otherEngineeringCost 其它工程费
         */
        public Builder otherEngineeringCost(BigDecimal otherEngineeringCost) {
            obj.setOtherEngineeringCost(otherEngineeringCost);
            return this;
        }

        /**
         * 其它工程费说明
         * @param otherEngineeringCostExplain 其它工程费说明
         */
        public Builder otherEngineeringCostExplain(String otherEngineeringCostExplain) {
            obj.setOtherEngineeringCostExplain(otherEngineeringCostExplain);
            return this;
        }

        /**
         * 不可预见费率
         * @param unforeseenExpenses 不可预见费率
         */
        public Builder unforeseenExpenses(BigDecimal unforeseenExpenses) {
            obj.setUnforeseenExpenses(unforeseenExpenses);
            return this;
        }

        /**
         * 不可预见费率说明
         * @param unforeseenExpensesExplain 不可预见费率说明
         */
        public Builder unforeseenExpensesExplain(String unforeseenExpensesExplain) {
            obj.setUnforeseenExpensesExplain(unforeseenExpensesExplain);
            return this;
        }

        /**
         * 管理费率
         * @param managementExpense 管理费率
         */
        public Builder managementExpense(BigDecimal managementExpense) {
            obj.setManagementExpense(managementExpense);
            return this;
        }

        /**
         * 管理费率说明
         * @param managementExpenseExplain 管理费率说明
         */
        public Builder managementExpenseExplain(String managementExpenseExplain) {
            obj.setManagementExpenseExplain(managementExpenseExplain);
            return this;
        }

        /**
         * 销售费率
         * @param salesFee 销售费率
         */
        public Builder salesFee(BigDecimal salesFee) {
            obj.setSalesFee(salesFee);
            return this;
        }

        /**
         * 销售费率说明
         * @param salesFeeExplain 销售费率说明
         */
        public Builder salesFeeExplain(String salesFeeExplain) {
            obj.setSalesFeeExplain(salesFeeExplain);
            return this;
        }

        /**
         * 销售税金及附加率
         * @param salesTaxAndAdditional 销售税金及附加率
         */
        public Builder salesTaxAndAdditional(BigDecimal salesTaxAndAdditional) {
            obj.setSalesTaxAndAdditional(salesTaxAndAdditional);
            return this;
        }

        /**
         * 销售税金及附加率说明
         * @param salesTaxAndAdditionalExplain 销售税金及附加率说明
         */
        public Builder salesTaxAndAdditionalExplain(String salesTaxAndAdditionalExplain) {
            obj.setSalesTaxAndAdditionalExplain(salesTaxAndAdditionalExplain);
            return this;
        }

        /**
         * 土地取得附加成本
         * @param additionalCostLandAcquisition 土地取得附加成本
         */
        public Builder additionalCostLandAcquisition(BigDecimal additionalCostLandAcquisition) {
            obj.setAdditionalCostLandAcquisition(additionalCostLandAcquisition);
            return this;
        }

        /**
         * 土地取得附加成本说明
         * @param additionalCostLandAcquisitionExplain 土地取得附加成本说明
         */
        public Builder additionalCostLandAcquisitionExplain(String additionalCostLandAcquisitionExplain) {
            obj.setAdditionalCostLandAcquisitionExplain(additionalCostLandAcquisitionExplain);
            return this;
        }

        /**
         * 成新率
         * @param residueRatio 成新率
         */
        public Builder residueRatio(BigDecimal residueRatio) {
            obj.setResidueRatio(residueRatio);
            return this;
        }

        /**
         * 成新率id
         * @param residueRatioId 成新率id
         */
        public Builder residueRatioId(Integer residueRatioId) {
            obj.setResidueRatioId(residueRatioId);
            return this;
        }

        /**
         * 成新率备注说明
         * @param residueRatioRemark 成新率备注说明
         */
        public Builder residueRatioRemark(String residueRatioRemark) {
            obj.setResidueRatioRemark(residueRatioRemark);
            return this;
        }

        /**
         * 成本法id
         * @param pid 成本法id
         */
        public Builder pid(Integer pid) {
            obj.setPid(pid);
            return this;
        }

        /**
         * 申报对象中间表id
         * @param centerId 申报对象中间表id
         */
        public Builder centerId(Integer centerId) {
            obj.setCenterId(centerId);
            return this;
        }

        /**
         * 创建人
         * @param creator 创建人
         */
        public Builder creator(String creator) {
            obj.setCreator(creator);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        /**
         * 创建时间
         * @param gmtCreated 创建时间
         */
        public Builder gmtCreated(Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * json数据
         * @param jsonContent json数据
         */
        public Builder jsonContent(String jsonContent) {
            obj.setJsonContent(jsonContent);
            return this;
        }

        public MdCostConstruction build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        parcelSettingOuter("parcel_setting_outer", "parcelSettingOuter", "VARCHAR", false),
        parcelSettingInner("parcel_setting_inner", "parcelSettingInner", "VARCHAR", false),
        constructionInstallationEngineeringFeeIds("construction_installation_engineering_fee_ids", "constructionInstallationEngineeringFeeIds", "VARCHAR", false),
        constructionAssessmentPriceCorrecting("construction_assessment_price_correcting", "constructionAssessmentPriceCorrecting", "DECIMAL", false),
        constructionAssessmentValue("construction_assessment_value", "constructionAssessmentValue", "VARCHAR", false),
        investmentProfit("investment_profit", "investmentProfit", "VARCHAR", false),
        interestInvestment("interest_investment", "interestInvestment", "VARCHAR", false),
        constructionSubtotal("construction_subtotal", "constructionSubtotal", "VARCHAR", false),
        landGetCostTotal("land_get_cost_total", "landGetCostTotal", "VARCHAR", false),
        developLandAreaTax("develop_land_area_tax", "developLandAreaTax", "DECIMAL", false),
        developBuildAreaTax("develop_build_area_tax", "developBuildAreaTax", "DECIMAL", false),
        developYearNumberTax("develop_year_number_tax", "developYearNumberTax", "DECIMAL", false),
        economicId("economic_id", "economicId", "INTEGER", false),
        baseLandId("base_land_id", "baseLandId", "INTEGER", false),
        approachId("approach_id", "approachId", "INTEGER", false),
        mcId("mc_id", "mcId", "INTEGER", false),
        landPurchasePrice("land_purchase_price", "landPurchasePrice", "DECIMAL", false),
        landPurchasePriceExplain("land_purchase_price_explain", "landPurchasePriceExplain", "VARCHAR", false),
        landGetRelevant("land_get_relevant", "landGetRelevant", "DECIMAL", false),
        landGetRelevantExplain("land_get_relevant_explain", "landGetRelevantExplain", "VARCHAR", false),
        reconnaissanceDesign("reconnaissance_design", "reconnaissanceDesign", "DECIMAL", false),
        constructionInstallationEngineeringFee("construction_installation_engineering_fee", "constructionInstallationEngineeringFee", "DECIMAL", false),
        infrastructureCost("infrastructure_cost", "infrastructureCost", "DECIMAL", false),
        infrastructureMatchingCost("infrastructure_matching_cost", "infrastructureMatchingCost", "DECIMAL", false),
        infrastructureMatchingCostExplain("infrastructure_matching_cost_explain", "infrastructureMatchingCostExplain", "VARCHAR", false),
        devDuring("dev_during", "devDuring", "DECIMAL", false),
        otherEngineeringCost("other_engineering_cost", "otherEngineeringCost", "DECIMAL", false),
        unforeseenExpenses("unforeseen_expenses", "unforeseenExpenses", "DECIMAL", false),
        unforeseenExpensesExplain("unforeseen_expenses_explain", "unforeseenExpensesExplain", "VARCHAR", false),
        managementExpense("management_expense", "managementExpense", "DECIMAL", false),
        managementExpenseExplain("management_expense_explain", "managementExpenseExplain", "VARCHAR", false),
        salesFee("sales_fee", "salesFee", "DECIMAL", false),
        salesFeeExplain("sales_fee_explain", "salesFeeExplain", "VARCHAR", false),
        interestInvestmentTax("interest_investment_tax", "interestInvestmentTax", "DECIMAL", false),
        interestInvestmentTaxExplain("interest_investment_tax_explain", "interestInvestmentTaxExplain", "VARCHAR", false),
        salesTaxAndAdditional("sales_tax_and_additional", "salesTaxAndAdditional", "DECIMAL", false),
        salesTaxAndAdditionalExplain("sales_tax_and_additional_explain", "salesTaxAndAdditionalExplain", "VARCHAR", false),
        investmentProfitTax("investment_profit_tax", "investmentProfitTax", "DECIMAL", false),
        investmentProfitTaxExplain("investment_profit_tax_explain", "investmentProfitTaxExplain", "VARCHAR", false),
        constructionAssessmentValue2("construction_assessment_value2", "constructionAssessmentValue2", "DECIMAL", false),
        additionalCostLandAcquisition("additional_cost_land_acquisition", "additionalCostLandAcquisition", "DECIMAL", false),
        infrastructureCostExplain("infrastructure_cost_explain", "infrastructureCostExplain", "VARCHAR", false),
        devDuringExplain("dev_during_explain", "devDuringExplain", "VARCHAR", false),
        otherEngineeringCostExplain("other_engineering_cost_explain", "otherEngineeringCostExplain", "VARCHAR", false),
        additionalCostLandAcquisitionExplain("additional_cost_land_acquisition_explain", "additionalCostLandAcquisitionExplain", "VARCHAR", false),
        reconnaissanceDesignExplain("reconnaissance_design_explain", "reconnaissanceDesignExplain", "VARCHAR", false),
        residueRatioId("residue_ratio_id", "residueRatioId", "INTEGER", false),
        residueRatioRemark("residue_ratio_remark", "residueRatioRemark", "VARCHAR", false),
        residueRatio("residue_ratio", "residueRatio", "DECIMAL", false),
        pid("pid", "pid", "INTEGER", false),
        centerId("center_id", "centerId", "INTEGER", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        jsonContent("json_content", "jsonContent", "LONGVARCHAR", false);

        /**
         * tb_md_cost_construction
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_md_cost_construction
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_md_cost_construction
         */
        private final String column;

        /**
         * tb_md_cost_construction
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_md_cost_construction
         */
        private final String javaProperty;

        /**
         * tb_md_cost_construction
         */
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}