package com.copower.pmcc.assess.dto.input.word;

import com.copower.pmcc.assess.common.enums.word.DataReplaceTypeEnum;
import com.copower.pmcc.assess.common.enums.word.DataSourceTypeEnum;

/**
 * Created by kings on 2018-6-15.
 */
public class DataReplaceDto {
    private String key;
    private String value;
    private DataReplaceTypeEnum dataReplaceTypeEnum;
    private DataSourceTypeEnum dataSourceTypeEnum;
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

    public DataSourceTypeEnum getDataSourceTypeEnum() {
        return dataSourceTypeEnum;
    }

    public void setDataSourceTypeEnum(DataSourceTypeEnum dataSourceTypeEnum) {
        this.dataSourceTypeEnum = dataSourceTypeEnum;
    }

    public Integer getReplaceRecordId() {
        return replaceRecordId;
    }

    public void setReplaceRecordId(Integer replaceRecordId) {
        this.replaceRecordId = replaceRecordId;
    }
}
