package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportLandAssociation {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型
    private String unitName;//报告使用单位
    private String numberValue;//估价报告编号
    private String projectName;//项目名称
    private String serviceComeFrom;//业务来源
    private String serviceComeFromName;
    private String entrustPurposeName;//估价目的
    private String methodNames;//评估方法
    private Date investigationsStartDate;//估价作业开始日
    private Date homeWorkEndTime;//估价作业结束日
    private Date valuationDate;//估价时点
    private Date gmtCreated;
    private BigDecimal landArea;//土地面积
    private BigDecimal evaluationArea;//建筑面积
    private BigDecimal assessTotal;//评估总值
    private BigDecimal price;//评估单价
    private String seat;//估价对象位置

    private String consignor;//委托人
    private String csAddress;//委托人地址
    private String csPostcode;//委托人邮编
    private String csPhnoe;//委托人电话

    private String qualificationType;//资质类型
    private String realEstateAppraiser;//估价师

    private String firstAppraiser;//第一报告人
    private String firstRegistrationNumber;//第一报告人注册号
    private String participationAppraiser;//参与报告人
    private String registrationNumber;//注册号
    private String contractPrice;//收费
    private Integer areaId;

    private Date preauditNumberDate;//预评时间
    private Date resultNumberDate;//结果时间

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPreauditNumberDate() {
        return preauditNumberDate;
    }

    public void setPreauditNumberDate(Date preauditNumberDate) {
        this.preauditNumberDate = preauditNumberDate;
    }

    public Date getResultNumberDate() {
        return resultNumberDate;
    }

    public void setResultNumberDate(Date resultNumberDate) {
        this.resultNumberDate = resultNumberDate;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getServiceComeFromName() {
        return serviceComeFromName;
    }

    public void setServiceComeFromName(String serviceComeFromName) {
        this.serviceComeFromName = serviceComeFromName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getServiceComeFrom() {
        return serviceComeFrom;
    }

    public void setServiceComeFrom(String serviceComeFrom) {
        this.serviceComeFrom = serviceComeFrom;
    }

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public String getMethodNames() {
        return methodNames;
    }

    public void setMethodNames(String methodNames) {
        this.methodNames = methodNames;
    }

    public Date getInvestigationsStartDate() {
        return investigationsStartDate;
    }

    public void setInvestigationsStartDate(Date investigationsStartDate) {
        this.investigationsStartDate = investigationsStartDate;
    }

    public Date getHomeWorkEndTime() {
        return homeWorkEndTime;
    }

    public void setHomeWorkEndTime(Date homeWorkEndTime) {
        this.homeWorkEndTime = homeWorkEndTime;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public BigDecimal getLandArea() {
        return landArea;
    }

    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    public BigDecimal getEvaluationArea() {
        return evaluationArea;
    }

    public void setEvaluationArea(BigDecimal evaluationArea) {
        this.evaluationArea = evaluationArea;
    }

    public BigDecimal getAssessTotal() {
        return assessTotal;
    }

    public void setAssessTotal(BigDecimal assessTotal) {
        this.assessTotal = assessTotal;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getCsAddress() {
        return csAddress;
    }

    public void setCsAddress(String csAddress) {
        this.csAddress = csAddress;
    }

    public String getCsPostcode() {
        return csPostcode;
    }

    public void setCsPostcode(String csPostcode) {
        this.csPostcode = csPostcode;
    }

    public String getCsPhnoe() {
        return csPhnoe;
    }

    public void setCsPhnoe(String csPhnoe) {
        this.csPhnoe = csPhnoe;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    public String getRealEstateAppraiser() {
        return realEstateAppraiser;
    }

    public void setRealEstateAppraiser(String realEstateAppraiser) {
        this.realEstateAppraiser = realEstateAppraiser;
    }

    public String getFirstAppraiser() {
        return firstAppraiser;
    }

    public void setFirstAppraiser(String firstAppraiser) {
        this.firstAppraiser = firstAppraiser;
    }

    public String getFirstRegistrationNumber() {
        return firstRegistrationNumber;
    }

    public void setFirstRegistrationNumber(String firstRegistrationNumber) {
        this.firstRegistrationNumber = firstRegistrationNumber;
    }

    public String getParticipationAppraiser() {
        return participationAppraiser;
    }

    public void setParticipationAppraiser(String participationAppraiser) {
        this.participationAppraiser = participationAppraiser;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
