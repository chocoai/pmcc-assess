package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareRealtyCheckList implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer autoInitNumber;

    /**
     * 主表id
     */
    private Integer marsterId;

    /**
     * 计划详情id
     */
    private Integer planDetailsId;

    /**
     * 所在区
     */
    private String district;

    /**
     * 街道号
     */
    private String streetNumber;

    /**
     * 门牌号
     */
    private String houseNumber;

    /**
     * 附号
     */
    private String attachedNumber;

    /**
     * 栋号
     */
    private String buildingNumber;

    /**
     * 单元
     */
    private String unit;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 房号
     */
    private String roomNumber;

    /**
     * 房屋结构
     */
    private String housingStructure;

    /**
     * 用途
     */
    private String certUse;

    /**
     * 房屋建筑面积
     */
    private BigDecimal floorArea;

    /**
     * 分摊面积
     */
    private BigDecimal apportionmentArea;

    /**
     * 不动产单元号
     */
    private String realEstateUnitNumber;

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
     * tb_declare_realty_check_list
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return auto_init_number 
     */
    public Integer getAutoInitNumber() {
        return autoInitNumber;
    }

    /**
     * 
     * @param autoInitNumber 
     */
    public void setAutoInitNumber(Integer autoInitNumber) {
        this.autoInitNumber = autoInitNumber;
    }

    /**
     * 主表id
     * @return marster_id 主表id
     */
    public Integer getMarsterId() {
        return marsterId;
    }

    /**
     * 主表id
     * @param marsterId 主表id
     */
    public void setMarsterId(Integer marsterId) {
        this.marsterId = marsterId;
    }

    /**
     * 计划详情id
     * @return plan_details_id 计划详情id
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 计划详情id
     * @param planDetailsId 计划详情id
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * 所在区
     * @return district 所在区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 所在区
     * @param district 所在区
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 街道号
     * @return street_number 街道号
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * 街道号
     * @param streetNumber 街道号
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber == null ? null : streetNumber.trim();
    }

    /**
     * 门牌号
     * @return house_number 门牌号
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * 门牌号
     * @param houseNumber 门牌号
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    /**
     * 附号
     * @return attached_number 附号
     */
    public String getAttachedNumber() {
        return attachedNumber;
    }

    /**
     * 附号
     * @param attachedNumber 附号
     */
    public void setAttachedNumber(String attachedNumber) {
        this.attachedNumber = attachedNumber == null ? null : attachedNumber.trim();
    }

    /**
     * 栋号
     * @return building_number 栋号
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * 栋号
     * @param buildingNumber 栋号
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    /**
     * 单元
     * @return unit 单元
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 单元
     * @param unit 单元
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 楼层
     * @return floor 楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 楼层
     * @param floor 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 房号
     * @return room_number 房号
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * 房号
     * @param roomNumber 房号
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    /**
     * 房屋结构
     * @return housing_structure 房屋结构
     */
    public String getHousingStructure() {
        return housingStructure;
    }

    /**
     * 房屋结构
     * @param housingStructure 房屋结构
     */
    public void setHousingStructure(String housingStructure) {
        this.housingStructure = housingStructure == null ? null : housingStructure.trim();
    }

    /**
     * 用途
     * @return cert_use 用途
     */
    public String getCertUse() {
        return certUse;
    }

    /**
     * 用途
     * @param certUse 用途
     */
    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    /**
     * 房屋建筑面积
     * @return floor_area 房屋建筑面积
     */
    public BigDecimal getFloorArea() {
        return floorArea;
    }

    /**
     * 房屋建筑面积
     * @param floorArea 房屋建筑面积
     */
    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * 分摊面积
     * @return apportionment_area 分摊面积
     */
    public BigDecimal getApportionmentArea() {
        return apportionmentArea;
    }

    /**
     * 分摊面积
     * @param apportionmentArea 分摊面积
     */
    public void setApportionmentArea(BigDecimal apportionmentArea) {
        this.apportionmentArea = apportionmentArea;
    }

    /**
     * 不动产单元号
     * @return real_estate_unit_number 不动产单元号
     */
    public String getRealEstateUnitNumber() {
        return realEstateUnitNumber;
    }

    /**
     * 不动产单元号
     * @param realEstateUnitNumber 不动产单元号
     */
    public void setRealEstateUnitNumber(String realEstateUnitNumber) {
        this.realEstateUnitNumber = realEstateUnitNumber == null ? null : realEstateUnitNumber.trim();
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

    public static DeclareRealtyCheckList.Builder builder() {
        return new DeclareRealtyCheckList.Builder();
    }

    /**
     * tb_declare_realty_check_list
     */
    public static class Builder {
        /**
         * tb_declare_realty_check_list
         */
        private DeclareRealtyCheckList obj;

        public Builder() {
            this.obj = new DeclareRealtyCheckList();
        }

        /**
         * 
         * @param id 
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 
         * @param autoInitNumber 
         */
        public Builder autoInitNumber(Integer autoInitNumber) {
            obj.setAutoInitNumber(autoInitNumber);
            return this;
        }

        /**
         * 主表id
         * @param marsterId 主表id
         */
        public Builder marsterId(Integer marsterId) {
            obj.setMarsterId(marsterId);
            return this;
        }

        /**
         * 计划详情id
         * @param planDetailsId 计划详情id
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * 所在区
         * @param district 所在区
         */
        public Builder district(String district) {
            obj.setDistrict(district);
            return this;
        }

        /**
         * 街道号
         * @param streetNumber 街道号
         */
        public Builder streetNumber(String streetNumber) {
            obj.setStreetNumber(streetNumber);
            return this;
        }

        /**
         * 门牌号
         * @param houseNumber 门牌号
         */
        public Builder houseNumber(String houseNumber) {
            obj.setHouseNumber(houseNumber);
            return this;
        }

        /**
         * 附号
         * @param attachedNumber 附号
         */
        public Builder attachedNumber(String attachedNumber) {
            obj.setAttachedNumber(attachedNumber);
            return this;
        }

        public DeclareRealtyCheckList build() {
            return this.obj;
        }

        /**
         * 栋号
         * @param buildingNumber 栋号
         */
        public Builder buildingNumber(String buildingNumber) {
            obj.setBuildingNumber(buildingNumber);
            return this;
        }

        /**
         * 单元
         * @param unit 单元
         */
        public Builder unit(String unit) {
            obj.setUnit(unit);
            return this;
        }

        /**
         * 楼层
         * @param floor 楼层
         */
        public Builder floor(String floor) {
            obj.setFloor(floor);
            return this;
        }

        /**
         * 房屋建筑面积
         * @param floorArea 房屋建筑面积
         */
        public Builder floorArea(BigDecimal floorArea) {
            obj.setFloorArea(floorArea);
            return this;
        }

        /**
         * 房号
         * @param roomNumber 房号
         */
        public Builder roomNumber(String roomNumber) {
            obj.setRoomNumber(roomNumber);
            return this;
        }

        /**
         * 房屋结构
         * @param housingStructure 房屋结构
         */
        public Builder housingStructure(String housingStructure) {
            obj.setHousingStructure(housingStructure);
            return this;
        }

        /**
         * 用途
         * @param certUse 用途
         */
        public Builder certUse(String certUse) {
            obj.setCertUse(certUse);
            return this;
        }

        /**
         * 分摊面积
         * @param apportionmentArea 分摊面积
         */
        public Builder apportionmentArea(BigDecimal apportionmentArea) {
            obj.setApportionmentArea(apportionmentArea);
            return this;
        }

        /**
         * 不动产单元号
         * @param realEstateUnitNumber 不动产单元号
         */
        public Builder realEstateUnitNumber(String realEstateUnitNumber) {
            obj.setRealEstateUnitNumber(realEstateUnitNumber);
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
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        autoInitNumber("auto_init_number", "autoInitNumber", "INTEGER", false),
        marsterId("marster_id", "marsterId", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        district("district", "district", "VARCHAR", false),
        streetNumber("street_number", "streetNumber", "VARCHAR", false),
        houseNumber("house_number", "houseNumber", "VARCHAR", false),
        attachedNumber("attached_number", "attachedNumber", "VARCHAR", false),
        buildingNumber("building_number", "buildingNumber", "VARCHAR", false),
        unit("unit", "unit", "VARCHAR", false),
        floor("floor", "floor", "VARCHAR", false),
        roomNumber("room_number", "roomNumber", "VARCHAR", false),
        housingStructure("housing_structure", "housingStructure", "VARCHAR", false),
        certUse("cert_use", "certUse", "VARCHAR", false),
        floorArea("floor_area", "floorArea", "DECIMAL", false),
        apportionmentArea("apportionment_area", "apportionmentArea", "DECIMAL", false),
        realEstateUnitNumber("real_estate_unit_number", "realEstateUnitNumber", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_declare_realty_check_list
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_realty_check_list
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_realty_check_list
         */
        private final String column;

        /**
         * tb_declare_realty_check_list
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_realty_check_list
         */
        private final String javaProperty;

        /**
         * tb_declare_realty_check_list
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