package com.copower.pmcc.assess.dto.input.project;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zch on 2019-12-10.
 * 查询参数 定义的一个收集数据的对象模板
 */
public class QueryProjectInfo {

    private String userAccount;
    private String projectName;
    private String projectStatus;
    private String queryCreator;
    private String queryMember;
    private Integer entrustPurpose;
    private String queryManager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date queryTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date queryTimeEnd;
    private String queryConsignor;
    private Integer queryConsignorType;
    private Integer queryUseUnit;
    private String queryEstateName;
    private Integer queryLoanType;
    private Integer queryDepartmentId;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getQueryCreator() {
        return queryCreator;
    }

    public void setQueryCreator(String queryCreator) {
        this.queryCreator = queryCreator;
    }

    public String getQueryMember() {
        return queryMember;
    }

    public void setQueryMember(String queryMember) {
        this.queryMember = queryMember;
    }

    public Integer getEntrustPurpose() {
        return entrustPurpose;
    }

    public void setEntrustPurpose(Integer entrustPurpose) {
        this.entrustPurpose = entrustPurpose;
    }

    public String getQueryManager() {
        return queryManager;
    }

    public void setQueryManager(String queryManager) {
        this.queryManager = queryManager;
    }

    public Date getQueryTimeStart() {
        return queryTimeStart;
    }

    public void setQueryTimeStart(Date queryTimeStart) {
        this.queryTimeStart = queryTimeStart;
    }

    public Date getQueryTimeEnd() {
        return queryTimeEnd;
    }

    public void setQueryTimeEnd(Date queryTimeEnd) {
        this.queryTimeEnd = queryTimeEnd;
    }

    public String getQueryConsignor() {
        return queryConsignor;
    }

    public void setQueryConsignor(String queryConsignor) {
        this.queryConsignor = queryConsignor;
    }

    public Integer getQueryUseUnit() {
        return queryUseUnit;
    }

    public void setQueryUseUnit(Integer queryUseUnit) {
        this.queryUseUnit = queryUseUnit;
    }

    public String getQueryEstateName() {
        return queryEstateName;
    }

    public void setQueryEstateName(String queryEstateName) {
        this.queryEstateName = queryEstateName;
    }

    public Integer getQueryLoanType() {
        return queryLoanType;
    }

    public void setQueryLoanType(Integer queryLoanType) {
        this.queryLoanType = queryLoanType;
    }

    public Integer getQueryDepartmentId() {
        return queryDepartmentId;
    }

    public void setQueryDepartmentId(Integer queryDepartmentId) {
        this.queryDepartmentId = queryDepartmentId;
    }

    public Integer getQueryConsignorType() {
        return queryConsignorType;
    }

    public void setQueryConsignorType(Integer queryConsignorType) {
        this.queryConsignorType = queryConsignorType;
    }
}
