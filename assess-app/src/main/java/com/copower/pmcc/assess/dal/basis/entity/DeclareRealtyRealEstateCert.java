package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DeclareRealtyRealEstateCert implements Serializable {
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
     * 权证有无
     */
    private Integer landCertGetQuestion;

    /**
     * 权证号或者批文文号
     */
    private String certName;

    /**
     * 所在地
     */
    private String location;

    /**
     * 座落
     */
    private String beLocated;

    /**
     * 编号
     */
    private String number;

    /**
     * 年份
     */
    private String year;

    /**
     * 名称
     */
    private String name;

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
     * 房屋用途
     */
    private String houseCertUse;

    /**
     * 房屋用途类别
     */
    private String houseCertUseCategory;

    /**
     * 房屋结构
     */
    private String housingStructure;

    /**
     * 总层数
     */
    private String floorCount;

    /**
     * 建筑面积
     */
    private BigDecimal floorArea;

    /**
     * 证载面积
     */
    private BigDecimal evidenceArea;

    /**
     * 套内面积
     */
    private BigDecimal innerArea;

    /**
     * 其它
     */
    private String other;

    /**
     * 地号
     */
    private String landNumber;

    /**
     * 图号
     */
    private String graphNumber;

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
     * 登记日期
     */
    private Date registrationDate;

    /**
     * 权利性质(划拨和出证)
     */
    private Integer landRightNature;

    /**
     * 土地权利类型
     */
    private Integer landRightType;

    /**
     * 取得价格
     */
    private BigDecimal acquisitionPrice;

    /**
     * 土地用途
     */
    private String landCertUse;

    /**
     * 土地用途类型
     */
    private String landCertUseCategory;

    /**
     * 土地取得方式
     */
    private String acquisitionType;

    /**
     * 终止日期
     */
    private Date terminationDate;

    /**
     * 记事
     */
    private String memo;

    /**
     * 分摊面积
     */
    private BigDecimal apportionmentArea;

    /**
     * 独用面积
     */
    private BigDecimal acreage;

    /**
     * 使用权面积
     */
    private BigDecimal useRightArea;

    /**
     * 是否启用(不属于此实体信息的字段专用来区分某些数据的)
     */
    private String enable;

    /**
     * 申报证书类型
     */
    private String declareType;

    /**
     * 批文机关
     */
    private String approvalMechanism;

    /**
     * 批文时间
     */
    private Date approvalTime;

    /**
     * 批文名称
     */
    private String approvalName;

    /**
     * 不动产单元号
     */
    private String realEstateUnitNumber;

    /**
     * 土地分摊面积
     */
    private BigDecimal landApportionArea;

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
     * tb_declare_realty_real_estate_cert
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
     * 权证号或者批文文号
     * @return cert_name 权证号或者批文文号
     */
    public String getCertName() {
        return certName;
    }

    /**
     * 权证号或者批文文号
     * @param certName 权证号或者批文文号
     */
    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
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
     * 座落
     * @return be_located 座落
     */
    public String getBeLocated() {
        return beLocated;
    }

    /**
     * 座落
     * @param beLocated 座落
     */
    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated == null ? null : beLocated.trim();
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
     * 房屋用途
     * @return house_cert_use 房屋用途
     */
    public String getHouseCertUse() {
        return houseCertUse;
    }

    /**
     * 房屋用途
     * @param houseCertUse 房屋用途
     */
    public void setHouseCertUse(String houseCertUse) {
        this.houseCertUse = houseCertUse == null ? null : houseCertUse.trim();
    }

    /**
     * 房屋用途类别
     * @return house_cert_use_category 房屋用途类别
     */
    public String getHouseCertUseCategory() {
        return houseCertUseCategory;
    }

    /**
     * 房屋用途类别
     * @param houseCertUseCategory 房屋用途类别
     */
    public void setHouseCertUseCategory(String houseCertUseCategory) {
        this.houseCertUseCategory = houseCertUseCategory == null ? null : houseCertUseCategory.trim();
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
     * 土地权利类型
     * @return land_right_type 土地权利类型
     */
    public Integer getLandRightType() {
        return landRightType;
    }

    /**
     * 土地权利类型
     * @param landRightType 土地权利类型
     */
    public void setLandRightType(Integer landRightType) {
        this.landRightType = landRightType;
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
     * 土地用途
     * @return land_cert_use 土地用途
     */
    public String getLandCertUse() {
        return landCertUse;
    }

    /**
     * 土地用途
     * @param landCertUse 土地用途
     */
    public void setLandCertUse(String landCertUse) {
        this.landCertUse = landCertUse == null ? null : landCertUse.trim();
    }

    /**
     * 土地用途类型
     * @return land_cert_use_category 土地用途类型
     */
    public String getLandCertUseCategory() {
        return landCertUseCategory;
    }

    /**
     * 土地用途类型
     * @param landCertUseCategory 土地用途类型
     */
    public void setLandCertUseCategory(String landCertUseCategory) {
        this.landCertUseCategory = landCertUseCategory == null ? null : landCertUseCategory.trim();
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
     * 不动产单元号
     * @return real_estate_unit_number 不动产单元号
     */
    public String getRealEstateUnitNumber() {
        return realEstateUnitNumber;
    }

    /**
     * 不动产单元号
     * @param realEstateUnitNumber 不动产单元号
     */
    public void setRealEstateUnitNumber(String realEstateUnitNumber) {
        this.realEstateUnitNumber = realEstateUnitNumber == null ? null : realEstateUnitNumber.trim();
    }

    /**
     * 土地分摊面积
     * @return land_apportion_area 土地分摊面积
     */
    public BigDecimal getLandApportionArea() {
        return landApportionArea;
    }

    /**
     * 土地分摊面积
     * @param landApportionArea 土地分摊面积
     */
    public void setLandApportionArea(BigDecimal landApportionArea) {
        this.landApportionArea = landApportionArea;
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

    public static DeclareRealtyRealEstateCert.Builder builder() {
        return new DeclareRealtyRealEstateCert.Builder();
    }

    /**
     * tb_declare_realty_real_estate_cert
     */
    public static class Builder {
        /**
         * tb_declare_realty_real_estate_cert
         */
        private DeclareRealtyRealEstateCert obj;

        public Builder() {
            this.obj = new DeclareRealtyRealEstateCert();
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
         * 权证有无
         * @param landCertGetQuestion 权证有无
         */
        public Builder landCertGetQuestion(Integer landCertGetQuestion) {
            obj.setLandCertGetQuestion(landCertGetQuestion);
            return this;
        }

        /**
         * 权证号或者批文文号
         * @param certName 权证号或者批文文号
         */
        public Builder certName(String certName) {
            obj.setCertName(certName);
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
         * 座落
         * @param beLocated 座落
         */
        public Builder beLocated(String beLocated) {
            obj.setBeLocated(beLocated);
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
         * 年份
         * @param year 年份
         */
        public Builder year(String year) {
            obj.setYear(year);
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

        public DeclareRealtyRealEstateCert build() {
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
        public Builder floorArea(BigDecimal floorArea) {
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
         * 房屋用途
         * @param houseCertUse 房屋用途
         */
        public Builder houseCertUse(String houseCertUse) {
            obj.setHouseCertUse(houseCertUse);
            return this;
        }

        /**
         * 房屋用途类别
         * @param houseCertUseCategory 房屋用途类别
         */
        public Builder houseCertUseCategory(String houseCertUseCategory) {
            obj.setHouseCertUseCategory(houseCertUseCategory);
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
         * 证载面积
         * @param evidenceArea 证载面积
         */
        public Builder evidenceArea(BigDecimal evidenceArea) {
            obj.setEvidenceArea(evidenceArea);
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
         * 地号
         * @param landNumber 地号
         */
        public Builder landNumber(String landNumber) {
            obj.setLandNumber(landNumber);
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
         * 登记日期
         * @param registrationDate 登记日期
         */
        public Builder registrationDate(Date registrationDate) {
            obj.setRegistrationDate(registrationDate);
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
         * 土地权利类型
         * @param landRightType 土地权利类型
         */
        public Builder landRightType(Integer landRightType) {
            obj.setLandRightType(landRightType);
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
         * 土地用途
         * @param landCertUse 土地用途
         */
        public Builder landCertUse(String landCertUse) {
            obj.setLandCertUse(landCertUse);
            return this;
        }

        /**
         * 土地用途类型
         * @param landCertUseCategory 土地用途类型
         */
        public Builder landCertUseCategory(String landCertUseCategory) {
            obj.setLandCertUseCategory(landCertUseCategory);
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
         * 终止日期
         * @param terminationDate 终止日期
         */
        public Builder terminationDate(Date terminationDate) {
            obj.setTerminationDate(terminationDate);
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
         * 分摊面积
         * @param apportionmentArea 分摊面积
         */
        public Builder apportionmentArea(BigDecimal apportionmentArea) {
            obj.setApportionmentArea(apportionmentArea);
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
         * 使用权面积
         * @param useRightArea 使用权面积
         */
        public Builder useRightArea(BigDecimal useRightArea) {
            obj.setUseRightArea(useRightArea);
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
         * 申报证书类型
         * @param declareType 申报证书类型
         */
        public Builder declareType(String declareType) {
            obj.setDeclareType(declareType);
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
         * 批文时间
         * @param approvalTime 批文时间
         */
        public Builder approvalTime(Date approvalTime) {
            obj.setApprovalTime(approvalTime);
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
         * 不动产单元号
         * @param realEstateUnitNumber 不动产单元号
         */
        public Builder realEstateUnitNumber(String realEstateUnitNumber) {
            obj.setRealEstateUnitNumber(realEstateUnitNumber);
            return this;
        }

        /**
         * 土地分摊面积
         * @param landApportionArea 土地分摊面积
         */
        public Builder landApportionArea(BigDecimal landApportionArea) {
            obj.setLandApportionArea(landApportionArea);
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
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        landCertGetQuestion("land_cert_get_question", "landCertGetQuestion", "INTEGER", false),
        certName("cert_name", "certName", "VARCHAR", false),
        location("location", "location", "VARCHAR", false),
        beLocated("be_located", "beLocated", "VARCHAR", false),
        number("number", "number", "VARCHAR", false),
        year("year", "year", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        ownership("ownership", "ownership", "VARCHAR", false),
        publicSituation("public_situation", "publicSituation", "INTEGER", false),
        publicSituationRemark("public_situation_remark", "publicSituationRemark", "VARCHAR", false),
        streetNumber("street_number", "streetNumber", "VARCHAR", false),
        attachedNumber("attached_number", "attachedNumber", "VARCHAR", false),
        buildingNumber("building_number", "buildingNumber", "VARCHAR", false),
        unit("unit", "unit", "VARCHAR", false),
        floor("floor", "floor", "VARCHAR", false),
        roomNumber("room_number", "roomNumber", "VARCHAR", false),
        registrationTime("registration_time", "registrationTime", "TIMESTAMP", false),
        nature("nature", "nature", "INTEGER", false),
        houseCertUse("house_cert_use", "houseCertUse", "VARCHAR", false),
        houseCertUseCategory("house_cert_use_category", "houseCertUseCategory", "VARCHAR", false),
        housingStructure("housing_structure", "housingStructure", "VARCHAR", false),
        floorCount("floor_count", "floorCount", "VARCHAR", false),
        floorArea("floor_area", "floorArea", "DECIMAL", false),
        evidenceArea("evidence_area", "evidenceArea", "DECIMAL", false),
        innerArea("inner_area", "innerArea", "DECIMAL", false),
        other("other", "other", "VARCHAR", false),
        landNumber("land_number", "landNumber", "VARCHAR", false),
        graphNumber("graph_number", "graphNumber", "VARCHAR", false),
        landAcquisition("land_acquisition", "landAcquisition", "VARCHAR", false),
        useStartDate("use_start_date", "useStartDate", "TIMESTAMP", false),
        useEndDate("use_end_date", "useEndDate", "TIMESTAMP", false),
        publicArea("public_area", "publicArea", "DECIMAL", false),
        otherNote("other_note", "otherNote", "VARCHAR", false),
        registrationAuthority("registration_authority", "registrationAuthority", "VARCHAR", false),
        registrationDate("registration_date", "registrationDate", "TIMESTAMP", false),
        landRightNature("land_right_nature", "landRightNature", "INTEGER", false),
        landRightType("land_right_type", "landRightType", "INTEGER", false),
        acquisitionPrice("acquisition_price", "acquisitionPrice", "DECIMAL", false),
        landCertUse("land_cert_use", "landCertUse", "VARCHAR", false),
        landCertUseCategory("land_cert_use_category", "landCertUseCategory", "VARCHAR", false),
        acquisitionType("acquisition_type", "acquisitionType", "VARCHAR", false),
        terminationDate("termination_date", "terminationDate", "TIMESTAMP", false),
        memo("memo", "memo", "VARCHAR", false),
        apportionmentArea("apportionment_area", "apportionmentArea", "DECIMAL", false),
        acreage("acreage", "acreage", "DECIMAL", false),
        useRightArea("use_right_area", "useRightArea", "DECIMAL", false),
        enable("enable", "enable", "VARCHAR", false),
        declareType("declare_type", "declareType", "VARCHAR", false),
        approvalMechanism("approval_mechanism", "approvalMechanism", "VARCHAR", false),
        approvalTime("approval_time", "approvalTime", "TIMESTAMP", false),
        approvalName("approval_name", "approvalName", "VARCHAR", false),
        realEstateUnitNumber("real_estate_unit_number", "realEstateUnitNumber", "VARCHAR", false),
        landApportionArea("land_apportion_area", "landApportionArea", "DECIMAL", false),
        bisRecord("bis_record", "bisRecord", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_declare_realty_real_estate_cert
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_declare_realty_real_estate_cert
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_declare_realty_real_estate_cert
         */
        private final String column;

        /**
         * tb_declare_realty_real_estate_cert
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_declare_realty_real_estate_cert
         */
        private final String javaProperty;

        /**
         * tb_declare_realty_real_estate_cert
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