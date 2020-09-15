package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MdBaseLandPrice implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer planDetailsId;

    /**
     * 流程实例id
     */
    private String processInsId;

    /**
     * 报酬率id
     */
    private Integer rewardRateId;

    /**
     * 报酬率
     */
    private String rewardRate;

    /**
     * 基准地价单价
     */
    private BigDecimal standardPremium;

    /**
     * 基准地价说明
     */
    private String standardPremiumRemark;

    /**
     * 法定年限
     */
    private BigDecimal legalAge;

    /**
     * 法定年限说明
     */
    private String legalAgeRemark;

    /**
     * 剩余使用年限
     */
    private BigDecimal landSurplusYear;

    /**
     * 剩余使用年限说明
     */
    private String landSurplusYearRemark;

    /**
     * 年期修正系数
     */
    private BigDecimal periodAmend;

    /**
     * 开发程度修正
     */
    private BigDecimal developCorrect;

    /**
     * 开发程度修正说明
     */
    private String developCorrectRemark;

    /**
     * 委估对象容积率
     */
    private BigDecimal volumetricRate;

    /**
     * 委估对象容积率说明
     */
    private String volumetricRateRemark;

    /**
     * 委估宗地面积
     */
    private BigDecimal evaluationArea;

    /**
     * 宗地面积说明
     */
    private String evaluationAreaRemark;

    /**
     * 期日修正系数
     */
    private BigDecimal dateAmend;

    /**
     * 容积率修正
     */
    private BigDecimal volumeFractionAmend;

    /**
     * 区域及个别修正系数
     */
    private BigDecimal areaAndSeveralAmend;

    /**
     * 宗地单价
     */
    private BigDecimal parcelPrice;

    /**
     * 宗地亩价
     */
    private BigDecimal parcelBhouPrice;

    /**
     * 宗地总价
     */
    private BigDecimal parcelTotalPrice;

    /**
     * 楼面地价
     */
    private BigDecimal floorPremium;

    /**
     * 修正差额
     */
    private String correctionDifference;

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
     * 土地级别json
     */
    private String landLevelContent;

    /**
     * tb_md_base_land_price
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
     * @return plan_details_id 
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 
     * @param planDetailsId 
     */
    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    /**
     * 流程实例id
     * @return process_ins_id 流程实例id
     */
    public String getProcessInsId() {
        return processInsId;
    }

    /**
     * 流程实例id
     * @param processInsId 流程实例id
     */
    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    /**
     * 报酬率id
     * @return reward_rate_id 报酬率id
     */
    public Integer getRewardRateId() {
        return rewardRateId;
    }

    /**
     * 报酬率id
     * @param rewardRateId 报酬率id
     */
    public void setRewardRateId(Integer rewardRateId) {
        this.rewardRateId = rewardRateId;
    }

    /**
     * 报酬率
     * @return reward_rate 报酬率
     */
    public String getRewardRate() {
        return rewardRate;
    }

    /**
     * 报酬率
     * @param rewardRate 报酬率
     */
    public void setRewardRate(String rewardRate) {
        this.rewardRate = rewardRate == null ? null : rewardRate.trim();
    }

    /**
     * 基准地价单价
     * @return standard_premium 基准地价单价
     */
    public BigDecimal getStandardPremium() {
        return standardPremium;
    }

    /**
     * 基准地价单价
     * @param standardPremium 基准地价单价
     */
    public void setStandardPremium(BigDecimal standardPremium) {
        this.standardPremium = standardPremium;
    }

    /**
     * 基准地价说明
     * @return standard_premium_remark 基准地价说明
     */
    public String getStandardPremiumRemark() {
        return standardPremiumRemark;
    }

    /**
     * 基准地价说明
     * @param standardPremiumRemark 基准地价说明
     */
    public void setStandardPremiumRemark(String standardPremiumRemark) {
        this.standardPremiumRemark = standardPremiumRemark == null ? null : standardPremiumRemark.trim();
    }

    /**
     * 法定年限
     * @return legal_age 法定年限
     */
    public BigDecimal getLegalAge() {
        return legalAge;
    }

    /**
     * 法定年限
     * @param legalAge 法定年限
     */
    public void setLegalAge(BigDecimal legalAge) {
        this.legalAge = legalAge;
    }

    /**
     * 法定年限说明
     * @return legal_age_remark 法定年限说明
     */
    public String getLegalAgeRemark() {
        return legalAgeRemark;
    }

    /**
     * 法定年限说明
     * @param legalAgeRemark 法定年限说明
     */
    public void setLegalAgeRemark(String legalAgeRemark) {
        this.legalAgeRemark = legalAgeRemark == null ? null : legalAgeRemark.trim();
    }

    /**
     * 剩余使用年限
     * @return land_surplus_year 剩余使用年限
     */
    public BigDecimal getLandSurplusYear() {
        return landSurplusYear;
    }

    /**
     * 剩余使用年限
     * @param landSurplusYear 剩余使用年限
     */
    public void setLandSurplusYear(BigDecimal landSurplusYear) {
        this.landSurplusYear = landSurplusYear;
    }

    /**
     * 剩余使用年限说明
     * @return land_surplus_year_remark 剩余使用年限说明
     */
    public String getLandSurplusYearRemark() {
        return landSurplusYearRemark;
    }

    /**
     * 剩余使用年限说明
     * @param landSurplusYearRemark 剩余使用年限说明
     */
    public void setLandSurplusYearRemark(String landSurplusYearRemark) {
        this.landSurplusYearRemark = landSurplusYearRemark == null ? null : landSurplusYearRemark.trim();
    }

    /**
     * 年期修正系数
     * @return period_amend 年期修正系数
     */
    public BigDecimal getPeriodAmend() {
        return periodAmend;
    }

    /**
     * 年期修正系数
     * @param periodAmend 年期修正系数
     */
    public void setPeriodAmend(BigDecimal periodAmend) {
        this.periodAmend = periodAmend;
    }

    /**
     * 开发程度修正
     * @return develop_correct 开发程度修正
     */
    public BigDecimal getDevelopCorrect() {
        return developCorrect;
    }

    /**
     * 开发程度修正
     * @param developCorrect 开发程度修正
     */
    public void setDevelopCorrect(BigDecimal developCorrect) {
        this.developCorrect = developCorrect;
    }

    /**
     * 开发程度修正说明
     * @return develop_correct_remark 开发程度修正说明
     */
    public String getDevelopCorrectRemark() {
        return developCorrectRemark;
    }

    /**
     * 开发程度修正说明
     * @param developCorrectRemark 开发程度修正说明
     */
    public void setDevelopCorrectRemark(String developCorrectRemark) {
        this.developCorrectRemark = developCorrectRemark == null ? null : developCorrectRemark.trim();
    }

    /**
     * 委估对象容积率
     * @return volumetric_rate 委估对象容积率
     */
    public BigDecimal getVolumetricRate() {
        return volumetricRate;
    }

    /**
     * 委估对象容积率
     * @param volumetricRate 委估对象容积率
     */
    public void setVolumetricRate(BigDecimal volumetricRate) {
        this.volumetricRate = volumetricRate;
    }

    /**
     * 委估对象容积率说明
     * @return volumetric_rate_remark 委估对象容积率说明
     */
    public String getVolumetricRateRemark() {
        return volumetricRateRemark;
    }

    /**
     * 委估对象容积率说明
     * @param volumetricRateRemark 委估对象容积率说明
     */
    public void setVolumetricRateRemark(String volumetricRateRemark) {
        this.volumetricRateRemark = volumetricRateRemark == null ? null : volumetricRateRemark.trim();
    }

    /**
     * 委估宗地面积
     * @return evaluation_area 委估宗地面积
     */
    public BigDecimal getEvaluationArea() {
        return evaluationArea;
    }

    /**
     * 委估宗地面积
     * @param evaluationArea 委估宗地面积
     */
    public void setEvaluationArea(BigDecimal evaluationArea) {
        this.evaluationArea = evaluationArea;
    }

    /**
     * 宗地面积说明
     * @return evaluation_area_remark 宗地面积说明
     */
    public String getEvaluationAreaRemark() {
        return evaluationAreaRemark;
    }

    /**
     * 宗地面积说明
     * @param evaluationAreaRemark 宗地面积说明
     */
    public void setEvaluationAreaRemark(String evaluationAreaRemark) {
        this.evaluationAreaRemark = evaluationAreaRemark == null ? null : evaluationAreaRemark.trim();
    }

    /**
     * 期日修正系数
     * @return date_amend 期日修正系数
     */
    public BigDecimal getDateAmend() {
        return dateAmend;
    }

    /**
     * 期日修正系数
     * @param dateAmend 期日修正系数
     */
    public void setDateAmend(BigDecimal dateAmend) {
        this.dateAmend = dateAmend;
    }

    /**
     * 容积率修正
     * @return volume_fraction_amend 容积率修正
     */
    public BigDecimal getVolumeFractionAmend() {
        return volumeFractionAmend;
    }

    /**
     * 容积率修正
     * @param volumeFractionAmend 容积率修正
     */
    public void setVolumeFractionAmend(BigDecimal volumeFractionAmend) {
        this.volumeFractionAmend = volumeFractionAmend;
    }

    /**
     * 区域及个别修正系数
     * @return area_and_several_amend 区域及个别修正系数
     */
    public BigDecimal getAreaAndSeveralAmend() {
        return areaAndSeveralAmend;
    }

    /**
     * 区域及个别修正系数
     * @param areaAndSeveralAmend 区域及个别修正系数
     */
    public void setAreaAndSeveralAmend(BigDecimal areaAndSeveralAmend) {
        this.areaAndSeveralAmend = areaAndSeveralAmend;
    }

    /**
     * 宗地单价
     * @return parcel_price 宗地单价
     */
    public BigDecimal getParcelPrice() {
        return parcelPrice;
    }

    /**
     * 宗地单价
     * @param parcelPrice 宗地单价
     */
    public void setParcelPrice(BigDecimal parcelPrice) {
        this.parcelPrice = parcelPrice;
    }

    /**
     * 宗地亩价
     * @return parcel_bhou_price 宗地亩价
     */
    public BigDecimal getParcelBhouPrice() {
        return parcelBhouPrice;
    }

    /**
     * 宗地亩价
     * @param parcelBhouPrice 宗地亩价
     */
    public void setParcelBhouPrice(BigDecimal parcelBhouPrice) {
        this.parcelBhouPrice = parcelBhouPrice;
    }

    /**
     * 宗地总价
     * @return parcel_total_price 宗地总价
     */
    public BigDecimal getParcelTotalPrice() {
        return parcelTotalPrice;
    }

    /**
     * 宗地总价
     * @param parcelTotalPrice 宗地总价
     */
    public void setParcelTotalPrice(BigDecimal parcelTotalPrice) {
        this.parcelTotalPrice = parcelTotalPrice;
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

    /**
     * 修正差额
     * @return correction_difference 修正差额
     */
    public String getCorrectionDifference() {
        return correctionDifference;
    }

    /**
     * 修正差额
     * @param correctionDifference 修正差额
     */
    public void setCorrectionDifference(String correctionDifference) {
        this.correctionDifference = correctionDifference == null ? null : correctionDifference.trim();
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

    public static MdBaseLandPrice.Builder builder() {
        return new MdBaseLandPrice.Builder();
    }

    /**
     * tb_md_base_land_price
     */
    public static class Builder {
        /**
         * tb_md_base_land_price
         */
        private MdBaseLandPrice obj;

        public Builder() {
            this.obj = new MdBaseLandPrice();
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
         * @param planDetailsId 
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * 流程实例id
         * @param processInsId 流程实例id
         */
        public Builder processInsId(String processInsId) {
            obj.setProcessInsId(processInsId);
            return this;
        }

        /**
         * 报酬率
         * @param rewardRate 报酬率
         */
        public Builder rewardRate(String rewardRate) {
            obj.setRewardRate(rewardRate);
            return this;
        }

        /**
         * 报酬率id
         * @param rewardRateId 报酬率id
         */
        public Builder rewardRateId(Integer rewardRateId) {
            obj.setRewardRateId(rewardRateId);
            return this;
        }

        /**
         * 基准地价单价
         * @param standardPremium 基准地价单价
         */
        public Builder standardPremium(BigDecimal standardPremium) {
            obj.setStandardPremium(standardPremium);
            return this;
        }

        /**
         * 基准地价说明
         * @param standardPremiumRemark 基准地价说明
         */
        public Builder standardPremiumRemark(String standardPremiumRemark) {
            obj.setStandardPremiumRemark(standardPremiumRemark);
            return this;
        }

        /**
         * 法定年限
         * @param legalAge 法定年限
         */
        public Builder legalAge(BigDecimal legalAge) {
            obj.setLegalAge(legalAge);
            return this;
        }

        /**
         * 法定年限说明
         * @param legalAgeRemark 法定年限说明
         */
        public Builder legalAgeRemark(String legalAgeRemark) {
            obj.setLegalAgeRemark(legalAgeRemark);
            return this;
        }

        /**
         * 剩余使用年限
         * @param landSurplusYear 剩余使用年限
         */
        public Builder landSurplusYear(BigDecimal landSurplusYear) {
            obj.setLandSurplusYear(landSurplusYear);
            return this;
        }

        /**
         * 剩余使用年限说明
         * @param landSurplusYearRemark 剩余使用年限说明
         */
        public Builder landSurplusYearRemark(String landSurplusYearRemark) {
            obj.setLandSurplusYearRemark(landSurplusYearRemark);
            return this;
        }

        /**
         * 年期修正系数
         * @param periodAmend 年期修正系数
         */
        public Builder periodAmend(BigDecimal periodAmend) {
            obj.setPeriodAmend(periodAmend);
            return this;
        }

        /**
         * 开发程度修正
         * @param developCorrect 开发程度修正
         */
        public Builder developCorrect(BigDecimal developCorrect) {
            obj.setDevelopCorrect(developCorrect);
            return this;
        }

        /**
         * 开发程度修正说明
         * @param developCorrectRemark 开发程度修正说明
         */
        public Builder developCorrectRemark(String developCorrectRemark) {
            obj.setDevelopCorrectRemark(developCorrectRemark);
            return this;
        }

        /**
         * 委估对象容积率
         * @param volumetricRate 委估对象容积率
         */
        public Builder volumetricRate(BigDecimal volumetricRate) {
            obj.setVolumetricRate(volumetricRate);
            return this;
        }

        /**
         * 委估对象容积率说明
         * @param volumetricRateRemark 委估对象容积率说明
         */
        public Builder volumetricRateRemark(String volumetricRateRemark) {
            obj.setVolumetricRateRemark(volumetricRateRemark);
            return this;
        }

        /**
         * 委估宗地面积
         * @param evaluationArea 委估宗地面积
         */
        public Builder evaluationArea(BigDecimal evaluationArea) {
            obj.setEvaluationArea(evaluationArea);
            return this;
        }

        /**
         * 宗地面积说明
         * @param evaluationAreaRemark 宗地面积说明
         */
        public Builder evaluationAreaRemark(String evaluationAreaRemark) {
            obj.setEvaluationAreaRemark(evaluationAreaRemark);
            return this;
        }

        /**
         * 期日修正系数
         * @param dateAmend 期日修正系数
         */
        public Builder dateAmend(BigDecimal dateAmend) {
            obj.setDateAmend(dateAmend);
            return this;
        }

        /**
         * 容积率修正
         * @param volumeFractionAmend 容积率修正
         */
        public Builder volumeFractionAmend(BigDecimal volumeFractionAmend) {
            obj.setVolumeFractionAmend(volumeFractionAmend);
            return this;
        }

        /**
         * 区域及个别修正系数
         * @param areaAndSeveralAmend 区域及个别修正系数
         */
        public Builder areaAndSeveralAmend(BigDecimal areaAndSeveralAmend) {
            obj.setAreaAndSeveralAmend(areaAndSeveralAmend);
            return this;
        }

        /**
         * 宗地单价
         * @param parcelPrice 宗地单价
         */
        public Builder parcelPrice(BigDecimal parcelPrice) {
            obj.setParcelPrice(parcelPrice);
            return this;
        }

        /**
         * 宗地亩价
         * @param parcelBhouPrice 宗地亩价
         */
        public Builder parcelBhouPrice(BigDecimal parcelBhouPrice) {
            obj.setParcelBhouPrice(parcelBhouPrice);
            return this;
        }

        /**
         * 宗地总价
         * @param parcelTotalPrice 宗地总价
         */
        public Builder parcelTotalPrice(BigDecimal parcelTotalPrice) {
            obj.setParcelTotalPrice(parcelTotalPrice);
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

        /**
         * 修正差额
         * @param correctionDifference 修正差额
         */
        public Builder correctionDifference(String correctionDifference) {
            obj.setCorrectionDifference(correctionDifference);
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
         * 土地级别json
         * @param landLevelContent 土地级别json
         */
        public Builder landLevelContent(String landLevelContent) {
            obj.setLandLevelContent(landLevelContent);
            return this;
        }

        public MdBaseLandPrice build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        processInsId("process_ins_id", "processInsId", "VARCHAR", false),
        rewardRateId("reward_rate_id", "rewardRateId", "INTEGER", false),
        rewardRate("reward_rate", "rewardRate", "VARCHAR", false),
        standardPremium("standard_premium", "standardPremium", "DECIMAL", false),
        standardPremiumRemark("standard_premium_remark", "standardPremiumRemark", "VARCHAR", false),
        legalAge("legal_age", "legalAge", "DECIMAL", false),
        legalAgeRemark("legal_age_remark", "legalAgeRemark", "VARCHAR", false),
        landSurplusYear("land_surplus_year", "landSurplusYear", "DECIMAL", false),
        landSurplusYearRemark("land_surplus_year_remark", "landSurplusYearRemark", "VARCHAR", false),
        periodAmend("period_amend", "periodAmend", "DECIMAL", false),
        developCorrect("develop_correct", "developCorrect", "DECIMAL", false),
        developCorrectRemark("develop_correct_remark", "developCorrectRemark", "VARCHAR", false),
        volumetricRate("volumetric_rate", "volumetricRate", "DECIMAL", false),
        volumetricRateRemark("volumetric_rate_remark", "volumetricRateRemark", "VARCHAR", false),
        evaluationArea("evaluation_area", "evaluationArea", "DECIMAL", false),
        evaluationAreaRemark("evaluation_area_remark", "evaluationAreaRemark", "VARCHAR", false),
        dateAmend("date_amend", "dateAmend", "DECIMAL", false),
        volumeFractionAmend("volume_fraction_amend", "volumeFractionAmend", "DECIMAL", false),
        areaAndSeveralAmend("area_and_several_amend", "areaAndSeveralAmend", "DECIMAL", false),
        parcelPrice("parcel_price", "parcelPrice", "DECIMAL", false),
        parcelBhouPrice("parcel_bhou_price", "parcelBhouPrice", "DECIMAL", false),
        parcelTotalPrice("parcel_total_price", "parcelTotalPrice", "DECIMAL", false),
        floorPremium("floor_premium", "floorPremium", "DECIMAL", false),
        correctionDifference("correction_difference", "correctionDifference", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        landLevelContent("land_level_content", "landLevelContent", "LONGVARCHAR", false);

        /**
         * tb_md_base_land_price
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_md_base_land_price
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_md_base_land_price
         */
        private final String column;

        /**
         * tb_md_base_land_price
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_md_base_land_price
         */
        private final String javaProperty;

        /**
         * tb_md_base_land_price
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