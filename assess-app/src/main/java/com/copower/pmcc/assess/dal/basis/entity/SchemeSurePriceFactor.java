package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SchemeSurePriceFactor implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 申报记录id
     */
    private Integer declareId;

    /**
     * 估价对象id
     */
    private Integer judgeObjectId;

    /**
     * 因素
     */
    private String factor;

    /**
     * 说明
     */
    private String remark;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 系数
     */
    private BigDecimal coefficient;

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
     * tb_scheme_sure_price_factor
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
     * 申报记录id
     * @return declare_id 申报记录id
     */
    public Integer getDeclareId() {
        return declareId;
    }

    /**
     * 申报记录id
     * @param declareId 申报记录id
     */
    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    /**
     * 估价对象id
     * @return judge_object_id 估价对象id
     */
    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    /**
     * 估价对象id
     * @param judgeObjectId 估价对象id
     */
    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    /**
     * 因素
     * @return factor 因素
     */
    public String getFactor() {
        return factor;
    }

    /**
     * 因素
     * @param factor 因素
     */
    public void setFactor(String factor) {
        this.factor = factor == null ? null : factor.trim();
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
     * 系数
     * @return coefficient 系数
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 系数
     * @param coefficient 系数
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
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

    public static SchemeSurePriceFactor.Builder builder() {
        return new SchemeSurePriceFactor.Builder();
    }

    /**
     * tb_scheme_sure_price_factor
     */
    public static class Builder {
        /**
         * tb_scheme_sure_price_factor
         */
        private SchemeSurePriceFactor obj;

        public Builder() {
            this.obj = new SchemeSurePriceFactor();
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
         * 申报记录id
         * @param declareId 申报记录id
         */
        public Builder declareId(Integer declareId) {
            obj.setDeclareId(declareId);
            return this;
        }

        /**
         * 估价对象id
         * @param judgeObjectId 估价对象id
         */
        public Builder judgeObjectId(Integer judgeObjectId) {
            obj.setJudgeObjectId(judgeObjectId);
            return this;
        }

        /**
         * 因素
         * @param factor 因素
         */
        public Builder factor(String factor) {
            obj.setFactor(factor);
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

        /**
         * 类型
         * @param type 类型
         */
        public Builder type(Integer type) {
            obj.setType(type);
            return this;
        }

        /**
         * 系数
         * @param coefficient 系数
         */
        public Builder coefficient(BigDecimal coefficient) {
            obj.setCoefficient(coefficient);
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

        public SchemeSurePriceFactor build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        declareId("declare_id", "declareId", "INTEGER", false),
        judgeObjectId("judge_object_id", "judgeObjectId", "INTEGER", false),
        factor("factor", "factor", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false),
        type("type", "type", "INTEGER", false),
        coefficient("coefficient", "coefficient", "DECIMAL", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_scheme_sure_price_factor
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_scheme_sure_price_factor
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_scheme_sure_price_factor
         */
        private final String column;

        /**
         * tb_scheme_sure_price_factor
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_scheme_sure_price_factor
         */
        private final String javaProperty;

        /**
         * tb_scheme_sure_price_factor
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