package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetInventoryRight {
    private Integer id;

    private Integer projectId;

    private Integer planDetailsId;

    private String certName;

    private Integer type;

    private Integer category;

    private String number;

    private String obligor;

    private String obligee;

    private String registerArea;

    private String rightRank;

    private String registerAmount;

    private String actualAmount;

    private Date registerDate;

    private Date beginDate;

    private Date endDate;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getObligor() {
        return obligor;
    }

    public void setObligor(String obligor) {
        this.obligor = obligor == null ? null : obligor.trim();
    }

    public String getObligee() {
        return obligee;
    }

    public void setObligee(String obligee) {
        this.obligee = obligee == null ? null : obligee.trim();
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea == null ? null : registerArea.trim();
    }

    public String getRightRank() {
        return rightRank;
    }

    public void setRightRank(String rightRank) {
        this.rightRank = rightRank == null ? null : rightRank.trim();
    }

    public String getRegisterAmount() {
        return registerAmount;
    }

    public void setRegisterAmount(String registerAmount) {
        this.registerAmount = registerAmount == null ? null : registerAmount.trim();
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount == null ? null : actualAmount.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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