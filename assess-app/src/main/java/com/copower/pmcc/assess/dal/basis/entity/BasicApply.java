package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicApply {
    private Integer id;

    private String name;

    private Integer batchDetailId;

    private String structuralInfo;

    private String address;

    private Integer caseEstateId;

    private Integer caseBuildingId;

    private Integer caseUnitId;

    private Integer caseHouseId;

    private Integer declareRecordId;

    private Integer basicEstateId;

    private Integer basicBuildingId;

    private Integer basicUnitId;

    private Integer basicHouseId;

    private Integer planDetailsId;

    private BigDecimal area;

    private String processInsId;

    private String province;

    private String city;

    private Integer type;

    private String estateName;

    private String buildingNumber;

    private String unitNumber;

    private String houseNumber;

    private String status;

    private Boolean draftFlag;

    private String estatePartInMode;

    private String buildingPartInMode;

    private String unitPartInMode;

    private String housePartInMode;

    private Boolean writeBackBlockFlag;

    private Integer copyId;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBatchDetailId() {
        return batchDetailId;
    }

    public void setBatchDetailId(Integer batchDetailId) {
        this.batchDetailId = batchDetailId;
    }

    public String getStructuralInfo() {
        return structuralInfo;
    }

    public void setStructuralInfo(String structuralInfo) {
        this.structuralInfo = structuralInfo == null ? null : structuralInfo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCaseEstateId() {
        return caseEstateId;
    }

    public void setCaseEstateId(Integer caseEstateId) {
        this.caseEstateId = caseEstateId;
    }

    public Integer getCaseBuildingId() {
        return caseBuildingId;
    }

    public void setCaseBuildingId(Integer caseBuildingId) {
        this.caseBuildingId = caseBuildingId;
    }

    public Integer getCaseUnitId() {
        return caseUnitId;
    }

    public void setCaseUnitId(Integer caseUnitId) {
        this.caseUnitId = caseUnitId;
    }

    public Integer getCaseHouseId() {
        return caseHouseId;
    }

    public void setCaseHouseId(Integer caseHouseId) {
        this.caseHouseId = caseHouseId;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public Integer getBasicEstateId() {
        return basicEstateId;
    }

    public void setBasicEstateId(Integer basicEstateId) {
        this.basicEstateId = basicEstateId;
    }

    public Integer getBasicBuildingId() {
        return basicBuildingId;
    }

    public void setBasicBuildingId(Integer basicBuildingId) {
        this.basicBuildingId = basicBuildingId;
    }

    public Integer getBasicUnitId() {
        return basicUnitId;
    }

    public void setBasicUnitId(Integer basicUnitId) {
        this.basicUnitId = basicUnitId;
    }

    public Integer getBasicHouseId() {
        return basicHouseId;
    }

    public void setBasicHouseId(Integer basicHouseId) {
        this.basicHouseId = basicHouseId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName == null ? null : estateName.trim();
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber == null ? null : unitNumber.trim();
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getDraftFlag() {
        return draftFlag;
    }

    public void setDraftFlag(Boolean draftFlag) {
        this.draftFlag = draftFlag;
    }

    public String getEstatePartInMode() {
        return estatePartInMode;
    }

    public void setEstatePartInMode(String estatePartInMode) {
        this.estatePartInMode = estatePartInMode == null ? null : estatePartInMode.trim();
    }

    public String getBuildingPartInMode() {
        return buildingPartInMode;
    }

    public void setBuildingPartInMode(String buildingPartInMode) {
        this.buildingPartInMode = buildingPartInMode == null ? null : buildingPartInMode.trim();
    }

    public String getUnitPartInMode() {
        return unitPartInMode;
    }

    public void setUnitPartInMode(String unitPartInMode) {
        this.unitPartInMode = unitPartInMode == null ? null : unitPartInMode.trim();
    }

    public String getHousePartInMode() {
        return housePartInMode;
    }

    public void setHousePartInMode(String housePartInMode) {
        this.housePartInMode = housePartInMode == null ? null : housePartInMode.trim();
    }

    public Boolean getWriteBackBlockFlag() {
        return writeBackBlockFlag;
    }

    public void setWriteBackBlockFlag(Boolean writeBackBlockFlag) {
        this.writeBackBlockFlag = writeBackBlockFlag;
    }

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}