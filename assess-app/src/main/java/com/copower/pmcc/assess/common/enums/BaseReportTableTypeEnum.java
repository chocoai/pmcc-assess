package com.copower.pmcc.assess.common.enums;

/**
 * 描述:选择表的类型
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum BaseReportTableTypeEnum {
    DATATABLE(1, "字段库表"), FILESTABLE(2, "附件库表");

    private Integer key;

    private String name;

    private BaseReportTableTypeEnum(Integer key, String name) {
        this.name = name;
        this.key = key;
    }


    public static BaseReportTableTypeEnum getEnumByName(Integer id) {
        for (BaseReportTableTypeEnum e : BaseReportTableTypeEnum.values()) {
            if (e.getKey()==id) {
                return e;
            }
        }
        return null;
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
