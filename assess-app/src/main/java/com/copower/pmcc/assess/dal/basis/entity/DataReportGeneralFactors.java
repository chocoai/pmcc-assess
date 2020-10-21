package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DataReportGeneralFactors implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 上级编号
     */
    private Integer pid;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 是否启用
     */
    private Boolean bisEnable;

    /**
     * 区县
     */
    private String district;

    /**
     * 类型
     */
    private String type;

    /**
     * 年份
     */
    private Integer relyear;

    /**
     * 名称
     */
    private String name;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 排序
     */
    private Integer sorting;

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
     * 模板
     */
    private String template;

    /**
     * tb_data_report_general_factors
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
     * 上级编号
     * @return pid 上级编号
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 上级编号
     * @param pid 上级编号
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 省
     * @return province 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 省
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 市
     * @return city 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 市
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 是否启用
     * @return bis_enable 是否启用
     */
    public Boolean getBisEnable() {
        return bisEnable;
    }

    /**
     * 是否启用
     * @param bisEnable 是否启用
     */
    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    /**
     * 区县
     * @return district 区县
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 区县
     * @param district 区县
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
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
     * 年份
     * @return relYear 年份
     */
    public Integer getRelyear() {
        return relyear;
    }

    /**
     * 年份
     * @param relyear 年份
     */
    public void setRelyear(Integer relyear) {
        this.relyear = relyear;
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
     * 字段名称
     * @return field_name 字段名称
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 字段名称
     * @param fieldName 字段名称
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**
     * 排序
     * @return sorting 排序
     */
    public Integer getSorting() {
        return sorting;
    }

    /**
     * 排序
     * @param sorting 排序
     */
    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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
     * 模板
     * @return template 模板
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 模板
     * @param template 模板
     */
    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public static DataReportGeneralFactors.Builder builder() {
        return new DataReportGeneralFactors.Builder();
    }

    /**
     * tb_data_report_general_factors
     */
    public static class Builder {
        /**
         * tb_data_report_general_factors
         */
        private DataReportGeneralFactors obj;

        public Builder() {
            this.obj = new DataReportGeneralFactors();
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
         * 上级编号
         * @param pid 上级编号
         */
        public Builder pid(Integer pid) {
            obj.setPid(pid);
            return this;
        }

        /**
         * 省
         * @param province 省
         */
        public Builder province(String province) {
            obj.setProvince(province);
            return this;
        }

        /**
         * 市
         * @param city 市
         */
        public Builder city(String city) {
            obj.setCity(city);
            return this;
        }

        /**
         * 是否启用
         * @param bisEnable 是否启用
         */
        public Builder bisEnable(Boolean bisEnable) {
            obj.setBisEnable(bisEnable);
            return this;
        }

        /**
         * 区县
         * @param district 区县
         */
        public Builder district(String district) {
            obj.setDistrict(district);
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
         * 年份
         * @param relyear 年份
         */
        public Builder relyear(Integer relyear) {
            obj.setRelyear(relyear);
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
         * 字段名称
         * @param fieldName 字段名称
         */
        public Builder fieldName(String fieldName) {
            obj.setFieldName(fieldName);
            return this;
        }

        /**
         * 排序
         * @param sorting 排序
         */
        public Builder sorting(Integer sorting) {
            obj.setSorting(sorting);
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
         * 模板
         * @param template 模板
         */
        public Builder template(String template) {
            obj.setTemplate(template);
            return this;
        }

        public DataReportGeneralFactors build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        pid("pid", "pid", "INTEGER", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        bisEnable("bis_enable", "bisEnable", "BIT", false),
        district("district", "district", "VARCHAR", false),
        type("type", "type", "VARCHAR", false),
        relyear("relYear", "relyear", "INTEGER", false),
        name("name", "name", "VARCHAR", false),
        fieldName("field_name", "fieldName", "VARCHAR", false),
        sorting("sorting", "sorting", "INTEGER", false),
        remark("remark", "remark", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        template("template", "template", "LONGVARCHAR", false);

        /**
         * tb_data_report_general_factors
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_data_report_general_factors
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_data_report_general_factors
         */
        private final String column;

        /**
         * tb_data_report_general_factors
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_data_report_general_factors
         */
        private final String javaProperty;

        /**
         * tb_data_report_general_factors
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