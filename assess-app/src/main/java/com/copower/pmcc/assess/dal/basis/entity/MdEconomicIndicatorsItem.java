package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MdEconomicIndicatorsItem implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer pid;

    /**
     * 主表id
     */
    private Integer economicId;

    /**
     * 项目计划详情id
     */
    private Integer planDetailsId;

    /**
     * key值
     */
    private String dataKey;

    /**
     * 名称
     */
    private String name;

    /**
     * 规划建筑面积 
     */
    private BigDecimal plannedBuildingArea;

    /**
     * 可售面积 
     */
    private BigDecimal saleableArea;

    /**
     * 评估面积
     */
    private BigDecimal assessArea;

    /**
     * 个数 
     */
    private Integer number;

    /**
     * 比较法id
     */
    private Integer mcId;

    /**
     * 单位售价
     */
    private BigDecimal unitPrice;

    /**
     * 说明 
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
     * tb_md_economic_indicators_item
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
     * 
     * @return pid 
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 
     * @param pid 
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 主表id
     * @return economic_id 主表id
     */
    public Integer getEconomicId() {
        return economicId;
    }

    /**
     * 主表id
     * @param economicId 主表id
     */
    public void setEconomicId(Integer economicId) {
        this.economicId = economicId;
    }

    /**
     * 项目计划详情id
     * @return plan_details_id 项目计划详情id
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 项目计划详情id
     * @param planDetailsId 项目计划详情id
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * key值
     * @return data_key key值
     */
    public String getDataKey() {
        return dataKey;
    }

    /**
     * key值
     * @param dataKey key值
     */
    public void setDataKey(String dataKey) {
        this.dataKey = dataKey == null ? null : dataKey.trim();
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
     * 规划建筑面积 
     * @return planned_building_area 规划建筑面积 
     */
    public BigDecimal getPlannedBuildingArea() {
        return plannedBuildingArea;
    }

    /**
     * 规划建筑面积 
     * @param plannedBuildingArea 规划建筑面积 
     */
    public void setPlannedBuildingArea(BigDecimal plannedBuildingArea) {
        this.plannedBuildingArea = plannedBuildingArea;
    }

    /**
     * 可售面积 
     * @return saleable_area 可售面积 
     */
    public BigDecimal getSaleableArea() {
        return saleableArea;
    }

    /**
     * 可售面积 
     * @param saleableArea 可售面积 
     */
    public void setSaleableArea(BigDecimal saleableArea) {
        this.saleableArea = saleableArea;
    }

    /**
     * 评估面积
     * @return assess_area 评估面积
     */
    public BigDecimal getAssessArea() {
        return assessArea;
    }

    /**
     * 评估面积
     * @param assessArea 评估面积
     */
    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    /**
     * 个数 
     * @return number 个数 
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 个数 
     * @param number 个数 
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 比较法id
     * @return mc_id 比较法id
     */
    public Integer getMcId() {
        return mcId;
    }

    /**
     * 比较法id
     * @param mcId 比较法id
     */
    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    /**
     * 单位售价
     * @return unit_price 单位售价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单位售价
     * @param unitPrice 单位售价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public static MdEconomicIndicatorsItem.Builder builder() {
        return new MdEconomicIndicatorsItem.Builder();
    }

    /**
     * tb_md_economic_indicators_item
     */
    public static class Builder {
        /**
         * tb_md_economic_indicators_item
         */
        private MdEconomicIndicatorsItem obj;

        public Builder() {
            this.obj = new MdEconomicIndicatorsItem();
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
         * 
         * @param pid 
         */
        public Builder pid(Integer pid) {
            obj.setPid(pid);
            return this;
        }

        /**
         * 主表id
         * @param economicId 主表id
         */
        public Builder economicId(Integer economicId) {
            obj.setEconomicId(economicId);
            return this;
        }

        /**
         * 项目计划详情id
         * @param planDetailsId 项目计划详情id
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * key值
         * @param dataKey key值
         */
        public Builder dataKey(String dataKey) {
            obj.setDataKey(dataKey);
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
         * 规划建筑面积 
         * @param plannedBuildingArea 规划建筑面积 
         */
        public Builder plannedBuildingArea(BigDecimal plannedBuildingArea) {
            obj.setPlannedBuildingArea(plannedBuildingArea);
            return this;
        }

        /**
         * 可售面积 
         * @param saleableArea 可售面积 
         */
        public Builder saleableArea(BigDecimal saleableArea) {
            obj.setSaleableArea(saleableArea);
            return this;
        }

        /**
         * 评估面积
         * @param assessArea 评估面积
         */
        public Builder assessArea(BigDecimal assessArea) {
            obj.setAssessArea(assessArea);
            return this;
        }

        /**
         * 个数 
         * @param number 个数 
         */
        public Builder number(Integer number) {
            obj.setNumber(number);
            return this;
        }

        /**
         * 比较法id
         * @param mcId 比较法id
         */
        public Builder mcId(Integer mcId) {
            obj.setMcId(mcId);
            return this;
        }

        /**
         * 单位售价
         * @param unitPrice 单位售价
         */
        public Builder unitPrice(BigDecimal unitPrice) {
            obj.setUnitPrice(unitPrice);
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

        public MdEconomicIndicatorsItem build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        pid("pid", "pid", "INTEGER", false),
        economicId("economic_id", "economicId", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        dataKey("data_key", "dataKey", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        plannedBuildingArea("planned_building_area", "plannedBuildingArea", "DECIMAL", false),
        saleableArea("saleable_area", "saleableArea", "DECIMAL", false),
        assessArea("assess_area", "assessArea", "DECIMAL", false),
        number("number", "number", "INTEGER", false),
        mcId("mc_id", "mcId", "INTEGER", false),
        unitPrice("unit_price", "unitPrice", "DECIMAL", false),
        remark("remark", "remark", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_md_economic_indicators_item
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_md_economic_indicators_item
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_md_economic_indicators_item
         */
        private final String column;

        /**
         * tb_md_economic_indicators_item
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_md_economic_indicators_item
         */
        private final String javaProperty;

        /**
         * tb_md_economic_indicators_item
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