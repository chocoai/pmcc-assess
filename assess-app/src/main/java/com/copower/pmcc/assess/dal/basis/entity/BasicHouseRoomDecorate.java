package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicHouseRoomDecorate {
    private Integer id;

    private Integer roomId;

    private Integer houseId;

    private Integer huxingPriceId;

    private Integer part;

    private Integer material;

    private Integer constructionTechnology;

    private Integer materialPrice;

    private String remark;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String location;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getHuxingPriceId() {
        return huxingPriceId;
    }

    public void setHuxingPriceId(Integer huxingPriceId) {
        this.huxingPriceId = huxingPriceId;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public Integer getConstructionTechnology() {
        return constructionTechnology;
    }

    public void setConstructionTechnology(Integer constructionTechnology) {
        this.constructionTechnology = constructionTechnology;
    }

    public Integer getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(Integer materialPrice) {
        this.materialPrice = materialPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}