package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseTrading;

/**
 * @Auther: zch
 * @Date: 2018/11/2 16:03
 * @Description:
 */
public class BasicHouseTradingVo extends BasicHouseTrading {
    private String tradingTimeName;
    private String tradingTypeName;
    private String informationTypeName;
    private String paymentMethodName;
    private String normalTransactionName;
    private String descriptionContentName;
    private String descriptionTypeName;
    private String taxBurdenName;
    private String informationName;
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

    public String getNormalTransactionName() {
        return normalTransactionName;
    }

    public void setNormalTransactionName(String normalTransactionName) {
        this.normalTransactionName = normalTransactionName;
    }

    public String getDescriptionContentName() {
        return descriptionContentName;
    }

    public void setDescriptionContentName(String descriptionContentName) {
        this.descriptionContentName = descriptionContentName;
    }


    public String getTaxBurdenName() {
        return taxBurdenName;
    }

    public void setTaxBurdenName(String taxBurdenName) {
        this.taxBurdenName = taxBurdenName;
    }

    public String getDescriptionTypeName() {
        return descriptionTypeName;
    }

    public void setDescriptionTypeName(String descriptionTypeName) {
        this.descriptionTypeName = descriptionTypeName;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
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
