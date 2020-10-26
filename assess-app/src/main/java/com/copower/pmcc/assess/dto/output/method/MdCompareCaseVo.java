package com.copower.pmcc.assess.dto.output.method;

import java.math.BigDecimal;

/**
 * Created by kings on 2019-4-26.
 */
public class MdCompareCaseVo {
    private Integer planDetailsId;
    private String name;
    private BigDecimal area;
    private String areaDesc;
    private BigDecimal price;

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
