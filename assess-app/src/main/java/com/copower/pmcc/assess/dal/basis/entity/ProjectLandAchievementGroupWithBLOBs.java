package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjectLandAchievementGroupWithBLOBs extends ProjectLandAchievementGroup implements Serializable {
    /**
     * key_value方式存储json数据
     */
    private String keyValue;

    /**
     * 说明
     */
    private String remark;

    /**
     * tb_project_land_achievement_group
     */
    private static final long serialVersionUID = 1L;

    /**
     * key_value方式存储json数据
     * @return key_value key_value方式存储json数据
     */
    public String getKeyValue() {
        return keyValue;
    }

    /**
     * key_value方式存储json数据
     * @param keyValue key_value方式存储json数据
     */
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue == null ? null : keyValue.trim();
    }

    /**
     * 说明
     * @return remark 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 说明
     * @param remark 说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public static ProjectLandAchievementGroupWithBLOBs.Builder builder() {
        return new ProjectLandAchievementGroupWithBLOBs.Builder();
    }

    /**
     * tb_project_land_achievement_group
     */
    public static class Builder extends ProjectLandAchievementGroup.Builder {
        /**
         * tb_project_land_achievement_group
         */
        private ProjectLandAchievementGroupWithBLOBs obj;

        public Builder() {
            this.obj = new ProjectLandAchievementGroupWithBLOBs();
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
         * 项目id
         * @param projectId 项目id
         */
        public Builder projectId(Integer projectId) {
            obj.setProjectId(projectId);
            return this;
        }

        /**
         * 数据来源表
         * @param dataTableName 数据来源表
         */
        public Builder dataTableName(String dataTableName) {
            obj.setDataTableName(dataTableName);
            return this;
        }

        /**
         * 数据来源表id
         * @param dataTableId 数据来源表id
         */
        public Builder dataTableId(Integer dataTableId) {
            obj.setDataTableId(dataTableId);
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
         * 类别
         * @param category 类别
         */
        public Builder category(String category) {
            obj.setCategory(category);
            return this;
        }

        /**
         * 因素（第一级）
         * @param classification 因素（第一级）
         */
        public Builder classification(String classification) {
            obj.setClassification(classification);
            return this;
        }

        /**
         * 选中id
         * @param selectId 选中id
         */
        public Builder selectId(Integer selectId) {
            obj.setSelectId(selectId);
            return this;
        }

        /**
         * 选中值
         * @param selectValue 选中值
         */
        public Builder selectValue(String selectValue) {
            obj.setSelectValue(selectValue);
            return this;
        }

        /**
         * tb_data_land_level_detail_achievement id
         * @param achievementIds tb_data_land_level_detail_achievement id
         */
        public Builder achievementIds(String achievementIds) {
            obj.setAchievementIds(achievementIds);
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
        public Builder gmtCreated(java.util.Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(java.util.Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        /**
         * key_value方式存储json数据
         * @param keyValue key_value方式存储json数据
         */
        public Builder keyValue(String keyValue) {
            obj.setKeyValue(keyValue);
            return this;
        }

        /**
         * 说明
         * @param remark 说明
         */
        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public ProjectLandAchievementGroupWithBLOBs build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        projectId("project_id", "projectId", "INTEGER", false),
        dataTableName("data_table_name", "dataTableName", "VARCHAR", false),
        dataTableId("data_table_id", "dataTableId", "INTEGER", false),
        type("type", "type", "VARCHAR", false),
        category("category", "category", "VARCHAR", false),
        classification("classification", "classification", "VARCHAR", false),
        selectId("select_id", "selectId", "INTEGER", false),
        selectValue("select_value", "selectValue", "VARCHAR", false),
        achievementIds("achievement_ids", "achievementIds", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        keyValue("key_value", "keyValue", "LONGVARCHAR", false),
        remark("remark", "remark", "LONGVARCHAR", false);

        /**
         * tb_project_land_achievement_group
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_project_land_achievement_group
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_project_land_achievement_group
         */
        private final String column;

        /**
         * tb_project_land_achievement_group
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_project_land_achievement_group
         */
        private final String javaProperty;

        /**
         * tb_project_land_achievement_group
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