package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class BaseAttachmentKeep {
    private Integer id;

    private Integer attachmentId;

    private String filePath;

    private String fileName;

    private String ftpFilesName;

    private String fileExtension;

    private String fileSize;

    private Date created;

    private Date mdified;

    private String owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getMdified() {
        return mdified;
    }

    public void setMdified(Date mdified) {
        this.mdified = mdified;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }
}