package com.copower.pmcc.assess.dto.input.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BasicHouseCaseSummaryParamsDto implements Serializable {
    @DateTimeFormat(pattern = DateUtils.DATE_PATTERN)
    private Date startDate;
    @DateTimeFormat(pattern = DateUtils.DATE_PATTERN)
    private Date endDate;
    private java.math.BigDecimal areaStart;
    private BigDecimal areaEnd;
    @DateTimeFormat(pattern = DateUtils.DATE_PATTERN)
    private Date tradingTimeStart;
    @DateTimeFormat(pattern = DateUtils.DATE_PATTERN)
    private Date tradingTimeEnd;
    private BasicHouseCaseSummary houseCaseSummary;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAreaStart() {
        return areaStart;
    }

    public void setAreaStart(BigDecimal areaStart) {
        this.areaStart = areaStart;
    }

    public BigDecimal getAreaEnd() {
        return areaEnd;
    }

    public void setAreaEnd(BigDecimal areaEnd) {
        this.areaEnd = areaEnd;
    }

    public Date getTradingTimeStart() {
        return tradingTimeStart;
    }

    public void setTradingTimeStart(Date tradingTimeStart) {
        this.tradingTimeStart = tradingTimeStart;
    }

    public Date getTradingTimeEnd() {
        return tradingTimeEnd;
    }

    public void setTradingTimeEnd(Date tradingTimeEnd) {
        this.tradingTimeEnd = tradingTimeEnd;
    }

    public BasicHouseCaseSummary getHouseCaseSummary() {
        return houseCaseSummary == null ? new BasicHouseCaseSummary() : houseCaseSummary;
    }

    public void setHouseCaseSummary(BasicHouseCaseSummary houseCaseSummary) {
        this.houseCaseSummary = houseCaseSummary;
    }
}
