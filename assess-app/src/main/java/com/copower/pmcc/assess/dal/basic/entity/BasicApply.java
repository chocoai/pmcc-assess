package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicApply {
    private Integer id;

    private String processInsId;

    private Integer type;

    private String estateName;

    private String buildingNumber;

    private String unitNumber;

    private String houseNumber;

    private String status;

    private Boolean draftFlag;

    private Integer caseEstateId;

    private Integer caseBuildingMainId;

    private Integer caseUnitId;

    private Integer caseHouseId;

    private Boolean estatePartInFlag;

    private Boolean buildingPartInFlag;

    private Boolean unitPartInFlag;

    private Boolean housePartInFlag;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
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

    public Integer getCaseEstateId() {
        return caseEstateId;
    }

    public void setCaseEstateId(Integer caseEstateId) {
        this.caseEstateId = caseEstateId;
    }

    public Integer getCaseBuildingMainId() {
        return caseBuildingMainId;
    }

    public void setCaseBuildingMainId(Integer caseBuildingMainId) {
        this.caseBuildingMainId = caseBuildingMainId;
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

    public Boolean getEstatePartInFlag() {
        return estatePartInFlag;
    }

    public void setEstatePartInFlag(Boolean estatePartInFlag) {
        this.estatePartInFlag = estatePartInFlag;
    }

    public Boolean getBuildingPartInFlag() {
        return buildingPartInFlag;
    }

    public void setBuildingPartInFlag(Boolean buildingPartInFlag) {
        this.buildingPartInFlag = buildingPartInFlag;
    }

    public Boolean getUnitPartInFlag() {
        return unitPartInFlag;
    }

    public void setUnitPartInFlag(Boolean unitPartInFlag) {
        this.unitPartInFlag = unitPartInFlag;
    }

    public Boolean getHousePartInFlag() {
        return housePartInFlag;
    }

    public void setHousePartInFlag(Boolean housePartInFlag) {
        this.housePartInFlag = housePartInFlag;
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