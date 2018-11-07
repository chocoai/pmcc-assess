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
    private String informationTypeName;
    private String paymentMethodName;
    private String normalTransactionName;
    private String descriptionContentName;
    private String descriptionTypeName;
    private String taxBurdenName;

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
}
