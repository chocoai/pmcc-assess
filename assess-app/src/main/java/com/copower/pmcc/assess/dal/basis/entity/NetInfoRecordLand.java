package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class NetInfoRecordLand implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 
     */
    private Integer assignTaskId;

    /**
     * 
     */
    private Integer masterId;

    /**
     * 
     */
    private String type;

    /**
     * 类别
     */
    private String category;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 土地类型
     */
    private String belongType;

    /**
     * 土地类别
     */
    private String belongCategory;

    /**
     * 土地性质
     */
    private String landPurpose;

    /**
     * 街道
     */
    private String street;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 单位（平方米、亩）
     */
    private String areaUnit;

    /**
     * 地块名称
     */
    private String name;

    /**
     * 宗地编号
     */
    private String parcelNumber;

    /**
     * 宗地位置
     */
    private String parcelSite;

    /**
     * 交易方式
     */
    private Integer dealType;

    /**
     * 成交价
     */
    private BigDecimal currentPrice;

    /**
     * 成交(协商)日期
     */
    private Date negotiatedDate;

    /**
     * 评估起拍单价(元/每平米)
     */
    private BigDecimal consultPrice;

    /**
     * 评估起拍单价(万元/每亩)
     */
    private BigDecimal consultPriceMu;

    /**
     * 评估基准日
     */
    private Date assessStandardDate;

    /**
     * 成交单价（元/每平米）
     */
    private BigDecimal unitPrice;

    /**
     * 成交单价（万元/每亩）
     */
    private BigDecimal unitPriceMu;

    /**
     * 楼面地价
     */
    private BigDecimal floorPrice;

    /**
     * 变现率
     */
    private BigDecimal landRealizationRatios;

    /**
     * 变现周期
     */
    private String realizationCycle;

    /**
     * 净用地面积
     */
    private BigDecimal landArea;

    /**
     * 净用地面积单位（平方米、亩）
     */
    private String landAreaUnit;

    /**
     * 容积率
     */
    private BigDecimal plotRatio;

    /**
     * 容积率说明
     */
    private String plotRatioRemark;

    /**
     * 绿化率
     */
    private BigDecimal greeningRate;

    /**
     * 绿化率说明
     */
    private String greeningRateRemark;

    /**
     * 建筑密度
     */
    private String buildDensity;

    /**
     * 建筑密度说明
     */
    private String buildDensityRemark;

    /**
     * 建筑高度
     */
    private BigDecimal buildHeight;

    /**
     * 建筑高度说明
     */
    private String buildHeightRemark;

    /**
     * 指标款(亩)
     */
    private BigDecimal indexAmount;

    /**
     * 指标款说明
     */
    private String indexAmountRemark;

    /**
     * 成交对象概况
     */
    private String dealPartInfo;

    /**
     * 1审批通过 2审批中 
     */
    private Integer status;

    /**
     * 审批人
     */
    private String approver;

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
     * 是否最新版本
     */
    private Boolean bisNewest;

    /**
     * 被升级数据id
     */
    private Integer beUpgradeId;

    /**
     * 
     */
    private Integer version;

    /**
     * tb_net_info_record_land
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     * @return id 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return assign_task_id 
     */
    public Integer getAssignTaskId() {
        return assignTaskId;
    }

    /**
     * 
     * @param assignTaskId 
     */
    public void setAssignTaskId(Integer assignTaskId) {
        this.assignTaskId = assignTaskId;
    }

    /**
     * 
     * @return master_id 
     */
    public Integer getMasterId() {
        return masterId;
    }

    /**
     * 
     * @param masterId 
     */
    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    /**
     * 
     * @return type 
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type 
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 类别
     * @return category 类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 类别
     * @param category 类别
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
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
     * 区
     * @return district 区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 区
     * @param district 区
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 土地类型
     * @return belong_type 土地类型
     */
    public String getBelongType() {
        return belongType;
    }

    /**
     * 土地类型
     * @param belongType 土地类型
     */
    public void setBelongType(String belongType) {
        this.belongType = belongType == null ? null : belongType.trim();
    }

    /**
     * 土地类别
     * @return belong_category 土地类别
     */
    public String getBelongCategory() {
        return belongCategory;
    }

    /**
     * 土地类别
     * @param belongCategory 土地类别
     */
    public void setBelongCategory(String belongCategory) {
        this.belongCategory = belongCategory == null ? null : belongCategory.trim();
    }

    /**
     * 土地性质
     * @return land_purpose 土地性质
     */
    public String getLandPurpose() {
        return landPurpose;
    }

    /**
     * 土地性质
     * @param landPurpose 土地性质
     */
    public void setLandPurpose(String landPurpose) {
        this.landPurpose = landPurpose == null ? null : landPurpose.trim();
    }

    /**
     * 街道
     * @return street 街道
     */
    public String getStreet() {
        return street;
    }

    /**
     * 街道
     * @param street 街道
     */
    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    /**
     * 面积
     * @return area 面积
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * 面积
     * @param area 面积
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * 单位（平方米、亩）
     * @return area_unit 单位（平方米、亩）
     */
    public String getAreaUnit() {
        return areaUnit;
    }

    /**
     * 单位（平方米、亩）
     * @param areaUnit 单位（平方米、亩）
     */
    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit == null ? null : areaUnit.trim();
    }

    /**
     * 地块名称
     * @return name 地块名称
     */
    public String getName() {
        return name;
    }

    /**
     * 地块名称
     * @param name 地块名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 宗地编号
     * @return parcel_number 宗地编号
     */
    public String getParcelNumber() {
        return parcelNumber;
    }

    /**
     * 宗地编号
     * @param parcelNumber 宗地编号
     */
    public void setParcelNumber(String parcelNumber) {
        this.parcelNumber = parcelNumber == null ? null : parcelNumber.trim();
    }

    /**
     * 宗地位置
     * @return parcel_site 宗地位置
     */
    public String getParcelSite() {
        return parcelSite;
    }

    /**
     * 宗地位置
     * @param parcelSite 宗地位置
     */
    public void setParcelSite(String parcelSite) {
        this.parcelSite = parcelSite == null ? null : parcelSite.trim();
    }

    /**
     * 交易方式
     * @return deal_type 交易方式
     */
    public Integer getDealType() {
        return dealType;
    }

    /**
     * 交易方式
     * @param dealType 交易方式
     */
    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    /**
     * 成交价
     * @return current_price 成交价
     */
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    /**
     * 成交价
     * @param currentPrice 成交价
     */
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * 成交(协商)日期
     * @return negotiated_date 成交(协商)日期
     */
    public Date getNegotiatedDate() {
        return negotiatedDate;
    }

    /**
     * 成交(协商)日期
     * @param negotiatedDate 成交(协商)日期
     */
    public void setNegotiatedDate(Date negotiatedDate) {
        this.negotiatedDate = negotiatedDate;
    }

    /**
     * 评估起拍单价(元/每平米)
     * @return consult_price 评估起拍单价(元/每平米)
     */
    public BigDecimal getConsultPrice() {
        return consultPrice;
    }

    /**
     * 评估起拍单价(元/每平米)
     * @param consultPrice 评估起拍单价(元/每平米)
     */
    public void setConsultPrice(BigDecimal consultPrice) {
        this.consultPrice = consultPrice;
    }

    /**
     * 评估起拍单价(万元/每亩)
     * @return consult_price_mu 评估起拍单价(万元/每亩)
     */
    public BigDecimal getConsultPriceMu() {
        return consultPriceMu;
    }

    /**
     * 评估起拍单价(万元/每亩)
     * @param consultPriceMu 评估起拍单价(万元/每亩)
     */
    public void setConsultPriceMu(BigDecimal consultPriceMu) {
        this.consultPriceMu = consultPriceMu;
    }

    /**
     * 评估基准日
     * @return assess_standard_date 评估基准日
     */
    public Date getAssessStandardDate() {
        return assessStandardDate;
    }

    /**
     * 评估基准日
     * @param assessStandardDate 评估基准日
     */
    public void setAssessStandardDate(Date assessStandardDate) {
        this.assessStandardDate = assessStandardDate;
    }

    /**
     * 成交单价（元/每平米）
     * @return unit_price 成交单价（元/每平米）
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 成交单价（元/每平米）
     * @param unitPrice 成交单价（元/每平米）
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 成交单价（万元/每亩）
     * @return unit_price_mu 成交单价（万元/每亩）
     */
    public BigDecimal getUnitPriceMu() {
        return unitPriceMu;
    }

    /**
     * 成交单价（万元/每亩）
     * @param unitPriceMu 成交单价（万元/每亩）
     */
    public void setUnitPriceMu(BigDecimal unitPriceMu) {
        this.unitPriceMu = unitPriceMu;
    }

    /**
     * 楼面地价
     * @return floor_price 楼面地价
     */
    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    /**
     * 楼面地价
     * @param floorPrice 楼面地价
     */
    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    /**
     * 变现率
     * @return land_realization_ratios 变现率
     */
    public BigDecimal getLandRealizationRatios() {
        return landRealizationRatios;
    }

    /**
     * 变现率
     * @param landRealizationRatios 变现率
     */
    public void setLandRealizationRatios(BigDecimal landRealizationRatios) {
        this.landRealizationRatios = landRealizationRatios;
    }

    /**
     * 变现周期
     * @return realization_cycle 变现周期
     */
    public String getRealizationCycle() {
        return realizationCycle;
    }

    /**
     * 变现周期
     * @param realizationCycle 变现周期
     */
    public void setRealizationCycle(String realizationCycle) {
        this.realizationCycle = realizationCycle == null ? null : realizationCycle.trim();
    }

    /**
     * 净用地面积
     * @return land_area 净用地面积
     */
    public BigDecimal getLandArea() {
        return landArea;
    }

    /**
     * 净用地面积
     * @param landArea 净用地面积
     */
    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    /**
     * 净用地面积单位（平方米、亩）
     * @return land_area_unit 净用地面积单位（平方米、亩）
     */
    public String getLandAreaUnit() {
        return landAreaUnit;
    }

    /**
     * 净用地面积单位（平方米、亩）
     * @param landAreaUnit 净用地面积单位（平方米、亩）
     */
    public void setLandAreaUnit(String landAreaUnit) {
        this.landAreaUnit = landAreaUnit == null ? null : landAreaUnit.trim();
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
     * 绿化率
     * @return greening_rate 绿化率
     */
    public BigDecimal getGreeningRate() {
        return greeningRate;
    }

    /**
     * 绿化率
     * @param greeningRate 绿化率
     */
    public void setGreeningRate(BigDecimal greeningRate) {
        this.greeningRate = greeningRate;
    }

    /**
     * 绿化率说明
     * @return greening_rate_remark 绿化率说明
     */
    public String getGreeningRateRemark() {
        return greeningRateRemark;
    }

    /**
     * 绿化率说明
     * @param greeningRateRemark 绿化率说明
     */
    public void setGreeningRateRemark(String greeningRateRemark) {
        this.greeningRateRemark = greeningRateRemark == null ? null : greeningRateRemark.trim();
    }

    /**
     * 建筑密度
     * @return build_density 建筑密度
     */
    public String getBuildDensity() {
        return buildDensity;
    }

    /**
     * 建筑密度
     * @param buildDensity 建筑密度
     */
    public void setBuildDensity(String buildDensity) {
        this.buildDensity = buildDensity == null ? null : buildDensity.trim();
    }

    /**
     * 建筑密度说明
     * @return build_density_remark 建筑密度说明
     */
    public String getBuildDensityRemark() {
        return buildDensityRemark;
    }

    /**
     * 建筑密度说明
     * @param buildDensityRemark 建筑密度说明
     */
    public void setBuildDensityRemark(String buildDensityRemark) {
        this.buildDensityRemark = buildDensityRemark == null ? null : buildDensityRemark.trim();
    }

    /**
     * 建筑高度
     * @return build_height 建筑高度
     */
    public BigDecimal getBuildHeight() {
        return buildHeight;
    }

    /**
     * 建筑高度
     * @param buildHeight 建筑高度
     */
    public void setBuildHeight(BigDecimal buildHeight) {
        this.buildHeight = buildHeight;
    }

    /**
     * 建筑高度说明
     * @return build_height_remark 建筑高度说明
     */
    public String getBuildHeightRemark() {
        return buildHeightRemark;
    }

    /**
     * 建筑高度说明
     * @param buildHeightRemark 建筑高度说明
     */
    public void setBuildHeightRemark(String buildHeightRemark) {
        this.buildHeightRemark = buildHeightRemark == null ? null : buildHeightRemark.trim();
    }

    /**
     * 指标款(亩)
     * @return index_amount 指标款(亩)
     */
    public BigDecimal getIndexAmount() {
        return indexAmount;
    }

    /**
     * 指标款(亩)
     * @param indexAmount 指标款(亩)
     */
    public void setIndexAmount(BigDecimal indexAmount) {
        this.indexAmount = indexAmount;
    }

    /**
     * 指标款说明
     * @return index_amount_remark 指标款说明
     */
    public String getIndexAmountRemark() {
        return indexAmountRemark;
    }

    /**
     * 指标款说明
     * @param indexAmountRemark 指标款说明
     */
    public void setIndexAmountRemark(String indexAmountRemark) {
        this.indexAmountRemark = indexAmountRemark == null ? null : indexAmountRemark.trim();
    }

    /**
     * 成交对象概况
     * @return deal_part_info 成交对象概况
     */
    public String getDealPartInfo() {
        return dealPartInfo;
    }

    /**
     * 成交对象概况
     * @param dealPartInfo 成交对象概况
     */
    public void setDealPartInfo(String dealPartInfo) {
        this.dealPartInfo = dealPartInfo == null ? null : dealPartInfo.trim();
    }

    /**
     * 1审批通过 2审批中 
     * @return status 1审批通过 2审批中 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 1审批通过 2审批中 
     * @param status 1审批通过 2审批中 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 审批人
     * @return approver 审批人
     */
    public String getApprover() {
        return approver;
    }

    /**
     * 审批人
     * @param approver 审批人
     */
    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
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
     * 是否最新版本
     * @return bis_newest 是否最新版本
     */
    public Boolean getBisNewest() {
        return bisNewest;
    }

    /**
     * 是否最新版本
     * @param bisNewest 是否最新版本
     */
    public void setBisNewest(Boolean bisNewest) {
        this.bisNewest = bisNewest;
    }

    /**
     * 被升级数据id
     * @return be_upgrade_id 被升级数据id
     */
    public Integer getBeUpgradeId() {
        return beUpgradeId;
    }

    /**
     * 被升级数据id
     * @param beUpgradeId 被升级数据id
     */
    public void setBeUpgradeId(Integer beUpgradeId) {
        this.beUpgradeId = beUpgradeId;
    }

    /**
     * 
     * @return version 
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 
     * @param version 
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    public static NetInfoRecordLand.Builder builder() {
        return new NetInfoRecordLand.Builder();
    }

    /**
     * tb_net_info_record_land
     */
    public static class Builder {
        /**
         * tb_net_info_record_land
         */
        private NetInfoRecordLand obj;

        public Builder() {
            this.obj = new NetInfoRecordLand();
        }

        /**
         * 主键id
         * @param id 主键id
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 
         * @param assignTaskId 
         */
        public Builder assignTaskId(Integer assignTaskId) {
            obj.setAssignTaskId(assignTaskId);
            return this;
        }

        /**
         * 
         * @param masterId 
         */
        public Builder masterId(Integer masterId) {
            obj.setMasterId(masterId);
            return this;
        }

        /**
         * 
         * @param type 
         */
        public Builder type(String type) {
            obj.setType(type);
            return this;
        }

        /**
         * 类别
         * @param category 类别
         */
        public Builder category(String category) {
            obj.setCategory(category);
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
         * 区
         * @param district 区
         */
        public Builder district(String district) {
            obj.setDistrict(district);
            return this;
        }

        /**
         * 土地类型
         * @param belongType 土地类型
         */
        public Builder belongType(String belongType) {
            obj.setBelongType(belongType);
            return this;
        }

        /**
         * 土地类别
         * @param belongCategory 土地类别
         */
        public Builder belongCategory(String belongCategory) {
            obj.setBelongCategory(belongCategory);
            return this;
        }

        /**
         * 土地性质
         * @param landPurpose 土地性质
         */
        public Builder landPurpose(String landPurpose) {
            obj.setLandPurpose(landPurpose);
            return this;
        }

        /**
         * 街道
         * @param street 街道
         */
        public Builder street(String street) {
            obj.setStreet(street);
            return this;
        }

        /**
         * 面积
         * @param area 面积
         */
        public Builder area(BigDecimal area) {
            obj.setArea(area);
            return this;
        }

        /**
         * 单位（平方米、亩）
         * @param areaUnit 单位（平方米、亩）
         */
        public Builder areaUnit(String areaUnit) {
            obj.setAreaUnit(areaUnit);
            return this;
        }

        /**
         * 地块名称
         * @param name 地块名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 宗地编号
         * @param parcelNumber 宗地编号
         */
        public Builder parcelNumber(String parcelNumber) {
            obj.setParcelNumber(parcelNumber);
            return this;
        }

        /**
         * 宗地位置
         * @param parcelSite 宗地位置
         */
        public Builder parcelSite(String parcelSite) {
            obj.setParcelSite(parcelSite);
            return this;
        }

        /**
         * 交易方式
         * @param dealType 交易方式
         */
        public Builder dealType(Integer dealType) {
            obj.setDealType(dealType);
            return this;
        }

        /**
         * 成交价
         * @param currentPrice 成交价
         */
        public Builder currentPrice(BigDecimal currentPrice) {
            obj.setCurrentPrice(currentPrice);
            return this;
        }

        /**
         * 成交(协商)日期
         * @param negotiatedDate 成交(协商)日期
         */
        public Builder negotiatedDate(Date negotiatedDate) {
            obj.setNegotiatedDate(negotiatedDate);
            return this;
        }

        /**
         * 评估起拍单价(元/每平米)
         * @param consultPrice 评估起拍单价(元/每平米)
         */
        public Builder consultPrice(BigDecimal consultPrice) {
            obj.setConsultPrice(consultPrice);
            return this;
        }

        /**
         * 评估起拍单价(万元/每亩)
         * @param consultPriceMu 评估起拍单价(万元/每亩)
         */
        public Builder consultPriceMu(BigDecimal consultPriceMu) {
            obj.setConsultPriceMu(consultPriceMu);
            return this;
        }

        /**
         * 评估基准日
         * @param assessStandardDate 评估基准日
         */
        public Builder assessStandardDate(Date assessStandardDate) {
            obj.setAssessStandardDate(assessStandardDate);
            return this;
        }

        /**
         * 成交单价（元/每平米）
         * @param unitPrice 成交单价（元/每平米）
         */
        public Builder unitPrice(BigDecimal unitPrice) {
            obj.setUnitPrice(unitPrice);
            return this;
        }

        /**
         * 成交单价（万元/每亩）
         * @param unitPriceMu 成交单价（万元/每亩）
         */
        public Builder unitPriceMu(BigDecimal unitPriceMu) {
            obj.setUnitPriceMu(unitPriceMu);
            return this;
        }

        /**
         * 楼面地价
         * @param floorPrice 楼面地价
         */
        public Builder floorPrice(BigDecimal floorPrice) {
            obj.setFloorPrice(floorPrice);
            return this;
        }

        /**
         * 变现率
         * @param landRealizationRatios 变现率
         */
        public Builder landRealizationRatios(BigDecimal landRealizationRatios) {
            obj.setLandRealizationRatios(landRealizationRatios);
            return this;
        }

        /**
         * 变现周期
         * @param realizationCycle 变现周期
         */
        public Builder realizationCycle(String realizationCycle) {
            obj.setRealizationCycle(realizationCycle);
            return this;
        }

        /**
         * 净用地面积
         * @param landArea 净用地面积
         */
        public Builder landArea(BigDecimal landArea) {
            obj.setLandArea(landArea);
            return this;
        }

        /**
         * 净用地面积单位（平方米、亩）
         * @param landAreaUnit 净用地面积单位（平方米、亩）
         */
        public Builder landAreaUnit(String landAreaUnit) {
            obj.setLandAreaUnit(landAreaUnit);
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
         * 容积率说明
         * @param plotRatioRemark 容积率说明
         */
        public Builder plotRatioRemark(String plotRatioRemark) {
            obj.setPlotRatioRemark(plotRatioRemark);
            return this;
        }

        /**
         * 绿化率
         * @param greeningRate 绿化率
         */
        public Builder greeningRate(BigDecimal greeningRate) {
            obj.setGreeningRate(greeningRate);
            return this;
        }

        /**
         * 绿化率说明
         * @param greeningRateRemark 绿化率说明
         */
        public Builder greeningRateRemark(String greeningRateRemark) {
            obj.setGreeningRateRemark(greeningRateRemark);
            return this;
        }

        public NetInfoRecordLand build() {
            return this.obj;
        }

        /**
         * 建筑密度
         * @param buildDensity 建筑密度
         */
        public Builder buildDensity(String buildDensity) {
            obj.setBuildDensity(buildDensity);
            return this;
        }

        /**
         * 建筑密度说明
         * @param buildDensityRemark 建筑密度说明
         */
        public Builder buildDensityRemark(String buildDensityRemark) {
            obj.setBuildDensityRemark(buildDensityRemark);
            return this;
        }

        /**
         * 建筑高度
         * @param buildHeight 建筑高度
         */
        public Builder buildHeight(BigDecimal buildHeight) {
            obj.setBuildHeight(buildHeight);
            return this;
        }

        /**
         * 建筑高度说明
         * @param buildHeightRemark 建筑高度说明
         */
        public Builder buildHeightRemark(String buildHeightRemark) {
            obj.setBuildHeightRemark(buildHeightRemark);
            return this;
        }

        /**
         * 指标款(亩)
         * @param indexAmount 指标款(亩)
         */
        public Builder indexAmount(BigDecimal indexAmount) {
            obj.setIndexAmount(indexAmount);
            return this;
        }

        /**
         * 指标款说明
         * @param indexAmountRemark 指标款说明
         */
        public Builder indexAmountRemark(String indexAmountRemark) {
            obj.setIndexAmountRemark(indexAmountRemark);
            return this;
        }

        /**
         * 成交对象概况
         * @param dealPartInfo 成交对象概况
         */
        public Builder dealPartInfo(String dealPartInfo) {
            obj.setDealPartInfo(dealPartInfo);
            return this;
        }

        /**
         * 1审批通过 2审批中 
         * @param status 1审批通过 2审批中 
         */
        public Builder status(Integer status) {
            obj.setStatus(status);
            return this;
        }

        /**
         * 审批人
         * @param approver 审批人
         */
        public Builder approver(String approver) {
            obj.setApprover(approver);
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
         * 是否最新版本
         * @param bisNewest 是否最新版本
         */
        public Builder bisNewest(Boolean bisNewest) {
            obj.setBisNewest(bisNewest);
            return this;
        }

        /**
         * 被升级数据id
         * @param beUpgradeId 被升级数据id
         */
        public Builder beUpgradeId(Integer beUpgradeId) {
            obj.setBeUpgradeId(beUpgradeId);
            return this;
        }

        /**
         * 
         * @param version 
         */
        public Builder version(Integer version) {
            obj.setVersion(version);
            return this;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        assignTaskId("assign_task_id", "assignTaskId", "INTEGER", false),
        masterId("master_id", "masterId", "INTEGER", false),
        type("type", "type", "VARCHAR", false),
        category("category", "category", "VARCHAR", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        belongType("belong_type", "belongType", "VARCHAR", false),
        belongCategory("belong_category", "belongCategory", "VARCHAR", false),
        landPurpose("land_purpose", "landPurpose", "VARCHAR", false),
        street("street", "street", "VARCHAR", false),
        area("area", "area", "DECIMAL", false),
        areaUnit("area_unit", "areaUnit", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        parcelNumber("parcel_number", "parcelNumber", "VARCHAR", false),
        parcelSite("parcel_site", "parcelSite", "VARCHAR", false),
        dealType("deal_type", "dealType", "INTEGER", false),
        currentPrice("current_price", "currentPrice", "DECIMAL", false),
        negotiatedDate("negotiated_date", "negotiatedDate", "TIMESTAMP", false),
        consultPrice("consult_price", "consultPrice", "DECIMAL", false),
        consultPriceMu("consult_price_mu", "consultPriceMu", "DECIMAL", false),
        assessStandardDate("assess_standard_date", "assessStandardDate", "TIMESTAMP", false),
        unitPrice("unit_price", "unitPrice", "DECIMAL", false),
        unitPriceMu("unit_price_mu", "unitPriceMu", "DECIMAL", false),
        floorPrice("floor_price", "floorPrice", "DECIMAL", false),
        landRealizationRatios("land_realization_ratios", "landRealizationRatios", "DECIMAL", false),
        realizationCycle("realization_cycle", "realizationCycle", "VARCHAR", false),
        landArea("land_area", "landArea", "DECIMAL", false),
        landAreaUnit("land_area_unit", "landAreaUnit", "VARCHAR", false),
        plotRatio("plot_ratio", "plotRatio", "DECIMAL", false),
        plotRatioRemark("plot_ratio_remark", "plotRatioRemark", "VARCHAR", false),
        greeningRate("greening_rate", "greeningRate", "DECIMAL", false),
        greeningRateRemark("greening_rate_remark", "greeningRateRemark", "VARCHAR", false),
        buildDensity("build_density", "buildDensity", "VARCHAR", false),
        buildDensityRemark("build_density_remark", "buildDensityRemark", "VARCHAR", false),
        buildHeight("build_height", "buildHeight", "DECIMAL", false),
        buildHeightRemark("build_height_remark", "buildHeightRemark", "VARCHAR", false),
        indexAmount("index_amount", "indexAmount", "DECIMAL", false),
        indexAmountRemark("index_amount_remark", "indexAmountRemark", "VARCHAR", false),
        dealPartInfo("deal_part_info", "dealPartInfo", "VARCHAR", false),
        status("status", "status", "INTEGER", false),
        approver("approver", "approver", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        bisNewest("bis_newest", "bisNewest", "BIT", false),
        beUpgradeId("be_upgrade_id", "beUpgradeId", "INTEGER", false),
        version("version", "version", "INTEGER", false);

        /**
         * tb_net_info_record_land
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_net_info_record_land
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_net_info_record_land
         */
        private final String column;

        /**
         * tb_net_info_record_land
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_net_info_record_land
         */
        private final String javaProperty;

        /**
         * tb_net_info_record_land
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