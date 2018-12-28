package com.copower.pmcc.assess.common.enums;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum ProjectStatusEnum {
    PAUSEAPPLY("pauseapply", "暂停申请"), PAUSE("pause", "暂停"), WAIT("wait", "待处理"), STARTAPPLY("startapply", "启动申请"),
    NORMAL("normal", "进行中"), CLOSE("close", "关闭"), FINISH("finish", "完成"), PLAN
            ("planExecute", "安排计划"), TASK("task", "提交成果"),RUNING("runing","正在进行中");

    private String key;

    private String name;

    private ProjectStatusEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static String getNameByKey(String key){
        for (ProjectStatusEnum e : ProjectStatusEnum.values()) {
            if (e.getKey().equals(key)) {
                return e.getName();
            }
        }
        return null;
    }

    public static ProjectStatusEnum getEnumByName(String name) {
        for (ProjectStatusEnum e : ProjectStatusEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
