package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicHouseRoom {
    private Integer id;

    private Integer houseId;

    private String roomType;

    private String name;

    private BigDecimal area;

    private String orientation;

    private String aeration;

    private String illumination;

    private String soundInsulation;

    private String sunshine;

    private String lighting;

    private String opening;

    private String depth;

    private BigDecimal layerHeight;

    private BigDecimal clearHeight;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String houseShape;

    private String length;

    private String width;

    private String adjacentPosition;

    private String distance;

    private String spanLength;

    private String standardSpan;

    private String maxSpan;

    private String minSpan;

    private String standardMeasure;

    private String storageRequest;

    private String spanNum;

    private String currentFloor;

    private String shapeRemark;

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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
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

    public BigDecimal getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(BigDecimal layerHeight) {
        this.layerHeight = layerHeight;
    }

    public BigDecimal getClearHeight() {
        return clearHeight;
    }

    public void setClearHeight(BigDecimal clearHeight) {
        this.clearHeight = clearHeight;
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

    public String getHouseShape() {
        return houseShape;
    }

    public void setHouseShape(String houseShape) {
        this.houseShape = houseShape == null ? null : houseShape.trim();
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    public String getAdjacentPosition() {
        return adjacentPosition;
    }

    public void setAdjacentPosition(String adjacentPosition) {
        this.adjacentPosition = adjacentPosition == null ? null : adjacentPosition.trim();
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
    }

    public String getSpanLength() {
        return spanLength;
    }

    public void setSpanLength(String spanLength) {
        this.spanLength = spanLength == null ? null : spanLength.trim();
    }

    public String getStandardSpan() {
        return standardSpan;
    }

    public void setStandardSpan(String standardSpan) {
        this.standardSpan = standardSpan == null ? null : standardSpan.trim();
    }

    public String getMaxSpan() {
        return maxSpan;
    }

    public void setMaxSpan(String maxSpan) {
        this.maxSpan = maxSpan == null ? null : maxSpan.trim();
    }

    public String getMinSpan() {
        return minSpan;
    }

    public void setMinSpan(String minSpan) {
        this.minSpan = minSpan == null ? null : minSpan.trim();
    }

    public String getStandardMeasure() {
        return standardMeasure;
    }

    public void setStandardMeasure(String standardMeasure) {
        this.standardMeasure = standardMeasure == null ? null : standardMeasure.trim();
    }

    public String getStorageRequest() {
        return storageRequest;
    }

    public void setStorageRequest(String storageRequest) {
        this.storageRequest = storageRequest == null ? null : storageRequest.trim();
    }

    public String getSpanNum() {
        return spanNum;
    }

    public void setSpanNum(String spanNum) {
        this.spanNum = spanNum == null ? null : spanNum.trim();
    }

    public String getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(String currentFloor) {
        this.currentFloor = currentFloor == null ? null : currentFloor.trim();
    }

    public String getShapeRemark() {
        return shapeRemark;
    }

    public void setShapeRemark(String shapeRemark) {
        this.shapeRemark = shapeRemark == null ? null : shapeRemark.trim();
    }
}