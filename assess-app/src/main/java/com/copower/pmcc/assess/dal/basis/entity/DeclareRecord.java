package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareRecord implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 编号
     */
    private Integer number;

    /**
     * 申报表类型
     */
    private String dataTableName;

    /**
     * 数据来源表id
     */
    private Integer dataTableId;

    /**
     * 
     */
    private String dataFromType;

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
     * 类型(房产证、土地证、不动产证)
     */
    private String type;

    /**
     * 是否有证
     */
    private Boolean hasCert;

    /**
     * 权证名称
     */
    private String name;

    /**
     * 所有权人
     */
    private String ownership;

    /**
     * 坐落
     */
    private String seat;

    /**
     * 街道号
     */
    private String streetNumber;

    /**
     * 附号
     */
    private String attachedNumber;

    /**
     * 栋号
     */
    private String buildingNumber;

    /**
     * 单元
     */
    private String unit;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 房号
     */
    private String roomNumber;

    /**
     * 证载用途
     */
    private String certUse;

    /**
     * 实际用途
     */
    private String practicalUse;

    /**
     * 土地证载用途
     */
    private String landCertUse;

    /**
     * 土地用途类别
     */
    private String landCertUseCategory;

    /**
     * 土地实际用途
     */
    private String landPracticalUse;

    /**
     * 共有情况
     */
    private String publicSituation;

    /**
     * 证载面积
     */
    private BigDecimal floorArea;

    /**
     * 实际面积
     */
    private BigDecimal practicalArea;

    /**
     * 房屋性质
     */
    private String nature;

    /**
     * 土地权利类型
     */
    private String landRightType;

    /**
     * 权利性质
     */
    private String landRightNature;

    /**
     * 土地使用权面积
     */
    private BigDecimal landUseRightArea;

    /**
     * 房屋结构
     */
    private String housingStructure;

    /**
     * 申报阶段的一次分组==申报记录(2019-10-15添加的字段)
     */
    private Integer groupId;

    /**
     * 区域分组id
     */
    private Integer areaGroupId;

    /**
     * 房产有效使用时间
     */
    private Date houseUseEndDate;

    /**
     * 土地有效使用时间
     */
    private Date landUseEndDate;

    /**
     * 清查内容项key值
     */
    private String inventoryContentKey;

    /**
     * 登记日期
     */
    private Date registrationDate;

    /**
     * 评估单价
     */
    private BigDecimal price;

    /**
     * 建筑状态（已完、在建）
     */
    private Integer buildingStatus;

    /**
     * 是否参与报告出具
     */
    private Boolean bisPartIn;

    /**
     * 是否生成了清查与查勘任务项
     */
    private Boolean bisGenerate;

    /**
     * 清查状态
     */
    private String inventoryStatus;

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
     * tb_declare_record
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
     * 项目id
     * @return project_id 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 编号
     * @return number 编号
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 编号
     * @param number 编号
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 申报表类型
     * @return data_table_name 申报表类型
     */
    public String getDataTableName() {
        return dataTableName;
    }

    /**
     * 申报表类型
     * @param dataTableName 申报表类型
     */
    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName == null ? null : dataTableName.trim();
    }

    /**
     * 数据来源表id
     * @return data_table_id 数据来源表id
     */
    public Integer getDataTableId() {
        return dataTableId;
    }

    /**
     * 数据来源表id
     * @param dataTableId 数据来源表id
     */
    public void setDataTableId(Integer dataTableId) {
        this.dataTableId = dataTableId;
    }

    /**
     * 
     * @return data_from_type 
     */
    public String getDataFromType() {
        return dataFromType;
    }

    /**
     * 
     * @param dataFromType 
     */
    public void setDataFromType(String dataFromType) {
        this.dataFromType = dataFromType == null ? null : dataFromType.trim();
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
     * 类型(房产证、土地证、不动产证)
     * @return type 类型(房产证、土地证、不动产证)
     */
    public String getType() {
        return type;
    }

    /**
     * 类型(房产证、土地证、不动产证)
     * @param type 类型(房产证、土地证、不动产证)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 是否有证
     * @return has_cert 是否有证
     */
    public Boolean getHasCert() {
        return hasCert;
    }

    /**
     * 是否有证
     * @param hasCert 是否有证
     */
    public void setHasCert(Boolean hasCert) {
        this.hasCert = hasCert;
    }

    /**
     * 权证名称
     * @return name 权证名称
     */
    public String getName() {
        return name;
    }

    /**
     * 权证名称
     * @param name 权证名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 所有权人
     * @return ownership 所有权人
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * 所有权人
     * @param ownership 所有权人
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    /**
     * 坐落
     * @return seat 坐落
     */
    public String getSeat() {
        return seat;
    }

    /**
     * 坐落
     * @param seat 坐落
     */
    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
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
     * 附号
     * @return attached_number 附号
     */
    public String getAttachedNumber() {
        return attachedNumber;
    }

    /**
     * 附号
     * @param attachedNumber 附号
     */
    public void setAttachedNumber(String attachedNumber) {
        this.attachedNumber = attachedNumber == null ? null : attachedNumber.trim();
    }

    /**
     * 栋号
     * @return building_number 栋号
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * 栋号
     * @param buildingNumber 栋号
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    /**
     * 单元
     * @return unit 单元
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 单元
     * @param unit 单元
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 楼层
     * @return floor 楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 楼层
     * @param floor 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 房号
     * @return room_number 房号
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * 房号
     * @param roomNumber 房号
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    /**
     * 证载用途
     * @return cert_use 证载用途
     */
    public String getCertUse() {
        return certUse;
    }

    /**
     * 证载用途
     * @param certUse 证载用途
     */
    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    /**
     * 实际用途
     * @return practical_use 实际用途
     */
    public String getPracticalUse() {
        return practicalUse;
    }

    /**
     * 实际用途
     * @param practicalUse 实际用途
     */
    public void setPracticalUse(String practicalUse) {
        this.practicalUse = practicalUse == null ? null : practicalUse.trim();
    }

    /**
     * 土地证载用途
     * @return land_cert_use 土地证载用途
     */
    public String getLandCertUse() {
        return landCertUse;
    }

    /**
     * 土地证载用途
     * @param landCertUse 土地证载用途
     */
    public void setLandCertUse(String landCertUse) {
        this.landCertUse = landCertUse == null ? null : landCertUse.trim();
    }

    /**
     * 土地用途类别
     * @return land_cert_use_category 土地用途类别
     */
    public String getLandCertUseCategory() {
        return landCertUseCategory;
    }

    /**
     * 土地用途类别
     * @param landCertUseCategory 土地用途类别
     */
    public void setLandCertUseCategory(String landCertUseCategory) {
        this.landCertUseCategory = landCertUseCategory == null ? null : landCertUseCategory.trim();
    }

    /**
     * 土地实际用途
     * @return land_practical_use 土地实际用途
     */
    public String getLandPracticalUse() {
        return landPracticalUse;
    }

    /**
     * 土地实际用途
     * @param landPracticalUse 土地实际用途
     */
    public void setLandPracticalUse(String landPracticalUse) {
        this.landPracticalUse = landPracticalUse == null ? null : landPracticalUse.trim();
    }

    /**
     * 共有情况
     * @return public_situation 共有情况
     */
    public String getPublicSituation() {
        return publicSituation;
    }

    /**
     * 共有情况
     * @param publicSituation 共有情况
     */
    public void setPublicSituation(String publicSituation) {
        this.publicSituation = publicSituation == null ? null : publicSituation.trim();
    }

    /**
     * 证载面积
     * @return floor_area 证载面积
     */
    public BigDecimal getFloorArea() {
        return floorArea;
    }

    /**
     * 证载面积
     * @param floorArea 证载面积
     */
    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * 实际面积
     * @return practical_area 实际面积
     */
    public BigDecimal getPracticalArea() {
        return practicalArea;
    }

    /**
     * 实际面积
     * @param practicalArea 实际面积
     */
    public void setPracticalArea(BigDecimal practicalArea) {
        this.practicalArea = practicalArea;
    }

    /**
     * 房屋性质
     * @return nature 房屋性质
     */
    public String getNature() {
        return nature;
    }

    /**
     * 房屋性质
     * @param nature 房屋性质
     */
    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
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
     * 权利性质
     * @return land_right_nature 权利性质
     */
    public String getLandRightNature() {
        return landRightNature;
    }

    /**
     * 权利性质
     * @param landRightNature 权利性质
     */
    public void setLandRightNature(String landRightNature) {
        this.landRightNature = landRightNature == null ? null : landRightNature.trim();
    }

    /**
     * 土地使用权面积
     * @return land_use_right_area 土地使用权面积
     */
    public BigDecimal getLandUseRightArea() {
        return landUseRightArea;
    }

    /**
     * 土地使用权面积
     * @param landUseRightArea 土地使用权面积
     */
    public void setLandUseRightArea(BigDecimal landUseRightArea) {
        this.landUseRightArea = landUseRightArea;
    }

    /**
     * 房屋结构
     * @return housing_structure 房屋结构
     */
    public String getHousingStructure() {
        return housingStructure;
    }

    /**
     * 房屋结构
     * @param housingStructure 房屋结构
     */
    public void setHousingStructure(String housingStructure) {
        this.housingStructure = housingStructure == null ? null : housingStructure.trim();
    }

    /**
     * 申报阶段的一次分组==申报记录(2019-10-15添加的字段)
     * @return group_id 申报阶段的一次分组==申报记录(2019-10-15添加的字段)
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 申报阶段的一次分组==申报记录(2019-10-15添加的字段)
     * @param groupId 申报阶段的一次分组==申报记录(2019-10-15添加的字段)
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 区域分组id
     * @return area_group_id 区域分组id
     */
    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    /**
     * 区域分组id
     * @param areaGroupId 区域分组id
     */
    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    /**
     * 房产有效使用时间
     * @return house_use_end_date 房产有效使用时间
     */
    public Date getHouseUseEndDate() {
        return houseUseEndDate;
    }

    /**
     * 房产有效使用时间
     * @param houseUseEndDate 房产有效使用时间
     */
    public void setHouseUseEndDate(Date houseUseEndDate) {
        this.houseUseEndDate = houseUseEndDate;
    }

    /**
     * 土地有效使用时间
     * @return land_use_end_date 土地有效使用时间
     */
    public Date getLandUseEndDate() {
        return landUseEndDate;
    }

    /**
     * 土地有效使用时间
     * @param landUseEndDate 土地有效使用时间
     */
    public void setLandUseEndDate(Date landUseEndDate) {
        this.landUseEndDate = landUseEndDate;
    }

    /**
     * 清查内容项key值
     * @return inventory_content_key 清查内容项key值
     */
    public String getInventoryContentKey() {
        return inventoryContentKey;
    }

    /**
     * 清查内容项key值
     * @param inventoryContentKey 清查内容项key值
     */
    public void setInventoryContentKey(String inventoryContentKey) {
        this.inventoryContentKey = inventoryContentKey == null ? null : inventoryContentKey.trim();
    }

    /**
     * 登记日期
     * @return registration_date 登记日期
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * 登记日期
     * @param registrationDate 登记日期
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * 评估单价
     * @return price 评估单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 评估单价
     * @param price 评估单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 建筑状态（已完、在建）
     * @return building_status 建筑状态（已完、在建）
     */
    public Integer getBuildingStatus() {
        return buildingStatus;
    }

    /**
     * 建筑状态（已完、在建）
     * @param buildingStatus 建筑状态（已完、在建）
     */
    public void setBuildingStatus(Integer buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    /**
     * 是否参与报告出具
     * @return bis_part_in 是否参与报告出具
     */
    public Boolean getBisPartIn() {
        return bisPartIn;
    }

    /**
     * 是否参与报告出具
     * @param bisPartIn 是否参与报告出具
     */
    public void setBisPartIn(Boolean bisPartIn) {
        this.bisPartIn = bisPartIn;
    }

    /**
     * 是否生成了清查与查勘任务项
     * @return bis_generate 是否生成了清查与查勘任务项
     */
    public Boolean getBisGenerate() {
        return bisGenerate;
    }

    /**
     * 是否生成了清查与查勘任务项
     * @param bisGenerate 是否生成了清查与查勘任务项
     */
    public void setBisGenerate(Boolean bisGenerate) {
        this.bisGenerate = bisGenerate;
    }

    /**
     * 清查状态
     * @return inventory_status 清查状态
     */
    public String getInventoryStatus() {
        return inventoryStatus;
    }

    /**
     * 清查状态
     * @param inventoryStatus 清查状态
     */
    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus == null ? null : inventoryStatus.trim();
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

    public static DeclareRecord.Builder builder() {
        return new DeclareRecord.Builder();
    }

    /**
     * tb_declare_record
     */
    public static class Builder {
        /**
         * tb_declare_record
         */
        private DeclareRecord obj;

        public Builder() {
            this.obj = new DeclareRecord();
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
         * 项目id
         * @param projectId 项目id
         */
        public Builder projectId(Integer projectId) {
            obj.setProjectId(projectId);
            return this;
        }

        /**
         * 编号
         * @param number 编号
         */
        public Builder number(Integer number) {
            obj.setNumber(number);
            return this;
        }

        /**
         * 申报表类型
         * @param dataTableName 申报表类型
         */
        public Builder dataTableName(String dataTableName) {
            obj.setDataTableName(dataTableName);
            return this;
        }

        /**
         * 数据来源表id
         * @param dataTableId 数据来源表id
         */
        public Builder dataTableId(Integer dataTableId) {
            obj.setDataTableId(dataTableId);
            return this;
        }

        /**
         * 
         * @param dataFromType 
         */
        public Builder dataFromType(String dataFromType) {
            obj.setDataFromType(dataFromType);
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
         * 类型(房产证、土地证、不动产证)
         * @param type 类型(房产证、土地证、不动产证)
         */
        public Builder type(String type) {
            obj.setType(type);
            return this;
        }

        /**
         * 是否有证
         * @param hasCert 是否有证
         */
        public Builder hasCert(Boolean hasCert) {
            obj.setHasCert(hasCert);
            return this;
        }

        /**
         * 权证名称
         * @param name 权证名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 所有权人
         * @param ownership 所有权人
         */
        public Builder ownership(String ownership) {
            obj.setOwnership(ownership);
            return this;
        }

        /**
         * 坐落
         * @param seat 坐落
         */
        public Builder seat(String seat) {
            obj.setSeat(seat);
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
         * 附号
         * @param attachedNumber 附号
         */
        public Builder attachedNumber(String attachedNumber) {
            obj.setAttachedNumber(attachedNumber);
            return this;
        }

        public DeclareRecord build() {
            return this.obj;
        }

        /**
         * 栋号
         * @param buildingNumber 栋号
         */
        public Builder buildingNumber(String buildingNumber) {
            obj.setBuildingNumber(buildingNumber);
            return this;
        }

        /**
         * 单元
         * @param unit 单元
         */
        public Builder unit(String unit) {
            obj.setUnit(unit);
            return this;
        }

        /**
         * 楼层
         * @param floor 楼层
         */
        public Builder floor(String floor) {
            obj.setFloor(floor);
            return this;
        }

        /**
         * 证载面积
         * @param floorArea 证载面积
         */
        public Builder floorArea(BigDecimal floorArea) {
            obj.setFloorArea(floorArea);
            return this;
        }

        /**
         * 房号
         * @param roomNumber 房号
         */
        public Builder roomNumber(String roomNumber) {
            obj.setRoomNumber(roomNumber);
            return this;
        }

        /**
         * 证载用途
         * @param certUse 证载用途
         */
        public Builder certUse(String certUse) {
            obj.setCertUse(certUse);
            return this;
        }

        /**
         * 实际用途
         * @param practicalUse 实际用途
         */
        public Builder practicalUse(String practicalUse) {
            obj.setPracticalUse(practicalUse);
            return this;
        }

        /**
         * 土地证载用途
         * @param landCertUse 土地证载用途
         */
        public Builder landCertUse(String landCertUse) {
            obj.setLandCertUse(landCertUse);
            return this;
        }

        /**
         * 土地用途类别
         * @param landCertUseCategory 土地用途类别
         */
        public Builder landCertUseCategory(String landCertUseCategory) {
            obj.setLandCertUseCategory(landCertUseCategory);
            return this;
        }

        /**
         * 土地实际用途
         * @param landPracticalUse 土地实际用途
         */
        public Builder landPracticalUse(String landPracticalUse) {
            obj.setLandPracticalUse(landPracticalUse);
            return this;
        }

        /**
         * 共有情况
         * @param publicSituation 共有情况
         */
        public Builder publicSituation(String publicSituation) {
            obj.setPublicSituation(publicSituation);
            return this;
        }

        /**
         * 实际面积
         * @param practicalArea 实际面积
         */
        public Builder practicalArea(BigDecimal practicalArea) {
            obj.setPracticalArea(practicalArea);
            return this;
        }

        /**
         * 房屋性质
         * @param nature 房屋性质
         */
        public Builder nature(String nature) {
            obj.setNature(nature);
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
         * 权利性质
         * @param landRightNature 权利性质
         */
        public Builder landRightNature(String landRightNature) {
            obj.setLandRightNature(landRightNature);
            return this;
        }

        /**
         * 土地使用权面积
         * @param landUseRightArea 土地使用权面积
         */
        public Builder landUseRightArea(BigDecimal landUseRightArea) {
            obj.setLandUseRightArea(landUseRightArea);
            return this;
        }

        /**
         * 房屋结构
         * @param housingStructure 房屋结构
         */
        public Builder housingStructure(String housingStructure) {
            obj.setHousingStructure(housingStructure);
            return this;
        }

        /**
         * 申报阶段的一次分组==申报记录(2019-10-15添加的字段)
         * @param groupId 申报阶段的一次分组==申报记录(2019-10-15添加的字段)
         */
        public Builder groupId(Integer groupId) {
            obj.setGroupId(groupId);
            return this;
        }

        /**
         * 区域分组id
         * @param areaGroupId 区域分组id
         */
        public Builder areaGroupId(Integer areaGroupId) {
            obj.setAreaGroupId(areaGroupId);
            return this;
        }

        /**
         * 房产有效使用时间
         * @param houseUseEndDate 房产有效使用时间
         */
        public Builder houseUseEndDate(Date houseUseEndDate) {
            obj.setHouseUseEndDate(houseUseEndDate);
            return this;
        }

        /**
         * 土地有效使用时间
         * @param landUseEndDate 土地有效使用时间
         */
        public Builder landUseEndDate(Date landUseEndDate) {
            obj.setLandUseEndDate(landUseEndDate);
            return this;
        }

        /**
         * 清查内容项key值
         * @param inventoryContentKey 清查内容项key值
         */
        public Builder inventoryContentKey(String inventoryContentKey) {
            obj.setInventoryContentKey(inventoryContentKey);
            return this;
        }

        /**
         * 登记日期
         * @param registrationDate 登记日期
         */
        public Builder registrationDate(Date registrationDate) {
            obj.setRegistrationDate(registrationDate);
            return this;
        }

        /**
         * 评估单价
         * @param price 评估单价
         */
        public Builder price(BigDecimal price) {
            obj.setPrice(price);
            return this;
        }

        /**
         * 建筑状态（已完、在建）
         * @param buildingStatus 建筑状态（已完、在建）
         */
        public Builder buildingStatus(Integer buildingStatus) {
            obj.setBuildingStatus(buildingStatus);
            return this;
        }

        /**
         * 是否参与报告出具
         * @param bisPartIn 是否参与报告出具
         */
        public Builder bisPartIn(Boolean bisPartIn) {
            obj.setBisPartIn(bisPartIn);
            return this;
        }

        /**
         * 是否生成了清查与查勘任务项
         * @param bisGenerate 是否生成了清查与查勘任务项
         */
        public Builder bisGenerate(Boolean bisGenerate) {
            obj.setBisGenerate(bisGenerate);
            return this;
        }

        /**
         * 清查状态
         * @param inventoryStatus 清查状态
         */
        public Builder inventoryStatus(String inventoryStatus) {
            obj.setInventoryStatus(inventoryStatus);
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
        projectId("project_id", "projectId", "INTEGER", false),
        number("number", "number", "INTEGER", false),
        dataTableName("data_table_name", "dataTableName", "VARCHAR", false),
        dataTableId("data_table_id", "dataTableId", "INTEGER", false),
        dataFromType("data_from_type", "dataFromType", "VARCHAR", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        type("type", "type", "VARCHAR", false),
        hasCert("has_cert", "hasCert", "BIT", false),
        name("name", "name", "VARCHAR", false),
        ownership("ownership", "ownership", "VARCHAR", false),
        seat("seat", "seat", "VARCHAR", false),
        streetNumber("street_number", "streetNumber", "VARCHAR", false),
        attachedNumber("attached_number", "attachedNumber", "VARCHAR", false),
        buildingNumber("building_number", "buildingNumber", "VARCHAR", false),
        unit("unit", "unit", "VARCHAR", false),
        floor("floor", "floor", "VARCHAR", false),
        roomNumber("room_number", "roomNumber", "VARCHAR", false),
        certUse("cert_use", "certUse", "VARCHAR", false),
        practicalUse("practical_use", "practicalUse", "VARCHAR", false),
        landCertUse("land_cert_use", "landCertUse", "VARCHAR", false),
        landCertUseCategory("land_cert_use_category", "landCertUseCategory", "VARCHAR", false),
        landPracticalUse("land_practical_use", "landPracticalUse", "VARCHAR", false),
        publicSituation("public_situation", "publicSituation", "VARCHAR", false),
        floorArea("floor_area", "floorArea", "DECIMAL", false),
        practicalArea("practical_area", "practicalArea", "DECIMAL", false),
        nature("nature", "nature", "VARCHAR", false),
        landRightType("land_right_type", "landRightType", "VARCHAR", false),
        landRightNature("land_right_nature", "landRightNature", "VARCHAR", false),
        landUseRightArea("land_use_right_area", "landUseRightArea", "DECIMAL", false),
        housingStructure("housing_structure", "housingStructure", "VARCHAR", false),
        groupId("group_id", "groupId", "INTEGER", false),
        areaGroupId("area_group_id", "areaGroupId", "INTEGER", false),
        houseUseEndDate("house_use_end_date", "houseUseEndDate", "TIMESTAMP", false),
        landUseEndDate("land_use_end_date", "landUseEndDate", "TIMESTAMP", false),
        inventoryContentKey("inventory_content_key", "inventoryContentKey", "VARCHAR", false),
        registrationDate("registration_date", "registrationDate", "TIMESTAMP", false),
        price("price", "price", "DECIMAL", false),
        buildingStatus("building_status", "buildingStatus", "INTEGER", false),
        bisPartIn("bis_part_in", "bisPartIn", "BIT", false),
        bisGenerate("bis_generate", "bisGenerate", "BIT", false),
        inventoryStatus("inventory_status", "inventoryStatus", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_declare_record
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_record
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_record
         */
        private final String column;

        /**
         * tb_declare_record
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_record
         */
        private final String javaProperty;

        /**
         * tb_declare_record
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