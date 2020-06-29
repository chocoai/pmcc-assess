package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum ProjectStatusEnum {
    PAUSE_APPLY("pauseapply", "暂停申请"),
    DRAFT("draft", "草稿"),
    PAUSE("pause", "暂停"),
    NONE("none", "无操作"),
    WAIT("wait", "待处理"),
    STARTAPPLY("startapply", "启动申请"),
    NORMAL("normal", "正常"),
    CLOSE("close", "关闭"),
    FINISH("finish", "完成"),
    FILED_AWAY("filed_away", "归档"),
    PLAN("planExecute", "安排计划"),
    TASK("task", "提交成果"),
    HANGUP("hangup", "挂起"),
    RUNING("runing","正在进行中");

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

    public static ProjectStatusEnum getEnumByKey(String key) {
        for (ProjectStatusEnum e : ProjectStatusEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getProjectStatusEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (ProjectStatusEnum e : ProjectStatusEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getKey()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public static List<KeyValueDto> getProjectStatusEnumList(String... keys) {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (String key : keys) {
            ProjectStatusEnum anEnum = getEnumByKey(key);
            if (anEnum != null) {
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(anEnum.getKey()));
                keyValueDto.setValue(anEnum.getName());
                keyValueDtos.add(keyValueDto);
            }
        }
        return keyValueDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
