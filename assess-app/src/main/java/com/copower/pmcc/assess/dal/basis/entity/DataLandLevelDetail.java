package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataLandLevelDetail {
    private Integer id;

    private Integer pid;

    private Integer landLevelId;

    private Integer classify;

    private Integer type;

    private String category;

    private BigDecimal price;

    private BigDecimal muPrice;

    private String landAcquisitionProportion;

    private BigDecimal volumeRate;

    private BigDecimal legalAge;

    private String mainStreet;

    private String levelRange;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLandLevelId() {
        return landLevelId;
    }

    public void setLandLevelId(Integer landLevelId) {
        this.landLevelId = landLevelId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMuPrice() {
        return muPrice;
    }

    public void setMuPrice(BigDecimal muPrice) {
        this.muPrice = muPrice;
    }

    public String getLandAcquisitionProportion() {
        return landAcquisitionProportion;
    }

    public void setLandAcquisitionProportion(String landAcquisitionProportion) {
        this.landAcquisitionProportion = landAcquisitionProportion == null ? null : landAcquisitionProportion.trim();
    }

    public BigDecimal getVolumeRate() {
        return volumeRate;
    }

    public void setVolumeRate(BigDecimal volumeRate) {
        this.volumeRate = volumeRate;
    }

    public BigDecimal getLegalAge() {
        return legalAge;
    }

    public void setLegalAge(BigDecimal legalAge) {
        this.legalAge = legalAge;
    }

    public String getMainStreet() {
        return mainStreet;
    }

    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet == null ? null : mainStreet.trim();
    }

    public String getLevelRange() {
        return levelRange;
    }

    public void setLevelRange(String levelRange) {
        this.levelRange = levelRange == null ? null : levelRange.trim();
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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