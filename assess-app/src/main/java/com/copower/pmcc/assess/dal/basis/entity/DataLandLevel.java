package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataLandLevel {
    private Integer id;

    private String province;

    private String city;

    private String district;

    private Date releaseDate;

    private Date valuationDate;

    private Date executionTime;

    private String title;

    private String wordSymbol;

    private String landDefinition;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer landRightType;

    private String townShipName;

    private String status;

    private String processInsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getWordSymbol() {
        return wordSymbol;
    }

    public void setWordSymbol(String wordSymbol) {
        this.wordSymbol = wordSymbol == null ? null : wordSymbol.trim();
    }

    public String getLandDefinition() {
        return landDefinition;
    }

    public void setLandDefinition(String landDefinition) {
        this.landDefinition = landDefinition == null ? null : landDefinition.trim();
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

    public Integer getLandRightType() {
        return landRightType;
    }

    public void setLandRightType(Integer landRightType) {
        this.landRightType = landRightType;
    }

    public String getTownShipName() {
        return townShipName;
    }

    public void setTownShipName(String townShipName) {
        this.townShipName = townShipName == null ? null : townShipName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }
}