package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dto.input.data.HousePriceIndexDto;

/**
 * Created by 13426 on 2018/4/23.
 */
public class HousePriceIndexVo  extends HousePriceIndexDto {
    private String yearMonthString;
    private String yearMonthSource;

    public String getYearMonthString() {
        return yearMonthString;
    }

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
