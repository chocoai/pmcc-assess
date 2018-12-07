package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;

/**
 * Created by kings on 2018-12-5.
 */
public class CaseBaseHouseVo extends CaseBaseHouse {
    private String practicalUseName;
    private String tradingTypeName;

    public String getPracticalUseName() {
        return practicalUseName;
    }

    public void setPracticalUseName(String practicalUseName) {
        this.practicalUseName = practicalUseName;
    }

    public String getTradingTypeName() {
        return tradingTypeName;
    }

    public void setTradingTypeName(String tradingTypeName) {
        this.tradingTypeName = tradingTypeName;
    }
}
