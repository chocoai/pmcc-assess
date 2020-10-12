package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InitiateConsignor implements Serializable {
    /**
     * 
     */
    private Integer id;

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
     * 项目id
     */
    private Integer projectId;

    /**
     * 委托人类型:如1表示法人,0表示自然人
     */
    private Integer csType;

    /**
     * 委托单位
     */
    private String csEntrustmentUnit;

    /**
     * 法定代表人
     */
    private String csLegalRepresentative;

    /**
     * 社会统一信用代码
     */
    private String csSociologyCode;

    /**
     * 地址
     */
    private String csAddress;

    /**
     * 经营范围
     */
    private String csScopeOperation;

    /**
     * 单位性质
     */
    private String csUnitProperties;

    /**
     * 姓名
     */
    private String csName;

    /**
     * 备用字段
     */
    private String csSpareField;

    /**
     * 身份证号码
     */
    private String csIdcard;

    /**
     * tb_initiate_consignor
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
     * 项目id
     * @return project_id 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 委托人类型:如1表示法人,0表示自然人
     * @return cs_type 委托人类型:如1表示法人,0表示自然人
     */
    public Integer getCsType() {
        return csType;
    }

    /**
     * 委托人类型:如1表示法人,0表示自然人
     * @param csType 委托人类型:如1表示法人,0表示自然人
     */
    public void setCsType(Integer csType) {
        this.csType = csType;
    }

    /**
     * 委托单位
     * @return cs_entrustment_unit 委托单位
     */
    public String getCsEntrustmentUnit() {
        return csEntrustmentUnit;
    }

    /**
     * 委托单位
     * @param csEntrustmentUnit 委托单位
     */
    public void setCsEntrustmentUnit(String csEntrustmentUnit) {
        this.csEntrustmentUnit = csEntrustmentUnit == null ? null : csEntrustmentUnit.trim();
    }

    /**
     * 法定代表人
     * @return cs_legal_representative 法定代表人
     */
    public String getCsLegalRepresentative() {
        return csLegalRepresentative;
    }

    /**
     * 法定代表人
     * @param csLegalRepresentative 法定代表人
     */
    public void setCsLegalRepresentative(String csLegalRepresentative) {
        this.csLegalRepresentative = csLegalRepresentative == null ? null : csLegalRepresentative.trim();
    }

    /**
     * 社会统一信用代码
     * @return cs_sociology_code 社会统一信用代码
     */
    public String getCsSociologyCode() {
        return csSociologyCode;
    }

    /**
     * 社会统一信用代码
     * @param csSociologyCode 社会统一信用代码
     */
    public void setCsSociologyCode(String csSociologyCode) {
        this.csSociologyCode = csSociologyCode == null ? null : csSociologyCode.trim();
    }

    /**
     * 地址
     * @return cs_address 地址
     */
    public String getCsAddress() {
        return csAddress;
    }

    /**
     * 地址
     * @param csAddress 地址
     */
    public void setCsAddress(String csAddress) {
        this.csAddress = csAddress == null ? null : csAddress.trim();
    }

    /**
     * 经营范围
     * @return cs_scope_operation 经营范围
     */
    public String getCsScopeOperation() {
        return csScopeOperation;
    }

    /**
     * 经营范围
     * @param csScopeOperation 经营范围
     */
    public void setCsScopeOperation(String csScopeOperation) {
        this.csScopeOperation = csScopeOperation == null ? null : csScopeOperation.trim();
    }

    /**
     * 单位性质
     * @return cs_unit_properties 单位性质
     */
    public String getCsUnitProperties() {
        return csUnitProperties;
    }

    /**
     * 单位性质
     * @param csUnitProperties 单位性质
     */
    public void setCsUnitProperties(String csUnitProperties) {
        this.csUnitProperties = csUnitProperties == null ? null : csUnitProperties.trim();
    }

    /**
     * 姓名
     * @return cs_name 姓名
     */
    public String getCsName() {
        return csName;
    }

    /**
     * 姓名
     * @param csName 姓名
     */
    public void setCsName(String csName) {
        this.csName = csName == null ? null : csName.trim();
    }

    /**
     * 备用字段
     * @return cs_spare_field 备用字段
     */
    public String getCsSpareField() {
        return csSpareField;
    }

    /**
     * 备用字段
     * @param csSpareField 备用字段
     */
    public void setCsSpareField(String csSpareField) {
        this.csSpareField = csSpareField == null ? null : csSpareField.trim();
    }

    /**
     * 身份证号码
     * @return cs_idcard 身份证号码
     */
    public String getCsIdcard() {
        return csIdcard;
    }

    /**
     * 身份证号码
     * @param csIdcard 身份证号码
     */
    public void setCsIdcard(String csIdcard) {
        this.csIdcard = csIdcard == null ? null : csIdcard.trim();
    }

    public static InitiateConsignor.Builder builder() {
        return new InitiateConsignor.Builder();
    }

    /**
     * tb_initiate_consignor
     */
    public static class Builder {
        /**
         * tb_initiate_consignor
         */
        private InitiateConsignor obj;

        public Builder() {
            this.obj = new InitiateConsignor();
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
         * 项目id
         * @param projectId 项目id
         */
        public Builder projectId(Integer projectId) {
            obj.setProjectId(projectId);
            return this;
        }

        /**
         * 委托人类型:如1表示法人,0表示自然人
         * @param csType 委托人类型:如1表示法人,0表示自然人
         */
        public Builder csType(Integer csType) {
            obj.setCsType(csType);
            return this;
        }

        /**
         * 委托单位
         * @param csEntrustmentUnit 委托单位
         */
        public Builder csEntrustmentUnit(String csEntrustmentUnit) {
            obj.setCsEntrustmentUnit(csEntrustmentUnit);
            return this;
        }

        /**
         * 法定代表人
         * @param csLegalRepresentative 法定代表人
         */
        public Builder csLegalRepresentative(String csLegalRepresentative) {
            obj.setCsLegalRepresentative(csLegalRepresentative);
            return this;
        }

        /**
         * 社会统一信用代码
         * @param csSociologyCode 社会统一信用代码
         */
        public Builder csSociologyCode(String csSociologyCode) {
            obj.setCsSociologyCode(csSociologyCode);
            return this;
        }

        /**
         * 地址
         * @param csAddress 地址
         */
        public Builder csAddress(String csAddress) {
            obj.setCsAddress(csAddress);
            return this;
        }

        /**
         * 经营范围
         * @param csScopeOperation 经营范围
         */
        public Builder csScopeOperation(String csScopeOperation) {
            obj.setCsScopeOperation(csScopeOperation);
            return this;
        }

        /**
         * 单位性质
         * @param csUnitProperties 单位性质
         */
        public Builder csUnitProperties(String csUnitProperties) {
            obj.setCsUnitProperties(csUnitProperties);
            return this;
        }

        /**
         * 姓名
         * @param csName 姓名
         */
        public Builder csName(String csName) {
            obj.setCsName(csName);
            return this;
        }

        /**
         * 备用字段
         * @param csSpareField 备用字段
         */
        public Builder csSpareField(String csSpareField) {
            obj.setCsSpareField(csSpareField);
            return this;
        }

        /**
         * 身份证号码
         * @param csIdcard 身份证号码
         */
        public Builder csIdcard(String csIdcard) {
            obj.setCsIdcard(csIdcard);
            return this;
        }

        public InitiateConsignor build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        projectId("project_id", "projectId", "INTEGER", false),
        csType("cs_type", "csType", "INTEGER", false),
        csEntrustmentUnit("cs_entrustment_unit", "csEntrustmentUnit", "VARCHAR", false),
        csLegalRepresentative("cs_legal_representative", "csLegalRepresentative", "VARCHAR", false),
        csSociologyCode("cs_sociology_code", "csSociologyCode", "VARCHAR", false),
        csAddress("cs_address", "csAddress", "VARCHAR", false),
        csScopeOperation("cs_scope_operation", "csScopeOperation", "VARCHAR", false),
        csUnitProperties("cs_unit_properties", "csUnitProperties", "VARCHAR", false),
        csName("cs_name", "csName", "VARCHAR", false),
        csSpareField("cs_spare_field", "csSpareField", "VARCHAR", false),
        csIdcard("cs_idcard", "csIdcard", "VARCHAR", false);

        /**
         * tb_initiate_consignor
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_initiate_consignor
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_initiate_consignor
         */
        private final String column;

        /**
         * tb_initiate_consignor
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_initiate_consignor
         */
        private final String javaProperty;

        /**
         * tb_initiate_consignor
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