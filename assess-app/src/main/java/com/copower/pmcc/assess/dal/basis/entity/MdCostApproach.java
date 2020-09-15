package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MdCostApproach implements Serializable {
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
     * 农用地面积
     */
    private BigDecimal farmlandArea;

    /**
     * 耕地面积
     */
    private BigDecimal ploughArea;

    /**
     * 人口数
     */
    private Integer populationNumber;

    /**
     * 还原率id
     */
    private Integer rewardRateId;

    /**
     * 还原率
     */
    private String rewardRate;

    /**
     * 宗地外流通费用
     */
    private BigDecimal circulationExpense;

    /**
     * 宗地外六通费用说明
     */
    private String circulationExpenseRemark;

    /**
     * 场平费用
     */
    private BigDecimal flatExpense;

    /**
     * 场平费用说明
     */
    private String flatExpenseRemark;

    /**
     * 计息周期
     */
    private BigDecimal machineCycle;

    /**
     * 计算周期说明
     */
    private String machineCycleRemark;

    /**
     * 计算利息
     */
    private String calculatedInterest;

    /**
     * 计算利息说明
     */
    private String calculatedInterestRemark;

    /**
     * 利润率
     */
    private String profitMargin;

    /**
     * 利润率说明
     */
    private String profitMarginRemark;

    /**
     * 土地增值率
     */
    private String incrementalBenefit;

    /**
     * 土地增值率说明
     */
    private String incrementalBenefitRemark;

    /**
     * 容积率调整
     */
    private String plotRatioAdjust;

    /**
     * 容积率调整说明
     */
    private String plotRatioAdjustRemark;

    /**
     * 容积率个别因素修正
     */
    private BigDecimal plotRatioElementAmend;

    /**
     * 容积率个别因素修正说明
     */
    private String plotRatioElementAmendRemark;

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
     * 剩余年限
     */
    private BigDecimal landRemainingYear;

    /**
     * 剩余年限说明
     */
    private String landRemainingYearRemark;

    /**
     * 宗地外设定
     */
    private String parcelSettingOuter;

    /**
     * 宗地内设定
     */
    private String parcelSettingInner;

    /**
     * 无限年期土地使用权价格
     */
    private BigDecimal landUsePrice;

    /**
     * 年期修正系数
     */
    private BigDecimal yearFixed;

    /**
     * 委估宗地单价
     */
    private BigDecimal parcelUnit;

    /**
     * 土地取得成本
     */
    private BigDecimal landCostPriceUnit;

    /**
     * 土地取得费及相关税费
     */
    private BigDecimal landAcquisitionUnit;

    /**
     * 土地开发利润
     */
    private BigDecimal landProductionProfitUnit;

    /**
     * 土地开发利息
     */
    private BigDecimal landProductionInterestUnit;

    /**
     * 不含代征地每平税费
     */
    private BigDecimal haveNotLandAcquisition;

    /**
     * 修正明细
     */
    private String landLevelContent;

    /**
     * tb_md_cost_approach
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
     * 农用地面积
     * @return farmland_area 农用地面积
     */
    public BigDecimal getFarmlandArea() {
        return farmlandArea;
    }

    /**
     * 农用地面积
     * @param farmlandArea 农用地面积
     */
    public void setFarmlandArea(BigDecimal farmlandArea) {
        this.farmlandArea = farmlandArea;
    }

    /**
     * 耕地面积
     * @return plough_area 耕地面积
     */
    public BigDecimal getPloughArea() {
        return ploughArea;
    }

    /**
     * 耕地面积
     * @param ploughArea 耕地面积
     */
    public void setPloughArea(BigDecimal ploughArea) {
        this.ploughArea = ploughArea;
    }

    /**
     * 人口数
     * @return population_number 人口数
     */
    public Integer getPopulationNumber() {
        return populationNumber;
    }

    /**
     * 人口数
     * @param populationNumber 人口数
     */
    public void setPopulationNumber(Integer populationNumber) {
        this.populationNumber = populationNumber;
    }

    /**
     * 还原率id
     * @return reward_rate_id 还原率id
     */
    public Integer getRewardRateId() {
        return rewardRateId;
    }

    /**
     * 还原率id
     * @param rewardRateId 还原率id
     */
    public void setRewardRateId(Integer rewardRateId) {
        this.rewardRateId = rewardRateId;
    }

    /**
     * 还原率
     * @return reward_rate 还原率
     */
    public String getRewardRate() {
        return rewardRate;
    }

    /**
     * 还原率
     * @param rewardRate 还原率
     */
    public void setRewardRate(String rewardRate) {
        this.rewardRate = rewardRate == null ? null : rewardRate.trim();
    }

    /**
     * 宗地外流通费用
     * @return circulation_expense 宗地外流通费用
     */
    public BigDecimal getCirculationExpense() {
        return circulationExpense;
    }

    /**
     * 宗地外流通费用
     * @param circulationExpense 宗地外流通费用
     */
    public void setCirculationExpense(BigDecimal circulationExpense) {
        this.circulationExpense = circulationExpense;
    }

    /**
     * 宗地外六通费用说明
     * @return circulation_expense_remark 宗地外六通费用说明
     */
    public String getCirculationExpenseRemark() {
        return circulationExpenseRemark;
    }

    /**
     * 宗地外六通费用说明
     * @param circulationExpenseRemark 宗地外六通费用说明
     */
    public void setCirculationExpenseRemark(String circulationExpenseRemark) {
        this.circulationExpenseRemark = circulationExpenseRemark == null ? null : circulationExpenseRemark.trim();
    }

    /**
     * 场平费用
     * @return flat_expense 场平费用
     */
    public BigDecimal getFlatExpense() {
        return flatExpense;
    }

    /**
     * 场平费用
     * @param flatExpense 场平费用
     */
    public void setFlatExpense(BigDecimal flatExpense) {
        this.flatExpense = flatExpense;
    }

    /**
     * 场平费用说明
     * @return flat_expense_remark 场平费用说明
     */
    public String getFlatExpenseRemark() {
        return flatExpenseRemark;
    }

    /**
     * 场平费用说明
     * @param flatExpenseRemark 场平费用说明
     */
    public void setFlatExpenseRemark(String flatExpenseRemark) {
        this.flatExpenseRemark = flatExpenseRemark == null ? null : flatExpenseRemark.trim();
    }

    /**
     * 计息周期
     * @return machine_cycle 计息周期
     */
    public BigDecimal getMachineCycle() {
        return machineCycle;
    }

    /**
     * 计息周期
     * @param machineCycle 计息周期
     */
    public void setMachineCycle(BigDecimal machineCycle) {
        this.machineCycle = machineCycle;
    }

    /**
     * 计算周期说明
     * @return machine_cycle_remark 计算周期说明
     */
    public String getMachineCycleRemark() {
        return machineCycleRemark;
    }

    /**
     * 计算周期说明
     * @param machineCycleRemark 计算周期说明
     */
    public void setMachineCycleRemark(String machineCycleRemark) {
        this.machineCycleRemark = machineCycleRemark == null ? null : machineCycleRemark.trim();
    }

    /**
     * 计算利息
     * @return calculated_interest 计算利息
     */
    public String getCalculatedInterest() {
        return calculatedInterest;
    }

    /**
     * 计算利息
     * @param calculatedInterest 计算利息
     */
    public void setCalculatedInterest(String calculatedInterest) {
        this.calculatedInterest = calculatedInterest == null ? null : calculatedInterest.trim();
    }

    /**
     * 计算利息说明
     * @return calculated_interest_remark 计算利息说明
     */
    public String getCalculatedInterestRemark() {
        return calculatedInterestRemark;
    }

    /**
     * 计算利息说明
     * @param calculatedInterestRemark 计算利息说明
     */
    public void setCalculatedInterestRemark(String calculatedInterestRemark) {
        this.calculatedInterestRemark = calculatedInterestRemark == null ? null : calculatedInterestRemark.trim();
    }

    /**
     * 利润率
     * @return profit_margin 利润率
     */
    public String getProfitMargin() {
        return profitMargin;
    }

    /**
     * 利润率
     * @param profitMargin 利润率
     */
    public void setProfitMargin(String profitMargin) {
        this.profitMargin = profitMargin == null ? null : profitMargin.trim();
    }

    /**
     * 利润率说明
     * @return profit_margin_remark 利润率说明
     */
    public String getProfitMarginRemark() {
        return profitMarginRemark;
    }

    /**
     * 利润率说明
     * @param profitMarginRemark 利润率说明
     */
    public void setProfitMarginRemark(String profitMarginRemark) {
        this.profitMarginRemark = profitMarginRemark == null ? null : profitMarginRemark.trim();
    }

    /**
     * 土地增值率
     * @return incremental_benefit 土地增值率
     */
    public String getIncrementalBenefit() {
        return incrementalBenefit;
    }

    /**
     * 土地增值率
     * @param incrementalBenefit 土地增值率
     */
    public void setIncrementalBenefit(String incrementalBenefit) {
        this.incrementalBenefit = incrementalBenefit == null ? null : incrementalBenefit.trim();
    }

    /**
     * 土地增值率说明
     * @return incremental_benefit_remark 土地增值率说明
     */
    public String getIncrementalBenefitRemark() {
        return incrementalBenefitRemark;
    }

    /**
     * 土地增值率说明
     * @param incrementalBenefitRemark 土地增值率说明
     */
    public void setIncrementalBenefitRemark(String incrementalBenefitRemark) {
        this.incrementalBenefitRemark = incrementalBenefitRemark == null ? null : incrementalBenefitRemark.trim();
    }

    /**
     * 容积率调整
     * @return plot_ratio_adjust 容积率调整
     */
    public String getPlotRatioAdjust() {
        return plotRatioAdjust;
    }

    /**
     * 容积率调整
     * @param plotRatioAdjust 容积率调整
     */
    public void setPlotRatioAdjust(String plotRatioAdjust) {
        this.plotRatioAdjust = plotRatioAdjust == null ? null : plotRatioAdjust.trim();
    }

    /**
     * 容积率调整说明
     * @return plot_ratio_adjust_remark 容积率调整说明
     */
    public String getPlotRatioAdjustRemark() {
        return plotRatioAdjustRemark;
    }

    /**
     * 容积率调整说明
     * @param plotRatioAdjustRemark 容积率调整说明
     */
    public void setPlotRatioAdjustRemark(String plotRatioAdjustRemark) {
        this.plotRatioAdjustRemark = plotRatioAdjustRemark == null ? null : plotRatioAdjustRemark.trim();
    }

    /**
     * 容积率个别因素修正
     * @return plot_ratio_element_amend 容积率个别因素修正
     */
    public BigDecimal getPlotRatioElementAmend() {
        return plotRatioElementAmend;
    }

    /**
     * 容积率个别因素修正
     * @param plotRatioElementAmend 容积率个别因素修正
     */
    public void setPlotRatioElementAmend(BigDecimal plotRatioElementAmend) {
        this.plotRatioElementAmend = plotRatioElementAmend;
    }

    /**
     * 容积率个别因素修正说明
     * @return plot_ratio_element_amend_remark 容积率个别因素修正说明
     */
    public String getPlotRatioElementAmendRemark() {
        return plotRatioElementAmendRemark;
    }

    /**
     * 容积率个别因素修正说明
     * @param plotRatioElementAmendRemark 容积率个别因素修正说明
     */
    public void setPlotRatioElementAmendRemark(String plotRatioElementAmendRemark) {
        this.plotRatioElementAmendRemark = plotRatioElementAmendRemark == null ? null : plotRatioElementAmendRemark.trim();
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
     * 剩余年限
     * @return land_remaining_year 剩余年限
     */
    public BigDecimal getLandRemainingYear() {
        return landRemainingYear;
    }

    /**
     * 剩余年限
     * @param landRemainingYear 剩余年限
     */
    public void setLandRemainingYear(BigDecimal landRemainingYear) {
        this.landRemainingYear = landRemainingYear;
    }

    /**
     * 剩余年限说明
     * @return land_remaining_year_remark 剩余年限说明
     */
    public String getLandRemainingYearRemark() {
        return landRemainingYearRemark;
    }

    /**
     * 剩余年限说明
     * @param landRemainingYearRemark 剩余年限说明
     */
    public void setLandRemainingYearRemark(String landRemainingYearRemark) {
        this.landRemainingYearRemark = landRemainingYearRemark == null ? null : landRemainingYearRemark.trim();
    }

    /**
     * 宗地外设定
     * @return parcel_setting_outer 宗地外设定
     */
    public String getParcelSettingOuter() {
        return parcelSettingOuter;
    }

    /**
     * 宗地外设定
     * @param parcelSettingOuter 宗地外设定
     */
    public void setParcelSettingOuter(String parcelSettingOuter) {
        this.parcelSettingOuter = parcelSettingOuter == null ? null : parcelSettingOuter.trim();
    }

    /**
     * 宗地内设定
     * @return parcel_setting_inner 宗地内设定
     */
    public String getParcelSettingInner() {
        return parcelSettingInner;
    }

    /**
     * 宗地内设定
     * @param parcelSettingInner 宗地内设定
     */
    public void setParcelSettingInner(String parcelSettingInner) {
        this.parcelSettingInner = parcelSettingInner == null ? null : parcelSettingInner.trim();
    }

    /**
     * 无限年期土地使用权价格
     * @return land_use_price 无限年期土地使用权价格
     */
    public BigDecimal getLandUsePrice() {
        return landUsePrice;
    }

    /**
     * 无限年期土地使用权价格
     * @param landUsePrice 无限年期土地使用权价格
     */
    public void setLandUsePrice(BigDecimal landUsePrice) {
        this.landUsePrice = landUsePrice;
    }

    /**
     * 年期修正系数
     * @return year_fixed 年期修正系数
     */
    public BigDecimal getYearFixed() {
        return yearFixed;
    }

    /**
     * 年期修正系数
     * @param yearFixed 年期修正系数
     */
    public void setYearFixed(BigDecimal yearFixed) {
        this.yearFixed = yearFixed;
    }

    /**
     * 委估宗地单价
     * @return parcel_unit 委估宗地单价
     */
    public BigDecimal getParcelUnit() {
        return parcelUnit;
    }

    /**
     * 委估宗地单价
     * @param parcelUnit 委估宗地单价
     */
    public void setParcelUnit(BigDecimal parcelUnit) {
        this.parcelUnit = parcelUnit;
    }

    /**
     * 土地取得成本
     * @return land_cost_price_unit 土地取得成本
     */
    public BigDecimal getLandCostPriceUnit() {
        return landCostPriceUnit;
    }

    /**
     * 土地取得成本
     * @param landCostPriceUnit 土地取得成本
     */
    public void setLandCostPriceUnit(BigDecimal landCostPriceUnit) {
        this.landCostPriceUnit = landCostPriceUnit;
    }

    /**
     * 土地取得费及相关税费
     * @return land_acquisition_unit 土地取得费及相关税费
     */
    public BigDecimal getLandAcquisitionUnit() {
        return landAcquisitionUnit;
    }

    /**
     * 土地取得费及相关税费
     * @param landAcquisitionUnit 土地取得费及相关税费
     */
    public void setLandAcquisitionUnit(BigDecimal landAcquisitionUnit) {
        this.landAcquisitionUnit = landAcquisitionUnit;
    }

    /**
     * 土地开发利润
     * @return land_production_profit_unit 土地开发利润
     */
    public BigDecimal getLandProductionProfitUnit() {
        return landProductionProfitUnit;
    }

    /**
     * 土地开发利润
     * @param landProductionProfitUnit 土地开发利润
     */
    public void setLandProductionProfitUnit(BigDecimal landProductionProfitUnit) {
        this.landProductionProfitUnit = landProductionProfitUnit;
    }

    /**
     * 土地开发利息
     * @return land_production_interest_unit 土地开发利息
     */
    public BigDecimal getLandProductionInterestUnit() {
        return landProductionInterestUnit;
    }

    /**
     * 土地开发利息
     * @param landProductionInterestUnit 土地开发利息
     */
    public void setLandProductionInterestUnit(BigDecimal landProductionInterestUnit) {
        this.landProductionInterestUnit = landProductionInterestUnit;
    }

    /**
     * 不含代征地每平税费
     * @return have_not_land_acquisition 不含代征地每平税费
     */
    public BigDecimal getHaveNotLandAcquisition() {
        return haveNotLandAcquisition;
    }

    /**
     * 不含代征地每平税费
     * @param haveNotLandAcquisition 不含代征地每平税费
     */
    public void setHaveNotLandAcquisition(BigDecimal haveNotLandAcquisition) {
        this.haveNotLandAcquisition = haveNotLandAcquisition;
    }

    /**
     * 修正明细
     * @return land_level_content 修正明细
     */
    public String getLandLevelContent() {
        return landLevelContent;
    }

    /**
     * 修正明细
     * @param landLevelContent 修正明细
     */
    public void setLandLevelContent(String landLevelContent) {
        this.landLevelContent = landLevelContent == null ? null : landLevelContent.trim();
    }

    public static MdCostApproach.Builder builder() {
        return new MdCostApproach.Builder();
    }

    /**
     * tb_md_cost_approach
     */
    public static class Builder {
        /**
         * tb_md_cost_approach
         */
        private MdCostApproach obj;

        public Builder() {
            this.obj = new MdCostApproach();
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
         * 农用地面积
         * @param farmlandArea 农用地面积
         */
        public Builder farmlandArea(BigDecimal farmlandArea) {
            obj.setFarmlandArea(farmlandArea);
            return this;
        }

        /**
         * 耕地面积
         * @param ploughArea 耕地面积
         */
        public Builder ploughArea(BigDecimal ploughArea) {
            obj.setPloughArea(ploughArea);
            return this;
        }

        /**
         * 人口数
         * @param populationNumber 人口数
         */
        public Builder populationNumber(Integer populationNumber) {
            obj.setPopulationNumber(populationNumber);
            return this;
        }

        /**
         * 还原率
         * @param rewardRate 还原率
         */
        public Builder rewardRate(String rewardRate) {
            obj.setRewardRate(rewardRate);
            return this;
        }

        /**
         * 还原率id
         * @param rewardRateId 还原率id
         */
        public Builder rewardRateId(Integer rewardRateId) {
            obj.setRewardRateId(rewardRateId);
            return this;
        }

        /**
         * 宗地外流通费用
         * @param circulationExpense 宗地外流通费用
         */
        public Builder circulationExpense(BigDecimal circulationExpense) {
            obj.setCirculationExpense(circulationExpense);
            return this;
        }

        /**
         * 宗地外六通费用说明
         * @param circulationExpenseRemark 宗地外六通费用说明
         */
        public Builder circulationExpenseRemark(String circulationExpenseRemark) {
            obj.setCirculationExpenseRemark(circulationExpenseRemark);
            return this;
        }

        /**
         * 场平费用
         * @param flatExpense 场平费用
         */
        public Builder flatExpense(BigDecimal flatExpense) {
            obj.setFlatExpense(flatExpense);
            return this;
        }

        /**
         * 场平费用说明
         * @param flatExpenseRemark 场平费用说明
         */
        public Builder flatExpenseRemark(String flatExpenseRemark) {
            obj.setFlatExpenseRemark(flatExpenseRemark);
            return this;
        }

        /**
         * 计息周期
         * @param machineCycle 计息周期
         */
        public Builder machineCycle(BigDecimal machineCycle) {
            obj.setMachineCycle(machineCycle);
            return this;
        }

        /**
         * 计算周期说明
         * @param machineCycleRemark 计算周期说明
         */
        public Builder machineCycleRemark(String machineCycleRemark) {
            obj.setMachineCycleRemark(machineCycleRemark);
            return this;
        }

        /**
         * 计算利息
         * @param calculatedInterest 计算利息
         */
        public Builder calculatedInterest(String calculatedInterest) {
            obj.setCalculatedInterest(calculatedInterest);
            return this;
        }

        /**
         * 计算利息说明
         * @param calculatedInterestRemark 计算利息说明
         */
        public Builder calculatedInterestRemark(String calculatedInterestRemark) {
            obj.setCalculatedInterestRemark(calculatedInterestRemark);
            return this;
        }

        /**
         * 利润率
         * @param profitMargin 利润率
         */
        public Builder profitMargin(String profitMargin) {
            obj.setProfitMargin(profitMargin);
            return this;
        }

        /**
         * 利润率说明
         * @param profitMarginRemark 利润率说明
         */
        public Builder profitMarginRemark(String profitMarginRemark) {
            obj.setProfitMarginRemark(profitMarginRemark);
            return this;
        }

        /**
         * 土地增值率
         * @param incrementalBenefit 土地增值率
         */
        public Builder incrementalBenefit(String incrementalBenefit) {
            obj.setIncrementalBenefit(incrementalBenefit);
            return this;
        }

        /**
         * 土地增值率说明
         * @param incrementalBenefitRemark 土地增值率说明
         */
        public Builder incrementalBenefitRemark(String incrementalBenefitRemark) {
            obj.setIncrementalBenefitRemark(incrementalBenefitRemark);
            return this;
        }

        /**
         * 容积率调整
         * @param plotRatioAdjust 容积率调整
         */
        public Builder plotRatioAdjust(String plotRatioAdjust) {
            obj.setPlotRatioAdjust(plotRatioAdjust);
            return this;
        }

        /**
         * 容积率调整说明
         * @param plotRatioAdjustRemark 容积率调整说明
         */
        public Builder plotRatioAdjustRemark(String plotRatioAdjustRemark) {
            obj.setPlotRatioAdjustRemark(plotRatioAdjustRemark);
            return this;
        }

        /**
         * 容积率个别因素修正
         * @param plotRatioElementAmend 容积率个别因素修正
         */
        public Builder plotRatioElementAmend(BigDecimal plotRatioElementAmend) {
            obj.setPlotRatioElementAmend(plotRatioElementAmend);
            return this;
        }

        /**
         * 容积率个别因素修正说明
         * @param plotRatioElementAmendRemark 容积率个别因素修正说明
         */
        public Builder plotRatioElementAmendRemark(String plotRatioElementAmendRemark) {
            obj.setPlotRatioElementAmendRemark(plotRatioElementAmendRemark);
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
         * 剩余年限
         * @param landRemainingYear 剩余年限
         */
        public Builder landRemainingYear(BigDecimal landRemainingYear) {
            obj.setLandRemainingYear(landRemainingYear);
            return this;
        }

        /**
         * 剩余年限说明
         * @param landRemainingYearRemark 剩余年限说明
         */
        public Builder landRemainingYearRemark(String landRemainingYearRemark) {
            obj.setLandRemainingYearRemark(landRemainingYearRemark);
            return this;
        }

        /**
         * 宗地外设定
         * @param parcelSettingOuter 宗地外设定
         */
        public Builder parcelSettingOuter(String parcelSettingOuter) {
            obj.setParcelSettingOuter(parcelSettingOuter);
            return this;
        }

        /**
         * 宗地内设定
         * @param parcelSettingInner 宗地内设定
         */
        public Builder parcelSettingInner(String parcelSettingInner) {
            obj.setParcelSettingInner(parcelSettingInner);
            return this;
        }

        /**
         * 无限年期土地使用权价格
         * @param landUsePrice 无限年期土地使用权价格
         */
        public Builder landUsePrice(BigDecimal landUsePrice) {
            obj.setLandUsePrice(landUsePrice);
            return this;
        }

        /**
         * 年期修正系数
         * @param yearFixed 年期修正系数
         */
        public Builder yearFixed(BigDecimal yearFixed) {
            obj.setYearFixed(yearFixed);
            return this;
        }

        /**
         * 委估宗地单价
         * @param parcelUnit 委估宗地单价
         */
        public Builder parcelUnit(BigDecimal parcelUnit) {
            obj.setParcelUnit(parcelUnit);
            return this;
        }

        /**
         * 土地取得成本
         * @param landCostPriceUnit 土地取得成本
         */
        public Builder landCostPriceUnit(BigDecimal landCostPriceUnit) {
            obj.setLandCostPriceUnit(landCostPriceUnit);
            return this;
        }

        /**
         * 土地取得费及相关税费
         * @param landAcquisitionUnit 土地取得费及相关税费
         */
        public Builder landAcquisitionUnit(BigDecimal landAcquisitionUnit) {
            obj.setLandAcquisitionUnit(landAcquisitionUnit);
            return this;
        }

        /**
         * 土地开发利润
         * @param landProductionProfitUnit 土地开发利润
         */
        public Builder landProductionProfitUnit(BigDecimal landProductionProfitUnit) {
            obj.setLandProductionProfitUnit(landProductionProfitUnit);
            return this;
        }

        /**
         * 土地开发利息
         * @param landProductionInterestUnit 土地开发利息
         */
        public Builder landProductionInterestUnit(BigDecimal landProductionInterestUnit) {
            obj.setLandProductionInterestUnit(landProductionInterestUnit);
            return this;
        }

        /**
         * 不含代征地每平税费
         * @param haveNotLandAcquisition 不含代征地每平税费
         */
        public Builder haveNotLandAcquisition(BigDecimal haveNotLandAcquisition) {
            obj.setHaveNotLandAcquisition(haveNotLandAcquisition);
            return this;
        }

        /**
         * 修正明细
         * @param landLevelContent 修正明细
         */
        public Builder landLevelContent(String landLevelContent) {
            obj.setLandLevelContent(landLevelContent);
            return this;
        }

        public MdCostApproach build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        processInsId("process_ins_id", "processInsId", "VARCHAR", false),
        farmlandArea("farmland_area", "farmlandArea", "DECIMAL", false),
        ploughArea("plough_area", "ploughArea", "DECIMAL", false),
        populationNumber("population_number", "populationNumber", "INTEGER", false),
        rewardRateId("reward_rate_id", "rewardRateId", "INTEGER", false),
        rewardRate("reward_rate", "rewardRate", "VARCHAR", false),
        circulationExpense("circulation_expense", "circulationExpense", "DECIMAL", false),
        circulationExpenseRemark("circulation_expense_remark", "circulationExpenseRemark", "VARCHAR", false),
        flatExpense("flat_expense", "flatExpense", "DECIMAL", false),
        flatExpenseRemark("flat_expense_remark", "flatExpenseRemark", "VARCHAR", false),
        machineCycle("machine_cycle", "machineCycle", "DECIMAL", false),
        machineCycleRemark("machine_cycle_remark", "machineCycleRemark", "VARCHAR", false),
        calculatedInterest("calculated_interest", "calculatedInterest", "VARCHAR", false),
        calculatedInterestRemark("calculated_interest_remark", "calculatedInterestRemark", "VARCHAR", false),
        profitMargin("profit_margin", "profitMargin", "VARCHAR", false),
        profitMarginRemark("profit_margin_remark", "profitMarginRemark", "VARCHAR", false),
        incrementalBenefit("incremental_benefit", "incrementalBenefit", "VARCHAR", false),
        incrementalBenefitRemark("incremental_benefit_remark", "incrementalBenefitRemark", "VARCHAR", false),
        plotRatioAdjust("plot_ratio_adjust", "plotRatioAdjust", "VARCHAR", false),
        plotRatioAdjustRemark("plot_ratio_adjust_remark", "plotRatioAdjustRemark", "VARCHAR", false),
        plotRatioElementAmend("plot_ratio_element_amend", "plotRatioElementAmend", "DECIMAL", false),
        plotRatioElementAmendRemark("plot_ratio_element_amend_remark", "plotRatioElementAmendRemark", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        landRemainingYear("land_remaining_year", "landRemainingYear", "DECIMAL", false),
        landRemainingYearRemark("land_remaining_year_remark", "landRemainingYearRemark", "VARCHAR", false),
        parcelSettingOuter("parcel_setting_outer", "parcelSettingOuter", "VARCHAR", false),
        parcelSettingInner("parcel_setting_inner", "parcelSettingInner", "VARCHAR", false),
        landUsePrice("land_use_price", "landUsePrice", "DECIMAL", false),
        yearFixed("year_fixed", "yearFixed", "DECIMAL", false),
        parcelUnit("parcel_unit", "parcelUnit", "DECIMAL", false),
        landCostPriceUnit("land_cost_price_unit", "landCostPriceUnit", "DECIMAL", false),
        landAcquisitionUnit("land_acquisition_unit", "landAcquisitionUnit", "DECIMAL", false),
        landProductionProfitUnit("land_production_profit_unit", "landProductionProfitUnit", "DECIMAL", false),
        landProductionInterestUnit("land_production_interest_unit", "landProductionInterestUnit", "DECIMAL", false),
        haveNotLandAcquisition("have_not_land_acquisition", "haveNotLandAcquisition", "DECIMAL", false),
        landLevelContent("land_level_content", "landLevelContent", "LONGVARCHAR", false);

        /**
         * tb_md_cost_approach
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_md_cost_approach
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_md_cost_approach
         */
        private final String column;

        /**
         * tb_md_cost_approach
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_md_cost_approach
         */
        private final String javaProperty;

        /**
         * tb_md_cost_approach
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