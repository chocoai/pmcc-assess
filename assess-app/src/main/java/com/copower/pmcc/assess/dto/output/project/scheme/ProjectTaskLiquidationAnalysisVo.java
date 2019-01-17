package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocation;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-10-9.
 */
public class ProjectTaskLiquidationAnalysisVo extends DataTaxRateAllocation {
    private BigDecimal money;
    private String typeName;
    private String remark;
    private BigDecimal price;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
