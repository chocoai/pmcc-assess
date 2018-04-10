package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class BaseAttachment {
    private Integer id;

    private String tableName;

    private Integer tableId;

    private String processInsId;

    private String filePath;

    private String filePathPdf;

    private String fileName;

    private String ftpFilesName;

    private String fileExtension;

    private String fileSize;

    private String reName;

    private String reActivityName;

    private String fieldsName;

    private Integer projectId;

    private Boolean bisLastEdit;

    private String creater;

    private Date created;

    private Date modified;

    private String modifier;

    private String processTaskId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFilePathPdf() {
        return filePathPdf;
    }

    public void setFilePathPdf(String filePathPdf) {
        this.filePathPdf = filePathPdf == null ? null : filePathPdf.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFtpFilesName() {
        return ftpFilesName;
    }

    public void setFtpFilesName(String ftpFilesName) {
        this.ftpFilesName = ftpFilesName == null ? null : ftpFilesName.trim();
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension == null ? null : fileExtension.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName == null ? null : reName.trim();
    }

    public String getReActivityName() {
        return reActivityName;
    }

    public void setReActivityName(String reActivityName) {
        this.reActivityName = reActivityName == null ? null : reActivityName.trim();
    }

    public String getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String fieldsName) {
        this.fieldsName = fieldsName == null ? null : fieldsName.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Boolean getBisLastEdit() {
        return bisLastEdit;
    }

    public void setBisLastEdit(Boolean bisLastEdit) {
        this.bisLastEdit = bisLastEdit;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
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

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getProcessTaskId() {
        return processTaskId;
    }

    public void setProcessTaskId(String processTaskId) {
        this.processTaskId = processTaskId == null ? null : processTaskId.trim();
    }
}