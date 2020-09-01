package com.copower.pmcc.assess.common.enums.archives;

import java.io.Serializable;

public enum ArchivesFileProfessionEnum implements Serializable {

    Warrant("权证") ,
    ECONOMIC_INDICATORS("经济指标") ,
    OTHER_RIGHT("他权") ,
    SITE_SURVEY("现场查勘") ,
    CASE_INVESTIGATION("案例调查") ,
    EVALUATION_REPORT("评估报告") ,
    ;

    private String name;

    ArchivesFileProfessionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ArchivesFileProfessionEnum getEnumByKey(String name) {
        for (ArchivesFileProfessionEnum e : ArchivesFileProfessionEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
