package com.copower.pmcc.assess.common.enums.meau;

/**
 * Created by zch on 2019-10-15.
 * 资产申报分组 枚举
 */
public enum DeclareRecordGroupEnum {
    Area("区域分组",1),
    BuildingNumber("楼栋分组",2),
    Unit("单元分组",3),
    ;
    private String remark;
    private Integer id;

    DeclareRecordGroupEnum(String remark, Integer id) {
        this.remark = remark;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getRemark() {
        return remark;
    }
}
