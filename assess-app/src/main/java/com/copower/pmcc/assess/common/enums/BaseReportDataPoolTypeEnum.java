package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:数据来源
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum BaseReportDataPoolTypeEnum {
    COLUMNS(1, "字段"), FILES(2, "文件"), TEMPLATE(3, "子模板");

    private Integer key;

    private String name;

    private BaseReportDataPoolTypeEnum(Integer key, String name) {
        this.name = name;
        this.key = key;
    }

    public static BaseReportDataPoolTypeEnum getEnumByName(Integer id) {
        for (BaseReportDataPoolTypeEnum e : BaseReportDataPoolTypeEnum.values()) {
            if (e.getKey() == id) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportDataPoolTypeEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportDataPoolTypeEnum e : BaseReportDataPoolTypeEnum.values()) {
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
