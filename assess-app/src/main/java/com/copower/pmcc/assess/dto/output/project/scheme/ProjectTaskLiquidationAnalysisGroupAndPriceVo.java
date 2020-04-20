package com.copower.pmcc.assess.dto.output.project.scheme;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-10-9.
 */
public class ProjectTaskLiquidationAnalysisGroupAndPriceVo {
    private BigDecimal groupArea;
    private BigDecimal groupPrice;
    private String huxingPriceIds;

    public String getHuxingPriceIds() {
        return huxingPriceIds;
    }

    public void setHuxingPriceIds(String huxingPriceIds) {
        this.huxingPriceIds = huxingPriceIds;
    }

    public BigDecimal getGroupArea() {
        return groupArea;
    }

    public void setGroupArea(BigDecimal groupArea) {
        this.groupArea = groupArea;
    }

    public BigDecimal getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(BigDecimal groupPrice) {
        this.groupPrice = groupPrice;
    }
}
