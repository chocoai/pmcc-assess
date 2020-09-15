package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MdCostApproachItem implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 主表id
     */
    private Integer masterId;

    /**
     * 年份描述
     */
    private String yearDescribe;

    /**
     * 平均产值
     */
    private BigDecimal averageProduction;

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
     * tb_md_cost_approach_item
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
     * 年份描述
     * @return year_describe 年份描述
     */
    public String getYearDescribe() {
        return yearDescribe;
    }

    /**
     * 年份描述
     * @param yearDescribe 年份描述
     */
    public void setYearDescribe(String yearDescribe) {
        this.yearDescribe = yearDescribe == null ? null : yearDescribe.trim();
    }

    /**
     * 平均产值
     * @return average_production 平均产值
     */
    public BigDecimal getAverageProduction() {
        return averageProduction;
    }

    /**
     * 平均产值
     * @param averageProduction 平均产值
     */
    public void setAverageProduction(BigDecimal averageProduction) {
        this.averageProduction = averageProduction;
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

    public static MdCostApproachItem.Builder builder() {
        return new MdCostApproachItem.Builder();
    }

    /**
     * tb_md_cost_approach_item
     */
    public static class Builder {
        /**
         * tb_md_cost_approach_item
         */
        private MdCostApproachItem obj;

        public Builder() {
            this.obj = new MdCostApproachItem();
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
         * @param masterId 主表id
         */
        public Builder masterId(Integer masterId) {
            obj.setMasterId(masterId);
            return this;
        }

        /**
         * 年份描述
         * @param yearDescribe 年份描述
         */
        public Builder yearDescribe(String yearDescribe) {
            obj.setYearDescribe(yearDescribe);
            return this;
        }

        /**
         * 平均产值
         * @param averageProduction 平均产值
         */
        public Builder averageProduction(BigDecimal averageProduction) {
            obj.setAverageProduction(averageProduction);
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

        public MdCostApproachItem build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        masterId("master_id", "masterId", "INTEGER", false),
        yearDescribe("year_describe", "yearDescribe", "VARCHAR", false),
        averageProduction("average_production", "averageProduction", "DECIMAL", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_md_cost_approach_item
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_md_cost_approach_item
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_md_cost_approach_item
         */
        private final String column;

        /**
         * tb_md_cost_approach_item
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_md_cost_approach_item
         */
        private final String javaProperty;

        /**
         * tb_md_cost_approach_item
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