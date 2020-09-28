package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicEstateLandCategoryInfo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer landId;

    /**
     * 
     */
    private Integer houseId;

    /**
     * 土地用途类型
     */
    private String landUseType;

    /**
     * 土地用途类别
     */
    private String landUseCategory;

    /**
     * 取得时间
     */
    private Date acquisitionTime;

    /**
     * 土地使用年限
     */
    private BigDecimal landUseYear;

    /**
     * 土地因素合计分值
     */
    private BigDecimal landFactorTotalScore;

    /**
     * 土地级别
     */
    private Integer landLevel;

    /**
     * 土地级别说明
     */
    private String landLevelRemark;

    /**
     * 土地级别名称
     */
    private String landLevelName;

    /**
     * 
     */
    private Boolean bisDelete;

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
     * 土地形状
     */
    private String landShape;

    /**
     * 开发时间
     */
    private Date developTime;

    /**
     * 容积率
     */
    private BigDecimal plotRatio;

    /**
     * 建筑密度
     */
    private String buildingDensity;

    /**
     * 绿地率
     */
    private String greeningRate;

    /**
     * 兼容类型
     */
    private String compatibilityType;

    /**
     * 兼容比例
     */
    private String compatibilityRate;

    /**
     * 建筑高度
     */
    private String heightPermitted;

    /**
     * 容积率描述
     */
    private String plotRatioRemark;

    /**
     * 土地终止日期
     */
    private Date terminationData;

    /**
     * 土地因素json串
     */
    private String landLevelContentResult;

    /**
     * tb_basic_estate_land_category_info
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
     * @return land_id 
     */
    public Integer getLandId() {
        return landId;
    }

    /**
     * 
     * @param landId 
     */
    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    /**
     * 
     * @return house_id 
     */
    public Integer getHouseId() {
        return houseId;
    }

    /**
     * 
     * @param houseId 
     */
    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    /**
     * 土地用途类型
     * @return land_use_type 土地用途类型
     */
    public String getLandUseType() {
        return landUseType;
    }

    /**
     * 土地用途类型
     * @param landUseType 土地用途类型
     */
    public void setLandUseType(String landUseType) {
        this.landUseType = landUseType == null ? null : landUseType.trim();
    }

    /**
     * 土地用途类别
     * @return land_use_category 土地用途类别
     */
    public String getLandUseCategory() {
        return landUseCategory;
    }

    /**
     * 土地用途类别
     * @param landUseCategory 土地用途类别
     */
    public void setLandUseCategory(String landUseCategory) {
        this.landUseCategory = landUseCategory == null ? null : landUseCategory.trim();
    }

    /**
     * 取得时间
     * @return acquisition_time 取得时间
     */
    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    /**
     * 取得时间
     * @param acquisitionTime 取得时间
     */
    public void setAcquisitionTime(Date acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    /**
     * 土地使用年限
     * @return land_use_year 土地使用年限
     */
    public BigDecimal getLandUseYear() {
        return landUseYear;
    }

    /**
     * 土地使用年限
     * @param landUseYear 土地使用年限
     */
    public void setLandUseYear(BigDecimal landUseYear) {
        this.landUseYear = landUseYear;
    }

    /**
     * 土地因素合计分值
     * @return land_factor_total_score 土地因素合计分值
     */
    public BigDecimal getLandFactorTotalScore() {
        return landFactorTotalScore;
    }

    /**
     * 土地因素合计分值
     * @param landFactorTotalScore 土地因素合计分值
     */
    public void setLandFactorTotalScore(BigDecimal landFactorTotalScore) {
        this.landFactorTotalScore = landFactorTotalScore;
    }

    /**
     * 土地级别
     * @return land_level 土地级别
     */
    public Integer getLandLevel() {
        return landLevel;
    }

    /**
     * 土地级别
     * @param landLevel 土地级别
     */
    public void setLandLevel(Integer landLevel) {
        this.landLevel = landLevel;
    }

    /**
     * 土地级别说明
     * @return land_level_remark 土地级别说明
     */
    public String getLandLevelRemark() {
        return landLevelRemark;
    }

    /**
     * 土地级别说明
     * @param landLevelRemark 土地级别说明
     */
    public void setLandLevelRemark(String landLevelRemark) {
        this.landLevelRemark = landLevelRemark == null ? null : landLevelRemark.trim();
    }

    /**
     * 土地级别名称
     * @return land_level_name 土地级别名称
     */
    public String getLandLevelName() {
        return landLevelName;
    }

    /**
     * 土地级别名称
     * @param landLevelName 土地级别名称
     */
    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName == null ? null : landLevelName.trim();
    }

    /**
     * 
     * @return bis_delete 
     */
    public Boolean getBisDelete() {
        return bisDelete;
    }

    /**
     * 
     * @param bisDelete 
     */
    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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
     * 土地形状
     * @return land_shape 土地形状
     */
    public String getLandShape() {
        return landShape;
    }

    /**
     * 土地形状
     * @param landShape 土地形状
     */
    public void setLandShape(String landShape) {
        this.landShape = landShape == null ? null : landShape.trim();
    }

    /**
     * 开发时间
     * @return develop_time 开发时间
     */
    public Date getDevelopTime() {
        return developTime;
    }

    /**
     * 开发时间
     * @param developTime 开发时间
     */
    public void setDevelopTime(Date developTime) {
        this.developTime = developTime;
    }

    /**
     * 容积率
     * @return plot_ratio 容积率
     */
    public BigDecimal getPlotRatio() {
        return plotRatio;
    }

    /**
     * 容积率
     * @param plotRatio 容积率
     */
    public void setPlotRatio(BigDecimal plotRatio) {
        this.plotRatio = plotRatio;
    }

    /**
     * 建筑密度
     * @return building_density 建筑密度
     */
    public String getBuildingDensity() {
        return buildingDensity;
    }

    /**
     * 建筑密度
     * @param buildingDensity 建筑密度
     */
    public void setBuildingDensity(String buildingDensity) {
        this.buildingDensity = buildingDensity == null ? null : buildingDensity.trim();
    }

    /**
     * 绿地率
     * @return greening_rate 绿地率
     */
    public String getGreeningRate() {
        return greeningRate;
    }

    /**
     * 绿地率
     * @param greeningRate 绿地率
     */
    public void setGreeningRate(String greeningRate) {
        this.greeningRate = greeningRate == null ? null : greeningRate.trim();
    }

    /**
     * 兼容类型
     * @return compatibility_type 兼容类型
     */
    public String getCompatibilityType() {
        return compatibilityType;
    }

    /**
     * 兼容类型
     * @param compatibilityType 兼容类型
     */
    public void setCompatibilityType(String compatibilityType) {
        this.compatibilityType = compatibilityType == null ? null : compatibilityType.trim();
    }

    /**
     * 兼容比例
     * @return compatibility_rate 兼容比例
     */
    public String getCompatibilityRate() {
        return compatibilityRate;
    }

    /**
     * 兼容比例
     * @param compatibilityRate 兼容比例
     */
    public void setCompatibilityRate(String compatibilityRate) {
        this.compatibilityRate = compatibilityRate == null ? null : compatibilityRate.trim();
    }

    /**
     * 建筑高度
     * @return height_permitted 建筑高度
     */
    public String getHeightPermitted() {
        return heightPermitted;
    }

    /**
     * 建筑高度
     * @param heightPermitted 建筑高度
     */
    public void setHeightPermitted(String heightPermitted) {
        this.heightPermitted = heightPermitted == null ? null : heightPermitted.trim();
    }

    /**
     * 容积率描述
     * @return plot_ratio_remark 容积率描述
     */
    public String getPlotRatioRemark() {
        return plotRatioRemark;
    }

    /**
     * 容积率描述
     * @param plotRatioRemark 容积率描述
     */
    public void setPlotRatioRemark(String plotRatioRemark) {
        this.plotRatioRemark = plotRatioRemark == null ? null : plotRatioRemark.trim();
    }

    /**
     * 土地终止日期
     * @return termination_data 土地终止日期
     */
    public Date getTerminationData() {
        return terminationData;
    }

    /**
     * 土地终止日期
     * @param terminationData 土地终止日期
     */
    public void setTerminationData(Date terminationData) {
        this.terminationData = terminationData;
    }

    /**
     * 土地因素json串
     * @return land_level_content_result 土地因素json串
     */
    public String getLandLevelContentResult() {
        return landLevelContentResult;
    }

    /**
     * 土地因素json串
     * @param landLevelContentResult 土地因素json串
     */
    public void setLandLevelContentResult(String landLevelContentResult) {
        this.landLevelContentResult = landLevelContentResult == null ? null : landLevelContentResult.trim();
    }

    public static BasicEstateLandCategoryInfo.Builder builder() {
        return new BasicEstateLandCategoryInfo.Builder();
    }

    /**
     * tb_basic_estate_land_category_info
     */
    public static class Builder {
        /**
         * tb_basic_estate_land_category_info
         */
        private BasicEstateLandCategoryInfo obj;

        public Builder() {
            this.obj = new BasicEstateLandCategoryInfo();
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
         * @param landId 
         */
        public Builder landId(Integer landId) {
            obj.setLandId(landId);
            return this;
        }

        /**
         * 
         * @param houseId 
         */
        public Builder houseId(Integer houseId) {
            obj.setHouseId(houseId);
            return this;
        }

        /**
         * 土地用途类型
         * @param landUseType 土地用途类型
         */
        public Builder landUseType(String landUseType) {
            obj.setLandUseType(landUseType);
            return this;
        }

        /**
         * 土地用途类别
         * @param landUseCategory 土地用途类别
         */
        public Builder landUseCategory(String landUseCategory) {
            obj.setLandUseCategory(landUseCategory);
            return this;
        }

        /**
         * 取得时间
         * @param acquisitionTime 取得时间
         */
        public Builder acquisitionTime(Date acquisitionTime) {
            obj.setAcquisitionTime(acquisitionTime);
            return this;
        }

        /**
         * 土地使用年限
         * @param landUseYear 土地使用年限
         */
        public Builder landUseYear(BigDecimal landUseYear) {
            obj.setLandUseYear(landUseYear);
            return this;
        }

        /**
         * 土地因素合计分值
         * @param landFactorTotalScore 土地因素合计分值
         */
        public Builder landFactorTotalScore(BigDecimal landFactorTotalScore) {
            obj.setLandFactorTotalScore(landFactorTotalScore);
            return this;
        }

        /**
         * 土地级别
         * @param landLevel 土地级别
         */
        public Builder landLevel(Integer landLevel) {
            obj.setLandLevel(landLevel);
            return this;
        }

        /**
         * 土地因素json串
         * @param landLevelContentResult 土地因素json串
         */
        public Builder landLevelContentResult(String landLevelContentResult) {
            obj.setLandLevelContentResult(landLevelContentResult);
            return this;
        }

        /**
         * 土地级别名称
         * @param landLevelName 土地级别名称
         */
        public Builder landLevelName(String landLevelName) {
            obj.setLandLevelName(landLevelName);
            return this;
        }

        /**
         * 土地级别说明
         * @param landLevelRemark 土地级别说明
         */
        public Builder landLevelRemark(String landLevelRemark) {
            obj.setLandLevelRemark(landLevelRemark);
            return this;
        }

        /**
         * 
         * @param bisDelete 
         */
        public Builder bisDelete(Boolean bisDelete) {
            obj.setBisDelete(bisDelete);
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
         * 土地形状
         * @param landShape 土地形状
         */
        public Builder landShape(String landShape) {
            obj.setLandShape(landShape);
            return this;
        }

        /**
         * 开发时间
         * @param developTime 开发时间
         */
        public Builder developTime(Date developTime) {
            obj.setDevelopTime(developTime);
            return this;
        }

        /**
         * 容积率
         * @param plotRatio 容积率
         */
        public Builder plotRatio(BigDecimal plotRatio) {
            obj.setPlotRatio(plotRatio);
            return this;
        }

        /**
         * 容积率描述
         * @param plotRatioRemark 容积率描述
         */
        public Builder plotRatioRemark(String plotRatioRemark) {
            obj.setPlotRatioRemark(plotRatioRemark);
            return this;
        }

        public BasicEstateLandCategoryInfo build() {
            return this.obj;
        }

        /**
         * 建筑密度
         * @param buildingDensity 建筑密度
         */
        public Builder buildingDensity(String buildingDensity) {
            obj.setBuildingDensity(buildingDensity);
            return this;
        }

        /**
         * 绿地率
         * @param greeningRate 绿地率
         */
        public Builder greeningRate(String greeningRate) {
            obj.setGreeningRate(greeningRate);
            return this;
        }

        /**
         * 兼容类型
         * @param compatibilityType 兼容类型
         */
        public Builder compatibilityType(String compatibilityType) {
            obj.setCompatibilityType(compatibilityType);
            return this;
        }

        /**
         * 兼容比例
         * @param compatibilityRate 兼容比例
         */
        public Builder compatibilityRate(String compatibilityRate) {
            obj.setCompatibilityRate(compatibilityRate);
            return this;
        }

        /**
         * 建筑高度
         * @param heightPermitted 建筑高度
         */
        public Builder heightPermitted(String heightPermitted) {
            obj.setHeightPermitted(heightPermitted);
            return this;
        }

        /**
         * 土地终止日期
         * @param terminationData 土地终止日期
         */
        public Builder terminationData(Date terminationData) {
            obj.setTerminationData(terminationData);
            return this;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        landId("land_id", "landId", "INTEGER", false),
        houseId("house_id", "houseId", "INTEGER", false),
        landUseType("land_use_type", "landUseType", "VARCHAR", false),
        landUseCategory("land_use_category", "landUseCategory", "VARCHAR", false),
        acquisitionTime("acquisition_time", "acquisitionTime", "TIMESTAMP", false),
        landUseYear("land_use_year", "landUseYear", "DECIMAL", false),
        landFactorTotalScore("land_factor_total_score", "landFactorTotalScore", "DECIMAL", false),
        landLevel("land_level", "landLevel", "INTEGER", false),
        landLevelRemark("land_level_remark", "landLevelRemark", "VARCHAR", false),
        landLevelName("land_level_name", "landLevelName", "VARCHAR", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        landShape("land_shape", "landShape", "VARCHAR", false),
        developTime("develop_time", "developTime", "TIMESTAMP", false),
        plotRatio("plot_ratio", "plotRatio", "DECIMAL", false),
        buildingDensity("building_density", "buildingDensity", "VARCHAR", false),
        greeningRate("greening_rate", "greeningRate", "VARCHAR", false),
        compatibilityType("compatibility_type", "compatibilityType", "VARCHAR", false),
        compatibilityRate("compatibility_rate", "compatibilityRate", "VARCHAR", false),
        heightPermitted("height_permitted", "heightPermitted", "VARCHAR", false),
        plotRatioRemark("plot_ratio_remark", "plotRatioRemark", "VARCHAR", false),
        terminationData("termination_data", "terminationData", "TIMESTAMP", false),
        landLevelContentResult("land_level_content_result", "landLevelContentResult", "LONGVARCHAR", false);

        /**
         * tb_basic_estate_land_category_info
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_estate_land_category_info
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_estate_land_category_info
         */
        private final String column;

        /**
         * tb_basic_estate_land_category_info
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_estate_land_category_info
         */
        private final String javaProperty;

        /**
         * tb_basic_estate_land_category_info
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