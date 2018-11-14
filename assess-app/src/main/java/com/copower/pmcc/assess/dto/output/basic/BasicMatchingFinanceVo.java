package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingFinance;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:05
 * @Description:
 */
public class BasicMatchingFinanceVo extends BasicMatchingFinance {
    private String categoryName;
    private String natureName;
    private String serviceContentName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getServiceContentName() {
        return serviceContentName;
    }

    public void setServiceContentName(String serviceContentName) {
        this.serviceContentName = serviceContentName;
    }
}
