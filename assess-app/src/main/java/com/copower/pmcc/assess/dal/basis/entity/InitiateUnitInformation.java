package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InitiateUnitInformation implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 是否写入到crm中
     */
    private Boolean infoWrite;

    /**
     * 报告使用单位名称
     */
    private String uUseUnitName;

    /**
     * 单位性质
     */
    private String uUnitProperties;

    /**
     * 经营范围
     */
    private String uScopeOperation;

    /**
     * 地址
     */
    private String uAddress;

    /**
     * 证照号
     */
    private String uCertificateNumber;

    /**
     * 法定代表人
     */
    private String uLegalRepresentative;

    /**
     * 报告使用单位
     */
    private String uUseUnit;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 评估类型
     */
    private String assessType;

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
     * tb_initiate_unit_information
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
     * 是否写入到crm中
     * @return info_write 是否写入到crm中
     */
    public Boolean getInfoWrite() {
        return infoWrite;
    }

    /**
     * 是否写入到crm中
     * @param infoWrite 是否写入到crm中
     */
    public void setInfoWrite(Boolean infoWrite) {
        this.infoWrite = infoWrite;
    }

    /**
     * 报告使用单位名称
     * @return u_use_unit_name 报告使用单位名称
     */
    public String getuUseUnitName() {
        return uUseUnitName;
    }

    /**
     * 报告使用单位名称
     * @param uUseUnitName 报告使用单位名称
     */
    public void setuUseUnitName(String uUseUnitName) {
        this.uUseUnitName = uUseUnitName == null ? null : uUseUnitName.trim();
    }

    /**
     * 单位性质
     * @return u_unit_properties 单位性质
     */
    public String getuUnitProperties() {
        return uUnitProperties;
    }

    /**
     * 单位性质
     * @param uUnitProperties 单位性质
     */
    public void setuUnitProperties(String uUnitProperties) {
        this.uUnitProperties = uUnitProperties == null ? null : uUnitProperties.trim();
    }

    /**
     * 经营范围
     * @return u_scope_operation 经营范围
     */
    public String getuScopeOperation() {
        return uScopeOperation;
    }

    /**
     * 经营范围
     * @param uScopeOperation 经营范围
     */
    public void setuScopeOperation(String uScopeOperation) {
        this.uScopeOperation = uScopeOperation == null ? null : uScopeOperation.trim();
    }

    /**
     * 地址
     * @return u_address 地址
     */
    public String getuAddress() {
        return uAddress;
    }

    /**
     * 地址
     * @param uAddress 地址
     */
    public void setuAddress(String uAddress) {
        this.uAddress = uAddress == null ? null : uAddress.trim();
    }

    /**
     * 证照号
     * @return u_certificate_number 证照号
     */
    public String getuCertificateNumber() {
        return uCertificateNumber;
    }

    /**
     * 证照号
     * @param uCertificateNumber 证照号
     */
    public void setuCertificateNumber(String uCertificateNumber) {
        this.uCertificateNumber = uCertificateNumber == null ? null : uCertificateNumber.trim();
    }

    /**
     * 法定代表人
     * @return u_legal_representative 法定代表人
     */
    public String getuLegalRepresentative() {
        return uLegalRepresentative;
    }

    /**
     * 法定代表人
     * @param uLegalRepresentative 法定代表人
     */
    public void setuLegalRepresentative(String uLegalRepresentative) {
        this.uLegalRepresentative = uLegalRepresentative == null ? null : uLegalRepresentative.trim();
    }

    /**
     * 报告使用单位
     * @return u_use_unit 报告使用单位
     */
    public String getuUseUnit() {
        return uUseUnit;
    }

    /**
     * 报告使用单位
     * @param uUseUnit 报告使用单位
     */
    public void setuUseUnit(String uUseUnit) {
        this.uUseUnit = uUseUnit == null ? null : uUseUnit.trim();
    }

    /**
     * 业务类型
     * @return business_type 业务类型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 业务类型
     * @param businessType 业务类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * 评估类型
     * @return assess_type 评估类型
     */
    public String getAssessType() {
        return assessType;
    }

    /**
     * 评估类型
     * @param assessType 评估类型
     */
    public void setAssessType(String assessType) {
        this.assessType = assessType == null ? null : assessType.trim();
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

    public static InitiateUnitInformation.Builder builder() {
        return new InitiateUnitInformation.Builder();
    }

    /**
     * tb_initiate_unit_information
     */
    public static class Builder {
        /**
         * tb_initiate_unit_information
         */
        private InitiateUnitInformation obj;

        public Builder() {
            this.obj = new InitiateUnitInformation();
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
         * 项目id
         * @param projectId 项目id
         */
        public Builder projectId(Integer projectId) {
            obj.setProjectId(projectId);
            return this;
        }

        /**
         * 是否写入到crm中
         * @param infoWrite 是否写入到crm中
         */
        public Builder infoWrite(Boolean infoWrite) {
            obj.setInfoWrite(infoWrite);
            return this;
        }

        /**
         * 报告使用单位
         * @param uUseUnit 报告使用单位
         */
        public Builder uUseUnit(String uUseUnit) {
            obj.setuUseUnit(uUseUnit);
            return this;
        }

        /**
         * 报告使用单位名称
         * @param uUseUnitName 报告使用单位名称
         */
        public Builder uUseUnitName(String uUseUnitName) {
            obj.setuUseUnitName(uUseUnitName);
            return this;
        }

        /**
         * 单位性质
         * @param uUnitProperties 单位性质
         */
        public Builder uUnitProperties(String uUnitProperties) {
            obj.setuUnitProperties(uUnitProperties);
            return this;
        }

        /**
         * 经营范围
         * @param uScopeOperation 经营范围
         */
        public Builder uScopeOperation(String uScopeOperation) {
            obj.setuScopeOperation(uScopeOperation);
            return this;
        }

        /**
         * 地址
         * @param uAddress 地址
         */
        public Builder uAddress(String uAddress) {
            obj.setuAddress(uAddress);
            return this;
        }

        /**
         * 证照号
         * @param uCertificateNumber 证照号
         */
        public Builder uCertificateNumber(String uCertificateNumber) {
            obj.setuCertificateNumber(uCertificateNumber);
            return this;
        }

        /**
         * 法定代表人
         * @param uLegalRepresentative 法定代表人
         */
        public Builder uLegalRepresentative(String uLegalRepresentative) {
            obj.setuLegalRepresentative(uLegalRepresentative);
            return this;
        }

        /**
         * 业务类型
         * @param businessType 业务类型
         */
        public Builder businessType(String businessType) {
            obj.setBusinessType(businessType);
            return this;
        }

        /**
         * 评估类型
         * @param assessType 评估类型
         */
        public Builder assessType(String assessType) {
            obj.setAssessType(assessType);
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

        public InitiateUnitInformation build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        projectId("project_id", "projectId", "INTEGER", false),
        infoWrite("info_write", "infoWrite", "BIT", false),
        uUseUnitName("u_use_unit_name", "uUseUnitName", "VARCHAR", false),
        uUnitProperties("u_unit_properties", "uUnitProperties", "VARCHAR", false),
        uScopeOperation("u_scope_operation", "uScopeOperation", "VARCHAR", false),
        uAddress("u_address", "uAddress", "VARCHAR", false),
        uCertificateNumber("u_certificate_number", "uCertificateNumber", "VARCHAR", false),
        uLegalRepresentative("u_legal_representative", "uLegalRepresentative", "VARCHAR", false),
        uUseUnit("u_use_unit", "uUseUnit", "VARCHAR", false),
        businessType("business_type", "businessType", "VARCHAR", false),
        assessType("assess_type", "assessType", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_initiate_unit_information
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_initiate_unit_information
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_initiate_unit_information
         */
        private final String column;

        /**
         * tb_initiate_unit_information
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_initiate_unit_information
         */
        private final String javaProperty;

        /**
         * tb_initiate_unit_information
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