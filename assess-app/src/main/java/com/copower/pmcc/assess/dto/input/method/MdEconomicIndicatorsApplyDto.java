package com.copower.pmcc.assess.dto.input.method;

import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicators;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItem;

import java.util.List;

/**
 * Created by wangpc on 2019-9-6.
 */
public class MdEconomicIndicatorsApplyDto {
    private MdEconomicIndicators economicIndicators;
    private List<MdEconomicIndicatorsItem> economicIndicatorsItemList;

    public MdEconomicIndicators getEconomicIndicators() {
        return economicIndicators;
    }

    public void setEconomicIndicators(MdEconomicIndicators economicIndicators) {
        this.economicIndicators = economicIndicators;
    }

    public List<MdEconomicIndicatorsItem> getEconomicIndicatorsItemList() {
        return economicIndicatorsItemList;
    }

    public void setEconomicIndicatorsItemList(List<MdEconomicIndicatorsItem> economicIndicatorsItemList) {
        this.economicIndicatorsItemList = economicIndicatorsItemList;
    }
}
