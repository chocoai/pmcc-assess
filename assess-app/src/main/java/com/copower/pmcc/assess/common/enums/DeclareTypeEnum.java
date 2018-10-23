package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/10/22 13:37
 * @Description:
 */
public enum DeclareTypeEnum {
    Enable("yes","非关联数据"),
    EnableNo("no","关联数据"),HOUSE("房产证","BaseProjectClassify名称匹配"),
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
