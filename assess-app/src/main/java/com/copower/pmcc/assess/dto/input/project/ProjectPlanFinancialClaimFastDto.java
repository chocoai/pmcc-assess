package com.copower.pmcc.assess.dto.input.project;

import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 17:21
 */
public class ProjectPlanFinancialClaimFastDto {

    private Integer projectId;

    private Integer planId;

   private  String phaseList;

   private String customerList;

   private String planStartDate;

   private String planEndDate;

   private String executeUserAccount;

   private BigDecimal planHours;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPhaseList() {
        return phaseList;
    }

    public void setPhaseList(String phaseList) {
        this.phaseList = phaseList;
    }

    public String getCustomerList() {
        return customerList;
    }

    public void setCustomerList(String customerList) {
        this.customerList = customerList;
    }

    public String getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(String planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(String planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getExecuteUserAccount() {
        return executeUserAccount;
    }

    public void setExecuteUserAccount(String executeUserAccount) {
        this.executeUserAccount = executeUserAccount;
    }

    public BigDecimal getPlanHours() {
        return planHours;
    }

    public void setPlanHours(BigDecimal planHours) {
        this.planHours = planHours;
    }
}
