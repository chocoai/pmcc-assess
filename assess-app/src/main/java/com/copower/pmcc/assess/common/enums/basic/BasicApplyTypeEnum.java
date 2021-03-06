package com.copower.pmcc.assess.common.enums.basic;

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
public enum BasicApplyTypeEnum {
    RESIDENCE(0, "residence", "非工业交通仓储"),
    INDUSTRY(1, "industry", "工业交通仓储");

    private Integer id;
    private String key;
    private String name;

    private BasicApplyTypeEnum(Integer id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }

    public static List<KeyValueDto> getBasicApplyTypeEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BasicApplyTypeEnum e : BasicApplyTypeEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getId()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public static BasicApplyTypeEnum getEnumById(Integer id) {
        for (BasicApplyTypeEnum e : BasicApplyTypeEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static BasicApplyTypeEnum getEnumByKey(String key) {
        for (BasicApplyTypeEnum e : BasicApplyTypeEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
