package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class NetInfoRecordHouse implements Serializable {
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
     * 房产类型
     */
    private String belongType;

    /**
     * 房产类别
     */
    private String belongCategory;

    /**
     * 街道
     */
    private String street;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 楼盘名称
     */
    private String name;

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
     * 评估价
     */
    private BigDecimal consultPrice;

    /**
     * 评估基准日
     */
    private Date assessStandardDate;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 变现率
     */
    private BigDecimal houseRealizationRatios;

    /**
     * 变现周期
     */
    private String realizationCycle;

    /**
     * 成交对象概况
     */
    private String dealPartInfo;

    /**
     * 1审批通过 2审批中
     */
    private Integer status;

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
     * 楼栋号
     */
    private String buildingNumber;

    /**
     * 单元号
     */
    private String unitNumber;

    /**
     * 房号
     */
    private String houseNumber;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 
     */
    private Integer tradingType;

    /**
     * 限购状态
     */
    private String purchaseLimitStatus;

    /**
     * 是否最新版本
     */
    private Boolean bisNewest;

    /**
     * 被升级数据id
     */
    private Integer beUpgradeId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * tb_net_info_record_house
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
     * 房产类型
     * @return belong_type 房产类型
     */
    public String getBelongType() {
        return belongType;
    }

    /**
     * 房产类型
     * @param belongType 房产类型
     */
    public void setBelongType(String belongType) {
        this.belongType = belongType == null ? null : belongType.trim();
    }

    /**
     * 房产类别
     * @return belong_category 房产类别
     */
    public String getBelongCategory() {
        return belongCategory;
    }

    /**
     * 房产类别
     * @param belongCategory 房产类别
     */
    public void setBelongCategory(String belongCategory) {
        this.belongCategory = belongCategory == null ? null : belongCategory.trim();
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
     * 楼盘名称
     * @return name 楼盘名称
     */
    public String getName() {
        return name;
    }

    /**
     * 楼盘名称
     * @param name 楼盘名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 评估价
     * @return consult_price 评估价
     */
    public BigDecimal getConsultPrice() {
        return consultPrice;
    }

    /**
     * 评估价
     * @param consultPrice 评估价
     */
    public void setConsultPrice(BigDecimal consultPrice) {
        this.consultPrice = consultPrice;
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
     * 单价
     * @return unit_price 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单价
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 变现率
     * @return house_realization_ratios 变现率
     */
    public BigDecimal getHouseRealizationRatios() {
        return houseRealizationRatios;
    }

    /**
     * 变现率
     * @param houseRealizationRatios 变现率
     */
    public void setHouseRealizationRatios(BigDecimal houseRealizationRatios) {
        this.houseRealizationRatios = houseRealizationRatios;
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
     * 楼栋号
     * @return building_number 楼栋号
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * 楼栋号
     * @param buildingNumber 楼栋号
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    /**
     * 单元号
     * @return unit_number 单元号
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    /**
     * 单元号
     * @param unitNumber 单元号
     */
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber == null ? null : unitNumber.trim();
    }

    /**
     * 房号
     * @return house_number 房号
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * 房号
     * @param houseNumber 房号
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
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
     * 
     * @return trading_type 
     */
    public Integer getTradingType() {
        return tradingType;
    }

    /**
     * 
     * @param tradingType 
     */
    public void setTradingType(Integer tradingType) {
        this.tradingType = tradingType;
    }

    /**
     * 限购状态
     * @return purchase_limit_status 限购状态
     */
    public String getPurchaseLimitStatus() {
        return purchaseLimitStatus;
    }

    /**
     * 限购状态
     * @param purchaseLimitStatus 限购状态
     */
    public void setPurchaseLimitStatus(String purchaseLimitStatus) {
        this.purchaseLimitStatus = purchaseLimitStatus == null ? null : purchaseLimitStatus.trim();
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

    public static NetInfoRecordHouse.Builder builder() {
        return new NetInfoRecordHouse.Builder();
    }

    /**
     * tb_net_info_record_house
     */
    public static class Builder {
        /**
         * tb_net_info_record_house
         */
        private NetInfoRecordHouse obj;

        public Builder() {
            this.obj = new NetInfoRecordHouse();
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
         * 房产类型
         * @param belongType 房产类型
         */
        public Builder belongType(String belongType) {
            obj.setBelongType(belongType);
            return this;
        }

        /**
         * 房产类别
         * @param belongCategory 房产类别
         */
        public Builder belongCategory(String belongCategory) {
            obj.setBelongCategory(belongCategory);
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
         * 楼盘名称
         * @param name 楼盘名称
         */
        public Builder name(String name) {
            obj.setName(name);
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
         * 评估价
         * @param consultPrice 评估价
         */
        public Builder consultPrice(BigDecimal consultPrice) {
            obj.setConsultPrice(consultPrice);
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
         * 单价
         * @param unitPrice 单价
         */
        public Builder unitPrice(BigDecimal unitPrice) {
            obj.setUnitPrice(unitPrice);
            return this;
        }

        /**
         * 变现率
         * @param houseRealizationRatios 变现率
         */
        public Builder houseRealizationRatios(BigDecimal houseRealizationRatios) {
            obj.setHouseRealizationRatios(houseRealizationRatios);
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

        public NetInfoRecordHouse build() {
            return this.obj;
        }

        /**
         * 楼栋号
         * @param buildingNumber 楼栋号
         */
        public Builder buildingNumber(String buildingNumber) {
            obj.setBuildingNumber(buildingNumber);
            return this;
        }

        /**
         * 单元号
         * @param unitNumber 单元号
         */
        public Builder unitNumber(String unitNumber) {
            obj.setUnitNumber(unitNumber);
            return this;
        }

        /**
         * 房号
         * @param houseNumber 房号
         */
        public Builder houseNumber(String houseNumber) {
            obj.setHouseNumber(houseNumber);
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
         * 
         * @param tradingType 
         */
        public Builder tradingType(Integer tradingType) {
            obj.setTradingType(tradingType);
            return this;
        }

        /**
         * 限购状态
         * @param purchaseLimitStatus 限购状态
         */
        public Builder purchaseLimitStatus(String purchaseLimitStatus) {
            obj.setPurchaseLimitStatus(purchaseLimitStatus);
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
         * 版本号
         * @param version 版本号
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
        street("street", "street", "VARCHAR", false),
        area("area", "area", "DECIMAL", false),
        name("name", "name", "VARCHAR", false),
        dealType("deal_type", "dealType", "INTEGER", false),
        currentPrice("current_price", "currentPrice", "DECIMAL", false),
        negotiatedDate("negotiated_date", "negotiatedDate", "TIMESTAMP", false),
        consultPrice("consult_price", "consultPrice", "DECIMAL", false),
        assessStandardDate("assess_standard_date", "assessStandardDate", "TIMESTAMP", false),
        unitPrice("unit_price", "unitPrice", "DECIMAL", false),
        houseRealizationRatios("house_realization_ratios", "houseRealizationRatios", "DECIMAL", false),
        realizationCycle("realization_cycle", "realizationCycle", "VARCHAR", false),
        dealPartInfo("deal_part_info", "dealPartInfo", "VARCHAR", false),
        status("status", "status", "INTEGER", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        buildingNumber("building_number", "buildingNumber", "VARCHAR", false),
        unitNumber("unit_number", "unitNumber", "VARCHAR", false),
        houseNumber("house_number", "houseNumber", "VARCHAR", false),
        approver("approver", "approver", "VARCHAR", false),
        tradingType("trading_type", "tradingType", "INTEGER", false),
        purchaseLimitStatus("purchase_limit_status", "purchaseLimitStatus", "VARCHAR", false),
        bisNewest("bis_newest", "bisNewest", "BIT", false),
        beUpgradeId("be_upgrade_id", "beUpgradeId", "INTEGER", false),
        version("version", "version", "INTEGER", false);

        /**
         * tb_net_info_record_house
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_net_info_record_house
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_net_info_record_house
         */
        private final String column;

        /**
         * tb_net_info_record_house
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_net_info_record_house
         */
        private final String javaProperty;

        /**
         * tb_net_info_record_house
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