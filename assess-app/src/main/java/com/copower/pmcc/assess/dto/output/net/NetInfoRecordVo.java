package com.copower.pmcc.assess.dto.output.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;

public class NetInfoRecordVo extends NetInfoRecord {
    private String unitPrice;//单价
    private String liquidCycle;//变现周期
    private String statusName;//状态

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLiquidCycle() {
        return liquidCycle;
    }

    public void setLiquidCycle(String liquidCycle) {
        this.liquidCycle = liquidCycle;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
