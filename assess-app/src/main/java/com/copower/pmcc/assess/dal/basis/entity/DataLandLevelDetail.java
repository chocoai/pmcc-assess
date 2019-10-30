package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataLandLevelDetail {
    private Integer id;

    private Integer landLevelId;

    private String classify;

    private String type;

    private String category;

    private String levelRange;

    private String mainStreet;

    private Boolean bisDelete;

    private String landAcquisitionProportion;

    private BigDecimal price;

    private BigDecimal muPrice;

    private BigDecimal volumeRate;

    private BigDecimal legalAge;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLandLevelId() {
        return landLevelId;
    }

    public void setLandLevelId(Integer landLevelId) {
        this.landLevelId = landLevelId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getLevelRange() {
        return levelRange;
    }

    public void setLevelRange(String levelRange) {
        this.levelRange = levelRange == null ? null : levelRange.trim();
    }

    public String getMainStreet() {
        return mainStreet;
    }

    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet == null ? null : mainStreet.trim();
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
    }

    public String getLandAcquisitionProportion() {
        return landAcquisitionProportion;
    }

    public void setLandAcquisitionProportion(String landAcquisitionProportion) {
        this.landAcquisitionProportion = landAcquisitionProportion == null ? null : landAcquisitionProportion.trim();
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}