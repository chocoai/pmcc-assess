package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ProjectLandAchievementGroup implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 数据来源表
     */
    private String dataTableName;

    /**
     * 数据来源表id
     */
    private Integer dataTableId;

    /**
     * 类型
     */
    private String type;

    /**
     * 类别
     */
    private String category;

    /**
     * 因素（第一级）
     */
    private String classification;

    /**
     * 选中id
     */
    private Integer selectId;

    /**
     * 选中值
     */
    private String selectValue;

    /**
     * tb_data_land_level_detail_achievement id
     */
    private String achievementIds;

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
     * tb_project_land_achievement_group
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
     * 数据来源表
     * @return data_table_name 数据来源表
     */
    public String getDataTableName() {
        return dataTableName;
    }

    /**
     * 数据来源表
     * @param dataTableName 数据来源表
     */
    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName == null ? null : dataTableName.trim();
    }

    /**
     * 数据来源表id
     * @return data_table_id 数据来源表id
     */
    public Integer getDataTableId() {
        return dataTableId;
    }

    /**
     * 数据来源表id
     * @param dataTableId 数据来源表id
     */
    public void setDataTableId(Integer dataTableId) {
        this.dataTableId = dataTableId;
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
     * 类别
     * @return category 类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 类别
     * @param category 类别
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * 因素（第一级）
     * @return classification 因素（第一级）
     */
    public String getClassification() {
        return classification;
    }

    /**
     * 因素（第一级）
     * @param classification 因素（第一级）
     */
    public void setClassification(String classification) {
        this.classification = classification == null ? null : classification.trim();
    }

    /**
     * 选中id
     * @return select_id 选中id
     */
    public Integer getSelectId() {
        return selectId;
    }

    /**
     * 选中id
     * @param selectId 选中id
     */
    public void setSelectId(Integer selectId) {
        this.selectId = selectId;
    }

    /**
     * 选中值
     * @return select_value 选中值
     */
    public String getSelectValue() {
        return selectValue;
    }

    /**
     * 选中值
     * @param selectValue 选中值
     */
    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue == null ? null : selectValue.trim();
    }

    /**
     * tb_data_land_level_detail_achievement id
     * @return achievement_ids tb_data_land_level_detail_achievement id
     */
    public String getAchievementIds() {
        return achievementIds;
    }

    /**
     * tb_data_land_level_detail_achievement id
     * @param achievementIds tb_data_land_level_detail_achievement id
     */
    public void setAchievementIds(String achievementIds) {
        this.achievementIds = achievementIds == null ? null : achievementIds.trim();
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

    public static ProjectLandAchievementGroup.Builder builder() {
        return new ProjectLandAchievementGroup.Builder();
    }

    /**
     * tb_project_land_achievement_group
     */
    public static class Builder {
        /**
         * tb_project_land_achievement_group
         */
        private ProjectLandAchievementGroup obj;

        public Builder() {
            this.obj = new ProjectLandAchievementGroup();
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

        public ProjectLandAchievementGroup build() {
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