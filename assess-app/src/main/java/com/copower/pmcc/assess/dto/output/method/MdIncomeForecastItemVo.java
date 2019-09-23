package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastItem;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeForecastItemVo extends MdIncomeForecastItem {
    private String name;
    private BigDecimal total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
