package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastMonth;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeForecastMonthVo extends MdIncomeForecastMonth {
    private String accountingSubjectName;

    public String getAccountingSubjectName() {
        return accountingSubjectName;
    }

    public void setAccountingSubjectName(String accountingSubjectName) {
        this.accountingSubjectName = accountingSubjectName;
    }
}
