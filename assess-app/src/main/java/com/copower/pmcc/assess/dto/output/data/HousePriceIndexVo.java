package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.HousePriceIndex;

/**
 * Created by kings on 2018-4-23.
 */
public class HousePriceIndexVo extends HousePriceIndex {
    private String yearMonthString;
    private String yearMonthSource;

    @Override
    public String getYearMonthString() {
        return yearMonthString;
    }

    @Override
    public void setYearMonthString(String yearMonthString) {
        this.yearMonthString = yearMonthString;
    }

    public String getYearMonthSource() {
        return yearMonthSource;
    }

    public void setYearMonthSource(String yearMonthSource) {
        this.yearMonthSource = yearMonthSource;
    }
}
