package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataMethodFormula {
    private Integer id;

    private Integer method;

    private String formula;

    private String relevantParameter;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String methodKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getRelevantParameter() {
        return relevantParameter;
    }

    public void setRelevantParameter(String relevantParameter) {
        this.relevantParameter = relevantParameter == null ? null : relevantParameter.trim();
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

    public String getMethodKey() {
        return methodKey;
    }

    public void setMethodKey(String methodKey) {
        this.methodKey = methodKey == null ? null : methodKey.trim();
    }
}