package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLease;

import java.util.Date;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeLeaseVo extends MdIncomeLease {
    private Date beginDate;
    private Date endDate;
    private Integer yearCount;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getYearCount() {
        return yearCount;
    }

    public void setYearCount(Integer yearCount) {
        this.yearCount = yearCount;
    }
}
