package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:模板类型表
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum BaseReportMarkbookTypeEnum {
    TEXT(0, "文本"),BOOKMARK(1, "书签"), TEMPLATE(2, "子模板");

    private Integer key;

    private String name;

    private BaseReportMarkbookTypeEnum(Integer key, String name) {
        this.name = name;
        this.key = key;
    }

    public static BaseReportMarkbookTypeEnum getEnumByName(Integer id) {
        for (BaseReportMarkbookTypeEnum e : BaseReportMarkbookTypeEnum.values()) {
            if (e.getKey() == id) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportTemplateTypeEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportMarkbookTypeEnum e : BaseReportMarkbookTypeEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getKey()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
