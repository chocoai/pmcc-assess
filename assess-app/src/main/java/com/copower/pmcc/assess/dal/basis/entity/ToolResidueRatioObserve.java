package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ToolResidueRatioObserve implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer masterId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 类别
     */
    private Integer category;

    /**
     * 实体状况
     */
    private String entityCondition;

    /**
     * 实体状况内容
     */
    private String entityConditionContent;

    /**
     * 标准得分
     */
    private BigDecimal standardScore;

    /**
     * 得分
     */
    private BigDecimal score;

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
     * tb_tool_residue_ratio_observe
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
     * @return master_id 
     */
    public Integer getMasterId() {
        return masterId;
    }

    /**
     * 
     * @param masterId 
     */
    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    /**
     * 类型
     * @return type 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 类别
     * @return category 类别
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 类别
     * @param category 类别
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 实体状况
     * @return entity_condition 实体状况
     */
    public String getEntityCondition() {
        return entityCondition;
    }

    /**
     * 实体状况
     * @param entityCondition 实体状况
     */
    public void setEntityCondition(String entityCondition) {
        this.entityCondition = entityCondition == null ? null : entityCondition.trim();
    }

    /**
     * 实体状况内容
     * @return entity_condition_content 实体状况内容
     */
    public String getEntityConditionContent() {
        return entityConditionContent;
    }

    /**
     * 实体状况内容
     * @param entityConditionContent 实体状况内容
     */
    public void setEntityConditionContent(String entityConditionContent) {
        this.entityConditionContent = entityConditionContent == null ? null : entityConditionContent.trim();
    }

    /**
     * 标准得分
     * @return standard_score 标准得分
     */
    public BigDecimal getStandardScore() {
        return standardScore;
    }

    /**
     * 标准得分
     * @param standardScore 标准得分
     */
    public void setStandardScore(BigDecimal standardScore) {
        this.standardScore = standardScore;
    }

    /**
     * 得分
     * @return score 得分
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 得分
     * @param score 得分
     */
    public void setScore(BigDecimal score) {
        this.score = score;
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

    public static ToolResidueRatioObserve.Builder builder() {
        return new ToolResidueRatioObserve.Builder();
    }

    /**
     * tb_tool_residue_ratio_observe
     */
    public static class Builder {
        /**
         * tb_tool_residue_ratio_observe
         */
        private ToolResidueRatioObserve obj;

        public Builder() {
            this.obj = new ToolResidueRatioObserve();
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
         * @param masterId 
         */
        public Builder masterId(Integer masterId) {
            obj.setMasterId(masterId);
            return this;
        }

        /**
         * 类型
         * @param type 类型
         */
        public Builder type(Integer type) {
            obj.setType(type);
            return this;
        }

        /**
         * 类别
         * @param category 类别
         */
        public Builder category(Integer category) {
            obj.setCategory(category);
            return this;
        }

        /**
         * 实体状况
         * @param entityCondition 实体状况
         */
        public Builder entityCondition(String entityCondition) {
            obj.setEntityCondition(entityCondition);
            return this;
        }

        /**
         * 实体状况内容
         * @param entityConditionContent 实体状况内容
         */
        public Builder entityConditionContent(String entityConditionContent) {
            obj.setEntityConditionContent(entityConditionContent);
            return this;
        }

        /**
         * 标准得分
         * @param standardScore 标准得分
         */
        public Builder standardScore(BigDecimal standardScore) {
            obj.setStandardScore(standardScore);
            return this;
        }

        /**
         * 得分
         * @param score 得分
         */
        public Builder score(BigDecimal score) {
            obj.setScore(score);
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

        public ToolResidueRatioObserve build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        masterId("master_id", "masterId", "INTEGER", false),
        type("type", "type", "INTEGER", false),
        category("category", "category", "INTEGER", false),
        entityCondition("entity_condition", "entityCondition", "VARCHAR", false),
        entityConditionContent("entity_condition_content", "entityConditionContent", "VARCHAR", false),
        standardScore("standard_score", "standardScore", "DECIMAL", false),
        score("score", "score", "DECIMAL", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_tool_residue_ratio_observe
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_tool_residue_ratio_observe
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_tool_residue_ratio_observe
         */
        private final String column;

        /**
         * tb_tool_residue_ratio_observe
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_tool_residue_ratio_observe
         */
        private final String javaProperty;

        /**
         * tb_tool_residue_ratio_observe
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