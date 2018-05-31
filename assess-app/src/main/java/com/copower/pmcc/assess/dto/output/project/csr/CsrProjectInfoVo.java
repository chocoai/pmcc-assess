package com.copower.pmcc.assess.dto.output.project.csr;

import com.copower.pmcc.assess.dal.entity.CsrProjectInfo;

/**
 * Created by kings on 2018-5-31.
 */
public class CsrProjectInfoVo extends CsrProjectInfo {
    private String distributionUserName;
    private String customerTypeName;
    private String entrustPurposeName;

    public String getDistributionUserName() {
        return distributionUserName;
    }

    public void setDistributionUserName(String distributionUserName) {
        this.distributionUserName = distributionUserName;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }
}
