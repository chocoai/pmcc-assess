package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.ChksApprovalBusiness;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/28
 * @time: 15:58
 */
public class ChksApprovalBusinessVo extends ChksApprovalBusiness {

    private String businessUrl;

    public String getBusinessUrl() {
        return businessUrl;
    }

    public void setBusinessUrl(String businessUrl) {
        this.businessUrl = businessUrl;
    }
}
