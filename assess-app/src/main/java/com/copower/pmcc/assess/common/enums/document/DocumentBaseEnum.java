package com.copower.pmcc.assess.common.enums.document;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.List;

public enum DocumentBaseEnum {
    PROJECTNAME("PROJECTNAME", "项目名称");

    private String value;

    private String name;

    private DocumentBaseEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static List<KeyValueDto> getEnumList()
    {
        List<KeyValueDto> keyValueDtos=new ArrayList<>();
        for(DocumentBaseEnum item:DocumentBaseEnum.values())
        {
            KeyValueDto keyValueDto=new KeyValueDto();
            keyValueDto.setKey(item.getValue());
            keyValueDto.setValue(item.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
