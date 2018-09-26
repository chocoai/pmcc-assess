package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeHistory;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeHistoryVo extends MdIncomeHistory {
    private String accountingSubjectName;
    private String firstLevelNumberName;
    private String secondLevelNumberName;

    public String getAccountingSubjectName() {
        return accountingSubjectName;
    }

    public void setAccountingSubjectName(String accountingSubjectName) {
        this.accountingSubjectName = accountingSubjectName;
    }

    public String getFirstLevelNumberName() {
        return firstLevelNumberName;
    }

    public void setFirstLevelNumberName(String firstLevelNumberName) {
        this.firstLevelNumberName = firstLevelNumberName;
    }

    public String getSecondLevelNumberName() {
        return secondLevelNumberName;
    }

    public void setSecondLevelNumberName(String secondLevelNumberName) {
        this.secondLevelNumberName = secondLevelNumberName;
    }
}
