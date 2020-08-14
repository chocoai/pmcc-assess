package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareApply implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 计划详细id
     */
    private Integer planDetailsId;

    /**
     * 流程实例id
     */
    private String processInsId;

    /**
     * 申请类型
     */
    private String applyType;

    /**
     * 委托单位
     */
    private String client;

    /**
     * 完成时限
     */
    private Date dateLimit;

    /**
     * 流程状态
     */
    private String status;

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
     * 名称
     */
    private String name;

    /**
     * 评估面积
     */
    private BigDecimal assessArea;

    /**
     * 评估金额
     */
    private BigDecimal assessMoney;

    /**
     * 评估机构
     */
    private String assessOrganization;

    /**
     * 备注
     */
    private String remark;

    /**
     * tb_declare_apply
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
     * 计划详细id
     * @return plan_details_id 计划详细id
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 计划详细id
     * @param planDetailsId 计划详细id
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * 流程实例id
     * @return process_ins_id 流程实例id
     */
    public String getProcessInsId() {
        return processInsId;
    }

    /**
     * 流程实例id
     * @param processInsId 流程实例id
     */
    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    /**
     * 申请类型
     * @return apply_type 申请类型
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * 申请类型
     * @param applyType 申请类型
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    /**
     * 委托单位
     * @return client 委托单位
     */
    public String getClient() {
        return client;
    }

    /**
     * 委托单位
     * @param client 委托单位
     */
    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
    }

    /**
     * 完成时限
     * @return date_limit 完成时限
     */
    public Date getDateLimit() {
        return dateLimit;
    }

    /**
     * 完成时限
     * @param dateLimit 完成时限
     */
    public void setDateLimit(Date dateLimit) {
        this.dateLimit = dateLimit;
    }

    /**
     * 流程状态
     * @return status 流程状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 流程状态
     * @param status 流程状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 评估面积
     * @return assess_area 评估面积
     */
    public BigDecimal getAssessArea() {
        return assessArea;
    }

    /**
     * 评估面积
     * @param assessArea 评估面积
     */
    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    /**
     * 评估金额
     * @return assess_money 评估金额
     */
    public BigDecimal getAssessMoney() {
        return assessMoney;
    }

    /**
     * 评估金额
     * @param assessMoney 评估金额
     */
    public void setAssessMoney(BigDecimal assessMoney) {
        this.assessMoney = assessMoney;
    }

    /**
     * 评估机构
     * @return assess_organization 评估机构
     */
    public String getAssessOrganization() {
        return assessOrganization;
    }

    /**
     * 评估机构
     * @param assessOrganization 评估机构
     */
    public void setAssessOrganization(String assessOrganization) {
        this.assessOrganization = assessOrganization == null ? null : assessOrganization.trim();
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

    public static DeclareApply.Builder builder() {
        return new DeclareApply.Builder();
    }

    /**
     * tb_declare_apply
     */
    public static class Builder {
        /**
         * tb_declare_apply
         */
        private DeclareApply obj;

        public Builder() {
            this.obj = new DeclareApply();
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
         * 计划详细id
         * @param planDetailsId 计划详细id
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * 流程实例id
         * @param processInsId 流程实例id
         */
        public Builder processInsId(String processInsId) {
            obj.setProcessInsId(processInsId);
            return this;
        }

        /**
         * 申请类型
         * @param applyType 申请类型
         */
        public Builder applyType(String applyType) {
            obj.setApplyType(applyType);
            return this;
        }

        /**
         * 委托单位
         * @param client 委托单位
         */
        public Builder client(String client) {
            obj.setClient(client);
            return this;
        }

        /**
         * 完成时限
         * @param dateLimit 完成时限
         */
        public Builder dateLimit(Date dateLimit) {
            obj.setDateLimit(dateLimit);
            return this;
        }

        /**
         * 流程状态
         * @param status 流程状态
         */
        public Builder status(String status) {
            obj.setStatus(status);
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
         * 名称
         * @param name 名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 评估面积
         * @param assessArea 评估面积
         */
        public Builder assessArea(BigDecimal assessArea) {
            obj.setAssessArea(assessArea);
            return this;
        }

        /**
         * 评估金额
         * @param assessMoney 评估金额
         */
        public Builder assessMoney(BigDecimal assessMoney) {
            obj.setAssessMoney(assessMoney);
            return this;
        }

        /**
         * 评估机构
         * @param assessOrganization 评估机构
         */
        public Builder assessOrganization(String assessOrganization) {
            obj.setAssessOrganization(assessOrganization);
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

        public DeclareApply build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        projectId("project_id", "projectId", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        processInsId("process_ins_id", "processInsId", "VARCHAR", false),
        applyType("apply_type", "applyType", "VARCHAR", false),
        client("client", "client", "VARCHAR", false),
        dateLimit("date_limit", "dateLimit", "TIMESTAMP", false),
        status("status", "status", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        name("name", "name", "VARCHAR", false),
        assessArea("assess_area", "assessArea", "DECIMAL", false),
        assessMoney("assess_money", "assessMoney", "DECIMAL", false),
        assessOrganization("assess_organization", "assessOrganization", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false);

        /**
         * tb_declare_apply
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_apply
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_apply
         */
        private final String column;

        /**
         * tb_declare_apply
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_apply
         */
        private final String javaProperty;

        /**
         * tb_declare_apply
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