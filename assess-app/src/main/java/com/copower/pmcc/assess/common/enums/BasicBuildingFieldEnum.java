package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:57
 * @Description:案例基础 楼栋
 */
public enum BasicBuildingFieldEnum {
    BuildOne("1","楼栋第一部分"),
    BuildTwo("2","楼栋第二部分"),
    BuildThree("3","楼栋第三部分"),
    BuildFour("4","楼栋第四部分"),
    ;
    private String key;
    private String dir;

    BasicBuildingFieldEnum(String key, String dir) {
        this.key = key;
        this.dir = dir;
    }

    public String getKey() {
        return key;
    }
}
