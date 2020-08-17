package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicHouseCorollaryEquipment implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer houseId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 类别
     */
    private Integer category;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备用途
     */
    private Integer equipmentUse;

    /**
     * 参数指标
     */
    private String parameterIndex;

    /**
     * 价格
     */
    private String price;

    /**
     * 位置
     */
    private String location;

    /**
     * 维护状况
     */
    private Integer maintenanceStatus;

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
     * tb_basic_house_corollary_equipment
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
     * 类型
     * @return type 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 类别
     * @return category 类别
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 类别
     * @param category 类别
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 设备名称
     * @return name 设备名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设备名称
     * @param name 设备名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 设备用途
     * @return equipment_use 设备用途
     */
    public Integer getEquipmentUse() {
        return equipmentUse;
    }

    /**
     * 设备用途
     * @param equipmentUse 设备用途
     */
    public void setEquipmentUse(Integer equipmentUse) {
        this.equipmentUse = equipmentUse;
    }

    /**
     * 参数指标
     * @return parameter_index 参数指标
     */
    public String getParameterIndex() {
        return parameterIndex;
    }

    /**
     * 参数指标
     * @param parameterIndex 参数指标
     */
    public void setParameterIndex(String parameterIndex) {
        this.parameterIndex = parameterIndex == null ? null : parameterIndex.trim();
    }

    /**
     * 价格
     * @return price 价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 价格
     * @param price 价格
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * 位置
     * @return location 位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 位置
     * @param location 位置
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 维护状况
     * @return maintenance_status 维护状况
     */
    public Integer getMaintenanceStatus() {
        return maintenanceStatus;
    }

    /**
     * 维护状况
     * @param maintenanceStatus 维护状况
     */
    public void setMaintenanceStatus(Integer maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
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

    public static BasicHouseCorollaryEquipment.Builder builder() {
        return new BasicHouseCorollaryEquipment.Builder();
    }

    /**
     * tb_basic_house_corollary_equipment
     */
    public static class Builder {
        /**
         * tb_basic_house_corollary_equipment
         */
        private BasicHouseCorollaryEquipment obj;

        public Builder() {
            this.obj = new BasicHouseCorollaryEquipment();
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
         * 类型
         * @param type 类型
         */
        public Builder type(Integer type) {
            obj.setType(type);
            return this;
        }

        /**
         * 类别
         * @param category 类别
         */
        public Builder category(Integer category) {
            obj.setCategory(category);
            return this;
        }

        /**
         * 设备名称
         * @param name 设备名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 设备用途
         * @param equipmentUse 设备用途
         */
        public Builder equipmentUse(Integer equipmentUse) {
            obj.setEquipmentUse(equipmentUse);
            return this;
        }

        /**
         * 参数指标
         * @param parameterIndex 参数指标
         */
        public Builder parameterIndex(String parameterIndex) {
            obj.setParameterIndex(parameterIndex);
            return this;
        }

        /**
         * 价格
         * @param price 价格
         */
        public Builder price(String price) {
            obj.setPrice(price);
            return this;
        }

        /**
         * 位置
         * @param location 位置
         */
        public Builder location(String location) {
            obj.setLocation(location);
            return this;
        }

        /**
         * 维护状况
         * @param maintenanceStatus 维护状况
         */
        public Builder maintenanceStatus(Integer maintenanceStatus) {
            obj.setMaintenanceStatus(maintenanceStatus);
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

        public BasicHouseCorollaryEquipment build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        houseId("house_id", "houseId", "INTEGER", false),
        type("type", "type", "INTEGER", false),
        category("category", "category", "INTEGER", false),
        name("name", "name", "VARCHAR", false),
        equipmentUse("equipment_use", "equipmentUse", "INTEGER", false),
        parameterIndex("parameter_index", "parameterIndex", "VARCHAR", false),
        price("price", "price", "VARCHAR", false),
        location("location", "location", "VARCHAR", false),
        maintenanceStatus("maintenance_status", "maintenanceStatus", "INTEGER", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_basic_house_corollary_equipment
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_house_corollary_equipment
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_house_corollary_equipment
         */
        private final String column;

        /**
         * tb_basic_house_corollary_equipment
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_house_corollary_equipment
         */
        private final String javaProperty;

        /**
         * tb_basic_house_corollary_equipment
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