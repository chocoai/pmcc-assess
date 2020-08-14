package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareBuildEngineeringAndEquipmentCenter implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer planDetailsId;

    /**
     * 建筑工程施工许可证
     */
    private Integer buildingConstructionPermitId;

    /**
     * 建设工程规划许可证
     */
    private Integer buildingPermitId;

    /**
     * 建设用地规划许可证
     */
    private Integer landUsePermitId;

    /**
     * 商品房预售许可证
     */
    private Integer preSalePermitId;

    /**
     * 土地证
     */
    private Integer landId;

    /**
     * 房产证
     */
    private Integer houseId;

    /**
     * 经济指标
     */
    private Integer indicatorId;

    /**
     * 不动产证
     */
    private Integer realEstateId;

    /**
     * 在建工程 土建
     */
    private Integer buildEngineeringId;

    /**
     * 在建工程 设备安装
     */
    private Integer buildEquipmentId;

    /**
     * 类型(土建,设备安装,房产证)
     */
    private String type;

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
     * tb_declare_build_engineering_and_equipment_center
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
     * @return plan_details_id 
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 
     * @param planDetailsId 
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * 建筑工程施工许可证
     * @return building_construction_permit_id 建筑工程施工许可证
     */
    public Integer getBuildingConstructionPermitId() {
        return buildingConstructionPermitId;
    }

    /**
     * 建筑工程施工许可证
     * @param buildingConstructionPermitId 建筑工程施工许可证
     */
    public void setBuildingConstructionPermitId(Integer buildingConstructionPermitId) {
        this.buildingConstructionPermitId = buildingConstructionPermitId;
    }

    /**
     * 建设工程规划许可证
     * @return building_permit_id 建设工程规划许可证
     */
    public Integer getBuildingPermitId() {
        return buildingPermitId;
    }

    /**
     * 建设工程规划许可证
     * @param buildingPermitId 建设工程规划许可证
     */
    public void setBuildingPermitId(Integer buildingPermitId) {
        this.buildingPermitId = buildingPermitId;
    }

    /**
     * 建设用地规划许可证
     * @return land_use_permit_id 建设用地规划许可证
     */
    public Integer getLandUsePermitId() {
        return landUsePermitId;
    }

    /**
     * 建设用地规划许可证
     * @param landUsePermitId 建设用地规划许可证
     */
    public void setLandUsePermitId(Integer landUsePermitId) {
        this.landUsePermitId = landUsePermitId;
    }

    /**
     * 商品房预售许可证
     * @return pre_sale_permit_id 商品房预售许可证
     */
    public Integer getPreSalePermitId() {
        return preSalePermitId;
    }

    /**
     * 商品房预售许可证
     * @param preSalePermitId 商品房预售许可证
     */
    public void setPreSalePermitId(Integer preSalePermitId) {
        this.preSalePermitId = preSalePermitId;
    }

    /**
     * 土地证
     * @return land_id 土地证
     */
    public Integer getLandId() {
        return landId;
    }

    /**
     * 土地证
     * @param landId 土地证
     */
    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    /**
     * 房产证
     * @return house_id 房产证
     */
    public Integer getHouseId() {
        return houseId;
    }

    /**
     * 房产证
     * @param houseId 房产证
     */
    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    /**
     * 经济指标
     * @return indicator_id 经济指标
     */
    public Integer getIndicatorId() {
        return indicatorId;
    }

    /**
     * 经济指标
     * @param indicatorId 经济指标
     */
    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    /**
     * 不动产证
     * @return real_estate_id 不动产证
     */
    public Integer getRealEstateId() {
        return realEstateId;
    }

    /**
     * 不动产证
     * @param realEstateId 不动产证
     */
    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }

    /**
     * 在建工程 土建
     * @return build_engineering_id 在建工程 土建
     */
    public Integer getBuildEngineeringId() {
        return buildEngineeringId;
    }

    /**
     * 在建工程 土建
     * @param buildEngineeringId 在建工程 土建
     */
    public void setBuildEngineeringId(Integer buildEngineeringId) {
        this.buildEngineeringId = buildEngineeringId;
    }

    /**
     * 在建工程 设备安装
     * @return build_equipment_id 在建工程 设备安装
     */
    public Integer getBuildEquipmentId() {
        return buildEquipmentId;
    }

    /**
     * 在建工程 设备安装
     * @param buildEquipmentId 在建工程 设备安装
     */
    public void setBuildEquipmentId(Integer buildEquipmentId) {
        this.buildEquipmentId = buildEquipmentId;
    }

    /**
     * 类型(土建,设备安装,房产证)
     * @return type 类型(土建,设备安装,房产证)
     */
    public String getType() {
        return type;
    }

    /**
     * 类型(土建,设备安装,房产证)
     * @param type 类型(土建,设备安装,房产证)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public static DeclareBuildEngineeringAndEquipmentCenter.Builder builder() {
        return new DeclareBuildEngineeringAndEquipmentCenter.Builder();
    }

    /**
     * tb_declare_build_engineering_and_equipment_center
     */
    public static class Builder {
        /**
         * tb_declare_build_engineering_and_equipment_center
         */
        private DeclareBuildEngineeringAndEquipmentCenter obj;

        public Builder() {
            this.obj = new DeclareBuildEngineeringAndEquipmentCenter();
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
         * @param planDetailsId 
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        public DeclareBuildEngineeringAndEquipmentCenter build() {
            return this.obj;
        }

        /**
         * 建筑工程施工许可证
         * @param buildingConstructionPermitId 建筑工程施工许可证
         */
        public Builder buildingConstructionPermitId(Integer buildingConstructionPermitId) {
            obj.setBuildingConstructionPermitId(buildingConstructionPermitId);
            return this;
        }

        /**
         * 建设工程规划许可证
         * @param buildingPermitId 建设工程规划许可证
         */
        public Builder buildingPermitId(Integer buildingPermitId) {
            obj.setBuildingPermitId(buildingPermitId);
            return this;
        }

        /**
         * 建设用地规划许可证
         * @param landUsePermitId 建设用地规划许可证
         */
        public Builder landUsePermitId(Integer landUsePermitId) {
            obj.setLandUsePermitId(landUsePermitId);
            return this;
        }

        /**
         * 商品房预售许可证
         * @param preSalePermitId 商品房预售许可证
         */
        public Builder preSalePermitId(Integer preSalePermitId) {
            obj.setPreSalePermitId(preSalePermitId);
            return this;
        }

        /**
         * 土地证
         * @param landId 土地证
         */
        public Builder landId(Integer landId) {
            obj.setLandId(landId);
            return this;
        }

        /**
         * 房产证
         * @param houseId 房产证
         */
        public Builder houseId(Integer houseId) {
            obj.setHouseId(houseId);
            return this;
        }

        /**
         * 经济指标
         * @param indicatorId 经济指标
         */
        public Builder indicatorId(Integer indicatorId) {
            obj.setIndicatorId(indicatorId);
            return this;
        }

        /**
         * 不动产证
         * @param realEstateId 不动产证
         */
        public Builder realEstateId(Integer realEstateId) {
            obj.setRealEstateId(realEstateId);
            return this;
        }

        /**
         * 在建工程 土建
         * @param buildEngineeringId 在建工程 土建
         */
        public Builder buildEngineeringId(Integer buildEngineeringId) {
            obj.setBuildEngineeringId(buildEngineeringId);
            return this;
        }

        /**
         * 在建工程 设备安装
         * @param buildEquipmentId 在建工程 设备安装
         */
        public Builder buildEquipmentId(Integer buildEquipmentId) {
            obj.setBuildEquipmentId(buildEquipmentId);
            return this;
        }

        /**
         * 类型(土建,设备安装,房产证)
         * @param type 类型(土建,设备安装,房产证)
         */
        public Builder type(String type) {
            obj.setType(type);
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
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        buildingConstructionPermitId("building_construction_permit_id", "buildingConstructionPermitId", "INTEGER", false),
        buildingPermitId("building_permit_id", "buildingPermitId", "INTEGER", false),
        landUsePermitId("land_use_permit_id", "landUsePermitId", "INTEGER", false),
        preSalePermitId("pre_sale_permit_id", "preSalePermitId", "INTEGER", false),
        landId("land_id", "landId", "INTEGER", false),
        houseId("house_id", "houseId", "INTEGER", false),
        indicatorId("indicator_id", "indicatorId", "INTEGER", false),
        realEstateId("real_estate_id", "realEstateId", "INTEGER", false),
        buildEngineeringId("build_engineering_id", "buildEngineeringId", "INTEGER", false),
        buildEquipmentId("build_equipment_id", "buildEquipmentId", "INTEGER", false),
        type("type", "type", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_declare_build_engineering_and_equipment_center
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_build_engineering_and_equipment_center
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_build_engineering_and_equipment_center
         */
        private final String column;

        /**
         * tb_declare_build_engineering_and_equipment_center
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_build_engineering_and_equipment_center
         */
        private final String javaProperty;

        /**
         * tb_declare_build_engineering_and_equipment_center
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