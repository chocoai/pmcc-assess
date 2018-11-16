package com.copower.pmcc.assess.common.enums;

/**
 * 描述: 项目变更类型枚举
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/03 10:31
 */
public enum ProjectChangeTypeEnum {
    INFO_CHANGE("info_change", "项目信息变更"), PAUSE_CHANGE("pause_change", "项目暂停变更"), STOP_CHANGE("stop_change", "项目终止变更"), RESTART_CHANGE("restart_change", "项目重启变更"),
    MANAGER_CHANGE("manager_change", "项目经理变更"), MEMBER_CHANGE("member_change", "组员变更"),STAGE_RESTART("stage_restart","阶段重启");

    private String value;
    private String name;

    ProjectChangeTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ProjectChangeTypeEnum create(String value) {
        for (ProjectChangeTypeEnum e : ProjectChangeTypeEnum.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
