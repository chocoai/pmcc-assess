package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ProjectXlxPigeonholeRecord {
    private Integer id;

    private Integer projectId;

    private String fileName;

    private Boolean hasPaperDatum;

    private Boolean hasElectronicDatum;

    private Integer sorting;

    private String creator;

    private Date created;

    private Date modified;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Boolean getHasPaperDatum() {
        return hasPaperDatum;
    }

    public void setHasPaperDatum(Boolean hasPaperDatum) {
        this.hasPaperDatum = hasPaperDatum;
    }

    public Boolean getHasElectronicDatum() {
        return hasElectronicDatum;
    }

    public void setHasElectronicDatum(Boolean hasElectronicDatum) {
        this.hasElectronicDatum = hasElectronicDatum;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}