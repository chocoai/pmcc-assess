package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/10/22 13:37
 * @Description:
 */
public enum DeclareTypeEnum {
    MasterData("master","主数据"),
    BranchData("branch","从表数据"),

    House_Type_Enum("房产","写入 DeclareRecord 中字段类型"),
    HouseOrLand_Type_Enum("土地加房产","写入 DeclareRecord 中type字段类型"),
    Land_Type_Enum("纯土地","写入 DeclareRecord 中字段类型"),
    RealEstateCert_Type_Enum("不动产","写入 DeclareRecord 中字段类型"),


    HOUSE("房产证","BaseProjectClassify名称匹配"),
    DeclareBuildEquipmentInstall("设备安装","BaseProjectClassify名称匹配"),
    DeclareBuildEngineering("土建","BaseProjectClassify名称匹配"),
    LAND("土地证","BaseProjectClassify名称匹配"),
    RealEstate("不动产证","BaseProjectClassify名称匹配");
    private String key;
    private String dir;

    DeclareTypeEnum(String key, String dir) {
        this.key = key;
        this.dir = dir;
    }

    public String getKey() {
        return key;
    }

}
