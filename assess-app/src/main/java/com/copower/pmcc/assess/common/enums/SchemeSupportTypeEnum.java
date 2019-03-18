package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/5/25.
 */
public enum SchemeSupportTypeEnum {
    HYPOTHESIS("hypothesis", "评估假设"),//假设
    PRINCIPLE("principle", "评估原则"),//原则
    BASIS("basis", "评估依据"),//依据
    REPORT_ANALYSIS("report_analysis", "报告分析");//报告分析
    private String key;
    private String name;

    SchemeSupportTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    // name替换id
    public static String getName(String key) {
        for (SchemeSupportTypeEnum c : SchemeSupportTypeEnum.values()) {
            if (StringUtils.equals(c.getKey(),key)) {
                return c.name;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getSchemeSupportTypeEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (SchemeSupportTypeEnum e : SchemeSupportTypeEnum.values()) {
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

