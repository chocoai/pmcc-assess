package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicEstateLandState implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer applyId;

    /**
     * 
     */
    private Integer estateId;

    /**
     * 土地名称
     */
    private String name;

    /**
     * 土地用途类型
     */
    private String landUseType;

    /**
     * 土地用途类别
     */
    private String landUseCategory;

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
     * 土地面积单位
     */
    private String landAreaUnit;

    /**
     * 土地面积
     */
    private String landArea;

    /**
     * 
     */
    private String eastToName;

    /**
     * 东至
     */
    private String eastTo;

    /**
     * 
     */
    private String southToName;

    /**
     * 南至
     */
    private String southTo;

    /**
     * 
     */
    private String westToName;

    /**
     * 西至
     */
    private String westTo;

    /**
     * 
     */
    private String northToName;

    /**
     * 北至
     */
    private String northTo;

    /**
     * 土地形状状况
     */
    private Integer shapeState;

    /**
     * 土地形状备注
     */
    private String shapeStateRemark;

    /**
     * 地形
     */
    private Integer planeness;

    /**
     * 地势
     */
    private Integer topographicTerrain;

    /**
     * 土地开发程度
     */
    private Integer developmentDegree;

    /**
     * 土地开发程度备注
     */
    private String developmentDegreeRemark;

    /**
     * 开发程度熟地内容
     */
    private String developmentDegreeContent;

    /**
     * 取得时间
     */
    private Date acquisitionTime;

    /**
     * 容积率说明
     */
    private String plotRatioRemark;

    /**
     * 容积率
     */
    private String plotRatio;

    /**
     * 建筑密度
     */
    private String buildingDensity;

    /**
     * 绿地率
     */
    private String greenSpaceRate;

    /**
     * 兼容比例类型
     */
    private String compatibleRatioType;

    /**
     * 兼容比例
     */
    private String compatibleRatio;

    /**
     * 承载力
     */
    private String bearingCapacity;

    /**
     * 污染
     */
    private String contaminated;

    /**
     * 酸碱度
     */
    private String ph;

    /**
     * 肥力
     */
    private String fertility;

    /**
     * 土地实体结论
     */
    private String conclusion;

    /**
     * 稳定性
     */
    private String holdOn;

    /**
     * 建筑限高
     */
    private BigDecimal buildingHeightLimit;

    /**
     * 投资强度（万元/亩）
     */
    private BigDecimal investmentIntensity;

    /**
     * 特殊限制
     */
    private String specialRestrictions;

    /**
     * 土地利用现状
     */
    private String presentSituationLandUse;

    /**
     * 基础设施完备度
     */
    private Integer infrastructureCompleteness;

    /**
     * 开发时间
     */
    private Date developmentTime;

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
     * 宗地内现状
     */
    private String currentSituation;

    /**
     * 土地级别json
     */
    private String landLevelContent;

    /**
     * tb_basic_estate_land_state
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
     * @return apply_id 
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * 
     * @param applyId 
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 
     * @return estate_id 
     */
    public Integer getEstateId() {
        return estateId;
    }

    /**
     * 
     * @param estateId 
     */
    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    /**
     * 土地名称
     * @return name 土地名称
     */
    public String getName() {
        return name;
    }

    /**
     * 土地名称
     * @param name 土地名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 土地面积单位
     * @return land_area_unit 土地面积单位
     */
    public String getLandAreaUnit() {
        return landAreaUnit;
    }

    /**
     * 土地面积单位
     * @param landAreaUnit 土地面积单位
     */
    public void setLandAreaUnit(String landAreaUnit) {
        this.landAreaUnit = landAreaUnit == null ? null : landAreaUnit.trim();
    }

    /**
     * 土地面积
     * @return land_area 土地面积
     */
    public String getLandArea() {
        return landArea;
    }

    /**
     * 土地面积
     * @param landArea 土地面积
     */
    public void setLandArea(String landArea) {
        this.landArea = landArea == null ? null : landArea.trim();
    }

    /**
     * 
     * @return east_to_name 
     */
    public String getEastToName() {
        return eastToName;
    }

    /**
     * 
     * @param eastToName 
     */
    public void setEastToName(String eastToName) {
        this.eastToName = eastToName == null ? null : eastToName.trim();
    }

    /**
     * 东至
     * @return east_to 东至
     */
    public String getEastTo() {
        return eastTo;
    }

    /**
     * 东至
     * @param eastTo 东至
     */
    public void setEastTo(String eastTo) {
        this.eastTo = eastTo == null ? null : eastTo.trim();
    }

    /**
     * 
     * @return south_to_name 
     */
    public String getSouthToName() {
        return southToName;
    }

    /**
     * 
     * @param southToName 
     */
    public void setSouthToName(String southToName) {
        this.southToName = southToName == null ? null : southToName.trim();
    }

    /**
     * 南至
     * @return south_to 南至
     */
    public String getSouthTo() {
        return southTo;
    }

    /**
     * 南至
     * @param southTo 南至
     */
    public void setSouthTo(String southTo) {
        this.southTo = southTo == null ? null : southTo.trim();
    }

    /**
     * 
     * @return west_to_name 
     */
    public String getWestToName() {
        return westToName;
    }

    /**
     * 
     * @param westToName 
     */
    public void setWestToName(String westToName) {
        this.westToName = westToName == null ? null : westToName.trim();
    }

    /**
     * 西至
     * @return west_to 西至
     */
    public String getWestTo() {
        return westTo;
    }

    /**
     * 西至
     * @param westTo 西至
     */
    public void setWestTo(String westTo) {
        this.westTo = westTo == null ? null : westTo.trim();
    }

    /**
     * 
     * @return north_to_name 
     */
    public String getNorthToName() {
        return northToName;
    }

    /**
     * 
     * @param northToName 
     */
    public void setNorthToName(String northToName) {
        this.northToName = northToName == null ? null : northToName.trim();
    }

    /**
     * 北至
     * @return north_to 北至
     */
    public String getNorthTo() {
        return northTo;
    }

    /**
     * 北至
     * @param northTo 北至
     */
    public void setNorthTo(String northTo) {
        this.northTo = northTo == null ? null : northTo.trim();
    }

    /**
     * 土地形状状况
     * @return shape_state 土地形状状况
     */
    public Integer getShapeState() {
        return shapeState;
    }

    /**
     * 土地形状状况
     * @param shapeState 土地形状状况
     */
    public void setShapeState(Integer shapeState) {
        this.shapeState = shapeState;
    }

    /**
     * 土地形状备注
     * @return shape_state_remark 土地形状备注
     */
    public String getShapeStateRemark() {
        return shapeStateRemark;
    }

    /**
     * 土地形状备注
     * @param shapeStateRemark 土地形状备注
     */
    public void setShapeStateRemark(String shapeStateRemark) {
        this.shapeStateRemark = shapeStateRemark == null ? null : shapeStateRemark.trim();
    }

    /**
     * 地形
     * @return planeness 地形
     */
    public Integer getPlaneness() {
        return planeness;
    }

    /**
     * 地形
     * @param planeness 地形
     */
    public void setPlaneness(Integer planeness) {
        this.planeness = planeness;
    }

    /**
     * 地势
     * @return topographic_terrain 地势
     */
    public Integer getTopographicTerrain() {
        return topographicTerrain;
    }

    /**
     * 地势
     * @param topographicTerrain 地势
     */
    public void setTopographicTerrain(Integer topographicTerrain) {
        this.topographicTerrain = topographicTerrain;
    }

    /**
     * 土地开发程度
     * @return development_degree 土地开发程度
     */
    public Integer getDevelopmentDegree() {
        return developmentDegree;
    }

    /**
     * 土地开发程度
     * @param developmentDegree 土地开发程度
     */
    public void setDevelopmentDegree(Integer developmentDegree) {
        this.developmentDegree = developmentDegree;
    }

    /**
     * 土地开发程度备注
     * @return development_degree_remark 土地开发程度备注
     */
    public String getDevelopmentDegreeRemark() {
        return developmentDegreeRemark;
    }

    /**
     * 土地开发程度备注
     * @param developmentDegreeRemark 土地开发程度备注
     */
    public void setDevelopmentDegreeRemark(String developmentDegreeRemark) {
        this.developmentDegreeRemark = developmentDegreeRemark == null ? null : developmentDegreeRemark.trim();
    }

    /**
     * 开发程度熟地内容
     * @return development_degree_content 开发程度熟地内容
     */
    public String getDevelopmentDegreeContent() {
        return developmentDegreeContent;
    }

    /**
     * 开发程度熟地内容
     * @param developmentDegreeContent 开发程度熟地内容
     */
    public void setDevelopmentDegreeContent(String developmentDegreeContent) {
        this.developmentDegreeContent = developmentDegreeContent == null ? null : developmentDegreeContent.trim();
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
     * 容积率说明
     * @return plot_ratio_remark 容积率说明
     */
    public String getPlotRatioRemark() {
        return plotRatioRemark;
    }

    /**
     * 容积率说明
     * @param plotRatioRemark 容积率说明
     */
    public void setPlotRatioRemark(String plotRatioRemark) {
        this.plotRatioRemark = plotRatioRemark == null ? null : plotRatioRemark.trim();
    }

    /**
     * 容积率
     * @return plot_ratio 容积率
     */
    public String getPlotRatio() {
        return plotRatio;
    }

    /**
     * 容积率
     * @param plotRatio 容积率
     */
    public void setPlotRatio(String plotRatio) {
        this.plotRatio = plotRatio == null ? null : plotRatio.trim();
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
     * @return green_space_rate 绿地率
     */
    public String getGreenSpaceRate() {
        return greenSpaceRate;
    }

    /**
     * 绿地率
     * @param greenSpaceRate 绿地率
     */
    public void setGreenSpaceRate(String greenSpaceRate) {
        this.greenSpaceRate = greenSpaceRate == null ? null : greenSpaceRate.trim();
    }

    /**
     * 兼容比例类型
     * @return compatible_ratio_type 兼容比例类型
     */
    public String getCompatibleRatioType() {
        return compatibleRatioType;
    }

    /**
     * 兼容比例类型
     * @param compatibleRatioType 兼容比例类型
     */
    public void setCompatibleRatioType(String compatibleRatioType) {
        this.compatibleRatioType = compatibleRatioType == null ? null : compatibleRatioType.trim();
    }

    /**
     * 兼容比例
     * @return compatible_ratio 兼容比例
     */
    public String getCompatibleRatio() {
        return compatibleRatio;
    }

    /**
     * 兼容比例
     * @param compatibleRatio 兼容比例
     */
    public void setCompatibleRatio(String compatibleRatio) {
        this.compatibleRatio = compatibleRatio == null ? null : compatibleRatio.trim();
    }

    /**
     * 承载力
     * @return bearing_capacity 承载力
     */
    public String getBearingCapacity() {
        return bearingCapacity;
    }

    /**
     * 承载力
     * @param bearingCapacity 承载力
     */
    public void setBearingCapacity(String bearingCapacity) {
        this.bearingCapacity = bearingCapacity == null ? null : bearingCapacity.trim();
    }

    /**
     * 污染
     * @return contaminated 污染
     */
    public String getContaminated() {
        return contaminated;
    }

    /**
     * 污染
     * @param contaminated 污染
     */
    public void setContaminated(String contaminated) {
        this.contaminated = contaminated == null ? null : contaminated.trim();
    }

    /**
     * 酸碱度
     * @return ph 酸碱度
     */
    public String getPh() {
        return ph;
    }

    /**
     * 酸碱度
     * @param ph 酸碱度
     */
    public void setPh(String ph) {
        this.ph = ph == null ? null : ph.trim();
    }

    /**
     * 肥力
     * @return fertility 肥力
     */
    public String getFertility() {
        return fertility;
    }

    /**
     * 肥力
     * @param fertility 肥力
     */
    public void setFertility(String fertility) {
        this.fertility = fertility == null ? null : fertility.trim();
    }

    /**
     * 土地实体结论
     * @return conclusion 土地实体结论
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     * 土地实体结论
     * @param conclusion 土地实体结论
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    /**
     * 稳定性
     * @return hold_on 稳定性
     */
    public String getHoldOn() {
        return holdOn;
    }

    /**
     * 稳定性
     * @param holdOn 稳定性
     */
    public void setHoldOn(String holdOn) {
        this.holdOn = holdOn == null ? null : holdOn.trim();
    }

    /**
     * 建筑限高
     * @return building_height_limit 建筑限高
     */
    public BigDecimal getBuildingHeightLimit() {
        return buildingHeightLimit;
    }

    /**
     * 建筑限高
     * @param buildingHeightLimit 建筑限高
     */
    public void setBuildingHeightLimit(BigDecimal buildingHeightLimit) {
        this.buildingHeightLimit = buildingHeightLimit;
    }

    /**
     * 投资强度（万元/亩）
     * @return investment_intensity 投资强度（万元/亩）
     */
    public BigDecimal getInvestmentIntensity() {
        return investmentIntensity;
    }

    /**
     * 投资强度（万元/亩）
     * @param investmentIntensity 投资强度（万元/亩）
     */
    public void setInvestmentIntensity(BigDecimal investmentIntensity) {
        this.investmentIntensity = investmentIntensity;
    }

    /**
     * 特殊限制
     * @return special_restrictions 特殊限制
     */
    public String getSpecialRestrictions() {
        return specialRestrictions;
    }

    /**
     * 特殊限制
     * @param specialRestrictions 特殊限制
     */
    public void setSpecialRestrictions(String specialRestrictions) {
        this.specialRestrictions = specialRestrictions == null ? null : specialRestrictions.trim();
    }

    /**
     * 土地利用现状
     * @return present_situation_land_use 土地利用现状
     */
    public String getPresentSituationLandUse() {
        return presentSituationLandUse;
    }

    /**
     * 土地利用现状
     * @param presentSituationLandUse 土地利用现状
     */
    public void setPresentSituationLandUse(String presentSituationLandUse) {
        this.presentSituationLandUse = presentSituationLandUse == null ? null : presentSituationLandUse.trim();
    }

    /**
     * 基础设施完备度
     * @return infrastructure_completeness 基础设施完备度
     */
    public Integer getInfrastructureCompleteness() {
        return infrastructureCompleteness;
    }

    /**
     * 基础设施完备度
     * @param infrastructureCompleteness 基础设施完备度
     */
    public void setInfrastructureCompleteness(Integer infrastructureCompleteness) {
        this.infrastructureCompleteness = infrastructureCompleteness;
    }

    /**
     * 开发时间
     * @return development_time 开发时间
     */
    public Date getDevelopmentTime() {
        return developmentTime;
    }

    /**
     * 开发时间
     * @param developmentTime 开发时间
     */
    public void setDevelopmentTime(Date developmentTime) {
        this.developmentTime = developmentTime;
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
     * 宗地内现状
     * @return current_situation 宗地内现状
     */
    public String getCurrentSituation() {
        return currentSituation;
    }

    /**
     * 宗地内现状
     * @param currentSituation 宗地内现状
     */
    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation == null ? null : currentSituation.trim();
    }

    /**
     * 土地级别json
     * @return land_level_content 土地级别json
     */
    public String getLandLevelContent() {
        return landLevelContent;
    }

    /**
     * 土地级别json
     * @param landLevelContent 土地级别json
     */
    public void setLandLevelContent(String landLevelContent) {
        this.landLevelContent = landLevelContent == null ? null : landLevelContent.trim();
    }

    public static BasicEstateLandState.Builder builder() {
        return new BasicEstateLandState.Builder();
    }

    /**
     * tb_basic_estate_land_state
     */
    public static class Builder {
        /**
         * tb_basic_estate_land_state
         */
        private BasicEstateLandState obj;

        public Builder() {
            this.obj = new BasicEstateLandState();
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
         * @param applyId 
         */
        public Builder applyId(Integer applyId) {
            obj.setApplyId(applyId);
            return this;
        }

        /**
         * 
         * @param estateId 
         */
        public Builder estateId(Integer estateId) {
            obj.setEstateId(estateId);
            return this;
        }

        /**
         * 土地名称
         * @param name 土地名称
         */
        public Builder name(String name) {
            obj.setName(name);
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
         * 土地级别json
         * @param landLevelContent 土地级别json
         */
        public Builder landLevelContent(String landLevelContent) {
            obj.setLandLevelContent(landLevelContent);
            return this;
        }

        /**
         * 土地面积
         * @param landArea 土地面积
         */
        public Builder landArea(String landArea) {
            obj.setLandArea(landArea);
            return this;
        }

        /**
         * 土地面积单位
         * @param landAreaUnit 土地面积单位
         */
        public Builder landAreaUnit(String landAreaUnit) {
            obj.setLandAreaUnit(landAreaUnit);
            return this;
        }

        /**
         * 东至
         * @param eastTo 东至
         */
        public Builder eastTo(String eastTo) {
            obj.setEastTo(eastTo);
            return this;
        }

        /**
         * 
         * @param eastToName 
         */
        public Builder eastToName(String eastToName) {
            obj.setEastToName(eastToName);
            return this;
        }

        /**
         * 南至
         * @param southTo 南至
         */
        public Builder southTo(String southTo) {
            obj.setSouthTo(southTo);
            return this;
        }

        /**
         * 
         * @param southToName 
         */
        public Builder southToName(String southToName) {
            obj.setSouthToName(southToName);
            return this;
        }

        /**
         * 西至
         * @param westTo 西至
         */
        public Builder westTo(String westTo) {
            obj.setWestTo(westTo);
            return this;
        }

        /**
         * 
         * @param westToName 
         */
        public Builder westToName(String westToName) {
            obj.setWestToName(westToName);
            return this;
        }

        /**
         * 北至
         * @param northTo 北至
         */
        public Builder northTo(String northTo) {
            obj.setNorthTo(northTo);
            return this;
        }

        /**
         * 
         * @param northToName 
         */
        public Builder northToName(String northToName) {
            obj.setNorthToName(northToName);
            return this;
        }

        /**
         * 土地形状状况
         * @param shapeState 土地形状状况
         */
        public Builder shapeState(Integer shapeState) {
            obj.setShapeState(shapeState);
            return this;
        }

        /**
         * 土地形状备注
         * @param shapeStateRemark 土地形状备注
         */
        public Builder shapeStateRemark(String shapeStateRemark) {
            obj.setShapeStateRemark(shapeStateRemark);
            return this;
        }

        /**
         * 地形
         * @param planeness 地形
         */
        public Builder planeness(Integer planeness) {
            obj.setPlaneness(planeness);
            return this;
        }

        /**
         * 地势
         * @param topographicTerrain 地势
         */
        public Builder topographicTerrain(Integer topographicTerrain) {
            obj.setTopographicTerrain(topographicTerrain);
            return this;
        }

        /**
         * 土地开发程度
         * @param developmentDegree 土地开发程度
         */
        public Builder developmentDegree(Integer developmentDegree) {
            obj.setDevelopmentDegree(developmentDegree);
            return this;
        }

        /**
         * 开发程度熟地内容
         * @param developmentDegreeContent 开发程度熟地内容
         */
        public Builder developmentDegreeContent(String developmentDegreeContent) {
            obj.setDevelopmentDegreeContent(developmentDegreeContent);
            return this;
        }

        /**
         * 土地开发程度备注
         * @param developmentDegreeRemark 土地开发程度备注
         */
        public Builder developmentDegreeRemark(String developmentDegreeRemark) {
            obj.setDevelopmentDegreeRemark(developmentDegreeRemark);
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
         * 容积率
         * @param plotRatio 容积率
         */
        public Builder plotRatio(String plotRatio) {
            obj.setPlotRatio(plotRatio);
            return this;
        }

        /**
         * 容积率说明
         * @param plotRatioRemark 容积率说明
         */
        public Builder plotRatioRemark(String plotRatioRemark) {
            obj.setPlotRatioRemark(plotRatioRemark);
            return this;
        }

        public BasicEstateLandState build() {
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
         * @param greenSpaceRate 绿地率
         */
        public Builder greenSpaceRate(String greenSpaceRate) {
            obj.setGreenSpaceRate(greenSpaceRate);
            return this;
        }

        /**
         * 兼容比例
         * @param compatibleRatio 兼容比例
         */
        public Builder compatibleRatio(String compatibleRatio) {
            obj.setCompatibleRatio(compatibleRatio);
            return this;
        }

        /**
         * 兼容比例类型
         * @param compatibleRatioType 兼容比例类型
         */
        public Builder compatibleRatioType(String compatibleRatioType) {
            obj.setCompatibleRatioType(compatibleRatioType);
            return this;
        }

        /**
         * 承载力
         * @param bearingCapacity 承载力
         */
        public Builder bearingCapacity(String bearingCapacity) {
            obj.setBearingCapacity(bearingCapacity);
            return this;
        }

        /**
         * 污染
         * @param contaminated 污染
         */
        public Builder contaminated(String contaminated) {
            obj.setContaminated(contaminated);
            return this;
        }

        /**
         * 酸碱度
         * @param ph 酸碱度
         */
        public Builder ph(String ph) {
            obj.setPh(ph);
            return this;
        }

        /**
         * 肥力
         * @param fertility 肥力
         */
        public Builder fertility(String fertility) {
            obj.setFertility(fertility);
            return this;
        }

        /**
         * 土地实体结论
         * @param conclusion 土地实体结论
         */
        public Builder conclusion(String conclusion) {
            obj.setConclusion(conclusion);
            return this;
        }

        /**
         * 稳定性
         * @param holdOn 稳定性
         */
        public Builder holdOn(String holdOn) {
            obj.setHoldOn(holdOn);
            return this;
        }

        /**
         * 建筑限高
         * @param buildingHeightLimit 建筑限高
         */
        public Builder buildingHeightLimit(BigDecimal buildingHeightLimit) {
            obj.setBuildingHeightLimit(buildingHeightLimit);
            return this;
        }

        /**
         * 投资强度（万元/亩）
         * @param investmentIntensity 投资强度（万元/亩）
         */
        public Builder investmentIntensity(BigDecimal investmentIntensity) {
            obj.setInvestmentIntensity(investmentIntensity);
            return this;
        }

        /**
         * 特殊限制
         * @param specialRestrictions 特殊限制
         */
        public Builder specialRestrictions(String specialRestrictions) {
            obj.setSpecialRestrictions(specialRestrictions);
            return this;
        }

        /**
         * 土地利用现状
         * @param presentSituationLandUse 土地利用现状
         */
        public Builder presentSituationLandUse(String presentSituationLandUse) {
            obj.setPresentSituationLandUse(presentSituationLandUse);
            return this;
        }

        /**
         * 基础设施完备度
         * @param infrastructureCompleteness 基础设施完备度
         */
        public Builder infrastructureCompleteness(Integer infrastructureCompleteness) {
            obj.setInfrastructureCompleteness(infrastructureCompleteness);
            return this;
        }

        /**
         * 开发时间
         * @param developmentTime 开发时间
         */
        public Builder developmentTime(Date developmentTime) {
            obj.setDevelopmentTime(developmentTime);
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
         * 宗地内现状
         * @param currentSituation 宗地内现状
         */
        public Builder currentSituation(String currentSituation) {
            obj.setCurrentSituation(currentSituation);
            return this;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        applyId("apply_id", "applyId", "INTEGER", false),
        estateId("estate_id", "estateId", "INTEGER", false),
        name("name", "name", "VARCHAR", false),
        landUseType("land_use_type", "landUseType", "VARCHAR", false),
        landUseCategory("land_use_category", "landUseCategory", "VARCHAR", false),
        landUseYear("land_use_year", "landUseYear", "DECIMAL", false),
        landFactorTotalScore("land_factor_total_score", "landFactorTotalScore", "DECIMAL", false),
        landLevel("land_level", "landLevel", "INTEGER", false),
        landAreaUnit("land_area_unit", "landAreaUnit", "VARCHAR", false),
        landArea("land_area", "landArea", "VARCHAR", false),
        eastToName("east_to_name", "eastToName", "VARCHAR", false),
        eastTo("east_to", "eastTo", "VARCHAR", false),
        southToName("south_to_name", "southToName", "VARCHAR", false),
        southTo("south_to", "southTo", "VARCHAR", false),
        westToName("west_to_name", "westToName", "VARCHAR", false),
        westTo("west_to", "westTo", "VARCHAR", false),
        northToName("north_to_name", "northToName", "VARCHAR", false),
        northTo("north_to", "northTo", "VARCHAR", false),
        shapeState("shape_state", "shapeState", "INTEGER", false),
        shapeStateRemark("shape_state_remark", "shapeStateRemark", "VARCHAR", false),
        planeness("planeness", "planeness", "INTEGER", false),
        topographicTerrain("topographic_terrain", "topographicTerrain", "INTEGER", false),
        developmentDegree("development_degree", "developmentDegree", "INTEGER", false),
        developmentDegreeRemark("development_degree_remark", "developmentDegreeRemark", "VARCHAR", false),
        developmentDegreeContent("development_degree_content", "developmentDegreeContent", "VARCHAR", false),
        acquisitionTime("acquisition_time", "acquisitionTime", "TIMESTAMP", false),
        plotRatioRemark("plot_ratio_remark", "plotRatioRemark", "VARCHAR", false),
        plotRatio("plot_ratio", "plotRatio", "VARCHAR", false),
        buildingDensity("building_density", "buildingDensity", "VARCHAR", false),
        greenSpaceRate("green_space_rate", "greenSpaceRate", "VARCHAR", false),
        compatibleRatioType("compatible_ratio_type", "compatibleRatioType", "VARCHAR", false),
        compatibleRatio("compatible_ratio", "compatibleRatio", "VARCHAR", false),
        bearingCapacity("bearing_capacity", "bearingCapacity", "VARCHAR", false),
        contaminated("contaminated", "contaminated", "VARCHAR", false),
        ph("ph", "ph", "VARCHAR", false),
        fertility("fertility", "fertility", "VARCHAR", false),
        conclusion("conclusion", "conclusion", "VARCHAR", false),
        holdOn("hold_on", "holdOn", "VARCHAR", false),
        buildingHeightLimit("building_height_limit", "buildingHeightLimit", "DECIMAL", false),
        investmentIntensity("investment_intensity", "investmentIntensity", "DECIMAL", false),
        specialRestrictions("special_restrictions", "specialRestrictions", "VARCHAR", false),
        presentSituationLandUse("present_situation_land_use", "presentSituationLandUse", "VARCHAR", false),
        infrastructureCompleteness("infrastructure_completeness", "infrastructureCompleteness", "INTEGER", false),
        developmentTime("development_time", "developmentTime", "TIMESTAMP", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        currentSituation("current_situation", "currentSituation", "VARCHAR", false),
        landLevelContent("land_level_content", "landLevelContent", "LONGVARCHAR", false);

        /**
         * tb_basic_estate_land_state
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_estate_land_state
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_estate_land_state
         */
        private final String column;

        /**
         * tb_basic_estate_land_state
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_estate_land_state
         */
        private final String javaProperty;

        /**
         * tb_basic_estate_land_state
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