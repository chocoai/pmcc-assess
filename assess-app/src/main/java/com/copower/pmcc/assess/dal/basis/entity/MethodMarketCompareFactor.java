package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class MethodMarketCompareFactor {
    private Integer id;

    private Integer evaluationObjectId;

    private String name;

    private Integer type;

    private Object jsonContent;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvaluationObjectId() {
        return evaluationObjectId;
    }

    public void setEvaluationObjectId(Integer evaluationObjectId) {
        this.evaluationObjectId = evaluationObjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(Object jsonContent) {
        this.jsonContent = jsonContent;
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