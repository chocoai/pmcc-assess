package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclarePreSalePermit implements Serializable {
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
     * 售房单位
     */
    private String salesUnit;

    /**
     * 法定代表人
     */
    private String legalRepresentative;

    /**
     * 项目坐落
     */
    private String beLocated;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 预售面积
     */
    private BigDecimal preSaleArea;

    /**
     * 预售范围
     */
    private String preSaleScope;

    /**
     * 房屋用途
     */
    private String housingUse;

    /**
     * 建筑结构
     */
    private String buildingStructure;

    /**
     * 预售款监管信息
     */
    private String preSaleSupervisionInformation;

    /**
     * 日期
     */
    private Date date;

    /**
     * 在建工程抵押情况
     */
    private String mortgageSituation;

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
     * tb_declare_pre_sale_permit
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
     * 售房单位
     * @return sales_unit 售房单位
     */
    public String getSalesUnit() {
        return salesUnit;
    }

    /**
     * 售房单位
     * @param salesUnit 售房单位
     */
    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit == null ? null : salesUnit.trim();
    }

    /**
     * 法定代表人
     * @return legal_representative 法定代表人
     */
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    /**
     * 法定代表人
     * @param legalRepresentative 法定代表人
     */
    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
    }

    /**
     * 项目坐落
     * @return be_located 项目坐落
     */
    public String getBeLocated() {
        return beLocated;
    }

    /**
     * 项目坐落
     * @param beLocated 项目坐落
     */
    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated == null ? null : beLocated.trim();
    }

    /**
     * 项目名称
     * @return name 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 项目名称
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 预售面积
     * @return pre_sale_area 预售面积
     */
    public BigDecimal getPreSaleArea() {
        return preSaleArea;
    }

    /**
     * 预售面积
     * @param preSaleArea 预售面积
     */
    public void setPreSaleArea(BigDecimal preSaleArea) {
        this.preSaleArea = preSaleArea;
    }

    /**
     * 预售范围
     * @return pre_sale_scope 预售范围
     */
    public String getPreSaleScope() {
        return preSaleScope;
    }

    /**
     * 预售范围
     * @param preSaleScope 预售范围
     */
    public void setPreSaleScope(String preSaleScope) {
        this.preSaleScope = preSaleScope == null ? null : preSaleScope.trim();
    }

    /**
     * 房屋用途
     * @return housing_use 房屋用途
     */
    public String getHousingUse() {
        return housingUse;
    }

    /**
     * 房屋用途
     * @param housingUse 房屋用途
     */
    public void setHousingUse(String housingUse) {
        this.housingUse = housingUse == null ? null : housingUse.trim();
    }

    /**
     * 建筑结构
     * @return building_structure 建筑结构
     */
    public String getBuildingStructure() {
        return buildingStructure;
    }

    /**
     * 建筑结构
     * @param buildingStructure 建筑结构
     */
    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure == null ? null : buildingStructure.trim();
    }

    /**
     * 预售款监管信息
     * @return pre_sale_supervision_information 预售款监管信息
     */
    public String getPreSaleSupervisionInformation() {
        return preSaleSupervisionInformation;
    }

    /**
     * 预售款监管信息
     * @param preSaleSupervisionInformation 预售款监管信息
     */
    public void setPreSaleSupervisionInformation(String preSaleSupervisionInformation) {
        this.preSaleSupervisionInformation = preSaleSupervisionInformation == null ? null : preSaleSupervisionInformation.trim();
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
     * 在建工程抵押情况
     * @return mortgage_situation 在建工程抵押情况
     */
    public String getMortgageSituation() {
        return mortgageSituation;
    }

    /**
     * 在建工程抵押情况
     * @param mortgageSituation 在建工程抵押情况
     */
    public void setMortgageSituation(String mortgageSituation) {
        this.mortgageSituation = mortgageSituation == null ? null : mortgageSituation.trim();
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

    public static DeclarePreSalePermit.Builder builder() {
        return new DeclarePreSalePermit.Builder();
    }

    /**
     * tb_declare_pre_sale_permit
     */
    public static class Builder {
        /**
         * tb_declare_pre_sale_permit
         */
        private DeclarePreSalePermit obj;

        public Builder() {
            this.obj = new DeclarePreSalePermit();
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
         * 售房单位
         * @param salesUnit 售房单位
         */
        public Builder salesUnit(String salesUnit) {
            obj.setSalesUnit(salesUnit);
            return this;
        }

        /**
         * 法定代表人
         * @param legalRepresentative 法定代表人
         */
        public Builder legalRepresentative(String legalRepresentative) {
            obj.setLegalRepresentative(legalRepresentative);
            return this;
        }

        /**
         * 项目坐落
         * @param beLocated 项目坐落
         */
        public Builder beLocated(String beLocated) {
            obj.setBeLocated(beLocated);
            return this;
        }

        /**
         * 项目名称
         * @param name 项目名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 预售面积
         * @param preSaleArea 预售面积
         */
        public Builder preSaleArea(BigDecimal preSaleArea) {
            obj.setPreSaleArea(preSaleArea);
            return this;
        }

        /**
         * 预售范围
         * @param preSaleScope 预售范围
         */
        public Builder preSaleScope(String preSaleScope) {
            obj.setPreSaleScope(preSaleScope);
            return this;
        }

        /**
         * 房屋用途
         * @param housingUse 房屋用途
         */
        public Builder housingUse(String housingUse) {
            obj.setHousingUse(housingUse);
            return this;
        }

        public DeclarePreSalePermit build() {
            return this.obj;
        }

        /**
         * 建筑结构
         * @param buildingStructure 建筑结构
         */
        public Builder buildingStructure(String buildingStructure) {
            obj.setBuildingStructure(buildingStructure);
            return this;
        }

        /**
         * 预售款监管信息
         * @param preSaleSupervisionInformation 预售款监管信息
         */
        public Builder preSaleSupervisionInformation(String preSaleSupervisionInformation) {
            obj.setPreSaleSupervisionInformation(preSaleSupervisionInformation);
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
         * 在建工程抵押情况
         * @param mortgageSituation 在建工程抵押情况
         */
        public Builder mortgageSituation(String mortgageSituation) {
            obj.setMortgageSituation(mortgageSituation);
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
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        certificateNumber("certificate_number", "certificateNumber", "VARCHAR", false),
        issuingOrgan("issuing_organ", "issuingOrgan", "VARCHAR", false),
        salesUnit("sales_unit", "salesUnit", "VARCHAR", false),
        legalRepresentative("legal_representative", "legalRepresentative", "VARCHAR", false),
        beLocated("be_located", "beLocated", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        preSaleArea("pre_sale_area", "preSaleArea", "DECIMAL", false),
        preSaleScope("pre_sale_scope", "preSaleScope", "VARCHAR", false),
        housingUse("housing_use", "housingUse", "VARCHAR", false),
        buildingStructure("building_structure", "buildingStructure", "VARCHAR", false),
        preSaleSupervisionInformation("pre_sale_supervision_information", "preSaleSupervisionInformation", "VARCHAR", false),
        date("date", "date", "TIMESTAMP", false),
        mortgageSituation("mortgage_situation", "mortgageSituation", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        remark("remark", "remark", "VARCHAR", false),
        masterId("master_id", "masterId", "INTEGER", false),
        masterType("master_type", "masterType", "VARCHAR", false);

        /**
         * tb_declare_pre_sale_permit
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_pre_sale_permit
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_pre_sale_permit
         */
        private final String column;

        /**
         * tb_declare_pre_sale_permit
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_pre_sale_permit
         */
        private final String javaProperty;

        /**
         * tb_declare_pre_sale_permit
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