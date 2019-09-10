package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastItem;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeForecastItemVo extends MdIncomeForecastItem {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
