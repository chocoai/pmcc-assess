package com.copower.pmcc.assess.dto.input;

import java.util.List;

/**
 * Created by kings on 2018-3-28.
 */
public class FormConfigureDto {
    private Integer primaryId;
    private String primaryTableName;
    private String processInsId;
    private String phaseId;
    private List<FormConfigureDetailDto> singelFormList;
    private List<FormConfigureDetailDto> multipleFormList;

    public Integer getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Integer primaryId) {
        this.primaryId = primaryId;
    }

    public String getPrimaryTableName() {
        return primaryTableName;
    }

    public void setPrimaryTableName(String primaryTableName) {
        this.primaryTableName = primaryTableName;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public List<FormConfigureDetailDto> getSingelFormList() {
        return singelFormList;
    }

    public void setSingelFormList(List<FormConfigureDetailDto> singelFormList) {
        this.singelFormList = singelFormList;
    }

    public List<FormConfigureDetailDto> getMultipleFormList() {
        return multipleFormList;
    }

    public void setMultipleFormList(List<FormConfigureDetailDto> multipleFormList) {
        this.multipleFormList = multipleFormList;
    }
}
