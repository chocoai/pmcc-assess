package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;

/**
 * @Auther: zch
 * @Date: 2018/9/13 17:59
 * @Description:
 */
public class CaseHouseTradingVo extends CaseHouseTrading {
    private String tradingTimeName;
    private String tradingTypeName;
    private String paymentMethodName;
    private String transactionSituationName;
    private String descriptionTypeName;
    private String taxBurdenName;
    private String informationTypeName;
    private String informationCategoryName;
    private String financingConditionsName;
    private String scopePropertyName;
    public String getTradingTimeName() {
        return tradingTimeName;
    }

    public void setTradingTimeName(String tradingTimeName) {
        this.tradingTimeName = tradingTimeName;
    }

    public String getTradingTypeName() {
        return tradingTypeName;
    }

    public void setTradingTypeName(String tradingTypeName) {
        this.tradingTypeName = tradingTypeName;
    }

    public String getInformationTypeName() {
        return informationTypeName;
    }

    public void setInformationTypeName(String informationTypeName) {
        this.informationTypeName = informationTypeName;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getTransactionSituationName() {
        return transactionSituationName;
    }

    public void setTransactionSituationName(String transactionSituationName) {
        this.transactionSituationName = transactionSituationName;
    }

    public String getDescriptionTypeName() {
        return descriptionTypeName;
    }

    public void setDescriptionTypeName(String descriptionTypeName) {
        this.descriptionTypeName = descriptionTypeName;
    }

    public String getTaxBurdenName() {
        return taxBurdenName;
    }

    public void setTaxBurdenName(String taxBurdenName) {
        this.taxBurdenName = taxBurdenName;
    }

    public String getInformationCategoryName() {
        return informationCategoryName;
    }

    public void setInformationCategoryName(String informationCategoryName) {
        this.informationCategoryName = informationCategoryName;
    }

    public String getFinancingConditionsName() {
        return financingConditionsName;
    }

    public void setFinancingConditionsName(String financingConditionsName) {
        this.financingConditionsName = financingConditionsName;
    }

    public String getScopePropertyName() {
        return scopePropertyName;
    }

    public void setScopePropertyName(String scopePropertyName) {
        this.scopePropertyName = scopePropertyName;
    }
}
