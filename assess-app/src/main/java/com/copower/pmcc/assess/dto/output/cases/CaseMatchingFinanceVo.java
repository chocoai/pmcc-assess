package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingFinance;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:49
 * @Description:
 */
public class CaseMatchingFinanceVo extends CaseMatchingFinance {
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
