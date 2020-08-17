package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicHouseRoom implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer houseId;

    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 方位
     */
    private String orientation;

    /**
     * 通风
     */
    private String aeration;

    /**
     * 光照
     */
    private String illumination;

    /**
     * 隔音
     */
    private String soundInsulation;

    /**
     * 日照
     */
    private String sunshine;

    /**
     * 采光
     */
    private String lighting;

    /**
     * 开间
     */
    private String opening;

    /**
     * 进深
     */
    private String depth;

    /**
     * 层高
     */
    private BigDecimal layerHeight;

    /**
     * 净高
     */
    private BigDecimal clearHeight;

    /**
     * 
     */
    private Boolean bisDelete;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;

    /**
     * 房间形状
     */
    private String houseShape;

    /**
     * 长度
     */
    private String length;

    /**
     * 宽度
     */
    private String width;

    /**
     * 相邻位置
     */
    private String adjacentPosition;

    /**
     * 距离
     */
    private String distance;

    /**
     * 跨长
     */
    private String spanLength;

    /**
     * 标准跨距
     */
    private String standardSpan;

    /**
     * 最长跨距
     */
    private String maxSpan;

    /**
     * 最小跨距
     */
    private String minSpan;

    /**
     * 标准计量
     */
    private String standardMeasure;

    /**
     * 仓储要求
     */
    private String storageRequest;

    /**
     * 跨数
     */
    private String spanNum;

    /**
     * 所在楼层
     */
    private String currentFloor;

    /**
     * 形状说明
     */
    private String shapeRemark;

    /**
     * 特殊因素
     */
    private String specialFactors;

    /**
     * tb_basic_house_room
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return house_id 
     */
    public Integer getHouseId() {
        return houseId;
    }

    /**
     * 
     * @param houseId 
     */
    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    /**
     * 房间类型
     * @return room_type 房间类型
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * 房间类型
     * @param roomType 房间类型
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    /**
     * 房间名称
     * @return name 房间名称
     */
    public String getName() {
        return name;
    }

    /**
     * 房间名称
     * @param name 房间名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 面积
     * @return area 面积
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * 面积
     * @param area 面积
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * 方位
     * @return orientation 方位
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * 方位
     * @param orientation 方位
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
    }

    /**
     * 通风
     * @return aeration 通风
     */
    public String getAeration() {
        return aeration;
    }

    /**
     * 通风
     * @param aeration 通风
     */
    public void setAeration(String aeration) {
        this.aeration = aeration == null ? null : aeration.trim();
    }

    /**
     * 光照
     * @return Illumination 光照
     */
    public String getIllumination() {
        return illumination;
    }

    /**
     * 光照
     * @param illumination 光照
     */
    public void setIllumination(String illumination) {
        this.illumination = illumination == null ? null : illumination.trim();
    }

    /**
     * 隔音
     * @return sound_insulation 隔音
     */
    public String getSoundInsulation() {
        return soundInsulation;
    }

    /**
     * 隔音
     * @param soundInsulation 隔音
     */
    public void setSoundInsulation(String soundInsulation) {
        this.soundInsulation = soundInsulation == null ? null : soundInsulation.trim();
    }

    /**
     * 日照
     * @return sunshine 日照
     */
    public String getSunshine() {
        return sunshine;
    }

    /**
     * 日照
     * @param sunshine 日照
     */
    public void setSunshine(String sunshine) {
        this.sunshine = sunshine == null ? null : sunshine.trim();
    }

    /**
     * 采光
     * @return lighting 采光
     */
    public String getLighting() {
        return lighting;
    }

    /**
     * 采光
     * @param lighting 采光
     */
    public void setLighting(String lighting) {
        this.lighting = lighting == null ? null : lighting.trim();
    }

    /**
     * 开间
     * @return opening 开间
     */
    public String getOpening() {
        return opening;
    }

    /**
     * 开间
     * @param opening 开间
     */
    public void setOpening(String opening) {
        this.opening = opening == null ? null : opening.trim();
    }

    /**
     * 进深
     * @return depth 进深
     */
    public String getDepth() {
        return depth;
    }

    /**
     * 进深
     * @param depth 进深
     */
    public void setDepth(String depth) {
        this.depth = depth == null ? null : depth.trim();
    }

    /**
     * 层高
     * @return layer_height 层高
     */
    public BigDecimal getLayerHeight() {
        return layerHeight;
    }

    /**
     * 层高
     * @param layerHeight 层高
     */
    public void setLayerHeight(BigDecimal layerHeight) {
        this.layerHeight = layerHeight;
    }

    /**
     * 净高
     * @return clear_height 净高
     */
    public BigDecimal getClearHeight() {
        return clearHeight;
    }

    /**
     * 净高
     * @param clearHeight 净高
     */
    public void setClearHeight(BigDecimal clearHeight) {
        this.clearHeight = clearHeight;
    }

    /**
     * 
     * @return bis_delete 
     */
    public Boolean getBisDelete() {
        return bisDelete;
    }

    /**
     * 
     * @param bisDelete 
     */
    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return gmt_created 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return gmt_modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 房间形状
     * @return house_shape 房间形状
     */
    public String getHouseShape() {
        return houseShape;
    }

    /**
     * 房间形状
     * @param houseShape 房间形状
     */
    public void setHouseShape(String houseShape) {
        this.houseShape = houseShape == null ? null : houseShape.trim();
    }

    /**
     * 长度
     * @return length 长度
     */
    public String getLength() {
        return length;
    }

    /**
     * 长度
     * @param length 长度
     */
    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    /**
     * 宽度
     * @return width 宽度
     */
    public String getWidth() {
        return width;
    }

    /**
     * 宽度
     * @param width 宽度
     */
    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    /**
     * 相邻位置
     * @return adjacent_position 相邻位置
     */
    public String getAdjacentPosition() {
        return adjacentPosition;
    }

    /**
     * 相邻位置
     * @param adjacentPosition 相邻位置
     */
    public void setAdjacentPosition(String adjacentPosition) {
        this.adjacentPosition = adjacentPosition == null ? null : adjacentPosition.trim();
    }

    /**
     * 距离
     * @return distance 距离
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 距离
     * @param distance 距离
     */
    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
    }

    /**
     * 跨长
     * @return span_length 跨长
     */
    public String getSpanLength() {
        return spanLength;
    }

    /**
     * 跨长
     * @param spanLength 跨长
     */
    public void setSpanLength(String spanLength) {
        this.spanLength = spanLength == null ? null : spanLength.trim();
    }

    /**
     * 标准跨距
     * @return standard_span 标准跨距
     */
    public String getStandardSpan() {
        return standardSpan;
    }

    /**
     * 标准跨距
     * @param standardSpan 标准跨距
     */
    public void setStandardSpan(String standardSpan) {
        this.standardSpan = standardSpan == null ? null : standardSpan.trim();
    }

    /**
     * 最长跨距
     * @return max_span 最长跨距
     */
    public String getMaxSpan() {
        return maxSpan;
    }

    /**
     * 最长跨距
     * @param maxSpan 最长跨距
     */
    public void setMaxSpan(String maxSpan) {
        this.maxSpan = maxSpan == null ? null : maxSpan.trim();
    }

    /**
     * 最小跨距
     * @return min_span 最小跨距
     */
    public String getMinSpan() {
        return minSpan;
    }

    /**
     * 最小跨距
     * @param minSpan 最小跨距
     */
    public void setMinSpan(String minSpan) {
        this.minSpan = minSpan == null ? null : minSpan.trim();
    }

    /**
     * 标准计量
     * @return standard_measure 标准计量
     */
    public String getStandardMeasure() {
        return standardMeasure;
    }

    /**
     * 标准计量
     * @param standardMeasure 标准计量
     */
    public void setStandardMeasure(String standardMeasure) {
        this.standardMeasure = standardMeasure == null ? null : standardMeasure.trim();
    }

    /**
     * 仓储要求
     * @return storage_request 仓储要求
     */
    public String getStorageRequest() {
        return storageRequest;
    }

    /**
     * 仓储要求
     * @param storageRequest 仓储要求
     */
    public void setStorageRequest(String storageRequest) {
        this.storageRequest = storageRequest == null ? null : storageRequest.trim();
    }

    /**
     * 跨数
     * @return span_num 跨数
     */
    public String getSpanNum() {
        return spanNum;
    }

    /**
     * 跨数
     * @param spanNum 跨数
     */
    public void setSpanNum(String spanNum) {
        this.spanNum = spanNum == null ? null : spanNum.trim();
    }

    /**
     * 所在楼层
     * @return current_floor 所在楼层
     */
    public String getCurrentFloor() {
        return currentFloor;
    }

    /**
     * 所在楼层
     * @param currentFloor 所在楼层
     */
    public void setCurrentFloor(String currentFloor) {
        this.currentFloor = currentFloor == null ? null : currentFloor.trim();
    }

    /**
     * 形状说明
     * @return shape_remark 形状说明
     */
    public String getShapeRemark() {
        return shapeRemark;
    }

    /**
     * 形状说明
     * @param shapeRemark 形状说明
     */
    public void setShapeRemark(String shapeRemark) {
        this.shapeRemark = shapeRemark == null ? null : shapeRemark.trim();
    }

    /**
     * 特殊因素
     * @return special_factors 特殊因素
     */
    public String getSpecialFactors() {
        return specialFactors;
    }

    /**
     * 特殊因素
     * @param specialFactors 特殊因素
     */
    public void setSpecialFactors(String specialFactors) {
        this.specialFactors = specialFactors == null ? null : specialFactors.trim();
    }

    public static BasicHouseRoom.Builder builder() {
        return new BasicHouseRoom.Builder();
    }

    /**
     * tb_basic_house_room
     */
    public static class Builder {
        /**
         * tb_basic_house_room
         */
        private BasicHouseRoom obj;

        public Builder() {
            this.obj = new BasicHouseRoom();
        }

        /**
         * id
         * @param id id
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 
         * @param houseId 
         */
        public Builder houseId(Integer houseId) {
            obj.setHouseId(houseId);
            return this;
        }

        /**
         * 房间类型
         * @param roomType 房间类型
         */
        public Builder roomType(String roomType) {
            obj.setRoomType(roomType);
            return this;
        }

        /**
         * 房间名称
         * @param name 房间名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 面积
         * @param area 面积
         */
        public Builder area(BigDecimal area) {
            obj.setArea(area);
            return this;
        }

        /**
         * 方位
         * @param orientation 方位
         */
        public Builder orientation(String orientation) {
            obj.setOrientation(orientation);
            return this;
        }

        /**
         * 通风
         * @param aeration 通风
         */
        public Builder aeration(String aeration) {
            obj.setAeration(aeration);
            return this;
        }

        /**
         * 光照
         * @param illumination 光照
         */
        public Builder illumination(String illumination) {
            obj.setIllumination(illumination);
            return this;
        }

        /**
         * 隔音
         * @param soundInsulation 隔音
         */
        public Builder soundInsulation(String soundInsulation) {
            obj.setSoundInsulation(soundInsulation);
            return this;
        }

        /**
         * 日照
         * @param sunshine 日照
         */
        public Builder sunshine(String sunshine) {
            obj.setSunshine(sunshine);
            return this;
        }

        /**
         * 采光
         * @param lighting 采光
         */
        public Builder lighting(String lighting) {
            obj.setLighting(lighting);
            return this;
        }

        /**
         * 开间
         * @param opening 开间
         */
        public Builder opening(String opening) {
            obj.setOpening(opening);
            return this;
        }

        /**
         * 进深
         * @param depth 进深
         */
        public Builder depth(String depth) {
            obj.setDepth(depth);
            return this;
        }

        /**
         * 层高
         * @param layerHeight 层高
         */
        public Builder layerHeight(BigDecimal layerHeight) {
            obj.setLayerHeight(layerHeight);
            return this;
        }

        /**
         * 净高
         * @param clearHeight 净高
         */
        public Builder clearHeight(BigDecimal clearHeight) {
            obj.setClearHeight(clearHeight);
            return this;
        }

        /**
         * 
         * @param bisDelete 
         */
        public Builder bisDelete(Boolean bisDelete) {
            obj.setBisDelete(bisDelete);
            return this;
        }

        /**
         * 创建人
         * @param creator 创建人
         */
        public Builder creator(String creator) {
            obj.setCreator(creator);
            return this;
        }

        /**
         * 创建时间
         * @param gmtCreated 创建时间
         */
        public Builder gmtCreated(Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        /**
         * 房间形状
         * @param houseShape 房间形状
         */
        public Builder houseShape(String houseShape) {
            obj.setHouseShape(houseShape);
            return this;
        }

        /**
         * 长度
         * @param length 长度
         */
        public Builder length(String length) {
            obj.setLength(length);
            return this;
        }

        /**
         * 宽度
         * @param width 宽度
         */
        public Builder width(String width) {
            obj.setWidth(width);
            return this;
        }

        /**
         * 相邻位置
         * @param adjacentPosition 相邻位置
         */
        public Builder adjacentPosition(String adjacentPosition) {
            obj.setAdjacentPosition(adjacentPosition);
            return this;
        }

        /**
         * 距离
         * @param distance 距离
         */
        public Builder distance(String distance) {
            obj.setDistance(distance);
            return this;
        }

        /**
         * 跨长
         * @param spanLength 跨长
         */
        public Builder spanLength(String spanLength) {
            obj.setSpanLength(spanLength);
            return this;
        }

        /**
         * 标准跨距
         * @param standardSpan 标准跨距
         */
        public Builder standardSpan(String standardSpan) {
            obj.setStandardSpan(standardSpan);
            return this;
        }

        /**
         * 最长跨距
         * @param maxSpan 最长跨距
         */
        public Builder maxSpan(String maxSpan) {
            obj.setMaxSpan(maxSpan);
            return this;
        }

        /**
         * 最小跨距
         * @param minSpan 最小跨距
         */
        public Builder minSpan(String minSpan) {
            obj.setMinSpan(minSpan);
            return this;
        }

        /**
         * 标准计量
         * @param standardMeasure 标准计量
         */
        public Builder standardMeasure(String standardMeasure) {
            obj.setStandardMeasure(standardMeasure);
            return this;
        }

        /**
         * 仓储要求
         * @param storageRequest 仓储要求
         */
        public Builder storageRequest(String storageRequest) {
            obj.setStorageRequest(storageRequest);
            return this;
        }

        /**
         * 跨数
         * @param spanNum 跨数
         */
        public Builder spanNum(String spanNum) {
            obj.setSpanNum(spanNum);
            return this;
        }

        /**
         * 所在楼层
         * @param currentFloor 所在楼层
         */
        public Builder currentFloor(String currentFloor) {
            obj.setCurrentFloor(currentFloor);
            return this;
        }

        /**
         * 形状说明
         * @param shapeRemark 形状说明
         */
        public Builder shapeRemark(String shapeRemark) {
            obj.setShapeRemark(shapeRemark);
            return this;
        }

        /**
         * 特殊因素
         * @param specialFactors 特殊因素
         */
        public Builder specialFactors(String specialFactors) {
            obj.setSpecialFactors(specialFactors);
            return this;
        }

        public BasicHouseRoom build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        houseId("house_id", "houseId", "INTEGER", false),
        roomType("room_type", "roomType", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        area("area", "area", "DECIMAL", false),
        orientation("orientation", "orientation", "VARCHAR", false),
        aeration("aeration", "aeration", "VARCHAR", false),
        illumination("Illumination", "illumination", "VARCHAR", false),
        soundInsulation("sound_insulation", "soundInsulation", "VARCHAR", false),
        sunshine("sunshine", "sunshine", "VARCHAR", false),
        lighting("lighting", "lighting", "VARCHAR", false),
        opening("opening", "opening", "VARCHAR", false),
        depth("depth", "depth", "VARCHAR", false),
        layerHeight("layer_height", "layerHeight", "DECIMAL", false),
        clearHeight("clear_height", "clearHeight", "DECIMAL", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        houseShape("house_shape", "houseShape", "VARCHAR", false),
        length("length", "length", "VARCHAR", false),
        width("width", "width", "VARCHAR", false),
        adjacentPosition("adjacent_position", "adjacentPosition", "VARCHAR", false),
        distance("distance", "distance", "VARCHAR", false),
        spanLength("span_length", "spanLength", "VARCHAR", false),
        standardSpan("standard_span", "standardSpan", "VARCHAR", false),
        maxSpan("max_span", "maxSpan", "VARCHAR", false),
        minSpan("min_span", "minSpan", "VARCHAR", false),
        standardMeasure("standard_measure", "standardMeasure", "VARCHAR", false),
        storageRequest("storage_request", "storageRequest", "VARCHAR", false),
        spanNum("span_num", "spanNum", "VARCHAR", false),
        currentFloor("current_floor", "currentFloor", "VARCHAR", false),
        shapeRemark("shape_remark", "shapeRemark", "VARCHAR", false),
        specialFactors("special_factors", "specialFactors", "VARCHAR", false);

        /**
         * tb_basic_house_room
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_house_room
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_house_room
         */
        private final String column;

        /**
         * tb_basic_house_room
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_house_room
         */
        private final String javaProperty;

        /**
         * tb_basic_house_room
         */
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}