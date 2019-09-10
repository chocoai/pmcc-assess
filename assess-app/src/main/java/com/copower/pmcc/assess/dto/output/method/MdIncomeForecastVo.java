package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecast;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-8-15.
 */
public class MdIncomeForecastVo extends MdIncomeForecast {
    private Date beginDate;
    private Date endDate;
    private BigDecimal yearCount;
    private BigDecimal incomeTotal;

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

    public BigDecimal getYearCount() {
        return yearCount;
    }

    public void setYearCount(BigDecimal yearCount) {
        this.yearCount = yearCount;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }
}
