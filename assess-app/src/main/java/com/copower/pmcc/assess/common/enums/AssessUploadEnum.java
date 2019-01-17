package com.copower.pmcc.assess.common.enums;

/**
 * 描述:模板类型表
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum AssessUploadEnum {
    INVENTORY_CHECK_ORIGINAL("inventory_check_original", "资产清查证明文件"),
    INVENTORY_SPECIAL_CASE("inventory_special_case", "资产清查特殊情况附件"),

    ESTATE_FLOOR_TOTAL_PLAN("estate_floor_total_plan", "楼盘总平面图"),
    ESTATE_FLOOR_APPEARANCE_FIGURE("estate_floor_Appearance_figure", "楼盘外观图"),
    ESTATE_WATER_SUPPLY_PLAN("estate_water_supply_plan", "楼盘供水平面图"),
    ESTATE_POWER_SUPPLY_PLAN("estate_power_supply_plan", "楼盘供电平面图"),
    ESTATE_AIR_SUPPLY_PLAN("estate_air_supply_plan", "楼盘供气平面图"),
    ESTATE_HEATING_PLAN("estate_heating_plan", "楼盘采暖平面图"),

    HOUSE_HUXING_PLAN("house_huxing_plan", "户型图"),
    HOUSE_NEW_HUXING_PLAN("house_new_huxing_plan", "新户型图"),
    HOUSE_IMG_PLAN("house_img_plan", "房屋平面图"),
    OTHER_FILE("otherFile", "其它附件"),

    BUILDING_FLOOR_PLAN("building_floor_plan", "平面图 (楼栋)"),
    BUILDING_FIGURE_OUTSIDE("building_figure_outside", "外装图(楼栋)"),
    BUILDING_FLOOR_APPEARANCE_FIGURE("building_floor_Appearance_figure", "外观图(楼栋)");


    private String key;
    private String value;

    private AssessUploadEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static AssessUploadEnum getEnumByKey(String key) {
        for (AssessUploadEnum e : AssessUploadEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
