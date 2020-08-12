package com.copower.pmcc.assess.common.enums.archives;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public enum ArchivesFileTypeEnum  implements Serializable {
    POWER_ATTORNEY_ENUM("001" ,"委托书") ,
    OTHER_ENUM("000" ,"其它") ,
    ;

    private String key;
    private String name;

    ArchivesFileTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static ArchivesFileTypeEnum getTypeEnumByKey(String key) {
        if (StringUtils.isBlank(key)) return null;
        for (ArchivesFileTypeEnum assessProjectTypeEnum : values()) {
            if (assessProjectTypeEnum.getKey().equals(key))
                return assessProjectTypeEnum;
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
