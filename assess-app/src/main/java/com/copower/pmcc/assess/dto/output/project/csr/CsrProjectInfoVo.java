package com.copower.pmcc.assess.dto.output.project.csr;

import com.copower.pmcc.assess.dal.entity.CsrProjectInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-5-31.
 */
public class CsrProjectInfoVo extends CsrProjectInfo {
    private String projectClassName;
    private String projectTypeName;
    private String distributionUserName;
    private String customerTypeName;
    private String entrustPurposeName;
    private String statusName;
    private String projectCategoryName;
    private List<CsrProjectInfoGroupVo> csrProjectInfoGroupVos = new ArrayList<>();

    public String getProjectClassName() {
        return projectClassName;
    }

    public void setProjectClassName(String projectClassName) {
        this.projectClassName = projectClassName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDistributionUserName() {
        return distributionUserName;
    }

    public void setDistributionUserName(String distributionUserName) {
        this.distributionUserName = distributionUserName;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public List<CsrProjectInfoGroupVo> getCsrProjectInfoGroupVos() {
        return csrProjectInfoGroupVos;
    }

    public void setCsrProjectInfoGroupVos(List<CsrProjectInfoGroupVo> csrProjectInfoGroupVos) {
        this.csrProjectInfoGroupVos = csrProjectInfoGroupVos;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }
}
