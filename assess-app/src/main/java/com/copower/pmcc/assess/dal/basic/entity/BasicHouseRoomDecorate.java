package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicHouseRoomDecorate {
    private Integer id;

    private Integer caseRoomDecorateId;

    private Integer roomId;

    private Integer part;

    private Integer material;

    private String materialPrice;

    private String constructionTechnology;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseRoomDecorateId() {
        return caseRoomDecorateId;
    }

    public void setCaseRoomDecorateId(Integer caseRoomDecorateId) {
        this.caseRoomDecorateId = caseRoomDecorateId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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

    public String getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(String materialPrice) {
        this.materialPrice = materialPrice == null ? null : materialPrice.trim();
    }

    public String getConstructionTechnology() {
        return constructionTechnology;
    }

    public void setConstructionTechnology(String constructionTechnology) {
        this.constructionTechnology = constructionTechnology == null ? null : constructionTechnology.trim();
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