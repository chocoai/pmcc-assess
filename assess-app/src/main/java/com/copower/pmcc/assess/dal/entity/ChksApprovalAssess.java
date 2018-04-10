package com.copower.pmcc.assess.dal.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChksApprovalAssess {
    private Integer id;

    private String userAccount;

    private Integer chksApprovalBusinessId;

    private Integer chksApprovalInfoId;

    private BigDecimal totalScore;

    private BigDecimal totalVaildScore;

    private String creator;

    private Date created;

    private Boolean bisRepair;

    private Boolean bisEnable;

    private String modifyUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public Integer getChksApprovalBusinessId() {
        return chksApprovalBusinessId;
    }

    public void setChksApprovalBusinessId(Integer chksApprovalBusinessId) {
        this.chksApprovalBusinessId = chksApprovalBusinessId;
    }

    public Integer getChksApprovalInfoId() {
        return chksApprovalInfoId;
    }

    public void setChksApprovalInfoId(Integer chksApprovalInfoId) {
        this.chksApprovalInfoId = chksApprovalInfoId;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getTotalVaildScore() {
        return totalVaildScore;
    }

    public void setTotalVaildScore(BigDecimal totalVaildScore) {
        this.totalVaildScore = totalVaildScore;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getBisRepair() {
        return bisRepair;
    }

    public void setBisRepair(Boolean bisRepair) {
        this.bisRepair = bisRepair;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }
}