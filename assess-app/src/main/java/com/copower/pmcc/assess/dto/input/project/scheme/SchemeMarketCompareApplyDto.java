package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfo;
import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeMarketCompareApplyDto {
    private MarketCompareResultDto marketCompare;
    private List<SchemeSupportInfo> supportInfoList;

    public MarketCompareResultDto getMarketCompare() {
        return marketCompare;
    }

    public void setMarketCompare(MarketCompareResultDto marketCompare) {
        this.marketCompare = marketCompare;
    }

    public List<SchemeSupportInfo> getSupportInfoList() {
        return supportInfoList;
    }

    public void setSupportInfoList(List<SchemeSupportInfo> supportInfoList) {
        this.supportInfoList = supportInfoList;
    }
}
