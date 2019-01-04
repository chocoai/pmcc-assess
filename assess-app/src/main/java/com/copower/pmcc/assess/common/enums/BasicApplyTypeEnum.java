package com.copower.pmcc.assess.common.enums;

/**
 * 描述:模板类型表
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum BasicApplyTypeEnum {
    NON_INDUSTRY(0, "添加"), INDUSTRY(1, "升版本");

    private Integer id;
    private String name;

    private BasicApplyTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BasicApplyTypeEnum getEnumByKey(Integer id) {
        for (BasicApplyTypeEnum e : BasicApplyTypeEnum.values()) {
            if (e.getId().equals(id)) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
