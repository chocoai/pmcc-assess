package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MdDevelopmentInfrastructureChildren implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 名称 
     */
    private String name;

    /**
     * 金额 
     */
    private BigDecimal number;

    /**
     * 税费 
     */
    private BigDecimal tax;

    /**
     * 计划详情id
     */
    private Integer planDetailsId;

    /**
     * 类别
     */
    private String type;

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
     * 关联主键
     */
    private Integer pid;

    /**
     * tb_md_development_infrastructure_children
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
     * 金额 
     * @return number 金额 
     */
    public BigDecimal getNumber() {
        return number;
    }

    /**
     * 金额 
     * @param number 金额 
     */
    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    /**
     * 税费 
     * @return tax 税费 
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * 税费 
     * @param tax 税费 
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * 计划详情id
     * @return plan_details_id 计划详情id
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 计划详情id
     * @param planDetailsId 计划详情id
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * 类别
     * @return type 类别
     */
    public String getType() {
        return type;
    }

    /**
     * 类别
     * @param type 类别
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 关联主键
     * @return pid 关联主键
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 关联主键
     * @param pid 关联主键
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public static MdDevelopmentInfrastructureChildren.Builder builder() {
        return new MdDevelopmentInfrastructureChildren.Builder();
    }

    /**
     * tb_md_development_infrastructure_children
     */
    public static class Builder {
        /**
         * tb_md_development_infrastructure_children
         */
        private MdDevelopmentInfrastructureChildren obj;

        public Builder() {
            this.obj = new MdDevelopmentInfrastructureChildren();
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
         * 名称 
         * @param name 名称 
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 金额 
         * @param number 金额 
         */
        public Builder number(BigDecimal number) {
            obj.setNumber(number);
            return this;
        }

        /**
         * 税费 
         * @param tax 税费 
         */
        public Builder tax(BigDecimal tax) {
            obj.setTax(tax);
            return this;
        }

        /**
         * 计划详情id
         * @param planDetailsId 计划详情id
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * 类别
         * @param type 类别
         */
        public Builder type(String type) {
            obj.setType(type);
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
         * 关联主键
         * @param pid 关联主键
         */
        public Builder pid(Integer pid) {
            obj.setPid(pid);
            return this;
        }

        public MdDevelopmentInfrastructureChildren build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        name("name", "name", "VARCHAR", false),
        number("number", "number", "DECIMAL", false),
        tax("tax", "tax", "DECIMAL", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        type("type", "type", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        pid("pid", "pid", "INTEGER", false);

        /**
         * tb_md_development_infrastructure_children
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_md_development_infrastructure_children
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_md_development_infrastructure_children
         */
        private final String column;

        /**
         * tb_md_development_infrastructure_children
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_md_development_infrastructure_children
         */
        private final String javaProperty;

        /**
         * tb_md_development_infrastructure_children
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