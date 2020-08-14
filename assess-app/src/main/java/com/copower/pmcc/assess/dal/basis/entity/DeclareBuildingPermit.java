package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareBuildingPermit implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 主表id
     */
    private Integer planDetailsId;

    /**
     * 证书编号
     */
    private String certificateNumber;

    /**
     * 发证机关
     */
    private String issuingOrgan;

    /**
     * 日期
     */
    private Date date;

    /**
     * 建设单位（个人）
     */
    private String unit;

    /**
     * 建设项目名称
     */
    private String name;

    /**
     * 建设位置
     */
    private String location;

    /**
     * 建设规模
     */
    private String scaleConstruction;

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
     * 备注
     */
    private String remark;

    /**
     * 主表id
     */
    private Integer masterId;

    /**
     * 主表类型
     */
    private String masterType;

    /**
     * tb_declare_building_permit
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
     * 主表id
     * @return plan_details_id 主表id
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 主表id
     * @param planDetailsId 主表id
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * 证书编号
     * @return certificate_number 证书编号
     */
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * 证书编号
     * @param certificateNumber 证书编号
     */
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
    }

    /**
     * 发证机关
     * @return issuing_organ 发证机关
     */
    public String getIssuingOrgan() {
        return issuingOrgan;
    }

    /**
     * 发证机关
     * @param issuingOrgan 发证机关
     */
    public void setIssuingOrgan(String issuingOrgan) {
        this.issuingOrgan = issuingOrgan == null ? null : issuingOrgan.trim();
    }

    /**
     * 日期
     * @return date 日期
     */
    public Date getDate() {
        return date;
    }

    /**
     * 日期
     * @param date 日期
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 建设单位（个人）
     * @return unit 建设单位（个人）
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 建设单位（个人）
     * @param unit 建设单位（个人）
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 建设项目名称
     * @return name 建设项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 建设项目名称
     * @param name 建设项目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 建设位置
     * @return location 建设位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 建设位置
     * @param location 建设位置
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 建设规模
     * @return scale_construction 建设规模
     */
    public String getScaleConstruction() {
        return scaleConstruction;
    }

    /**
     * 建设规模
     * @param scaleConstruction 建设规模
     */
    public void setScaleConstruction(String scaleConstruction) {
        this.scaleConstruction = scaleConstruction == null ? null : scaleConstruction.trim();
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
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 主表id
     * @return master_id 主表id
     */
    public Integer getMasterId() {
        return masterId;
    }

    /**
     * 主表id
     * @param masterId 主表id
     */
    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    /**
     * 主表类型
     * @return master_type 主表类型
     */
    public String getMasterType() {
        return masterType;
    }

    /**
     * 主表类型
     * @param masterType 主表类型
     */
    public void setMasterType(String masterType) {
        this.masterType = masterType == null ? null : masterType.trim();
    }

    public static DeclareBuildingPermit.Builder builder() {
        return new DeclareBuildingPermit.Builder();
    }

    /**
     * tb_declare_building_permit
     */
    public static class Builder {
        /**
         * tb_declare_building_permit
         */
        private DeclareBuildingPermit obj;

        public Builder() {
            this.obj = new DeclareBuildingPermit();
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
         * 主表id
         * @param planDetailsId 主表id
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * 证书编号
         * @param certificateNumber 证书编号
         */
        public Builder certificateNumber(String certificateNumber) {
            obj.setCertificateNumber(certificateNumber);
            return this;
        }

        /**
         * 发证机关
         * @param issuingOrgan 发证机关
         */
        public Builder issuingOrgan(String issuingOrgan) {
            obj.setIssuingOrgan(issuingOrgan);
            return this;
        }

        /**
         * 日期
         * @param date 日期
         */
        public Builder date(Date date) {
            obj.setDate(date);
            return this;
        }

        /**
         * 建设单位（个人）
         * @param unit 建设单位（个人）
         */
        public Builder unit(String unit) {
            obj.setUnit(unit);
            return this;
        }

        /**
         * 建设项目名称
         * @param name 建设项目名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 建设位置
         * @param location 建设位置
         */
        public Builder location(String location) {
            obj.setLocation(location);
            return this;
        }

        /**
         * 建设规模
         * @param scaleConstruction 建设规模
         */
        public Builder scaleConstruction(String scaleConstruction) {
            obj.setScaleConstruction(scaleConstruction);
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
         * 备注
         * @param remark 备注
         */
        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        /**
         * 主表id
         * @param masterId 主表id
         */
        public Builder masterId(Integer masterId) {
            obj.setMasterId(masterId);
            return this;
        }

        /**
         * 主表类型
         * @param masterType 主表类型
         */
        public Builder masterType(String masterType) {
            obj.setMasterType(masterType);
            return this;
        }

        public DeclareBuildingPermit build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        certificateNumber("certificate_number", "certificateNumber", "VARCHAR", false),
        issuingOrgan("issuing_organ", "issuingOrgan", "VARCHAR", false),
        date("date", "date", "TIMESTAMP", false),
        unit("unit", "unit", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        location("location", "location", "VARCHAR", false),
        scaleConstruction("scale_construction", "scaleConstruction", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        remark("remark", "remark", "VARCHAR", false),
        masterId("master_id", "masterId", "INTEGER", false),
        masterType("master_type", "masterType", "VARCHAR", false);

        /**
         * tb_declare_building_permit
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_building_permit
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_building_permit
         */
        private final String column;

        /**
         * tb_declare_building_permit
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_building_permit
         */
        private final String javaProperty;

        /**
         * tb_declare_building_permit
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