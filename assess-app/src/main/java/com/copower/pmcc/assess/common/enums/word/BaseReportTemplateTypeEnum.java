package com.copower.pmcc.assess.common.enums.word;

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
public enum BaseReportTemplateTypeEnum {
    REPORT("report", "报告模板"), EXPORT("export", "导出汇总模板");

    private String key;

    private String name;

    private BaseReportTemplateTypeEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public static BaseReportTemplateTypeEnum getEnumByName(String id) {
        for (BaseReportTemplateTypeEnum e : BaseReportTemplateTypeEnum.values()) {
            if (e.getKey() .equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportTemplateTypeEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportTemplateTypeEnum e : BaseReportTemplateTypeEnum.values()) {
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
