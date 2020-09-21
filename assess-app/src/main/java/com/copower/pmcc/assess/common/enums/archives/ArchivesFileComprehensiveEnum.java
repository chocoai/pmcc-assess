package com.copower.pmcc.assess.common.enums.archives;


import java.io.Serializable;

public enum ArchivesFileComprehensiveEnum  implements Serializable {
    contract("合同") ,
    POWER_ATTORNEY("委托书") ,
    LETTER("信函") ,
    SIGN_DOCUMENTS("签收文件") ,
    OTHER("其他") ,
    ;

    private String name;

    ArchivesFileComprehensiveEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ArchivesFileComprehensiveEnum getEnumByKey(String name) {
        for (ArchivesFileComprehensiveEnum e : ArchivesFileComprehensiveEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
