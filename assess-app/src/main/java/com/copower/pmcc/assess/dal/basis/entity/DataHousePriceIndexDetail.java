package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DataHousePriceIndexDetail implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 主表id
     */
    private Integer housePriceId;

    /**
     * 指数
     */
    private BigDecimal indexNumber;

    /**
     * 开始月份
     */
    private Date startDate;

    /**
     * 结束月份
     */
    private Date endDate;

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
     * 单位地价
     */
    private BigDecimal unitPremium;

    /**
     * 楼面地价
     */
    private BigDecimal floorPremium;

    /**
     * tb_data_house_price_index_detail
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
     * @return house_price_id 主表id
     */
    public Integer getHousePriceId() {
        return housePriceId;
    }

    /**
     * 主表id
     * @param housePriceId 主表id
     */
    public void setHousePriceId(Integer housePriceId) {
        this.housePriceId = housePriceId;
    }

    /**
     * 指数
     * @return index_number 指数
     */
    public BigDecimal getIndexNumber() {
        return indexNumber;
    }

    /**
     * 指数
     * @param indexNumber 指数
     */
    public void setIndexNumber(BigDecimal indexNumber) {
        this.indexNumber = indexNumber;
    }

    /**
     * 开始月份
     * @return start_date 开始月份
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 开始月份
     * @param startDate 开始月份
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 结束月份
     * @return end_date 结束月份
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 结束月份
     * @param endDate 结束月份
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * 单位地价
     * @return unit_premium 单位地价
     */
    public BigDecimal getUnitPremium() {
        return unitPremium;
    }

    /**
     * 单位地价
     * @param unitPremium 单位地价
     */
    public void setUnitPremium(BigDecimal unitPremium) {
        this.unitPremium = unitPremium;
    }

    /**
     * 楼面地价
     * @return floor_premium 楼面地价
     */
    public BigDecimal getFloorPremium() {
        return floorPremium;
    }

    /**
     * 楼面地价
     * @param floorPremium 楼面地价
     */
    public void setFloorPremium(BigDecimal floorPremium) {
        this.floorPremium = floorPremium;
    }

    public static DataHousePriceIndexDetail.Builder builder() {
        return new DataHousePriceIndexDetail.Builder();
    }

    /**
     * tb_data_house_price_index_detail
     */
    public static class Builder {
        /**
         * tb_data_house_price_index_detail
         */
        private DataHousePriceIndexDetail obj;

        public Builder() {
            this.obj = new DataHousePriceIndexDetail();
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
         * @param housePriceId 主表id
         */
        public Builder housePriceId(Integer housePriceId) {
            obj.setHousePriceId(housePriceId);
            return this;
        }

        /**
         * 指数
         * @param indexNumber 指数
         */
        public Builder indexNumber(BigDecimal indexNumber) {
            obj.setIndexNumber(indexNumber);
            return this;
        }

        /**
         * 开始月份
         * @param startDate 开始月份
         */
        public Builder startDate(Date startDate) {
            obj.setStartDate(startDate);
            return this;
        }

        /**
         * 结束月份
         * @param endDate 结束月份
         */
        public Builder endDate(Date endDate) {
            obj.setEndDate(endDate);
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
         * 单位地价
         * @param unitPremium 单位地价
         */
        public Builder unitPremium(BigDecimal unitPremium) {
            obj.setUnitPremium(unitPremium);
            return this;
        }

        /**
         * 楼面地价
         * @param floorPremium 楼面地价
         */
        public Builder floorPremium(BigDecimal floorPremium) {
            obj.setFloorPremium(floorPremium);
            return this;
        }

        public DataHousePriceIndexDetail build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        housePriceId("house_price_id", "housePriceId", "INTEGER", false),
        indexNumber("index_number", "indexNumber", "DECIMAL", false),
        startDate("start_date", "startDate", "TIMESTAMP", false),
        endDate("end_date", "endDate", "TIMESTAMP", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        unitPremium("unit_premium", "unitPremium", "DECIMAL", false),
        floorPremium("floor_premium", "floorPremium", "DECIMAL", false);

        /**
         * tb_data_house_price_index_detail
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_data_house_price_index_detail
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_data_house_price_index_detail
         */
        private final String column;

        /**
         * tb_data_house_price_index_detail
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_data_house_price_index_detail
         */
        private final String javaProperty;

        /**
         * tb_data_house_price_index_detail
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