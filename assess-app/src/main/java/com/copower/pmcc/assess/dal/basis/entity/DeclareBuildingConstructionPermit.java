package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareBuildingConstructionPermit implements Serializable {
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
    private String buildUnit;

    /**
     * 工程名称
     */
    private String name;

    /**
     * 建设地址
     */
    private String buildAddress;

    /**
     * 建设规模
     */
    private String scaleConstruction;

    /**
     * 勘察单位
     */
    private String reconnaissanceUnit;

    /**
     * 设计单位
     */
    private String designUnit;

    /**
     * 施工单位
     */
    private String constructionUnit;

    /**
     * 监理单位
     */
    private String constructionControlUnit;

    /**
     * 勘察单位项目负责人
     */
    private String reconnaissanceUnitPerson;

    /**
     * 设计单位项目负责人
     */
    private String designUnitPerson;

    /**
     * 施工单位项目负责人
     */
    private String constructionUnitPerson;

    /**
     * 总监理工程师
     */
    private String chiefEngineerConstructionInspection;

    /**
     * 合同日期
     */
    private Date contractPeriod;

    /**
     * 备注
     */
    private String remark;

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
     * 主表id
     */
    private Integer masterId;

    /**
     * 主表类型
     */
    private String masterType;

    /**
     * tb_declare_building_construction_permit
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
     * @return build_unit 建设单位（个人）
     */
    public String getBuildUnit() {
        return buildUnit;
    }

    /**
     * 建设单位（个人）
     * @param buildUnit 建设单位（个人）
     */
    public void setBuildUnit(String buildUnit) {
        this.buildUnit = buildUnit == null ? null : buildUnit.trim();
    }

    /**
     * 工程名称
     * @return name 工程名称
     */
    public String getName() {
        return name;
    }

    /**
     * 工程名称
     * @param name 工程名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 建设地址
     * @return build_address 建设地址
     */
    public String getBuildAddress() {
        return buildAddress;
    }

    /**
     * 建设地址
     * @param buildAddress 建设地址
     */
    public void setBuildAddress(String buildAddress) {
        this.buildAddress = buildAddress == null ? null : buildAddress.trim();
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
     * 勘察单位
     * @return reconnaissance_unit 勘察单位
     */
    public String getReconnaissanceUnit() {
        return reconnaissanceUnit;
    }

    /**
     * 勘察单位
     * @param reconnaissanceUnit 勘察单位
     */
    public void setReconnaissanceUnit(String reconnaissanceUnit) {
        this.reconnaissanceUnit = reconnaissanceUnit == null ? null : reconnaissanceUnit.trim();
    }

    /**
     * 设计单位
     * @return design_unit 设计单位
     */
    public String getDesignUnit() {
        return designUnit;
    }

    /**
     * 设计单位
     * @param designUnit 设计单位
     */
    public void setDesignUnit(String designUnit) {
        this.designUnit = designUnit == null ? null : designUnit.trim();
    }

    /**
     * 施工单位
     * @return construction_unit 施工单位
     */
    public String getConstructionUnit() {
        return constructionUnit;
    }

    /**
     * 施工单位
     * @param constructionUnit 施工单位
     */
    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit == null ? null : constructionUnit.trim();
    }

    /**
     * 监理单位
     * @return construction_control_unit 监理单位
     */
    public String getConstructionControlUnit() {
        return constructionControlUnit;
    }

    /**
     * 监理单位
     * @param constructionControlUnit 监理单位
     */
    public void setConstructionControlUnit(String constructionControlUnit) {
        this.constructionControlUnit = constructionControlUnit == null ? null : constructionControlUnit.trim();
    }

    /**
     * 勘察单位项目负责人
     * @return reconnaissance_unit_person 勘察单位项目负责人
     */
    public String getReconnaissanceUnitPerson() {
        return reconnaissanceUnitPerson;
    }

    /**
     * 勘察单位项目负责人
     * @param reconnaissanceUnitPerson 勘察单位项目负责人
     */
    public void setReconnaissanceUnitPerson(String reconnaissanceUnitPerson) {
        this.reconnaissanceUnitPerson = reconnaissanceUnitPerson == null ? null : reconnaissanceUnitPerson.trim();
    }

    /**
     * 设计单位项目负责人
     * @return design_unit_person 设计单位项目负责人
     */
    public String getDesignUnitPerson() {
        return designUnitPerson;
    }

    /**
     * 设计单位项目负责人
     * @param designUnitPerson 设计单位项目负责人
     */
    public void setDesignUnitPerson(String designUnitPerson) {
        this.designUnitPerson = designUnitPerson == null ? null : designUnitPerson.trim();
    }

    /**
     * 施工单位项目负责人
     * @return construction_unit_person 施工单位项目负责人
     */
    public String getConstructionUnitPerson() {
        return constructionUnitPerson;
    }

    /**
     * 施工单位项目负责人
     * @param constructionUnitPerson 施工单位项目负责人
     */
    public void setConstructionUnitPerson(String constructionUnitPerson) {
        this.constructionUnitPerson = constructionUnitPerson == null ? null : constructionUnitPerson.trim();
    }

    /**
     * 总监理工程师
     * @return chief_engineer_construction_inspection 总监理工程师
     */
    public String getChiefEngineerConstructionInspection() {
        return chiefEngineerConstructionInspection;
    }

    /**
     * 总监理工程师
     * @param chiefEngineerConstructionInspection 总监理工程师
     */
    public void setChiefEngineerConstructionInspection(String chiefEngineerConstructionInspection) {
        this.chiefEngineerConstructionInspection = chiefEngineerConstructionInspection == null ? null : chiefEngineerConstructionInspection.trim();
    }

    /**
     * 合同日期
     * @return contract_period 合同日期
     */
    public Date getContractPeriod() {
        return contractPeriod;
    }

    /**
     * 合同日期
     * @param contractPeriod 合同日期
     */
    public void setContractPeriod(Date contractPeriod) {
        this.contractPeriod = contractPeriod;
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

    public static DeclareBuildingConstructionPermit.Builder builder() {
        return new DeclareBuildingConstructionPermit.Builder();
    }

    /**
     * tb_declare_building_construction_permit
     */
    public static class Builder {
        /**
         * tb_declare_building_construction_permit
         */
        private DeclareBuildingConstructionPermit obj;

        public Builder() {
            this.obj = new DeclareBuildingConstructionPermit();
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

        public DeclareBuildingConstructionPermit build() {
            return this.obj;
        }

        /**
         * 建设单位（个人）
         * @param buildUnit 建设单位（个人）
         */
        public Builder buildUnit(String buildUnit) {
            obj.setBuildUnit(buildUnit);
            return this;
        }

        /**
         * 工程名称
         * @param name 工程名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 建设地址
         * @param buildAddress 建设地址
         */
        public Builder buildAddress(String buildAddress) {
            obj.setBuildAddress(buildAddress);
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
         * 勘察单位
         * @param reconnaissanceUnit 勘察单位
         */
        public Builder reconnaissanceUnit(String reconnaissanceUnit) {
            obj.setReconnaissanceUnit(reconnaissanceUnit);
            return this;
        }

        /**
         * 勘察单位项目负责人
         * @param reconnaissanceUnitPerson 勘察单位项目负责人
         */
        public Builder reconnaissanceUnitPerson(String reconnaissanceUnitPerson) {
            obj.setReconnaissanceUnitPerson(reconnaissanceUnitPerson);
            return this;
        }

        /**
         * 设计单位
         * @param designUnit 设计单位
         */
        public Builder designUnit(String designUnit) {
            obj.setDesignUnit(designUnit);
            return this;
        }

        /**
         * 设计单位项目负责人
         * @param designUnitPerson 设计单位项目负责人
         */
        public Builder designUnitPerson(String designUnitPerson) {
            obj.setDesignUnitPerson(designUnitPerson);
            return this;
        }

        /**
         * 施工单位
         * @param constructionUnit 施工单位
         */
        public Builder constructionUnit(String constructionUnit) {
            obj.setConstructionUnit(constructionUnit);
            return this;
        }

        /**
         * 施工单位项目负责人
         * @param constructionUnitPerson 施工单位项目负责人
         */
        public Builder constructionUnitPerson(String constructionUnitPerson) {
            obj.setConstructionUnitPerson(constructionUnitPerson);
            return this;
        }

        /**
         * 监理单位
         * @param constructionControlUnit 监理单位
         */
        public Builder constructionControlUnit(String constructionControlUnit) {
            obj.setConstructionControlUnit(constructionControlUnit);
            return this;
        }

        /**
         * 总监理工程师
         * @param chiefEngineerConstructionInspection 总监理工程师
         */
        public Builder chiefEngineerConstructionInspection(String chiefEngineerConstructionInspection) {
            obj.setChiefEngineerConstructionInspection(chiefEngineerConstructionInspection);
            return this;
        }

        /**
         * 合同日期
         * @param contractPeriod 合同日期
         */
        public Builder contractPeriod(Date contractPeriod) {
            obj.setContractPeriod(contractPeriod);
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
        date("date", "date", "TIMESTAMP", false),
        buildUnit("build_unit", "buildUnit", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        buildAddress("build_address", "buildAddress", "VARCHAR", false),
        scaleConstruction("scale_construction", "scaleConstruction", "VARCHAR", false),
        reconnaissanceUnit("reconnaissance_unit", "reconnaissanceUnit", "VARCHAR", false),
        designUnit("design_unit", "designUnit", "VARCHAR", false),
        constructionUnit("construction_unit", "constructionUnit", "VARCHAR", false),
        constructionControlUnit("construction_control_unit", "constructionControlUnit", "VARCHAR", false),
        reconnaissanceUnitPerson("reconnaissance_unit_person", "reconnaissanceUnitPerson", "VARCHAR", false),
        designUnitPerson("design_unit_person", "designUnitPerson", "VARCHAR", false),
        constructionUnitPerson("construction_unit_person", "constructionUnitPerson", "VARCHAR", false),
        chiefEngineerConstructionInspection("chief_engineer_construction_inspection", "chiefEngineerConstructionInspection", "VARCHAR", false),
        contractPeriod("contract_period", "contractPeriod", "TIMESTAMP", false),
        remark("remark", "remark", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        masterId("master_id", "masterId", "INTEGER", false),
        masterType("master_type", "masterType", "VARCHAR", false);

        /**
         * tb_declare_building_construction_permit
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_building_construction_permit
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_building_construction_permit
         */
        private final String column;

        /**
         * tb_declare_building_construction_permit
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_building_construction_permit
         */
        private final String javaProperty;

        /**
         * tb_declare_building_construction_permit
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