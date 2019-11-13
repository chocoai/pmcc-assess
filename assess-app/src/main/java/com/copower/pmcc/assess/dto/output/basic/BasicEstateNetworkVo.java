package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateNetwork;

/**
 * Created by kings on 2018-12-27.
 */
public class BasicEstateNetworkVo extends BasicEstateNetwork {
    private String supplierName;
    private String serviceContentName;
    private String creatorName;

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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
