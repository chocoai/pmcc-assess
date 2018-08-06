package com.copower.pmcc.assess.dto.input;

import com.copower.pmcc.assess.common.enums.word.DataReplaceTypeEnum;

/**
 * Created by kings on 2018-6-15.
 */
public class DataReplaceDto {
    private String key;
    private String value;
    private DataReplaceTypeEnum dataReplaceTypeEnum;
    private Integer replaceRecordId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataReplaceTypeEnum getDataReplaceTypeEnum() {
        return dataReplaceTypeEnum;
    }

    public void setDataReplaceTypeEnum(DataReplaceTypeEnum dataReplaceTypeEnum) {
        this.dataReplaceTypeEnum = dataReplaceTypeEnum;
    }


    public Integer getReplaceRecordId() {
        return replaceRecordId;
    }

    public void setReplaceRecordId(Integer replaceRecordId) {
        this.replaceRecordId = replaceRecordId;
    }
}
