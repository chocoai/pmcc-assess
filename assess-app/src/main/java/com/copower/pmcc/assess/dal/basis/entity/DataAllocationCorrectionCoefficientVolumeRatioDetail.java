package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataAllocationCorrectionCoefficientVolumeRatioDetail {
    private Integer id;

    private Integer allocationVolumeRatioId;

    private BigDecimal plotRatio;

    private BigDecimal correctionFactor;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAllocationVolumeRatioId() {
        return allocationVolumeRatioId;
    }

    public void setAllocationVolumeRatioId(Integer allocationVolumeRatioId) {
        this.allocationVolumeRatioId = allocationVolumeRatioId;
    }

    public BigDecimal getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(BigDecimal plotRatio) {
        this.plotRatio = plotRatio;
    }

    public BigDecimal getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(BigDecimal correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}