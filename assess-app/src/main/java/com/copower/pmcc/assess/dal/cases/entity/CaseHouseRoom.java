package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseHouseRoom {
    private Integer id;

    private Integer houseId;

    private Integer roomType;

    private String name;

    private BigDecimal area;

    private String orientation;

    private String aeration;

    private String illumination;

    private String soundInsulation;

    private String creator;

    private String sunshine;

    private String lighting;

    private String opening;

    private String depth;

    private Double layerHeight;

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

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
    }

    public String getAeration() {
        return aeration;
    }

    public void setAeration(String aeration) {
        this.aeration = aeration == null ? null : aeration.trim();
    }

    public String getIllumination() {
        return illumination;
    }

    public void setIllumination(String illumination) {
        this.illumination = illumination == null ? null : illumination.trim();
    }

    public String getSoundInsulation() {
        return soundInsulation;
    }

    public void setSoundInsulation(String soundInsulation) {
        this.soundInsulation = soundInsulation == null ? null : soundInsulation.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getSunshine() {
        return sunshine;
    }

    public void setSunshine(String sunshine) {
        this.sunshine = sunshine == null ? null : sunshine.trim();
    }

    public String getLighting() {
        return lighting;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting == null ? null : lighting.trim();
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening == null ? null : opening.trim();
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth == null ? null : depth.trim();
    }

    public Double getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(Double layerHeight) {
        this.layerHeight = layerHeight;
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