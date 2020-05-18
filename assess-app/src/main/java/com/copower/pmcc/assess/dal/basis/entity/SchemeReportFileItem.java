package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SchemeReportFileItem {
    private Integer id;

    private Integer planDetailsId;

    private Integer schemeJudgeObjectId;

    private String type;

    private Integer attachmentId;

    private String fileName;

    private Integer sorting;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer declareRecordId;

    private Integer certifyPart;

    private Integer certifyPartCategory;

    private Boolean bisEnable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getSchemeJudgeObjectId() {
        return schemeJudgeObjectId;
    }

    public void setSchemeJudgeObjectId(Integer schemeJudgeObjectId) {
        this.schemeJudgeObjectId = schemeJudgeObjectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public Integer getCertifyPart() {
        return certifyPart;
    }

    public void setCertifyPart(Integer certifyPart) {
        this.certifyPart = certifyPart;
    }

    public Integer getCertifyPartCategory() {
        return certifyPartCategory;
    }

    public void setCertifyPartCategory(Integer certifyPartCategory) {
        this.certifyPartCategory = certifyPartCategory;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }
}