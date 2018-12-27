package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetwork;

/**
 * @Auther: zch
 * @Date: 2018/9/17 11:04
 * @Description:
 */
public class CaseEstateNetworkVo extends CaseEstateNetwork {
    private String supplierName;
    private String serviceContentName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getServiceContentName() {
        return serviceContentName;
    }

    public void setServiceContentName(String serviceContentName) {
        this.serviceContentName = serviceContentName;
    }
}
