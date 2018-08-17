package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCost;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeSelfSupportCostVo extends MdIncomeSelfSupportCost {
    private String accountingSubjectName;
    private String costTypeName;
    private String costCategoryName;

    public String getAccountingSubjectName() {
        return accountingSubjectName;
    }

    public void setAccountingSubjectName(String accountingSubjectName) {
        this.accountingSubjectName = accountingSubjectName;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
    }

    public String getCostCategoryName() {
        return costCategoryName;
    }

    public void setCostCategoryName(String costCategoryName) {
        this.costCategoryName = costCategoryName;
    }
}
