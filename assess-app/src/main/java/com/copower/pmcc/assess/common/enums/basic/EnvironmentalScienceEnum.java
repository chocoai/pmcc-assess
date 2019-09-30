package com.copower.pmcc.assess.common.enums.basic;

/**
 * @author: zch
 * @date: 2019/3/26 17:43
 * @description:环境
 */
public enum EnvironmentalScienceEnum {
    NATURAL("自然环境要素","estate.environment.type.natural"),
    HUMANITY("人文环境要素","estate.environment.type.cultural"),
    SCENERY("景观要素","estate.environment.type.scenery"),
    ;
    private String name;
    private String key;

    EnvironmentalScienceEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
