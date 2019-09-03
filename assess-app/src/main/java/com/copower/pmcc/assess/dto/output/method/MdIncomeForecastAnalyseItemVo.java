package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyseItem;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeForecastAnalyseItemVo extends MdIncomeForecastAnalyseItem {
    private String name;
    private String quantitativeTrend;
    private String moneyTrend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantitativeTrend() {
        return quantitativeTrend;
    }

    public void setQuantitativeTrend(String quantitativeTrend) {
        this.quantitativeTrend = quantitativeTrend;
    }

    public String getMoneyTrend() {
        return moneyTrend;
    }

    public void setMoneyTrend(String moneyTrend) {
        this.moneyTrend = moneyTrend;
    }
}
