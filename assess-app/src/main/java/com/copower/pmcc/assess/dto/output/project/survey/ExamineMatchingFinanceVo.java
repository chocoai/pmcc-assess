package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingFinance;

/**
 * @Auther: zch
 * @Date: 2018/7/20 17:13
 * @Description:
 */
public class ExamineMatchingFinanceVo extends ExamineMatchingFinance {
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
