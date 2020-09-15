package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MdCostApproachTaxes implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 主表id
     */
    private Integer masterId;

    /**
     * 类型
     */
    private String typeName;

    /**
     * 类型key
     */
    private String typeKey;

    /**
     * 标准1
     */
    private BigDecimal standardFirst;

    /**
     * 标准2
     */
    private BigDecimal standardSecond;

    /**
     * 金额
     */
    private BigDecimal price;

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
     * tb_md_cost_approach_taxes
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
     * 类型
     * @return type_name 类型
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 类型
     * @param typeName 类型
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * 类型key
     * @return type_key 类型key
     */
    public String getTypeKey() {
        return typeKey;
    }

    /**
     * 类型key
     * @param typeKey 类型key
     */
    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey == null ? null : typeKey.trim();
    }

    /**
     * 标准1
     * @return standard_first 标准1
     */
    public BigDecimal getStandardFirst() {
        return standardFirst;
    }

    /**
     * 标准1
     * @param standardFirst 标准1
     */
    public void setStandardFirst(BigDecimal standardFirst) {
        this.standardFirst = standardFirst;
    }

    /**
     * 标准2
     * @return standard_second 标准2
     */
    public BigDecimal getStandardSecond() {
        return standardSecond;
    }

    /**
     * 标准2
     * @param standardSecond 标准2
     */
    public void setStandardSecond(BigDecimal standardSecond) {
        this.standardSecond = standardSecond;
    }

    /**
     * 金额
     * @return price 金额
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 金额
     * @param price 金额
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public static MdCostApproachTaxes.Builder builder() {
        return new MdCostApproachTaxes.Builder();
    }

    /**
     * tb_md_cost_approach_taxes
     */
    public static class Builder {
        /**
         * tb_md_cost_approach_taxes
         */
        private MdCostApproachTaxes obj;

        public Builder() {
            this.obj = new MdCostApproachTaxes();
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
         * 类型
         * @param typeName 类型
         */
        public Builder typeName(String typeName) {
            obj.setTypeName(typeName);
            return this;
        }

        /**
         * 类型key
         * @param typeKey 类型key
         */
        public Builder typeKey(String typeKey) {
            obj.setTypeKey(typeKey);
            return this;
        }

        /**
         * 标准1
         * @param standardFirst 标准1
         */
        public Builder standardFirst(BigDecimal standardFirst) {
            obj.setStandardFirst(standardFirst);
            return this;
        }

        /**
         * 标准2
         * @param standardSecond 标准2
         */
        public Builder standardSecond(BigDecimal standardSecond) {
            obj.setStandardSecond(standardSecond);
            return this;
        }

        /**
         * 金额
         * @param price 金额
         */
        public Builder price(BigDecimal price) {
            obj.setPrice(price);
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

        public MdCostApproachTaxes build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        masterId("master_id", "masterId", "INTEGER", false),
        typeName("type_name", "typeName", "VARCHAR", false),
        typeKey("type_key", "typeKey", "VARCHAR", false),
        standardFirst("standard_first", "standardFirst", "DECIMAL", false),
        standardSecond("standard_second", "standardSecond", "DECIMAL", false),
        price("price", "price", "DECIMAL", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        remark("remark", "remark", "VARCHAR", false);

        /**
         * tb_md_cost_approach_taxes
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_md_cost_approach_taxes
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_md_cost_approach_taxes
         */
        private final String column;

        /**
         * tb_md_cost_approach_taxes
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_md_cost_approach_taxes
         */
        private final String javaProperty;

        /**
         * tb_md_cost_approach_taxes
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