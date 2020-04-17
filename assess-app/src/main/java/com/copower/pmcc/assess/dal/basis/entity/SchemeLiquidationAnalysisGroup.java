package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SchemeLiquidationAnalysisGroup {
    private Integer id;

    private Integer projectId;

    private Integer areaId;

    private String recordIds;

    private Integer planDetailsId;

    private BigDecimal total;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private BigDecimal area;

    private BigDecimal buyerTotal;

    private BigDecimal sellerTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(String recordIds) {
        this.recordIds = recordIds == null ? null : recordIds.trim();
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getBuyerTotal() {
        return buyerTotal;
    }

    public void setBuyerTotal(BigDecimal buyerTotal) {
        this.buyerTotal = buyerTotal;
    }

    public BigDecimal getSellerTotal() {
        return sellerTotal;
    }

    public void setSellerTotal(BigDecimal sellerTotal) {
        this.sellerTotal = sellerTotal;
    }
}