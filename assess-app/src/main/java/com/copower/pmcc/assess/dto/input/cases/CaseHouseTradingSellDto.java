package com.copower.pmcc.assess.dto.input.cases;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/13 17:57
 * @Description:
 */
public class CaseHouseTradingSellDto {
    private Integer id;

    private Integer houseId;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date instalmentPeriodStart;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date instalmentPeriodEnd;

    private String instalmentInterest;

    private Integer version;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getInstalmentPeriodStart() {
        return instalmentPeriodStart;
    }

    public void setInstalmentPeriodStart(Date instalmentPeriodStart) {
        this.instalmentPeriodStart = instalmentPeriodStart;
    }

    public Date getInstalmentPeriodEnd() {
        return instalmentPeriodEnd;
    }

    public void setInstalmentPeriodEnd(Date instalmentPeriodEnd) {
        this.instalmentPeriodEnd = instalmentPeriodEnd;
    }

    public String getInstalmentInterest() {
        return instalmentInterest;
    }

    public void setInstalmentInterest(String instalmentInterest) {
        this.instalmentInterest = instalmentInterest == null ? null : instalmentInterest.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
