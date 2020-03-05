package com.copower.pmcc.assess.dto.input.project.declare;

import java.io.Serializable;

/**
 * Created by zch on 2020-3-5.
 */
public class AutomatedWarrants implements Serializable {

    private String prefixNumber;
    private Integer startNumber;
    private Integer endNumber;
    private Integer step;
    private Integer attachmentId;
    private String tableName;
    private String fieldsName;
    private Integer planDetailsId;

    private String isSource;

    public String getPrefixNumber() {
        return prefixNumber;
    }

    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String fieldsName) {
        this.fieldsName = fieldsName;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getIsSource() {
        return isSource;
    }

    public void setIsSource(String isSource) {
        this.isSource = isSource;
    }
}
