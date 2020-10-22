package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicEstate implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer applyId;

    /**
     * 引用案例数据id
     */
    private Integer quoteId;

    /**
     * 大类
     */
    private Integer classify;

    /**
     * 类型 0非工业仓储 1工业仓储
     */
    private Integer type;

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
     * 
     */
    private Integer blockId;

    /**
     * 版块名称
     */
    private String blockName;

    /**
     * 基础版块描述
     */
    private String blockDescription;

    /**
     * 开发商名称
     */
    private String developer;

    /**
     * 开发商名称
     */
    private String developerName;

    /**
     * 名称
     */
    private String name;

    /**
     * 街道号
     */
    private String streetNumber;

    /**
     * 街道
     */
    private String street;

    /**
     * 编号
     */
    private String number;

    /**
     * 土地级别
     */
    private Integer landLevel;

    /**
     * 附号
     */
    private String attachNumber;

    /**
     * 建筑面积
     */
    private BigDecimal floorArea;

    /**
     * 占地面积
     */
    private BigDecimal coverAnArea;

    /**
     * 容积率
     */
    private String volumetricRate;

    /**
     * 绿化率
     */
    private String greeningRate;

    /**
     * 开盘时间
     */
    private Date openTime;

    /**
     * 总栋数 
     */
    private Integer buildingNumber;

    /**
     * 方位
     */
    private Integer position;

    /**
     * 楼盘概况
     */
    private String description;

    /**
     * 均价
     */
    private String averagePrice;

    /**
     * 价格区间
     */
    private String priceRange;

    /**
     * 供热信息 有无
     */
    private Integer supplyHeating;

    /**
     * 供电信息 有无
     */
    private Integer supplyPower;

    /**
     * 通讯信息 有无
     */
    private Integer supplyCommunication;

    /**
     * 道路信息 有无
     */
    private Integer supplyRoad;

    /**
     * 供排水情况 有无
     */
    private Integer supplyWater;

    /**
     * 排水
     */
    private Integer drainWater;

    /**
     * 供气信息 有无
     */
    private Integer supplyGas;

    /**
     * 基础设施情况
     */
    private String infrastructure;

    /**
     * 基础设施完备度
     */
    private Integer infrastructureCompleteness;

    /**
     * 区位描述
     */
    private String locationDescribe;

    /**
     * 查看案例详情id
     */
    private Integer displayCaseId;

    /**
     * map id
     */
    private Integer mapId;

    /**
     * 属于某id的旧数据
     */
    private Integer relevanceId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 是否为案例
     */
    private Boolean bisCase;

    /**
     * 均价单位
     */
    private String averagePriceUnit;

    /**
     * 价格区间单位
     */
    private String priceRangeUnit;

    /**
     * 占地面积单位
     */
    private String coverAnAreaUnit;

    /**
     * 土地取得方式
     */
    private String acquisitionType;

    /**
     * 土地权利性质
     */
    private String landRightNature;

    /**
     * 土地权利类型
     */
    private String landRightType;

    /**
     * 地块编号
     */
    private String landPieceNumbering;

    /**
     * 区域规划
     */
    private String regionalPlanning;

    /**
     * 
     */
    private Boolean bisDelete;

    /**
     * 是否有效
     */
    private Boolean bisEnable;

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
     * tb_basic_estate
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
     * 引用案例数据id
     * @return quote_id 引用案例数据id
     */
    public Integer getQuoteId() {
        return quoteId;
    }

    /**
     * 引用案例数据id
     * @param quoteId 引用案例数据id
     */
    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * 大类
     * @return classify 大类
     */
    public Integer getClassify() {
        return classify;
    }

    /**
     * 大类
     * @param classify 大类
     */
    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    /**
     * 类型 0非工业仓储 1工业仓储
     * @return type 类型 0非工业仓储 1工业仓储
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型 0非工业仓储 1工业仓储
     * @param type 类型 0非工业仓储 1工业仓储
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 
     * @return block_id 
     */
    public Integer getBlockId() {
        return blockId;
    }

    /**
     * 
     * @param blockId 
     */
    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    /**
     * 版块名称
     * @return block_name 版块名称
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * 版块名称
     * @param blockName 版块名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    /**
     * 基础版块描述
     * @return block_description 基础版块描述
     */
    public String getBlockDescription() {
        return blockDescription;
    }

    /**
     * 基础版块描述
     * @param blockDescription 基础版块描述
     */
    public void setBlockDescription(String blockDescription) {
        this.blockDescription = blockDescription == null ? null : blockDescription.trim();
    }

    /**
     * 开发商名称
     * @return developer 开发商名称
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * 开发商名称
     * @param developer 开发商名称
     */
    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    /**
     * 开发商名称
     * @return developer_name 开发商名称
     */
    public String getDeveloperName() {
        return developerName;
    }

    /**
     * 开发商名称
     * @param developerName 开发商名称
     */
    public void setDeveloperName(String developerName) {
        this.developerName = developerName == null ? null : developerName.trim();
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
     * 街道号
     * @return street_number 街道号
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * 街道号
     * @param streetNumber 街道号
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber == null ? null : streetNumber.trim();
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
     * 编号
     * @return number 编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 编号
     * @param number 编号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
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
     * 附号
     * @return attach_number 附号
     */
    public String getAttachNumber() {
        return attachNumber;
    }

    /**
     * 附号
     * @param attachNumber 附号
     */
    public void setAttachNumber(String attachNumber) {
        this.attachNumber = attachNumber == null ? null : attachNumber.trim();
    }

    /**
     * 建筑面积
     * @return floor_area 建筑面积
     */
    public BigDecimal getFloorArea() {
        return floorArea;
    }

    /**
     * 建筑面积
     * @param floorArea 建筑面积
     */
    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * 占地面积
     * @return cover_an_area 占地面积
     */
    public BigDecimal getCoverAnArea() {
        return coverAnArea;
    }

    /**
     * 占地面积
     * @param coverAnArea 占地面积
     */
    public void setCoverAnArea(BigDecimal coverAnArea) {
        this.coverAnArea = coverAnArea;
    }

    /**
     * 容积率
     * @return volumetric_rate 容积率
     */
    public String getVolumetricRate() {
        return volumetricRate;
    }

    /**
     * 容积率
     * @param volumetricRate 容积率
     */
    public void setVolumetricRate(String volumetricRate) {
        this.volumetricRate = volumetricRate == null ? null : volumetricRate.trim();
    }

    /**
     * 绿化率
     * @return greening_rate 绿化率
     */
    public String getGreeningRate() {
        return greeningRate;
    }

    /**
     * 绿化率
     * @param greeningRate 绿化率
     */
    public void setGreeningRate(String greeningRate) {
        this.greeningRate = greeningRate == null ? null : greeningRate.trim();
    }

    /**
     * 开盘时间
     * @return open_time 开盘时间
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * 开盘时间
     * @param openTime 开盘时间
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    /**
     * 总栋数 
     * @return building_number 总栋数 
     */
    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * 总栋数 
     * @param buildingNumber 总栋数 
     */
    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * 方位
     * @return position 方位
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 方位
     * @param position 方位
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 楼盘概况
     * @return description 楼盘概况
     */
    public String getDescription() {
        return description;
    }

    /**
     * 楼盘概况
     * @param description 楼盘概况
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 均价
     * @return average_price 均价
     */
    public String getAveragePrice() {
        return averagePrice;
    }

    /**
     * 均价
     * @param averagePrice 均价
     */
    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice == null ? null : averagePrice.trim();
    }

    /**
     * 价格区间
     * @return price_range 价格区间
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * 价格区间
     * @param priceRange 价格区间
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange == null ? null : priceRange.trim();
    }

    /**
     * 供热信息 有无
     * @return supply_heating 供热信息 有无
     */
    public Integer getSupplyHeating() {
        return supplyHeating;
    }

    /**
     * 供热信息 有无
     * @param supplyHeating 供热信息 有无
     */
    public void setSupplyHeating(Integer supplyHeating) {
        this.supplyHeating = supplyHeating;
    }

    /**
     * 供电信息 有无
     * @return supply_power 供电信息 有无
     */
    public Integer getSupplyPower() {
        return supplyPower;
    }

    /**
     * 供电信息 有无
     * @param supplyPower 供电信息 有无
     */
    public void setSupplyPower(Integer supplyPower) {
        this.supplyPower = supplyPower;
    }

    /**
     * 通讯信息 有无
     * @return supply_communication 通讯信息 有无
     */
    public Integer getSupplyCommunication() {
        return supplyCommunication;
    }

    /**
     * 通讯信息 有无
     * @param supplyCommunication 通讯信息 有无
     */
    public void setSupplyCommunication(Integer supplyCommunication) {
        this.supplyCommunication = supplyCommunication;
    }

    /**
     * 道路信息 有无
     * @return supply_road 道路信息 有无
     */
    public Integer getSupplyRoad() {
        return supplyRoad;
    }

    /**
     * 道路信息 有无
     * @param supplyRoad 道路信息 有无
     */
    public void setSupplyRoad(Integer supplyRoad) {
        this.supplyRoad = supplyRoad;
    }

    /**
     * 供排水情况 有无
     * @return supply_water 供排水情况 有无
     */
    public Integer getSupplyWater() {
        return supplyWater;
    }

    /**
     * 供排水情况 有无
     * @param supplyWater 供排水情况 有无
     */
    public void setSupplyWater(Integer supplyWater) {
        this.supplyWater = supplyWater;
    }

    /**
     * 排水
     * @return drain_water 排水
     */
    public Integer getDrainWater() {
        return drainWater;
    }

    /**
     * 排水
     * @param drainWater 排水
     */
    public void setDrainWater(Integer drainWater) {
        this.drainWater = drainWater;
    }

    /**
     * 供气信息 有无
     * @return supply_gas 供气信息 有无
     */
    public Integer getSupplyGas() {
        return supplyGas;
    }

    /**
     * 供气信息 有无
     * @param supplyGas 供气信息 有无
     */
    public void setSupplyGas(Integer supplyGas) {
        this.supplyGas = supplyGas;
    }

    /**
     * 基础设施情况
     * @return infrastructure 基础设施情况
     */
    public String getInfrastructure() {
        return infrastructure;
    }

    /**
     * 基础设施情况
     * @param infrastructure 基础设施情况
     */
    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure == null ? null : infrastructure.trim();
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
     * 区位描述
     * @return location_describe 区位描述
     */
    public String getLocationDescribe() {
        return locationDescribe;
    }

    /**
     * 区位描述
     * @param locationDescribe 区位描述
     */
    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe == null ? null : locationDescribe.trim();
    }

    /**
     * 查看案例详情id
     * @return display_case_id 查看案例详情id
     */
    public Integer getDisplayCaseId() {
        return displayCaseId;
    }

    /**
     * 查看案例详情id
     * @param displayCaseId 查看案例详情id
     */
    public void setDisplayCaseId(Integer displayCaseId) {
        this.displayCaseId = displayCaseId;
    }

    /**
     * map id
     * @return map_id map id
     */
    public Integer getMapId() {
        return mapId;
    }

    /**
     * map id
     * @param mapId map id
     */
    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    /**
     * 属于某id的旧数据
     * @return relevance_id 属于某id的旧数据
     */
    public Integer getRelevanceId() {
        return relevanceId;
    }

    /**
     * 属于某id的旧数据
     * @param relevanceId 属于某id的旧数据
     */
    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    /**
     * 版本号
     * @return version 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 版本号
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 是否为案例
     * @return bis_case 是否为案例
     */
    public Boolean getBisCase() {
        return bisCase;
    }

    /**
     * 是否为案例
     * @param bisCase 是否为案例
     */
    public void setBisCase(Boolean bisCase) {
        this.bisCase = bisCase;
    }

    /**
     * 均价单位
     * @return average_price_unit 均价单位
     */
    public String getAveragePriceUnit() {
        return averagePriceUnit;
    }

    /**
     * 均价单位
     * @param averagePriceUnit 均价单位
     */
    public void setAveragePriceUnit(String averagePriceUnit) {
        this.averagePriceUnit = averagePriceUnit == null ? null : averagePriceUnit.trim();
    }

    /**
     * 价格区间单位
     * @return price_range_unit 价格区间单位
     */
    public String getPriceRangeUnit() {
        return priceRangeUnit;
    }

    /**
     * 价格区间单位
     * @param priceRangeUnit 价格区间单位
     */
    public void setPriceRangeUnit(String priceRangeUnit) {
        this.priceRangeUnit = priceRangeUnit == null ? null : priceRangeUnit.trim();
    }

    /**
     * 占地面积单位
     * @return cover_an_area_unit 占地面积单位
     */
    public String getCoverAnAreaUnit() {
        return coverAnAreaUnit;
    }

    /**
     * 占地面积单位
     * @param coverAnAreaUnit 占地面积单位
     */
    public void setCoverAnAreaUnit(String coverAnAreaUnit) {
        this.coverAnAreaUnit = coverAnAreaUnit == null ? null : coverAnAreaUnit.trim();
    }

    /**
     * 土地取得方式
     * @return acquisition_type 土地取得方式
     */
    public String getAcquisitionType() {
        return acquisitionType;
    }

    /**
     * 土地取得方式
     * @param acquisitionType 土地取得方式
     */
    public void setAcquisitionType(String acquisitionType) {
        this.acquisitionType = acquisitionType == null ? null : acquisitionType.trim();
    }

    /**
     * 土地权利性质
     * @return land_right_nature 土地权利性质
     */
    public String getLandRightNature() {
        return landRightNature;
    }

    /**
     * 土地权利性质
     * @param landRightNature 土地权利性质
     */
    public void setLandRightNature(String landRightNature) {
        this.landRightNature = landRightNature == null ? null : landRightNature.trim();
    }

    /**
     * 土地权利类型
     * @return land_right_type 土地权利类型
     */
    public String getLandRightType() {
        return landRightType;
    }

    /**
     * 土地权利类型
     * @param landRightType 土地权利类型
     */
    public void setLandRightType(String landRightType) {
        this.landRightType = landRightType == null ? null : landRightType.trim();
    }

    /**
     * 地块编号
     * @return land_piece_numbering 地块编号
     */
    public String getLandPieceNumbering() {
        return landPieceNumbering;
    }

    /**
     * 地块编号
     * @param landPieceNumbering 地块编号
     */
    public void setLandPieceNumbering(String landPieceNumbering) {
        this.landPieceNumbering = landPieceNumbering == null ? null : landPieceNumbering.trim();
    }

    /**
     * 区域规划
     * @return regional_planning 区域规划
     */
    public String getRegionalPlanning() {
        return regionalPlanning;
    }

    /**
     * 区域规划
     * @param regionalPlanning 区域规划
     */
    public void setRegionalPlanning(String regionalPlanning) {
        this.regionalPlanning = regionalPlanning == null ? null : regionalPlanning.trim();
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
     * 是否有效
     * @return bis_enable 是否有效
     */
    public Boolean getBisEnable() {
        return bisEnable;
    }

    /**
     * 是否有效
     * @param bisEnable 是否有效
     */
    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
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

    public static BasicEstate.Builder builder() {
        return new BasicEstate.Builder();
    }

    /**
     * tb_basic_estate
     */
    public static class Builder {
        /**
         * tb_basic_estate
         */
        private BasicEstate obj;

        public Builder() {
            this.obj = new BasicEstate();
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
         * 引用案例数据id
         * @param quoteId 引用案例数据id
         */
        public Builder quoteId(Integer quoteId) {
            obj.setQuoteId(quoteId);
            return this;
        }

        /**
         * 大类
         * @param classify 大类
         */
        public Builder classify(Integer classify) {
            obj.setClassify(classify);
            return this;
        }

        /**
         * 类型 0非工业仓储 1工业仓储
         * @param type 类型 0非工业仓储 1工业仓储
         */
        public Builder type(Integer type) {
            obj.setType(type);
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
         * 
         * @param blockId 
         */
        public Builder blockId(Integer blockId) {
            obj.setBlockId(blockId);
            return this;
        }

        /**
         * 版块名称
         * @param blockName 版块名称
         */
        public Builder blockName(String blockName) {
            obj.setBlockName(blockName);
            return this;
        }

        /**
         * 基础版块描述
         * @param blockDescription 基础版块描述
         */
        public Builder blockDescription(String blockDescription) {
            obj.setBlockDescription(blockDescription);
            return this;
        }

        /**
         * 开发商名称
         * @param developer 开发商名称
         */
        public Builder developer(String developer) {
            obj.setDeveloper(developer);
            return this;
        }

        /**
         * 开发商名称
         * @param developerName 开发商名称
         */
        public Builder developerName(String developerName) {
            obj.setDeveloperName(developerName);
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
         * 街道
         * @param street 街道
         */
        public Builder street(String street) {
            obj.setStreet(street);
            return this;
        }

        /**
         * 街道号
         * @param streetNumber 街道号
         */
        public Builder streetNumber(String streetNumber) {
            obj.setStreetNumber(streetNumber);
            return this;
        }

        /**
         * 编号
         * @param number 编号
         */
        public Builder number(String number) {
            obj.setNumber(number);
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
         * 附号
         * @param attachNumber 附号
         */
        public Builder attachNumber(String attachNumber) {
            obj.setAttachNumber(attachNumber);
            return this;
        }

        /**
         * 建筑面积
         * @param floorArea 建筑面积
         */
        public Builder floorArea(BigDecimal floorArea) {
            obj.setFloorArea(floorArea);
            return this;
        }

        /**
         * 占地面积
         * @param coverAnArea 占地面积
         */
        public Builder coverAnArea(BigDecimal coverAnArea) {
            obj.setCoverAnArea(coverAnArea);
            return this;
        }

        /**
         * 占地面积单位
         * @param coverAnAreaUnit 占地面积单位
         */
        public Builder coverAnAreaUnit(String coverAnAreaUnit) {
            obj.setCoverAnAreaUnit(coverAnAreaUnit);
            return this;
        }

        /**
         * 容积率
         * @param volumetricRate 容积率
         */
        public Builder volumetricRate(String volumetricRate) {
            obj.setVolumetricRate(volumetricRate);
            return this;
        }

        /**
         * 绿化率
         * @param greeningRate 绿化率
         */
        public Builder greeningRate(String greeningRate) {
            obj.setGreeningRate(greeningRate);
            return this;
        }

        /**
         * 开盘时间
         * @param openTime 开盘时间
         */
        public Builder openTime(Date openTime) {
            obj.setOpenTime(openTime);
            return this;
        }

        public BasicEstate build() {
            return this.obj;
        }

        /**
         * 总栋数 
         * @param buildingNumber 总栋数 
         */
        public Builder buildingNumber(Integer buildingNumber) {
            obj.setBuildingNumber(buildingNumber);
            return this;
        }

        /**
         * 方位
         * @param position 方位
         */
        public Builder position(Integer position) {
            obj.setPosition(position);
            return this;
        }

        /**
         * 楼盘概况
         * @param description 楼盘概况
         */
        public Builder description(String description) {
            obj.setDescription(description);
            return this;
        }

        /**
         * 均价
         * @param averagePrice 均价
         */
        public Builder averagePrice(String averagePrice) {
            obj.setAveragePrice(averagePrice);
            return this;
        }

        /**
         * 均价单位
         * @param averagePriceUnit 均价单位
         */
        public Builder averagePriceUnit(String averagePriceUnit) {
            obj.setAveragePriceUnit(averagePriceUnit);
            return this;
        }

        /**
         * 价格区间
         * @param priceRange 价格区间
         */
        public Builder priceRange(String priceRange) {
            obj.setPriceRange(priceRange);
            return this;
        }

        /**
         * 价格区间单位
         * @param priceRangeUnit 价格区间单位
         */
        public Builder priceRangeUnit(String priceRangeUnit) {
            obj.setPriceRangeUnit(priceRangeUnit);
            return this;
        }

        /**
         * 供热信息 有无
         * @param supplyHeating 供热信息 有无
         */
        public Builder supplyHeating(Integer supplyHeating) {
            obj.setSupplyHeating(supplyHeating);
            return this;
        }

        /**
         * 供电信息 有无
         * @param supplyPower 供电信息 有无
         */
        public Builder supplyPower(Integer supplyPower) {
            obj.setSupplyPower(supplyPower);
            return this;
        }

        /**
         * 通讯信息 有无
         * @param supplyCommunication 通讯信息 有无
         */
        public Builder supplyCommunication(Integer supplyCommunication) {
            obj.setSupplyCommunication(supplyCommunication);
            return this;
        }

        /**
         * 道路信息 有无
         * @param supplyRoad 道路信息 有无
         */
        public Builder supplyRoad(Integer supplyRoad) {
            obj.setSupplyRoad(supplyRoad);
            return this;
        }

        /**
         * 供排水情况 有无
         * @param supplyWater 供排水情况 有无
         */
        public Builder supplyWater(Integer supplyWater) {
            obj.setSupplyWater(supplyWater);
            return this;
        }

        /**
         * 排水
         * @param drainWater 排水
         */
        public Builder drainWater(Integer drainWater) {
            obj.setDrainWater(drainWater);
            return this;
        }

        /**
         * 供气信息 有无
         * @param supplyGas 供气信息 有无
         */
        public Builder supplyGas(Integer supplyGas) {
            obj.setSupplyGas(supplyGas);
            return this;
        }

        /**
         * 基础设施情况
         * @param infrastructure 基础设施情况
         */
        public Builder infrastructure(String infrastructure) {
            obj.setInfrastructure(infrastructure);
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
         * 区位描述
         * @param locationDescribe 区位描述
         */
        public Builder locationDescribe(String locationDescribe) {
            obj.setLocationDescribe(locationDescribe);
            return this;
        }

        /**
         * 查看案例详情id
         * @param displayCaseId 查看案例详情id
         */
        public Builder displayCaseId(Integer displayCaseId) {
            obj.setDisplayCaseId(displayCaseId);
            return this;
        }

        /**
         * map id
         * @param mapId map id
         */
        public Builder mapId(Integer mapId) {
            obj.setMapId(mapId);
            return this;
        }

        /**
         * 属于某id的旧数据
         * @param relevanceId 属于某id的旧数据
         */
        public Builder relevanceId(Integer relevanceId) {
            obj.setRelevanceId(relevanceId);
            return this;
        }

        /**
         * 版本号
         * @param version 版本号
         */
        public Builder version(Integer version) {
            obj.setVersion(version);
            return this;
        }

        /**
         * 是否为案例
         * @param bisCase 是否为案例
         */
        public Builder bisCase(Boolean bisCase) {
            obj.setBisCase(bisCase);
            return this;
        }

        /**
         * 土地取得方式
         * @param acquisitionType 土地取得方式
         */
        public Builder acquisitionType(String acquisitionType) {
            obj.setAcquisitionType(acquisitionType);
            return this;
        }

        /**
         * 土地权利性质
         * @param landRightNature 土地权利性质
         */
        public Builder landRightNature(String landRightNature) {
            obj.setLandRightNature(landRightNature);
            return this;
        }

        /**
         * 土地权利类型
         * @param landRightType 土地权利类型
         */
        public Builder landRightType(String landRightType) {
            obj.setLandRightType(landRightType);
            return this;
        }

        /**
         * 地块编号
         * @param landPieceNumbering 地块编号
         */
        public Builder landPieceNumbering(String landPieceNumbering) {
            obj.setLandPieceNumbering(landPieceNumbering);
            return this;
        }

        /**
         * 区域规划
         * @param regionalPlanning 区域规划
         */
        public Builder regionalPlanning(String regionalPlanning) {
            obj.setRegionalPlanning(regionalPlanning);
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
         * 是否有效
         * @param bisEnable 是否有效
         */
        public Builder bisEnable(Boolean bisEnable) {
            obj.setBisEnable(bisEnable);
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
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        applyId("apply_id", "applyId", "INTEGER", false),
        quoteId("quote_id", "quoteId", "INTEGER", false),
        classify("classify", "classify", "INTEGER", false),
        type("type", "type", "INTEGER", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        blockId("block_id", "blockId", "INTEGER", false),
        blockName("block_name", "blockName", "VARCHAR", false),
        blockDescription("block_description", "blockDescription", "VARCHAR", false),
        developer("developer", "developer", "VARCHAR", false),
        developerName("developer_name", "developerName", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        streetNumber("street_number", "streetNumber", "VARCHAR", false),
        street("street", "street", "VARCHAR", false),
        number("number", "number", "VARCHAR", false),
        landLevel("land_level", "landLevel", "INTEGER", false),
        attachNumber("attach_number", "attachNumber", "VARCHAR", false),
        floorArea("floor_area", "floorArea", "DECIMAL", false),
        coverAnArea("cover_an_area", "coverAnArea", "DECIMAL", false),
        volumetricRate("volumetric_rate", "volumetricRate", "VARCHAR", false),
        greeningRate("greening_rate", "greeningRate", "VARCHAR", false),
        openTime("open_time", "openTime", "TIMESTAMP", false),
        buildingNumber("building_number", "buildingNumber", "INTEGER", false),
        position("position", "position", "INTEGER", false),
        description("description", "description", "VARCHAR", false),
        averagePrice("average_price", "averagePrice", "VARCHAR", false),
        priceRange("price_range", "priceRange", "VARCHAR", false),
        supplyHeating("supply_heating", "supplyHeating", "INTEGER", false),
        supplyPower("supply_power", "supplyPower", "INTEGER", false),
        supplyCommunication("supply_communication", "supplyCommunication", "INTEGER", false),
        supplyRoad("supply_road", "supplyRoad", "INTEGER", false),
        supplyWater("supply_water", "supplyWater", "INTEGER", false),
        drainWater("drain_water", "drainWater", "INTEGER", false),
        supplyGas("supply_gas", "supplyGas", "INTEGER", false),
        infrastructure("infrastructure", "infrastructure", "VARCHAR", false),
        infrastructureCompleteness("infrastructure_completeness", "infrastructureCompleteness", "INTEGER", false),
        locationDescribe("location_describe", "locationDescribe", "VARCHAR", false),
        displayCaseId("display_case_id", "displayCaseId", "INTEGER", false),
        mapId("map_id", "mapId", "INTEGER", false),
        relevanceId("relevance_id", "relevanceId", "INTEGER", false),
        version("version", "version", "INTEGER", false),
        bisCase("bis_case", "bisCase", "BIT", false),
        averagePriceUnit("average_price_unit", "averagePriceUnit", "VARCHAR", false),
        priceRangeUnit("price_range_unit", "priceRangeUnit", "VARCHAR", false),
        coverAnAreaUnit("cover_an_area_unit", "coverAnAreaUnit", "VARCHAR", false),
        acquisitionType("acquisition_type", "acquisitionType", "VARCHAR", false),
        landRightNature("land_right_nature", "landRightNature", "VARCHAR", false),
        landRightType("land_right_type", "landRightType", "VARCHAR", false),
        landPieceNumbering("land_piece_numbering", "landPieceNumbering", "VARCHAR", false),
        regionalPlanning("regional_planning", "regionalPlanning", "VARCHAR", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        bisEnable("bis_enable", "bisEnable", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_basic_estate
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_estate
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_estate
         */
        private final String column;

        /**
         * tb_basic_estate
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_estate
         */
        private final String javaProperty;

        /**
         * tb_basic_estate
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