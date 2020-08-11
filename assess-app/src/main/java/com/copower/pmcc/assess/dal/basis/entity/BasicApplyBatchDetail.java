package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicApplyBatchDetail implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer pid;

    /**
     * 工作事项id
     */
    private Integer planDetailsId;

    /**
     * 
     */
    private Integer applyBatchId;

    /**
     * 申报记录id
     */
    private Integer declareRecordId;

    /**
     * 
     */
    private String declareRecordName;

    /**
     * estate&building&unit&house
     */
    private String tableName;

    /**
     * 关联数据的id
     */
    private Integer tableId;

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 
     */
    private String fullName;

    /**
     * 执行人
     */
    private String executor;

    /**
     * 引用数据id
     */
    private Integer quoteId;

    /**
     * 升级案列数据id
     */
    private Integer upgradeTableId;

    /**
     * 正常 nomal,引用状态 reference
     */
    private String modifyType;

    /**
     * 修改的内容
     */
    private String modifyContent;

    /**
     * 是否为案例数据
     */
    private Boolean bisFromCase;

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
     * tb_basic_apply_batch_detail
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
     * @return pid 
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 
     * @param pid 
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 工作事项id
     * @return plan_details_id 工作事项id
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 工作事项id
     * @param planDetailsId 工作事项id
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * 
     * @return apply_batch_id 
     */
    public Integer getApplyBatchId() {
        return applyBatchId;
    }

    /**
     * 
     * @param applyBatchId 
     */
    public void setApplyBatchId(Integer applyBatchId) {
        this.applyBatchId = applyBatchId;
    }

    /**
     * 申报记录id
     * @return declare_record_id 申报记录id
     */
    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    /**
     * 申报记录id
     * @param declareRecordId 申报记录id
     */
    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    /**
     * 
     * @return declare_record_name 
     */
    public String getDeclareRecordName() {
        return declareRecordName;
    }

    /**
     * 
     * @param declareRecordName 
     */
    public void setDeclareRecordName(String declareRecordName) {
        this.declareRecordName = declareRecordName == null ? null : declareRecordName.trim();
    }

    /**
     * estate&building&unit&house
     * @return table_name estate&building&unit&house
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * estate&building&unit&house
     * @param tableName estate&building&unit&house
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 关联数据的id
     * @return table_id 关联数据的id
     */
    public Integer getTableId() {
        return tableId;
    }

    /**
     * 关联数据的id
     * @param tableId 关联数据的id
     */
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    /**
     * 类型
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 显示名称
     * @return display_name 显示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 显示名称
     * @param displayName 显示名称
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * 
     * @return full_name 
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName 
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 执行人
     * @return executor 执行人
     */
    public String getExecutor() {
        return executor;
    }

    /**
     * 执行人
     * @param executor 执行人
     */
    public void setExecutor(String executor) {
        this.executor = executor == null ? null : executor.trim();
    }

    /**
     * 引用数据id
     * @return quote_id 引用数据id
     */
    public Integer getQuoteId() {
        return quoteId;
    }

    /**
     * 引用数据id
     * @param quoteId 引用数据id
     */
    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * 升级案列数据id
     * @return upgrade_table_id 升级案列数据id
     */
    public Integer getUpgradeTableId() {
        return upgradeTableId;
    }

    /**
     * 升级案列数据id
     * @param upgradeTableId 升级案列数据id
     */
    public void setUpgradeTableId(Integer upgradeTableId) {
        this.upgradeTableId = upgradeTableId;
    }

    /**
     * 正常 nomal,引用状态 reference
     * @return modify_type 正常 nomal,引用状态 reference
     */
    public String getModifyType() {
        return modifyType;
    }

    /**
     * 正常 nomal,引用状态 reference
     * @param modifyType 正常 nomal,引用状态 reference
     */
    public void setModifyType(String modifyType) {
        this.modifyType = modifyType == null ? null : modifyType.trim();
    }

    /**
     * 修改的内容
     * @return modify_content 修改的内容
     */
    public String getModifyContent() {
        return modifyContent;
    }

    /**
     * 修改的内容
     * @param modifyContent 修改的内容
     */
    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent == null ? null : modifyContent.trim();
    }

    /**
     * 是否为案例数据
     * @return bis_from_case 是否为案例数据
     */
    public Boolean getBisFromCase() {
        return bisFromCase;
    }

    /**
     * 是否为案例数据
     * @param bisFromCase 是否为案例数据
     */
    public void setBisFromCase(Boolean bisFromCase) {
        this.bisFromCase = bisFromCase;
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

    public static BasicApplyBatchDetail.Builder builder() {
        return new BasicApplyBatchDetail.Builder();
    }

    /**
     * tb_basic_apply_batch_detail
     */
    public static class Builder {
        /**
         * tb_basic_apply_batch_detail
         */
        private BasicApplyBatchDetail obj;

        public Builder() {
            this.obj = new BasicApplyBatchDetail();
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
         * @param pid 
         */
        public Builder pid(Integer pid) {
            obj.setPid(pid);
            return this;
        }

        /**
         * 工作事项id
         * @param planDetailsId 工作事项id
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * 
         * @param applyBatchId 
         */
        public Builder applyBatchId(Integer applyBatchId) {
            obj.setApplyBatchId(applyBatchId);
            return this;
        }

        /**
         * 申报记录id
         * @param declareRecordId 申报记录id
         */
        public Builder declareRecordId(Integer declareRecordId) {
            obj.setDeclareRecordId(declareRecordId);
            return this;
        }

        /**
         * 
         * @param declareRecordName 
         */
        public Builder declareRecordName(String declareRecordName) {
            obj.setDeclareRecordName(declareRecordName);
            return this;
        }

        /**
         * estate&building&unit&house
         * @param tableName estate&building&unit&house
         */
        public Builder tableName(String tableName) {
            obj.setTableName(tableName);
            return this;
        }

        /**
         * 关联数据的id
         * @param tableId 关联数据的id
         */
        public Builder tableId(Integer tableId) {
            obj.setTableId(tableId);
            return this;
        }

        /**
         * 类型
         * @param type 类型
         */
        public Builder type(String type) {
            obj.setType(type);
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
         * 显示名称
         * @param displayName 显示名称
         */
        public Builder displayName(String displayName) {
            obj.setDisplayName(displayName);
            return this;
        }

        /**
         * 
         * @param fullName 
         */
        public Builder fullName(String fullName) {
            obj.setFullName(fullName);
            return this;
        }

        /**
         * 执行人
         * @param executor 执行人
         */
        public Builder executor(String executor) {
            obj.setExecutor(executor);
            return this;
        }

        /**
         * 引用数据id
         * @param quoteId 引用数据id
         */
        public Builder quoteId(Integer quoteId) {
            obj.setQuoteId(quoteId);
            return this;
        }

        /**
         * 升级案列数据id
         * @param upgradeTableId 升级案列数据id
         */
        public Builder upgradeTableId(Integer upgradeTableId) {
            obj.setUpgradeTableId(upgradeTableId);
            return this;
        }

        /**
         * 正常 nomal,引用状态 reference
         * @param modifyType 正常 nomal,引用状态 reference
         */
        public Builder modifyType(String modifyType) {
            obj.setModifyType(modifyType);
            return this;
        }

        /**
         * 修改的内容
         * @param modifyContent 修改的内容
         */
        public Builder modifyContent(String modifyContent) {
            obj.setModifyContent(modifyContent);
            return this;
        }

        /**
         * 是否为案例数据
         * @param bisFromCase 是否为案例数据
         */
        public Builder bisFromCase(Boolean bisFromCase) {
            obj.setBisFromCase(bisFromCase);
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

        public BasicApplyBatchDetail build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        pid("pid", "pid", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        applyBatchId("apply_batch_id", "applyBatchId", "INTEGER", false),
        declareRecordId("declare_record_id", "declareRecordId", "INTEGER", false),
        declareRecordName("declare_record_name", "declareRecordName", "VARCHAR", false),
        tableName("table_name", "tableName", "VARCHAR", false),
        tableId("table_id", "tableId", "INTEGER", false),
        type("type", "type", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        displayName("display_name", "displayName", "VARCHAR", false),
        fullName("full_name", "fullName", "VARCHAR", false),
        executor("executor", "executor", "VARCHAR", false),
        quoteId("quote_id", "quoteId", "INTEGER", false),
        upgradeTableId("upgrade_table_id", "upgradeTableId", "INTEGER", false),
        modifyType("modify_type", "modifyType", "VARCHAR", false),
        modifyContent("modify_content", "modifyContent", "VARCHAR", false),
        bisFromCase("bis_from_case", "bisFromCase", "BIT", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_basic_apply_batch_detail
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_apply_batch_detail
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_apply_batch_detail
         */
        private final String column;

        /**
         * tb_basic_apply_batch_detail
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_apply_batch_detail
         */
        private final String javaProperty;

        /**
         * tb_basic_apply_batch_detail
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