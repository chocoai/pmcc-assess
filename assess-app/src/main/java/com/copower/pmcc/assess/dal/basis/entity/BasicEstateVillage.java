package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicEstateVillage {
    private Integer id;

    private Integer estateId;

    private String district;

    private String villageStreet;

    private String burgStreet;

    private String numberGroup;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Boolean bisDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getVillageStreet() {
        return villageStreet;
    }

    public void setVillageStreet(String villageStreet) {
        this.villageStreet = villageStreet == null ? null : villageStreet.trim();
    }

    public String getBurgStreet() {
        return burgStreet;
    }

    public void setBurgStreet(String burgStreet) {
        this.burgStreet = burgStreet == null ? null : burgStreet.trim();
    }

    public String getNumberGroup() {
        return numberGroup;
    }

    public void setNumberGroup(String numberGroup) {
        this.numberGroup = numberGroup == null ? null : numberGroup.trim();
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

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
    }
}