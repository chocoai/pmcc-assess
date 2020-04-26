package com.copower.pmcc.assess.common.enums.basic;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 描述:查勘表单大类
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum BasicFormStructureEnum {
    RealEstate("project.survey.form.classify.multiple", "房产",  "basicStructureRealEstateService"),
    Land("project.survey.form.classify.land.only", "土地",  "basicStructureLandService"),
    LandIncludRealEstate("project.survey.form.classify.land", "土地带房产",  "basicStructureLandIncludService"),
    ;

    private Integer level;
    private String key;
    private String value;
    private String serviceName;

    private BasicFormStructureEnum(String key, String value, String serviceName) {
        this.key = key;
        this.value = value;
        this.serviceName = serviceName;
    }

    public static BasicFormStructureEnum getEnumByKey(String key) {
        for (BasicFormStructureEnum e : BasicFormStructureEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 用来处理本枚举无法解析的问题 类似的building.difference 转为 building_difference 到 解析完转回building.difference
     * @param flag
     * @return
     */
    public static String transform(boolean flag){
        if (flag){
            return "_" ;
        }else {
            return "." ;
        }
    }

    public static List<BasicFormStructureEnum> getEnumByFuzzyKey(String key) {
        List<BasicFormStructureEnum> list = Lists.newArrayList();
        for (BasicFormStructureEnum e : BasicFormStructureEnum.values()) {
            if (e.getKey().contains(key)) {
                list.add(e);
            }
        }
        return list;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
