package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicHouseHuxingPrice {
    private Integer id;

    private Integer huxingId;

    private Integer houseId;

    private String houseNumber;

    private BigDecimal area;

    private String floor;

    private String adjustFactor;

    private BigDecimal price;

    private String remark;

    private Integer declareId;

    private String declareName;

    private String seat;

    private String aeration;

    private String lighting;

    private String sunshine;

    private String soundInsulation;

    private String length;

    private String width;

    private String adjacentPosition;

    private String orientation;

    private String opening;

    private String depth;

    private String distance;

    private String spanLength;

    private String spanNum;

    private String maxSpan;

    private String minSpan;

    private String standardSpan;

    private String standardMeasure;

    private String storageRequest;

    private String specialFactors;

    private String houseShape;

    private String shapeRemark;

    private BigDecimal layerHeight;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String jsonData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHuxingId() {
        return huxingId;
    }

    public void setHuxingId(Integer huxingId) {
        this.huxingId = huxingId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getAdjustFactor() {
        return adjustFactor;
    }

    public void setAdjustFactor(String adjustFactor) {
        this.adjustFactor = adjustFactor == null ? null : adjustFactor.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public String getDeclareName() {
        return declareName;
    }

    public void setDeclareName(String declareName) {
        this.declareName = declareName == null ? null : declareName.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public String getAeration() {
        return aeration;
    }

    public void setAeration(String aeration) {
        this.aeration = aeration == null ? null : aeration.trim();
    }

    public String getLighting() {
        return lighting;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting == null ? null : lighting.trim();
    }

    public String getSunshine() {
        return sunshine;
    }

    public void setSunshine(String sunshine) {
        this.sunshine = sunshine == null ? null : sunshine.trim();
    }

    public String getSoundInsulation() {
        return soundInsulation;
    }

    public void setSoundInsulation(String soundInsulation) {
        this.soundInsulation = soundInsulation == null ? null : soundInsulation.trim();
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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
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

    public String getSpanNum() {
        return spanNum;
    }

    public void setSpanNum(String spanNum) {
        this.spanNum = spanNum == null ? null : spanNum.trim();
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

    public String getStandardSpan() {
        return standardSpan;
    }

    public void setStandardSpan(String standardSpan) {
        this.standardSpan = standardSpan == null ? null : standardSpan.trim();
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

    public String getSpecialFactors() {
        return specialFactors;
    }

    public void setSpecialFactors(String specialFactors) {
        this.specialFactors = specialFactors == null ? null : specialFactors.trim();
    }

    public String getHouseShape() {
        return houseShape;
    }

    public void setHouseShape(String houseShape) {
        this.houseShape = houseShape == null ? null : houseShape.trim();
    }

    public String getShapeRemark() {
        return shapeRemark;
    }

    public void setShapeRemark(String shapeRemark) {
        this.shapeRemark = shapeRemark == null ? null : shapeRemark.trim();
    }

    public BigDecimal getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(BigDecimal layerHeight) {
        this.layerHeight = layerHeight;
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

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData == null ? null : jsonData.trim();
    }
}