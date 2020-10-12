package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InitiatePossessor implements Serializable {
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
    private Integer pType;

    /**
     * 委托单位
     */
    private String pEntrustmentUnit;

    /**
     * 法定代表人
     */
    private String pLegalRepresentative;

    /**
     * 社会统一信用代码
     */
    private String pSociologyCode;

    /**
     * 地址
     */
    private String pAddress;

    /**
     * 经营范围
     */
    private String pScopeOperation;

    /**
     * 单位性质
     */
    private String pUnitProperties;

    /**
     * 姓名
     */
    private String pName;

    /**
     * 备用字段
     */
    private String spareField;

    /**
     * 身份证号码
     */
    private String pIdcard;

    /**
     * tb_initiate_possessor
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
     * @return p_type 委托人类型:如1表示法人,0表示自然人
     */
    public Integer getpType() {
        return pType;
    }

    /**
     * 委托人类型:如1表示法人,0表示自然人
     * @param pType 委托人类型:如1表示法人,0表示自然人
     */
    public void setpType(Integer pType) {
        this.pType = pType;
    }

    /**
     * 委托单位
     * @return p_entrustment_unit 委托单位
     */
    public String getpEntrustmentUnit() {
        return pEntrustmentUnit;
    }

    /**
     * 委托单位
     * @param pEntrustmentUnit 委托单位
     */
    public void setpEntrustmentUnit(String pEntrustmentUnit) {
        this.pEntrustmentUnit = pEntrustmentUnit == null ? null : pEntrustmentUnit.trim();
    }

    /**
     * 法定代表人
     * @return p_legal_representative 法定代表人
     */
    public String getpLegalRepresentative() {
        return pLegalRepresentative;
    }

    /**
     * 法定代表人
     * @param pLegalRepresentative 法定代表人
     */
    public void setpLegalRepresentative(String pLegalRepresentative) {
        this.pLegalRepresentative = pLegalRepresentative == null ? null : pLegalRepresentative.trim();
    }

    /**
     * 社会统一信用代码
     * @return p_sociology_code 社会统一信用代码
     */
    public String getpSociologyCode() {
        return pSociologyCode;
    }

    /**
     * 社会统一信用代码
     * @param pSociologyCode 社会统一信用代码
     */
    public void setpSociologyCode(String pSociologyCode) {
        this.pSociologyCode = pSociologyCode == null ? null : pSociologyCode.trim();
    }

    /**
     * 地址
     * @return p_address 地址
     */
    public String getpAddress() {
        return pAddress;
    }

    /**
     * 地址
     * @param pAddress 地址
     */
    public void setpAddress(String pAddress) {
        this.pAddress = pAddress == null ? null : pAddress.trim();
    }

    /**
     * 经营范围
     * @return p_scope_operation 经营范围
     */
    public String getpScopeOperation() {
        return pScopeOperation;
    }

    /**
     * 经营范围
     * @param pScopeOperation 经营范围
     */
    public void setpScopeOperation(String pScopeOperation) {
        this.pScopeOperation = pScopeOperation == null ? null : pScopeOperation.trim();
    }

    /**
     * 单位性质
     * @return p_unit_properties 单位性质
     */
    public String getpUnitProperties() {
        return pUnitProperties;
    }

    /**
     * 单位性质
     * @param pUnitProperties 单位性质
     */
    public void setpUnitProperties(String pUnitProperties) {
        this.pUnitProperties = pUnitProperties == null ? null : pUnitProperties.trim();
    }

    /**
     * 姓名
     * @return p_name 姓名
     */
    public String getpName() {
        return pName;
    }

    /**
     * 姓名
     * @param pName 姓名
     */
    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    /**
     * 备用字段
     * @return spare_field 备用字段
     */
    public String getSpareField() {
        return spareField;
    }

    /**
     * 备用字段
     * @param spareField 备用字段
     */
    public void setSpareField(String spareField) {
        this.spareField = spareField == null ? null : spareField.trim();
    }

    /**
     * 身份证号码
     * @return p_idcard 身份证号码
     */
    public String getpIdcard() {
        return pIdcard;
    }

    /**
     * 身份证号码
     * @param pIdcard 身份证号码
     */
    public void setpIdcard(String pIdcard) {
        this.pIdcard = pIdcard == null ? null : pIdcard.trim();
    }

    public static InitiatePossessor.Builder builder() {
        return new InitiatePossessor.Builder();
    }

    /**
     * tb_initiate_possessor
     */
    public static class Builder {
        /**
         * tb_initiate_possessor
         */
        private InitiatePossessor obj;

        public Builder() {
            this.obj = new InitiatePossessor();
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
         * @param pType 委托人类型:如1表示法人,0表示自然人
         */
        public Builder pType(Integer pType) {
            obj.setpType(pType);
            return this;
        }

        /**
         * 委托单位
         * @param pEntrustmentUnit 委托单位
         */
        public Builder pEntrustmentUnit(String pEntrustmentUnit) {
            obj.setpEntrustmentUnit(pEntrustmentUnit);
            return this;
        }

        /**
         * 法定代表人
         * @param pLegalRepresentative 法定代表人
         */
        public Builder pLegalRepresentative(String pLegalRepresentative) {
            obj.setpLegalRepresentative(pLegalRepresentative);
            return this;
        }

        /**
         * 社会统一信用代码
         * @param pSociologyCode 社会统一信用代码
         */
        public Builder pSociologyCode(String pSociologyCode) {
            obj.setpSociologyCode(pSociologyCode);
            return this;
        }

        /**
         * 地址
         * @param pAddress 地址
         */
        public Builder pAddress(String pAddress) {
            obj.setpAddress(pAddress);
            return this;
        }

        /**
         * 经营范围
         * @param pScopeOperation 经营范围
         */
        public Builder pScopeOperation(String pScopeOperation) {
            obj.setpScopeOperation(pScopeOperation);
            return this;
        }

        /**
         * 单位性质
         * @param pUnitProperties 单位性质
         */
        public Builder pUnitProperties(String pUnitProperties) {
            obj.setpUnitProperties(pUnitProperties);
            return this;
        }

        /**
         * 姓名
         * @param pName 姓名
         */
        public Builder pName(String pName) {
            obj.setpName(pName);
            return this;
        }

        /**
         * 备用字段
         * @param spareField 备用字段
         */
        public Builder spareField(String spareField) {
            obj.setSpareField(spareField);
            return this;
        }

        /**
         * 身份证号码
         * @param pIdcard 身份证号码
         */
        public Builder pIdcard(String pIdcard) {
            obj.setpIdcard(pIdcard);
            return this;
        }

        public InitiatePossessor build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        projectId("project_id", "projectId", "INTEGER", false),
        pType("p_type", "pType", "INTEGER", false),
        pEntrustmentUnit("p_entrustment_unit", "pEntrustmentUnit", "VARCHAR", false),
        pLegalRepresentative("p_legal_representative", "pLegalRepresentative", "VARCHAR", false),
        pSociologyCode("p_sociology_code", "pSociologyCode", "VARCHAR", false),
        pAddress("p_address", "pAddress", "VARCHAR", false),
        pScopeOperation("p_scope_operation", "pScopeOperation", "VARCHAR", false),
        pUnitProperties("p_unit_properties", "pUnitProperties", "VARCHAR", false),
        pName("p_name", "pName", "VARCHAR", false),
        spareField("spare_field", "spareField", "VARCHAR", false),
        pIdcard("p_idcard", "pIdcard", "VARCHAR", false);

        /**
         * tb_initiate_possessor
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_initiate_possessor
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_initiate_possessor
         */
        private final String column;

        /**
         * tb_initiate_possessor
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_initiate_possessor
         */
        private final String javaProperty;

        /**
         * tb_initiate_possessor
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