package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeMarketCompareApplyDto {
    private MarketCompareResultDto marketCompare;

    public MarketCompareResultDto getMarketCompare() {
        return marketCompare;
    }

    public void setMarketCompare(MarketCompareResultDto marketCompare) {
        this.marketCompare = marketCompare;
    }

}
