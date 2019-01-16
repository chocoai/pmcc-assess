package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/14 15:27
 * @Description:
 */
public enum BaseReportFieldReplaceEnum {
    TEXT("word.text","文本"),
    FILE("word.file","附件(书签替换模板)"),
    BOOKMARK("word.bookmark","书签"),
    OTHER("word.other","自定义"),
    ;
    private String key;

    private String name;

    private BaseReportFieldReplaceEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public static BaseReportFieldReplaceEnum getEnumByName(String id) {
        for (BaseReportFieldReplaceEnum e : BaseReportFieldReplaceEnum.values()) {
            if (e.getKey().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportFieldReplaceEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportFieldReplaceEnum e : BaseReportFieldReplaceEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getKey()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
