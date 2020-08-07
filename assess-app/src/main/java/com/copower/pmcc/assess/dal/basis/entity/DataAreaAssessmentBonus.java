package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DataAreaAssessmentBonus implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 部门名称(冗余字段)
     */
    private String departmentName;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 最高得分
     */
    private BigDecimal maxScore;

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
     * tb_data_area_assessment_bonus
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
     * 部门名称(冗余字段)
     * @return department_name 部门名称(冗余字段)
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 部门名称(冗余字段)
     * @param departmentName 部门名称(冗余字段)
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    /**
     * 部门id
     * @return department_id 部门id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 部门id
     * @param departmentId 部门id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 最高得分
     * @return max_score 最高得分
     */
    public BigDecimal getMaxScore() {
        return maxScore;
    }

    /**
     * 最高得分
     * @param maxScore 最高得分
     */
    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
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

    public static DataAreaAssessmentBonus.Builder builder() {
        return new DataAreaAssessmentBonus.Builder();
    }

    /**
     * tb_data_area_assessment_bonus
     */
    public static class Builder {
        /**
         * tb_data_area_assessment_bonus
         */
        private DataAreaAssessmentBonus obj;

        public Builder() {
            this.obj = new DataAreaAssessmentBonus();
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
         * 区县
         * @param district 区县
         */
        public Builder district(String district) {
            obj.setDistrict(district);
            return this;
        }

        /**
         * 部门名称(冗余字段)
         * @param departmentName 部门名称(冗余字段)
         */
        public Builder departmentName(String departmentName) {
            obj.setDepartmentName(departmentName);
            return this;
        }

        /**
         * 部门id
         * @param departmentId 部门id
         */
        public Builder departmentId(Integer departmentId) {
            obj.setDepartmentId(departmentId);
            return this;
        }

        /**
         * 最高得分
         * @param maxScore 最高得分
         */
        public Builder maxScore(BigDecimal maxScore) {
            obj.setMaxScore(maxScore);
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

        public DataAreaAssessmentBonus build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        departmentName("department_name", "departmentName", "VARCHAR", false),
        departmentId("department_id", "departmentId", "INTEGER", false),
        maxScore("max_score", "maxScore", "DECIMAL", false),
        coefficient("coefficient", "coefficient", "DECIMAL", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_data_area_assessment_bonus
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_data_area_assessment_bonus
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_data_area_assessment_bonus
         */
        private final String column;

        /**
         * tb_data_area_assessment_bonus
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_data_area_assessment_bonus
         */
        private final String javaProperty;

        /**
         * tb_data_area_assessment_bonus
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