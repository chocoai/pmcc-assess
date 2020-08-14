package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareRealtyHouseCert implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer pid;

    /**
     * 
     */
    private Integer planDetailsId;

    /**
     * 导入的编号
     */
    private Integer autoInitNumber;

    /**
     * 房产权证号
     */
    private String certName;

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
     * 房产证类型
     */
    private String type;

    /**
     * 编号
     */
    private String number;

    /**
     * 房屋所有权人
     */
    private String ownership;

    /**
     * 共有情况
     */
    private Integer publicSituation;

    /**
     * 共有情况说明
     */
    private String publicSituationRemark;

    /**
     * 坐落
     */
    private String beLocated;

    /**
     * 街道号
     */
    private String streetNumber;

    /**
     * 附号
     */
    private String attachedNumber;

    /**
     * 幢号
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
     * 登记时间
     */
    private Date registrationTime;

    /**
     * 房屋性质
     */
    private Integer nature;

    /**
     * 总层数
     */
    private String floorCount;

    /**
     * 证载用途
     */
    private String certUse;

    /**
     * 房屋用途类别
     */
    private String certUseCategory;

    /**
     * 房屋结构
     */
    private String housingStructure;

    /**
     * 建筑面积
     */
    private String floorArea;

    /**
     * 所在地
     */
    private String location;

    /**
     * 套内面积
     */
    private BigDecimal innerArea;

    /**
     * 证载面积
     */
    private BigDecimal evidenceArea;

    /**
     * 其它
     */
    private String other;

    /**
     * 土地证号
     */
    private String landNumber;

    /**
     * 土地取得方式
     */
    private String landAcquisition;

    /**
     * 土地使用开始日期
     */
    private Date useStartDate;

    /**
     * 土地使用结束日期
     */
    private Date useEndDate;

    /**
     * 公摊面积
     */
    private BigDecimal publicArea;

    /**
     * 附记其它
     */
    private String otherNote;

    /**
     * 登记机关
     */
    private String registrationAuthority;

    /**
     * 登记日期土地
     */
    private Date landRegistrationDate;

    /**
     * 登记日期
     */
    private Date registrationDate;

    /**
     * 申报证书类型
     */
    private String declareType;

    /**
     * 丘地号
     */
    private String groundNum;

    /**
     * 分摊面积
     */
    private BigDecimal apportionmentArea;

    /**
     * 是否启用(不属于此实体信息的字段专用来区分某些数据的)
     */
    private String enable;

    /**
     * 是否写入到申报记录中
     */
    private Boolean bisRecord;

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
     * tb_declare_realty_house_cert
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
     * 导入的编号
     * @return auto_init_number 导入的编号
     */
    public Integer getAutoInitNumber() {
        return autoInitNumber;
    }

    /**
     * 导入的编号
     * @param autoInitNumber 导入的编号
     */
    public void setAutoInitNumber(Integer autoInitNumber) {
        this.autoInitNumber = autoInitNumber;
    }

    /**
     * 房产权证号
     * @return cert_name 房产权证号
     */
    public String getCertName() {
        return certName;
    }

    /**
     * 房产权证号
     * @param certName 房产权证号
     */
    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
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
     * 房产证类型
     * @return type 房产证类型
     */
    public String getType() {
        return type;
    }

    /**
     * 房产证类型
     * @param type 房产证类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 房屋所有权人
     * @return ownership 房屋所有权人
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * 房屋所有权人
     * @param ownership 房屋所有权人
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    /**
     * 共有情况
     * @return public_situation 共有情况
     */
    public Integer getPublicSituation() {
        return publicSituation;
    }

    /**
     * 共有情况
     * @param publicSituation 共有情况
     */
    public void setPublicSituation(Integer publicSituation) {
        this.publicSituation = publicSituation;
    }

    /**
     * 共有情况说明
     * @return public_situation_remark 共有情况说明
     */
    public String getPublicSituationRemark() {
        return publicSituationRemark;
    }

    /**
     * 共有情况说明
     * @param publicSituationRemark 共有情况说明
     */
    public void setPublicSituationRemark(String publicSituationRemark) {
        this.publicSituationRemark = publicSituationRemark == null ? null : publicSituationRemark.trim();
    }

    /**
     * 坐落
     * @return be_located 坐落
     */
    public String getBeLocated() {
        return beLocated;
    }

    /**
     * 坐落
     * @param beLocated 坐落
     */
    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated == null ? null : beLocated.trim();
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
     * 幢号
     * @return building_number 幢号
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * 幢号
     * @param buildingNumber 幢号
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
     * 登记时间
     * @return registration_time 登记时间
     */
    public Date getRegistrationTime() {
        return registrationTime;
    }

    /**
     * 登记时间
     * @param registrationTime 登记时间
     */
    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    /**
     * 房屋性质
     * @return nature 房屋性质
     */
    public Integer getNature() {
        return nature;
    }

    /**
     * 房屋性质
     * @param nature 房屋性质
     */
    public void setNature(Integer nature) {
        this.nature = nature;
    }

    /**
     * 总层数
     * @return floor_count 总层数
     */
    public String getFloorCount() {
        return floorCount;
    }

    /**
     * 总层数
     * @param floorCount 总层数
     */
    public void setFloorCount(String floorCount) {
        this.floorCount = floorCount == null ? null : floorCount.trim();
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
     * 房屋用途类别
     * @return cert_use_category 房屋用途类别
     */
    public String getCertUseCategory() {
        return certUseCategory;
    }

    /**
     * 房屋用途类别
     * @param certUseCategory 房屋用途类别
     */
    public void setCertUseCategory(String certUseCategory) {
        this.certUseCategory = certUseCategory == null ? null : certUseCategory.trim();
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
     * 建筑面积
     * @return floor_area 建筑面积
     */
    public String getFloorArea() {
        return floorArea;
    }

    /**
     * 建筑面积
     * @param floorArea 建筑面积
     */
    public void setFloorArea(String floorArea) {
        this.floorArea = floorArea == null ? null : floorArea.trim();
    }

    /**
     * 所在地
     * @return location 所在地
     */
    public String getLocation() {
        return location;
    }

    /**
     * 所在地
     * @param location 所在地
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 套内面积
     * @return inner_area 套内面积
     */
    public BigDecimal getInnerArea() {
        return innerArea;
    }

    /**
     * 套内面积
     * @param innerArea 套内面积
     */
    public void setInnerArea(BigDecimal innerArea) {
        this.innerArea = innerArea;
    }

    /**
     * 证载面积
     * @return evidence_area 证载面积
     */
    public BigDecimal getEvidenceArea() {
        return evidenceArea;
    }

    /**
     * 证载面积
     * @param evidenceArea 证载面积
     */
    public void setEvidenceArea(BigDecimal evidenceArea) {
        this.evidenceArea = evidenceArea;
    }

    /**
     * 其它
     * @return other 其它
     */
    public String getOther() {
        return other;
    }

    /**
     * 其它
     * @param other 其它
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * 土地证号
     * @return land_number 土地证号
     */
    public String getLandNumber() {
        return landNumber;
    }

    /**
     * 土地证号
     * @param landNumber 土地证号
     */
    public void setLandNumber(String landNumber) {
        this.landNumber = landNumber == null ? null : landNumber.trim();
    }

    /**
     * 土地取得方式
     * @return land_acquisition 土地取得方式
     */
    public String getLandAcquisition() {
        return landAcquisition;
    }

    /**
     * 土地取得方式
     * @param landAcquisition 土地取得方式
     */
    public void setLandAcquisition(String landAcquisition) {
        this.landAcquisition = landAcquisition == null ? null : landAcquisition.trim();
    }

    /**
     * 土地使用开始日期
     * @return use_start_date 土地使用开始日期
     */
    public Date getUseStartDate() {
        return useStartDate;
    }

    /**
     * 土地使用开始日期
     * @param useStartDate 土地使用开始日期
     */
    public void setUseStartDate(Date useStartDate) {
        this.useStartDate = useStartDate;
    }

    /**
     * 土地使用结束日期
     * @return use_end_date 土地使用结束日期
     */
    public Date getUseEndDate() {
        return useEndDate;
    }

    /**
     * 土地使用结束日期
     * @param useEndDate 土地使用结束日期
     */
    public void setUseEndDate(Date useEndDate) {
        this.useEndDate = useEndDate;
    }

    /**
     * 公摊面积
     * @return public_area 公摊面积
     */
    public BigDecimal getPublicArea() {
        return publicArea;
    }

    /**
     * 公摊面积
     * @param publicArea 公摊面积
     */
    public void setPublicArea(BigDecimal publicArea) {
        this.publicArea = publicArea;
    }

    /**
     * 附记其它
     * @return other_note 附记其它
     */
    public String getOtherNote() {
        return otherNote;
    }

    /**
     * 附记其它
     * @param otherNote 附记其它
     */
    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote == null ? null : otherNote.trim();
    }

    /**
     * 登记机关
     * @return registration_authority 登记机关
     */
    public String getRegistrationAuthority() {
        return registrationAuthority;
    }

    /**
     * 登记机关
     * @param registrationAuthority 登记机关
     */
    public void setRegistrationAuthority(String registrationAuthority) {
        this.registrationAuthority = registrationAuthority == null ? null : registrationAuthority.trim();
    }

    /**
     * 登记日期土地
     * @return land_registration_date 登记日期土地
     */
    public Date getLandRegistrationDate() {
        return landRegistrationDate;
    }

    /**
     * 登记日期土地
     * @param landRegistrationDate 登记日期土地
     */
    public void setLandRegistrationDate(Date landRegistrationDate) {
        this.landRegistrationDate = landRegistrationDate;
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
     * 申报证书类型
     * @return declare_type 申报证书类型
     */
    public String getDeclareType() {
        return declareType;
    }

    /**
     * 申报证书类型
     * @param declareType 申报证书类型
     */
    public void setDeclareType(String declareType) {
        this.declareType = declareType == null ? null : declareType.trim();
    }

    /**
     * 丘地号
     * @return ground_num 丘地号
     */
    public String getGroundNum() {
        return groundNum;
    }

    /**
     * 丘地号
     * @param groundNum 丘地号
     */
    public void setGroundNum(String groundNum) {
        this.groundNum = groundNum == null ? null : groundNum.trim();
    }

    /**
     * 分摊面积
     * @return apportionment_area 分摊面积
     */
    public BigDecimal getApportionmentArea() {
        return apportionmentArea;
    }

    /**
     * 分摊面积
     * @param apportionmentArea 分摊面积
     */
    public void setApportionmentArea(BigDecimal apportionmentArea) {
        this.apportionmentArea = apportionmentArea;
    }

    /**
     * 是否启用(不属于此实体信息的字段专用来区分某些数据的)
     * @return enable 是否启用(不属于此实体信息的字段专用来区分某些数据的)
     */
    public String getEnable() {
        return enable;
    }

    /**
     * 是否启用(不属于此实体信息的字段专用来区分某些数据的)
     * @param enable 是否启用(不属于此实体信息的字段专用来区分某些数据的)
     */
    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    /**
     * 是否写入到申报记录中
     * @return bis_record 是否写入到申报记录中
     */
    public Boolean getBisRecord() {
        return bisRecord;
    }

    /**
     * 是否写入到申报记录中
     * @param bisRecord 是否写入到申报记录中
     */
    public void setBisRecord(Boolean bisRecord) {
        this.bisRecord = bisRecord;
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

    public static DeclareRealtyHouseCert.Builder builder() {
        return new DeclareRealtyHouseCert.Builder();
    }

    /**
     * tb_declare_realty_house_cert
     */
    public static class Builder {
        /**
         * tb_declare_realty_house_cert
         */
        private DeclareRealtyHouseCert obj;

        public Builder() {
            this.obj = new DeclareRealtyHouseCert();
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
         * 
         * @param planDetailsId 
         */
        public Builder planDetailsId(Integer planDetailsId) {
            obj.setPlanDetailsId(planDetailsId);
            return this;
        }

        /**
         * 导入的编号
         * @param autoInitNumber 导入的编号
         */
        public Builder autoInitNumber(Integer autoInitNumber) {
            obj.setAutoInitNumber(autoInitNumber);
            return this;
        }

        /**
         * 房产权证号
         * @param certName 房产权证号
         */
        public Builder certName(String certName) {
            obj.setCertName(certName);
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
         * 房产证类型
         * @param type 房产证类型
         */
        public Builder type(String type) {
            obj.setType(type);
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
         * 房屋所有权人
         * @param ownership 房屋所有权人
         */
        public Builder ownership(String ownership) {
            obj.setOwnership(ownership);
            return this;
        }

        /**
         * 共有情况
         * @param publicSituation 共有情况
         */
        public Builder publicSituation(Integer publicSituation) {
            obj.setPublicSituation(publicSituation);
            return this;
        }

        /**
         * 共有情况说明
         * @param publicSituationRemark 共有情况说明
         */
        public Builder publicSituationRemark(String publicSituationRemark) {
            obj.setPublicSituationRemark(publicSituationRemark);
            return this;
        }

        /**
         * 坐落
         * @param beLocated 坐落
         */
        public Builder beLocated(String beLocated) {
            obj.setBeLocated(beLocated);
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

        public DeclareRealtyHouseCert build() {
            return this.obj;
        }

        /**
         * 幢号
         * @param buildingNumber 幢号
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
         * 建筑面积
         * @param floorArea 建筑面积
         */
        public Builder floorArea(String floorArea) {
            obj.setFloorArea(floorArea);
            return this;
        }

        /**
         * 总层数
         * @param floorCount 总层数
         */
        public Builder floorCount(String floorCount) {
            obj.setFloorCount(floorCount);
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
         * 登记时间
         * @param registrationTime 登记时间
         */
        public Builder registrationTime(Date registrationTime) {
            obj.setRegistrationTime(registrationTime);
            return this;
        }

        /**
         * 房屋性质
         * @param nature 房屋性质
         */
        public Builder nature(Integer nature) {
            obj.setNature(nature);
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
         * 房屋用途类别
         * @param certUseCategory 房屋用途类别
         */
        public Builder certUseCategory(String certUseCategory) {
            obj.setCertUseCategory(certUseCategory);
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
         * 所在地
         * @param location 所在地
         */
        public Builder location(String location) {
            obj.setLocation(location);
            return this;
        }

        /**
         * 套内面积
         * @param innerArea 套内面积
         */
        public Builder innerArea(BigDecimal innerArea) {
            obj.setInnerArea(innerArea);
            return this;
        }

        /**
         * 证载面积
         * @param evidenceArea 证载面积
         */
        public Builder evidenceArea(BigDecimal evidenceArea) {
            obj.setEvidenceArea(evidenceArea);
            return this;
        }

        /**
         * 其它
         * @param other 其它
         */
        public Builder other(String other) {
            obj.setOther(other);
            return this;
        }

        /**
         * 附记其它
         * @param otherNote 附记其它
         */
        public Builder otherNote(String otherNote) {
            obj.setOtherNote(otherNote);
            return this;
        }

        /**
         * 土地证号
         * @param landNumber 土地证号
         */
        public Builder landNumber(String landNumber) {
            obj.setLandNumber(landNumber);
            return this;
        }

        /**
         * 土地取得方式
         * @param landAcquisition 土地取得方式
         */
        public Builder landAcquisition(String landAcquisition) {
            obj.setLandAcquisition(landAcquisition);
            return this;
        }

        /**
         * 土地使用开始日期
         * @param useStartDate 土地使用开始日期
         */
        public Builder useStartDate(Date useStartDate) {
            obj.setUseStartDate(useStartDate);
            return this;
        }

        /**
         * 土地使用结束日期
         * @param useEndDate 土地使用结束日期
         */
        public Builder useEndDate(Date useEndDate) {
            obj.setUseEndDate(useEndDate);
            return this;
        }

        /**
         * 公摊面积
         * @param publicArea 公摊面积
         */
        public Builder publicArea(BigDecimal publicArea) {
            obj.setPublicArea(publicArea);
            return this;
        }

        /**
         * 登记机关
         * @param registrationAuthority 登记机关
         */
        public Builder registrationAuthority(String registrationAuthority) {
            obj.setRegistrationAuthority(registrationAuthority);
            return this;
        }

        /**
         * 登记日期土地
         * @param landRegistrationDate 登记日期土地
         */
        public Builder landRegistrationDate(Date landRegistrationDate) {
            obj.setLandRegistrationDate(landRegistrationDate);
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
         * 申报证书类型
         * @param declareType 申报证书类型
         */
        public Builder declareType(String declareType) {
            obj.setDeclareType(declareType);
            return this;
        }

        /**
         * 丘地号
         * @param groundNum 丘地号
         */
        public Builder groundNum(String groundNum) {
            obj.setGroundNum(groundNum);
            return this;
        }

        /**
         * 分摊面积
         * @param apportionmentArea 分摊面积
         */
        public Builder apportionmentArea(BigDecimal apportionmentArea) {
            obj.setApportionmentArea(apportionmentArea);
            return this;
        }

        /**
         * 是否启用(不属于此实体信息的字段专用来区分某些数据的)
         * @param enable 是否启用(不属于此实体信息的字段专用来区分某些数据的)
         */
        public Builder enable(String enable) {
            obj.setEnable(enable);
            return this;
        }

        /**
         * 是否写入到申报记录中
         * @param bisRecord 是否写入到申报记录中
         */
        public Builder bisRecord(Boolean bisRecord) {
            obj.setBisRecord(bisRecord);
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
        pid("pid", "pid", "INTEGER", false),
        planDetailsId("plan_details_id", "planDetailsId", "INTEGER", false),
        autoInitNumber("auto_init_number", "autoInitNumber", "INTEGER", false),
        certName("cert_name", "certName", "VARCHAR", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        type("type", "type", "VARCHAR", false),
        number("number", "number", "VARCHAR", false),
        ownership("ownership", "ownership", "VARCHAR", false),
        publicSituation("public_situation", "publicSituation", "INTEGER", false),
        publicSituationRemark("public_situation_remark", "publicSituationRemark", "VARCHAR", false),
        beLocated("be_located", "beLocated", "VARCHAR", false),
        streetNumber("street_number", "streetNumber", "VARCHAR", false),
        attachedNumber("attached_number", "attachedNumber", "VARCHAR", false),
        buildingNumber("building_number", "buildingNumber", "VARCHAR", false),
        unit("unit", "unit", "VARCHAR", false),
        floor("floor", "floor", "VARCHAR", false),
        roomNumber("room_number", "roomNumber", "VARCHAR", false),
        registrationTime("registration_time", "registrationTime", "TIMESTAMP", false),
        nature("nature", "nature", "INTEGER", false),
        floorCount("floor_count", "floorCount", "VARCHAR", false),
        certUse("cert_use", "certUse", "VARCHAR", false),
        certUseCategory("cert_use_category", "certUseCategory", "VARCHAR", false),
        housingStructure("housing_structure", "housingStructure", "VARCHAR", false),
        floorArea("floor_area", "floorArea", "VARCHAR", false),
        location("location", "location", "VARCHAR", false),
        innerArea("inner_area", "innerArea", "DECIMAL", false),
        evidenceArea("evidence_area", "evidenceArea", "DECIMAL", false),
        other("other", "other", "VARCHAR", false),
        landNumber("land_number", "landNumber", "VARCHAR", false),
        landAcquisition("land_acquisition", "landAcquisition", "VARCHAR", false),
        useStartDate("use_start_date", "useStartDate", "TIMESTAMP", false),
        useEndDate("use_end_date", "useEndDate", "TIMESTAMP", false),
        publicArea("public_area", "publicArea", "DECIMAL", false),
        otherNote("other_note", "otherNote", "VARCHAR", false),
        registrationAuthority("registration_authority", "registrationAuthority", "VARCHAR", false),
        landRegistrationDate("land_registration_date", "landRegistrationDate", "TIMESTAMP", false),
        registrationDate("registration_date", "registrationDate", "TIMESTAMP", false),
        declareType("declare_type", "declareType", "VARCHAR", false),
        groundNum("ground_num", "groundNum", "VARCHAR", false),
        apportionmentArea("apportionment_area", "apportionmentArea", "DECIMAL", false),
        enable("enable", "enable", "VARCHAR", false),
        bisRecord("bis_record", "bisRecord", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_declare_realty_house_cert
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_realty_house_cert
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_realty_house_cert
         */
        private final String column;

        /**
         * tb_declare_realty_house_cert
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_realty_house_cert
         */
        private final String javaProperty;

        /**
         * tb_declare_realty_house_cert
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