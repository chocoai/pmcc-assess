package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareRealtyLandCert implements Serializable {
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
    private Integer planDetailsId;

    /**
     * 导入的编号
     */
    private Integer autoInitNumber;

    /**
     * 权证有无
     */
    private Integer landCertGetQuestion;

    /**
     * 土地权证号
     */
    private String landCertName;

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
     * 权利类型 （国用 集用）
     */
    private Integer landRightType;

    /**
     * 所在地
     */
    private String location;

    /**
     * 年份
     */
    private String year;

    /**
     * 编号
     */
    private String number;

    /**
     * 土地使用权人
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
     * 地号
     */
    private String landNumber;

    /**
     * 证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）
     */
    private String certUse;

    /**
     * 证载用途类别
     */
    private String certUseCategory;

    /**
     * 结构
     */
    private String housingStructure;

    /**
     * 图号
     */
    private String graphNumber;

    /**
     * 权利性质(划拨和出证)
     */
    private Integer landRightNature;

    /**
     * 取得价格
     */
    private BigDecimal acquisitionPrice;

    /**
     * 终止日期
     */
    private Date terminationDate;

    /**
     * 使用权面积
     */
    private BigDecimal useRightArea;

    /**
     * 独用面积
     */
    private BigDecimal acreage;

    /**
     * 分摊面积
     */
    private BigDecimal apportionmentArea;

    /**
     * 记事
     */
    private String memo;

    /**
     * 登记机关
     */
    private String registrationAuthority;

    /**
     * 登记日期
     */
    private Date registrationDate;

    /**
     * 批文名称
     */
    private String approvalName;

    /**
     * 批文时间
     */
    private Date approvalTime;

    /**
     * 批文机关
     */
    private String approvalMechanism;

    /**
     * 申报证书类型
     */
    private String declareType;

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
     * tb_declare_realty_land_cert
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
     * @return plan_details_id 主表id
     */
    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    /**
     * 主表id
     * @param planDetailsId 主表id
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
     * 权证有无
     * @return land_cert_get_question 权证有无
     */
    public Integer getLandCertGetQuestion() {
        return landCertGetQuestion;
    }

    /**
     * 权证有无
     * @param landCertGetQuestion 权证有无
     */
    public void setLandCertGetQuestion(Integer landCertGetQuestion) {
        this.landCertGetQuestion = landCertGetQuestion;
    }

    /**
     * 土地权证号
     * @return land_cert_name 土地权证号
     */
    public String getLandCertName() {
        return landCertName;
    }

    /**
     * 土地权证号
     * @param landCertName 土地权证号
     */
    public void setLandCertName(String landCertName) {
        this.landCertName = landCertName == null ? null : landCertName.trim();
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
     * 权利类型 （国用 集用）
     * @return land_right_type 权利类型 （国用 集用）
     */
    public Integer getLandRightType() {
        return landRightType;
    }

    /**
     * 权利类型 （国用 集用）
     * @param landRightType 权利类型 （国用 集用）
     */
    public void setLandRightType(Integer landRightType) {
        this.landRightType = landRightType;
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
     * 年份
     * @return year 年份
     */
    public String getYear() {
        return year;
    }

    /**
     * 年份
     * @param year 年份
     */
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
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
     * 土地使用权人
     * @return ownership 土地使用权人
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * 土地使用权人
     * @param ownership 土地使用权人
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
     * 地号
     * @return land_number 地号
     */
    public String getLandNumber() {
        return landNumber;
    }

    /**
     * 地号
     * @param landNumber 地号
     */
    public void setLandNumber(String landNumber) {
        this.landNumber = landNumber == null ? null : landNumber.trim();
    }

    /**
     * 证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）
     * @return cert_use 证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）
     */
    public String getCertUse() {
        return certUse;
    }

    /**
     * 证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）
     * @param certUse 证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）
     */
    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    /**
     * 证载用途类别
     * @return cert_use_category 证载用途类别
     */
    public String getCertUseCategory() {
        return certUseCategory;
    }

    /**
     * 证载用途类别
     * @param certUseCategory 证载用途类别
     */
    public void setCertUseCategory(String certUseCategory) {
        this.certUseCategory = certUseCategory == null ? null : certUseCategory.trim();
    }

    /**
     * 结构
     * @return housing_structure 结构
     */
    public String getHousingStructure() {
        return housingStructure;
    }

    /**
     * 结构
     * @param housingStructure 结构
     */
    public void setHousingStructure(String housingStructure) {
        this.housingStructure = housingStructure == null ? null : housingStructure.trim();
    }

    /**
     * 图号
     * @return graph_number 图号
     */
    public String getGraphNumber() {
        return graphNumber;
    }

    /**
     * 图号
     * @param graphNumber 图号
     */
    public void setGraphNumber(String graphNumber) {
        this.graphNumber = graphNumber == null ? null : graphNumber.trim();
    }

    /**
     * 权利性质(划拨和出证)
     * @return land_right_nature 权利性质(划拨和出证)
     */
    public Integer getLandRightNature() {
        return landRightNature;
    }

    /**
     * 权利性质(划拨和出证)
     * @param landRightNature 权利性质(划拨和出证)
     */
    public void setLandRightNature(Integer landRightNature) {
        this.landRightNature = landRightNature;
    }

    /**
     * 取得价格
     * @return acquisition_price 取得价格
     */
    public BigDecimal getAcquisitionPrice() {
        return acquisitionPrice;
    }

    /**
     * 取得价格
     * @param acquisitionPrice 取得价格
     */
    public void setAcquisitionPrice(BigDecimal acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    /**
     * 终止日期
     * @return termination_date 终止日期
     */
    public Date getTerminationDate() {
        return terminationDate;
    }

    /**
     * 终止日期
     * @param terminationDate 终止日期
     */
    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    /**
     * 使用权面积
     * @return use_right_area 使用权面积
     */
    public BigDecimal getUseRightArea() {
        return useRightArea;
    }

    /**
     * 使用权面积
     * @param useRightArea 使用权面积
     */
    public void setUseRightArea(BigDecimal useRightArea) {
        this.useRightArea = useRightArea;
    }

    /**
     * 独用面积
     * @return acreage 独用面积
     */
    public BigDecimal getAcreage() {
        return acreage;
    }

    /**
     * 独用面积
     * @param acreage 独用面积
     */
    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
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
     * 记事
     * @return memo 记事
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 记事
     * @param memo 记事
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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
     * 批文名称
     * @return approval_name 批文名称
     */
    public String getApprovalName() {
        return approvalName;
    }

    /**
     * 批文名称
     * @param approvalName 批文名称
     */
    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName == null ? null : approvalName.trim();
    }

    /**
     * 批文时间
     * @return approval_time 批文时间
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * 批文时间
     * @param approvalTime 批文时间
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * 批文机关
     * @return approval_mechanism 批文机关
     */
    public String getApprovalMechanism() {
        return approvalMechanism;
    }

    /**
     * 批文机关
     * @param approvalMechanism 批文机关
     */
    public void setApprovalMechanism(String approvalMechanism) {
        this.approvalMechanism = approvalMechanism == null ? null : approvalMechanism.trim();
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

    public static DeclareRealtyLandCert.Builder builder() {
        return new DeclareRealtyLandCert.Builder();
    }

    /**
     * tb_declare_realty_land_cert
     */
    public static class Builder {
        /**
         * tb_declare_realty_land_cert
         */
        private DeclareRealtyLandCert obj;

        public Builder() {
            this.obj = new DeclareRealtyLandCert();
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
         * @param planDetailsId 主表id
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
         * 权证有无
         * @param landCertGetQuestion 权证有无
         */
        public Builder landCertGetQuestion(Integer landCertGetQuestion) {
            obj.setLandCertGetQuestion(landCertGetQuestion);
            return this;
        }

        /**
         * 土地权证号
         * @param landCertName 土地权证号
         */
        public Builder landCertName(String landCertName) {
            obj.setLandCertName(landCertName);
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
         * 权利类型 （国用 集用）
         * @param landRightType 权利类型 （国用 集用）
         */
        public Builder landRightType(Integer landRightType) {
            obj.setLandRightType(landRightType);
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
         * 年份
         * @param year 年份
         */
        public Builder year(String year) {
            obj.setYear(year);
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
         * 土地使用权人
         * @param ownership 土地使用权人
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

        public DeclareRealtyLandCert build() {
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
         * 房号
         * @param roomNumber 房号
         */
        public Builder roomNumber(String roomNumber) {
            obj.setRoomNumber(roomNumber);
            return this;
        }

        /**
         * 地号
         * @param landNumber 地号
         */
        public Builder landNumber(String landNumber) {
            obj.setLandNumber(landNumber);
            return this;
        }

        /**
         * 证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）
         * @param certUse 证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）
         */
        public Builder certUse(String certUse) {
            obj.setCertUse(certUse);
            return this;
        }

        /**
         * 证载用途类别
         * @param certUseCategory 证载用途类别
         */
        public Builder certUseCategory(String certUseCategory) {
            obj.setCertUseCategory(certUseCategory);
            return this;
        }

        /**
         * 结构
         * @param housingStructure 结构
         */
        public Builder housingStructure(String housingStructure) {
            obj.setHousingStructure(housingStructure);
            return this;
        }

        /**
         * 图号
         * @param graphNumber 图号
         */
        public Builder graphNumber(String graphNumber) {
            obj.setGraphNumber(graphNumber);
            return this;
        }

        /**
         * 权利性质(划拨和出证)
         * @param landRightNature 权利性质(划拨和出证)
         */
        public Builder landRightNature(Integer landRightNature) {
            obj.setLandRightNature(landRightNature);
            return this;
        }

        /**
         * 取得价格
         * @param acquisitionPrice 取得价格
         */
        public Builder acquisitionPrice(BigDecimal acquisitionPrice) {
            obj.setAcquisitionPrice(acquisitionPrice);
            return this;
        }

        /**
         * 终止日期
         * @param terminationDate 终止日期
         */
        public Builder terminationDate(Date terminationDate) {
            obj.setTerminationDate(terminationDate);
            return this;
        }

        /**
         * 使用权面积
         * @param useRightArea 使用权面积
         */
        public Builder useRightArea(BigDecimal useRightArea) {
            obj.setUseRightArea(useRightArea);
            return this;
        }

        /**
         * 独用面积
         * @param acreage 独用面积
         */
        public Builder acreage(BigDecimal acreage) {
            obj.setAcreage(acreage);
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
         * 记事
         * @param memo 记事
         */
        public Builder memo(String memo) {
            obj.setMemo(memo);
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
         * 登记日期
         * @param registrationDate 登记日期
         */
        public Builder registrationDate(Date registrationDate) {
            obj.setRegistrationDate(registrationDate);
            return this;
        }

        /**
         * 批文名称
         * @param approvalName 批文名称
         */
        public Builder approvalName(String approvalName) {
            obj.setApprovalName(approvalName);
            return this;
        }

        /**
         * 批文时间
         * @param approvalTime 批文时间
         */
        public Builder approvalTime(Date approvalTime) {
            obj.setApprovalTime(approvalTime);
            return this;
        }

        /**
         * 批文机关
         * @param approvalMechanism 批文机关
         */
        public Builder approvalMechanism(String approvalMechanism) {
            obj.setApprovalMechanism(approvalMechanism);
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
        landCertGetQuestion("land_cert_get_question", "landCertGetQuestion", "INTEGER", false),
        landCertName("land_cert_name", "landCertName", "VARCHAR", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        landRightType("land_right_type", "landRightType", "INTEGER", false),
        location("location", "location", "VARCHAR", false),
        year("year", "year", "VARCHAR", false),
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
        landNumber("land_number", "landNumber", "VARCHAR", false),
        certUse("cert_use", "certUse", "VARCHAR", false),
        certUseCategory("cert_use_category", "certUseCategory", "VARCHAR", false),
        housingStructure("housing_structure", "housingStructure", "VARCHAR", false),
        graphNumber("graph_number", "graphNumber", "VARCHAR", false),
        landRightNature("land_right_nature", "landRightNature", "INTEGER", false),
        acquisitionPrice("acquisition_price", "acquisitionPrice", "DECIMAL", false),
        terminationDate("termination_date", "terminationDate", "TIMESTAMP", false),
        useRightArea("use_right_area", "useRightArea", "DECIMAL", false),
        acreage("acreage", "acreage", "DECIMAL", false),
        apportionmentArea("apportionment_area", "apportionmentArea", "DECIMAL", false),
        memo("memo", "memo", "VARCHAR", false),
        registrationAuthority("registration_authority", "registrationAuthority", "VARCHAR", false),
        registrationDate("registration_date", "registrationDate", "TIMESTAMP", false),
        approvalName("approval_name", "approvalName", "VARCHAR", false),
        approvalTime("approval_time", "approvalTime", "TIMESTAMP", false),
        approvalMechanism("approval_mechanism", "approvalMechanism", "VARCHAR", false),
        declareType("declare_type", "declareType", "VARCHAR", false),
        enable("enable", "enable", "VARCHAR", false),
        bisRecord("bis_record", "bisRecord", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_declare_realty_land_cert
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_realty_land_cert
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_realty_land_cert
         */
        private final String column;

        /**
         * tb_declare_realty_land_cert
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_realty_land_cert
         */
        private final String javaProperty;

        /**
         * tb_declare_realty_land_cert
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