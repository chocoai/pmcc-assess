package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectXlxReportIndividual {
    private Integer id;

    private String location;

    private String projectName;

    private String entrustedUnit;

    private Integer reportScore;

    private Date reportModificationDate;

    private Date reportBindingDate;

    private Date filingDate;

    private BigDecimal landPlotsNumber;

    private String landPlotsLocation;

    private String entrustedBank;

    private String phonePrincipal;

    private String addressPrincipal;

    private Date contractSigningDate;

    private String signerPeople;

    private BigDecimal floorArea;

    private String actualCharges;

    private String standardCharge;

    private String valuationMethod;

    private String valuationPurpose;

    private String assessMethod;

    private String assessPurpose;

    private String signedBy;

    private String draftAuthor;

    private Date appraisalPeriod;

    private String projectAcquisitionMethod;

    private String projectLeader;

    private String housingUse;

    private String landUse;

    private String certUse;

    private Date valueTimePoint;

    private String timePointExplain;

    private String reportPeople;

    private String numberValue;

    private BigDecimal landArea;

    private BigDecimal assessArea;

    private BigDecimal judgeObjectLandArea;

    private BigDecimal judgeObjectFloorArea;

    private BigDecimal assessPrice;

    private BigDecimal assessTotalPrice;

    private String undertakeSheet;

    private BigDecimal assessTotalToll;

    private BigDecimal price;

    private BigDecimal worth;

    private BigDecimal accountValueOriginal;

    private String remark;

    private Integer planDetailsId;

    private Integer projectId;

    private String assessType;

    private Integer sorting;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getEntrustedUnit() {
        return entrustedUnit;
    }

    public void setEntrustedUnit(String entrustedUnit) {
        this.entrustedUnit = entrustedUnit == null ? null : entrustedUnit.trim();
    }

    public Integer getReportScore() {
        return reportScore;
    }

    public void setReportScore(Integer reportScore) {
        this.reportScore = reportScore;
    }

    public Date getReportModificationDate() {
        return reportModificationDate;
    }

    public void setReportModificationDate(Date reportModificationDate) {
        this.reportModificationDate = reportModificationDate;
    }

    public Date getReportBindingDate() {
        return reportBindingDate;
    }

    public void setReportBindingDate(Date reportBindingDate) {
        this.reportBindingDate = reportBindingDate;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public BigDecimal getLandPlotsNumber() {
        return landPlotsNumber;
    }

    public void setLandPlotsNumber(BigDecimal landPlotsNumber) {
        this.landPlotsNumber = landPlotsNumber;
    }

    public String getLandPlotsLocation() {
        return landPlotsLocation;
    }

    public void setLandPlotsLocation(String landPlotsLocation) {
        this.landPlotsLocation = landPlotsLocation == null ? null : landPlotsLocation.trim();
    }

    public String getEntrustedBank() {
        return entrustedBank;
    }

    public void setEntrustedBank(String entrustedBank) {
        this.entrustedBank = entrustedBank == null ? null : entrustedBank.trim();
    }

    public String getPhonePrincipal() {
        return phonePrincipal;
    }

    public void setPhonePrincipal(String phonePrincipal) {
        this.phonePrincipal = phonePrincipal == null ? null : phonePrincipal.trim();
    }

    public String getAddressPrincipal() {
        return addressPrincipal;
    }

    public void setAddressPrincipal(String addressPrincipal) {
        this.addressPrincipal = addressPrincipal == null ? null : addressPrincipal.trim();
    }

    public Date getContractSigningDate() {
        return contractSigningDate;
    }

    public void setContractSigningDate(Date contractSigningDate) {
        this.contractSigningDate = contractSigningDate;
    }

    public String getSignerPeople() {
        return signerPeople;
    }

    public void setSignerPeople(String signerPeople) {
        this.signerPeople = signerPeople == null ? null : signerPeople.trim();
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public String getActualCharges() {
        return actualCharges;
    }

    public void setActualCharges(String actualCharges) {
        this.actualCharges = actualCharges == null ? null : actualCharges.trim();
    }

    public String getStandardCharge() {
        return standardCharge;
    }

    public void setStandardCharge(String standardCharge) {
        this.standardCharge = standardCharge == null ? null : standardCharge.trim();
    }

    public String getValuationMethod() {
        return valuationMethod;
    }

    public void setValuationMethod(String valuationMethod) {
        this.valuationMethod = valuationMethod == null ? null : valuationMethod.trim();
    }

    public String getValuationPurpose() {
        return valuationPurpose;
    }

    public void setValuationPurpose(String valuationPurpose) {
        this.valuationPurpose = valuationPurpose == null ? null : valuationPurpose.trim();
    }

    public String getAssessMethod() {
        return assessMethod;
    }

    public void setAssessMethod(String assessMethod) {
        this.assessMethod = assessMethod == null ? null : assessMethod.trim();
    }

    public String getAssessPurpose() {
        return assessPurpose;
    }

    public void setAssessPurpose(String assessPurpose) {
        this.assessPurpose = assessPurpose == null ? null : assessPurpose.trim();
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy == null ? null : signedBy.trim();
    }

    public String getDraftAuthor() {
        return draftAuthor;
    }

    public void setDraftAuthor(String draftAuthor) {
        this.draftAuthor = draftAuthor == null ? null : draftAuthor.trim();
    }

    public Date getAppraisalPeriod() {
        return appraisalPeriod;
    }

    public void setAppraisalPeriod(Date appraisalPeriod) {
        this.appraisalPeriod = appraisalPeriod;
    }

    public String getProjectAcquisitionMethod() {
        return projectAcquisitionMethod;
    }

    public void setProjectAcquisitionMethod(String projectAcquisitionMethod) {
        this.projectAcquisitionMethod = projectAcquisitionMethod == null ? null : projectAcquisitionMethod.trim();
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader == null ? null : projectLeader.trim();
    }

    public String getHousingUse() {
        return housingUse;
    }

    public void setHousingUse(String housingUse) {
        this.housingUse = housingUse == null ? null : housingUse.trim();
    }

    public String getLandUse() {
        return landUse;
    }

    public void setLandUse(String landUse) {
        this.landUse = landUse == null ? null : landUse.trim();
    }

    public String getCertUse() {
        return certUse;
    }

    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    public Date getValueTimePoint() {
        return valueTimePoint;
    }

    public void setValueTimePoint(Date valueTimePoint) {
        this.valueTimePoint = valueTimePoint;
    }

    public String getTimePointExplain() {
        return timePointExplain;
    }

    public void setTimePointExplain(String timePointExplain) {
        this.timePointExplain = timePointExplain == null ? null : timePointExplain.trim();
    }

    public String getReportPeople() {
        return reportPeople;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople == null ? null : reportPeople.trim();
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue == null ? null : numberValue.trim();
    }

    public BigDecimal getLandArea() {
        return landArea;
    }

    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    public BigDecimal getAssessArea() {
        return assessArea;
    }

    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    public BigDecimal getJudgeObjectLandArea() {
        return judgeObjectLandArea;
    }

    public void setJudgeObjectLandArea(BigDecimal judgeObjectLandArea) {
        this.judgeObjectLandArea = judgeObjectLandArea;
    }

    public BigDecimal getJudgeObjectFloorArea() {
        return judgeObjectFloorArea;
    }

    public void setJudgeObjectFloorArea(BigDecimal judgeObjectFloorArea) {
        this.judgeObjectFloorArea = judgeObjectFloorArea;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
    }

    public BigDecimal getAssessTotalPrice() {
        return assessTotalPrice;
    }

    public void setAssessTotalPrice(BigDecimal assessTotalPrice) {
        this.assessTotalPrice = assessTotalPrice;
    }

    public String getUndertakeSheet() {
        return undertakeSheet;
    }

    public void setUndertakeSheet(String undertakeSheet) {
        this.undertakeSheet = undertakeSheet == null ? null : undertakeSheet.trim();
    }

    public BigDecimal getAssessTotalToll() {
        return assessTotalToll;
    }

    public void setAssessTotalToll(BigDecimal assessTotalToll) {
        this.assessTotalToll = assessTotalToll;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWorth() {
        return worth;
    }

    public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }

    public BigDecimal getAccountValueOriginal() {
        return accountValueOriginal;
    }

    public void setAccountValueOriginal(BigDecimal accountValueOriginal) {
        this.accountValueOriginal = accountValueOriginal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getAssessType() {
        return assessType;
    }

    public void setAssessType(String assessType) {
        this.assessType = assessType == null ? null : assessType.trim();
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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