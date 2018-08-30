package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTrading;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineHouseTradingVo extends ExamineHouseTrading {
    private String tradingTypeName;
    private String descriptionTypeName;

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
}
