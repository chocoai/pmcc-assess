package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTrading;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineHouseTradingVo extends ExamineHouseTrading {
    private String tradingTypeName;
    private String descriptionTypeName;
    private String paymentMethodName;

    private String normalTransactionName;
    private String informationTypeName;

    private String taxBurdenName;

    public String getTradingTypeName() {
        return tradingTypeName;
    }

    public void setTradingTypeName(String tradingTypeName) {
        this.tradingTypeName = tradingTypeName;
    }

    public String getDescriptionTypeName() {
        return descriptionTypeName;
    }

    public void setDescriptionTypeName(String descriptionTypeName) {
        this.descriptionTypeName = descriptionTypeName;
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

    public String getTaxBurdenName() {
        return taxBurdenName;
    }

    public void setTaxBurdenName(String taxBurdenName) {
        this.taxBurdenName = taxBurdenName;
    }

    public String getInformationTypeName() {
        return informationTypeName;
    }

    public void setInformationTypeName(String informationTypeName) {
        this.informationTypeName = informationTypeName;
    }
}
